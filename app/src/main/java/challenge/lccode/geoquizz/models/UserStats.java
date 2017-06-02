package challenge.lccode.geoquizz.models;

import java.util.List;

/**
 * Created by zizzy on 2.6.2017..
 */

public class UserStats {

    public Integer numberOfPlayed;
    public Double percentageOfCorrectAnswers;
    public List<UserRateByCountry> userRates;

    UserStats() {
    }
}
