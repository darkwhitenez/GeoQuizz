package challenge.lccode.geoquizz;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterViewAnimator;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import challenge.lccode.geoquizz.adapter.QuizAdapter;
import challenge.lccode.geoquizz.helper.ApiVersionHelper;
import challenge.lccode.geoquizz.helper.Util;
import challenge.lccode.geoquizz.models.Quiz;
import challenge.lccode.geoquizz.models.QuizItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class QuizActivity extends AppCompatActivity {

    private ImageView back;
    private TextView quizNumberText;
    private AdapterViewAnimator mQuizView;
    private QuizAdapter mQuizAdapter;
    private Quiz quiz;
    private int quizCount;
    private List<Boolean> quizCorrect;
    private Toolbar toolbar;
    private ProgressDialog dialog;
    private Retrofit retrofit;
    private String countryCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        countryCode = (String)getIntent().getExtras().get("country_code");
        toolbar.setTitle(countryCode + " QUIZ");

        toolbar = (Toolbar) findViewById(R.id.toolbar_challenge);
        dialog = new ProgressDialog(this, R.style.ProgressbarTheme);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(true);
        dialog.setTitle("Please wait");
        dialog.setMessage("Loading the quiz data");
        dialog.show();
        retrofit = Util.getRetrofit();
        fetchQuiz();

    }

    private void initQuiz() {
        System.out.println(quiz.getQuizItems());
        quizCount = -1;
        quizCorrect = new ArrayList<>();
        quizNumberText = (TextView) findViewById(R.id.toolbar_count);
        setQuestionNumber();
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        mQuizView = (AdapterViewAnimator) findViewById(R.id.quiz_view);
        setQuizAnimations();
        mQuizView.setAdapter(getQuizAdapter());
        mQuizView.setSelection(0);
    }


    private void setQuestionNumber() {
        if (quizCount == -1) {
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


    private void fetchQuiz() {
        QuizRestInterface apiService = retrofit.create(QuizRestInterface.class);
        final Call<List<QuizItem>> callQuiz = apiService.getQuizForCountry(countryCode, Application.token);
        System.out.println(Application.token);
        callQuiz.enqueue(new Callback<List<QuizItem>>() {
            @Override
            public void onResponse(Call<List<QuizItem>> call, Response<List<QuizItem>> response) {
                int statusCode = response.code();
                System.out.println(statusCode);
                System.out.println(response.body());

                if (statusCode == 200) {
                    Application.quiz = new Quiz(response.body());
                    quiz = Application.quiz;
                    initQuiz();
                    dialog.hide();
                } else {
                    // error handling
                }

            }

            @Override
            public void onFailure(Call<List<QuizItem>> call, Throwable t) {
                t.printStackTrace();
            }

        });

    }


}
