package com.w4.bangbang93hub;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogUtil {
    private static final String TAG = "LogUtil";
    private static final String LOG_DIR = "bangbang93HUB";
    private static final String LOG_FILE = "latest.log";

    public static void log(String tAG, String p1) {
    }

    public static void log(String message) {
        Log.d(TAG, message);
        writeLogToFile(message);
    }

    private static void writeLogToFile(String message) {
        if (isExternalStorageWritable()) {
            File logDir = new File(Environment.getExternalStorageDirectory(), LOG_DIR);
            if (!logDir.exists()) {
                logDir.mkdirs();
            }
            File logFile = new File(logDir, LOG_FILE);
            try {
                try (FileWriter writer = new FileWriter(logFile, true)) {
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
                    writer.append(timeStamp).append(" - ").append(message).append("\n");
                }
            } catch (IOException e) {} 
        }
    }

    private static boolean isExternalStorageWritable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}

