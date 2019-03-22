package com.jenking.graduatesinternship.fragments.student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.RecruitDetailActivity;
import com.jenking.graduatesinternship.activitys.common.RecruitSearchActivity;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.dialog.CommonBottomListDialog;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.RecruitModel;
import com.jenking.graduatesinternship.presenter.RecruitPresenter;
import com.jenking.graduatesinternship.utils.StringUtil;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class StudentMainFragment1 extends Fragment {
    private Context context;
    private Unbinder unbinder;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private List<RecruitModel> datas;
    private RecruitPresenter recruitPresenter;

    private int pageNum = 1;
    private int getDataType = 0;//获取数据方式：0为刷新第一页数据，1为加载更多

    private String select_working_day;
    private String select_salary;

    //筛选条件
    @BindView(R.id.working_day)
    TextView working_day;
    @BindView(R.id.salary)
    TextView salary;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.empty_show)
    LinearLayout empty_show;

    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.get_data_tips)
    TextView get_data_tips;//更新提示

    @OnClick(R.id.search_button)
    void search_button(){
        Intent intent = new Intent(getContext(), RecruitSearchActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.select_working_day)
    void select_working_day(){
        List<String> workingDays = new ArrayList<>();
        workingDays.add("全部");
        workingDays.add("1-2天/周");
        workingDays.add("3-4天/周");
        workingDays.add("5-7天/周");
        CommonBottomListDialog commonBottomListDialog = new CommonBottomListDialog(getContext(),"选择时长",workingDays,"",true) {
            @Override
            protected void setOnItemClickListener(String value) {
                if (StringUtil.isEquals(value,"全部")){
                    select_working_day = null;
                }else {
                    select_working_day = value;
                }
                working_day.setText(value);
                working_day.setTextColor(getResources().getColor(R.color.main_color));

                getFilterData();
            }
        };
        commonBottomListDialog.show();
    }

    @OnClick(R.id.select_salary)
    void select_salary(){
        List<String> workingDays = new ArrayList<>();
        workingDays.add("全部");
        workingDays.add("0-2000元/月");
        workingDays.add("2000-4000元/月");
        workingDays.add("4000-6000元/月");
        workingDays.add("6000-10000元/月");
        workingDays.add("10000元以上/月");
        CommonBottomListDialog commonBottomListDialog = new CommonBottomListDialog(getContext(),"选择薪资范围",workingDays,"",true) {
            @Override
            protected void setOnItemClickListener(String value) {
                if (StringUtil.isEquals(value,"全部")){
                    select_salary = null;
                }else {
                    select_salary = value;
                }
                salary.setText(value);
                salary.setTextColor(getResources().getColor(R.color.main_color));

                getFilterData();

            }
        };
        commonBottomListDialog.show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_main_part1,container,false);
        unbinder = ButterKnife.bind(this,view);
        initData();
        return view;
    }

    private void initData(){
        datas = new ArrayList<>();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        baseRecyclerAdapter = new BaseRecyclerAdapter<RecruitModel>(this.getContext(),datas,R.layout.fragment_student_main_part1_item) {
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
                    Intent intent = new Intent(getContext(), RecruitDetailActivity.class);
                    intent.putExtra("model",new Gson().toJson(datas.get(position)));
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "数据错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setAdapter(baseRecyclerAdapter);

        recruitPresenter = new RecruitPresenter(this.getContext());
        recruitPresenter.setOnCallBack(new RecruitPresenter.OnCallBack() {
            @Override
            public void getAllRecruit(boolean isSuccess, Object object) {
                if (isSuccess){
                    Log.e("getAllRecruit",isSuccess+object.toString()+"");
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                        List<RecruitModel> recruitModels = resultModel.getData();
                        if (getDataType==0){
                            datas = recruitModels!=null?recruitModels:datas;
                            baseRecyclerAdapter.setData(datas);
                            recyclerView.smoothScrollToPosition(0);
                        }else {
                            datas.addAll(recruitModels);
                            baseRecyclerAdapter.setData(datas);
                            recyclerView.smoothScrollToPosition(datas!=null?datas.size():0);
                        }
                        if (recruitModels!=null&&recruitModels.size()>0){
                            showTips(recruitModels.size());
                        }
                    }
                }else{
                    Toast.makeText(getContext(), "服务器繁忙", Toast.LENGTH_SHORT).show();
                }
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadMore();
                checkData();
            }

            @Override
            public void getFilterRecruitMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    Log.e("getFilterRecruitMobile",isSuccess+object.toString()+"");
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                        List<RecruitModel> recruitModels = resultModel.getData();
                        if (getDataType==0){
                            datas = recruitModels!=null?recruitModels:datas;
                            baseRecyclerAdapter.setData(datas);
                            recyclerView.smoothScrollToPosition(0);
                        }else {
                            datas.addAll(recruitModels);
                            baseRecyclerAdapter.setData(datas);
                            recyclerView.smoothScrollToPosition(datas!=null?datas.size():0);
                        }
                        if (recruitModels!=null&&recruitModels.size()>0){
                            showTips(recruitModels.size());
                        }
                    }
                }
                checkData();
            }
        });
        smartRefreshLayout.setEnableLoadMore(true);
        smartRefreshLayout.setRefreshHeader(new TaurusHeader(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getDataType = 0;
                pageNum = 1;
                getAllData();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getDataType = 1;
                getAllData();
            }
        });

        getAllData();
    }

    void showTips(int size){
        get_data_tips.setText("已获取"+size+"条数据");
        get_data_tips.setVisibility(View.VISIBLE);
        showTipsHander.sendEmptyMessageDelayed(0,2500);
    }

    private Handler showTipsHander = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            get_data_tips.setVisibility(View.GONE);
        }
    };

    void getAllData(){
        Map<String,String> params = RS.getBaseParams(getContext());
        params.put("pageNum",pageNum+"");
        pageNum++;
        recruitPresenter.getAllRecruit(params);
    }

    void getFilterData(){
        //如果两个筛选条件同事为空时，将pageNum置为1
        if (!StringUtil.isNotEmpty(select_working_day)&&!StringUtil.isNotEmpty(select_salary)){
            pageNum = 1;
        }
        Map<String,String> params = RS.getBaseParams(getContext());
        params.put("working_day",select_working_day+"");
        params.put("salary",select_salary+"");
        recruitPresenter.getFilterRecruitMobile(params);
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
