package com.ellen.libsave.keyvalue;

import com.tencent.mmkv.MMKV;

public class MMKVHelper extends BaseAbstractKeyValueHelper {

    private MMKV mmkv;

    public MMKVHelper(String name) {
        super(name);
        mmkv = MMKV.mmkvWithID(name);
    }

    @Override
    public void save(String key, Object value) {
        if (value instanceof String) {
            mmkv.encode(key,(String)value);
        } else if (value instanceof Integer) {
            mmkv.encode(key,(Integer) value);
        } else if (value instanceof Boolean) {
            mmkv.encode(key,(Boolean) value);
        } else if (value instanceof Float) {
            mmkv.encode(key,(Float) value);
        } else if (value instanceof Long) {
            mmkv.encode(key,(Long) value);
        } else {
            mmkv.encode(key,(String)value);
        }
    }

    @Override
    public <T> T getValue(String key, T defaultValue) {
        Object value;
        if (defaultValue instanceof String) {
            value = mmkv.decodeString(key);
            if(value == null) value = defaultValue;
        } else if (defaultValue instanceof Integer) {
            value = mmkv.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Boolean) {
            value = mmkv.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Float) {
            value = mmkv.getFloat(key, (Float) defaultValue );
        } else if (defaultValue instanceof Long) {
            value = mmkv.getLong(key, (Long) defaultValue);
        } else {
            value = mmkv.getString(key, (String) defaultValue);
        }
        return (T)value;
    }

    @Override
    public void deleteKey(String key) {
        mmkv.removeValueForKey(key);
    }

    @Override
    public void safeSave(String encryptionString, String key, Object value) {
         save(encryptionString + key,value);
    }

    @Override
    public <T> T safeGetValue(String encryptionString, String key, T defaultValue) {
        return getValue(encryptionString + key,defaultValue);
    }
}
