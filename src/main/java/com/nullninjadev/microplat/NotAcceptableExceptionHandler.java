package com.nullninjadev.microplat;

import com.google.gson.Gson;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;

/**
 * Created by alan on 1/1/2017.
 */
public class NotAcceptableExceptionHandler implements ExceptionHandler {
    @Override
    public void handle(Exception e, Request request, Response response) {
        response.status(406);
        response.body(new Gson().toJson(new HydraError("Not Acceptable", "The Content Type specified in the \"" + HttpHeader.ACCEPT + "\" header is not supported for this resource.")));
        response.header(HttpHeader.CONTENT_TYPE, "application/json");
    }
}
