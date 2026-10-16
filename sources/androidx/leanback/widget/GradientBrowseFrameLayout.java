package androidx.leanback.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import androidx.core.view.ViewCompat;

/* JADX INFO: loaded from: classes.dex */
class GradientBrowseFrameLayout extends BrowseFrameLayout {
    public GradientBrowseFrameLayout(Context context) {
        this(context, null);
    }

    public GradientBrowseFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GradientBrowseFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        int width = getWidth();
        Rect rect = new Rect(width / 2, 0, width, getHeight());
        LinearGradient linearGradient = new LinearGradient(rect.left, rect.top, rect.right, rect.top, new int[]{ViewCompat.MEASURED_STATE_MASK, 0}, (float[]) null, Shader.TileMode.CLAMP);
        PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        Paint paint = new Paint(1);
        paint.setShader(linearGradient);
        paint.setXfermode(porterDuffXfermode);
        int iSaveLayer = canvas.saveLayer(0.0f, 0.0f, getWidth(), getHeight(), null, 31);
        super.dispatchDraw(canvas);
        canvas.drawRect(rect, paint);
        canvas.restoreToCount(iSaveLayer);
    }
}
