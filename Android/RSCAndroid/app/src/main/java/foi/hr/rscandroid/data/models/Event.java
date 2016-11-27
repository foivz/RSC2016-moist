package foi.hr.rscandroid.data.models;

import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


public class Event implements Parcelable {

    public static final float INVALID_DISTANCE = -1.0f;

    @SerializedName("id")
    private long id;

    @SerializedName("moderator_id")
    private long moderatorId;

    @SerializedName("name")
    private String name;

    @SerializedName("latitude")
    private double latitude;

    @SerializedName("longitude")
    private double longitude;

    @SerializedName("date")
    private DateTime date;

    @SerializedName("time")
    private String time;

    @SerializedName("description")
    private String description;

    @SerializedName("prizes")
    private String prizes;

    @SerializedName("teams")
    private List<Team> teams;

    private transient float distanceFromCurrentLocation = INVALID_DISTANCE;

    private transient boolean userModerator = false;

    public Event() {
    }


    protected Event(Parcel in) {
        id = in.readLong();
        moderatorId = in.readLong();
        name = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        time = in.readString();
        description = in.readString();
        prizes = in.readString();
        teams = in.createTypedArrayList(Team.CREATOR);
        distanceFromCurrentLocation = in.readFloat();
        userModerator = in.readInt() == 1;
        date = (DateTime) in.readSerializable();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(moderatorId);
        dest.writeString(name);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(time);
        dest.writeString(description);
        dest.writeString(prizes);
        dest.writeTypedList(teams);
        dest.writeFloat(distanceFromCurrentLocation);
        dest.writeInt(userModerator ? 1 : 0);
        dest.writeSerializable(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(long moderatorId) {
        this.moderatorId = moderatorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrizes() {
        return prizes;
    }

    public void setPrizes(String prizes) {
        this.prizes = prizes;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public float getDistanceFromCurrentLocation() {
        return distanceFromCurrentLocation;
    }

    public void setDistanceFromCurrentLocation(float distanceFromCurrentLocation) {
        this.distanceFromCurrentLocation = distanceFromCurrentLocation;
    }

    public boolean isUserModerator() {
        return userModerator;
    }

    public void setUserModerator(boolean moderator) {
        this.userModerator = moderator;
    }
}
