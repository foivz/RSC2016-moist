package foi.hr.rscandroid.ui.details;


import foi.hr.rscandroid.ui.BaseMvp;

public interface EventDetailsView extends BaseMvp.View {

    void quizStarted();

    void quizStartFailed(String error);
}
