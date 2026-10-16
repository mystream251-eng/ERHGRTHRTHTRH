package com.dotscreen.portal.ui.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.bumptech.glide.request.RequestListener;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.ui.ImageLoaderManager;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ProfileView.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\b\u0010\u0011\u001a\u00020\u0012H\u0014R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/portal/ui/views/ProfileView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "defStyleRes", "(Landroid/content/Context;Landroid/util/AttributeSet;II)V", "avatarView", "Landroid/widget/ImageView;", "usernameTextView", "Landroid/widget/TextView;", "onAttachedToWindow", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ProfileView extends LinearLayout {
    private HashMap _$_findViewCache;
    private final ImageView avatarView;
    private final TextView usernameTextView;

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
    public ProfileView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        Intrinsics.checkParameterIsNotNull(context, "context");
        View viewInflate = View.inflate(getContext(), R.layout.profile_view, this);
        if (viewInflate == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
        }
        View viewFindViewById = findViewById(R.id.username);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.username)");
        this.usernameTextView = (TextView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.avatar);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById(R.id.avatar)");
        this.avatarView = (ImageView) viewFindViewById2;
    }

    public /* synthetic */ ProfileView(Context context, AttributeSet attributeSet, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i3 & 2) != 0 ? (AttributeSet) null : attributeSet, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? 0 : i2);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ProfileView(Context context) {
        this(context, null, 0, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ProfileView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ProfileView(Context context, AttributeSet attrs, int i) {
        this(context, attrs, i, 0);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attrs, "attrs");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            return;
        }
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context context = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        LiveData<PortalApplication.Config> config = companion.getSingleton(context).getConfig();
        Context context2 = getContext();
        if (context2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.BaseFragmentActivity");
        }
        config.observe((BaseFragmentActivity) context2, new AnonymousClass1());
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.views.ProfileView$onAttachedToWindow$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProfileView.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0016\u0010\u0002\u001a\u0012 \u0005*\b\u0018\u00010\u0003R\u00020\u00040\u0003R\u00020\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "config", "Lcom/dotscreen/portal/PortalApplication$Config;", "Lcom/dotscreen/portal/PortalApplication;", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass1<T> implements Observer<PortalApplication.Config> {
        AnonymousClass1() {
        }

        @Override // androidx.lifecycle.Observer
        public final void onChanged(PortalApplication.Config config) {
            if (config == null) {
                Intrinsics.throwNpe();
            }
            final RightVUser user = config.getUser();
            if (user instanceof RightVUser) {
                ProfileView.this.usernameTextView.post(new Runnable() { // from class: com.dotscreen.portal.ui.views.ProfileView$onAttachedToWindow$1$$special$$inlined$let$lambda$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        ProfileView.this.usernameTextView.setText(StringsKt.take(user.getTitle(), 10));
                        Context context = ProfileView.this.getContext();
                        Intrinsics.checkExpressionValueIsNotNull(context, "context");
                        new ImageLoaderManager(context).loadImageAsBitmap(user.getAvatarUrl(), (3064 & 2) != 0 ? (ImageView) null : ProfileView.this.avatarView, (3064 & 4) != 0 ? false : false, (3064 & 8) != 0 ? 300 : 0, (3064 & 16) != 0 ? 0 : 0, (3064 & 32) != 0 ? 0 : 0, (3064 & 64) != 0 ? (ImageView.ScaleType) null : null, (3064 & 128) != 0 ? 0 : 0, (3064 & 256) != 0 ? 0 : 0, (3064 & 512) != 0 ? (RequestListener) null : null, (3064 & 1024) == 0 ? false : false, (3064 & 2048) != 0 ? (Drawable) null : null);
                    }
                });
            }
        }
    }
}
