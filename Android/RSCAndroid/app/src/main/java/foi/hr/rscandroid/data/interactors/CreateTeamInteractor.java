package foi.hr.rscandroid.data.interactors;

import android.support.annotation.Nullable;

import foi.hr.rscandroid.data.models.BaseRequest;
import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.TeamRequest;
import foi.hr.rscandroid.data.models.TeamResponse;
import foi.hr.rscandroid.data.networking.ApiService;
import foi.hr.rscandroid.data.networking.BaseCallback;
import foi.hr.rscandroid.ui.shared.Listener;
import retrofit2.Response;

public class CreateTeamInteractor {

    private ApiService apiService;

    private Listener<BaseResponse<TeamResponse>> listener;

    public CreateTeamInteractor(ApiService apiService) {
        this.apiService = apiService;
    }

    public void createTeam(BaseRequest<TeamRequest> request, Listener<BaseResponse<TeamResponse>> listener) {
        this.listener = listener;
        apiService.createTeam(request).enqueue(callback);
    }

    private BaseCallback<BaseResponse<TeamResponse>> callback = new BaseCallback<BaseResponse<TeamResponse>>() {
        @Override
        public void onSuccess(BaseResponse<TeamResponse> body, Response<BaseResponse<TeamResponse>> response) {
            listener.onSuccess(body);
        }

        @Override
        public void onUnknownError(@Nullable String error) {
            listener.onError(error);
        }
    };
}
