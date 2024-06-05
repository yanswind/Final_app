package com.example.finalapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class RunActivity extends AppCompatActivity {
    private EditText setTime;
    private EditText setLength;
    private long baseTimer = 0; // 计时开始时的时间基线
    private TextView timerView; // 显示时间的TextView
    private Handler myhandler = new Handler(); // Handler对象
    private boolean isRunning = false; // 标记计时器是否正在运行
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        Button btnStart = findViewById(R.id.btnStart);
        Button btnStop = findViewById(R.id.btnStop);
        Button btnRest = findViewById(R.id.btnReset);
        Button btnRecord = findViewById(R.id.btn_record);
        setTime = findViewById(R.id.settime);
        setLength = findViewById(R.id.length);

        baseTimer = SystemClock.elapsedRealtime();
        timerView = findViewById(R.id.timerView);

        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RunActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRunning = true;
                Handler myhandler = new Handler() {
                    public void handleMessage(android.os.Message msg) {
                        if (isRunning) {
                            if (0 == baseTimer) {
                                baseTimer = SystemClock.elapsedRealtime();
                            }
                            int time = (int) ((SystemClock.elapsedRealtime() - baseTimer) / 1000);
                            String mm = new DecimalFormat("00").format(time / 60);
                            String ss = new DecimalFormat("00").format(time % 60);
                            if (null != timerView) {
                                timerView.setText(mm + ":" + ss);
                            }

                            sendMessageDelayed(Message.obtain(this, 0x0), 1000);
                        }

                    }
                };
                myhandler.sendMessageDelayed(Message.obtain(myhandler, 0x0), 1000);
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("RunRecords", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String distance = setLength.getText().toString();
        String settime = setTime.getText().toString();
        String time = timerView.getText().toString();

        LocalDate currentDate = LocalDate.now(); // 获取当前日期
        // 定义日期格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 将日期转换为字符串
        String date = currentDate.format(formatter);

        // 将跑步记录保存到 SharedPreferences
        editor.putString("distance",distance);
        editor.putString("set_time",settime);
        editor.putString("actual_time", time);
        editor.putString("date",date);
        editor.apply();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void clickStop(View view) {
        isRunning = false;
        baseTimer = 0;
        saveData();
        showTimeUpDialog();
    }

    public void clickReset(View view) {
        setTime.setText("");
        setLength.setText("");
        timerView.setText("00:00");
        // 如果计时器正在运行，可以在这里停止它
        if (isRunning) {
            myhandler.removeCallbacksAndMessages(null);
            isRunning = false;
            baseTimer = 0;
        }
    }

    private void showTimeUpDialog() {
        new AlertDialog.Builder(this)
                .setTitle("温馨提示：")
                .setMessage("用时"+timerView.getText().toString())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                })
                .show();
    }
}