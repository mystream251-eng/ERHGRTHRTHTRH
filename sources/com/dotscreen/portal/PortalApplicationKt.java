package com.dotscreen.portal;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.google.android.exoplayer2.C;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX INFO: compiled from: PortalApplication.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u0016\u0010\u0005\u001a\u00020\u0001*\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a\u0016\u0010\t\u001a\u00020\u0001*\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¨\u0006\n"}, d2 = {"scheduleCoreCheck", "", "context", "Landroid/content/Context;", "scheduleUpdaterCheck", "dumpColumnNames", "Landroid/database/Cursor;", "cursorUri", "Landroid/net/Uri;", "dumpValues", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class PortalApplicationKt {
    public static /* synthetic */ void dumpColumnNames$default(Cursor cursor, Uri uri, int i, Object obj) {
        if ((i & 1) != 0) {
            uri = (Uri) null;
        }
        dumpColumnNames(cursor, uri);
    }

    public static final void dumpColumnNames(Cursor dumpColumnNames, Uri uri) {
        String str;
        Intrinsics.checkParameterIsNotNull(dumpColumnNames, "$this$dumpColumnNames");
        if (uri != null) {
            str = "Cursor - " + uri.getPath();
        } else {
            str = "Cursor";
        }
        String[] columnNames = dumpColumnNames.getColumnNames();
        Intrinsics.checkExpressionValueIsNotNull(columnNames, "columnNames");
        int i = 1;
        if (columnNames.length == 0) {
            throw new UnsupportedOperationException("Empty array can't be reduced.");
        }
        String str2 = columnNames[0];
        int lastIndex = ArraysKt.getLastIndex(columnNames);
        if (1 <= lastIndex) {
            while (true) {
                str2 = str2 + '|' + columnNames[i];
                if (i == lastIndex) {
                    break;
                } else {
                    i++;
                }
            }
        }
        Log.d(str, str2);
    }

    public static /* synthetic */ void dumpValues$default(Cursor cursor, Uri uri, int i, Object obj) {
        if ((i & 1) != 0) {
            uri = (Uri) null;
        }
        dumpValues(cursor, uri);
    }

    public static final void dumpValues(Cursor dumpValues, Uri uri) {
        Intrinsics.checkParameterIsNotNull(dumpValues, "$this$dumpValues");
        String str = uri != null ? "Cursor - " + uri.getPath() : "Cursor";
        ArrayList arrayList = new ArrayList();
        int columnCount = dumpValues.getColumnCount();
        for (int i = 0; i < columnCount; i++) {
            int type = dumpValues.getType(i);
            if (type == 0) {
                arrayList.add("null");
            } else if (type == 1) {
                arrayList.add(String.valueOf(dumpValues.getInt(i)));
            } else if (type == 2) {
                arrayList.add(String.valueOf(dumpValues.getFloat(i)));
            } else if (type == 3) {
                arrayList.add(dumpValues.getString(i).toString());
            } else if (type == 4) {
                byte[] blob = dumpValues.getBlob(i);
                Intrinsics.checkExpressionValueIsNotNull(blob, "getBlob(i)");
                arrayList.add(new String(blob, Charsets.UTF_8));
            }
        }
        Iterator it = arrayList.iterator();
        if (!it.hasNext()) {
            throw new UnsupportedOperationException("Empty collection can't be reduced.");
        }
        Object next = it.next();
        while (it.hasNext()) {
            next = ((String) next) + '|' + ((String) it.next());
        }
        Log.d(str, (String) next);
    }

    public static final void scheduleUpdaterCheck(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("jobscheduler");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.job.JobScheduler");
        }
        JobScheduler jobScheduler = (JobScheduler) systemService;
        JobInfo pendingJob = jobScheduler.getPendingJob(0);
        JobInfo pendingJob2 = jobScheduler.getPendingJob(2);
        if (pendingJob == null && pendingJob2 == null) {
            Intent intent = Intent.parseUri("megacable://com.dotscreen.megacable.updater/schedule", 0);
            Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
            intent.setAction("android.intent.action.RUN");
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
            try {
                context.startActivity(intent);
            } catch (Exception e) {
                Log.e("PortalApplication", "LUDO retry launch updater, " + e);
            }
        }
    }

    public static final void scheduleCoreCheck(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Object systemService = context.getSystemService("jobscheduler");
        if (systemService == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.app.job.JobScheduler");
        }
        if (((JobScheduler) systemService).getPendingJob(1) == null) {
            Intent intent = Intent.parseUri("megacable://com.dotscreen.megacable.core/schedule", 0);
            Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
            intent.setAction("android.intent.action.RUN");
            intent.setFlags(C.ENCODING_PCM_MU_LAW);
            try {
                context.startActivity(intent);
            } catch (Exception e) {
                Log.e("PortalApplication", "LUDO retry launch core", e);
            }
        }
    }
}
