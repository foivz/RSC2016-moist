package foi.hr.rscandroid.data.interactors;


import android.support.annotation.Nullable;

import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.TeamDetails;
import foi.hr.rscandroid.data.networking.ApiService;
import foi.hr.rscandroid.data.networking.BaseCallback;
import foi.hr.rscandroid.ui.shared.Listener;
import retrofit2.Call;
import retrofit2.Response;

public class TeamDetailsInteractor {

    private ApiService service;

    private Call<BaseResponse<TeamDetails>> call;

    private BaseCallback<BaseResponse<TeamDetails>> callback;

    public TeamDetailsInteractor(ApiService service) {
        this.service = service;
    }

    public void fetchTeamDetails(int id, final Listener<BaseResponse<TeamDetails>> teamDetailsListener) {
        call = service.fetchTeamDetails(id);

        callback = new BaseCallback<BaseResponse<TeamDetails>>() {
            @Override
            public void onSuccess(BaseResponse<TeamDetails> body, Response<BaseResponse<TeamDetails>> response) {
                teamDetailsListener.onSuccess(body);
            }

            @Override
            public void onUnknownError(@Nullable String error) {
                teamDetailsListener.onError(error);
            }
        };

        call.enqueue(callback);
    }
}
