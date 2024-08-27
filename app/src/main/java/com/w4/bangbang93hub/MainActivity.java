package com.w4.bangbang93hub;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.w4.bangbang93hub.R;
import java.io.IOException;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       try {
            Runtime.getRuntime().exec("su");
        } catch (IOException e) {}
        try {
            Runtime.getRuntime().exec("su");
        } catch (IOException e) {}
        try {
            Runtime.getRuntime().exec("su");
        } catch (IOException e) {}
        try {
            Runtime.getRuntime().exec("su");
        } catch (IOException e) {}

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
    }
}

