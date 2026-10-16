package com.dotscreen.portal.ui.screens.packages;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.leanback.widget.VerticalGridPresenter;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.dotscreen.bokit.services.PincodeType;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.PortalApplicationSingletons;
import com.dotscreen.portal.ui.cards.PackageCardPresenter;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.screens.profile.PinCodeDialog;
import com.dotscreen.portal.ui.screens.setup.SettingsActivity;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.RightVPackage;
import com.dotscreen.tv.common.bokit.RightVService;
import com.dotscreen.tv.common.bokit.SubscriptionResult;
import com.dotscreen.tv.ui.dialog.PortalDialog;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PackagesActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0013H\u0014J\u000e\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u0004J\u000e\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000fj\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0011\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000fj\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/dotscreen/portal/ui/screens/packages/PackagesActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "adapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "favoriteChannelsDisposable", "Lio/reactivex/disposables/Disposable;", "loaderProgress", "Landroid/widget/ProgressBar;", "no_data_text", "Landroid/widget/TextView;", "packageId", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "serviceId", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStop", "requestPurchase", "item", "Lcom/dotscreen/tv/common/bokit/RightVPackage;", "shelf_purchase_popup", "title", "showPinCode", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class PackagesActivity extends BaseFragmentActivity {
    private final String TAG = SettingsActivity.class.getSimpleName();
    private HashMap _$_findViewCache;
    private ArrayObjectAdapter adapter;
    private Disposable favoriteChannelsDisposable;
    private ProgressBar loaderProgress;
    private TextView no_data_text;
    private ArrayList<String> packageId;
    private ArrayList<String> serviceId;

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

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        Single<List<RightVService>> singleJust;
        List listSplit$default;
        List listSplit$default2;
        Uri data;
        Uri data2;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_packages);
        final PortalApplicationSingletons singleton = PortalApplication.INSTANCE.getSingleton(this);
        VerticalGridPresenter verticalGridPresenter = new VerticalGridPresenter(0, false);
        verticalGridPresenter.setNumberOfColumns(1);
        verticalGridPresenter.setShadowEnabled(false);
        verticalGridPresenter.setShadowEnabled(true);
        Intent intent = getIntent();
        String queryParameter = (intent == null || (data2 = intent.getData()) == null) ? null : data2.getQueryParameter(TvContractCompat.Channels.COLUMN_SERVICE_ID);
        Intent intent2 = getIntent();
        String queryParameter2 = (intent2 == null || (data = intent2.getData()) == null) ? null : data.getQueryParameter("package_id");
        this.serviceId = (ArrayList) ((queryParameter == null || (listSplit$default2 = StringsKt.split$default((CharSequence) queryParameter, new String[]{","}, false, 0, 6, (Object) null)) == null) ? null : CollectionsKt.toMutableList((Collection) listSplit$default2));
        this.packageId = (ArrayList) ((queryParameter2 == null || (listSplit$default = StringsKt.split$default((CharSequence) queryParameter2, new String[]{","}, false, 0, 6, (Object) null)) == null) ? null : CollectionsKt.toMutableList((Collection) listSplit$default));
        if (singleton.getDegradedMode()) {
            Toast.makeText(getApplicationContext(), R.string.noAvailable, 1).show();
            finish();
            return;
        }
        verticalGridPresenter.setOnItemViewClickedListener(new OnItemViewClickedListener() { // from class: com.dotscreen.portal.ui.screens.packages.PackagesActivity$onCreate$listener$1
            @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
            public final void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
                if (obj instanceof RightVPackage) {
                    RightVPackage rightVPackage = (RightVPackage) obj;
                    if (rightVPackage.getExtrafields().containsKey("Self_Subscription") && Intrinsics.areEqual(rightVPackage.getExtrafields().get("Self_Subscription"), "true")) {
                        this.this$0.showPinCode(rightVPackage);
                    } else {
                        this.this$0.shelf_purchase_popup(rightVPackage.getName());
                    }
                }
            }
        });
        this.adapter = new ArrayObjectAdapter(new PackageCardPresenter(null, null, 3, null));
        this.no_data_text = (TextView) findViewById(R.id.no_data_text);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.loaderProgress);
        this.loaderProgress = progressBar;
        if (progressBar == null) {
            Intrinsics.throwNpe();
        }
        progressBar.setVisibility(8);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.browse_grid_dock);
        final VerticalGridPresenter.ViewHolder viewHolderOnCreateViewHolder = verticalGridPresenter.onCreateViewHolder(viewGroup);
        viewGroup.addView(viewHolderOnCreateViewHolder.view);
        verticalGridPresenter.onBindViewHolder(viewHolderOnCreateViewHolder, this.adapter);
        final ArrayObjectAdapter arrayObjectAdapter = this.adapter;
        if (arrayObjectAdapter != null) {
            UserService userService = singleton.getBOService().getUserService();
            if (userService == null) {
                Intrinsics.throwNpe();
            }
            if (userService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
            }
            Single<List<RightVPackage>> list = ((MegacableUserService) userService).getRightVPackages().toList();
            UserService userService2 = singleton.getBOService().getUserService();
            if (userService2 == null) {
                Intrinsics.throwNpe();
            }
            if (userService2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
            }
            Single<List<RightVPackage>> list2 = ((MegacableUserService) userService2).getGetUserRightVPackages().toList();
            if (this.serviceId != null) {
                UserService userService3 = singleton.getBOService().getUserService();
                if (userService3 == null) {
                    Intrinsics.throwNpe();
                }
                if (userService3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                }
                singleJust = ((MegacableUserService) userService3).getRightVServices().toList();
            } else {
                singleJust = Single.just(new ArrayList());
            }
            this.favoriteChannelsDisposable = Single.zip(list, list2, singleJust, new Function3<List<RightVPackage>, List<RightVPackage>, List<RightVService>, List<RightVPackage>>() { // from class: com.dotscreen.portal.ui.screens.packages.PackagesActivity$onCreate$$inlined$let$lambda$1
                @Override // io.reactivex.functions.Function3
                public final List<RightVPackage> apply(List<RightVPackage> all, List<RightVPackage> userList, List<RightVService> serviceList) {
                    ArrayList arrayList;
                    Intrinsics.checkParameterIsNotNull(all, "all");
                    Intrinsics.checkParameterIsNotNull(userList, "userList");
                    Intrinsics.checkParameterIsNotNull(serviceList, "serviceList");
                    if (this.this$0.serviceId != null && serviceList.size() > 0) {
                        for (RightVService rightVService : serviceList) {
                            ArrayList arrayList2 = this.this$0.serviceId;
                            if (arrayList2 == null) {
                                Intrinsics.throwNpe();
                            }
                            if (arrayList2.contains(rightVService.getExternalId())) {
                                this.this$0.packageId = new ArrayList();
                                if (rightVService.getExtrafields().containsKey("Self_Subscription")) {
                                    Intrinsics.areEqual(rightVService.getExtrafields().get("Self_Subscription"), "true");
                                }
                                if (rightVService.getExtrafields().containsKey("Package_ExternalId_List") && (arrayList = this.this$0.packageId) != null) {
                                    String str = rightVService.getExtrafields().get("Package_ExternalId_List");
                                    if (str == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    arrayList.add(str);
                                }
                            }
                        }
                    }
                    ArrayList arrayList3 = new ArrayList();
                    if (userList.size() <= 0) {
                        return all;
                    }
                    Iterator<RightVPackage> it = userList.iterator();
                    while (it.hasNext()) {
                        arrayList3.add(it.next().getExternalId());
                    }
                    ArrayList arrayList4 = new ArrayList();
                    for (Object obj : all) {
                        if (!arrayList3.contains(((RightVPackage) obj).getExternalId())) {
                            arrayList4.add(obj);
                        }
                    }
                    return arrayList4;
                }
            }).subscribe(new Consumer<List<RightVPackage>>() { // from class: com.dotscreen.portal.ui.screens.packages.PackagesActivity$onCreate$$inlined$let$lambda$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(final List<RightVPackage> count) {
                    Intrinsics.checkExpressionValueIsNotNull(count, "count");
                    List<RightVPackage> list3 = count;
                    ArrayList arrayList = new ArrayList();
                    Iterator<T> it = list3.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        T next = it.next();
                        RightVPackage rightVPackage = (RightVPackage) next;
                        if (rightVPackage.getExtrafields().containsKey("Self_Subscription") && Intrinsics.areEqual(rightVPackage.getExtrafields().get("Self_Subscription"), "true")) {
                            arrayList.add(next);
                        }
                    }
                    if (arrayList.size() > 0) {
                        ArrayObjectAdapter arrayObjectAdapter2 = arrayObjectAdapter;
                        ArrayList arrayList2 = new ArrayList();
                        for (T t : list3) {
                            RightVPackage rightVPackage2 = (RightVPackage) t;
                            if (rightVPackage2.getExtrafields().containsKey("Self_Subscription") && Intrinsics.areEqual(rightVPackage2.getExtrafields().get("Self_Subscription"), "true")) {
                                arrayList2.add(t);
                            }
                        }
                        arrayObjectAdapter2.addAll(0, arrayList2);
                        final TextView textView = this.no_data_text;
                        if (textView != null) {
                            this.runOnUiThread(new Runnable() { // from class: com.dotscreen.portal.ui.screens.packages.PackagesActivity$onCreate$$inlined$let$lambda$2.1
                                @Override // java.lang.Runnable
                                public final void run() {
                                    textView.setVisibility(8);
                                    ArrayList arrayList3 = this.packageId;
                                    if ((arrayList3 != null ? arrayList3.size() : 0) > 0) {
                                        RightVPackage rightVPackage3 = (RightVPackage) null;
                                        List<RightVPackage> list4 = count;
                                        boolean z = true;
                                        if (list4 != null) {
                                            for (RightVPackage rightVPackage4 : list4) {
                                                ArrayList arrayList4 = this.packageId;
                                                if ((arrayList4 != null ? arrayList4.contains(rightVPackage4.getExternalId()) : false) && (rightVPackage3 == null || (((!rightVPackage3.getExtrafields().containsKey("Self_Subscription") || (!Intrinsics.areEqual(rightVPackage3.getExtrafields().get("Self_Subscription"), "true"))) && rightVPackage4.getExtrafields().containsKey("Self_Subscription") && Intrinsics.areEqual(rightVPackage4.getExtrafields().get("Self_Subscription"), "true")) || rightVPackage4.getPrice() > rightVPackage3.getPrice()))) {
                                                    rightVPackage3 = rightVPackage4;
                                                }
                                            }
                                        }
                                        if (rightVPackage3 == null) {
                                            ArrayList arrayList5 = this.serviceId;
                                            if (arrayList5 != null) {
                                                ArrayList arrayList6 = arrayList5;
                                                if (arrayList6 != null && !arrayList6.isEmpty()) {
                                                    z = false;
                                                }
                                                if (!z) {
                                                    VerticalGridPresenter.ViewHolder gridViewHolder = viewHolderOnCreateViewHolder;
                                                    Intrinsics.checkExpressionValueIsNotNull(gridViewHolder, "gridViewHolder");
                                                    gridViewHolder.getGridView().requestFocus();
                                                    PackagesActivity packagesActivity = this;
                                                    ArrayList arrayList7 = this.serviceId;
                                                    if (arrayList7 == null) {
                                                        Intrinsics.throwNpe();
                                                    }
                                                    Object obj = arrayList7.get(0);
                                                    Intrinsics.checkExpressionValueIsNotNull(obj, "serviceId!![0]");
                                                    packagesActivity.shelf_purchase_popup((String) obj);
                                                    return;
                                                }
                                            }
                                            VerticalGridPresenter.ViewHolder gridViewHolder2 = viewHolderOnCreateViewHolder;
                                            Intrinsics.checkExpressionValueIsNotNull(gridViewHolder2, "gridViewHolder");
                                            gridViewHolder2.getGridView().requestFocus();
                                            return;
                                        }
                                        if (rightVPackage3.getExtrafields().containsKey("Self_Subscription") && (!Intrinsics.areEqual(rightVPackage3.getExtrafields().get("Self_Subscription"), "true"))) {
                                            VerticalGridPresenter.ViewHolder gridViewHolder3 = viewHolderOnCreateViewHolder;
                                            Intrinsics.checkExpressionValueIsNotNull(gridViewHolder3, "gridViewHolder");
                                            gridViewHolder3.getGridView().requestFocus();
                                            this.shelf_purchase_popup(rightVPackage3.getName());
                                            return;
                                        }
                                        VerticalGridPresenter.ViewHolder gridViewHolder4 = viewHolderOnCreateViewHolder;
                                        Intrinsics.checkExpressionValueIsNotNull(gridViewHolder4, "gridViewHolder");
                                        gridViewHolder4.getGridView().scrollToPosition(count.indexOf(rightVPackage3));
                                        VerticalGridPresenter.ViewHolder gridViewHolder5 = viewHolderOnCreateViewHolder;
                                        Intrinsics.checkExpressionValueIsNotNull(gridViewHolder5, "gridViewHolder");
                                        gridViewHolder5.getGridView().requestFocus();
                                        this.showPinCode(rightVPackage3);
                                    }
                                }
                            });
                            return;
                        }
                        return;
                    }
                    this.runOnUiThread(new Runnable() { // from class: com.dotscreen.portal.ui.screens.packages.PackagesActivity$onCreate$$inlined$let$lambda$2.2
                        /* JADX WARN: Code duplicated, block: B:16:0x003c  */
                        /* JADX WARN: Code duplicated, block: B:18:0x0046  */
                        /* JADX WARN: Code duplicated, block: B:25:0x0054  */
                        /* JADX WARN: Code duplicated, block: B:27:0x0062  */
                        @Override // java.lang.Runnable
                        public final void run() {
                            ArrayList arrayList3;
                            ArrayList arrayList4;
                            ArrayList arrayList5;
                            ArrayList arrayList6 = this.packageId;
                            boolean z = true;
                            if (arrayList6 == null) {
                                arrayList3 = this.serviceId;
                                if (arrayList3 != null) {
                                    arrayList4 = arrayList3;
                                    if (arrayList4 != null && !arrayList4.isEmpty()) {
                                        z = false;
                                    }
                                    if (!z) {
                                        PackagesActivity packagesActivity = this;
                                        arrayList5 = this.serviceId;
                                        if (arrayList5 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        Object obj = arrayList5.get(0);
                                        Intrinsics.checkExpressionValueIsNotNull(obj, "serviceId!![0]");
                                        packagesActivity.shelf_purchase_popup((String) obj);
                                    }
                                }
                            } else {
                                ArrayList arrayList7 = arrayList6;
                                if (arrayList7 == null || arrayList7.isEmpty()) {
                                    arrayList3 = this.serviceId;
                                    if (arrayList3 != null) {
                                        arrayList4 = arrayList3;
                                        if (arrayList4 != null) {
                                            z = false;
                                        }
                                        if (!z) {
                                            PackagesActivity packagesActivity2 = this;
                                            arrayList5 = this.serviceId;
                                            if (arrayList5 == null) {
                                                Intrinsics.throwNpe();
                                            }
                                            Object obj2 = arrayList5.get(0);
                                            Intrinsics.checkExpressionValueIsNotNull(obj2, "serviceId!![0]");
                                            packagesActivity2.shelf_purchase_popup((String) obj2);
                                        }
                                    }
                                } else {
                                    PackagesActivity packagesActivity3 = this;
                                    ArrayList arrayList8 = this.packageId;
                                    if (arrayList8 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    Object obj3 = arrayList8.get(0);
                                    Intrinsics.checkExpressionValueIsNotNull(obj3, "packageId!![0]");
                                    packagesActivity3.shelf_purchase_popup((String) obj3);
                                }
                            }
                            TextView textView2 = this.no_data_text;
                            if (textView2 != null) {
                                textView2.setVisibility(0);
                            }
                        }
                    });
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.packages.PackagesActivity$onCreate$$inlined$let$lambda$3
                @Override // io.reactivex.functions.Consumer
                public final void accept(Throwable th) {
                    final TextView textView = this.this$0.no_data_text;
                    if (textView != null) {
                        this.this$0.runOnUiThread(new Runnable() { // from class: com.dotscreen.portal.ui.screens.packages.PackagesActivity$onCreate$$inlined$let$lambda$3.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                textView.setVisibility(0);
                            }
                        });
                    }
                }
            });
        }
    }

    public final void showPinCode(RightVPackage item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        PinCodeDialog.Builder message = new PinCodeDialog.Builder(this).setCheckPinCode(false).setTitle(R.string.package_pin_purchase_popup_title).setMessage(R.string.package_pin_purchase_popup_subtitle);
        StringBuilder sb = new StringBuilder();
        sb.append(Intrinsics.areEqual(item.getName(), "") ^ true ? item.getName() : item.getExternalId());
        sb.append("\n$");
        sb.append(item.getPrice());
        message.setInformation(sb.toString()).setOnPinCodeListener((PinCodeDialog.OnPinCodeListener) new C04031(item)).create().show();
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.packages.PackagesActivity$showPinCode$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PackagesActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"com/dotscreen/portal/ui/screens/packages/PackagesActivity$showPinCode$1", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$OnPinCodeListener;", "onPinCodeCorrect", "", "dialog", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog;", "pinCode", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class C04031 implements PinCodeDialog.OnPinCodeListener {
        final /* synthetic */ RightVPackage $item;

        C04031(RightVPackage rightVPackage) {
            this.$item = rightVPackage;
        }

        @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
        public void onPinCodeCorrect(PinCodeDialog dialog, List<String> pinCodes) {
            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
            Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
            PinCodeDialog.OnPinCodeListener.DefaultImpls.onPinCodeCorrect(this, dialog, pinCodes);
        }

        @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
        public void onPinCodeInvalid(PinCodeDialog dialog, String pinCode) {
            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
            Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
            PinCodeDialog.OnPinCodeListener.DefaultImpls.onPinCodeInvalid(this, dialog, pinCode);
        }

        @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
        public void onPinCodeInvalid(PinCodeDialog dialog, List<String> pinCodes, Exception exc) {
            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
            Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
            PinCodeDialog.OnPinCodeListener.DefaultImpls.onPinCodeInvalid(this, dialog, pinCodes, exc);
        }

        @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
        public void onPinCodeCorrect(final PinCodeDialog dialog, String pinCode) {
            Single<Boolean> singleCheckPincode;
            Single<Boolean> singleSubscribeOn;
            Single<Boolean> singleObserveOn;
            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
            Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
            PackagesActivity packagesActivity = PackagesActivity.this;
            PortalApplication.Companion companion = PortalApplication.INSTANCE;
            Context context = dialog.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "dialog.context");
            UserService userService = companion.getSingleton(context).getBOService().getUserService();
            Disposable disposableSubscribe = null;
            if (userService != null && (singleCheckPincode = userService.checkPincode(null, PincodeType.Purchase, pinCode)) != null && (singleSubscribeOn = singleCheckPincode.subscribeOn(Schedulers.io())) != null && (singleObserveOn = singleSubscribeOn.observeOn(AndroidSchedulers.mainThread())) != null) {
                disposableSubscribe = singleObserveOn.subscribe(new BiConsumer<Boolean, Throwable>() { // from class: com.dotscreen.portal.ui.screens.packages.PackagesActivity$showPinCode$1$onPinCodeCorrect$1
                    @Override // io.reactivex.functions.BiConsumer
                    public final void accept(Boolean correct, Throwable th) {
                        dialog.dismiss();
                        if (th != null) {
                            BaseFragmentActivity.displayErrorMessage$default(PackagesActivity.this, th, 0, 2, null);
                            return;
                        }
                        Intrinsics.checkExpressionValueIsNotNull(correct, "correct");
                        if (correct.booleanValue()) {
                            PackagesActivity.this.requestPurchase(this.this$0.$item);
                        } else {
                            Toast.makeText(dialog.getContext(), R.string.error_pin_code_wrong, 0).show();
                        }
                    }
                });
            }
            packagesActivity.favoriteChannelsDisposable = disposableSubscribe;
        }
    }

    public final void requestPurchase(RightVPackage item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        ProgressBar progressBar = this.loaderProgress;
        if (progressBar == null) {
            Intrinsics.throwNpe();
        }
        progressBar.setVisibility(0);
        UserService userService = PortalApplication.INSTANCE.getSingleton(this).getBOService().getUserService();
        if (userService == null) {
            Intrinsics.throwNpe();
        }
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        ((MegacableUserService) userService).sendSubscribeRequest(item.getExternalId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<SubscriptionResult>() { // from class: com.dotscreen.portal.ui.screens.packages.PackagesActivity.requestPurchase.1
            @Override // io.reactivex.functions.Consumer
            public final void accept(SubscriptionResult subscriptionResult) {
                String string;
                String string2;
                ProgressBar progressBar2 = PackagesActivity.this.loaderProgress;
                if (progressBar2 == null) {
                    Intrinsics.throwNpe();
                }
                progressBar2.setVisibility(8);
                if (Intrinsics.areEqual(Locale.getDefault().getLanguage(), "en")) {
                    PortalDialog.Builder builder = new PortalDialog.Builder(PackagesActivity.this);
                    if (subscriptionResult == null || (string2 = subscriptionResult.getEn()) == null) {
                        string2 = PackagesActivity.this.getResources().getString(R.string.package_ok_purchase_popup_subtitle);
                        Intrinsics.checkExpressionValueIsNotNull(string2, "resources.getString(R.st…_purchase_popup_subtitle)");
                    }
                    builder.setMessage(string2).create().show();
                    return;
                }
                PortalDialog.Builder builder2 = new PortalDialog.Builder(PackagesActivity.this);
                if (subscriptionResult == null || (string = subscriptionResult.getSp()) == null) {
                    string = PackagesActivity.this.getResources().getString(R.string.package_ok_purchase_popup_subtitle);
                    Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.st…_purchase_popup_subtitle)");
                }
                builder2.setMessage(string).create().show();
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.packages.PackagesActivity.requestPurchase.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                ProgressBar progressBar2 = PackagesActivity.this.loaderProgress;
                if (progressBar2 == null) {
                    Intrinsics.throwNpe();
                }
                progressBar2.setVisibility(8);
                PortalDialog.Builder builder = new PortalDialog.Builder(PackagesActivity.this);
                String string = PackagesActivity.this.getResources().getString(R.string.package_error_purchase_popup_subtitle);
                Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.st…_purchase_popup_subtitle)");
                builder.setMessage(string).create().show();
            }
        });
    }

    public final void shelf_purchase_popup(String title) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        new PortalDialog.Builder(this).setTitle(title).setMessage(R.string.package_self_subscription_popup_subtitle).create().show();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        Disposable disposable = this.favoriteChannelsDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
