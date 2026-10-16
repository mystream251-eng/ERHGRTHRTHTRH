package com.dotscreen.portal.ui.screens.details;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.LiveData;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.Media;
import com.dotscreen.bokit.model.MediaMetadata;
import com.dotscreen.bokit.model.Movie;
import com.dotscreen.bokit.model.Offer;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.Person;
import com.dotscreen.bokit.model.PlaybackRight;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.CatchupDetailsViewModel;
import com.dotscreen.portal.model.VODDetailsViewModel;
import com.dotscreen.tv.ui.ImageLoaderManager;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ProgramDetailsView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\u0010\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eJ\u000e\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020!R\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\""}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/ProgramDetailsView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "TAG", "", "kotlin.jvm.PlatformType", "value", "", "detailsContent", "getDetailsContent", "()Ljava/lang/Object;", "setDetailsContent", "(Ljava/lang/Object;)V", "rootView", "Landroid/view/ViewGroup;", "getRootView", "()Landroid/view/ViewGroup;", "updateAiringInfo", "", "airing", "Lcom/dotscreen/bokit/model/TVAiring;", "updateRentInfo", TtmlNode.RIGHT, "Lcom/dotscreen/bokit/model/PlaybackRight;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ProgramDetailsView extends LinearLayout {
    private final String TAG;
    private HashMap _$_findViewCache;
    private Object detailsContent;
    private final ViewGroup rootView;

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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProgramDetailsView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.TAG = ProgramDetailsView.class.getSimpleName();
        View viewInflate = View.inflate(getContext(), R.layout.view_program_details, this);
        if (viewInflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        this.rootView = (ViewGroup) viewInflate;
        setOrientation(1);
        int i3 = Build.VERSION.SDK_INT;
    }

    public /* synthetic */ ProgramDetailsView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? (AttributeSet) null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ProgramDetailsView(Context context) {
        this(context, null, 0, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ProgramDetailsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ProgramDetailsView(Context context, AttributeSet attrs, int i) {
        this(context, attrs, i, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    @Override // android.view.View
    public final ViewGroup getRootView() {
        return this.rootView;
    }

    public final Object getDetailsContent() {
        return this.detailsContent;
    }

    /* JADX WARN: Code duplicated, block: B:120:0x0310  */
    /* JADX WARN: Code duplicated, block: B:184:0x047d  */
    /* JADX WARN: Code duplicated, block: B:186:0x0486  */
    /* JADX WARN: Code duplicated, block: B:187:0x0488  */
    /* JADX WARN: Code duplicated, block: B:189:0x048c  */
    /* JADX WARN: Code duplicated, block: B:192:0x049d  */
    /* JADX WARN: Code duplicated, block: B:194:0x04a3  */
    /* JADX WARN: Code duplicated, block: B:196:0x04ab  */
    /* JADX WARN: Code duplicated, block: B:237:0x05c8  */
    /* JADX WARN: Code duplicated, block: B:23:0x0078  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v39 */
    /* JADX WARN: Type inference failed for: r1v40 */
    /* JADX WARN: Type inference failed for: r1v45 */
    /* JADX WARN: Type inference failed for: r2v23 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v43 */
    /* JADX WARN: Type inference failed for: r2v44 */
    /* JADX WARN: Type inference failed for: r2v49 */
    /* JADX WARN: Type inference failed for: r2v80 */
    /* JADX WARN: Type inference failed for: r2v81 */
    /* JADX WARN: Type inference failed for: r2v86 */
    /* JADX WARN: Type inference failed for: r4v105 */
    /* JADX WARN: Type inference failed for: r4v106 */
    /* JADX WARN: Type inference failed for: r4v110 */
    /* JADX WARN: Type inference failed for: r4v128 */
    /* JADX WARN: Type inference failed for: r4v129 */
    /* JADX WARN: Type inference failed for: r4v135 */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v44 */
    /* JADX WARN: Type inference failed for: r4v45 */
    /* JADX WARN: Type inference failed for: r4v50 */
    /* JADX WARN: Type inference failed for: r5v12 */
    /* JADX WARN: Type inference failed for: r5v24 */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v30 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v29 */
    /* JADX WARN: Type inference failed for: r6v30 */
    /* JADX WARN: Type inference failed for: r6v34 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v9 */
    public final void setDetailsContent(Object obj) {
        String strValueOf;
        TextView textView;
        Map<String, String> providers;
        String str;
        MediaMetadata metadata;
        ?? r4;
        String strValueOf2;
        ParentalRating parentalLevel;
        Integer durationInMinutes;
        String[] genres;
        Person[] cast;
        Person[] directors;
        Person person;
        Integer numValueOf;
        this.detailsContent = obj;
        MediaMetadata metadata2 = null;
        if (obj != null ? obj instanceof EPGDetailsViewModel.Result : true) {
            EPGDetailsViewModel.Result result = (EPGDetailsViewModel.Result) obj;
            updateAiringInfo(result != null ? result.getAiring() : null);
            if (result != null) {
                TextView textView2 = (TextView) findViewById(R.id.details_synopsis);
                if (textView2 != null) {
                    String summary = result.getProgram().getSummary();
                    if (summary == null) {
                        textView2.setText(getResources().getString(R.string.details_no_synopsis));
                        textView2.setTypeface(ResourcesCompat.getFont(getContext(), R.font.lato_italic));
                    } else if ((summary.length() > 0) == true) {
                        textView2.setText(result.getProgram().getSummary());
                        textView2.setTypeface(ResourcesCompat.getFont(getContext(), R.font.lato));
                    } else {
                        textView2.setText(getResources().getString(R.string.details_no_synopsis));
                        textView2.setTypeface(ResourcesCompat.getFont(getContext(), R.font.lato_italic));
                    }
                    Unit unit = Unit.INSTANCE;
                }
                StringBuilder sb = new StringBuilder();
                Person person2 = (Person) ArraysKt.firstOrNull(result.getProgram().getMetadata().getDirectors());
                if (person2 != null) {
                    sb.append(getResources().getString(R.string.directors) + " : ");
                    sb.append(person2.getTitle());
                }
                Person[] cast2 = result.getProgram().getMetadata().getCast();
                StringBuilder sb2 = sb;
                if ((sb2.length() > 0) != false) {
                    sb.append("          ");
                }
                String str2 = getResources().getString(R.string.actors) + " : ";
                if (((cast2.length == 0) ^ true) != false) {
                    sb.append(str2);
                    for (Person person3 : cast2) {
                        sb.append(person3.getTitle());
                        sb.append(", ");
                    }
                    sb.delete(sb.length() - 2, sb.length() - 1);
                }
                Unit unit2 = Unit.INSTANCE;
                TextView textView3 = (TextView) findViewById(R.id.details_casting);
                if (textView3 != null) {
                    textView3.setText(sb2);
                }
                StringBuilder sb3 = new StringBuilder();
                sb3.append(ArraysKt.joinToString$default(result.getAiring().getMetadata().getGenres(), " | ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
                if (result.getProgram().getMetadata().getDurationInMinutes() != null) {
                    numValueOf = result.getProgram().getMetadata().getDurationInMinutes();
                } else if (result.getAiring().getMetadata().getDurationInMinutes() != null) {
                    numValueOf = result.getAiring().getMetadata().getDurationInMinutes();
                } else {
                    numValueOf = Integer.valueOf((int) ((result.getAiring().getEndDate().getTime() - result.getAiring().getStartDate().getTime()) / ((long) 60000)));
                }
                if (numValueOf != null && numValueOf.intValue() > 0) {
                    if ((sb3.length() > 0) != false) {
                        sb3.append(getResources().getString(R.string.details_info_separator));
                    }
                    sb3.append(getResources().getString(R.string.details_info_runtime, numValueOf));
                }
                if (result.getAiring().getMetadata().getParentalLevel() != null) {
                    if ((sb3.length() > 0) != false) {
                        sb3.append(getResources().getString(R.string.details_info_separator));
                    }
                    ParentalRating parentalLevel2 = result.getAiring().getMetadata().getParentalLevel();
                    if (parentalLevel2 == null) {
                        Intrinsics.throwNpe();
                    }
                    sb3.append(getResources().getString(R.string.parental_level, parentalLevel2.getTitle()));
                } else {
                    if ((sb3.length() > 0) != false) {
                        sb3.append(getResources().getString(R.string.details_info_separator));
                    }
                    sb3.append(getResources().getString(R.string.parental_level, "NR"));
                }
                TextView textView4 = (TextView) findViewById(R.id.details_info);
                if (textView4 != null) {
                    textView4.setText(sb3.toString());
                    Unit unit3 = Unit.INSTANCE;
                    return;
                }
                return;
            }
            return;
        }
        if (obj != null ? obj instanceof CatchupDetailsViewModel.Result : true) {
            CatchupDetailsViewModel.Result result2 = (CatchupDetailsViewModel.Result) obj;
            Media media = result2 != null ? result2.getMedia() : null;
            if (media instanceof Movie) {
                Media media2 = result2.getMedia();
                if (media2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.Movie");
                }
                metadata = ((Movie) media2).getMetadata();
            } else if (media instanceof Episode) {
                Media media3 = result2.getMedia();
                if (media3 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.Episode");
                }
                metadata = ((Episode) media3).getMetadata();
            } else {
                metadata = null;
            }
            if (result2 != null) {
                TextView textView5 = (TextView) findViewById(R.id.details_synopsis);
                if (textView5 != null) {
                    String summary2 = result2.getMedia().getSummary();
                    if (summary2 == null) {
                        textView5.setText(getResources().getString(R.string.details_no_synopsis));
                        textView5.setTypeface(ResourcesCompat.getFont(getContext(), R.font.lato_italic));
                    } else if ((summary2.length() > 0) == true) {
                        textView5.setText(result2.getMedia().getSummary());
                        textView5.setTypeface(ResourcesCompat.getFont(getContext(), R.font.lato));
                    } else {
                        textView5.setText(getResources().getString(R.string.details_no_synopsis));
                        textView5.setTypeface(ResourcesCompat.getFont(getContext(), R.font.lato_italic));
                    }
                    Unit unit4 = Unit.INSTANCE;
                }
                StringBuilder sb4 = new StringBuilder();
                if (metadata != null && (directors = metadata.getDirectors()) != null && (person = (Person) ArraysKt.firstOrNull(directors)) != null) {
                    sb4.append(getResources().getString(R.string.directors) + " : ");
                    sb4.append(person.getTitle());
                }
                if (metadata != null && (cast = metadata.getCast()) != null) {
                    if ((sb4.length() > 0) != false) {
                        sb4.append("          ");
                    }
                    if (((cast.length == 0) ^ true) != false) {
                        sb4.append(getResources().getString(R.string.actors) + " : ");
                        int length = cast.length;
                        for (int i = 0; i < length; i++) {
                            sb4.append(cast[i].getTitle());
                            sb4.append(", ");
                        }
                        sb4.delete(sb4.length() - 2, sb4.length() - 1);
                    }
                    Unit unit5 = Unit.INSTANCE;
                }
                TextView textView6 = (TextView) findViewById(R.id.details_casting);
                if (textView6 != null) {
                    textView6.setText(sb4);
                }
                StringBuilder sb5 = new StringBuilder();
                sb5.append((metadata == null || (genres = metadata.getGenres()) == null) ? null : ArraysKt.joinToString$default(genres, " | ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
                if (metadata != null && (durationInMinutes = metadata.getDurationInMinutes()) != null) {
                    int iIntValue = durationInMinutes.intValue();
                    if ((sb5.length() > 0) != false) {
                        sb5.append(getResources().getString(R.string.details_info_separator));
                    }
                    sb5.append(getResources().getString(R.string.details_info_runtime, Integer.valueOf(iIntValue)));
                }
                if ((metadata != null ? metadata.getParentalLevel() : null) != null) {
                    if (sb5.length() > 0) {
                        r4 = true;
                    } else {
                        r4 = false;
                    }
                    if (r4 != false) {
                        sb5.append(getResources().getString(R.string.details_info_separator));
                    }
                    if (metadata.getParentalLevel() != null) {
                        parentalLevel = metadata.getParentalLevel();
                        if (parentalLevel == null) {
                            Intrinsics.throwNpe();
                        }
                        strValueOf2 = parentalLevel.getTitle();
                    } else {
                        strValueOf2 = String.valueOf(metadata.getRating());
                    }
                    sb5.append(getResources().getString(R.string.parental_level, strValueOf2));
                } else if ((metadata != null ? metadata.getRating() : null) != null) {
                    if (sb5.length() > 0) {
                        r4 = true;
                    } else {
                        r4 = false;
                    }
                    if (r4 != false) {
                        sb5.append(getResources().getString(R.string.details_info_separator));
                    }
                    if (metadata.getParentalLevel() != null) {
                        parentalLevel = metadata.getParentalLevel();
                        if (parentalLevel == null) {
                            Intrinsics.throwNpe();
                        }
                        strValueOf2 = parentalLevel.getTitle();
                    } else {
                        strValueOf2 = String.valueOf(metadata.getRating());
                    }
                    sb5.append(getResources().getString(R.string.parental_level, strValueOf2));
                } else {
                    if ((sb5.length() > 0) != false) {
                        sb5.append(getResources().getString(R.string.details_info_separator));
                    }
                    sb5.append(getResources().getString(R.string.parental_level, "NR"));
                }
                TextView textView7 = (TextView) findViewById(R.id.details_info);
                if (textView7 != null) {
                    textView7.setText(sb5.toString());
                    Unit unit6 = Unit.INSTANCE;
                    return;
                }
                return;
            }
            return;
        }
        if (obj instanceof VODDetailsViewModel.Result) {
            if (obj != null) {
                VODDetailsViewModel.Result result3 = (VODDetailsViewModel.Result) obj;
                Media media4 = ((result3.getMedia() instanceof Movie) || (result3.getMedia() instanceof Episode)) ? result3.getMedia() : null;
                if (media4 != null) {
                    try {
                        Offer offer = (Offer) CollectionsKt.first((List) result3.getOffers());
                        PortalApplication.Companion companion = PortalApplication.INSTANCE;
                        Context context = getContext();
                        Intrinsics.checkExpressionValueIsNotNull(context, "context");
                        LiveData<PortalApplication.Config> config = companion.getSingleton(context).getConfig();
                        Intrinsics.checkExpressionValueIsNotNull(config, "PortalApplication.getSin…                  .config");
                        PortalApplication.Config value = config.getValue();
                        if (value != null && (providers = value.getProviders()) != null && (str = providers.get(offer.getTitle())) != null) {
                            final ImageView imageView = (ImageView) findViewById(R.id.provider_logo);
                            Context context2 = getContext();
                            Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                            new ImageLoaderManager(context2).loadImageAsBitmap(str, (3064 & 2) != 0 ? (ImageView) null : imageView, (3064 & 4) != 0 ? false : true, (3064 & 8) != 0 ? 300 : android.R.integer.config_shortAnimTime, (3064 & 16) != 0 ? 0 : 0, (3064 & 32) != 0 ? 0 : 0, (3064 & 64) != 0 ? (ImageView.ScaleType) null : null, (3064 & 128) != 0 ? 0 : 0, (3064 & 256) != 0 ? 0 : 0, (3064 & 512) != 0 ? (RequestListener) null : new RequestListener<Bitmap>() { // from class: com.dotscreen.portal.ui.screens.details.ProgramDetailsView$detailsContent$3$1$1
                                @Override // com.bumptech.glide.request.RequestListener
                                public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override // com.bumptech.glide.request.RequestListener
                                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                                    ImageView providerLogoView = imageView;
                                    Intrinsics.checkExpressionValueIsNotNull(providerLogoView, "providerLogoView");
                                    providerLogoView.setVisibility(0);
                                    return false;
                                }
                            }, (3064 & 1024) == 0, (3064 & 2048) != 0 ? (Drawable) null : null);
                        }
                    } catch (NoSuchElementException unused) {
                        Log.d(this.TAG, "No offer found");
                    }
                    TextView textView8 = (TextView) findViewById(R.id.details_airing_date);
                    if (textView8 != null) {
                        textView8.setVisibility(8);
                    }
                    TextView textView9 = (TextView) findViewById(R.id.details_synopsis);
                    if (textView9 != null) {
                        if (media4.getSummary() == null) {
                            textView9.setText(getResources().getString(R.string.details_no_synopsis));
                            textView9.setTypeface(ResourcesCompat.getFont(getContext(), R.font.lato_italic));
                        } else {
                            String summary3 = media4.getSummary();
                            if (summary3 == null) {
                                Intrinsics.throwNpe();
                            }
                            if ((summary3.length() > 0) != false) {
                                textView9.setText(media4.getSummary());
                                textView9.setTypeface(ResourcesCompat.getFont(getContext(), R.font.lato));
                            } else {
                                textView9.setText(getResources().getString(R.string.details_no_synopsis));
                                textView9.setTypeface(ResourcesCompat.getFont(getContext(), R.font.lato_italic));
                            }
                        }
                        Unit unit7 = Unit.INSTANCE;
                    }
                    PlaybackRight right = result3.getRight();
                    if (right != null) {
                        updateRentInfo(right);
                        Unit unit8 = Unit.INSTANCE;
                    }
                    if (media4 instanceof Movie) {
                        metadata2 = ((Movie) media4).getMetadata();
                    } else if (media4 instanceof Episode) {
                        metadata2 = ((Episode) media4).getMetadata();
                    }
                    if (metadata2 != null) {
                        StringBuilder sb6 = new StringBuilder();
                        Person person4 = (Person) ArraysKt.firstOrNull(metadata2.getDirectors());
                        if (person4 != null) {
                            sb6.append(getResources().getString(R.string.directors) + " : ");
                            sb6.append(person4.getTitle());
                        }
                        Person[] cast3 = metadata2.getCast();
                        StringBuilder sb7 = sb6;
                        if ((sb7.length() > 0) != false) {
                            sb6.append("   ");
                        }
                        sb6.append(getResources().getString(R.string.actors) + " : ");
                        int length2 = cast3.length;
                        ?? r7 = true;
                        int i2 = 0;
                        while (i2 < length2) {
                            sb6.append(cast3[i2].getTitle());
                            sb6.append(", ");
                            i2++;
                            r7 = false;
                        }
                        sb6.delete(sb6.length() - 2, sb6.length() - 1);
                        if (r7 == false && (textView = (TextView) findViewById(R.id.details_casting)) != null) {
                            textView.setText(sb7);
                        }
                        StringBuilder sb8 = new StringBuilder();
                        String[] genres2 = metadata2.getGenres();
                        String string = getResources().getString(R.string.details_info_separator);
                        Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.st…g.details_info_separator)");
                        sb8.append(StringsKt.capitalize(ArraysKt.joinToString$default(genres2, string, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)));
                        Integer durationInMinutes2 = metadata2.getDurationInMinutes();
                        if (durationInMinutes2 != null) {
                            int iIntValue2 = durationInMinutes2.intValue();
                            if ((sb8.length() > 0) != false) {
                                sb8.append(getResources().getString(R.string.details_info_separator));
                            }
                            sb8.append(getResources().getString(R.string.details_info_runtime, Integer.valueOf(iIntValue2)));
                        }
                        if (metadata2.getParentalLevel() != null || metadata2.getRating() != null) {
                            if ((sb8.length() > 0) != false) {
                                sb8.append(getResources().getString(R.string.details_info_separator));
                            }
                            if (metadata2.getParentalLevel() != null) {
                                ParentalRating parentalLevel3 = metadata2.getParentalLevel();
                                if (parentalLevel3 == null) {
                                    Intrinsics.throwNpe();
                                }
                                strValueOf = parentalLevel3.getTitle();
                            } else {
                                strValueOf = String.valueOf(metadata2.getRating());
                            }
                            sb8.append(getResources().getString(R.string.parental_level, strValueOf));
                        } else {
                            if ((sb8.length() > 0) != false) {
                                sb8.append(getResources().getString(R.string.details_info_separator));
                            }
                            sb8.append(getResources().getString(R.string.parental_level, "AA"));
                        }
                        TextView textView10 = (TextView) findViewById(R.id.details_info);
                        if (textView10 != null) {
                            textView10.setText(sb8.toString());
                            Unit unit9 = Unit.INSTANCE;
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.model.VODDetailsViewModel.Result");
        }
    }

    public final void updateAiringInfo(TVAiring airing) {
        if (airing != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE MMMM dd yyyy");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH':'mm");
            Date date = new Date();
            if (airing.isLive()) {
                TextView textView = (TextView) findViewById(R.id.details_live_start);
                if (textView != null) {
                    textView.setVisibility(0);
                    textView.setText(simpleDateFormat2.format(airing.getStartDate()));
                }
                TextView textView2 = (TextView) findViewById(R.id.details_live_end);
                if (textView2 != null) {
                    textView2.setVisibility(0);
                    textView2.setText(simpleDateFormat2.format(airing.getEndDate()));
                }
                ProgressBar progressBar = (ProgressBar) findViewById(R.id.details_live_progress);
                if (progressBar != null) {
                    progressBar.setVisibility(0);
                    progressBar.setMax((int) (airing.getEndDate().getTime() - airing.getStartDate().getTime()));
                    progressBar.setProgress((int) (date.getTime() - airing.getStartDate().getTime()));
                }
                TextView textView3 = (TextView) findViewById(R.id.details_airing_date);
                if (textView3 != null) {
                    textView3.setVisibility(4);
                }
            } else {
                Calendar airingCal = Calendar.getInstance();
                Intrinsics.checkExpressionValueIsNotNull(airingCal, "airingCal");
                airingCal.setTimeInMillis(airing.getStartDate().getTime());
                Calendar calendar = Calendar.getInstance();
                boolean zIsFuture = airing.isFuture();
                int i = R.string.details_airing_time_other;
                if (zIsFuture) {
                    if (calendar.get(6) == airingCal.get(6)) {
                        i = R.string.details_airing_time_today;
                    } else if (airingCal.get(6) - calendar.get(6) == 1) {
                        i = R.string.details_airing_time_tomorrow;
                    }
                }
                TextView textView4 = (TextView) findViewById(R.id.details_airing_date);
                if (textView4 != null) {
                    textView4.setVisibility(0);
                    textView4.setText(getResources().getString(i, simpleDateFormat2.format(airing.getStartDate()), simpleDateFormat2.format(airing.getEndDate()), simpleDateFormat.format(airing.getStartDate())));
                }
                TextView textView5 = (TextView) findViewById(R.id.details_live_start);
                if (textView5 != null) {
                    textView5.setVisibility(8);
                }
                TextView textView6 = (TextView) findViewById(R.id.details_live_end);
                if (textView6 != null) {
                    textView6.setVisibility(8);
                }
                ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.details_live_progress);
                if (progressBar2 != null) {
                    progressBar2.setVisibility(8);
                }
            }
        }
        postDelayed(new Runnable() { // from class: com.dotscreen.portal.ui.screens.details.ProgramDetailsView.updateAiringInfo.2
            @Override // java.lang.Runnable
            public final void run() {
                ProgramDetailsView programDetailsView = ProgramDetailsView.this;
                EPGDetailsViewModel.Result result = (EPGDetailsViewModel.Result) programDetailsView.getDetailsContent();
                programDetailsView.updateAiringInfo(result != null ? result.getAiring() : null);
            }
        }, 1000L);
    }

    public final void updateRentInfo(PlaybackRight right) {
        TextView textView;
        Intrinsics.checkParameterIsNotNull(right, "right");
        Log.v(this.TAG, "Will update rent info with: " + right);
        if (right.getEnd() == null || (textView = (TextView) findViewById(R.id.tvod_expiration_delay)) == null) {
            return;
        }
        Log.v(this.TAG, "Expired : " + right.getEnd());
        textView.setVisibility(0);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH':'mm");
        Locale locale = Locale.getDefault();
        Intrinsics.checkExpressionValueIsNotNull(locale, "Locale.getDefault()");
        textView.setText(getResources().getString(R.string.tvod_available_until, DateFormat.getDateInstance(1, Intrinsics.areEqual(locale.getLanguage(), "en") ? Locale.ENGLISH : new Locale("es")).format(right.getEnd()), simpleDateFormat.format(right.getEnd())));
    }
}
