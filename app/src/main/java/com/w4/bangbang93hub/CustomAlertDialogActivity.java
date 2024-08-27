package com.w4.bangbang93hub;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class CustomAlertDialogActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Dialog alertDialog = new AlertDialog.Builder(this)
            .setTitle("提示")
            .setMessage("您确定要退出应用吗？")
            .setIcon(R.drawable.ic_launcher)
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 处理确定按钮点击事件
                    finish(); // 关闭当前 Activity
                }
            })
            .setNegativeButton("继续", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 处理继续按钮点击事件
                    // 这里可以添加退出应用的逻辑
                }
            })
            .create();
        alertDialog.show();
    }
}
