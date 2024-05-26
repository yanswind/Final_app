package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        TestManager testManager = new TestManager(FirstActivity.this);
        ListView listView1 = findViewById(R.id.list1);
        List<TestItem> itemList = testManager.findByGrade(1);
        ScoreAdapter adapter = new ScoreAdapter(this, itemList);
        listView1.setAdapter(adapter);

    }
}