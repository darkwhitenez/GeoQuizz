package challenge.lccode.geoquizz;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import challenge.lccode.geoquizz.helper.Util;


public class QuizResultActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView percentage_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quiz_result);
        toolbar = (Toolbar) findViewById(R.id.activity_stats_toolbar);
        toolbar.setTitle("QUIZ RESULTS");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        percentage_value = (TextView) findViewById(R.id.percentage_value);


    }


}
