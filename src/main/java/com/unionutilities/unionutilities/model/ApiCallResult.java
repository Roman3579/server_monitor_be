package com.unionutilities.unionutilities.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiCallResult {
    private String targetUrl;
    private AppInfoModel appInfoModel;
}
