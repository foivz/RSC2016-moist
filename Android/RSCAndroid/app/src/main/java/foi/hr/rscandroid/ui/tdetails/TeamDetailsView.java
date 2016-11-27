package foi.hr.rscandroid.ui.tdetails;


import foi.hr.rscandroid.data.models.TeamDetailsData;
import foi.hr.rscandroid.ui.BaseMvp;

public interface TeamDetailsView extends BaseMvp.View {

    void showTeamDetails(TeamDetailsData detailsData);
}
