package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TableLayout;

public class StandardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard);

        TableLayout tableLayout1 = findViewById(R.id.table1);
        Drawable border1 = getResources().getDrawable(R.drawable.table_border);
        tableLayout1.setBackgroundDrawable(border1);

        TableLayout tableLayout2 = findViewById(R.id.table2);
        Drawable border2 = getResources().getDrawable(R.drawable.table_border);
        tableLayout2.setBackgroundDrawable(border2);
    }
}