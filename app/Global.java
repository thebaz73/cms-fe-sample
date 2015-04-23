import play.Application;
import play.GlobalSettings;
import util.Configuration;

/**
 * Global
 * Created by thebaz on 23/04/15.
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application application) {
        Configuration.getInstance().loadData();
        super.onStart(application);
    }
}
