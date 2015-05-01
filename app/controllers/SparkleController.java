package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import model.ContentPage;
import play.libs.F;
import play.libs.Json;
import play.libs.ws.WSResponse;
import play.mvc.Controller;

import java.util.concurrent.TimeUnit;

/**
 * SparkleController
 * Created by thebaz on 29/04/15.
 */
public class SparkleController extends Controller {
    protected static ContentPage toContentPage(F.Promise<WSResponse> response) {
        ContentPage contents = new ContentPage();
        if(response != null) {
            WSResponse wsResponse = response.get(30, TimeUnit.SECONDS);
            if(wsResponse.getStatus() == 200) {
                JsonNode embedded = wsResponse.asJson().get("_embedded");
                contents = Json.fromJson(embedded, ContentPage.class);
            }
        }
        return contents;
    }
}
