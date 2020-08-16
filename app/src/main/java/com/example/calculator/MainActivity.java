package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    // Variables to hold the operands and calculations
    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = findViewById(R.id.result);
        newNumber = findViewById(R.id.newNumber);
        displayOperation = findViewById(R.id.operation);

        Button button0 = findViewById(R.id.number0);
        Button button1 = findViewById(R.id.number1);
        Button button2 = findViewById(R.id.number2);
        Button button3 = findViewById(R.id.number3);
        Button button4 = findViewById(R.id.number4);
        Button button5 = findViewById(R.id.number5);
        Button button6 = findViewById(R.id.number6);
        Button button7 = findViewById(R.id.number7);
        Button button8 = findViewById(R.id.number8);
        Button button9 = findViewById(R.id.number9);
        Button decimal = findViewById(R.id.decimal);

        Button addition = findViewById(R.id.addition);
        Button subtraction = findViewById(R.id.subtraction);
        Button multiplication = findViewById(R.id.multiplication);
        Button division = findViewById(R.id.division);
        Button negative = findViewById(R.id.negative);
        Button solve = findViewById(R.id.solve);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newNumber.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        decimal.setOnClickListener(listener);

        View.OnClickListener opListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String op = b.getText().toString();
                String value = newNumber.getText().toString();
                if (value.length() != 0){
                    performOperation(value, op);
                }
                pendingOperation = op;
                displayOperation.setText(pendingOperation);
            }
        };

        addition.setOnClickListener(opListener);
        subtraction.setOnClickListener(opListener);
        multiplication.setOnClickListener(opListener);
        division.setOnClickListener(opListener);
        solve.setOnClickListener(opListener);


    }

    private void performOperation(String value, String operation){
        if (null == operand1){
            operand1 = Double.valueOf(value);
        }
        else {
            operand2 = Double.valueOf(value);
            if (pendingOperation.equals("=")) {
                pendingOperation = operation;
            }
            switch (pendingOperation){
                case "=":
                    operand1 = operand2;
                    break;
                case "+":
                    operand1 += operand2;
                    break;
                case "-":
                    operand1 -= operand2;
                    break;
                case "*":
                    operand1 *= operand2;
                    break;
                case "/":
                    if (operand2 == 0){
                        operand1 = 0.0;
                    }
                    else{
                        operand1 /= operand2;
                    }
                    break;
            }

            result.setText(operand1.toString());
            newNumber.setText("");
        }
    }
}
