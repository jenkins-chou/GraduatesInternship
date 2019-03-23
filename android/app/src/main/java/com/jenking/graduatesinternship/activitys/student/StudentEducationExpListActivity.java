package com.jenking.graduatesinternship.activitys.student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentEducationExpListActivity extends BaseActivity {

    private EducationExperiencePresenter presenter;
    private List<EducationExpModel> datas;
    private BaseRecyclerAdapter adapter;

    @BindView(R.id.empty_show)
    LinearLayout empty_show;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.add_education_exp)
    void add_education_exp(){
        if (AccountTool.isLogin(this)){
            Intent intent = new Intent(this,StudentEducationExpOperateActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_education_exp_list);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        adapter = new BaseRecyclerAdapter<EducationExpModel>(this,datas,R.layout.activity_student_education_exp_list_item) {
            @Override
            protected void convert(BaseViewHolder helper, EducationExpModel item) {
                helper.setText(R.id.school,item.getEducation_school());
                helper.setText(R.id.major,item.getEducation_major());
                helper.setText(R.id.time,item.getEducation_start_time()+"-"+item.getEducation_end_time());
            }
        };
        adapter.openLoadAnimation(false);
        adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(StudentEducationExpListActivity.this, StudentEducationExpDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(datas.get(position)));
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(adapter);

        presenter = new EducationExperiencePresenter(this);
        presenter.setOnCallBack(new EducationExperiencePresenter.OnCallBack() {
            @Override
            public void getMineEducationExperienceMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    Log.e("getAllRecruit",isSuccess+object.toString()+"");
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                        List<EducationExpModel> educationExpModels = resultModel.getData();
                        datas = educationExpModels!=null?educationExpModels:datas;
                        adapter.setData(datas);
                        recyclerView.smoothScrollToPosition(0);
                    }
                }else{
                    Toast.makeText(StudentEducationExpListActivity.this, "服务器繁忙", Toast.LENGTH_SHORT).show();
                }
                checkData();
            }

            @Override
            public void addEducationExperienceMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void modifyEducationExperienceMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteEducationExperienceMobile(boolean isSuccess, Object object) {

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
            presenter.getMineEducationExperienceMobile(params);
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
