package com.jenking.graduatesinternship.fragments.teacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.google.gson.Gson;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.student.StudentTutorActivity;
import com.jenking.graduatesinternship.activitys.teacher.ShowStudentDataActivity;
import com.jenking.graduatesinternship.api.BaseAPI;
import com.jenking.graduatesinternship.api.RS;
import com.jenking.graduatesinternship.models.base.ResultModel;
import com.jenking.graduatesinternship.models.impl.StudentTutorModel;
import com.jenking.graduatesinternship.models.impl.UserModel;
import com.jenking.graduatesinternship.presenter.StudentTutorPresenter;
import com.jenking.graduatesinternship.utils.AccountTool;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TeacherMainFragment1 extends Fragment{
    private List<UserModel> userModels;
    private BaseRecyclerAdapter adapter;
    private StudentTutorPresenter presenter;
    private Context context;
    private Unbinder unbinder;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacher_main_part1,container,false);
        unbinder = ButterKnife.bind(this,view);
        initData();
        return view;
    }

    private void initData(){
        context = getContext();
        userModels = new ArrayList<>();
        adapter = new BaseRecyclerAdapter<UserModel>(context,userModels,R.layout.fragment_teacher_main_part1_item) {
            @Override
            protected void convert(BaseViewHolder helper, UserModel item) {
                ImageView item_image = helper.getView(R.id.item_image);
                RequestOptions options = new RequestOptions();
                options.error(R.mipmap.avatar_default);
                options.placeholder(R.mipmap.avatar_default);
                Glide.with(context).load(BaseAPI.base_url+item.getAvatar()).apply(options).into(item_image);

                helper.setText(R.id.user_name,StringUtil.isNotEmpty(item.getRealname())?item.getRealname():"暂未录入姓名");
                helper.setText(R.id.user_identity,"学号:"+item.getUseridentify());
                helper.setText(R.id.user_class,StringUtil.isNotEmpty(item.getClass_name())?item.getClass_name():"暂未录入班级");
            }
        };
        adapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (userModels.get(position)!=null){
                    Intent intent = new Intent(getContext(), ShowStudentDataActivity.class);
                    intent.putExtra("model",new Gson().toJson(userModels.get(position)));
                    intent.putExtra("receive_user_id",userModels.get(position).getId());
                    intent.putExtra("receive_user_type",userModels.get(position).getType());
                    startActivity(intent);
                }
            }
        });
        adapter.openLoadAnimation(false);
        presenter = new StudentTutorPresenter(getContext());
        presenter.setOnCallBack(new StudentTutorPresenter.OnCallBack() {
            @Override
            public void getStudentTutor(boolean isSuccess, Object object) {

            }

            @Override
            public void addStudentTutor(boolean isSuccess, Object object) {

            }

            @Override
            public void deleteStudentTutor(boolean isSuccess, Object object) {

            }

            @Override
            public void updateStudentTutor(boolean isSuccess, Object object) {

            }

            @Override
            public void getTeachersStudents(boolean isSuccess, Object object) {
                if (isSuccess){
                    ResultModel resultModel = (ResultModel)object;
                    if (resultModel!=null&& StringUtil.isEquals(resultModel.getCode(),"200")){
                        List<UserModel> models = resultModel.getData();
                        if (models!=null&&models.size()>0){
                            Log.e("getTeachersStudents",models.toString());
                            userModels = models;
                            adapter.setData(userModels);
                        }
                    }
                }else{
                    Toast.makeText(getContext(), "服务器繁忙", Toast.LENGTH_SHORT).show();
                }
            }
        });

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData(){
        if (AccountTool.isLogin(getContext())&&presenter!=null){
            Map<String,String> params = RS.getBaseParams(getContext());
            params.put("teacher_useridentify",AccountTool.getLoginUser(getContext()).getUseridentify());
            presenter.getTeachersStudents(params);
        }
    }
}
