package challenge.lccode.geoquizz.models;

/**
 * Created by zizzy on 2.6.2017..
 */

public class UserLogin {
    public String _id;
    public String name;
    public String password;

    public UserLogin() {
    }

    public UserLogin(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
