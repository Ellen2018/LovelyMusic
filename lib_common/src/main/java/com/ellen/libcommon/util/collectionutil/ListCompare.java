package com.ellen.libcommon.util.collectionutil;

import java.util.List;

public class ListCompare {

    /**
     * 是否相同
     */
    private boolean isSame = false;
    /**
     * 不相同的位置的集合
     */
    private List<Integer> integerList;

    /**
     * 是否数目不一致
     */
    private boolean isCountSame = false;

    public boolean isSame() {
        return isSame;
    }

    public void setSame(boolean same) {
        isSame = same;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public boolean isCountSame() {
        return isCountSame;
    }

    public void setCountSame(boolean countSame) {
        isCountSame = countSame;
    }
}
