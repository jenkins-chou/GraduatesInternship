package com.jenking.graduatesinternship.activitys.student;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import com.github.library.listener.OnRecyclerItemLongClickListener;
import com.google.gson.Gson;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.BaseActivity;
import com.jenking.graduatesinternship.activitys.common.RecruitDetailActivity;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.dialog.CommonTipsDialog;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.RecruitDeliveryModel;
import com.jenking.graduatesinternship.models.impl.RecruitModel;
import com.jenking.graduatesinternship.presenter.RecruitDeliveryPresenter;
import com.jenking.graduatesinternship.presenter.RecruitDeliveryPresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentRecruitDeliveryActivity extends BaseActivity {
    private RecruitDeliveryPresenter presenter;
    private List<RecruitDeliveryModel> datas;
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
        CommonTipsDialog.showTip(this,"温馨提示","长按后撤销申请",false);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_recruit_delivery);
    }


    @Override
    public void initData() {
        super.initData();

        datas = new ArrayList<>();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        adapter = new BaseRecyclerAdapter<RecruitDeliveryModel>(StudentRecruitDeliveryActivity.this,datas,R.layout.activity_student_recruit_delivery_item) {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            protected void convert(BaseViewHolder helper, RecruitDeliveryModel item) {
                helper.setText(R.id.job_name,item.getJob_name());
                helper.setText(R.id.salary,item.getSalary());
                helper.setText(R.id.enterprise_name,item.getEnterprise_name());
                helper.setText(R.id.working_region,item.getWorking_region());
                helper.setText(R.id.working_day,item.getWorking_day());
                helper.setText(R.id.working_time,item.getWorking_start_time()+"-"+item.getWorking_end_time());
                helper.setText(R.id.publisher,item.getPublisher());

                TextView status = helper.getView(R.id.status);
                switch (item.getStatus()){
                    case RecruitDeliveryModel.DELIVERY_STATUS_INIT:
                        status.setText("待审核");
                        status.setBackground(getResources().getDrawable(R.drawable.common_tag_blue));
                        break;
                    case RecruitDeliveryModel.DELIVERY_STATUS_INTERVIEW:
                        status.setText("待面试");
                        status.setBackground(getResources().getDrawable(R.drawable.common_tag_orange));
                        break;
                    case RecruitDeliveryModel.DELIVERY_STATUS_UNPASS:
                        status.setText("不通过");
                        status.setBackground(getResources().getDrawable(R.drawable.common_tag_red));
                        break;
                    case RecruitDeliveryModel.DELIVERY_STATUS_PASS:
                        status.setText("已通过");
                        status.setBackground(getResources().getDrawable(R.drawable.common_tag_green));
                        break;
                    case RecruitDeliveryModel.DELIVERY_STATUS_INTERNSHIP:
                        status.setText("实习中");
                        status.setBackground(getResources().getDrawable(R.drawable.common_tag_gray));
                        break;
                    case RecruitDeliveryModel.DELIVERY_STATUS_END:
                        status.setText("实习结束");
                        status.setBackground(getResources().getDrawable(R.drawable.common_tag_blue));
                        break;
                    case RecruitDeliveryModel.DELIVERY_STATUS_CANCEL:
                        status.setText("因其他原因注销");
                        status.setBackground(getResources().getDrawable(R.drawable.common_tag_red));
                        break;
                        default:
                            helper.setText(R.id.status,"未知状态");
                            status.setBackground(getResources().getDrawable(R.drawable.common_tag_red));
                            break;
                }
            }
        };
        adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (datas.get(position)!=null){
                    Intent intent = new Intent(StudentRecruitDeliveryActivity.this, RecruitDetailActivity.class);
                    intent.putExtra("model",new Gson().toJson(datas.get(position)));
                    startActivity(intent);
                }else{
                    Toast.makeText(StudentRecruitDeliveryActivity.this, "数据错误", Toast.LENGTH_SHORT).show();
                }

            }
        });

        adapter.setOnRecyclerItemLongClickListener(new OnRecyclerItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, final int position) {
                if (datas.get(position)!=null){
                    if (StringUtil.isEquals(datas.get(position).getStatus(),"0")) {
                        CommonTipsDialog.create(StudentRecruitDeliveryActivity.this, "温馨提示", "确定要撤销申请吗?", false)
                                .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                                    @Override
                                    public void cancel() {

                                    }

                                    @Override
                                    public void confirm() {
                                        Map<String, String> params = RS.getBaseParams(StudentRecruitDeliveryActivity.this);
                                        params.put("user_id", AccountTool.getLoginUser(StudentRecruitDeliveryActivity.this).getId());
                                        params.put("recruit_id", datas.get(position).getRecruit_id());
                                        presenter.deleteRecruitmentDeliveryMobile(params);
                                    }
                                }).show();
                    }else{
                        Toast.makeText(StudentRecruitDeliveryActivity.this, "当前状态已进入企业受理阶段，不可撤销", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            }
        });
        adapter.openLoadAnimation(false);
        recyclerView.setAdapter(adapter);

        presenter = new RecruitDeliveryPresenter(this);
        presenter.setOnCallBack(new RecruitDeliveryPresenter.OnCallBack() {
            @Override
            public void getMineRecruitmentDeliveryMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    Log.e("getAllRecruit",isSuccess+object.toString()+"");
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                        List<RecruitDeliveryModel> recruitModels = resultModel.getData();
                        datas = recruitModels!=null?recruitModels:datas;
                        adapter.setData(datas);
                        recyclerView.smoothScrollToPosition(0);
                    }
                }else{
                    Toast.makeText(StudentRecruitDeliveryActivity.this, "服务器繁忙", Toast.LENGTH_SHORT).show();
                }
                checkData();
            }

            @Override
            public void addRecruitmentDeliveryMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void modifyRecruitmentDeliveryMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteRecruitmentDeliveryMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentRecruitDeliveryActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        getData();
                    }
                }else{
                    Toast.makeText(StudentRecruitDeliveryActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void checkIsDelivery(boolean isSuccess, Object object) {

            }
        });
        getData();
    }

    private void getData(){
        if (AccountTool.isLogin(this)){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("user_id",AccountTool.getLoginUser(this).getId());
            presenter.getMineRecruitmentDeliveryMobile(params);
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
