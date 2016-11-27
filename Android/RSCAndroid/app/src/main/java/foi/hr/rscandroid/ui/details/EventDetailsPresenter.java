package foi.hr.rscandroid.ui.details;


import com.google.firebase.messaging.FirebaseMessaging;

import foi.hr.rscandroid.data.interactors.EventDetailsInteractor;
import foi.hr.rscandroid.data.models.BaseRequest;
import foi.hr.rscandroid.data.models.Event;
import foi.hr.rscandroid.data.models.Question;
import foi.hr.rscandroid.data.models.TeamsResponse;
import foi.hr.rscandroid.ui.shared.Listener;
import foi.hr.rscandroid.ui.shared.SharedPrefsHelper;

public class EventDetailsPresenter {

    private EventDetailsView view;

    private EventDetailsInteractor interactor;

    public EventDetailsPresenter(EventDetailsView view, EventDetailsInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void makeCall(Event event) {
        FirebaseMessaging.getInstance().subscribeToTopic(event.getId() + "");
        if (event.isUserModerator()) {
            BaseRequest<Question> baseRequest = new BaseRequest<>();
            Question q = new Question();
            q.setQuestionData(event.getQuestions().get(0));
            baseRequest.setRequest(q);
            interactor.startQuiz(event.getId(), baseRequest, startQuizListener);
            SharedPrefsHelper.setSharedPrefsBoolean("isMod", event.isUserModerator());
        } else {
            SharedPrefsHelper.setSharedPrefsBoolean("isMod", event.isUserModerator());
            interactor.getTeams(fetchTeamsListener);
        }
    }


    private Listener<Void> startQuizListener = new Listener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
            view.hideProgress();
            view.quizStarted();
        }

        @Override
        public void onError(String error) {
            view.hideProgress();
            view.quizStartFailed(error);
        }
    };

    private Listener<TeamsResponse> fetchTeamsListener = new Listener<TeamsResponse>() {
        @Override
        public void onSuccess(TeamsResponse teamsResponse) {
            view.hideProgress();
            view.showTeams(teamsResponse.getTeamArrayList());
        }

        @Override
        public void onError(String error) {
            view.hideProgress();
        }
    };

}
