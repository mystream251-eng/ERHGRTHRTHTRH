package androidx.leanback.widget;

/* JADX INFO: loaded from: classes.dex */
final class StaggeredGridDefault extends StaggeredGrid {
    StaggeredGridDefault() {
    }

    int getRowMax(int i) {
        int i2;
        StaggeredGrid.Location location;
        if (this.mFirstVisibleIndex < 0) {
            return Integer.MIN_VALUE;
        }
        if (this.mReversedFlow) {
            int edge = this.mProvider.getEdge(this.mFirstVisibleIndex);
            if (getLocation(this.mFirstVisibleIndex).row == i) {
                return edge;
            }
            int i3 = this.mFirstVisibleIndex;
            do {
                i3++;
                if (i3 <= getLastIndex()) {
                    location = getLocation(i3);
                    edge += location.offset;
                }
            } while (location.row != i);
            return edge;
        }
        int edge2 = this.mProvider.getEdge(this.mLastVisibleIndex);
        StaggeredGrid.Location location2 = getLocation(this.mLastVisibleIndex);
        if (location2.row == i) {
            i2 = location2.size;
        } else {
            int i4 = this.mLastVisibleIndex;
            do {
                i4--;
                if (i4 >= getFirstIndex()) {
                    edge2 -= location2.offset;
                    location2 = getLocation(i4);
                }
            } while (location2.row != i);
            i2 = location2.size;
        }
        return edge2 + i2;
        return Integer.MIN_VALUE;
    }

    int getRowMin(int i) {
        StaggeredGrid.Location location;
        int i2;
        if (this.mFirstVisibleIndex < 0) {
            return Integer.MAX_VALUE;
        }
        if (this.mReversedFlow) {
            int edge = this.mProvider.getEdge(this.mLastVisibleIndex);
            StaggeredGrid.Location location2 = getLocation(this.mLastVisibleIndex);
            if (location2.row == i) {
                i2 = location2.size;
            } else {
                int i3 = this.mLastVisibleIndex;
                do {
                    i3--;
                    if (i3 >= getFirstIndex()) {
                        edge -= location2.offset;
                        location2 = getLocation(i3);
                    }
                } while (location2.row != i);
                i2 = location2.size;
            }
            return edge - i2;
        }
        int edge2 = this.mProvider.getEdge(this.mFirstVisibleIndex);
        if (getLocation(this.mFirstVisibleIndex).row == i) {
            return edge2;
        }
        int i4 = this.mFirstVisibleIndex;
        do {
            i4++;
            if (i4 <= getLastIndex()) {
                location = getLocation(i4);
                edge2 += location.offset;
            }
        } while (location.row != i);
        return edge2;
        return Integer.MAX_VALUE;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0036  */
    /* JADX WARN: Code duplicated, block: B:30:0x0071  */
    @Override // androidx.leanback.widget.Grid
    public int findRowMax(boolean z, int i, int[] iArr) {
        int i2;
        int edge = this.mProvider.getEdge(i);
        StaggeredGrid.Location location = getLocation(i);
        int i3 = location.row;
        if (this.mReversedFlow) {
            i2 = i3;
            int i4 = i2;
            int i5 = 1;
            int i6 = edge;
            for (int i7 = i + 1; i5 < this.mNumRows && i7 <= this.mLastVisibleIndex; i7++) {
                StaggeredGrid.Location location2 = getLocation(i7);
                i6 += location2.offset;
                if (location2.row != i4) {
                    i4 = location2.row;
                    i5++;
                    if (z) {
                        if (i6 > edge) {
                            edge = i6;
                            i = i7;
                            i2 = i4;
                        }
                    } else if (i6 < edge) {
                        edge = i6;
                        i = i7;
                        i2 = i4;
                    }
                }
            }
        } else {
            int i8 = 1;
            int i9 = i3;
            StaggeredGrid.Location location3 = location;
            int i10 = edge;
            edge = this.mProvider.getSize(i) + edge;
            i2 = i9;
            for (int i11 = i - 1; i8 < this.mNumRows && i11 >= this.mFirstVisibleIndex; i11--) {
                i10 -= location3.offset;
                location3 = getLocation(i11);
                if (location3.row != i9) {
                    i9 = location3.row;
                    i8++;
                    int size = this.mProvider.getSize(i11) + i10;
                    if (z) {
                        if (size > edge) {
                            i2 = i9;
                            i = i11;
                            edge = size;
                        }
                    } else if (size < edge) {
                        i2 = i9;
                        i = i11;
                        edge = size;
                    }
                }
            }
        }
        if (iArr != null) {
            iArr[0] = i2;
            iArr[1] = i;
        }
        return edge;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0045  */
    /* JADX WARN: Code duplicated, block: B:30:0x0070  */
    @Override // androidx.leanback.widget.Grid
    public int findRowMin(boolean z, int i, int[] iArr) {
        int size;
        int edge = this.mProvider.getEdge(i);
        StaggeredGrid.Location location = getLocation(i);
        int i2 = location.row;
        if (this.mReversedFlow) {
            int i3 = 1;
            size = edge - this.mProvider.getSize(i);
            int i4 = i2;
            for (int i5 = i - 1; i3 < this.mNumRows && i5 >= this.mFirstVisibleIndex; i5--) {
                edge -= location.offset;
                location = getLocation(i5);
                if (location.row != i4) {
                    i4 = location.row;
                    i3++;
                    int size2 = edge - this.mProvider.getSize(i5);
                    if (z) {
                        if (size2 > size) {
                            i2 = i4;
                            i = i5;
                            size = size2;
                        }
                    } else if (size2 < size) {
                        i2 = i4;
                        i = i5;
                        size = size2;
                    }
                }
            }
        } else {
            int i6 = i2;
            int i7 = i6;
            int i8 = 1;
            int i9 = edge;
            for (int i10 = i + 1; i8 < this.mNumRows && i10 <= this.mLastVisibleIndex; i10++) {
                StaggeredGrid.Location location2 = getLocation(i10);
                i9 += location2.offset;
                if (location2.row != i7) {
                    i7 = location2.row;
                    i8++;
                    if (z) {
                        if (i9 > edge) {
                            edge = i9;
                            i = i10;
                            i6 = i7;
                        }
                    } else if (i9 < edge) {
                        edge = i9;
                        i = i10;
                        i6 = i7;
                    }
                }
            }
            size = edge;
            i2 = i6;
        }
        if (iArr != null) {
            iArr[0] = i2;
            iArr[1] = i;
        }
        return size;
    }

    private int findRowEdgeLimitSearchIndex(boolean z) {
        boolean z2 = false;
        if (z) {
            for (int i = this.mLastVisibleIndex; i >= this.mFirstVisibleIndex; i--) {
                int i2 = getLocation(i).row;
                if (i2 == 0) {
                    z2 = true;
                } else if (z2 && i2 == this.mNumRows - 1) {
                    return i;
                }
            }
            return -1;
        }
        for (int i3 = this.mFirstVisibleIndex; i3 <= this.mLastVisibleIndex; i3++) {
            int i4 = getLocation(i3).row;
            if (i4 == this.mNumRows - 1) {
                z2 = true;
            } else if (z2 && i4 == 0) {
                return i3;
            }
        }
        return -1;
    }

    @Override // androidx.leanback.widget.StaggeredGrid
    protected boolean appendVisibleItemsWithoutCache(int i, boolean z) {
        int i2;
        int i3;
        boolean z2;
        int rowMin;
        int i4;
        int i5;
        int count = this.mProvider.getCount();
        if (this.mLastVisibleIndex >= 0) {
            if (this.mLastVisibleIndex < getLastIndex()) {
                return false;
            }
            i2 = this.mLastVisibleIndex + 1;
            i3 = getLocation(this.mLastVisibleIndex).row;
            int iFindRowEdgeLimitSearchIndex = findRowEdgeLimitSearchIndex(true);
            if (iFindRowEdgeLimitSearchIndex < 0) {
                rowMin = Integer.MIN_VALUE;
                for (int i6 = 0; i6 < this.mNumRows; i6++) {
                    rowMin = this.mReversedFlow ? getRowMin(i6) : getRowMax(i6);
                    if (rowMin != Integer.MIN_VALUE) {
                        break;
                    }
                }
            } else {
                rowMin = this.mReversedFlow ? findRowMin(false, iFindRowEdgeLimitSearchIndex, null) : findRowMax(true, iFindRowEdgeLimitSearchIndex, null);
            }
            if (!this.mReversedFlow ? getRowMax(i3) >= rowMin : getRowMin(i3) <= rowMin) {
                i3++;
                if (i3 == this.mNumRows) {
                    rowMin = this.mReversedFlow ? findRowMin(false, null) : findRowMax(true, null);
                    i3 = 0;
                }
            }
            z2 = true;
        } else {
            i2 = this.mStartIndex != -1 ? this.mStartIndex : 0;
            i3 = (this.mLocations.size() > 0 ? getLocation(getLastIndex()).row + 1 : i2) % this.mNumRows;
            z2 = false;
            rowMin = 0;
        }
        boolean z3 = false;
        while (true) {
            if (i3 < this.mNumRows) {
                if (i2 == count || (!z && checkAppendOverLimit(i))) {
                    break;
                }
                int rowMin2 = this.mReversedFlow ? getRowMin(i3) : getRowMax(i3);
                if (rowMin2 != Integer.MAX_VALUE && rowMin2 != Integer.MIN_VALUE) {
                    if (this.mReversedFlow) {
                        i5 = this.mSpacing;
                        i4 = -i5;
                    } else {
                        i4 = this.mSpacing;
                    }
                    rowMin2 += i4;
                } else if (i3 == 0) {
                    rowMin2 = this.mReversedFlow ? getRowMin(this.mNumRows - 1) : getRowMax(this.mNumRows - 1);
                    if (rowMin2 != Integer.MAX_VALUE && rowMin2 != Integer.MIN_VALUE) {
                        if (this.mReversedFlow) {
                            i5 = this.mSpacing;
                            i4 = -i5;
                        } else {
                            i4 = this.mSpacing;
                        }
                        rowMin2 += i4;
                    }
                } else {
                    rowMin2 = this.mReversedFlow ? getRowMax(i3 - 1) : getRowMin(i3 - 1);
                }
                int i7 = i2 + 1;
                int iAppendVisibleItemToRow = appendVisibleItemToRow(i2, i3, rowMin2);
                if (z2) {
                    while (true) {
                        if (!this.mReversedFlow) {
                            if (rowMin2 + iAppendVisibleItemToRow >= rowMin) {
                                break;
                            }
                            if (i7 != count) {
                            }
                            return true;
                        }
                        if (rowMin2 - iAppendVisibleItemToRow <= rowMin) {
                            break;
                        }
                        if (i7 != count || (!z && checkAppendOverLimit(i))) {
                            return true;
                        }
                        rowMin2 += this.mReversedFlow ? (-iAppendVisibleItemToRow) - this.mSpacing : iAppendVisibleItemToRow + this.mSpacing;
                        int i8 = i7 + 1;
                        int iAppendVisibleItemToRow2 = appendVisibleItemToRow(i7, i3, rowMin2);
                        i7 = i8;
                        iAppendVisibleItemToRow = iAppendVisibleItemToRow2;
                    }
                } else {
                    rowMin = this.mReversedFlow ? getRowMin(i3) : getRowMax(i3);
                    z2 = true;
                }
                i2 = i7;
                i3++;
                z3 = true;
            } else {
                if (z) {
                    return z3;
                }
                rowMin = this.mReversedFlow ? findRowMin(false, null) : findRowMax(true, null);
                i3 = 0;
            }
        }
        return z3;
    }

    @Override // androidx.leanback.widget.StaggeredGrid
    protected boolean prependVisibleItemsWithoutCache(int i, boolean z) {
        int i2;
        int i3;
        boolean z2;
        int rowMax;
        int i4;
        int i5;
        if (this.mFirstVisibleIndex >= 0) {
            if (this.mFirstVisibleIndex > getFirstIndex()) {
                return false;
            }
            i2 = this.mFirstVisibleIndex - 1;
            i3 = getLocation(this.mFirstVisibleIndex).row;
            int iFindRowEdgeLimitSearchIndex = findRowEdgeLimitSearchIndex(false);
            if (iFindRowEdgeLimitSearchIndex < 0) {
                i3--;
                rowMax = Integer.MAX_VALUE;
                for (int i6 = this.mNumRows - 1; i6 >= 0; i6--) {
                    rowMax = this.mReversedFlow ? getRowMax(i6) : getRowMin(i6);
                    if (rowMax != Integer.MAX_VALUE) {
                        break;
                    }
                }
            } else {
                rowMax = this.mReversedFlow ? findRowMax(true, iFindRowEdgeLimitSearchIndex, null) : findRowMin(false, iFindRowEdgeLimitSearchIndex, null);
            }
            if (!this.mReversedFlow ? getRowMin(i3) <= rowMax : getRowMax(i3) >= rowMax) {
                i3--;
                if (i3 < 0) {
                    i3 = this.mNumRows - 1;
                    rowMax = this.mReversedFlow ? findRowMax(true, null) : findRowMin(false, null);
                }
            }
            z2 = true;
        } else {
            i2 = this.mStartIndex != -1 ? this.mStartIndex : 0;
            i3 = (this.mLocations.size() > 0 ? (getLocation(getFirstIndex()).row + this.mNumRows) - 1 : i2) % this.mNumRows;
            z2 = false;
            rowMax = 0;
        }
        boolean z3 = false;
        while (true) {
            if (i3 >= 0) {
                if (i2 < 0 || (!z && checkPrependOverLimit(i))) {
                    break;
                }
                int rowMax2 = this.mReversedFlow ? getRowMax(i3) : getRowMin(i3);
                if (rowMax2 == Integer.MAX_VALUE || rowMax2 == Integer.MIN_VALUE) {
                    if (i3 == this.mNumRows - 1) {
                        rowMax2 = this.mReversedFlow ? getRowMax(0) : getRowMin(0);
                        if (rowMax2 != Integer.MAX_VALUE && rowMax2 != Integer.MIN_VALUE) {
                            if (this.mReversedFlow) {
                                i5 = this.mSpacing;
                            } else {
                                i4 = this.mSpacing;
                                i5 = -i4;
                            }
                            rowMax2 += i5;
                        }
                    } else {
                        rowMax2 = this.mReversedFlow ? getRowMin(i3 + 1) : getRowMax(i3 + 1);
                    }
                } else {
                    if (this.mReversedFlow) {
                        i5 = this.mSpacing;
                    } else {
                        i4 = this.mSpacing;
                        i5 = -i4;
                    }
                    rowMax2 += i5;
                }
                int i7 = i2 - 1;
                int iPrependVisibleItemToRow = prependVisibleItemToRow(i2, i3, rowMax2);
                if (z2) {
                    while (true) {
                        if (!this.mReversedFlow) {
                            if (rowMax2 - iPrependVisibleItemToRow <= rowMax) {
                                break;
                            }
                            if (i7 >= 0) {
                            }
                            return true;
                        }
                        if (rowMax2 + iPrependVisibleItemToRow >= rowMax) {
                            break;
                        }
                        if (i7 >= 0 || (!z && checkPrependOverLimit(i))) {
                            return true;
                        }
                        rowMax2 += this.mReversedFlow ? iPrependVisibleItemToRow + this.mSpacing : (-iPrependVisibleItemToRow) - this.mSpacing;
                        int i8 = i7 - 1;
                        int iPrependVisibleItemToRow2 = prependVisibleItemToRow(i7, i3, rowMax2);
                        i7 = i8;
                        iPrependVisibleItemToRow = iPrependVisibleItemToRow2;
                    }
                } else {
                    rowMax = this.mReversedFlow ? getRowMax(i3) : getRowMin(i3);
                    z2 = true;
                }
                i2 = i7;
                i3--;
                z3 = true;
            } else {
                if (z) {
                    return z3;
                }
                rowMax = this.mReversedFlow ? findRowMax(true, null) : findRowMin(false, null);
                i3 = this.mNumRows - 1;
            }
        }
        return z3;
    }
}
