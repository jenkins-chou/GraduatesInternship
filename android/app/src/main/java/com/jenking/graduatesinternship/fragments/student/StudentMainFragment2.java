package com.jenking.graduatesinternship.fragments.student;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jenking.graduatesinternship.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class StudentMainFragment2 extends Fragment{
    private Context context;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_main_part2,container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }
}
