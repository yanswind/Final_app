package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ScoreActivity extends AppCompatActivity {
    String TAG = "ScoreActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);



        //向数据库中写入数据
        TestManager manager = new TestManager(ScoreActivity.this);
        int stu_id = 42224231;
        String sex = "男";
        int grade1 = 1;
        String[] name = {"身高","体重","肺活量","立定跳远"};
        String[] record = {"180","65","3500","230"};

        //调用函数计算体测各项分数，写入数据库
        ScoreCalc scoreCalc = new ScoreCalc();
        int[] score = scoreCalc.calculateTest(sex,"大一",180,65f,3500,230,18.6f,8.53f,4.30f,8);
        for(int i=0;i<4;i++){
            TestItem item = new TestItem(stu_id,sex,grade1,name[i],record[i],score[i]);
            manager.add(item);
        }
        Log.i(TAG,"数据写入完毕");
    }

    public void click1(View v) {
        Intent intent = new Intent(ScoreActivity.this, FirstActivity.class);
        startActivity(intent);
    }
}