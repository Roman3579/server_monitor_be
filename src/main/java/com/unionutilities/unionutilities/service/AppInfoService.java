package com.unionutilities.unionutilities.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.unionutilities.unionutilities.client.AppInfoRestClient;
import com.unionutilities.unionutilities.config.EndpointsConfig;
import com.unionutilities.unionutilities.config.IpConfig;
import com.unionutilities.unionutilities.config.PortsConfig;
import com.unionutilities.unionutilities.model.ApiCallResult;
import com.unionutilities.unionutilities.model.ApiCallResultContainer;
import com.unionutilities.unionutilities.model.AppInfoModel;
import com.unionutilities.unionutilities.throwable.ConnectionFailedException;
import com.unionutilities.unionutilities.throwable.NotFoundException;
import com.unionutilities.unionutilities.throwable.UnknownApiException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AppInfoService {

    private final IpConfig ipConfig;
    private final PortsConfig portsConfig;
    private final EndpointsConfig endpointsConfig;
    private final AppInfoRestClient client;
    private final FileService fileService;

    public Boolean refreshAppInfos() {
        try {
            fileService.writeResultsToFile(getAllAppInfos());
            return true;
        } catch (IOException e){
            log.error("An error occurred when refreshing app info: {}", e.getMessage());
            return false;
        }
    }

    public Optional<ApiCallResultContainer> getStoredInfos() {
        try {
            return Optional.of(fileService.getStoredResults());
        } catch (IOException e){
            log.error("An error occurred when retrieving stored results: {}", e.getMessage());
            return Optional.empty();
        }
    }

    public List<ApiCallResult> getAllAppInfos() {
        List<ApiCallResult> results = new ArrayList<>();
        for(String url : buildTargetUrls()){
            try {
                results.add(ApiCallResult.success(url, client.getAppInfo(url)));
            } catch (NotFoundException | ConnectionFailedException | UnknownApiException exception){
                results.add(ApiCallResult.error(url, exception.getMessage()));
            }
        }
        return results;
    }

    public Resource getLogs(String url){
        return client.getAppLogs(url);
    }

    public Boolean updateAppInfo(String targetUrl, AppInfoModel appInfoModel){
        try {
            client.updateAppInfo(targetUrl, appInfoModel);
        } catch (JsonProcessingException e){
            log.info("Failed to process app info JSON: {}", e.getMessage());
            return false;
        }
        return true;
    }

    private List<String> buildTargetUrls() {
        List<String> allTargets = new ArrayList<>();
        for(String ip : ipConfig.getIpAddresses()){
            for(Integer port = portsConfig.getLowerBound(); port <= portsConfig.getUpperBound(); port++){
                for(String endpoint : endpointsConfig.getInfoEndpoints()){
                    allTargets.add(buildUrl(ip, port.toString(), endpoint));
                }
            }
        }
        return allTargets;
    }

    private String buildUrl(String ip, String port, String endpoint){
        return ip + ":" + port + endpoint;
    }

}
