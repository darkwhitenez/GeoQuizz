package challenge.lccode.geoquizz;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zizzy on 2.6.2017..
 */

public class Application extends android.app.Application {
    public static final String BASE_URL = "http://138.197.16.97";
    private static Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Creating retrofit");
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }
    public static Retrofit getRetrofit(){
        return retrofit;
    }
}
