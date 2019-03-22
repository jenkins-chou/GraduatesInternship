package com.jenking.graduatesinternship.activitys.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.dialog.CommonTipsDialog;
import com.jenking.graduatesinternship.models.impl.RecruitModel;
import com.jenking.graduatesinternship.ui.CommonLoading;
import com.jenking.graduatesinternship.utils.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class RecruitDetailActivity extends BaseActivity {

    private RecruitModel recruitModel;

    @BindView(R.id.job_name)
    TextView job_name;
    @BindView(R.id.salary)
    TextView salary;
    @BindView(R.id.working_region)
    TextView working_region;
    @BindView(R.id.depertment)
    TextView department;
    @BindView(R.id.working_day)
    TextView working_day;
    @BindView(R.id.working_time)
    TextView working_time;
    @BindView(R.id.job_content)
    TextView job_content;
    @BindView(R.id.working_address)
    TextView working_address;
    @BindView(R.id.job_abstract)
    TextView job_abstract;
    @BindView(R.id.job_detail)
    TextView job_detail;
    @BindView(R.id.welfare)
    TextView welfare;
    @BindView(R.id.job_requirements)
    TextView job_requirements;
    @BindView(R.id.skill_requirement)
    TextView skill_requirement;
    @BindView(R.id.team_detail)
    TextView team_detail;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.collect)
    void collect(){
        if (recruitModel!=null){
            CommonTipsDialog.create(this,"温馨提示","确定要收藏该招聘吗？",false)
                    .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                        @Override
                        public void cancel() {

                        }

                        @Override
                        public void confirm() {

                        }
                    }).show();
        }
    }

    @OnClick(R.id.submit)
    void submit(){
        if (recruitModel!=null){
            CommonTipsDialog.create(this,"温馨提示","确定要申请该招聘吗？",false)
                    .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                        @Override
                        public void cancel() {

                        }

                        @Override
                        public void confirm() {

                        }
                    }).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit_detail);
    }

    @Override
    public void initData() {
        super.initData();
        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)){
            recruitModel = new Gson().fromJson(modelJson,RecruitModel.class);
            if (recruitModel!=null){
                refreshView();
            }
        }
    }

    private void refreshView(){
        job_name.setText(recruitModel.getJob_name());
        salary.setText(recruitModel.getSalary());
        working_region.setText(recruitModel.getWorking_region());
        department.setText(recruitModel.getDepertment());
        working_day.setText(recruitModel.getWorking_day());
        working_time.setText(recruitModel.getWorking_start_time()+"-"+recruitModel.getWorking_end_time());
        job_content.setText(recruitModel.getJob_content());
        working_address.setText(recruitModel.getWorking_address());
        job_abstract.setText(recruitModel.getJob_abstract());
        job_detail.setText(recruitModel.getJob_detail());
        welfare.setText(recruitModel.getWelfare());
        job_requirements.setText(recruitModel.getJob_requirements());
        skill_requirement.setText(recruitModel.getSkill_requirement());
        team_detail.setText(recruitModel.getTeam_detail());
    }
}
