package com.dotscreen.portal.components;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.dotscreen.megacable.portal.R;

/* JADX INFO: loaded from: classes.dex */
public class PointPagerIndexView extends ImageView {
    public PointPagerIndexView(Context context) {
        super(context);
        setUnselected();
    }

    public PointPagerIndexView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setUnselected();
    }

    public PointPagerIndexView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setUnselected();
    }

    public void setSelected() {
        setImageResource(R.drawable.point_on);
    }

    public void setUnselected() {
        setImageResource(R.drawable.point_off);
    }
}
