package com.ellen.modlocal;

import com.ellen.common.base.BaseSecondMvpFragment;

public class LocalFragment extends BaseSecondMvpFragment<LocalPresenter> implements LocalAgreement.ALocalView {
    @Override
    protected void initMVP() {
       mFragmentPresenter = new LocalPresenter();
       mFragmentPresenter.mModel = new LocalModel();
       mFragmentPresenter.mView = this;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_local;
    }
}
