package com.example.project2;

import static android.app.PendingIntent.getActivity;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //EditText editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
    private TextView result2;
    private TextView result1;
    private EditText number;
    int example = 0;
    int ForExample = example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = findViewById(R.id.number);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        result1 = findViewById(R.id.result1);
        result2 = findViewById(R.id.result2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton1();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton2();
            }
        });
        number.addTextChangedListener(new TextWatcher() {// добавляем обработчик изменения текста
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    int value = Integer.parseInt(editable.toString());
                    if (value > 19) {
                        Toast.makeText(MainActivity.this, "Ай, ай, ай, я вижу тут гангстера\nНе делай так больше!", Toast.LENGTH_SHORT).show();
                        editable.replace(0,editable.length(),"19", 0,2);
                    }
                } catch (NumberFormatException e) {

                }
            }
        });

    }

    private void clickButton1() {
        hideKeyboard(MainActivity.this);
        String numberVal = number.getText().toString();
        if (numberVal.isEmpty()) {
            numberVal = "0";
        }
        int count = Integer.parseInt(numberVal);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(i + 1);
        }
        int sum = list.stream().mapToInt(Integer::intValue).sum();
        result1.setText(String.format("Result1:%d", sum));
    }

    private void clickButton2() {
        hideKeyboard(MainActivity.this);
        String numberVal = number.getText().toString();
        if (numberVal.isEmpty()) {
            numberVal = "0";
        }
        int count = Integer.parseInt(numberVal);
        int product = 1; // Начинаем считать произведение
        for (int i = 1; i <= count; i++) {
            if (i % 2 == 0) {
                product *= i;
            }
        }
        if (product == 1) {
            product = 0;
        }
        //int sum = list.stream().mapToInt(Integer::intValue).sum();
        result2.setText(String.format("Result2:%d", product));
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}