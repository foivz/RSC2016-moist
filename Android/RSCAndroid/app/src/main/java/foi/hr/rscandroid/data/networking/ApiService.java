package foi.hr.rscandroid.data.networking;


import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.FacebookLoginModel;
import foi.hr.rscandroid.data.models.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/api/facebook/authenticate/{token}")
    Call<BaseResponse<FacebookLoginModel>> authorizeFb(@Path("token") String token);

    @GET("/api/users/1")
    Call<BaseResponse<User>> getUser();
}
