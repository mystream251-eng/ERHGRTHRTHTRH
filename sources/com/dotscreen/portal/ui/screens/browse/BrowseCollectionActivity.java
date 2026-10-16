package com.dotscreen.portal.ui.screens.browse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentTransaction;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.CatalogSection;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.farkit.rx.LifecycleAwareDisposableKt;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.Constants;
import com.dotscreen.portal.PortalApplicationSingletons;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.io.InterruptedIOException;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrowseCollectionActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\r\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u000bH\u0002J\u0012\u0010\u0010\u001a\u00020\u000b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/portal/ui/screens/browse/BrowseCollectionActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "adultBrowsing", "", "catalogId", "collectionId", "loadVodCatalog", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "openMosaic", "setTitle", "title", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class BrowseCollectionActivity extends BaseFragmentActivity {
    private final String TAG = BrowseCollectionActivity.class.getSimpleName();
    private HashMap _$_findViewCache;
    private boolean adultBrowsing;
    private String catalogId;
    private String collectionId;

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity
    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity
    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View viewFindViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x006c  */
    /* JADX WARN: Code duplicated, block: B:31:0x0070  */
    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        String str;
        String queryParameter;
        super.onCreate(savedInstanceState);
        if (getIntent() != null) {
            Intent intent = getIntent();
            Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
            Uri data = intent.getData();
            this.catalogId = data != null ? data.getQueryParameter("catalog_id") : null;
            this.collectionId = data != null ? data.getQueryParameter("collection_id") : null;
            this.adultBrowsing = (data == null || (queryParameter = data.getQueryParameter("adult_browsing")) == null) ? false : Boolean.parseBoolean(queryParameter);
            if (this.catalogId != null && (str = this.collectionId) != null) {
                if (str == null) {
                    Intrinsics.throwNpe();
                }
                if (!Intrinsics.areEqual(str, "null")) {
                    if (Intrinsics.areEqual(this.collectionId, Constants.INSTANCE.getHOME_HIGHLIGHT_CATALOG_ID())) {
                        openMosaic();
                    } else {
                        loadVodCatalog();
                    }
                } else if (this.catalogId != null) {
                    openMosaic();
                }
            } else if (this.catalogId != null) {
                openMosaic();
            }
        }
        setContentView(R.layout.activity_browse_collection);
    }

    private final void loadVodCatalog() {
        setLoaderVisible(true);
        Object applicationContext = getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplicationSingletons");
        }
        VODCatalogService vodCatalogService = ((PortalApplicationSingletons) applicationContext).getBOService().getVodCatalogService();
        String str = this.catalogId;
        if (str == null) {
            Intrinsics.throwNpe();
        }
        String str2 = this.collectionId;
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        Disposable disposableSubscribe = vodCatalogService.sectionById(str, str2).flatMapSingle(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity.loadVodCatalog.1
            @Override // io.reactivex.functions.Function
            public final Single<Pair<ItemCollection<BrowsableItem>, Boolean>> apply(CatalogSection it) {
                Single<Boolean> singleJust;
                Intrinsics.checkParameterIsNotNull(it, "it");
                Single singleJust2 = Single.just(it);
                Observable<ItemCollection<BrowsableItem>> children = it.getChildren();
                if (children == null || (singleJust = children.isEmpty()) == null) {
                    singleJust = Single.just(true);
                }
                return Single.zip(singleJust2, singleJust, new BiFunction<ItemCollection<BrowsableItem>, Boolean, Pair<? extends ItemCollection<BrowsableItem>, ? extends Boolean>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity.loadVodCatalog.1.1
                    @Override // io.reactivex.functions.BiFunction
                    public final Pair<ItemCollection<BrowsableItem>, Boolean> apply(ItemCollection<BrowsableItem> col, Boolean empty) {
                        Intrinsics.checkParameterIsNotNull(col, "col");
                        Intrinsics.checkParameterIsNotNull(empty, "empty");
                        return new Pair<>(col, empty);
                    }
                });
            }
        }).retry(new Predicate<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity.loadVodCatalog.2
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Throwable error) {
                Intrinsics.checkParameterIsNotNull(error, "error");
                return error.getCause() instanceof InterruptedIOException;
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Pair<? extends ItemCollection<BrowsableItem>, ? extends Boolean>>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity.loadVodCatalog.3
            @Override // io.reactivex.functions.Consumer
            public /* bridge */ /* synthetic */ void accept(Pair<? extends ItemCollection<BrowsableItem>, ? extends Boolean> pair) {
                accept2((Pair<? extends ItemCollection<BrowsableItem>, Boolean>) pair);
            }

            /* JADX INFO: renamed from: accept, reason: avoid collision after fix types in other method */
            public final void accept2(Pair<? extends ItemCollection<BrowsableItem>, Boolean> pair) {
                BrowseCollectionActivity.this.setLoaderVisible(false);
                try {
                    if (pair.getSecond().booleanValue()) {
                        BrowseMosaicFragment browseMosaicFragmentNewInstance = BrowseMosaicFragment.INSTANCE.newInstance(BrowseCollectionActivity.this.catalogId, BrowseCollectionActivity.this.collectionId, BrowseCollectionActivity.this.adultBrowsing);
                        FragmentTransaction fragmentTransactionBeginTransaction = BrowseCollectionActivity.this.getSupportFragmentManager().beginTransaction();
                        Intrinsics.checkExpressionValueIsNotNull(fragmentTransactionBeginTransaction, "supportFragmentManager.beginTransaction()");
                        fragmentTransactionBeginTransaction.replace(R.id.browse_grid_dock, browseMosaicFragmentNewInstance);
                        fragmentTransactionBeginTransaction.commit();
                        return;
                    }
                    BrowseCollectionFragment.Companion companion = BrowseCollectionFragment.INSTANCE;
                    String str3 = BrowseCollectionActivity.this.catalogId;
                    if (str3 == null) {
                        Intrinsics.throwNpe();
                    }
                    BrowseCollectionFragment browseCollectionFragmentNewInstance = companion.newInstance(str3, BrowseCollectionActivity.this.collectionId, BrowseCollectionActivity.this.adultBrowsing);
                    FragmentTransaction fragmentTransactionBeginTransaction2 = BrowseCollectionActivity.this.getSupportFragmentManager().beginTransaction();
                    Intrinsics.checkExpressionValueIsNotNull(fragmentTransactionBeginTransaction2, "supportFragmentManager.beginTransaction()");
                    fragmentTransactionBeginTransaction2.replace(R.id.browse_grid_dock, browseCollectionFragmentNewInstance);
                    fragmentTransactionBeginTransaction2.commit();
                } catch (Exception unused) {
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.browse.BrowseCollectionActivity.loadVodCatalog.4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.e(BrowseCollectionActivity.this.TAG, "Error while loading VOD", th);
                BrowseCollectionActivity browseCollectionActivity = BrowseCollectionActivity.this;
                Toast.makeText(browseCollectionActivity, browseCollectionActivity.getString(R.string.technical_error), 1).show();
                BrowseCollectionActivity.this.finish();
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "(applicationContext as P…nish()\n                })");
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, getDisposable());
    }

    private final void openMosaic() {
        setLoaderVisible(true);
        BrowseMosaicFragment browseMosaicFragmentNewInstance = BrowseMosaicFragment.INSTANCE.newInstance(this.catalogId, this.collectionId, this.adultBrowsing);
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        Intrinsics.checkExpressionValueIsNotNull(fragmentTransactionBeginTransaction, "supportFragmentManager.beginTransaction()");
        fragmentTransactionBeginTransaction.replace(R.id.browse_grid_dock, browseMosaicFragmentNewInstance);
        fragmentTransactionBeginTransaction.commit();
    }

    @Override // android.app.Activity
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        TextView textView = (TextView) findViewById(R.id.title);
        if (textView != null) {
            textView.setText(title);
        }
    }
}
