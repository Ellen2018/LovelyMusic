package com.ellen.libcommon.basemvp.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ellen.libcommon.basemvp.BasePresenter;

/**
 * 建议再封装一层自己的
 * @param <P>
 */
public abstract class BaseAppCompatMvpActivity<P extends BasePresenter> extends AppCompatActivity {
    protected P mActivityPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMvp();
    }

    protected abstract void initMvp();

}
