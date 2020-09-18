package com.example.getrequestokhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
{
    Runnable refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.textView);
        final Handler handler = new Handler();
        refresh = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(refresh,500);
                //**************************************************************************************
                OkHttpClient okHttpClient = new OkHttpClient();

                String url = "Put Your Url Here";

                Request request = new Request.Builder().url(url).build();
                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e)
                    {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        if (response.isSuccessful()){
                            final String myResponse = response.body().string();
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(myResponse);
                                }
                            });
                        }

                    }
                });
            }
        };
        handler.post(refresh);




    }
}
