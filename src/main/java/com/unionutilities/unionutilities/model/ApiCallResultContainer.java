package com.unionutilities.unionutilities.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiCallResultContainer {
    private List<ApiCallResult> apiCallResults;
}
