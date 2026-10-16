package com.dotscreen.portal.model;

import android.content.Context;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.ItemExternalIdScheme;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.MegacableVODCatalogService;
import com.dotscreen.tv.common.utils.RxViewModel;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HomeVODRecommendationsViewModel.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0002\u0010\u0011B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u000fH\u0014R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/portal/model/HomeVODRecommendationsViewModel;", "Lcom/dotscreen/tv/common/utils/RxViewModel;", "", "Lcom/dotscreen/bokit/model/BrowsableItem;", "context", "Landroid/content/Context;", "blend", "", "(Landroid/content/Context;Ljava/lang/String;)V", "TAG", "getTAG", "()Ljava/lang/String;", "getContext", "()Landroid/content/Context;", "getSingle", "Lio/reactivex/Single;", "Factory", "Result", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class HomeVODRecommendationsViewModel extends RxViewModel<List<? extends BrowsableItem>> {
    private final String TAG;
    private final String blend;
    private final Context context;

    /* JADX INFO: compiled from: HomeVODRecommendationsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0006J\u000f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J)\u0010\f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/dotscreen/portal/model/HomeVODRecommendationsViewModel$Result;", "", "mosaicRecommendations", "Lcom/dotscreen/bokit/model/ItemCollection;", "Lcom/dotscreen/bokit/model/BrowsableItem;", "rowRecommendations", "(Lcom/dotscreen/bokit/model/ItemCollection;Lcom/dotscreen/bokit/model/ItemCollection;)V", "getMosaicRecommendations", "()Lcom/dotscreen/bokit/model/ItemCollection;", "getRowRecommendations", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final /* data */ class Result {
        private final ItemCollection<BrowsableItem> mosaicRecommendations;
        private final ItemCollection<BrowsableItem> rowRecommendations;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Result copy$default(Result result, ItemCollection itemCollection, ItemCollection itemCollection2, int i, Object obj) {
            if ((i & 1) != 0) {
                itemCollection = result.mosaicRecommendations;
            }
            if ((i & 2) != 0) {
                itemCollection2 = result.rowRecommendations;
            }
            return result.copy(itemCollection, itemCollection2);
        }

        public final ItemCollection<BrowsableItem> component1() {
            return this.mosaicRecommendations;
        }

        public final ItemCollection<BrowsableItem> component2() {
            return this.rowRecommendations;
        }

        public final Result copy(ItemCollection<BrowsableItem> mosaicRecommendations, ItemCollection<BrowsableItem> rowRecommendations) {
            Intrinsics.checkParameterIsNotNull(mosaicRecommendations, "mosaicRecommendations");
            Intrinsics.checkParameterIsNotNull(rowRecommendations, "rowRecommendations");
            return new Result(mosaicRecommendations, rowRecommendations);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return Intrinsics.areEqual(this.mosaicRecommendations, result.mosaicRecommendations) && Intrinsics.areEqual(this.rowRecommendations, result.rowRecommendations);
        }

        public int hashCode() {
            ItemCollection<BrowsableItem> itemCollection = this.mosaicRecommendations;
            int iHashCode = (itemCollection != null ? itemCollection.hashCode() : 0) * 31;
            ItemCollection<BrowsableItem> itemCollection2 = this.rowRecommendations;
            return iHashCode + (itemCollection2 != null ? itemCollection2.hashCode() : 0);
        }

        public String toString() {
            return "Result(mosaicRecommendations=" + this.mosaicRecommendations + ", rowRecommendations=" + this.rowRecommendations + ")";
        }

        public Result(ItemCollection<BrowsableItem> mosaicRecommendations, ItemCollection<BrowsableItem> rowRecommendations) {
            Intrinsics.checkParameterIsNotNull(mosaicRecommendations, "mosaicRecommendations");
            Intrinsics.checkParameterIsNotNull(rowRecommendations, "rowRecommendations");
            this.mosaicRecommendations = mosaicRecommendations;
            this.rowRecommendations = rowRecommendations;
        }

        public final ItemCollection<BrowsableItem> getMosaicRecommendations() {
            return this.mosaicRecommendations;
        }

        public final ItemCollection<BrowsableItem> getRowRecommendations() {
            return this.rowRecommendations;
        }
    }

    public HomeVODRecommendationsViewModel(Context context, String blend) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(blend, "blend");
        this.context = context;
        this.blend = blend;
        this.TAG = HomeVODRecommendationsViewModel.class.getSimpleName();
    }

    public final Context getContext() {
        return this.context;
    }

    public final String getTAG() {
        return this.TAG;
    }

    /* JADX INFO: compiled from: HomeVODRecommendationsViewModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J'\u0010\u0007\u001a\u0002H\b\"\n\b\u0000\u0010\b*\u0004\u0018\u00010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/model/HomeVODRecommendationsViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "blend", "", "(Landroid/content/Context;Ljava/lang/String;)V", "create", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final String blend;
        private final Context context;

        public Factory(Context context, String blend) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(blend, "blend");
            this.context = context;
            this.blend = blend;
        }

        @Override // androidx.lifecycle.ViewModelProvider.NewInstanceFactory, androidx.lifecycle.ViewModelProvider.Factory
        public <T extends ViewModel> T create(Class<T> modelClass) {
            Intrinsics.checkParameterIsNotNull(modelClass, "modelClass");
            return new HomeVODRecommendationsViewModel(this.context, this.blend);
        }
    }

    @Override // com.dotscreen.tv.common.utils.RxViewModel
    protected Single<List<? extends BrowsableItem>> getSingle() {
        VODCatalogService vodCatalogService = PortalApplication.INSTANCE.getSingleton(this.context).getBOService().getVodCatalogService();
        if (vodCatalogService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
        }
        Single<List<? extends BrowsableItem>> singleOnErrorReturn = ((MegacableVODCatalogService) vodCatalogService).getRecommendations(this.blend, (26 & 2) != 0 ? (String) null : null, (26 & 4) != 0 ? (String) null : null, (26 & 8) != 0 ? 10 : 0, (26 & 16) != 0 ? false : false, (26 & 32) == 0 ? false : false).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.model.HomeVODRecommendationsViewModel.getSingle.1
            @Override // io.reactivex.functions.Function
            public final Observable<BrowsableItem> apply(ItemCollection<BrowsableItem> it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).filter(new Predicate<BrowsableItem>() { // from class: com.dotscreen.portal.model.HomeVODRecommendationsViewModel.getSingle.2
            @Override // io.reactivex.functions.Predicate
            public final boolean test(BrowsableItem it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.externalId(ItemExternalIdScheme.Netflix) == null;
            }
        }).toList().onErrorReturn(new Function<Throwable, List<BrowsableItem>>() { // from class: com.dotscreen.portal.model.HomeVODRecommendationsViewModel.getSingle.3
            @Override // io.reactivex.functions.Function
            public final List<BrowsableItem> apply(Throwable it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return CollectionsKt.emptyList();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(singleOnErrorReturn, "vodCatalogService.getRec…nErrorReturn { listOf() }");
        return singleOnErrorReturn;
    }
}
