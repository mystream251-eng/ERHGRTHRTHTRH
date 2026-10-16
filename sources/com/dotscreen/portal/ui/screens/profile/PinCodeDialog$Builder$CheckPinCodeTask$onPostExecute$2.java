package com.dotscreen.portal.ui.screens.profile;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import com.dotscreen.megacable.portal.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PinCodeDialog.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
final class PinCodeDialog$Builder$CheckPinCodeTask$onPostExecute$2 implements Runnable {
    final /* synthetic */ PinCodeDialog.Builder.CheckPinCodeTask this$0;

    PinCodeDialog$Builder$CheckPinCodeTask$onPostExecute$2(PinCodeDialog.Builder.CheckPinCodeTask checkPinCodeTask) {
        this.this$0 = checkPinCodeTask;
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
            springAnimation.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: com.dotscreen.portal.ui.screens.profile.PinCodeDialog$Builder$CheckPinCodeTask$onPostExecute$2$$special$$inlined$let$lambda$1
                @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
                public final void onAnimationEnd(DynamicAnimation<DynamicAnimation<?>> dynamicAnimation, boolean z, float f, float f2) {
                    ProgressBar progressBar = (ProgressBar) this.this$0.this$0.getDialog().findViewById(R.id.progress);
                    if (progressBar != null) {
                        progressBar.setVisibility(4);
                    }
                    PinCodeDialog.OnPinCodeListener onPinCodeListener = this.this$0.this$0.getBuilder().getOnPinCodeListener();
                    if (onPinCodeListener != null) {
                        onPinCodeListener.onPinCodeInvalid(this.this$0.this$0.getDialog(), this.this$0.this$0.getDialog().getPinCode());
                        this.this$0.this$0.getDialog().setPinCode("");
                        this.this$0.this$0.getDialog().setError(true);
                    }
                    this.this$0.this$0.getDialog().setPinCode("");
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
