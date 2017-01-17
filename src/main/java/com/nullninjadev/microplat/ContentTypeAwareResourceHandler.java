package com.nullninjadev.microplat;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alan on 12/30/2016.
 */
public class ContentTypeAwareResourceHandler implements Route {
    static class Builder {
        private Map<ContentType, Route> contentTypeRouteMap;

        Builder() {
            contentTypeRouteMap = new HashMap<>();
        }

        Builder addRoute(ContentType contentType, Route route) {
            contentTypeRouteMap.put(contentType, route);
            return this;
        }

        ContentTypeAwareResourceHandler build() {
            return new ContentTypeAwareResourceHandler(contentTypeRouteMap);
        }
    }

    private final Map<ContentType, Route> contentTypeRouteMap;

    private ContentTypeAwareResourceHandler(Map<ContentType, Route> contentTypeRouteMap) {
        this.contentTypeRouteMap = contentTypeRouteMap;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String serializedContentType = request.headers("Accept");
        ContentTypeMapper contentTypeMapper = new ContentTypeMapper();
        ContentType contentType = contentTypeMapper.fromString(serializedContentType);
        Route handler = contentTypeRouteMap.get(contentType);
        return handler.handle(request, response);
    }
}
