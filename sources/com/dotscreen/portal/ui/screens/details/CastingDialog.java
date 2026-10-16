package com.dotscreen.portal.ui.screens.details;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.VerticalGridPresenter;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dotscreen.bokit.model.Episode;
import com.dotscreen.bokit.model.Movie;
import com.dotscreen.bokit.model.Person;
import com.dotscreen.bokit.model.TVProgram;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.ui.ImageLoaderManager;
import com.dotscreen.tv.ui.dialog.PortalDialog;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CastingDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u0000 \t2\u00020\u0001:\t\u0007\b\t\n\u000b\f\r\u000e\u000fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CastingDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "themeResId", "", "(Landroid/content/Context;I)V", "Builder", "CastSelector", "Companion", "ContentPresenter", "ContentString", "CreditPresenter", "OnItemClickedListener", "TitlePresenter", "TitleString", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class CastingDialog extends Dialog {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = CastingDialog.class.getSimpleName();

    /* JADX INFO: compiled from: CastingDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CastingDialog$OnItemClickedListener;", "", "onItemClicked", "", "dialog", "Lcom/dotscreen/portal/ui/screens/details/CastingDialog;", "itemIndex", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface OnItemClickedListener {
        void onItemClicked(CastingDialog dialog, int itemIndex);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CastingDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    /* JADX INFO: compiled from: CastingDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CastingDialog$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return CastingDialog.TAG;
        }
    }

    /* JADX INFO: compiled from: CastingDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u000eH\u0014J\u0010\u0010\u0010\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CastingDialog$Builder;", "Lcom/dotscreen/tv/ui/dialog/PortalDialog$InternalBuilder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", ToolboxAnalyticsModuleKt.KEY_CONTENT, "", "contentViewId", "", "getContentViewId", "()I", "onItemClickedListener", "Lcom/dotscreen/portal/ui/screens/details/CastingDialog$OnItemClickedListener;", "create", "Landroid/app/Dialog;", "createDialog", "setContent", "setOnItemClickedListener", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static class Builder extends PortalDialog.InternalBuilder<Builder> {
        private Object content;
        private OnItemClickedListener onItemClickedListener;

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected int getContentViewId() {
            return R.layout.dialog_cast;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(Context context) {
            super(context);
            Intrinsics.checkParameterIsNotNull(context, "context");
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected Dialog createDialog() {
            return new CastingDialog(getContext(), 2131951633);
        }

        public final Builder setContent(Object content) {
            this.content = content;
            return this;
        }

        public final Builder setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
            Intrinsics.checkParameterIsNotNull(onItemClickedListener, "onItemClickedListener");
            this.onItemClickedListener = onItemClickedListener;
            return this;
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        public Dialog create() {
            Dialog dialogCreate = super.create();
            if (dialogCreate == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.details.CastingDialog");
            }
            CastingDialog castingDialog = (CastingDialog) dialogCreate;
            FrameLayout frameLayout = (FrameLayout) castingDialog.findViewById(R.id.grid_container);
            VerticalGridPresenter verticalGridPresenter = new VerticalGridPresenter(0, false);
            verticalGridPresenter.setShadowEnabled(false);
            verticalGridPresenter.enableChildRoundedCorners(false);
            verticalGridPresenter.setNumberOfColumns(1);
            ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new CastSelector());
            Object obj = this.content;
            if (obj instanceof TVProgram) {
                if (obj != null) {
                    TVProgram tVProgram = (TVProgram) obj;
                    String string = castingDialog.getContext().getString(R.string.details_credits_synopsis);
                    Intrinsics.checkExpressionValueIsNotNull(string, "dialog.context.getString…details_credits_synopsis)");
                    arrayObjectAdapter.add(new TitleString(string));
                    String summary = tVProgram.getSummary();
                    if (summary == null) {
                        summary = getContext().getResources().getString(R.string.details_no_synopsis);
                        Intrinsics.checkExpressionValueIsNotNull(summary, "context.resources.getStr…ring.details_no_synopsis)");
                    }
                    arrayObjectAdapter.add(new ContentString(summary));
                    Person[] cast = tVProgram.getMetadata().getCast();
                    if (!(cast.length == 0)) {
                        String string2 = castingDialog.getContext().getString(R.string.details_credits_actors);
                        Intrinsics.checkExpressionValueIsNotNull(string2, "dialog.context.getString…g.details_credits_actors)");
                        arrayObjectAdapter.add(new TitleString(string2));
                        for (Person person : cast) {
                            arrayObjectAdapter.add(person);
                        }
                    }
                    Person[] directors = tVProgram.getMetadata().getDirectors();
                    if (!(directors.length == 0)) {
                        String string3 = castingDialog.getContext().getString(R.string.details_credits_director);
                        Intrinsics.checkExpressionValueIsNotNull(string3, "dialog.context.getString…details_credits_director)");
                        arrayObjectAdapter.add(new TitleString(string3));
                        arrayObjectAdapter.add(ArraysKt.first(directors));
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.TVProgram");
                }
            } else if (obj instanceof Movie) {
                if (obj != null) {
                    Movie movie = (Movie) obj;
                    String string4 = castingDialog.getContext().getString(R.string.details_credits_synopsis);
                    Intrinsics.checkExpressionValueIsNotNull(string4, "dialog.context.getString…details_credits_synopsis)");
                    arrayObjectAdapter.add(new TitleString(string4));
                    String summary2 = movie.getSummary();
                    if (summary2 == null) {
                        summary2 = getContext().getResources().getString(R.string.details_no_synopsis);
                        Intrinsics.checkExpressionValueIsNotNull(summary2, "context.resources.getStr…ring.details_no_synopsis)");
                    }
                    arrayObjectAdapter.add(new ContentString(summary2));
                    Person[] cast2 = movie.getMetadata().getCast();
                    if (!(cast2.length == 0)) {
                        String string5 = castingDialog.getContext().getString(R.string.details_credits_actors);
                        Intrinsics.checkExpressionValueIsNotNull(string5, "dialog.context.getString…g.details_credits_actors)");
                        arrayObjectAdapter.add(new TitleString(string5));
                        for (Person person2 : cast2) {
                            arrayObjectAdapter.add(person2);
                        }
                    }
                    Person[] directors2 = movie.getMetadata().getDirectors();
                    if (!(directors2.length == 0)) {
                        String string6 = castingDialog.getContext().getString(R.string.details_credits_director);
                        Intrinsics.checkExpressionValueIsNotNull(string6, "dialog.context.getString…details_credits_director)");
                        arrayObjectAdapter.add(new TitleString(string6));
                        arrayObjectAdapter.add(ArraysKt.first(directors2));
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.Movie");
                }
            } else if (obj instanceof Episode) {
                if (obj != null) {
                    Episode episode = (Episode) obj;
                    String string7 = castingDialog.getContext().getString(R.string.details_credits_synopsis);
                    Intrinsics.checkExpressionValueIsNotNull(string7, "dialog.context.getString…details_credits_synopsis)");
                    arrayObjectAdapter.add(new TitleString(string7));
                    String summary3 = episode.getSummary();
                    if (summary3 == null) {
                        summary3 = getContext().getResources().getString(R.string.details_no_synopsis);
                        Intrinsics.checkExpressionValueIsNotNull(summary3, "context.resources.getStr…ring.details_no_synopsis)");
                    }
                    arrayObjectAdapter.add(new ContentString(summary3));
                    Person[] cast3 = episode.getMetadata().getCast();
                    if (!(cast3.length == 0)) {
                        String string8 = castingDialog.getContext().getString(R.string.details_credits_actors);
                        Intrinsics.checkExpressionValueIsNotNull(string8, "dialog.context.getString…g.details_credits_actors)");
                        arrayObjectAdapter.add(new TitleString(string8));
                        for (Person person3 : cast3) {
                            arrayObjectAdapter.add(person3);
                        }
                    }
                    Person[] directors3 = episode.getMetadata().getDirectors();
                    if (!(directors3.length == 0)) {
                        String string9 = castingDialog.getContext().getString(R.string.details_credits_director);
                        Intrinsics.checkExpressionValueIsNotNull(string9, "dialog.context.getString…details_credits_director)");
                        arrayObjectAdapter.add(new TitleString(string9));
                        arrayObjectAdapter.add(ArraysKt.first(directors3));
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.Episode");
                }
            }
            VerticalGridPresenter.ViewHolder viewHolderOnCreateViewHolder = verticalGridPresenter.onCreateViewHolder((ViewGroup) frameLayout);
            View gridView = viewHolderOnCreateViewHolder.view;
            Intrinsics.checkExpressionValueIsNotNull(gridView, "gridView");
            gridView.setPadding(gridView.getPaddingLeft(), 50, gridView.getPaddingRight(), gridView.getPaddingBottom());
            frameLayout.addView(gridView);
            verticalGridPresenter.onBindViewHolder(viewHolderOnCreateViewHolder, arrayObjectAdapter);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            Window window = castingDialog.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "dialog.window");
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.height = -1;
            layoutParams.width = getContext().getResources().getDimensionPixelSize(R.dimen.right_popup_width);
            layoutParams.gravity = 5;
            Window window2 = castingDialog.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window2, "dialog.window");
            window2.setAttributes(layoutParams);
            return castingDialog;
        }
    }

    /* JADX INFO: compiled from: CastingDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CastingDialog$TitleString;", "", "string", "", "(Ljava/lang/String;)V", "getString", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class TitleString {
        private final String string;

        public static /* synthetic */ TitleString copy$default(TitleString titleString, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = titleString.string;
            }
            return titleString.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getString() {
            return this.string;
        }

        public final TitleString copy(String string) {
            Intrinsics.checkParameterIsNotNull(string, "string");
            return new TitleString(string);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof TitleString) && Intrinsics.areEqual(this.string, ((TitleString) other).string);
            }
            return true;
        }

        public int hashCode() {
            String str = this.string;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "TitleString(string=" + this.string + ")";
        }

        public TitleString(String string) {
            Intrinsics.checkParameterIsNotNull(string, "string");
            this.string = string;
        }

        public final String getString() {
            return this.string;
        }
    }

    /* JADX INFO: compiled from: CastingDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CastingDialog$ContentString;", "", "string", "", "(Ljava/lang/String;)V", "getString", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private static final /* data */ class ContentString {
        private final String string;

        public static /* synthetic */ ContentString copy$default(ContentString contentString, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                str = contentString.string;
            }
            return contentString.copy(str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getString() {
            return this.string;
        }

        public final ContentString copy(String string) {
            Intrinsics.checkParameterIsNotNull(string, "string");
            return new ContentString(string);
        }

        public boolean equals(Object other) {
            if (this != other) {
                return (other instanceof ContentString) && Intrinsics.areEqual(this.string, ((ContentString) other).string);
            }
            return true;
        }

        public int hashCode() {
            String str = this.string;
            if (str != null) {
                return str.hashCode();
            }
            return 0;
        }

        public String toString() {
            return "ContentString(string=" + this.string + ")";
        }

        public ContentString(String string) {
            Intrinsics.checkParameterIsNotNull(string, "string");
            this.string = string;
        }

        public final String getString() {
            return this.string;
        }
    }

    /* JADX INFO: compiled from: CastingDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CastingDialog$CastSelector;", "Landroidx/leanback/widget/PresenterSelector;", "()V", "getPresenter", "Landroidx/leanback/widget/Presenter;", "item", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private static final class CastSelector extends PresenterSelector {
        @Override // androidx.leanback.widget.PresenterSelector
        public Presenter getPresenter(Object item) {
            if (item instanceof TitleString) {
                return new TitlePresenter();
            }
            if (item instanceof ContentString) {
                return new ContentPresenter();
            }
            if (item instanceof Person) {
                return new CreditPresenter();
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: CastingDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\r"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CastingDialog$TitlePresenter;", "Landroidx/leanback/widget/Presenter;", "()V", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private static final class TitlePresenter extends Presenter {
        @Override // androidx.leanback.widget.Presenter
        public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        }

        @Override // androidx.leanback.widget.Presenter
        public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
            if (parent == null) {
                Intrinsics.throwNpe();
            }
            return new Presenter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_casting_section_title, parent, false));
        }

        @Override // androidx.leanback.widget.Presenter
        public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
            Log.d(CastingDialog.INSTANCE.getTAG(), "TitlePresenter.onBindViewHolder: " + item);
            View view = viewHolder != null ? viewHolder.view : null;
            if (view == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
            TextView textView = (TextView) view;
            if (item == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.details.CastingDialog.TitleString");
            }
            textView.setText(((TitleString) item).getString());
        }
    }

    /* JADX INFO: compiled from: CastingDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CastingDialog$ContentPresenter;", "Landroidx/leanback/widget/Presenter;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    private static final class ContentPresenter extends Presenter {
        private Context context;

        @Override // androidx.leanback.widget.Presenter
        public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        }

        public final Context getContext() {
            return this.context;
        }

        public final void setContext(Context context) {
            this.context = context;
        }

        @Override // androidx.leanback.widget.Presenter
        public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
            if (parent == null) {
                Intrinsics.throwNpe();
            }
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(parent.getContext());
            this.context = parent.getContext();
            View view = layoutInflaterFrom.inflate(R.layout.view_casting_text, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(view, "view");
            view.setFocusable(true);
            return new Presenter.ViewHolder(view);
        }

        @Override // androidx.leanback.widget.Presenter
        public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
            Log.d(CastingDialog.INSTANCE.getTAG(), "ContentPresenter.onBindViewHolder: " + item);
            ViewGroup viewGroup = (ViewGroup) (viewHolder != null ? viewHolder.view : null);
            if (viewGroup != null) {
                View viewFindViewById = viewGroup.findViewById(R.id.casting_text);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "it.findViewById<TextView>(R.id.casting_text)");
                TextView textView = (TextView) viewFindViewById;
                if (item == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.details.CastingDialog.ContentString");
                }
                textView.setText(((ContentString) item).getString());
                Context context = this.context;
                Context applicationContext = context != null ? context.getApplicationContext() : null;
                if (applicationContext == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                }
                PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
                if (value != null) {
                    FrameLayout selector = (FrameLayout) viewGroup.findViewById(R.id.synopsis_selector);
                    Intrinsics.checkExpressionValueIsNotNull(selector, "selector");
                    DesignManager designManager = value.getDesignManager();
                    Drawable background = selector.getBackground();
                    Intrinsics.checkExpressionValueIsNotNull(background, "selector.background");
                    selector.setBackground(designManager.tintDrawable(background, "standard.item.selected.outline.color"));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: CastingDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0012\u0010\u0012\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/portal/ui/screens/details/CastingDialog$CreditPresenter;", "Landroidx/leanback/widget/Presenter;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    static final class CreditPresenter extends Presenter {
        private Context context;

        @Override // androidx.leanback.widget.Presenter
        public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        }

        public final Context getContext() {
            return this.context;
        }

        public final void setContext(Context context) {
            this.context = context;
        }

        @Override // androidx.leanback.widget.Presenter
        public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
            if (parent == null) {
                Intrinsics.throwNpe();
            }
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(parent.getContext());
            this.context = parent.getContext();
            View view = layoutInflaterFrom.inflate(R.layout.view_casting_credit, parent, false);
            Intrinsics.checkExpressionValueIsNotNull(view, "view");
            view.setFocusable(true);
            return new Presenter.ViewHolder(view);
        }

        @Override // androidx.leanback.widget.Presenter
        public void onBindViewHolder(Presenter.ViewHolder viewHolder, final Object item) {
            Log.d(CastingDialog.INSTANCE.getTAG(), "CreditPresenter.onBindViewHolder: " + item);
            final ViewGroup viewGroup = (ViewGroup) (viewHolder != null ? viewHolder.view : null);
            if (viewGroup != null) {
                if (item != null) {
                    final Person person = (Person) item;
                    StringBuilder sb = new StringBuilder();
                    sb.append(person.getTitle());
                    String summary = person.getSummary();
                    if (summary != null) {
                        sb.append("\n");
                        sb.append(summary);
                    }
                    View viewFindViewById = viewGroup.findViewById(R.id.credit_title);
                    Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "it.findViewById<TextView>(R.id.credit_title)");
                    ((TextView) viewFindViewById).setText(sb);
                    Context context = this.context;
                    Context applicationContext = context != null ? context.getApplicationContext() : null;
                    if (applicationContext == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                    }
                    final PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
                    if (value != null) {
                        FrameLayout selector = (FrameLayout) viewGroup.findViewById(R.id.casting_selector);
                        Intrinsics.checkExpressionValueIsNotNull(selector, "selector");
                        DesignManager designManager = value.getDesignManager();
                        Drawable background = selector.getBackground();
                        Intrinsics.checkExpressionValueIsNotNull(background, "selector.background");
                        selector.setBackground(designManager.tintDrawable(background, "standard.item.selected.outline.color"));
                        final ImageView imageView = (ImageView) viewGroup.findViewById(R.id.credit_image);
                        if (imageView != null) {
                            Drawable drawable = viewGroup.getResources().getDrawable(R.drawable.credit_default, null);
                            if (drawable == null) {
                                throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
                            }
                            RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(viewGroup.getResources(), ((BitmapDrawable) drawable).getBitmap());
                            Intrinsics.checkExpressionValueIsNotNull(roundedBitmapDrawableCreate, "RoundedBitmapDrawableFac…esources, default.bitmap)");
                            roundedBitmapDrawableCreate.setCornerRadius(viewGroup.getResources().getDimension(R.dimen.details_casting_credit_image_size) / 2.0f);
                            String str = Uri.encode(person.getTitle()) + ".jpg";
                            PortalApplication.Companion companion = PortalApplication.INSTANCE;
                            Context context2 = this.context;
                            if (context2 == null) {
                                Intrinsics.throwNpe();
                            }
                            String string = companion.getSingleton(context2).getBOService().resolveUrl(Intrinsics.stringPlus(value.getActorBaseUrl(), str)).toString();
                            Intrinsics.checkExpressionValueIsNotNull(string, "PortalApplication.getSin…eUrl + endURL).toString()");
                            Context context3 = this.context;
                            if (context3 == null) {
                                Intrinsics.throwNpe();
                            }
                            new ImageLoaderManager(context3).loadImageAsBitmap(string, (3064 & 2) != 0 ? (ImageView) null : imageView, (3064 & 4) != 0 ? false : false, (3064 & 8) != 0 ? 300 : 0, (3064 & 16) != 0 ? 0 : 0, (3064 & 32) != 0 ? 0 : 0, (3064 & 64) != 0 ? (ImageView.ScaleType) null : null, (3064 & 128) != 0 ? 0 : 0, (3064 & 256) != 0 ? 0 : 0, (3064 & 512) != 0 ? (RequestListener) null : new RequestListener<Bitmap>() { // from class: com.dotscreen.portal.ui.screens.details.CastingDialog$CreditPresenter$onBindViewHolder$$inlined$let$lambda$1
                                @Override // com.bumptech.glide.request.RequestListener
                                public boolean onLoadFailed(GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override // com.bumptech.glide.request.RequestListener
                                public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z) {
                                    if (bitmap == null) {
                                        return true;
                                    }
                                    Context context4 = this.getContext();
                                    if (context4 == null) {
                                        Intrinsics.throwNpe();
                                    }
                                    RoundedBitmapDrawable roundedBitmapDrawableCreate2 = RoundedBitmapDrawableFactory.create(context4.getResources(), bitmap);
                                    Intrinsics.checkExpressionValueIsNotNull(roundedBitmapDrawableCreate2, "RoundedBitmapDrawableFac…xt!!.resources, resource)");
                                    roundedBitmapDrawableCreate2.setCornerRadius(bitmap.getHeight() / 2.0f);
                                    imageView.setImageDrawable(roundedBitmapDrawableCreate2);
                                    return true;
                                }
                            }, (3064 & 1024) == 0 ? false : false, (3064 & 2048) != 0 ? (Drawable) null : roundedBitmapDrawableCreate);
                            return;
                        }
                        return;
                    }
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.bokit.model.Person");
            }
        }
    }
}
