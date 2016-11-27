package foi.hr.rscandroid.ui.shared;


import foi.hr.rscandroid.data.models.Team;

public interface OnTeamClickListener {

    void onTeamClicked(Team team);

    void onRecruit(Team team);
}
