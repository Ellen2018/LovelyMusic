package com.ellen.libcommon.util.collectionutil;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtils {

    /**
     *  对某个集合进行排序
     */
    public static <E> List<E> sort(List<E> eList) {
        List<E> copyList = new ArrayList<>();
        for (E e : eList) {
            copyList.add(e);
        }
        E e = eList.get(0);
        if (!(e instanceof CompareableInterface)) {
            //抛出异常 -> 说明它没有实现比较器接口
            throw new CompareableException("", "your class Not Implemented CompareableInterface");
        }
        //使用冒泡排序进行排序
        for (int i = 0; i < copyList.size(); i++) {
            for (int j = i + 1; j < copyList.size(); j++) {
                CompareableInterface iCompareable = (CompareableInterface) copyList.get(i);
                if (iCompareable.compareTo(copyList.get(j)) >= 0) {
                    E e1 = copyList.get(i);
                    copyList.set(i, copyList.get(j));
                    copyList.set(j, e1);
                }
            }
        }
        return copyList;
    }

    /**
     *  对某个集合进行整理算法
     */
    public static <E> List<List<E>> arrange(List<E> eList) {
        E e = eList.get(0);
        if (!(e instanceof ArrangeInterface)) {
            //抛出异常 -> 说明它没有实现归类整理器接口
            throw new CompareableException("", "your class Not Implemented ArrangeInterface");
        }
        if (eList == null || eList.size() == 0) {
            //执行这里说明无法进行整理归类
            return null;
        }
        List<E> copyList = new ArrayList<>();
        List<List<E>> listList = new ArrayList<>();
        for (E e1 : eList) {
            copyList.add(e1);
        }
        for (int i = 0; i < eList.size(); i++) {
            boolean isAddList = true;
            for (List<E> list : listList) {
                E eCompare = list.get(0);
                ArrangeInterface arrangeInterface = (ArrangeInterface) eList.get(i);
                boolean falg = arrangeInterface.identical(eCompare);
                if (falg) {
                    //是相同的
                    list.add(eList.get(i));
                    isAddList = false;
                    break;
                }
            }
            if (isAddList) {
                List<E> eList1 = new ArrayList<>();
                eList1.add(eList.get(i));
                listList.add(eList1);
            }
        }
        return listList;
    }

    /**
     * 比较两个集合是否一致
     *
     * @param list1
     * @param list2
     * @param <E>
     * @return
     */
    public static <E> ListCompare compare(List<E> list1, List<E> list2) {
        ListCompare listCompare = new ListCompare();
        if (list1 == list2) {
            //地址一致说明它们指向同一块内存区域，则说明它们根本就是同一个集合
           listCompare.setSame(true);
           listCompare.setCountSame(true);
           return listCompare;
        }
        if (list1.size() != list2.size()) {
            //数目都不一致，肯定是不相同的啦
            listCompare.setSame(false);
            listCompare.setCountSame(false);
            return listCompare;
        }
        //数目相同
        listCompare.setCountSame(true);
        for (int i = 0; i < list1.size(); i++) {
            E e1 = list1.get(i);
            E e2 = list2.get(i);
            if(!e1.equals(e2)){
               if(listCompare.getIntegerList() == null){
                   listCompare.setIntegerList(new ArrayList<Integer>());
               }
               listCompare.getIntegerList().add(i);
            }
        }
        if(listCompare.getIntegerList() != null && listCompare.getIntegerList().size() > 0){
            listCompare.setSame(false);
        }else {
            listCompare.setSame(true);
        }
        return listCompare;
    }

}
