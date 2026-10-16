package com.dotscreen.portal.model;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.dotscreen.farkit.tools.LanguagePreferences;
import com.dotscreen.portal.PortalApplicationSingletons;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayerViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ'\u0010\t\u001a\u0002H\n\"\n\b\u0000\u0010\n*\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/portal/model/PlayerViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "params", "Lcom/dotscreen/portal/model/PlayerViewModelFactory$Companion$PlayerViewModelParams;", "languagePreferences", "Lcom/dotscreen/farkit/tools/LanguagePreferences;", "applicationSingleton", "Lcom/dotscreen/portal/PortalApplicationSingletons;", "(Lcom/dotscreen/portal/model/PlayerViewModelFactory$Companion$PlayerViewModelParams;Lcom/dotscreen/farkit/tools/LanguagePreferences;Lcom/dotscreen/portal/PortalApplicationSingletons;)V", "create", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "Companion", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class PlayerViewModelFactory implements ViewModelProvider.Factory {
    private final PortalApplicationSingletons applicationSingleton;
    private final LanguagePreferences languagePreferences;
    private final Companion.PlayerViewModelParams params;

    public PlayerViewModelFactory(Companion.PlayerViewModelParams params, LanguagePreferences languagePreferences, PortalApplicationSingletons applicationSingleton) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Intrinsics.checkParameterIsNotNull(languagePreferences, "languagePreferences");
        Intrinsics.checkParameterIsNotNull(applicationSingleton, "applicationSingleton");
        this.params = params;
        this.languagePreferences = languagePreferences;
        this.applicationSingleton = applicationSingleton;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkParameterIsNotNull(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(PlayerViewModel.class)) {
            Log.v("PlayerViewModel", "params: " + this.params);
            return new PlayerViewModel(this.params.getContendId(), this.params.getChannelId(), this.params.getTimestamp(), this.params.getRecordingId(), this.params.getReplayType(), this.params.getResume(), this.params.getTrailer(), this.params.getLaunchMode(), this.params.getAudioLanguage(), this.params.getSubtitleLanguage(), this.languagePreferences, this.applicationSingleton);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
