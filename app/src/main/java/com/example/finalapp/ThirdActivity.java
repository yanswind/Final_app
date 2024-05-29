package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        TestManager testManager = new TestManager(ThirdActivity.this);
        ListView listView3 = findViewById(R.id.list3);
        List<TestItem> itemList = testManager.findByGrade(3);
        ScoreAdapter adapter = new ScoreAdapter(this, itemList);
        listView3.setAdapter(adapter);

        TextView nodata = findViewById(R.id.nodata);
        listView3.setEmptyView(nodata);
        nodata.setText("No data");
    }
}