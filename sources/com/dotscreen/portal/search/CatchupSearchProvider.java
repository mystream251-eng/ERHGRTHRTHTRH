package com.dotscreen.portal.search;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Point;
import android.net.Uri;
import android.util.Log;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.services.LiveTVService;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.LiveProgramsSearchResponse;
import com.dotscreen.tv.common.bokit.MegacableLiveTVService;
import com.dotscreen.tv.common.bokit.SearchTVProgram;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0006\u001a\u00020\u0007H\u0016JK\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u000e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\u0011R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/portal/search/CatchupSearchProvider;", "Lcom/dotscreen/tv/common/search/LocalSearchProvider;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "onCreate", "", SearchIntents.EXTRA_QUERY, "Landroid/database/Cursor;", "uri", "Landroid/net/Uri;", "projection", "", "selection", "selectionArgs", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class CatchupSearchProvider extends LocalSearchProvider {
    private final String TAG = CatchupSearchProvider.class.getSimpleName();

    @Override // com.dotscreen.tv.common.search.LocalSearchProvider, android.content.ContentProvider
    public boolean onCreate() {
        Log.d(this.TAG, "onCreate()");
        return super.onCreate();
    }

    @Override // com.dotscreen.tv.common.search.LocalSearchProvider, android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String str;
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        Log.d(this.TAG, "enter CatchupSearchProvider");
        Log.d(this.TAG, "query(" + uri + ", " + Arrays.toString(projection) + ", " + selection + ", " + Arrays.toString(selectionArgs) + ", " + sortOrder + ")");
        if (selectionArgs == null || (str = selectionArgs[0]) == null) {
            return null;
        }
        Log.e(this.TAG, "args = " + str);
        try {
            PortalApplication.Companion companion = PortalApplication.INSTANCE;
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            LiveTVService liveTVService = companion.getSingleton(context).getBOService().getLiveTVService();
            if (liveTVService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableLiveTVService");
            }
            return (Cursor) ((MegacableLiveTVService) liveTVService).searchProgram(str).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.search.CatchupSearchProvider.query.1
                @Override // io.reactivex.functions.Function
                public final Observable<SearchTVProgram> apply(LiveProgramsSearchResponse it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.getCatchups().getItems();
                }
            }).take(10L).map(new Function<T, R>() { // from class: com.dotscreen.portal.search.CatchupSearchProvider.query.2
                @Override // io.reactivex.functions.Function
                public final List<Object> apply(SearchTVProgram item) {
                    Object string;
                    URL urlUrlWithSize$default;
                    String string2;
                    URL urlUrlWithSize$default2;
                    Intrinsics.checkParameterIsNotNull(item, "item");
                    ArrayList arrayList = new ArrayList();
                    Context context2 = CatchupSearchProvider.this.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context2, "context");
                    int dimensionPixelSize = context2.getResources().getDimensionPixelSize(R.dimen.airing_card_width);
                    Context context3 = CatchupSearchProvider.this.getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context3, "context");
                    int dimensionPixelSize2 = context3.getResources().getDimensionPixelSize(R.dimen.airing_card_height);
                    Log.i(CatchupSearchProvider.this.TAG, "SUGGEST_COLUMN_TEXT_1 = row.add(\"" + item.getTitle() + " en " + item.getChannel().getZappingNumber() + " - " + item.getChannel().getTitle() + "\")");
                    String str2 = CatchupSearchProvider.this.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("SUGGEST_COLUMN_TEXT_2 = row.add(\"");
                    sb.append(item.getStartDate());
                    sb.append(" -> ");
                    sb.append(item.getEndDate());
                    sb.append("\")");
                    Log.i(str2, sb.toString());
                    String str3 = CatchupSearchProvider.this.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("SUGGEST_COLUMN_RESULT_CARD_IMAGE = row.add(");
                    Artwork stillArtwork = item.getStillArtwork();
                    Object objValueOf = Integer.valueOf(R.drawable.ph_horizontal);
                    if (stillArtwork == null || (urlUrlWithSize$default2 = Artwork.urlWithSize$default(stillArtwork, new Point(dimensionPixelSize, dimensionPixelSize2), 0.0d, 2, null)) == null || (string = urlUrlWithSize$default2.toString()) == null) {
                        string = objValueOf;
                    }
                    sb2.append(string);
                    sb2.append(')');
                    Log.i(str3, sb2.toString());
                    Log.i(CatchupSearchProvider.this.TAG, "SUGGEST_COLUMN_INTENT_ACTION = row.add(Intent.ACTION_VIEW)");
                    Log.i(CatchupSearchProvider.this.TAG, "SUGGEST_COLUMN_INTENT_DATA = row.add(\"megacable://com.dotscreen.megacable.portal/epg/details?channel_id=" + item.getChannel().getId() + "&start=" + item.getStartDate().getTime() + "\")");
                    Log.i(CatchupSearchProvider.this.TAG, "SUGGEST_COLUMN_CONTENT_TYPE = row.add(TvContract.Programs.CONTENT_ITEM_TYPE)");
                    Log.i(CatchupSearchProvider.this.TAG, "SUGGEST_COLUMN_IS_LIVE = row.add(" + item.isLive() + ')');
                    Log.i(CatchupSearchProvider.this.TAG, "SUGGEST_COLUMN_VIDEO_WIDTH = row.add(" + dimensionPixelSize + ')');
                    Log.i(CatchupSearchProvider.this.TAG, "SUGGEST_COLUMN_VIDEO_HEIGHT = row.add(" + dimensionPixelSize2 + ')');
                    String str4 = CatchupSearchProvider.this.TAG;
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("SUGGEST_COLUMN_DURATION = row.add(");
                    Integer durationInMinutes = item.getMetadata().getDurationInMinutes();
                    sb3.append((durationInMinutes != null ? durationInMinutes.intValue() : 0) * 60000);
                    sb3.append(')');
                    Log.i(str4, sb3.toString());
                    Log.i(CatchupSearchProvider.this.TAG, "SUGGEST_COLUMN_PRODUCTION_YEAR = row.add(" + item.getMetadata().getProductionYear() + ')');
                    arrayList.add(item.getTitle() + " en " + item.getChannel().getZappingNumber() + " - " + item.getChannel().getTitle());
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(item.getStartDate().toLocaleString());
                    sb4.append(" -> ");
                    sb4.append(item.getEndDate().toLocaleString());
                    arrayList.add(sb4.toString());
                    Artwork stillArtwork2 = item.getStillArtwork();
                    if (stillArtwork2 != null && (urlUrlWithSize$default = Artwork.urlWithSize$default(stillArtwork2, new Point(dimensionPixelSize, dimensionPixelSize2), 0.0d, 2, null)) != null && (string2 = urlUrlWithSize$default.toString()) != null) {
                        objValueOf = string2;
                    }
                    arrayList.add(objValueOf);
                    arrayList.add("android.intent.action.VIEW");
                    arrayList.add("megacable://com.dotscreen.megacable.portal/epg/details?channel_id=" + item.getChannel().getId() + "&start=" + item.getStartDate().getTime() + "&from_search=true&entity_type=" + item.getEntityType() + "&search_result_id=" + item.getContentSearchId());
                    arrayList.add(TvContractCompat.Programs.CONTENT_ITEM_TYPE);
                    arrayList.add(Boolean.valueOf(item.isLive()));
                    arrayList.add(Integer.valueOf(dimensionPixelSize));
                    arrayList.add(Integer.valueOf(dimensionPixelSize2));
                    arrayList.add(item.getMetadata().getDurationInMinutes());
                    arrayList.add(item.getMetadata().getProductionYear());
                    return arrayList;
                }
            }).toList().map(new Function<T, R>() { // from class: com.dotscreen.portal.search.CatchupSearchProvider.query.3
                @Override // io.reactivex.functions.Function
                public final MatrixCursor apply(List<List<Object>> rows) {
                    Intrinsics.checkParameterIsNotNull(rows, "rows");
                    Log.d(CatchupSearchProvider.this.TAG, "nb results: " + rows.size());
                    MatrixCursor matrixCursor = new MatrixCursor(LocalSearchProvider.SEARCHABLE_COLUMNS, rows.size());
                    Iterator<List<Object>> it = rows.iterator();
                    while (it.hasNext()) {
                        matrixCursor.addRow(it.next());
                    }
                    return matrixCursor;
                }
            }).blockingGet();
        } catch (Exception e) {
            e.printStackTrace();
            return new MatrixCursor(LocalSearchProvider.SEARCHABLE_COLUMNS, 0);
        }
    }
}
