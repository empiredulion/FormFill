package com.example.formtofill;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

import android.os.Bundle;

public class FormFillActivity extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();
    TextView birthDay, isOK;
    EditText FirstN, LastN, Email, Adress;
    CheckBox AgreeBox;

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        birthDay.setText(sdf.format(myCalendar.getTime()));
    }
    
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_fill);

        birthDay = findViewById(R.id.BirthDay);
        FirstN = findViewById(R.id.FirstName);
        LastN = findViewById(R.id.LastName);
        Email = findViewById(R.id.Email);
        Adress = findViewById(R.id.Address);
        isOK = findViewById(R.id.isOK);
        AgreeBox = findViewById(R.id.AgreeBox);

        findViewById(R.id.DatePick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FormFillActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        findViewById(R.id.Register).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                isOK.setText("Please fill in ");

                if (Objects.equals(FirstN.getText().toString(), "")) {
                    isOK.append("First Name, ");
                }

                if (Objects.equals(LastN.getText().toString(), "")) {
                    isOK.append("Last Name, ");
                }

                if (Objects.equals(birthDay.getText(), "")) {
                    isOK.append("Birth Day, ");
                }

                if (Objects.equals(Adress.getText().toString(), "")) {
                    isOK.append("Adress, ");
                }

                if (Objects.equals(Email.getText().toString(), "")) {
                    isOK.append("Email, ");
                }

                isOK.setText(isOK.getText().toString().substring(0,isOK.getText().toString().length()-2));
                isOK.append(".");
                Log.d("TAG", "Length = " + Float.toString(isOK.getText().toString().length()));
                Log.d("TAG", isOK.getText().toString());
                if (isOK.getText().toString().length() == 14) isOK.setText("");

                if (!AgreeBox.isChecked()) {
                    if (isOK.getText().toString().length() > 0) isOK.append("\n");
                    isOK.append("Please agree with Terms of Use. ");
                }
            }
        });
    }
}