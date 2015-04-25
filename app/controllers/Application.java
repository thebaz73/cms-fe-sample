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
public class Application extends Controller {
    private static final String WS_URL = "http://ec2-52-17-75-152.eu-west-1.compute.amazonaws.com";
    private static Configuration configuration = Configuration.getInstance();

    public static Result index() throws ConfigurationException {
        return show("/");
    }

    public static Result show(String uri) throws ConfigurationException {
        User user = configuration.getUser();
        Site site = configuration.getSite();
        String serviceUrl;
        if(uri.equals("contact")) {
            return ok(views.html.contact.render(site.getName(), "", user, site));
        }
        else if (uri.equals("/") || uri.startsWith("/?")) {
            serviceUrl = String.format("%s/api/contents/%s", WS_URL, site.getId());
        } else {
            serviceUrl = String.format("%s/api/contents/%s/%s", WS_URL, site.getId(), uri);
        }
        F.Promise<WSResponse> response = WS.url(serviceUrl)
                .setAuth(user.getUsername(), user.getPassword(), WSAuthScheme.BASIC)
                .get();
        JsonNode embedded = response.get(5, TimeUnit.SECONDS).asJson().get("_embedded");
        ContentPage contents = Json.fromJson(embedded, ContentPage.class);
        if (uri.equals("/") || uri.startsWith("/?")) {
            return ok(views.html.index.render(site.getName(), "", user, site, contents.toContentList()));
        } else {
            return ok(views.html.content.render(site.getName(), "", user, site, contents.toContent()));
        }
    }
}
