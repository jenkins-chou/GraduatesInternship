package com.jenking.graduatesinternship.activitys.common;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseActivity extends AppCompatActivity {

    //别名
    private String TAG;

    //类名
    String className ;
    private Unbinder unbinder;

    //下一步class
    public Class<?> nextStep;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBarColor();
        setBinder();
        initData();
        initView();
    }

    private void setStatusBarColor() {
//        StatusBarUtil.setColor(this,getResources().getColor(R.color.baseColor),0);
    }

    private void setBinder() {
        unbinder = ButterKnife.bind(this);
    }

    public void initData(){
        TAG = getClass().getCanonicalName();
        Intent intent = getIntent();
        if (intent!=null){
            nextStep = (Class<?>)intent.getSerializableExtra("nextStep");
        }
    }

    public void initView(){}

    public boolean checkResultModel(boolean isSuccess,Object object){
        boolean result = false;
//        if (isSuccess&&object!=null){
//            ResultModel resultModel = (ResultModel)object;
//            if (resultModel!=null&&resultModel.getStatus().equals("200")){
//                result = true;
//            }
//        }
        return result;
    }
}
