package com.jenking.graduatesinternship.activitys.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.BaseActivity;
import com.jenking.graduatesinternship.activitys.common.MessageSendActivity;
import com.jenking.graduatesinternship.models.impl.UserModel;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class ShowStudentDataActivity extends BaseActivity {

    private UserModel userModel;
    private String receive_user_id;
    private String receive_user_type;

    @BindView(R.id.title)
    TextView title;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.base_info)
    void base_info(){
        if (userModel!=null) {
            Intent intent = new Intent(this, ShowStudentBaseInfoActivity.class);
            intent.putExtra("model", new Gson().toJson(userModel));
            startActivity(intent);
        }
    }

    @OnClick(R.id.student_internship)
    void student_internship(){
        if (userModel!=null) {
            Intent intent = new Intent(this, ShowStudentInternshipListActivity.class);
            intent.putExtra("model", new Gson().toJson(userModel));
            startActivity(intent);
        }
    }

    @OnClick(R.id.send_message)
    void send_message(){
        if (AccountTool.isLogin(this)
                && StringUtil.isNotEmpty(receive_user_id)
                &&StringUtil.isNotEmpty(receive_user_type)){
            Intent intent = new Intent(this, MessageSendActivity.class);
            intent.putExtra("receive_user_id",receive_user_id);
            intent.putExtra("receive_user_type",receive_user_type);
            startActivity(intent);
        }else{
            Toast.makeText(this, "请登录后重试", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_data);
    }

    @Override
    public void initData() {
        super.initData();
        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)) {
            userModel = new Gson().fromJson(modelJson, UserModel.class);
            if (userModel!=null){
                title.setText(userModel.getRealname()+"同学的资料");
            }
        }
        receive_user_id = getIntent().getStringExtra("receive_user_id");
        receive_user_type = getIntent().getStringExtra("receive_user_type");
    }
}
