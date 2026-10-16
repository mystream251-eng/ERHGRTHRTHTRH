package com.google.android.gms.internal;

import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
@zzabh
public class zzyn {
    private final zzaof zza;
    private final String zzb;

    public zzyn(zzaof zzaofVar) {
        this(zzaofVar, "");
    }

    public zzyn(zzaof zzaofVar, String str) {
        this.zza = zzaofVar;
        this.zzb = str;
    }

    public final void zza(int i, int i2, int i3, int i4) {
        try {
            this.zza.zza("onSizeChanged", new JSONObject().put("x", i).put("y", i2).put(ToolboxAnalyticsModuleKt.M_WIDTH, i3).put(ToolboxAnalyticsModuleKt.M_HEIGHT, i4));
        } catch (JSONException e) {
            zzahw.zzb("Error occured while dispatching size change.", e);
        }
    }

    public final void zza(int i, int i2, int i3, int i4, float f, int i5) {
        try {
            this.zza.zza("onScreenInfoChanged", new JSONObject().put(ToolboxAnalyticsModuleKt.M_WIDTH, i).put(ToolboxAnalyticsModuleKt.M_HEIGHT, i2).put("maxSizeWidth", i3).put("maxSizeHeight", i4).put("density", f).put("rotation", i5));
        } catch (JSONException e) {
            zzahw.zzb("Error occured while obtaining screen information.", e);
        }
    }

    public final void zza(String str) {
        try {
            this.zza.zza("onError", new JSONObject().put("message", str).put("action", this.zzb));
        } catch (JSONException e) {
            zzahw.zzb("Error occurred while dispatching error event.", e);
        }
    }

    public final void zzb(int i, int i2, int i3, int i4) {
        try {
            this.zza.zza("onDefaultPositionReceived", new JSONObject().put("x", i).put("y", i2).put(ToolboxAnalyticsModuleKt.M_WIDTH, i3).put(ToolboxAnalyticsModuleKt.M_HEIGHT, i4));
        } catch (JSONException e) {
            zzahw.zzb("Error occured while dispatching default position.", e);
        }
    }

    public final void zzb(String str) {
        try {
            this.zza.zza("onReadyEventReceived", new JSONObject().put("js", str));
        } catch (JSONException e) {
            zzahw.zzb("Error occured while dispatching ready Event.", e);
        }
    }

    public final void zzc(String str) {
        try {
            this.zza.zza("onStateChanged", new JSONObject().put("state", str));
        } catch (JSONException e) {
            zzahw.zzb("Error occured while dispatching state change.", e);
        }
    }
}
