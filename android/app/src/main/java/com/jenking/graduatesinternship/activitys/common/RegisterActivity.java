package com.jenking.graduatesinternship.activitys.common;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.student.StudentMainActivity;
import com.jenking.graduatesinternship.activitys.teacher.TeacherMainActivity;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.dialog.CommonTipsDialog;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.UserModel;
import com.jenking.graduatesinternship.presenter.UserPresenter;
import com.jenking.graduatesinternship.ui.CommonLoading;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    private UserPresenter userPresenter;

    @BindView(R.id.type_student)
    RadioButton type_student;
    @BindView(R.id.type_teacher)
    RadioButton type_teacher;
    @BindView(R.id.useridentity)
    TextView useridentity;
    @BindView(R.id.realname)
    TextView realname;
    @BindView(R.id.pass)
    TextView password;
    @BindView(R.id.confirm_pass)
    TextView confirm_pass;

    @BindView(R.id.loading)
    CommonLoading loading;

    @OnClick(R.id.goto_register)
    void goto_register(){
        String useridentity_str = useridentity.getText().toString();
        String realname_str = realname.getText().toString();
        String password_str = password.getText().toString();
        String confirm_pass_str = confirm_pass.getText().toString();
        if (!StringUtil.isNotEmpty(useridentity_str)
                ||!StringUtil.isNotEmpty(realname_str)
                ||!StringUtil.isNotEmpty(password_str)
                ||!StringUtil.isNotEmpty(confirm_pass_str)){
            CommonTipsDialog.showTip(this,"温馨提示","请完善信息",false);
            return;
        }else{
            if (!StringUtil.isEquals(password_str,confirm_pass_str)){
                Toast.makeText(this, "前后密码不一致", Toast.LENGTH_SHORT).show();
                return;
            }
            Map<String,String> params = RS.getBaseParams(this);
            params.put("useridentify",useridentity_str);
            params.put("realname",realname_str);
            params.put("pass",password_str);
            if (type_student.isChecked()){
                params.put("type","student");
            }else{
                params.put("type","teacher");
            }
            userPresenter.registerMobile(params);
            setLoadingEnable(true);
        }
    }

    @OnClick(R.id.return_login)
    void return_login(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    void setLoadingEnable(boolean enable){
        if (loading==null)return;
        if (enable){
            loading.setVisibility(View.VISIBLE);
        }else{
            loading.setVisibility(View.GONE);
        }
    }

    @Override
    public void initData() {
        super.initData();
        userPresenter = new UserPresenter(this);
        userPresenter.setOnCallBack(new UserPresenter.OnCallBack() {

            @Override
            public void loginMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void registerMobile(boolean isSuccess, Object object) {
                setLoadingEnable(false);
                if (isSuccess){
                    if (object!=null){
                        ResultModel resultModel = (ResultModel)object;
                        if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                            if (resultModel.getData()!=null&&resultModel.getData().size()>0){
                                List<UserModel> userModels = resultModel.getData();
                                UserModel userModel = userModels.get(0);
                                checkUserType(userModel);
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "已存在账号", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "系统繁忙", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void updateUser(boolean isSuccess, Object object) {

            }
        });
    }

    void checkUserType(UserModel userModel){
        if (StringUtil.isNotEmpty(userModel.getType())){
            switch (userModel.getType()){
                case "student":
                    Toast.makeText(this, "注册成功，已默认登录", Toast.LENGTH_SHORT).show();
                    AccountTool.saveUser(this,userModel);
                    startActivity(new Intent(this, StudentMainActivity.class));
                    finish();
                    break;
                case "teacher":
                    Toast.makeText(this, "注册成功，已默认登录", Toast.LENGTH_SHORT).show();
                    AccountTool.saveUser(this,userModel);
                    startActivity(new Intent(this, TeacherMainActivity.class));
                    finish();
                    break;
                default:
                    Toast.makeText(this, "未知类型，登录失败", Toast.LENGTH_SHORT).show();
                    break;
            }

        }else{
            Toast.makeText(this, "数据错误", Toast.LENGTH_SHORT).show();
        }
    }
}
