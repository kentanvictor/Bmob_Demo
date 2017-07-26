package com.example.dell.bmob_demo.json;

import java.util.List;

import cn.bmob.v3.BmobObject;

/*
 * Created by KenTan on 2017/7/10.
 */

public class Person extends BmobObject {
    private String name;
    private String address;
    private String phoneNumber;
    private int age;
    private List<String> hobbys;
    private List<BankCard> cards;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public static class BankCard {
        private String cardNumber;
        private String bankName;
        public String getCardNumber() {
            return cardNumber;
        }
        public void setBankName(String bankName) {
            this.bankName = bankName;
        }
        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }
        public String getBankName() {
            return bankName;
        }
        public BankCard(String bankName, String cardNumber) {
            this.bankName = bankName;
            this.cardNumber = cardNumber;
        }
    }
}
