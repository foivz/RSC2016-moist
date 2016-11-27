package foi.hr.rscandroid.data.interactors;


import android.support.annotation.Nullable;

import java.util.ArrayList;

import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.Team;
import foi.hr.rscandroid.data.models.TeamsResponse;
import foi.hr.rscandroid.data.networking.ApiService;
import foi.hr.rscandroid.data.networking.BaseCallback;
import foi.hr.rscandroid.ui.shared.Listener;
import foi.hr.rscandroid.ui.shared.SharedPrefsHelper;
import retrofit2.Call;
import retrofit2.Response;

public class TeamInteractor {

    private ApiService service;

    private Call<BaseResponse<TeamsResponse>> call;

    private BaseCallback<BaseResponse<TeamsResponse>> baseCallback;

    public TeamInteractor(ApiService service) {
        this.service = service;
    }

    public void fetchTeams(final Listener<ArrayList<Team>> teamListener) {
        call = service.fetchMyTeams(SharedPrefsHelper.getSharedPrefsInt("USER_ID"));

        baseCallback = new BaseCallback<BaseResponse<TeamsResponse>>() {
            @Override
            public void onSuccess(BaseResponse<TeamsResponse> body, Response<BaseResponse<TeamsResponse>> response) {
                teamListener.onSuccess(body.getResponse().getTeamArrayList());
            }

            @Override
            public void onUnknownError(@Nullable String error) {
                teamListener.onError(error);
            }
        };

        call.enqueue(baseCallback);
    }
}
