package com.jenking.graduatesinternship.fragments.student;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.library.BaseRecyclerAdapter;
import com.github.library.BaseViewHolder;
import com.jenking.graduatesinternship.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StudentMainFragment1 extends Fragment {
    private Context context;
    private Unbinder unbinder;
    private BaseRecyclerAdapter baseRecyclerAdapter;
    private List<String> datas;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_main_part1,container,false);
        unbinder = ButterKnife.bind(this,view);
        initData();
        return view;
    }

    private void initData(){
        datas = new ArrayList<>();
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        datas.add("");
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,1));
        baseRecyclerAdapter = new BaseRecyclerAdapter<String>(this.getContext(),datas,R.layout.fragment_student_main_part1_item) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {

            }
        };
        baseRecyclerAdapter.openLoadAnimation(false);
        recyclerView.setAdapter(baseRecyclerAdapter);
    }
}
