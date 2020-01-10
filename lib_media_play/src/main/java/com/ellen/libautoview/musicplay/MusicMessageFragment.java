package com.ellen.libautoview.musicplay;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ellen.libautoview.R;
import com.ellen.libcommon.base.BaseFragment;

public class MusicMessageFragment extends BaseFragment {

    private ImageView ivMusicIcon;
    private TextView tvMusicName,tvSingerName;
    private MusicPlayCallback musicPlayCallback;

    private String musicName,singerName;

    public void setMusicPlayCallback(MusicPlayCallback musicPlayCallback) {
        this.musicPlayCallback = musicPlayCallback;
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        musicName = bundle.getString("music_name");
        singerName = bundle.getString("music_singer_name");
        tvMusicName.setText(musicName);
        tvSingerName.setText(singerName);
        tvSingerName.setSelected(true);
        tvMusicName.setSelected(true);
    }

    @Override
    protected void initView() {
       ivMusicIcon = findViewById(R.id.iv_music_icon);
       tvMusicName = findViewById(R.id.tv_music_name);
       tvSingerName = findViewById(R.id.tv_singer_name);
       mContentView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(musicPlayCallback != null){
                   musicPlayCallback.clickMusicPlayer();
               }
           }
       });
    }

    @Override
    protected int setLayout() {
        return R.layout.fragment_music_message;
    }
}
