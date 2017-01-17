package com.nullninjadev.microplat;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alan on 1/1/2017.
 */
public class HydraError {
    @SerializedName("@context")
    private final String context = "http://www.w3.org/ns/hydra/context.jsonld";

    @SerializedName("@type")
    private final String type = "Error";

    private final String title;
    private final String description;

    public HydraError(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
