package com.ellen.libsave.keyvalue;

/**
 * SharedPreference或者MMKV的抽象层
 */
public abstract class BaseAbstractKeyValueHelper {

    private String name;

    public BaseAbstractKeyValueHelper(String name){
        this.name = name;
    }

    //存储获取非加密数据
    public abstract void save(String key, Object value);
    public abstract <T> T getValue(String key, T defaultValue);
    public abstract void deleteKey(String key);

    //存储获取加密数据
    public abstract void safeSave(String encryptionString, String key, Object value);
    public abstract <T> T safeGetValue(String encryptionString, String key, T defaultValue);
}
