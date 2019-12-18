package com.ellen.modlocal;

import android.content.Context;

import com.ellen.libcommon.util.ContentProviderUtils;

import java.util.List;

public class LocalModel implements LocalAgreement.ALocalModel {
    @Override
    public List<ContentProviderUtils.Music> getLocalMusics(Context context) {
        ContentProviderUtils contentProviderUtils = new ContentProviderUtils();
        return contentProviderUtils.getMusicPathList(context);
    }
}
