package com.ellen.common.base;

import android.view.View;

import com.ellen.libcommon.base.BaseFragment;
import com.ellen.libcommon.basemvp.BasePresenter;
import com.ellen.libcommon.basemvp.fragment.BaseMvpFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseSecondMvpFragment<P extends BasePresenter> extends BaseMvpFragment<P> implements BaseFragment.ButterKnifeInterface {

    private Unbinder unbinder;

    @Override
    public void initButterKnife(View view) {
        unbinder = ButterKnife.bind(this,view);
    }

    @Override
    public void unBindButterKnife() {
        unbinder.unbind();
    }


}
