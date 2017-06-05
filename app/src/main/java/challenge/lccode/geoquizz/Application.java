package challenge.lccode.geoquizz;

import android.app.Activity;
import android.content.SharedPreferences;

import java.util.List;

import challenge.lccode.geoquizz.models.Quiz;
import challenge.lccode.geoquizz.models.UserStats;

/**
 * Created by zizzy on 2.6.2017..
 */

public class Application extends android.app.Application {
    public static final String DEFAULT_SERVER_URL =  "http://128.199.42.111";
    public static String token;
    public static boolean isLoggedIn;
    public static String name;
    public static List<UserStats> userStats;
    public static List<UserStats> mapStats;
    public static Quiz quiz;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        isLoggedIn = false;
        sharedPreferences = getSharedPreferences("server_uri", Activity.MODE_PRIVATE);
        if (sharedPreferences == null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("server_uri", DEFAULT_SERVER_URL);
            editor.commit();
        }

    }


}
