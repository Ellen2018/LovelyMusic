package com.ellen.libcommon.basemvp.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.ellen.libcommon.base.BaseActivity;
import com.ellen.libcommon.basemvp.BasePresenter;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P mActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMvp();
    }

    protected abstract void initMvp();

}
