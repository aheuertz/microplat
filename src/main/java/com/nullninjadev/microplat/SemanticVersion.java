package com.nullninjadev.microplat;

/**
 * Created by alan on 12/30/2016.
 */
public class SemanticVersion implements Version {
    private final int major;
    private final int minor;
    private final int patch;

    SemanticVersion(int major) {
        this(major, 0, 0);
    }

    SemanticVersion(int major, int minor) {
        this(major, minor, 0);
    }

    SemanticVersion(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    SemanticVersion(String version) {
        String[] versionParts = version.split("\\.");
        if (versionParts.length != 3) {
            throw new IllegalArgumentException("Version must be formatted as MAJOR.MINOR.PATCH");
        }
        this.major = Integer.parseInt(versionParts[0]);
        this.minor = Integer.parseInt(versionParts[1]);
        this.patch = Integer.parseInt(versionParts[2]);
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + patch;
    }
}
