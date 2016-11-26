package foi.hr.rscandroid.data.models;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user")
    private UserData userData;

    @SerializedName("token")
    private String token;

    public UserData getUserData() {
        return userData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
