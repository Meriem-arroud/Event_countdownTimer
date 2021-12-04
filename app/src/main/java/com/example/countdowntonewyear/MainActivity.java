package com.example.countdowntonewyear;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.iwgang.countdownview.CountdownView;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDatePicker = findViewById(R.id.bntDatePicker);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(),"date picker");
            }}
        );

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
     Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.YEAR , year);
        calendar.set(calendar.MONTH , month);
        calendar.set(calendar.DAY_OF_MONTH , dayOfMonth);

        String pickerDateString = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView tvDatePicker = findViewById(R.id.textViewContent);
        CountdownView myCvCountdownView = findViewById(R.id.countdownView);

        try {
            tvDatePicker.setText(pickerDateString);
            Date now = new Date();

            long currentDate = now.getTime();
            long pickerDate = calendar.getTimeInMillis();
            long countDownToPickerDate = pickerDate - currentDate;
            myCvCountdownView.start(countDownToPickerDate);
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
