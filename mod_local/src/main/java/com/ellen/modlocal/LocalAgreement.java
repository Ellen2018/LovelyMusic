package com.ellen.modlocal;

import com.ellen.libcommon.basemvp.BaseModel;
import com.ellen.libcommon.basemvp.BasePresenter;
import com.ellen.libcommon.basemvp.BaseView;

public interface LocalAgreement {

    interface ALocalModel extends BaseModel{}

    interface ALocalView extends BaseView{}

    abstract class ALocalPresenter extends BasePresenter<ALocalModel,ALocalView>{}

}
