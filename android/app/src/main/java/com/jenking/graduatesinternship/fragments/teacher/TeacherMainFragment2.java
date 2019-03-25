package com.jenking.graduatesinternship.fragments.teacher;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.LoginActivity;
import com.jenking.graduatesinternship.activitys.common.SettingActivity;
import com.jenking.graduatesinternship.activitys.common.UserInfoActivity;
import com.jenking.graduatesinternship.activitys.common.UserInfoAvatarActivity;
import com.jenking.graduatesinternship.activitys.student.StudentDataActivity;
import com.jenking.graduatesinternship.api.BaseAPI;
import com.jenking.graduatesinternship.dialog.CommonTipsDialog;
import com.jenking.graduatesinternship.models.impl.UserModel;
import com.jenking.graduatesinternship.utils.AccountTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TeacherMainFragment2 extends Fragment{
    private UserModel userModel;
    private Context context;
    private Unbinder unbinder;

    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.username)
    TextView username;

    @OnClick(R.id.avatar)
    void modifyAvatar(){
        if (checkLogin()) {
            Intent intent = new Intent(getContext(), UserInfoAvatarActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.user_info)
    void user_info(){
        if (checkLogin()) {
            Intent intent = new Intent(getContext(),UserInfoActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.setting)
    void setting(){
        Intent intent = new Intent(getContext(),SettingActivity.class);
        startActivity(intent);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_main_part2,container,false);
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

    boolean checkLogin(){
        if (AccountTool.isLogin(getContext())){
            return true;
        }else{
            CommonTipsDialog.create(getContext(),"温馨提示","请登录后重试",false)
                    .setOnClickListener(new CommonTipsDialog.OnClickListener() {
                        @Override
                        public void cancel() {

                        }

                        @Override
                        public void confirm() {
                            startActivity(new Intent(getContext(),LoginActivity.class));
                            getActivity().finish();
                        }
                    }).show();
            return false;
        }
    }
}
