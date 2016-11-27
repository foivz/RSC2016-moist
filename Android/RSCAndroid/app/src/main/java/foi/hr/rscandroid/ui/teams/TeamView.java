package foi.hr.rscandroid.ui.teams;


import java.util.ArrayList;

import foi.hr.rscandroid.data.models.Team;
import foi.hr.rscandroid.ui.BaseMvp;

public interface TeamView extends BaseMvp.View {

    void populateAdapter(ArrayList<Team> teams);

    void showNoTeamsView();

}
