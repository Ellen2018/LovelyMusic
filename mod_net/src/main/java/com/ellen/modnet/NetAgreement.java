package com.ellen.modnet;

import com.ellen.libcommon.basemvp.BaseModel;
import com.ellen.libcommon.basemvp.BasePresenter;
import com.ellen.libcommon.basemvp.BaseView;

public interface NetAgreement {

    interface ALocalModel extends BaseModel{}

    interface ALocalView extends BaseView{}

    abstract class ALocalPresenter extends BasePresenter<ALocalModel,ALocalView>{}

}
