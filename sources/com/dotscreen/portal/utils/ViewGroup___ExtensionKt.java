package com.dotscreen.portal.utils;

import android.view.View;
import android.view.ViewGroup;
import androidx.exifinterface.media.ExifInterface;
import com.dotscreen.tv.common.utils.notifications.NotificationsContract;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ViewGroup + Extension.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a-\u0010\u0007\u001a\u00020\b\"\n\b\u0000\u0010\t\u0018\u0001*\u00020\u0002*\u00020\u00042\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\b0\u000bH\u0086\b¨\u0006\f"}, d2 = {"findViewsByTag", "Ljava/util/ArrayList;", "Landroid/view/View;", "root", "Landroid/view/ViewGroup;", NotificationsContract.COLUMN_TAG, "", "findViewsByType", "", ExifInterface.GPS_DIRECTION_TRUE, "action", "Lkotlin/Function1;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class ViewGroup___ExtensionKt {
    public static final /* synthetic */ <T extends View> void findViewsByType(ViewGroup findViewsByType, Function1<? super T, Unit> action) {
        Intrinsics.checkParameterIsNotNull(findViewsByType, "$this$findViewsByType");
        Intrinsics.checkParameterIsNotNull(action, "action");
        Stack stack = new Stack();
        IntRange intRangeUntil = RangesKt.until(0, findViewsByType.getChildCount());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            arrayList.add(findViewsByType.getChildAt(((IntIterator) it).nextInt()));
        }
        stack.addAll(arrayList);
        while (!stack.isEmpty()) {
            View view = (View) stack.pop();
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (view instanceof View) {
                action.invoke(view);
            }
            if (view instanceof ViewGroup) {
                IntRange intRangeUntil2 = RangesKt.until(0, findViewsByType.getChildCount());
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil2, 10));
                Iterator<Integer> it2 = intRangeUntil2.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(findViewsByType.getChildAt(((IntIterator) it2).nextInt()));
                }
                stack.addAll(arrayList2);
            }
        }
    }

    private static final ArrayList<View> findViewsByTag(ViewGroup viewGroup, String str) {
        ArrayList<View> arrayList = new ArrayList<>();
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof ViewGroup) {
                ArrayList<View> arrayListFindViewsByTag = findViewsByTag((ViewGroup) child, str);
                if (arrayListFindViewsByTag == null) {
                    Intrinsics.throwNpe();
                }
                arrayList.addAll(arrayListFindViewsByTag);
            }
            Intrinsics.checkExpressionValueIsNotNull(child, "child");
            Object tag = child.getTag();
            if (tag != null && Intrinsics.areEqual(tag, str)) {
                arrayList.add(child);
            }
        }
        return arrayList;
    }
}
