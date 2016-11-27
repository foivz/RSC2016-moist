package foi.hr.rscandroid.data.interactors;


import android.support.annotation.Nullable;

import foi.hr.rscandroid.data.models.BaseRequest;
import foi.hr.rscandroid.data.models.UserRequest;
import foi.hr.rscandroid.data.networking.ApiService;
import foi.hr.rscandroid.data.networking.BaseCallback;
import foi.hr.rscandroid.ui.shared.Listener;
import retrofit2.Call;
import retrofit2.Response;

public class RegistrationInteractor {

    private ApiService apiService;

    public RegistrationInteractor(ApiService apiService) {
        this.apiService = apiService;
    }

    public void sendNickname(BaseRequest<UserRequest> user, final Listener<Void> nicknameListener) {
        Call<Void> call = apiService.sendNickname(user);

        BaseCallback<Void> callback = new BaseCallback<Void>() {
            @Override
            public void onSuccess(Void body, Response<Void> response) {
                nicknameListener.onSuccess(body);
            }

            @Override
            public void onUnknownError(@Nullable String error) {
                nicknameListener.onError(error);
            }
        };

        call.enqueue(callback);
    }
}
