package challenge.lccode.geoquizz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import challenge.lccode.geoquizz.models.Quiz;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_spin)
    TextView spin;
    @BindView(R.id.activity_main_challenge)
    TextView challenge;
    @BindView(R.id.activity_main_register)
    TextView register;
    @BindView(R.id.activity_main_stats)
    TextView stats;
    @BindView(R.id.activity_main_frame_register)
    FrameLayout frame_register;
    @BindView(R.id.activity_main_frame_stats)
    FrameLayout frame_stats;

    ProgressDialog dialog;
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ButterKnife.bind(this);
        if (Application.isLoggedIn) {
            frame_register.setVisibility(View.GONE);
        }else{
            frame_stats.setVisibility(View.GONE);
        }


    }



    /*  listeners   */

    @OnClick(R.id.activity_main_spin)
    public void spin(View view) {
        Intent intent = new Intent(this, GlobeActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.activity_main_challenge)
    public void challenge(View view) {
        dialog = new ProgressDialog(this, R.style.ProgressbarTheme);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(true);
        dialog.setTitle("Please wait");
        dialog.setMessage("Loading the quiz data");
        dialog.show();

        //  new RandomQuizProvider().execute("");
        startActivity(new Intent(MainActivity.this, ChallengeActivity.class));

    }


    @OnClick(R.id.activity_main_register)
    public void register(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.activity_main_stats)
    public void stats(View view) {
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
    }

    public Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Application.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    private class RandomQuizProvider extends AsyncTask<String, Void, Quiz> {

        @Override
        protected void onPostExecute(Quiz q) {
            super.onPostExecute(q);
            if (q != null) {
                Intent intent = new Intent(MainActivity.this, ChallengeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("quiz", q);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                return;
            }

            /* snackbar TODO */
        }

        @Override
        protected Quiz doInBackground(String... params) {

            Retrofit retrofit = getRetrofit();
            if (retrofit == null) {
                return null;
            }
            QuizRestInterface apiService = retrofit.create(QuizRestInterface.class);
            final Call<Quiz> callSticker = apiService.getRandomQuiz();

            try {

                Quiz quiz = callSticker.execute().body();
                System.out.println("GETTING QUIZ");
                return quiz;

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem bedMenuItem = menu.findItem(R.id.actionbar_user_login);
        if (Application.isLoggedIn) {
            bedMenuItem.setTitle("Log out");
        }else{
            bedMenuItem.setTitle("Login");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.actionbar_settings_settings:
                break;
            case R.id.actionbar_settings_info:
                break;
            case R.id.actionbar_user_login: {
                if (Application.isLoggedIn) {
                    logOut();
                } else {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;

            }

        }

        return super.onOptionsItemSelected(item);
    }

    private void logOut() {
        Application.isLoggedIn = false;
        Application.token = "";
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }


}
