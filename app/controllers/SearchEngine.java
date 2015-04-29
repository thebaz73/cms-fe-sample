package controllers;

import model.ContentPage;
import model.Site;
import model.User;
import play.libs.F;
import play.libs.ws.WS;
import play.libs.ws.WSAuthScheme;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import util.Configuration;
import util.ConfigurationException;

import java.util.ArrayList;

/**
 * SearchEngine
 * Created by bazzoni on 29/04/2015.
 */
public class SearchEngine extends SparkleController {
    private static final String WS_URL = "http://ec2-52-17-75-152.eu-west-1.compute.amazonaws.com";
    private static Configuration configuration = Configuration.getInstance();

    public static Result search(String q) throws ConfigurationException {
        User user = configuration.getUser();
        Site site = configuration.getSite();
        String serviceUrl = String.format("%s/api/authors/%s", WS_URL, user.getId());
        F.Promise<WSResponse> response = WS.url(serviceUrl)
                .setAuth(user.getUsername(), user.getPassword(), WSAuthScheme.BASIC)
                .get();
        ContentPage contents = toContentPage(response);
        return ok(views.html.search.render(site.getName(), "", user, site, contents.toContentList()));
    }}
