package com.unionutilities.unionutilities.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiCallResult {
    private String targetUrl;
    private AppInfoModel appInfoModel;
}
