package com.dotscreen.portal.model;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.MutableLiveData;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: compiled from: MyContentsViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0005¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/dotscreen/portal/model/AdultMutableLiveData;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/MutableLiveData;", "", "()V", "addValue", "", "newValue", "(Ljava/lang/Object;)V", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class AdultMutableLiveData<T> extends MutableLiveData<List<? extends T>> {
    public final void addValue(T newValue) {
        if (getValue() == null) {
            postValue(CollectionsKt.emptyList());
        }
        List list = (List) getValue();
        List mutableList = list != null ? CollectionsKt.toMutableList((Collection) list) : null;
        if (mutableList != null) {
            mutableList.add(newValue);
        }
        postValue(mutableList);
    }
}
