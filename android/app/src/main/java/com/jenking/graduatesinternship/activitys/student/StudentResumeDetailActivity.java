package com.jenking.graduatesinternship.activitys.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.BaseActivity;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.dialog.CommonTipsDialog;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.ResumeModel;
import com.jenking.graduatesinternship.presenter.ResumePresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentResumeDetailActivity extends BaseActivity {

    private ResumePresenter resumePresenter;
    private ResumeModel model;
    @BindView(R.id.resume_name)
    TextView resume_name;
    @BindView(R.id.resume_expected_salary)
    TextView resume_expected_salary;
    @BindView(R.id.resume_work_life)
    TextView resume_work_life;
    @BindView(R.id.resume_education)
    TextView resume_education;
    @BindView(R.id.resume_age)
    TextView resume_age;
    @BindView(R.id.resume_website)
    TextView resume_website;
    @BindView(R.id.resume_wechat)
    TextView resume_wechat;
    @BindView(R.id.resume_phone)
    TextView resume_phone;
    @BindView(R.id.resume_email)
    TextView resume_email;
    @BindView(R.id.resume_qq)
    TextView resume_qq;
    @BindView(R.id.resume_address)
    TextView resume_address;
    @BindView(R.id.resume_intention_job)
    TextView resume_intention_job;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.modify)
    void modify(){
        if (!AccountTool.isLogin(this)){
            Toast.makeText(this, "请登录后重试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (model!=null){
            Intent intent = new Intent(this, StudentResumeOperateActivity.class);
            intent.putExtra("model",new Gson().toJson(model));
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.delete)
    void delete(){
        if (!AccountTool.isLogin(this)){
            Toast.makeText(this, "请登录后重试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (model!=null){
            CommonTipsDialog.create(this,"温馨提示","确定要删除吗?",false)
                    .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                        @Override
                        public void cancel() {

                        }

                        @Override
                        public void confirm() {
                            Map<String,String> params = RS.getBaseParams(StudentResumeDetailActivity.this);
                            params.put("id",model.getId());
                            resumePresenter.deleteResumeMobile(params);
                        }
                    }).show();

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_resume_detail);
    }
    @Override
    public void initData() {
        super.initData();

        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)){
            model = new Gson().fromJson(modelJson,ResumeModel.class);
            resume_name.setText(model.getResume_name());
            resume_expected_salary.setText(model.getResume_expected_salary());
            resume_work_life.setText(model.getResume_work_life());
            resume_education.setText(model.getResume_education());
            resume_age.setText(model.getResume_age());
            resume_website.setText(model.getResume_website());
            resume_wechat.setText(model.getResume_wechat());
            resume_phone.setText(model.getResume_phone());
            resume_email.setText(model.getResume_email());
            resume_qq.setText(model.getResume_qq());
            resume_address.setText(model.getResume_address());
            resume_intention_job.setText(model.getResume_intention_job());
        }

        resumePresenter = new ResumePresenter(this);
        resumePresenter.setOnCallBack(new ResumePresenter.OnCallBack() {
            @Override
            public void getMineResumeMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void addResumeMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void modifyResumeMobile(boolean isSuccess, Object object) {
            }

            @Override
            public void deleteResumeMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentResumeDetailActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(StudentResumeDetailActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
