package com.tb.rx_retrofit.tools.task_management;


import android.util.ArrayMap;

import com.tb.rx_retrofit.tools.TBHttpLog;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Set;

import rx.Subscription;

/**
 * @描述： -网络请求线程管理
 * -
 * @作者：zhusw
 * @创建时间：17/11/16 下午12:03
 * @最后更新时间：17/11/16 下午12:03
 */
public class RxHttpTaskManagement implements HttpTaskManagement<Object> {

    private static HttpTaskManagement INSTANCE = null;

    private ArrayMap<Integer, List<Subscription>> tasks;

    private RxHttpTaskManagement () {
        tasks = new ArrayMap<>();
    }

    public static HttpTaskManagement<Object> getINSTANCE () {
        if (null == INSTANCE) {
            synchronized (RxHttpTaskManagement.class) {
                if (null == INSTANCE) {
                    INSTANCE = new RxHttpTaskManagement();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void addSubscription (Object tag, Subscription subscription) {
        if (null != tag) {
            List<Subscription> list = tasks.get(tag.hashCode());
            if (null == list) {
                list = new ArrayList<>();
            }
            list.add(subscription);
            tasks.put(tag.hashCode(), list);
            TBHttpLog.printI("RxHttpTaskmanagement", "记录新订阅 key:" + tag.hashCode());
        }
    }

    @Override
    public void unSubscribe (Object tag) {
        if (null == tasks || tasks.isEmpty()) return;
        TBHttpLog.printI("RxHttpTaskmanagement", "执行取消订阅 key:" + tag.hashCode());
        List<Subscription> list = tasks.get(tag.hashCode());
        if (null != list && list.size() > 0) {
            TBHttpLog.printI("RxHttpTaskmanagement", "执行取消订阅 size:" + list.size());
            for (int i = 0; i < list.size(); i++) {
                Subscription subscription = list.get(i);
                if (null != subscription ) {
                    TBHttpLog.printI("RxHttpTaskmanagement", "取消了订阅 key:" + tag.hashCode());
                    subscription.unsubscribe();
                }
            }
            tasks.remove(tag.hashCode());
        }
    }


    @Override
    public void unSubscribeAll () {
        if (!tasks.isEmpty()) return;
        Set<Integer> keys = tasks.keySet();
        for (Integer tag : keys) {
            unSubscribe(tag);
        }
    }

    /**
     * 抛弃之前已添加的所有任务接收者,但不负责回收
     * －潜意识里觉得需要这么一个方法～～～
     * 因为tasks 引用发生在多个线程 所以不可以 置空
     * 仅可使用clear 来释放所有保存的对象
     */
    @Override
    public void abandonAll () {
        if (!tasks.isEmpty()) {
            tasks.clear();
        }
    }


}
