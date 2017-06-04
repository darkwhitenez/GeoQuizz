package challenge.lccode.geoquizz;

/**
 * Created by zizzy on 2.6.2017..
 */

public class Application extends android.app.Application {
    public static final String BASE_URL = "http://128.199.42.111";
    public static String token;
    public static boolean isLoggedIn;
    public static String name;

    @Override
    public void onCreate() {
        super.onCreate();
        isLoggedIn = false;
    }


}
