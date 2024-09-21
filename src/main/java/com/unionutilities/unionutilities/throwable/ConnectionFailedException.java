package com.unionutilities.unionutilities.throwable;

public class ConnectionFailedException extends ApiCallException {
    public ConnectionFailedException() {
        super("Connection failed.");
    }
}
