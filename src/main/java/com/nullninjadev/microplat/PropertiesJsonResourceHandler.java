package com.nullninjadev.microplat;

import spark.Request;
import spark.Response;

/**
 * Created by alan on 12/30/2016.
 */
public class PropertiesJsonResourceHandler extends JsonResourceHandler {
    private final PropertiesProvider propertiesProvider;

    PropertiesJsonResourceHandler(PropertiesProvider propertiesProvider) {
        this.propertiesProvider = propertiesProvider;
    }

    @Override
    protected Object handleRequest(Request request, Response response) {
        System.out.println(this.getClass() + "::handleRequest(request, response)");
        return propertiesProvider.getProperties();
    }
}
