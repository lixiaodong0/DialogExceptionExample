package com.lixd.dialog.test.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.lixd.dialog.test.R;

/**
 * Created by Administrator on 2018/12/3.
 */

public class DialogOpenActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private boolean mIsChecked = false;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_open);
        findViewById(R.id.btn_open).setOnClickListener(this);
        Switch switchCheck = findViewById(R.id.switch_check);
        switchCheck.setOnCheckedChangeListener(this);
    }

    private void testOpenException() {
        //手动模拟页面关闭
        finish();
        //延迟3秒弹窗模拟异常的出现
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final AlertDialog alertDialog = new AlertDialog.Builder(DialogOpenActivity.this)
                        .setCancelable(false)
                        .setMessage("这是用来演示的Dialog")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                //这里会抛出dialog异常
                alertDialog.show();
            }
        }, 3000);
    }

    private void testSafetyOpen() {
        //手动模拟页面关闭
        finish();
        //延迟3秒弹窗模拟异常的出现
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final AlertDialog alertDialog = new AlertDialog.Builder(DialogOpenActivity.this)
                        .setCancelable(false)
                        .setMessage("这是用来演示的Dialog")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
                //弹窗显示时做安全校验，防止异常出现
                if (!isFinishing() && alertDialog != null && !alertDialog.isShowing()) {
                    alertDialog.show();
                }
            }
        }, 3000);
    }

    @Override
    public void onClick(View v) {
        if (mIsChecked) {
            testSafetyOpen();
        } else {
            testOpenException();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mIsChecked = isChecked;
    }
}
