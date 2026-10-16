package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.JvmProtoBuf;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: JvmNameResolver.kt */
/* JADX INFO: loaded from: classes3.dex */
public final class JvmNameResolver implements NameResolver {
    private static final List<String> PREDEFINED_STRINGS;
    private static final Map<String, Integer> PREDEFINED_STRINGS_MAP;
    private final Set<Integer> localNameIndices;
    private final List<JvmProtoBuf.StringTableTypes.Record> records;
    private final String[] strings;
    private final JvmProtoBuf.StringTableTypes types;
    public static final Companion Companion = new Companion(null);

    /* JADX INFO: renamed from: kotlin, reason: collision with root package name */
    private static final String f171kotlin = CollectionsKt.joinToString$default(CollectionsKt.listOf((Object[]) new Character[]{'k', 'o', 't', 'l', 'i', 'n'}), "", null, null, 0, null, null, 62, null);

    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JvmProtoBuf.StringTableTypes.Record.Operation.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[JvmProtoBuf.StringTableTypes.Record.Operation.NONE.ordinal()] = 1;
            $EnumSwitchMapping$0[JvmProtoBuf.StringTableTypes.Record.Operation.INTERNAL_TO_CLASS_ID.ordinal()] = 2;
            $EnumSwitchMapping$0[JvmProtoBuf.StringTableTypes.Record.Operation.DESC_TO_CLASS_ID.ordinal()] = 3;
        }
    }

    public JvmNameResolver(JvmProtoBuf.StringTableTypes types, String[] strings) {
        Intrinsics.checkNotNullParameter(types, "types");
        Intrinsics.checkNotNullParameter(strings, "strings");
        this.types = types;
        this.strings = strings;
        List<Integer> localNameList = types.getLocalNameList();
        this.localNameIndices = localNameList.isEmpty() ? SetsKt.emptySet() : CollectionsKt.toSet(localNameList);
        ArrayList arrayList = new ArrayList();
        List<JvmProtoBuf.StringTableTypes.Record> recordList = this.types.getRecordList();
        arrayList.ensureCapacity(recordList.size());
        for (JvmProtoBuf.StringTableTypes.Record record : recordList) {
            Intrinsics.checkNotNullExpressionValue(record, "record");
            int range = record.getRange();
            for (int i = 0; i < range; i++) {
                arrayList.add(record);
            }
        }
        arrayList.trimToSize();
        Unit unit = Unit.INSTANCE;
        this.records = arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x003b  */
    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    public String getString(int i) {
        String string;
        JvmProtoBuf.StringTableTypes.Record record = this.records.get(i);
        if (record.hasString()) {
            string = record.getString();
        } else if (record.hasPredefinedIndex()) {
            int size = PREDEFINED_STRINGS.size();
            int predefinedIndex = record.getPredefinedIndex();
            if (predefinedIndex >= 0 && size > predefinedIndex) {
                string = PREDEFINED_STRINGS.get(record.getPredefinedIndex());
            } else {
                string = this.strings[i];
            }
        } else {
            string = this.strings[i];
        }
        if (record.getSubstringIndexCount() >= 2) {
            List<Integer> substringIndexList = record.getSubstringIndexList();
            Integer begin = substringIndexList.get(0);
            Integer end = substringIndexList.get(1);
            Intrinsics.checkNotNullExpressionValue(begin, "begin");
            if (begin.intValue() >= 0) {
                int iIntValue = begin.intValue();
                Intrinsics.checkNotNullExpressionValue(end, "end");
                if (iIntValue <= end.intValue() && end.intValue() <= string.length()) {
                    Intrinsics.checkNotNullExpressionValue(string, "string");
                    int iIntValue2 = begin.intValue();
                    int iIntValue3 = end.intValue();
                    if (string == null) {
                        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                    }
                    string = string.substring(iIntValue2, iIntValue3);
                    Intrinsics.checkNotNullExpressionValue(string, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                }
            }
        }
        String string2 = string;
        if (record.getReplaceCharCount() >= 2) {
            List<Integer> replaceCharList = record.getReplaceCharList();
            Integer num = replaceCharList.get(0);
            Integer num2 = replaceCharList.get(1);
            Intrinsics.checkNotNullExpressionValue(string2, "string");
            string2 = StringsKt.replace$default(string2, (char) num.intValue(), (char) num2.intValue(), false, 4, (Object) null);
        }
        String string3 = string2;
        JvmProtoBuf.StringTableTypes.Record.Operation operation = record.getOperation();
        if (operation == null) {
            operation = JvmProtoBuf.StringTableTypes.Record.Operation.NONE;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$0[operation.ordinal()];
        if (i2 == 2) {
            Intrinsics.checkNotNullExpressionValue(string3, "string");
            string3 = StringsKt.replace$default(string3, Typography.dollar, '.', false, 4, (Object) null);
        } else if (i2 == 3) {
            if (string3.length() >= 2) {
                Intrinsics.checkNotNullExpressionValue(string3, "string");
                int length = string3.length() - 1;
                if (string3 == null) {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                string3 = string3.substring(1, length);
                Intrinsics.checkNotNullExpressionValue(string3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            }
            String string4 = string3;
            Intrinsics.checkNotNullExpressionValue(string4, "string");
            string3 = StringsKt.replace$default(string4, Typography.dollar, '.', false, 4, (Object) null);
        }
        Intrinsics.checkNotNullExpressionValue(string3, "string");
        return string3;
    }

    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    public String getQualifiedClassName(int i) {
        return getString(i);
    }

    @Override // kotlin.reflect.jvm.internal.impl.metadata.deserialization.NameResolver
    public boolean isLocalClassName(int i) {
        return this.localNameIndices.contains(Integer.valueOf(i));
    }

    /* JADX INFO: compiled from: JvmNameResolver.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        List<String> listListOf = CollectionsKt.listOf((Object[]) new String[]{f171kotlin + "/Any", f171kotlin + "/Nothing", f171kotlin + "/Unit", f171kotlin + "/Throwable", f171kotlin + "/Number", f171kotlin + "/Byte", f171kotlin + "/Double", f171kotlin + "/Float", f171kotlin + "/Int", f171kotlin + "/Long", f171kotlin + "/Short", f171kotlin + "/Boolean", f171kotlin + "/Char", f171kotlin + "/CharSequence", f171kotlin + "/String", f171kotlin + "/Comparable", f171kotlin + "/Enum", f171kotlin + "/Array", f171kotlin + "/ByteArray", f171kotlin + "/DoubleArray", f171kotlin + "/FloatArray", f171kotlin + "/IntArray", f171kotlin + "/LongArray", f171kotlin + "/ShortArray", f171kotlin + "/BooleanArray", f171kotlin + "/CharArray", f171kotlin + "/Cloneable", f171kotlin + "/Annotation", f171kotlin + "/collections/Iterable", f171kotlin + "/collections/MutableIterable", f171kotlin + "/collections/Collection", f171kotlin + "/collections/MutableCollection", f171kotlin + "/collections/List", f171kotlin + "/collections/MutableList", f171kotlin + "/collections/Set", f171kotlin + "/collections/MutableSet", f171kotlin + "/collections/Map", f171kotlin + "/collections/MutableMap", f171kotlin + "/collections/Map.Entry", f171kotlin + "/collections/MutableMap.MutableEntry", f171kotlin + "/collections/Iterator", f171kotlin + "/collections/MutableIterator", f171kotlin + "/collections/ListIterator", f171kotlin + "/collections/MutableListIterator"});
        PREDEFINED_STRINGS = listListOf;
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(listListOf);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterableWithIndex, 10)), 16));
        for (IndexedValue indexedValue : iterableWithIndex) {
            linkedHashMap.put((String) indexedValue.getValue(), Integer.valueOf(indexedValue.getIndex()));
        }
        PREDEFINED_STRINGS_MAP = linkedHashMap;
    }
}
