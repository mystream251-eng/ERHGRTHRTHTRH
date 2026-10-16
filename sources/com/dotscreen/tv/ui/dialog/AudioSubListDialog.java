package com.dotscreen.tv.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.android.tv.common.R;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioSubListDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0002\u001b\u001cB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\"\u0010\u0007\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013R$\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0011\"\u0004\b\u0019\u0010\u0013R\u0016\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/dotscreen/tv/ui/dialog/AudioSubListDialog;", "Landroid/app/Dialog;", "context", "Landroid/content/Context;", "themeResId", "", "(Landroid/content/Context;I)V", "audios", "", "Lkotlin/Pair;", "", "", "defaultSubtitleOnCheckedChangeListener", "Landroid/widget/RadioGroup$OnCheckedChangeListener;", "value", "selectedAudioItem", "getSelectedAudioItem", "()I", "setSelectedAudioItem", "(I)V", "selectedItem", "getSelectedItem", "setSelectedItem", "selectedSubtitleItem", "getSelectedSubtitleItem", "setSelectedSubtitleItem", "subtitles", "Builder", "OnItemClickedListener", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class AudioSubListDialog extends Dialog {
    private List<Pair<String, Boolean>> audios;
    private RadioGroup.OnCheckedChangeListener defaultSubtitleOnCheckedChangeListener;
    private int selectedAudioItem;
    private int selectedItem;
    private int selectedSubtitleItem;
    private List<String> subtitles;

    /* JADX INFO: compiled from: AudioSubListDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lcom/dotscreen/tv/ui/dialog/AudioSubListDialog$OnItemClickedListener;", "", "onItemClicked", "", "dialog", "Lcom/dotscreen/tv/ui/dialog/AudioSubListDialog;", "item_type", "", "itemIndex", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public interface OnItemClickedListener {
        void onItemClicked(AudioSubListDialog dialog, int item_type, int itemIndex);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AudioSubListDialog(Context context, int i) {
        super(context, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    public final int getSelectedItem() {
        return this.selectedItem;
    }

    public final void setSelectedItem(int i) {
        this.selectedItem = i;
    }

    public final int getSelectedSubtitleItem() {
        return this.selectedSubtitleItem;
    }

    public final void setSelectedSubtitleItem(int i) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.subtitle_container);
        if (radioGroup != null) {
            Log.d("SubtitleDialog", "will check " + i);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.dotscreen.tv.ui.dialog.AudioSubListDialog$selectedSubtitleItem$1$1
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public final void onCheckedChanged(RadioGroup radioGroup2, int i2) {
                }
            });
            radioGroup.check(i);
            this.selectedSubtitleItem = i;
            radioGroup.setOnCheckedChangeListener(this.defaultSubtitleOnCheckedChangeListener);
        }
    }

    public final int getSelectedAudioItem() {
        return this.selectedAudioItem;
    }

    public final void setSelectedAudioItem(int i) {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.audio_container);
        if (radioGroup != null) {
            radioGroup.check(i);
            this.selectedAudioItem = i;
        }
    }

    /* JADX INFO: compiled from: AudioSubListDialog.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0014J*\u0010\u0018\u001a\u00020\u00002\u0018\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\n2\b\b\u0002\u0010\u001a\u001a\u00020\u0006J\u001e\u0010\u001b\u001a\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\f0\n2\b\b\u0002\u0010\u001a\u001a\u00020\u0006J\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\rJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\rR\u0014\u0010\u0005\u001a\u00020\u00068TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/dotscreen/tv/ui/dialog/AudioSubListDialog$Builder;", "Lcom/dotscreen/tv/ui/dialog/PortalDialog$InternalBuilder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "contentViewId", "", "getContentViewId", "()I", "itemAudioList", "", "Lkotlin/Pair;", "", "", "itemSubtitleList", "onItemClickedListener", "Lcom/dotscreen/tv/ui/dialog/AudioSubListDialog$OnItemClickedListener;", "selectedAudioItem", "selectedSubtitleItem", "showMasInfo", "showSubscribePackage", "create", "Landroid/app/Dialog;", "createDialog", "setItemAudioList", "items", "pos", "setItemSubtitleList", "setMasInfobutton", "show", "setOnItemClickedListener", "setSubscribePackageButton", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static class Builder extends PortalDialog.InternalBuilder<Builder> {
        private List<Pair<String, Boolean>> itemAudioList;
        private List<String> itemSubtitleList;
        private OnItemClickedListener onItemClickedListener;
        private int selectedAudioItem;
        private int selectedSubtitleItem;
        private boolean showMasInfo;
        private boolean showSubscribePackage;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Builder(Context context) {
            super(context);
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.showMasInfo = true;
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected int getContentViewId() {
            return R.layout.dialog_audio_subtitle;
        }

        @Override // com.dotscreen.tv.ui.dialog.PortalDialog.InternalBuilder
        protected Dialog createDialog() {
            return new AudioSubListDialog(getContext(), R.style.AppTheme_DialogTranslucent);
        }

        public final Builder setMasInfobutton(boolean show) {
            this.showMasInfo = show;
            return this;
        }

        public final Builder setSubscribePackageButton(boolean show) {
            this.showSubscribePackage = show;
            return this;
        }

        public static /* synthetic */ Builder setItemAudioList$default(Builder builder, List list, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setItemAudioList");
            }
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return builder.setItemAudioList(list, i);
        }

        public final Builder setItemAudioList(List<Pair<String, Boolean>> items, int pos) {
            Intrinsics.checkParameterIsNotNull(items, "items");
            this.itemAudioList = items;
            this.selectedAudioItem = pos;
            return this;
        }

        public static /* synthetic */ Builder setItemSubtitleList$default(Builder builder, List list, int i, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setItemSubtitleList");
            }
            if ((i2 & 2) != 0) {
                i = 0;
            }
            return builder.setItemSubtitleList(list, i);
        }

        public final Builder setItemSubtitleList(List<String> items, int pos) {
            Intrinsics.checkParameterIsNotNull(items, "items");
            this.itemSubtitleList = items;
            this.selectedSubtitleItem = pos;
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
                throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.ui.dialog.AudioSubListDialog");
            }
            final AudioSubListDialog audioSubListDialog = (AudioSubListDialog) dialogCreate;
            TextView textView = (TextView) audioSubListDialog.findViewById(R.id.see_detail_button);
            int i = 0;
            if (textView != null) {
                textView.setVisibility(this.showMasInfo ? 0 : 8);
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.tv.ui.dialog.AudioSubListDialog$Builder$create$$inlined$let$lambda$1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AudioSubListDialog.OnItemClickedListener onItemClickedListener = this.this$0.onItemClickedListener;
                        if (onItemClickedListener != null) {
                            onItemClickedListener.onItemClicked(audioSubListDialog, 0, 0);
                        }
                    }
                });
                if (this.showMasInfo && !this.showSubscribePackage) {
                    textView.requestFocus();
                }
            }
            TextView textView2 = (TextView) audioSubListDialog.findViewById(R.id.see_package_page_button);
            if (textView2 != null) {
                textView2.setVisibility(this.showSubscribePackage ? 0 : 8);
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.tv.ui.dialog.AudioSubListDialog$Builder$create$$inlined$let$lambda$2
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AudioSubListDialog.OnItemClickedListener onItemClickedListener = this.this$0.onItemClickedListener;
                        if (onItemClickedListener != null) {
                            onItemClickedListener.onItemClicked(audioSubListDialog, 3, 0);
                        }
                    }
                });
                if (this.showSubscribePackage) {
                    textView2.requestFocus();
                }
            }
            final List<Pair<String, Boolean>> list = this.itemAudioList;
            if (list != null) {
                audioSubListDialog.audios = list;
                final RadioGroup radioGroup = (RadioGroup) audioSubListDialog.findViewById(R.id.audio_container);
                if (radioGroup != null) {
                    Iterator<T> it = list.iterator();
                    final int i2 = 0;
                    while (it.hasNext()) {
                        Pair pair = (Pair) it.next();
                        View viewInflate = audioSubListDialog.getLayoutInflater().inflate(R.layout.dialog_icon_radio_button_item, (ViewGroup) null);
                        if (viewInflate == null) {
                            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
                        }
                        LinearLayout linearLayout = (LinearLayout) viewInflate;
                        RadioButton check = (RadioButton) linearLayout.findViewById(R.id.radio);
                        Intrinsics.checkExpressionValueIsNotNull(check, "check");
                        check.setId(i2);
                        if (((Boolean) pair.getSecond()).booleanValue()) {
                            View viewFindViewById = linearLayout.findViewById(R.id.icon_view);
                            Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "button.findViewById<ImageView>(R.id.icon_view)");
                            ((ImageView) viewFindViewById).setVisibility(0);
                        }
                        TextView textView3 = (TextView) linearLayout.findViewById(R.id.item_name);
                        if (textView3 != null) {
                            textView3.setText((CharSequence) pair.getFirst());
                        }
                        linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.dotscreen.tv.ui.dialog.AudioSubListDialog$Builder$create$3$1$1
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                radioGroup.check(i2);
                            }
                        });
                        radioGroup.addView(linearLayout, new ViewGroup.LayoutParams(-1, getContext().getResources().getDimensionPixelSize(R.dimen.dialog_list_item_height)));
                        if (i2 == this.selectedAudioItem && !this.showMasInfo) {
                            linearLayout.requestFocus();
                        }
                        i2++;
                    }
                    radioGroup.check(this.selectedAudioItem);
                    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.dotscreen.tv.ui.dialog.AudioSubListDialog$Builder$create$$inlined$let$lambda$3
                        @Override // android.widget.RadioGroup.OnCheckedChangeListener
                        public final void onCheckedChanged(RadioGroup radioGroup2, int i3) {
                            audioSubListDialog.setSelectedItem(i3);
                            AudioSubListDialog.OnItemClickedListener onItemClickedListener = this.onItemClickedListener;
                            if (onItemClickedListener != null) {
                                onItemClickedListener.onItemClicked(audioSubListDialog, 1, i3);
                            }
                        }
                    });
                }
            }
            final List<String> list2 = this.itemSubtitleList;
            if (list2 != null) {
                audioSubListDialog.subtitles = list2;
                RadioGroup radioGroup2 = (RadioGroup) audioSubListDialog.findViewById(R.id.subtitle_container);
                if (radioGroup2 != null) {
                    for (String str : list2) {
                        View viewInflate2 = audioSubListDialog.getLayoutInflater().inflate(R.layout.dialog_radio_button_item, (ViewGroup) null);
                        if (viewInflate2 == null) {
                            throw new TypeCastException("null cannot be cast to non-null type android.widget.RadioButton");
                        }
                        RadioButton radioButton = (RadioButton) viewInflate2;
                        radioButton.setId(i);
                        radioButton.setText(str);
                        radioGroup2.addView(radioButton, new ViewGroup.LayoutParams(-1, getContext().getResources().getDimensionPixelSize(R.dimen.dialog_list_item_height)));
                        i++;
                    }
                    radioGroup2.check(this.selectedSubtitleItem);
                    audioSubListDialog.defaultSubtitleOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() { // from class: com.dotscreen.tv.ui.dialog.AudioSubListDialog$Builder$create$$inlined$let$lambda$4
                        @Override // android.widget.RadioGroup.OnCheckedChangeListener
                        public final void onCheckedChanged(RadioGroup radioGroup3, int i3) {
                            audioSubListDialog.setSelectedItem(i3);
                            AudioSubListDialog.OnItemClickedListener onItemClickedListener = this.onItemClickedListener;
                            if (onItemClickedListener != null) {
                                onItemClickedListener.onItemClicked(audioSubListDialog, 2, i3);
                            }
                        }
                    };
                    radioGroup2.setOnCheckedChangeListener(audioSubListDialog.defaultSubtitleOnCheckedChangeListener);
                }
            }
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(audioSubListDialog.getWindow().getAttributes());
            layoutParams.height = -1;
            layoutParams.width = getContext().getResources().getDimensionPixelSize(R.dimen.right_popup_width);
            layoutParams.gravity = 5;
            Window window = audioSubListDialog.getWindow();
            Intrinsics.checkExpressionValueIsNotNull(window, "dialog.window");
            window.setAttributes(layoutParams);
            return audioSubListDialog;
        }
    }
}
