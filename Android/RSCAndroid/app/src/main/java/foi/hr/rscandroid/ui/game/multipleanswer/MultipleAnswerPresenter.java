package foi.hr.rscandroid.ui.game.multipleanswer;


import foi.hr.rscandroid.data.interactors.MultipleAnswerInteractor;

public class MultipleAnswerPresenter {

    private MultipleAnswerView view;
    private MultipleAnswerInteractor interactor;

    public MultipleAnswerPresenter(MultipleAnswerView view, MultipleAnswerInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }
}
