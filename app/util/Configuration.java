package util;

import com.typesafe.config.Config;
import model.Site;
import model.User;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSAuthScheme;
import play.libs.ws.WSRequestHolder;

/**
 * Config
 * Created by thebaz on 23/04/15.
 */
public class Configuration {
    private static Configuration instance;
    private final SparkleContext sparkleContext;
    private User user;
    private Site site;

    private Configuration() {
        sparkleContext = new SparkleContext();
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public void loadData() {
        WSRequestHolder userRequest = WS.url(String.format("%s/public/user", sparkleContext.getRegistrationURI()));
        WSRequestHolder siteRequest = WS.url(String.format("%s/api/site", sparkleContext.getRegistrationURI()));

        userRequest.setQueryParameter("param", sparkleContext.getWebmaster()).get()
                .map(userResponse -> {
                    user = Json.fromJson(userResponse.asJson(), User.class);
                    siteRequest.setAuth(user.getUsername(), user.getPassword(), WSAuthScheme.BASIC)
                            .setQueryParameter("param", sparkleContext.getSite())
                            .get()
                            .map(siteResponse -> {
                                site = Json.fromJson(siteResponse.asJson(), Site.class);
                                return site;
                            });
                    return user;
                });
    }

    public User getUser() throws ConfigurationException {
        if (user == null) {
            throw new ConfigurationException("User is null");
        }
        return user;
    }

    public Site getSite() throws ConfigurationException {
        if (site == null) {
            throw new ConfigurationException("Site is null");
        }
        return site;
    }

    public String getSiteName() {
        return (site == null ? "" : site.getName());
    }

    public SparkleContext getSparkleContext() {
        return sparkleContext;
    }

    public Config getConfig() {
        return sparkleContext.getConfig();
    }
}
