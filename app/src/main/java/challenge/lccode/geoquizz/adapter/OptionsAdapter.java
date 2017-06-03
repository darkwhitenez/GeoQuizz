package challenge.lccode.geoquizz.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zizzy on 3.6.2017..
 */

public class OptionsAdapter extends BaseAdapter {
    private List<String> answers;
    private int layoutId;
    private Context context;

    public OptionsAdapter(List<String> answers, @LayoutRes int layoutId, Context context) {
        this.answers = answers;
        this.layoutId = layoutId;
        this.context = context;
    }

    @Override
    public int getCount() {
        return answers.size();
    }

    @Override
    public Object getItem(int position) {
        return answers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(layoutId, parent, false);
        }
        String text = answers.get(position);
        ((TextView) convertView).setText(getAlphaIndex(position) + "  " + text);
        return convertView;
    }

    private String getAlphaIndex(int p){
    switch(p){
        case 0:
            return "a)";
        case 1:
            return "b)";
        case 2:
            return "c)";
        case 3:
            return "d)";
        default:
            return "";
    }
    }

}
