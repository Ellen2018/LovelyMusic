package com.ellen.libsave.keyvalue;

import android.content.Context;

public abstract class BaseKeyValueImpl extends BaseAbstractKeyValueHelper {

    private BaseAbstractKeyValueHelper baseAbstractKeyValueHelper;

    public BaseKeyValueImpl(Context context, String name) {
        super(name);
        baseAbstractKeyValueHelper = getKeyValueImapl(context,name);
    }

    protected abstract BaseAbstractKeyValueHelper getKeyValueImapl(Context context, String fileName);

    @Override
    public void save(String key, Object value) {
        baseAbstractKeyValueHelper.save(key,value);
    }

    @Override
    public <T> T getValue(String key, T defaultValue) {
        return baseAbstractKeyValueHelper.getValue(key,defaultValue);
    }

    @Override
    public void deleteKey(String key) {
      baseAbstractKeyValueHelper.deleteKey(key);
    }

    @Override
    public void safeSave(String encryptionString, String key, Object value) {
         baseAbstractKeyValueHelper.safeSave(encryptionString,key,value);
    }

    @Override
    public <T> T safeGetValue(String encryptionString, String key, T defaultValue) {
        return baseAbstractKeyValueHelper.safeGetValue(encryptionString,key,defaultValue);
    }
}
