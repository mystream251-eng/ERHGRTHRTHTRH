package com.dotscreen.portal.ui.screens.setup;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.tv.ui.views.SettingsInfoCellView;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;

/* JADX INFO: compiled from: SettingsInfoActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0013H\u0014J\b\u0010\u0017\u001a\u00020\u0013H&R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/SettingsInfoActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "infoCells", "Ljava/util/ArrayList;", "Lcom/dotscreen/tv/ui/views/SettingsInfoCellView;", "Lkotlin/collections/ArrayList;", "getInfoCells", "()Ljava/util/ArrayList;", "title", "Landroid/widget/TextView;", "getTitle", "()Landroid/widget/TextView;", "setTitle", "(Landroid/widget/TextView;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "populateTab", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class SettingsInfoActivity extends BaseFragmentActivity {
    private HashMap _$_findViewCache;
    private TextView title;
    private final String TAG = SettingsInfoActivity.class.getSimpleName();
    private final ArrayList<SettingsInfoCellView> infoCells = new ArrayList<>();

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

    public abstract void populateTab();

    protected final ArrayList<SettingsInfoCellView> getInfoCells() {
        return this.infoCells;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.app.Activity
    public final TextView getTitle() {
        return this.title;
    }

    protected final void setTitle(TextView textView) {
        this.title = textView;
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_infos);
        for (int i = 0; i <= 11; i++) {
            this.infoCells.add((SettingsInfoCellView) findViewById(getResources().getIdentifier("settingsInfoCell" + i, "id", getPackageName())));
        }
        this.title = (TextView) findViewById(R.id.title);
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        populateTab();
    }
}
