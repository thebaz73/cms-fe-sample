package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Mailer
 * Created by thebaz on 26/04/15.
 */
public class Mailer extends Controller {

    public static Result sendMail() {
        Http.RequestBody body = request().body();
        //Logger.debug(body.asJson());
        return ok("Got json: " + body.asJson());
    }
}
