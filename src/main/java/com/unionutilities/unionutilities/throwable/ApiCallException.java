package com.unionutilities.unionutilities.throwable;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class ApiCallException extends Exception {
    public ApiCallException(String message){
        super(message);
    }
}
