package com.ellen.lovelymusic.mediaplay;

import java.util.List;

public abstract class MusicPlaySetting<T> {
    /**
     * 播放模式
     */
    private PlayMode playMode = PlayMode.XUN_HUAN;
    /**
     * 播放列表
     */
    private List<T> dataList;
    /**
     * 当前的播放的位置
     */
    private int currentPostion;

    public abstract String getPlayPath(T t);

    public PlayMode getPlayMode() {
        return playMode;
    }

    public void setPlayMode(PlayMode playMode) {
        this.playMode = playMode;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getCurrentPostion() {
        return currentPostion;
    }

    public void setCurrentPostion(int currentPostion) {
        this.currentPostion = currentPostion;
    }
}
