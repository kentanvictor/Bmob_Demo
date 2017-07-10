package com.example.dell.bmob_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by DELL on 2017/7/10.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private Button back;
    private Button btn_true;
    private EditText addName;
    private EditText addAddress;
    private EditText addPhone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_back:
                break;
            case R.id.btn_true:
                break;
            case R.id.name_edit1:
                break;
            case R.id.address_edit1:
                break;
            case R.id.phone_number:
                break;
            default:
                break;
        }
    }

    public void init()
    {
        back = (Button) findViewById(R.id.btn_back);
        btn_true = (Button) findViewById(R.id.btn_true);
        addName = (EditText) findViewById(R.id.name_edit1);
        addAddress = (EditText) findViewById(R.id.address_edit1);
        addPhone = (EditText) findViewById(R.id.phone_number);
    }
}
