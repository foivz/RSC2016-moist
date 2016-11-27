package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TeamsResponse {

    @SerializedName("teams")
    private ArrayList<Team> teamArrayList;

    public ArrayList<Team> getTeamArrayList() {
        return teamArrayList;
    }

    public void setTeamArrayList(ArrayList<Team> teamArrayList) {
        this.teamArrayList = teamArrayList;
    }
}
