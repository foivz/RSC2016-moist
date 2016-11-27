package foi.hr.rscandroid.ui.game;


import android.support.v4.app.Fragment;

import java.util.ArrayList;

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


    public void setupFragments(ArrayList<QuestionData> questions) {
        ArrayList<Fragment> fragmentsList = new ArrayList<>();

        for (QuestionData q : questions) {
            switch (q.getQuestionType()) {
                case QuestionData.SINGLE_ANSWER_QUESTION:
                    fragmentsList.add(SingleAnswerFragment.newInstance(q));
                    break;
                case QuestionData.MULTIPLE_ANSWER_QUESTION:
                    fragmentsList.add(MultipleAnswerFragment.newInstance(q));
                    break;
                case QuestionData.EDIT_ANSWER_QUESTION:
                    fragmentsList.add(EditAnswerFragment.newInstance(q));
                    break;
                default:
                    break;
            }
        }

        view.returnFragments(fragmentsList);
    }
}
