package com.example.finalapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class RecordAdapter extends ArrayAdapter {
    public RecordAdapter(Context context, List<RunRecord> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_record, parent, false);
        }
        RunRecord record = (RunRecord) getItem(position);
        TextView date = (TextView) itemView.findViewById(R.id.date);
        TextView distance = (TextView) itemView.findViewById(R.id.setLength);
        TextView settime = (TextView) itemView.findViewById(R.id.setTime);
        TextView time = (TextView) itemView.findViewById(R.id.time);

        date.setText(record.getDate());
        distance.setText("跑步距离："+record.getDistance());
        settime.setText("设定时长："+record.getSetTime());
        time.setText("计时："+record.getActualTime());

        return itemView;
    }
}
