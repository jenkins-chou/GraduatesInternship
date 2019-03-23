package com.jenking.graduatesinternship.activitys.student;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.BaseActivity;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.PersonalCertModel;
import com.jenking.graduatesinternship.presenter.PersonalCertPresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentPersonalCertOperateActivity extends BaseActivity {

    private PersonalCertPresenter presenter;
    private PersonalCertModel model;
    @BindView(R.id.cert_name)
    EditText cert_name;
    @BindView(R.id.cert_time)
    EditText cert_time;

    @OnClick(R.id.submit)
    void submit(){
        if (AccountTool.isLogin(this)){
            String cert_name_str = cert_name.getText().toString();
            String cert_time_str = cert_time.getText().toString();
            if (StringUtil.isNotEmpty(cert_name_str)
                    &&StringUtil.isNotEmpty(cert_time_str)){

                Map<String,String> params = RS.getBaseParams(this);
                params.put("user_id",AccountTool.getLoginUser(this).getId());
                params.put("cert_name",cert_name_str);
                params.put("cert_time",cert_time_str);
                if (model!=null){
                    params.put("id",model.getId());
                    presenter.modifyPersonalCertMobile(params);
                }else{
                    presenter.addPersonalCertMobile(params);
                }
            }else{
                Toast.makeText(this, "请输入", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.back)
    void back(){
        finish();
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_personal_cert_operate);
    }

    @Override
    public void initData() {
        super.initData();

        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)){
            model = new Gson().fromJson(modelJson,PersonalCertModel.class);
            cert_name.setText(model.getCert_name());
            cert_time.setText(model.getCert_time());
        }

        presenter = new PersonalCertPresenter(this);
        presenter.setOnCallBack(new PersonalCertPresenter.OnCallBack() {
            @Override
            public void getMinePersonalCertMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void addPersonalCertMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentPersonalCertOperateActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(StudentPersonalCertOperateActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void modifyPersonalCertMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentPersonalCertOperateActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(StudentPersonalCertOperateActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void deletePersonalCertMobile(boolean isSuccess, Object object) {

            }
        });
    }
    
}
