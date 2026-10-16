package com.dotscreen.tv.common;

import kotlin.Metadata;

/* JADX INFO: compiled from: ISmartLibProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0001H&J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0001H&J\u0014\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\b"}, d2 = {"Lcom/dotscreen/tv/common/ISmartLibProvider;", "", "getSmartLib", "getSmartLibCoreEngine", "reinitSmartLibIfNeeded", "", "message", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public interface ISmartLibProvider {
    Object getSmartLib();

    Object getSmartLibCoreEngine();

    void reinitSmartLibIfNeeded(String message);

    /* JADX INFO: compiled from: ISmartLibProvider.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static final class DefaultImpls {
        public static /* synthetic */ void reinitSmartLibIfNeeded$default(ISmartLibProvider iSmartLibProvider, String str, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: reinitSmartLibIfNeeded");
            }
            if ((i & 1) != 0) {
                str = (String) null;
            }
            iSmartLibProvider.reinitSmartLibIfNeeded(str);
        }
    }
}
