package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TestManager testManager = new TestManager(SecondActivity.this);
        ListView listView2 = findViewById(R.id.list2);
        List<TestItem> itemList = testManager.findByGrade(2);
        ScoreAdapter adapter = new ScoreAdapter(this, itemList);
        listView2.setAdapter(adapter);

        TextView nodata = findViewById(R.id.nodata);
        listView2.setEmptyView(nodata);
        nodata.setText("No data");
    }
}