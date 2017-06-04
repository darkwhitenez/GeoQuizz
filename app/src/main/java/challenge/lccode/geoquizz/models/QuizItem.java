package challenge.lccode.geoquizz.models;

import java.util.List;

/**
 * Created by zizzy on 2.6.2017..
 */

public class QuizItem {

    public List<Answer> answers;
    public String text;
    public Long _id;


    public QuizItem() {
    }

    public String getQuestion() {
        return text;
    }

    public QuizItem(Long _id, String question, List<Answer> answers) {
        this._id = _id;
        this.text = question;
        this.answers = answers;
    }

    public boolean isAnswerCorrect(String playerAnswer) {
        for (Answer a : answers) {
            if (a.correct && a.text.equals(playerAnswer)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAnswerCorrect(int playerAnswer) {
        return answers.get(playerAnswer).correct;
    }

}
