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
import com.jenking.graduatesinternship.models.impl.InternshipExperienceModel;
import com.jenking.graduatesinternship.presenter.InternshipExperiencePresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentInternShipDetailActivity extends BaseActivity {

    private InternshipExperiencePresenter presenter;
    private InternshipExperienceModel model;
    @BindView(R.id.internship_job)
    TextView internship_job;
    @BindView(R.id.internship_time)
    TextView internship_time;
    @BindView(R.id.internship_enterprise)
    TextView internship_enterprise;
    @BindView(R.id.internship_job_content)
    TextView internship_job_content;
    @BindView(R.id.internship_result)
    TextView internship_result;
    @BindView(R.id.internship_harvest)
    TextView internship_harvest;
    
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
            Intent intent = new Intent(this, StudentInternshipOperateActivity.class);
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
                            Map<String,String> params = RS.getBaseParams(StudentInternShipDetailActivity.this);
                            params.put("id",model.getId());
                            presenter.deleteInternshipMobile(params);
                        }
                    }).show();

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_intern_ship_detail);
    }

    @Override
    public void initData() {
        super.initData();
        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)){
            model = new Gson().fromJson(modelJson,InternshipExperienceModel.class);
            internship_job.setText(model.getInternship_job());
            internship_time.setText(model.getInternship_start_time()+"-"+model.getInternship_end_time());
            internship_enterprise.setText(model.getInternship_enterprise());
            internship_job_content.setText(model.getInternship_job_content());
            internship_result.setText(model.getInternship_result());
            internship_harvest.setText(model.getInternship_harvest());
        }
        presenter = new InternshipExperiencePresenter(this);
        presenter.setOnCallBack(new InternshipExperiencePresenter.OnCallBack() {
            @Override
            public void addInternshipMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void modifyInternshipMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteInternshipMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentInternShipDetailActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(StudentInternShipDetailActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void getMineInternshipMobile(boolean isSuccess, Object object) {

            }
        });

    }
}
