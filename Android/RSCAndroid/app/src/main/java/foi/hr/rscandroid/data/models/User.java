package foi.hr.rscandroid.data.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user")
    private UserData userData;

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
