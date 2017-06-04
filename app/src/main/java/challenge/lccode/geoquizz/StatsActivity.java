package challenge.lccode.geoquizz;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import challenge.lccode.geoquizz.adapter.UserStatsAdapter;
import challenge.lccode.geoquizz.helper.Util;
import challenge.lccode.geoquizz.models.UserStats;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by zizzy on 4.6.2017..
 */

public class StatsActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private FetchStatsData fetchStatsData;
    private UserStatsAdapter userStatsAdapter;
    private ProgressBar bar;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrofit = Util.getRetrofit();
        setContentView(R.layout.activity_stats);
        toolbar = (Toolbar) findViewById(R.id.activity_stats_toolbar);
        toolbar.setTitle("STATISTICS");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bar = (ProgressBar) findViewById(R.id.progressbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        userStatsAdapter = new UserStatsAdapter();
        fetchStatsData = new FetchStatsData();
        fetchStatsData.execute();

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(userStatsAdapter);


    }

    public class FetchStatsData extends AsyncTask<Object, Void, List<UserStats>> {

        @Override
        protected void onPostExecute(List<UserStats> stats) {
            super.onPostExecute(stats);
            bar.setVisibility(View.GONE);
            userStatsAdapter.notifyDataSetChanged();
        }

        @Override
        protected void onPreExecute() {
            bar.setVisibility(View.VISIBLE);
        }


        @Override
        protected List<UserStats> doInBackground(Object... params) {
            QuizRestInterface apiService = retrofit.create(QuizRestInterface.class);
            List<UserStats> userStats = null;
            final Call<List<UserStats>> callStats = apiService.getUserStats(Application.token);
            try {

                userStats = callStats.execute().body();
                System.out.println(userStats);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return userStats;
        }
    }


}
