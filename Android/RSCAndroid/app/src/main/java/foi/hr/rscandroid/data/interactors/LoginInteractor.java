package foi.hr.rscandroid.data.interactors;


import com.google.firebase.messaging.FirebaseMessaging;

import android.support.annotation.Nullable;
import android.util.Log;

import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.FacebookLoginModel;
import foi.hr.rscandroid.data.models.User;
import foi.hr.rscandroid.data.networking.ApiService;
import foi.hr.rscandroid.data.networking.BaseCallback;
import foi.hr.rscandroid.ui.shared.Listener;
import retrofit2.Call;
import retrofit2.Response;

public class LoginInteractor {

    private ApiService apiService;

    private Call<BaseResponse<FacebookLoginModel>> call;

    private BaseCallback<BaseResponse<FacebookLoginModel>> callback;

    private Call<BaseResponse<User>> userCall;

    private BaseCallback<BaseResponse<User>> userCallback;

    public LoginInteractor(ApiService apiService) {
        this.apiService = apiService;
    }

    public void authorizeFb(String token, final Listener<FacebookLoginModel> facebookLoginListener) {
        call = apiService.authorizeFb(token);

        callback = new BaseCallback<BaseResponse<FacebookLoginModel>>() {
            @Override
            public void onSuccess(BaseResponse<FacebookLoginModel> body, Response<BaseResponse<FacebookLoginModel>> response) {
                facebookLoginListener.onSuccess(body.getResponse());
            }

            @Override
            public void onUnknownError(@Nullable String error) {
                facebookLoginListener.onError(error);
            }
        };

        call.enqueue(callback);
    }

    public void testOutLogin() {
        userCall = apiService.getUser();

        userCallback = new BaseCallback<BaseResponse<User>>() {
            @Override
            public void onSuccess(BaseResponse<User> body, Response<BaseResponse<User>> response) {
                FirebaseMessaging.getInstance().subscribeToTopic(body.getResponse().getUserData().getId() + "");
            }

            @Override
            public void onUnknownError(@Nullable String error) {
                Log.e("error", error);
            }
        };

        userCall.enqueue(userCallback);
    }

    public void loginNormally(String email, String pw) {

    }
}
