package com.ellen.common.base;

import com.ellen.libcommon.base.BaseActivity;
import com.ellen.libcommon.basemvp.BasePresenter;
import com.ellen.libcommon.basemvp.activity.BaseMvpActivity;

import butterknife.ButterKnife;

public abstract class BaseSecondMvpActivity<P extends BasePresenter> extends BaseMvpActivity<P> implements BaseActivity.ButterKnifeInterface {

    @Override
    public void initButterKnife() {
        ButterKnife.bind(this);
    }
}
