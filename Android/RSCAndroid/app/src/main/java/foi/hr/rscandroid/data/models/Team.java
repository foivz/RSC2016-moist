package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Team implements Parcelable {

    @SerializedName("name")
    private String teamName;

    @SerializedName("id")
    private long teamId;

    @SerializedName("size")
    private long maxAmountOfMembers;

    public Team() {

    }

    protected Team(Parcel in) {
        teamName = in.readString();
        teamId = in.readLong();
        maxAmountOfMembers = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(teamName);
        dest.writeLong(teamId);
        dest.writeLong(maxAmountOfMembers);
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

    public long getMaxAmountOfMembers() {
        return maxAmountOfMembers;
    }

    public void setMaxAmountOfMembers(long maxAmountOfMembers) {
        this.maxAmountOfMembers = maxAmountOfMembers;
    }
}
