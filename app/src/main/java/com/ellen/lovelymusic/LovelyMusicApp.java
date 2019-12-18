package com.ellen.lovelymusic;

import com.ellen.libcommon.base.BaseApplication;
import com.tencent.mmkv.MMKV;


public class LovelyMusicApp extends BaseApplication {

    @Override
    protected Boolean isListenerActivity() {
        return true;
    }

    @Override
    protected void initLibraySetting() {
        MMKV.initialize(this);
    }
}
