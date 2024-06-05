package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        // 从SharedPreferences读取保存的信息
        SharedPreferences sharedPreferences = getSharedPreferences("RunRecords", MODE_PRIVATE);
        String savedDistance = sharedPreferences.getString("distance", "");
        String savedSettime = sharedPreferences.getString("set_time", "");
        String savedTime = sharedPreferences.getString("actual_time", "");
        String savedDate = sharedPreferences.getString("date", "");

        ListView listView = findViewById(R.id.runRecord);
        List<RunRecord> runList = new ArrayList<>();
        RunRecord runRecord = new RunRecord(savedDistance,savedSettime,savedTime,savedDate);
        runList.add(runRecord);

        RecordAdapter adapter = new RecordAdapter(this,runList);
        listView.setAdapter(adapter);

        TextView nodata = findViewById(R.id.nodata);
        listView.setEmptyView(nodata);
        nodata.setText("No data");
    }
}