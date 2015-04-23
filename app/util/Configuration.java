package util;

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
    public static final String WS_URL = "http://ec2-52-16-32-200.eu-west-1.compute.amazonaws.com";
    private static Configuration instance;
    private User user;
    private Site site;

    private Configuration() {
    }

    public static Configuration getInstance() {
        if (instance == null) {
            instance = new Configuration();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public Site getSite() {
        return site;
    }

    public void loadData() {
        WSRequestHolder userRequest = WS.url(String.format("%s/public/user", WS_URL));
        WSRequestHolder siteRequest = WS.url(String.format("%s/api/site", WS_URL));
        userRequest.setQueryParameter("param", "bazzoni.marco@gmail.com").get()
                .map(userResponse -> {
                    user = Json.fromJson(userResponse.asJson(), User.class);
                    siteRequest.setAuth(user.getUsername(), user.getPassword(), WSAuthScheme.BASIC)
                            .setQueryParameter("param", "halfblood.com")
                            .get()
                            .map(siteResponse -> {
                                site = Json.fromJson(siteResponse.asJson(), Site.class);
                                return site;
                            });
                    return user;
                });
    }
}
