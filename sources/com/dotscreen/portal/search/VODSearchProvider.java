package com.dotscreen.portal.search;

import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Point;
import android.net.Uri;
import android.util.Log;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.bokit.model.ItemCollection;
import com.dotscreen.bokit.services.VODCatalogService;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.bokit.MegacableVODCatalogService;
import com.dotscreen.tv.common.bokit.SearchableContent;
import com.dotscreen.tv.common.search.LocalSearchProvider;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.gms.actions.SearchIntents;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchProvider.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00072\u0006\u0010\t\u001a\u00020\nJ\b\u0010\u000b\u001a\u00020\fH\u0016JK\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0004H\u0016¢\u0006\u0002\u0010\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/dotscreen/portal/search/VODSearchProvider;", "Lcom/dotscreen/tv/common/search/LocalSearchProvider;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "createRow", "", "", "item", "Lcom/dotscreen/bokit/model/Content;", "onCreate", "", SearchIntents.EXTRA_QUERY, "Landroid/database/Cursor;", "uri", "Landroid/net/Uri;", "projection", "", "selection", "selectionArgs", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class VODSearchProvider extends LocalSearchProvider {
    private final String TAG = VODSearchProvider.class.getSimpleName();

    @Override // com.dotscreen.tv.common.search.LocalSearchProvider, android.content.ContentProvider
    public boolean onCreate() {
        Log.d(this.TAG, "onCreate()");
        return super.onCreate();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<Object> createRow(Content item) {
        Object string;
        URL urlUrlWithSize$default;
        String string2;
        URL urlUrlWithSize$default2;
        Intrinsics.checkParameterIsNotNull(item, "item");
        ArrayList arrayList = new ArrayList();
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.vod_content_card_width);
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        int dimensionPixelSize2 = context2.getResources().getDimensionPixelSize(R.dimen.vod_content_card_height);
        arrayList.add(item.getTitle());
        arrayList.add("");
        Artwork posterArtwork = item.getPosterArtwork();
        Object objValueOf = Integer.valueOf(R.drawable.ph_vertical);
        if (posterArtwork == null || (urlUrlWithSize$default2 = Artwork.urlWithSize$default(posterArtwork, new Point(dimensionPixelSize, dimensionPixelSize2), 0.0d, 2, null)) == null || (string = urlUrlWithSize$default2.toString()) == null) {
            string = objValueOf;
        }
        arrayList.add(string);
        arrayList.add("android.intent.action.VIEW");
        if (item instanceof SearchableContent) {
            StringBuilder sb = new StringBuilder();
            sb.append("megacable://com.dotscreen.megacable.portal/vod/details?content_id=");
            sb.append(item.getId());
            sb.append("&from_search=true&entity_type=");
            SearchableContent searchableContent = (SearchableContent) item;
            sb.append(searchableContent.getEntityType());
            sb.append("&search_result_id=");
            sb.append(searchableContent.getContentSearchId());
            arrayList.add(sb.toString());
        } else {
            arrayList.add("megacable://com.dotscreen.megacable.portal/vod/details?content_id=" + item.getId());
        }
        arrayList.add(MimeTypes.VIDEO_MP4);
        arrayList.add(false);
        arrayList.add(1920);
        arrayList.add(1080);
        Integer durationInMinutes = item.getMetadata().getDurationInMinutes();
        arrayList.add(Integer.valueOf((durationInMinutes != null ? durationInMinutes.intValue() : 0) * 60 * 1000));
        arrayList.add(item.getMetadata().getProductionYear());
        Log.e(this.TAG, "--> send to google for VOD :");
        Log.e(this.TAG, "SUGGEST_COLUMN_TEXT_1 = " + item.getTitle());
        Log.e(this.TAG, "SUGGEST_COLUMN_TEXT_2 = ");
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("SUGGEST_COLUMN_RESULT_CARD_IMAGE = ");
        Artwork posterArtwork2 = item.getPosterArtwork();
        if (posterArtwork2 != null && (urlUrlWithSize$default = Artwork.urlWithSize$default(posterArtwork2, new Point(dimensionPixelSize, dimensionPixelSize2), 0.0d, 2, null)) != null && (string2 = urlUrlWithSize$default.toString()) != null) {
            objValueOf = string2;
        }
        sb2.append(objValueOf);
        Log.e(str, sb2.toString());
        Log.e(this.TAG, "SUGGEST_COLUMN_INTENT_ACTION = android.intent.action.VIEW");
        String str2 = "megacable://com.dotscreen.megacable.portal/vod/details?content_id=" + item.getId();
        Log.e(this.TAG, "SUGGEST_COLUMN_INTENT_DATA = " + str2);
        Log.e(this.TAG, "SUGGEST_COLUMN_CONTENT_TYPE = video/mp4");
        Log.e(this.TAG, "SUGGEST_COLUMN_IS_LIVE = false");
        Log.e(this.TAG, "SUGGEST_COLUMN_VIDEO_WIDTH = 1920");
        Log.e(this.TAG, "SUGGEST_COLUMN_VIDEO_HEIGHT = 1080");
        String str3 = this.TAG;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("SUGGEST_COLUMN_DURATION = ");
        Integer durationInMinutes2 = item.getMetadata().getDurationInMinutes();
        sb3.append((durationInMinutes2 != null ? durationInMinutes2.intValue() : 0) * 60 * 1000);
        Log.e(str3, sb3.toString());
        Log.e(this.TAG, "SUGGEST_COLUMN_PRODUCTION_YEAR = " + item.getMetadata().getProductionYear());
        return arrayList;
    }

    @Override // com.dotscreen.tv.common.search.LocalSearchProvider, android.content.ContentProvider
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        String str;
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        Log.d(this.TAG, "enter VODSearchProvider");
        Log.d(this.TAG, "query(" + uri + ", " + Arrays.toString(projection) + ", " + selection + ", " + Arrays.toString(selectionArgs) + ", " + sortOrder + ")");
        if (selectionArgs == null || (str = selectionArgs[0]) == null) {
            return null;
        }
        Log.e(this.TAG, "args = " + str);
        try {
            PortalApplication.Companion companion = PortalApplication.INSTANCE;
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            VODCatalogService vodCatalogService = companion.getSingleton(context).getBOService().getVodCatalogService();
            if (vodCatalogService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableVODCatalogService");
            }
            return (Cursor) ((MegacableVODCatalogService) vodCatalogService).compassSearch(str).flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.portal.search.VODSearchProvider.query.1
                @Override // io.reactivex.functions.Function
                public final Observable<Content> apply(ItemCollection<Content> it) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    return it.getItems();
                }
            }).toList().map(new Function<T, R>() { // from class: com.dotscreen.portal.search.VODSearchProvider.query.2
                @Override // io.reactivex.functions.Function
                public final MatrixCursor apply(List<Content> rows) {
                    Intrinsics.checkParameterIsNotNull(rows, "rows");
                    ArrayList arrayList = new ArrayList();
                    for (T t : rows) {
                        if (!Intrinsics.areEqual(((Content) t).getId(), "dummyItem")) {
                            arrayList.add(t);
                        }
                    }
                    ArrayList arrayList2 = arrayList;
                    List<Content> listSubList = arrayList2.subList(0, Math.min(arrayList2.size(), 10));
                    Log.d(VODSearchProvider.this.TAG, "nb results: " + listSubList.size());
                    MatrixCursor matrixCursor = new MatrixCursor(LocalSearchProvider.SEARCHABLE_COLUMNS, listSubList.size());
                    for (Content row : listSubList) {
                        VODSearchProvider vODSearchProvider = VODSearchProvider.this;
                        Intrinsics.checkExpressionValueIsNotNull(row, "row");
                        matrixCursor.addRow(vODSearchProvider.createRow(row));
                    }
                    return matrixCursor;
                }
            }).blockingGet();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(this.TAG, "error during loading");
            return new MatrixCursor(LocalSearchProvider.SEARCHABLE_COLUMNS, 0);
        }
    }
}
