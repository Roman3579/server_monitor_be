package com.unionutilities.unionutilities.service;

import com.unionutilities.unionutilities.client.AppInfoRestClient;
import com.unionutilities.unionutilities.config.EndpointsConfig;
import com.unionutilities.unionutilities.config.IpConfig;
import com.unionutilities.unionutilities.config.PortsConfig;
import com.unionutilities.unionutilities.model.AppInfoModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class AppInfoService {

    private final IpConfig ipConfig;
    private final PortsConfig portsConfig;
    private final EndpointsConfig endpointsConfig;
    private final AppInfoRestClient client;

    public Map<String, AppInfoModel> getAllAppInfos() {
        Map<String, AppInfoModel> results = new HashMap<>();
        List<String> targetUrls = buildTargetUrls();
        for (String url : targetUrls){
            results.put(url, client.getAppInfo(url));
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
