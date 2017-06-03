package challenge.lccode.geoquizz;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterViewAnimator;
import android.widget.ImageView;

import challenge.lccode.geoquizz.adapter.QuizAdapter;
import challenge.lccode.geoquizz.helper.ApiVersionHelper;
import challenge.lccode.geoquizz.models.Quiz;


public class ChallengeActivity extends AppCompatActivity {

    private ImageView back;
    private AdapterViewAnimator mQuizView;
    private QuizAdapter mQuizAdapter;
    private Quiz quiz;
    private int selection = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChallengeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        quiz = FakeQuiz.getQuiz();

        mQuizView = (AdapterViewAnimator) findViewById(R.id.quiz_view);
        mQuizView.setAdapter(getQuizAdapter());
        mQuizView.setSelection(selection++);
        setQuizAnimations();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setQuizAnimations() {
        if (ApiVersionHelper.isEqualOrHigher(Build.VERSION_CODES.LOLLIPOP)) {
            return;
        }
        mQuizView.setInAnimation(this, R.animator.slide_in_bottom);
        mQuizView.setOutAnimation(this, R.animator.slide_out_top);
    }

    private QuizAdapter getQuizAdapter() {
        if (null == mQuizAdapter) {
            mQuizAdapter = new QuizAdapter(this, quiz);
        }
        return mQuizAdapter;
    }


    public void proceed() {
        if (mQuizView == null) {
            return;
        }
        int nextItem = mQuizView.getDisplayedChild() + 1;
        final int count = mQuizView.getAdapter().getCount();
        System.out.println(nextItem + " : " + count);
        if (nextItem < count) {
            mQuizView.showNext();
            return;
        }

        Intent intent = new Intent(this, QuizResultActivity.class);
        startActivity(intent);

    }


}
