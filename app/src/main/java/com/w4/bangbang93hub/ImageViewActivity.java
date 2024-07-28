package com.w4.bangbang93hub;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import org.json.JSONArray;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ImageViewActivity extends Activity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(getApplicationContext(), "若图片显示不全，请尝试上下左右划拉划拉查看", Toast.LENGTH_SHORT).show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        webView = findViewById(R.id.webview);
        new FetchImageTask().execute();
    }

    private class FetchImageTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            LogUtil.log("Starting to fetch image URL");
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://apis.bmclapi.online/api/v1/bangbang93hub/filelist");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // 解析字符串数组
                JSONArray jsonArray = new JSONArray(response.toString());
                int randomIndex = (int) (Math.random() * jsonArray.length());
                String imageUrl = jsonArray.getString(randomIndex);

                LogUtil.log("Fetched image URL: " + imageUrl);

                // 检测是否包含中文字符并进行URL编码
                if (containsChinese(imageUrl)) {
                    imageUrl = URLEncoder.encode(imageUrl, StandardCharsets.UTF_8.toString());
                    LogUtil.log("Encoded image URL: " + imageUrl);
                }

                return "https://jsd.onmicrosoft.cn/gh/Mxmilu666/bangbang93HUB/" + imageUrl;
            } catch (Exception e) {
                LogUtil.log("Error fetching image URL: " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String imageUrl) {
            if (imageUrl != null) {
                LogUtil.log("Loading image URL in WebView: " + imageUrl);
                webView.loadUrl(imageUrl);
            } else {
                LogUtil.log("Failed to load image URL");
            }
        }

        private boolean containsChinese(String str) {
            for (char c : str.toCharArray()) {
                if (Character.UnicodeScript.of(c) == Character.UnicodeScript.HAN) {
                    return true;
                }
            }
            return false;
        }
    }
}
