package foi.hr.rscandroid.data.networking;

import android.util.Log;

import java.io.IOException;

import foi.hr.rscandroid.ui.login.LoginPresenter;
import foi.hr.rscandroid.ui.shared.SharedPrefsHelper;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Log.e("URL",     original.url().uri().getPath().contains("authenticate") + "");
        if (!original.url().uri().getPath().contains("authenticate")) {
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + SharedPrefsHelper.getSharedPrefsString(LoginPresenter.TOKEN));
            Request request = requestBuilder.build();
            return chain.proceed(request);
        } else {
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json");
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    }
}
