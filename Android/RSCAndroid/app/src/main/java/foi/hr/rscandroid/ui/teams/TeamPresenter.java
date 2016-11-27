package foi.hr.rscandroid.ui.teams;


import java.util.ArrayList;

import foi.hr.rscandroid.data.interactors.TeamInteractor;
import foi.hr.rscandroid.data.models.Team;
import foi.hr.rscandroid.ui.shared.Listener;

public class TeamPresenter {

    private TeamView view;

    private TeamInteractor interactor;

    public TeamPresenter(TeamView view, TeamInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void checkTeams(ArrayList<Team> teams) {
        if (teams != null && !teams.isEmpty()) {
            view.populateAdapter(teams);
        } else {
            view.showNoTeamsView();
            view.showProgress();
            interactor.fetchTeams(teamListener);
        }
    }

    private Listener<ArrayList<Team>> teamListener = new Listener<ArrayList<Team>>() {
        @Override
        public void onSuccess(ArrayList<Team> team) {
            view.hideProgress();
            view.populateAdapter(team);
        }

        @Override
        public void onError(String error) {
            view.hideProgress();
            view.showNoTeamsView();
        }
    };
}
