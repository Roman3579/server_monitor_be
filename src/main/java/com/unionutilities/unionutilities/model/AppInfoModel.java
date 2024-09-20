package com.unionutilities.unionutilities.model;

import com.unionutilities.unionutilities.client.ApiCallError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppInfoModel {
    private String appType;
    private String description;
    private String frontendUrl;
    private ApiCallError apiCallError;

    public AppInfoModel(ApiCallError error){
        this.apiCallError = error;
    }

}
