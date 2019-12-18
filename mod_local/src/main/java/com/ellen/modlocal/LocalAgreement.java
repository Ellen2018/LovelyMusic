package com.ellen.modlocal;

import android.content.Context;

import com.ellen.libcommon.basemvp.BaseModel;
import com.ellen.libcommon.basemvp.BasePresenter;
import com.ellen.libcommon.basemvp.BaseView;
import com.ellen.libcommon.util.ContentProviderUtils;

import java.util.List;

public interface LocalAgreement {

    interface ALocalModel extends BaseModel {
        List<ContentProviderUtils.Music> getLocalMusics(Context context);
    }

    interface ALocalView extends BaseView {
        void getLocalMusicSuccess(List<ContentProviderUtils.Music> musicList);

        void getLocalMusicFailure(String errMessage);
    }

    abstract class ALocalPresenter extends BasePresenter<ALocalModel, ALocalView> {
        abstract void getLocalMusics(Context context);
    }

}
