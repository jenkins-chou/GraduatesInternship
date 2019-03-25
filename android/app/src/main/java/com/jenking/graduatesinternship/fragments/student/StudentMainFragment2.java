package com.jenking.graduatesinternship.fragments.student;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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
import com.jenking.graduatesinternship.activitys.student.StudentInternShipDetailActivity;
import com.jenking.graduatesinternship.activitys.student.StudentInternshipOperateActivity;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.InternshipExperienceModel;
import com.jenking.graduatesinternship.models.impl.RecruitModel;
import com.jenking.graduatesinternship.presenter.InternshipExperiencePresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
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

public class StudentMainFragment2 extends Fragment{
    private Context context;
    private Unbinder unbinder;

    private InternshipExperiencePresenter presenter;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private List<InternshipExperienceModel> datas;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.smartRefreshLayout)
    SmartRefreshLayout smartRefreshLayout;

    @BindView(R.id.empty_show)
    LinearLayout empty_show;

    @OnClick(R.id.internship_add)
    void internship_add(){
        Intent intent = new Intent(getContext(), StudentInternshipOperateActivity.class);
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_main_part2,container,false);
        unbinder = ButterKnife.bind(this,view);
        initData();
        return view;
    }

    private void initData(){
        datas = new ArrayList<>();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        baseRecyclerAdapter = new BaseRecyclerAdapter<InternshipExperienceModel>(this.getContext(),datas,R.layout.fragment_student_main_part2_item) {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            protected void convert(BaseViewHolder helper, InternshipExperienceModel item) {
                View header_view = helper.getView(R.id.header_view);
                if (StringUtil.isNotEmpty(item.getId())&&StringUtil.isNumber(item.getId())){
                    int idInt = Integer.parseInt(item.getId());
                    if (idInt%3 == 0){
                        header_view.setBackground(getResources().getDrawable(R.drawable.fragment_student_main_part2_item_tag1));
                    }else if(idInt%3 == 1){
                        header_view.setBackground(getResources().getDrawable(R.drawable.fragment_student_main_part2_item_tag2));
                    }else{
                        header_view.setBackground(getResources().getDrawable(R.drawable.fragment_student_main_part2_item_tag3));
                    }
                }
                helper.setText(R.id.job_name,item.getInternship_job());
                helper.setText(R.id.enterprise,item.getInternship_enterprise());
                helper.setText(R.id.job_time,item.getInternship_start_time()+"-"+item.getInternship_end_time());
            }
        };
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), StudentInternShipDetailActivity.class);
                intent.putExtra("model",new Gson().toJson(datas.get(position)));
                startActivity(intent);
            }
        });
        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setAdapter(baseRecyclerAdapter);

        presenter = new InternshipExperiencePresenter(getContext());
        presenter.setOnCallBack(new InternshipExperiencePresenter.OnCallBack() {
            @Override
            public void addInternshipMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void modifyInternshipMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteInternshipMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void getMineInternshipMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    Log.e("getAllRecruit",isSuccess+object.toString()+"");
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                        List<InternshipExperienceModel> recruitModels = resultModel.getData();
                        datas = recruitModels!=null?recruitModels:datas;
                        baseRecyclerAdapter.setData(datas);
                        recyclerView.smoothScrollToPosition(0);
                    }
                }else{
                    Toast.makeText(getContext(), "服务器繁忙", Toast.LENGTH_SHORT).show();
                }
                smartRefreshLayout.finishRefresh();
                smartRefreshLayout.finishLoadMore();
                checkData();
            }
        });
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setRefreshHeader(new TaurusHeader(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                if (AccountTool.isLogin(getContext())){
                    getAllData();
                }else{
                    smartRefreshLayout.finishRefresh();
                    Toast.makeText(getContext(), "请登录后重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getAllData();
    }

    void getAllData(){
        if (AccountTool.isLogin(getContext())){
            Map<String,String> params = RS.getBaseParams(getContext());
            params.put("user_id",AccountTool.getLoginUser(getContext()).getId());
            presenter.getMineInternshipMobile(params);
        }else{
            datas.clear();
            baseRecyclerAdapter.setData(datas);
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
