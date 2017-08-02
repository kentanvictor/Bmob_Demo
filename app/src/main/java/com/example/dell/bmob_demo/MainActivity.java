package com.example.dell.bmob_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dell.bmob_demo.data.Data;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button add;
    private Button delete;
    private Button change;
    private Button search;
    private String name;
    private Object objectId;
    private String createdAt;
    private String address;
    private int age;
    private String phoneNumber;
    Data user = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        add.setOnClickListener(this);
        search.setOnClickListener(this);
        delete.setOnClickListener(this);
        change.setOnClickListener(this);
    }

    public void init() {
        add = (Button) findViewById(R.id.add_but);
        delete = (Button) findViewById(R.id.delete_but1);
        change = (Button) findViewById(R.id.change_but1);
        search = (Button) findViewById(R.id.search_but1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_but:
                /*addArray();*/
//                Intent intent = new Intent(MainActivity.this, AddActivity.class);
//                startActivity(intent);
//                addPlentyData();
                user.addPlentyData();
                break;
            case R.id.delete_but1:
                user.deleteSingleData();
                break;
            case R.id.change_but1:
                user.updateArray();
                break;
            case R.id.search_but1:
                /*querySingleData();*/
                user.queryPlentyData();
                break;
            default:
                break;
        }
    }

}
