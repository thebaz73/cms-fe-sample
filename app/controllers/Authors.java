package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import util.ConfigurationException;

import java.util.ArrayList;

/**
 * SearchEngine
 * Created by bazzoni on 29/04/2015.
 */
public class Authors extends Controller {

    public static Result show(String id) throws ConfigurationException {
        return ok(views.html.authors.render(null, "", null, null, new ArrayList<>()));
    }}
