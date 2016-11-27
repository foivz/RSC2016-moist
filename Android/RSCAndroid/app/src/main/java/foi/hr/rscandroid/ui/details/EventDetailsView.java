package foi.hr.rscandroid.ui.details;


import java.util.ArrayList;

import foi.hr.rscandroid.data.models.Team;
import foi.hr.rscandroid.ui.BaseMvp;

public interface EventDetailsView extends BaseMvp.View {

    void quizStarted();

    void quizStartFailed(String error);

    void showTeams(ArrayList<Team> teamArrayList);
}
