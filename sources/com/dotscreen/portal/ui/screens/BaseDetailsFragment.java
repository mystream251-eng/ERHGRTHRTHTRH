package com.dotscreen.portal.ui.screens;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.BrowseSupportFragment;
import com.dotscreen.portal.ui.screens.details.EPGDetailsActivity;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: BaseDetailsFragment.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0014J\b\u0010\u000b\u001a\u00020\nH\u0014J\b\u0010\f\u001a\u00020\nH\u0014J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/portal/ui/screens/BaseDetailsFragment;", "Landroidx/leanback/app/BrowseSupportFragment;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "handler", "Landroid/os/Handler;", "hasMultipleAudios", "", "hasMultipleSubtitles", "isBookmarked", "startActivity", "", "intent", "Landroid/content/Intent;", "Companion", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class BaseDetailsFragment extends BrowseSupportFragment {
    public static final String EXTRA_CALLING_INTENT = "extra_calling_intent";
    private HashMap _$_findViewCache;
    private final Handler handler = new Handler();

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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View viewFindViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), viewFindViewById);
        return viewFindViewById;
    }

    public abstract String getTAG();

    @Override // androidx.leanback.app.BrowseSupportFragment, androidx.leanback.app.BrandedSupportFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    protected boolean hasMultipleAudios() {
        Log.v(getTAG(), " TODO : check if the content has multiple audios");
        return false;
    }

    protected boolean hasMultipleSubtitles() {
        Log.v(getTAG(), " TODO : check if the content has multiple subtitles");
        return false;
    }

    protected boolean isBookmarked() {
        Log.v(getTAG(), " TODO : check if the content is bookmarked");
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void startActivity(Intent intent) {
        Uri data;
        String string;
        if (intent != null && (data = intent.getData()) != null && (string = data.toString()) != null) {
            if (StringsKt.contains$default((CharSequence) string, (CharSequence) "/details?", false, 2, (Object) null)) {
                FragmentActivity activity = getActivity();
                intent.putExtra("extra_calling_intent", activity != null ? activity.getIntent() : null);
                FragmentActivity activity2 = getActivity();
                EPGDetailsActivity ePGDetailsActivity = (EPGDetailsActivity) (activity2 instanceof EPGDetailsActivity ? activity2 : null);
                if (ePGDetailsActivity != null && ePGDetailsActivity.getStartedFromLiveTv()) {
                    intent.addFlags(524288);
                    intent.addFlags(134217728);
                    intent.putExtra("extra_started_from_live_tv", true);
                }
                this.handler.postDelayed(new Runnable() { // from class: com.dotscreen.portal.ui.screens.BaseDetailsFragment$startActivity$$inlined$let$lambda$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        FragmentActivity activity3 = this.this$0.getActivity();
                        if (activity3 != null) {
                            activity3.finish();
                        }
                    }
                }, 10L);
            }
        }
        super.startActivity(intent);
    }
}
