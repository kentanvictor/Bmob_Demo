package com.example.dell.bmob_demo.json;

import cn.bmob.v3.BmobObject;

/*
 * Created by KenTan on 2017/7/10.
 */

public class Person extends BmobObject {
    private String name;
    private String address;
    private int phoneNumber;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
