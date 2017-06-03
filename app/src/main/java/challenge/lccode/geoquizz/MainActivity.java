package challenge.lccode.geoquizz;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public Retrofit getRetrofit(){
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
            if(q != null) {
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
            if (retrofit == null){
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

}
