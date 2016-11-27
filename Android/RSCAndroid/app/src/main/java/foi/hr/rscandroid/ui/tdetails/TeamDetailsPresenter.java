package foi.hr.rscandroid.ui.tdetails;


import foi.hr.rscandroid.data.interactors.TeamDetailsInteractor;
import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.TeamDetails;
import foi.hr.rscandroid.ui.shared.Listener;

public class TeamDetailsPresenter {

    private TeamDetailsInteractor interactor;

    private TeamDetailsView view;

    public TeamDetailsPresenter(TeamDetailsView view, TeamDetailsInteractor interactor) {
        this.interactor = interactor;
        this.view = view;
    }

    public void fetchTeamDetails(long id) {
        interactor.fetchTeamDetails((int) id, teamDetailsListener);
    }

    private Listener<BaseResponse<TeamDetails>> teamDetailsListener = new Listener<BaseResponse<TeamDetails>>() {
        @Override
        public void onSuccess(BaseResponse<TeamDetails> teamDetailsBaseResponse) {
            view.hideProgress();
            view.showTeamDetails(teamDetailsBaseResponse.getResponse().getDetailsData());
        }

        @Override
        public void onError(String error) {
            view.hideProgress();
            view.showMessage(error);
        }
    };
}

