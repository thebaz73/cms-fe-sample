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
public class SearchEngine extends SparkleController {
    private static Configuration configuration = Configuration.getInstance();

    public static Result search(String q) throws ConfigurationException {
        User user;
        Site site;
        F.Promise<WSResponse> response;
        try {
            user = configuration.getUser();
            site = configuration.getSite();
            String serviceUrl = String.format("%s/api/search?q=%s", configuration.getSparkleContext().getContentURI(), q);
            response = WS.url(serviceUrl)
                    .setAuth(user.getUsername(), user.getPassword(), WSAuthScheme.BASIC)
                    .get();
        } catch (ConfigurationException e) {
            Logger.error("Searching contents", e);
            return ok(views.html.notReady.render(configuration.getSiteName()));
        }
        return ok(views.html.search.render(site.getName(), "", user, site, toContentPage(response), configuration.getSparkleContext()));
    }}
