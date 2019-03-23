package com.jenking.graduatesinternship.activitys.common;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.RecruitModel;
import com.jenking.graduatesinternship.presenter.RecruitPresenter;
import com.jenking.graduatesinternship.utils.StringUtil;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class RecruitSearchActivity extends BaseActivity {

    private RecruitPresenter recruitPresenter;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private List<RecruitModel> datas;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.search_input)
    EditText search_input;

    @BindView(R.id.empty_show)
    LinearLayout empty_show;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.search_button)
    void search_button(){
        String value = search_input.getText().toString();
        if (StringUtil.isNotEmpty(value)){
            if (recruitPresenter!=null){
                Map<String,String> params = RS.getBaseParams(this);
                params.put("keyword",value);
                recruitPresenter.searchRecruitMobile(params);
            }
        }else{
            Toast.makeText(this, ""+value, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruit_search);
    }

    public void initData(){
        datas = new ArrayList<>();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        baseRecyclerAdapter = new BaseRecyclerAdapter<RecruitModel>(RecruitSearchActivity.this,datas,R.layout.fragment_student_main_part1_item) {
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
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (datas.get(position)!=null){
                    Intent intent = new Intent(RecruitSearchActivity.this, RecruitDetailActivity.class);
                    intent.putExtra("model",new Gson().toJson(datas.get(position)));
                    startActivity(intent);
                }else{
                    Toast.makeText(RecruitSearchActivity.this, "数据错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setAdapter(baseRecyclerAdapter);

        recruitPresenter = new RecruitPresenter(RecruitSearchActivity.this);
        recruitPresenter.setOnCallBack(new RecruitPresenter.OnCallBack() {
            @Override
            public void getAllRecruit(boolean isSuccess, Object object) {

            }

            @Override
            public void getFilterRecruitMobile(boolean isSuccess, Object object) {
            }

            @Override
            public void searchRecruitMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    Log.e("searchRecruitMobile",isSuccess+object.toString()+"");
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                        List<RecruitModel> recruitModels = resultModel.getData();
                        datas = recruitModels!=null?recruitModels:datas;
                        baseRecyclerAdapter.setData(datas);
                        recyclerView.smoothScrollToPosition(0);
                    }
                }else{
                    Toast.makeText(RecruitSearchActivity.this, "服务器繁忙", Toast.LENGTH_SHORT).show();
                }
                checkData();
            }
        });
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
