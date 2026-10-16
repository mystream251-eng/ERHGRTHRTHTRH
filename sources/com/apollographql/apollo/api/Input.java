package com.apollographql.apollo.api;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Input.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \f*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001\fB\u0019\b\u0000\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\b\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u0004\u0018\u00018\u00008\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/apollographql/apollo/api/Input;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "", "value", "defined", "", "(Ljava/lang/Object;Z)V", "Ljava/lang/Object;", "equals", "other", "hashCode", "", "Companion", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class Input<V> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public final boolean defined;
    public final V value;

    @JvmStatic
    public static final <V> Input<V> absent() {
        return INSTANCE.absent();
    }

    @JvmStatic
    public static final <V> Input<V> fromNullable(V v) {
        return INSTANCE.fromNullable(v);
    }

    @JvmStatic
    public static final <V> Input<V> optional(V v) {
        return INSTANCE.optional(v);
    }

    public Input(V v, boolean z) {
        this.value = v;
        this.defined = z;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Input)) {
            return false;
        }
        Input input = (Input) other;
        return !(Intrinsics.areEqual(this.value, input.value) ^ true) && this.defined == input.defined;
    }

    public int hashCode() {
        V v = this.value;
        return ((v != null ? v.hashCode() : 0) * 31) + Boolean.hashCode(this.defined);
    }

    /* JADX INFO: compiled from: Input.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u0005H\u0007J#\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\b\u0010\u0007\u001a\u0004\u0018\u0001H\u0005H\u0007¢\u0006\u0002\u0010\bJ#\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0001\u0010\u00052\b\u0010\u0007\u001a\u0004\u0018\u0001H\u0005H\u0007¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"Lcom/apollographql/apollo/api/Input$Companion;", "", "()V", "absent", "Lcom/apollographql/apollo/api/Input;", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, "fromNullable", "value", "(Ljava/lang/Object;)Lcom/apollographql/apollo/api/Input;", "optional", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final <V> Input<V> optional(V value) {
            Input<V> inputFromNullable;
            return (value == null || (inputFromNullable = Input.INSTANCE.fromNullable(value)) == null) ? absent() : inputFromNullable;
        }

        @JvmStatic
        public final <V> Input<V> fromNullable(V value) {
            return new Input<>(value, true);
        }

        @JvmStatic
        public final <V> Input<V> absent() {
            return new Input<>(null, false);
        }
    }
}
