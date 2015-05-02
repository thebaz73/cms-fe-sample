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

import java.util.concurrent.TimeUnit;

/**
 * SearchEngine
 * Created by bazzoni on 29/04/2015.
 */
public class Tags extends SparkleController {
    private static final String WS_URL = "http://ec2-52-17-75-152.eu-west-1.compute.amazonaws.com";
    private static Configuration configuration = Configuration.getInstance();

    public static Result tags() throws ConfigurationException {
        User user;
        Site site;
        F.Promise<WSResponse> response;
        try {
            user = configuration.getUser();
            site = configuration.getSite();

            String serviceUrl = String.format("%s/api/tags/%s", WS_URL, site.getId());
            response = WS.url(serviceUrl)
                    .setAuth(user.getUsername(), user.getPassword(), WSAuthScheme.BASIC)
                    .get();
            return ok(response.get(30, TimeUnit.SECONDS).asJson());
        } catch (ConfigurationException e) {
            Logger.error("Getting tags", e);
        }
        return ok();
    }

    public static Result show(String tag) throws ConfigurationException {
        User user;
        Site site;
        F.Promise<WSResponse> response = null;
        try {
            user = configuration.getUser();
            site = configuration.getSite();

            String serviceUrl = String.format("%s/api/contents/%s?tag=%s", WS_URL, site.getId(), tag);
            response = WS.url(serviceUrl)
                    .setAuth(user.getUsername(), user.getPassword(), WSAuthScheme.BASIC)
                    .get();
        } catch (ConfigurationException e) {
            Logger.error("Getting tag contents", e);
            return ok(views.html.notReady.render(configuration.getSiteName()));
        }
        return ok(views.html.tags.render(site.getName(), "", user, site, toContentPage(response)));
    }

}
