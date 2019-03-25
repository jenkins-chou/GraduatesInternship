package com.jenking.graduatesinternship.activitys.common;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.student.StudentResumeSelectActivity;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.dialog.CommonTipsDialog;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.RecruitDeliveryModel;
import com.jenking.graduatesinternship.models.impl.RecruitModel;
import com.jenking.graduatesinternship.presenter.RecruitCollectionPresenter;
import com.jenking.graduatesinternship.presenter.RecruitDeliveryPresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RecruitDetailActivity extends BaseActivity {

    private RecruitDeliveryPresenter recruitDeliveryPresenter;//岗位投递presenter
    private RecruitCollectionPresenter recruitCollectionPresenter;//岗位收藏presenter
    private RecruitModel recruitModel;

    @BindView(R.id.job_name)
    TextView job_name;
    @BindView(R.id.salary)
    TextView salary;
    @BindView(R.id.working_region)
    TextView working_region;
    @BindView(R.id.depertment)
    TextView department;
    @BindView(R.id.ecterprise_name)
    TextView ecterprise_name;
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
    @BindView(R.id.collect)
    TextView collect;//收藏按钮

    @BindView(R.id.submit)
    TextView submit;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.collect)
    void collect(){
        if (checkLogin()) {
            if (recruitModel != null) {
                CommonTipsDialog.create(this, "温馨提示", "确定要收藏该招聘吗？", false)
                        .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                            @Override
                            public void cancel() {

                            }

                            @Override
                            public void confirm() {
                                if (recruitCollectionPresenter!=null){
                                    Map<String,String> params = RS.getBaseParams(RecruitDetailActivity.this);
                                    params.put("user_id",AccountTool.getLoginUser(RecruitDetailActivity.this).getId());
                                    params.put("recruit_id",recruitModel.getId());
                                    recruitCollectionPresenter.addRecruitmentCollectionMobile(params);
                                }
                            }
                        }).show();
            }
        }
    }

    @OnClick(R.id.submit)
    void submit(){
        if (checkLogin()) {
            if (recruitModel!=null){
                CommonTipsDialog.create(this,"温馨提示","确定要申请该招聘吗？",false)
                        .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                            @Override
                            public void cancel() {

                            }

                            @Override
                            public void confirm() {
                                selectResume();
                            }
                        }).show();
            }
        }
    }

    @OnClick(R.id.ecterprise_bar)
    void ecterprise_bar(){

    }

    //选择简历
    void selectResume(){
        Intent intent = new Intent(this,StudentResumeSelectActivity.class);
        startActivityForResult(intent,StudentResumeSelectActivity.selectResumeCode);
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

        initRecruitCollection();
        initRecruitDelivery();

        getData();
    }

    /**
     * 获取是否已经投递、收藏过得数据
     */
    void getData(){
        if (AccountTool.isLogin(this)&&recruitModel!=null){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("user_id",AccountTool.getLoginUser(this).getId());
            params.put("recruit_id",recruitModel.getId());
            recruitDeliveryPresenter.checkIsDelivery(params);
            recruitCollectionPresenter.checkIsCollect(params);
        }
    }

    private void refreshView(){
        job_name.setText(recruitModel.getJob_name());
        salary.setText(recruitModel.getSalary());
        working_region.setText(recruitModel.getWorking_region());
        department.setText(recruitModel.getDepertment());
        ecterprise_name.setText(recruitModel.getEnterprise_name());
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

    private boolean checkLogin(){
        if (AccountTool.isLogin(this)){
            return true;
        }else{
            Toast.makeText(this, "请登录后重试", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    void initRecruitCollection(){
        recruitCollectionPresenter = new RecruitCollectionPresenter(this);
        recruitCollectionPresenter.setOnCallBack(new RecruitCollectionPresenter.OnCallBack() {
            @Override
            public void getMineRecruitmentCollectionMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void addRecruitmentCollectionMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(RecruitDetailActivity.this, "收藏成功，请前往我的岗位收藏查看", Toast.LENGTH_SHORT).show();
                        collect.setText("已收藏");
                        collect.setEnabled(false);
                        collect.setTextColor(getResources().getColor(R.color.base_font_color2));
                    }else{
                        CommonTipsDialog.showTip(RecruitDetailActivity.this,"温馨提示",resultModel.getMessage()+"",false);
                    }
                }else{
                    Toast.makeText(RecruitDetailActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void modifyRecruitmentCollectionMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteRecruitmentCollectionMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void checkIsCollect(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null){
                        if (StringUtil.isEquals(resultModel.getCode(),"200")){//未收藏

                        }else if (StringUtil.isEquals(resultModel.getCode(),"201")){//已经收藏过了
                            collect.setText("已收藏");
                            collect.setEnabled(false);
                            collect.setTextColor(getResources().getColor(R.color.base_font_color2));
                        }else{
                        }
                    }
                }
            }
        });
    }

    void initRecruitDelivery(){
        recruitDeliveryPresenter = new RecruitDeliveryPresenter(this);
        recruitDeliveryPresenter.setOnCallBack(new RecruitDeliveryPresenter.OnCallBack() {
            @Override
            public void getMineRecruitmentDeliveryMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void addRecruitmentDeliveryMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(RecruitDetailActivity.this, "投递成功，请前往我的申请查看", Toast.LENGTH_SHORT).show();
                        submit.setEnabled(false);
                        submit.setText("已经投递过该职位");
                    }else{
                        CommonTipsDialog.showTip(RecruitDetailActivity.this,"温馨提示",resultModel.getMessage()+"",false);
                    }
                }else{
                    Toast.makeText(RecruitDetailActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void modifyRecruitmentDeliveryMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteRecruitmentDeliveryMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void checkIsDelivery(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null){
                        if (StringUtil.isEquals(resultModel.getCode(),"200")){//未投递

                        }else if (StringUtil.isEquals(resultModel.getCode(),"201")){//已经投递过了
                            submit.setEnabled(false);
                            submit.setText("已经投递过该职位");
                        }else{
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode){
            case StudentResumeSelectActivity.selectResumeCode:
//                Toast.makeText(this, ""+data.getStringExtra("resume_name"), Toast.LENGTH_SHORT).show();
                submit.setText("已选择简历："+data.getStringExtra("resume_name"));
                Map<String,String> params = RS.getBaseParams(RecruitDetailActivity.this);
                params.put("user_id",AccountTool.getLoginUser(RecruitDetailActivity.this).getId());
                params.put("recruit_id",recruitModel.getId());
                params.put("resume_id",data.getStringExtra("resume_id"));
                params.put("status", RecruitDeliveryModel.DELIVERY_STATUS_INIT);
                recruitDeliveryPresenter.addRecruitmentDeliveryMobile(params);
                break;
        }
    }
}
