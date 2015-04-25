package controllers;

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

/**
 * Application
 * Created by thebaz on 23/04/15.
 */
public class Application extends Controller {
    private static final String WS_URL = "http://ec2-52-17-34-133.eu-west-1.compute.amazonaws.com/";

    public static Result index() throws ConfigurationException {
        return show("/");
    }

    public static Result show(String uri) throws ConfigurationException {
        User user = Configuration.getInstance().getUser();
        Site site = Configuration.getInstance().getSite();
        String serviceUrl;
        if (uri.equals("/") || uri.startsWith("/?")) {
            serviceUrl = String.format("%s/api/contents/%s", WS_URL, site.getId());
        } else {
            serviceUrl = String.format("%s/api/contents/%s/%s", WS_URL, site.getId(), uri);
        }
        F.Promise<WSResponse> response = WS.url(serviceUrl)
                .setAuth(user.getUsername(), user.getPassword(), WSAuthScheme.BASIC)
                .get();
        ContentPage contents = Json.fromJson(response.get(0).asJson(), ContentPage.class);
        if (contents.size() == 1) {
            return ok(views.html.content.render(site.getName(), uri, contents.toContent()));
        } else {
            return ok(views.html.index.render(site.getName(), uri, contents.toContentList()));
        }
    }
}
