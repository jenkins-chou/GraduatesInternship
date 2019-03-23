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
import com.jenking.graduatesinternship.dialog.CommonTipsDialog;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.PersonalCertModel;
import com.jenking.graduatesinternship.models.impl.PersonalSkillModel;
import com.jenking.graduatesinternship.presenter.PersonalCertPresenter;
import com.jenking.graduatesinternship.presenter.PersonalSkillPresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class StudentPersonalCertListActivity extends BaseActivity {

    private PersonalCertPresenter presenter;
    private List<PersonalCertModel> datas;
    private BaseRecyclerAdapter adapter;

    @BindView(R.id.empty_show)
    LinearLayout empty_show;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.add_personal_cert)
    void add_personal_cert(){
        if (AccountTool.isLogin(this)){
            Intent intent = new Intent(this,StudentPersonalCertOperateActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_personal_cert_list);
    }

    @Override
    public void initData() {
        super.initData();

        datas = new ArrayList<>();

        adapter = new BaseRecyclerAdapter<PersonalCertModel>(this,datas,R.layout.activity_student_personal_cert_list_item) {
            @Override
            protected void convert(BaseViewHolder helper, final PersonalCertModel item) {
                helper.setText(R.id.cert_name,item.getCert_name());
                helper.setText(R.id.cert_time,item.getCert_time());

                TextView delete = helper.getView(R.id.delete);
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CommonTipsDialog.create(StudentPersonalCertListActivity.this,"温馨提示","确定要删除吗",false)
                                .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                                    @Override
                                    public void cancel() {

                                    }

                                    @Override
                                    public void confirm() {
                                        Map<String,String> params = RS.getBaseParams(StudentPersonalCertListActivity.this);
                                        params.put("id",item.getId());
                                        presenter.deletePersonalCertMobile(params);
                                    }
                                }).show();
                    }
                });
            }
        };
        adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(StudentPersonalCertListActivity.this,StudentPersonalCertOperateActivity.class);
                intent.putExtra("model",new Gson().toJson(datas.get(position)));
                startActivity(intent);
            }
        });
        adapter.openLoadAnimation(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(adapter);

        presenter = new PersonalCertPresenter(this);
        presenter.setOnCallBack(new PersonalCertPresenter.OnCallBack() {
            @Override
            public void getMinePersonalCertMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    Log.e("getAllRecruit",isSuccess+object.toString()+"");
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                        List<PersonalCertModel> personalCertModels = resultModel.getData();
                        datas = personalCertModels!=null?personalCertModels:datas;
                        adapter.setData(datas);
                        recyclerView.smoothScrollToPosition(0);
                    }
                }else{
                    Toast.makeText(StudentPersonalCertListActivity.this, "服务器繁忙", Toast.LENGTH_SHORT).show();
                }
                checkData();
            }

            @Override
            public void addPersonalCertMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void modifyPersonalCertMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void deletePersonalCertMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(StudentPersonalCertListActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        getData();
                    }
                }else{
                    Toast.makeText(StudentPersonalCertListActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
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
            presenter.getMinePersonalCertMobile(params);
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
