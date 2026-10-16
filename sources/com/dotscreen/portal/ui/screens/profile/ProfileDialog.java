package com.dotscreen.portal.ui.screens.profile;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.GravityCompat;
import androidx.leanback.widget.HorizontalGridView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.dotscreen.bokit.services.PincodeType;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.Profile;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.modules.ToolboxAnalyticsModuleKt;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.ui.dialog.PortalDialog;
import com.google.android.gms.common.Scopes;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProfileDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 \f2\u00020\u0001:\u0006\b\t\n\u000b\f\rB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/ProfileDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "themeResId", "", "(Landroid/content/Context;I)V", "selectorColor", "AvatarAdapter", "AvatarView", "AvatarViewHolder", "Builder", "Companion", "OnProfileChangeListener", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ProfileDialog extends Dialog {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;
    private int selectorColor;

    /* JADX INFO: compiled from: ProfileDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001f\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&¢\u0006\u0002\u0010\tJ\u001f\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&¢\u0006\u0002\u0010\t¨\u0006\u000b"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/ProfileDialog$OnProfileChangeListener;", "", "onNewProfile", "", Scopes.PROFILE, "Lcom/dotscreen/tv/common/bokit/Profile;", "onProfileDeleted", "index", "", "(Lcom/dotscreen/tv/common/bokit/Profile;Ljava/lang/Integer;)V", "onProfileUpdate", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface OnProfileChangeListener {
        void onNewProfile(Profile profile);

        void onProfileDeleted(Profile profile, Integer index);

        void onProfileUpdate(Profile profile, Integer index);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProfileDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
        if (value != null) {
            Integer color = value.getDesignManager().getColor("standard.item.selected.outline.color");
            if (color == null) {
                Intrinsics.throwNpe();
            }
            this.selectorColor = color.intValue();
        }
    }

    /* JADX INFO: compiled from: ProfileDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/ProfileDialog$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return ProfileDialog.TAG;
        }
    }

    static {
        String simpleName = ProfileDialog.class.getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "ProfileDialog::class.java.simpleName");
        TAG = simpleName;
    }

    /* JADX INFO: compiled from: ProfileDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020!H\u0014J\b\u0010#\u001a\u00020$H\u0002J\u000e\u0010%\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010&\u001a\u00020\u00002\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u0015\u0010'\u001a\u00020\u00002\b\u0010(\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010)J\u0006\u0010*\u001a\u00020$J\b\u0010+\u001a\u00020$H\u0002J\b\u0010,\u001a\u00020-H\u0002R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0006X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\nR\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001bR\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/ProfileDialog$Builder;", "Lcom/dotscreen/tv/ui/dialog/PortalDialog$InternalBuilder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "bottomText", "Landroid/widget/TextView;", "getBottomText", "()Landroid/widget/TextView;", "setBottomText", "(Landroid/widget/TextView;)V", ToolboxAnalyticsModuleKt.KEY_CONTENT, "Landroid/widget/LinearLayout;", "contentViewId", "", "getContentViewId", "()I", "errorView", "getErrorView", "setErrorView", "onProfileChangeListener", "Lcom/dotscreen/portal/ui/screens/profile/ProfileDialog$OnProfileChangeListener;", "parentalSpinner", "Landroid/widget/Spinner;", Scopes.PROFILE, "Lcom/dotscreen/tv/common/bokit/Profile;", "profileIndex", "Ljava/lang/Integer;", "progress", "Landroid/widget/ProgressBar;", "usernameInput", "Landroid/widget/EditText;", "create", "Landroid/app/Dialog;", "createDialog", "hideProgress", "", "setOnProfileChangeListener", "setProfile", "setProfileIndex", "index", "(Ljava/lang/Integer;)Lcom/dotscreen/portal/ui/screens/profile/ProfileDialog$Builder;", "showPinCode", "showProgress", "validForm", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static class Builder extends PortalDialog.InternalBuilder<Builder> {
        private TextView bottomText;
        private LinearLayout content;
        private TextView errorView;
        private OnProfileChangeListener onProfileChangeListener;
        private Spinner parentalSpinner;
        private Profile profile;
        private Integer profileIndex;
        private ProgressBar progress;
        private EditText usernameInput;

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected int getContentViewId() {
            return R.layout.dialog_profile;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(Context context) {
            super(context);
            Intrinsics.checkParameterIsNotNull(context, "context");
        }

        protected final TextView getErrorView() {
            return this.errorView;
        }

        protected final void setErrorView(TextView textView) {
            this.errorView = textView;
        }

        protected final TextView getBottomText() {
            return this.bottomText;
        }

        protected final void setBottomText(TextView textView) {
            this.bottomText = textView;
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected Dialog createDialog() {
            return new ProfileDialog(getContext(), 2131951633);
        }

        public final Builder setOnProfileChangeListener(OnProfileChangeListener onProfileChangeListener) {
            Intrinsics.checkParameterIsNotNull(onProfileChangeListener, "onProfileChangeListener");
            this.onProfileChangeListener = onProfileChangeListener;
            return this;
        }

        public final Builder setProfileIndex(Integer index) {
            this.profileIndex = index;
            return this;
        }

        public final Builder setProfile(Profile profile) {
            this.profile = profile;
            return this;
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        public Dialog create() {
            Profile profile;
            String image;
            List listSplit$default;
            String str;
            List listSplit$default2;
            String str2;
            Dialog dialogCreate = super.create();
            if (dialogCreate == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.profile.ProfileDialog");
            }
            final ProfileDialog profileDialog = (ProfileDialog) dialogCreate;
            Context applicationContext = getContext().getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            UserService userService = ((PortalApplication) applicationContext).getBOService().getUserService();
            if (userService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
            }
            final MegacableUserService megacableUserService = (MegacableUserService) userService;
            this.progress = (ProgressBar) profileDialog.findViewById(R.id.progress);
            this.content = (LinearLayout) profileDialog.findViewById(R.id.content);
            this.usernameInput = (EditText) profileDialog.findViewById(R.id.username_input);
            final HorizontalGridView avatarList = (HorizontalGridView) profileDialog.findViewById(R.id.avatar_list);
            FrameLayout frameLayout = (FrameLayout) profileDialog.findViewById(R.id.parental_rating_choose_container);
            this.parentalSpinner = (Spinner) profileDialog.findViewById(R.id.parental_rating_choose);
            TextView textView = (TextView) profileDialog.findViewById(R.id.validation_button);
            EditText editText = this.usernameInput;
            if (editText != null) {
                editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileDialog$Builder$create$1
                    @Override // android.widget.TextView.OnEditorActionListener
                    public final boolean onEditorAction(TextView textView2, int i, KeyEvent keyEvent) {
                        Log.d(ProfileDialog.INSTANCE.getTAG(), " UserInput => view : " + textView2 + ", action: " + i);
                        if (7 != i) {
                            return false;
                        }
                        Intrinsics.checkExpressionValueIsNotNull(textView2, "textView");
                        Object systemService = textView2.getContext().getSystemService("input_method");
                        if (systemService == null) {
                            throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                        }
                        ((InputMethodManager) systemService).hideSoftInputFromWindow(textView2.getWindowToken(), 0);
                        return false;
                    }
                });
            }
            Intrinsics.checkExpressionValueIsNotNull(avatarList, "avatarList");
            avatarList.setAdapter(new AvatarAdapter(getContext()));
            avatarList.setHorizontalSpacing(50);
            avatarList.setSelectedPosition(1);
            String[] stringArray = getContext().getResources().getStringArray(R.array.parental_rating_array_to_display);
            Spinner spinner = this.parentalSpinner;
            if (spinner != null) {
                spinner.setAdapter((SpinnerAdapter) new ArrayAdapter(getContext(), R.layout.spinner_text, stringArray));
            }
            frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileDialog$Builder$create$2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.this$0.showPinCode();
                }
            });
            if (Intrinsics.areEqual(getTitle(), getContext().getString(R.string.update_profile)) && (profile = this.profile) != null) {
                EditText editText2 = this.usernameInput;
                if (editText2 != null) {
                    editText2.setText(profile != null ? profile.getName() : null, TextView.BufferType.EDITABLE);
                }
                Profile profile2 = this.profile;
                Integer numValueOf = (profile2 == null || (image = profile2.getImage()) == null || (listSplit$default = StringsKt.split$default((CharSequence) image, new String[]{"/"}, false, 0, 6, (Object) null)) == null || (str = (String) CollectionsKt.last(listSplit$default)) == null || (listSplit$default2 = StringsKt.split$default((CharSequence) str, new String[]{"."}, false, 0, 6, (Object) null)) == null || (str2 = (String) listSplit$default2.get(0)) == null) ? null : Integer.valueOf(Integer.parseInt(str2));
                avatarList.setSelectedPosition(numValueOf != null ? numValueOf.intValue() : 1);
                Profile profile3 = this.profile;
                if (profile3 != null) {
                    int parentalLevel = profile3.getParentalLevel();
                    Spinner spinner2 = this.parentalSpinner;
                    if (spinner2 != null) {
                        if (parentalLevel >= stringArray.length) {
                            parentalLevel = stringArray.length - 1;
                        } else if (parentalLevel != 0) {
                            parentalLevel--;
                        }
                        spinner2.setSelection(parentalLevel);
                    }
                }
            }
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileDialog$Builder$create$4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    String string;
                    Log.d(ProfileDialog.INSTANCE.getTAG(), "Will submit => " + this.this$0.getTitle());
                    if (!Intrinsics.areEqual(this.this$0.getTitle(), this.this$0.getContext().getString(R.string.add_profile)) || !this.this$0.validForm()) {
                        if (Intrinsics.areEqual(this.this$0.getTitle(), this.this$0.getContext().getString(R.string.update_profile))) {
                            if (this.this$0.validForm()) {
                                EditText editText3 = this.this$0.usernameInput;
                                if (editText3 == null) {
                                    Intrinsics.throwNpe();
                                }
                                string = editText3.getText().toString();
                            } else {
                                string = null;
                            }
                            String str3 = string;
                            MegacableUserService megacableUserService2 = megacableUserService;
                            Spinner spinner3 = this.this$0.parentalSpinner;
                            if (spinner3 == null) {
                                Intrinsics.throwNpe();
                            }
                            Integer numValueOf2 = Integer.valueOf(spinner3.getSelectedItemPosition() + 1);
                            HorizontalGridView avatarList2 = avatarList;
                            Intrinsics.checkExpressionValueIsNotNull(avatarList2, "avatarList");
                            Intrinsics.checkExpressionValueIsNotNull(MegacableUserService.updateProfile$default(megacableUserService2, str3, numValueOf2, Integer.valueOf(avatarList2.getSelectedPosition()), null, null, 24, null).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Profile>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileDialog$Builder$create$4.4
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Profile profile4) {
                                    ProfileDialog$Builder$create$4.this.this$0.getContext().sendBroadcast(new Intent(CommonUtils.ACTION_USER_CHANGED));
                                    ProfileDialog.OnProfileChangeListener onProfileChangeListener = ProfileDialog$Builder$create$4.this.this$0.onProfileChangeListener;
                                    if (onProfileChangeListener != null) {
                                        Intrinsics.checkExpressionValueIsNotNull(profile4, "profile");
                                        onProfileChangeListener.onProfileUpdate(profile4, ProfileDialog$Builder$create$4.this.this$0.profileIndex);
                                    }
                                    profileDialog.dismiss();
                                }
                            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileDialog$Builder$create$4.5
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Throwable t) {
                                    profileDialog.dismiss();
                                    t.printStackTrace();
                                    Context context = ProfileDialog$Builder$create$4.this.this$0.getContext();
                                    if (!(context instanceof BaseFragmentActivity)) {
                                        context = null;
                                    }
                                    BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) context;
                                    if (baseFragmentActivity != null) {
                                        Intrinsics.checkExpressionValueIsNotNull(t, "t");
                                        BaseFragmentActivity.displayErrorMessage$default(baseFragmentActivity, t, 0, 2, null);
                                    }
                                }
                            }), "userService\n            …                       })");
                            return;
                        }
                        Toast.makeText(this.this$0.getContext(), R.string.invalid_username_input, 0).show();
                        return;
                    }
                    MegacableUserService megacableUserService3 = megacableUserService;
                    EditText editText4 = this.this$0.usernameInput;
                    if (editText4 == null) {
                        Intrinsics.throwNpe();
                    }
                    String string2 = editText4.getText().toString();
                    Spinner spinner4 = this.this$0.parentalSpinner;
                    if (spinner4 == null) {
                        Intrinsics.throwNpe();
                    }
                    int selectedItemPosition = spinner4.getSelectedItemPosition() + 1;
                    HorizontalGridView avatarList3 = avatarList;
                    Intrinsics.checkExpressionValueIsNotNull(avatarList3, "avatarList");
                    MegacableUserService.addProfile$default(megacableUserService3, string2, selectedItemPosition, Integer.valueOf(avatarList3.getSelectedPosition()), null, null, 24, null).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileDialog$Builder$create$4.1
                        @Override // io.reactivex.functions.Function
                        public final Maybe<Profile> apply(JSONObject it) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            return MegacableUserService.profiles$default(megacableUserService, false, 1, null).lastElement();
                        }
                    }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Profile>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileDialog$Builder$create$4.2
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Profile profile4) {
                            ProfileDialog.OnProfileChangeListener onProfileChangeListener = ProfileDialog$Builder$create$4.this.this$0.onProfileChangeListener;
                            if (onProfileChangeListener != null) {
                                Intrinsics.checkExpressionValueIsNotNull(profile4, "profile");
                                onProfileChangeListener.onNewProfile(profile4);
                            }
                            profileDialog.dismiss();
                        }
                    }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileDialog$Builder$create$4.3
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Throwable t) {
                            profileDialog.dismiss();
                            t.printStackTrace();
                            Context context = ProfileDialog$Builder$create$4.this.this$0.getContext();
                            if (!(context instanceof BaseFragmentActivity)) {
                                context = null;
                            }
                            BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) context;
                            if (baseFragmentActivity != null) {
                                Intrinsics.checkExpressionValueIsNotNull(t, "t");
                                BaseFragmentActivity.displayErrorMessage$default(baseFragmentActivity, t, 0, 2, null);
                            }
                        }
                    });
                }
            });
            this.bottomText = (TextView) profileDialog.findViewById(R.id.bottom_text);
            hideProgress();
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            Window window = profileDialog.getWindow();
            layoutParams.copyFrom(window != null ? window.getAttributes() : null);
            layoutParams.height = -1;
            layoutParams.width = getContext().getResources().getDimensionPixelSize(R.dimen.right_popup_width);
            layoutParams.gravity = GravityCompat.END;
            Window window2 = profileDialog.getWindow();
            if (window2 != null) {
                window2.setAttributes(layoutParams);
            }
            return profileDialog;
        }

        public final void showPinCode() {
            PinCodeDialog.Builder title = new PinCodeDialog.Builder(getContext()).setCheckPinCode(true).setPincodeType(PincodeType.Parental).setTitle(R.string.input_parental_pin_code);
            Context applicationContext = getContext().getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
            if (value == null) {
                Intrinsics.throwNpe();
            }
            RightVUser user = value.getUser();
            if (user == null) {
                Intrinsics.throwNpe();
            }
            title.setUser(user).setOnPinCodeListener(new PinCodeDialog.OnPinCodeListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileDialog$Builder$showPinCode$1
                @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                public void onPinCodeCorrect(PinCodeDialog dialog, List<String> pinCodes) {
                    Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                    Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
                    PinCodeDialog.OnPinCodeListener.DefaultImpls.onPinCodeCorrect(this, dialog, pinCodes);
                }

                @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                public void onPinCodeInvalid(PinCodeDialog dialog, String pinCode) {
                    Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                    Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
                    PinCodeDialog.OnPinCodeListener.DefaultImpls.onPinCodeInvalid(this, dialog, pinCode);
                }

                @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                public void onPinCodeInvalid(PinCodeDialog dialog, List<String> pinCodes, Exception exc) {
                    Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                    Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
                    PinCodeDialog.OnPinCodeListener.DefaultImpls.onPinCodeInvalid(this, dialog, pinCodes, exc);
                }

                @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
                public void onPinCodeCorrect(PinCodeDialog dialog, String pinCode) {
                    Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                    Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
                    dialog.dismiss();
                    Spinner spinner = this.this$0.parentalSpinner;
                    if (spinner != null) {
                        spinner.performClick();
                    }
                }
            }).create().show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean validForm() {
            EditText editText = this.usernameInput;
            Boolean boolValueOf = null;
            Editable text = editText != null ? editText.getText() : null;
            if (text != null) {
                boolValueOf = Boolean.valueOf(text.length() > 0);
            }
            if (boolValueOf == null) {
                Intrinsics.throwNpe();
            }
            return boolValueOf.booleanValue();
        }

        private final void hideProgress() {
            ProgressBar progressBar = this.progress;
            if (progressBar != null) {
                progressBar.setVisibility(4);
            }
        }

        private final void showProgress() {
            ProgressBar progressBar = this.progress;
            if (progressBar != null) {
                progressBar.setVisibility(0);
            }
            LinearLayout linearLayout = this.content;
            if (linearLayout != null) {
                linearLayout.setVisibility(4);
            }
        }
    }

    /* JADX INFO: compiled from: ProfileDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\u0007H\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u0007H\u0016J\u0018\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0007H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/ProfileDialog$AvatarAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/dotscreen/portal/ui/screens/profile/ProfileDialog$AvatarViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "avatarCounts", "", "avatarUrl", "", "getContext", "()Landroid/content/Context;", "getItemCount", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class AvatarAdapter extends RecyclerView.Adapter<AvatarViewHolder> {
        private final int avatarCounts;
        private final String avatarUrl;
        private final Context context;

        public AvatarAdapter(Context context) {
            PortalApplication.AvatarsConfig avatarsConfig;
            PortalApplication.AvatarsConfig avatarsConfig2;
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.context = context;
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
            Integer numValueOf = null;
            String path = (value == null || (avatarsConfig2 = value.getAvatarsConfig()) == null) ? null : avatarsConfig2.getPath();
            if (path == null) {
                Intrinsics.throwNpe();
            }
            this.avatarUrl = path;
            Context applicationContext2 = this.context.getApplicationContext();
            if (applicationContext2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value2 = ((PortalApplication) applicationContext2).getConfig().getValue();
            if (value2 != null && (avatarsConfig = value2.getAvatarsConfig()) != null) {
                numValueOf = Integer.valueOf(avatarsConfig.getTotal());
            }
            if (numValueOf == null) {
                Intrinsics.throwNpe();
            }
            this.avatarCounts = numValueOf.intValue();
        }

        public final Context getContext() {
            return this.context;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public AvatarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Intrinsics.checkParameterIsNotNull(parent, "parent");
            return new AvatarViewHolder(this.context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* JADX INFO: renamed from: getItemCount, reason: from getter */
        public int getAvatarCounts() {
            return this.avatarCounts;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(AvatarViewHolder holder, int position) {
            Intrinsics.checkParameterIsNotNull(holder, "holder");
            View view = holder.itemView;
            if (view == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.profile.ProfileDialog.AvatarView");
            }
            ((AvatarView) view).setPicture(this.avatarUrl + position + ".png");
        }
    }

    /* JADX INFO: compiled from: ProfileDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/ProfileDialog$AvatarViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class AvatarViewHolder extends RecyclerView.ViewHolder {
        private final Context context;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AvatarViewHolder(Context context) {
            super(new AvatarView(context, null, 0, 6, null));
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.context = context;
            View itemView = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(itemView, "itemView");
            itemView.setFocusable(true);
            View itemView2 = this.itemView;
            Intrinsics.checkExpressionValueIsNotNull(itemView2, "itemView");
            itemView2.setFocusableInTouchMode(true);
            this.itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileDialog.AvatarViewHolder.1
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view, boolean z) {
                    Intrinsics.checkExpressionValueIsNotNull(view, "view");
                    view.setSelected(z);
                }
            });
        }

        public final Context getContext() {
            return this.context;
        }
    }

    /* JADX INFO: compiled from: ProfileDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0017\u001a\u00020\u0018H\u0014R \u0010\t\u001a\b\u0018\u00010\nR\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR(\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0019"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/ProfileDialog$AvatarView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "imageContainer", "Lcom/android/volley/toolbox/ImageLoader$ImageContainer;", "Lcom/android/volley/toolbox/ImageLoader;", "getImageContainer", "()Lcom/android/volley/toolbox/ImageLoader$ImageContainer;", "setImageContainer", "(Lcom/android/volley/toolbox/ImageLoader$ImageContainer;)V", "value", "", "picture", "getPicture", "()Ljava/lang/String;", "setPicture", "(Ljava/lang/String;)V", "onDetachedFromWindow", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class AvatarView extends FrameLayout {
        private HashMap _$_findViewCache;
        private ImageLoader.ImageContainer imageContainer;
        private String picture;

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
        public AvatarView(Context context, AttributeSet attributeSet, int i) {
            View viewFindViewById;
            super(context, attributeSet, i);
            Intrinsics.checkParameterIsNotNull(context, "context");
            FrameLayout.inflate(context, R.layout.avatar_view, this);
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
            if (value == null || (viewFindViewById = findViewById(R.id.root)) == null) {
                return;
            }
            DesignManager designManager = value.getDesignManager();
            Drawable foreground = viewFindViewById.getForeground();
            Intrinsics.checkExpressionValueIsNotNull(foreground, "it.foreground");
            viewFindViewById.setForeground(designManager.tintDrawable(foreground, "standard.item.selected.outline.color"));
        }

        public /* synthetic */ AvatarView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(context, (i2 & 2) != 0 ? (AttributeSet) null : attributeSet, (i2 & 4) != 0 ? 0 : i);
        }

        public final ImageLoader.ImageContainer getImageContainer() {
            return this.imageContainer;
        }

        public final void setImageContainer(ImageLoader.ImageContainer imageContainer) {
            this.imageContainer = imageContainer;
        }

        public final String getPicture() {
            return this.picture;
        }

        public final void setPicture(String str) {
            final ImageView imageView;
            this.picture = str;
            if (str == null || (imageView = (ImageView) findViewById(R.id.avatar)) == null) {
                return;
            }
            PortalApplication.Companion companion = PortalApplication.INSTANCE;
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            this.imageContainer = companion.getSingleton(context).getImageLoader().get(str, new ImageLoader.ImageListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileDialog$AvatarView$picture$1$1$1
                @Override // com.android.volley.Response.ErrorListener
                public void onErrorResponse(VolleyError error) {
                    Intrinsics.checkParameterIsNotNull(error, "error");
                }

                @Override // com.android.volley.toolbox.ImageLoader.ImageListener
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                    Intrinsics.checkParameterIsNotNull(response, "response");
                    imageView.setImageBitmap(response.getBitmap());
                }
            });
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            ImageLoader.ImageContainer imageContainer = this.imageContainer;
            if (imageContainer != null) {
                imageContainer.cancelRequest();
            }
        }
    }
}
