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
    private String frontendLink;

}
