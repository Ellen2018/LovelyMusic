package com.ellen.common.save.keyvalue;

import android.content.Context;

import com.ellen.libsave.keyvalue.BaseAbstractKeyValueHelper;
import com.ellen.libsave.keyvalue.BaseKeyValueImpl;
import com.ellen.libsave.keyvalue.MMKVHelper;

public class MyKeyValueImpl extends BaseKeyValueImpl {

    public MyKeyValueImpl(Context context, String name) {
        super(context, name);
    }

    @Override
    protected BaseAbstractKeyValueHelper getKeyValueImapl(Context context, String fileName) {
        //后期只用改这里即可任性切换其它的存储方式

        //使用SharePreference的方式进行key-value存储
        //BaseAbstractKeyValueHelper baseAbstractKeyValueHelper = new SharePreferenceHelper(context,fileName);
        //使用MMKV的方式进行key-value存储
        BaseAbstractKeyValueHelper baseAbstractKeyValueHelper = new MMKVHelper(fileName);
        return baseAbstractKeyValueHelper;
    }
}
