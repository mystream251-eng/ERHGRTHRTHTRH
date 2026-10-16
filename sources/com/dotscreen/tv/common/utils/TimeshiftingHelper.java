package com.dotscreen.tv.common.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: compiled from: TimeshiftingHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/dotscreen/tv/common/utils/TimeshiftingHelper;", "", "()V", "catchUpAvailabilityPeriodInHours", "", "cpvrAvailabilityPeriodInHours", "lagTimeInMinutes", "leadTimeInMinutes", "npvrAvaliabilityPeriod", "npvrAvaliabilityPeriodUnit", "npvrExpirationPeriod", "npvrExpirationPeriodUnit", "responseElementType", "", "getAdditionnalDurationInSeconds", "", "getLagTimeInSeconds", "parseData", "", "jsonObject", "Lorg/json/JSONObject;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class TimeshiftingHelper {
    private int catchUpAvailabilityPeriodInHours;
    private int cpvrAvailabilityPeriodInHours;
    private int npvrAvaliabilityPeriod;
    private int npvrAvaliabilityPeriodUnit;
    private int npvrExpirationPeriod;
    private int npvrExpirationPeriodUnit;
    private int lagTimeInMinutes = 5;
    private int leadTimeInMinutes = 5;
    private String responseElementType = "";

    public final long getLagTimeInSeconds() {
        return this.lagTimeInMinutes * 60;
    }

    public final long getAdditionnalDurationInSeconds() {
        return (this.lagTimeInMinutes * 60) + (this.leadTimeInMinutes * 60);
    }

    public final void parseData(JSONObject jsonObject) {
        Intrinsics.checkParameterIsNotNull(jsonObject, "jsonObject");
        try {
            this.catchUpAvailabilityPeriodInHours = jsonObject.optInt("catchUpAvailabilityPeriodInHours", 0);
            this.cpvrAvailabilityPeriodInHours = jsonObject.optInt("cpvrAvailabilityPeriodInHours", 0);
            this.lagTimeInMinutes = jsonObject.optInt("lagTimeInMinutes", 5);
            this.leadTimeInMinutes = jsonObject.optInt("leadTimeInMinutes", 5);
            this.npvrAvaliabilityPeriod = jsonObject.optInt("npvrAvaliabilityPeriod", 0);
            this.npvrAvaliabilityPeriodUnit = jsonObject.optInt("npvrAvaliabilityPeriodUnit", 0);
            this.npvrExpirationPeriod = jsonObject.optInt("npvrExpirationPeriod", 0);
            this.npvrExpirationPeriodUnit = jsonObject.optInt("npvrExpirationPeriodUnit", 0);
            String strOptString = jsonObject.optString("responseElementType", "");
            Intrinsics.checkExpressionValueIsNotNull(strOptString, "jsonObject.optString(\"responseElementType\", \"\")");
            this.responseElementType = strOptString;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
