package androidx.leanback.widget;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
class ItemAlignment {
    public final Axis horizontal;
    private Axis mMainAxis;
    private Axis mSecondAxis;
    private int mOrientation = 0;
    public final Axis vertical = new Axis(1);

    ItemAlignment() {
        Axis axis = new Axis(0);
        this.horizontal = axis;
        this.mMainAxis = axis;
        this.mSecondAxis = this.vertical;
    }

    static final class Axis extends ItemAlignmentFacet.ItemAlignmentDef {
        private int mOrientation;

        Axis(int i) {
            this.mOrientation = i;
        }

        public int getAlignmentPosition(View view) {
            return ItemAlignmentFacetHelper.getAlignmentPosition(view, this, this.mOrientation);
        }
    }

    public final Axis mainAxis() {
        return this.mMainAxis;
    }

    public final Axis secondAxis() {
        return this.mSecondAxis;
    }

    public final void setOrientation(int i) {
        this.mOrientation = i;
        if (i == 0) {
            this.mMainAxis = this.horizontal;
            this.mSecondAxis = this.vertical;
        } else {
            this.mMainAxis = this.vertical;
            this.mSecondAxis = this.horizontal;
        }
    }

    public final int getOrientation() {
        return this.mOrientation;
    }
}
