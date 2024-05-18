package com.example.finalapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    private RadioGroup rg_tab_bar;
    private RadioButton rb_home;
    private FragmentManager fManager;
    String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fManager = getFragmentManager();
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);

        //注册页面
        LayoutInflater inflater1 = getLayoutInflater();
        View dialogView1 = inflater1.inflate(R.layout.register, null);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(HomeActivity.this);
        builder1.setView(dialogView1);

        final AlertDialog registerDialog = builder1.create();
        registerDialog.show();

        // 获取布局中的控件
        final EditText school = dialogView1.findViewById(R.id.school);
        final EditText name = dialogView1.findViewById(R.id.name);
        final RadioGroup gender = dialogView1.findViewById(R.id.gender);
        final EditText id1 = dialogView1.findViewById(R.id.stu_id);
        final EditText grade1 = dialogView1.findViewById(R.id.grade);
        final EditText pw1 = dialogView1.findViewById(R.id.password);
        Button btn_register = dialogView1.findViewById(R.id.btn_register);
        Button btn_to_login = dialogView1.findViewById(R.id.btn_to_login);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取输入数据
                String school_name = school.getText().toString().trim();
                String stu_name = name.getText().toString().trim();
                String stu_id1 = id1.getText().toString().trim();
                String stu_grade1 = grade1.getText().toString().trim();
                String password1 = pw1.getText().toString().trim();
                String sex = ((RadioButton) gender.findViewById(gender.getCheckedRadioButtonId())).getText().toString();
                // 校验学号和密码
                if (!stu_id1.matches("\\d{8}")) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(HomeActivity.this);
                    builder2.setTitle("错误")
                            .setMessage("学号必须是长度为8的数字")
                            .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.i(TAG, "学号输入错误");
                                    dialog.dismiss();
                                }
                            });
                    builder2.create().show();
                }
                // 保存信息到SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("school", school_name);
                editor.putString("name", stu_name);
                editor.putString("sex", sex);
                editor.putString("studentId", stu_id1);
                editor.putString("grade", stu_grade1);
                editor.putString("password", password1);
                editor.apply();

                // 注册成功
                new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("注册成功")
                        .setMessage("注册成功，请登录")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // 显示登录AlertDialog
                                showLoginDialog();
                                registerDialog.dismiss();//关闭注册对话框
                            }
                        })
                        .show();
            }
        });

        //如已注册成功，登录
        btn_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 显示登录AlertDialog
                showLoginDialog();
                registerDialog.dismiss();//关闭注册对话框
            }
        });

        //首页列表数据
        List<String> list1 = new ArrayList<String>();
        list1.add("体测标准");
        list1.add("体测分数");
        list1.add("体测计算器");
        //构造适配器
        ListAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list1);

        //获取控件
        ListView listView = findViewById(R.id.homelist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = (String) parent.getItemAtPosition(position);
                Log.i(TAG,"onCreate:"+clickedItem);
                // 处理点击事件
                if(position == 2){
                    Intent intent = new Intent(HomeActivity.this, CalcActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    private void showLoginDialog() {
        //登录页面
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.login, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setView(dialogView);

        final AlertDialog loginDialog = builder.create();
        loginDialog.show();

        final EditText id = dialogView.findViewById(R.id.stu_id);
        final EditText pw = dialogView.findViewById(R.id.password);
        Button btn_login = dialogView.findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stu_id = id.getText().toString().trim();
                String password = pw.getText().toString().trim();

                // 从SharedPreferences读取保存的信息
                SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
                String savedStudentId = sharedPreferences.getString("studentId", "");
                String savedPassword = sharedPreferences.getString("password", "");

                // 比较登录信息和保存的信息
                if (stu_id.equals(savedStudentId) && password.equals(savedPassword)) {
                    Toast.makeText(HomeActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                    loginDialog.dismiss(); // 关闭对话框
                    ListView homelist = findViewById(R.id.homelist);
                    ProgressBar bar = findViewById(R.id.progressBar);
                    TextView nodata = findViewById(R.id.nodata);
                    homelist.setEmptyView(nodata);
                    // 显示Loading进度条
                    bar.setVisibility(View.VISIBLE);
                    // 模拟网络请求
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //hide bar
                            bar.setVisibility(View.GONE);
                            nodata.setText("No Data");
                        }
                    }, 1000); // 3秒后执行
                } else {
                    Toast.makeText(HomeActivity.this, "登录失败，学号或密码错误!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        if(checkedId == R.id.rb_home){
            //当前页面
        }else if(checkedId == R.id.rb_community){

        }else if(checkedId == R.id.rb_info){
            Intent intent = new Intent(HomeActivity.this, InfoActivity.class);
            startActivity(intent);
        }
        fTransaction.commit();
    }

}