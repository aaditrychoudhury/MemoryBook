package org.tensorflow.lite.examples.MemoryBook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReminderActivity extends AppCompatActivity {

    @BindView(R.id.spinner)
    Spinner spinner;
    EditText editText;
    @BindView(R.id.timePicker) EditText tp;
    Calendar myCalendar;
    private int mHour, mMinute;
    public int a = 0;
    private String format = "";
    Button setTime;
    FirebaseDatabase rootnode;

    // creating a variable for our Database
    // Reference for Firebase.

    DatabaseReference addressRef,item1ref,item2ref,item3ref,item4ref,item5ref,dateref,timeref,amtref,hashRef;

    /*@BindView(R.id.timePicker) private TimePicker timePicker1;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        ButterKnife.bind(this);


        ArrayList<String> categories = new ArrayList<String>();
        categories.add("Essential");
        categories.add("Dail Needs");
        categories.add("Medicines");
        categories.add("Task");

        Collections.sort(categories);


        ArrayAdapter<String> countryAdapter = new ArrayAdapter<String>(ReminderActivity.this,
                android.R.layout.simple_spinner_item, categories);

        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the your spinner
        spinner.setAdapter(countryAdapter);

        myCalendar = Calendar.getInstance();

        editText= findViewById(R.id.datepicker);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, i);
                myCalendar.set(Calendar.MONTH, i1);
                myCalendar.set(Calendar.DAY_OF_MONTH, i2);
                updateLabel();
            }

        };

        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ReminderActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        mHour = myCalendar.get(Calendar.HOUR_OF_DAY);
        mMinute = myCalendar.get(Calendar.MINUTE);
        TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                tp.setText(hourOfDay + ":" + minute);
            }
        };
        tp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(ReminderActivity.this,time,mHour,mMinute,false).show();
            }
        });

    }
    public void back(View view){
        startActivity(new Intent(this,HomeActivity.class));
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);
        finish();
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(myCalendar.getTime()));
    }


    public void remindsubmit(View view){
        startActivity(new Intent(this,HomeActivity.class));
    }
}