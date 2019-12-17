package com.ellen.modme;

import com.ellen.common.base.BaseSecondMvpFragment;

public class MeFragment extends BaseSecondMvpFragment<MePresenter> implements MeAgreement.ALocalView {
    @Override
    protected void initMVP() {
       mFragmentPresenter = new MePresenter();
       mFragmentPresenter.mModel = new MeModel();
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
        return R.layout.fragment_me;
    }
}
