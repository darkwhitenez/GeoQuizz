package challenge.lccode.geoquizz.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import challenge.lccode.geoquizz.QuizView;
import challenge.lccode.geoquizz.models.Quiz;
import challenge.lccode.geoquizz.models.QuizItem;

/**
 * Created by zizzy on 3.6.2017..
 */

public class QuizAdapter extends BaseAdapter {
    private final Context context;
    private final Quiz quiz;

    public QuizAdapter(Context context, Quiz quiz) {
        this.context = context;
        this.quiz = quiz;
    }


    @Override
    public int getCount() {
        return quiz.getQuizItems().size();
    }

    @Override
    public QuizItem getItem(int position) {
        return quiz.getQuizItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return quiz.getQuizItems().get(position)._id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final QuizItem quizItem = getItem(position);
        convertView = getQuizView(quizItem);
        return convertView;
    }

    private QuizView getQuizView(QuizItem quizItem){
        if (null == quiz) {
            throw new IllegalArgumentException("Quiz must not be null");
        }
        return new QuizView(context, quizItem);
    }

}
