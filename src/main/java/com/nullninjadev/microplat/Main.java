package com.nullninjadev.microplat;

import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alan on 12/30/2016.
 */
public class Main {
    public static void main(String[] args) {
        String currentWorkingDirectory = Paths.get(".").toAbsolutePath().normalize().toString();

        Logger logger = Logger.getLogger("com.nullninjadev.microplat");
        logger.setLevel(Level.ALL);
        logger.info("Initializing Microplat");

        PropertiesProvider propertiesProvider = new PropertiesProvider(logger);
        try {
            String logPath = "/var/log/microplat/" + propertiesProvider.getProperty("service_name") + ".log";
            FileHandler fileHandler = new FileHandler(logPath);
            logger.addHandler(fileHandler);
        } catch (IOException exception) {
            System.out.println("An error occurred while initializing logger.");
            System.out.println(exception.getMessage());
            return;
        }

        Spark.exception(NotAcceptableException.class, new NotAcceptableExceptionHandler());
        initializeRoutes(propertiesProvider, currentWorkingDirectory);
    }

    private static void initializeRoutes(PropertiesProvider propertiesProvider, String currentWorkingDirectory) {
        // Version
        VersionProvider versionProvider = new VersionProvider(propertiesProvider);
        Route versionRoute = new ContentTypeAwareResourceHandler.Builder()
                .addRoute(ContentType.APPLICATION_JSON, new VersionJsonResourceHandler(versionProvider))
                .build();
        Spark.get("/version", versionRoute);

        // Current Working Directory
        Spark.get("/cwd", (request, response) -> currentWorkingDirectory);

        // Properties
        Route propertiesRoute = new ContentTypeAwareResourceHandler.Builder()
                .addRoute(ContentType.APPLICATION_JSON, new PropertiesJsonResourceHandler(propertiesProvider))
                .build();
        Spark.get("/properties", propertiesRoute);
    }
}
