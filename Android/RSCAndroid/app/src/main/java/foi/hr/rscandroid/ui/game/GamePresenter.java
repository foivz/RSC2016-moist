package foi.hr.rscandroid.ui.game;


import android.support.v4.app.Fragment;

import foi.hr.rscandroid.RSCApplication;
import foi.hr.rscandroid.data.interactors.GameInteractor;
import foi.hr.rscandroid.data.models.Answer;
import foi.hr.rscandroid.data.models.AnswerRequest;
import foi.hr.rscandroid.data.models.BaseRequest;
import foi.hr.rscandroid.data.models.Question;
import foi.hr.rscandroid.data.models.QuestionData;
import foi.hr.rscandroid.ui.game.fillanswer.EditAnswerFragment;
import foi.hr.rscandroid.ui.game.multipleanswer.MultipleAnswerFragment;
import foi.hr.rscandroid.ui.game.singleanswer.SingleAnswerFragment;
import foi.hr.rscandroid.ui.shared.Listener;
import foi.hr.rscandroid.ui.shared.SharedPrefsHelper;

public class GamePresenter {

    private GameView view;

    private GameInteractor interactor;

    public GamePresenter(GameView view, GameInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }


    public void addNewFragment(QuestionData question) {
        Fragment fragment = null;
        switch (question.getQuestionType()) {
            case QuestionData.SINGLE_ANSWER_QUESTION:
                fragment = SingleAnswerFragment.newInstance(question);
                break;
            case QuestionData.MULTIPLE_ANSWER_QUESTION:
                fragment = MultipleAnswerFragment.newInstance(question);
                break;
            case QuestionData.EDIT_ANSWER_QUESTION:
                fragment = EditAnswerFragment.newInstance(question);
                break;
            default:
                break;
        }
        view.switchFragments(fragment);
    }

    public void requestNewQuestion() {
        BaseRequest<Question> baseRequest = new BaseRequest<>();
        Question q = new Question();
        q.setQuestionData(RSCApplication.getQuestions().get(RSCApplication.getCurrPos()));
        baseRequest.setRequest(q);
        interactor.startQuiz(SharedPrefsHelper.getSharedPrefsInt("EVENT_ID"), baseRequest, startQuizListener);
    }

    private Listener<Void> startQuizListener = new Listener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
            view.hideProgress();
        }

        @Override
        public void onError(String error) {
            view.hideProgress();
        }
    };

    public void syncClicked(int currAnswer) {
        view.showProgress();
        BaseRequest<AnswerRequest> baseRequest = new BaseRequest<>();
        AnswerRequest ar = new AnswerRequest();
        Answer a = new Answer();
        a.setAnswerId(currAnswer);
        ar.setAnswer(a);
        baseRequest.setRequest(ar);
        interactor.syncAnswer(baseRequest, SharedPrefsHelper.getSharedPrefsInt("TEAM_ID"), syncListener);
    }

    private Listener<Void> syncListener = new Listener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
            view.hideProgress();
        }

        @Override
        public void onError(String error) {
            view.hideProgress();
        }
    };
}
