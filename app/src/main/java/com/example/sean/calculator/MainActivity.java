package com.example.sean.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Double.NaN;
import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private String DEFAULT_DISPLAY = "Thx for using Sean's Calculator";
    private String ERROR_MSG = "Invalid number. Try again!";
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

    //if resetDisplay == true, then renew the display and add the number clicked.
    //if not, then add the number clicked at the end of current String on display.
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

    //Constrain the decimal button so that there is no two decimals on the same display.
    //if resetDisplay == true, then renew the display and add one decimal.
    //if not, then add decimal at the end of current String on display.
    public void onClickDec(View v) {
        Button b = (Button) v;
        if (!display.contains(".")) {
            if (resetDisplay) {
                display = b.getText().toString();
                resetDisplay = false;
            } else {
                display += b.getText();
            }
            updateScreen();
        }
    }

    //Toggle the negative sign in front of current String on display.
    public void onClickNeg(View v) {
        try {
            answer = Double.parseDouble(display) * (-1);
            display = String.valueOf(answer);
            updateScreen();
        } catch (Exception e) {
            clear(ERROR_MSG);
        }
    }

    //Remove the last number or character of the current String on display.
    public void onClickBackSpace(View v) {
        display = display.substring(0, display.length() - 1);
        updateScreen();
    }

    //Changes the currentOperator to the button clicked.
    //Assigns the current String (i.e. number) on display as the variable firstNum.
    public void onClickOperator(View v) {
        try {
            Button b = (Button) v;
            firstNum = Double.parseDouble(display);
            currentOperator = b.getText().toString();
            resetDisplay = true;
            updateScreen();
        } catch (Exception e) {
            clear(ERROR_MSG);
        }
    }

    //Takes the current String on display, square roots it, and displays that answer.
    //That answer is automatically assigned as firstNum for future operations.
    public void onClickSqrt(View v) {
        try {
            firstNum = Double.parseDouble(display);
            //if firstNum is not a NaN and is bigger or equal to 0, square root the number and display it.
            if (firstNum >= 0) {
                answer = sqrt(firstNum);
                firstNum = answer;
                display = String.valueOf(answer);
                resetDisplay = true;
                updateScreen();
            }
        } catch (Exception e) {
            clear(ERROR_MSG);
        }

    }

    //Takes the current String on display, squares it, and displays that answer.
    //That answer is automatically assigned as firstNum for future operations.
    public void onClickSq(View v) {
        try {
            firstNum = Double.parseDouble(display);
            answer = firstNum * firstNum;
            firstNum = answer;
            display = String.valueOf(answer);
            resetDisplay = true;
            updateScreen();
        } catch (Exception e) {
            clear(ERROR_MSG);
        }
    }

    //Clears the display of numbers, displays a String msg, and resets all the variables to default.
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

    //Takes the firstNum, currentOperator, and whatever number is on display and computes them.
    //Sets the currentOperator to the default value of "".
    public void onClickEqual(View v) {
        try {
            switch (currentOperator) {
                case "":
                    return;
                case "+":
                    answer = firstNum + Double.parseDouble(display);
                    firstNum = answer;
                    break;
                case "-":
                    answer = firstNum - Double.parseDouble(display);
                    firstNum = answer;
                    break;
                case "*":
                    answer = firstNum * Double.parseDouble(display);
                    firstNum = answer;
                    break;
                case "/":
                    answer = firstNum / Double.parseDouble(display);
                    firstNum = answer;
                    break;
            }
            display = String.valueOf(answer);
            resetDisplay = true;
            updateScreen();
        } catch (Exception e) {
            clear(ERROR_MSG);
        }
        currentOperator = "";
    }
}
