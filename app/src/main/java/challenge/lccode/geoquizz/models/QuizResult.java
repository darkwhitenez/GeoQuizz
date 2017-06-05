package challenge.lccode.geoquizz.models;

/**
 * Created by zizzy on 2.6.2017..
 */

public class QuizResult {
    public Integer id;
    public Boolean correct;

    public QuizResult(Integer id, Boolean correct) {
        this.id = id;
        this.correct = correct;
    }

    public QuizResult() {
    }
}
