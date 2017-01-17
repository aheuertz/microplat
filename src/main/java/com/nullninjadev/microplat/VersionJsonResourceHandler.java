package com.nullninjadev.microplat;

import spark.Request;
import spark.Response;

/**
 * Created by alan on 12/30/2016.
 */
public class VersionJsonResourceHandler extends JsonResourceHandler {
    private final VersionProvider versionProvider;

    VersionJsonResourceHandler(VersionProvider versionProvider) {
        this.versionProvider = versionProvider;
    }

    @Override
    public Object handleRequest(Request request, Response response) {
        return versionProvider.getVersion();
    }
}
