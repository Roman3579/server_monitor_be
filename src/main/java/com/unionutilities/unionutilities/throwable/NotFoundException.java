package com.unionutilities.unionutilities.throwable;

public class NotFoundException extends ApiCallException {
    public NotFoundException() {
        super("Not found.");
    }
}
