package com.ellen.modnet;

import com.ellen.common.base.BaseSecondMvpFragment;

public class NetFragment extends BaseSecondMvpFragment<NetPresenter> implements NetAgreement.ALocalView {
    @Override
    protected void initMVP() {
       mFragmentPresenter = new NetPresenter();
       mFragmentPresenter.mModel = new NetModel();
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
        return R.layout.fragment_net;
    }
}
