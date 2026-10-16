package com.android.tv.common;

import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BooleanSystemProperty {
    private static final List<BooleanSystemProperty> ALL_PROPERTIES = new ArrayList();
    private static final boolean DEBUG = false;
    private static final String TAG = "BooleanSystemProperty";
    private final boolean mDefaultValue;
    private final String mKey;
    private Boolean mValue = null;

    public BooleanSystemProperty(String str, boolean z) {
        this.mDefaultValue = z;
        this.mKey = str;
        ALL_PROPERTIES.add(this);
    }

    public static void resetAll() {
        Iterator<BooleanSystemProperty> it = ALL_PROPERTIES.iterator();
        while (it.hasNext()) {
            it.next().reset();
        }
    }

    private static boolean getBoolean(String str, boolean z) {
        try {
            return ((Boolean) Class.forName("android.os.SystemProperties").getMethod("getBoolean", String.class, Boolean.TYPE).invoke(null, str, Boolean.valueOf(z))).booleanValue();
        } catch (Exception e) {
            Log.e(TAG, "Error getting boolean for  " + str, e);
            return z;
        }
    }

    public void reset() {
        this.mValue = null;
    }

    public boolean getValue() {
        if (this.mValue == null) {
            this.mValue = Boolean.valueOf(getBoolean(this.mKey, this.mDefaultValue));
        }
        return this.mValue.booleanValue();
    }

    public String toString() {
        return "SystemProperty[" + this.mKey + "]=" + getValue();
    }
}
