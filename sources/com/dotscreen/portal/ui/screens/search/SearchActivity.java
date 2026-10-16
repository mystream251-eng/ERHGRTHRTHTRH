package com.dotscreen.portal.ui.screens.search;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.dotscreen.bokit.model.Content;
import com.dotscreen.farkit.design.DesignManager;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.ui.screens.Activity_StartIntentKt;
import com.dotscreen.portal.ui.screens.BaseFragmentActivity;
import com.dotscreen.tv.common.utils.CommonUtils;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: SearchActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000O\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\f\u0018\u00002\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\u0012\u0010\u0016\u001a\u00020\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/dotscreen/portal/ui/screens/search/SearchActivity;", "Lcom/dotscreen/portal/ui/screens/BaseFragmentActivity;", "()V", "MSG_START_SEARCH", "", "TAG", "", "kotlin.jvm.PlatformType", "TRIGGER_AUTO_COMPLETE", "autoSuggestAdapter", "Lcom/dotscreen/portal/ui/screens/search/SearchActivity$AutoSuggestAdapter;", "handler", "com/dotscreen/portal/ui/screens/search/SearchActivity$handler$1", "Lcom/dotscreen/portal/ui/screens/search/SearchActivity$handler$1;", "inputTextView", "Landroid/widget/EditText;", "searchDisposable", "Lio/reactivex/disposables/Disposable;", "searchFragment", "Lcom/dotscreen/portal/ui/screens/search/SearchFragment;", "buildImeCompletions", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "shouldDisplayVoiceButton", "", "AutoSuggestAdapter", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SearchActivity extends BaseFragmentActivity {
    private HashMap _$_findViewCache;
    private AutoSuggestAdapter autoSuggestAdapter;
    private EditText inputTextView;
    private Disposable searchDisposable;
    private SearchFragment searchFragment;
    private final String TAG = SearchActivity.class.getSimpleName();
    private final int MSG_START_SEARCH = 1;
    private final int TRIGGER_AUTO_COMPLETE = 2;
    private final SearchActivity$handler$1 handler = new SearchActivity$handler$1(this);

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

    private final boolean shouldDisplayVoiceButton() {
        switch (CommonUtils.getCurrentPlatform()) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                Intrinsics.checkExpressionValueIsNotNull(adapter, "adapter");
                for (BluetoothDevice device : adapter.getBondedDevices()) {
                    Intrinsics.checkExpressionValueIsNotNull(device, "device");
                    if (Intrinsics.areEqual(device.getName(), "Megacable")) {
                        return true;
                    }
                }
                return false;
            default:
                return false;
        }
    }

    @Override // com.dotscreen.portal.ui.screens.BaseFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        View view;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Fragment fragmentFindFragmentByTag = getSupportFragmentManager().findFragmentByTag("search_fragment");
        if (fragmentFindFragmentByTag == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.search.SearchFragment");
        }
        SearchFragment searchFragment = (SearchFragment) fragmentFindFragmentByTag;
        this.searchFragment = searchFragment;
        if (searchFragment != null && (view = searchFragment.getView()) != null) {
            view.setVisibility(8);
        }
        View viewFindViewById = findViewById(R.id.voice_button);
        if (viewFindViewById != null) {
            viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.search.SearchActivity$onCreate$$inlined$let$lambda$1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    DSLog.INSTANCE.a("Clicked on Voice button");
                    Intent intentPutExtra = new Intent("android.intent.action.ASSIST").putExtra("search_type", 1);
                    Intrinsics.checkExpressionValueIsNotNull(intentPutExtra, "Intent(Intent.ACTION_ASS…utExtra(\"search_type\", 1)");
                    Activity_StartIntentKt.startIntent(this.this$0, intentPutExtra);
                }
            });
            Context applicationContext = getApplicationContext();
            if (applicationContext == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value = ((PortalApplication) applicationContext).getConfig().getValue();
            if (value != null && Build.VERSION.SDK_INT >= 26) {
                DesignManager designManager = value.getDesignManager();
                Drawable foreground = viewFindViewById.getForeground();
                Intrinsics.checkExpressionValueIsNotNull(foreground, "it.foreground");
                viewFindViewById.setForeground(designManager.tintDrawable(foreground, "standard.item.selected.outline.color"));
            }
            if (!shouldDisplayVoiceButton()) {
                viewFindViewById.setVisibility(4);
                EditText editText = (EditText) findViewById(R.id.edit_text);
                if (editText != null) {
                    editText.requestFocus();
                }
            }
        }
        final EditText editText2 = (EditText) findViewById(R.id.edit_text);
        if (editText2 != null) {
            this.inputTextView = editText2;
            editText2.addTextChangedListener(new TextWatcher() { // from class: com.dotscreen.portal.ui.screens.search.SearchActivity$onCreate$$inlined$let$lambda$2
                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence seq, int start, int before, int count) {
                    View view2;
                    if (seq == null || seq.length() < 3) {
                        return;
                    }
                    SearchFragment searchFragment2 = this.this$0.searchFragment;
                    if (searchFragment2 != null && (view2 = searchFragment2.getView()) != null) {
                        view2.setVisibility(0);
                    }
                    this.this$0.handler.removeMessages(this.this$0.TRIGGER_AUTO_COMPLETE);
                    this.this$0.handler.sendMessageDelayed(this.this$0.handler.obtainMessage(this.this$0.TRIGGER_AUTO_COMPLETE, seq.toString()), 300L);
                    this.this$0.handler.removeMessages(this.this$0.MSG_START_SEARCH);
                    this.this$0.handler.sendMessageDelayed(this.this$0.handler.obtainMessage(this.this$0.MSG_START_SEARCH, seq.toString()), 500L);
                }
            });
            Context applicationContext2 = getApplicationContext();
            if (applicationContext2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.PortalApplication");
            }
            PortalApplication.Config value2 = ((PortalApplication) applicationContext2).getConfig().getValue();
            if (value2 != null && Build.VERSION.SDK_INT >= 26) {
                DesignManager designManager2 = value2.getDesignManager();
                Drawable foreground2 = editText2.getForeground();
                Intrinsics.checkExpressionValueIsNotNull(foreground2, "edit.foreground");
                editText2.setForeground(designManager2.tintDrawable(foreground2, "standard.item.selected.outline.color"));
            }
            editText2.setOnKeyListener(new View.OnKeyListener() { // from class: com.dotscreen.portal.ui.screens.search.SearchActivity$onCreate$$inlined$let$lambda$3
                @Override // android.view.View.OnKeyListener
                public final boolean onKey(View view2, int i, KeyEvent keyEvent) {
                    Log.d(this.this$0.TAG, "OnKeyListener: " + keyEvent);
                    return false;
                }
            });
            editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.dotscreen.portal.ui.screens.search.SearchActivity$onCreate$$inlined$let$lambda$4
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    Log.d(this.TAG, "OnFocusChangeListener: state " + z);
                    if (Intrinsics.areEqual(view2, editText2)) {
                        if (z) {
                            Object systemService = this.getApplicationContext().getSystemService("input_method");
                            if (systemService == null) {
                                throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                            }
                            ((InputMethodManager) systemService).showSoftInput(editText2, 2);
                            return;
                        }
                        Object systemService2 = this.getApplicationContext().getSystemService("input_method");
                        if (systemService2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                        }
                        ((InputMethodManager) systemService2).hideSoftInputFromWindow(editText2.getWindowToken(), 0);
                    }
                }
            });
            editText2.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.dotscreen.portal.ui.screens.search.SearchActivity$onCreate$$inlined$let$lambda$5
                @Override // android.view.View.OnApplyWindowInsetsListener
                public final WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                    Log.d(this.this$0.TAG, "OnApplyWindowInsetsListener: state " + windowInsets);
                    return windowInsets;
                }
            });
            editText2.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.dotscreen.portal.ui.screens.search.SearchActivity$onCreate$$inlined$let$lambda$6
                @Override // android.widget.TextView.OnEditorActionListener
                public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    Log.d(this.this$0.TAG, "EditorAction: state " + i + ", " + keyEvent);
                    return false;
                }
            });
            this.autoSuggestAdapter = new AutoSuggestAdapter(this, android.R.layout.simple_dropdown_item_1line);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void buildImeCompletions() {
        if (this.autoSuggestAdapter != null) {
            Object systemService = getSystemService("input_method");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
            }
            InputMethodManager inputMethodManager = (InputMethodManager) systemService;
            AutoSuggestAdapter autoSuggestAdapter = this.autoSuggestAdapter;
            if (autoSuggestAdapter == null) {
                Intrinsics.throwNpe();
            }
            int iCoerceAtMost = RangesKt.coerceAtMost(autoSuggestAdapter.getCount(), 20);
            CompletionInfo[] completionInfoArr = new CompletionInfo[iCoerceAtMost];
            int i = 0;
            for (int i2 = 0; i2 < iCoerceAtMost; i2++) {
                AutoSuggestAdapter autoSuggestAdapter2 = this.autoSuggestAdapter;
                if (autoSuggestAdapter2 == null) {
                    Intrinsics.throwNpe();
                }
                if (autoSuggestAdapter2.isEnabled(i2)) {
                    AutoSuggestAdapter autoSuggestAdapter3 = this.autoSuggestAdapter;
                    if (autoSuggestAdapter3 == null) {
                        Intrinsics.throwNpe();
                    }
                    String item = autoSuggestAdapter3.getItem(i2);
                    AutoSuggestAdapter autoSuggestAdapter4 = this.autoSuggestAdapter;
                    if (autoSuggestAdapter4 == null) {
                        Intrinsics.throwNpe();
                    }
                    completionInfoArr[i] = new CompletionInfo(autoSuggestAdapter4.getItemId(i2), i, item);
                    i++;
                }
            }
            if (i != iCoerceAtMost) {
                CompletionInfo[] completionInfoArr2 = new CompletionInfo[i];
                System.arraycopy(completionInfoArr, 0, completionInfoArr2, 0, i);
                completionInfoArr = completionInfoArr2;
            }
            inputMethodManager.displayCompletions(this.inputTextView, completionInfoArr);
        }
    }

    /* JADX INFO: compiled from: SearchActivity.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\f\u001a\u00020\u0007H\u0016J\u0010\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u0007H\u0016J\u0016\u0010\u000f\u001a\u00020\u00102\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0012R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/dotscreen/portal/ui/screens/search/SearchActivity$AutoSuggestAdapter;", "Landroid/widget/ArrayAdapter;", "", "Landroid/widget/Filterable;", "context", "Landroid/content/Context;", "resource", "", "(Landroid/content/Context;I)V", "mlistData", "", "Lcom/dotscreen/bokit/model/Content;", "getCount", "getItem", "position", "setData", "", "list", "", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class AutoSuggestAdapter extends ArrayAdapter<String> implements Filterable {
        private final List<Content> mlistData;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AutoSuggestAdapter(Context context, int i) {
            super(context, i);
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.mlistData = new ArrayList();
        }

        public final void setData(List<? extends Content> list) {
            this.mlistData.clear();
            List<Content> list2 = this.mlistData;
            if (list == null) {
                Intrinsics.throwNpe();
            }
            list2.addAll(list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public int getCount() {
            return this.mlistData.size();
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public String getItem(int position) {
            String title = this.mlistData.get(position).getTitle();
            if (title == null) {
                Intrinsics.throwNpe();
            }
            return title;
        }
    }
}
