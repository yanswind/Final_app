package com.example.finalapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalcActivity extends AppCompatActivity {
    private Spinner grade;
    private RadioGroup gender;
    private EditText height;
    private EditText weight;
    private EditText capacity1;
    private EditText jump1;
    private EditText sit_reach;
    private EditText run1;
    private EditText run2;
    private EditText up1;
    private TextView result;
    String TAG = "Calc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_calc);

        grade = findViewById(R.id.select_grade);
        gender = findViewById(R.id.select_gender);
        height = findViewById(R.id.heightInput);
        weight = findViewById(R.id.weightInput);
        capacity1 = findViewById(R.id.capacityInput);
        sit_reach = findViewById(R.id.input_sit_reach);
        jump1 = findViewById(R.id.jumpInput);
        run1 = findViewById(R.id.input_run1);
        run2 = findViewById(R.id.input_run2);
        up1 = findViewById(R.id.input_up);
        result = findViewById(R.id.result);
        TextView run_style = findViewById(R.id.run2);
        TextView up_style = findViewById(R.id.up);
        Button calc = findViewById(R.id.calc);

        final String[] selectedGrade = new String[1];
        final String[] sex = new String[1];
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton sexbtn = (RadioButton) findViewById(checkedId);
                sex[0] = (String) sexbtn.getText();
                if ("男".equals(sexbtn.getText())) {
                    run_style.setText("1000米");
                    up_style.setText("引体向上");
                } else if ("女".equals(sexbtn.getText())) {
                    run_style.setText("800米");
                    up_style.setText("仰卧起坐");
                }
                Log.i(TAG, "onCreate:" + sexbtn.getText());
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_values, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.select_grade);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedGrade[0] = parent.getItemAtPosition(position).toString();
                Log.i(TAG, "onCreate:" + selectedGrade[0]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 可以在Spinner没有任何选项被选中时做一些处理
            }
        });
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatePhysicalTest(selectedGrade[0], sex[0]);
            }
        });
    }

    private void calculatePhysicalTest(String grade, String sex) {

        float height1 = Float.parseFloat(height.getText().toString()) / 100;
        float weight1 = Float.parseFloat(weight.getText().toString());
        int capacity = Integer.parseInt(capacity1.getText().toString());
        int jump = Integer.parseInt(jump1.getText().toString());
        float reach = Float.parseFloat(sit_reach.getText().toString());
        float shortrun = Float.parseFloat(run1.getText().toString());
        float longrun = Float.parseFloat(run2.getText().toString());
        int up = Integer.parseInt(up1.getText().toString());

        double bmi = weight1 / (height1 * height1);
        int BMIScore = 0;
        int capacityScore = 0;
        int reachScore = 0;
        int jumpScore = 0;
        int shortrunScore = 0;
        int longrunScore = 0;
        double differenceSecond = 0;
        int additionScore = 0;
        int pullupsitupScore = 0;
        if ("男".equals(sex)) {
            //BMI指数
            if (bmi >= 17.9 && bmi <= 23.9) {
                BMIScore = 100;
            } else if (bmi <= 17.8 || (bmi >= 24 && bmi <= 27.9)) {
                BMIScore = 80;
            } else if (bmi >= 28) {
                BMIScore = 60;
            }
        } else if ("女".equals(sex)) {
            if (bmi >= 17.2 && bmi <= 23.9) {
                BMIScore = 100;
            } else if (bmi <= 17.1 || (bmi >= 24 && bmi <= 27.9)) {
                BMIScore = 80;
            } else if (bmi >= 28) {
                BMIScore = 60;
            }
        }
        if("大一".equals(grade)||"大二".equals(grade)) {
            if ("男".equals(sex)) {
                //肺活量
                if (capacity >= 5040) {
                    capacityScore = 100;
                } else if (capacity >= 4920) {
                    capacityScore = 95;
                } else if (capacity >= 4800) {
                    capacityScore = 90;
                } else if (capacity >= 4500) {
                    capacityScore = 85;
                } else if (capacity >= 4300) {
                    capacityScore = 80;
                } else if (capacity >= 4180) {
                    capacityScore = 78;
                } else if (capacity >= 4060) {
                    capacityScore = 76;
                } else if (capacity >= 3940) {
                    capacityScore = 74;
                } else if (capacity >= 3820) {
                    capacityScore = 72;
                } else if (capacity >= 3700) {
                    capacityScore = 70;
                } else if (capacity >= 3580) {
                    capacityScore = 68;
                } else if (capacity >= 3460) {
                    capacityScore = 66;
                } else if (capacity >= 3340) {
                    capacityScore = 64;
                } else if (capacity >= 3220) {
                    capacityScore = 62;
                } else if (capacity >= 3100) {
                    capacityScore = 60;
                } else if (capacity >= 2940) {
                    capacityScore = 50;
                } else if (capacity >= 2780) {
                    capacityScore = 40;
                } else if (capacity >= 2620) {
                    capacityScore = 30;
                } else if (capacity >= 2460) {
                    capacityScore = 20;
                } else if (capacity >= 2300) {
                    capacityScore = 10;
                }
                //坐位体前屈
                if (reach >= 24.9) {
                    reachScore = 100;
                } else if (reach >= 23.1) {
                    reachScore = 95;
                } else if (reach >= 21.3) {
                    reachScore = 90;
                } else if (reach >= 19.5) {
                    reachScore = 85;
                } else if (reach >= 17.7) {
                    reachScore = 80;
                } else if (reach >= 16.3) {
                    reachScore = 78;
                } else if (reach >= 14.9) {
                    reachScore = 76;
                } else if (reach >= 13.5) {
                    reachScore = 74;
                } else if (reach >= 12.1) {
                    reachScore = 72;
                } else if (reach >= 10.7) {
                    reachScore = 70;
                } else if (reach >= 9.3) {
                    reachScore = 68;
                } else if (reach >= 7.9) {
                    reachScore = 66;
                } else if (reach >= 6.5) {
                    reachScore = 64;
                } else if (reach >= 5.1) {
                    reachScore = 62;
                } else if (reach >= 3.7) {
                    reachScore = 60;
                } else if (reach >= 2.7) {
                    reachScore = 50;
                } else if (reach >= 1.7) {
                    reachScore = 40;
                } else if (reach >= 0.7) {
                    reachScore = 30;
                } else if (reach >= -0.3) {
                    reachScore = 20;
                } else if (reach >= -1.3) {
                    reachScore = 10;
                }
                //立定跳远
                if (jump >= 273) {
                    jumpScore = 100;
                } else if (jump >= 268) {
                    jumpScore = 95;
                } else if (jump >= 263) {
                    jumpScore = 90;
                } else if (jump >= 256) {
                    jumpScore = 85;
                } else if (jump >= 248) {
                    jumpScore = 80;
                } else if (jump >= 244) {
                    jumpScore = 78;
                } else if (jump >= 240) {
                    jumpScore = 76;
                } else if (jump >= 236) {
                    jumpScore = 74;
                } else if (jump >= 232) {
                    jumpScore = 72;
                } else if (jump >= 228) {
                    jumpScore = 70;
                } else if (jump >= 224) {
                    jumpScore = 68;
                } else if (jump >= 220) {
                    jumpScore = 66;
                } else if (jump >= 216) {
                    jumpScore = 64;
                } else if (jump >= 212) {
                    jumpScore = 62;
                } else if (jump >= 208) {
                    jumpScore = 60;
                } else if (jump >= 203) {
                    jumpScore = 50;
                } else if (jump >= 198) {
                    jumpScore = 40;
                } else if (jump >= 193) {
                    jumpScore = 30;
                } else if (jump >= 188) {
                    jumpScore = 20;
                } else if (jump >= 183) {
                    jumpScore = 10;
                }
                //50m
                if (shortrun <= 6.7 && shortrun >= 2) {
                    shortrunScore = 100;
                } else if (shortrun <= 6.8) {
                    shortrunScore = 95;
                } else if (shortrun <= 6.9) {
                    shortrunScore = 90;
                } else if (shortrun <= 7) {
                    shortrunScore = 85;
                } else if (shortrun <= 7.1) {
                    shortrunScore = 80;
                } else if (shortrun <= 7.3) {
                    shortrunScore = 78;
                } else if (shortrun <= 7.5) {
                    shortrunScore = 76;
                } else if (shortrun <= 7.7) {
                    shortrunScore = 74;
                } else if (shortrun <= 7.9) {
                    shortrunScore = 72;
                } else if (shortrun <= 8.1) {
                    shortrunScore = 70;
                } else if (shortrun <= 8.3) {
                    shortrunScore = 68;
                } else if (shortrun <= 8.5) {
                    shortrunScore = 66;
                } else if (shortrun <= 8.7) {
                    shortrunScore = 64;
                } else if (shortrun <= 8.9) {
                    shortrunScore = 62;
                } else if (shortrun <= 9.1) {
                    shortrunScore = 60;
                } else if (shortrun <= 9.3) {
                    shortrunScore = 50;
                } else if (shortrun <= 9.5) {
                    shortrunScore = 40;
                } else if (shortrun <= 9.7) {
                    shortrunScore = 30;
                } else if (shortrun <= 9.9) {
                    shortrunScore = 20;
                } else if (shortrun <= 10.1) {
                    shortrunScore = 10;
                }
                //1000m
                if (longrun <= 3.17 && longrun >= 2) {
                    longrunScore = 100;
                    if (longrun >= 3) {
                        differenceSecond = (3.17 - longrun) * 100;
                    } else if (longrun >= 2) {
                        differenceSecond = (0.17 + 2.6 - longrun) * 100;
                    }
                    if (differenceSecond >= 4 && differenceSecond < 8) {
                        additionScore += 1;
                    } else if (differenceSecond >= 8 && differenceSecond < 12) {
                        additionScore += 2;
                    } else if (differenceSecond >= 12 && differenceSecond < 16) {
                        additionScore += 3;
                    } else if (differenceSecond >= 16 && differenceSecond < 20) {
                        additionScore += 4;
                    } else if (differenceSecond >= 20 && differenceSecond < 23) {
                        additionScore += 5;
                    } else if (differenceSecond >= 23 && differenceSecond < 26) {
                        additionScore += 6;
                    } else if (differenceSecond >= 26 && differenceSecond < 29) {
                        additionScore += 7;
                    } else if (differenceSecond >= 29 && differenceSecond < 32) {
                        additionScore += 8;
                    } else if (differenceSecond >= 32 && differenceSecond < 35) {
                        additionScore += 9;
                    } else if (differenceSecond >= 35) {
                        additionScore += 10;
                    }
                } else if (longrun <= 3.22) {
                    longrunScore = 95;
                } else if (longrun <= 3.27) {
                    longrunScore = 90;
                } else if (longrun <= 3.34) {
                    longrunScore = 85;
                } else if (longrun <= 3.42) {
                    longrunScore = 80;
                } else if (longrun <= 3.47) {
                    longrunScore = 78;
                } else if (longrun <= 3.52) {
                    longrunScore = 76;
                } else if (longrun <= 3.57) {
                    longrunScore = 74;
                } else if (longrun <= 4.02) {
                    longrunScore = 72;
                } else if (longrun <= 4.07) {
                    longrunScore = 70;
                } else if (longrun <= 4.12) {
                    longrunScore = 68;
                } else if (longrun <= 4.17) {
                    longrunScore = 66;
                } else if (longrun <= 4.22) {
                    longrunScore = 64;
                } else if (longrun <= 4.27) {
                    longrunScore = 62;
                } else if (longrun <= 4.32) {
                    longrunScore = 60;
                } else if (longrun <= 4.52) {
                    longrunScore = 50;
                } else if (longrun <= 5.12) {
                    longrunScore = 40;
                } else if (longrun <= 5.32) {
                    longrunScore = 30;
                } else if (longrun <= 5.52) {
                    longrunScore = 20;
                } else if (longrun <= 6.12) {
                    longrunScore = 10;
                }
                //引体向上
                if (up >= 19) {
                    pullupsitupScore = 100;
                    int differenceNum = up - 19;
                    if (differenceNum >= 1 && differenceNum < 2) {
                        additionScore += 1;
                    } else if (differenceNum >= 2 && differenceNum < 3) {
                        additionScore += 2;
                    } else if (differenceNum >= 3 && differenceNum < 4) {
                        additionScore += 3;
                    } else if (differenceNum >= 4 && differenceNum < 5) {
                        additionScore += 4;
                    } else if (differenceNum >= 5 && differenceNum < 6) {
                        additionScore += 5;
                    } else if (differenceNum >= 6 && differenceNum < 7) {
                        additionScore += 6;
                    } else if (differenceNum >= 7 && differenceNum < 8) {
                        additionScore += 7;
                    } else if (differenceNum >= 8 && differenceNum < 9) {
                        additionScore += 8;
                    } else if (differenceNum >= 9 && differenceNum < 10) {
                        additionScore += 9;
                    } else if (differenceNum >= 10) {
                        additionScore += 10;
                    }
                } else if (up >= 18) {
                    pullupsitupScore = 95;
                } else if (up >= 17) {
                    pullupsitupScore = 90;
                } else if (up >= 16) {
                    pullupsitupScore = 85;
                } else if (up >= 15) {
                    pullupsitupScore = 80;
                } else if (up >= 14) {
                    pullupsitupScore = 76;
                } else if (up >= 13) {
                    pullupsitupScore = 72;
                } else if (up >= 12) {
                    pullupsitupScore = 68;
                } else if (up >= 11) {
                    pullupsitupScore = 64;
                } else if (up >= 10) {
                    pullupsitupScore = 60;
                } else if (up >= 9) {
                    pullupsitupScore = 50;
                } else if (up >= 8) {
                    pullupsitupScore = 40;
                } else if (up >= 7) {
                    pullupsitupScore = 30;
                } else if (up >= 6) {
                    pullupsitupScore = 20;
                } else if (up >= 1) {
                    pullupsitupScore = 10;
                }
            } else if ("女".equals(sex)) {
                if (capacity >= 3400) {
                    capacityScore = 100;
                } else if (capacity >= 3350) {
                    capacityScore = 95;
                } else if (capacity >= 3300) {
                    capacityScore = 90;
                } else if (capacity >= 3150) {
                    capacityScore = 85;
                } else if (capacity >= 3000) {
                    capacityScore = 80;
                } else if (capacity >= 2900) {
                    capacityScore = 78;
                } else if (capacity >= 2800) {
                    capacityScore = 76;
                } else if (capacity >= 2700) {
                    capacityScore = 74;
                } else if (capacity >= 2600) {
                    capacityScore = 72;
                } else if (capacity >= 2500) {
                    capacityScore = 70;
                } else if (capacity >= 2400) {
                    capacityScore = 68;
                } else if (capacity >= 2300) {
                    capacityScore = 66;
                } else if (capacity >= 2200) {
                    capacityScore = 64;
                } else if (capacity >= 2100) {
                    capacityScore = 62;
                } else if (capacity >= 2000) {
                    capacityScore = 60;
                } else if (capacity >= 1960) {
                    capacityScore = 50;
                } else if (capacity >= 1920) {
                    capacityScore = 40;
                } else if (capacity >= 1880) {
                    capacityScore = 30;
                } else if (capacity >= 1840) {
                    capacityScore = 20;
                } else if (capacity >= 1800) {
                    capacityScore = 10;
                }
                if (reach >= 25.8) {
                    reachScore = 100;
                } else if (reach >= 24) {
                    reachScore = 95;
                } else if (reach >= 22.2) {
                    reachScore = 90;
                } else if (reach >= 20.6) {
                    reachScore = 85;
                } else if (reach >= 19) {
                    reachScore = 80;
                } else if (reach >= 17.7) {
                    reachScore = 78;
                } else if (reach >= 16.4) {
                    reachScore = 76;
                } else if (reach >= 15.1) {
                    reachScore = 74;
                } else if (reach >= 13.8) {
                    reachScore = 72;
                } else if (reach >= 12.5) {
                    reachScore = 70;
                } else if (reach >= 11.2) {
                    reachScore = 68;
                } else if (reach >= 9.9) {
                    reachScore = 66;
                } else if (reach >= 8.6) {
                    reachScore = 64;
                } else if (reach >= 7.3) {
                    reachScore = 62;
                } else if (reach >= 6) {
                    reachScore = 60;
                } else if (reach >= 5.2) {
                    reachScore = 50;
                } else if (reach >= 4.4) {
                    reachScore = 40;
                } else if (reach >= 3.6) {
                    reachScore = 30;
                } else if (reach >= 2.8) {
                    reachScore = 20;
                } else if (reach >= 2) {
                    reachScore = 10;
                }
                if (jump >= 207) {
                    jumpScore = 100;
                } else if (jump >= 201) {
                    jumpScore = 95;
                } else if (jump >= 195) {
                    jumpScore = 90;
                } else if (jump >= 188) {
                    jumpScore = 85;
                } else if (jump >= 181) {
                    jumpScore = 80;
                } else if (jump >= 178) {
                    jumpScore = 78;
                } else if (jump >= 175) {
                    jumpScore = 76;
                } else if (jump >= 172) {
                    jumpScore = 74;
                } else if (jump >= 169) {
                    jumpScore = 72;
                } else if (jump >= 166) {
                    jumpScore = 70;
                } else if (jump >= 163) {
                    jumpScore = 68;
                } else if (jump >= 160) {
                    jumpScore = 66;
                } else if (jump >= 157) {
                    jumpScore = 64;
                } else if (jump >= 154) {
                    jumpScore = 62;
                } else if (jump >= 151) {
                    jumpScore = 60;
                } else if (jump >= 146) {
                    jumpScore = 50;
                } else if (jump >= 141) {
                    jumpScore = 40;
                } else if (jump >= 136) {
                    jumpScore = 30;
                } else if (jump >= 131) {
                    jumpScore = 20;
                } else if (jump >= 126) {
                    jumpScore = 10;
                }
                if (shortrun <= 7.5 && shortrun >= 2) {
                    shortrunScore = 100;
                } else if (shortrun <= 7.6) {
                    shortrunScore = 95;
                } else if (shortrun <= 7.7) {
                    shortrunScore = 90;
                } else if (shortrun <= 8) {
                    shortrunScore = 85;
                } else if (shortrun <= 8.3) {
                    shortrunScore = 80;
                } else if (shortrun <= 8.5) {
                    shortrunScore = 78;
                } else if (shortrun <= 8.7) {
                    shortrunScore = 76;
                } else if (shortrun <= 8.9) {
                    shortrunScore = 74;
                } else if (shortrun <= 9.1) {
                    shortrunScore = 72;
                } else if (shortrun <= 9.3) {
                    shortrunScore = 70;
                } else if (shortrun <= 9.5) {
                    shortrunScore = 68;
                } else if (shortrun <= 9.7) {
                    shortrunScore = 66;
                } else if (shortrun <= 9.9) {
                    shortrunScore = 64;
                } else if (shortrun <= 10.1) {
                    shortrunScore = 62;
                } else if (shortrun <= 10.3) {
                    shortrunScore = 60;
                } else if (shortrun <= 10.5) {
                    shortrunScore = 50;
                } else if (shortrun <= 10.7) {
                    shortrunScore = 40;
                } else if (shortrun <= 10.9) {
                    shortrunScore = 30;
                } else if (shortrun <= 11.1) {
                    shortrunScore = 20;
                } else if (shortrun <= 11.3) {
                    shortrunScore = 10;
                }
                if (longrun <= 3.18 && longrun >= 2) {
                    longrunScore = 100;
                    if (longrun >= 3) {
                        differenceSecond = (3.18 - longrun) * 100;
                    } else if (longrun >= 2) {
                        differenceSecond = (0.18 + 2.6 - longrun) * 100;
                    }
                    if (differenceSecond >= 5 && differenceSecond < 10) {
                        additionScore += 1;
                    } else if (differenceSecond >= 10 && differenceSecond < 15) {
                        additionScore += 2;
                    } else if (differenceSecond >= 15 && differenceSecond < 20) {
                        additionScore += 3;
                    } else if (differenceSecond >= 20 && differenceSecond < 25) {
                        additionScore += 4;
                    } else if (differenceSecond >= 25 && differenceSecond < 30) {
                        additionScore += 5;
                    } else if (differenceSecond >= 30 && differenceSecond < 35) {
                        additionScore += 6;
                    } else if (differenceSecond >= 35 && differenceSecond < 40) {
                        additionScore += 7;
                    } else if (differenceSecond >= 40 && differenceSecond < 45) {
                        additionScore += 8;
                    } else if (differenceSecond >= 45 && differenceSecond < 50) {
                        additionScore += 9;
                    } else if (differenceSecond >= 50) {
                        additionScore += 10;
                    }
                } else if (longrun <= 3.24) {
                    longrunScore = 95;
                } else if (longrun <= 3.30) {
                    longrunScore = 90;
                } else if (longrun <= 3.37) {
                    longrunScore = 85;
                } else if (longrun <= 3.44) {
                    longrunScore = 80;
                } else if (longrun <= 3.49) {
                    longrunScore = 78;
                } else if (longrun <= 3.54) {
                    longrunScore = 76;
                } else if (longrun <= 3.59) {
                    longrunScore = 74;
                } else if (longrun <= 4.04) {
                    longrunScore = 72;
                } else if (longrun <= 4.09) {
                    longrunScore = 70;
                } else if (longrun <= 4.14) {
                    longrunScore = 68;
                } else if (longrun <= 4.19) {
                    longrunScore = 66;
                } else if (longrun <= 4.24) {
                    longrunScore = 64;
                } else if (longrun <= 4.29) {
                    longrunScore = 62;
                } else if (longrun <= 4.34) {
                    longrunScore = 60;
                } else if (longrun <= 4.44) {
                    longrunScore = 50;
                } else if (longrun <= 4.54) {
                    longrunScore = 40;
                } else if (longrun <= 5.04) {
                    longrunScore = 30;
                } else if (longrun <= 5.14) {
                    longrunScore = 20;
                } else if (longrun <= 5.24) {
                    longrunScore = 10;
                }
                if (up >= 56) {
                    pullupsitupScore = 100;
                    int differenceNum = up - 56;
                    if (differenceNum >= 2 && differenceNum < 4) {
                        additionScore += 1;
                    } else if (differenceNum >= 4 && differenceNum < 6) {
                        additionScore += 2;
                    } else if (differenceNum >= 6 && differenceNum < 7) {
                        additionScore += 3;
                    } else if (differenceNum >= 7 && differenceNum < 8) {
                        additionScore += 4;
                    } else if (differenceNum >= 8 && differenceNum < 9) {
                        additionScore += 5;
                    } else if (differenceNum >= 9 && differenceNum < 10) {
                        additionScore += 6;
                    } else if (differenceNum >= 10 && differenceNum < 11) {
                        additionScore += 7;
                    } else if (differenceNum >= 11 && differenceNum < 12) {
                        additionScore += 8;
                    } else if (differenceNum >= 12 && differenceNum < 13) {
                        additionScore += 9;
                    } else if (differenceNum >= 13) {
                        additionScore += 10;
                    }
                } else if (up >= 54) {
                    pullupsitupScore = 95;
                } else if (up >= 52) {
                    pullupsitupScore = 90;
                } else if (up >= 49) {
                    pullupsitupScore = 85;
                } else if (up >= 46) {
                    pullupsitupScore = 80;
                } else if (up >= 44) {
                    pullupsitupScore = 78;
                } else if (up >= 42) {
                    pullupsitupScore = 76;
                } else if (up >= 40) {
                    pullupsitupScore = 74;
                } else if (up >= 38) {
                    pullupsitupScore = 72;
                } else if (up >= 36) {
                    pullupsitupScore = 70;
                } else if (up >= 34) {
                    pullupsitupScore = 68;
                } else if (up >= 32) {
                    pullupsitupScore = 66;
                } else if (up >= 30) {
                    pullupsitupScore = 64;
                } else if (up >= 28) {
                    pullupsitupScore = 62;
                } else if (up >= 26) {
                    pullupsitupScore = 60;
                } else if (up >= 24) {
                    pullupsitupScore = 50;
                } else if (up >= 22) {
                    pullupsitupScore = 40;
                } else if (up >= 20) {
                    pullupsitupScore = 30;
                } else if (up >= 18) {
                    pullupsitupScore = 20;
                } else if (up >= 16) {
                    pullupsitupScore = 10;
                }
            }
        } else if("大三".equals(grade)||"大四".equals(grade)){
            if("男".equals(sex)){
                if(capacity>=5140){
                    capacityScore = 100;
                }else if(capacity>=5020){
                    capacityScore = 95;
                }else if(capacity>=4900){
                    capacityScore = 90;
                }else if(capacity>=4600){
                    capacityScore = 85;
                }else if(capacity>=4400){
                    capacityScore = 80;
                }else if(capacity>=4280){
                    capacityScore = 78;
                }else if(capacity>=4160){
                    capacityScore = 76;
                }else if(capacity>=4040){
                    capacityScore = 74;
                }else if(capacity>=3920){
                    capacityScore = 72;
                }else if(capacity>=3800){
                    capacityScore = 70;
                }else if(capacity>=3680){
                    capacityScore = 68;
                }else if(capacity>=3560){
                    capacityScore = 66;
                }else if(capacity>=3440){
                    capacityScore = 64;
                }else if(capacity>=3320){
                    capacityScore = 62;
                }else if(capacity>=3200){
                    capacityScore = 60;
                }else if(capacity>=3030){
                    capacityScore = 50;
                }else if(capacity>=2860){
                    capacityScore = 40;
                }else if(capacity>=2690){
                    capacityScore = 30;
                }else if(capacity>=2520){
                    capacityScore = 20;
                }else if(capacity>=2350){
                    capacityScore = 10;
                }
                if(reach>=25.1){
                    reachScore = 100;
                }else if(reach>=23.3){
                    reachScore = 95;
                }else if(reach>=21.5){
                    reachScore = 90;
                }else if(reach>=19.9){
                    reachScore = 85;
                }else if(reach>=18.2){
                    reachScore = 80;
                }else if(reach>=16.8){
                    reachScore = 78;
                }else if(reach>=15.4){
                    reachScore = 76;
                }else if(reach>=14){
                    reachScore = 74;
                }else if(reach>=12.6){
                    reachScore = 72;
                }else if(reach>=11.2){
                    reachScore = 70;
                }else if(reach>=9.8){
                    reachScore = 68;
                }else if(reach>=8.4){
                    reachScore = 66;
                }else if(reach>=7){
                    reachScore = 64;
                }else if(reach>=5.6){
                    reachScore = 62;
                }else if(reach>=4.2){
                    reachScore = 60;
                }else if(reach>=3.2){
                    reachScore = 50;
                }else if(reach>=2.2){
                    reachScore = 40;
                }else if(reach>=1.2){
                    reachScore = 30;
                }else if(reach>=.2){
                    reachScore = 20;
                }else if(reach>=-.8){
                    reachScore = 10;
                }
                if(jump>=275){
                    jumpScore = 100;
                }else if(jump>=270){
                    jumpScore = 95;
                }else if(jump>=265){
                    jumpScore = 90;
                }else if(jump>=258){
                    jumpScore = 85;
                }else if(jump>=250){
                    jumpScore = 80;
                }else if(jump>=246){
                    jumpScore = 78;
                }else if(jump>=242){
                    jumpScore = 76;
                }else if(jump>=238){
                    jumpScore = 74;
                }else if(jump>=234){
                    jumpScore = 72;
                }else if(jump>=230){
                    jumpScore = 70;
                }else if(jump>=226){
                    jumpScore = 68;
                }else if(jump>=222){
                    jumpScore = 66;
                }else if(jump>=218){
                    jumpScore = 64;
                }else if(jump>=214){
                    jumpScore = 62;
                }else if(jump>=210){
                    jumpScore = 60;
                }else if(jump>=205){
                    jumpScore = 50;
                }else if(jump>=200){
                    jumpScore = 40;
                }else if(jump>=195){
                    jumpScore = 30;
                }else if(jump>=190){
                    jumpScore = 20;
                }else if(jump>=185){
                    jumpScore = 10;
                }
                if(shortrun<=6.6 && shortrun>=2){
                    shortrunScore = 100;
                }else if(shortrun<=6.7){
                    shortrunScore = 95;
                }else if(shortrun<=6.8){
                    shortrunScore = 90;
                }else if(shortrun<=6.9){
                    shortrunScore = 85;
                }else if(shortrun<=7){
                    shortrunScore = 80;
                }else if(shortrun<=7.2){
                    shortrunScore = 78;
                }else if(shortrun<=7.4){
                    shortrunScore = 76;
                }else if(shortrun<=7.6){
                    shortrunScore = 74;
                }else if(shortrun<=7.8){
                    shortrunScore = 72;
                }else if(shortrun<=8){
                    shortrunScore = 70;
                }else if(shortrun<=8.2){
                    shortrunScore = 68;
                }else if(shortrun<=8.4){
                    shortrunScore = 66;
                }else if(shortrun<=8.6){
                    shortrunScore = 64;
                }else if(shortrun<=8.8){
                    shortrunScore = 62;
                }else if(shortrun<=9){
                    shortrunScore = 60;
                }else if(shortrun<=9.2){
                    shortrunScore = 50;
                }else if(shortrun<=9.4){
                    shortrunScore = 40;
                }else if(shortrun<=9.6){
                    shortrunScore = 30;
                }else if(shortrun<=9.8){
                    shortrunScore = 20;
                }else if(shortrun<=10){
                    shortrunScore = 10;
                }
                if(longrun<=3.15 && longrun>=2){
                    longrunScore = 100;
                    if(longrun>=3){
                        differenceSecond = (3.15-longrun)*100;
                    }else if(longrun>=2){
                        differenceSecond = (0.15+2.6-longrun)*100;
                    }
                    if(differenceSecond>=4&&differenceSecond<8){
                        additionScore+=1;
                    }else if(differenceSecond>=8&&differenceSecond<12){
                        additionScore+=2;
                    }else if(differenceSecond>=12&&differenceSecond<16){
                        additionScore+=3;
                    }else if(differenceSecond>=16&&differenceSecond<20){
                        additionScore+=4;
                    }else if(differenceSecond>=20&&differenceSecond<23){
                        additionScore+=5;
                    }else if(differenceSecond>=23&&differenceSecond<26){
                        additionScore+=6;
                    }else if(differenceSecond>=26&&differenceSecond<29){
                        additionScore+=7;
                    }else if(differenceSecond>=29&&differenceSecond<32){
                        additionScore+=8;
                    }else if(differenceSecond>=32&&differenceSecond<35){
                        additionScore+=9;
                    }else if(differenceSecond>=35){
                        additionScore+=10;
                    }
                }else if(longrun<=3.20){
                    longrunScore = 95;
                }else if(longrun<=3.25){
                    longrunScore = 90;
                }else if(longrun<=3.32){
                    longrunScore = 85;
                }else if(longrun<=3.40){
                    longrunScore = 80;
                }else if(longrun<=3.45){
                    longrunScore = 78;
                }else if(longrun<=3.50){
                    longrunScore = 76;
                }else if(longrun<=3.55){
                    longrunScore = 74;
                }else if(longrun<=4.00){
                    longrunScore = 72;
                }else if(longrun<=4.05){
                    longrunScore = 70;
                }else if(longrun<=4.10){
                    longrunScore = 68;
                }else if(longrun<=4.15){
                    longrunScore = 66;
                }else if(longrun<=4.20){
                    longrunScore = 64;
                }else if(longrun<=4.25){
                    longrunScore = 62;
                }else if(longrun<=4.30){
                    longrunScore = 60;
                }else if(longrun<=4.50){
                    longrunScore = 50;
                }else if(longrun<=5.10){
                    longrunScore = 40;
                }else if(longrun<=5.30){
                    longrunScore = 30;
                }else if(longrun<=5.50){
                    longrunScore = 20;
                }else if(longrun<=6.10){
                    longrunScore = 10;
                }
                if(up>=20){
                    pullupsitupScore= 100;
                    int differenceNum = up-20;
                    if(differenceNum>=1&&differenceNum<2){
                        additionScore+=1;
                    }else if(differenceNum>=2&&differenceNum<3){
                        additionScore+=2;
                    }else if(differenceNum>=3&&differenceNum<4){
                        additionScore+=3;
                    }else if(differenceNum>=4&&differenceNum<5){
                        additionScore+=4;
                    }else if(differenceNum>=5&&differenceNum<6){
                        additionScore+=5;
                    }else if(differenceNum>=6&&differenceNum<7){
                        additionScore+=6;
                    }else if(differenceNum>=7&&differenceNum<8){
                        additionScore+=7;
                    }else if(differenceNum>=8&&differenceNum<9){
                        additionScore+=8;
                    }else if(differenceNum>=9&&differenceNum<10){
                        additionScore+=9;
                    }else if(differenceNum>=10){
                        additionScore+=10;
                    }
                }else if(up>=19){
                    pullupsitupScore= 95;
                }else if(up>=18){
                    pullupsitupScore= 90;
                }else if(up>=17){
                    pullupsitupScore= 85;
                }else if(up>=16){
                    pullupsitupScore= 80;
                }else if(up>=15){
                    pullupsitupScore= 76;
                }else if(up>=14){
                    pullupsitupScore= 72;
                }else if(up>=13){
                    pullupsitupScore= 68;
                }else if(up>=12){
                    pullupsitupScore= 64;
                }else if(up>=11){
                    pullupsitupScore= 60;
                }else if(up>=10){
                    pullupsitupScore= 50;
                }else if(up>=9){
                    pullupsitupScore= 40;
                }else if(up>=8){
                    pullupsitupScore= 30;
                }else if(up>=7){
                    pullupsitupScore= 20;
                }else if(up>=1){
                    pullupsitupScore= 10;
                }
            } else if("女".equals(sex)){
                if(capacity>=3450){
                    capacityScore = 100;
                }else if(capacity>=3400){
                    capacityScore = 95;
                }else if(capacity>=3350){
                    capacityScore = 90;
                }else if(capacity>=3200){
                    capacityScore = 85;
                }else if(capacity>=3050){
                    capacityScore = 80;
                }else if(capacity>=2950){
                    capacityScore = 78;
                }else if(capacity>=2850){
                    capacityScore = 76;
                }else if(capacity>=2750){
                    capacityScore = 74;
                }else if(capacity>=2650){
                    capacityScore = 72;
                }else if(capacity>=2550){
                    capacityScore = 70;
                }else if(capacity>=2450){
                    capacityScore = 68;
                }else if(capacity>=2350){
                    capacityScore = 66;
                }else if(capacity>=2250){
                    capacityScore = 64;
                }else if(capacity>=2150){
                    capacityScore = 62;
                }else if(capacity>=2050){
                    capacityScore = 60;
                }else if(capacity>=2010){
                    capacityScore = 50;
                }else if(capacity>=1970){
                    capacityScore = 40;
                }else if(capacity>=1930){
                    capacityScore = 30;
                }else if(capacity>=1890){
                    capacityScore = 20;
                }else if(capacity>=1850){
                    capacityScore = 10;
                }
                if(reach>=26.3){
                    reachScore = 100;
                }else if(reach>=24.4){
                    reachScore = 95;
                }else if(reach>=22.4){
                    reachScore = 90;
                }else if(reach>=21){
                    reachScore = 85;
                }else if(reach>=19.5){
                    reachScore = 80;
                }else if(reach>=18.2){
                    reachScore = 78;
                }else if(reach>=16.9){
                    reachScore = 76;
                }else if(reach>=15.6){
                    reachScore = 74;
                }else if(reach>=14.3){
                    reachScore = 72;
                }else if(reach>=13){
                    reachScore = 70;
                }else if(reach>=11.7){
                    reachScore = 68;
                }else if(reach>=10.4){
                    reachScore = 66;
                }else if(reach>=9.1){
                    reachScore = 64;
                }else if(reach>=7.8){
                    reachScore = 62;
                }else if(reach>=6.5){
                    reachScore = 60;
                }else if(reach>=5.7){
                    reachScore = 50;
                }else if(reach>=4.9){
                    reachScore = 40;
                }else if(reach>=4.1){
                    reachScore = 30;
                }else if(reach>=3.3){
                    reachScore = 20;
                }else if(reach>=2.5){
                    reachScore = 10;
                }
                if(jump>=208){
                    jumpScore = 100;
                }else if(jump>=202){
                    jumpScore = 95;
                }else if(jump>=196){
                    jumpScore = 90;
                }else if(jump>=189){
                    jumpScore = 85;
                }else if(jump>=182){
                    jumpScore = 80;
                }else if(jump>=179){
                    jumpScore = 78;
                }else if(jump>=176){
                    jumpScore = 76;
                }else if(jump>=173){
                    jumpScore = 74;
                }else if(jump>=170){
                    jumpScore = 72;
                }else if(jump>=167){
                    jumpScore = 70;
                }else if(jump>=164){
                    jumpScore = 68;
                }else if(jump>=161){
                    jumpScore = 66;
                }else if(jump>=158){
                    jumpScore = 64;
                }else if(jump>=155){
                    jumpScore = 62;
                }else if(jump>=152){
                    jumpScore = 60;
                }else if(jump>=147){
                    jumpScore = 50;
                }else if(jump>=142){
                    jumpScore = 40;
                }else if(jump>=137){
                    jumpScore = 30;
                }else if(jump>=132){
                    jumpScore = 20;
                }else if(jump>=127){
                    jumpScore = 10;
                }
                if(shortrun<=7.4 && shortrun>=2){
                    shortrunScore = 100;
                }else if(shortrun<=7.5){
                    shortrunScore = 95;
                }else if(shortrun<=7.6){
                    shortrunScore = 90;
                }else if(shortrun<=7.9){
                    shortrunScore = 85;
                }else if(shortrun<=8.2){
                    shortrunScore = 80;
                }else if(shortrun<=8.4){
                    shortrunScore = 78;
                }else if(shortrun<=8.6){
                    shortrunScore = 76;
                }else if(shortrun<=8.8){
                    shortrunScore = 74;
                }else if(shortrun<=9){
                    shortrunScore = 72;
                }else if(shortrun<=9.2){
                    shortrunScore = 70;
                }else if(shortrun<=9.4){
                    shortrunScore = 68;
                }else if(shortrun<=9.6){
                    shortrunScore = 66;
                }else if(shortrun<=9.8){
                    shortrunScore = 64;
                }else if(shortrun<=10){
                    shortrunScore = 62;
                }else if(shortrun<=10.2){
                    shortrunScore = 60;
                }else if(shortrun<=10.4){
                    shortrunScore = 50;
                }else if(shortrun<=10.6){
                    shortrunScore = 40;
                }else if(shortrun<=10.8){
                    shortrunScore = 30;
                }else if(shortrun<=11){
                    shortrunScore = 20;
                }else if(shortrun<=11.2){
                    shortrunScore = 10;
                }
                if(longrun<=3.16 && longrun>=2){
                    longrunScore = 100;
                    if(longrun>=3){
                        differenceSecond = (3.16-longrun)*100;
                    }else if(longrun>=2){
                        differenceSecond = (0.16+2.6-longrun)*100;
                    }
                    if(differenceSecond>=5&&differenceSecond<10){
                        additionScore+=1;
                    }else if(differenceSecond>=10&&differenceSecond<15){
                        additionScore+=2;
                    }else if(differenceSecond>=15&&differenceSecond<20){
                        additionScore+=3;
                    }else if(differenceSecond>=20&&differenceSecond<25){
                        additionScore+=4;
                    }else if(differenceSecond>=25&&differenceSecond<30){
                        additionScore+=5;
                    }else if(differenceSecond>=30&&differenceSecond<35){
                        additionScore+=6;
                    }else if(differenceSecond>=35&&differenceSecond<40){
                        additionScore+=7;
                    }else if(differenceSecond>=40&&differenceSecond<45){
                        additionScore+=8;
                    }else if(differenceSecond>=45&&differenceSecond<50){
                        additionScore+=9;
                    }else if(differenceSecond>=50){
                        additionScore+=10;
                    }
                }else if(longrun<=3.22){
                    longrunScore = 95;
                }else if(longrun<=3.28){
                    longrunScore = 90;
                }else if(longrun<=3.35){
                    longrunScore = 85;
                }else if(longrun<=4.42){
                    longrunScore = 80;
                }else if(longrun<=3.47){
                    longrunScore = 78;
                }else if(longrun<=3.52){
                    longrunScore = 76;
                }else if(longrun<=3.57){
                    longrunScore = 74;
                }else if(longrun<=4.02){
                    longrunScore = 72;
                }else if(longrun<=4.07){
                    longrunScore = 70;
                }else if(longrun<=4.12){
                    longrunScore = 68;
                }else if(longrun<=4.17){
                    longrunScore = 66;
                }else if(longrun<=4.22){
                    longrunScore = 64;
                }else if(longrun<=4.27){
                    longrunScore = 62;
                }else if(longrun<=4.32){
                    longrunScore = 60;
                }else if(longrun<=4.42){
                    longrunScore = 50;
                }else if(longrun<=4.52){
                    longrunScore = 40;
                }else if(longrun<=5.02){
                    longrunScore = 30;
                }else if(longrun<=5.12){
                    longrunScore = 20;
                }else if(longrun<=5.22){
                    longrunScore = 10;
                }
                if(up>=57){
                    pullupsitupScore= 100;
                    int differenceNum = up-57;
                    if(differenceNum>=2&&differenceNum<4){
                        additionScore+=1;
                    }else if(differenceNum>=4&&differenceNum<6){
                        additionScore+=2;
                    }else if(differenceNum>=6&&differenceNum<7){
                        additionScore+=3;
                    }else if(differenceNum>=7&&differenceNum<8){
                        additionScore+=4;
                    }else if(differenceNum>=8&&differenceNum<9){
                        additionScore+=5;
                    }else if(differenceNum>=9&&differenceNum<10){
                        additionScore+=6;
                    }else if(differenceNum>=10&&differenceNum<11){
                        additionScore+=7;
                    }else if(differenceNum>=11&&differenceNum<12){
                        additionScore+=8;
                    }else if(differenceNum>=12&&differenceNum<13){
                        additionScore+=9;
                    }else if(differenceNum>=13){
                        additionScore+=10;
                    }
                }else if(up>=55){
                    pullupsitupScore= 95;
                }else if(up>=53){
                    pullupsitupScore= 90;
                }else if(up>=50){
                    pullupsitupScore= 85;
                }else if(up>=47){
                    pullupsitupScore= 80;
                }else if(up>=45){
                    pullupsitupScore= 78;
                }else if(up>=43){
                    pullupsitupScore= 76;
                }else if(up>=41){
                    pullupsitupScore= 74;
                }else if(up>=39){
                    pullupsitupScore= 72;
                }else if(up>=37){
                    pullupsitupScore= 70;
                }else if(up>=35){
                    pullupsitupScore= 68;
                }else if(up>=33){
                    pullupsitupScore= 66;
                }else if(up>=31){
                    pullupsitupScore= 64;
                }else if(up>=29){
                    pullupsitupScore= 62;
                }else if(up>=27){
                    pullupsitupScore= 60;
                }else if(up>=25){
                    pullupsitupScore= 50;
                }else if(up>=23){
                    pullupsitupScore= 40;
                }else if(up>=21){
                    pullupsitupScore= 30;
                }else if(up>=19){
                    pullupsitupScore= 20;
                }else if(up>=17){
                    pullupsitupScore= 10;
                }
            }
        }

        // 显示结果
        double score = (BMIScore * 0.15 + capacityScore * 0.15 + reachScore * 0.1 + jumpScore * 0.1 + shortrunScore * 0.2 + longrunScore * 0.2 + pullupsitupScore * 0.1 + additionScore);
        String result1 = String.format("%.2f", score);
        result.setText("体测分数：" + result1);
    }
}
