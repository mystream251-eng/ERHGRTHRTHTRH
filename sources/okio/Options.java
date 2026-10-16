package okio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.UByte;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Options.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004:\u0001\u0015B\u001f\b\u0002\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u000eH\u0096\u0002R\u001e\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lokio/Options;", "Lkotlin/collections/AbstractList;", "Lokio/ByteString;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "byteStrings", "", "trie", "", "([Lokio/ByteString;[I)V", "getByteStrings$okio", "()[Lokio/ByteString;", "[Lokio/ByteString;", "size", "", "getSize", "()I", "getTrie$okio", "()[I", "get", "index", "Companion", "okio"}, k = 1, mv = {1, 1, 16})
public final class Options extends AbstractList<ByteString> implements RandomAccess {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ByteString[] byteStrings;
    private final int[] trie;

    @JvmStatic
    public static final Options of(ByteString... byteStringArr) {
        return INSTANCE.of(byteStringArr);
    }

    public /* synthetic */ Options(ByteString[] byteStringArr, int[] iArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(byteStringArr, iArr);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ByteString) {
            return contains((ByteString) obj);
        }
        return false;
    }

    public /* bridge */ boolean contains(ByteString byteString) {
        return super.contains((Object) byteString);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ByteString) {
            return indexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int indexOf(ByteString byteString) {
        return super.indexOf((Object) byteString);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ByteString) {
            return lastIndexOf((ByteString) obj);
        }
        return -1;
    }

    public /* bridge */ int lastIndexOf(ByteString byteString) {
        return super.lastIndexOf((Object) byteString);
    }

    /* JADX INFO: renamed from: getByteStrings$okio, reason: from getter */
    public final ByteString[] getByteStrings() {
        return this.byteStrings;
    }

    /* JADX INFO: renamed from: getTrie$okio, reason: from getter */
    public final int[] getTrie() {
        return this.trie;
    }

    private Options(ByteString[] byteStringArr, int[] iArr) {
        this.byteStrings = byteStringArr;
        this.trie = iArr;
    }

    @Override // kotlin.collections.AbstractList, kotlin.collections.AbstractCollection
    public int getSize() {
        return this.byteStrings.length;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public ByteString get(int index) {
        return this.byteStrings[index];
    }

    /* JADX INFO: compiled from: Options.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JT\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0012\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u000fH\u0002J!\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u000e\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0016\"\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0017R\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lokio/Options$Companion;", "", "()V", "intCount", "", "Lokio/Buffer;", "getIntCount", "(Lokio/Buffer;)J", "buildTrieRecursive", "", "nodeOffset", "node", "byteStringOffset", "", "byteStrings", "", "Lokio/ByteString;", "fromIndex", "toIndex", "indexes", "of", "Lokio/Options;", "", "([Lokio/ByteString;)Lokio/Options;", "okio"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final Options of(ByteString... byteStrings) throws IOException {
            Intrinsics.checkParameterIsNotNull(byteStrings, "byteStrings");
            int i = 0;
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (byteStrings.length == 0) {
                return new Options(new ByteString[0], new int[]{0, -1}, defaultConstructorMarker);
            }
            List mutableList = ArraysKt.toMutableList(byteStrings);
            CollectionsKt.sort(mutableList);
            ArrayList arrayList = new ArrayList(byteStrings.length);
            for (ByteString byteString : byteStrings) {
                arrayList.add(-1);
            }
            Object[] array = arrayList.toArray(new Integer[0]);
            if (array != null) {
                Integer[] numArr = (Integer[]) array;
                List listMutableListOf = CollectionsKt.mutableListOf((Integer[]) Arrays.copyOf(numArr, numArr.length));
                int length = byteStrings.length;
                int i2 = 0;
                int i3 = 0;
                while (i2 < length) {
                    listMutableListOf.set(CollectionsKt.binarySearch$default(mutableList, byteStrings[i2], 0, 0, 6, (Object) null), Integer.valueOf(i3));
                    i2++;
                    i3++;
                }
                if (!(((ByteString) mutableList.get(0)).size() > 0)) {
                    throw new IllegalArgumentException("the empty byte string is not a supported option".toString());
                }
                int i4 = 0;
                while (i4 < mutableList.size()) {
                    ByteString byteString2 = (ByteString) mutableList.get(i4);
                    int i5 = i4 + 1;
                    int i6 = i5;
                    while (i6 < mutableList.size()) {
                        ByteString byteString3 = (ByteString) mutableList.get(i6);
                        if (!byteString3.startsWith(byteString2)) {
                            break;
                        }
                        if (!(byteString3.size() != byteString2.size())) {
                            throw new IllegalArgumentException(("duplicate option: " + byteString3).toString());
                        }
                        if (((Number) listMutableListOf.get(i6)).intValue() > ((Number) listMutableListOf.get(i4)).intValue()) {
                            mutableList.remove(i6);
                            listMutableListOf.remove(i6);
                        } else {
                            i6++;
                        }
                    }
                    i4 = i5;
                }
                Buffer buffer = new Buffer();
                Companion companion = this;
                buildTrieRecursive$default(companion, 0L, buffer, 0, mutableList, 0, 0, listMutableListOf, 53, null);
                int[] iArr = new int[(int) companion.getIntCount(buffer)];
                while (!buffer.exhausted()) {
                    iArr[i] = buffer.readInt();
                    i++;
                }
                Object[] objArrCopyOf = Arrays.copyOf(byteStrings, byteStrings.length);
                Intrinsics.checkExpressionValueIsNotNull(objArrCopyOf, "java.util.Arrays.copyOf(this, size)");
                return new Options((ByteString[]) objArrCopyOf, iArr, defaultConstructorMarker);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }

        static /* synthetic */ void buildTrieRecursive$default(Companion companion, long j, Buffer buffer, int i, List list, int i2, int i3, List list2, int i4, Object obj) throws IOException {
            companion.buildTrieRecursive((i4 & 1) != 0 ? 0L : j, buffer, (i4 & 4) != 0 ? 0 : i, list, (i4 & 16) != 0 ? 0 : i2, (i4 & 32) != 0 ? list.size() : i3, list2);
        }

        private final void buildTrieRecursive(long nodeOffset, Buffer node, int byteStringOffset, List<? extends ByteString> byteStrings, int fromIndex, int toIndex, List<Integer> indexes) throws IOException {
            int i;
            int i2;
            int i3;
            int i4 = byteStringOffset;
            if (!(fromIndex < toIndex)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            for (int i5 = fromIndex; i5 < toIndex; i5++) {
                if (!(byteStrings.get(i5).size() >= i4)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
            }
            ByteString byteString = byteStrings.get(fromIndex);
            ByteString byteString2 = byteStrings.get(toIndex - 1);
            if (i4 == byteString.size()) {
                int iIntValue = indexes.get(fromIndex).intValue();
                int i6 = fromIndex + 1;
                ByteString byteString3 = byteStrings.get(i6);
                i = i6;
                i2 = iIntValue;
                byteString = byteString3;
            } else {
                i = fromIndex;
                i2 = -1;
            }
            if (byteString.getByte(i4) != byteString2.getByte(i4)) {
                int i7 = 1;
                for (int i8 = i + 1; i8 < toIndex; i8++) {
                    if (byteStrings.get(i8 - 1).getByte(i4) != byteStrings.get(i8).getByte(i4)) {
                        i7++;
                    }
                }
                Companion companion = this;
                long intCount = nodeOffset + companion.getIntCount(node) + ((long) 2) + ((long) (i7 * 2));
                node.writeInt(i7);
                node.writeInt(i2);
                for (int i9 = i; i9 < toIndex; i9++) {
                    byte b = byteStrings.get(i9).getByte(i4);
                    if (i9 == i || b != byteStrings.get(i9 - 1).getByte(i4)) {
                        node.writeInt(b & UByte.MAX_VALUE);
                    }
                }
                Buffer buffer = new Buffer();
                int i10 = i;
                while (i10 < toIndex) {
                    byte b2 = byteStrings.get(i10).getByte(i4);
                    int i11 = i10 + 1;
                    int i12 = i11;
                    while (true) {
                        if (i12 >= toIndex) {
                            i3 = toIndex;
                            break;
                        } else {
                            if (b2 != byteStrings.get(i12).getByte(i4)) {
                                i3 = i12;
                                break;
                            }
                            i12++;
                        }
                    }
                    if (i11 == i3 && i4 + 1 == byteStrings.get(i10).size()) {
                        node.writeInt(indexes.get(i10).intValue());
                    } else {
                        node.writeInt(((int) (intCount + companion.getIntCount(buffer))) * (-1));
                        companion.buildTrieRecursive(intCount, buffer, i4 + 1, byteStrings, i10, i3, indexes);
                    }
                    i10 = i3;
                    buffer = buffer;
                }
                node.writeAll(buffer);
                return;
            }
            int iMin = Math.min(byteString.size(), byteString2.size());
            int i13 = 0;
            for (int i14 = i4; i14 < iMin && byteString.getByte(i14) == byteString2.getByte(i14); i14++) {
                i13++;
            }
            Companion companion2 = this;
            long intCount2 = 1 + nodeOffset + companion2.getIntCount(node) + ((long) 2) + ((long) i13);
            node.writeInt(-i13);
            node.writeInt(i2);
            int i15 = i4 + i13;
            while (i4 < i15) {
                node.writeInt(byteString.getByte(i4) & UByte.MAX_VALUE);
                i4++;
            }
            if (i + 1 == toIndex) {
                if (!(i15 == byteStrings.get(i).size())) {
                    throw new IllegalStateException("Check failed.".toString());
                }
                node.writeInt(indexes.get(i).intValue());
            } else {
                Buffer buffer2 = new Buffer();
                node.writeInt(((int) (companion2.getIntCount(buffer2) + intCount2)) * (-1));
                companion2.buildTrieRecursive(intCount2, buffer2, i15, byteStrings, i, toIndex, indexes);
                node.writeAll(buffer2);
            }
        }

        private final long getIntCount(Buffer buffer) {
            return buffer.size() / ((long) 4);
        }
    }
}
