package com.dotscreen.portal.model;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.PurchasableItem;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVRecording;
import com.dotscreen.bokit.model.User;
import com.dotscreen.bokit.services.PVRService;
import com.dotscreen.bokit.services.PVRStorageMode;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.portal.Constants;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.PortalApplicationSingletons;
import com.dotscreen.portal.utils.MegaNotificationPublisher;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.RightVChannel;
import com.dotscreen.tv.common.bokit.RightVUser;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MyContentsViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001:\u0001PB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001b\u0010>\u001a\u00020\u0005\"\u0004\b\u0000\u0010?2\u0006\u0010@\u001a\u0002H?H\u0002¢\u0006\u0002\u0010AJ\u0006\u0010B\u001a\u00020CJ\u0006\u0010D\u001a\u00020CJ\u0006\u0010E\u001a\u00020CJ\u0006\u0010F\u001a\u00020CJ\u0006\u0010G\u001a\u00020CJ\u0006\u0010H\u001a\u00020CJ\b\u0010I\u001a\u00020CH\u0002J\b\u0010J\u001a\u00020CH\u0002J\b\u0010K\u001a\u00020CH\u0002J\b\u0010L\u001a\u00020CH\u0002J\b\u0010M\u001a\u00020CH\u0002J\b\u0010N\u001a\u00020CH\u0002J\b\u0010O\u001a\u00020CH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\t0\b8F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\fR\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0010\"\u0004\b\u001a\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\fR\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\t0\b8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\fR\u001c\u0010 \u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0010\"\u0004\b\"\u0010\u0012R\u001d\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\t0\b8F¢\u0006\u0006\u001a\u0004\b%\u0010\fR\u001c\u0010&\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0010\"\u0004\b(\u0010\u0012R\u001a\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\t0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010,\u001a\u0010\u0012\f\u0012\n -*\u0004\u0018\u00010\u00050\u00050*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\t0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\t0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002020\t0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\t0*X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000\t0\b8F¢\u0006\u0006\u001a\u0004\b5\u0010\fR\u001c\u00106\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0010\"\u0004\b8\u0010\u0012R\u001d\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002020\t0\b8F¢\u0006\u0006\u001a\u0004\b:\u0010\fR\u001c\u0010;\u001a\u0004\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u0010\"\u0004\b=\u0010\u0012¨\u0006Q"}, d2 = {"Lcom/dotscreen/portal/model/MyContentsViewModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "adultBrowsing", "", "(Landroid/content/Context;Z)V", "bookmarks", "Landroidx/lifecycle/LiveData;", "", "Lcom/dotscreen/bokit/model/Media;", "getBookmarks", "()Landroidx/lifecycle/LiveData;", "bookmarksDisposable", "Lio/reactivex/disposables/Disposable;", "getBookmarksDisposable", "()Lio/reactivex/disposables/Disposable;", "setBookmarksDisposable", "(Lio/reactivex/disposables/Disposable;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "compras", "Lcom/dotscreen/bokit/model/PurchasableItem;", "getCompras", "comprasDisposable", "getComprasDisposable", "setComprasDisposable", "error", "getError", "favoriteChannels", "Lcom/dotscreen/bokit/model/Channel;", "getFavoriteChannels", "favoriteChannelsDisposable", "getFavoriteChannelsDisposable", "setFavoriteChannelsDisposable", "favoriteVOD", "Lcom/dotscreen/bokit/model/BrowsableItem;", "getFavoriteVOD", "favoritesVODDisposable", "getFavoritesVODDisposable", "setFavoritesVODDisposable", "internalBookmarks", "Landroidx/lifecycle/MutableLiveData;", "internalCompras", "internalError", "kotlin.jvm.PlatformType", "internalFavoriteChannels", "internalRecordings", "Lcom/dotscreen/bokit/model/TVRecording;", "internalReminders", "Lcom/dotscreen/bokit/model/TVAiring;", "internalfavoritesVOD", "recordings", "getRecordings", "recordingsDisposable", "getRecordingsDisposable", "setRecordingsDisposable", "reminders", "getReminders", "remindersDisposable", "getRemindersDisposable", "setRemindersDisposable", "filterAdultContent", ExifInterface.GPS_DIRECTION_TRUE, "item", "(Ljava/lang/Object;)Z", "invalidateBookmarks", "", "invalidateCompras", "invalidateFavoriteChannels", "invalidateFavoriteVOD", "invalidateRecordings", "invalidateReminders", "loadBookmarks", "loadCompras", "loadFavoriteChannels", "loadRecordings", "loadReminders", "loadVODChannels", "onCleared", "Factory", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class MyContentsViewModel extends ViewModel {
    private final boolean adultBrowsing;
    private Disposable bookmarksDisposable;
    private CompositeDisposable compositeDisposable;
    private Disposable comprasDisposable;
    private final Context context;
    private final LiveData<Boolean> error;
    private Disposable favoriteChannelsDisposable;
    private Disposable favoritesVODDisposable;
    private final MutableLiveData<List<Media>> internalBookmarks;
    private final MutableLiveData<List<PurchasableItem>> internalCompras;
    private final MutableLiveData<Boolean> internalError;
    private final MutableLiveData<List<Channel>> internalFavoriteChannels;
    private final MutableLiveData<List<TVRecording>> internalRecordings;
    private final MutableLiveData<List<TVAiring>> internalReminders;
    private final MutableLiveData<List<BrowsableItem>> internalfavoritesVOD;
    private Disposable recordingsDisposable;
    private Disposable remindersDisposable;

    public MyContentsViewModel(Context context, boolean z) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.adultBrowsing = z;
        this.compositeDisposable = new CompositeDisposable();
        this.internalFavoriteChannels = new MutableLiveData<>();
        this.internalRecordings = new MutableLiveData<>();
        this.internalBookmarks = new MutableLiveData<>();
        this.internalReminders = new MutableLiveData<>();
        this.internalfavoritesVOD = new MutableLiveData<>();
        this.internalCompras = new MutableLiveData<>();
        MutableLiveData<Boolean> mutableLiveData = new MutableLiveData<>(false);
        this.internalError = mutableLiveData;
        this.error = mutableLiveData;
    }

    public final Disposable getFavoriteChannelsDisposable() {
        return this.favoriteChannelsDisposable;
    }

    public final void setFavoriteChannelsDisposable(Disposable disposable) {
        this.favoriteChannelsDisposable = disposable;
    }

    public final Disposable getRecordingsDisposable() {
        return this.recordingsDisposable;
    }

    public final void setRecordingsDisposable(Disposable disposable) {
        this.recordingsDisposable = disposable;
    }

    public final Disposable getBookmarksDisposable() {
        return this.bookmarksDisposable;
    }

    public final void setBookmarksDisposable(Disposable disposable) {
        this.bookmarksDisposable = disposable;
    }

    public final Disposable getRemindersDisposable() {
        return this.remindersDisposable;
    }

    public final void setRemindersDisposable(Disposable disposable) {
        this.remindersDisposable = disposable;
    }

    public final Disposable getFavoritesVODDisposable() {
        return this.favoritesVODDisposable;
    }

    public final void setFavoritesVODDisposable(Disposable disposable) {
        this.favoritesVODDisposable = disposable;
    }

    public final Disposable getComprasDisposable() {
        return this.comprasDisposable;
    }

    public final void setComprasDisposable(Disposable disposable) {
        this.comprasDisposable = disposable;
    }

    public final LiveData<Boolean> getError() {
        return this.error;
    }

    /* JADX INFO: compiled from: MyContentsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J'\u0010\u0007\u001a\u0002H\b\"\n\b\u0000\u0010\b*\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/model/MyContentsViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "adultBrowsing", "", "(Landroid/content/Context;Z)V", "create", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final boolean adultBrowsing;
        private final Context context;

        public Factory(Context context, boolean z) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.context = context;
            this.adultBrowsing = z;
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            Intrinsics.checkParameterIsNotNull(modelClass, "modelClass");
            return new MyContentsViewModel(this.context, this.adultBrowsing);
        }
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        super.onCleared();
        this.compositeDisposable.dispose();
    }

    private final void loadFavoriteChannels() {
        UserService userService = PortalApplication.INSTANCE.getSingleton(this.context).getBOService().getUserService();
        if (userService == null) {
            Intrinsics.throwNpe();
        }
        Single singleFlatMapSingle = userService.getActiveUser().map(new Function<T, R>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadFavoriteChannels.1
            @Override // io.reactivex.functions.Function
            public final RightVUser apply(User it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return (RightVUser) it;
            }
        }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadFavoriteChannels.2
            @Override // io.reactivex.functions.Function
            public final Single<List<Channel>> apply(RightVUser user) {
                Intrinsics.checkParameterIsNotNull(user, "user");
                return user.favoritesChannels().map(new Function<T, R>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadFavoriteChannels.2.1
                    @Override // io.reactivex.functions.Function
                    public final Channel apply(RightVChannel it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return it;
                    }
                }).filter(new Predicate<Channel>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadFavoriteChannels.2.2
                    @Override // io.reactivex.functions.Predicate
                    public final boolean test(Channel item) {
                        Intrinsics.checkParameterIsNotNull(item, "item");
                        return MyContentsViewModel.this.filterAdultContent(item);
                    }
                }).toList();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMapSingle, "app.boService.userServic…oList()\n                }");
        Disposable disposableBindToLiveData = MyContentsViewModelKt.bindToLiveData(singleFlatMapSingle, this.internalFavoriteChannels, CollectionsKt.emptyList(), this.internalError);
        this.favoriteChannelsDisposable = disposableBindToLiveData;
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        if (disposableBindToLiveData == null) {
            Intrinsics.throwNpe();
        }
        compositeDisposable.add(disposableBindToLiveData);
    }

    public final void invalidateFavoriteChannels() {
        Disposable disposable = this.favoriteChannelsDisposable;
        if (disposable != null) {
            this.compositeDisposable.remove(disposable);
            disposable.dispose();
        }
        loadFavoriteChannels();
    }

    public final LiveData<List<Channel>> getFavoriteChannels() {
        return this.internalFavoriteChannels;
    }

    private final void loadVODChannels() {
        UserService userService = PortalApplication.INSTANCE.getSingleton(this.context).getBOService().getUserService();
        if (userService == null) {
            Intrinsics.throwNpe();
        }
        Single singleFlatMapSingle = userService.getActiveUser().map(new Function<T, R>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadVODChannels.1
            @Override // io.reactivex.functions.Function
            public final RightVUser apply(User it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return (RightVUser) it;
            }
        }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadVODChannels.2
            @Override // io.reactivex.functions.Function
            public final Single<List<BrowsableItem>> apply(RightVUser user) {
                Intrinsics.checkParameterIsNotNull(user, "user");
                return user.favoritesVOD().map(new Function<T, R>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadVODChannels.2.1
                    @Override // io.reactivex.functions.Function
                    public final BrowsableItem apply(BrowsableItem it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return it;
                    }
                }).filter(new Predicate<BrowsableItem>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadVODChannels.2.2
                    @Override // io.reactivex.functions.Predicate
                    public final boolean test(BrowsableItem item) {
                        Intrinsics.checkParameterIsNotNull(item, "item");
                        return MyContentsViewModel.this.filterAdultContent(item);
                    }
                }).toList();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMapSingle, "app.boService.userServic…oList()\n                }");
        Disposable disposableBindToLiveData = MyContentsViewModelKt.bindToLiveData(singleFlatMapSingle, this.internalfavoritesVOD, CollectionsKt.emptyList(), this.internalError);
        this.favoritesVODDisposable = disposableBindToLiveData;
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        if (disposableBindToLiveData == null) {
            Intrinsics.throwNpe();
        }
        compositeDisposable.add(disposableBindToLiveData);
    }

    public final void invalidateFavoriteVOD() {
        Disposable disposable = this.favoritesVODDisposable;
        if (disposable != null) {
            this.compositeDisposable.remove(disposable);
            disposable.dispose();
        }
        loadVODChannels();
    }

    public final LiveData<List<BrowsableItem>> getFavoriteVOD() {
        return this.internalfavoritesVOD;
    }

    private final void loadRecordings() {
        PVRService pvrService = PortalApplication.INSTANCE.getSingleton(this.context).getBOService().getPvrService();
        if (pvrService == null) {
            Intrinsics.throwNpe();
        }
        Single<List<TVRecording>> list = pvrService.recordings(PVRStorageMode.Network).filter(new Predicate<TVRecording>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadRecordings.1
            @Override // io.reactivex.functions.Predicate
            public final boolean test(TVRecording item) {
                Intrinsics.checkParameterIsNotNull(item, "item");
                return MyContentsViewModel.this.filterAdultContent(item);
            }
        }).toList();
        Intrinsics.checkExpressionValueIsNotNull(list, "app.boService.pvrService…                .toList()");
        Disposable disposableBindToLiveData = MyContentsViewModelKt.bindToLiveData(list, this.internalRecordings, CollectionsKt.emptyList(), this.internalError);
        this.recordingsDisposable = disposableBindToLiveData;
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        if (disposableBindToLiveData == null) {
            Intrinsics.throwNpe();
        }
        compositeDisposable.add(disposableBindToLiveData);
    }

    public final void invalidateRecordings() {
        Disposable disposable = this.recordingsDisposable;
        if (disposable != null) {
            this.compositeDisposable.remove(disposable);
            disposable.dispose();
        }
        loadRecordings();
    }

    public final LiveData<List<TVRecording>> getRecordings() {
        return this.internalRecordings;
    }

    private final void loadBookmarks() {
        UserService userService = PortalApplication.INSTANCE.getSingleton(this.context).getBOService().getUserService();
        if (userService == null) {
            Intrinsics.throwNpe();
        }
        Single singleFlatMapSingle = userService.getActiveUser().map(new Function<T, R>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadBookmarks.1
            @Override // io.reactivex.functions.Function
            public final RightVUser apply(User it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return (RightVUser) it;
            }
        }).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadBookmarks.2
            @Override // io.reactivex.functions.Function
            public final Single<List<Media>> apply(RightVUser user) {
                Intrinsics.checkParameterIsNotNull(user, "user");
                return user.bookmarks(MyContentsViewModel.this.adultBrowsing).toList();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleFlatMapSingle, "app.boService.userServic…adultBrowsing).toList() }");
        Disposable disposableBindToLiveData = MyContentsViewModelKt.bindToLiveData(singleFlatMapSingle, this.internalBookmarks, CollectionsKt.emptyList(), this.internalError);
        this.bookmarksDisposable = disposableBindToLiveData;
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        if (disposableBindToLiveData == null) {
            Intrinsics.throwNpe();
        }
        compositeDisposable.add(disposableBindToLiveData);
    }

    public final void invalidateBookmarks() {
        Disposable disposable = this.bookmarksDisposable;
        if (disposable != null) {
            this.compositeDisposable.remove(disposable);
            disposable.dispose();
        }
        loadBookmarks();
    }

    public final LiveData<List<Media>> getBookmarks() {
        return this.internalBookmarks;
    }

    private final void loadReminders() {
        final PortalApplicationSingletons singleton = PortalApplication.INSTANCE.getSingleton(this.context);
        UserService userService = singleton.getBOService().getUserService();
        if (userService == null) {
            Intrinsics.throwNpe();
        }
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        Single map = ((MegacableUserService) userService).getReminderString().map((Function) new Function<T, R>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadReminders.1
            @Override // io.reactivex.functions.Function
            public final String apply(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                long time = new Date().getTime();
                try {
                    JSONArray jSONArray = new JSONArray(it);
                    JSONArray jSONArray2 = new JSONArray(it);
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        Object obj = jSONArray.get(i);
                        if (obj == null) {
                            throw new TypeCastException("null cannot be cast to non-null type org.json.JSONObject");
                        }
                        JSONObject jSONObject = (JSONObject) obj;
                        long j = jSONObject.getLong("startDate") * ((long) 1000);
                        if (j < time) {
                            MegaNotificationPublisher.Companion companion = MegaNotificationPublisher.INSTANCE;
                            Context context = MyContentsViewModel.this.context;
                            String string = jSONObject.getString("title");
                            Intrinsics.checkExpressionValueIsNotNull(string, "tempObject.getString(\"title\")");
                            companion.removeNotification(context, string, j);
                            jSONArray2.remove(i);
                        }
                    }
                    if (!Intrinsics.areEqual(jSONArray2.toString(), jSONArray.toString())) {
                        UserService userService2 = singleton.getBOService().getUserService();
                        if (userService2 == null) {
                            Intrinsics.throwNpe();
                        }
                        if (userService2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                        }
                        String string2 = jSONArray2.toString();
                        Intrinsics.checkExpressionValueIsNotNull(string2, "_tempjson2.toString()");
                        ((MegacableUserService) userService2).updateReminder(string2).subscribe(new Consumer<JSONObject>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadReminders.1.1
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(JSONObject jSONObject2) {
                            }
                        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadReminders.1.2
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Throwable th) {
                            }
                        });
                    }
                    return jSONArray2.toString();
                } catch (Exception unused) {
                    return "[]";
                }
            }
        }).map(new Function<T, R>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadReminders.2
            @Override // io.reactivex.functions.Function
            public final List<TVAiring> apply(String it) throws JSONException {
                Intrinsics.checkParameterIsNotNull(it, "it");
                JSONArray jSONArray = new JSONArray(it);
                ArrayList arrayList = new ArrayList();
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    UserService userService2 = singleton.getBOService().getUserService();
                    if (userService2 == null) {
                        Intrinsics.throwNpe();
                    }
                    if (userService2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                    }
                    MegacableUserService megacableUserService = (MegacableUserService) userService2;
                    Object obj = jSONArray.get(i);
                    if (obj == null) {
                        throw new TypeCastException("null cannot be cast to non-null type org.json.JSONObject");
                    }
                    arrayList.add(MegacableUserService.parseReminderToTVAiring$default(megacableUserService, (JSONObject) obj, null, 2, null));
                }
                List list = CollectionsKt.toList(arrayList);
                ArrayList arrayList2 = new ArrayList();
                for (T t : list) {
                    if (MyContentsViewModel.this.filterAdultContent((TVAiring) t)) {
                        arrayList2.add(t);
                    }
                }
                return arrayList2;
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(map, "(app.boService.userServi…      }\n                }");
        Disposable disposableBindToLiveData = MyContentsViewModelKt.bindToLiveData(map, this.internalReminders, CollectionsKt.emptyList(), this.internalError);
        this.remindersDisposable = disposableBindToLiveData;
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        if (disposableBindToLiveData == null) {
            Intrinsics.throwNpe();
        }
        compositeDisposable.add(disposableBindToLiveData);
    }

    public final void invalidateReminders() {
        Disposable disposable = this.remindersDisposable;
        if (disposable != null) {
            this.compositeDisposable.remove(disposable);
            disposable.dispose();
        }
        loadReminders();
    }

    public final LiveData<List<TVAiring>> getReminders() {
        return this.internalReminders;
    }

    private final void loadCompras() {
        UserService userService = PortalApplication.INSTANCE.getSingleton(this.context).getBOService().getUserService();
        if (userService == null) {
            Intrinsics.throwNpe();
        }
        Single list = userService.getActiveUser().flatMapObservable((Function) new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadCompras.1
            @Override // io.reactivex.functions.Function
            public final Observable<PurchasableItem> apply(User it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.orderedItems().filter(new Predicate<PurchasableItem>() { // from class: com.dotscreen.portal.model.MyContentsViewModel.loadCompras.1.1
                    @Override // io.reactivex.functions.Predicate
                    public final boolean test(PurchasableItem item) {
                        Intrinsics.checkParameterIsNotNull(item, "item");
                        return MyContentsViewModel.this.filterAdultContent(item);
                    }
                });
            }
        }).toList();
        Intrinsics.checkExpressionValueIsNotNull(list, "app\n                .boS…                .toList()");
        Disposable disposableBindToLiveData = MyContentsViewModelKt.bindToLiveData(list, this.internalCompras, CollectionsKt.emptyList(), this.internalError);
        this.comprasDisposable = disposableBindToLiveData;
        CompositeDisposable compositeDisposable = this.compositeDisposable;
        if (disposableBindToLiveData == null) {
            Intrinsics.throwNpe();
        }
        compositeDisposable.add(disposableBindToLiveData);
    }

    public final void invalidateCompras() {
        Disposable disposable = this.comprasDisposable;
        if (disposable != null) {
            this.compositeDisposable.remove(disposable);
            disposable.dispose();
        }
        loadCompras();
    }

    public final LiveData<List<PurchasableItem>> getCompras() {
        return this.internalCompras;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final <T> boolean filterAdultContent(T item) {
        Constants constants = Constants.INSTANCE;
        if (item == 0) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.BrowsableItem");
        }
        boolean zItemIsAdult = constants.itemIsAdult((BrowsableItem) item);
        if (this.adultBrowsing) {
            return zItemIsAdult;
        }
        return !zItemIsAdult;
    }
}
