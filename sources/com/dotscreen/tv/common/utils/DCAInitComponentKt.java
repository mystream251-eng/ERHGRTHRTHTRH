package com.dotscreen.tv.common.utils;

import com.dotscreen.farkit.analytics.GenericAnalyticsModule;
import com.viaccessorca.analytics.dca.VoDcaEvent;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DCAInitComponent.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a0\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u001a\"\u0010\n\u001a\u00020\t*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004¨\u0006\u0010"}, d2 = {"submitContent", "", "Lcom/dotscreen/farkit/analytics/GenericAnalyticsModule;", "contentType", "", "channelId", "programId", "callback", "Lkotlin/Function0;", "", "submitEvent", "eventType", "Lcom/viaccessorca/analytics/dca/VoDcaEvent$EventType;", "eventKey", "Lcom/viaccessorca/analytics/dca/VoDcaEvent$EventKey;", "value", "common_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class DCAInitComponentKt {
    public static final void submitEvent(GenericAnalyticsModule submitEvent, VoDcaEvent.EventType eventType, VoDcaEvent.EventKey eventKey, String value) {
        Intrinsics.checkParameterIsNotNull(submitEvent, "$this$submitEvent");
        Intrinsics.checkParameterIsNotNull(eventType, "eventType");
        Intrinsics.checkParameterIsNotNull(eventKey, "eventKey");
        Intrinsics.checkParameterIsNotNull(value, "value");
        try {
            new VoDcaEvent().setType(eventType).setParameter(eventKey, value).submit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final boolean submitContent(GenericAnalyticsModule submitContent, String contentType, String channelId, String programId, Function0<Unit> callback) {
        Intrinsics.checkParameterIsNotNull(submitContent, "$this$submitContent");
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        Intrinsics.checkParameterIsNotNull(channelId, "channelId");
        Intrinsics.checkParameterIsNotNull(programId, "programId");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        try {
            submitEvent(submitContent, VoDcaEvent.EventType.OPEN, VoDcaEvent.EventKey.CONTENT_TYPE, contentType);
            submitEvent(submitContent, VoDcaEvent.EventType.SET_CHANNEL, VoDcaEvent.EventKey.CHANNEL_ID, channelId);
            submitEvent(submitContent, VoDcaEvent.EventType.SET_PROGRAM, VoDcaEvent.EventKey.PROGRAM_ID, programId);
            return true;
        } catch (Exception unused) {
            callback.invoke();
            return false;
        }
    }
}
