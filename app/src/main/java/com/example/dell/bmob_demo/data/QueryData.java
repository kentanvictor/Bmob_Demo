package com.example.dell.bmob_demo.data;

import android.widget.Toast;

import com.example.dell.bmob_demo.App;
import com.example.dell.bmob_demo.json.Person;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

/*
 * Created by KenTan on 2017/8/5.
 */

public class QueryData extends App {
    private String name;
    private Object objectId;
    private String createdAt;
    private String address;
    private int age;
    private String phoneNumber;

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
        //查詢name中叫KenTan的數據
        query.addWhereEqualTo("name", "KenTan");
        query.addWhereLessThan("age", 50);//條件:年齡小於50歲
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
                        //獲取address
                        address = p2.getAddress();
                        //獲取age
                        age = p2.getAge();
                        //獲取number
                        phoneNumber = p2.getPhoneNumber();
                        showToast("數據為:\n" + name + "\n" + "Phone NUmber:" + phoneNumber + "\n" + "Address:" + address + "\n" + "Age:" + age + "\n" + createdAt);
                    }
                } else {
                    showToast("張三查詢失敗:" + e.getMessage() + "," + e.getErrorCode());
                }

            }
        });
    }

    //Toast
    public void showToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
