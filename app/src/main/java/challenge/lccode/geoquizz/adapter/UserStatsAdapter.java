package challenge.lccode.geoquizz.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import challenge.lccode.geoquizz.Application;
import challenge.lccode.geoquizz.QuizActivity;
import challenge.lccode.geoquizz.R;
import challenge.lccode.geoquizz.models.Quiz;
import challenge.lccode.geoquizz.models.UserStats;
import retrofit2.Retrofit;

/**
 * Created by zizzy on 4.6.2017..
 */

public class UserStatsAdapter extends RecyclerView.Adapter<UserStatsAdapter.MyViewHolder> {
    private Context context;

    public UserStatsAdapter(Context context) {
        this.context = context;
    }

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
        holder.q_correct.setText(userStat.questions_correct.toString());
        holder.q_played.setText(userStat.questions_answered.toString());

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

        public MyViewHolder(View view) {
            super(view);
            country_code = (TextView) view.findViewById(R.id.country_code);
            q_correct = (TextView) view.findViewById(R.id.q_correct);
            q_played = (TextView) view.findViewById(R.id.q_played);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final String countryCode = Application.userStats.get(position).country_code;
                    AlertDialog.Builder adb = new AlertDialog.Builder(context);
                    adb.setTitle("New quiz");
                    adb.setMessage("Do you wish to start a " + countryCode + " quiz?");
                    adb.setCancelable(true);
                    String yesButtonText = "Yes";
                    String noButtonText = "No";
                    adb.setNegativeButton(noButtonText, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });

                    adb.setPositiveButton(yesButtonText, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("country_code", countryCode);
                            Intent intent = new Intent(context, QuizActivity.class);
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        }
                    });
                    adb.show();
                }
            });
        }

        @Override
        public void onClick(View v) {
        }
    }
}


