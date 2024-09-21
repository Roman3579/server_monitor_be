package com.unionutilities.unionutilities.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ApiCallError {

    NOT_FOUND(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()),
    CONNECTION_ERROR(null, "Failed to connect to server."),
    UNKNOWN(null, "Unknown error");

    private final Integer errorCode;
    private final String errorDescription;

    ApiCallError(Integer errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    @JsonCreator
    public static ApiCallError forValues(
            @JsonProperty("errorCode") Integer errorCode,
            @JsonProperty("errorDescription") String errorDescription) {
        if(errorCode == null || errorDescription == null){
            return null;
        }
        for (ApiCallError error : ApiCallError.values()) {
            if (error.errorCode.equals(errorCode) && error.errorDescription.equals(errorDescription)){
                return error;
            }
        }
        return null;
    }
}
