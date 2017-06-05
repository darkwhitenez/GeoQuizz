package challenge.lccode.geoquizz.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import challenge.lccode.geoquizz.Application;
import challenge.lccode.geoquizz.ChallengeActivity;
import challenge.lccode.geoquizz.R;
import challenge.lccode.geoquizz.helper.Countries;
import challenge.lccode.geoquizz.models.UserStats;

/**
 * Created by zizzy on 4.6.2017..
 */

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.MyViewHolder> {
    private Context context;

    public CountriesAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String countryCode = Countries.getByIndex(position);
        System.out.println(countryCode);

        holder.country_code.setText(countryCode);

        try{
            String uri = "flags_" + countryCode.toLowerCase();
            int id = context.getResources().getIdentifier(uri, "drawable", context.getPackageName());
            holder.country_icon.setImageResource(id);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return Countries.getSize();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView country_code;
        public ImageView country_icon;
        public String countryCode;


        public MyViewHolder(View view) {
            super(view);
            country_code = (TextView) view.findViewById(R.id.country_code);
            country_icon = (ImageView) view.findViewById(R.id.country_icon);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    countryCode = Countries.getByIndex(position);
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
                            Intent intent = new Intent(context, ChallengeActivity.class);
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


