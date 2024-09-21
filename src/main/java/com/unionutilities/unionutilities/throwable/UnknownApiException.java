package com.unionutilities.unionutilities.throwable;

public class UnknownApiException extends ApiCallException {
    public UnknownApiException() {
        super("Unknown error.");
    }
}
