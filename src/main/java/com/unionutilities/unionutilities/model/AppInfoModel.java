package com.unionutilities.unionutilities.model;

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

    private AppInfoModel(String errorValue){
        this.appType = errorValue;
        this.description = errorValue;
        this.frontendUrl = errorValue;
    }

    public static AppInfoModel error() {
        return new AppInfoModel("error");
    }
}
