package foi.hr.rscandroid.data.models;

import com.google.gson.annotations.SerializedName;

public class TeamResponse {

    @SerializedName("team")
    private Team team;

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
