package challenge.lccode.geoquizz.helper;

import android.os.Build;

/**
 * Created by zizzy on 3.6.2017..
 */

public class ApiVersionHelper {

    public static boolean isEqualOrHigher(int level){
        return Build.VERSION.SDK_INT >= level;
    }

}
