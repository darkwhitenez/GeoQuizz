package challenge.lccode.geoquizz.helper;

/**
 * Created by sebo on 6/4/17.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

import challenge.lccode.geoquizz.Application;
import challenge.lccode.geoquizz.QuizRestInterface;
import challenge.lccode.geoquizz.StatsActivity;
import challenge.lccode.geoquizz.models.UserStats;
import retrofit2.Call;
import retrofit2.Retrofit;


public class SVGHelper {
    public static final String FILENAME = "world.svg";

    private static String svg = "";
    private static Context cntx;
    private static FetchStatsData fetch;

    public static void getMap(Context context) {
        cntx = context;
        fetch = new SVGHelper.FetchStatsData();

        fetch.execute();
    }

    public static String getContent() {
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        String line = "";
        try {
            inputReader = new InputStreamReader(cntx.getResources().getAssets().open(FILENAME));
            bufReader = new BufferedReader(inputReader);

            while ((line = bufReader.readLine()) != null)
                svg += line;

            colorCountries();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return svg;
    }

    public static void colorCountries() {
        for(UserStats stat : Application.mapStats) {
            colorCountry(stat.country_code, Float.toString(stat.questions_answered/stat.questions_correct * 100));
        }

    }


    public static void colorCountry(String id, String opacity) {
        int idIndex = svg.indexOf(id);
        int styleIndex = svg.indexOf("style", idIndex);

        String replaceWith = "style=\"fill:#ff0000;fill-rule:evenodd\"";
        String forReplacement = svg.substring(styleIndex, styleIndex + 38);
        if (forReplacement.contains("f2f2f2") == false)
            return;

        String svgStart = svg.substring(0, styleIndex - 1);
        String svgMiddle = svg.replace(forReplacement, replaceWith).substring(styleIndex, styleIndex + 38);
        String svgEnd = svg.substring(styleIndex + 39);

        svg = svgStart + svgMiddle + svgEnd;
    }

    public static String getSVG() {
        return svg;
    }

    public static class FetchStatsData extends AsyncTask<Object, Void, List<UserStats>> {

        private Retrofit retrofit;

        @Override
        protected void onPostExecute(List<UserStats> stats) {
            super.onPostExecute(stats);
            Application.mapStats = stats;

            getContent();
        }

        @Override
        protected void onPreExecute() {

        }


        @Override
        protected List<UserStats> doInBackground(Object... params) {
            retrofit = Util.getRetrofit();

            QuizRestInterface apiService = retrofit.create(QuizRestInterface.class);
            List<UserStats> mapStats = null;
            final Call<List<UserStats>> callStats = apiService.getUserStats(Application.token);
            try {

                mapStats = callStats.execute().body();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return mapStats;
        }
    }
}
