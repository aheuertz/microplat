package com.nullninjadev.microplat;

/**
 * Created by alan on 12/30/2016.
 */
public class ContentTypeMapper {
    ContentType fromString(String contentType) throws NotAcceptableException {
        switch (contentType.toUpperCase()) {
            case "APPLICATION/JSON":
                return ContentType.APPLICATION_JSON;
            case "TEXT/HTML":
                return ContentType.TEXT_HTML;
        }
        throw new NotAcceptableException();
    }
}
