package challenge.lccode.geoquizz;

import java.util.List;

import challenge.lccode.geoquizz.models.GeneralStats;
import challenge.lccode.geoquizz.models.Quiz;
import challenge.lccode.geoquizz.models.QuizItem;
import challenge.lccode.geoquizz.models.QuizResult;
import challenge.lccode.geoquizz.models.UserLogin;
import challenge.lccode.geoquizz.models.UserStats;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by zizzy on 2.6.2017..
 */

public interface QuizRestInterface {

    // registracija novog korisnika, vraća unique _id korisnika
    @FormUrlEncoded
    @POST("/api/user/register")
    Call<Void> registerUser(@Field("username") String username, @Field("password") String password);

    // login postojećeh korisnika, vraća unique _id korisnika
    @FormUrlEncoded
    @POST("/api/user/login")
    Call<Object> loginUser(@Field("username") String username, @Field("password") String password);

    // statistika o korisniku općenito
    @GET("/api/user/get_stats")
    Call<List<UserStats>> getUserStats(@Header("x-auth-token") String token);

    // dohvat random kviza
    @GET("/api/quiz/get_random")
    Call<List<QuizItem>> getRandomQuiz(@Header("x-auth-token") String token);

    // dohvat random kviza neke države
    @FormUrlEncoded
    @POST("/api/quiz/get_for_country")
    Call<List<QuizItem>> getQuizForCountry(@Field("country_code") String countryCode, @Header("x-auth-token") String token);

    @FormUrlEncoded
    @POST("/api/quiz/send_result")
    Call<Void> sendQuizResult(@Body List<QuizResult> results,  @Header("x-auth-token") String token);


}
