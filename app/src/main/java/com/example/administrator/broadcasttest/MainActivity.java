package com.example.administrator.broadcasttest;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    private Button loginButton;
    private EditText account_edit,password_edit;
    private CheckBox rememberPass;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = findViewById(R.id.login_button);
        account_edit = findViewById(R.id.account_edit);
        password_edit = findViewById(R.id.key_edit);
        rememberPass = findViewById(R.id.is_remenber);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isChe = pref.getBoolean("IsCheck",false);
        if(isChe){
            account_edit.setText(pref.getString("Account",""));
            password_edit.setText(pref.getString("Password",""));
            rememberPass.setChecked(true);
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = account_edit.getText().toString();
                String pass = password_edit.getText().toString();
                if(account.equals("admin") && pass.equals("123")){
                    editor = pref.edit();
                    if(rememberPass.isChecked()){//如果复选框被选中
                        editor.putString("Account",account);
                        editor.putString("Password",pass);
                        editor.putBoolean("IsCheck",true);
                    }else {
                        editor.clear();
                    }
                    editor.apply();
                    SecondActivity.addAction(MainActivity.this);
                    finish();
                }else {
                    Toast.makeText(MainActivity.this,"Input Error.Please input again",Toast.LENGTH_SHORT).show();
                    password_edit.setText("");
                }
            }
        });
    }
}
