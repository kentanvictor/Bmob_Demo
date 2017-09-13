package com.example.dell.bmob_demo;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.dell.bmob_demo.data.AddData;
import com.example.dell.bmob_demo.data.DeleteData;
import com.example.dell.bmob_demo.data.QueryData;
import com.example.dell.bmob_demo.data.UpdateData;

import java.util.ArrayList;
import java.util.List;

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
    private TabLayout mTabLayout;
    private ViewPager mViewPaper;
    private BottomNavigationView mBottomNavigationView;
    private AppBarLayout mAppBarLayout;
    AddData addData = new AddData();
    DeleteData deleteData = new DeleteData();
    QueryData queryData = new QueryData();
    UpdateData updateData = new UpdateData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        /*
        add.setOnClickListener(this);
        search.setOnClickListener(this);
        delete.setOnClickListener(this);
        change.setOnClickListener(this);*/
    }

    public void init() {
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mViewPaper = (ViewPager) findViewById(R.id.viewpaper);
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        /*add = (Button) findViewById(R.id.add_but);
        delete = (Button) findViewById(R.id.delete_but1);
        change = (Button) findViewById(R.id.change_but1);
        search = (Button) findViewById(R.id.search_but1);*/
    }


    /**
     * 如果不想使用Behavior实现BottomNavigationView进行隐藏显示的画，也可用采用下面的监听实现此效果
     */
        /*mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d("MainActivity", verticalOffset + "");
                mBottomNavigationView.setTranslationY(-verticalOffset);
            }
        });*/

    private void initViewData() {

        List<ContentFragment> fragments = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            fragments.add(ContentFragment.newInstance(i));
        }
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        ContentFragmentAdapter adapter = new ContentFragmentAdapter(fragments, getSupportFragmentManager());
        mViewPaper.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPaper);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_but:
                addData.setUser();
                break;
            case R.id.delete_but1:
                deleteData.deleteSingleData();
                break;
            case R.id.change_but1:
                updateData.updateArray();
                break;
            case R.id.search_but1:
                /*querySingleData();*/
                queryData.queryPlentyData();
                break;
            default:
                break;
        }
    }

}
