package com.dotscreen.bokit.services;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BOService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"Lcom/dotscreen/bokit/services/BOServicePlugin;", "", "createLiveTVService", "Lcom/dotscreen/bokit/services/LiveTVService;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "createPVRService", "Lcom/dotscreen/bokit/services/PVRService;", "createUserService", "Lcom/dotscreen/bokit/services/UserService;", "createVODCatalogService", "Lcom/dotscreen/bokit/services/VODCatalogService;", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public interface BOServicePlugin {
    LiveTVService createLiveTVService(BOService boService);

    PVRService createPVRService(BOService boService);

    UserService createUserService(BOService boService);

    VODCatalogService createVODCatalogService(BOService boService);

    /* JADX INFO: compiled from: BOService.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 1})
    public static final class DefaultImpls {
        public static PVRService createPVRService(BOServicePlugin bOServicePlugin, BOService boService) {
            Intrinsics.checkNotNullParameter(boService, "boService");
            return null;
        }

        public static UserService createUserService(BOServicePlugin bOServicePlugin, BOService boService) {
            Intrinsics.checkNotNullParameter(boService, "boService");
            return null;
        }

        public static LiveTVService createLiveTVService(BOServicePlugin bOServicePlugin, BOService boService) {
            Intrinsics.checkNotNullParameter(boService, "boService");
            return new LiveTVService(boService);
        }

        public static VODCatalogService createVODCatalogService(BOServicePlugin bOServicePlugin, BOService boService) {
            Intrinsics.checkNotNullParameter(boService, "boService");
            return new VODCatalogService(boService);
        }
    }
}
