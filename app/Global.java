import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.libs.F;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import util.Configuration;

import static play.mvc.Results.*;

/**
 * Global
 * Created by thebaz on 23/04/15.
 */
public class Global extends GlobalSettings {

    @Override
    public F.Promise<Result> onBadRequest(RequestHeader request, String error) {
        return F.Promise.<Result>pure(badRequest("Don't try to hack the URI!"));
    }

    @Override
    public F.Promise<Result> onHandlerNotFound(RequestHeader request) {
        return F.Promise.<Result>pure(notFound(
                views.html.notFoundPage.render(Configuration.getInstance().getSiteName(), request.uri())
        ));
    }

    @Override
    public F.Promise<Result> onError(RequestHeader request, Throwable t) {
        return F.Promise.<Result>pure(internalServerError(
                views.html.errorPage.render(Configuration.getInstance().getSiteName(), t)
        ));
    }

    @Override
    public void onStart(Application application) {
        Logger.info("Sparkle FrontEnd starting...");
        Configuration.getInstance().loadData();
        super.onStart(application);
    }
}
