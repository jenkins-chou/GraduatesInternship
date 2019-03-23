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
import com.jenking.graduatesinternship.models.impl.InternshipExperienceModel;
import com.jenking.graduatesinternship.presenter.InternshipExperiencePresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentInternshipOperateActivity extends BaseActivity {

    private InternshipExperienceModel internshipExperienceModel;
    private InternshipExperiencePresenter presenter;

    @BindView(R.id.internship_job)
    EditText internship_job;
    @BindView(R.id.internship_start_time)
    EditText internship_start_time;
    @BindView(R.id.internship_end_time)
    EditText internship_end_time;
    @BindView(R.id.internship_enterprise)
    EditText internship_enterprise;
    @BindView(R.id.internship_department)
    EditText internship_department;
    @BindView(R.id.internship_job_content)
    EditText internship_job_content;
    @BindView(R.id.internship_result)
    EditText internship_result;
    @BindView(R.id.internship_harvest)
    EditText internship_harvest;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.submit)
    void submit(){
        if (!AccountTool.isLogin(this)){
            Toast.makeText(this, "请登陆后重试", Toast.LENGTH_SHORT).show();
            return;
        }

        String internship_job_str = internship_job.getText().toString();
        String internship_start_time_str = internship_start_time.getText().toString();
        String internship_end_time_str = internship_end_time.getText().toString();
        String internship_enterprise_str = internship_enterprise.getText().toString();
        String internship_department_str = internship_department.getText().toString();
        String internship_job_content_str = internship_job_content.getText().toString();
        String internship_result_str = internship_result.getText().toString();
        String internship_harvest_str = internship_harvest.getText().toString();

        if (!StringUtil.isNotEmpty(internship_job_str)
                ||!StringUtil.isNotEmpty(internship_start_time_str)
                ||!StringUtil.isNotEmpty(internship_end_time_str)
                ||!StringUtil.isNotEmpty(internship_enterprise_str)
                ||!StringUtil.isNotEmpty(internship_department_str)
                ||!StringUtil.isNotEmpty(internship_job_content_str)
                ||!StringUtil.isNotEmpty(internship_result_str)
                ||!StringUtil.isNotEmpty(internship_harvest_str)){
            Toast.makeText(this, "请输入完整信息", Toast.LENGTH_SHORT).show();
        }else{
            Map<String,String> params = RS.getBaseParams(this);
            params.put("user_id",AccountTool.getLoginUser(this).getId());
            params.put("internship_job",internship_job_str);
            params.put("internship_start_time",internship_start_time_str);
            params.put("internship_end_time",internship_end_time_str);
            params.put("internship_enterprise",internship_enterprise_str);
            params.put("internship_department",internship_department_str);
            params.put("internship_job_content",internship_job_content_str);
            params.put("internship_result",internship_result_str);
            params.put("internship_harvest",internship_harvest_str);

            if (presenter!=null){
                if (internshipExperienceModel==null){ //添加
                    params.put("create_time",StringUtil.getTime());
                    params.put("del","normal");
                    presenter.addInternshipMobile(params);
                }else{//修改
                    params.put("id",internshipExperienceModel.getId());
                    presenter.modityInternshipMobile(params);
                }
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_internship_operate);
    }

    @Override
    public void initData() {
        super.initData();

        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)){
            internshipExperienceModel = new Gson().fromJson(modelJson,InternshipExperienceModel.class);
            internship_job.setText(internshipExperienceModel.getInternship_job());
            internship_start_time.setText(internshipExperienceModel.getInternship_start_time());
            internship_end_time.setText(internshipExperienceModel.getInternship_end_time());
            internship_enterprise.setText(internshipExperienceModel.getInternship_enterprise());
            internship_department.setText(internshipExperienceModel.getInternship_department());
            internship_job_content.setText(internshipExperienceModel.getInternship_job_content());
            internship_result.setText(internshipExperienceModel.getInternship_result());
            internship_harvest.setText(internshipExperienceModel.getInternship_harvest());
        }

        presenter = new InternshipExperiencePresenter(this);
        presenter.setOnCallBack(new InternshipExperiencePresenter.OnCallBack() {
            @Override
            public void addInternshipMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentInternshipOperateActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(StudentInternshipOperateActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void modifyInternshipMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentInternshipOperateActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(StudentInternshipOperateActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void deleteInternshipMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void getMineInternshipMobile(boolean isSuccess, Object object) {

            }
        });
    }
}
