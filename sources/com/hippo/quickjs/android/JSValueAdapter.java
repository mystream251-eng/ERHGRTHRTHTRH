package com.hippo.quickjs.android;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes2.dex */
class JSValueAdapter {
    static final TypeAdapter.Factory FACTORY = new TypeAdapter.Factory() { // from class: com.hippo.quickjs.android.JSValueAdapter.1
        @Override // com.hippo.quickjs.android.TypeAdapter.Factory
        public TypeAdapter<?> create(TypeAdapter.Depot depot, Type type) {
            if (type == JSValue.class) {
                return JSValueAdapter.JS_VALUE_TYPE_ADAPTER;
            }
            return null;
        }
    };
    private static final TypeAdapter<JSValue> JS_VALUE_TYPE_ADAPTER = new TypeAdapter<JSValue>() { // from class: com.hippo.quickjs.android.JSValueAdapter.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.hippo.quickjs.android.TypeAdapter
        public JSValue fromJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, JSValue jSValue) {
            return jSValue;
        }

        @Override // com.hippo.quickjs.android.TypeAdapter
        public JSValue toJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, JSValue jSValue) {
            if (jSValue != null) {
                return jSValue;
            }
            throw new NullPointerException("value == null");
        }
    };

    JSValueAdapter() {
    }
}
