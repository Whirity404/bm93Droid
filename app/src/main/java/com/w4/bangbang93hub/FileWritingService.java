package com.w4.bangbang93hub;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class FileWritingService extends Service {
    private static final String TAG = "FileWritingService";
    private Handler handler = new Handler();
    private Runnable fileWritingTask;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Service started");
        startWritingFiles();
        return START_STICKY;
    }

    private void startWritingFiles() {
        fileWritingTask = new Runnable() {
            @Override
            public void run() {
                try {
                    writeToFile();
                    handler.postDelayed(this, TimeUnit.SECONDS.toMillis(1));
                } catch (Exception e) {
                    Log.e(TAG, "Error writing file", e);
                }
            }
        };
        handler.post(fileWritingTask);
    }

    private void writeToFile() throws IOException {
        File cacheDir = getApplicationContext().getCacheDir();
        String timestamp = String.valueOf(System.currentTimeMillis());
        File file = new File(cacheDir, timestamp + ".bin");

        // Create a 1KB file
        byte[] data = new byte[559999]; // 0.5MB
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(data);
        }

        Log.d(TAG, "File written: " + file.getAbsolutePath());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(fileWritingTask);
        Log.d(TAG, "Service stopped");
    }
}

