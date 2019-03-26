package com.jenking.graduatesinternship.activitys.common;

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
import android.widget.Toast;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemLongClickListener;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.student.StudentEducationExpListActivity;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.dialog.CommonTipsDialog;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.EducationExpModel;
import com.jenking.graduatesinternship.models.impl.InternshipExperienceModel;
import com.jenking.graduatesinternship.models.impl.MessageModel;
import com.jenking.graduatesinternship.presenter.MessagePresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity {

    private MessagePresenter presenter;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private List<MessageModel> datas;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.empty_show)
    LinearLayout empty_show;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.help)
    void help(){
        CommonTipsDialog.showTip(this,"温馨提示","长按后回复",false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }

    @Override
    public void initData() {
        super.initData();
        datas = new ArrayList<>();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        baseRecyclerAdapter = new BaseRecyclerAdapter<MessageModel>(this,datas,R.layout.activity_message_item) {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            protected void convert(BaseViewHolder helper, MessageModel item) {
                helper.setText(R.id.send_user_name,item.getSend_user_name());
                helper.setText(R.id.create_time,StringUtil.getStrTime(item.getCreate_time(),"yyyy-MM-dd HH:mm:ss"));
                helper.setText(R.id.message,item.getMessage());
            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);
        baseRecyclerAdapter.setOnRecyclerItemLongClickListener(new OnRecyclerItemLongClickListener() {
            @Override
            public boolean onItemLongClick(View view, final int position) {
                if (datas.get(position)!=null) {
                    CommonTipsDialog.create(MessageActivity.this, "温馨提示", "是否要回复" + datas.get(position).getSend_user_name() + "的信息", false)
                            .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                                @Override
                                public void cancel() {

                                }

                                @Override
                                public void confirm() {
                                    Intent intent = new Intent(MessageActivity.this, MessageSendActivity.class);
                                    intent.putExtra("receive_user_id", datas.get(position).getSend_user_id());
                                    intent.putExtra("receive_user_type", datas.get(position).getSend_user_type());
                                    startActivity(intent);
                                }
                            }).show();
                }
                return false;
            }
        });
        recyclerView.setAdapter(baseRecyclerAdapter);

        presenter = new MessagePresenter(this);
        presenter.setOnCallBack(new MessagePresenter.OnCallBack() {
            @Override
            public void getMineMessageMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    Log.e("getAllRecruit",isSuccess+object.toString()+"");
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                        List<MessageModel> messageModels = resultModel.getData();
                        datas = messageModels!=null?messageModels:datas;
                        baseRecyclerAdapter.setData(datas);
                        recyclerView.smoothScrollToPosition(0);
                    }
                }else{
                    Toast.makeText(MessageActivity.this, "服务器繁忙", Toast.LENGTH_SHORT).show();
                }
                checkData();
            }

            @Override
            public void addMessageMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void modifyMessageMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteMessageMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void checkIsCollect(boolean isSuccess, Object object) {

            }
        });

        getData();
    }

    void getData(){
        if (AccountTool.isLogin(this)) {
            Map<String, String> params = RS.getBaseParams(this);
            params.put("receive_user_id", AccountTool.getLoginUser(this).getId());
            presenter.getMineMessageMobile(params);
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
