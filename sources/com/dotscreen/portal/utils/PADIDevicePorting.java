package com.dotscreen.portal.utils;

import android.content.Context;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Base64;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PADIDevicePorting.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/dotscreen/portal/utils/PADIDevicePorting;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "protectedStorage", "Lcom/dotscreen/portal/utils/ProtectedStorage;", "getPartnerDeviceIdentifier", "", "savePartnerDeviceIdentifier", "", "pdi", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class PADIDevicePorting {
    private final Context context;
    private final ProtectedStorage protectedStorage;

    public PADIDevicePorting(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.protectedStorage = new ProtectedStorage(this.context, "pdi.bin");
    }

    public final String getPartnerDeviceIdentifier() {
        if (this.protectedStorage.fileExists()) {
            return Base64.getEncoder().encodeToString(this.protectedStorage.retrieveData(32));
        }
        return null;
    }

    public final void savePartnerDeviceIdentifier(String pdi) throws GeneralSecurityException, IOException {
        Intrinsics.checkParameterIsNotNull(pdi, "pdi");
        this.protectedStorage.storeData(Base64.getDecoder().decode(pdi));
    }
}
