package com.dotscreen.portal.ui.screens.search;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.farkit.rx.LifecycleAwareDisposableKt;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.LiveProgramsSearchResponse;
import com.dotscreen.tv.common.bokit.MegacableLiveTVService;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.MegacableVODCatalogService;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\u000b\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/dotscreen/portal/ui/screens/search/SearchActivity$handler$1", "Landroid/os/Handler;", "handleMessage", "", NotificationCompat.CATEGORY_MESSAGE, "Landroid/os/Message;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SearchActivity$handler$1 extends Handler {
    final /* synthetic */ SearchActivity this$0;

    SearchActivity$handler$1(SearchActivity searchActivity) {
        this.this$0 = searchActivity;
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        Integer numValueOf = msg != null ? Integer.valueOf(msg.what) : null;
        int i = this.this$0.MSG_START_SEARCH;
        if (numValueOf != null && numValueOf.intValue() == i) {
            Log.d(this.this$0.TAG, "MSG_START_SEARCH");
            this.this$0.setLoaderVisible(true);
            Disposable disposable = this.this$0.searchDisposable;
            if (disposable != null) {
                disposable.dispose();
            }
            Object obj = msg.obj;
            if (obj == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            String str = (String) obj;
            LiveTVService liveTVService = PortalApplication.INSTANCE.getSingleton(this.this$0).getBOService().getLiveTVService();
            if (liveTVService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
            }
            Single<LiveProgramsSearchResponse> singleSearchProgram = ((MegacableLiveTVService) liveTVService).searchProgram(str);
            VODCatalogService vodCatalogService = PortalApplication.INSTANCE.getSingleton(this.this$0).getBOService().getVodCatalogService();
            if (vodCatalogService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
            }
            Single<ItemCollection<Content>> singleCompassSearch = ((MegacableVODCatalogService) vodCatalogService).compassSearch(str);
            SearchActivity searchActivity = this.this$0;
            Disposable disposableSubscribe = Single.zip(singleSearchProgram, singleCompassSearch, new BiFunction<LiveProgramsSearchResponse, ItemCollection<Content>, List<? extends Object>>() { // from class: com.dotscreen.portal.ui.screens.search.SearchActivity$handler$1$handleMessage$1
                @Override // io.reactivex.functions.BiFunction
                public final List<Object> apply(LiveProgramsSearchResponse programSearchResponse, ItemCollection<Content> vodSearchResponse) {
                    Intrinsics.checkParameterIsNotNull(programSearchResponse, "programSearchResponse");
                    Intrinsics.checkParameterIsNotNull(vodSearchResponse, "vodSearchResponse");
                    return CollectionsKt.listOf(programSearchResponse, vodSearchResponse);
                }
            }).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.search.SearchActivity$handler$1$handleMessage$2
                @Override // io.reactivex.functions.Function
                public final Single<List<Object>> apply(List<? extends Object> result) {
                    Intrinsics.checkParameterIsNotNull(result, "result");
                    Object obj2 = result.get(0);
                    if (obj2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.LiveProgramsSearchResponse");
                    }
                    Single list = ((LiveProgramsSearchResponse) obj2).getAirings().getItems().toList();
                    Object obj3 = result.get(0);
                    if (obj3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.LiveProgramsSearchResponse");
                    }
                    Single list2 = ((LiveProgramsSearchResponse) obj3).getCatchups().getItems().toList();
                    Object obj4 = result.get(1);
                    if (obj4 != null) {
                        return Single.zip(list, list2, ((ItemCollection) obj4).getItems().toList(), new Function3<List<? extends TVAiring>, List<? extends BrowsableItem>, List<? extends BrowsableItem>, List<? extends Object>>() { // from class: com.dotscreen.portal.ui.screens.search.SearchActivity$handler$1$handleMessage$2.1
                            @Override // io.reactivex.functions.Function3
                            public final List<List<BrowsableItem>> apply(List<? extends TVAiring> airings, List<? extends BrowsableItem> catchups, List<? extends BrowsableItem> vods) {
                                Intrinsics.checkParameterIsNotNull(airings, "airings");
                                Intrinsics.checkParameterIsNotNull(catchups, "catchups");
                                Intrinsics.checkParameterIsNotNull(vods, "vods");
                                return CollectionsKt.listOf((Object[]) new List[]{airings, catchups, vods});
                            }
                        });
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.ItemCollection<com.dotscreen.bokit.model.Content>");
                }
            }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<? extends Object>>() { // from class: com.dotscreen.portal.ui.screens.search.SearchActivity$handler$1$handleMessage$3
                @Override // io.reactivex.functions.Consumer
                public final void accept(List<? extends Object> list) {
                    Object obj2 = list.get(0);
                    if (obj2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.dotscreen.bokit.model.TVAiring>");
                    }
                    List<? extends TVAiring> list2 = (List) obj2;
                    Object obj3 = list.get(1);
                    if (obj3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.dotscreen.bokit.model.TVAiring>");
                    }
                    List<? extends BrowsableItem> list3 = (List) obj3;
                    Object obj4 = list.get(2);
                    if (obj4 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.dotscreen.bokit.model.Content>");
                    }
                    List<? extends BrowsableItem> list4 = (List) obj4;
                    View viewFindViewById = this.this$0.this$0.findViewById(R.id.no_result);
                    if (viewFindViewById != null) {
                        viewFindViewById.setVisibility((list2.isEmpty() && list3.isEmpty() && list4.isEmpty()) ? 0 : 8);
                    }
                    SearchFragment searchFragment = this.this$0.this$0.searchFragment;
                    if (searchFragment != null) {
                        searchFragment.setAirings(list2);
                    }
                    SearchFragment searchFragment2 = this.this$0.this$0.searchFragment;
                    if (searchFragment2 != null) {
                        searchFragment2.setCatchups(list3);
                    }
                    SearchFragment searchFragment3 = this.this$0.this$0.searchFragment;
                    if (searchFragment3 != null) {
                        searchFragment3.setVods(list4);
                    }
                    this.this$0.this$0.setLoaderVisible(false);
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.search.SearchActivity$handler$1$handleMessage$4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    th.printStackTrace();
                    View viewFindViewById = this.this$0.this$0.findViewById(R.id.no_result);
                    if (viewFindViewById != null) {
                        viewFindViewById.setVisibility(0);
                    }
                    this.this$0.this$0.setLoaderVisible(false);
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "Single.zip(\n            …                       })");
            searchActivity.searchDisposable = LifecycleAwareDisposableKt.addTo(disposableSubscribe, this.this$0.getDisposable());
            return;
        }
        int i2 = this.this$0.TRIGGER_AUTO_COMPLETE;
        if (numValueOf != null && numValueOf.intValue() == i2) {
            Object obj2 = msg.obj;
            if (obj2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            String str2 = (String) obj2;
            UserService userService = PortalApplication.INSTANCE.getSingleton(this.this$0).getBOService().getUserService();
            if (userService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
            }
            Disposable disposableSubscribe2 = ((MegacableUserService) userService).searchSuggestion(str2).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).toList().subscribe(new BiConsumer<List<Content>, Throwable>() { // from class: com.dotscreen.portal.ui.screens.search.SearchActivity$handler$1$handleMessage$5
                @Override // io.reactivex.functions.BiConsumer
                public final void accept(List<Content> list, Throwable th) {
                    if (th == null) {
                        Log.e(this.this$0.this$0.TAG, "Suggestions => " + list);
                        SearchActivity.AutoSuggestAdapter autoSuggestAdapter = this.this$0.this$0.autoSuggestAdapter;
                        if (autoSuggestAdapter != null) {
                            autoSuggestAdapter.setData(list);
                        }
                        SearchActivity.AutoSuggestAdapter autoSuggestAdapter2 = this.this$0.this$0.autoSuggestAdapter;
                        if (autoSuggestAdapter2 != null) {
                            autoSuggestAdapter2.notifyDataSetChanged();
                        }
                        this.this$0.this$0.buildImeCompletions();
                    }
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe2, "(PortalApplication.getSi…                        }");
            LifecycleAwareDisposableKt.addTo(disposableSubscribe2, this.this$0.getDisposable());
        }
    }
}
