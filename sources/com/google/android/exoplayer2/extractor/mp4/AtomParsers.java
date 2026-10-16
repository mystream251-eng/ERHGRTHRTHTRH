package com.google.android.exoplayer2.extractor.mp4;

import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.video.AvcConfig;
import com.google.android.exoplayer2.video.HevcConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class AtomParsers {
    private static final int MAX_GAPLESS_TRIM_SIZE_SAMPLES = 3;
    private static final String TAG = "AtomParsers";
    private static final int TYPE_vide = Util.getIntegerCodeForString("vide");
    private static final int TYPE_soun = Util.getIntegerCodeForString("soun");
    private static final int TYPE_text = Util.getIntegerCodeForString("text");
    private static final int TYPE_sbtl = Util.getIntegerCodeForString("sbtl");
    private static final int TYPE_subt = Util.getIntegerCodeForString("subt");
    private static final int TYPE_clcp = Util.getIntegerCodeForString("clcp");
    private static final int TYPE_meta = Util.getIntegerCodeForString("meta");
    private static final int TYPE_mdta = Util.getIntegerCodeForString("mdta");
    private static final byte[] opusMagic = Util.getUtf8Bytes("OpusHead");

    private interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    public static Track parseTrak(Atom.ContainerAtom containerAtom, Atom.LeafAtom leafAtom, long j, DrmInitData drmInitData, boolean z, boolean z2) throws ParserException {
        long[] jArr;
        long[] jArr2;
        Atom.ContainerAtom containerAtomOfType = containerAtom.getContainerAtomOfType(Atom.TYPE_mdia);
        int trackTypeForHdlr = getTrackTypeForHdlr(parseHdlr(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_hdlr).data));
        if (trackTypeForHdlr == -1) {
            return null;
        }
        TkhdData tkhd = parseTkhd(containerAtom.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        long jScaleLargeTimestamp = C.TIME_UNSET;
        long j2 = j == C.TIME_UNSET ? tkhd.duration : j;
        long mvhd = parseMvhd(leafAtom.data);
        if (j2 != C.TIME_UNSET) {
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(j2, 1000000L, mvhd);
        }
        long j3 = jScaleLargeTimestamp;
        Atom.ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        Pair<Long, String> mdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdData stsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(Atom.TYPE_stsd).data, tkhd.id, tkhd.rotationDegrees, (String) mdhd.second, drmInitData, z2);
        if (z) {
            jArr = null;
            jArr2 = null;
        } else {
            Pair<long[], long[]> edts = parseEdts(containerAtom.getContainerAtomOfType(Atom.TYPE_edts));
            long[] jArr3 = (long[]) edts.first;
            jArr2 = (long[]) edts.second;
            jArr = jArr3;
        }
        if (stsd.format == null) {
            return null;
        }
        return new Track(tkhd.id, trackTypeForHdlr, ((Long) mdhd.first).longValue(), mvhd, j3, stsd.format, stsd.requiredSampleTransformation, stsd.trackEncryptionBoxes, stsd.nalUnitLengthFieldLength, jArr, jArr2);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0229  */
    /* JADX WARN: Code duplicated, block: B:101:0x022c  */
    /* JADX WARN: Code duplicated, block: B:104:0x023f  */
    /* JADX WARN: Code duplicated, block: B:107:0x0250 A[LOOP:9: B:105:0x024a->B:107:0x0250, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:146:0x0380  */
    /* JADX WARN: Code duplicated, block: B:147:0x0382  */
    /* JADX WARN: Code duplicated, block: B:151:0x0396  */
    /* JADX WARN: Code duplicated, block: B:153:0x03a0  */
    /* JADX WARN: Code duplicated, block: B:161:0x03e0  */
    /* JADX WARN: Code duplicated, block: B:162:0x03e2  */
    /* JADX WARN: Code duplicated, block: B:164:0x03e9  */
    /* JADX WARN: Code duplicated, block: B:169:0x0401  */
    /* JADX WARN: Code duplicated, block: B:172:0x0406  */
    /* JADX WARN: Code duplicated, block: B:173:0x0409  */
    /* JADX WARN: Code duplicated, block: B:175:0x040c  */
    /* JADX WARN: Code duplicated, block: B:176:0x040f  */
    /* JADX WARN: Code duplicated, block: B:178:0x0413  */
    /* JADX WARN: Code duplicated, block: B:180:0x0417  */
    /* JADX WARN: Code duplicated, block: B:181:0x041a  */
    /* JADX WARN: Code duplicated, block: B:185:0x0426  */
    /* JADX WARN: Code duplicated, block: B:187:0x0432  */
    /* JADX WARN: Code duplicated, block: B:188:0x0446  */
    /* JADX WARN: Code duplicated, block: B:191:0x0453  */
    /* JADX WARN: Code duplicated, block: B:196:0x0484  */
    /* JADX WARN: Code duplicated, block: B:205:0x01ba A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:206:0x0129 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:210:0x0121 A[EDGE_INSN: B:210:0x0121->B:54:0x0121 BREAK  A[LOOP:1: B:50:0x0104->B:53:0x010c], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:213:0x01d5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:214:0x01cd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:43:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:46:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:49:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:51:0x0106  */
    /* JADX WARN: Code duplicated, block: B:53:0x010c A[LOOP:1: B:50:0x0104->B:53:0x010c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:57:0x0145 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:58:0x0147 A[ADDED_TO_REGION, LOOP:2: B:58:0x0147->B:60:0x014b, LOOP_START, PHI: r16 r21 r24
  0x0147: PHI (r16v11 int) = (r16v7 int), (r16v12 int) binds: [B:57:0x0145, B:60:0x014b] A[DONT_GENERATE, DONT_INLINE]
  0x0147: PHI (r21v15 int) = (r21v11 int), (r21v16 int) binds: [B:57:0x0145, B:60:0x014b] A[DONT_GENERATE, DONT_INLINE]
  0x0147: PHI (r24v4 int) = (r24v2 int), (r24v6 int) binds: [B:57:0x0145, B:60:0x014b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:64:0x0166  */
    /* JADX WARN: Code duplicated, block: B:67:0x0170  */
    /* JADX WARN: Code duplicated, block: B:68:0x0172  */
    /* JADX WARN: Code duplicated, block: B:71:0x0177  */
    /* JADX WARN: Code duplicated, block: B:73:0x017e  */
    /* JADX WARN: Code duplicated, block: B:78:0x019b  */
    /* JADX WARN: Code duplicated, block: B:83:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:86:0x01cf A[LOOP:3: B:82:0x01c5->B:86:0x01cf, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:97:0x01e8  */
    public static TrackSampleTable parseStbl(Track track, Atom.ContainerAtom containerAtom, GaplessInfoHolder gaplessInfoHolder) throws ParserException {
        SampleSizeBox stz2SampleSizeBox;
        boolean z;
        int unsignedIntToInt;
        int unsignedIntToInt2;
        boolean z2;
        Track track2;
        long[] jArr;
        int[] iArr;
        long[] jArr2;
        int[] iArr2;
        long j;
        int i;
        int i2;
        int[] iArr3;
        long[] jArr3;
        int i3;
        int[] iArr4;
        boolean z3;
        int[] iArr5;
        int[] iArr6;
        int i4;
        boolean z4;
        int i5;
        int i6;
        int[] iArr7;
        int i7;
        int i8;
        boolean z5;
        long[] jArr4;
        int[] iArr8;
        int[] iArr9;
        long[] jArr5;
        int i9;
        long j2;
        long j3;
        int i10;
        int i11;
        int[] iArr10;
        int[] iArr11;
        int i12;
        int i13;
        int i14;
        long j4;
        boolean z6;
        long[] jArrCopyOf;
        int[] iArrCopyOf;
        long[] jArrCopyOf2;
        int[] iArrCopyOf2;
        int unsignedIntToInt3;
        long j5;
        long j6;
        int i15;
        int i16;
        int i17;
        int i18;
        int unsignedIntToInt4;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25;
        int i26;
        boolean z7;
        int i27;
        String str;
        long j7;
        int i28;
        boolean zMoveNext;
        int i29;
        int i30;
        int i31;
        int unsignedIntToInt5;
        int i32;
        int i33;
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_stsz);
        if (leafAtomOfType != null) {
            stz2SampleSizeBox = new StszSampleSizeBox(leafAtomOfType);
        } else {
            Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_stz2);
            if (leafAtomOfType2 == null) {
                throw new ParserException("Track has no sample table size information");
            }
            stz2SampleSizeBox = new Stz2SampleSizeBox(leafAtomOfType2);
        }
        int sampleCount = stz2SampleSizeBox.getSampleCount();
        if (sampleCount == 0) {
            return new TrackSampleTable(track, new long[0], new int[0], 0, new long[0], new int[0], C.TIME_UNSET);
        }
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_stco);
        if (leafAtomOfType3 == null) {
            leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_co64);
            z = true;
        } else {
            z = false;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType3.data;
        ParsableByteArray parsableByteArray2 = containerAtom.getLeafAtomOfType(Atom.TYPE_stsc).data;
        ParsableByteArray parsableByteArray3 = containerAtom.getLeafAtomOfType(Atom.TYPE_stts).data;
        Atom.LeafAtom leafAtomOfType4 = containerAtom.getLeafAtomOfType(Atom.TYPE_stss);
        ParsableByteArray parsableByteArray4 = null;
        ParsableByteArray parsableByteArray5 = leafAtomOfType4 != null ? leafAtomOfType4.data : null;
        Atom.LeafAtom leafAtomOfType5 = containerAtom.getLeafAtomOfType(Atom.TYPE_ctts);
        ParsableByteArray parsableByteArray6 = leafAtomOfType5 != null ? leafAtomOfType5.data : null;
        ChunkIterator chunkIterator = new ChunkIterator(parsableByteArray2, parsableByteArray, z);
        parsableByteArray3.setPosition(12);
        int unsignedIntToInt6 = parsableByteArray3.readUnsignedIntToInt() - 1;
        int unsignedIntToInt7 = parsableByteArray3.readUnsignedIntToInt();
        int unsignedIntToInt8 = parsableByteArray3.readUnsignedIntToInt();
        if (parsableByteArray6 != null) {
            parsableByteArray6.setPosition(12);
            unsignedIntToInt = parsableByteArray6.readUnsignedIntToInt();
        } else {
            unsignedIntToInt = 0;
        }
        int unsignedIntToInt9 = -1;
        if (parsableByteArray5 != null) {
            parsableByteArray5.setPosition(12);
            unsignedIntToInt2 = parsableByteArray5.readUnsignedIntToInt();
            unsignedIntToInt9 = unsignedIntToInt2 > 0 ? parsableByteArray5.readUnsignedIntToInt() - 1 : -1;
            if (!stz2SampleSizeBox.isFixedSampleSize() && MimeTypes.AUDIO_RAW.equals(track.format.sampleMimeType) && unsignedIntToInt6 == 0 && unsignedIntToInt == 0 && unsignedIntToInt2 == 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (!z2) {
                jArrCopyOf = new long[sampleCount];
                iArrCopyOf = new int[sampleCount];
                jArrCopyOf2 = new long[sampleCount];
                int i34 = unsignedIntToInt2;
                iArrCopyOf2 = new int[sampleCount];
                unsignedIntToInt3 = unsignedIntToInt9;
                j5 = 0;
                j6 = 0;
                i15 = 0;
                i16 = 0;
                i17 = 0;
                i18 = 0;
                unsignedIntToInt4 = 0;
                i19 = unsignedIntToInt;
                i20 = unsignedIntToInt8;
                i21 = unsignedIntToInt7;
                i22 = unsignedIntToInt6;
                i23 = i34;
                while (true) {
                    i24 = i22;
                    if (i15 < sampleCount) {
                        i25 = i21;
                        i26 = i18;
                        break;
                    }
                    j7 = j6;
                    i28 = i18;
                    zMoveNext = true;
                    while (i28 == 0) {
                        zMoveNext = chunkIterator.moveNext();
                        if (zMoveNext) {
                            break;
                        }
                        int i35 = i21;
                        long j8 = chunkIterator.offset;
                        i28 = chunkIterator.numSamples;
                        j7 = j8;
                        i21 = i35;
                        i20 = i20;
                        sampleCount = sampleCount;
                    }
                    i29 = sampleCount;
                    i25 = i21;
                    i30 = i20;
                    if (!zMoveNext) {
                        Log.w(TAG, "Unexpected end of chunk data");
                        jArrCopyOf = Arrays.copyOf(jArrCopyOf, i15);
                        iArrCopyOf = Arrays.copyOf(iArrCopyOf, i15);
                        jArrCopyOf2 = Arrays.copyOf(jArrCopyOf2, i15);
                        iArrCopyOf2 = Arrays.copyOf(iArrCopyOf2, i15);
                        sampleCount = i15;
                        i26 = i28;
                        break;
                    }
                    if (parsableByteArray6 != null) {
                        while (unsignedIntToInt4 == 0 && i19 > 0) {
                            unsignedIntToInt4 = parsableByteArray6.readUnsignedIntToInt();
                            i17 = parsableByteArray6.readInt();
                            i19--;
                        }
                        unsignedIntToInt4--;
                    }
                    int i36 = i17;
                    jArrCopyOf[i15] = j7;
                    iArrCopyOf[i15] = stz2SampleSizeBox.readNextSampleSize();
                    if (iArrCopyOf[i15] > i16) {
                        i16 = iArrCopyOf[i15];
                    }
                    jArrCopyOf2[i15] = j5 + ((long) i36);
                    if (parsableByteArray4 == null) {
                        i31 = 1;
                    } else {
                        i31 = 0;
                    }
                    iArrCopyOf2[i15] = i31;
                    if (i15 == unsignedIntToInt3) {
                        iArrCopyOf2[i15] = 1;
                        i23--;
                        if (i23 > 0) {
                            unsignedIntToInt3 = parsableByteArray4.readUnsignedIntToInt() - 1;
                        }
                    }
                    int i37 = unsignedIntToInt3;
                    j5 += (long) i30;
                    unsignedIntToInt5 = i25 - 1;
                    if (unsignedIntToInt5 == 0 || i24 <= 0) {
                        i32 = i30;
                        i33 = i24;
                    } else {
                        unsignedIntToInt5 = parsableByteArray3.readUnsignedIntToInt();
                        i32 = parsableByteArray3.readInt();
                        i33 = i24 - 1;
                    }
                    int i38 = unsignedIntToInt5;
                    long j9 = j7 + ((long) iArrCopyOf[i15]);
                    i18 = i28 - 1;
                    i15++;
                    j6 = j9;
                    unsignedIntToInt3 = i37;
                    i20 = i32;
                    sampleCount = i29;
                    i17 = i36;
                    i22 = i33;
                    i21 = i38;
                }
                j = j5 + ((long) i17);
                while (true) {
                    if (i19 > 0) {
                        z7 = true;
                        break;
                    }
                    if (parsableByteArray6.readUnsignedIntToInt() != 0) {
                        z7 = false;
                        break;
                    }
                    parsableByteArray6.readInt();
                    i19--;
                }
                if (i23 != 0 && i25 == 0 && i26 == 0 && i24 == 0) {
                    i27 = unsignedIntToInt4;
                    if (i27 == 0 && z7) {
                        track2 = track;
                    }
                    i = sampleCount;
                    jArr3 = jArrCopyOf;
                    iArr2 = iArrCopyOf2;
                    jArr2 = jArrCopyOf2;
                    i2 = i16;
                    iArr3 = iArrCopyOf;
                } else {
                    i27 = unsignedIntToInt4;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("Inconsistent stbl box for track ");
                track2 = track;
                sb.append(track2.id);
                sb.append(": remainingSynchronizationSamples ");
                sb.append(i23);
                sb.append(", remainingSamplesAtTimestampDelta ");
                sb.append(i25);
                sb.append(", remainingSamplesInChunk ");
                sb.append(i26);
                sb.append(", remainingTimestampDeltaChanges ");
                sb.append(i24);
                sb.append(", remainingSamplesAtTimestampOffset ");
                sb.append(i27);
                if (z7) {
                    str = "";
                } else {
                    str = ", ctts invalid";
                }
                sb.append(str);
                Log.w(TAG, sb.toString());
                i = sampleCount;
                jArr3 = jArrCopyOf;
                iArr2 = iArrCopyOf2;
                jArr2 = jArrCopyOf2;
                i2 = i16;
                iArr3 = iArrCopyOf;
            } else {
                track2 = track;
                jArr = new long[chunkIterator.length];
                iArr = new int[chunkIterator.length];
                while (chunkIterator.moveNext()) {
                    jArr[chunkIterator.index] = chunkIterator.offset;
                    iArr[chunkIterator.index] = chunkIterator.numSamples;
                }
                FixedSampleSizeRechunker.Results resultsRechunk = FixedSampleSizeRechunker.rechunk(Util.getPcmFrameSize(track2.format.pcmEncoding, track2.format.channelCount), jArr, iArr, unsignedIntToInt8);
                long[] jArr6 = resultsRechunk.offsets;
                int[] iArr12 = resultsRechunk.sizes;
                int i39 = resultsRechunk.maximumSize;
                jArr2 = resultsRechunk.timestamps;
                iArr2 = resultsRechunk.flags;
                j = resultsRechunk.duration;
                i = sampleCount;
                i2 = i39;
                iArr3 = iArr12;
                jArr3 = jArr6;
            }
            long jScaleLargeTimestamp = Util.scaleLargeTimestamp(j, 1000000L, track2.timescale);
            if (track2.editListDurations != null || gaplessInfoHolder.hasGaplessInfo()) {
                Util.scaleLargeTimestampsInPlace(jArr2, 1000000L, track2.timescale);
                return new TrackSampleTable(track, jArr3, iArr3, i2, jArr2, iArr2, jScaleLargeTimestamp);
            }
            if (track2.editListDurations.length == 1 && track2.type == 1 && jArr2.length >= 2) {
                long j10 = track2.editListMediaTimes[0];
                long jScaleLargeTimestamp2 = j10 + Util.scaleLargeTimestamp(track2.editListDurations[0], track2.timescale, track2.movieTimescale);
                i3 = i;
                if (canApplyEditWithGaplessInfo(jArr2, j, j10, jScaleLargeTimestamp2)) {
                    long jScaleLargeTimestamp3 = Util.scaleLargeTimestamp(j10 - jArr2[0], track2.format.sampleRate, track2.timescale);
                    iArr4 = iArr2;
                    long jScaleLargeTimestamp4 = Util.scaleLargeTimestamp(j - jScaleLargeTimestamp2, track2.format.sampleRate, track2.timescale);
                    if ((jScaleLargeTimestamp3 != 0 || jScaleLargeTimestamp4 != 0) && jScaleLargeTimestamp3 <= 2147483647L && jScaleLargeTimestamp4 <= 2147483647L) {
                        gaplessInfoHolder.encoderDelay = (int) jScaleLargeTimestamp3;
                        gaplessInfoHolder.encoderPadding = (int) jScaleLargeTimestamp4;
                        Util.scaleLargeTimestampsInPlace(jArr2, 1000000L, track2.timescale);
                        return new TrackSampleTable(track, jArr3, iArr3, i2, jArr2, iArr4, Util.scaleLargeTimestamp(track2.editListDurations[0], 1000000L, track2.movieTimescale));
                    }
                }
                if (track2.editListDurations.length != 1 && track2.editListDurations[0] == 0) {
                    long j11 = track2.editListMediaTimes[0];
                    for (int i40 = 0; i40 < jArr2.length; i40++) {
                        jArr2[i40] = Util.scaleLargeTimestamp(jArr2[i40] - j11, 1000000L, track2.timescale);
                    }
                    return new TrackSampleTable(track, jArr3, iArr3, i2, jArr2, iArr4, Util.scaleLargeTimestamp(j - j11, 1000000L, track2.timescale));
                }
                if (track2.type == 1) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                iArr5 = new int[track2.editListDurations.length];
                iArr6 = new int[track2.editListDurations.length];
                i4 = 0;
                z4 = false;
                i5 = 0;
                i6 = 0;
                while (i4 < track2.editListDurations.length) {
                    j4 = track2.editListMediaTimes[i4];
                    if (j4 != -1) {
                        long jScaleLargeTimestamp5 = Util.scaleLargeTimestamp(track2.editListDurations[i4], track2.timescale, track2.movieTimescale);
                        iArr5[i4] = Util.binarySearchCeil(jArr2, j4, true, true);
                        iArr6[i4] = Util.binarySearchCeil(jArr2, j4 + jScaleLargeTimestamp5, z3, false);
                        while (iArr5[i4] < iArr6[i4] && (iArr4[iArr5[i4]] & 1) == 0) {
                            iArr5[i4] = iArr5[i4] + 1;
                        }
                        i5 += iArr6[i4] - iArr5[i4];
                        if (i6 != iArr5[i4]) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        i6 = iArr6[i4];
                        z4 = z6 | z4;
                    }
                    i4++;
                    iArr3 = iArr3;
                    i2 = i2;
                }
                iArr7 = iArr3;
                i7 = i2;
                i8 = 0;
                z5 = z4 | (i5 != i3);
                if (z5) {
                    jArr4 = new long[i5];
                } else {
                    jArr4 = jArr3;
                }
                if (z5) {
                    iArr8 = new int[i5];
                } else {
                    iArr8 = iArr7;
                }
                if (z5) {
                    i7 = 0;
                }
                if (z5) {
                    iArr9 = new int[i5];
                } else {
                    iArr9 = iArr4;
                }
                jArr5 = new long[i5];
                i9 = 0;
                j2 = 0;
                while (i8 < track2.editListDurations.length) {
                    j3 = track2.editListMediaTimes[i8];
                    i10 = iArr5[i8];
                    i11 = iArr6[i8];
                    int[] iArr13 = iArr5;
                    if (z5) {
                        int i41 = i11 - i10;
                        System.arraycopy(jArr3, i10, jArr4, i9, i41);
                        iArr10 = iArr7;
                        System.arraycopy(iArr10, i10, iArr8, i9, i41);
                        iArr11 = iArr4;
                        System.arraycopy(iArr11, i10, iArr9, i9, i41);
                    } else {
                        iArr10 = iArr7;
                        iArr11 = iArr4;
                    }
                    i12 = i10;
                    i13 = i7;
                    while (i12 < i11) {
                        int[] iArr14 = iArr11;
                        int[] iArr15 = iArr10;
                        int[] iArr16 = iArr9;
                        i14 = i13;
                        int i42 = i11;
                        jArr5[i9] = Util.scaleLargeTimestamp(j2, 1000000L, track2.movieTimescale) + Util.scaleLargeTimestamp(jArr2[i12] - j3, 1000000L, track2.timescale);
                        if (z5 || iArr8[i9] <= i14) {
                            i13 = i14;
                        } else {
                            i13 = iArr15[i12];
                        }
                        i9++;
                        i12++;
                        iArr11 = iArr14;
                        i11 = i42;
                        iArr10 = iArr15;
                        iArr9 = iArr16;
                    }
                    iArr4 = iArr11;
                    int[] iArr17 = iArr10;
                    j2 += track2.editListDurations[i8];
                    i8++;
                    i7 = i13;
                    iArr5 = iArr13;
                    iArr6 = iArr6;
                    jArr3 = jArr3;
                    iArr7 = iArr17;
                    iArr9 = iArr9;
                }
                return new TrackSampleTable(track, jArr4, iArr8, i7, jArr5, iArr9, Util.scaleLargeTimestamp(j2, 1000000L, track2.movieTimescale));
            }
            i3 = i;
            iArr4 = iArr2;
            if (track2.editListDurations.length != 1) {
            }
            if (track2.type == 1) {
                z3 = true;
            } else {
                z3 = false;
            }
            iArr5 = new int[track2.editListDurations.length];
            iArr6 = new int[track2.editListDurations.length];
            i4 = 0;
            z4 = false;
            i5 = 0;
            i6 = 0;
            while (i4 < track2.editListDurations.length) {
                j4 = track2.editListMediaTimes[i4];
                if (j4 != -1) {
                    long jScaleLargeTimestamp6 = Util.scaleLargeTimestamp(track2.editListDurations[i4], track2.timescale, track2.movieTimescale);
                    iArr5[i4] = Util.binarySearchCeil(jArr2, j4, true, true);
                    iArr6[i4] = Util.binarySearchCeil(jArr2, j4 + jScaleLargeTimestamp6, z3, false);
                    while (iArr5[i4] < iArr6[i4]) {
                        iArr5[i4] = iArr5[i4] + 1;
                    }
                    i5 += iArr6[i4] - iArr5[i4];
                    if (i6 != iArr5[i4]) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    i6 = iArr6[i4];
                    z4 = z6 | z4;
                }
                i4++;
                iArr3 = iArr3;
                i2 = i2;
            }
            iArr7 = iArr3;
            i7 = i2;
            i8 = 0;
            z5 = z4 | (i5 != i3);
            if (z5) {
                jArr4 = new long[i5];
            } else {
                jArr4 = jArr3;
            }
            if (z5) {
                iArr8 = new int[i5];
            } else {
                iArr8 = iArr7;
            }
            if (z5) {
                i7 = 0;
            }
            if (z5) {
                iArr9 = new int[i5];
            } else {
                iArr9 = iArr4;
            }
            jArr5 = new long[i5];
            i9 = 0;
            j2 = 0;
            while (i8 < track2.editListDurations.length) {
                j3 = track2.editListMediaTimes[i8];
                i10 = iArr5[i8];
                i11 = iArr6[i8];
                int[] iArr18 = iArr5;
                if (z5) {
                    int i43 = i11 - i10;
                    System.arraycopy(jArr3, i10, jArr4, i9, i43);
                    iArr10 = iArr7;
                    System.arraycopy(iArr10, i10, iArr8, i9, i43);
                    iArr11 = iArr4;
                    System.arraycopy(iArr11, i10, iArr9, i9, i43);
                } else {
                    iArr10 = iArr7;
                    iArr11 = iArr4;
                }
                i12 = i10;
                i13 = i7;
                while (i12 < i11) {
                    int[] iArr19 = iArr11;
                    int[] iArr110 = iArr10;
                    int[] iArr111 = iArr9;
                    i14 = i13;
                    int i44 = i11;
                    jArr5[i9] = Util.scaleLargeTimestamp(j2, 1000000L, track2.movieTimescale) + Util.scaleLargeTimestamp(jArr2[i12] - j3, 1000000L, track2.timescale);
                    if (z5) {
                        i13 = i14;
                    } else {
                        i13 = i14;
                    }
                    i9++;
                    i12++;
                    iArr11 = iArr19;
                    i11 = i44;
                    iArr10 = iArr110;
                    iArr9 = iArr111;
                }
                iArr4 = iArr11;
                int[] iArr112 = iArr10;
                j2 += track2.editListDurations[i8];
                i8++;
                i7 = i13;
                iArr5 = iArr18;
                iArr6 = iArr6;
                jArr3 = jArr3;
                iArr7 = iArr112;
                iArr9 = iArr9;
            }
            return new TrackSampleTable(track, jArr4, iArr8, i7, jArr5, iArr9, Util.scaleLargeTimestamp(j2, 1000000L, track2.movieTimescale));
        }
        unsignedIntToInt2 = 0;
        parsableByteArray4 = parsableByteArray5;
        if (!stz2SampleSizeBox.isFixedSampleSize()) {
            z2 = false;
        } else {
            z2 = false;
        }
        if (!z2) {
            jArrCopyOf = new long[sampleCount];
            iArrCopyOf = new int[sampleCount];
            jArrCopyOf2 = new long[sampleCount];
            int i310 = unsignedIntToInt2;
            iArrCopyOf2 = new int[sampleCount];
            unsignedIntToInt3 = unsignedIntToInt9;
            j5 = 0;
            j6 = 0;
            i15 = 0;
            i16 = 0;
            i17 = 0;
            i18 = 0;
            unsignedIntToInt4 = 0;
            i19 = unsignedIntToInt;
            i20 = unsignedIntToInt8;
            i21 = unsignedIntToInt7;
            i22 = unsignedIntToInt6;
            i23 = i310;
            while (true) {
                i24 = i22;
                if (i15 < sampleCount) {
                    i25 = i21;
                    i26 = i18;
                    break;
                }
                j7 = j6;
                i28 = i18;
                zMoveNext = true;
                while (i28 == 0) {
                    zMoveNext = chunkIterator.moveNext();
                    if (zMoveNext) {
                        break;
                        break;
                    }
                    int i311 = i21;
                    long j12 = chunkIterator.offset;
                    i28 = chunkIterator.numSamples;
                    j7 = j12;
                    i21 = i311;
                    i20 = i20;
                    sampleCount = sampleCount;
                }
                i29 = sampleCount;
                i25 = i21;
                i30 = i20;
                if (!zMoveNext) {
                    Log.w(TAG, "Unexpected end of chunk data");
                    jArrCopyOf = Arrays.copyOf(jArrCopyOf, i15);
                    iArrCopyOf = Arrays.copyOf(iArrCopyOf, i15);
                    jArrCopyOf2 = Arrays.copyOf(jArrCopyOf2, i15);
                    iArrCopyOf2 = Arrays.copyOf(iArrCopyOf2, i15);
                    sampleCount = i15;
                    i26 = i28;
                    break;
                }
                if (parsableByteArray6 != null) {
                    while (unsignedIntToInt4 == 0) {
                        unsignedIntToInt4 = parsableByteArray6.readUnsignedIntToInt();
                        i17 = parsableByteArray6.readInt();
                        i19--;
                    }
                    unsignedIntToInt4--;
                }
                int i312 = i17;
                jArrCopyOf[i15] = j7;
                iArrCopyOf[i15] = stz2SampleSizeBox.readNextSampleSize();
                if (iArrCopyOf[i15] > i16) {
                    i16 = iArrCopyOf[i15];
                }
                jArrCopyOf2[i15] = j5 + ((long) i312);
                if (parsableByteArray4 == null) {
                    i31 = 1;
                } else {
                    i31 = 0;
                }
                iArrCopyOf2[i15] = i31;
                if (i15 == unsignedIntToInt3) {
                    iArrCopyOf2[i15] = 1;
                    i23--;
                    if (i23 > 0) {
                        unsignedIntToInt3 = parsableByteArray4.readUnsignedIntToInt() - 1;
                    }
                }
                int i313 = unsignedIntToInt3;
                j5 += (long) i30;
                unsignedIntToInt5 = i25 - 1;
                if (unsignedIntToInt5 == 0) {
                    i32 = i30;
                    i33 = i24;
                } else {
                    i32 = i30;
                    i33 = i24;
                }
                int i314 = unsignedIntToInt5;
                long j13 = j7 + ((long) iArrCopyOf[i15]);
                i18 = i28 - 1;
                i15++;
                j6 = j13;
                unsignedIntToInt3 = i313;
                i20 = i32;
                sampleCount = i29;
                i17 = i312;
                i22 = i33;
                i21 = i314;
            }
            j = j5 + ((long) i17);
            while (true) {
                if (i19 > 0) {
                    z7 = true;
                    break;
                }
                if (parsableByteArray6.readUnsignedIntToInt() != 0) {
                    z7 = false;
                    break;
                }
                parsableByteArray6.readInt();
                i19--;
            }
            if (i23 != 0) {
                i27 = unsignedIntToInt4;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Inconsistent stbl box for track ");
                track2 = track;
                sb2.append(track2.id);
                sb2.append(": remainingSynchronizationSamples ");
                sb2.append(i23);
                sb2.append(", remainingSamplesAtTimestampDelta ");
                sb2.append(i25);
                sb2.append(", remainingSamplesInChunk ");
                sb2.append(i26);
                sb2.append(", remainingTimestampDeltaChanges ");
                sb2.append(i24);
                sb2.append(", remainingSamplesAtTimestampOffset ");
                sb2.append(i27);
                if (z7) {
                    str = ", ctts invalid";
                } else {
                    str = "";
                }
                sb2.append(str);
                Log.w(TAG, sb2.toString());
            } else {
                i27 = unsignedIntToInt4;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Inconsistent stbl box for track ");
                track2 = track;
                sb3.append(track2.id);
                sb3.append(": remainingSynchronizationSamples ");
                sb3.append(i23);
                sb3.append(", remainingSamplesAtTimestampDelta ");
                sb3.append(i25);
                sb3.append(", remainingSamplesInChunk ");
                sb3.append(i26);
                sb3.append(", remainingTimestampDeltaChanges ");
                sb3.append(i24);
                sb3.append(", remainingSamplesAtTimestampOffset ");
                sb3.append(i27);
                if (z7) {
                    str = ", ctts invalid";
                } else {
                    str = "";
                }
                sb3.append(str);
                Log.w(TAG, sb3.toString());
            }
            i = sampleCount;
            jArr3 = jArrCopyOf;
            iArr2 = iArrCopyOf2;
            jArr2 = jArrCopyOf2;
            i2 = i16;
            iArr3 = iArrCopyOf;
        } else {
            track2 = track;
            jArr = new long[chunkIterator.length];
            iArr = new int[chunkIterator.length];
            while (chunkIterator.moveNext()) {
                jArr[chunkIterator.index] = chunkIterator.offset;
                iArr[chunkIterator.index] = chunkIterator.numSamples;
            }
            FixedSampleSizeRechunker.Results resultsRechunk2 = FixedSampleSizeRechunker.rechunk(Util.getPcmFrameSize(track2.format.pcmEncoding, track2.format.channelCount), jArr, iArr, unsignedIntToInt8);
            long[] jArr7 = resultsRechunk2.offsets;
            int[] iArr113 = resultsRechunk2.sizes;
            int i315 = resultsRechunk2.maximumSize;
            jArr2 = resultsRechunk2.timestamps;
            iArr2 = resultsRechunk2.flags;
            j = resultsRechunk2.duration;
            i = sampleCount;
            i2 = i315;
            iArr3 = iArr113;
            jArr3 = jArr7;
        }
        long jScaleLargeTimestamp7 = Util.scaleLargeTimestamp(j, 1000000L, track2.timescale);
        if (track2.editListDurations != null) {
        }
        Util.scaleLargeTimestampsInPlace(jArr2, 1000000L, track2.timescale);
        return new TrackSampleTable(track, jArr3, iArr3, i2, jArr2, iArr2, jScaleLargeTimestamp7);
    }

    public static Metadata parseUdta(Atom.LeafAtom leafAtom, boolean z) {
        if (z) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int i = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_meta) {
                parsableByteArray.setPosition(position);
                return parseUdtaMeta(parsableByteArray, position + i);
            }
            parsableByteArray.setPosition(position + i);
        }
        return null;
    }

    public static Metadata parseMdtaFromMeta(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_hdlr);
        Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_keys);
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_ilst);
        if (leafAtomOfType == null || leafAtomOfType2 == null || leafAtomOfType3 == null || parseHdlr(leafAtomOfType.data) != TYPE_mdta) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType2.data;
        parsableByteArray.setPosition(12);
        int i = parsableByteArray.readInt();
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            strArr[i2] = parsableByteArray.readString(i3 - 8);
        }
        ParsableByteArray parsableByteArray2 = leafAtomOfType3.data;
        parsableByteArray2.setPosition(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray2.bytesLeft() > 8) {
            int position = parsableByteArray2.getPosition();
            int i4 = parsableByteArray2.readInt();
            int i5 = parsableByteArray2.readInt() - 1;
            if (i5 >= 0 && i5 < i) {
                MdtaMetadataEntry mdtaMetadataEntryFromIlst = MetadataUtil.parseMdtaMetadataEntryFromIlst(parsableByteArray2, position + i4, strArr[i5]);
                if (mdtaMetadataEntryFromIlst != null) {
                    arrayList.add(mdtaMetadataEntryFromIlst);
                }
            } else {
                Log.w(TAG, "Skipped metadata with unknown key index: " + i5);
            }
            parsableByteArray2.setPosition(position + i4);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    private static Metadata parseUdtaMeta(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int i2 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_ilst) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + i2);
            }
            parsableByteArray.setPosition(position + i2);
        }
        return null;
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i) {
            Metadata.Entry ilstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (ilstElement != null) {
                arrayList.add(ilstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        parsableByteArray.skipBytes(Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0 ? 16 : 8);
        return parsableByteArray.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        boolean z;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 8 : 16);
        int i = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        int i2 = fullAtomVersion == 0 ? 4 : 8;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i4 >= i2) {
                z = true;
                break;
            }
            if (parsableByteArray.data[position + i4] != -1) {
                z = false;
                break;
            }
            i4++;
        }
        long j = C.TIME_UNSET;
        if (z) {
            parsableByteArray.skipBytes(i2);
        } else {
            long unsignedInt = fullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (unsignedInt != 0) {
                j = unsignedInt;
            }
        }
        parsableByteArray.skipBytes(16);
        int i5 = parsableByteArray.readInt();
        int i6 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int i7 = parsableByteArray.readInt();
        int i8 = parsableByteArray.readInt();
        if (i5 == 0 && i6 == 65536 && i7 == -65536 && i8 == 0) {
            i3 = 90;
        } else if (i5 == 0 && i6 == -65536 && i7 == 65536 && i8 == 0) {
            i3 = 270;
        } else if (i5 == -65536 && i6 == 0 && i7 == 0 && i8 == -65536) {
            i3 = 180;
        }
        return new TkhdData(i, j, i3);
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    private static int getTrackTypeForHdlr(int i) {
        if (i == TYPE_soun) {
            return 1;
        }
        if (i == TYPE_vide) {
            return 2;
        }
        if (i == TYPE_text || i == TYPE_sbtl || i == TYPE_subt || i == TYPE_clcp) {
            return 3;
        }
        return i == TYPE_meta ? 4 : -1;
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 8 : 16);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 4 : 8);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        return Pair.create(Long.valueOf(unsignedInt), "" + ((char) (((unsignedShort >> 10) & 31) + 96)) + ((char) (((unsignedShort >> 5) & 31) + 96)) + ((char) ((unsignedShort & 31) + 96)));
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, int i2, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        parsableByteArray.setPosition(12);
        int i3 = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(i3);
        for (int i4 = 0; i4 < i3; i4++) {
            int position = parsableByteArray.getPosition();
            int i5 = parsableByteArray.readInt();
            Assertions.checkArgument(i5 > 0, "childAtomSize should be positive");
            int i6 = parsableByteArray.readInt();
            if (i6 == Atom.TYPE_avc1 || i6 == Atom.TYPE_avc3 || i6 == Atom.TYPE_encv || i6 == Atom.TYPE_mp4v || i6 == Atom.TYPE_hvc1 || i6 == Atom.TYPE_hev1 || i6 == Atom.TYPE_s263 || i6 == Atom.TYPE_vp08 || i6 == Atom.TYPE_vp09) {
                parseVideoSampleEntry(parsableByteArray, i6, position, i5, i, i2, drmInitData, stsdData, i4);
            } else if (i6 == Atom.TYPE_mp4a || i6 == Atom.TYPE_enca || i6 == Atom.TYPE_ac_3 || i6 == Atom.TYPE_ec_3 || i6 == Atom.TYPE_dtsc || i6 == Atom.TYPE_dtse || i6 == Atom.TYPE_dtsh || i6 == Atom.TYPE_dtsl || i6 == Atom.TYPE_samr || i6 == Atom.TYPE_sawb || i6 == Atom.TYPE_lpcm || i6 == Atom.TYPE_sowt || i6 == Atom.TYPE__mp3 || i6 == Atom.TYPE_alac || i6 == Atom.TYPE_alaw || i6 == Atom.TYPE_ulaw || i6 == Atom.TYPE_Opus || i6 == Atom.TYPE_fLaC) {
                parseAudioSampleEntry(parsableByteArray, i6, position, i5, i, str, z, drmInitData, stsdData, i4);
            } else if (i6 == Atom.TYPE_TTML || i6 == Atom.TYPE_tx3g || i6 == Atom.TYPE_wvtt || i6 == Atom.TYPE_stpp || i6 == Atom.TYPE_c608) {
                parseTextSampleEntry(parsableByteArray, i6, position, i5, i, str, stsdData);
            } else if (i6 == Atom.TYPE_camm) {
                stsdData.format = Format.createSampleFormat(Integer.toString(i), MimeTypes.APPLICATION_CAMERA_MOTION, null, -1, null);
            }
            parsableByteArray.setPosition(position + i5);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, StsdData stsdData) throws ParserException {
        parsableByteArray.setPosition(i2 + 8 + 8);
        int i5 = Atom.TYPE_TTML;
        String str2 = MimeTypes.APPLICATION_TTML;
        List listSingletonList = null;
        long j = Long.MAX_VALUE;
        if (i != i5) {
            if (i == Atom.TYPE_tx3g) {
                int i6 = (i3 - 8) - 8;
                byte[] bArr = new byte[i6];
                parsableByteArray.readBytes(bArr, 0, i6);
                listSingletonList = Collections.singletonList(bArr);
                str2 = MimeTypes.APPLICATION_TX3G;
            } else if (i == Atom.TYPE_wvtt) {
                str2 = MimeTypes.APPLICATION_MP4VTT;
            } else if (i == Atom.TYPE_stpp) {
                j = 0;
            } else if (i == Atom.TYPE_c608) {
                stsdData.requiredSampleTransformation = 1;
                str2 = MimeTypes.APPLICATION_MP4CEA608;
            } else {
                throw new IllegalStateException();
            }
        }
        stsdData.format = Format.createTextSampleFormat(Integer.toString(i4), str2, null, -1, 0, str, -1, null, j, listSingletonList);
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, int i5, DrmInitData drmInitData, StsdData stsdData, int i6) throws ParserException {
        DrmInitData drmInitDataCopyWithSchemeType = drmInitData;
        parsableByteArray.setPosition(i2 + 8 + 8);
        parsableByteArray.skipBytes(16);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(50);
        int position = parsableByteArray.getPosition();
        String str = null;
        int iIntValue = i;
        if (iIntValue == Atom.TYPE_encv) {
            Pair<Integer, TrackEncryptionBox> sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i2, i3);
            if (sampleEntryEncryptionData != null) {
                iIntValue = ((Integer) sampleEntryEncryptionData.first).intValue();
                drmInitDataCopyWithSchemeType = drmInitDataCopyWithSchemeType == null ? null : drmInitDataCopyWithSchemeType.copyWithSchemeType(((TrackEncryptionBox) sampleEntryEncryptionData.second).schemeType);
                stsdData.trackEncryptionBoxes[i6] = (TrackEncryptionBox) sampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        DrmInitData drmInitData2 = drmInitDataCopyWithSchemeType;
        int i7 = -1;
        List<byte[]> listSingletonList = null;
        byte[] projFromParent = null;
        float paspFromParent = 1.0f;
        boolean z = false;
        while (position - i2 < i3) {
            parsableByteArray.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            int i8 = parsableByteArray.readInt();
            if (i8 == 0 && parsableByteArray.getPosition() - i2 == i3) {
                break;
            }
            Assertions.checkArgument(i8 > 0, "childAtomSize should be positive");
            int i9 = parsableByteArray.readInt();
            if (i9 == Atom.TYPE_avcC) {
                Assertions.checkState(str == null);
                parsableByteArray.setPosition(position2 + 8);
                AvcConfig avcConfig = AvcConfig.parse(parsableByteArray);
                listSingletonList = avcConfig.initializationData;
                stsdData.nalUnitLengthFieldLength = avcConfig.nalUnitLengthFieldLength;
                if (!z) {
                    paspFromParent = avcConfig.pixelWidthAspectRatio;
                }
                str = MimeTypes.VIDEO_H264;
            } else if (i9 == Atom.TYPE_hvcC) {
                Assertions.checkState(str == null);
                parsableByteArray.setPosition(position2 + 8);
                HevcConfig hevcConfig = HevcConfig.parse(parsableByteArray);
                listSingletonList = hevcConfig.initializationData;
                stsdData.nalUnitLengthFieldLength = hevcConfig.nalUnitLengthFieldLength;
                str = MimeTypes.VIDEO_H265;
            } else if (i9 == Atom.TYPE_vpcC) {
                Assertions.checkState(str == null);
                str = iIntValue == Atom.TYPE_vp08 ? MimeTypes.VIDEO_VP8 : MimeTypes.VIDEO_VP9;
            } else if (i9 == Atom.TYPE_d263) {
                Assertions.checkState(str == null);
                str = MimeTypes.VIDEO_H263;
            } else if (i9 == Atom.TYPE_esds) {
                Assertions.checkState(str == null);
                Pair<String, byte[]> esdsFromParent = parseEsdsFromParent(parsableByteArray, position2);
                str = (String) esdsFromParent.first;
                listSingletonList = Collections.singletonList(esdsFromParent.second);
            } else if (i9 == Atom.TYPE_pasp) {
                paspFromParent = parsePaspFromParent(parsableByteArray, position2);
                z = true;
            } else if (i9 == Atom.TYPE_sv3d) {
                projFromParent = parseProjFromParent(parsableByteArray, position2, i8);
            } else if (i9 == Atom.TYPE_st3d) {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                parsableByteArray.skipBytes(3);
                if (unsignedByte == 0) {
                    int unsignedByte2 = parsableByteArray.readUnsignedByte();
                    if (unsignedByte2 == 0) {
                        i7 = 0;
                    } else if (unsignedByte2 == 1) {
                        i7 = 1;
                    } else if (unsignedByte2 == 2) {
                        i7 = 2;
                    } else if (unsignedByte2 == 3) {
                        i7 = 3;
                    }
                }
            }
            position += i8;
        }
        if (str == null) {
            return;
        }
        stsdData.format = Format.createVideoSampleFormat(Integer.toString(i4), str, null, -1, -1, unsignedShort, unsignedShort2, -1.0f, listSingletonList, i5, paspFromParent, projFromParent, i7, null, drmInitData2);
    }

    private static Pair<long[], long[]> parseEdts(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType;
        if (containerAtom == null || (leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_elst)) == null) {
            return Pair.create(null, null);
        }
        ParsableByteArray parsableByteArray = leafAtomOfType.data;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[unsignedIntToInt];
        long[] jArr2 = new long[unsignedIntToInt];
        for (int i = 0; i < unsignedIntToInt; i++) {
            jArr[i] = fullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i] = fullAtomVersion == 1 ? parsableByteArray.readLong() : parsableByteArray.readInt();
            if (parsableByteArray.readShort() != 1) {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
            parsableByteArray.skipBytes(2);
        }
        return Pair.create(jArr, jArr2);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return parsableByteArray.readUnsignedIntToInt() / parsableByteArray.readUnsignedIntToInt();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, boolean z, DrmInitData drmInitData, StsdData stsdData, int i5) throws ParserException {
        int unsignedShort;
        int unsignedFixedPoint1616;
        int unsignedIntToInt;
        String str2;
        int i6;
        int i7;
        String str3;
        String str4;
        DrmInitData drmInitData2;
        boolean z2;
        int i8;
        int i9;
        int i10 = i2;
        DrmInitData drmInitDataCopyWithSchemeType = drmInitData;
        parsableByteArray.setPosition(i10 + 8 + 8);
        int i11 = 0;
        if (z) {
            unsignedShort = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
        } else {
            parsableByteArray.skipBytes(8);
            unsignedShort = 0;
        }
        int i12 = 2;
        boolean z3 = true;
        if (unsignedShort == 0 || unsignedShort == 1) {
            int unsignedShort2 = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
            unsignedFixedPoint1616 = parsableByteArray.readUnsignedFixedPoint1616();
            if (unsignedShort == 1) {
                parsableByteArray.skipBytes(16);
            }
            unsignedIntToInt = unsignedShort2;
        } else {
            if (unsignedShort != 2) {
                return;
            }
            parsableByteArray.skipBytes(16);
            unsignedFixedPoint1616 = (int) Math.round(parsableByteArray.readDouble());
            unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            parsableByteArray.skipBytes(20);
        }
        int position = parsableByteArray.getPosition();
        int iIntValue = i;
        if (iIntValue == Atom.TYPE_enca) {
            Pair<Integer, TrackEncryptionBox> sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i10, i3);
            if (sampleEntryEncryptionData != null) {
                iIntValue = ((Integer) sampleEntryEncryptionData.first).intValue();
                drmInitDataCopyWithSchemeType = drmInitDataCopyWithSchemeType == null ? null : drmInitDataCopyWithSchemeType.copyWithSchemeType(((TrackEncryptionBox) sampleEntryEncryptionData.second).schemeType);
                stsdData.trackEncryptionBoxes[i5] = (TrackEncryptionBox) sampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        DrmInitData drmInitData3 = drmInitDataCopyWithSchemeType;
        int i13 = Atom.TYPE_ac_3;
        String str5 = MimeTypes.AUDIO_RAW;
        if (iIntValue == i13) {
            str2 = MimeTypes.AUDIO_AC3;
        } else if (iIntValue == Atom.TYPE_ec_3) {
            str2 = MimeTypes.AUDIO_E_AC3;
        } else if (iIntValue == Atom.TYPE_dtsc) {
            str2 = MimeTypes.AUDIO_DTS;
        } else if (iIntValue == Atom.TYPE_dtsh || iIntValue == Atom.TYPE_dtsl) {
            str2 = MimeTypes.AUDIO_DTS_HD;
        } else if (iIntValue == Atom.TYPE_dtse) {
            str2 = MimeTypes.AUDIO_DTS_EXPRESS;
        } else if (iIntValue == Atom.TYPE_samr) {
            str2 = MimeTypes.AUDIO_AMR_NB;
        } else if (iIntValue == Atom.TYPE_sawb) {
            str2 = MimeTypes.AUDIO_AMR_WB;
        } else if (iIntValue == Atom.TYPE_lpcm || iIntValue == Atom.TYPE_sowt) {
            str2 = MimeTypes.AUDIO_RAW;
        } else if (iIntValue == Atom.TYPE__mp3) {
            str2 = MimeTypes.AUDIO_MPEG;
        } else if (iIntValue == Atom.TYPE_alac) {
            str2 = MimeTypes.AUDIO_ALAC;
        } else if (iIntValue == Atom.TYPE_alaw) {
            str2 = MimeTypes.AUDIO_ALAW;
        } else if (iIntValue == Atom.TYPE_ulaw) {
            str2 = MimeTypes.AUDIO_MLAW;
        } else if (iIntValue == Atom.TYPE_Opus) {
            str2 = MimeTypes.AUDIO_OPUS;
        } else {
            str2 = iIntValue == Atom.TYPE_fLaC ? MimeTypes.AUDIO_FLAC : null;
        }
        String str6 = str2;
        int iIntValue2 = unsignedFixedPoint1616;
        int iIntValue3 = unsignedIntToInt;
        int i14 = position;
        byte[] bArr = null;
        while (i14 - i10 < i3) {
            parsableByteArray.setPosition(i14);
            int i15 = parsableByteArray.readInt();
            Assertions.checkArgument(i15 > 0 ? z3 : i11, "childAtomSize should be positive");
            int i16 = parsableByteArray.readInt();
            if (i16 == Atom.TYPE_esds || (z && i16 == Atom.TYPE_wave)) {
                i6 = i15;
                i7 = i14;
                str3 = str6;
                str4 = str5;
                drmInitData2 = drmInitData3;
                z2 = z3;
                i8 = i12;
                i9 = i11;
                int iFindEsdsPosition = i16 == Atom.TYPE_esds ? i7 : findEsdsPosition(parsableByteArray, i7, i6);
                if (iFindEsdsPosition != -1) {
                    Pair<String, byte[]> esdsFromParent = parseEsdsFromParent(parsableByteArray, iFindEsdsPosition);
                    str6 = (String) esdsFromParent.first;
                    bArr = (byte[]) esdsFromParent.second;
                    if (MimeTypes.AUDIO_AAC.equals(str6)) {
                        Pair<Integer, Integer> aacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArr);
                        iIntValue2 = ((Integer) aacAudioSpecificConfig.first).intValue();
                        iIntValue3 = ((Integer) aacAudioSpecificConfig.second).intValue();
                    }
                }
                i14 = i7 + i6;
                i11 = i9;
                drmInitData3 = drmInitData2;
                z3 = z2;
                i12 = i8;
                str5 = str4;
                i10 = i2;
            } else {
                if (i16 == Atom.TYPE_dac3) {
                    parsableByteArray.setPosition(i14 + 8);
                    stsdData.format = Ac3Util.parseAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), str, drmInitData3);
                } else if (i16 == Atom.TYPE_dec3) {
                    parsableByteArray.setPosition(i14 + 8);
                    stsdData.format = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), str, drmInitData3);
                } else if (i16 == Atom.TYPE_ddts) {
                    str3 = str6;
                    str4 = str5;
                    drmInitData2 = drmInitData3;
                    z2 = z3;
                    i8 = i12;
                    i9 = i11;
                    stsdData.format = Format.createAudioSampleFormat(Integer.toString(i4), str6, null, -1, -1, iIntValue3, iIntValue2, null, drmInitData2, 0, str);
                    i6 = i15;
                    i7 = i14;
                } else {
                    int i17 = i14;
                    str3 = str6;
                    str4 = str5;
                    drmInitData2 = drmInitData3;
                    z2 = z3;
                    i8 = i12;
                    i9 = i11;
                    if (i16 == Atom.TYPE_alac) {
                        i6 = i15;
                        byte[] bArr2 = new byte[i6];
                        i7 = i17;
                        parsableByteArray.setPosition(i7);
                        parsableByteArray.readBytes(bArr2, i9, i6);
                        bArr = bArr2;
                    } else {
                        i6 = i15;
                        i7 = i17;
                        if (i16 == Atom.TYPE_dOps) {
                            int i18 = i6 - 8;
                            byte[] bArr3 = opusMagic;
                            byte[] bArr4 = new byte[bArr3.length + i18];
                            System.arraycopy(bArr3, i9, bArr4, i9, bArr3.length);
                            parsableByteArray.setPosition(i7 + 8);
                            parsableByteArray.readBytes(bArr4, opusMagic.length, i18);
                            bArr = bArr4;
                        } else if (i6 == Atom.TYPE_dfLa) {
                            int i19 = i6 - 12;
                            byte[] bArr5 = new byte[i19];
                            parsableByteArray.setPosition(i7 + 12);
                            parsableByteArray.readBytes(bArr5, i9, i19);
                            bArr = bArr5;
                        }
                    }
                }
                i6 = i15;
                i7 = i14;
                str3 = str6;
                str4 = str5;
                drmInitData2 = drmInitData3;
                z2 = z3;
                i8 = i12;
                i9 = i11;
            }
            str6 = str3;
            i14 = i7 + i6;
            i11 = i9;
            drmInitData3 = drmInitData2;
            z3 = z2;
            i12 = i8;
            str5 = str4;
            i10 = i2;
        }
        String str7 = str6;
        String str8 = str5;
        DrmInitData drmInitData4 = drmInitData3;
        int i20 = i12;
        if (stsdData.format != null || str7 == null) {
            return;
        }
        stsdData.format = Format.createAudioSampleFormat(Integer.toString(i4), str7, null, -1, -1, iIntValue3, iIntValue2, str8.equals(str7) ? i20 : -1, bArr != null ? Collections.singletonList(bArr) : null, drmInitData4, 0, str);
    }

    private static int findEsdsPosition(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int i3 = parsableByteArray.readInt();
            Assertions.checkArgument(i3 > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_esds) {
                return position;
            }
            position += i3;
        }
        return -1;
    }

    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8 + 4);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if ((unsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((unsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((unsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if (MimeTypes.AUDIO_MPEG.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS_HD.equals(mimeTypeFromMp4ObjectType)) {
            return Pair.create(mimeTypeFromMp4ObjectType, null);
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        int expandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[expandableClassSize];
        parsableByteArray.readBytes(bArr, 0, expandableClassSize);
        return Pair.create(mimeTypeFromMp4ObjectType, bArr);
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2) {
        Pair<Integer, TrackEncryptionBox> commonEncryptionSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int i3 = parsableByteArray.readInt();
            Assertions.checkArgument(i3 > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_sinf && (commonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, i3)) != null) {
                return commonEncryptionSinfFromParent;
            }
            position += i3;
        }
        return null;
    }

    static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        int i4 = -1;
        String string = null;
        Integer numValueOf = null;
        int i5 = 0;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i6 = parsableByteArray.readInt();
            int i7 = parsableByteArray.readInt();
            if (i7 == Atom.TYPE_frma) {
                numValueOf = Integer.valueOf(parsableByteArray.readInt());
            } else if (i7 == Atom.TYPE_schm) {
                parsableByteArray.skipBytes(4);
                string = parsableByteArray.readString(4);
            } else if (i7 == Atom.TYPE_schi) {
                i4 = i3;
                i5 = i6;
            }
            i3 += i6;
        }
        if (!C.CENC_TYPE_cenc.equals(string) && !C.CENC_TYPE_cbc1.equals(string) && !C.CENC_TYPE_cens.equals(string) && !C.CENC_TYPE_cbcs.equals(string)) {
            return null;
        }
        Assertions.checkArgument(numValueOf != null, "frma atom is mandatory");
        Assertions.checkArgument(i4 != -1, "schi atom is mandatory");
        TrackEncryptionBox schiFromParent = parseSchiFromParent(parsableByteArray, i4, i5, string);
        Assertions.checkArgument(schiFromParent != null, "tenc atom is mandatory");
        return Pair.create(numValueOf, schiFromParent);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3;
        int i4;
        int i5 = i + 8;
        while (true) {
            byte[] bArr = null;
            if (i5 - i >= i2) {
                return null;
            }
            parsableByteArray.setPosition(i5);
            int i6 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_tenc) {
                int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (fullAtomVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i4 = 0;
                    i3 = 0;
                } else {
                    int unsignedByte = parsableByteArray.readUnsignedByte();
                    i3 = unsignedByte & 15;
                    i4 = (unsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, 16);
                if (z && unsignedByte2 == 0) {
                    int unsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[unsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, unsignedByte3);
                }
                return new TrackEncryptionBox(z, str, unsignedByte2, bArr2, i4, i3, bArr);
            }
            i5 += i6;
        }
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i4 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parsableByteArray.data, i3, i4 + i3);
            }
            i3 += i4;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i = unsignedByte & 127;
        while ((unsignedByte & 128) == 128) {
            unsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (unsignedByte & 127);
        }
        return i;
    }

    private static boolean canApplyEditWithGaplessInfo(long[] jArr, long j, long j2, long j3) {
        int length = jArr.length - 1;
        return jArr[0] <= j2 && j2 < jArr[Util.constrainValue(3, 0, length)] && jArr[Util.constrainValue(jArr.length - 3, 0, length)] < j3 && j3 <= j;
    }

    private AtomParsers() {
    }

    private static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            Assertions.checkState(parsableByteArray.readInt() == 1, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            this.offset = this.chunkOffsetsAreLongs ? this.chunkOffsets.readUnsignedLongToLong() : this.chunkOffsets.readUnsignedInt();
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    private static final class TkhdData {
        private final long duration;
        private final int id;
        private final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    private static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fixedSampleSize = this.data.readUnsignedIntToInt();
            this.sampleCount = this.data.readUnsignedIntToInt();
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fixedSampleSize;
            return i == 0 ? this.data.readUnsignedIntToInt() : i;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }
    }

    static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return false;
        }

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fieldSize = this.data.readUnsignedIntToInt() & 255;
            this.sampleCount = this.data.readUnsignedIntToInt();
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.google.android.exoplayer2.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fieldSize;
            if (i == 8) {
                return this.data.readUnsignedByte();
            }
            if (i == 16) {
                return this.data.readUnsignedShort();
            }
            int i2 = this.sampleIndex;
            this.sampleIndex = i2 + 1;
            if (i2 % 2 == 0) {
                int unsignedByte = this.data.readUnsignedByte();
                this.currentByte = unsignedByte;
                return (unsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
            }
            return this.currentByte & 15;
        }
    }
}
