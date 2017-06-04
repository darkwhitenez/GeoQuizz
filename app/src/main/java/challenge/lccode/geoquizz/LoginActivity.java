package challenge.lccode.geoquizz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    boolean isDone;
    FloatingActionButton done;
    private EditText name;
    private EditText password;
    private TextView activity_register_label;
    private TextView activity_register_account;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id
                .coordinatorLayout);

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
        activity_register_label = (TextView) findViewById(R.id.activity_register_label);
        activity_register_label.setText("LOGIN");
        activity_register_account = (TextView) findViewById(R.id.activity_register_account);
        activity_register_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        done = (FloatingActionButton) findViewById(R.id.done);
        name = (EditText) findViewById(R.id.activity_register_name);
        name.addTextChangedListener(textWatcher);
        password = (EditText) findViewById(R.id.activity_register_password);
        password.addTextChangedListener(textWatcher);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

    }


    public Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Application.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    private void loginUser() {
        final String userName = name.getText().toString().trim();
        String userPass = password.getText().toString().trim();
        QuizRestInterface apiService = getRetrofit().create(QuizRestInterface.class);
        Call<Object> call = apiService.loginUser(userName, userPass);
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    String gson = new Gson().toJson(response.body());
                    String token = new Gson().fromJson(gson, JsonObject.class).get("token").toString();
                    Application.token = token;
                    Application.isLoggedIn = true;
                    Application.name = userName;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                } else {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Invalid login credentials", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.printStackTrace();
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Error ocurred while connecting to server. Please wait and try again.", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }

        });


    }


}
