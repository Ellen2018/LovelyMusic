package com.ellen.lovelymusic;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ellen.libcommon.base.BaseActivity;
import com.ellen.libcommon.base.adapter.viewpager.SaveStatusPagerFragment;
import com.ellen.libcommon.util.statusutil.StatusUtils;
import com.ellen.modlocal.LocalFragment;
import com.ellen.modme.MeFragment;
import com.ellen.modnet.NetFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements BaseActivity.ButterKnifeInterface {

    @BindView(R.id.iv_left_menu)
    ImageView ivLeftMenu;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_menu_1)
    TextView tvMenu1;
    @BindView(R.id.tv_menu_2)
    TextView tvMenu2;
    @BindView(R.id.tv_menu_3)
    TextView tvMenu3;

    private Fragment currentFragment;
    private List<Fragment> fragmentList;

    @OnClick({R.id.tv_menu_1,R.id.tv_menu_2,R.id.tv_menu_3})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tv_menu_1:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tv_menu_2:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tv_menu_3:
                viewPager.setCurrentItem(2);
                break;
        }
    }

    private final int MOD_LOCAL = 0,MOD_NET = 1,MOD_ME = 2;

    @Override
    protected void setStatus() {
        StatusUtils.setNoActionBar(this);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        tvMenu1.setText("本地");
        tvMenu2.setText("网络");
        tvMenu3.setText("我");
        fragmentList = new ArrayList<>();
        fragmentList.add(new LocalFragment());
        fragmentList.add(new NetFragment());
        fragmentList.add(new MeFragment());
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
               switch (position){
                   case MOD_LOCAL:
                       tvMenu1.setTextColor(Color.RED);
                       tvMenu2.setTextColor(Color.BLACK);
                       tvMenu3.setTextColor(Color.BLACK);
                       break;
                   case MOD_NET:
                       tvMenu1.setTextColor(Color.BLACK);
                       tvMenu2.setTextColor(Color.RED);
                       tvMenu3.setTextColor(Color.BLACK);
                       break;
                   case MOD_ME:
                       tvMenu1.setTextColor(Color.BLACK);
                       tvMenu2.setTextColor(Color.BLACK);
                       tvMenu3.setTextColor(Color.RED);
                       break;
               }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setAdapter(new SaveStatusPagerFragment(getActivity(),fragmentList));
        tvMenu1.setTextColor(Color.RED);
        tvMenu2.setTextColor(Color.BLACK);
        tvMenu3.setTextColor(Color.BLACK);
    }

    @Override
    protected void destory() {

    }

    @Override
    protected Boolean isSetVerticalScreen() {
        return true;
    }

    @Override
    public void initButterKnife() {
        ButterKnife.bind(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        currentFragment.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
