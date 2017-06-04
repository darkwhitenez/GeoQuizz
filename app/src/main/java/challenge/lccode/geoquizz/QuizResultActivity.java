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


public class QuizResultActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView quiz_result_msg;
    private TextView activity_main_register;
    private TextView percentage_value;
    ProgressDialog dialog;


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
        List<Boolean> results = (List<Boolean>) bundle.getSerializable("quiz_results");
        System.out.println(results);
        int correct = 0;
        for (Boolean res : results) {
            if (res) {
                correct++;
            }
        }
        double percentage = (double) correct / results.size();
        System.out.println(percentage);
        int percentageInt = (int) (percentage * 100);


        percentage_value.setText(percentageInt + "%");
        quiz_result_msg.setText(Util.getOffensiveStatement(percentageInt));


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
                startActivity(new Intent(QuizResultActivity.this, ChallengeActivity.class));

            }
        }.start();

        //  new RandomQuizProvider().execute("");
    }


}
