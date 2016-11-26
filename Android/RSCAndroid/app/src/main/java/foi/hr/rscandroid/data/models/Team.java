package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

public class Team {

    @SerializedName("name")
    private String teamName;

    @SerializedName("id")
    private long teamId;

    @SerializedName("max_members")
    private long maxMembers;

    @SerializedName("size")
    private long populatedMembers;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public long getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(long maxMembers) {
        this.maxMembers = maxMembers;
    }

    public long getPopulatedMembers() {
        return populatedMembers;
    }

    public void setPopulatedMembers(long populatedMembers) {
        this.populatedMembers = populatedMembers;
    }
}
