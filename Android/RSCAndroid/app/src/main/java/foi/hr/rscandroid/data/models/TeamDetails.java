package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

public class TeamDetails {

    @SerializedName("team")
    private TeamDetailsData detailsData;

    public TeamDetailsData getDetailsData() {
        return detailsData;
    }

    public void setDetailsData(TeamDetailsData detailsData) {
        this.detailsData = detailsData;
    }
}
