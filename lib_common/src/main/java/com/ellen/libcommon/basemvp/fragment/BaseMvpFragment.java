package com.ellen.libcommon.basemvp.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ellen.libcommon.base.BaseFragment;
import com.ellen.libcommon.basemvp.BasePresenter;

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P mFragmentPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMVP();
    }

    protected abstract void initMVP();
}
