package foi.hr.rscandroid.ui.teams;


import java.util.ArrayList;

import foi.hr.rscandroid.data.models.Team;

public interface TeamView {

    void populateAdapter(ArrayList<Team> teams);

    void showNoTeamsView();

}
