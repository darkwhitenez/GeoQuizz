package challenge.lccode.geoquizz;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by zizzy on 5.6.2017..
 */

public class SettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Button server_uri_btn_ok;
    private EditText server_connection;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        toolbar = (Toolbar) findViewById(R.id.activity_settings_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        server_uri_btn_ok = (Button) findViewById(R.id.server_uri_btn_ok);
        server_connection = (EditText) findViewById(R.id.server_connection);
        sharedPreferences = getSharedPreferences("server_uri", Activity.MODE_PRIVATE);

        String server_uri = sharedPreferences.getString("server_url", "");
        server_connection.setText(server_uri);
        server_uri_btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("server_uri", server_connection.getText().toString());
                editor.commit();
            }
        });

    }
}
