package foi.hr.rscandroid;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import android.app.Application;

/**
 * @author Igor Tepavac
 *         igor.tepavac@infinum.hr
 * @since 24/11/16
 */

public class RSCApplication extends Application {

    private static RSCApplication instance;

    public static RSCApplication getInstance() {
        return instance;
    }

    public static void setInstance(RSCApplication instance) {
        RSCApplication.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
    }


}
