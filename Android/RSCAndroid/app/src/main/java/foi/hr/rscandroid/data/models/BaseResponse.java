package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

public class BaseResponse<Response> {

    @SerializedName("response")
    private Response response;

    public Response getResponse() {
        return response;
    }
}
