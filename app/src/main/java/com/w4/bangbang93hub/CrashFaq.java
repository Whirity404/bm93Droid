package com.w4.bangbang93hub;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class CrashFaq extends Activity {
    
    
    private long firstBackTime;
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstBackTime > 2000) {
            AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("????")
                .setMessage("????")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dia, int which) {
                        finish();
                    }
                })
              //  .setNegativeButton("取消", null)
                .create();
            dialog.show();
          //  Toast.makeText(this, "再按一次返回键退出程序", Toast.LENGTH_SHORT).show();
            firstBackTime = System.currentTimeMillis();
            return;
        }

        super.onBackPressed();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AlertDialog dialog2 = new AlertDialog.Builder(this)
            .setTitle("看图卡死")
            .setMessage("清楚应用数据，授予权限，然后在进入app等待10+秒初始化")
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dia, int which) {
                    finish();
                }
            })
            // .setNegativeButton("取消", null)
            .create();
        dialog2.show();
        
        super.onCreate(savedInstanceState);
        
    }
    
}
