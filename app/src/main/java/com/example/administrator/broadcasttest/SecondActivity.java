package com.example.administrator.broadcasttest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends BaseActivity {
  private Button offline_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        offline_button = findViewById(R.id.offline_button);
        offline_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("OFFLINE_BROADCAST");
                sendBroadcast(intent);//发送广播

            }
        });

    }

    public static void addAction(Context context){
        Intent intent = new Intent(context,SecondActivity.class);
        context.startActivity(intent);

    }
}
