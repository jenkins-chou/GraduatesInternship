package com.jenking.graduatesinternship.activitys.common;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.student.StudentPersonalCertListActivity;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.presenter.MessagePresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class MessageSendActivity extends BaseActivity {

    private MessagePresenter messagePresenter;
    private String receive_user_id;
    private String receive_user_type;

    @BindView(R.id.contract)
    EditText contract;
    @BindView(R.id.message)
    EditText message;

    @OnClick(R.id.back)
    void back(){
        finish();
    }

    @OnClick(R.id.submit)
    void submit(){
        if (!AccountTool.isLogin(this))return;
        String contract_str = contract.getText().toString();
        String message_str = message.getText().toString();
        if (StringUtil.isNotEmpty(contract_str)
                &&StringUtil.isNotEmpty(message_str)
                &&StringUtil.isNotEmpty(receive_user_id)
                &&StringUtil.isNotEmpty(receive_user_type)){
            Map<String,String> params = RS.getBaseParams(this);
            params.put("send_user_id",AccountTool.getLoginUser(this).getId());
            params.put("send_user_type",AccountTool.getLoginUser(this).getType());
            params.put("send_user_name",AccountTool.getLoginUser(this).getRealname());
            params.put("send_user_contract",contract_str);
            params.put("receive_user_id",receive_user_id);
            params.put("receive_user_type",receive_user_type);
            params.put("message",message_str);

            Log.e("message",params.toString());
            messagePresenter.addMessageMobile(params);
        }else{

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_send);
    }

    @Override
    public void initData() {
        super.initData();
        receive_user_id = getIntent().getStringExtra("receive_user_id");
        receive_user_type = getIntent().getStringExtra("receive_user_type");

        messagePresenter = new MessagePresenter(this);
        messagePresenter.setOnCallBack(new MessagePresenter.OnCallBack() {
            @Override
            public void getMineMessageMobile(boolean isSuccess, Object object) {

            }

            @Override
            public void addMessageMobile(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel) object;
                    if (resultModel!=null&&StringUtil.isEquals(resultModel.getCode(),"200")){
                        Toast.makeText(MessageSendActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else{
                    Toast.makeText(MessageSendActivity.this, "系统繁忙，请重试", Toast.LENGTH_SHORT).show();
                }
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
    }
}
