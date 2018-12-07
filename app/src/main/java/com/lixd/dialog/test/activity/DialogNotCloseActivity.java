package com.lixd.dialog.test.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.lixd.dialog.test.R;

/**
 * Created by Administrator on 2018/12/3.
 */

public class DialogNotCloseActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private static final String TAG = DialogNotCloseActivity.class.getSimpleName();
    private AlertDialog alertDialog;
    private boolean mIsChecked = false;
    private static Handler sHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_not_close);
        findViewById(R.id.btn_open).setOnClickListener(this);
        Switch switchCheck = findViewById(R.id.switch_check);
        switchCheck.setOnCheckedChangeListener(this);
    }

    private void testOpenException() {
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Loading.............")
                .create();
        //这里会抛出dialog异常
        alertDialog.show();
        //手动模拟页面关闭
        finish();
    }

    private void testSafetyOpen() {
        alertDialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Loading.............")
                .create();
        alertDialog.show();
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //手动模拟页面关闭
                finish();
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
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        if (alertDialog != null && alertDialog.isShowing()) {
            Log.d(TAG, "销毁dialog");
            alertDialog.dismiss();
            alertDialog = null;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mIsChecked = isChecked;
    }
}
