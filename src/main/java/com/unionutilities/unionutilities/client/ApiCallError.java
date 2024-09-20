package com.unionutilities.unionutilities.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
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
