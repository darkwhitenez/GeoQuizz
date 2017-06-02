package challenge.lccode.geoquizz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_spin)
    TextView spin;
    @BindView(R.id.activity_main_challenge)
    TextView challenge;
    @BindView(R.id.activity_main_register)
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    /*  listeners   */

    @OnClick(R.id.activity_main_spin)
    public void spin(View view) {
        Intent intent = new Intent(this, GlobeActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.activity_main_challenge)
    public void challenge(View view) {
        Intent intent = new Intent(this, ChallengeActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.activity_main_register)
    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}
