package org.tukaani.xz.lzma;

import java.io.IOException;
import java.lang.reflect.Array;
import org.tukaani.xz.ArrayCache;
import org.tukaani.xz.lz.LZEncoder;
import org.tukaani.xz.lz.Matches;
import org.tukaani.xz.rangecoder.RangeEncoder;

/* JADX INFO: loaded from: classes3.dex */
public abstract class LZMAEncoder extends LZMACoder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ALIGN_PRICE_UPDATE_INTERVAL = 16;
    private static final int DIST_PRICE_UPDATE_INTERVAL = 128;
    private static final int LZMA2_COMPRESSED_LIMIT = 65510;
    private static final int LZMA2_UNCOMPRESSED_LIMIT = 2096879;
    public static final int MODE_FAST = 1;
    public static final int MODE_NORMAL = 2;
    private int alignPriceCount;
    private final int[] alignPrices;
    int back;
    private int distPriceCount;
    private final int[][] distSlotPrices;
    private final int distSlotPricesSize;
    private final int[][] fullDistPrices;
    final LiteralEncoder literalEncoder;
    final LZEncoder lz;
    final LengthEncoder matchLenEncoder;
    final int niceLen;
    private final RangeEncoder rc;
    int readAhead;
    final LengthEncoder repLenEncoder;
    private int uncompressedSize;

    class LengthEncoder extends LZMACoder.LengthCoder {
        private static final int PRICE_UPDATE_INTERVAL = 32;
        private final int[] counters;
        private final int[][] prices;

        LengthEncoder(int i, int i2) {
            super();
            int i3 = 1 << i;
            this.counters = new int[i3];
            this.prices = (int[][]) Array.newInstance((Class<?>) int.class, i3, Math.max((i2 - 2) + 1, 16));
        }

        private void updatePrices(int i) {
            int bitPrice = RangeEncoder.getBitPrice(this.choice[0], 0);
            int i2 = 0;
            while (i2 < 8) {
                this.prices[i][i2] = RangeEncoder.getBitTreePrice(this.low[i], i2) + bitPrice;
                i2++;
            }
            int bitPrice2 = RangeEncoder.getBitPrice(this.choice[0], 1);
            int bitPrice3 = RangeEncoder.getBitPrice(this.choice[1], 0);
            while (i2 < 16) {
                this.prices[i][i2] = bitPrice2 + bitPrice3 + RangeEncoder.getBitTreePrice(this.mid[i], i2 - 8);
                i2++;
            }
            int bitPrice4 = RangeEncoder.getBitPrice(this.choice[1], 1);
            while (true) {
                int[][] iArr = this.prices;
                if (i2 >= iArr[i].length) {
                    return;
                }
                iArr[i][i2] = bitPrice2 + bitPrice4 + RangeEncoder.getBitTreePrice(this.high, (i2 - 8) - 8);
                i2++;
            }
        }

        void encode(int i, int i2) throws IOException {
            int i3 = i - 2;
            if (i3 < 8) {
                LZMAEncoder.this.rc.encodeBit(this.choice, 0, 0);
                LZMAEncoder.this.rc.encodeBitTree(this.low[i2], i3);
            } else {
                LZMAEncoder.this.rc.encodeBit(this.choice, 0, 1);
                int i4 = i3 - 8;
                if (i4 < 8) {
                    LZMAEncoder.this.rc.encodeBit(this.choice, 1, 0);
                    LZMAEncoder.this.rc.encodeBitTree(this.mid[i2], i4);
                } else {
                    LZMAEncoder.this.rc.encodeBit(this.choice, 1, 1);
                    LZMAEncoder.this.rc.encodeBitTree(this.high, i4 - 8);
                }
            }
            int[] iArr = this.counters;
            iArr[i2] = iArr[i2] - 1;
        }

        int getPrice(int i, int i2) {
            return this.prices[i2][i - 2];
        }

        @Override // org.tukaani.xz.lzma.LZMACoder.LengthCoder
        void reset() {
            super.reset();
            int i = 0;
            while (true) {
                int[] iArr = this.counters;
                if (i >= iArr.length) {
                    return;
                }
                iArr[i] = 0;
                i++;
            }
        }

        void updatePrices() {
            int i = 0;
            while (true) {
                int[] iArr = this.counters;
                if (i >= iArr.length) {
                    return;
                }
                if (iArr[i] <= 0) {
                    iArr[i] = 32;
                    updatePrices(i);
                }
                i++;
            }
        }
    }

    class LiteralEncoder extends LZMACoder.LiteralCoder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final LiteralSubencoder[] subencoders;

        private class LiteralSubencoder extends LZMACoder.LiteralCoder.LiteralSubcoder {
            private LiteralSubencoder() {
                super();
            }

            void encode() throws IOException {
                int i = 256;
                int i2 = LZMAEncoder.this.lz.getByte(LZMAEncoder.this.readAhead) | 256;
                if (LZMAEncoder.this.state.isLiteral()) {
                    do {
                        LZMAEncoder.this.rc.encodeBit(this.probs, i2 >>> 8, (i2 >>> 7) & 1);
                        i2 <<= 1;
                    } while (i2 < 65536);
                } else {
                    int i3 = LZMAEncoder.this.lz.getByte(LZMAEncoder.this.reps[0] + 1 + LZMAEncoder.this.readAhead);
                    do {
                        i3 <<= 1;
                        LZMAEncoder.this.rc.encodeBit(this.probs, (i3 & i) + i + (i2 >>> 8), (i2 >>> 7) & 1);
                        i2 <<= 1;
                        i &= ~(i3 ^ i2);
                    } while (i2 < 65536);
                }
                LZMAEncoder.this.state.updateLiteral();
            }

            int getMatchedPrice(int i, int i2) {
                int i3 = 256;
                int i4 = i | 256;
                int bitPrice = 0;
                do {
                    i2 <<= 1;
                    bitPrice += RangeEncoder.getBitPrice(this.probs[(i2 & i3) + i3 + (i4 >>> 8)], (i4 >>> 7) & 1);
                    i4 <<= 1;
                    i3 &= ~(i2 ^ i4);
                } while (i4 < 65536);
                return bitPrice;
            }

            int getNormalPrice(int i) {
                int i2 = i | 256;
                int bitPrice = 0;
                do {
                    bitPrice += RangeEncoder.getBitPrice(this.probs[i2 >>> 8], (i2 >>> 7) & 1);
                    i2 <<= 1;
                } while (i2 < 65536);
                return bitPrice;
            }
        }

        LiteralEncoder(int i, int i2) {
            super(i, i2);
            this.subencoders = new LiteralSubencoder[1 << (i + i2)];
            int i3 = 0;
            while (true) {
                LiteralSubencoder[] literalSubencoderArr = this.subencoders;
                if (i3 >= literalSubencoderArr.length) {
                    return;
                }
                literalSubencoderArr[i3] = new LiteralSubencoder();
                i3++;
            }
        }

        void encode() throws IOException {
            this.subencoders[getSubcoderIndex(LZMAEncoder.this.lz.getByte(LZMAEncoder.this.readAhead + 1), LZMAEncoder.this.lz.getPos() - LZMAEncoder.this.readAhead)].encode();
        }

        void encodeInit() throws IOException {
            this.subencoders[0].encode();
        }

        int getPrice(int i, int i2, int i3, int i4, State state) {
            int bitPrice = RangeEncoder.getBitPrice(LZMAEncoder.this.isMatch[state.get()][LZMAEncoder.this.posMask & i4], 0);
            int subcoderIndex = getSubcoderIndex(i3, i4);
            return bitPrice + (state.isLiteral() ? this.subencoders[subcoderIndex].getNormalPrice(i) : this.subencoders[subcoderIndex].getMatchedPrice(i, i2));
        }

        void reset() {
            int i = 0;
            while (true) {
                LiteralSubencoder[] literalSubencoderArr = this.subencoders;
                if (i >= literalSubencoderArr.length) {
                    return;
                }
                literalSubencoderArr[i].reset();
                i++;
            }
        }
    }

    LZMAEncoder(RangeEncoder rangeEncoder, LZEncoder lZEncoder, int i, int i2, int i3, int i4, int i5) {
        super(i3);
        this.distPriceCount = 0;
        this.alignPriceCount = 0;
        this.fullDistPrices = (int[][]) Array.newInstance((Class<?>) int.class, 4, 128);
        this.alignPrices = new int[16];
        this.back = 0;
        this.readAhead = -1;
        this.uncompressedSize = 0;
        this.rc = rangeEncoder;
        this.lz = lZEncoder;
        this.niceLen = i5;
        this.literalEncoder = new LiteralEncoder(i, i2);
        this.matchLenEncoder = new LengthEncoder(i3, i5);
        this.repLenEncoder = new LengthEncoder(i3, i5);
        int distSlot = getDistSlot(i4 - 1) + 1;
        this.distSlotPricesSize = distSlot;
        this.distSlotPrices = (int[][]) Array.newInstance((Class<?>) int.class, 4, distSlot);
        reset();
    }

    private boolean encodeInit() throws IOException {
        if (!this.lz.hasEnoughData(0)) {
            return false;
        }
        skip(1);
        this.rc.encodeBit(this.isMatch[this.state.get()], 0, 0);
        this.literalEncoder.encodeInit();
        this.readAhead--;
        this.uncompressedSize++;
        return true;
    }

    private void encodeMatch(int i, int i2, int i3) throws IOException {
        this.state.updateMatch();
        this.matchLenEncoder.encode(i2, i3);
        int distSlot = getDistSlot(i);
        this.rc.encodeBitTree(this.distSlots[getDistState(i2)], distSlot);
        if (distSlot >= 4) {
            int i4 = (distSlot >>> 1) - 1;
            int i5 = i - (((distSlot & 1) | 2) << i4);
            if (distSlot < 14) {
                this.rc.encodeReverseBitTree(this.distSpecial[distSlot - 4], i5);
            } else {
                this.rc.encodeDirectBits(i5 >>> 4, i4 - 4);
                this.rc.encodeReverseBitTree(this.distAlign, i5 & 15);
                this.alignPriceCount--;
            }
        }
        this.reps[3] = this.reps[2];
        this.reps[2] = this.reps[1];
        this.reps[1] = this.reps[0];
        this.reps[0] = i;
        this.distPriceCount--;
    }

    private void encodeRepMatch(int i, int i2, int i3) throws IOException {
        if (i == 0) {
            this.rc.encodeBit(this.isRep0, this.state.get(), 0);
            this.rc.encodeBit(this.isRep0Long[this.state.get()], i3, i2 != 1 ? 1 : 0);
        } else {
            int i4 = this.reps[i];
            this.rc.encodeBit(this.isRep0, this.state.get(), 1);
            if (i == 1) {
                this.rc.encodeBit(this.isRep1, this.state.get(), 0);
            } else {
                this.rc.encodeBit(this.isRep1, this.state.get(), 1);
                this.rc.encodeBit(this.isRep2, this.state.get(), i - 2);
                if (i == 3) {
                    this.reps[3] = this.reps[2];
                }
                this.reps[2] = this.reps[1];
            }
            this.reps[1] = this.reps[0];
            this.reps[0] = i4;
        }
        if (i2 == 1) {
            this.state.updateShortRep();
        } else {
            this.repLenEncoder.encode(i2, i3);
            this.state.updateLongRep();
        }
    }

    private boolean encodeSymbol() throws IOException {
        if (!this.lz.hasEnoughData(this.readAhead + 1)) {
            return false;
        }
        int nextSymbol = getNextSymbol();
        int pos = (this.lz.getPos() - this.readAhead) & this.posMask;
        if (this.back == -1) {
            this.rc.encodeBit(this.isMatch[this.state.get()], pos, 0);
            this.literalEncoder.encode();
        } else {
            this.rc.encodeBit(this.isMatch[this.state.get()], pos, 1);
            if (this.back < 4) {
                this.rc.encodeBit(this.isRep, this.state.get(), 1);
                encodeRepMatch(this.back, nextSymbol, pos);
            } else {
                this.rc.encodeBit(this.isRep, this.state.get(), 0);
                encodeMatch(this.back - 4, nextSymbol, pos);
            }
        }
        this.readAhead -= nextSymbol;
        this.uncompressedSize += nextSymbol;
        return true;
    }

    public static int getDistSlot(int i) {
        int i2;
        int i3;
        if (i <= 4 && i >= 0) {
            return i;
        }
        if (((-65536) & i) == 0) {
            i3 = i << 16;
            i2 = 15;
        } else {
            i2 = 31;
            i3 = i;
        }
        if (((-16777216) & i3) == 0) {
            i3 <<= 8;
            i2 -= 8;
        }
        if (((-268435456) & i3) == 0) {
            i3 <<= 4;
            i2 -= 4;
        }
        if (((-1073741824) & i3) == 0) {
            i3 <<= 2;
            i2 -= 2;
        }
        if ((i3 & Integer.MIN_VALUE) == 0) {
            i2--;
        }
        return (i2 << 1) + ((i >>> (i2 - 1)) & 1);
    }

    public static LZMAEncoder getInstance(RangeEncoder rangeEncoder, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, ArrayCache arrayCache) {
        if (i4 == 1) {
            return new LZMAEncoderFast(rangeEncoder, i, i2, i3, i5, i6, i7, i8, i9, arrayCache);
        }
        if (i4 == 2) {
            return new LZMAEncoderNormal(rangeEncoder, i, i2, i3, i5, i6, i7, i8, i9, arrayCache);
        }
        throw new IllegalArgumentException();
    }

    public static int getMemoryUsage(int i, int i2, int i3, int i4) {
        int memoryUsage;
        if (i == 1) {
            memoryUsage = LZMAEncoderFast.getMemoryUsage(i2, i3, i4);
        } else {
            if (i != 2) {
                throw new IllegalArgumentException();
            }
            memoryUsage = LZMAEncoderNormal.getMemoryUsage(i2, i3, i4);
        }
        return memoryUsage + 80;
    }

    private void updateAlignPrices() {
        this.alignPriceCount = 16;
        for (int i = 0; i < 16; i++) {
            this.alignPrices[i] = RangeEncoder.getReverseBitTreePrice(this.distAlign, i);
        }
    }

    private void updateDistPrices() {
        this.distPriceCount = 128;
        int i = 0;
        while (true) {
            if (i >= 4) {
                break;
            }
            for (int i2 = 0; i2 < this.distSlotPricesSize; i2++) {
                this.distSlotPrices[i][i2] = RangeEncoder.getBitTreePrice(this.distSlots[i], i2);
            }
            for (int i3 = 14; i3 < this.distSlotPricesSize; i3++) {
                int[] iArr = this.distSlotPrices[i];
                iArr[i3] = iArr[i3] + RangeEncoder.getDirectBitsPrice(((i3 >>> 1) - 1) - 4);
            }
            for (int i4 = 0; i4 < 4; i4++) {
                this.fullDistPrices[i][i4] = this.distSlotPrices[i][i4];
            }
            i++;
        }
        int i5 = 4;
        for (int i6 = 4; i6 < 14; i6++) {
            int i7 = ((i6 & 1) | 2) << ((i6 >>> 1) - 1);
            int i8 = i6 - 4;
            int length = this.distSpecial[i8].length;
            for (int i9 = 0; i9 < length; i9++) {
                int reverseBitTreePrice = RangeEncoder.getReverseBitTreePrice(this.distSpecial[i8], i5 - i7);
                for (int i10 = 0; i10 < 4; i10++) {
                    this.fullDistPrices[i10][i5] = this.distSlotPrices[i10][i6] + reverseBitTreePrice;
                }
                i5++;
            }
        }
    }

    public void encodeForLZMA1() throws IOException {
        if (this.lz.isStarted() || encodeInit()) {
            while (encodeSymbol()) {
            }
        }
    }

    public boolean encodeForLZMA2() {
        try {
            if (!this.lz.isStarted() && !encodeInit()) {
                return false;
            }
            while (this.uncompressedSize <= LZMA2_UNCOMPRESSED_LIMIT && this.rc.getPendingSize() <= LZMA2_COMPRESSED_LIMIT) {
                if (!encodeSymbol()) {
                    return false;
                }
            }
            return true;
        } catch (IOException unused) {
            throw new Error();
        }
    }

    public void encodeLZMA1EndMarker() throws IOException {
        int pos = (this.lz.getPos() - this.readAhead) & this.posMask;
        this.rc.encodeBit(this.isMatch[this.state.get()], pos, 1);
        this.rc.encodeBit(this.isRep, this.state.get(), 0);
        encodeMatch(-1, 2, pos);
    }

    int getAnyMatchPrice(State state, int i) {
        return RangeEncoder.getBitPrice(this.isMatch[state.get()][i], 1);
    }

    int getAnyRepPrice(int i, State state) {
        return i + RangeEncoder.getBitPrice(this.isRep[state.get()], 1);
    }

    public LZEncoder getLZEncoder() {
        return this.lz;
    }

    int getLongRepAndLenPrice(int i, int i2, State state, int i3) {
        return getLongRepPrice(getAnyRepPrice(getAnyMatchPrice(state, i3), state), i, state, i3) + this.repLenEncoder.getPrice(i2, i3);
    }

    int getLongRepPrice(int i, int i2, State state, int i3) {
        int bitPrice;
        if (i2 == 0) {
            bitPrice = RangeEncoder.getBitPrice(this.isRep0[state.get()], 0) + RangeEncoder.getBitPrice(this.isRep0Long[state.get()][i3], 1);
        } else {
            i += RangeEncoder.getBitPrice(this.isRep0[state.get()], 1);
            if (i2 != 1) {
                return i + RangeEncoder.getBitPrice(this.isRep1[state.get()], 1) + RangeEncoder.getBitPrice(this.isRep2[state.get()], i2 - 2);
            }
            bitPrice = RangeEncoder.getBitPrice(this.isRep1[state.get()], 0);
        }
        return i + bitPrice;
    }

    int getMatchAndLenPrice(int i, int i2, int i3, int i4) {
        int price = i + this.matchLenEncoder.getPrice(i3, i4);
        int distState = getDistState(i3);
        if (i2 < 128) {
            return price + this.fullDistPrices[distState][i2];
        }
        return price + this.distSlotPrices[distState][getDistSlot(i2)] + this.alignPrices[i2 & 15];
    }

    Matches getMatches() {
        this.readAhead++;
        return this.lz.getMatches();
    }

    abstract int getNextSymbol();

    int getNormalMatchPrice(int i, State state) {
        return i + RangeEncoder.getBitPrice(this.isRep[state.get()], 0);
    }

    int getShortRepPrice(int i, State state, int i2) {
        return i + RangeEncoder.getBitPrice(this.isRep0[state.get()], 0) + RangeEncoder.getBitPrice(this.isRep0Long[state.get()][i2], 0);
    }

    public int getUncompressedSize() {
        return this.uncompressedSize;
    }

    public void putArraysToCache(ArrayCache arrayCache) {
        this.lz.putArraysToCache(arrayCache);
    }

    @Override // org.tukaani.xz.lzma.LZMACoder
    public void reset() {
        super.reset();
        this.literalEncoder.reset();
        this.matchLenEncoder.reset();
        this.repLenEncoder.reset();
        this.distPriceCount = 0;
        this.alignPriceCount = 0;
        this.uncompressedSize += this.readAhead + 1;
        this.readAhead = -1;
    }

    public void resetUncompressedSize() {
        this.uncompressedSize = 0;
    }

    void skip(int i) {
        this.readAhead += i;
        this.lz.skip(i);
    }

    void updatePrices() {
        if (this.distPriceCount <= 0) {
            updateDistPrices();
        }
        if (this.alignPriceCount <= 0) {
            updateAlignPrices();
        }
        this.matchLenEncoder.updatePrices();
        this.repLenEncoder.updatePrices();
    }
}
