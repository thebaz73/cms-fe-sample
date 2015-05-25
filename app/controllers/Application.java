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
 * Application
 * Created by thebaz on 23/04/15.
 */
public class Application extends SparkleController {
    private static Configuration configuration = Configuration.getInstance();

    public static Result index() {
        return show("contents/");
    }
    public static Result about() {
        return show("/about");
    }

    public static Result contact() {
        User user;
        Site site;
        try {
            user = configuration.getUser();
            site = configuration.getSite();
        } catch (ConfigurationException e) {
            Logger.error("Getting contact content", e);
            return ok(views.html.notReady.render(configuration.getSiteName()));
        }
        return ok(views.html.contact.render(site.getName(), "", user, site));
    }

    public static Result show(String uri) {
        User user;
        Site site;
        F.Promise<WSResponse> response = null;
        try {
            user = configuration.getUser();
            site = configuration.getSite();
            String serviceUrl;
            if (uri.equals("contents/") || uri.startsWith("contents/?")) {
                serviceUrl = String.format("%s/api/contents/%s", sparkleContext.getContentURI(), site.getId());
            } else {
                serviceUrl = String.format("%s/api/contents/%s/%s", sparkleContext.getContentURI(), site.getId(), uri);
            }
            response = WS.url(serviceUrl)
                    .setAuth(user.getUsername(), user.getPassword(), WSAuthScheme.BASIC)
                    .get();
        } catch (ConfigurationException e) {
            Logger.error("Getting contents: " + uri, e);
            return ok(views.html.notReady.render(configuration.getSiteName()));
        }
        if (uri.equals("contents/") || uri.startsWith("contents/?")) {
            return ok(views.html.index.render(site.getName(), "", user, site, toContentPage(response)));
        } else {
            return ok(views.html.content.render(site.getName(), "", user, site, toContentPage(response)));
        }
    }
}
