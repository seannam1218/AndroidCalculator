package com.example.sean.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private String DEFAULT_DISPLAY = "Thx for using Sean's Calculator";
    private String ERROR_MSG = "Operation failed due to invalid number. Try again!";
    private String display = DEFAULT_DISPLAY;
    private boolean resetDisplay = true;

    private String currentOperator = "";
    private double firstNum = 0.0;
    private double answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = (TextView)findViewById(R.id.screen);
        screen.setText(display);
    }

    private void updateScreen() {
        screen.setText(display);
    }

    public void onClickNumber(View v) {
        Button b = (Button) v;
        if (resetDisplay) {
            display = b.getText().toString();
            resetDisplay = false;
        } else {
            display += b.getText();
        }
        updateScreen();
    }

    public void onClickErase(View v) {
        display = display.substring(0, display.length() - 1);
        updateScreen();
    }

    public void onClickOperator(View v) {
        try {
            firstNum = Double.parseDouble(display);
            Button b = (Button) v;
            currentOperator = b.getText().toString();
            switch (currentOperator) {
                case "sqrt": display = String.valueOf(sqrt(Double.parseDouble(display)));
                    break;
                case "^2": display = String.valueOf(Double.parseDouble(display) * Double.parseDouble(display));
                    break;
            }
            resetDisplay = true;
            updateScreen();
        } catch (Exception e) {
            clear(ERROR_MSG);
        }
    }

    private void clear(String msg) {
        firstNum = 0.0;
        display = msg;
        currentOperator = "";
        resetDisplay = true;
        updateScreen();
    }

    public void onClickClear(View v) {
        clear(DEFAULT_DISPLAY);
    }

    public void onClickEqual(View v) {
        try {
            switch (currentOperator) {
                case "+":
                    answer = firstNum + Double.parseDouble(display);
                    break;
                case "-":
                    answer = firstNum - Double.parseDouble(display);
                    break;
                case "*":
                    answer = firstNum * Double.parseDouble(display);
                    break;
                case "/":
                    answer = firstNum / Double.parseDouble(display);
                    break;
            }
            display = String.valueOf(answer);
            resetDisplay = true;
            updateScreen();
            firstNum = answer;
        } catch (Exception e) {
            clear(ERROR_MSG);
        }
    }
}
