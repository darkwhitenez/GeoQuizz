package challenge.lccode.geoquizz.models;

import java.util.List;

/**
 * Created by zizzy on 2.6.2017..
 */

public class Quiz {
    public String _id;
    public List<QuizItem> quizItems;

    public Quiz() {
    }

    public Quiz(String _id, List<QuizItem> quizItems) {
    this._id = _id;
        this.quizItems = quizItems;
    }

}
