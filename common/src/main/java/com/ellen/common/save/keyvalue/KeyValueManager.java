package com.ellen.common.save.keyvalue;

import android.content.Context;

import com.ellen.libsave.keyvalue.BaseKeyValueImpl;

public class KeyValueManager {

    private static volatile KeyValueManager keyValueManager;
    private BaseKeyValueImpl keyValue;

    private KeyValueManager(){}

    public static KeyValueManager getInstance(Context context){
        if(keyValueManager == null){
            synchronized (KeyValueManager.class){
                if(keyValueManager == null){
                    keyValueManager = new KeyValueManager();
                    keyValueManager.keyValue = new MyKeyValueImpl(context, KeyValueSaveName.MMKV_FILE_NAME);
                }
            }
        }
        return keyValueManager;
    }

    public BaseKeyValueImpl getKeyValueImpl() {
        return keyValue;
    }

}
