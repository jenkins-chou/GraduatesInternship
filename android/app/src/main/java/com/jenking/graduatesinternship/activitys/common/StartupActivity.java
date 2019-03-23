package com.jenking.graduatesinternship.activitys.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.student.StudentMainActivity;
import com.jenking.graduatesinternship.utils.AccountTool;

public class StartupActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent();
        if (AccountTool.isLogin(this)){
            intent.setClass(this,StudentMainActivity.class);
            startActivity(intent);
            finish();
        }else{
            intent.setClass(this,LoginActivity.class);
            startActivity(intent);
        }
    }
}
