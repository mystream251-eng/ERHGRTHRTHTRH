package com.dotscreen.portal.ui.screens.profile;

import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.leanback.widget.VerticalGridView;
import com.dotscreen.megacable.portal.R;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiplePinCodeDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
final class MultiplePinCodeDialog$Builder$MultipleCheckPinCodeTask$onPostExecute$2 implements Runnable {
    final /* synthetic */ Pair $result;
    final /* synthetic */ MultiplePinCodeDialog.Builder.MultipleCheckPinCodeTask this$0;

    MultiplePinCodeDialog$Builder$MultipleCheckPinCodeTask$onPostExecute$2(MultiplePinCodeDialog.Builder.MultipleCheckPinCodeTask multipleCheckPinCodeTask, Pair pair) {
        this.this$0 = multipleCheckPinCodeTask;
        this.$result = pair;
    }

    @Override // java.lang.Runnable
    public final void run() {
        View viewFindViewById = this.this$0.getDialog().findViewById(R.id.root);
        if (viewFindViewById != null) {
            SpringAnimation springAnimation = new SpringAnimation(viewFindViewById, DynamicAnimation.TRANSLATION_X);
            springAnimation.setMaxValue(100.0f);
            springAnimation.setStartValue(100.0f);
            springAnimation.setSpring(new SpringForce());
            SpringForce spring = springAnimation.getSpring();
            if (spring != null) {
                spring.setFinalPosition(0.0f);
            }
            SpringForce spring2 = springAnimation.getSpring();
            if (spring2 != null) {
                spring2.setDampingRatio(0.2f);
            }
            SpringForce spring3 = springAnimation.getSpring();
            if (spring3 != null) {
                spring3.setStiffness(1500.0f);
            }
            springAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: com.dotscreen.portal.ui.screens.profile.MultiplePinCodeDialog$Builder$MultipleCheckPinCodeTask$onPostExecute$2$$special$$inlined$let$lambda$1
                @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
                public final void onAnimationEnd(DynamicAnimation<DynamicAnimation<?>> dynamicAnimation, boolean z, float f, float f2) {
                    ProgressBar progressBar = (ProgressBar) this.this$0.this$0.getDialog().findViewById(R.id.progress);
                    if (progressBar != null) {
                        VerticalGridView verticalGridView = this.this$0.this$0.this$0.verticalItemList;
                        if (verticalGridView != null) {
                            verticalGridView.setVisibility(0);
                        }
                        progressBar.setVisibility(4);
                    }
                    PinCodeDialog.OnPinCodeListener onPinCodeListener = this.this$0.this$0.getBuilder().getOnPinCodeListener();
                    if (onPinCodeListener != null) {
                        Log.v(PinCodeDialog.INSTANCE.getTAG(), String.valueOf(this.this$0.$result.getSecond()));
                        onPinCodeListener.onPinCodeInvalid(this.this$0.this$0.getDialog(), this.this$0.this$0.getDialog().getAllPinCodeValues(), (Exception) this.this$0.$result.getSecond());
                        this.this$0.this$0.getDialog().setError(true);
                        View viewFindViewById2 = this.this$0.this$0.getDialog().findViewById(R.id.error_message);
                        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "dialog.findViewById<TextView>(R.id.error_message)");
                        ((TextView) viewFindViewById2).setText(this.this$0.this$0.this$0.getErrorMessageAndResetInputs(this.this$0.this$0.getDialog(), (Exception) this.this$0.$result.getSecond()));
                    }
                    LinearLayout deleteImage = this.this$0.this$0.this$0.getDeleteImage();
                    if (deleteImage == null) {
                        Intrinsics.throwNpe();
                    }
                    deleteImage.setVisibility(8);
                }
            });
            springAnimation.start();
        }
    }
}
