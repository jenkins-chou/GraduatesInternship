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
import com.jenking.graduatesinternship.models.impl.PersonalSkillModel;
import com.jenking.graduatesinternship.presenter.PersonalSkillPresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentPersonalSkillOperateActivity extends BaseActivity {
    private PersonalSkillPresenter presenter;
    private PersonalSkillModel model;
    @BindView(R.id.skill_name)
    EditText skill_name;

    @OnClick(R.id.submit)
    void submit(){
        if (AccountTool.isLogin(this)){
            String skill_name_str = skill_name.getText().toString();
            if (StringUtil.isNotEmpty(skill_name_str)){
                Map<String,String> params = RS.getBaseParams(this);
                params.put("user_id",AccountTool.getLoginUser(this).getId());
                params.put("skill_name",skill_name_str);
                if (model!=null){
                    params.put("id",model.getId());
                    presenter.modifyPersonalSkillMobile(params);
                }else{
                    presenter.addPersonalSkillMobile(params);
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
        setContentView(R.layout.activity_student_personal_skill_add);
    }

    @Override
    public void initData() {
        super.initData();

        String modelJson = getIntent().getStringExtra("model");
        if (StringUtil.isNotEmpty(modelJson)){
            model = new Gson().fromJson(modelJson,PersonalSkillModel.class);
            skill_name.setText(model.getSkill_name());
        }

        presenter = new PersonalSkillPresenter(this);
        presenter.setOnCallBack(new PersonalSkillPresenter.OnCallBack() {
            @Override
            public void getMinePersonalSkillMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void addPersonalSkillMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentPersonalSkillOperateActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(StudentPersonalSkillOperateActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void modifyPersonalSkillMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentPersonalSkillOperateActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(StudentPersonalSkillOperateActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void deletePersonalSkillMobile(boolean isSuccess, Object object) {

            }
        });
    }
}
