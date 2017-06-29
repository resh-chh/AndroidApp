package com.xyz.myapplication;

import android.speech.tts.TextToSpeech;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNumber;
    Button btnSquare, btnSquareRoot, btnCube;
    TextView tvAnswer;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber = (EditText) findViewById(R.id.etNumber);
        btnSquare = (Button) findViewById(R.id.btnSquare);
        btnSquareRoot = (Button) findViewById(R.id.btnSquareRoot);
        btnCube = (Button) findViewById(R.id.btnCube);
        tvAnswer= (TextView) findViewById(R.id.tvAnswer);

        btnSquare.setOnClickListener(this);
        btnSquare.setOnClickListener(this);
        btnSquare.setOnClickListener(this);

        textToSpeech=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.GERMAN);

                }
            }
        });
        }



    @Override
    public void onClick(View v) {

        if (etNumber.getText().toString().length() == 0) {
            Toast.makeText(this, "Enter a Number", Toast.LENGTH_SHORT).show();
            etNumber.requestFocus();
            return;
        }

        double number;
        switch (v.getId()) {

            case R.id.btnSquare:
                number = Double.parseDouble(etNumber.getText().toString());
                double Square = number * number;
                Toast.makeText(getApplicationContext(), "Square of " + number + "is: " + Square, Toast.LENGTH_LONG).show();
                tvAnswer.setText("Square of " + number + "is: " + Square);
                break;

            case R.id.btnSquareRoot:
                number = Double.parseDouble(etNumber.getText().toString());
                double SquareRoot = Math.sqrt(number);
                Toast.makeText(getApplicationContext(), "Square Root of " + number + "is: " + SquareRoot, Toast.LENGTH_LONG).show();
                tvAnswer.setText("Square Root of " + number + "is: " + SquareRoot);
                break;

            case R.id.btnCube:
                number = Double.parseDouble(etNumber.getText().toString());
                double Cube = number * number * number;
                Toast.makeText(getApplicationContext(), "Cube of " + number + "is: " + Cube, Toast.LENGTH_LONG).show();
                tvAnswer.setText("Cube of " + number + "is: " + Cube);
                break;

        }

        String toSpeak = tvAnswer.getText().toString();
        Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
        textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
    }


    protected void onSaveInstanceState(Bundle b1)
    {
        b1.putString("ans",tvAnswer.getText().toString());
        super.onSaveInstanceState(b1);
    }

    @Override
    protected void onRestoreInstanceState(Bundle b2)
    {
        String a= b2.getString("ans");
        tvAnswer.setText(a);
        super.onRestoreInstanceState(b2);
    }
}

