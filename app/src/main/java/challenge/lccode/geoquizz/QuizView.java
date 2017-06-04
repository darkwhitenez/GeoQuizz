package challenge.lccode.geoquizz;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import challenge.lccode.geoquizz.adapter.OptionsAdapter;
import challenge.lccode.geoquizz.models.QuizItem;

/**
 * Created by zizzy on 3.6.2017..
 */

public class QuizView extends FrameLayout {

    private final LayoutInflater mLayoutInflater;
    private GridView gridView;
    private Context context;
    private QuizItem quizItem;
    private View answeredCellView;
    private int mAnswered = -1;
    private TextView mQuestionView;
    private FloatingActionButton mSubmitAnswer;

    public QuizView(Context context, QuizItem quizItem) {
        super(context);
        this.context = context;
        this.quizItem = quizItem;
        mLayoutInflater = LayoutInflater.from(context);
        createQuizQuestionView();
        createQuizOptionsView();
        addContentToTheView();
        addOnLayoutChangeListener(new OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom,
                                       int oldLeft,
                                       int oldTop, int oldRight, int oldBottom) {
                removeOnLayoutChangeListener(this);
                createFloatingActionButton();
            }
        });

    }

    private void addContentToTheView() {
        LinearLayout container = new LinearLayout(context);
        container.setOrientation(LinearLayout.VERTICAL);

        container.addView(mQuestionView, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));
        container.addView(gridView, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        addView(container, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));


    }

    protected void createQuizQuestionView() {
        mQuestionView = (TextView) mLayoutInflater.inflate(R.layout.question, this, false);
        mQuestionView.setText(quizItem.question);
        mQuestionView.setGravity(Gravity.CENTER);
    }

    protected void createQuizOptionsView() {
        gridView = new GridView(context);
        gridView.setTextAlignment(TEXT_ALIGNMENT_VIEW_START);
        gridView.setGravity(Gravity.START);
        gridView.setSelector(R.drawable.selector_button);
        gridView.setNumColumns(1);
        gridView.setAdapter(new OptionsAdapter(quizItem.answers, R.layout.item_answer, context));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                answeredCellView = view;
                mAnswered = position;
                allowAnswer();
            }
        });
    }


    private void createFloatingActionButton() {
        addView(getSubmitButton());
    }

    private FloatingActionButton getSubmitButton() {
        if (null == mSubmitAnswer) {
            mSubmitAnswer = (FloatingActionButton) getLayoutInflater()
                    .inflate(R.layout.answer_submit, this, false);
            mSubmitAnswer.hide();
            mSubmitAnswer.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    submitAnswer();
                    mSubmitAnswer.setEnabled(false);
                }
            });
        }
        return mSubmitAnswer;
    }

    protected LayoutInflater getLayoutInflater() {
        return mLayoutInflater;
    }


    private void submitAnswer() {
        final boolean answerCorrect = isAnswerCorrect();
        if (answerCorrect) {
            answeredCellView.setBackgroundColor(ContextCompat.getColor(context, R.color.green));
        } else {
            answeredCellView.setBackgroundColor(ContextCompat.getColor(context, R.color.red));
        }

        new CountDownTimer(600, 200) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                if (getContext() instanceof ChallengeActivity) {
                    ((ChallengeActivity) getContext()).proceed(answerCorrect);
                }
            }
        }.start();


    }

    protected boolean isAnswerCorrect() {
        return quizItem.isAnswerCorrect(mAnswered);
    }

    protected void allowAnswer() {
        mSubmitAnswer.show();
    }

}
