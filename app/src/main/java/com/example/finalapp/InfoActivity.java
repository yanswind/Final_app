package com.example.finalapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {
    String TAG = "MyInfo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView school = findViewById(R.id.school);
        TextView name = findViewById(R.id.name);
        TextView gender = findViewById(R.id.gender);
        TextView id = findViewById(R.id.stu_id);
        TextView grade = findViewById(R.id.grade);
        TextView pw = findViewById(R.id.password);

        // 从SharedPreferences获取保存的信息
        SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
        String school_name = sharedPreferences.getString("school", "");
        String stu_name = sharedPreferences.getString("name", "");
        String sex = sharedPreferences.getString("sex", "");
        String studentId = sharedPreferences.getString("studentId", "");
        String stu_grade = sharedPreferences.getString("grade", "");
        String password = sharedPreferences.getString("password", "");

        // 设置TextView的显示文本
        school.setText(school_name);
        name.setText(stu_name);
        gender.setText(sex);
        id.setText(studentId);
        grade.setText(stu_grade);
        pw.setText(password);

        Button buttonChangePassword = findViewById(R.id.config);
        buttonChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangePasswordDialog();
            }
        });
    }

    private void showChangePasswordDialog() {
        final EditText editTextNewPassword = new EditText(this);
        editTextNewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editTextNewPassword.setHint("输入新密码");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("修改密码")
                .setView(editTextNewPassword)
                .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String newPassword = editTextNewPassword.getText().toString().trim();
                        if (!newPassword.isEmpty()) {
                            // 更新SharedPreferences中的密码
                            SharedPreferences sharedPreferences = getSharedPreferences("UserDetails", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("password", newPassword);
                            editor.apply();

                            // 可以在这里添加密码修改成功的提示
                            Toast.makeText(InfoActivity.this, "密码修改成功!", Toast.LENGTH_SHORT).show();
                        } else {
                            // 输入为空时的提示
                            Toast.makeText(InfoActivity.this, "密码不能为空!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .create()
                .show();
    }
}