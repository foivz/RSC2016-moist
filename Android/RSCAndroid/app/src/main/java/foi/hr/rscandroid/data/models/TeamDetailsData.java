package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TeamDetailsData {

    @SerializedName("id")
    private long id;

    @SerializedName("name")
    private String name;

    @SerializedName("size")
    private long maxSize;

    @SerializedName("members")
    private ArrayList<UserData> userArrayList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public ArrayList<UserData> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<UserData> userArrayList) {
        this.userArrayList = userArrayList;
    }
}
