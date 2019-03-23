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
import com.jenking.graduatesinternship.models.impl.EducationExpModel;
import com.jenking.graduatesinternship.presenter.EducationExperiencePresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentEducationExpDetailActivity extends BaseActivity {

    private EducationExperiencePresenter presenter;
    private EducationExpModel model;
    @BindView(R.id.education_school)
    TextView education_school;
    @BindView(R.id.education_record)
    TextView education_record;
    @BindView(R.id.education_major)
    TextView education_major;
    @BindView(R.id.education_start_time)
    TextView education_start_time;
    @BindView(R.id.education_end_time)
    TextView education_end_time;
    @BindView(R.id.experience)
    TextView experience;

    @OnClick(R.id.back)
    void back() {
        finish();
    }

    @OnClick(R.id.delete)
    void delete() {
        if (!AccountTool.isLogin(this)) {
            Toast.makeText(this, "请登陆后重试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (presenter!=null&&model!=null){
            CommonTipsDialog.create(this,"温馨提示","确定要删除吗?",false)
                    .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                        @Override
                        public void cancel() {

                        }

                        @Override
                        public void confirm() {
                            Map<String,String> params = RS.getBaseParams(StudentEducationExpDetailActivity.this);
                            params.put("id",model.getId());
                            presenter.deleteEducationExperienceMobile(params);
                        }
                    }).show();
        }
    }

    @OnClick(R.id.modify)
    void modify() {
        if (model!=null){
            Intent intent = new Intent(this, StudentEducationExpOperateActivity.class);
            intent.putExtra("model",new Gson().toJson(model));
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_education_exp_detail);
    }

    @Override
    public void initData() {
        super.initData();

        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)) {
            model = new Gson().fromJson(modelJson, EducationExpModel.class);
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

            }

            @Override
            public void modifyEducationExperienceMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteEducationExperienceMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentEducationExpDetailActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(StudentEducationExpDetailActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
