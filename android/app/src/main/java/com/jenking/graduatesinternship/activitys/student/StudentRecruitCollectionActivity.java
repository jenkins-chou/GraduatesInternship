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
import com.github.library.listener.OnRecyclerItemLongClickListener;
import com.google.gson.Gson;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.BaseActivity;
import com.jenking.graduatesinternship.activitys.common.RecruitDetailActivity;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.dialog.CommonTipsDialog;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.PersonalSkillModel;
import com.jenking.graduatesinternship.models.impl.RecruitModel;
import com.jenking.graduatesinternship.presenter.RecruitCollectionPresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentRecruitCollectionActivity extends BaseActivity {

    private RecruitCollectionPresenter presenter;
    private List<RecruitModel> datas;
    private BaseRecyclerAdapter adapter;

    @BindView(R.id.empty_show)
    LinearLayout empty_show;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.operate_tips)
    void operate_tips(){
        CommonTipsDialog.showTip(this,"温馨提示","长按后删除",false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_recruit_collection);
    }

    @Override
    public void initData() {
        super.initData();

        datas = new ArrayList<>();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        adapter = new BaseRecyclerAdapter<RecruitModel>(StudentRecruitCollectionActivity.this,datas,R.layout.fragment_student_main_part1_item) {
            @Override
            protected void convert(BaseViewHolder helper, RecruitModel item) {
                helper.setText(R.id.job_name,item.getJob_name());
                helper.setText(R.id.salary,item.getSalary());
                helper.setText(R.id.enterprise_name,item.getEnterprise_name());
                helper.setText(R.id.working_region,item.getWorking_region());
                helper.setText(R.id.working_day,item.getWorking_day());
                helper.setText(R.id.working_time,item.getWorking_start_time()+"-"+item.getWorking_end_time());
                helper.setText(R.id.publisher,item.getPublisher());

                if (StringUtil.isNotEmpty(item.getCreate_time())){
                    long create_time_long = Long.parseLong(item.getCreate_time());
                    long durationTime = System.currentTimeMillis()/1000-create_time_long;

                    Log.e("create_time_long",create_time_long+"");
                    Log.e("durationTime",durationTime+"");
                    if (durationTime<3600){
                        helper.setText(R.id.create_time,"刚刚发布");
                    }else if (durationTime<86400&&durationTime>3600){
                        helper.setText(R.id.create_time,"今天内");
                    }else{
                        helper.setText(R.id.create_time,StringUtil.getStrTime(item.getCreate_time()));
                    }
                }else{
                    helper.setText(R.id.create_time,"未知时间");
                }

            }
        };
        adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (datas.get(position)!=null){
                    Intent intent = new Intent(StudentRecruitCollectionActivity.this, RecruitDetailActivity.class);
                    intent.putExtra("model",new Gson().toJson(datas.get(position)));
                    startActivity(intent);
                }else{
                    Toast.makeText(StudentRecruitCollectionActivity.this, "数据错误", Toast.LENGTH_SHORT).show();
                }

            }
        });

        adapter.setOnRecyclerItemLongClickListener(new OnRecyclerItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, final int position) {
                if (datas.get(position)!=null){
                    CommonTipsDialog.create(StudentRecruitCollectionActivity.this,"温馨提示","确定要删除吗?",false)
                            .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                                @Override
                                public void cancel() {

                                }

                                @Override
                                public void confirm() {
                                    Map<String,String> params = RS.getBaseParams(StudentRecruitCollectionActivity.this);
                                    params.put("user_id",AccountTool.getLoginUser(StudentRecruitCollectionActivity.this).getId());
                                    params.put("recruit_id",datas.get(position).getId());
                                    presenter.deleteRecruitmentCollectionMobile(params);
                                }
                            }).show();
                }
                return false;
            }
        });
        adapter.openLoadAnimation(false);
        recyclerView.setAdapter(adapter);

        presenter = new RecruitCollectionPresenter(this);
        presenter.setOnCallBack(new RecruitCollectionPresenter.OnCallBack() {
            @Override
            public void getMineRecruitmentCollectionMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    Log.e("getAllRecruit",isSuccess+object.toString()+"");
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                        List<RecruitModel> recruitModels = resultModel.getData();
                        datas = recruitModels!=null?recruitModels:datas;
                        adapter.setData(datas);
                        recyclerView.smoothScrollToPosition(0);
                    }
                }else{
                    Toast.makeText(StudentRecruitCollectionActivity.this, "服务器繁忙", Toast.LENGTH_SHORT).show();
                }
                checkData();
            }

            @Override
            public void addRecruitmentCollectionMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void modifyRecruitmentCollectionMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteRecruitmentCollectionMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentRecruitCollectionActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        getData();
                    }
                }else{
                    Toast.makeText(StudentRecruitCollectionActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getData();
    }

    private void getData(){
        if (AccountTool.isLogin(this)){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("user_id",AccountTool.getLoginUser(this).getId());
            presenter.getMineRecruitmentCollectionMobile(params);
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
