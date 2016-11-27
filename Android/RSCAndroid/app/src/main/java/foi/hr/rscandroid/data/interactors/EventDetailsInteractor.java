package foi.hr.rscandroid.data.interactors;


import android.support.annotation.Nullable;

import foi.hr.rscandroid.data.models.BaseRequest;
import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.Question;
import foi.hr.rscandroid.data.models.TeamsResponse;
import foi.hr.rscandroid.data.networking.ApiService;
import foi.hr.rscandroid.data.networking.BaseCallback;
import foi.hr.rscandroid.ui.shared.Listener;
import foi.hr.rscandroid.ui.shared.SharedPrefsHelper;
import retrofit2.Call;
import retrofit2.Response;

public class EventDetailsInteractor {

    private ApiService apiService;

    private Call<Void> call;

    private BaseCallback<Void> callback;

    private Call<BaseResponse<TeamsResponse>> teamsCall;

    private BaseCallback<BaseResponse<TeamsResponse>> teamsCallback;

    public EventDetailsInteractor(ApiService apiService) {
        this.apiService = apiService;
    }

    public void startQuiz(long id, BaseRequest<Question> questionData, final Listener<Void> startQuizListener) {
        call = apiService.startQuiz(id, questionData);

        callback = new BaseCallback<Void>() {
            @Override
            public void onSuccess(Void body, Response<Void> response) {
                startQuizListener.onSuccess(body);
            }

            @Override
            public void onUnknownError(@Nullable String error) {
                startQuizListener.onError(error);
            }
        };

        call.enqueue(callback);
    }

    public void getTeams(final Listener<TeamsResponse> fetchTeamsListener) {
        teamsCall = apiService.fetchMyTeams(SharedPrefsHelper.getSharedPrefsInt("USER_ID"));

        teamsCallback = new BaseCallback<BaseResponse<TeamsResponse>>() {
            @Override
            public void onSuccess(BaseResponse<TeamsResponse> body, Response<BaseResponse<TeamsResponse>> response) {
                fetchTeamsListener.onSuccess(body.getResponse());
            }

            @Override
            public void onUnknownError(@Nullable String error) {
                fetchTeamsListener.onError(error);
            }
        };

        teamsCall.enqueue(teamsCallback);
    }
}
