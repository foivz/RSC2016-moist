package foi.hr.rscandroid.data.models;


import com.google.gson.annotations.SerializedName;

public class BaseRequest<Request> {

    @SerializedName("request")
    private Request request;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
