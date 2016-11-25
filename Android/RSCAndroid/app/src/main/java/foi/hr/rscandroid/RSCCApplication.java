package foi.hr.rscandroid;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import android.app.Application;

import foi.hr.rscandroid.data.networking.ApiManagerImpl;
import foi.hr.rscandroid.data.networking.ApiService;

/**
 * @author Igor Tepavac
 *         igor.tepavac@infinum.hr
 * @since 24/11/16
 */

public class RSCCApplication extends Application {

    private static RSCCApplication instance;

    private static ApiService apiService;

    public static RSCCApplication getInstance() {
        return instance;
    }

    public static void setInstance(RSCCApplication instance) {
        RSCCApplication.instance = instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);

        ApiManagerImpl.getInstance().init();
        apiService = ApiManagerImpl.getInstance().getApiService();
    }

    public static ApiService getApiService() {
        return apiService;
    }
}
