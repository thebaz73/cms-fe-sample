package controllers;

import model.Site;
import model.User;
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
    private static final String WS_URL = "http://ec2-52-17-75-152.eu-west-1.compute.amazonaws.com";
    private static Configuration configuration = Configuration.getInstance();

    public static Result search(String q) throws ConfigurationException {
        User user;
        Site site;
        F.Promise<WSResponse> response = null;
        try {
            user = configuration.getUser();
            site = configuration.getSite();
            String serviceUrl = String.format("%s/api/search/%s", WS_URL, user.getId());
            response = WS.url(serviceUrl)
                    .setAuth(user.getUsername(), user.getPassword(), WSAuthScheme.BASIC)
                    .get();
        } catch (ConfigurationException e) {
            user = new User();
            site = new Site();
        }
        return ok(views.html.search.render(site.getName(), "", user, site, toContentPage(response)));
    }}
