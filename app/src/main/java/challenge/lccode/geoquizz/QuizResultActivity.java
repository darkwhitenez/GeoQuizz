package challenge.lccode.geoquizz;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterViewAnimator;
import android.widget.ImageView;

import challenge.lccode.geoquizz.adapter.QuizAdapter;
import challenge.lccode.geoquizz.helper.ApiVersionHelper;
import challenge.lccode.geoquizz.models.Quiz;


public class QuizResultActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
    }



}
