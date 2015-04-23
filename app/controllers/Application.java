package controllers;

import model.Site;
import play.mvc.Controller;
import play.mvc.Result;
import util.Configuration;

/**
 * Application
 * Created by thebaz on 23/04/15.
 */
public class Application extends Controller {
    public static Result index() {

        return show("/");
    }

    public static Result show(String uri) {
        Site site = Configuration.getInstance().getSite();
        return ok(views.html.index.render(site.getName(), uri));
    }
}
