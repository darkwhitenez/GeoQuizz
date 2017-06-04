package challenge.lccode.geoquizz;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterViewAnimator;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import challenge.lccode.geoquizz.adapter.QuizAdapter;
import challenge.lccode.geoquizz.helper.ApiVersionHelper;
import challenge.lccode.geoquizz.models.Quiz;


public class ChallengeActivity extends AppCompatActivity {

    private ImageView back;
    private TextView quizNumberText;
    private AdapterViewAnimator mQuizView;
    private QuizAdapter mQuizAdapter;
    private Quiz quiz;
    private int quizCount;
    private List<Boolean> quizCorrect;
    private Animation slide_in_left, slide_in_right, slide_out_left, slide_out_right;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        quiz = FakeQuiz.getQuiz();
        quizCount = -1;
        quizCorrect = new ArrayList<>();
        quizNumberText = (TextView) findViewById(R.id.toolbar_count);
        setQuestionNumber();
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChallengeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mQuizView = (AdapterViewAnimator) findViewById(R.id.quiz_view);
        setQuizAnimations();
        mQuizView.setAdapter(getQuizAdapter());
        mQuizView.setSelection(0);

        slide_in_left = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);

    }

    private void setQuestionNumber() {
        if (quizCount == -1){
            quizCount = 1;
        }
        quizNumberText.setText(quizCount++ + "/" + quiz.getQuizItems().size());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setQuizAnimations() {
        if (ApiVersionHelper.isEqualOrHigher(Build.VERSION_CODES.LOLLIPOP)) {
            return;
        }

    }

    private QuizAdapter getQuizAdapter() {
        if (null == mQuizAdapter) {
            mQuizAdapter = new QuizAdapter(this, quiz);
        }
        return mQuizAdapter;
    }


    public void proceed(boolean isCorrect) {
        if (mQuizView == null) {
            return;
        }
        quizCorrect.add(isCorrect);
        int nextItem = mQuizView.getDisplayedChild() + 1;
        final int count = mQuizView.getAdapter().getCount();

        if (nextItem < count) {

            mQuizView.getInAnimation().start();
            mQuizView.getOutAnimation().start();
            mQuizView.showNext();
            setQuestionNumber();
            return;

        }

        Bundle bundle = new Bundle();
        bundle.putSerializable("quiz_results", (Serializable) quizCorrect);
        Intent intent = new Intent(this, QuizResultActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }


}
