package com.ellen.modme;

import com.ellen.libcommon.basemvp.BaseModel;
import com.ellen.libcommon.basemvp.BasePresenter;
import com.ellen.libcommon.basemvp.BaseView;

public interface MeAgreement {

    interface ALocalModel extends BaseModel{}

    interface ALocalView extends BaseView{}

    abstract class ALocalPresenter extends BasePresenter<ALocalModel,ALocalView>{}

}
