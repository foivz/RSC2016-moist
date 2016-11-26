package foi.hr.rscandroid.ui.teams;


import java.util.ArrayList;

import foi.hr.rscandroid.data.interactors.TeamInteractor;
import foi.hr.rscandroid.data.models.Team;

public class TeamPresenter {

    private TeamView view;

    private TeamInteractor interactor;

    public TeamPresenter(TeamView view, TeamInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void checkTeams(ArrayList<Team> teams) {
        if (!teams.isEmpty()) {
            view.populateAdapter(teams);
        } else {
            view.showNoTeamsView();
        }
    }
}
