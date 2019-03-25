package com.jenking.graduatesinternship.activitys.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.BaseActivity;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.PersonalSkillModel;
import com.jenking.graduatesinternship.models.impl.StudentTutorModel;
import com.jenking.graduatesinternship.presenter.StudentTutorPresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentTutorActivity extends BaseActivity {

    private StudentTutorModel model;
    private StudentTutorPresenter presenter;
    @BindView(R.id.tutor_name)
    EditText tutor_name;
    @BindView(R.id.tutor_useridentify)
    EditText tutor_useridentify;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.submit)
    void submit(){
        if (!AccountTool.isLogin(this))return;
        String tutor_name_str = tutor_name.getText().toString();
        String tutor_useridentify_str = tutor_useridentify.getText().toString();
        if (StringUtil.isNotEmpty(tutor_name_str)
                &&StringUtil.isNotEmpty(tutor_useridentify_str)){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("student_id",AccountTool.getLoginUser(this).getId());
            params.put("teacher_useridentify",tutor_useridentify_str);
            params.put("teacher_name",tutor_name_str);
            if (model==null){
                presenter.addStudentTutor(params);
            }else{
                params.put("id",model.getId());
                presenter.addStudentTutor(params);
            }


        }else{
            Toast.makeText(this, "请输入完整信息", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_tutor);
    }

    @Override
    public void initData() {
        super.initData();
        presenter = new StudentTutorPresenter(this);
        presenter.setOnCallBack(new StudentTutorPresenter.OnCallBack() {
            @Override
            public void getStudentTutor(boolean isSuccess, Object object) {
                if (isSuccess){
                    Log.e("getAllRecruit",isSuccess+object.toString()+"");
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                        List<StudentTutorModel> studentTutorModels = resultModel.getData();
                        if (studentTutorModels!=null&&studentTutorModels.size()>0){
                            model = studentTutorModels.get(0);
                            tutor_name.setText(studentTutorModels.get(0).getTeacher_name());
                            tutor_useridentify.setText(studentTutorModels.get(0).getTeacher_useridentify());
                        }
                    }
                }else{
                    Toast.makeText(StudentTutorActivity.this, "服务器繁忙", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void addStudentTutor(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentTutorActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(StudentTutorActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void deleteStudentTutor(boolean isSuccess, Object object) {

            }

            @Override
            public void updateStudentTutor(boolean isSuccess, Object object) {

            }

            @Override
            public void getTeachersStudents(boolean isSuccess, Object object) {

            }
        });

        Map<String,String> params = RS.getBaseParams(this);
        params.put("student_id",AccountTool.getLoginUser(this).getId());
        presenter.getStudentTutor(params);
    }
}
