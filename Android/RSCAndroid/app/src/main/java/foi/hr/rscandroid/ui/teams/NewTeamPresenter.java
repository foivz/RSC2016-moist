package foi.hr.rscandroid.ui.teams;

import foi.hr.rscandroid.data.interactors.CreateTeamInteractor;
import foi.hr.rscandroid.data.models.BaseRequest;
import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.Team;
import foi.hr.rscandroid.data.models.TeamRequest;
import foi.hr.rscandroid.data.models.TeamResponse;
import foi.hr.rscandroid.ui.shared.Listener;

public class NewTeamPresenter {

    private NewTeamView view;

    private CreateTeamInteractor interactor;

    public NewTeamPresenter(NewTeamView view, CreateTeamInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void createTeam(String name, String numberInput) {
        long number;
        try {
            number = Long.parseLong(numberInput);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            view.showMessage("Invalid number input");
            return;
        }

        view.showProgress();

        BaseRequest<TeamRequest> request = new BaseRequest<>();
        Team team = new Team();
        team.setTeamName(name);
        team.setMaxAmountOfMembers(number);

        TeamRequest tr = new TeamRequest(team);
        request.setRequest(tr);

        interactor.createTeam(request, listener);
    }

    private Listener<BaseResponse<TeamResponse>> listener = new Listener<BaseResponse<TeamResponse>>() {
        @Override
        public void onSuccess(BaseResponse<TeamResponse> teamResponseBaseResponse) {
            view.hideProgress();
            view.showNewTeam(teamResponseBaseResponse.getResponse().getTeam());
        }

        @Override
        public void onError(String error) {
            view.hideProgress();
            view.showMessage(error);
        }
    };
}
