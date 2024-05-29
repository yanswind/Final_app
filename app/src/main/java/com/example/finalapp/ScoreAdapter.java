package com.example.finalapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ScoreAdapter extends ArrayAdapter {
    private static final String TAG = "ScoreAdapter";

    public ScoreAdapter(Context context, List<TestItem> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TestItem item = (TestItem) getItem(position);
        TextView name = (TextView) itemView.findViewById(R.id.itemName);
        TextView record = (TextView) itemView.findViewById(R.id.itemRecord);
        TextView score = (TextView) itemView.findViewById(R.id.itemScore);

        name.setText(item.getItemName());
        record.setText("成绩："+item.getItemRecord());
        score.setText("得分："+item.getItemScore());
        return itemView;
    }
}
