package challenge.lccode.geoquizz.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import challenge.lccode.geoquizz.Application;
import challenge.lccode.geoquizz.R;
import challenge.lccode.geoquizz.helper.Util;
import challenge.lccode.geoquizz.models.UserStats;
import retrofit2.Retrofit;

/**
 * Created by zizzy on 4.6.2017..
 */

public class UserStatsAdapter extends RecyclerView.Adapter<UserStatsAdapter.MyViewHolder> {


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_stats_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if (Application.userStats == null || Application.userStats.isEmpty()) {
            return;
        }

        UserStats userStat = Application.userStats.get(position);
        holder.country_code.setText(userStat.country_code);
        holder.q_correct.setText(userStat.questions_correct);
        holder.q_played.setText(userStat.questions_answered);

    }

    @Override
    public int getItemCount() {
        if (Application.userStats == null || Application.userStats.isEmpty()) {
            return 0;
        }
        return Application.userStats.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView country_code, q_correct, q_played;
        public Button play_country;
        public Retrofit retrofit;

        public MyViewHolder(View view) {
            super(view);
            retrofit = Util.getRetrofit();

            country_code = (TextView) view.findViewById(R.id.country_code);
            q_correct = (TextView) view.findViewById(R.id.q_correct);
            q_played = (TextView) view.findViewById(R.id.q_played);
           // play_country = (Button) view.findViewById(R.id.play_country);
        }

        @Override
        public void onClick(View v) {

        }
    }
}


