package com.unionutilities.unionutilities.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiCallResultContainer {
    private List<ApiCallResult> apiCallResults;
}
