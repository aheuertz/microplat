package com.nullninjadev.microplat;

/**
 * Created by alan on 12/30/2016.
 */
public class VersionProvider {
    private final PropertiesProvider propertiesProvider;

    public VersionProvider(PropertiesProvider propertiesProvider) {
        this.propertiesProvider = propertiesProvider;
    }

    public Version getVersion() {
        return new SemanticVersion(propertiesProvider.getProperty("version"));
    }
}
