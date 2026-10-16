package com.dotscreen.portal.ui.screens.setup;

import android.app.Dialog;
import android.content.Context;
import android.os.RemoteException;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import com.dotscreen.farkit.tools.DSLog;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.PortalApplication;
import com.dotscreen.portal.utils.AudioZTEManager;
import com.dotscreen.portal.utils.AudioZTEManagerKt;
import com.dotscreen.tv.ui.dialog.PortalDialog;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import megacable.settings.aidl.AudioInterface;

/* JADX INFO: compiled from: AudioDialogZTE.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0002\u000e\u000fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/AudioDialogZTE;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "themeResId", "", "(Landroid/content/Context;I)V", "selectorColor", "title", "", "getTitle", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "Builder", "Companion", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class AudioDialogZTE extends Dialog {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = AudioDialogZTE.class.getSimpleName();
    private int selectorColor;
    private String title;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioDialogZTE(Context context, int i) {
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

    protected final String getTitle() {
        return this.title;
    }

    protected final void setTitle(String str) {
        this.title = str;
    }

    /* JADX INFO: compiled from: AudioDialogZTE.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/AudioDialogZTE$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "getTAG", "()Ljava/lang/String;", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getTAG() {
            return AudioDialogZTE.TAG;
        }
    }

    /* JADX INFO: compiled from: AudioDialogZTE.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0016\u0010\u0019\u001a\u00020\u001a2\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010J\b\u0010\u001b\u001a\u00020\u001aH\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0004\n\u0002\u0010\u000eR\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\fX\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001c"}, d2 = {"Lcom/dotscreen/portal/ui/screens/setup/AudioDialogZTE$Builder;", "Lcom/dotscreen/tv/ui/dialog/PortalDialog$InternalBuilder;", "context", "Landroid/content/Context;", "audioManager", "Lcom/dotscreen/portal/utils/AudioZTEManager;", "(Landroid/content/Context;Lcom/dotscreen/portal/utils/AudioZTEManager;)V", "contentViewId", "", "getContentViewId", "()I", "radioBtns", "", "Landroid/widget/RadioButton;", "[Landroid/widget/RadioButton;", "refresh", "Lkotlin/Function0;", "", "values", "", "getValues", "()[Ljava/lang/String;", "setValues", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "create", "Landroid/app/Dialog;", "createDialog", "MegaCable-Portal-0.75.1(396)_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static class Builder extends PortalDialog.InternalBuilder<Builder> {
        private final AudioZTEManager audioManager;
        private RadioButton[] radioBtns;
        private Function0<Unit> refresh;
        private String[] values;

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected int getContentViewId() {
            return R.layout.dialog_audio_zte;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(Context context, AudioZTEManager audioManager) {
            super(context);
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(audioManager, "audioManager");
            this.audioManager = audioManager;
        }

        public final String[] getValues() {
            return this.values;
        }

        public final void setValues(String[] strArr) {
            this.values = strArr;
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected Dialog createDialog() {
            return new AudioDialogZTE(getContext(), 2131951633);
        }

        public final Dialog create(Function0<Unit> refresh) {
            this.refresh = refresh;
            return create();
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        public Dialog create() {
            String[] strArr;
            RadioButton[] radioButtonArr;
            AudioInterface audioService;
            AudioInterface audioService2;
            Dialog dialogCreate = super.create();
            if (dialogCreate == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.portal.ui.screens.setup.AudioDialogZTE");
            }
            final AudioDialogZTE audioDialogZTE = (AudioDialogZTE) dialogCreate;
            if (AudioZTEManager.INSTANCE.isZtePlatform2()) {
                strArr = new String[]{"auto", AudioZTEManagerKt.DIGITAL_SOUND_PCM};
            } else {
                strArr = new String[]{"auto", AudioZTEManagerKt.DIGITAL_SOUND_PCM, AudioZTEManagerKt.DIGITAL_SOUND_SPDIF};
            }
            this.values = strArr;
            int i = 0;
            if (AudioZTEManager.INSTANCE.isZtePlatform2()) {
                View viewFindViewById = audioDialogZTE.findViewById(R.id.btn_spdif);
                if (viewFindViewById == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.widget.RadioButton");
                }
                ((RadioButton) viewFindViewById).setVisibility(8);
                View viewFindViewById2 = audioDialogZTE.findViewById(R.id.btn_auto);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "dialog.findViewById(R.id.btn_auto)");
                View viewFindViewById3 = audioDialogZTE.findViewById(R.id.btn_pcm);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "dialog.findViewById(R.id.btn_pcm)");
                radioButtonArr = new RadioButton[]{(RadioButton) viewFindViewById2, (RadioButton) viewFindViewById3};
            } else {
                View viewFindViewById4 = audioDialogZTE.findViewById(R.id.btn_auto);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById4, "dialog.findViewById(R.id.btn_auto)");
                View viewFindViewById5 = audioDialogZTE.findViewById(R.id.btn_pcm);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById5, "dialog.findViewById(R.id.btn_pcm)");
                View viewFindViewById6 = audioDialogZTE.findViewById(R.id.btn_spdif);
                Intrinsics.checkExpressionValueIsNotNull(viewFindViewById6, "dialog.findViewById(R.id.btn_spdif)");
                radioButtonArr = new RadioButton[]{(RadioButton) viewFindViewById4, (RadioButton) viewFindViewById5, (RadioButton) viewFindViewById6};
            }
            this.radioBtns = radioButtonArr;
            AudioZTEManager audioZTEManager = this.audioManager;
            System.out.println((Object) ((audioZTEManager == null || (audioService2 = audioZTEManager.getAudioService()) == null) ? null : audioService2.getDigitalAudioFormat()));
            String[] strArr2 = this.values;
            if (strArr2 != null) {
                int length = strArr2.length;
                int i2 = 0;
                int i3 = 0;
                while (i2 < length) {
                    String str = strArr2[i2];
                    int i4 = i3 + 1;
                    AudioZTEManager audioZTEManager2 = this.audioManager;
                    if (Intrinsics.areEqual(str, (audioZTEManager2 == null || (audioService = audioZTEManager2.getAudioService()) == null) ? null : audioService.getDigitalAudioFormat())) {
                        RadioButton[] radioButtonArr2 = this.radioBtns;
                        if (radioButtonArr2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("radioBtns");
                        }
                        radioButtonArr2[i3].setChecked(true);
                    }
                    i2++;
                    i3 = i4;
                }
            }
            RadioButton[] radioButtonArr3 = this.radioBtns;
            if (radioButtonArr3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("radioBtns");
            }
            int length2 = radioButtonArr3.length;
            final int i5 = 0;
            while (i < length2) {
                radioButtonArr3[i].setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.portal.ui.screens.setup.AudioDialogZTE$Builder$create$$inlined$forEachIndexed$lambda$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) throws RemoteException {
                        DSLog.INSTANCE.a("Selected one radio button");
                        AudioZTEManager audioZTEManager3 = this.audioManager;
                        String[] values = this.getValues();
                        if (values == null) {
                            Intrinsics.throwNpe();
                        }
                        audioZTEManager3.setDigitalAudioFormat(values[i5]);
                        Function0 function0 = this.refresh;
                        if (function0 != null) {
                        }
                        audioDialogZTE.dismiss();
                    }
                });
                i++;
                i5++;
            }
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            Window window = audioDialogZTE.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "dialog.window");
            layoutParams.copyFrom(window.getAttributes());
            layoutParams.height = -1;
            layoutParams.width = getContext().getResources().getDimensionPixelSize(R.dimen.right_popup_width);
            layoutParams.gravity = 5;
            Window window2 = audioDialogZTE.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window2, "dialog.window");
            window2.setAttributes(layoutParams);
            return audioDialogZTE;
        }
    }
}
