package challenge.lccode.geoquizz;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class RegisterActivity extends AppCompatActivity {

    boolean isDone;
    FloatingActionButton done;
    private EditText name;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initContentViews();
        isDone = false;
    }

    private void initContentViews() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // hiding the floating action button if text is empty
                if (isDone && s.length() == 0) {
                    isDone = false;
                    done.hide();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // showing the floating action button if avatar is selected and input data is valid
                if (!isDone && name.getText().length() > 0 && password.getText().length() > 0) {
                    isDone = true;
                    done.show();
                }
            }
        };
        done = (FloatingActionButton)  findViewById(R.id.done);
        name = (EditText) findViewById(R.id.activity_register_name);
        name.addTextChangedListener(textWatcher);
        password = (EditText) findViewById(R.id.activity_register_password);
        password.addTextChangedListener(textWatcher);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public static void start(Activity activity, Boolean isInEditMode) {
        Intent starter = new Intent(activity, RegisterActivity.class);
        ActivityCompat.startActivity(activity,
                starter,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }



}
