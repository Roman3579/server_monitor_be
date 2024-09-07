package com.unionutilities.unionutilities.service;

import com.unionutilities.unionutilities.client.AppInfoRestClient;
import com.unionutilities.unionutilities.config.EndpointsConfig;
import com.unionutilities.unionutilities.config.IpConfig;
import com.unionutilities.unionutilities.config.PortsConfig;
import com.unionutilities.unionutilities.model.ApiCallResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AppInfoService {

    private final IpConfig ipConfig;
    private final PortsConfig portsConfig;
    private final EndpointsConfig endpointsConfig;
    private final AppInfoRestClient client;

    public List<ApiCallResult> getAllAppInfos() {
        List<ApiCallResult> results = new ArrayList<>();
        for(String url : buildTargetUrls()){
            results.add(new ApiCallResult(url, client.getAppInfo(url)));
        }
        return results;
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
