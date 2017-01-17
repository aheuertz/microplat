package com.nullninjadev.microplat;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by alan on 12/30/2016.
 */
public abstract class JsonResourceHandler implements Route {
    @Override
    public final Object handle(Request request, Response response) throws Exception {
        response.header("Content-Type", "application/json");
        return new Gson().toJson(handleRequest(request, response));
    }

    protected abstract Object handleRequest(Request request, Response response);
}
