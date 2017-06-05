package challenge.lccode.geoquizz;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import challenge.lccode.geoquizz.helper.Util;
import challenge.lccode.geoquizz.models.QuizResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class QuizResultActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView quiz_result_msg;
    private TextView activity_main_register;
    private TextView percentage_value;
    private ProgressDialog dialog;
    private String countryCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz_result);
        toolbar = (Toolbar) findViewById(R.id.activity_stats_toolbar);
        toolbar.setTitle("QUIZ RESULTS");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        percentage_value = (TextView) findViewById(R.id.percentage_value);
        quiz_result_msg = (TextView) findViewById(R.id.quiz_result_msg);
        activity_main_register = (TextView) findViewById(R.id.activity_main_register);
        activity_main_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnotherChallenge();
            }
        });
        Bundle bundle = getIntent().getExtras();
        List<QuizResult> results = (List<QuizResult>) bundle.getSerializable("quiz_results");
        countryCode = bundle.getString("country_code");
        int correct = 0;
        for (QuizResult res : results) {
            if (res.correct) {
                correct++;
            }
        }

        if (countryCode != null) {
            sendQuizResult(results);
        }
        double percentage = (double) correct / results.size();
        int percentageInt = (int) (percentage * 100);


        percentage_value.setText(percentageInt + "%");
        quiz_result_msg.setText(Util.getOffensiveStatement(percentageInt));

    }

    private void sendQuizResult(List<QuizResult> results) {
        System.out.println(results);
        QuizRestInterface apiService = Util.getRetrofit().create(QuizRestInterface.class);
        Call<List<QuizResult>> call = apiService.sendQuizResult(results, Application.token);
        call.enqueue(new Callback<List<QuizResult>>() {
            @Override
            public void onResponse(Call<List<QuizResult>> call, Response<List<QuizResult>> response) {
                System.out.println("Quiz result successfully stored.");
            }

            @Override
            public void onFailure(Call<List<QuizResult>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void startAnotherChallenge() {
        dialog = new ProgressDialog(this, R.style.ProgressbarTheme);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(true);
        dialog.setTitle("Please wait");
        dialog.setMessage("Loading the quiz data");
        dialog.show();

        new CountDownTimer(600, 100) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                Intent intent = new Intent(QuizResultActivity.this, ChallengeActivity.class);
                if (countryCode == null){
                    startActivity(intent);
                }else{
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("country_code", countryCode);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        }.start();
    }


}
