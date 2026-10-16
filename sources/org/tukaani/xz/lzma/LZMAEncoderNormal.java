package org.tukaani.xz.lzma;

import org.tukaani.xz.ArrayCache;
import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lz.Matches;
import org.tukaani.xz.rangecoder.RangeEncoder;

/* JADX INFO: loaded from: classes3.dex */
final class LZMAEncoderNormal extends LZMAEncoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int EXTRA_SIZE_AFTER = 4096;
    private static final int EXTRA_SIZE_BEFORE = 4096;
    private static final int OPTS = 4096;
    private Matches matches;
    private final State nextState;
    private int optCur;
    private int optEnd;
    private final Optimum[] opts;
    private final int[] repLens;

    LZMAEncoderNormal(RangeEncoder rangeEncoder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, ArrayCache arrayCache) {
        super(rangeEncoder, LZEncoder.getInstance(i4, Math.max(i5, 4096), 4096, i6, 273, i7, i8, arrayCache), i, i2, i3, i4, i6);
        this.opts = new Optimum[4096];
        this.optCur = 0;
        this.optEnd = 0;
        this.repLens = new int[4];
        this.nextState = new State();
        for (int i9 = 0; i9 < 4096; i9++) {
            this.opts[i9] = new Optimum();
        }
    }

    private void calc1BytePrices(int i, int i2, int i3, int i4) {
        boolean z;
        int shortRepPrice;
        int i5 = this.lz.getByte(0);
        int i6 = this.lz.getByte(this.opts[this.optCur].reps[0] + 1);
        int price = this.opts[this.optCur].price + this.literalEncoder.getPrice(i5, i6, this.lz.getByte(1), i, this.opts[this.optCur].state);
        if (price < this.opts[this.optCur + 1].price) {
            Optimum[] optimumArr = this.opts;
            int i7 = this.optCur;
            optimumArr[i7 + 1].set1(price, i7, -1);
            z = true;
        } else {
            z = false;
        }
        if (i6 == i5) {
            int i8 = this.opts[this.optCur + 1].optPrev;
            int i9 = this.optCur;
            if ((i8 == i9 || this.opts[i9 + 1].backPrev != 0) && (shortRepPrice = getShortRepPrice(i4, this.opts[this.optCur].state, i2)) <= this.opts[this.optCur + 1].price) {
                Optimum[] optimumArr2 = this.opts;
                int i10 = this.optCur;
                optimumArr2[i10 + 1].set1(shortRepPrice, i10, 0);
                z = true;
            }
        }
        if (z || i6 == i5 || i3 <= 2) {
            return;
        }
        int matchLen = this.lz.getMatchLen(1, this.opts[this.optCur].reps[0], Math.min(this.niceLen, i3 - 1));
        if (matchLen >= 2) {
            this.nextState.set(this.opts[this.optCur].state);
            this.nextState.updateLiteral();
            int longRepAndLenPrice = price + getLongRepAndLenPrice(0, matchLen, this.nextState, (i + 1) & this.posMask);
            int i11 = this.optCur + 1 + matchLen;
            while (true) {
                int i12 = this.optEnd;
                if (i12 >= i11) {
                    break;
                }
                Optimum[] optimumArr3 = this.opts;
                int i13 = i12 + 1;
                this.optEnd = i13;
                optimumArr3[i13].reset();
            }
            if (longRepAndLenPrice < this.opts[i11].price) {
                this.opts[i11].set2(longRepAndLenPrice, this.optCur, 0);
            }
        }
    }

    private int calcLongRepPrices(int i, int i2, int i3, int i4) {
        int i5;
        int iMin = Math.min(i3, this.niceLen);
        int i6 = 2;
        for (int i7 = 0; i7 < 4; i7++) {
            int matchLen = this.lz.getMatchLen(this.opts[this.optCur].reps[i7], iMin);
            if (matchLen >= 2) {
                while (true) {
                    int i8 = this.optEnd;
                    i5 = this.optCur;
                    if (i8 >= i5 + matchLen) {
                        break;
                    }
                    Optimum[] optimumArr = this.opts;
                    int i9 = i8 + 1;
                    this.optEnd = i9;
                    optimumArr[i9].reset();
                }
                int longRepPrice = getLongRepPrice(i4, i7, this.opts[i5].state, i2);
                for (int i10 = matchLen; i10 >= 2; i10--) {
                    int price = this.repLenEncoder.getPrice(i10, i2) + longRepPrice;
                    if (price < this.opts[this.optCur + i10].price) {
                        Optimum[] optimumArr2 = this.opts;
                        int i11 = this.optCur;
                        optimumArr2[i11 + i10].set1(price, i11, i7);
                    }
                }
                if (i7 == 0) {
                    i6 = matchLen + 1;
                }
                int i12 = i6;
                int matchLen2 = this.lz.getMatchLen(matchLen + 1, this.opts[this.optCur].reps[i7], Math.min(this.niceLen, (i3 - matchLen) - 1));
                if (matchLen2 >= 2) {
                    int price2 = longRepPrice + this.repLenEncoder.getPrice(matchLen, i2);
                    this.nextState.set(this.opts[this.optCur].state);
                    this.nextState.updateLongRep();
                    int i13 = i + matchLen;
                    int price3 = price2 + this.literalEncoder.getPrice(this.lz.getByte(matchLen, 0), this.lz.getByte(0), this.lz.getByte(matchLen, 1), i13, this.nextState);
                    this.nextState.updateLiteral();
                    int longRepAndLenPrice = price3 + getLongRepAndLenPrice(0, matchLen2, this.nextState, (i13 + 1) & this.posMask);
                    int i14 = this.optCur + matchLen + 1 + matchLen2;
                    while (true) {
                        int i15 = this.optEnd;
                        if (i15 >= i14) {
                            break;
                        }
                        Optimum[] optimumArr3 = this.opts;
                        int i16 = i15 + 1;
                        this.optEnd = i16;
                        optimumArr3[i16].reset();
                    }
                    if (longRepAndLenPrice < this.opts[i14].price) {
                        this.opts[i14].set3(longRepAndLenPrice, this.optCur, i7, matchLen, 0);
                    }
                }
                i6 = i12;
            }
        }
        return i6;
    }

    private void calcNormalMatchPrices(int i, int i2, int i3, int i4, int i5) {
        int i6 = i5;
        if (this.matches.len[this.matches.count - 1] > i3) {
            this.matches.count = 0;
            while (this.matches.len[this.matches.count] < i3) {
                this.matches.count++;
            }
            int[] iArr = this.matches.len;
            Matches matches = this.matches;
            int i7 = matches.count;
            matches.count = i7 + 1;
            iArr[i7] = i3;
        }
        if (this.matches.len[this.matches.count - 1] < i6) {
            return;
        }
        while (this.optEnd < this.optCur + this.matches.len[this.matches.count - 1]) {
            Optimum[] optimumArr = this.opts;
            int i8 = this.optEnd + 1;
            this.optEnd = i8;
            optimumArr[i8].reset();
        }
        int normalMatchPrice = getNormalMatchPrice(i4, this.opts[this.optCur].state);
        int i9 = 0;
        while (i6 > this.matches.len[i9]) {
            i9++;
        }
        while (true) {
            int i10 = this.matches.dist[i9];
            int matchAndLenPrice = getMatchAndLenPrice(normalMatchPrice, i10, i6, i2);
            if (matchAndLenPrice < this.opts[this.optCur + i6].price) {
                Optimum[] optimumArr2 = this.opts;
                int i11 = this.optCur;
                optimumArr2[i11 + i6].set1(matchAndLenPrice, i11, i10 + 4);
            }
            if (i6 == this.matches.len[i9]) {
                int matchLen = this.lz.getMatchLen(i6 + 1, i10, Math.min(this.niceLen, (i3 - i6) - 1));
                if (matchLen >= 2) {
                    this.nextState.set(this.opts[this.optCur].state);
                    this.nextState.updateMatch();
                    int i12 = i + i6;
                    int price = matchAndLenPrice + this.literalEncoder.getPrice(this.lz.getByte(i6, 0), this.lz.getByte(0), this.lz.getByte(i6, 1), i12, this.nextState);
                    this.nextState.updateLiteral();
                    int longRepAndLenPrice = price + getLongRepAndLenPrice(0, matchLen, this.nextState, (i12 + 1) & this.posMask);
                    int i13 = this.optCur + i6 + 1 + matchLen;
                    while (true) {
                        int i14 = this.optEnd;
                        if (i14 >= i13) {
                            break;
                        }
                        Optimum[] optimumArr3 = this.opts;
                        int i15 = i14 + 1;
                        this.optEnd = i15;
                        optimumArr3[i15].reset();
                    }
                    if (longRepAndLenPrice < this.opts[i13].price) {
                        this.opts[i13].set3(longRepAndLenPrice, this.optCur, i10 + 4, i6, 0);
                    }
                }
                i9++;
                if (i9 == this.matches.count) {
                    return;
                }
            }
            i6++;
        }
    }

    private int convertOpts() {
        int i = this.optCur;
        this.optEnd = i;
        int i2 = this.opts[i].optPrev;
        while (true) {
            Optimum optimum = this.opts[this.optCur];
            if (optimum.prev1IsLiteral) {
                this.opts[i2].optPrev = this.optCur;
                this.opts[i2].backPrev = -1;
                int i3 = i2 - 1;
                this.optCur = i2;
                if (optimum.hasPrev2) {
                    this.opts[i3].optPrev = i3 + 1;
                    this.opts[i3].backPrev = optimum.backPrev2;
                    this.optCur = i3;
                    i2 = optimum.optPrev2;
                } else {
                    i2 = i3;
                }
            }
            int i4 = this.opts[i2].optPrev;
            this.opts[i2].optPrev = this.optCur;
            this.optCur = i2;
            if (i2 <= 0) {
                int i5 = this.opts[0].optPrev;
                this.optCur = i5;
                this.back = this.opts[i5].backPrev;
                return this.optCur;
            }
            i2 = i4;
        }
    }

    static int getMemoryUsage(int i, int i2, int i3) {
        return LZEncoder.getMemoryUsage(i, Math.max(i2, 4096), 4096, 273, i3) + 256;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x010c  */
    /* JADX WARN: Code duplicated, block: B:34:0x011c A[LOOP:0: B:33:0x011a->B:34:0x011c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:36:0x0135 A[LOOP:1: B:35:0x0133->B:36:0x0135, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:37:0x014a  */
    private void updateOptStateAndReps() {
        int i;
        Optimum optimum;
        int i2;
        Optimum[] optimumArr;
        int i3;
        int i4 = this.opts[this.optCur].optPrev;
        if (this.opts[this.optCur].prev1IsLiteral) {
            i4--;
            if (this.opts[this.optCur].hasPrev2) {
                State state = this.opts[this.optCur].state;
                Optimum[] optimumArr2 = this.opts;
                state.set(optimumArr2[optimumArr2[this.optCur].optPrev2].state);
                if (this.opts[this.optCur].backPrev2 < 4) {
                    this.opts[this.optCur].state.updateLongRep();
                } else {
                    this.opts[this.optCur].state.updateMatch();
                }
            } else {
                this.opts[this.optCur].state.set(this.opts[i4].state);
            }
            this.opts[this.optCur].state.updateLiteral();
        } else {
            this.opts[this.optCur].state.set(this.opts[i4].state);
        }
        int i5 = this.optCur;
        if (i4 == i5 - 1) {
            if (this.opts[i5].backPrev == 0) {
                this.opts[this.optCur].state.updateShortRep();
            } else {
                this.opts[this.optCur].state.updateLiteral();
            }
            System.arraycopy(this.opts[i4].reps, 0, this.opts[this.optCur].reps, 0, 4);
            return;
        }
        if (!this.opts[i5].prev1IsLiteral || !this.opts[this.optCur].hasPrev2) {
            i = this.opts[this.optCur].backPrev;
            Optimum[] optimumArr3 = this.opts;
            int i6 = this.optCur;
            if (i < 4) {
                optimum = optimumArr3[i6];
            } else {
                optimumArr3[i6].state.updateMatch();
            }
            i2 = 1;
            optimumArr = this.opts;
            i3 = this.optCur;
            if (i < 4) {
                optimumArr[i3].reps[0] = i - 4;
                System.arraycopy(this.opts[i4].reps, 0, this.opts[this.optCur].reps, 1, 3);
                return;
            }
            optimumArr[i3].reps[0] = this.opts[i4].reps[i];
            while (i2 <= i) {
                this.opts[this.optCur].reps[i2] = this.opts[i4].reps[i2 - 1];
                i2++;
            }
            while (i2 < 4) {
                this.opts[this.optCur].reps[i2] = this.opts[i4].reps[i2];
                i2++;
            }
        }
        i4 = this.opts[this.optCur].optPrev2;
        i = this.opts[this.optCur].backPrev2;
        optimum = this.opts[this.optCur];
        optimum.state.updateLongRep();
        i2 = 1;
        optimumArr = this.opts;
        i3 = this.optCur;
        if (i < 4) {
            optimumArr[i3].reps[0] = i - 4;
            System.arraycopy(this.opts[i4].reps, 0, this.opts[this.optCur].reps, 1, 3);
            return;
        }
        optimumArr[i3].reps[0] = this.opts[i4].reps[i];
        while (i2 <= i) {
            this.opts[this.optCur].reps[i2] = this.opts[i4].reps[i2 - 1];
            i2++;
        }
        while (i2 < 4) {
            this.opts[this.optCur].reps[i2] = this.opts[i4].reps[i2];
            i2++;
        }
    }

    @Override // org.tukaani.xz.lzma.LZMAEncoder
    int getNextSymbol() {
        int i;
        int shortRepPrice;
        int i2 = this.optCur;
        if (i2 < this.optEnd) {
            int i3 = this.opts[i2].optPrev;
            int i4 = this.optCur;
            int i5 = i3 - i4;
            int i6 = this.opts[i4].optPrev;
            this.optCur = i6;
            this.back = this.opts[i6].backPrev;
            return i5;
        }
        this.optCur = 0;
        this.optEnd = 0;
        this.back = -1;
        if (this.readAhead == -1) {
            this.matches = getMatches();
        }
        int iMin = Math.min(this.lz.getAvail(), 273);
        if (iMin < 2) {
            return 1;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < 4; i8++) {
            this.repLens[i8] = this.lz.getMatchLen(this.reps[i8], iMin);
            int[] iArr = this.repLens;
            if (iArr[i8] < 2) {
                iArr[i8] = 0;
            } else if (iArr[i8] > iArr[i7]) {
                i7 = i8;
            }
        }
        if (this.repLens[i7] >= this.niceLen) {
            this.back = i7;
            skip(this.repLens[i7] - 1);
            return this.repLens[i7];
        }
        if (this.matches.count > 0) {
            i = this.matches.len[this.matches.count - 1];
            int i9 = this.matches.dist[this.matches.count - 1];
            if (i >= this.niceLen) {
                this.back = i9 + 4;
                skip(i - 1);
                return i;
            }
        } else {
            i = 0;
        }
        int i10 = this.lz.getByte(0);
        int i11 = this.lz.getByte(this.reps[0] + 1);
        if (i < 2 && i10 != i11 && this.repLens[i7] < 2) {
            return 1;
        }
        int pos = this.lz.getPos();
        int i12 = pos & this.posMask;
        this.opts[1].set1(this.literalEncoder.getPrice(i10, i11, this.lz.getByte(1), pos, this.state), 0, -1);
        int anyMatchPrice = getAnyMatchPrice(this.state, i12);
        int anyRepPrice = getAnyRepPrice(anyMatchPrice, this.state);
        if (i11 == i10 && (shortRepPrice = getShortRepPrice(anyRepPrice, this.state, i12)) < this.opts[1].price) {
            this.opts[1].set1(shortRepPrice, 0, 0);
        }
        int iMax = Math.max(i, this.repLens[i7]);
        this.optEnd = iMax;
        if (iMax < 2) {
            this.back = this.opts[1].backPrev;
            return 1;
        }
        updatePrices();
        this.opts[0].state.set(this.state);
        System.arraycopy(this.reps, 0, this.opts[0].reps, 0, 4);
        for (int i13 = this.optEnd; i13 >= 2; i13--) {
            this.opts[i13].reset();
        }
        for (int i14 = 0; i14 < 4; i14++) {
            int i15 = this.repLens[i14];
            if (i15 >= 2) {
                int longRepPrice = getLongRepPrice(anyRepPrice, i14, this.state, i12);
                do {
                    int price = this.repLenEncoder.getPrice(i15, i12) + longRepPrice;
                    if (price < this.opts[i15].price) {
                        this.opts[i15].set1(price, 0, i14);
                    }
                    i15--;
                } while (i15 >= 2);
            }
        }
        int iMax2 = Math.max(this.repLens[0] + 1, 2);
        if (iMax2 <= i) {
            int normalMatchPrice = getNormalMatchPrice(anyMatchPrice, this.state);
            int i16 = 0;
            while (iMax2 > this.matches.len[i16]) {
                i16++;
            }
            while (true) {
                int i17 = this.matches.dist[i16];
                int matchAndLenPrice = getMatchAndLenPrice(normalMatchPrice, i17, iMax2, i12);
                if (matchAndLenPrice < this.opts[iMax2].price) {
                    this.opts[iMax2].set1(matchAndLenPrice, 0, i17 + 4);
                }
                if (iMax2 == this.matches.len[i16] && (i16 = i16 + 1) == this.matches.count) {
                    break;
                }
                iMax2++;
            }
        }
        int iMin2 = Math.min(this.lz.getAvail(), 4095);
        while (true) {
            int i18 = this.optCur + 1;
            this.optCur = i18;
            if (i18 >= this.optEnd) {
                break;
            }
            Matches matches = getMatches();
            this.matches = matches;
            if (matches.count > 0 && this.matches.len[this.matches.count - 1] >= this.niceLen) {
                break;
            }
            int i19 = iMin2 - 1;
            int i20 = pos + 1;
            int i21 = i20 & this.posMask;
            updateOptStateAndReps();
            int anyMatchPrice2 = this.opts[this.optCur].price + getAnyMatchPrice(this.opts[this.optCur].state, i21);
            int anyRepPrice2 = getAnyRepPrice(anyMatchPrice2, this.opts[this.optCur].state);
            calc1BytePrices(i20, i21, i19, anyRepPrice2);
            if (i19 >= 2) {
                int iCalcLongRepPrices = calcLongRepPrices(i20, i21, i19, anyRepPrice2);
                if (this.matches.count > 0) {
                    calcNormalMatchPrices(i20, i21, i19, anyMatchPrice2, iCalcLongRepPrices);
                }
            }
            iMin2 = i19;
            pos = i20;
        }
        return convertOpts();
    }

    @Override // org.tukaani.xz.lzma.LZMAEncoder, org.tukaani.xz.lzma.LZMACoder
    public void reset() {
        this.optCur = 0;
        this.optEnd = 0;
        super.reset();
    }
}
