package com.jenking.graduatesinternship.activitys.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.BaseActivity;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.ResumeModel;
import com.jenking.graduatesinternship.presenter.ResumePresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentResumeSelectActivity extends BaseActivity {
    public static final int selectResumeCode = 1001;//选择简历code
    private ResumePresenter resumePresenter;
    private List<ResumeModel> datas;
    private BaseRecyclerAdapter adapter;

    @BindView(R.id.empty_show)
    LinearLayout empty_show;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.create)
    void create(){
        if (!AccountTool.isLogin(this)){
            Toast.makeText(this, "请登录后重试", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(this, StudentResumeOperateActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_resume_select);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        adapter = new BaseRecyclerAdapter<ResumeModel>(this,datas,R.layout.activity_student_resume_select_item) {
            @Override
            protected void convert(BaseViewHolder helper, final ResumeModel item) {
                helper.setText(R.id.resume_name,item.getResume_name());
                helper.setText(R.id.resume_intention_job,"期望岗位:"+item.getResume_intention_job());
                helper.setText(R.id.resume_expected_salary,"期望月薪:"+item.getResume_expected_salary());
                helper.setText(R.id.resume_education,"教育程度:"+item.getResume_education());

                TextView select = helper.getView(R.id.select);
                select.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("resume_id",item.getId());
                        intent.putExtra("resume_name",item.getResume_name());
                        setResult(selectResumeCode,intent);
                        finish();
                    }
                });
            }
        };
        adapter.openLoadAnimation(false);
        adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(StudentResumeSelectActivity.this, StudentResumeDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(datas.get(position)));
                startActivity(intent);
            }
        });

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(adapter);

        resumePresenter = new ResumePresenter(this);
        resumePresenter.setOnCallBack(new ResumePresenter.OnCallBack() {
            @Override
            public void getMineResumeMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    Log.e("getAllRecruit",isSuccess+object.toString()+"");
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                        List<ResumeModel> resumeModels = resultModel.getData();
                        datas = resumeModels!=null?resumeModels:datas;
                        adapter.setData(datas);
                        recyclerView.smoothScrollToPosition(0);
                    }
                }else{
                    Toast.makeText(StudentResumeSelectActivity.this, "服务器繁忙", Toast.LENGTH_SHORT).show();
                }
                checkData();
            }

            @Override
            public void addResumeMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void modifyResumeMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteResumeMobile(boolean isSuccess, Object object) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData(){
        if (AccountTool.isLogin(this)){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("user_id",AccountTool.getLoginUser(this).getId());
            resumePresenter.getMineResumeMobile(params);
        }
    }

    //检查数据
    void checkData(){
        if (datas==null||datas.size()<=0){
            empty_show.setVisibility(View.VISIBLE);
        }else{
            empty_show.setVisibility(View.GONE);
        }
    }
}
