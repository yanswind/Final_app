package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {
    String TAG = "Home";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //数据
        List<String> list1 = new ArrayList<String>();
        list1.add("体测标准");
        list1.add("体测分数");
        list1.add("体测计算器");
        //构造适配器
        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list1);

        //获取控件
        ListView listView = findViewById(R.id.list1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = (String) parent.getItemAtPosition(position);
                Log.i(TAG,"onCreate:"+clickedItem);
                // 处理点击事件
                if(position == 2){
                    Intent intent = new Intent(Home.this, score_calc.class);
                    startActivity(intent);
                }

            }
        });
    }
}
