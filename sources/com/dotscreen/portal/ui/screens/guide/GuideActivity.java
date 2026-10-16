package com.dotscreen.portal.ui.screens.guide;

import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Outline;
import android.graphics.Point;
import android.media.tv.TvContract;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.leanback.widget.OnChildSelectedListener;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.request.RequestListener;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.PortalApplicationSingletons;
import com.dotscreen.portal.components.NotificationView;
import com.dotscreen.portal.model.GuideViewModel;
import com.dotscreen.portal.model.OptimizerGuideViewModel;
import com.dotscreen.portal.model.TIFGuideViewModel;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.views.FontTextView;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.providers.LastChannelsProvider;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.common.utils.TIFCompanionLibraryExtensionKt;
import com.dotscreen.tv.ui.DirectChannelAccessView;
import com.dotscreen.tv.ui.ImageLoaderManager;
import com.dotscreen.tv.ui.dialog.CheckListDialog;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.media.tv.companionlibrary.model.Channel;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: GuideActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Õ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\n*\u0001K\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010U\u001a\u00020\u00052\u0006\u0010V\u001a\u00020\u00052\u0006\u0010W\u001a\u00020\u0005J\u0012\u0010X\u001a\u00020\u001c2\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0016J\b\u0010\u0019\u001a\u00020[H\u0002J\u0018\u0010\\\u001a\u00020\u00052\u0006\u0010V\u001a\u00020\u00052\u0006\u0010W\u001a\u00020\u0005H\u0002J\n\u0010]\u001a\u0004\u0018\u00010^H\u0002J\u0006\u0010_\u001a\u00020+J\u0012\u0010`\u001a\u00020\r2\b\u0010a\u001a\u0004\u0018\u00010bH\u0002J\b\u0010c\u001a\u00020[H\u0002J\b\u0010d\u001a\u00020[H\u0002J\b\u0010e\u001a\u00020[H\u0002J\b\u0010f\u001a\u00020[H\u0002J\u0012\u0010g\u001a\u00020[2\b\u0010h\u001a\u0004\u0018\u00010iH\u0014J\u0010\u0010j\u001a\u00020[2\u0006\u0010k\u001a\u00020+H\u0002J\u001a\u0010l\u001a\u00020\u001c2\u0006\u0010m\u001a\u00020+2\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0016J\u001a\u0010n\u001a\u00020\u001c2\u0006\u0010m\u001a\u00020+2\b\u0010Y\u001a\u0004\u0018\u00010ZH\u0016J\b\u0010o\u001a\u00020[H\u0014J\u001c\u0010p\u001a\u00020[2\b\u0010q\u001a\u0004\u0018\u00010$2\b\u0010r\u001a\u0004\u0018\u00010$H\u0016J\b\u0010s\u001a\u00020[H\u0014J\b\u0010t\u001a\u00020[H\u0014J\b\u0010u\u001a\u00020[H\u0016J\b\u0010v\u001a\u00020[H\u0002J\b\u0010w\u001a\u00020[H\u0002J\u0006\u0010x\u001a\u00020[J\b\u0010y\u001a\u00020[H\u0002J\b\u0010z\u001a\u00020[H\u0002J\u000e\u0010{\u001a\u00020[2\u0006\u0010|\u001a\u00020+J\u000e\u0010}\u001a\u00020[2\u0006\u0010~\u001a\u00020\u007fJ\u0014\u0010\u0080\u0001\u001a\u00020[2\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010$H\u0002J\u0014\u0010\u0082\u0001\u001a\u00020[2\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010$H\u0002J\t\u0010\u0083\u0001\u001a\u00020[H\u0002J\t\u0010\u0084\u0001\u001a\u00020[H\u0002J\t\u0010\u0085\u0001\u001a\u00020[H\u0002J\t\u0010\u0086\u0001\u001a\u00020[H\u0002J\t\u0010\u0087\u0001\u001a\u00020[H\u0002J\t\u0010\u0088\u0001\u001a\u00020[H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0018\u00010\u0011R\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\b\u0012\u00060\rj\u0002`\u0015\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001e\"\u0004\b\"\u0010 R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u000200X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00105\u001a\u000206X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u000e\u0010;\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010A\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010B\u001a\u0004\u0018\u00010CX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010E\u001a\u0004\u0018\u00010FX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010H\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010I\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010J\u001a\u00020KX\u0082\u0004¢\u0006\u0004\n\u0002\u0010LR\u000e\u0010M\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u000200X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010O\u001a\u00020+X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010P\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010T¨\u0006\u0089\u0001"}, d2 = {"Lcom/dotscreen/portal/ui/screens/guide/GuideActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "Lcom/dotscreen/portal/ui/screens/guide/ProgramGrid$ChildFocusListener;", "()V", "HALF_HOUR_IN_MILLIS", "", "HOUR_IN_MILLIS", "IDLE_TIME_BEFORE_DISPLAY_INFO", "MIN_DURATION_FROM_START_TIME_TO_CURRENT_TIME", "OnPreDrawListener", "Landroid/view/ViewTreeObserver$OnPreDrawListener;", "REFRESH_TIMEOUT", "TAG", "", "kotlin.jvm.PlatformType", "TIME_INDICATOR_UPDATE_FREQUENCY", "_data", "Lcom/dotscreen/portal/model/GuideViewModel$Data;", "Lcom/dotscreen/portal/model/GuideViewModel;", "channelGenres", "", "Lcom/dotscreen/bokit/model/Genre;", "currentGenre", "dayText", "Landroid/widget/TextView;", "displayProgramInfo", "Ljava/lang/Runnable;", "fastDataDisplay", "", "isFirstDisplayOfInstance", "()Z", "setFirstDisplayOfInstance", "(Z)V", "isFirstTimeFocusChanged", "setFirstTimeFocusChanged", "last_focus", "Landroid/view/View;", "loaderProgress", "Landroid/widget/ProgressBar;", "mContainer", "Landroid/widget/RelativeLayout;", "mCurrentTimeIndicator", "mCurrentTimeIndicatorWidth", "", "mDetailHeight", "mGrid", "Lcom/dotscreen/portal/ui/screens/guide/ProgramGrid;", "mHandler", "Landroid/os/Handler;", "mIsDuringResetRowSelection", "mLastRequestedGenreId", "mOnLayoutListenerForShow", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "mProgramManager", "Lcom/dotscreen/portal/ui/screens/guide/ProgramManager;", "getMProgramManager", "()Lcom/dotscreen/portal/ui/screens/guide/ProgramManager;", "setMProgramManager", "(Lcom/dotscreen/portal/ui/screens/guide/ProgramManager;)V", "mRowHeight", "mSelectedRow", "mSelectionRow", "mSharedPreference", "Landroid/content/SharedPreferences;", "mStartUtcTime", "mTable", "mTimeListAdapter", "Lcom/dotscreen/portal/ui/screens/guide/TimeListAdapter;", "mTimelineAnimation", "mTimelineRow", "Lcom/dotscreen/portal/ui/screens/guide/TimelineRow;", "mUpdateTimeIndicator", "mViewPortMillis", "mWidthPerHour", "programManagerListener", "com/dotscreen/portal/ui/screens/guide/GuideActivity$programManagerListener$1", "Lcom/dotscreen/portal/ui/screens/guide/GuideActivity$programManagerListener$1;", "refreshCallback", "refreshHandler", "start_focus_position", NotificationCompat.CATEGORY_STATUS, "getStatus", "()I", "setStatus", "(I)V", "ceilTime", "timeMs", "timeUnit", "dispatchKeyEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "", "floorTime", "getSelectedEntry", "Lcom/dotscreen/portal/ui/screens/guide/TableEntry;", "getTimelineRowScrollOffset", "getTypeFromMetadata", TtmlNode.TAG_METADATA, "Lcom/dotscreen/bokit/model/MediaMetadata;", "hideProgramInfo", "loadData", "loadDataFuture", "mOnLayoutListenerForShowFun", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onHorizontalScrolled", "dx", "onKeyDown", "keyCode", "onKeyUp", "onPause", "onRequestChildFocus", "oldFocus", "newFocus", "onRestart", "onResume", "onUserInteraction", "positionCurrentTimeIndicator", "refreshActivity", "reloadFullGuide", "resetDisconnectTimer", "resetRowSelection", "selectChannelByZappingNumber", "zapping", "setCurrentDate", "date", "Ljava/util/Date;", "setInitialProgramFocus", "row", "setSelectedProgramFocus", "show", "stopCurrentTimeIndicator", "stopDisconnectTimer", "stopDisplayProgramInfo", "updateGuidePosition", "updateTimefunction", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class GuideActivity extends BaseFragmentActivity implements ProgramGrid.ChildFocusListener {
    private final long HALF_HOUR_IN_MILLIS;
    private final long HOUR_IN_MILLIS;
    private final long IDLE_TIME_BEFORE_DISPLAY_INFO;
    private final long MIN_DURATION_FROM_START_TIME_TO_CURRENT_TIME;
    private ViewTreeObserver.OnPreDrawListener OnPreDrawListener;
    private final long REFRESH_TIMEOUT;
    private final String TAG = GuideActivity.class.getSimpleName();
    private final long TIME_INDICATOR_UPDATE_FREQUENCY = TimeUnit.SECONDS.toMillis(1);
    private HashMap _$_findViewCache;
    private GuideViewModel.Data _data;
    private List<String> channelGenres;
    private String currentGenre;
    private TextView dayText;
    private final Runnable displayProgramInfo;
    private boolean fastDataDisplay;
    private boolean isFirstDisplayOfInstance;
    private boolean isFirstTimeFocusChanged;
    private View last_focus;
    private ProgressBar loaderProgress;
    private RelativeLayout mContainer;
    private View mCurrentTimeIndicator;
    private int mCurrentTimeIndicatorWidth;
    private int mDetailHeight;
    private ProgramGrid mGrid;
    private Handler mHandler;
    private boolean mIsDuringResetRowSelection;
    private int mLastRequestedGenreId;
    private ViewTreeObserver.OnGlobalLayoutListener mOnLayoutListenerForShow;
    public ProgramManager mProgramManager;
    private int mRowHeight;
    private View mSelectedRow;
    private int mSelectionRow;
    private SharedPreferences mSharedPreference;
    private long mStartUtcTime;
    private RelativeLayout mTable;
    private TimeListAdapter mTimeListAdapter;
    private boolean mTimelineAnimation;
    private TimelineRow mTimelineRow;
    private final Runnable mUpdateTimeIndicator;
    private long mViewPortMillis;
    private int mWidthPerHour;
    private final GuideActivity$programManagerListener$1 programManagerListener;
    private final Runnable refreshCallback;
    private final Handler refreshHandler;
    private int start_focus_position;
    private int status;

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

    /* JADX WARN: Type inference failed for: r0v13, types: [com.dotscreen.portal.ui.screens.guide.GuideActivity$programManagerListener$1] */
    public GuideActivity() {
        long millis = TimeUnit.HOURS.toMillis(1L);
        this.HOUR_IN_MILLIS = millis;
        this.HALF_HOUR_IN_MILLIS = millis / ((long) 2);
        this.MIN_DURATION_FROM_START_TIME_TO_CURRENT_TIME = TimeUnit.MINUTES.toMillis(1L);
        this.REFRESH_TIMEOUT = TimeUnit.HOURS.toMillis(12L);
        this.IDLE_TIME_BEFORE_DISPLAY_INFO = TimeUnit.SECONDS.toMillis(1L);
        this.mHandler = new Handler();
        this.fastDataDisplay = true;
        this.mUpdateTimeIndicator = new Runnable() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity$mUpdateTimeIndicator$1
            @Override // java.lang.Runnable
            public final void run() {
                this.this$0.updateTimefunction();
            }
        };
        this.refreshHandler = new Handler(new Handler.Callback() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity$refreshHandler$1
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return true;
            }
        });
        this.refreshCallback = new Runnable() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity$refreshCallback$1
            @Override // java.lang.Runnable
            public final void run() {
                this.this$0.refreshActivity();
            }
        };
        this.displayProgramInfo = new Runnable() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity.displayProgramInfo.1
            @Override // java.lang.Runnable
            public final void run() {
                GuideActivity.this.displayProgramInfo();
            }
        };
        this.isFirstDisplayOfInstance = true;
        this.isFirstTimeFocusChanged = true;
        this.mOnLayoutListenerForShow = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity$mOnLayoutListenerForShow$1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                this.this$0.mOnLayoutListenerForShowFun();
            }
        };
        this.programManagerListener = new ProgramManager.ListenerAdapter() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity$programManagerListener$1
            @Override // com.dotscreen.portal.ui.screens.guide.ProgramManager.ListenerAdapter, com.dotscreen.portal.ui.screens.guide.ProgramManager.Listener
            public void onTimeRangeUpdated() {
                long shiftedTime = (((long) this.this$0.mWidthPerHour) * this.this$0.getMProgramManager().getShiftedTime()) / this.this$0.HOUR_IN_MILLIS;
                Log.d("GuideActivity", "Horizontal scroll to " + shiftedTime + " pixels (" + this.this$0.getMProgramManager().getShiftedTime() + " millis)");
                TimelineRow timelineRow = this.this$0.mTimelineRow;
                if (timelineRow == null) {
                    Intrinsics.throwNpe();
                }
                timelineRow.scrollTo((int) shiftedTime, this.this$0.mTimelineAnimation);
            }
        };
    }

    public final ProgramManager getMProgramManager() {
        ProgramManager programManager = this.mProgramManager;
        if (programManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        return programManager;
    }

    public final void setMProgramManager(ProgramManager programManager) {
        Intrinsics.checkParameterIsNotNull(programManager, "<set-?>");
        this.mProgramManager = programManager;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateTimefunction() {
        positionCurrentTimeIndicator();
        stopCurrentTimeIndicator();
        this.mHandler.postAtTime(this.mUpdateTimeIndicator, ceilTime(SystemClock.uptimeMillis(), this.TIME_INDICATOR_UPDATE_FREQUENCY));
    }

    public final int getStatus() {
        return this.status;
    }

    public final void setStatus(int i) {
        this.status = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshActivity() {
        this.start_focus_position = -1;
        loadData();
        Log.d(this.TAG, "REFRESH ACTIVITY");
        this.status = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetDisconnectTimer() {
        if (this.status == 2) {
            refreshActivity();
        }
        Log.d(this.TAG, "RESET TIMER");
        this.refreshHandler.removeCallbacks(this.refreshCallback);
        this.refreshHandler.postDelayed(this.refreshCallback, this.REFRESH_TIMEOUT);
        View viewFindViewById = findViewById(R.id.dca_background);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById<ImageView>(R.id.dca_background)");
        if (((ImageView) viewFindViewById).getVisibility() != 0) {
            this.refreshHandler.removeCallbacks(this.displayProgramInfo);
            this.refreshHandler.postDelayed(this.displayProgramInfo, this.IDLE_TIME_BEFORE_DISPLAY_INFO);
        }
        this.status = 0;
    }

    private final void stopDisconnectTimer() {
        Log.d(this.TAG, "STOP TIMER");
        this.refreshHandler.removeCallbacks(this.refreshCallback);
        this.status = 2;
    }

    private final void stopDisplayProgramInfo() {
        this.refreshHandler.removeCallbacks(this.displayProgramInfo);
    }

    @Override // android.app.Activity
    public void onUserInteraction() {
        Log.d(this.TAG, "USER INTERACTION");
        resetDisconnectTimer();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        hideProgramInfo();
        return super.onKeyDown(keyCode, event);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        int currentChannelIndexByTableEntry;
        TableEntry selectedEntry = getSelectedEntry();
        if (selectedEntry != null) {
            ProgramManager programManager = this.mProgramManager;
            if (programManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
            }
            currentChannelIndexByTableEntry = programManager.getCurrentChannelIndexByTableEntry(selectedEntry);
        } else {
            currentChannelIndexByTableEntry = 0;
        }
        this.start_focus_position = currentChannelIndexByTableEntry;
        return super.onKeyUp(keyCode, event);
    }

    private final void hideProgramInfo() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.program_info);
        if (linearLayout == null || linearLayout.getVisibility() != 0) {
            return;
        }
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, 0.0f, linearLayout.getHeight());
        translateAnimation.setDuration(100L);
        translateAnimation.setFillAfter(true);
        linearLayout.startAnimation(translateAnimation);
        linearLayout.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void displayProgramInfo() {
        int i;
        URL urlUrlWithSize$default;
        URL urlUrlWithSize$default2;
        TableEntry selectedEntry = getSelectedEntry();
        String string = null;
        if ((selectedEntry != null ? selectedEntry.getProgram() : null) != null) {
            View viewFindViewById = findViewById(R.id.dca_view);
            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById<DirectChann…ccessView>(R.id.dca_view)");
            boolean z = true;
            if (viewFindViewById.getVisibility() == 0) {
                return;
            }
            TVAiring program = selectedEntry.getProgram();
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.program_info);
            if (linearLayout != null) {
                View viewFindViewById2 = findViewById(R.id.image_info);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.image_info)");
                ImageView imageView = (ImageView) viewFindViewById2;
                View viewFindViewById3 = findViewById(R.id.textViewTitleInfo);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "findViewById(R.id.textViewTitleInfo)");
                TextView textView = (TextView) viewFindViewById3;
                View viewFindViewById4 = findViewById(R.id.textViewTypeInfo);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "findViewById(R.id.textViewTypeInfo)");
                TextView textView2 = (TextView) viewFindViewById4;
                View viewFindViewById5 = findViewById(R.id.textViewTimeInfo);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "findViewById(R.id.textViewTimeInfo)");
                TextView textView3 = (TextView) viewFindViewById5;
                View viewFindViewById6 = findViewById(R.id.textViewDescriptionInfo);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "findViewById(R.id.textViewDescriptionInfo)");
                TextView textView4 = (TextView) viewFindViewById6;
                linearLayout.setVisibility(0);
                TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 0.0f, linearLayout.getHeight(), 0.0f);
                translateAnimation.setDuration(300L);
                linearLayout.startAnimation(translateAnimation);
                final float f = 20.0f;
                imageView.setOutlineProvider(new ViewOutlineProvider() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity$displayProgramInfo$2$1
                    @Override // android.view.ViewOutlineProvider
                    public void getOutline(View view, Outline outline) {
                        if (outline != null) {
                            if (view == null) {
                                Intrinsics.throwNpe();
                            }
                            outline.setRoundRect(0, 0, (int) (view.getWidth() + f), view.getHeight(), f);
                        }
                    }
                });
                imageView.setClipToOutline(true);
                Artwork stillArtwork = selectedEntry.getProgram().getStillArtwork();
                if (((stillArtwork == null || (urlUrlWithSize$default2 = Artwork.urlWithSize$default(stillArtwork, new Point(269, 162), 0.0d, 2, null)) == null) ? null : urlUrlWithSize$default2.toString()) != null) {
                    ImageLoaderManager imageLoaderManager = new ImageLoaderManager(this);
                    Artwork stillArtwork2 = selectedEntry.getProgram().getStillArtwork();
                    if (stillArtwork2 != null && (urlUrlWithSize$default = Artwork.urlWithSize$default(stillArtwork2, new Point(269, 162), 0.0d, 2, null)) != null) {
                        string = urlUrlWithSize$default.toString();
                    }
                    imageLoaderManager.loadImage(string, (1016 & 2) != 0 ? (ImageView) null : imageView, (1016 & 4) != 0 ? false : true, (1016 & 8) != 0 ? 300 : 0, (1016 & 16) != 0 ? 0 : 0, (1016 & 32) != 0 ? 0 : 0, (1016 & 64) != 0 ? (ImageView.ScaleType) null : null, (1016 & 128) != 0 ? 0 : 0, (1016 & 256) == 0 ? R.drawable.ph_horizontal : 0, (1016 & 512) != 0 ? (RequestListener) null : null);
                    i = 8;
                } else {
                    i = 8;
                    imageView.setVisibility(8);
                    linearLayout.setVisibility(0);
                }
                String title = program.getTitle();
                if (title == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                String upperCase = title.toUpperCase();
                Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase()");
                textView.setText(upperCase);
                textView2.setText(getTypeFromMetadata(program.getMetadata()));
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH':'mm", Locale.getDefault(Locale.Category.FORMAT));
                textView3.setText(getString(R.string.program_info_time, new Object[]{simpleDateFormat.format(Long.valueOf(selectedEntry.getEntryStartUtcMillis())), simpleDateFormat.format(Long.valueOf(selectedEntry.getEntryEndUtcMillis()))}));
                String summary = program.getSummary();
                if (summary != null && !StringsKt.isBlank(summary)) {
                    z = false;
                }
                if (z) {
                    textView4.setVisibility(i);
                } else {
                    textView4.setVisibility(0);
                    textView4.setText(program.getSummary());
                }
            }
        }
    }

    private final String getTypeFromMetadata(MediaMetadata metadata) {
        String str;
        String title;
        if (metadata == null) {
            return "";
        }
        if (!(metadata.getGenres().length == 0)) {
            str = "";
            for (String str2 : metadata.getGenres()) {
                str = str + str2;
                if (!Intrinsics.areEqual((String) ArraysKt.last(metadata.getGenres()), str2)) {
                    str = str + ", ";
                }
            }
        } else {
            str = "";
        }
        Integer durationInMinutes = metadata.getDurationInMinutes();
        if ((durationInMinutes != null ? durationInMinutes.intValue() : 0) > 0) {
            if ((!Intrinsics.areEqual(str, "")) && !StringsKt.endsWith$default(str, "| ", false, 2, (Object) null)) {
                str = str + " | ";
            }
            str = str + String.valueOf(metadata.getDurationInMinutes()) + "min";
        }
        ParentalRating parentalLevel = metadata.getParentalLevel();
        if (parentalLevel == null || (title = parentalLevel.getTitle()) == null) {
            title = "NR";
        }
        if (!(!Intrinsics.areEqual("NR", title))) {
            return str;
        }
        if ((!Intrinsics.areEqual(str, "")) && !StringsKt.endsWith$default(str, "| ", false, 2, (Object) null)) {
            str = str + " | ";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        ParentalRating parentalLevel2 = metadata.getParentalLevel();
        sb.append(parentalLevel2 != null ? parentalLevel2.getTitle() : null);
        return sb.toString();
    }

    private final TableEntry getSelectedEntry() {
        ProgramGrid programGrid = this.mGrid;
        if (programGrid == null) {
            return null;
        }
        if (programGrid == null) {
            Intrinsics.throwNpe();
        }
        if (!programGrid.hasFocus()) {
            return null;
        }
        ProgramGrid programGrid2 = this.mGrid;
        if (programGrid2 == null) {
            Intrinsics.throwNpe();
        }
        if (programGrid2.mLastFocusedView == null) {
            return null;
        }
        ProgramGrid programGrid3 = this.mGrid;
        if (programGrid3 == null) {
            Intrinsics.throwNpe();
        }
        View view = programGrid3.mLastFocusedView;
        if (view != null) {
            return ((ProgramItemMegaCell) view).getMTableEntry();
        }
        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.guide.ProgramItemMegaCell");
    }

    /* JADX INFO: renamed from: isFirstDisplayOfInstance, reason: from getter */
    public final boolean getIsFirstDisplayOfInstance() {
        return this.isFirstDisplayOfInstance;
    }

    public final void setFirstDisplayOfInstance(boolean z) {
        this.isFirstDisplayOfInstance = z;
    }

    /* JADX INFO: renamed from: isFirstTimeFocusChanged, reason: from getter */
    public final boolean getIsFirstTimeFocusChanged() {
        return this.isFirstTimeFocusChanged;
    }

    public final void setFirstTimeFocusChanged(boolean z) {
        this.isFirstTimeFocusChanged = z;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        PortalApplication.Config value;
        super.onCreate(savedInstanceState);
        this.isFirstDisplayOfInstance = true;
        DSLog.INSTANCE.epgGridTimeStamp(this.TAG + '@' + hashCode(), false);
        GuideActivity guideActivity = this;
        PortalApplicationSingletons singleton = PortalApplication.INSTANCE.getSingleton(guideActivity);
        if (singleton == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        final PortalApplication portalApplication = (PortalApplication) singleton;
        final LiveData<PortalApplication.Config> config = portalApplication.getConfig();
        if (config.getValue() == null) {
            setContentView(R.layout.activity_startup);
            config.observeForever(new Observer<PortalApplication.Config>() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity$onCreate$observer$1
                @Override // androidx.lifecycle.Observer
                public void onChanged(PortalApplication.Config config2) {
                    PortalApplication.Config config3;
                    Log.d(this.this$0.TAG, "...config loaded");
                    this.this$0.finish();
                    if (portalApplication.getDegradedMode() && (!CommonUtils.isDVBCSTB(this.this$0) || portalApplication.getBootWithoutInternet())) {
                        PortalApplication.showToastMessage$default(portalApplication, R.string.noAvailable, 0, 2, (Object) null);
                        return;
                    }
                    PortalApplication.Config config4 = (PortalApplication.Config) config.getValue();
                    if ((config4 != null ? config4.getUser() : null) == null && (config3 = (PortalApplication.Config) config.getValue()) != null && !config3.getLoginFailed()) {
                        this.this$0.startActivity(Intent.parseUri("megacable://com.dotscreen.megacable.portal/profile?action=choose", 0));
                    } else {
                        GuideActivity guideActivity2 = this.this$0;
                        guideActivity2.startActivity(guideActivity2.getIntent());
                    }
                    config.removeObserver(this);
                }
            });
            portalApplication.loadConfig();
        } else {
            PortalApplication.Config value2 = config.getValue();
            if ((value2 != null ? value2.getUser() : null) == null && (value = config.getValue()) != null && !value.getLoginFailed()) {
                finish();
            }
        }
        setContentView(R.layout.activity_guide);
        if (portalApplication.getDegradedMode() && (!CommonUtils.isDVBCSTB(guideActivity) || portalApplication.getBootWithoutInternet())) {
            Toast.makeText(guideActivity, R.string.noAvailable, 1).show();
            finish();
            return;
        }
        this.mProgramManager = new ProgramManager();
        this.mWidthPerHour = getResources().getDimensionPixelSize(R.dimen.program_guide_table_width_per_hour);
        GuideUtils.INSTANCE.setWidthPerHour(this.mWidthPerHour);
        Point point = new Point();
        WindowManager windowManager = getWindowManager();
        Intrinsics.checkExpressionValueIsNotNull(windowManager, "windowManager");
        windowManager.getDefaultDisplay().getSize(point);
        this.mViewPortMillis = (((long) ((point.x - getResources().getDimensionPixelOffset(R.dimen.program_guide_table_margin_start)) - getResources().getDimensionPixelSize(R.dimen.program_guide_table_header_column_width))) * this.HOUR_IN_MILLIS) / ((long) this.mWidthPerHour);
        this.mRowHeight = getResources().getDimensionPixelSize(R.dimen.program_guide_table_item_row_height) + 50;
        this.mDetailHeight = getResources().getDimensionPixelSize(R.dimen.program_guide_table_detail_height);
        this.mSelectionRow = getResources().getInteger(R.integer.program_guide_selection_row);
        TimelineRow timelineRow = (TimelineRow) findViewById(R.id.time_row);
        if (timelineRow != null) {
            this.mTimelineRow = timelineRow;
            Resources resources = getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            this.mTimeListAdapter = new TimeListAdapter(resources);
            TimelineRow timelineRow2 = this.mTimelineRow;
            if (timelineRow2 == null) {
                Intrinsics.throwNpe();
            }
            timelineRow2.getRecycledViewPool().setMaxRecycledViews(R.layout.program_guide_table_header_row_item, 7);
            TimelineRow timelineRow3 = this.mTimelineRow;
            if (timelineRow3 == null) {
                Intrinsics.throwNpe();
            }
            timelineRow3.setAdapter(this.mTimeListAdapter);
            RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity$onCreate$$inlined$let$lambda$1
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
                    this.this$0.onHorizontalScrolled(dx);
                }
            };
            TimelineRow timelineRow4 = this.mTimelineRow;
            if (timelineRow4 == null) {
                Intrinsics.throwNpe();
            }
            timelineRow4.addOnScrollListener(onScrollListener);
        }
        final FontTextView fontTextView = (FontTextView) findViewById(R.id.tabFilter);
        if (fontTextView != null) {
            fontTextView.setText(getString(R.string.epg_tab_filter, new Object[]{getString(R.string.epg_filter_all)}));
            fontTextView.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity$onCreate$$inlined$let$lambda$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DSLog.INSTANCE.a("Clicked on Filter");
                    List list = this.channelGenres;
                    if ((list != null ? list.size() : 0) > 0) {
                        List<String> listMutableListOf = CollectionsKt.mutableListOf(this.getString(R.string.epg_filter_all));
                        List list2 = this.channelGenres;
                        if (list2 == null) {
                            Intrinsics.throwNpe();
                        }
                        listMutableListOf.addAll(list2);
                        CheckListDialog.Builder itemList = new CheckListDialog.Builder(this).setTitle(R.string.dialog_select_category_title).setItemList(listMutableListOf);
                        List list3 = this.channelGenres;
                        if (list3 == null) {
                            Intrinsics.throwNpe();
                        }
                        String str = this.currentGenre;
                        if (str == null) {
                            str = "";
                        }
                        itemList.setSelectedItem(list3.indexOf(str) + 1).setOnItemClickedListener(new CheckListDialog.OnItemClickedListener() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity$onCreate$$inlined$let$lambda$2.1
                            @Override // com.dotscreen.tv.ui.dialog.CheckListDialog.OnItemClickedListener
                            public void onItemClicked(CheckListDialog dialog, int itemIndex) {
                                Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                                if (itemIndex == 0) {
                                    this.currentGenre = (String) null;
                                    this.getMProgramManager().filterChannel("todos");
                                    fontTextView.setText(this.getString(R.string.epg_tab_filter, new Object[]{this.getString(R.string.epg_filter_all)}));
                                } else {
                                    GuideActivity guideActivity2 = this;
                                    List list4 = this.channelGenres;
                                    if (list4 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    guideActivity2.currentGenre = (String) list4.get(itemIndex - 1);
                                    ProgramManager mProgramManager = this.getMProgramManager();
                                    String str2 = this.currentGenre;
                                    if (str2 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    mProgramManager.filterChannel(str2);
                                    fontTextView.setText(this.getString(R.string.epg_tab_filter, new Object[]{this.currentGenre}));
                                }
                                dialog.dismiss();
                            }
                        }).create().show();
                    }
                }
            });
        }
        DirectChannelAccessView directChannelAccessView = (DirectChannelAccessView) findViewById(R.id.dca_view);
        if (directChannelAccessView != null) {
            directChannelAccessView.setShowTitleName(false);
            directChannelAccessView.setOnVisibilityChangedListener(new DirectChannelAccessView.OnVisibilityChangedListener() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity$onCreate$$inlined$let$lambda$3
                @Override // com.dotscreen.tv.ui.DirectChannelAccessView.OnVisibilityChangedListener
                public void onVisibilityChanged(boolean visible) {
                    ImageView imageView = (ImageView) this.this$0.findViewById(R.id.dca_background);
                    if (imageView != null) {
                        if (visible) {
                            imageView.setVisibility(0);
                        } else {
                            imageView.setVisibility(8);
                            this.this$0.resetDisconnectTimer();
                        }
                    }
                }
            });
            directChannelAccessView.setOnChannelSelectedListener(new DirectChannelAccessView.OnChannelSelectedListener() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity$onCreate$$inlined$let$lambda$4
                @Override // com.dotscreen.tv.ui.DirectChannelAccessView.OnChannelSelectedListener
                public void onChannelSelected(int channelNumber) {
                    this.this$0.selectChannelByZappingNumber(channelNumber);
                }
            });
        }
        ProgramManager programManager = this.mProgramManager;
        if (programManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        final ProgramTableAdapter programTableAdapter = new ProgramTableAdapter(guideActivity, programManager, this);
        programTableAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity.onCreate.4
            @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
            public void onChanged() {
                GuideActivity.this.resetRowSelection();
                GuideActivity.this.updateGuidePosition();
            }
        });
        this.OnPreDrawListener = new ViewTreeObserver.OnPreDrawListener() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity.onCreate.5
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public final boolean onPreDraw() {
                ProgramGrid programGrid = GuideActivity.this.mGrid;
                if (programGrid == null) {
                    Intrinsics.throwNpe();
                }
                if (programGrid.getChildCount() <= 0) {
                    return true;
                }
                ProgramGrid programGrid2 = GuideActivity.this.mGrid;
                if (programGrid2 == null) {
                    Intrinsics.throwNpe();
                }
                programGrid2.getViewTreeObserver().removeOnPreDrawListener(GuideActivity.this.OnPreDrawListener);
                ProgramGrid programGrid3 = GuideActivity.this.mGrid;
                if (programGrid3 == null) {
                    Intrinsics.throwNpe();
                }
                programGrid3.setSelectedPosition(GuideActivity.this.start_focus_position);
                ProgramGrid programGrid4 = GuideActivity.this.mGrid;
                if (programGrid4 == null) {
                    Intrinsics.throwNpe();
                }
                programGrid4.requestFocus();
                new Handler().postDelayed(new Runnable() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity.onCreate.5.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ProgramGrid programGrid5 = GuideActivity.this.mGrid;
                        if (programGrid5 == null) {
                            Intrinsics.throwNpe();
                        }
                        programGrid5.setAlpha(1.0f);
                    }
                }, 1L);
                return true;
            }
        };
        this.mTable = (RelativeLayout) findViewById(R.id.program_guide_table);
        this.loaderProgress = (ProgressBar) findViewById(R.id.loaderProgress);
        this.dayText = (TextView) findViewById(R.id.dayText);
        setCurrentDate(new Date());
        ProgramGrid programGrid = (ProgramGrid) findViewById(R.id.grid);
        if (programGrid != null) {
            this.mGrid = programGrid;
            if (programGrid == null) {
                Intrinsics.throwNpe();
            }
            ProgramManager programManager2 = this.mProgramManager;
            if (programManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
            }
            programGrid.initialize(programManager2);
            ProgramGrid programGrid2 = this.mGrid;
            if (programGrid2 == null) {
                Intrinsics.throwNpe();
            }
            programGrid2.getRecycledViewPool().setMaxRecycledViews(R.layout.program_guide_table_row, 15);
            ProgramGrid programGrid3 = this.mGrid;
            if (programGrid3 == null) {
                Intrinsics.throwNpe();
            }
            programGrid3.setAdapter(programTableAdapter);
            ProgramGrid programGrid4 = this.mGrid;
            if (programGrid4 == null) {
                Intrinsics.throwNpe();
            }
            programGrid4.setChildFocusListener(this);
            ProgramGrid programGrid5 = this.mGrid;
            if (programGrid5 == null) {
                Intrinsics.throwNpe();
            }
            programGrid5.setOnChildSelectedListener(new OnChildSelectedListener() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity$onCreate$$inlined$let$lambda$5
                @Override // androidx.leanback.widget.OnChildSelectedListener
                public final void onChildSelected(ViewGroup viewGroup, View view, int i, long j) {
                    if (this.this$0.mIsDuringResetRowSelection) {
                        this.this$0.mIsDuringResetRowSelection = false;
                        return;
                    }
                    if (!this.this$0.getIsFirstTimeFocusChanged()) {
                        this.this$0.setSelectedProgramFocus(view);
                    } else {
                        this.this$0.setFirstTimeFocusChanged(false);
                        this.this$0.setInitialProgramFocus(view);
                    }
                    NotificationView notificationView = (NotificationView) this.this$0.findViewById(R.id.notification_icon);
                    if (notificationView == null || i < 0) {
                        return;
                    }
                    notificationView.setFocusable(0);
                }
            });
            ProgramGrid programGrid6 = this.mGrid;
            if (programGrid6 == null) {
                Intrinsics.throwNpe();
            }
            programGrid6.setWindowAlignmentOffset(this.mSelectionRow * this.mRowHeight);
            ProgramGrid programGrid7 = this.mGrid;
            if (programGrid7 == null) {
                Intrinsics.throwNpe();
            }
            programGrid7.setWindowAlignmentOffsetPercent(-1.0f);
            ProgramGrid programGrid8 = this.mGrid;
            if (programGrid8 == null) {
                Intrinsics.throwNpe();
            }
            programGrid8.setItemAlignmentOffset(0);
            ProgramGrid programGrid9 = this.mGrid;
            if (programGrid9 == null) {
                Intrinsics.throwNpe();
            }
            programGrid9.getViewTreeObserver().addOnPreDrawListener(this.OnPreDrawListener);
            ProgramGrid programGrid10 = this.mGrid;
            if (programGrid10 == null) {
                Intrinsics.throwNpe();
            }
            programGrid10.setItemSpacing(0);
            ProgramGrid programGrid11 = this.mGrid;
            if (programGrid11 == null) {
                Intrinsics.throwNpe();
            }
            programGrid11.setAlpha(0.0f);
        }
        this.mSharedPreference = PreferenceManager.getDefaultSharedPreferences(guideActivity);
        this.mCurrentTimeIndicator = findViewById(R.id.current_time_indicator);
        View viewFindViewById = findViewById(R.id.container);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.container)");
        this.mContainer = (RelativeLayout) viewFindViewById;
        loadData();
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.guide.GuideActivity$onRestart$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: GuideActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
    @DebugMetadata(c = "com.dotscreen.portal.ui.screens.guide.GuideActivity$onRestart$1", f = "GuideActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    static final class C03851 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;

        C03851(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C03851 c03851 = GuideActivity.this.new C03851(completion);
            c03851.p$ = (CoroutineScope) obj;
            return c03851;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C03851) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws IOException {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            LiveData<PortalApplication.Config> config = PortalApplication.INSTANCE.getSingleton(GuideActivity.this).getConfig();
            Intrinsics.checkExpressionValueIsNotNull(config, "PortalApplication.getSin…his@GuideActivity).config");
            PortalApplication.Config value = config.getValue();
            if (value != null) {
                ContentResolver contentResolver = GuideActivity.this.getContentResolver();
                Uri content_uri = LastChannelsProvider.INSTANCE.getCONTENT_URI();
                String str = LastChannelsProvider.INSTANCE.getCOLUMN_PROFILE_ID() + "=?";
                String[] strArr = new String[1];
                RightVUser user = value.getUser();
                if (user == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVUser");
                }
                strArr[0] = user.getCompassId();
                Cursor cursorQuery = contentResolver.query(content_uri, null, str, strArr, null);
                if (cursorQuery != null) {
                    Cursor cursor = cursorQuery;
                    Throwable th = (Throwable) null;
                    try {
                        Cursor cursor2 = cursor;
                        if (cursor2.moveToLast()) {
                            Cursor cursorQuery2 = GuideActivity.this.getContentResolver().query(TvContract.buildChannelUri(cursor2.getLong(1)), Channel.PROJECTION, null, null, null);
                            if (cursorQuery2 != null) {
                                Cursor cursor3 = cursorQuery2;
                                Throwable th2 = (Throwable) null;
                                try {
                                    Cursor cursor4 = cursor3;
                                    if (cursor4.moveToFirst()) {
                                        Channel tifChannel = Channel.fromCursor(cursor4);
                                        Intrinsics.checkExpressionValueIsNotNull(tifChannel, "tifChannel");
                                        Context applicationContext = GuideActivity.this.getApplicationContext();
                                        if (applicationContext == null) {
                                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                                        }
                                        com.dotscreen.bokit.model.Channel optimizerChannel = TIFCompanionLibraryExtensionKt.getOptimizerChannel(tifChannel, ((PortalApplication) applicationContext).getBOService());
                                        if (optimizerChannel != null) {
                                            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new GuideActivity$onRestart$1$invokeSuspend$$inlined$let$lambda$1(optimizerChannel, null, this), 2, null);
                                        }
                                    }
                                    Unit unit = Unit.INSTANCE;
                                    CloseableKt.closeFinally(cursor3, th2);
                                } catch (Throwable th3) {
                                    try {
                                        throw th3;
                                    } catch (Throwable th4) {
                                        CloseableKt.closeFinally(cursor3, th3);
                                        throw th4;
                                    }
                                }
                            }
                        }
                        Unit unit2 = Unit.INSTANCE;
                        CloseableKt.closeFinally(cursor, th);
                    } catch (Throwable th5) {
                        try {
                            throw th5;
                        } catch (Throwable th6) {
                            CloseableKt.closeFinally(cursor, th5);
                            throw th6;
                        }
                    }
                }
            }
            return Unit.INSTANCE;
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new C03851(null), 2, null);
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        FontTextView fontTextView;
        if (event != null && event.getAction() == 1) {
            if (event.getKeyCode() == 19 && Intrinsics.areEqual(this.last_focus, getCurrentFocus())) {
                FontTextView fontTextView2 = (FontTextView) findViewById(R.id.tabFilter);
                if (fontTextView2 != null) {
                    NotificationView notificationView = (NotificationView) findViewById(R.id.notification_icon);
                    if (notificationView != null) {
                        notificationView.setFocusable(1);
                    }
                    fontTextView2.requestFocus();
                    return true;
                }
            } else if (event.getKeyCode() == 172 && (fontTextView = (FontTextView) findViewById(R.id.tabFilter)) != null) {
                fontTextView.requestFocus();
                return true;
            }
        }
        this.last_focus = getCurrentFocus();
        return super.dispatchKeyEvent(event);
    }

    public final void setCurrentDate(Date date) {
        String str;
        Intrinsics.checkParameterIsNotNull(date, "date");
        String pattern1 = new SimpleDateFormat("EEEE").format(date);
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkExpressionValueIsNotNull(pattern1, "pattern1");
        if (pattern1 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = pattern1.substring(0, 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (strSubstring == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase = strSubstring.toUpperCase();
        Intrinsics.checkNotNullExpressionValue(upperCase, "(this as java.lang.String).toUpperCase()");
        sb.append(upperCase);
        String strSubstring2 = pattern1.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
        sb.append(strSubstring2);
        String str2 = (sb.toString() + " ") + new SimpleDateFormat("d").format(date);
        if (Intrinsics.areEqual(Locale.getDefault().getLanguage(), "es")) {
            str = str2 + " de ";
        } else {
            str = str2 + " ";
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        String str3 = new SimpleDateFormat("MMMM").format(date);
        Intrinsics.checkExpressionValueIsNotNull(str3, "SimpleDateFormat(\"MMMM\").format(date)");
        if (str3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring3 = str3.substring(0, 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (strSubstring3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase2 = strSubstring3.toUpperCase();
        Intrinsics.checkNotNullExpressionValue(upperCase2, "(this as java.lang.String).toUpperCase()");
        sb2.append(upperCase2);
        String str4 = new SimpleDateFormat("MMMM").format(date);
        Intrinsics.checkExpressionValueIsNotNull(str4, "SimpleDateFormat(\"MMMM\").format(date)");
        if (str4 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring4 = str4.substring(1);
        Intrinsics.checkNotNullExpressionValue(strSubstring4, "(this as java.lang.String).substring(startIndex)");
        sb2.append(strSubstring4);
        String string = sb2.toString();
        TextView textView = this.dayText;
        if (textView == null) {
            Intrinsics.throwNpe();
        }
        if (string == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String upperCase3 = string.toUpperCase();
        Intrinsics.checkNotNullExpressionValue(upperCase3, "(this as java.lang.String).toUpperCase()");
        textView.setText(upperCase3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void mOnLayoutListenerForShowFun() {
        RelativeLayout relativeLayout = this.mContainer;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContainer");
        }
        relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this.mOnLayoutListenerForShow);
        RelativeLayout relativeLayout2 = this.mTable;
        if (relativeLayout2 == null) {
            Intrinsics.throwNpe();
        }
        relativeLayout2.setLayerType(2, null);
        RelativeLayout relativeLayout3 = this.mTable;
        if (relativeLayout3 == null) {
            Intrinsics.throwNpe();
        }
        relativeLayout3.buildLayer();
        this.mOnLayoutListenerForShow = (ViewTreeObserver.OnGlobalLayoutListener) null;
        this.mTimelineAnimation = true;
        stopCurrentTimeIndicator();
        this.mHandler.postDelayed(this.mUpdateTimeIndicator, this.TIME_INDICATOR_UPDATE_FREQUENCY);
        updateGuidePosition();
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        resetDisconnectTimer();
        if (this.mOnLayoutListenerForShow == null) {
            stopCurrentTimeIndicator();
            this.mHandler.postDelayed(this.mUpdateTimeIndicator, this.TIME_INDICATOR_UPDATE_FREQUENCY);
            updateGuidePosition();
            positionCurrentTimeIndicator();
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        stopCurrentTimeIndicator();
        stopDisconnectTimer();
        stopDisplayProgramInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateGuidePosition() {
        Resources resources = getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
        RelativeLayout relativeLayout = this.mContainer;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContainer");
        }
        int height = relativeLayout.getHeight();
        if (height <= 0) {
            return;
        }
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.program_guide_table_margin_top);
        int dimensionPixelOffset2 = resources.getDimensionPixelOffset(R.dimen.program_guide_table_margin_bottom);
        int dimensionPixelOffset3 = resources.getDimensionPixelOffset(R.dimen.program_guide_table_header_row_height) + this.mDetailHeight;
        int i = this.mRowHeight;
        ProgramGrid programGrid = this.mGrid;
        if (programGrid == null) {
            Intrinsics.throwNpe();
        }
        RecyclerView.Adapter adapter = programGrid.getAdapter();
        if (adapter == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(adapter, "mGrid!!.adapter!!");
        int itemCount = dimensionPixelOffset3 + (i * adapter.getItemCount()) + dimensionPixelOffset + dimensionPixelOffset2;
        if (itemCount > height) {
            RelativeLayout relativeLayout2 = this.mTable;
            if (relativeLayout2 == null) {
                Intrinsics.throwNpe();
            }
            relativeLayout2.setPaddingRelative(0, dimensionPixelOffset, 0, 0);
            RelativeLayout relativeLayout3 = this.mTable;
            if (relativeLayout3 == null) {
                Intrinsics.throwNpe();
            }
            ViewGroup.LayoutParams layoutParams = relativeLayout3.getLayoutParams();
            layoutParams.height = -2;
            RelativeLayout relativeLayout4 = this.mTable;
            if (relativeLayout4 == null) {
                Intrinsics.throwNpe();
            }
            relativeLayout4.setLayoutParams(layoutParams);
            return;
        }
        RelativeLayout relativeLayout5 = this.mTable;
        if (relativeLayout5 == null) {
            Intrinsics.throwNpe();
        }
        relativeLayout5.setPaddingRelative(0, dimensionPixelOffset, 0, dimensionPixelOffset2);
        RelativeLayout relativeLayout6 = this.mTable;
        if (relativeLayout6 == null) {
            Intrinsics.throwNpe();
        }
        ViewGroup.LayoutParams layoutParams2 = relativeLayout6.getLayoutParams();
        layoutParams2.height = itemCount;
        RelativeLayout relativeLayout7 = this.mTable;
        if (relativeLayout7 == null) {
            Intrinsics.throwNpe();
        }
        relativeLayout7.setLayoutParams(layoutParams2);
    }

    @Override // com.dotscreen.portal.ui.screens.guide.ProgramGrid.ChildFocusListener
    public void onRequestChildFocus(View oldFocus, View newFocus) {
        if (oldFocus == null || newFocus == null) {
            return;
        }
        int i = this.mSelectionRow * this.mRowHeight;
        if (oldFocus.getTop() < newFocus.getTop()) {
            ProgramGrid programGrid = this.mGrid;
            if (programGrid == null) {
                Intrinsics.throwNpe();
            }
            programGrid.setWindowAlignmentOffset(i + this.mRowHeight + this.mDetailHeight);
            ProgramGrid programGrid2 = this.mGrid;
            if (programGrid2 == null) {
                Intrinsics.throwNpe();
            }
            programGrid2.setItemAlignmentOffsetPercent(100.0f);
            return;
        }
        if (oldFocus.getTop() > newFocus.getTop()) {
            ProgramGrid programGrid3 = this.mGrid;
            if (programGrid3 == null) {
                Intrinsics.throwNpe();
            }
            programGrid3.setWindowAlignmentOffset(i);
            ProgramGrid programGrid4 = this.mGrid;
            if (programGrid4 == null) {
                Intrinsics.throwNpe();
            }
            programGrid4.setItemAlignmentOffsetPercent(0.0f);
        }
    }

    private final void loadData() {
        OptimizerGuideViewModel.Factory factory;
        setLoaderVisible(true);
        ProgramGrid programGrid = this.mGrid;
        if (programGrid == null) {
            Intrinsics.throwNpe();
        }
        programGrid.setAlpha(0.5f);
        Context applicationContext = getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        if (((PortalApplication) applicationContext).getDegradedMode()) {
            factory = new TIFGuideViewModel.Factory(this);
        } else {
            factory = new OptimizerGuideViewModel.Factory(this);
        }
        ViewModel viewModel = ViewModelProviders.of(this, factory).get(GuideViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ideViewModel::class.java)");
        GuideViewModel.getValue$default((GuideViewModel) viewModel, this.fastDataDisplay ? 5 : 6, 0, 2, null).observe(this, new Observer<GuideViewModel.Data>() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity.loadData.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(GuideViewModel.Data data) throws IOException {
                RightVUser user;
                Cursor cursorQuery;
                if (data != null) {
                    Log.d(GuideActivity.this.TAG, "data loaded");
                    if (GuideActivity.this.getMProgramManager().getMSelectedProgram() == null || GuideActivity.this.getIsFirstTimeFocusChanged()) {
                        if (GuideActivity.this.start_focus_position <= 0) {
                            LiveData<PortalApplication.Config> config = PortalApplication.INSTANCE.getSingleton(GuideActivity.this).getConfig();
                            Intrinsics.checkExpressionValueIsNotNull(config, "PortalApplication.getSingleton(this).config");
                            PortalApplication.Config value = config.getValue();
                            if (value != null && (user = value.getUser()) != null) {
                                Cursor cursorQuery2 = GuideActivity.this.getContentResolver().query(LastChannelsProvider.INSTANCE.getCONTENT_URI(), null, LastChannelsProvider.INSTANCE.getCOLUMN_PROFILE_ID() + "=?", new String[]{user.getCompassId()}, null);
                                if (cursorQuery2 != null) {
                                    Cursor cursor = cursorQuery2;
                                    Throwable th = (Throwable) null;
                                    try {
                                        Cursor cursor2 = cursor;
                                        if (cursor2.moveToLast() && (cursorQuery = GuideActivity.this.getContentResolver().query(TvContract.buildChannelUri(cursor2.getLong(1)), Channel.PROJECTION, null, null, null)) != null) {
                                            Cursor cursor3 = cursorQuery;
                                            Throwable th2 = (Throwable) null;
                                            try {
                                                Cursor cursor4 = cursor3;
                                                if (cursor4.moveToFirst()) {
                                                    Channel tifChannel = Channel.fromCursor(cursor4);
                                                    Intrinsics.checkExpressionValueIsNotNull(tifChannel, "tifChannel");
                                                    Context applicationContext2 = GuideActivity.this.getApplicationContext();
                                                    if (applicationContext2 != null) {
                                                        GuideActivity.this.start_focus_position = CollectionsKt.indexOf((List<? extends com.dotscreen.bokit.model.Channel>) data.getChannels(), TIFCompanionLibraryExtensionKt.getOptimizerChannel(tifChannel, ((PortalApplication) applicationContext2).getBOService()));
                                                    } else {
                                                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                                                    }
                                                }
                                                Unit unit = Unit.INSTANCE;
                                                CloseableKt.closeFinally(cursor3, th2);
                                            } catch (Throwable th3) {
                                                try {
                                                    throw th3;
                                                } catch (Throwable th4) {
                                                    CloseableKt.closeFinally(cursor3, th3);
                                                    throw th4;
                                                }
                                            }
                                        }
                                        Unit unit2 = Unit.INSTANCE;
                                        CloseableKt.closeFinally(cursor, th);
                                    } catch (Throwable th5) {
                                        try {
                                            throw th5;
                                        } catch (Throwable th6) {
                                            CloseableKt.closeFinally(cursor, th5);
                                            throw th6;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        GuideActivity guideActivity = GuideActivity.this;
                        List<com.dotscreen.bokit.model.Channel> channels = data.getChannels();
                        ArrayList arrayList = new ArrayList();
                        for (T t : channels) {
                            List<String> genres = ((com.dotscreen.bokit.model.Channel) t).getGenres();
                            String str = GuideActivity.this.currentGenre;
                            if (str == null) {
                                str = "todos";
                            }
                            if (genres.contains(str)) {
                                arrayList.add(t);
                            }
                        }
                        ArrayList arrayList2 = arrayList;
                        TableEntry mSelectedProgram = GuideActivity.this.getMProgramManager().getMSelectedProgram();
                        if (mSelectedProgram == null) {
                            Intrinsics.throwNpe();
                        }
                        guideActivity.start_focus_position = arrayList2.indexOf(mSelectedProgram.getChannel());
                    }
                    GuideActivity.this._data = data;
                    LinkedHashSet linkedHashSet = new LinkedHashSet();
                    Iterator<T> it = data.getChannels().iterator();
                    while (it.hasNext()) {
                        linkedHashSet.addAll(((com.dotscreen.bokit.model.Channel) it.next()).getGenres());
                    }
                    GuideActivity.this.channelGenres = CollectionsKt.sorted(linkedHashSet);
                    Log.d(GuideActivity.this.TAG, "channelGenres = " + GuideActivity.this.channelGenres);
                    ProgramGrid programGrid2 = GuideActivity.this.mGrid;
                    if (programGrid2 == null) {
                        Intrinsics.throwNpe();
                    }
                    programGrid2.setAlpha(1.0f);
                    GuideActivity.this.show();
                } else {
                    Toast.makeText(GuideActivity.this, R.string.technical_error_retry_later, 1).show();
                    GuideActivity.this.finish();
                }
                GuideActivity.this.setLoaderVisible(false);
            }
        });
    }

    private final void loadDataFuture() {
        OptimizerGuideViewModel.Factory factory;
        Context applicationContext = getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        if (((PortalApplication) applicationContext).getDegradedMode()) {
            factory = new TIFGuideViewModel.Factory(this);
        } else {
            factory = new OptimizerGuideViewModel.Factory(this);
        }
        ViewModel viewModel = ViewModelProviders.of(this, factory).get(GuideViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…ideViewModel::class.java)");
        GuideViewModel.getValue$default((GuideViewModel) viewModel, 7, 0, 2, null).observe(this, new Observer<GuideViewModel.Data>() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity.loadDataFuture.1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(GuideViewModel.Data data) {
                if (data != null) {
                    GuideActivity.this.getMProgramManager().updateContent(data);
                    GuideActivity.this.getMProgramManager().updateTimeRange(GuideActivity.this.mStartUtcTime, GuideViewModel.INSTANCE.getLastDay().getTimeInMillis());
                }
                ProgramManager mProgramManager = GuideActivity.this.getMProgramManager();
                String str = GuideActivity.this.currentGenre;
                if (str == null) {
                    str = "todos";
                }
                mProgramManager.filterChannel(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void show() {
        ProgramManager programManager = this.mProgramManager;
        if (programManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        programManager.removeListener(this.programManagerListener);
        this.mStartUtcTime = (this.fastDataDisplay ? GuideViewModel.INSTANCE.getCurrentDayStart() : GuideViewModel.INSTANCE.getPastDayStart()).getTimeInMillis();
        long timeInMillis = GuideViewModel.INSTANCE.getCurrentDayEnd().getTimeInMillis();
        long jFloorTime = floorTime(System.currentTimeMillis() - this.MIN_DURATION_FROM_START_TIME_TO_CURRENT_TIME, this.HALF_HOUR_IN_MILLIS);
        ProgramManager programManager2 = this.mProgramManager;
        if (programManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        if (programManager2.getMSelectedProgram() != null && !this.isFirstTimeFocusChanged) {
            ProgramManager programManager3 = this.mProgramManager;
            if (programManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
            }
            TableEntry mSelectedProgram = programManager3.getMSelectedProgram();
            if (mSelectedProgram == null) {
                Intrinsics.throwNpe();
            }
            jFloorTime = mSelectedProgram.getEntryStartUtcMillis();
        } else if (!this.fastDataDisplay) {
            jFloorTime = GuideViewModel.INSTANCE.getCurrentDayStart().getTimeInMillis();
        }
        long j = jFloorTime;
        long j2 = this.mStartUtcTime;
        ProgramManager programManager4 = this.mProgramManager;
        if (programManager4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        programManager4.updateInitialTimeRange(j2, timeInMillis, this.mViewPortMillis);
        ProgramManager programManager5 = this.mProgramManager;
        if (programManager5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        long j3 = j - j2;
        programManager5.shiftTime(j3);
        ProgramManager programManager6 = this.mProgramManager;
        if (programManager6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        GuideViewModel.Data data = this._data;
        if (data == null) {
            Intrinsics.throwNpe();
        }
        programManager6.setContent(data);
        ProgramManager programManager7 = this.mProgramManager;
        if (programManager7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        programManager7.addListener(this.programManagerListener);
        this.mLastRequestedGenreId = 0;
        TimeListAdapter timeListAdapter = this.mTimeListAdapter;
        if (timeListAdapter == null) {
            Intrinsics.throwNpe();
        }
        timeListAdapter.update(j2);
        long jAbs = Math.abs(j3);
        long j4 = j3 >= 0 ? 1 : -1;
        long j5 = 60;
        int dimensionPixelSize = (int) (((long) getResources().getDimensionPixelSize(R.dimen.program_guide_table_width_per_hour)) * (((jAbs / ((long) 1000)) * j5) % j5) * j4);
        int dimensionPixelSize2 = (int) (j4 * ((long) getResources().getDimensionPixelSize(R.dimen.program_guide_table_width_per_hour)) * (jAbs / ((long) 3600000)));
        TimelineRow timelineRow = this.mTimelineRow;
        if (timelineRow == null) {
            Intrinsics.throwNpe();
        }
        timelineRow.scrollTo(dimensionPixelSize2 + dimensionPixelSize, false);
        TimelineRow timelineRow2 = this.mTimelineRow;
        if (timelineRow2 == null) {
            Intrinsics.throwNpe();
        }
        timelineRow2.setVisibility(0);
        RelativeLayout relativeLayout = this.mContainer;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContainer");
        }
        relativeLayout.setVisibility(0);
        positionCurrentTimeIndicator();
        if (this.mOnLayoutListenerForShow == null) {
            this.mOnLayoutListenerForShow = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity.show.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public final void onGlobalLayout() {
                    GuideActivity.this.mOnLayoutListenerForShowFun();
                }
            };
        }
        RelativeLayout relativeLayout2 = this.mContainer;
        if (relativeLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContainer");
        }
        relativeLayout2.getViewTreeObserver().addOnGlobalLayoutListener(this.mOnLayoutListenerForShow);
        if (!this.fastDataDisplay) {
            ProgramGrid programGrid = this.mGrid;
            if (programGrid == null) {
                Intrinsics.throwNpe();
            }
            programGrid.setSelectedPosition(this.start_focus_position);
            ProgramGrid programGrid2 = this.mGrid;
            if (programGrid2 == null) {
                Intrinsics.throwNpe();
            }
            programGrid2.requestFocus();
        }
        Log.d(this.TAG, "loadDataFuture...");
        loadDataFuture();
        resetDisconnectTimer();
        if (this.isFirstDisplayOfInstance) {
            this.isFirstDisplayOfInstance = false;
            DSLog.INSTANCE.epgGridTimeStamp(this.TAG + '@' + hashCode(), true);
        }
        DSLog.INSTANCE.printCurrentRAMUsage((ActivityManager) getApplicationContext().getSystemService("activity"));
    }

    private final long floorTime(long timeMs, long timeUnit) {
        return timeMs - (timeMs % timeUnit);
    }

    public final void selectChannelByZappingNumber(int zapping) {
        ProgramManager programManager = this.mProgramManager;
        if (programManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        int channelPositionByZappingNumber = programManager.getChannelPositionByZappingNumber(zapping, true);
        this.start_focus_position = channelPositionByZappingNumber;
        if (channelPositionByZappingNumber >= 0) {
            this.mSelectedRow = (View) null;
            this.mIsDuringResetRowSelection = true;
            ProgramGrid programGrid = this.mGrid;
            if (programGrid == null) {
                Intrinsics.throwNpe();
            }
            programGrid.setSelectedPosition(this.start_focus_position);
            ProgramGrid programGrid2 = this.mGrid;
            if (programGrid2 == null) {
                Intrinsics.throwNpe();
            }
            programGrid2.onItemSelectionReset();
            this.mIsDuringResetRowSelection = false;
        }
    }

    public final int getTimelineRowScrollOffset() {
        TimelineRow timelineRow = this.mTimelineRow;
        if (timelineRow == null) {
            Intrinsics.throwNpe();
        }
        return timelineRow.getScrollOffset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onHorizontalScrolled(int dx) {
        Log.d("ProgramGuide", "onHorizontalScrolled(dx=" + dx + ')');
        positionCurrentTimeIndicator();
        ProgramGrid programGrid = this.mGrid;
        if (programGrid == null) {
            Intrinsics.throwNpe();
        }
        int childCount = programGrid.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ProgramGrid programGrid2 = this.mGrid;
            if (programGrid2 == null) {
                Intrinsics.throwNpe();
            }
            programGrid2.getChildAt(i).findViewById(R.id.row).scrollBy(dx, 0);
        }
    }

    private final void stopCurrentTimeIndicator() {
        this.mHandler.removeCallbacks(this.mUpdateTimeIndicator);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resetRowSelection() {
        this.mSelectedRow = (View) null;
        this.mIsDuringResetRowSelection = true;
        ProgramGrid programGrid = this.mGrid;
        if (programGrid == null) {
            Intrinsics.throwNpe();
        }
        programGrid.setSelectedPosition(this.start_focus_position);
        ProgramGrid programGrid2 = this.mGrid;
        if (programGrid2 == null) {
            Intrinsics.throwNpe();
        }
        programGrid2.resetFocusState();
        ProgramGrid programGrid3 = this.mGrid;
        if (programGrid3 == null) {
            Intrinsics.throwNpe();
        }
        programGrid3.onItemSelectionReset();
        this.mIsDuringResetRowSelection = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:15:0x001e  */
    /* JADX WARN: Code duplicated, block: B:17:0x0027  */
    /* JADX WARN: Code duplicated, block: B:20:0x0036  */
    public final void setInitialProgramFocus(View row) {
        View view;
        View viewFindViewById;
        if (row == null || row == (view = this.mSelectedRow)) {
            return;
        }
        if (view != null) {
            ProgramGrid programGrid = this.mGrid;
            if (programGrid == null) {
                Intrinsics.throwNpe();
            }
            View view2 = this.mSelectedRow;
            if (view2 == null) {
                Intrinsics.throwNpe();
            }
            if (programGrid.getChildAdapterPosition(view2) == -1) {
                viewFindViewById = row.findViewById(R.id.row);
                if (viewFindViewById != null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.guide.ProgramRow");
                }
                final ProgramRow programRow = (ProgramRow) viewFindViewById;
                programRow.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity.setInitialProgramFocus.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        programRow.focusCurrentProgram();
                    }
                });
            }
        } else {
            viewFindViewById = row.findViewById(R.id.row);
            if (viewFindViewById != null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.guide.ProgramRow");
            }
            final ProgramRow programRow2 = (ProgramRow) viewFindViewById;
            programRow2.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity.setInitialProgramFocus.1
                @Override // java.lang.Runnable
                public final void run() {
                    programRow2.focusCurrentProgram();
                }
            });
        }
        this.mSelectedRow = row;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:15:0x001e  */
    /* JADX WARN: Code duplicated, block: B:17:0x0027  */
    /* JADX WARN: Code duplicated, block: B:20:0x0036  */
    public final void setSelectedProgramFocus(View row) {
        View view;
        View viewFindViewById;
        if (row == null || row == (view = this.mSelectedRow)) {
            return;
        }
        if (view != null) {
            ProgramGrid programGrid = this.mGrid;
            if (programGrid == null) {
                Intrinsics.throwNpe();
            }
            View view2 = this.mSelectedRow;
            if (view2 == null) {
                Intrinsics.throwNpe();
            }
            if (programGrid.getChildAdapterPosition(view2) == -1) {
                viewFindViewById = row.findViewById(R.id.row);
                if (viewFindViewById != null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.guide.ProgramRow");
                }
                final ProgramRow programRow = (ProgramRow) viewFindViewById;
                programRow.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity.setSelectedProgramFocus.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        programRow.focusOnSelectedProgram();
                    }
                });
            }
        } else {
            viewFindViewById = row.findViewById(R.id.row);
            if (viewFindViewById != null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.guide.ProgramRow");
            }
            final ProgramRow programRow2 = (ProgramRow) viewFindViewById;
            programRow2.post(new Runnable() { // from class: com.dotscreen.portal.ui.screens.guide.GuideActivity.setSelectedProgramFocus.1
                @Override // java.lang.Runnable
                public final void run() {
                    programRow2.focusOnSelectedProgram();
                }
            });
        }
        this.mSelectedRow = row;
    }

    private final void positionCurrentTimeIndicator() {
        int iConvertMillisToPixel = GuideUtils.INSTANCE.convertMillisToPixel(this.mStartUtcTime, System.currentTimeMillis());
        TimelineRow timelineRow = this.mTimelineRow;
        if (timelineRow == null) {
            Intrinsics.throwNpe();
        }
        int scrollOffset = iConvertMillisToPixel - timelineRow.getScrollOffset();
        if (scrollOffset < 0) {
            View view = this.mCurrentTimeIndicator;
            if (view == null) {
                Intrinsics.throwNpe();
            }
            view.setVisibility(8);
            return;
        }
        if (this.mCurrentTimeIndicatorWidth == 0) {
            View view2 = this.mCurrentTimeIndicator;
            if (view2 == null) {
                Intrinsics.throwNpe();
            }
            view2.measure(0, 0);
            View view3 = this.mCurrentTimeIndicator;
            if (view3 == null) {
                Intrinsics.throwNpe();
            }
            this.mCurrentTimeIndicatorWidth = view3.getMeasuredWidth();
        }
        View view4 = this.mCurrentTimeIndicator;
        if (view4 == null) {
            Intrinsics.throwNpe();
        }
        view4.setPaddingRelative(scrollOffset - (this.mCurrentTimeIndicatorWidth / 2), 0, 0, 0);
        View view5 = this.mCurrentTimeIndicator;
        if (view5 == null) {
            Intrinsics.throwNpe();
        }
        view5.setVisibility(0);
    }

    public final long ceilTime(long timeMs, long timeUnit) {
        return (timeMs + timeUnit) - (timeMs % timeUnit);
    }

    public final void reloadFullGuide() {
        ProgramManager programManager = this.mProgramManager;
        if (programManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mProgramManager");
        }
        programManager.setSelectedProgram(getSelectedEntry());
        if (this.fastDataDisplay) {
            this.fastDataDisplay = false;
            loadData();
        }
    }
}
