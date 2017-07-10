package com.example.dell.bmob_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.bmob_demo.json.Person;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/*
 * Created by KenTan on 2017/7/10.
 */

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private Button back;
    private Button btn_true;
    private EditText addName;
    private EditText addAddress;
    private EditText addPhone;
    private String name = "abc";
    private String address = "China";
    private int number = 123;
    private static final String TAG = "bao";

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
                Intent intent = new Intent(AddActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_true:
                name = addName.getText().toString();
                address = addAddress.getText().toString();
                number = Integer.parseInt(addPhone.getText().toString());
                Log.i(TAG, "onClick: " + name);
                addSingleData(name, address, number);
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
    }

    //增加單條數據
    public void addSingleData(String name, String address, int number) {
        Person p2 = new Person();
        p2.setName(name);
        p2.setAddress(address);
        p2.setPhoneNumber(number);
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    Toast.makeText(AddActivity.this, "添加数据成功，返回objectId为：" + objectId, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddActivity.this, "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
