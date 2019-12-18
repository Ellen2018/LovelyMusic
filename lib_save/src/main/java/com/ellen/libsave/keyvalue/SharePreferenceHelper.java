package com.ellen.libsave.keyvalue;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceHelper extends BaseAbstractKeyValueHelper {

    private SharedPreferences sharedPreferences;
    /*
     * 保存手机里面的名字
     */
    private SharedPreferences.Editor editor;

    public SharePreferenceHelper(Context context, String fileName) {
        super(fileName);
        sharedPreferences = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public void save(String key, Object value) {
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, value.toString());
        }
        editor.commit();
    }

    @Override
    public <T> T getValue(String key, T defaultObject) {
        Object objectValue;
        if (defaultObject instanceof String) {
            objectValue = sharedPreferences.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            objectValue = sharedPreferences.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            objectValue = sharedPreferences.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            objectValue = sharedPreferences.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            objectValue = sharedPreferences.getLong(key, (Long) defaultObject);
        } else {
            objectValue = sharedPreferences.getString(key, null);
        }
        return (T) objectValue;
    }

    @Override
    public void deleteKey(String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    @Override
    public void safeSave(String encryptionString, String key, Object value) {
        save(encryptionString + key, value);
    }

    @Override
    public <T> T safeGetValue(String encryptionString, String key, T defaultObject) {
        String newKey = encryptionString + key;
        return getValue(newKey, defaultObject);
    }
}
