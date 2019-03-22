package com.jenking.graduatesinternship.activitys.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.BaseActivity;

import butterknife.OnClick;

//个人资料页面
public class StudentDataActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.base_info)
    void base_info(){

    }
    @OnClick(R.id.personal_skill)
    void personal_skill(){

    }
    @OnClick(R.id.personal_cert)
    void personal_cert(){

    }
    @OnClick(R.id.education_experience)
    void education_experience(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_data);
    }
}
