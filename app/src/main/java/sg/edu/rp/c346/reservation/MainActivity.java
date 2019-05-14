package sg.edu.rp.c346.reservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextNum , editTextSize;
    DatePicker dp;
    TimePicker tp;
    CheckBox checkBoxSmoking;
    Button btnReservation , btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextNum = findViewById(R.id.editTextNumber);
        editTextSize = findViewById(R.id.editTextSizeGroup);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        checkBoxSmoking = findViewById(R.id.checkBoxSmoking);
        btnReservation = findViewById(R.id.btnReservation);
        btnReset = findViewById(R.id.btnReset);

        //ENHANCEMENT
        dp.setMinDate(System.currentTimeMillis() - 1000);


        checkBoxSmoking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBoxSmoking.isChecked()){
                    checkBoxSmoking.setText("Smoking Area");
                }else {
                    checkBoxSmoking.setText("Non-Smoking Area");
                }

            }
        });

        btnReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextName.getText().length() > 0 && editTextNum.getText().length() > 0 && editTextSize.getText().length() > 0){
                    String name = editTextName.getText().toString().trim();
                    String mobile = editTextNum.getText().toString().trim();
                    String pax = editTextSize.getText().toString().trim();

                    int day = dp.getDayOfMonth();
                    int mth = dp.getMonth();
                    int year = dp.getYear();

                    int hour = tp.getCurrentHour();
                    int min = tp.getCurrentMinute();

                    String date = day + "/" + mth +"/" + year;
                    String time = hour + ":" + min;

                    if(checkBoxSmoking.isChecked()){
                        String smokeArea = "yes";
                        Toast.makeText(MainActivity.this ,"Customer Info: \n Name: " + name + "\n Mobile: " + mobile + "\n Pax: " + pax + "\n Smoking Area: " + smokeArea + "\n Date: " + date + "\n Time: " + time , Toast.LENGTH_LONG).show();

                    }else{
                        String smokeArea = "no";
                        Toast.makeText(MainActivity.this ,"Customer Info: \n Name: " + name + "\n Mobile: " + mobile + "\n Pax: " + pax + "\n Smoking Area: " + smokeArea + "\n Date: " + date + "\n Time: " + time , Toast.LENGTH_LONG).show();
                    }

                }

            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextName.setText(null);
                editTextNum.setText(null);
                editTextSize.setText(null);

                checkBoxSmoking.setChecked(false);

                dp.updateDate(2019, 5, 1);

                tp.setCurrentHour(19);
                tp.setCurrentMinute(30);
            }
        });

        //ENHANCEMENT
        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker v, int hourOfDay, int minute) {
                if (hourOfDay >= 21) {
                    tp.setCurrentHour(20);
                    tp.setCurrentMinute(0);
                    Toast.makeText(MainActivity.this ,"Please pick a timing before 9pm", Toast.LENGTH_LONG).show();
                }
                else if (hourOfDay < 8) {
                    tp.setCurrentHour(8);
                    tp.setCurrentMinute(0);
                    Toast.makeText(MainActivity.this,"Please pick a timing after 8am", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}

