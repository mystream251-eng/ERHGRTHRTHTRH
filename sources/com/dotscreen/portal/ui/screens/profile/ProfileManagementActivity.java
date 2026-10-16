package com.dotscreen.portal.ui.screens.profile;

import android.app.ActivityManager;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HorizontalGridPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;
import androidx.lifecycle.LiveData;
import com.dotscreen.bokit.model.User;
import com.dotscreen.bokit.services.PincodeType;
import com.dotscreen.bokit.services.UserService;
import com.dotscreen.bokit.services.UserStatus;
import com.dotscreen.farkit.rx.LifecycleAwareDisposableKt;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.model.BaseWidget;
import com.dotscreen.portal.ui.cards.ProfileCardPresenter;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.portal.ui.views.ProfileView;
import com.dotscreen.portal.utils.Activity_ExtensionKt;
import com.dotscreen.portal.utils.MegaNotificationPublisher;
import com.dotscreen.tv.common.bokit.MegacableUserService;
import com.dotscreen.tv.common.bokit.Profile;
import com.dotscreen.tv.common.bokit.RightVUser;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.dotscreen.tv.ui.dialog.MultipleChoiceDialog;
import com.dotscreen.tv.ui.dialog.PortalDialog;
import com.google.android.gms.common.Scopes;
import com.viaccessorca.analytics.dca.VoDcaController;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.single.SingleJust;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProfileManagementActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0017\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u0015H\u0002J,\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\u001d\u001a\u00020\u00102\b\b\u0002\u0010\u001e\u001a\u00020\u0010H\u0002J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u0010J$\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\"\u001a\u00020\u0010H\u0002J\u0006\u0010#\u001a\u00020\u0015J\u001a\u0010$\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u0010H\u0002J\u001a\u0010%\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001c\u001a\u00020\u0010H\u0002J\u0012\u0010&\u001a\u00020\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u0010H\u0002J\b\u0010'\u001a\u00020\u0015H\u0002J\u0016\u0010(\u001a\u00020\u00152\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u000e0*H\u0002J\u0017\u0010+\u001a\u00020,2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0002¢\u0006\u0002\u0010-J4\u0010.\u001a\u00020\u00122\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u0006002\b\b\u0002\u00101\u001a\u00020\u00102\b\b\u0002\u0010\u001d\u001a\u00020\u00102\b\b\u0002\u0010\u001e\u001a\u00020\u0010H\u0002J-\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u00062\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0002\u00105J\u0012\u00106\u001a\u00020,2\b\b\u0002\u00101\u001a\u00020\u0010H\u0002J\u0012\u00107\u001a\u00020\u00152\b\u0010\u001c\u001a\u0004\u0018\u000108H\u0002J'\u00109\u001a\u00020\u00152\u0006\u0010:\u001a\u00020\u00102\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010!\u001a\u00020\u000eH\u0002¢\u0006\u0002\u0010;J\b\u0010<\u001a\u00020\u0015H\u0016J\u0012\u0010=\u001a\u00020\u00152\b\u0010>\u001a\u0004\u0018\u00010?H\u0014J\u0010\u0010@\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u000eH\u0016J\u0018\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u00122\u0006\u0010C\u001a\u00020\u0006H\u0016J\u001e\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00020\u00122\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00060EH\u0016J\u001f\u0010F\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010GJ\u001f\u0010H\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016¢\u0006\u0002\u0010GJ\b\u0010I\u001a\u00020\u0015H\u0014J\u001a\u0010J\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u0010H\u0002J$\u0010K\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u000e2\b\b\u0002\u0010\u001c\u001a\u00020\u00102\b\b\u0002\u0010\"\u001a\u00020\u0010H\u0002J!\u0010L\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010!\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0002\u0010MJ!\u0010N\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010!\u001a\u0004\u0018\u00010\u000eH\u0002¢\u0006\u0002\u0010MR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006O"}, d2 = {"Lcom/dotscreen/portal/ui/screens/profile/ProfileManagementActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "Lcom/dotscreen/portal/ui/screens/profile/ProfileDialog$OnProfileChangeListener;", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$OnPinCodeListener;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "action", "adapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "currentUser", "Lcom/dotscreen/tv/common/bokit/RightVUser;", "mainProfile", "Lcom/dotscreen/tv/common/bokit/Profile;", "needToChooseProfile", "", "pinCodeDialog", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog;", "preventBack", "addPinCode", "", "index", "", "(Ljava/lang/Integer;)V", "addProfile", "askParentalPinCode", "Lio/reactivex/Single;", "error", "forDeletion", "hideMessage", "askProfilePinCode", "changeProfile", Scopes.PROFILE, "shouldAskPinCode", "createAndRefreshAdapter", "deleteOtherProfile", "deletePinCode", "deleteProfile", "displayProfiles", "extractMainProfile", "profiles", "", "generateAddPinCodeDialog", "Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog;", "(Ljava/lang/Integer;)Lcom/dotscreen/portal/ui/screens/profile/MultiplePinCodeDialog;", "generatePinCodeDialog", "emitter", "Lio/reactivex/SingleEmitter;", "parental", "generateProfileDialog", "Lcom/dotscreen/portal/ui/screens/profile/ProfileDialog;", "title", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/dotscreen/tv/common/bokit/Profile;)Lcom/dotscreen/portal/ui/screens/profile/ProfileDialog;", "generateUpdatePinCodeDialog", "handleError", "", "manageProfile", "isMain", "(ZLjava/lang/Integer;Lcom/dotscreen/tv/common/bokit/Profile;)V", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onNewProfile", "onPinCodeCorrect", "dialog", "pinCode", "pinCodes", "", "onProfileDeleted", "(Lcom/dotscreen/tv/common/bokit/Profile;Ljava/lang/Integer;)V", "onProfileUpdate", "onResume", "removeProfile", "showPopupToMangeProfile", "updatePinCode", "(Ljava/lang/Integer;Lcom/dotscreen/tv/common/bokit/Profile;)V", "updateProfile", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class ProfileManagementActivity extends BaseFragmentActivity implements ProfileDialog.OnProfileChangeListener, PinCodeDialog.OnPinCodeListener {
    private final String TAG = ProfileManagementActivity.class.getSimpleName();
    private HashMap _$_findViewCache;
    private String action;
    private ArrayObjectAdapter adapter;
    private RightVUser currentUser;
    private Profile mainProfile;
    private boolean needToChooseProfile;
    private PinCodeDialog pinCodeDialog;
    private boolean preventBack;

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

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);
        LiveData<PortalApplication.Config> config = PortalApplication.INSTANCE.getSingleton(this).getConfig();
        Intrinsics.checkExpressionValueIsNotNull(config, "PortalApplication.getSingleton(this).config");
        PortalApplication.Config value = config.getValue();
        this.currentUser = value != null ? value.getUser() : null;
        PortalApplication.Companion companion = PortalApplication.INSTANCE;
        Context applicationContext = getApplicationContext();
        Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
        if (companion.getSingleton(applicationContext).getDegradedMode()) {
            Activity_ExtensionKt.launchHome$default(this, null, 1, null);
            Runtime.getRuntime().exit(0);
        } else {
            createAndRefreshAdapter();
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        Application application = getApplication();
        if (application == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
        }
        if (!((PortalApplication) application).getIsSartupScreenAlreadyDisplayed()) {
            Application application2 = getApplication();
            if (application2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            ((PortalApplication) application2).setSartupScreenAlreadyDisplayed(true);
            DSLog.INSTANCE.appStartTimeStamp(getApplication().getClass().getSimpleName() + '@' + getApplication().hashCode() + " > " + this.TAG, true);
        }
        DSLog.INSTANCE.printCurrentRAMUsage((ActivityManager) getSystemService("activity"));
    }

    public final void createAndRefreshAdapter() {
        String queryParameter;
        this.adapter = (ArrayObjectAdapter) null;
        HorizontalGridPresenter horizontalGridPresenter = new HorizontalGridPresenter(1, false);
        horizontalGridPresenter.setNumberOfRows(1);
        horizontalGridPresenter.setShadowEnabled(false);
        setLoaderVisible(true);
        OnItemViewClickedListener onItemViewClickedListener = new OnItemViewClickedListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$createAndRefreshAdapter$manageListener$1
            @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
            public final void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
                if (obj instanceof Profile) {
                    if (this.this$0.currentUser != null) {
                        RightVUser rightVUser = this.this$0.currentUser;
                        if (rightVUser == null) {
                            Intrinsics.throwNpe();
                        }
                        Profile profile = (Profile) obj;
                        if (Intrinsics.areEqual(rightVUser.getCompassId(), profile.getExternalId())) {
                            ProfileManagementActivity profileManagementActivity = this.this$0;
                            RightVUser rightVUser2 = profileManagementActivity.currentUser;
                            if (rightVUser2 == null) {
                                Intrinsics.throwNpe();
                            }
                            boolean isMain = rightVUser2.getIsMain();
                            ArrayObjectAdapter arrayObjectAdapter = this.this$0.adapter;
                            profileManagementActivity.manageProfile(isMain, arrayObjectAdapter != null ? Integer.valueOf(arrayObjectAdapter.indexOf(obj)) : null, profile);
                            return;
                        }
                        ProfileManagementActivity.showPopupToMangeProfile$default(this.this$0, profile, false, false, 6, null);
                        return;
                    }
                    Log.e(this.this$0.TAG, "Error to handle");
                    return;
                }
                if (!(obj instanceof BaseWidget) || !Intrinsics.areEqual(((BaseWidget) obj).getTitle(), "add")) {
                    Log.e(this.this$0.TAG, "Not a profile");
                    Toast.makeText(this.this$0, R.string.noAvailable, 0).show();
                } else {
                    DSLog.INSTANCE.a("Clicked on Add a profil");
                    this.this$0.addProfile();
                }
            }
        };
        OnItemViewClickedListener onItemViewClickedListener2 = new OnItemViewClickedListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$createAndRefreshAdapter$chooseListener$1
            @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
            public final void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
                if (obj instanceof Profile) {
                    String str = this.this$0.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Profile ");
                    Profile profile = (Profile) obj;
                    sb.append(profile.getName());
                    sb.append(" was selected");
                    Log.v(str, sb.toString());
                    DSLog.INSTANCE.a("Profile " + profile.getName() + " was selected");
                    ProfileManagementActivity.changeProfile$default(this.this$0, profile, false, false, 6, null);
                    return;
                }
                Log.e(this.this$0.TAG, "Not a profile");
            }
        };
        this.adapter = new ArrayObjectAdapter(new ProfileCardPresenter(Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.settings_page_card_width)), Integer.valueOf(getResources().getDimensionPixelSize(R.dimen.settings_page_card_width))));
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.browse_grid_dock);
        if (viewGroup != null) {
            HorizontalGridPresenter.ViewHolder viewHolderOnCreateViewHolder = horizontalGridPresenter.onCreateViewHolder(viewGroup);
            viewGroup.addView(viewHolderOnCreateViewHolder.view);
            horizontalGridPresenter.onBindViewHolder(viewHolderOnCreateViewHolder, this.adapter);
            Intent intent = getIntent();
            if (intent != null) {
                OnItemViewClickedListener onItemViewClickedListener3 = (OnItemViewClickedListener) null;
                Uri data = intent.getData();
                if (data != null && (queryParameter = data.getQueryParameter("action")) != null) {
                    this.action = queryParameter;
                    if (Intrinsics.areEqual(queryParameter, "manage") && this.currentUser != null) {
                        TextView textView = (TextView) findViewById(R.id.title);
                        if (textView != null) {
                            textView.setText(getResources().getString(R.string.settings_pageTitle) + " - " + getResources().getString(R.string.setting_item_profiles));
                        }
                    } else if (Intrinsics.areEqual(this.action, "choose") || this.currentUser == null) {
                        TextView textView2 = (TextView) findViewById(R.id.title);
                        if (textView2 != null) {
                            textView2.setText(getResources().getString(R.string.setting_item_profiles));
                        }
                        View viewFindViewById = findViewById(R.id.root);
                        if (viewFindViewById != null) {
                            if (viewFindViewById == null) {
                                throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
                            }
                            ((ViewGroup) viewFindViewById).removeView(findViewById(R.id.dca_view));
                        }
                        ProfileView profileView = (ProfileView) findViewById(R.id.profile_view);
                        if (profileView != null) {
                            profileView.setVisibility(8);
                        }
                        this.preventBack = true;
                        onItemViewClickedListener = onItemViewClickedListener2;
                    } else {
                        onItemViewClickedListener = onItemViewClickedListener3;
                    }
                    onItemViewClickedListener3 = onItemViewClickedListener;
                }
                displayProfiles();
                horizontalGridPresenter.setOnItemViewClickedListener(onItemViewClickedListener3);
            }
        }
    }

    public static /* synthetic */ Single askProfilePinCode$default(ProfileManagementActivity profileManagementActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return profileManagementActivity.askProfilePinCode(z);
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$askProfilePinCode$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProfileManagementActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "emitter", "Lio/reactivex/SingleEmitter;", "", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class C04221<T> implements SingleOnSubscribe<T> {
        final /* synthetic */ boolean $error;

        C04221(boolean z) {
            this.$error = z;
        }

        /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$askProfilePinCode$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: ProfileManagementActivity.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
        static final class RunnableC01421 implements Runnable {
            final /* synthetic */ SingleEmitter $emitter;

            RunnableC01421(SingleEmitter singleEmitter) {
                this.$emitter = singleEmitter;
            }

            @Override // java.lang.Runnable
            public final void run() {
                BehaviorSubject<UserStatus> status;
                PinCodeDialog pinCodeDialog = ProfileManagementActivity.this.pinCodeDialog;
                if (pinCodeDialog != null) {
                    pinCodeDialog.dismiss();
                }
                if (C04221.this.$error && Intrinsics.areEqual(ProfileManagementActivity.this.action, "manage")) {
                    Context applicationContext = ProfileManagementActivity.this.getApplicationContext();
                    if (applicationContext == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                    }
                    UserService userService = ((PortalApplication) applicationContext).getBOService().getUserService();
                    if (userService != null && (status = userService.getStatus()) != null) {
                        status.onNext(new UserStatus.Anonymous());
                    }
                    Dialog dialogCreate = new PortalDialog.Builder(ProfileManagementActivity.this).setTitle(R.string.error_pin_code_wrong_retry).setPositiveButton(R.string.resume_playback_yes, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$askProfilePinCode$1$1$builder$1
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) {
                            ProfileManagementActivity profileManagementActivity = ProfileManagementActivity.this;
                            ProfileManagementActivity profileManagementActivity2 = ProfileManagementActivity.this;
                            SingleEmitter emitter = this.this$0.$emitter;
                            Intrinsics.checkExpressionValueIsNotNull(emitter, "emitter");
                            profileManagementActivity.pinCodeDialog = ProfileManagementActivity.generatePinCodeDialog$default(profileManagementActivity2, emitter, false, false, false, 14, null);
                            PinCodeDialog pinCodeDialog2 = ProfileManagementActivity.this.pinCodeDialog;
                            if (pinCodeDialog2 != null) {
                                pinCodeDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$askProfilePinCode$1$1$builder$1.1
                                    @Override // android.content.DialogInterface.OnDismissListener
                                    public final void onDismiss(DialogInterface dialogInterface2) {
                                        ProfileManagementActivity.this.setLoaderVisible(false);
                                        ProfileManagementActivity.this.setBlockInput(false);
                                    }
                                });
                            }
                            PinCodeDialog pinCodeDialog3 = ProfileManagementActivity.this.pinCodeDialog;
                            if (pinCodeDialog3 != null) {
                                pinCodeDialog3.setError(ProfileManagementActivity.C04221.this.$error);
                            }
                            PinCodeDialog pinCodeDialog4 = ProfileManagementActivity.this.pinCodeDialog;
                            if (pinCodeDialog4 != null) {
                                pinCodeDialog4.show();
                            }
                        }
                    }).setNegativeButton(R.string.resume_playback_no, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$askProfilePinCode$1$1$builder$2
                        @Override // android.content.DialogInterface.OnClickListener
                        public final void onClick(DialogInterface dialogInterface, int i) throws URISyntaxException {
                            ProfileManagementActivity.this.finish();
                            ProfileManagementActivity.this.startActivity(Intent.parseUri("megacable://com.dotscreen.megacable.portal/profile?action=choose", 0));
                        }
                    }).create();
                    dialogCreate.setCancelable(false);
                    dialogCreate.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.askProfilePinCode.1.1.1
                        @Override // android.content.DialogInterface.OnKeyListener
                        public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                            Log.e(ProfileManagementActivity.this.TAG, "ludo " + i);
                            return false;
                        }
                    });
                    Window window = dialogCreate.getWindow();
                    if (window != null) {
                        window.setType(1000);
                    }
                    dialogCreate.show();
                    return;
                }
                ProfileManagementActivity profileManagementActivity = ProfileManagementActivity.this;
                ProfileManagementActivity profileManagementActivity2 = ProfileManagementActivity.this;
                SingleEmitter emitter = this.$emitter;
                Intrinsics.checkExpressionValueIsNotNull(emitter, "emitter");
                profileManagementActivity.pinCodeDialog = ProfileManagementActivity.generatePinCodeDialog$default(profileManagementActivity2, emitter, false, false, false, 14, null);
                PinCodeDialog pinCodeDialog2 = ProfileManagementActivity.this.pinCodeDialog;
                if (pinCodeDialog2 != null) {
                    pinCodeDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.askProfilePinCode.1.1.2
                        @Override // android.content.DialogInterface.OnDismissListener
                        public final void onDismiss(DialogInterface dialogInterface) {
                            ProfileManagementActivity.this.setLoaderVisible(false);
                            ProfileManagementActivity.this.setBlockInput(false);
                        }
                    });
                }
                PinCodeDialog pinCodeDialog3 = ProfileManagementActivity.this.pinCodeDialog;
                if (pinCodeDialog3 != null) {
                    pinCodeDialog3.setError(C04221.this.$error);
                }
                PinCodeDialog pinCodeDialog4 = ProfileManagementActivity.this.pinCodeDialog;
                if (pinCodeDialog4 != null) {
                    pinCodeDialog4.show();
                }
            }
        }

        @Override // io.reactivex.SingleOnSubscribe
        public final void subscribe(SingleEmitter<String> emitter) {
            Intrinsics.checkParameterIsNotNull(emitter, "emitter");
            ProfileManagementActivity.this.runOnUiThread(new RunnableC01421(emitter));
        }
    }

    public final Single<String> askProfilePinCode(boolean error) {
        Single<String> singleCreate = Single.create(new C04221(error));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create<String> { …}\n            }\n        }");
        return singleCreate;
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$askParentalPinCode$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProfileManagementActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "emitter", "Lio/reactivex/SingleEmitter;", "", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
    static final class AnonymousClass1<T> implements SingleOnSubscribe<T> {
        final /* synthetic */ boolean $error;
        final /* synthetic */ boolean $forDeletion;
        final /* synthetic */ boolean $hideMessage;

        AnonymousClass1(boolean z, boolean z2, boolean z3) {
            this.$forDeletion = z;
            this.$hideMessage = z2;
            this.$error = z3;
        }

        /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$askParentalPinCode$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: ProfileManagementActivity.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 16})
        @DebugMetadata(c = "com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$askParentalPinCode$1$1", f = "ProfileManagementActivity.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01401 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ SingleEmitter $emitter;
            int label;
            private CoroutineScope p$;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01401(SingleEmitter singleEmitter, Continuation continuation) {
                super(2, continuation);
                this.$emitter = singleEmitter;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
                Intrinsics.checkParameterIsNotNull(completion, "completion");
                C01401 c01401 = new C01401(this.$emitter, completion);
                c01401.p$ = (CoroutineScope) obj;
                return c01401;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01401) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                PinCodeDialog pinCodeDialog = ProfileManagementActivity.this.pinCodeDialog;
                if (pinCodeDialog != null) {
                    pinCodeDialog.dismiss();
                }
                ProfileManagementActivity profileManagementActivity = ProfileManagementActivity.this;
                ProfileManagementActivity profileManagementActivity2 = ProfileManagementActivity.this;
                SingleEmitter emitter = this.$emitter;
                Intrinsics.checkExpressionValueIsNotNull(emitter, "emitter");
                profileManagementActivity.pinCodeDialog = profileManagementActivity2.generatePinCodeDialog(emitter, true, AnonymousClass1.this.$forDeletion, AnonymousClass1.this.$hideMessage);
                PinCodeDialog pinCodeDialog2 = ProfileManagementActivity.this.pinCodeDialog;
                if (pinCodeDialog2 != null) {
                    pinCodeDialog2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.askParentalPinCode.1.1.1
                        @Override // android.content.DialogInterface.OnDismissListener
                        public final void onDismiss(DialogInterface dialogInterface) {
                            ProfileManagementActivity.this.setLoaderVisible(false);
                            ProfileManagementActivity.this.setBlockInput(false);
                            ProfileManagementActivity.this.pinCodeDialog = (PinCodeDialog) null;
                        }
                    });
                }
                PinCodeDialog pinCodeDialog3 = ProfileManagementActivity.this.pinCodeDialog;
                if (pinCodeDialog3 != null) {
                    pinCodeDialog3.setError(AnonymousClass1.this.$error);
                }
                PinCodeDialog pinCodeDialog4 = ProfileManagementActivity.this.pinCodeDialog;
                if (pinCodeDialog4 != null) {
                    pinCodeDialog4.show();
                }
                return Unit.INSTANCE;
            }
        }

        @Override // io.reactivex.SingleOnSubscribe
        public final void subscribe(SingleEmitter<String> emitter) {
            Intrinsics.checkParameterIsNotNull(emitter, "emitter");
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C01401(emitter, null), 2, null);
        }
    }

    static /* synthetic */ Single askParentalPinCode$default(ProfileManagementActivity profileManagementActivity, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        return profileManagementActivity.askParentalPinCode(z, z2, z3);
    }

    private final Single<String> askParentalPinCode(boolean error, boolean forDeletion, boolean hideMessage) {
        Single<String> singleCreate = Single.create(new AnonymousClass1(forDeletion, hideMessage, error));
        Intrinsics.checkExpressionValueIsNotNull(singleCreate, "Single.create { emitter …)\n            }\n        }");
        return singleCreate;
    }

    static /* synthetic */ PinCodeDialog generatePinCodeDialog$default(ProfileManagementActivity profileManagementActivity, SingleEmitter singleEmitter, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        if ((i & 8) != 0) {
            z3 = false;
        }
        return profileManagementActivity.generatePinCodeDialog(singleEmitter, z, z2, z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PinCodeDialog generatePinCodeDialog(SingleEmitter<String> emitter, boolean parental, boolean forDeletion, boolean hideMessage) {
        PinCodeDialog.Builder builder = new PinCodeDialog.Builder(this);
        if (forDeletion) {
            builder.setTitle(R.string.input_parental_pin_code);
        }
        if (hideMessage) {
            builder.hideDefaultMessage();
        }
        Dialog dialogCreate = builder.setCheckPinCode(false).setOnPinCodeListener((PinCodeDialog.OnPinCodeListener) new C04311(parental, emitter)).create();
        if (dialogCreate != null) {
            return (PinCodeDialog) dialogCreate;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.profile.PinCodeDialog");
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$generatePinCodeDialog$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProfileManagementActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0016¨\u0006\n"}, d2 = {"com/dotscreen/portal/ui/screens/profile/ProfileManagementActivity$generatePinCodeDialog$1", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$OnPinCodeListener;", "onPinCodeCorrect", "", "dialog", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog;", "pinCode", "", "pinCodes", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class C04311 implements PinCodeDialog.OnPinCodeListener {
        final /* synthetic */ SingleEmitter $emitter;
        final /* synthetic */ boolean $parental;

        @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
        public void onPinCodeCorrect(PinCodeDialog dialog, List<String> pinCodes) {
            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
            Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
        }

        C04311(boolean z, SingleEmitter singleEmitter) {
            this.$parental = z;
            this.$emitter = singleEmitter;
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
            Single<Boolean> singleCheckPincode;
            Single<Boolean> singleSubscribeOn;
            Single<Boolean> singleObserveOn;
            Disposable disposableSubscribe;
            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
            Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
            ProfileManagementActivity.this.pinCodeDialog = dialog;
            if (!this.$parental) {
                this.$emitter.onSuccess(pinCode);
                return;
            }
            PortalApplication.Companion companion = PortalApplication.INSTANCE;
            Context baseContext = ProfileManagementActivity.this.getBaseContext();
            Intrinsics.checkExpressionValueIsNotNull(baseContext, "baseContext");
            UserService userService = companion.getSingleton(baseContext).getBOService().getUserService();
            if (userService == null || (singleCheckPincode = userService.checkPincode(null, PincodeType.Parental, pinCode)) == null || (singleSubscribeOn = singleCheckPincode.subscribeOn(Schedulers.io())) == null || (singleObserveOn = singleSubscribeOn.observeOn(AndroidSchedulers.mainThread())) == null || (disposableSubscribe = singleObserveOn.subscribe(new BiConsumer<Boolean, Throwable>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$generatePinCodeDialog$1$onPinCodeCorrect$1
                @Override // io.reactivex.functions.BiConsumer
                public final void accept(Boolean correct, Throwable th) {
                    Intrinsics.checkExpressionValueIsNotNull(correct, "correct");
                    if (correct.booleanValue()) {
                        this.this$0.$emitter.onSuccess("");
                    } else if (th != null) {
                        this.this$0.$emitter.onError(th);
                    } else {
                        this.this$0.$emitter.onError(new Throwable("INVALID_PIN_CODE"));
                    }
                }
            })) == null) {
                return;
            }
            LifecycleAwareDisposableKt.addTo(disposableSubscribe, ProfileManagementActivity.this.getDisposable());
        }
    }

    private final MultiplePinCodeDialog generateAddPinCodeDialog(Integer index) {
        Dialog dialogCreate = new MultiplePinCodeDialog.Builder(this).setEditPinCodeType(MultiplePinCodeDialog.EditPincodeType.CreateProfile).setOnPinCodeListener((PinCodeDialog.OnPinCodeListener) new C04301(index)).create();
        if (dialogCreate != null) {
            return (MultiplePinCodeDialog) dialogCreate;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.profile.MultiplePinCodeDialog");
    }

    /* JADX INFO: renamed from: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$generateAddPinCodeDialog$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: ProfileManagementActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0016¨\u0006\n"}, d2 = {"com/dotscreen/portal/ui/screens/profile/ProfileManagementActivity$generateAddPinCodeDialog$1", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog$OnPinCodeListener;", "onPinCodeCorrect", "", "dialog", "Lcom/dotscreen/portal/ui/screens/profile/PinCodeDialog;", "pinCode", "", "pinCodes", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class C04301 implements PinCodeDialog.OnPinCodeListener {
        final /* synthetic */ Integer $index;

        @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
        public void onPinCodeCorrect(PinCodeDialog dialog, String pinCode) {
            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
            Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
        }

        C04301(Integer num) {
            this.$index = num;
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
        public void onPinCodeCorrect(final PinCodeDialog dialog, List<String> pinCodes) {
            Intrinsics.checkParameterIsNotNull(dialog, "dialog");
            Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
            PortalApplication.Companion companion = PortalApplication.INSTANCE;
            Context context = dialog.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "dialog.context");
            UserService userService = companion.getSingleton(context).getBOService().getUserService();
            if (userService != null) {
                if (userService != null) {
                    MegacableUserService megacableUserService = (MegacableUserService) userService;
                    megacableUserService.cancelProfileCache();
                    Disposable disposableSubscribe = megacableUserService.getGetCurrentProfile().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BiConsumer<Profile, Throwable>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$generateAddPinCodeDialog$1$onPinCodeCorrect$$inlined$let$lambda$1
                        @Override // io.reactivex.functions.BiConsumer
                        public final void accept(Profile profile, Throwable th) {
                            dialog.dismiss();
                            if (th != null) {
                                ProfileManagementActivity.this.handleError(th);
                                return;
                            }
                            ProfileManagementActivity profileManagementActivity = ProfileManagementActivity.this;
                            Intrinsics.checkExpressionValueIsNotNull(profile, "profile");
                            profileManagementActivity.onProfileUpdate(profile, this.this$0.$index);
                        }
                    });
                    Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "userService.getCurrentPr…                        }");
                    LifecycleAwareDisposableKt.addTo(disposableSubscribe, ProfileManagementActivity.this.getDisposable());
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
            }
        }
    }

    static /* synthetic */ MultiplePinCodeDialog generateUpdatePinCodeDialog$default(ProfileManagementActivity profileManagementActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return profileManagementActivity.generateUpdatePinCodeDialog(z);
    }

    private final MultiplePinCodeDialog generateUpdatePinCodeDialog(boolean parental) {
        MultiplePinCodeDialog.Builder builder = new MultiplePinCodeDialog.Builder(this);
        if (parental) {
            builder.setTitle(R.string.edit_parental_pin_code);
            builder.setEditPinCodeType(MultiplePinCodeDialog.EditPincodeType.UpdateParental);
        } else {
            builder.setTitle(R.string.edit_profile_pin_code);
            builder.setEditPinCodeType(MultiplePinCodeDialog.EditPincodeType.UpdateProfile);
        }
        Dialog dialogCreate = builder.setOnPinCodeListener((PinCodeDialog.OnPinCodeListener) this).create();
        if (dialogCreate != null) {
            return (MultiplePinCodeDialog) dialogCreate;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.profile.MultiplePinCodeDialog");
    }

    static /* synthetic */ ProfileDialog generateProfileDialog$default(ProfileManagementActivity profileManagementActivity, String str, Integer num, Profile profile, int i, Object obj) {
        if ((i & 2) != 0) {
            num = (Integer) null;
        }
        if ((i & 4) != 0) {
            profile = (Profile) null;
        }
        return profileManagementActivity.generateProfileDialog(str, num, profile);
    }

    private final ProfileDialog generateProfileDialog(String title, Integer index, Profile profile) {
        Dialog dialogCreate = new ProfileDialog.Builder(this).setTitle(title).setProfileIndex(index).setProfile(profile).setOnProfileChangeListener(this).create();
        if (dialogCreate != null) {
            return (ProfileDialog) dialogCreate;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.profile.ProfileDialog");
    }

    static /* synthetic */ void showPopupToMangeProfile$default(ProfileManagementActivity profileManagementActivity, Profile profile, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        profileManagementActivity.showPopupToMangeProfile(profile, z, z2);
    }

    private final void showPopupToMangeProfile(final Profile profile, final boolean error, final boolean shouldAskPinCode) {
        MultipleChoiceDialog.Builder builder = new MultipleChoiceDialog.Builder(this);
        String string = getString(R.string.user_profile_dialog);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.user_profile_dialog)");
        String str = String.format(string, Arrays.copyOf(new Object[]{profile.getName()}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        MultipleChoiceDialog.Builder title = builder.setTitle(str);
        title.setItemList(CollectionsKt.mutableListOf(getString(R.string.connect_profile), getString(R.string.remove_profile)));
        title.setOnItemClickedListener(new MultipleChoiceDialog.OnItemClickedListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.showPopupToMangeProfile.1
            @Override // com.dotscreen.tv.ui.dialog.MultipleChoiceDialog.OnItemClickedListener
            public void onItemClicked(MultipleChoiceDialog dialog, int itemIndex, Object item) {
                Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                if (item instanceof String) {
                    if (Intrinsics.areEqual(item, ProfileManagementActivity.this.getString(R.string.connect_profile))) {
                        ProfileManagementActivity.this.changeProfile(profile, error, shouldAskPinCode);
                    } else if (Intrinsics.areEqual(item, ProfileManagementActivity.this.getString(R.string.remove_profile))) {
                        if (!profile.isMainProfile()) {
                            ProfileManagementActivity.this.removeProfile(profile, error);
                        } else {
                            Toast.makeText(ProfileManagementActivity.this, R.string.message_for_remove_main, 1).show();
                        }
                    }
                }
                dialog.dismiss();
            }
        });
        title.create().show();
    }

    static /* synthetic */ void removeProfile$default(ProfileManagementActivity profileManagementActivity, Profile profile, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        profileManagementActivity.removeProfile(profile, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeProfile(final Profile profile, final boolean error) {
        PortalDialog.Builder builder = new PortalDialog.Builder(this);
        String string = getString(R.string.user_profile_dialog);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.user_profile_dialog)");
        String str = String.format(string, Arrays.copyOf(new Object[]{profile.getName()}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
        PortalDialog.Builder title = builder.setTitle(str);
        String string2 = getString(R.string.remove_profile_question);
        Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.remove_profile_question)");
        title.setMessage(string2).setPositiveButton(R.string.resume_playback_yes, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.removeProfile.1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ProfileManagementActivity.this.deleteOtherProfile(profile, error);
                dialogInterface.dismiss();
            }
        }).setNegativeButton(R.string.resume_playback_no, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.removeProfile.2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }

    static /* synthetic */ void changeProfile$default(ProfileManagementActivity profileManagementActivity, Profile profile, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        profileManagementActivity.changeProfile(profile, z, z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeProfile(final Profile profile, boolean error, boolean shouldAskPinCode) {
        final SingleJust singleJustAskParentalPinCode$default;
        if (profile.getHasPinCode()) {
            singleJustAskParentalPinCode$default = askProfilePinCode(error);
        } else {
            singleJustAskParentalPinCode$default = (profile.isMainProfile() && shouldAskPinCode) ? askParentalPinCode$default(this, error, false, false, 6, null) : new SingleJust("");
        }
        setLoaderVisible(true);
        setBlockInput(true);
        final UserService userService = PortalApplication.INSTANCE.getSingleton(this).getBOService().getUserService();
        if (userService != null) {
            if (userService == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
            }
            String externalId = profile.getExternalId();
            Single<String> singleDoAfterSuccess = singleJustAskParentalPinCode$default.doAfterSuccess(new Consumer<String>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$changeProfile$$inlined$let$lambda$1
                @Override // io.reactivex.functions.Consumer
                public final void accept(String str) {
                    userService.getActiveUser().subscribe(new Consumer<User>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$changeProfile$$inlined$let$lambda$1.1
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(User user) {
                            MegaNotificationPublisher.Companion companion = MegaNotificationPublisher.INSTANCE;
                            ProfileManagementActivity profileManagementActivity = this;
                            if (user == null) {
                                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVUser");
                            }
                            companion.removeUserNotification(profileManagementActivity, ((RightVUser) user).getCompassId());
                            this.setIntent(new Intent(CommonUtils.ACTION_USER_CHANGED));
                            this.sendBroadcast(this.getIntent());
                        }
                    });
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(singleDoAfterSuccess, "pinCodeObs.doAfterSucces…  }\n                    }");
            Disposable disposableSubscribe = ((MegacableUserService) userService).login(externalId, singleDoAfterSuccess).doAfterSuccess(new Consumer<RightVUser>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$changeProfile$$inlined$let$lambda$2
                @Override // io.reactivex.functions.Consumer
                public final void accept(final RightVUser rightVUser) {
                    ((MegacableUserService) userService).getReminders().subscribe(new BiConsumer<JSONArray, Throwable>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$changeProfile$$inlined$let$lambda$2.1
                        @Override // io.reactivex.functions.BiConsumer
                        public final void accept(JSONArray jSONArray, Throwable th) throws JSONException {
                            if (th != null) {
                                Log.e(this.TAG, "Error while set the reminders", th);
                                return;
                            }
                            Log.d(this.TAG, "Set the current user reminders");
                            int length = jSONArray.length();
                            for (int i = 0; i < length; i++) {
                                Object obj = jSONArray.get(i);
                                if (obj == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type org.json.JSONObject");
                                }
                                JSONObject jSONObject = (JSONObject) obj;
                                MegaNotificationPublisher.Companion companion = MegaNotificationPublisher.INSTANCE;
                                ProfileManagementActivity profileManagementActivity = this;
                                String string = jSONObject.getString("title");
                                Intrinsics.checkExpressionValueIsNotNull(string, "reminder.getString(\"title\")");
                                String string2 = this.getString(R.string.reminder_text_description_notif);
                                Intrinsics.checkExpressionValueIsNotNull(string2, "this.getString(R.string.…r_text_description_notif)");
                                String compassId = rightVUser.getCompassId();
                                StringBuilder sb = new StringBuilder();
                                sb.append("megacable://com.dotscreen.megacable.portal/epg/details?channel_id=");
                                sb.append(jSONObject.getString("channelId"));
                                sb.append("&start=");
                                long j = 1000;
                                sb.append(jSONObject.getLong("startDate") * j);
                                companion.scheduleNotification(profileManagementActivity, string, string2, compassId, sb.toString(), jSONObject.getLong("startDate") * j);
                            }
                        }
                    });
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<RightVUser>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$changeProfile$$inlined$let$lambda$3
                @Override // io.reactivex.functions.Consumer
                public final void accept(RightVUser rightVUser) {
                    try {
                        VoDcaController dcaController = VoDcaController.getDcaController();
                        if (rightVUser == null) {
                            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.RightVUser");
                        }
                        dcaController.setAccountID(rightVUser.getId());
                        VoDcaController.getDcaController().setProfileID(profile.getExternalId());
                        PinCodeDialog pinCodeDialog = this.this$0.pinCodeDialog;
                        if (pinCodeDialog != null) {
                            pinCodeDialog.dismiss();
                        }
                        this.this$0.needToChooseProfile = false;
                        Log.i(this.this$0.TAG, "Login Successfully");
                        this.this$0.finish();
                        Activity_ExtensionKt.launchHome$default(this.this$0, null, 1, null);
                        this.this$0.setLoaderVisible(false);
                        this.this$0.setBlockInput(false);
                    } catch (Exception unused) {
                    }
                }
            }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$changeProfile$$inlined$let$lambda$4
                /* JADX WARN: Code restructure failed: missing block: B:13:0x005b, code lost:
                
                    if (kotlin.jvm.internal.Intrinsics.areEqual(((com.dotscreen.bokit.BackendErrorException) r0).getBackendCode(), "INVALID_PIN_CODE") != false) goto L29;
                 */
                @Override // io.reactivex.functions.Consumer
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void accept(java.lang.Throwable r10) {
                    /*
                        r9 = this;
                        com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity r0 = r9.this$0
                        java.lang.String r0 = com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.access$getTAG$p(r0)
                        java.lang.StringBuilder r1 = new java.lang.StringBuilder
                        r1.<init>()
                        java.lang.String r2 = "Error: "
                        r1.append(r2)
                        r1.append(r10)
                        java.lang.String r1 = r1.toString()
                        android.util.Log.e(r0, r1)
                        com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity r0 = r9.this$0
                        r1 = 0
                        r0.setLoaderVisible(r1)
                        com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity r0 = r9.this$0
                        r0.setBlockInput(r1)
                        java.lang.String r0 = r10.getMessage()
                        java.lang.String r1 = "INVALID_PIN_CODE"
                        boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                        r2 = 1
                        if (r0 != 0) goto L90
                        boolean r0 = r10 instanceof com.dotscreen.bokit.BackendErrorException
                        if (r0 == 0) goto L43
                        r0 = r10
                        com.dotscreen.bokit.BackendErrorException r0 = (com.dotscreen.bokit.BackendErrorException) r0
                        java.lang.String r0 = r0.getBackendCode()
                        boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                        if (r0 != 0) goto L90
                    L43:
                        java.lang.Throwable r0 = r10.getCause()
                        boolean r0 = r0 instanceof com.dotscreen.bokit.BackendErrorException
                        if (r0 == 0) goto L66
                        java.lang.Throwable r0 = r10.getCause()
                        if (r0 == 0) goto L5e
                        com.dotscreen.bokit.BackendErrorException r0 = (com.dotscreen.bokit.BackendErrorException) r0
                        java.lang.String r0 = r0.getBackendCode()
                        boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                        if (r0 == 0) goto L66
                        goto L90
                    L5e:
                        kotlin.TypeCastException r10 = new kotlin.TypeCastException
                        java.lang.String r0 = "null cannot be cast to non-null type com.dotscreen.bokit.BackendErrorException"
                        r10.<init>(r0)
                        throw r10
                    L66:
                        boolean r0 = r10 instanceof com.android.volley.VolleyError
                        if (r0 != 0) goto L79
                        boolean r0 = r10 instanceof java.net.SocketTimeoutException
                        if (r0 != 0) goto L79
                        boolean r0 = r10 instanceof java.io.InterruptedIOException
                        if (r0 == 0) goto L73
                        goto L79
                    L73:
                        com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity r0 = r9.this$0
                        com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.access$handleError(r0, r10)
                        goto Lc3
                    L79:
                        com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity r10 = r9.this$0
                        com.dotscreen.portal.ui.screens.profile.PinCodeDialog r10 = com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.access$getPinCodeDialog$p(r10)
                        if (r10 == 0) goto L84
                        r10.dismiss()
                    L84:
                        com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity r10 = r9.this$0
                        r10.finish()
                        com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity r10 = r9.this$0
                        r0 = 0
                        com.dotscreen.portal.utils.Activity_ExtensionKt.launchHome$default(r10, r0, r2, r0)
                        goto Lc3
                    L90:
                        com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity r10 = r9.this$0
                        java.lang.String r10 = com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.access$getAction$p(r10)
                        java.lang.String r0 = "manage"
                        boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r0)
                        if (r10 == 0) goto La3
                        com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity r10 = r9.this$0
                        com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.access$setNeedToChooseProfile$p(r10, r2)
                    La3:
                        com.dotscreen.tv.common.bokit.Profile r10 = r2
                        boolean r10 = r10.isMainProfile()
                        if (r10 != 0) goto Lb8
                        com.dotscreen.tv.common.bokit.Profile r10 = r2
                        boolean r10 = r10.getHasPinCode()
                        if (r10 != 0) goto Lb8
                        com.dotscreen.tv.common.bokit.Profile r10 = r2
                        r10.setHasPinCode(r2)
                    Lb8:
                        com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity r3 = r9.this$0
                        com.dotscreen.tv.common.bokit.Profile r4 = r2
                        r5 = 1
                        r6 = 0
                        r7 = 4
                        r8 = 0
                        com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.changeProfile$default(r3, r4, r5, r6, r7, r8)
                    Lc3:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$changeProfile$$inlined$let$lambda$4.accept(java.lang.Throwable):void");
                }
            });
            Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "(userService as Megacabl… }\n                    })");
            LifecycleAwareDisposableKt.addTo(disposableSubscribe, getDisposable());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addProfile() {
        String string = getString(R.string.add_profile);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.add_profile)");
        generateProfileDialog$default(this, string, null, null, 6, null).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateProfile(Integer index, Profile profile) {
        Log.d(this.TAG, "Will update profile " + index);
        String string = getString(R.string.update_profile);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.update_profile)");
        generateProfileDialog(string, index, profile).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updatePinCode(Integer index, Profile profile) {
        Log.d(this.TAG, "Will update pin code " + index);
        if (profile == null) {
            Intrinsics.throwNpe();
        }
        generateUpdatePinCodeDialog(profile.isMainProfile()).show();
    }

    static /* synthetic */ void deleteProfile$default(ProfileManagementActivity profileManagementActivity, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        profileManagementActivity.deleteProfile(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteProfile(final boolean error) {
        Disposable disposableSubscribe = askParentalPinCode$default(this, error, true, false, 4, null).flatMap(new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.deleteProfile.1
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                PinCodeDialog pinCodeDialog = ProfileManagementActivity.this.pinCodeDialog;
                if (pinCodeDialog != null) {
                    pinCodeDialog.dismiss();
                }
                Context applicationContext = ProfileManagementActivity.this.getApplicationContext();
                if (applicationContext == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                }
                UserService userService = ((PortalApplication) applicationContext).getBOService().getUserService();
                if (userService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                }
                return ((MegacableUserService) userService).deleteProfile();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<JSONObject>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.deleteProfile.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(JSONObject jSONObject) {
                Profile profile = ProfileManagementActivity.this.mainProfile;
                if (profile != null) {
                    ProfileManagementActivity.changeProfile$default(ProfileManagementActivity.this, profile, false, false, 2, null);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.deleteProfile.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.e(ProfileManagementActivity.this.TAG, "Error: " + error);
                String message = th.getMessage();
                if (message != null && message.hashCode() == -527589409 && message.equals("INVALID_PIN_CODE")) {
                    ProfileManagementActivity.this.deleteProfile(true);
                } else {
                    ProfileManagementActivity.this.handleError(th);
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "askParentalPinCode(error…     }\n                })");
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, getDisposable());
    }

    static /* synthetic */ void deleteOtherProfile$default(ProfileManagementActivity profileManagementActivity, Profile profile, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        profileManagementActivity.deleteOtherProfile(profile, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void deleteOtherProfile(final Profile profile, final boolean error) {
        Disposable disposableSubscribe = askParentalPinCode(error, true, true).flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.deleteOtherProfile.1
            @Override // io.reactivex.functions.Function
            public final Single<JSONObject> apply(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                PinCodeDialog pinCodeDialog = ProfileManagementActivity.this.pinCodeDialog;
                if (pinCodeDialog != null) {
                    pinCodeDialog.dismiss();
                }
                Context applicationContext = ProfileManagementActivity.this.getApplicationContext();
                if (applicationContext == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                }
                UserService userService = ((PortalApplication) applicationContext).getBOService().getUserService();
                if (userService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                }
                return ((MegacableUserService) userService).deleteOtherProfile(profile.getId());
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<JSONObject>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.deleteOtherProfile.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(JSONObject jSONObject) {
                Log.i(ProfileManagementActivity.this.TAG, "Remove Profile Successfully");
                Log.i(ProfileManagementActivity.this.TAG, jSONObject.toString());
                if (jSONObject.optString(NotificationCompat.CATEGORY_STATUS).equals("SUCCESS")) {
                    ProfileManagementActivity.this.onProfileDeleted(profile, 0);
                }
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.deleteOtherProfile.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.e(ProfileManagementActivity.this.TAG, "Error: " + error);
                String message = th.getMessage();
                if (message != null && message.hashCode() == -527589409 && message.equals("INVALID_PIN_CODE")) {
                    ProfileManagementActivity.this.deleteProfile(true);
                } else {
                    ProfileManagementActivity.this.handleError(th);
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "askParentalPinCode(error…     }\n                })");
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, getDisposable());
    }

    static /* synthetic */ void deletePinCode$default(ProfileManagementActivity profileManagementActivity, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        profileManagementActivity.deletePinCode(i, z);
    }

    private final void deletePinCode(final int index, boolean error) {
        Disposable disposableSubscribe = askProfilePinCode(error).flatMap((Function) new Function<T, SingleSource<? extends R>>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.deletePinCode.1
            @Override // io.reactivex.functions.Function
            public final Single<Profile> apply(String it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                PinCodeDialog pinCodeDialog = ProfileManagementActivity.this.pinCodeDialog;
                if (pinCodeDialog != null) {
                    pinCodeDialog.dismiss();
                }
                Context applicationContext = ProfileManagementActivity.this.getApplicationContext();
                if (applicationContext == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
                }
                UserService userService = ((PortalApplication) applicationContext).getBOService().getUserService();
                if (userService == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
                }
                return ((MegacableUserService) userService).removeProfilePinCode(it);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Profile>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.deletePinCode.2
            @Override // io.reactivex.functions.Consumer
            public final void accept(Profile it) {
                ProfileManagementActivity profileManagementActivity = ProfileManagementActivity.this;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                profileManagementActivity.onProfileUpdate(it, Integer.valueOf(index));
            }
        }, new Consumer<Throwable>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.deletePinCode.3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Throwable th) {
                Log.e(ProfileManagementActivity.this.TAG, "Error: " + th);
                ProfileManagementActivity.this.handleError(th);
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "askProfilePinCode(error)…ocess)\n                })");
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, getDisposable());
    }

    private final void displayProfiles() {
        UserService userService;
        final ArrayObjectAdapter arrayObjectAdapter = this.adapter;
        if (arrayObjectAdapter == null || (userService = PortalApplication.INSTANCE.getSingleton(this).getBOService().getUserService()) == null) {
            return;
        }
        if (userService == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.bokit.MegacableUserService");
        }
        Disposable disposableSubscribe = ((MegacableUserService) userService).profiles(Intrinsics.areEqual(this.action, "choose")).toList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new BiConsumer<List<Profile>, Throwable>() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity$displayProfiles$$inlined$let$lambda$1
            @Override // io.reactivex.functions.BiConsumer
            public final void accept(List<Profile> list, Throwable th) {
                if (th != null) {
                    Log.e(this.TAG, "Error on getProfilesList: " + th.getMessage());
                    this.finish();
                    Activity_ExtensionKt.launchHome$default(this, null, 1, null);
                    return;
                }
                if (list == null) {
                    Intrinsics.throwNpe();
                }
                List<Profile> list2 = list;
                if (true ^ list2.isEmpty()) {
                    arrayObjectAdapter.addAll(0, list2);
                    this.extractMainProfile(list);
                    RightVUser rightVUser = this.currentUser;
                    if (rightVUser != null && Intrinsics.areEqual(this.action, "manage") && rightVUser.getIsMain()) {
                        arrayObjectAdapter.add(new BaseWidget("add", null, false, null, 14, null));
                    }
                    ViewGroup viewGroup = (ViewGroup) this.findViewById(R.id.browse_grid_dock);
                    if (viewGroup != null) {
                        viewGroup.requestFocus();
                    }
                    this.setLoaderVisible(false);
                }
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(disposableSubscribe, "(userService as Megacabl…                        }");
        LifecycleAwareDisposableKt.addTo(disposableSubscribe, getDisposable());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void extractMainProfile(List<Profile> profiles) {
        for (Profile profile : profiles) {
            if (profile.isMainProfile()) {
                this.mainProfile = profile;
                return;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleError(Throwable error) {
        if (error != null) {
            PinCodeDialog pinCodeDialog = this.pinCodeDialog;
            if (pinCodeDialog != null) {
                pinCodeDialog.dismiss();
            }
            BaseFragmentActivity.displayErrorMessage$default(this, error, 0, 2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void manageProfile(boolean isMain, final Integer index, final Profile profile) {
        String string;
        MultipleChoiceDialog.Builder builder = new MultipleChoiceDialog.Builder(this);
        if (isMain) {
            string = getString(R.string.main_profile_dialog);
        } else {
            String string2 = getString(R.string.user_profile_dialog);
            Intrinsics.checkExpressionValueIsNotNull(string2, "getString(R.string.user_profile_dialog)");
            string = String.format(string2, Arrays.copyOf(new Object[]{profile.getName()}, 1));
            Intrinsics.checkNotNullExpressionValue(string, "java.lang.String.format(this, *args)");
        }
        Intrinsics.checkExpressionValueIsNotNull(string, "if (isMain) getString(R.…log).format(profile.name)");
        MultipleChoiceDialog.Builder title = builder.setTitle(string);
        List<String> listMutableListOf = CollectionsKt.mutableListOf(getString(R.string.update_profile));
        if (isMain) {
            listMutableListOf.add(getString(R.string.edit_parental_pin_code));
        } else if (profile.getHasPinCode()) {
            listMutableListOf.add(getString(R.string.edit_profile_pin_code));
            listMutableListOf.add(getString(R.string.delete_profile_pin_code));
        } else {
            listMutableListOf.add(getString(R.string.add_profile_pin_code));
        }
        if (!isMain) {
            listMutableListOf.add(getString(R.string.delete_profile));
        }
        title.setItemList(listMutableListOf);
        title.setOnItemClickedListener(new MultipleChoiceDialog.OnItemClickedListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.manageProfile.1
            @Override // com.dotscreen.tv.ui.dialog.MultipleChoiceDialog.OnItemClickedListener
            public void onItemClicked(MultipleChoiceDialog dialog, int itemIndex, Object item) {
                Intrinsics.checkParameterIsNotNull(dialog, "dialog");
                if (item instanceof String) {
                    if (Intrinsics.areEqual(item, ProfileManagementActivity.this.getString(R.string.update_profile))) {
                        ProfileManagementActivity.this.updateProfile(index, profile);
                    } else if (Intrinsics.areEqual(item, ProfileManagementActivity.this.getString(R.string.add_profile_pin_code))) {
                        ProfileManagementActivity.this.addPinCode(index);
                    } else if (Intrinsics.areEqual(item, ProfileManagementActivity.this.getString(R.string.edit_profile_pin_code)) || Intrinsics.areEqual(item, ProfileManagementActivity.this.getString(R.string.edit_parental_pin_code))) {
                        ProfileManagementActivity.this.updatePinCode(index, profile);
                    } else if (Intrinsics.areEqual(item, ProfileManagementActivity.this.getString(R.string.delete_profile_pin_code))) {
                        ProfileManagementActivity profileManagementActivity = ProfileManagementActivity.this;
                        Integer num = index;
                        if (num == null) {
                            Intrinsics.throwNpe();
                        }
                        ProfileManagementActivity.deletePinCode$default(profileManagementActivity, num.intValue(), false, 2, null);
                    } else if (Intrinsics.areEqual(item, ProfileManagementActivity.this.getString(R.string.delete_profile))) {
                        ProfileManagementActivity.deleteProfile$default(ProfileManagementActivity.this, false, 1, null);
                    }
                }
                dialog.dismiss();
            }
        });
        if (!isMain) {
            if (profile.getHasPinCode()) {
                title.setNeutralButton(R.string.delete_profile_pin_code, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.manageProfile.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        ProfileManagementActivity profileManagementActivity = ProfileManagementActivity.this;
                        Integer num = index;
                        if (num == null) {
                            Intrinsics.throwNpe();
                        }
                        ProfileManagementActivity.deletePinCode$default(profileManagementActivity, num.intValue(), false, 2, null);
                        dialogInterface.dismiss();
                    }
                });
            }
            title.setNegativeButton(R.string.delete_profile, new DialogInterface.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.profile.ProfileManagementActivity.manageProfile.3
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    ProfileManagementActivity.deleteProfile$default(ProfileManagementActivity.this, false, 1, null);
                    dialogInterface.dismiss();
                }
            });
        }
        title.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addPinCode(Integer index) {
        Log.d(this.TAG, "Will add pin code " + index);
        generateAddPinCodeDialog(index).show();
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.preventBack) {
            return;
        }
        super.onBackPressed();
    }

    @Override // com.dotscreen.portal.ui.screens.profile.ProfileDialog.OnProfileChangeListener
    public void onNewProfile(Profile profile) {
        Intrinsics.checkParameterIsNotNull(profile, "profile");
        ArrayObjectAdapter arrayObjectAdapter = this.adapter;
        if (arrayObjectAdapter != null) {
            if (arrayObjectAdapter == null) {
                Intrinsics.throwNpe();
            }
            arrayObjectAdapter.add(RangesKt.coerceAtLeast(0, arrayObjectAdapter.size() - 1), profile);
        }
    }

    @Override // com.dotscreen.portal.ui.screens.profile.ProfileDialog.OnProfileChangeListener
    public void onProfileUpdate(Profile profile, Integer index) {
        Intrinsics.checkParameterIsNotNull(profile, "profile");
        ArrayObjectAdapter arrayObjectAdapter = this.adapter;
        if (arrayObjectAdapter != null) {
            if (index == null) {
                Intrinsics.throwNpe();
            }
            arrayObjectAdapter.replace(index.intValue(), profile);
        }
    }

    @Override // com.dotscreen.portal.ui.screens.profile.ProfileDialog.OnProfileChangeListener
    public void onProfileDeleted(Profile profile, Integer index) {
        Intrinsics.checkParameterIsNotNull(profile, "profile");
        ArrayObjectAdapter arrayObjectAdapter = this.adapter;
        if (arrayObjectAdapter != null) {
            arrayObjectAdapter.remove(profile);
        }
    }

    @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
    public void onPinCodeCorrect(PinCodeDialog dialog, String pinCode) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        Intrinsics.checkParameterIsNotNull(pinCode, "pinCode");
        dialog.dismiss();
    }

    @Override // com.dotscreen.portal.ui.screens.profile.PinCodeDialog.OnPinCodeListener
    public void onPinCodeCorrect(PinCodeDialog dialog, List<String> pinCodes) {
        Intrinsics.checkParameterIsNotNull(dialog, "dialog");
        Intrinsics.checkParameterIsNotNull(pinCodes, "pinCodes");
        dialog.dismiss();
    }
}
