package com.jenking.graduatesinternship.activitys.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.BaseActivity;
import com.jenking.graduatesinternship.activitys.common.UserInfoActivity;
import com.jenking.graduatesinternship.utils.AccountTool;

import butterknife.OnClick;

//个人资料页面
public class StudentDataActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.base_info)
    void base_info(){
        if (AccountTool.isLogin(this)) {
            Intent intent = new Intent(this,UserInfoActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "请登录后重试", Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.student_tutor)
    void student_tutor(){
        if (AccountTool.isLogin(this)) {
            Intent intent = new Intent(this,StudentTutorActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "请登录后重试", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.personal_skill)
    void personal_skill(){
        if (AccountTool.isLogin(this)) {
            Intent intent = new Intent(this,StudentPersonalSkillListActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "请登录后重试", Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.personal_cert)
    void personal_cert(){
        if (AccountTool.isLogin(this)) {
            Intent intent = new Intent(this,StudentPersonalCertListActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "请登录后重试", Toast.LENGTH_SHORT).show();
        }
    }
    @OnClick(R.id.education_experience)
    void education_experience(){
        if (AccountTool.isLogin(this)) {
            Intent intent = new Intent(this,StudentEducationExpListActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "请登录后重试", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data);
    }
}
