package com.google.android.exoplayer2.extractor.mp4;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
final class Sniffer {
    private static final int[] COMPATIBLE_BRANDS = {Util.getIntegerCodeForString("isom"), Util.getIntegerCodeForString("iso2"), Util.getIntegerCodeForString("iso3"), Util.getIntegerCodeForString("iso4"), Util.getIntegerCodeForString("iso5"), Util.getIntegerCodeForString("iso6"), Util.getIntegerCodeForString("avc1"), Util.getIntegerCodeForString("hvc1"), Util.getIntegerCodeForString("hev1"), Util.getIntegerCodeForString("mp41"), Util.getIntegerCodeForString("mp42"), Util.getIntegerCodeForString("3g2a"), Util.getIntegerCodeForString("3g2b"), Util.getIntegerCodeForString("3gr6"), Util.getIntegerCodeForString("3gs6"), Util.getIntegerCodeForString("3ge6"), Util.getIntegerCodeForString("3gg6"), Util.getIntegerCodeForString("M4V "), Util.getIntegerCodeForString("M4A "), Util.getIntegerCodeForString("f4v "), Util.getIntegerCodeForString("kddi"), Util.getIntegerCodeForString("M4VP"), Util.getIntegerCodeForString("qt  "), Util.getIntegerCodeForString("MSNV")};
    private static final int SEARCH_LENGTH = 4096;

    public static boolean sniffFragmented(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return sniffInternal(extractorInput, true);
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput) throws InterruptedException, IOException {
        return sniffInternal(extractorInput, false);
    }

    private static boolean sniffInternal(ExtractorInput extractorInput, boolean z) throws InterruptedException, IOException {
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        long length = extractorInput.getLength();
        long j = -1;
        long j2 = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        if (length != -1 && length <= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            j2 = length;
        }
        int i = (int) j2;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        boolean z6 = false;
        int i2 = 0;
        boolean z7 = false;
        while (true) {
            if (i2 < i) {
                parsableByteArray.reset(8);
                extractorInput.peekFully(parsableByteArray.data, z6 ? 1 : 0, 8);
                long unsignedInt = parsableByteArray.readUnsignedInt();
                int i3 = parsableByteArray.readInt();
                int i4 = 16;
                if (unsignedInt == 1) {
                    extractorInput.peekFully(parsableByteArray.data, 8, 8);
                    parsableByteArray.setLimit(16);
                    unsignedInt = parsableByteArray.readLong();
                } else {
                    if (unsignedInt == 0) {
                        long length2 = extractorInput.getLength();
                        if (length2 != j) {
                            unsignedInt = (length2 - extractorInput.getPeekPosition()) + ((long) 8);
                        }
                    }
                    i4 = 8;
                }
                if (length != -1 && ((long) i2) + unsignedInt > length) {
                    return z6;
                }
                long j3 = i4;
                if (unsignedInt < j3) {
                    return z6;
                }
                i2 += i4;
                if (i3 == Atom.TYPE_moov) {
                    i += (int) unsignedInt;
                    if (length != -1 && i > length) {
                        i = (int) length;
                    }
                } else {
                    if (i3 == Atom.TYPE_moof || i3 == Atom.TYPE_mvex) {
                        z2 = z6 ? 1 : 0;
                        z3 = true;
                        z4 = true;
                        return (z7 || z != z4) ? z2 : z3;
                    }
                    long j4 = length;
                    if ((((long) i2) + unsignedInt) - j3 >= i) {
                        z2 = false;
                    } else {
                        int i5 = (int) (unsignedInt - j3);
                        i2 += i5;
                        if (i3 != Atom.TYPE_ftyp) {
                            z5 = false;
                            if (i5 != 0) {
                                z7 = z7;
                                extractorInput.advancePeekPosition(i5);
                                z7 = z7;
                            }
                        } else {
                            if (i5 < 8) {
                                return false;
                            }
                            parsableByteArray.reset(i5);
                            extractorInput.peekFully(parsableByteArray.data, 0, i5);
                            int i6 = i5 / 4;
                            for (int i7 = 0; i7 < i6; i7++) {
                                if (i7 == 1) {
                                    parsableByteArray.skipBytes(4);
                                } else if (isCompatibleBrand(parsableByteArray.readInt())) {
                                    z7 = true;
                                    break;
                                }
                            }
                            if (!z7) {
                                return false;
                            }
                            z5 = false;
                            z7 = z7;
                        }
                        z7 = z7;
                        z6 = z5;
                        length = j4;
                    }
                }
                j = -1;
                z7 = z7;
            } else {
                z2 = z6 ? 1 : 0;
            }
            z3 = true;
            z4 = z2 ? 1 : 0;
            if (z7) {
            }
        }
    }

    private static boolean isCompatibleBrand(int i) {
        if ((i >>> 8) == Util.getIntegerCodeForString("3gp")) {
            return true;
        }
        for (int i2 : COMPATIBLE_BRANDS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private Sniffer() {
    }
}
