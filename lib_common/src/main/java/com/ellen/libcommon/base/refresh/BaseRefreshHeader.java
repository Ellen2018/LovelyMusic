package com.ellen.libcommon.base.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

public abstract class BaseRefreshHeader extends RelativeLayout implements RefreshHeader {

    protected View mContentView;

    public BaseRefreshHeader(Context context) {
        super(context);
        initView();
    }

    public BaseRefreshHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public BaseRefreshHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        if (newState.isDragging && !newState.isReleaseToOpening) {
            //单纯的拖动状态
            draggingStatus();
        }

        if (newState.isDragging && newState.isReleaseToOpening) {
            //释放立马刷新状态
            releaseRefreshStatus();
        }

        if (newState.isOpening) {
            //刷新状态
            freshingStatus();
        }

        if (newState.isFinishing) {
            //完成状态
            finishRefreshStatus();
        }
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

    protected void initView(){
        mContentView = LayoutInflater.from(getContext()).inflate(getLayoutId(),this);
        bindView(mContentView);
    }

    public  <T extends View> T findViewObjectById(int viewId){
        return mContentView.findViewById(viewId);
    }

    protected abstract int getLayoutId();
    protected abstract void bindView(View view);
    protected abstract void draggingStatus();
    protected abstract void releaseRefreshStatus();
    protected abstract void freshingStatus();
    protected abstract void finishRefreshStatus();
}
