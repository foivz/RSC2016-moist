package foi.hr.rscandroid.ui.game.singleanswer;


import foi.hr.rscandroid.data.interactors.SingleAnswerInteractor;
import foi.hr.rscandroid.data.models.QuestionData;

public class SingleAnswerPresenter {

    private SingleAnswerView view;
    private SingleAnswerInteractor interactor;

    public SingleAnswerPresenter(SingleAnswerView view, SingleAnswerInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }


    public void analyzeQuestion(QuestionData question) {

    }
}

