package com.example.guohaifang.elderlysocial;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;


import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private HomeFragment homeFragment;
    private SecondFragment secondFragment;
    private  ThirdFragment thirdFragment;
    private ForthFragment forthFragment;
    private  FifthFragment fifthFragment;

    private Fragment nowFragment;//当前显示的Fragment
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.home, "活动"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.earth, "讲述"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.message, "交流"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.video, "消息"));
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.person, "我"));
        bottomNavigationBar.setFirstSelectedPosition(0);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar.setActiveColor(R.color.gray)//设置Item选中颜色方法
                .setInActiveColor(R.color.lightGray)//设置Item未选中颜色方法
                .setBarBackgroundColor(R.color.colorAccent)//背景颜色
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch(position) {
                    case 0:
                        switchFragment(homeFragment);
                        break;
                    case 1:
                        switchFragment(secondFragment);
                        break;
                    case 2:
                        switchFragment(thirdFragment);
                        break;
                    case 3:
                        switchFragment(forthFragment);
                        break;
                    case 4:
                        switchFragment(fifthFragment);
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        initFragment();

    }
    private void initFragment() {
        homeFragment = new HomeFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();
        forthFragment = new ForthFragment();
        fifthFragment = new FifthFragment();

        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fram_layout, homeFragment)
                .commit();
        nowFragment = homeFragment;
    }

    private void switchFragment(Fragment fragment) {
        //判断当前显示的Fragment是不是切换的Fragment
        if(nowFragment != fragment) {
            //判断切换的Fragment是否已经添加过
            if (!fragment.isAdded()) {
                //如果没有，则先把当前的Fragment隐藏，把切换的Fragment添加上
                getSupportFragmentManager().beginTransaction().hide(nowFragment)
                        .add(R.id.fram_layout,fragment).commit();
            } else {
                //如果已经添加过，则先把当前的Fragment隐藏，把切换的Fragment显示出来
                getSupportFragmentManager().beginTransaction().hide(nowFragment).show(fragment).commit();
            }
            nowFragment = fragment;
        }
    }
}
