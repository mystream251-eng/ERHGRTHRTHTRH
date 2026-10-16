package com.dotscreen.tv.common.bokit;

import android.content.Context;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.bokit.services.BOServicePlugin;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.bokit.services.PVRService;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.VODCatalogService;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MegacableServicePlugin.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/dotscreen/tv/common/bokit/MegacableServicePlugin;", "Lcom/dotscreen/bokit/services/BOServicePlugin;", "context", "Landroid/content/Context;", "requestManager", "Lcom/dotscreen/tv/common/bokit/RightVRequestManager;", "(Landroid/content/Context;Lcom/dotscreen/tv/common/bokit/RightVRequestManager;)V", "getContext", "()Landroid/content/Context;", "getRequestManager", "()Lcom/dotscreen/tv/common/bokit/RightVRequestManager;", "createLiveTVService", "Lcom/dotscreen/bokit/services/LiveTVService;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "createPVRService", "Lcom/dotscreen/bokit/services/PVRService;", "createUserService", "Lcom/dotscreen/bokit/services/UserService;", "createVODCatalogService", "Lcom/dotscreen/bokit/services/VODCatalogService;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MegacableServicePlugin implements BOServicePlugin {
    private final Context context;
    private final RightVRequestManager requestManager;

    public MegacableServicePlugin(Context context, RightVRequestManager requestManager) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(requestManager, "requestManager");
        this.context = context;
        this.requestManager = requestManager;
    }

    public final Context getContext() {
        return this.context;
    }

    public final RightVRequestManager getRequestManager() {
        return this.requestManager;
    }

    @Override // com.dotscreen.bokit.services.BOServicePlugin
    public VODCatalogService createVODCatalogService(BOService boService) {
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        return new MegacableVODCatalogService(this.context, boService, this.requestManager);
    }

    @Override // com.dotscreen.bokit.services.BOServicePlugin
    public LiveTVService createLiveTVService(BOService boService) {
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        return new MegacableLiveTVService(this.context, boService, this.requestManager);
    }

    @Override // com.dotscreen.bokit.services.BOServicePlugin
    public UserService createUserService(BOService boService) {
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        return new MegacableUserService(this.context, boService, this.requestManager);
    }

    @Override // com.dotscreen.bokit.services.BOServicePlugin
    public PVRService createPVRService(BOService boService) {
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        return new MegacablePVRService(this.context, boService, this.requestManager);
    }
}
