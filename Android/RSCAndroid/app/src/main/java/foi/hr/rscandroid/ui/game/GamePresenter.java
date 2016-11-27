package foi.hr.rscandroid.ui.game;


import android.support.v4.app.Fragment;

import foi.hr.rscandroid.data.interactors.GameInteractor;
import foi.hr.rscandroid.data.models.QuestionData;
import foi.hr.rscandroid.ui.game.fillanswer.EditAnswerFragment;
import foi.hr.rscandroid.ui.game.multipleanswer.MultipleAnswerFragment;
import foi.hr.rscandroid.ui.game.singleanswer.SingleAnswerFragment;

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
}
