package foi.hr.rscandroid.data.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User extends UserRequest implements Serializable {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
