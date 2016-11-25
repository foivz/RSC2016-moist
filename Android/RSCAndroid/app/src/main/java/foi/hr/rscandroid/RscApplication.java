package foi.hr.rscandroid;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import android.app.Application;
import android.content.Intent;

import foi.hr.rscandroid.data.networking.ApiManagerImpl;
import foi.hr.rscandroid.data.networking.ApiService;
import foi.hr.rscandroid.data.push.RSCFirebaseInstanceIdService;
import foi.hr.rscandroid.data.push.RSCFirebaseMessagingService;

/**
 * @author Igor Tepavac
 *         igor.tepavac@infinum.hr
 * @since 24/11/16
 */

public class RSCApplication extends Application {

    private static RSCApplication instance;

    private static ApiService apiService;

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

        ApiManagerImpl.getInstance().init();
        apiService = ApiManagerImpl.getInstance().getApiService();

        startService(new Intent(this, RSCFirebaseInstanceIdService.class));
        startService(new Intent(this, RSCFirebaseMessagingService.class));
    }

    public static ApiService getApiService() {
        return apiService;
    }
}
