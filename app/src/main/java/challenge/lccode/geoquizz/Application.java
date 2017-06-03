package challenge.lccode.geoquizz;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zizzy on 2.6.2017..
 */

public class Application extends android.app.Application {
    public static final String BASE_URL = "http://128.199.42.111";
    public static String token;

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
