package com.jenking.graduatesinternship.activitys.teacher;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.jenking.graduatesinternship.R;
import com.jenking.graduatesinternship.activitys.common.BaseActivity;
import com.jenking.graduatesinternship.fragments.student.StudentMainFragment1;
import com.jenking.graduatesinternship.fragments.student.StudentMainFragment2;
import com.jenking.graduatesinternship.fragments.student.StudentMainFragment3;
import com.jenking.graduatesinternship.fragments.teacher.TeacherMainFragment1;
import com.jenking.graduatesinternship.fragments.teacher.TeacherMainFragment2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TeacherMainActivity extends BaseActivity {

    private Context context;
    private Unbinder unbinder;

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottom_navigation_bar;
    @BindView(R.id.ly_content)
    FrameLayout fl_content;

    //Fragment Object
    private Fragment fg1,fg2;
    private FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_main);
    }


    public void initData(){
        fManager = getSupportFragmentManager();
    }
    public void initView(){
        ButterKnife.bind(this);
        bottom_navigation_bar
                .setActiveColor(R.color.colorAccent)
                .setInActiveColor(R.color.base_normal_navcolor)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
                .addItem(new BottomNavigationItem(R.mipmap.icon_student, "学生列表").setActiveColorResource(R.color.main_color))
                .addItem(new BottomNavigationItem(R.mipmap.icon_mine, "我的").setActiveColorResource(R.color.main_color))
                .setFirstSelectedPosition(0)
                .initialise();
        bottom_navigation_bar.setAutoHideEnabled(true);
        bottom_navigation_bar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.e("position",""+position);
                showFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
        showFragment(0);
    }

    void showFragment(int position){
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (position){
            case 0:
                if (fg1==null){
                    fg1 = new TeacherMainFragment1();
                    fTransaction.add(R.id.ly_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                break;
            case 1:
                if (fg2==null){
                    fg2 = new TeacherMainFragment2();
                    fTransaction.add(R.id.ly_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                break;
        }
        fTransaction.commit();
    }
    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (context==null)
                context = this;
//            CommonTipsDialog.create(context,"温馨提示","确定要退出吗",false)
//                    .setOnClickListener(new CommonTipsDialog.OnClickListener() {
//                        @Override
//                        public void cancel() {
//
//                        }
//
//                        @Override
//                        public void confirm() {
//                            finish();
//                        }
//                    }).show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
