package com.ellen.libcommon.basemvp;

public class BasePresenter<M extends BaseModel,V extends BaseView> {
    public M mModel;
    public V mView;
}
