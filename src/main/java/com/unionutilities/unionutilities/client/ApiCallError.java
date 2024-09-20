package com.unionutilities.unionutilities.client;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ApiCallError {

    NOT_FOUND(404, "Not found."),
    CONNECTION_ERROR(null, "Failed to connect to server."),
    UNKNOWN(null, "Unknown error");

    private final Integer errorCode;
    private final String errorDescription;

    ApiCallError(Integer errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }
}
