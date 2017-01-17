package com.nullninjadev.microplat;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alan on 12/30/2016.
 */
public class PropertiesProvider {
    private static Properties properties;
    private final Logger logger;
    private String propertiesPath;

    PropertiesProvider(Logger logger) {
        this(logger, "config.properties");
    }

    PropertiesProvider(Logger logger, String propertiesPath) {
        this.logger = logger;
        this.propertiesPath = propertiesPath;
    }

    private synchronized void initProperties() {
        logger.fine(this.getClass() + "::initProperties()");
        if (properties == null) {
            synchronized (Properties.class) {
                logger.fine(this.getClass() + "::initProperties() -> in synchronized block");
                if (properties == null) {
                    logger.fine("Parsing properties from \"" + propertiesPath + "\"");
                    properties = new Properties();
                    InputStream input = null;

                    try {
                        input = new FileInputStream(propertiesPath);
                        properties.load(input);
                    } catch (IOException ex) {
                        logger.warning(ex.toString());
                    } finally {
                        if (input != null) {
                            try {
                                input.close();
                            } catch (IOException e) {
                                logger.warning(e.toString());
                            }
                        }
                    }
                }
            }
        }
    }

    public Properties getProperties() {
        initProperties();
        logger.fine(this.getClass() + "::getProperties()");
        return properties;
    }

    public String getProperty(String key) {
        initProperties();
        Level level;
        logger.fine(this.getClass() + "::getProperty(\"" + key + "\")");
        return properties.getProperty(key);
    }
}
