package androidx.leanback.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.dotscreen.megacable.portal.R;

/* JADX INFO: loaded from: classes.dex */
public final class VerticalListRowView extends LinearLayout {
    private VerticalGridView mGridView;

    public VerticalListRowView(Context context) {
        this(context, null);
    }

    public VerticalListRowView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VerticalListRowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        LayoutInflater.from(context).inflate(R.layout.lb_vertical_list_row, this);
        VerticalGridView verticalGridView = (VerticalGridView) findViewById(R.id.row_content);
        this.mGridView = verticalGridView;
        verticalGridView.setHasFixedSize(false);
        setOrientation(0);
        setDescendantFocusability(262144);
    }

    public VerticalGridView getGridView() {
        return this.mGridView;
    }
}
