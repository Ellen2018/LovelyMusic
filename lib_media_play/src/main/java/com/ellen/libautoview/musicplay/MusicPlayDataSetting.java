package com.ellen.libautoview.musicplay;

import java.util.List;

public abstract class MusicPlayDataSetting<T> {

    private List<T> dataList;
    private int currentPlayPosition;

    public int getCurrentPlayPosition() {
        return currentPlayPosition;
    }

    public void setCurrentPlayPosition(int currentPlayPosition) {
        this.currentPlayPosition = currentPlayPosition;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public List<T> getDataList() {
        return dataList;
    }

    /**
     * 获取歌手名
     * @param t
     * @return
     */
    public abstract String getSingerName(T t);

    /**
     * 获取音乐名
     * @param t
     * @return
     */
    public abstract String getMusicName(T t);

    /**
     * 获取歌曲时长
     * @param t
     * @return
     */
    public abstract long getMusicTime(T t);

    /**
     * 获取歌曲播放路径
     */
    public abstract String getPath(T t);

}
