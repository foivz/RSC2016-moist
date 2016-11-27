package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserRequest implements Serializable {

    @SerializedName("user")
    private UserData userData;

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
