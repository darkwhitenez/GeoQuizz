package challenge.lccode.geoquizz;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.LoginFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import challenge.lccode.geoquizz.models.UserLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    boolean isDone;
    FloatingActionButton done;
    private EditText name;
    private EditText password;
    private CoordinatorLayout coordinatorLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
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
        done = (FloatingActionButton) findViewById(R.id.done);
        name = (EditText) findViewById(R.id.activity_register_name);
        name.addTextChangedListener(textWatcher);
        password = (EditText) findViewById(R.id.activity_register_password);
        password.addTextChangedListener(textWatcher);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

    }

    public static void start(Activity activity, Boolean isInEditMode) {
        Intent starter = new Intent(activity, RegisterActivity.class);
        ActivityCompat.startActivity(activity,
                starter,
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity).toBundle());
    }

    public Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Application.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    private void loginUser() {
        String userName = name.getText().toString().trim();
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


    private void registerUser() {

        String userName = name.getText().toString().trim();
        String userPass = password.getText().toString().trim();
        System.out.println(userName + " : " + userPass);
        QuizRestInterface apiService = getRetrofit().create(QuizRestInterface.class);
        Call<Void> call = apiService.registerUser(userName, userPass);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Status: " + statusCode, Snackbar.LENGTH_LONG);
                    snackbar.show();
                    loginUser();

                } else if (statusCode == 409) {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Username is already taken", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                } else {
                    Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Error ocurred while connecting to server. Please wait and try again.", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "Error ocurred while connecting to server. Please wait and try again.", Snackbar.LENGTH_SHORT);
                snackbar.show();
            }


        });
    }


}
