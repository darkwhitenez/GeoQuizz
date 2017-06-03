package challenge.lccode.geoquizz.models;

import java.util.List;

/**
 * Created by zizzy on 2.6.2017..
 */

public class QuizItem {
    public long _id;
    public String question;
    public List<String> answers;
    public String correctAnswer;

    public QuizItem(){}

    public QuizItem(long _id, String question, List<String> answers, String correctAnswer){
        this._id = _id;
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public boolean isAnswerCorrect(String playerAnswer) {
        return playerAnswer.equals(correctAnswer);
    }
    public boolean isAnswerCorrect(int playerAnswer) {
        return answers.get(playerAnswer).equals(correctAnswer);
    }

}
