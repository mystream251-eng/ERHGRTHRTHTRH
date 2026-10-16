package com.nytimes.android.external.cache;

import java.util.Arrays;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class MoreObjects {
    public static <T> T firstNonNull(T t, T t2) {
        return t != null ? t : (T) Preconditions.checkNotNull(t2);
    }

    @Nonnull
    public static ToStringHelper toStringHelper(@Nonnull Object obj) {
        return new ToStringHelper(obj.getClass().getSimpleName());
    }

    public static final class ToStringHelper {
        private final String className;

        @Nonnull
        private ValueHolder holderHead;

        @Nonnull
        private ValueHolder holderTail;
        private boolean omitNullValues;

        private ToStringHelper(@Nonnull String str) {
            ValueHolder valueHolder = new ValueHolder();
            this.holderHead = valueHolder;
            this.holderTail = valueHolder;
            this.omitNullValues = false;
            this.className = (String) Preconditions.checkNotNull(str);
        }

        @Nonnull
        public ToStringHelper add(@Nonnull String str, Object obj) {
            return addHolder(str, obj);
        }

        @Nonnull
        public ToStringHelper add(@Nonnull String str, int i) {
            return addHolder(str, String.valueOf(i));
        }

        @Nonnull
        public ToStringHelper add(@Nonnull String str, long j) {
            return addHolder(str, String.valueOf(j));
        }

        @Nonnull
        public ToStringHelper addValue(Object obj) {
            return addHolder(obj);
        }

        public String toString() {
            boolean z = this.omitNullValues;
            StringBuilder sb = new StringBuilder(32);
            sb.append(this.className);
            sb.append('{');
            String str = "";
            for (ValueHolder valueHolder = this.holderHead.next; valueHolder != null; valueHolder = valueHolder.next) {
                Object obj = valueHolder.value;
                if (!z || obj != null) {
                    sb.append(str);
                    if (valueHolder.name != null) {
                        sb.append(valueHolder.name);
                        sb.append('=');
                    }
                    if (obj != null && obj.getClass().isArray()) {
                        String strDeepToString = Arrays.deepToString(new Object[]{obj});
                        sb.append(strDeepToString.substring(1, strDeepToString.length() - 1));
                    } else {
                        sb.append(obj);
                    }
                    str = ", ";
                }
            }
            sb.append('}');
            return sb.toString();
        }

        @Nonnull
        private ValueHolder addHolder() {
            ValueHolder valueHolder = new ValueHolder();
            this.holderTail.next = valueHolder;
            this.holderTail = valueHolder;
            return valueHolder;
        }

        @Nonnull
        private ToStringHelper addHolder(Object obj) {
            addHolder().value = obj;
            return this;
        }

        @Nonnull
        private ToStringHelper addHolder(@Nonnull String str, Object obj) {
            ValueHolder valueHolderAddHolder = addHolder();
            valueHolderAddHolder.value = obj;
            valueHolderAddHolder.name = (String) Preconditions.checkNotNull(str);
            return this;
        }

        private static final class ValueHolder {
            String name;
            ValueHolder next;
            Object value;

            private ValueHolder() {
            }
        }
    }

    private MoreObjects() {
    }
}
