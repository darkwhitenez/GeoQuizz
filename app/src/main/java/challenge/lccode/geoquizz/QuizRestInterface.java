package challenge.lccode.geoquizz;

import java.util.List;

import challenge.lccode.geoquizz.models.UserLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by zizzy on 2.6.2017..
 */

public interface QuizRestInterface {

    @POST("/api/user/register")
    Call<UserLogin> registerUser(@Body UserLogin user);

    @POST("/api/user/login")
    Call<UserLogin> loginUser(@Body UserLogin user);

    @GET("api/user/stats/{id}")
    Call<UserStats> getUserStats(@Path("id") String id);




}
