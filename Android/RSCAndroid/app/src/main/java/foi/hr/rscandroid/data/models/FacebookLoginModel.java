package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

public class FacebookLoginModel {

    @SerializedName("token")
    private String token;

    public String getToken() {
        return token;
    }
}
