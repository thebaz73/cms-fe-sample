package controllers;

import model.Content;
import model.User;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSAuthScheme;
import play.mvc.Controller;
import play.mvc.Result;
import scala.collection.immutable.List;
import util.Configuration;

/**
 * Application
 * Created by thebaz on 23/04/15.
 */
public class Application extends Controller {
    private static final String WS_URL = "http://ec2-52-17-34-133.eu-west-1.compute.amazonaws.com/";

    public static Result index() {
        return show("/");
    }

    public static Result show(String uri) {
        User user = Configuration.getInstance().getUser();
        String siteName = Configuration.getInstance().getSiteName();
        Object contents = WS.url(String.format("%s/api/contents", WS_URL))
                .setAuth(user.getUsername(), user.getPassword(), WSAuthScheme.BASIC)
                .get().map(response -> Json.fromJson(response.asJson(), Object.class));
        return ok(views.html.index.render(siteName, uri, (List<Content>) contents));
    }
}
