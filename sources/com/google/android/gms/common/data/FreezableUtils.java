package com.google.android.gms.common.data;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.reflect.jvm.internal.impl.types.model.ArgumentList;

/* JADX INFO: loaded from: classes2.dex */
public final class FreezableUtils {
    public static <T, E extends Freezable<T>> ArrayList<T> freeze(ArrayList<E> arrayList) {
        ArgumentList argumentList = (ArrayList<T>) new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            argumentList.add(arrayList.get(i).freeze());
        }
        return argumentList;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freeze(E[] eArr) {
        ArgumentList argumentList = (ArrayList<T>) new ArrayList(eArr.length);
        for (E e : eArr) {
            argumentList.add(e.freeze());
        }
        return argumentList;
    }

    public static <T, E extends Freezable<T>> ArrayList<T> freezeIterable(Iterable<E> iterable) {
        ArgumentList argumentList = (ArrayList<T>) new ArrayList();
        Iterator<E> it = iterable.iterator();
        while (it.hasNext()) {
            argumentList.add(it.next().freeze());
        }
        return argumentList;
    }
}
