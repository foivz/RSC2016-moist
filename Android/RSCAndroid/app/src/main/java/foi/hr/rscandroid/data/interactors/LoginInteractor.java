package foi.hr.rscandroid.data.interactors;


import com.google.firebase.messaging.FirebaseMessaging;

import android.support.annotation.Nullable;
import android.util.Log;

import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.User;
import foi.hr.rscandroid.data.networking.ApiService;
import foi.hr.rscandroid.data.networking.BaseCallback;
import foi.hr.rscandroid.ui.shared.Listener;
import retrofit2.Call;
import retrofit2.Response;

public class LoginInteractor {

    private ApiService apiService;

    public LoginInteractor(ApiService apiService) {
        this.apiService = apiService;
    }

    public void authorizeFb(String token, final Listener<User> facebookLoginListener) {
        Call<BaseResponse<User>> facebookCall = apiService.authorizeFb(token);

        BaseCallback<BaseResponse<User>> facebookCallback = new BaseCallback<BaseResponse<User>>() {
            @Override
            public void onSuccess(BaseResponse<User> body, Response<BaseResponse<User>> response) {
                facebookLoginListener.onSuccess(body.getResponse());
            }

            @Override
            public void onUnknownError(@Nullable String error) {
                facebookLoginListener.onError(error);
            }
        };

        facebookCall.enqueue(facebookCallback);
    }

    public void testOutLogin() {
        Call<BaseResponse<User>> normalCall = apiService.getUser();

        BaseCallback<BaseResponse<User>> normalCallback = new BaseCallback<BaseResponse<User>>() {
            @Override
            public void onSuccess(BaseResponse<User> body, Response<BaseResponse<User>> response) {
                FirebaseMessaging.getInstance().subscribeToTopic(body.getResponse().getUserData().getId() + "");
            }

            @Override
            public void onUnknownError(@Nullable String error) {
                Log.e("error", error);
            }
        };

        normalCall.enqueue(normalCallback);
    }

    public void loginNormally(String email, String pw) {

    }

    public void loginGoogle(String idToken, final Listener<BaseResponse<User>> googleLoginListener) {
        Call<BaseResponse<User>> googleCall = apiService.authorizeGoogle(idToken);

        BaseCallback<BaseResponse<User>> googleCallback = new BaseCallback<BaseResponse<User>>() {
            @Override
            public void onSuccess(BaseResponse<User> body, Response<BaseResponse<User>> response) {
                googleLoginListener.onSuccess(body);
            }

            @Override
            public void onUnknownError(@Nullable String error) {
                googleLoginListener.onError(error);
            }
        };

        googleCall.enqueue(googleCallback);
    }
}
