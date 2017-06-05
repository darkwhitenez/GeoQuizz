package challenge.lccode.geoquizz.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import challenge.lccode.geoquizz.Application;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zizzy on 4.6.2017..
 */

public class Util {

    private static SharedPreferences sharedPreferences;

    public synchronized  static Retrofit getRetrofit(Context context) {
        sharedPreferences = context.getSharedPreferences("server_uri", Activity.MODE_PRIVATE);
        String server_uri = sharedPreferences.getString("server_url", "");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Application.DEFAULT_SERVER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static String getOffensiveStatement(int percentage) {
        if (percentage < 25) {
            return "Eat your veggies. You should do better than that";
        }
        if (percentage < 50) {
            return "Not bad, but you could do better.";
        }

        if (percentage < 75) {
            return "Well done you, Magellan";
        }
        if (percentage < 90) {
            return "Straight to the top!";
        }
        return "Winner born and raised!";
    }
}
