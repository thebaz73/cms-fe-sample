package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.ContentPage;
import model.Site;
import model.User;
import play.libs.F;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSAuthScheme;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import util.Configuration;
import util.ConfigurationException;

import java.util.concurrent.TimeUnit;

/**
 * Application
 * Created by thebaz on 23/04/15.
 */
public class Application extends SparkleController {
    private static final String WS_URL = "http://ec2-52-17-75-152.eu-west-1.compute.amazonaws.com";
    private static Configuration configuration = Configuration.getInstance();

    public static Result index() {
        return show("contents/");
    }
    public static Result about() {
        return show("contents/about");
    }

    public static Result contact() {
        User user = null;
        Site site = null;
        try {
            user = configuration.getUser();
            site = configuration.getSite();
        } catch (ConfigurationException e) {
            user = new User();
            site = new Site();
        }
        return ok(views.html.contact.render(site.getName(), "", user, site));
    }

    public static Result show(String uri) {
        User user = null;
        Site site = null;
        F.Promise<WSResponse> response = null;
        try {
            user = configuration.getUser();
            site = configuration.getSite();
            String serviceUrl;
            if (uri.equals("contents/") || uri.startsWith("contents/?")) {
                serviceUrl = String.format("%s/api/contents/%s", WS_URL, site.getId());
            } else {
                serviceUrl = String.format("%s/api/contents/%s/%s", WS_URL, site.getId(), uri);
            }
            response = WS.url(serviceUrl)
                    .setAuth(user.getUsername(), user.getPassword(), WSAuthScheme.BASIC)
                    .get();
        } catch (ConfigurationException e) {
            user = new User();
            site = new Site();
        }
        return ok(views.html.index.render(site.getName(), "", user, site, toContentPage(response)));
    }
}
