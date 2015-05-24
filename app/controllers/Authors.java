package controllers;

import model.Site;
import model.User;
import play.Logger;
import play.libs.F;
import play.libs.ws.WS;
import play.libs.ws.WSAuthScheme;
import play.libs.ws.WSResponse;
import play.mvc.Result;
import util.Configuration;
import util.ConfigurationException;

/**
 * SearchEngine
 * Created by bazzoni on 29/04/2015.
 */
public class Authors extends SparkleController {
    private static final String WS_URL = "http://192.168.108.130:9000";
    private static Configuration configuration = Configuration.getInstance();

    public static Result show(String id) throws ConfigurationException {
        User user;
        Site site;
        F.Promise<WSResponse> response = null;
        try {
            user = configuration.getUser();
            site = configuration.getSite();

            String serviceUrl = String.format("%s/api/authors/%s/%s", WS_URL, site.getId(), id);
            response = WS.url(serviceUrl)
                    .setAuth(user.getUsername(), user.getPassword(), WSAuthScheme.BASIC)
                    .get();
        } catch (ConfigurationException e) {
            Logger.error("Getting author contents", e);
            return ok(views.html.notReady.render(configuration.getSiteName()));
        }
        return ok(views.html.authors.render(site.getName(), "", user, site, toContentPage(response)));
    }

}
