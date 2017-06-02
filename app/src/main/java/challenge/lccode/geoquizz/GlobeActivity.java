package challenge.lccode.geoquizz;

import android.databinding.DataBindingUtil;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;


/**
 * Created by zizzy on 2.6.2017..
 */

public class GlobeActivity extends AppCompatActivity {

    private GestureDetectorCompat gestDet;
    private GLSurfaceView mGlSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.mGlSurfaceView = new GLSurfaceView(this);
        this.mGlSurfaceView.setRenderer(new GlRenderer(this));
        this.setContentView(this.mGlSurfaceView);

        gestDet = new GestureDetectorCompat(this, new GestureListener());
    }

    /**
     * Remember to resume the glSurface.
     */
    @Override
    protected void onResume() {
        super.onResume();
        this.mGlSurfaceView.onResume();
    }

    /**
     * Also pause the glSurface.
     */
    @Override
    protected void onPause() {
        this.mGlSurfaceView.onPause();
        super.onPause();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.gestDet.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}