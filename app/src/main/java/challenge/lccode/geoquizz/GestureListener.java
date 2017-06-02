package challenge.lccode.geoquizz;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by sebo on 6/2/17.
 */

public class GestureListener extends GestureDetector.SimpleOnGestureListener {
    private static final String DEBUG_TAG = "Gestures";

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Log.d(DEBUG_TAG, "onFlingVel: " + velocityX/1000.0f);
        if (velocityX < 0)
            Maths.ROTATION_SPEED = velocityX/1000.0f;
        return true;
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG,"onDown: " + event.toString());
        Log.d("onTouch", "DOWN");
        if (Maths.ROTATION_SPEED < 0)
            Maths.IS_STOPPING = true;
        return true;
    }

}
