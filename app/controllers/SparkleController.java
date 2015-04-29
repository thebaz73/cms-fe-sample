package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.Content;
import model.ContentPage;
import model.User;
import play.libs.F;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSAuthScheme;
import play.libs.ws.WSResponse;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * SparkleController
 * Created by thebaz on 29/04/15.
 */
public class SparkleController extends Controller {
    protected static ContentPage toContentPage(F.Promise<WSResponse> response) {
        ContentPage contents = new ContentPage();
        WSResponse wsResponse = response.get(5, TimeUnit.SECONDS);
        if(wsResponse.getStatus() == 200) {
            JsonNode embedded = wsResponse.asJson().get("_embedded");
            contents = Json.fromJson(embedded, ContentPage.class);
        }
        return contents;
    }
}
