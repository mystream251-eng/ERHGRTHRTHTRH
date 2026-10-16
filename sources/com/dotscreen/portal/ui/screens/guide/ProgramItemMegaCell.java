package com.dotscreen.portal.ui.screens.guide;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Outline;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.media.tv.TvContract;
import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.Channel;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.ui.ImageLoaderManager;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.media.tv.companionlibrary.model.InternalProviderData;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ProgramItemMegaCell.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\u0018\u00002\u00020\u0001B'\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u00105\u001a\u000206J\u0018\u00107\u001a\u00020\u00072\u0006\u00108\u001a\u00020\n2\u0006\u00109\u001a\u00020\nH\u0002J\u0010\u0010:\u001a\u00020\u00072\u0006\u0010;\u001a\u00020<H\u0002J\u001a\u0010=\u001a\u0004\u0018\u00010>2\u0006\u0010;\u001a\u00020<2\u0006\u0010?\u001a\u00020\u0007H\u0002J\b\u0010@\u001a\u0004\u0018\u00010\u001eJ\b\u0010A\u001a\u000206H\u0002J\u000e\u0010B\u001a\u0002062\u0006\u0010C\u001a\u00020\u0007J\u0012\u0010D\u001a\u0004\u0018\u00010\u00112\u0006\u0010E\u001a\u00020\u0007H\u0014J\b\u0010F\u001a\u000206H\u0014J\b\u0010G\u001a\u000206H\u0014J\b\u0010H\u001a\u000206H\u0016J\b\u0010I\u001a\u000206H\u0002J\b\u0010J\u001a\u000206H\u0002J\u0012\u0010K\u001a\u0002062\b\u0010L\u001a\u0004\u0018\u00010\u0014H\u0002J\"\u0010M\u001a\u0002062\b\u0010N\u001a\u0004\u0018\u00010>2\u0006\u0010O\u001a\u00020\u00072\u0006\u0010P\u001a\u00020\u0007H\u0002J(\u0010Q\u001a\u0002062\u0006\u0010R\u001a\u00020\u001e2\u0006\u0010S\u001a\u00020\n2\u0006\u0010T\u001a\u00020\n2\b\u0010U\u001a\u0004\u0018\u00010\u0014J\b\u0010V\u001a\u000206H\u0002J\b\u0010W\u001a\u000206H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070'X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u000e\u0010,\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006X"}, d2 = {"Lcom/dotscreen/portal/ui/screens/guide/ProgramItemMegaCell;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "FOCUS_UPDATE_FREQUENCY", "", "MAX_PROGRESS", "ON_CLICKED", "Landroid/view/View$OnClickListener;", "ON_FOCUS_CHANGED", "Landroid/view/View$OnFocusChangeListener;", "STATE_CURRENT_PROGRAM", "", "STATE_TOO_WIDE", "TAG", "", "_handler", "Landroid/os/Handler;", "image_episode", "Landroid/widget/ImageView;", "mAiringImgUrl", "mMaxWidthForRipple", "mPreventParentRelayout", "", "mTableEntry", "Lcom/dotscreen/portal/ui/screens/guide/TableEntry;", "mTextWidth", "mUpdateFocus", "Ljava/lang/Runnable;", "getMUpdateFocus", "()Ljava/lang/Runnable;", "main_layout", "Landroid/widget/LinearLayout;", "prevNextZapping", "Lkotlin/Pair;", "getPrevNextZapping", "()Lkotlin/Pair;", "setPrevNextZapping", "(Lkotlin/Pair;)V", "sItemPadding", "sVisibleImageThreshold", "sVisibleThreshold", "sVisibleTitleThreshold", "textViewInteractive", "Landroid/widget/TextView;", "textViewTime", "textViewTitle", "textViewType", "clearValues", "", "getProgress", "start", TtmlNode.END, "getStateCount", "stateListDrawable", "Landroid/graphics/drawable/StateListDrawable;", "getStateDrawable", "Landroid/graphics/drawable/Drawable;", "index", "getTableEntry", "initIfNeeded", "layoutVisibleArea", "offset", "onCreateDrawableState", "extraSpace", "onDetachedFromWindow", "onFinishInflate", "requestLayout", "restartRunnable", "runUpdateFocus", "setAiringImage", ImagesContract.URL, "setProgress", "drawable", "id", "progress", "setValues", "entry", "fromUtcMillis", "toUtcMillis", "gapTitle", "startAmazonPrime", "startNetflix", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ProgramItemMegaCell extends FrameLayout {
    private final long FOCUS_UPDATE_FREQUENCY;
    private final int MAX_PROGRESS;
    private final View.OnClickListener ON_CLICKED;
    private final View.OnFocusChangeListener ON_FOCUS_CHANGED;
    private final int[] STATE_CURRENT_PROGRAM;
    private final int[] STATE_TOO_WIDE;
    private final String TAG;
    private HashMap _$_findViewCache;
    private Handler _handler;
    private final ImageView image_episode;
    private String mAiringImgUrl;
    private int mMaxWidthForRipple;
    private boolean mPreventParentRelayout;
    private TableEntry mTableEntry;
    private int mTextWidth;
    private final Runnable mUpdateFocus;
    private final LinearLayout main_layout;
    private Pair<Integer, Integer> prevNextZapping;
    private int sItemPadding;
    private int sVisibleImageThreshold;
    private int sVisibleThreshold;
    private int sVisibleTitleThreshold;
    private final TextView textViewInteractive;
    private final TextView textViewTime;
    private final TextView textViewTitle;
    private final TextView textViewType;

    public ProgramItemMegaCell(Context context) {
        this(context, null, 0, 6, null);
    }

    public ProgramItemMegaCell(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
    }

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

    public ProgramItemMegaCell(final Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = "ProgramItemMegaCell";
        this.FOCUS_UPDATE_FREQUENCY = TimeUnit.SECONDS.toMillis(1L);
        this.MAX_PROGRESS = 10000;
        this.STATE_CURRENT_PROGRAM = new int[]{R.attr.state_current_program};
        this.STATE_TOO_WIDE = new int[]{R.attr.state_program_too_wide};
        this.ON_CLICKED = new View.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramItemMegaCell$ON_CLICKED$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IOException {
                String channelId;
                TVAiring program;
                TVAiring program2;
                Date startDate;
                RightVUser user;
                TVAiring program3;
                TVAiring program4;
                if (this.this$0.mTableEntry == null) {
                    return;
                }
                DSLog.INSTANCE.a("Clicked on an itemMegaCell in Guide TV");
                Context context2 = context;
                lValueOf = null;
                lValueOf = null;
                Long lValueOf = null;
                Context applicationContext = context2 != null ? context2.getApplicationContext() : null;
                if (applicationContext != null) {
                    PortalApplication portalApplication = (PortalApplication) applicationContext;
                    TableEntry tableEntry = this.this$0.mTableEntry;
                    if (((tableEntry == null || (program4 = tableEntry.getProgram()) == null) ? null : program4.getId()) == null || portalApplication.getDegradedMode()) {
                        TableEntry tableEntry2 = this.this$0.mTableEntry;
                        if (CommonUtils.isNetflixChannel(tableEntry2 != null ? tableEntry2.getChannel() : null)) {
                            this.this$0.startNetflix();
                            return;
                        }
                        TableEntry tableEntry3 = this.this$0.mTableEntry;
                        if (CommonUtils.isAmazonPrimeChannel(tableEntry3 != null ? tableEntry3.getChannel() : null)) {
                            this.this$0.startAmazonPrime();
                            return;
                        }
                        TableEntry tableEntry4 = this.this$0.mTableEntry;
                        if (((tableEntry4 == null || (program = tableEntry4.getProgram()) == null) ? null : program.getId()) == null && !portalApplication.getDegradedMode()) {
                            TableEntry tableEntry5 = this.this$0.mTableEntry;
                            if ((tableEntry5 != null ? tableEntry5.getChannelId() : null) != null) {
                                PortalApplication portalApplication2 = portalApplication;
                                TableEntry tableEntry6 = this.this$0.mTableEntry;
                                Intent intent = new Intent("android.intent.action.VIEW", CommonUtils.getLiveTvDeeplink((Context) portalApplication2, tableEntry6 != null ? tableEntry6.getChannel() : null, (Boolean) true));
                                Context context3 = context;
                                if (context3 != null) {
                                    ((GuideActivity) context3).getMProgramManager().setSelectedProgram(this.this$0.mTableEntry);
                                    context.startActivity(intent);
                                    return;
                                }
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.guide.GuideActivity");
                            }
                        }
                        if (portalApplication.getDegradedMode()) {
                            TableEntry tableEntry7 = this.this$0.mTableEntry;
                            Intent intent2 = new Intent("android.intent.action.VIEW", TvContract.buildChannelUri((tableEntry7 == null || (channelId = tableEntry7.getChannelId()) == null) ? 0L : Long.parseLong(channelId)));
                            Context context4 = context;
                            if (context4 != null) {
                                ((GuideActivity) context4).getMProgramManager().setSelectedProgram(this.this$0.mTableEntry);
                                context.startActivity(intent2);
                                return;
                            }
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.guide.GuideActivity");
                        }
                        return;
                    }
                    TableEntry tableEntry8 = this.this$0.mTableEntry;
                    Channel channel = (tableEntry8 == null || (program3 = tableEntry8.getProgram()) == null) ? null : program3.getChannel();
                    com.google.android.media.tv.companionlibrary.model.Channel channel2 = (com.google.android.media.tv.companionlibrary.model.Channel) null;
                    if ((channel != null ? channel.getId() : null) != null) {
                        Cursor cursorQuery = context.getContentResolver().query(TvContract.Channels.CONTENT_URI, com.google.android.media.tv.companionlibrary.model.Channel.PROJECTION, "_id=" + channel.getId(), null, null);
                        if (cursorQuery != null) {
                            Cursor cursor = cursorQuery;
                            Throwable th = (Throwable) null;
                            try {
                                Cursor cursor2 = cursor;
                                while (cursor2.moveToNext()) {
                                    com.google.android.media.tv.companionlibrary.model.Channel fromCursor = com.google.android.media.tv.companionlibrary.model.Channel.fromCursor(cursor2);
                                    Intrinsics.checkExpressionValueIsNotNull(fromCursor, "fromCursor");
                                    InternalProviderData internalProviderData = fromCursor.getInternalProviderData();
                                    if (channel.getId().equals((String) (internalProviderData != null ? internalProviderData.get(CommonUtils.KEY_OPTIMIZER_CHANNEL_ID) : null))) {
                                        channel2 = fromCursor;
                                        break;
                                    }
                                }
                                Unit unit = Unit.INSTANCE;
                                CloseableKt.closeFinally(cursor, th);
                            } catch (Throwable th2) {
                                try {
                                    throw th2;
                                } catch (Throwable th3) {
                                    CloseableKt.closeFinally(cursor, th2);
                                    throw th3;
                                }
                            }
                        }
                        if (channel2 != null) {
                            Log.d(this.this$0.TAG, "setLastWatchedChannel");
                            PortalApplication.Config value = portalApplication.getConfig().getValue();
                            String id = (value == null || (user = value.getUser()) == null) ? null : user.getId();
                            Context context5 = context;
                            if (channel2 == null) {
                                Intrinsics.throwNpe();
                            }
                            long id2 = channel2.getId();
                            if (channel2 == null) {
                                Intrinsics.throwNpe();
                            }
                            CommonUtils.setLastWatchedChannel(context5, id2, channel2.getInputId(), false, id);
                        }
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append("megacable://com.dotscreen.megacable.portal/epg/details?channel_id=");
                    sb.append(channel != null ? channel.getId() : null);
                    sb.append("&start=");
                    TableEntry tableEntry9 = this.this$0.mTableEntry;
                    if (tableEntry9 != null && (program2 = tableEntry9.getProgram()) != null && (startDate = program2.getStartDate()) != null) {
                        lValueOf = Long.valueOf(startDate.getTime());
                    }
                    sb.append(lValueOf);
                    Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(sb.toString()));
                    Context context6 = context;
                    if (context6 != null) {
                        ((GuideActivity) context6).getMProgramManager().setSelectedProgram(this.this$0.mTableEntry);
                        context.startActivity(intent3);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.guide.GuideActivity");
                }
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
        };
        this.prevNextZapping = new Pair<>(-1, -1);
        this.ON_FOCUS_CHANGED = new View.OnFocusChangeListener() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramItemMegaCell$ON_FOCUS_CHANGED$1
            /* JADX WARN: Code duplicated, block: B:14:0x0029  */
            /* JADX WARN: Code duplicated, block: B:16:0x0031  */
            /* JADX WARN: Code duplicated, block: B:17:0x0033  */
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                int i2;
                TVAiring program;
                TableEntry tableEntry = this.this$0.mTableEntry;
                Date startDate = null;
                if (CommonUtils.isNetflixChannel(tableEntry != null ? tableEntry.getChannel() : null)) {
                    TextView textView = this.this$0.textViewInteractive;
                    if (z) {
                        i2 = 0;
                    } else {
                        i2 = 4;
                    }
                    textView.setVisibility(i2);
                } else {
                    TableEntry tableEntry2 = this.this$0.mTableEntry;
                    if (CommonUtils.isAmazonPrimeChannel(tableEntry2 != null ? tableEntry2.getChannel() : null)) {
                        TextView textView2 = this.this$0.textViewInteractive;
                        if (z) {
                            i2 = 0;
                        } else {
                            i2 = 4;
                        }
                        textView2.setVisibility(i2);
                    }
                }
                if (z) {
                    Intrinsics.checkExpressionValueIsNotNull(view, "view");
                    if (view.getContext() instanceof GuideActivity) {
                        TableEntry tableEntry3 = this.this$0.mTableEntry;
                        if (tableEntry3 != null && (program = tableEntry3.getProgram()) != null) {
                            startDate = program.getStartDate();
                        }
                        if (startDate != null) {
                            Context context2 = view.getContext();
                            if (context2 == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.guide.GuideActivity");
                            }
                            GuideActivity guideActivity = (GuideActivity) context2;
                            TableEntry tableEntry4 = this.this$0.mTableEntry;
                            if (tableEntry4 == null) {
                                Intrinsics.throwNpe();
                            }
                            TVAiring program2 = tableEntry4.getProgram();
                            if (program2 == null) {
                                Intrinsics.throwNpe();
                            }
                            guideActivity.setCurrentDate(program2.getStartDate());
                        }
                    }
                }
            }
        };
        this._handler = new Handler();
        this.mUpdateFocus = new Runnable() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramItemMegaCell$mUpdateFocus$1
            @Override // java.lang.Runnable
            public final void run() {
                this.this$0.runUpdateFocus();
            }
        };
        setOnClickListener(this.ON_CLICKED);
        setOnFocusChangeListener(this.ON_FOCUS_CHANGED);
        FrameLayout.inflate(context, R.layout.view_epg_item_cell, this);
        View viewFindViewById = findViewById(R.id.image_episode);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.image_episode)");
        ImageView imageView = (ImageView) viewFindViewById;
        this.image_episode = imageView;
        final float f = 20.0f;
        imageView.setOutlineProvider(new ViewOutlineProvider() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramItemMegaCell.1
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
        View viewFindViewById2 = findViewById(R.id.main_layout);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.main_layout)");
        this.main_layout = (LinearLayout) viewFindViewById2;
        this.image_episode.setClipToOutline(true);
        View viewFindViewById3 = findViewById(R.id.textViewTitle);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "findViewById(R.id.textViewTitle)");
        this.textViewTitle = (TextView) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.textViewType);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "findViewById(R.id.textViewType)");
        this.textViewType = (TextView) viewFindViewById4;
        View viewFindViewById5 = findViewById(R.id.textViewTime);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "findViewById(R.id.textViewTime)");
        this.textViewTime = (TextView) viewFindViewById5;
        View viewFindViewById6 = findViewById(R.id.textViewInteractive);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "findViewById(R.id.textViewInteractive)");
        this.textViewInteractive = (TextView) viewFindViewById6;
    }

    public /* synthetic */ ProgramItemMegaCell(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    public final Pair<Integer, Integer> getPrevNextZapping() {
        return this.prevNextZapping;
    }

    public final void setPrevNextZapping(Pair<Integer, Integer> pair) {
        Intrinsics.checkParameterIsNotNull(pair, "<set-?>");
        this.prevNextZapping = pair;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startNetflix() {
        String str;
        Channel channel;
        DSLog.INSTANCE.a("Start Netflix");
        StringBuilder sb = new StringBuilder();
        sb.append("chID=");
        TableEntry tableEntry = this.mTableEntry;
        sb.append((tableEntry == null || (channel = tableEntry.getChannel()) == null) ? null : Integer.valueOf(channel.getZappingNumber()));
        String string = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(string);
        String str2 = "";
        if (this.prevNextZapping.getFirst().intValue() != -1) {
            str = "&prevChID=" + this.prevNextZapping.getFirst().intValue();
        } else {
            str = "";
        }
        sb2.append(str);
        String string2 = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(string2);
        if (this.prevNextZapping.getSecond().intValue() != -1) {
            str2 = "&nextChID=" + this.prevNextZapping.getSecond().intValue();
        }
        sb3.append(str2);
        try {
            getContext().startActivity(CommonUtils.patchInteractiveIntent(new Intent("android.intent.action.VIEW", Uri.parse("http://www.netflix.com/browse?iid=6a70ad21&source_type_payload=" + URLEncoder.encode(sb3.toString() + "&category=OTHER")))));
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startAmazonPrime() {
        DSLog.INSTANCE.a("Start Amazon Prime");
        try {
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            Intent leanbackLaunchIntentForPackage = context.getPackageManager().getLeanbackLaunchIntentForPackage("com.amazon.amazonvideo.livingroom");
            if (leanbackLaunchIntentForPackage != null) {
                getContext().startActivity(leanbackLaunchIntentForPackage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final Runnable getMUpdateFocus() {
        return this.mUpdateFocus;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void runUpdateFocus() {
        Drawable drawable;
        refreshDrawableState();
        TableEntry tableEntry = this.mTableEntry;
        if (tableEntry != null) {
            LinearLayout linearLayout = this.main_layout;
            if (tableEntry.isCurrentProgram()) {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.epg_current_program_unfocused);
            } else {
                drawable = ContextCompat.getDrawable(getContext(), R.drawable.epg_program_unfocused);
            }
            linearLayout.setBackground(drawable);
            restartRunnable();
        }
    }

    private final void restartRunnable() {
        Handler handler = this._handler;
        if (handler != null) {
            handler.postAtTime(this.mUpdateFocus, GuideUtils.INSTANCE.ceilTime(SystemClock.uptimeMillis(), this.FOCUS_UPDATE_FREQUENCY));
        }
    }

    private final void initIfNeeded() {
        if (this.sVisibleThreshold != 0) {
            return;
        }
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Resources resources = context.getResources();
        restartRunnable();
        this.sVisibleThreshold = resources.getDimensionPixelOffset(R.dimen.program_guide_table_item_visible_threshold);
        this.sVisibleImageThreshold = resources.getDimensionPixelOffset(R.dimen.program_guide_table_preview_item_visible_threshold);
        this.sVisibleTitleThreshold = resources.getDimensionPixelOffset(R.dimen.program_guide_table_title_item_visible_threshold);
        this.sItemPadding = resources.getDimensionPixelOffset(R.dimen.program_guide_table_item_padding);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        initIfNeeded();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int extraSpace) {
        TVAiring program;
        if (this.mTableEntry != null) {
            int[] iArrOnCreateDrawableState = super.onCreateDrawableState(extraSpace + this.STATE_CURRENT_PROGRAM.length + this.STATE_TOO_WIDE.length);
            TableEntry tableEntry = this.mTableEntry;
            if (tableEntry != null && (program = tableEntry.getProgram()) != null && program.isLive()) {
                View.mergeDrawableStates(iArrOnCreateDrawableState, this.STATE_CURRENT_PROGRAM);
            }
            TableEntry tableEntry2 = this.mTableEntry;
            if (tableEntry2 == null) {
                Intrinsics.throwNpe();
            }
            if (tableEntry2.getWidth() > this.mMaxWidthForRipple) {
                View.mergeDrawableStates(iArrOnCreateDrawableState, this.STATE_TOO_WIDE);
            }
            return iArrOnCreateDrawableState;
        }
        return super.onCreateDrawableState(extraSpace);
    }

    /* JADX INFO: renamed from: getTableEntry, reason: from getter */
    public final TableEntry getMTableEntry() {
        return this.mTableEntry;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0102  */
    /* JADX WARN: Code duplicated, block: B:61:0x01b0  */
    public final void setValues(TableEntry entry, long fromUtcMillis, long toUtcMillis, String gapTitle) {
        Drawable drawable;
        String title;
        String string;
        URL urlUrlWithSize$default;
        String str;
        String title2;
        String string2;
        Intrinsics.checkParameterIsNotNull(entry, "entry");
        this.textViewTitle.setPaddingRelative(0, 0, 0, 0);
        this.textViewType.setPaddingRelative(0, 0, 0, 0);
        this.textViewTime.setPaddingRelative(0, 0, 0, 0);
        this.textViewInteractive.setPaddingRelative(0, 0, 0, 0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH':'mm");
        LinearLayout linearLayout = this.main_layout;
        if (entry.isCurrentProgram()) {
            drawable = ContextCompat.getDrawable(getContext(), R.drawable.epg_current_program_unfocused);
        } else {
            drawable = ContextCompat.getDrawable(getContext(), R.drawable.epg_program_unfocused);
        }
        linearLayout.setBackground(drawable);
        setForeground(ContextCompat.getDrawable(getContext(), R.drawable.epg_foreground_program_selector));
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = entry.getWidth();
        setLayoutParams(layoutParams);
        TVAiring program = entry.getProgram();
        String string3 = "";
        if (program == null || (title = program.getTitle()) == null) {
            title = "";
        }
        this.textViewTime.setText(simpleDateFormat.format(Long.valueOf(entry.getEntryStartUtcMillis())) + " - " + simpleDateFormat.format(Long.valueOf(entry.getEntryEndUtcMillis())));
        if (entry.getWidth() < this.sVisibleTitleThreshold || CommonUtils.isNetflixChannel(entry.getChannel()) || CommonUtils.isAmazonPrimeChannel(entry.getChannel())) {
            this.image_episode.setVisibility(8);
            this.textViewTime.setText("");
            this.textViewType.setText("");
            this.textViewTitle.setText("");
            this.textViewTime.setVisibility(8);
        } else if (entry.get_isGap() || entry.getProgram() == null) {
            this.image_episode.setVisibility(8);
            this.textViewTitle.setText("");
            this.textViewTime.setText("");
            this.textViewType.setVisibility(0);
            this.textViewType.setText(getResources().getString(R.string.program_title_for_no_information));
            this.textViewTime.setVisibility(4);
        } else {
            if (entry.getWidth() < this.sVisibleImageThreshold) {
                this.image_episode.setVisibility(8);
                TextView textView = this.textViewTitle;
                if (title == null) {
                    string2 = getResources().getString(R.string.program_title_no_title);
                } else {
                    if (title == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    string2 = title.toUpperCase();
                    Intrinsics.checkNotNullExpressionValue(string2, "(this as java.lang.String).toUpperCase()");
                    if (string2 == null) {
                        string2 = getResources().getString(R.string.program_title_no_title);
                    }
                }
                textView.setText(string2);
                MediaMetadata metadata = entry.getProgram().getMetadata();
                if ((metadata != null ? metadata.getGenres() : null) != null) {
                    new StringBuilder().append(ArraysKt.joinToString$default(entry.getProgram().getMetadata().getGenres(), " | ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
                }
            } else {
                Artwork stillArtwork = entry.getProgram().getStillArtwork();
                if (((stillArtwork == null || (urlUrlWithSize$default = Artwork.urlWithSize$default(stillArtwork, new Point(269, 162), 0.0d, 2, null)) == null) ? null : urlUrlWithSize$default.toString()) != null) {
                    Artwork stillArtwork2 = entry.getProgram().getStillArtwork();
                    setAiringImage(String.valueOf(stillArtwork2 != null ? Artwork.urlWithSize$default(stillArtwork2, new Point(269, 162), 0.0d, 2, null) : null));
                }
                TextView textView2 = this.textViewTitle;
                if (title == null) {
                    string = getResources().getString(R.string.program_title_no_title);
                } else {
                    if (title == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    string = title.toUpperCase();
                    Intrinsics.checkNotNullExpressionValue(string, "(this as java.lang.String).toUpperCase()");
                    if (string == null) {
                        string = getResources().getString(R.string.program_title_no_title);
                    }
                }
                textView2.setText(string);
                MediaMetadata metadata2 = entry.getProgram().getMetadata();
                if ((metadata2 != null ? metadata2.getGenres() : null) != null) {
                    new StringBuilder().append(ArraysKt.joinToString$default(entry.getProgram().getMetadata().getGenres(), " | ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
                }
            }
            MediaMetadata metadata3 = entry.getProgram().getMetadata();
            if (metadata3 != null) {
                if (!(metadata3.getGenres().length == 0)) {
                    str = "";
                    for (String str2 : metadata3.getGenres()) {
                        str = str + str2;
                        if (!Intrinsics.areEqual((String) ArraysKt.last(metadata3.getGenres()), str2)) {
                            str = str + ", ";
                        }
                    }
                } else {
                    str = "";
                }
                Integer durationInMinutes = metadata3.getDurationInMinutes();
                if ((durationInMinutes != null ? durationInMinutes.intValue() : 0) > 0) {
                    if ((!Intrinsics.areEqual(str, "")) && !StringsKt.endsWith$default(str, "| ", false, 2, (Object) null)) {
                        str = str + " | ";
                    }
                    str = str + String.valueOf(metadata3.getDurationInMinutes()) + "min";
                }
                ParentalRating parentalLevel = metadata3.getParentalLevel();
                if (parentalLevel == null || (title2 = parentalLevel.getTitle()) == null) {
                    title2 = "NR";
                }
                if (!Intrinsics.areEqual("NR", title2)) {
                    if ((!Intrinsics.areEqual(str, "")) && !StringsKt.endsWith$default(str, "| ", false, 2, (Object) null)) {
                        str = str + " | ";
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    ParentalRating parentalLevel2 = metadata3.getParentalLevel();
                    sb.append(parentalLevel2 != null ? parentalLevel2.getTitle() : null);
                    string3 = sb.toString();
                } else {
                    string3 = str;
                }
            }
            this.textViewTime.setVisibility(0);
            this.textViewType.setText(string3);
        }
        measure(0, 0);
        this.mTextWidth = (getMeasuredWidth() - getPaddingStart()) - getPaddingEnd();
        int iConvertMillisToPixel = GuideUtils.INSTANCE.convertMillisToPixel(entry.getEntryStartUtcMillis());
        int iConvertMillisToPixel2 = GuideUtils.INSTANCE.convertMillisToPixel(fromUtcMillis);
        this.mTableEntry = entry;
        layoutVisibleArea(iConvertMillisToPixel2 - iConvertMillisToPixel);
        this.mMaxWidthForRipple = GuideUtils.INSTANCE.convertMillisToPixel(fromUtcMillis, toUtcMillis);
    }

    private final void setAiringImage(String url) {
        if (url == null || (!Intrinsics.areEqual(url, this.mAiringImgUrl))) {
            this.mAiringImgUrl = url;
            this.image_episode.setImageBitmap(null);
            if (url != null) {
                this.image_episode.setVisibility(0);
                Context context = getContext();
                Intrinsics.checkExpressionValueIsNotNull(context, "context");
                new ImageLoaderManager(context).loadImage(url, (1016 & 2) != 0 ? (ImageView) null : this.image_episode, (1016 & 4) != 0 ? false : true, (1016 & 8) != 0 ? 300 : 0, (1016 & 16) != 0 ? 0 : 0, (1016 & 32) != 0 ? 0 : 0, (1016 & 64) != 0 ? (ImageView.ScaleType) null : null, (1016 & 128) != 0 ? 0 : 0, (1016 & 256) == 0 ? 0 : 0, (1016 & 512) != 0 ? (RequestListener) null : new RequestListener<Drawable>() { // from class: com.dotscreen.portal.ui.screens.guide.ProgramItemMegaCell.setAiringImage.1
                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }

                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        ProgramItemMegaCell.this.image_episode.setVisibility(8);
                        return false;
                    }
                });
                return;
            }
            this.image_episode.setVisibility(8);
        }
    }

    public final void layoutVisibleArea(int offset) {
        int width = getWidth();
        int iMax = Math.max(0, offset - 240);
        int iMin = Math.min(width, this.mTextWidth + (this.sItemPadding * 2));
        if (iMax > 0 && width - iMax < iMin) {
            iMax = Math.max(0, width - iMin);
        }
        if (this.sItemPadding + iMax != getPaddingStart()) {
            TableEntry tableEntry = this.mTableEntry;
            if (tableEntry == null) {
                Intrinsics.throwNpe();
            }
            if (tableEntry.getWidth() > this.sVisibleImageThreshold) {
                this.mPreventParentRelayout = true;
                this.textViewTitle.setPaddingRelative(this.sItemPadding + iMax, 0, 0, 0);
                this.textViewType.setPaddingRelative(this.sItemPadding + iMax, 0, 0, 0);
                this.textViewTime.setPaddingRelative(this.sItemPadding + iMax, 0, 0, 0);
                this.textViewInteractive.setPaddingRelative(iMax + this.sItemPadding, 0, 0, 0);
                this.mPreventParentRelayout = false;
                return;
            }
        }
        this.mPreventParentRelayout = true;
        this.textViewTitle.setPaddingRelative(0, 0, 0, 0);
        this.textViewType.setPaddingRelative(0, 0, 0, 0);
        this.textViewTime.setPaddingRelative(0, 0, 0, 0);
        this.textViewInteractive.setPaddingRelative(0, 0, 0, 0);
        this.mPreventParentRelayout = false;
    }

    public final void clearValues() {
        Handler handler = this._handler;
        if (handler != null) {
            handler.removeCallbacks(this.mUpdateFocus);
        }
        this.image_episode.setImageBitmap(null);
        this.textViewType.setText("");
        this.textViewTime.setText("");
        this.textViewTitle.setText("");
        setTag(null);
        this.mAiringImgUrl = (String) null;
        this.mTableEntry = (TableEntry) null;
    }

    private final int getProgress(long start, long end) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis <= start) {
            return 0;
        }
        if (jCurrentTimeMillis >= end) {
            return this.MAX_PROGRESS;
        }
        return (int) (((jCurrentTimeMillis - start) * ((long) this.MAX_PROGRESS)) / (end - start));
    }

    private final void setProgress(Drawable drawable, int id, int progress) {
        int i = 0;
        if (drawable instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) drawable;
            int stateCount = getStateCount(stateListDrawable);
            while (i < stateCount) {
                setProgress(getStateDrawable(stateListDrawable, i), id, progress);
                i++;
            }
            return;
        }
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            while (i < numberOfLayers) {
                setProgress(layerDrawable.getDrawable(i), id, progress);
                if (layerDrawable.getId(i) == id) {
                    Drawable drawable2 = layerDrawable.getDrawable(i);
                    Intrinsics.checkExpressionValueIsNotNull(drawable2, "layerDrawable.getDrawable(i)");
                    drawable2.setLevel(progress);
                }
                i++;
            }
        }
    }

    private final int getStateCount(StateListDrawable stateListDrawable) {
        try {
            Object objInvoke = StateListDrawable.class.getDeclaredMethod("getStateCount", new Class[0]).invoke(stateListDrawable, new Object[0]);
            if (objInvoke != null) {
                return ((Integer) objInvoke).intValue();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Int");
        } catch (IllegalAccessException e) {
            Log.e(this.TAG, "Failed to call StateListDrawable.getStateCount()", e);
            return 0;
        } catch (IllegalArgumentException e2) {
            Log.e(this.TAG, "Failed to call StateListDrawable.getStateCount()", e2);
            return 0;
        } catch (NoSuchMethodException e3) {
            Log.e(this.TAG, "Failed to call StateListDrawable.getStateCount()", e3);
            return 0;
        } catch (InvocationTargetException e4) {
            Log.e(this.TAG, "Failed to call StateListDrawable.getStateCount()", e4);
            return 0;
        }
    }

    private final Drawable getStateDrawable(StateListDrawable stateListDrawable, int index) {
        try {
            Object objInvoke = StateListDrawable.class.getDeclaredMethod("getStateDrawable", Integer.TYPE).invoke(stateListDrawable, Integer.valueOf(index));
            if (objInvoke != null) {
                return (Drawable) objInvoke;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.Drawable");
        } catch (IllegalAccessException e) {
            Log.e(this.TAG, "Failed to call StateListDrawable.getStateDrawable(" + index + ')', e);
            return null;
        } catch (IllegalArgumentException e2) {
            Log.e(this.TAG, "Failed to call StateListDrawable.getStateDrawable(" + index + ')', e2);
            return null;
        } catch (NoSuchMethodException e3) {
            Log.e(this.TAG, "Failed to call StateListDrawable.getStateDrawable(" + index + ')', e3);
            return null;
        } catch (InvocationTargetException e4) {
            Log.e(this.TAG, "Failed to call StateListDrawable.getStateDrawable(" + index + ')', e4);
            return null;
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mPreventParentRelayout) {
            forceLayout();
        } else {
            super.requestLayout();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        clearValues();
        super.onDetachedFromWindow();
    }
}
