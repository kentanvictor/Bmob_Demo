package com.example.dell.bmob_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dell.bmob_demo.json.Person;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button add;
    private Button delete;
    private Button change;
    private Button search;
    private Object QueryListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "fc49e0d873ae4a3d3abf55f7062ed079");
        init();
        add.setOnClickListener(this);
        search.setOnClickListener(this);
    }
    public void init()
    {
        add = (Button) findViewById(R.id.add_but);
        delete = (Button) findViewById(R.id.delete_but1);
        change = (Button) findViewById(R.id.change_but1);
        search = (Button) findViewById(R.id.search_but1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.add_but:
                Person p2 = new Person();
                p2.setName("KenTan");
                p2.setAddress("廣東廣州");
                p2.save(new SaveListener<String>() {
                    @Override
                    public void done(String objectId,BmobException e) {
                        if(e==null){
                            Toast.makeText(MainActivity.this, "添加数据成功，返回objectId为："+objectId, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.delete_but1:
                break;
            case R.id.change_but1:
                break;
            case R.id.search_but1:
                querySingleData();
                break;
            default:
                break;
        }
    }
    //Toast
    public void showToast(CharSequence text ){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
    //查詢單條數據
    public void querySingleData()
    {
        BmobQuery<Person> p1 = new BmobQuery<Person>();
        p1.getObject("5b57698a36",new QueryListener<Person>()
        {
            @Override
            public void done(Person person, BmobException e) {
                if(e == null)
                {
                    showToast("查詢成功"+person.getObjectId());
                }else
                {
                    showToast("查詢失敗:"+e.getMessage());

                }
            }
        });
    }
}
