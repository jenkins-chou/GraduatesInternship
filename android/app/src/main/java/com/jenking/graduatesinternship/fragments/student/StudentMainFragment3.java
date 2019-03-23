package com.jenking.graduatesinternship.fragments.student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.LoginActivity;
import com.jenking.graduatesinternship.activitys.common.MessageActivity;
import com.jenking.graduatesinternship.activitys.common.SettingActivity;
import com.jenking.graduatesinternship.activitys.common.UserInfoAvatarActivity;
import com.jenking.graduatesinternship.activitys.student.StudentDataActivity;
import com.jenking.graduatesinternship.activitys.student.StudentRecruitCollectionActivity;
import com.jenking.graduatesinternship.activitys.student.StudentRecruitDeliveryActivity;
import com.jenking.graduatesinternship.activitys.student.StudentResumeListActivity;
import com.jenking.graduatesinternship.api.BaseAPI;
import com.jenking.graduatesinternship.models.impl.UserModel;
import com.jenking.graduatesinternship.utils.AccountTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class StudentMainFragment3 extends Fragment{
    private UserModel userModel;
    private Unbinder unbinder;

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.username)
    TextView username;

    @OnClick(R.id.message)
    void showMessage(){
        Intent intent = new Intent(getContext(), MessageActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.avatar)
    void modifyAvatar(){
        if (AccountTool.isLogin(getContext())) {
            Intent intent = new Intent(getContext(), UserInfoAvatarActivity.class);
            startActivity(intent);
        }
    }
    @OnClick({R.id.user_header})
    void user_header(){
        if (!AccountTool.isLogin(getContext())) {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.user_info)
    void user_info(){
        if (AccountTool.isLogin(getContext())) {
            Intent intent = new Intent(getContext(),StudentDataActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getContext(), "请登录后重试", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.user_resume)
    void user_resume(){
        Intent intent = new Intent(getContext(),StudentResumeListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.mine_recruitment_delivery)
    void mine_recruitment_delivery(){
        Intent intent = new Intent(getContext(), StudentRecruitDeliveryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.mine_recruitment_collection)
    void mine_recruitment_collection(){
        Intent intent = new Intent(getContext(), StudentRecruitCollectionActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.setting)
    void setting(){
        Intent intent = new Intent(getContext(),SettingActivity.class);
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_main_part3,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (AccountTool.isLogin(getContext())){
            userModel = AccountTool.getLoginUser(getContext());
            if (userModel!=null){
                username.setText(userModel.getUseridentify());
                RequestOptions requestOptions = new RequestOptions();
                requestOptions.circleCrop();
                requestOptions.error(R.mipmap.avatar_default);
                requestOptions.placeholder(R.mipmap.avatar_default);
                Glide.with(getContext()).load(BaseAPI.base_url+userModel.getAvatar()).apply(requestOptions).into(avatar);
            }
        }else{
            username.setText("请登录");
            Glide.with(getContext()).load(R.mipmap.avatar_default).into(avatar);
        }
    }
}
