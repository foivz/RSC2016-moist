package foi.hr.rscandroid.ui.game.fillanswer;


import foi.hr.rscandroid.data.interactors.EditAnswerInteractor;

public class EditAnswerPresenter {

    private EditAnswerView view;

    private EditAnswerInteractor interactor;

    public EditAnswerPresenter(EditAnswerView view, EditAnswerInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }
}
