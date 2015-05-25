package util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * SparkleContext
 * Created by bazzoni on 25/05/2015.
 */
public class SparkleContext {
    private Config config;

    public SparkleContext() {
        this.config = ConfigFactory.load();
        config.checkValid(ConfigFactory.defaultReference(), "sparkle");
    }

    public String getRegistrationURI() {
        return config.getString("sparkle.ws.registration.uri");
    }

    public String getContentURI() {
        return config.getString("sparkle.ws.content.uri");
    }
}
