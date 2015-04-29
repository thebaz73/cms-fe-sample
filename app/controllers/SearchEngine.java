package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import util.ConfigurationException;

import java.util.ArrayList;

/**
 * SearchEngine
 * Created by bazzoni on 29/04/2015.
 */
public class SearchEngine extends Controller {

    public static Result search(String q) throws ConfigurationException {
        return ok(views.html.search.render(null, "", null, null, new ArrayList<>()));
    }}
