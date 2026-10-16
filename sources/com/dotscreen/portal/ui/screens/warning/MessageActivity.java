package com.dotscreen.portal.ui.screens.warning;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import androidx.leanback.app.BackgroundManager;
import androidx.lifecycle.LiveData;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MessageActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\u0004\u0018\u00010\nX\u0094D¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/dotscreen/portal/ui/screens/warning/MessageActivity;", "Landroid/app/Activity;", "()V", "background", "Landroid/graphics/drawable/Drawable;", "getBackground", "()Landroid/graphics/drawable/Drawable;", "setBackground", "(Landroid/graphics/drawable/Drawable;)V", "backgroundDesignPath", "", "getBackgroundDesignPath", "()Ljava/lang/String;", "backgroundManager", "Landroidx/leanback/app/BackgroundManager;", "metrics", "Landroid/util/DisplayMetrics;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "prepareBackgroundManager", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public abstract class MessageActivity extends Activity {
    private HashMap _$_findViewCache;
    private Drawable background;
    private final String backgroundDesignPath = "standard.background.image.default";
    private BackgroundManager backgroundManager;
    private DisplayMetrics metrics;

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

    public final Drawable getBackground() {
        return this.background;
    }

    public final void setBackground(Drawable drawable) {
        this.background = drawable;
    }

    protected String getBackgroundDesignPath() {
        return this.backgroundDesignPath;
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareBackgroundManager();
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
                designManager.getBitmap(backgroundDesignPath).subscribe(new Consumer<Bitmap>() { // from class: com.dotscreen.portal.ui.screens.warning.MessageActivity$prepareBackgroundManager$$inlined$let$lambda$1
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Bitmap bitmap) {
                        this.this$0.setBackground(new BitmapDrawable(this.this$0.getResources(), bitmap));
                        BackgroundManager backgroundManager3 = this.this$0.backgroundManager;
                        if (backgroundManager3 != null) {
                            backgroundManager3.setDrawable(this.this$0.getBackground());
                        }
                    }
                }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.warning.MessageActivity$prepareBackgroundManager$1$2
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Throwable th) {
                        th.printStackTrace();
                    }
                }, new Action() { // from class: com.dotscreen.portal.ui.screens.warning.MessageActivity$prepareBackgroundManager$1$3
                    @Override // io.reactivex.functions.Action
                    public final void run() {
                    }
                });
            }
        }
    }
}
