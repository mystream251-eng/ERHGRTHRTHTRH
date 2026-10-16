package com.dotscreen.portal.ui.screens;

import android.util.SparseArray;
import androidx.exifinterface.media.ExifInterface;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.Row;
import com.dotscreen.portal.model.BaseWidget;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ISwimlanesFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016Jr\u0010\u0011\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u0002H\u0012\u0018\u00010\u00182\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\b\u0002\u0010\u001f\u001a\u00020 2\u0010\b\u0002\u0010!\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\"H\u0016R\u001a\u0010\u0002\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u0004\u0018\u00010\bX¦\u000e¢\u0006\f\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006#"}, d2 = {"Lcom/dotscreen/portal/ui/screens/ISwimlanesFragment;", "", "listRows", "Landroid/util/SparseArray;", "Landroidx/leanback/widget/ListRow;", "getListRows", "()Landroid/util/SparseArray;", "rowsAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "getRowsAdapter", "()Landroidx/leanback/widget/ArrayObjectAdapter;", "setRowsAdapter", "(Landroidx/leanback/widget/ArrayObjectAdapter;)V", "addRowSorted", "", "newRow", "Landroidx/leanback/widget/Row;", "createOrUpdateRow", ExifInterface.GPS_DIRECTION_TRUE, "index", "", "header", "Landroidx/leanback/widget/HeaderItem;", "items", "", "moreItem", "Lcom/dotscreen/portal/model/BaseWidget;", "presenter", "Landroidx/leanback/widget/Presenter;", "presenterSelector", "Landroidx/leanback/widget/PresenterSelector;", "removeIfEmpty", "", "listRowClass", "Ljava/lang/Class;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public interface ISwimlanesFragment {
    void addRowSorted(Row newRow);

    <T> void createOrUpdateRow(int index, HeaderItem header, List<? extends T> items, BaseWidget moreItem, Presenter presenter, PresenterSelector presenterSelector, boolean removeIfEmpty, Class<? extends ListRow> listRowClass);

    SparseArray<ListRow> getListRows();

    ArrayObjectAdapter getRowsAdapter();

    void setRowsAdapter(ArrayObjectAdapter arrayObjectAdapter);

    /* JADX INFO: compiled from: ISwimlanesFragment.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static final class DefaultImpls {
        public static void addRowSorted(ISwimlanesFragment iSwimlanesFragment, Row newRow) {
            boolean z;
            Intrinsics.checkParameterIsNotNull(newRow, "newRow");
            ArrayObjectAdapter rowsAdapter = iSwimlanesFragment.getRowsAdapter();
            if (rowsAdapter != null) {
                rowsAdapter.add(newRow);
                int size = rowsAdapter.size();
                do {
                    z = false;
                    for (int i = 1; i < size; i++) {
                        int i2 = i - 1;
                        Object obj = rowsAdapter.get(i2);
                        if (obj == null) {
                            throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.Row");
                        }
                        long id = ((Row) obj).getId();
                        Object obj2 = rowsAdapter.get(i);
                        if (obj2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.Row");
                        }
                        if (id > ((Row) obj2).getId()) {
                            Object obj3 = rowsAdapter.get(i2);
                            rowsAdapter.replace(i2, rowsAdapter.get(i));
                            rowsAdapter.replace(i, obj3);
                            z = true;
                        }
                    }
                } while (z);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void createOrUpdateRow$default(ISwimlanesFragment iSwimlanesFragment, int i, HeaderItem headerItem, List list, BaseWidget baseWidget, Presenter presenter, PresenterSelector presenterSelector, boolean z, Class cls, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createOrUpdateRow");
            }
            iSwimlanesFragment.createOrUpdateRow(i, (i2 & 2) != 0 ? (HeaderItem) null : headerItem, list, (i2 & 8) != 0 ? (BaseWidget) null : baseWidget, (i2 & 16) != 0 ? (Presenter) null : presenter, (i2 & 32) != 0 ? (PresenterSelector) null : presenterSelector, (i2 & 64) != 0 ? true : z, (i2 & 128) != 0 ? ListRow.class : cls);
        }

        public static <T> void createOrUpdateRow(ISwimlanesFragment iSwimlanesFragment, int i, HeaderItem headerItem, List<? extends T> list, BaseWidget baseWidget, Presenter presenter, PresenterSelector presenterSelector, boolean z, Class<? extends ListRow> listRowClass) {
            ArrayObjectAdapter rowsAdapter;
            ArrayObjectAdapter arrayObjectAdapter;
            Intrinsics.checkParameterIsNotNull(listRowClass, "listRowClass");
            if (i == -1) {
                return;
            }
            if (list == null || (z && list.isEmpty())) {
                ListRow listRow = iSwimlanesFragment.getListRows().get(i);
                if (listRow != null && (rowsAdapter = iSwimlanesFragment.getRowsAdapter()) != null) {
                    rowsAdapter.remove(listRow);
                }
                iSwimlanesFragment.getListRows().delete(i);
                return;
            }
            if (baseWidget != null) {
                baseWidget.setSubtitle("SEE_ALL_TAG");
            }
            if (iSwimlanesFragment.getListRows().get(i) == null) {
                if (presenter != null) {
                    arrayObjectAdapter = new ArrayObjectAdapter(presenter);
                } else {
                    arrayObjectAdapter = presenterSelector != null ? new ArrayObjectAdapter(presenterSelector) : null;
                }
                if (arrayObjectAdapter != null) {
                    if (baseWidget != null) {
                        arrayObjectAdapter.add(baseWidget);
                        arrayObjectAdapter.addAll(1, list);
                    } else {
                        arrayObjectAdapter.addAll(0, list);
                    }
                    iSwimlanesFragment.getListRows().put(i, listRowClass.getConstructor(Long.TYPE, HeaderItem.class, ObjectAdapter.class).newInstance(Long.valueOf(i), headerItem, arrayObjectAdapter));
                    ListRow listRow2 = iSwimlanesFragment.getListRows().get(i);
                    if (listRow2 == null) {
                        Intrinsics.throwNpe();
                    }
                    iSwimlanesFragment.addRowSorted(listRow2);
                    return;
                }
                return;
            }
            ListRow listRow3 = iSwimlanesFragment.getListRows().get(i);
            if (listRow3 == null) {
                Intrinsics.throwNpe();
            }
            ObjectAdapter adapter = listRow3.getAdapter();
            if (adapter == null) {
                throw new TypeCastException("null cannot be cast to non-null type androidx.leanback.widget.ArrayObjectAdapter");
            }
            ArrayObjectAdapter arrayObjectAdapter2 = (ArrayObjectAdapter) adapter;
            if (baseWidget != null) {
                if (list.size() < arrayObjectAdapter2.size() - 1) {
                    arrayObjectAdapter2.removeItems(list.size() + 1, (arrayObjectAdapter2.size() - list.size()) - 1);
                }
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    T t = list.get(i2);
                    if (i2 < arrayObjectAdapter2.size() - 1) {
                        int i3 = i2 + 1;
                        if (arrayObjectAdapter2.get(i3).hashCode() != (t != null ? t.hashCode() : 0)) {
                            arrayObjectAdapter2.replace(i3, t);
                        }
                    } else {
                        arrayObjectAdapter2.add(t);
                    }
                }
                return;
            }
            if (list.size() < arrayObjectAdapter2.size()) {
                arrayObjectAdapter2.removeItems(list.size(), arrayObjectAdapter2.size() - list.size());
            }
            int size2 = list.size();
            for (int i4 = 0; i4 < size2; i4++) {
                T t2 = list.get(i4);
                if (i4 < arrayObjectAdapter2.size()) {
                    if (arrayObjectAdapter2.get(i4).hashCode() != (t2 != null ? t2.hashCode() : 0)) {
                        arrayObjectAdapter2.replace(i4, t2);
                    }
                } else {
                    arrayObjectAdapter2.add(t2);
                }
            }
        }
    }
}
