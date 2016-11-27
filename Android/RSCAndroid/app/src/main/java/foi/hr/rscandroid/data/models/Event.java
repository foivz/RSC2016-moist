package foi.hr.rscandroid.data.models;

import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import foi.hr.rscandroid.ui.dashboard.DashboardActivity;
import foi.hr.rscandroid.ui.shared.SharedPrefsHelper;


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
    private String date;

    @SerializedName("time")
    private String time;

    @SerializedName("description")
    private String description;

    @SerializedName("prizes")
    private String prizes;

    @SerializedName("teams")
    private List<Team> teams;

    @SerializedName("questions")
    private List<QuestionData> questions;

    private transient float distanceFromCurrentLocation = INVALID_DISTANCE;

    private transient boolean userModerator = false;

    protected Event(Parcel in) {
        id = in.readLong();
        moderatorId = in.readLong();
        name = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        date = in.readString();
        time = in.readString();
        description = in.readString();
        prizes = in.readString();
        teams = in.createTypedArrayList(Team.CREATOR);
        questions = in.createTypedArrayList(QuestionData.CREATOR);
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

    public static float getInvalidDistance() {
        return INVALID_DISTANCE;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

    public List<QuestionData> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionData> questions) {
        this.questions = questions;
    }

    public float getDistanceFromCurrentLocation() {
        return distanceFromCurrentLocation;
    }

    public void setDistanceFromCurrentLocation(float distanceFromCurrentLocation) {
        this.distanceFromCurrentLocation = distanceFromCurrentLocation;
    }

    public boolean isUserModerator() {
        userModerator = moderatorId == SharedPrefsHelper.getSharedPrefsInt(DashboardActivity.USER_ID);
        return userModerator;
    }

    public void setUserModerator(boolean userModerator) {
        this.userModerator = userModerator;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeLong(moderatorId);
        parcel.writeString(name);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(date);
        parcel.writeString(time);
        parcel.writeString(description);
        parcel.writeString(prizes);
        parcel.writeTypedList(teams);
        parcel.writeTypedList(questions);
    }
}
