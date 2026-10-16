package com.dotscreen.portal.ui.screens.player;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.PlaybackRight;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVProgram;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.services.PVRService;
import com.dotscreen.bokit.services.PVRStorageMode;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.farkit.rx.LifecycleAwareDisposableKt;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.tv.common.bokit.MegacablePVRService;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.ui.dialog.PortalDialog;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.exoplayer2.util.MimeTypes;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a&\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0001\u001a$\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010\u001aB\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0018\u001a\u00020\u0019\"\u0016\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"TAG", "", "kotlin.jvm.PlatformType", "resumeDisposable", "Lio/reactivex/disposables/Disposable;", "displayResumePopupOrStartCatchupPlayback", "", "activity", "Landroid/app/Activity;", "channelId", "timeStamp", "", "replayType", "displayResumePopupOrStartPVRPlayback", "recordingId", "onStart", "Lkotlin/Function0;", "displayResumePopupOrStartVODPlayback", "media", "Lcom/dotscreen/bokit/model/Media;", TtmlNode.RIGHT, "Lcom/dotscreen/bokit/model/PlaybackRight;", "audioLanguage", "subtitleLanguage", "catchupasVod", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 2, mv = {1, 1, 16})
public final class PlaybackActivityKt {
    private static final String TAG = VODPlaybackActivity.class.getSimpleName();
    private static Disposable resumeDisposable;

    public static /* synthetic */ void displayResumePopupOrStartVODPlayback$default(Activity activity, Media media, PlaybackRight playbackRight, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            str = (String) null;
        }
        String str3 = str;
        if ((i & 16) != 0) {
            str2 = (String) null;
        }
        String str4 = str2;
        if ((i & 32) != 0) {
            z = false;
        }
        displayResumePopupOrStartVODPlayback(activity, media, playbackRight, str3, str4, z);
    }

    public static final void displayResumePopupOrStartVODPlayback(final Activity activity, final Media media, PlaybackRight playbackRight, final String str, final String str2, final boolean z) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(media, "media");
        Disposable disposable = resumeDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        final BaseFragmentActivity baseFragmentActivity = activity instanceof BaseFragmentActivity ? (BaseFragmentActivity) activity : null;
        if (baseFragmentActivity != null) {
            baseFragmentActivity.setLoaderVisible(true);
        }
        UserService userService = PortalApplication.INSTANCE.getSingleton(activity).getBOService().getUserService();
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        final BaseFragmentActivity baseFragmentActivity2 = baseFragmentActivity;
        Disposable disposableSubscribe = ((MegacableUserService) userService).getMediaPosition(media).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartVODPlayback.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Integer num) {
                PlaybackActivityKt.resumeDisposable = (Disposable) null;
                BaseFragmentActivity baseFragmentActivity3 = baseFragmentActivity2;
                if (baseFragmentActivity3 != null) {
                    baseFragmentActivity3.setLoaderVisible(false);
                }
                if (Intrinsics.compare(num.intValue(), 0) > 0) {
                    new PortalDialog.Builder(activity).setTitle(R.string.resume_playback_title).setPositiveButton(R.string.resume_playback_yes, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartVODPlayback.1.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            DSLog.INSTANCE.a("Clicked on Si on ResumeOrStartPopupVodPlayback");
                            Uri.Builder builderBuildUpon = Uri.parse("megacable://com.dotscreen.megacable.portal/player?content_id=" + media.getId() + "&resume=true&catchup=" + z).buildUpon();
                            String str3 = str;
                            if (str3 != null) {
                                builderBuildUpon.appendQueryParameter(MimeTypes.BASE_TYPE_AUDIO, str3);
                            }
                            String str4 = str2;
                            if (str4 != null) {
                                builderBuildUpon.appendQueryParameter("subtitle", str4);
                            }
                            activity.startActivity(new Intent("android.intent.action.VIEW", builderBuildUpon.build()));
                        }
                    }).setNegativeButton(R.string.resume_playback_no, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartVODPlayback.1.2
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            DSLog.INSTANCE.a("Clicked on No on ResumeOrStartPopupVodPlayback");
                            Uri.Builder builderBuildUpon = Uri.parse("megacable://com.dotscreen.megacable.portal/player?content_id=" + media.getId() + "&resume=false&catchup=" + z).buildUpon();
                            String str3 = str;
                            if (str3 != null) {
                                builderBuildUpon.appendQueryParameter(MimeTypes.BASE_TYPE_AUDIO, str3);
                            }
                            String str4 = str2;
                            if (str4 != null) {
                                builderBuildUpon.appendQueryParameter("subtitle", str4);
                            }
                            activity.startActivity(new Intent("android.intent.action.VIEW", builderBuildUpon.build()));
                        }
                    }).create().show();
                    return;
                }
                Uri.Builder builderBuildUpon = Uri.parse("megacable://com.dotscreen.megacable.portal/player?content_id=" + media.getId()).buildUpon();
                String str3 = str;
                if (str3 != null) {
                    builderBuildUpon.appendQueryParameter(MimeTypes.BASE_TYPE_AUDIO, str3);
                }
                String str4 = str2;
                if (str4 != null) {
                    builderBuildUpon.appendQueryParameter("subtitle", str4);
                }
                activity.startActivity(new Intent("android.intent.action.VIEW", builderBuildUpon.build()));
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartVODPlayback.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                BaseFragmentActivity baseFragmentActivity3 = baseFragmentActivity;
                if (baseFragmentActivity3 != null) {
                    baseFragmentActivity3.setLoaderVisible(false);
                }
                Log.w(PlaybackActivityKt.TAG, "Get playback infos failed:", th);
                Activity activity2 = activity;
                if (activity2 instanceof BaseFragmentActivity) {
                    BaseFragmentActivity baseFragmentActivity4 = (BaseFragmentActivity) activity2;
                    if (th == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Exception /* = java.lang.Exception */");
                    }
                    baseFragmentActivity4.displayErrorMessage((Exception) th, 1);
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "(PortalApplication.getSi… }\n                    })");
        resumeDisposable = LifecycleAwareDisposableKt.addTo(disposableSubscribe, baseFragmentActivity != null ? baseFragmentActivity.getDisposable() : null);
    }

    public static final void displayResumePopupOrStartCatchupPlayback(final Activity activity, final String channelId, final long j, final String replayType) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(channelId, "channelId");
        Intrinsics.checkParameterIsNotNull(replayType, "replayType");
        Disposable disposable = resumeDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        final BaseFragmentActivity baseFragmentActivity = activity instanceof BaseFragmentActivity ? (BaseFragmentActivity) activity : null;
        if (baseFragmentActivity != null) {
            baseFragmentActivity.setLoaderVisible(true);
        }
        final BaseFragmentActivity baseFragmentActivity2 = baseFragmentActivity;
        Disposable disposableSubscribe = PortalApplication.INSTANCE.getSingleton(activity).getCurrentLineup().flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartCatchupPlayback.1
            @Override // io.reactivex.functions.Function
            public final Observable<Channel> apply(Lineup it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).filter(new Predicate<Channel>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartCatchupPlayback.2
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Channel it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return Intrinsics.areEqual(it.getId(), channelId);
            }
        }).firstElement().flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartCatchupPlayback.3
            @Override // io.reactivex.functions.Function
            public final Maybe<TVAiring> apply(Channel it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.airing(new Date(j));
            }
        }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartCatchupPlayback.4
            @Override // io.reactivex.functions.Function
            public final Single<Integer> apply(TVAiring airing) {
                Intrinsics.checkParameterIsNotNull(airing, "airing");
                String str = replayType;
                int iHashCode = str.hashCode();
                if (iHashCode == 3541000) {
                    if (str.equals("stov")) {
                        return airing.getStartOverMedia().flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartCatchupPlayback.4.1
                            @Override // io.reactivex.functions.Function
                            public final Single<Integer> apply(Media it) {
                                Intrinsics.checkParameterIsNotNull(it, "it");
                                UserService userService = PortalApplication.INSTANCE.getSingleton(activity).getBOService().getUserService();
                                if (userService != null) {
                                    return ((MegacableUserService) userService).getMediaPosition(it);
                                }
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                            }
                        });
                    }
                } else if (iHashCode == 555760278 && str.equals("catchup")) {
                    return airing.getTvProgram().flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartCatchupPlayback.4.2
                        @Override // io.reactivex.functions.Function
                        public final Maybe<Media> apply(TVProgram it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return it.getRelatedCatchupMedia();
                        }
                    }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartCatchupPlayback.4.3
                        @Override // io.reactivex.functions.Function
                        public final Single<Integer> apply(Media it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            UserService userService = PortalApplication.INSTANCE.getSingleton(activity).getBOService().getUserService();
                            if (userService != null) {
                                return ((MegacableUserService) userService).getMediaPosition(it);
                            }
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                        }
                    });
                }
                return Single.just(0);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartCatchupPlayback.5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Integer num) {
                PlaybackActivityKt.resumeDisposable = (Disposable) null;
                Log.i(PlaybackActivityKt.TAG, "time - " + num);
                BaseFragmentActivity baseFragmentActivity3 = baseFragmentActivity2;
                if (baseFragmentActivity3 != null) {
                    baseFragmentActivity3.setLoaderVisible(false);
                }
                if (Intrinsics.compare(num.intValue(), 0) > 0) {
                    new PortalDialog.Builder(activity).setTitle(R.string.resume_playback_title).setPositiveButton(R.string.resume_playback_yes, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartCatchupPlayback.5.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/player?channel_id=" + channelId + "&timestamp=" + j + "&type=" + replayType + "&resume=true")));
                        }
                    }).setNegativeButton(R.string.resume_playback_no, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartCatchupPlayback.5.2
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/player?channel_id=" + channelId + "&timestamp=" + j + "&type=" + replayType + "&resume=false")));
                        }
                    }).create().show();
                    return;
                }
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/player?channel_id=" + channelId + "&timestamp=" + j + "&type=" + replayType)));
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartCatchupPlayback.6
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                BaseFragmentActivity baseFragmentActivity3 = baseFragmentActivity;
                if (baseFragmentActivity3 != null) {
                    baseFragmentActivity3.setLoaderVisible(false);
                }
                Log.w(PlaybackActivityKt.TAG, "Get playback infos failed:", th);
                Activity activity2 = activity;
                if (activity2 instanceof BaseFragmentActivity) {
                    BaseFragmentActivity baseFragmentActivity4 = (BaseFragmentActivity) activity2;
                    if (th == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Exception /* = java.lang.Exception */");
                    }
                    baseFragmentActivity4.displayErrorMessage((Exception) th, 1);
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "PortalApplication.getSin…         }\n            })");
        resumeDisposable = LifecycleAwareDisposableKt.addTo(disposableSubscribe, baseFragmentActivity != null ? baseFragmentActivity.getDisposable() : null);
    }

    public static final void displayResumePopupOrStartPVRPlayback(final Activity activity, final String recordingId, final Function0<Unit> onStart) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(recordingId, "recordingId");
        Intrinsics.checkParameterIsNotNull(onStart, "onStart");
        Disposable disposable = resumeDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        final BaseFragmentActivity baseFragmentActivity = activity instanceof BaseFragmentActivity ? (BaseFragmentActivity) activity : null;
        if (baseFragmentActivity != null) {
            baseFragmentActivity.setLoaderVisible(true);
        }
        PVRService pvrService = PortalApplication.INSTANCE.getSingleton(activity).getBOService().getPvrService();
        if (pvrService == null) {
            Intrinsics.throwNpe();
        }
        if (pvrService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacablePVRService");
        }
        Disposable disposableSubscribe = ((MegacablePVRService) pvrService).recordingTickets(PVRStorageMode.Network).filter(new Predicate<TVRecording>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartPVRPlayback.1
            @Override // io.reactivex.functions.Predicate
            public final boolean test(TVRecording it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return Intrinsics.areEqual(it.getId(), recordingId);
            }
        }).firstElement().flatMap(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartPVRPlayback.2
            @Override // io.reactivex.functions.Function
            public final Maybe<Media> apply(TVRecording it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getMedia();
            }
        }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartPVRPlayback.3
            @Override // io.reactivex.functions.Function
            public final Single<Integer> apply(Media it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                UserService userService = PortalApplication.INSTANCE.getSingleton(activity).getBOService().getUserService();
                if (userService != null) {
                    return ((MegacableUserService) userService).getMediaPosition(it);
                }
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Integer>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartPVRPlayback.4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Integer num) {
                PlaybackActivityKt.resumeDisposable = (Disposable) null;
                BaseFragmentActivity baseFragmentActivity2 = baseFragmentActivity;
                if (baseFragmentActivity2 != null) {
                    baseFragmentActivity2.setLoaderVisible(false);
                }
                if (Intrinsics.compare(num.intValue(), 0) > 0) {
                    new PortalDialog.Builder(activity).setTitle(R.string.resume_playback_title).setPositiveButton(R.string.resume_playback_yes, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartPVRPlayback.4.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            onStart.invoke();
                            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/player?recording_id=" + recordingId + "&resume=true")));
                        }
                    }).setNegativeButton(R.string.resume_playback_no, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartPVRPlayback.4.2
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            onStart.invoke();
                            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/player?recording_id=" + recordingId + "&resume=false")));
                        }
                    }).create().show();
                    return;
                }
                onStart.invoke();
                activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("megacable://com.dotscreen.megacable.portal/player?recording_id=" + recordingId)));
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.player.PlaybackActivityKt.displayResumePopupOrStartPVRPlayback.5
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                BaseFragmentActivity baseFragmentActivity2 = baseFragmentActivity;
                if (baseFragmentActivity2 != null) {
                    baseFragmentActivity2.setLoaderVisible(false);
                }
                Log.w(PlaybackActivityKt.TAG, "Get playback infos failed:", th);
                Activity activity2 = activity;
                if (activity2 instanceof BaseFragmentActivity) {
                    BaseFragmentActivity baseFragmentActivity3 = (BaseFragmentActivity) activity2;
                    if (th == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Exception /* = java.lang.Exception */");
                    }
                    baseFragmentActivity3.displayErrorMessage((Exception) th, 1);
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "(PortalApplication.getSi…         }\n            })");
        resumeDisposable = LifecycleAwareDisposableKt.addTo(disposableSubscribe, baseFragmentActivity != null ? baseFragmentActivity.getDisposable() : null);
    }
}
