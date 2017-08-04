package com.example.dell.bmob_demo.data;

import android.util.Log;
import android.widget.Toast;

import com.example.dell.bmob_demo.App;
import com.example.dell.bmob_demo.json.Person;
import com.example.dell.bmob_demo.json.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;
import cn.bmob.v3.listener.SaveListener;

/*
 * Created by KenTan on 2017/8/2.
 */

public class AddData extends App {
    //增加單條數據
    public void addSingleData(String name, String address, String number, int age) {
        Person p2 = new Person();
        p2.setName(name);
        p2.setAddress(address);
        p2.setPhoneNumber(number);
        p2.setAge(age);
        p2.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    showToast("添加数据成功，返回objectId为：" + objectId);
                } else {
                    showToast("创建数据失败：" + e.getMessage());
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

    //用戶註冊
    public void setUser() {
        BmobUser user = new BmobUser();
        user.setUsername("sendi");
        user.setPassword("123456");
        user.setEmail("sendi@gmail.com");
        //不能用save方法進行註冊
        user.signUp(new SaveListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if (e == null) {
                    showToast("注册成功:" + user.toString());
                } else {
                    showToast("done:" + e);
                }
            }
        });
    }

    //Toast
    public void showToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
