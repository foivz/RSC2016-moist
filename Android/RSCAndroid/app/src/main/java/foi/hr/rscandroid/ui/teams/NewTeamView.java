package foi.hr.rscandroid.ui.teams;

import foi.hr.rscandroid.data.models.Team;
import foi.hr.rscandroid.ui.BaseMvp;

public interface NewTeamView extends BaseMvp.View {

    void showNewTeam(Team team);
}
