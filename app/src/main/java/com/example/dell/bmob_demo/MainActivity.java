package com.example.dell.bmob_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dell.bmob_demo.json.Person;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button add;
    private Button delete;
    private Button change;
    private Button search;
    private String name;
    private Object objectId;
    private String createdAt;

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
                /*Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);*/
                addPlentyData();
                break;
            case R.id.delete_but1:
                deleteSingleData();
                break;
            case R.id.change_but1:
                changeSingleData();
                break;
            case R.id.search_but1:
                /*querySingleData();*/
                queryPlentyData();
                break;
            default:
                break;
        }
    }

    //批量添加數據
    public void addPlentyData() {
        List<BmobObject> persons = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Person person = new Person();
            person.setName("張三");
            person.setAddress("北京海淀");
            persons.add(person);
        }
        //v.3.5.0之後開始提供的批量添加數據方法
        new BmobBatch().insertBatch(persons).doBatch(new QueryListListener<BatchResult>() {
            @Override
            public void done(List<BatchResult> list, BmobException e) {
                if (e == null) {
                    for (int i = 0; i < list.size(); i++) {
                        BatchResult result = list.get(i);
                        BmobException ex = result.getError();
                        if (ex == null) {
                            showToast("第" + (i+1) + "個數據批量添加成功:" + result.getCreatedAt() + "," + result.getObjectId() + "," + result.getUpdatedAt());
                        } else {
                            showToast("第" + (i+1) + "个数据批量添加失败：" + ex.getMessage() + "," + ex.getErrorCode());
                        }
                    }
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    //刪除單條數據
    public void deleteSingleData() {
        final Person p3 = new Person();
        p3.setObjectId("e3a9364d52");
        p3.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    showToast("刪除數據成功:" + p3.getUpdatedAt());
                } else {
                    showToast("刪除數據失敗:" + e.getMessage());
                }
            }
        });
    }

    //修改單條數據
    public void changeSingleData() {
        final Person p1 = new Person();
        p1.setAddress("上海虹橋");
        p1.update("e3a9364d52", new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    showToast("更新成功" + p1.getUpdatedAt());
                } else {
                    showToast("更新失敗" + e.getMessage());
                }
            }
        });
    }

    //批量更新數據
    public void updatePlentyData() {
        List<BmobObject> persons = new ArrayList<>();
        Person p1 = new Person();
    }

    //查詢單條數據
    public void querySingleData() {
        BmobQuery<Person> p1 = new BmobQuery<>();
        p1.getObject("e3a9364d52", new QueryListener<Person>() {
            @Override
            public void done(Person person, BmobException e) {
                if (e == null) {
                    showToast("查詢成功!" + person.getAddress());
                } else {
                    showToast("查詢失敗:" + e.getMessage());

                }
            }
        });
    }

    //查詢多條數據
    public void queryPlentyData() {
        BmobQuery<Person> query = new BmobQuery<>();
        //查詢name中叫張三的數據
        query.addWhereEqualTo("name", "張三");
        //返回50條數據,如果不加上這句,默認返回10條
        query.setLimit(50);
        //執行查詢方法
        query.findObjects(new FindListener<Person>() {
            @Override
            public void done(List<Person> list, BmobException e) {
                if (e == null) {
                    showToast("查询成功：共" + list.size() + "条数据。");
                    for (Person p2 : list) {
                        //獲取Name的信息
                        name = p2.getName();
                        //獲取數據的objectId的信息
                        objectId = p2.getObjectId();
                        //獲取createdAt數據創建時間(注意:是createdAt,不是createAt)
                        createdAt = p2.getCreatedAt();
                        showToast("數據為:"+name + objectId + createdAt);
                    }
                }else
                {
                    showToast("張三查詢失敗:"+e.getMessage()+","+e.getErrorCode());
                }

            }
        });
    }

    //Toast
    public void showToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

}
