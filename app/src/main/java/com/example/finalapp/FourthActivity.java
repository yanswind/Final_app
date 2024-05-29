package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class FourthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        TestManager testManager = new TestManager(FourthActivity.this);
        ListView listView4 = findViewById(R.id.list4);
        List<TestItem> itemList = testManager.findByGrade(4);
        ScoreAdapter adapter = new ScoreAdapter(this, itemList);
        listView4.setAdapter(adapter);

        TextView nodata = findViewById(R.id.nodata);
        listView4.setEmptyView(nodata);
        nodata.setText("No data");
    }
}