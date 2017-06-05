package challenge.lccode.geoquizz;

import java.util.List;

import challenge.lccode.geoquizz.models.Quiz;
import challenge.lccode.geoquizz.models.UserStats;

/**
 * Created by zizzy on 2.6.2017..
 */

public class Application extends android.app.Application {
    public static final String BASE_URL = "http://128.199.42.111";
    public static String token;
    public static boolean isLoggedIn;
    public static String name;
    public static List<UserStats> userStats;
    public static List<UserStats> mapStats;
    public static Quiz quiz;

    @Override
    public void onCreate() {
        super.onCreate();
        isLoggedIn = false;
    }


}
