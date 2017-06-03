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

    // slanje rezultata kviza
    @POST("/api/quiz/result/{id}")
    Call<Void> sendQuizResult(@Path("id") String userId, @Body QuizResult quizResult );

    // statistika o korisniku općenito
    @GET("/api/user/stats/{id}")
    Call<UserStats> getUserStats(@Path("id") String id);

    // dohvat random kviza
    @GET("/api/quiz/getrandom")
    Call<Quiz> getRandomQuiz();

    // dohvat random kviza neke države
    @GET("/api/quiz/getforcountry/{id}")
    Call<Quiz> getQuizForCountry(@Path("id") String id);

    // dohvat općenite statistike svih korisnika
    @GET("/api/quiz/getgeneralstats")
    Call<GeneralStats> getGeneralQuizStats();


}
