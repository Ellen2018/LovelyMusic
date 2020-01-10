package com.ellen.libautoview.musicplay;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ellen.libautoview.R;
import com.ellen.libcommon.base.adapter.viewpager.BaseFragmentStateAdapter;

public class MusicPlayView<T> extends RelativeLayout {

    private MusicPlayCallback musicPlayCallback;
    private MusicPlayDataSetting<T> musicPlayDataSetting;
    private View mContentView;

    private ViewPager viewPager;
    private ImageView ivPlay, ivNext;

    public MusicPlayView(Context context) {
        super(context);
        initView();
    }

    public MusicPlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MusicPlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        mContentView = LayoutInflater.from(getContext()).inflate(R.layout.view_music_play, this);
        viewPager = mContentView.findViewById(R.id.vp_music);
        ivPlay = mContentView.findViewById(R.id.iv_play);
        ivNext = mContentView.findViewById(R.id.iv_next);
        initListener();
    }

    private void initListener() {
        ivNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayCallback != null)
                    if (viewPager.getCurrentItem() == musicPlayDataSetting.getDataList().size() - 1) {
                        viewPager.setCurrentItem(0);
                    } else {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                musicPlayCallback.nextPiece();
            }
        });
        ivPlay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayCallback != null)
                    musicPlayCallback.clickMusicList();
            }
        });
        mContentView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (musicPlayCallback != null)
                    musicPlayCallback.clickMusicPlayer();
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (musicPlayCallback != null)
                    musicPlayCallback.playPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public MusicPlayCallback getMusicPlayCallback() {
        return musicPlayCallback;
    }

    public void setMusicPlayCallback(MusicPlayCallback musicPlayCallback) {
        this.musicPlayCallback = musicPlayCallback;
    }

    public void setMusicPlayDataSetting(AppCompatActivity appCompatActivity, MusicPlayDataSetting<T> musicPlayDataSetting) {
        this.musicPlayDataSetting = musicPlayDataSetting;
        viewPager.setAdapter(new BaseFragmentStateAdapter(appCompatActivity.getSupportFragmentManager()) {
            @Override
            protected int getFragmentPagerSize() {
                return musicPlayDataSetting.getDataList().size();
            }

            @Override
            protected Fragment getFragment(int position) {
                MusicMessageFragment fragment = new MusicMessageFragment();
                Bundle bundle = new Bundle();
                T t = musicPlayDataSetting.getDataList().get(position);
                bundle.putString("music_name", musicPlayDataSetting.getMusicName(t));
                bundle.putString("music_singer_name", musicPlayDataSetting.getSingerName(t));
                fragment.setArguments(bundle);
                fragment.setMusicPlayCallback(musicPlayCallback);
                return fragment;
            }
        });
        viewPager.setCurrentItem(musicPlayDataSetting.getCurrentPlayPosition());
    }

    public MusicPlayDataSetting<T> getMusicPlayDataSetting() {
        return musicPlayDataSetting;
    }
}
