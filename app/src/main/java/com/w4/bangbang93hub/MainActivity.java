package com.w4.bangbang93hub;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.w4.bangbang93hub.R;
import java.io.IOException;
import android.app.IntentService;

public class MainActivity extends Activity {
    
   
    public void onHomeText() {
// 处理点击事件
        return ;
    }
    
    private long firstBackTime;
    
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstBackTime > 20) {
            AlertDialog dialog3 = new AlertDialog.Builder(this)
                .setTitle(R.string.exit_h)
                .setMessage(R.string.exit_m)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dia, int which) {
                        finishAffinity();
                    }
                })
                .setNegativeButton(R.string.no, null)
                .create();
            dialog3.show();
            Toast.makeText(this, R.string.exit, Toast.LENGTH_SHORT).show();
            firstBackTime = System.currentTimeMillis();
            return;
        }

        super.onBackPressed();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //public void onDialButtonClick(View view) {
        //   };
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        AlertDialog dialog = new AlertDialog.Builder(this)
            .setTitle("欢迎使用BM93 Mobile")
            .setMessage("V2.1.0-Alpha：修复了11个bug，一共有45个屎山")
            .setPositiveButton("不好", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dia, int which) {
                    //Toast.makeText(this, "嗯...\n\n\n\n\n\n\n\n\n\n你好笨啊", Toast.LENGTH_SHORT).show();
                }
            })
            .setNegativeButton("好的", null)
            .create();
        dialog.show();
        
        setContentView(R.layout.activity_main);
        
        Intent intent = new ;
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ImageViewActivity.class);
                    startActivity(intent);
                }
            });
        Button buttonOpenAbout = findViewById(R.id.button_open_about);
        buttonOpenAbout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(intent);
                }
            });
        Button buttonOpenCrashFaq = findViewById(R.id.button_open_tips_crash);
        buttonOpenCrashFaq.setOnClickListener(new View.OnClickListener() {
            
                @Override
                public void onClick(View v) {
                    
                    Intent intent = new Intent(MainActivity.this, CleanDataService.class);
                    startService((intent));
                    /*
                    AlertDialog dialog2 = new AlertDialog.Builder(this)
                        .setTitle("看图卡死")
                        .setMessage("清楚应用数据，授予权限，然后在进入app等待10+秒初始化")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dia, int which) {

                            }
                        })
                        // .setNegativeButton("取消", null)
                        .create();
                    dialog2.show();
                    */
                    Toast.makeText(getApplication(), "Wait 10s to kill Application.", Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(12000);
                        finishAffinity();
                    } catch (InterruptedException e) {}
                }
                
                
            });
    }
}

