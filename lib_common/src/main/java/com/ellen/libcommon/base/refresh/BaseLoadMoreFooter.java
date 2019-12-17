package com.ellen.libcommon.base.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

public abstract class BaseLoadMoreFooter extends RelativeLayout implements RefreshFooter {

    protected View mContentView;

    public BaseLoadMoreFooter(Context context) {
        super(context);
        initView();
    }

    public BaseLoadMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BaseLoadMoreFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    protected void initView(){
        mContentView = LayoutInflater.from(getContext()).inflate(setLayoutId(),this);
        bindView(mContentView);
    }

    public  <T extends View> T findViewObjectById(int viewId){
        return mContentView.findViewById(viewId);
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        return 0;
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {

    }

    protected abstract int setLayoutId();
    protected abstract void bindView(View view);
}
