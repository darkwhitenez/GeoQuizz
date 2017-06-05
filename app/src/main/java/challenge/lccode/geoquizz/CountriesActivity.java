package challenge.lccode.geoquizz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;


import challenge.lccode.geoquizz.adapter.CountriesAdapter;
import challenge.lccode.geoquizz.helper.Util;
import retrofit2.Retrofit;

/**
 * Created by zizzy on 4.6.2017..
 */

public class CountriesActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private CountriesAdapter countriesAdapter;
    private ProgressBar bar;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retrofit = Util.getRetrofit();
        setContentView(R.layout.activity_countries);
        toolbar = (Toolbar) findViewById(R.id.activity_countries_toolbar);
        toolbar.setTitle("COUNTRIES");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bar = (ProgressBar) findViewById(R.id.progressbar);
        recyclerView = (RecyclerView) findViewById(R.id.countries_recycler_view);
        countriesAdapter = new CountriesAdapter(this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(countriesAdapter);
        countriesAdapter.notifyDataSetChanged();

    }


}
