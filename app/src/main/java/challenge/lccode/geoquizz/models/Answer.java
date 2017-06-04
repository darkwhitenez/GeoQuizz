package challenge.lccode.geoquizz.models;

/**
 * Created by zizzy on 4.6.2017..
 */

public class Answer {

    public Boolean correct;
    public String text;
    public Answer() {
    }

    public Answer(String text, Boolean correct) {
        this.text = text;
        this.correct = correct;
    }
}
