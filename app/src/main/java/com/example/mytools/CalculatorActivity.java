package com.example.mytools;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.fathzer.soft.javaluator.DoubleEvaluator;

import es.dmoral.toasty.Toasty;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    String rechnungUI = "";
    String rechnungPars = "";
    TextView inputBox, textViewShift, textViewDeg;
    public static byte DECIMAL = 2; //gibt an wie viele Nachkommastellen gerundet werden soll
    boolean pressed = true;
    boolean ph3pressed = true;
    Button buttonEqual, ph1, ph2, ph3, shift;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        TextView title = findViewById(R.id.title_toolbar);
        title.setText(R.string.calculator);
        ImageButton backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CalculatorActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });

        Button button0 = findViewById(R.id.button_0);
        Button button1 = findViewById(R.id.button_1);
        Button button2 = findViewById(R.id.button_2);
        Button button3 = findViewById(R.id.button_3);
        Button button4 = findViewById(R.id.button_4);
        Button button5 = findViewById(R.id.button_5);
        Button button6 = findViewById(R.id.button_6);
        Button button7 = findViewById(R.id.button_7);
        Button button8 = findViewById(R.id.button_8);
        Button button9 = findViewById(R.id.button_9);
        Button buttonAddition = findViewById(R.id.button_addition);
        Button buttonSubtraction = findViewById(R.id.button_subtraction);
        Button buttonPoint = findViewById(R.id.buttonPoint);
        Button C = findViewById(R.id.button_C);
        inputBox = findViewById(R.id.inputBox);
        Button multiplication = findViewById(R.id.button_multiplication);
        Button division = findViewById(R.id.button_division);
        Button CE = findViewById(R.id.button_CE);
        Button openBracket = findViewById(R.id.button_open_bracket);
        Button closeBracket = findViewById(R.id.button_close_bracket);
        Button sin = findViewById(R.id.button_sin);
        Button cos = findViewById(R.id.button_cos);
        Button tan = findViewById(R.id.button_tan);
        Button log = findViewById(R.id.button_log);
        Button naturalLog = findViewById(R.id.button_natural_log);
        Button squareRoot = findViewById(R.id.button_square_root);
        ph1 = findViewById(R.id.button_placeholder1);
        ph2 = findViewById(R.id.button_placeholder2);
        ph3 = findViewById(R.id.button_placeholder3);
        Button delete = findViewById(R.id.imgButton_delete);
        Button potency2 = findViewById(R.id.button_potency2);
        Button potencyY = findViewById(R.id.button_potencyY);
        Button sqrt3 = findViewById(R.id.button_sqrt);
        shift = findViewById(R.id.button_shift);
        buttonEqual = findViewById(R.id.buttonEqual);
        textViewShift = findViewById(R.id.textView_shift);
        textViewDeg = findViewById(R.id.textView_deg);

        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonAddition.setOnClickListener(this);
        buttonSubtraction.setOnClickListener(this);
        buttonPoint.setOnClickListener(this);
        C.setOnClickListener(this);
        buttonEqual.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        division.setOnClickListener(this);
        CE.setOnClickListener(this);
        openBracket.setOnClickListener(this);
        closeBracket.setOnClickListener(this);
        sin.setOnClickListener(this);
        cos.setOnClickListener(this);
        tan.setOnClickListener(this);
        log.setOnClickListener(this);
        squareRoot.setOnClickListener(this);
        ph1.setOnClickListener(this);
        ph2.setOnClickListener(this);
        ph3.setOnClickListener(this);
        delete.setOnClickListener(this);
        naturalLog.setOnClickListener(this);
        potency2.setOnClickListener(this);
        potencyY.setOnClickListener(this);
        sqrt3.setOnClickListener(this);
        shift.setOnClickListener(this);
        Log.d("UI", "onCreateCalculator");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("UI", "onStartCalculator");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("UI", "onResumeCalculator");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("UI", "onStopCalculator");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("UI", "onPauseCalculator");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("UI", "onDestroyCalculator");
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_0:
                rechnungUI = rechnungUI + "0";
                rechnungPars = rechnungPars + "0";
                updateUI();
                break;

            case R.id.button_1:
                rechnungUI = rechnungUI + "1";
                rechnungPars = rechnungPars + "1";
                updateUI();
                break;

            case R.id.button_2:
                rechnungUI = rechnungUI + "2";
                rechnungPars = rechnungPars + "2";
                updateUI();
                break;

            case R.id.button_3:
                rechnungUI = rechnungUI + "3";
                rechnungPars = rechnungPars + "3";
                updateUI();
                break;

            case R.id.button_4:
                rechnungUI = rechnungUI + "4";
                rechnungPars = rechnungPars + "4";
                updateUI();
                break;

            case R.id.button_5:
                rechnungUI = rechnungUI + "5";
                rechnungPars = rechnungPars + "5";
                updateUI();
                break;

            case R.id.button_6:
                rechnungUI = rechnungUI + "6";
                rechnungPars = rechnungPars + "6";
                updateUI();
                break;

            case R.id.button_7:
                rechnungUI = rechnungUI + "7";
                rechnungPars = rechnungPars + "7";
                updateUI();
                break;

            case R.id.button_8:
                rechnungUI = rechnungUI + "8";
                rechnungPars = rechnungPars + "8";
                updateUI();
                break;

            case R.id.button_9:
                rechnungUI = rechnungUI + "9";
                rechnungPars = rechnungPars + "9";
                updateUI();
                break;

            case R.id.button_subtraction:
                rechnungUI = rechnungUI + "-";
                rechnungPars = rechnungPars + "-";
                updateUI();
                break;

            case R.id.button_addition:
                rechnungUI = rechnungUI + "+";
                rechnungPars = rechnungPars + "+";
                updateUI();
                break;

            case R.id.buttonPoint:
                rechnungUI = rechnungUI + ".";
                rechnungPars = rechnungPars + ".";
                updateUI();
                break;

            case R.id.button_C:
                rechnungUI = "";
                rechnungPars = "";
                updateUI();
                break;

            case R.id.buttonEqual:
                if (!rechnungPars.equals("") || !rechnungUI.equals("")) {
                    rechnungUI = this.calculate(rechnungPars);
                    rechnungPars = this.calculate(rechnungPars);

                    if (buttonEqual.getText().equals("\u2248")) {
                        rechnungUI = round(rechnungPars);
                        rechnungPars = round(rechnungPars);

                    }
                }
                updateUI();
                break;

            case R.id.button_multiplication:
                rechnungUI = rechnungUI + "\u00D7";
                rechnungPars = rechnungPars + "*";
                updateUI();
                break;

            case R.id.button_division:
                rechnungUI = rechnungUI + "\u00F7";
                rechnungPars = rechnungPars + "/";
                updateUI();
                break;

            case R.id.imgButton_delete:
                if (!rechnungUI.equals("")) {
                    rechnungUI = rechnungUI.substring(0, rechnungUI.length() - 1);
                    rechnungPars = rechnungPars.substring(0, rechnungPars.length() - 1);
                    updateUI();
                }
                break;

            case R.id.button_open_bracket:
                rechnungUI = rechnungUI + "(";
                rechnungPars = rechnungPars + "(";
                updateUI();
                break;

            case R.id.button_close_bracket:
                rechnungUI = rechnungUI + ")";
                if (rechnungUI.contains("\u221A(")) {
                    rechnungPars = rechnungPars + "^(1/2)";
                } else {
                    if (rechnungUI.contains("\u221B(")) {
                        rechnungPars = rechnungPars + "^(1/3)";
                    } else {
                        rechnungPars = rechnungPars + ")";
                    }
                }
                updateUI();
                break;

            case R.id.button_placeholder1:
                Toasty.error(this, "This button is disabled", Toast.LENGTH_SHORT).show();
                updateUI();
                break;

            case R.id.button_placeholder2:
                Toasty.error(this, "This button is disabled", Toast.LENGTH_SHORT).show();
                updateUI();
                break;

            case R.id.button_placeholder3:
                if (ph3pressed) {
                    textViewDeg.setText(R.string.rad);
                    ph3.setText(R.string.rad);
                    ph3pressed = false;
                } else {
                    textViewDeg.setText(R.string.deg);
                    ph3.setText(R.string.deg);
                    ph3pressed = true;
                }
                break;

            case R.id.button_square_root:
                rechnungUI = rechnungUI + "\u221A(";
                updateUI();
                break;

            case R.id.button_log:
                rechnungUI = rechnungUI + "log(";
                rechnungPars = rechnungPars + "log(";
                updateUI();
                break;

            case R.id.button_natural_log:
                rechnungUI = rechnungUI + "ln(";
                rechnungPars = rechnungPars + "ln(";
                updateUI();
                break;

            case R.id.button_sqrt:
                rechnungUI = rechnungUI + "\u221B(";
                updateUI();
                break;

            case R.id.button_potency2:
                rechnungUI += "^2";
                rechnungPars = rechnungPars + "^2";
                updateUI();
                break;

            case R.id.button_potencyY:
                rechnungUI += "^";
                rechnungPars = rechnungPars + "^";
                updateUI();
                break;

            case R.id.button_mod:
                rechnungUI += "mod(";
                rechnungPars = rechnungPars + "mod(";
                updateUI();
                break;

            case R.id.button_sin:
                rechnungUI += "sin(";
                rechnungPars = rechnungPars + "sin(";
                updateUI();
                break;

            case R.id.button_cos:
                rechnungUI += "cos(";
                rechnungPars = rechnungPars + "cos(";
                updateUI();
                break;

            case R.id.button_tan:
                rechnungUI += "tan(";
                rechnungPars = rechnungPars + "tan(";
                updateUI();
                break;

            case R.id.button_shift:
                if (pressed) {
                    shift.setBackgroundColor(getResources().getColor(R.color.buttonshiftpressed));
                    buttonEqual.setText(R.string.round);
                    textViewShift.setText(R.string.shift);
                    pressed = false;
                } else {
                    shift.setBackgroundColor(getResources().getColor(R.color.shiftback));
                    buttonEqual.setText(R.string.equal);
                    textViewDeg.setText(R.string.deg);
                    textViewShift.setText("");
                    pressed = true;
                }
                break;
        }
    }

    private String calculate(String pRechnungPars) {
        Double result = new DoubleEvaluator().evaluate(pRechnungPars);
        rechnungPars = String.valueOf(result);
        this.removeNullAndPoint(rechnungPars);
        return rechnungPars;
    }


    public String round(String pRechnungPars) {
        Double rechnungParsDouble = Double.parseDouble(pRechnungPars);
        Double rechnungParsRounded = Math.round(rechnungParsDouble * Math.pow(10.0, DECIMAL)) / Math.pow(10.0, DECIMAL);
        rechnungPars = String.valueOf(rechnungParsRounded);
        this.removeNullAndPoint(rechnungPars);
        return rechnungPars;
    }

    private void removeNullAndPoint(String pRechnung) {
        if (pRechnung.endsWith("0")) {
            rechnungPars = pRechnung.substring(0, pRechnung.length() - 2);
        }
    }

    protected void updateUI() {
        inputBox.setText(rechnungUI);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
