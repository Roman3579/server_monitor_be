package com.unionutilities.unionutilities.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiCallResult {
    private String targetUrl;
    private AppInfoModel appInfoModel;
    private String errorMessage;

    public static ApiCallResult success(String url, AppInfoModel appInfoModel) {
        ApiCallResult apiCallResult = new ApiCallResult();
        apiCallResult.setTargetUrl(url);
        apiCallResult.setAppInfoModel(appInfoModel);
        return apiCallResult;
    }

    public static ApiCallResult error(String url, String errorMessage){
        ApiCallResult apiCallResult = new ApiCallResult();
        apiCallResult.setTargetUrl(url);
        apiCallResult.setErrorMessage(errorMessage);
        return apiCallResult;
    }
}
