package com.example.postthmessagetoserverokhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendButton();
            }
        });
    }
    private void sendButton()
    {
        EditText value1 = findViewById(R.id.editText);
        String message = value1.getText().toString();

        if (value1!=null){

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody formBody = new FormBody.Builder().add("value1",message).build();

            String url = "Put Your Url Here";
            Request request = new Request.Builder().url(url).post(formBody).build();
            okHttpClient.newCall(request).enqueue(new Callback()
            {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
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
    }
}
