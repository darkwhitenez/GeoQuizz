package challenge.lccode.geoquizz.models;

import java.io.Serializable;

/**
 * Created by zizzy on 2.6.2017..
 */

public class QuizResult implements Serializable{
    public Integer id;
    public Boolean correct;

    public QuizResult(Integer id, Boolean correct) {
        this.id = id;
        this.correct = correct;
    }

    public QuizResult() {
    }
}
