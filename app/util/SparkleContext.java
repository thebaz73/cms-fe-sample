package util;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * SparkleContext
 * Created by bazzoni on 25/05/2015.
 */
public class SparkleContext {
    private final Config config;

    public SparkleContext() {
        this.config = ConfigFactory.load();
        config.checkValid(ConfigFactory.defaultReference(), "sparkle");
    }

    public String getWebmaster() {
        return config.getString("sparkle.site.webmaster");
    }

    public String getSite() {
        return config.getString("sparkle.site.uri");
    }

    public String getRegistrationURI() {
        return config.getString("sparkle.ws.registration.uri");
    }

    public String getContentURI() {
        return config.getString("sparkle.ws.content.uri");
    }

    public Config getConfig() {
        return config;
    }

    public boolean isActive(String property) {
        return config.getBoolean(String.format("sparkle.%s.active", property));
    }

    public String getUri(String property) {
        return config.getString(String.format("sparkle.%s.uri", property));
    }
}
