package foi.hr.rscandroid.data.networking;


import foi.hr.rscandroid.data.models.BaseRequest;
import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.EventsResponse;
import foi.hr.rscandroid.data.models.Question;
import foi.hr.rscandroid.data.models.TeamDetails;
import foi.hr.rscandroid.data.models.TeamsResponse;
import foi.hr.rscandroid.data.models.User;
import foi.hr.rscandroid.data.models.UserRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @POST("/api/facebook/authenticate/{token}")
    Call<BaseResponse<User>> authorizeFb(@Path("token") String token);

    @GET("/api/users/1")
    Call<BaseResponse<User>> getUser();

    @POST("/api/google/authenticate/{token}")
    Call<BaseResponse<User>> authorizeGoogle(@Path("token") String idToken);

    @POST("/api/users/create")
    Call<Void> sendNickname(@Body BaseRequest<UserRequest> user);

    @GET("/api/quiz/")
    Call<BaseResponse<EventsResponse>> fetchEvents();

    @PUT("/api/users/{id}")
    Call<Void> updateNickname(@Body BaseRequest<User> newNickname, @Path("id") int id);

    @GET("/api/teams")
    Call<BaseResponse<TeamsResponse>> fetchTeams();

    @GET("/api/team/{id}")
    Call<BaseResponse<TeamDetails>> fetchTeamDetails(@Path("id") int id);

    @POST("/api/quiz/{quizId}/next_question")
    Call<Void> startQuiz(@Path("quizId") long id, @Body BaseRequest<Question> questionData);
}
