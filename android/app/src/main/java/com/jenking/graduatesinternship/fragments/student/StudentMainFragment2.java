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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnRecyclerItemClickListener;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.RecruitDetailActivity;
import com.jenking.graduatesinternship.activitys.student.StudentInternShipDetailActivity;
import com.jenking.graduatesinternship.models.impl.InternshipExperienceModel;
import com.jenking.graduatesinternship.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StudentMainFragment2 extends Fragment{
    private Context context;
    private Unbinder unbinder;

    private BaseRecyclerAdapter baseRecyclerAdapter;
    private List<InternshipExperienceModel> datas;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

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
        datas.add(new InternshipExperienceModel("1"));
        datas.add(new InternshipExperienceModel("2"));
        datas.add(new InternshipExperienceModel("3"));
        datas.add(new InternshipExperienceModel("4"));
        datas.add(new InternshipExperienceModel("5"));
        datas.add(new InternshipExperienceModel("6"));
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
            }
        };
        baseRecyclerAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), StudentInternShipDetailActivity.class);
                startActivity(intent);
            }
        });
        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setAdapter(baseRecyclerAdapter);
    }
}
