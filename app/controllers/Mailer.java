package controllers;

import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Mailer
 * Created by thebaz on 26/04/15.
 */
public class Mailer extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public static Result send() {
        Http.RequestBody body = request().body();
        return ok("Got json: " + body.asJson());
    }
}
