package androidx.leanback.widget;

/* JADX INFO: loaded from: classes.dex */
class WindowAlignment {
    public final Axis horizontal;
    private Axis mMainAxis;
    private Axis mSecondAxis;
    private int mOrientation = 0;
    public final Axis vertical = new Axis("vertical");

    WindowAlignment() {
        Axis axis = new Axis("horizontal");
        this.horizontal = axis;
        this.mMainAxis = axis;
        this.mSecondAxis = this.vertical;
    }

    public static class Axis {
        static final int PF_KEYLINE_OVER_HIGH_EDGE = 2;
        static final int PF_KEYLINE_OVER_LOW_EDGE = 1;
        private int mMaxEdge;
        private int mMaxScroll;
        private int mMinEdge;
        private int mMinScroll;
        private String mName;
        private int mPaddingMax;
        private int mPaddingMin;
        private boolean mReversedFlow;
        private int mSize;
        private int mPreferredKeyLine = 2;
        private int mWindowAlignment = 3;
        private int mWindowAlignmentOffset = 0;
        private float mWindowAlignmentOffsetPercent = 50.0f;

        final int calculateScrollToKeyLine(int i, int i2) {
            return i - i2;
        }

        public Axis(String str) {
            reset();
            this.mName = str;
        }

        public final int getWindowAlignment() {
            return this.mWindowAlignment;
        }

        public final void setWindowAlignment(int i) {
            this.mWindowAlignment = i;
        }

        final void setPreferKeylineOverLowEdge(boolean z) {
            this.mPreferredKeyLine = z ? this.mPreferredKeyLine | 1 : this.mPreferredKeyLine & (-2);
        }

        final void setPreferKeylineOverHighEdge(boolean z) {
            this.mPreferredKeyLine = z ? this.mPreferredKeyLine | 2 : this.mPreferredKeyLine & (-3);
        }

        final boolean isPreferKeylineOverHighEdge() {
            return (this.mPreferredKeyLine & 2) != 0;
        }

        final boolean isPreferKeylineOverLowEdge() {
            return (this.mPreferredKeyLine & 1) != 0;
        }

        public final int getWindowAlignmentOffset() {
            return this.mWindowAlignmentOffset;
        }

        public final void setWindowAlignmentOffset(int i) {
            this.mWindowAlignmentOffset = i;
        }

        public final void setWindowAlignmentOffsetPercent(float f) {
            if ((f < 0.0f || f > 100.0f) && f != -1.0f) {
                throw new IllegalArgumentException();
            }
            this.mWindowAlignmentOffsetPercent = f;
        }

        public final float getWindowAlignmentOffsetPercent() {
            return this.mWindowAlignmentOffsetPercent;
        }

        public final int getMinScroll() {
            return this.mMinScroll;
        }

        public final void invalidateScrollMin() {
            this.mMinEdge = Integer.MIN_VALUE;
            this.mMinScroll = Integer.MIN_VALUE;
        }

        public final int getMaxScroll() {
            return this.mMaxScroll;
        }

        public final void invalidateScrollMax() {
            this.mMaxEdge = Integer.MAX_VALUE;
            this.mMaxScroll = Integer.MAX_VALUE;
        }

        void reset() {
            this.mMinEdge = Integer.MIN_VALUE;
            this.mMaxEdge = Integer.MAX_VALUE;
        }

        public final boolean isMinUnknown() {
            return this.mMinEdge == Integer.MIN_VALUE;
        }

        public final boolean isMaxUnknown() {
            return this.mMaxEdge == Integer.MAX_VALUE;
        }

        public final void setSize(int i) {
            this.mSize = i;
        }

        public final int getSize() {
            return this.mSize;
        }

        public final void setPadding(int i, int i2) {
            this.mPaddingMin = i;
            this.mPaddingMax = i2;
        }

        public final int getPaddingMin() {
            return this.mPaddingMin;
        }

        public final int getPaddingMax() {
            return this.mPaddingMax;
        }

        public final int getClientSize() {
            return (this.mSize - this.mPaddingMin) - this.mPaddingMax;
        }

        final int calculateKeyline() {
            if (!this.mReversedFlow) {
                int i = this.mWindowAlignmentOffset;
                if (i < 0) {
                    i += this.mSize;
                }
                float f = this.mWindowAlignmentOffsetPercent;
                return f != -1.0f ? i + ((int) ((this.mSize * f) / 100.0f)) : i;
            }
            int i2 = this.mWindowAlignmentOffset;
            int i3 = i2 >= 0 ? this.mSize - i2 : -i2;
            float f2 = this.mWindowAlignmentOffsetPercent;
            return f2 != -1.0f ? i3 - ((int) ((this.mSize * f2) / 100.0f)) : i3;
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
        
            r4.mMinScroll = r4.mMinEdge - r4.mPaddingMin;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0048, code lost:
        
            r4.mMaxScroll = (r4.mMaxEdge - r4.mPaddingMin) - r5;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void updateMinMax(int r5, int r6, int r7, int r8) {
            /*
                Method dump skipped, instruction units count: 231
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.leanback.widget.WindowAlignment.Axis.updateMinMax(int, int, int, int):void");
        }

        public final int getScroll(int i) {
            int i2;
            int i3;
            int size = getSize();
            int iCalculateKeyline = calculateKeyline();
            boolean zIsMinUnknown = isMinUnknown();
            boolean zIsMaxUnknown = isMaxUnknown();
            if (!zIsMinUnknown) {
                int i4 = iCalculateKeyline - this.mPaddingMin;
                if (this.mReversedFlow ? (this.mWindowAlignment & 2) != 0 : (this.mWindowAlignment & 1) != 0) {
                    int i5 = this.mMinEdge;
                    if (i - i5 <= i4) {
                        int i6 = i5 - this.mPaddingMin;
                        return (zIsMaxUnknown || i6 <= (i3 = this.mMaxScroll)) ? i6 : i3;
                    }
                }
            }
            if (!zIsMaxUnknown) {
                int i7 = (size - iCalculateKeyline) - this.mPaddingMax;
                if (this.mReversedFlow ? (this.mWindowAlignment & 1) != 0 : (this.mWindowAlignment & 2) != 0) {
                    int i8 = this.mMaxEdge;
                    if (i8 - i <= i7) {
                        int i9 = i8 - (size - this.mPaddingMax);
                        return (zIsMinUnknown || i9 >= (i2 = this.mMinScroll)) ? i9 : i2;
                    }
                }
            }
            return calculateScrollToKeyLine(i, iCalculateKeyline);
        }

        public final void setReversedFlow(boolean z) {
            this.mReversedFlow = z;
        }

        public String toString() {
            return " min:" + this.mMinEdge + " " + this.mMinScroll + " max:" + this.mMaxEdge + " " + this.mMaxScroll;
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

    public final void reset() {
        mainAxis().reset();
    }

    public String toString() {
        return "horizontal=" + this.horizontal + "; vertical=" + this.vertical;
    }
}
