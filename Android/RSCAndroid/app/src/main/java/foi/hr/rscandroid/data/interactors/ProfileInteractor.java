package foi.hr.rscandroid.data.interactors;


import android.support.annotation.Nullable;

import foi.hr.rscandroid.data.models.BaseRequest;
import foi.hr.rscandroid.data.models.User;
import foi.hr.rscandroid.data.networking.ApiService;
import foi.hr.rscandroid.data.networking.BaseCallback;
import foi.hr.rscandroid.ui.shared.Listener;
import retrofit2.Call;
import retrofit2.Response;

public class ProfileInteractor {

    private ApiService service;

    private Call<Void> call;

    private BaseCallback<Void> baseCallback;

    public ProfileInteractor(ApiService service) {
        this.service = service;
    }

    public void updateNickname(BaseRequest<User> newNickname, int id, final Listener<Void> updateNicknameListener) {
        call = service.updateNickname(newNickname, id);

        baseCallback = new BaseCallback<Void>() {
            @Override
            public void onSuccess(Void body, Response<Void> response) {
                updateNicknameListener.onSuccess(body);
            }

            @Override
            public void onUnknownError(@Nullable String error) {
                updateNicknameListener.onError(error);
            }
        };

        call.enqueue(baseCallback);
    }
}
