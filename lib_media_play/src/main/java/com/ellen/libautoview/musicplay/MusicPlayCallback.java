package com.ellen.libautoview.musicplay;

public interface MusicPlayCallback {

    /**
     * 点击了播放按钮
     */
    void clickPlay();

    /**
     * 上一曲
     */
    void prePiece();

    /**
     * 下一曲
     */
    void nextPiece();

    /**
     * 点击了播放列表
     */
    void clickMusicList();

    /**
     * 播放指定位置歌曲
     * @param position
     */
    void playPosition(int position);

    /**
     * 点击了播放面板
     */
    void clickMusicPlayer();

}
