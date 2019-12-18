package com.ellen.libcommon.base.adapter.viewpager;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.lang.ref.WeakReference;
import java.util.List;

public class SaveStatusPagerFragment extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;
    private WeakReference<AppCompatActivity> activityWeakReference;

    public SaveStatusPagerFragment(AppCompatActivity appCompatActivity,List<Fragment> fragmentPagerAdapterList){
        super(appCompatActivity.getSupportFragmentManager());
        this.activityWeakReference = new WeakReference<>(appCompatActivity);
        this.fragmentList = fragmentPagerAdapterList;
    }

    private SaveStatusPagerFragment(@NonNull FragmentManager fm) {
        super(fm);
    }

    private SaveStatusPagerFragment(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = fragmentList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("id","" + position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 将实例化的fragment进行显示即可。
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        activityWeakReference.get().getSupportFragmentManager().beginTransaction().show(fragment).commit();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);// 注释父类方法
        Fragment fragment = fragmentList.get(position);// 获取要销毁的fragment
        activityWeakReference.get().getSupportFragmentManager().beginTransaction().hide(fragment).commit();// 将其隐藏即可，并不需要真正销毁，这样fragment状态就得到了保存
    }
}
