package com.lixd.dialog.test.activity;

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

public class DialogCloseActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private boolean mIsChecked = false;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_close);
        findViewById(R.id.btn_close).setOnClickListener(this);
        Switch switchCheck = findViewById(R.id.switch_check);
        switchCheck.setOnCheckedChangeListener(this);
    }

    private void testCloseException() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Loading.......")
                .create();
        alertDialog.show();
        //手动模拟页面关闭
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
        //延迟3秒关闭弹窗
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                alertDialog.dismiss();
            }
        }, 3000);
    }

    private void testSafetyClose() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Loading.......")
                .create();
        alertDialog.show();
        //手动模拟页面关闭
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1000);
        //延迟3秒关闭弹窗
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //关闭时，判断activity是否已经关闭
                if (!isFinishing() && alertDialog != null && alertDialog.isShowing()) {
                    alertDialog.dismiss();
                }
            }
        }, 3000);
    }

    @Override
    public void onClick(View v) {
        if (mIsChecked) {
            testSafetyClose();
        } else {
            testCloseException();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mIsChecked = isChecked;
    }
}
