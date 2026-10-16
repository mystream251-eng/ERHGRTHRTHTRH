package com.android.tv.common;

import android.content.Intent;
import android.media.tv.TvInputInfo;

/* JADX INFO: loaded from: classes.dex */
public final class TvCommonUtils {
    private TvCommonUtils() {
    }

    public static Intent createSetupIntent(Intent intent, String str) {
        if (intent == null) {
            return null;
        }
        Intent intent2 = new Intent(intent);
        if (TvCommonConstants.INTENT_ACTION_INPUT_SETUP.equals(intent.getAction())) {
            return intent2;
        }
        Intent intent3 = new Intent(TvCommonConstants.INTENT_ACTION_INPUT_SETUP);
        intent3.putExtra(TvCommonConstants.EXTRA_SETUP_INTENT, intent);
        intent3.putExtra(TvCommonConstants.EXTRA_INPUT_ID, str);
        return intent3;
    }

    public static Intent createSetupIntent(TvInputInfo tvInputInfo) {
        return createSetupIntent(tvInputInfo.createSetupIntent(), tvInputInfo.getId());
    }

    public static boolean isRunningInTest() {
        try {
            Class.forName("com.android.tv.testing.Utils");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }
}
