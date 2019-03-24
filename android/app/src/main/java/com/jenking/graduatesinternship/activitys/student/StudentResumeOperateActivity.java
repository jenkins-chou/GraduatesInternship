package com.jenking.graduatesinternship.activitys.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.BaseActivity;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.EducationExpModel;
import com.jenking.graduatesinternship.models.impl.ResumeModel;
import com.jenking.graduatesinternship.presenter.ResumePresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentResumeOperateActivity extends BaseActivity {

    private ResumePresenter resumePresenter;
    private ResumeModel model;
    @BindView(R.id.resume_name)
    EditText resume_name;
    @BindView(R.id.resume_expected_salary)
    EditText resume_expected_salary;
    @BindView(R.id.resume_work_life)
    EditText resume_work_life;
    @BindView(R.id.resume_education)
    EditText resume_education;
    @BindView(R.id.resume_age)
    EditText resume_age;
    @BindView(R.id.resume_website)
    EditText resume_website;
    @BindView(R.id.resume_wechat)
    EditText resume_wechat;
    @BindView(R.id.resume_phone)
    EditText resume_phone;
    @BindView(R.id.resume_email)
    EditText resume_email;
    @BindView(R.id.resume_qq)
    EditText resume_qq;
    @BindView(R.id.resume_address)
    EditText resume_address;
    @BindView(R.id.resume_intention_job)
    EditText resume_intention_job;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.submit)
    void submit(){
        if (!AccountTool.isLogin(this)) {
            Toast.makeText(this, "请登陆后重试", Toast.LENGTH_SHORT).show();
            return;
        }
        String resume_name_str = resume_name.getText().toString();//必填
        String resume_expected_salary_str = resume_expected_salary.getText().toString();//必填
        String resume_work_life_str = resume_work_life.getText().toString();//必填
        String resume_education_str = resume_education.getText().toString();//必填
        String resume_intention_job_str = resume_intention_job.getText().toString();//必填
        String resume_age_str = resume_age.getText().toString();
        String resume_website_str = resume_website.getText().toString();
        String resume_wechat_str = resume_wechat.getText().toString();
        String resume_phone_str = resume_phone.getText().toString();
        String resume_email_str = resume_email.getText().toString();
        String resume_qq_str = resume_qq.getText().toString();
        String resume_address_str = resume_address.getText().toString();


        if (!StringUtil.isNotEmpty(resume_name_str)
                ||!StringUtil.isNotEmpty(resume_expected_salary_str)
                ||!StringUtil.isNotEmpty(resume_work_life_str)
                ||!StringUtil.isNotEmpty(resume_education_str)
                ||!StringUtil.isNotEmpty(resume_intention_job_str)){
            Toast.makeText(this, "请输入必填信息", Toast.LENGTH_SHORT).show();
        }else{
            Map<String,String> params = RS.getBaseParams(this);
            params.put("user_id",AccountTool.getLoginUser(this).getId());
            params.put("resume_name",resume_name_str);
            params.put("resume_expected_salary",resume_expected_salary_str);
            params.put("resume_work_life",resume_work_life_str);
            params.put("resume_education",resume_education_str);
            params.put("resume_intention_job",resume_intention_job_str);
            params.put("resume_age",resume_age_str);
            params.put("resume_website",resume_website_str);
            params.put("resume_wechat",resume_wechat_str);
            params.put("resume_phone",resume_phone_str);
            params.put("resume_email",resume_email_str);
            params.put("resume_qq",resume_qq_str);
            params.put("resume_address",resume_address_str);
            params.put("resume_status","normal");
            if (resumePresenter!=null){
                if (model==null){ //添加
                    params.put("create_time",StringUtil.getTime());
                    params.put("del","normal");
                    resumePresenter.addResumeMobile(params);
                }else{//修改
                    params.put("id",model.getId());
                    resumePresenter.modifyResumeMobile(params);
                }
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_resume_operate);
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
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentResumeOperateActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(StudentResumeOperateActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void modifyResumeMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentResumeOperateActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(StudentResumeOperateActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void deleteResumeMobile(boolean isSuccess, Object object) {

            }
        });
    }
}
