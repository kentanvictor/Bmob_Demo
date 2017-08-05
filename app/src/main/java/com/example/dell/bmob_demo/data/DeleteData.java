package com.example.dell.bmob_demo.data;

import android.widget.Toast;
import com.example.dell.bmob_demo.App;
import com.example.dell.bmob_demo.json.Person;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/*
 * Created by KenTan on 2017/8/5.
 */

public class DeleteData extends App {
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

    //Toast
    public void showToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
