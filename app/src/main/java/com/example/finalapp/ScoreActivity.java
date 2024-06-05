package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ScoreActivity extends AppCompatActivity {
    String TAG = "ScoreActivity";
    float score_first = 0;
    float score_second = 0;
    float score_third = 0;
    float score_fourth = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        TableLayout tableLayout = findViewById(R.id.table_score);
        Drawable border = getResources().getDrawable(R.drawable.table_border);
        tableLayout.setBackgroundDrawable(border);

        //向数据库中写入数据
        TestManager manager = new TestManager(ScoreActivity.this);
        int stu_id = 42224231;
        String sex = "男";
        int grade1 = 1;
        String[] name = {"身高","体重","肺活量","立定跳远","坐位体前屈","50m","1000m","引体向上"};
        String[] record1 = {"180","65","3500","230","18.6","8.5","4.32","9"};
        //调用函数计算体测各项分数，写入数据库
        ScoreCalc scoreCalc1 = new ScoreCalc();
        int[] score1 = scoreCalc1.calculateTest(sex,"大一",180,65f,3500,230,18.6f,8.5f,4.32f,9);
        for(int i=0;i<8;i++){
            TestItem item1 = new TestItem(stu_id,sex,grade1,name[i],record1[i],score1[i]);
            manager.add(item1);
        }
        Log.i(TAG,"大一体测数据写入完毕");

        //大二
        int grade2 = 2;
        String[] record2 = {"182","63.5","3820","228","21","7.9","4.35","12"};
        //调用函数计算体测各项分数，写入数据库
        ScoreCalc scoreCalc2 = new ScoreCalc();
        int[] score2 = scoreCalc2.calculateTest(sex,"大二",182,63.5f,3820,228,21f,7.9f,4.35f,12);
        for(int j=0;j<8;j++){
            TestItem item2 = new TestItem(stu_id,sex,grade2,name[j],record2[j],score2[j]);
            manager.add(item2);
        }
        Log.i(TAG,"大二体测数据写入完毕");

        // 体测总分数
        score_first = (float) (score1[1] * 0.15 + score1[2] * 0.15 + score1[3] * 0.1 + score1[4] * 0.1 + score1[5] * 0.2 + score1[6] * 0.2 + score1[7] * 0.1 + score1[8]);
        String result1 = String.format("%.2f", score_first);
        TextView scoreView1 = findViewById(R.id.score1);
        scoreView1.setText(result1);
        TextView addition_score1 = findViewById(R.id.addition_score1);
        addition_score1.setText(String.valueOf(score1[8]));

        score_second = (float) (score2[1] * 0.15 + score2[2] * 0.15 + score2[3] * 0.1 + score2[4] * 0.1 + score2[5] * 0.2 + score2[6] * 0.2 + score2[7] * 0.1 + score2[8]);
        String result2 = String.format("%.2f", score_second);
        TextView scoreView2 = findViewById(R.id.score2);
        scoreView2.setText(result2);
        TextView addition_score2 = findViewById(R.id.addtion_score2);
        addition_score2.setText(String.valueOf(score2[8]));

        TextView tip = findViewById(R.id.tip);
        tip.setText("注：假设X1、X2、X3、X4分别代表大一到大四的体测总分，则毕业时成绩=（X1+X2+X3)/3 x 50% + X4 x 50%。（毕业时成绩达不到50分者按结业或肄业处理）");
        //当前总分
        TextView total_score = findViewById(R.id.total_score);

        total_score.setText(String.format("%.2f", (score_first+score_second+score_third)/3*0.5+score_fourth*0.5));
    }

    public void click1(View v) {
        Intent intent = new Intent(ScoreActivity.this, FirstActivity.class);
        startActivity(intent);
    }
    public void click2(View v) {
        Intent intent = new Intent(ScoreActivity.this, SecondActivity.class);
        startActivity(intent);
    }
    public void click3(View v) {
        Intent intent = new Intent(ScoreActivity.this, ThirdActivity.class);
        startActivity(intent);
    }
    public void click4(View v) {
        Intent intent = new Intent(ScoreActivity.this, FourthActivity.class);
        startActivity(intent);
    }
}