package com.apollographql.apollo.api.internal;

/* JADX INFO: loaded from: classes.dex */
public class Functions {

    private enum IdentityFunction implements Function<Object, Object> {
        INSTANCE;

        @Override // com.apollographql.apollo.api.internal.Function
        public Object apply(Object obj) {
            return obj;
        }

        @Override // java.lang.Enum
        public String toString() {
            return "Functions.identity()";
        }
    }

    public static <E> Function<E, E> identity() {
        return IdentityFunction.INSTANCE;
    }

    public static Function<Object, String> toStringFunction() {
        return ToStringFunction.INSTANCE;
    }

    private enum ToStringFunction implements Function<Object, String> {
        INSTANCE;

        @Override // java.lang.Enum
        public String toString() {
            return "Functions.toStringFunction()";
        }

        @Override // com.apollographql.apollo.api.internal.Function
        public String apply(Object obj) {
            Utils.checkNotNull(obj);
            return obj.toString();
        }
    }
}
