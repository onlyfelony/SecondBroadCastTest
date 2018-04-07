package com.example.administrator.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
   private   OffinBroadReceive offinBroadReceive;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("OFFLINE_BROADCAST");
         offinBroadReceive = new OffinBroadReceive();
        registerReceiver(offinBroadReceive,intentFilter);//注册广播


    }

    @Override
    protected void onPause() {
        super.onPause();

        if(offinBroadReceive!=null){
            unregisterReceiver(offinBroadReceive);//取消注册
        }

        offinBroadReceive = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    class OffinBroadReceive extends BroadcastReceiver{


        @Override
        public void onReceive(final Context context, final Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);//构建对话框
            builder.setMessage("You are forced offine,Please login again");
            builder.setCancelable(false);//设置对话框不可取消

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityCollector.removeAll();//销毁所有活动

                    Intent intent1 = new Intent(context,MainActivity.class);
                    startActivity(intent1);//重新启动MainActivity


                }
            });
            builder.show();

        }
    }

}
