package com.jenking.graduatesinternship.activitys.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.jenking.graduatesinternship.R;

import butterknife.BindView;
import butterknife.OnClick;

public class RecruitSearchActivity extends BaseActivity {

    @BindView(R.id.search_input)
    EditText search_input;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.search_button)
    void search_button(){
        String value = search_input.getText().toString();
        Toast.makeText(this, ""+value, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit_search);
    }
}
