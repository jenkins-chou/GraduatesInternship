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
import com.jenking.graduatesinternship.models.impl.PersonalCertModel;
import com.jenking.graduatesinternship.presenter.EducationExperiencePresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentEducationExpOperateActivity extends BaseActivity {

    private EducationExperiencePresenter presenter;
    private EducationExpModel model;
    @BindView(R.id.education_school)
    EditText education_school;
    @BindView(R.id.education_record)
    EditText education_record;
    @BindView(R.id.education_major)
    EditText education_major;
    @BindView(R.id.education_start_time)
    EditText education_start_time;
    @BindView(R.id.education_end_time)
    EditText education_end_time;
    @BindView(R.id.experience)
    EditText experience;

    @OnClick(R.id.back)
    void back(){
        finish();
    }
    @OnClick(R.id.submit)
    void submit() {
        if (!AccountTool.isLogin(this)) {
            Toast.makeText(this, "请登陆后重试", Toast.LENGTH_SHORT).show();
            return;
        }

        String education_school_str = education_school.getText().toString();
        String education_record_str = education_record.getText().toString();
        String education_major_str = education_major.getText().toString();
        String education_start_time_str = education_start_time.getText().toString();
        String education_end_time_str = education_end_time.getText().toString();
        String experience_str = experience.getText().toString();
        if (!StringUtil.isNotEmpty(education_school_str)
                ||!StringUtil.isNotEmpty(education_record_str)
                ||!StringUtil.isNotEmpty(education_major_str)
                ||!StringUtil.isNotEmpty(education_start_time_str)
                ||!StringUtil.isNotEmpty(education_end_time_str)
                ||!StringUtil.isNotEmpty(experience_str)){
            Toast.makeText(this, "请输入完整信息", Toast.LENGTH_SHORT).show();
        }else{
            Map<String,String> params = RS.getBaseParams(this);
            params.put("user_id",AccountTool.getLoginUser(this).getId());
            params.put("education_school",education_school_str);
            params.put("education_record",education_record_str);
            params.put("education_major",education_major_str);
            params.put("education_start_time",education_start_time_str);
            params.put("education_end_time",education_end_time_str);
            params.put("experience",experience_str);

            if (presenter!=null){
                if (model==null){ //添加
                    params.put("create_time",StringUtil.getTime());
                    params.put("del","normal");
                    presenter.addEducationExperienceMobile(params);
                }else{//修改
                    params.put("id",model.getId());
                    presenter.modifyEducationExperienceMobile(params);
                }
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_education_exp_operate);
    }

    @Override
    public void initData() {
        super.initData();

        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)){
            model = new Gson().fromJson(modelJson,EducationExpModel.class);
             education_school.setText(model.getEducation_school());
             education_record.setText(model.getEducation_record());
             education_major.setText(model.getEducation_major());
             education_start_time.setText(model.getEducation_start_time());
             education_end_time.setText(model.getEducation_end_time());
             experience.setText(model.getExperience());
        }

        presenter = new EducationExperiencePresenter(this);
        presenter.setOnCallBack(new EducationExperiencePresenter.OnCallBack() {
            @Override
            public void getMineEducationExperienceMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void addEducationExperienceMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentEducationExpOperateActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(StudentEducationExpOperateActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void modifyEducationExperienceMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentEducationExpOperateActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(StudentEducationExpOperateActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void deleteEducationExperienceMobile(boolean isSuccess, Object object) {

            }
        });
    }
}
