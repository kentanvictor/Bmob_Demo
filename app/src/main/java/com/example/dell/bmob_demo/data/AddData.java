package com.example.dell.bmob_demo.data;

import android.util.Log;
import android.widget.Toast;

import com.example.dell.bmob_demo.App;
import com.example.dell.bmob_demo.json.Person;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.SaveListener;

/*
 * Created by KenTan on 2017/8/2.
 */

public class AddData extends App {
    //增加單個數據
    public void addSingleData() {
        Person p1 = new Person();
        p1.setName("KenTan");
        p1.setAge(23);
        p1.setPhoneNumber("1231253242");
        p1.setAddress("China");
        p1.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    showToast("創建數據成功:" + objectId);
                } else {
                    showToast("bmob 失敗:" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
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
                            showToast("第" + (i + 1) + "個數據批量添加成功:" + result.getCreatedAt() + "," + result.getObjectId() + "," + result.getUpdatedAt());
                        } else {
                            showToast("第" + (i + 1) + "个数据批量添加失败：" + ex.getMessage() + "," + ex.getErrorCode());
                        }
                    }
                } else {
                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                }
            }
        });
    }

    //添加數組數據
    public void addArray() {
        Person p = new Person();
        p.setObjectId("d32143db92");
        //添加String类型的数组
        p.add("hobbys", "唱歌"); // 添加单个String
        //p.addAll("hobbys", Arrays.asList("游泳", "看书"));    // 添加多个String
        //添加Object类型的数组
        p.add("cards", new Person.BankCard("工行卡", "工行卡账号")); //添加单个Object
        List<Person.BankCard> cards = new ArrayList<Person.BankCard>();
        for (int i = 0; i < 2; i++) {
            cards.add(new Person.BankCard("建行卡" + i, "建行卡账号" + i));
        }
        //p.addAll("cards", cards);                            //添加多个Object值
        p.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    showToast("bmob 保存成功");
                } else {
                    showToast("bmob 保存失败：" + e.getMessage());
                }
            }

        });
    }

    //Toast
    public void showToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
