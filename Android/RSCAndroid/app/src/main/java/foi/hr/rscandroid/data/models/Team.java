package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {

    @SerializedName("name")
    private String teamName;

    @SerializedName("id")
    private long teamId;

    @SerializedName("max_members")
    private long maxMembers;

    @SerializedName("size")
    private long populatedMembers;


    protected Team(Parcel in) {
        teamName = in.readString();
        teamId = in.readLong();
        maxMembers = in.readLong();
        populatedMembers = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(teamName);
        dest.writeLong(teamId);
        dest.writeLong(maxMembers);
        dest.writeLong(populatedMembers);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

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
