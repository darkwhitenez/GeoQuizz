package challenge.lccode.geoquizz.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zizzy on 2.6.2017..
 */

public class Quiz implements Serializable {
    public List<QuizItem> quizItems;

    public List<QuizItem> getQuizItems() {
        return quizItems;
    }


    public Quiz() {
    }

    public Quiz(List<QuizItem> quizItems) {
        this.quizItems = quizItems;
    }

}
