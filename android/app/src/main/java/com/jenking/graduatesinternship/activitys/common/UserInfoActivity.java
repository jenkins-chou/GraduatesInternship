package com.jenking.graduatesinternship.activitys.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.models.impl.UserModel;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class UserInfoActivity extends BaseActivity {

    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.pass)
    TextView pass;
    @BindView(R.id.realname)
    TextView realname;
    @BindView(R.id.slogan)
    TextView slogan;
    @BindView(R.id.sex)
    TextView sex;
    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.idnum)
    TextView idnum;
    @BindView(R.id.nation)
    TextView nation;
    @BindView(R.id.registered_residence)
    TextView registered_residence;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.useridentify)
    TextView useridentify;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.health)
    TextView health;
    @BindView(R.id.entrance_time)
    TextView entrance_time;
    @BindView(R.id.college_name)
    TextView college_name;
    @BindView(R.id.class_bar)
    LinearLayout class_bar;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.modify)
    void modify(){
        if (AccountTool.isLogin(this)&&AccountTool.getLoginUser(this)!=null){
            UserModel userModel = AccountTool.getLoginUser(this);
            Intent intent = new Intent(this, UserInfoModifyActivity.class);
            intent.putExtra("model",new Gson().toJson(userModel));
            startActivity(intent);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (AccountTool.isLogin(this)){
            UserModel userModel = AccountTool.getLoginUser(this);
            if (userModel!=null){
                name.setText(userModel.getName());
                pass.setText(userModel.getPass());
                realname.setText(userModel.getRealname());
                slogan.setText(userModel.getSlogan());
                sex.setText(userModel.getSex());
                age.setText(userModel.getAge());
                idnum.setText(userModel.getIdnum());
                nation.setText(userModel.getNation());
                registered_residence.setText(userModel.getRegistered_residence());
                email.setText(userModel.getEmail());
                useridentify.setText(userModel.getUseridentify());
                phone.setText(userModel.getPhone());
                address.setText(userModel.getAddress());
                health.setText(userModel.getHealth());
                entrance_time.setText(userModel.getEntrance_time());
                college_name.setText(userModel.getSchool_name()+"-"+userModel.getCollege_name());

                college_name.setText(userModel.getSchool_name()+"--"+userModel.getCollege_name()+"--"+userModel.getClass_name());
            }
        }
    }

    @Override
    public void initView() {
        super.initView();
        if (AccountTool.isLogin(this)){
            if (!StringUtil.isEquals(AccountTool.getUserType(this),AccountTool.usertype_student)){
                class_bar.setVisibility(View.GONE);
            }
        }
    }
}
