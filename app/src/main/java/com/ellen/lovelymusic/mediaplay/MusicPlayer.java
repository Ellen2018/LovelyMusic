package com.ellen.lovelymusic.mediaplay;

import android.media.MediaPlayer;

import java.io.IOException;

public class MusicPlayer<T> {

    MusicPlaySetting<T> musicPlaySetting;
    private MediaPlayer mediaPlayer;
    private static MusicPlayer musicPlayer;

    private MusicPlayer() {
    }

    public static MusicPlayer getInstance() {
        if (musicPlayer == null) {
            synchronized (MusicPlayer.class) {
                if (musicPlayer == null) {
                    musicPlayer = new MusicPlayer();
                    musicPlayer.mediaPlayer = new MediaPlayer();
                }
            }
        }
        return musicPlayer;
    }

    public void setMusicPlaySetting(MusicPlaySetting<T> musicPlaySetting) {
        this.musicPlaySetting = musicPlaySetting;
        startPlay();
    }

    private void startPlay() {
        //重置MediaPlay
        mediaPlayer.reset();
        T t = musicPlaySetting.getDataList().get(musicPlaySetting.getCurrentPostion());
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                next();
                return true;// 返回true表示在此处理错误，不会回调onCompletion
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                next();
            }
        });
        try {
            mediaPlayer.setDataSource(musicPlaySetting.getPlayPath(t));
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放下一曲
     */
    public void next() {
        if (musicPlaySetting.getPlayMode() == PlayMode.SUI_JI) {
            //随机播放
            int nextPosition = (int) (Math.random()*musicPlaySetting.getDataList().size());
            musicPlaySetting.setCurrentPostion(nextPosition);
        } else {
            int nextPosition = musicPlaySetting.getCurrentPostion() + 1;
            if(nextPosition == musicPlaySetting.getDataList().size()){
                nextPosition = 0;
            }
            musicPlaySetting.setCurrentPostion(nextPosition);
        }
        startPlay();
    }

}
