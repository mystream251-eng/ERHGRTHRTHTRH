package com.benasher44.uuid;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.ranges.CharRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: uuid.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00038\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\"\"\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\u00038\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0006\u001a\u0004\b\u000f\u0010\b\"\u000e\u0010\u0010\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"UUID_BYTES", "", "UUID_CHARS", "", "", "UUID_CHARS$annotations", "()V", "getUUID_CHARS", "()Ljava/util/List;", "UUID_CHAR_RANGES", "Lkotlin/ranges/IntRange;", "UUID_CHAR_RANGES$annotations", "getUUID_CHAR_RANGES", "UUID_HYPHEN_INDICES", "UUID_HYPHEN_INDICES$annotations", "getUUID_HYPHEN_INDICES", "UUID_STRING_LENGTH", "uuid"}, k = 2, mv = {1, 1, 16})
public final class UuidUtil {
    public static final int UUID_BYTES = 16;
    public static final int UUID_STRING_LENGTH = 36;
    private static final List<IntRange> UUID_CHAR_RANGES = CollectionsKt.listOf((Object[]) new IntRange[]{RangesKt.until(0, 8), RangesKt.until(9, 13), RangesKt.until(14, 18), RangesKt.until(19, 23), RangesKt.until(24, 36)});
    private static final List<Integer> UUID_HYPHEN_INDICES = CollectionsKt.listOf((Object[]) new Integer[]{8, 13, 18, 23});
    private static final List<Character> UUID_CHARS = CollectionsKt.plus((Iterable) new CharRange('0', '9'), (Iterable) new CharRange('a', 'f'));

    public static /* synthetic */ void UUID_CHARS$annotations() {
    }

    public static /* synthetic */ void UUID_CHAR_RANGES$annotations() {
    }

    public static /* synthetic */ void UUID_HYPHEN_INDICES$annotations() {
    }

    public static final List<IntRange> getUUID_CHAR_RANGES() {
        return UUID_CHAR_RANGES;
    }

    public static final List<Integer> getUUID_HYPHEN_INDICES() {
        return UUID_HYPHEN_INDICES;
    }

    public static final List<Character> getUUID_CHARS() {
        return UUID_CHARS;
    }
}
