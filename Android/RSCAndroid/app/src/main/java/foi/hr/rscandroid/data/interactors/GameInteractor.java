package foi.hr.rscandroid.data.interactors;


import android.support.annotation.Nullable;

import foi.hr.rscandroid.data.models.AnswerRequest;
import foi.hr.rscandroid.data.models.BaseRequest;
import foi.hr.rscandroid.data.models.Question;
import foi.hr.rscandroid.data.networking.ApiService;
import foi.hr.rscandroid.data.networking.BaseCallback;
import foi.hr.rscandroid.ui.shared.Listener;
import foi.hr.rscandroid.ui.shared.SharedPrefsHelper;
import retrofit2.Call;
import retrofit2.Response;

public class GameInteractor {

    private ApiService service;

    private Call<Void> call;

    private BaseCallback<Void> callback;

    private Call<Void> syncCall;

    private BaseCallback<Void> syncCallback;

    public GameInteractor(ApiService service) {
        this.service = service;
    }

    public void startQuiz(long id, BaseRequest<Question> questionData, final Listener<Void> startQuizListener) {
        call = service.startQuiz(id, questionData);

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

    public void syncAnswer(BaseRequest<AnswerRequest> ar, int team_id, final Listener<Void> syncListener) {
        syncCall = service.syncAnswers(ar, team_id, SharedPrefsHelper.getSharedPrefsInt("EVENT_ID"));

        syncCallback = new BaseCallback<Void>() {
            @Override
            public void onSuccess(Void body, Response<Void> response) {
                syncListener.onSuccess(body);
            }

            @Override
            public void onUnknownError(@Nullable String error) {
                syncListener.onError(error);
            }
        };

        syncCall.enqueue(syncCallback);
    }
}
