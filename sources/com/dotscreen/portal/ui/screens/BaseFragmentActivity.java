package com.dotscreen.portal.ui.screens;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.BackgroundManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import com.dotscreen.bokit.model.BrowsableItem;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.Item;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVProgram;
import com.dotscreen.bokit.services.PincodeType;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.farkit.debug.CheatCodeHelper;
import com.dotscreen.farkit.design.DesignContextWrapper;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.farkit.network.RequestManager;
import com.dotscreen.farkit.rx.LifecycleAwareDisposable;
import com.dotscreen.farkit.rx.LifecycleAwareDisposableKt;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.Constants;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.PortalApplicationSingletons;
import com.dotscreen.portal.ui.screens.home.HomeActivity;
import com.dotscreen.portal.ui.screens.profile.PinCodeDialog;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.RightVRequestManager;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.ui.DirectChannelAccessView;
import com.dotscreen.tvinput.SyncTVChannelsWorkerKt;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import io.sentry.Breadcrumb;
import io.sentry.Sentry;
import io.sentry.SentryEvent;
import io.sentry.SentryLevel;
import io.sentry.protocol.Message;
import io.sentry.protocol.User;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BaseFragmentActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000§\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001+\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0014J\u0012\u00101\u001a\u00020\u00142\b\u00102\u001a\u0004\u0018\u000103H\u0016J\u0018\u00104\u001a\u00020.2\u0006\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u00020\u0006J\u0014\u00108\u001a\u00020.2\f\u00109\u001a\b\u0012\u0004\u0012\u00020.0:JB\u0010;\u001a\u00020.2\u0006\u0010<\u001a\u00020=2\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u00142!\u00109\u001a\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b@\u0012\b\bA\u0012\u0004\b\b(B\u0012\u0004\u0012\u00020.0?¢\u0006\u0002\u0010CJ\u0006\u0010D\u001a\u00020\u0014J\n\u0010E\u001a\u0004\u0018\u00010FH\u0002J\u0006\u0010G\u001a\u00020\u0014J\b\u0010H\u001a\u00020.H\u0016J\u0010\u0010I\u001a\u00020.2\u0006\u0010J\u001a\u00020\u0006H\u0016J\b\u0010K\u001a\u00020.H\u0016J\b\u0010L\u001a\u00020.H\u0016J\u0012\u0010M\u001a\u00020.2\b\u0010N\u001a\u0004\u0018\u00010OH\u0014J\b\u0010P\u001a\u00020.H\u0014J\b\u0010Q\u001a\u00020.H\u0014J\b\u0010R\u001a\u00020.H\u0014J\b\u0010S\u001a\u00020.H\u0002J\u0010\u0010T\u001a\u00020.2\u0006\u0010U\u001a\u00020\u0006H\u0016J\u001c\u0010V\u001a\u00020.2\b\u0010W\u001a\u0004\u0018\u00010X2\b\u0010Y\u001a\u0004\u0018\u00010OH\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\nX\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020\u0014X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0016\"\u0004\b!\u0010\u0018R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010%\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\u0014@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0016\"\u0004\b'\u0010\u0018R\u0010\u0010(\u001a\u0004\u0018\u00010)X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0004\n\u0002\u0010,¨\u0006Z"}, d2 = {"Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "Landroidx/fragment/app/FragmentActivity;", "Lcom/dotscreen/tv/ui/DirectChannelAccessView$OnChannelSelectedListener;", "Lcom/dotscreen/farkit/debug/CheatCodeHelper$OnTriggerListener;", "()V", "REQUEST_CHANNEL_SCAN", "", "getREQUEST_CHANNEL_SCAN$MegaCable_Portal_0_75_1_396__prodRelease", "()I", "TAG", "", "kotlin.jvm.PlatformType", "background", "Landroid/graphics/drawable/Drawable;", "backgroundDesignPath", "getBackgroundDesignPath", "()Ljava/lang/String;", "backgroundManager", "Landroidx/leanback/app/BackgroundManager;", "blockInput", "", "getBlockInput", "()Z", "setBlockInput", "(Z)V", "disposable", "Lcom/dotscreen/farkit/rx/LifecycleAwareDisposable;", "getDisposable", "()Lcom/dotscreen/farkit/rx/LifecycleAwareDisposable;", "setDisposable", "(Lcom/dotscreen/farkit/rx/LifecycleAwareDisposable;)V", "fromAdultBrowsing", "getFromAdultBrowsing$MegaCable_Portal_0_75_1_396__prodRelease", "setFromAdultBrowsing$MegaCable_Portal_0_75_1_396__prodRelease", "handler", "Landroid/os/Handler;", "value", "loaderVisible", "getLoaderVisible", "setLoaderVisible", "metrics", "Landroid/util/DisplayMetrics;", "scanStatusReceiver", "com/dotscreen/portal/ui/screens/BaseFragmentActivity$scanStatusReceiver$1", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity$scanStatusReceiver$1;", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "dispatchKeyEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "displayErrorMessage", "error", "", "duration", "displayParentalPinCode", "callback", "Lkotlin/Function0;", "displayParentalPinCodeIfNeeded", "item", "Lcom/dotscreen/bokit/model/Item;", "askPinCodeIfNeeded", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "isAdultContent", "(Lcom/dotscreen/bokit/model/Item;Ljava/lang/Boolean;Lkotlin/jvm/functions/Function1;)V", "displaySerialNumberEditDialogIfNeeded", "getCurrentUser", "Lcom/dotscreen/tv/common/bokit/RightVUser;", "isAdultBrowsing", "onBackPressed", "onChannelSelected", "channelNumber", "onCheatCodetriggered", "onContentChanged", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onResume", "prepareBackgroundManager", "setContentView", "layoutResID", "startActivity", "intent", "Landroid/content/Intent;", "options", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public class BaseFragmentActivity extends FragmentActivity implements DirectChannelAccessView.OnChannelSelectedListener, CheatCodeHelper.OnTriggerListener {
    private HashMap _$_findViewCache;
    private Drawable background;
    private BackgroundManager backgroundManager;
    private boolean blockInput;
    private boolean fromAdultBrowsing;
    private boolean loaderVisible;
    private DisplayMetrics metrics;
    private final String TAG = BaseFragmentActivity.class.getSimpleName();
    private final Handler handler = new Handler();
    private final int REQUEST_CHANNEL_SCAN = 100;
    private LifecycleAwareDisposable disposable = new LifecycleAwareDisposable();
    private final String backgroundDesignPath = "standard.background.image";
    private final BaseFragmentActivity$scanStatusReceiver$1 scanStatusReceiver = new BroadcastReceiver() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity$scanStatusReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra;
            if (Intrinsics.areEqual(intent != null ? intent.getAction() : null, CommonUtils.ACTION_SCAN_STATUS_CHANGED) && (stringExtra = intent.getStringExtra(CommonUtils.BUNDLE_KEY_SCAN_STATUS)) != null && stringExtra.hashCode() == -1766918726 && stringExtra.equals(CommonUtils.SCAN_STATUS_ERROR)) {
                Log.d(this.this$0.TAG, "SCAN_STATUS_ERROR");
                if (SyncTVChannelsWorkerKt.getNetworkIdChanged(this.this$0)) {
                    this.this$0.setContentView(R.layout.suspend_account);
                }
            }
        }
    };

    public void _$_clearFindViewByIdCache() {
        HashMap map = this._$_findViewCache;
        if (map != null) {
            map.clear();
        }
    }

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

    /* JADX INFO: renamed from: getFromAdultBrowsing$MegaCable_Portal_0_75_1_396__prodRelease, reason: from getter */
    public final boolean getFromAdultBrowsing() {
        return this.fromAdultBrowsing;
    }

    public final void setFromAdultBrowsing$MegaCable_Portal_0_75_1_396__prodRelease(boolean z) {
        this.fromAdultBrowsing = z;
    }

    /* JADX INFO: renamed from: getREQUEST_CHANNEL_SCAN$MegaCable_Portal_0_75_1_396__prodRelease, reason: from getter */
    public final int getREQUEST_CHANNEL_SCAN() {
        return this.REQUEST_CHANNEL_SCAN;
    }

    public final LifecycleAwareDisposable getDisposable() {
        return this.disposable;
    }

    public final void setDisposable(LifecycleAwareDisposable lifecycleAwareDisposable) {
        Intrinsics.checkParameterIsNotNull(lifecycleAwareDisposable, "<set-?>");
        this.disposable = lifecycleAwareDisposable;
    }

    protected String getBackgroundDesignPath() {
        return this.backgroundDesignPath;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        LifecycleAwareDisposable lifecycleAwareDisposable = this.disposable;
        Lifecycle lifecycle = getLifecycle();
        Intrinsics.checkExpressionValueIsNotNull(lifecycle, "lifecycle");
        lifecycleAwareDisposable.bindTo(lifecycle);
        super.onCreate(savedInstanceState);
        prepareBackgroundManager();
        setLoaderVisible(false);
        DSLog dSLog = DSLog.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this.javaClass.simpleName");
        dSLog.s(simpleName);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        super.onContentChanged();
        DirectChannelAccessView directChannelAccessView = (DirectChannelAccessView) findViewById(R.id.dca_view);
        if (directChannelAccessView != null) {
            directChannelAccessView.setOnCheatCodeListener(this);
            directChannelAccessView.setOnChannelSelectedListener(this);
            directChannelAccessView.setChannelName(new Function1<Integer, Maybe<String>>() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity$onContentChanged$$inlined$let$lambda$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Maybe<String> invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Maybe<String> invoke(final int i) {
                    Maybe<String> maybeFirstElement = PortalApplication.INSTANCE.getSingleton(this.this$0).getCurrentLineup().flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity$onContentChanged$1$1$1
                        @Override // io.reactivex.functions.Function
                        public final Observable<Channel> apply(Lineup it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return it.getItems();
                        }
                    }).filter(new Predicate<Channel>() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity$onContentChanged$$inlined$let$lambda$1.1
                        @Override // io.reactivex.functions.Predicate
                        public final boolean test(Channel it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return it.getZappingNumber() == i;
                        }
                    }).map(new Function<T, R>() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity$onContentChanged$1$1$3
                        @Override // io.reactivex.functions.Function
                        public final String apply(Channel it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            String title = it.getTitle();
                            if (title == null) {
                                Intrinsics.throwNpe();
                            }
                            return title;
                        }
                    }).firstElement();
                    Intrinsics.checkExpressionValueIsNotNull(maybeFirstElement, "PortalApplication.getSin…          .firstElement()");
                    return maybeFirstElement;
                }
            });
        }
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context newBase) {
        Intrinsics.checkParameterIsNotNull(newBase, "newBase");
        LiveData<PortalApplication.Config> config = PortalApplication.INSTANCE.getSingleton(newBase).getConfig();
        Intrinsics.checkExpressionValueIsNotNull(config, "PortalApplication.getSingleton(newBase).config");
        PortalApplication.Config value = config.getValue();
        if (value != null) {
            super.attachBaseContext(new DesignContextWrapper(newBase, value.getDesignManager()));
        } else {
            super.attachBaseContext(newBase);
        }
    }

    @Override // android.app.Activity
    public void setContentView(int layoutResID) {
        if (SyncTVChannelsWorkerKt.getNetworkIdChanged(this)) {
            super.setContentView(R.layout.suspend_account);
        } else {
            super.setContentView(layoutResID);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        BackgroundManager backgroundManager = this.backgroundManager;
        if (backgroundManager != null) {
            backgroundManager.setDrawable(this.background);
        }
        registerReceiver(this.scanStatusReceiver, new IntentFilter(CommonUtils.ACTION_SCAN_STATUS_CHANGED));
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        unregisterReceiver(this.scanStatusReceiver);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        DSLog.INSTANCE.a("Pressed back");
        if (SyncTVChannelsWorkerKt.getNetworkIdChanged(this)) {
            return;
        }
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("extra_calling_intent")) {
            Intent intent2 = getIntent();
            Intent intent3 = intent2 != null ? (Intent) intent2.getParcelableExtra("extra_calling_intent") : null;
            super.startActivity(intent3 instanceof Intent ? intent3 : null);
            this.handler.postDelayed(new Runnable() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity.onBackPressed.1
                @Override // java.lang.Runnable
                public final void run() {
                    BaseFragmentActivity.this.finish();
                }
            }, 10L);
            return;
        }
        super.onBackPressed();
    }

    private final void prepareBackgroundManager() {
        if (getBackgroundDesignPath() != null) {
            BackgroundManager backgroundManager = BackgroundManager.getInstance(this);
            this.backgroundManager = backgroundManager;
            if (backgroundManager != null) {
                backgroundManager.attach(getWindow());
            }
            this.metrics = new DisplayMetrics();
            WindowManager windowManager = getWindowManager();
            Intrinsics.checkExpressionValueIsNotNull(windowManager, "windowManager");
            windowManager.getDefaultDisplay().getMetrics(this.metrics);
            Drawable drawable = getResources().getDrawable(R.drawable.background, null);
            this.background = drawable;
            BackgroundManager backgroundManager2 = this.backgroundManager;
            if (backgroundManager2 != null) {
                backgroundManager2.setDrawable(drawable);
            }
            LiveData<PortalApplication.Config> config = PortalApplication.INSTANCE.getSingleton(this).getConfig();
            Intrinsics.checkExpressionValueIsNotNull(config, "app.config");
            PortalApplication.Config value = config.getValue();
            if (value != null) {
                DesignManager designManager = value.getDesignManager();
                String backgroundDesignPath = getBackgroundDesignPath();
                if (backgroundDesignPath == null) {
                    Intrinsics.throwNpe();
                }
                designManager.getBitmap(backgroundDesignPath).subscribe(new Consumer<Bitmap>() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity$prepareBackgroundManager$$inlined$let$lambda$1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Bitmap bitmap) {
                        this.this$0.background = new BitmapDrawable(this.this$0.getResources(), bitmap);
                        BackgroundManager backgroundManager3 = this.this$0.backgroundManager;
                        if (backgroundManager3 != null) {
                            backgroundManager3.setDrawable(this.this$0.background);
                        }
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity$prepareBackgroundManager$1$2
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        th.printStackTrace();
                    }
                }, new Action() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity$prepareBackgroundManager$1$3
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                    }
                });
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00b4 A[RETURN] */
    @Override // androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        DirectChannelAccessView directChannelAccessView;
        if (event != null) {
            Breadcrumb breadcrumb = new Breadcrumb();
            breadcrumb.setMessage(this.TAG + ": dispatchKeyEvent, blockInput=" + this.blockInput + ", event = " + event);
            breadcrumb.setData("keyEvent", event);
            Sentry.addBreadcrumb(breadcrumb);
            Log.d(this.TAG, "dispatchKeyEvent, blockInput=" + this.blockInput + ", event = " + event);
            if (this.blockInput && event.getKeyCode() != 4) {
                Log.d(this.TAG, "dispatchKeyEvent, BLOCKED !");
                return true;
            }
            if (event.getAction() == 1 && (directChannelAccessView = (DirectChannelAccessView) findViewById(R.id.dca_view)) != null) {
                int keyCode = event.getKeyCode();
                switch (keyCode) {
                    default:
                        switch (keyCode) {
                            case 268:
                            case 269:
                            case 270:
                            case 271:
                                break;
                            default:
                                if (directChannelAccessView.onKeyCode(event.getKeyCode())) {
                                    return true;
                                }
                                break;
                        }
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                        Application application = getApplication();
                        if (application == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                        }
                        ((PortalApplication) application).onBrowseActionInActivity();
                        if (event.getKeyCode() == 23) {
                            Application application2 = getApplication();
                            if (application2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                            }
                            if (((PortalApplication) application2).isShowingDismissableFloatingAds()) {
                                return true;
                            }
                        }
                        if (directChannelAccessView.onKeyCode(event.getKeyCode())) {
                            return true;
                        }
                        break;
                }
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override // com.dotscreen.tv.ui.DirectChannelAccessView.OnChannelSelectedListener
    public void onChannelSelected(final int channelNumber) {
        Disposable disposableSubscribe = PortalApplication.INSTANCE.getSingleton(this).getCurrentLineup().flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity.onChannelSelected.1
            @Override // io.reactivex.functions.Function
            public final Observable<Channel> apply(Lineup it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getItems();
            }
        }).filter(new Predicate<Channel>() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity.onChannelSelected.2
            @Override // io.reactivex.functions.Predicate
            public final boolean test(Channel it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return it.getZappingNumber() == channelNumber;
            }
        }).firstElement().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Channel>() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity.onChannelSelected.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Channel channel) {
                Activity_StartIntentKt.startIntent(BaseFragmentActivity.this, new Intent("android.intent.action.VIEW", CommonUtils.getLiveTvDeeplink((Context) BaseFragmentActivity.this, channel, (Boolean) true).buildUpon().appendQueryParameter("fromdca", "true").build()).addFlags(335544320));
                BaseFragmentActivity baseFragmentActivity = BaseFragmentActivity.this;
                if (baseFragmentActivity instanceof HomeActivity) {
                    return;
                }
                baseFragmentActivity.finishAffinity();
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity.onChannelSelected.4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
            }
        }, new Action() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity.onChannelSelected.5
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "PortalApplication.getSin…             }, { }, { })");
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, this.disposable);
    }

    public final boolean getLoaderVisible() {
        return this.loaderVisible;
    }

    public final void setLoaderVisible(final boolean z) {
        this.loaderVisible = z;
        View viewFindViewById = findViewById(android.R.id.content);
        if (viewFindViewById != null) {
            viewFindViewById.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity$loaderVisible$1
                @Override // java.lang.Runnable
                public final void run() {
                    View viewFindViewById2 = this.this$0.findViewById(R.id.loaderProgress);
                    if (viewFindViewById2 != null) {
                        viewFindViewById2.setVisibility(z ? 0 : 8);
                    }
                }
            });
        }
    }

    public final boolean getBlockInput() {
        return this.blockInput;
    }

    public final void setBlockInput(boolean z) {
        this.blockInput = z;
    }

    public final boolean displaySerialNumberEditDialogIfNeeded() {
        BaseFragmentActivity baseFragmentActivity = this;
        PortalApplicationSingletons singleton = PortalApplication.INSTANCE.getSingleton(baseFragmentActivity);
        if (singleton == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication portalApplication = (PortalApplication) singleton;
        if (Constants.INSTANCE.getINPUT_SERIAL_NUMER_AT_STARTUP()) {
            if (getSharedPreferences("debug", 0).getString("serialNumber", null) == null) {
                setContentView(R.layout.activity_startup);
                final EditText editText = new EditText(baseFragmentActivity);
                editText.setInputType(1);
                UserService userService = portalApplication.getBOService().getUserService();
                MegacableUserService megacableUserService = (MegacableUserService) (userService instanceof MegacableUserService ? userService : null);
                if (megacableUserService == null) {
                    Intrinsics.throwNpe();
                }
                editText.setText(megacableUserService.getSerialNumber());
                AlertDialog alertDialogCreate = new AlertDialog.Builder(baseFragmentActivity).setTitle("Serial number").setView(editText).setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity$displaySerialNumberEditDialogIfNeeded$builder$1
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        String string = editText.getText().toString();
                        if (string == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                        }
                        String string2 = StringsKt.trim((CharSequence) string).toString();
                        this.this$0.getSharedPreferences("debug", 0).edit().putString("serialNumber", string2).apply();
                        dialogInterface.dismiss();
                        this.this$0.finish();
                        BaseFragmentActivity baseFragmentActivity2 = this.this$0;
                        baseFragmentActivity2.startActivity(baseFragmentActivity2.getIntent());
                        User user = new User();
                        user.setId(string2);
                        Sentry.setUser(user);
                    }
                }).setNeutralButton("Reset", new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity$displaySerialNumberEditDialogIfNeeded$builder$2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).setCancelable(false).create();
                alertDialogCreate.show();
                alertDialogCreate.getButton(-1).requestFocus();
                alertDialogCreate.getButton(-3).setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity.displaySerialNumberEditDialogIfNeeded.1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DSLog.INSTANCE.a("Clicked on Neutral button");
                        editText.setText(Build.getSerial());
                    }
                });
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ void displayParentalPinCodeIfNeeded$default(BaseFragmentActivity baseFragmentActivity, Item item, Boolean bool, Function1 function1, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: displayParentalPinCodeIfNeeded");
        }
        if ((i & 2) != 0) {
            bool = true;
        }
        baseFragmentActivity.displayParentalPinCodeIfNeeded(item, bool, function1);
    }

    public final void displayParentalPinCodeIfNeeded(Item item, Boolean askPinCodeIfNeeded, final Function1<? super Boolean, Unit> callback) {
        ParentalRating parentalLevel;
        Intrinsics.checkParameterIsNotNull(item, "item");
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (Intrinsics.areEqual((Object) askPinCodeIfNeeded, (Object) false)) {
            callback.invoke(false);
            return;
        }
        int level = 6;
        if (item instanceof TVAiring) {
            ParentalRating parentalLevel2 = ((TVAiring) item).getMetadata().getParentalLevel();
            if (parentalLevel2 != null) {
                level = parentalLevel2.getLevel();
            }
        } else if (item instanceof Content) {
            Content content = (Content) item;
            if (content.getMetadata().getParentalLevel() != null) {
                ParentalRating parentalLevel3 = content.getMetadata().getParentalLevel();
                if (parentalLevel3 == null) {
                    Intrinsics.throwNpe();
                }
                if (parentalLevel3.getLevel() == 0) {
                    level = 7;
                } else {
                    ParentalRating parentalLevel4 = content.getMetadata().getParentalLevel();
                    if (parentalLevel4 == null) {
                        Intrinsics.throwNpe();
                    }
                    level = parentalLevel4.getLevel();
                }
            }
        } else if ((item instanceof TVProgram) && (parentalLevel = ((TVProgram) item).getMetadata().getParentalLevel()) != null) {
            level = parentalLevel.getLevel();
        }
        final boolean zItemIsAdult = Constants.INSTANCE.itemIsAdult((BrowsableItem) item);
        RightVUser currentUser = getCurrentUser();
        if (currentUser != null) {
            if (!this.fromAdultBrowsing && level > currentUser.getParentalLevel().getLevel()) {
                Dialog dialogCreate = new PinCodeDialog.Builder(this).setCheckPinCode(true).setUser(currentUser).setPincodeType(PincodeType.Parental).setTitle(R.string.input_parental_pin_code).setOnPinCodeListener(new PinCodeDialog.OnPinCodeListener() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity$displayParentalPinCodeIfNeeded$dialog$1
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
                    public void onPinCodeCorrect(PinCodeDialog dialog, String pinCode) {
                        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                        Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
                        dialog.dismiss();
                        callback.invoke(Boolean.valueOf(zItemIsAdult));
                    }
                }).create();
                dialogCreate.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity.displayParentalPinCodeIfNeeded.1
                    @Override // android.content.DialogInterface.OnCancelListener
                    public final void onCancel(DialogInterface dialogInterface) {
                        BaseFragmentActivity.this.finish();
                    }
                });
                dialogCreate.show();
                return;
            }
            callback.invoke(Boolean.valueOf(zItemIsAdult));
            return;
        }
        callback.invoke(Boolean.valueOf(zItemIsAdult));
    }

    public final void displayParentalPinCode(final Function0<Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        RightVUser currentUser = getCurrentUser();
        if (currentUser != null) {
            Dialog dialogCreate = new PinCodeDialog.Builder(this).setCheckPinCode(true).setUser(currentUser).setPincodeType(PincodeType.Parental).setTitle(R.string.input_parental_pin_code).setOnPinCodeListener(new PinCodeDialog.OnPinCodeListener() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity$displayParentalPinCode$dialog$1
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
                public void onPinCodeCorrect(PinCodeDialog dialog, String pinCode) {
                    Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                    Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
                    dialog.dismiss();
                    callback.invoke();
                }
            }).create();
            dialogCreate.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.dotscreen.portal.ui.screens.BaseFragmentActivity.displayParentalPinCode.1
                @Override // android.content.DialogInterface.OnCancelListener
                public final void onCancel(DialogInterface dialogInterface) {
                    BaseFragmentActivity.this.finish();
                }
            });
            dialogCreate.show();
        }
    }

    public final boolean isAdultBrowsing() {
        return this.fromAdultBrowsing;
    }

    public static /* synthetic */ void displayErrorMessage$default(BaseFragmentActivity baseFragmentActivity, Throwable th, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: displayErrorMessage");
        }
        if ((i2 & 2) != 0) {
            i = 1;
        }
        baseFragmentActivity.displayErrorMessage(th, i);
    }

    public final void displayErrorMessage(Throwable error, int duration) {
        Intrinsics.checkParameterIsNotNull(error, "error");
        BaseFragmentActivity baseFragmentActivity = this;
        Application application = getApplication();
        if (application == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        Toast.makeText(baseFragmentActivity, ((PortalApplication) application).getErrorManager().getErrorMessage((Exception) error), duration).show();
    }

    @Override // com.dotscreen.farkit.debug.CheatCodeHelper.OnTriggerListener
    public void onCheatCodetriggered() {
        Application application = getApplication();
        if (application == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        RequestManager requestManager = ((PortalApplication) application).getRequestManager();
        if (requestManager == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVRequestManager");
        }
        ((RightVRequestManager) requestManager).toggleCompassBackendState();
        Intent intent = new Intent();
        intent.setAction(CommonUtils.TOGGLE_BACKEND_STATE);
        sendBroadcast(intent);
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent, Bundle options) {
        Log.v(this.TAG, "startActivity: " + intent);
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("               data = ");
        sb.append(intent != null ? intent.getData() : null);
        Log.v(str, sb.toString());
        super.startActivity(intent, options);
    }

    private final RightVUser getCurrentUser() {
        PortalApplicationSingletons singleton = PortalApplication.INSTANCE.getSingleton(this);
        if (singleton == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication portalApplication = (PortalApplication) singleton;
        PortalApplication.Config value = portalApplication.getConfig().getValue();
        RightVUser user = value != null ? value.getUser() : null;
        if (user == null) {
            Sentry.addBreadcrumb("config value or user is null");
            if (portalApplication.getConfig().getValue() == null) {
                SentryEvent sentryEvent = new SentryEvent();
                sentryEvent.setLevel(SentryLevel.DEBUG);
                Message message = new Message();
                message.setMessage("App running without config value");
                sentryEvent.setMessage(message);
                Sentry.captureEvent(sentryEvent);
            }
        }
        return user;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        DSLog dSLog = DSLog.INSTANCE;
        String simpleName = getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "this.javaClass.simpleName");
        dSLog.f(simpleName);
        super.onDestroy();
    }
}
