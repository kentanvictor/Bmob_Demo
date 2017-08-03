package com.example.dell.bmob_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dell.bmob_demo.data.AddData;

/*
 * Created by KenTan on 2017/7/10.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private Button btn_true;
    private EditText addName;
    private EditText addAddress;
    private EditText addPhone;
    private EditText addAge;
    private String name;
    private String address;
    private String number;
    private int age;
    private static final String TAG = "bao";
    AddData user = new AddData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
        btn_true.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_true:
                name = addName.getText().toString();
                address = addAddress.getText().toString();
                number = addPhone.getText().toString();
                age = Integer.parseInt(addAge.getText().toString());
                Log.i(TAG, "onClick: " + name);
                user.addSingleData(name, address, number, age);
                break;
            default:
                break;
        }
    }

    public void init() {
        back = (Button) findViewById(R.id.btn_back);
        btn_true = (Button) findViewById(R.id.btn_true);
        addName = (EditText) findViewById(R.id.name_edit1);
        addAddress = (EditText) findViewById(R.id.address_edit1);
        addPhone = (EditText) findViewById(R.id.phone_number);
        addAge = (EditText) findViewById(R.id.age_edit1);
    }
}
