package com.jenking.graduatesinternship.activitys.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.BaseActivity;

import butterknife.OnClick;

public class StudentResumeEnclosureActivity extends BaseActivity {

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_resume_enclosure);
    }
}
