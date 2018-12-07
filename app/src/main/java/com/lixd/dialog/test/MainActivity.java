package com.lixd.dialog.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lixd.dialog.test.activity.DialogCloseActivity;
import com.lixd.dialog.test.activity.DialogNotCloseActivity;
import com.lixd.dialog.test.activity.DialogOpenActivity;
import com.lixd.dialog.test.adapter.ItemAdapter;
import com.lixd.dialog.test.bean.ItemBean;

public class MainActivity extends AppCompatActivity implements ItemAdapter.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        RecyclerView mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setHasFixedSize(true);
        ItemAdapter itemAdapter = new ItemAdapter(this, ItemBean.createItemBeans());
        itemAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(itemAdapter);
    }


    @Override
    public void onClick(ItemBean bean, int position) {
        final Intent intent = new Intent();
        switch (bean.type) {
            case ItemBean.TYPE_OPEN_EXCEPTION:
                //测试Dialog打开异常
                intent.setClass(this, DialogOpenActivity.class);
                break;
            case ItemBean.TYPE_CLOSE_EXCEPTION:
                //测试Dialog关闭异常
                intent.setClass(this, DialogCloseActivity.class);
                break;
            case ItemBean.TYPE_NOT_CLOSE_EXCEPTION:
                //测试未关闭Dialog异常
                intent.setClass(this, DialogNotCloseActivity.class);
            default:
                break;
        }
        startActivity(intent);
    }
}
