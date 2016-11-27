package foi.hr.rscandroid.data.models;

import com.google.gson.annotations.SerializedName;

public class TeamRequest {

    @SerializedName("team")
    private Team team;

    public TeamRequest() {
    }

    public TeamRequest(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
