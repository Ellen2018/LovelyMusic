package com.ellen.libcommon.base.atuoview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

public abstract class BaseAutoViewGroup extends ViewGroup {

    protected View mContentView;

    public BaseAutoViewGroup(Context context) {
        super(context);
        initView();
    }

    public BaseAutoViewGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BaseAutoViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mContentView = LayoutInflater.from(getContext()).inflate(setLayoutId(), this);
        bindView(mContentView);
    }

    protected <T extends View> T findViewObjctById(int viewId){
        return mContentView.findViewById(viewId);
    }

    protected abstract int setLayoutId();

    protected abstract void bindView(View view);
}
