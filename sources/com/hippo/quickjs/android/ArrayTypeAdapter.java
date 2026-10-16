package com.hippo.quickjs.android;

import java.lang.reflect.Array;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes2.dex */
class ArrayTypeAdapter extends TypeAdapter<Object> {
    public static final TypeAdapter.Factory FACTORY = new TypeAdapter.Factory() { // from class: com.hippo.quickjs.android.-$$Lambda$ArrayTypeAdapter$pOfabSkGU0D1pYvOPG3h7dNx1VM
        @Override // com.hippo.quickjs.android.TypeAdapter.Factory
        public final TypeAdapter create(TypeAdapter.Depot depot, Type type) {
            return ArrayTypeAdapter.lambda$static$0(depot, type);
        }
    };
    private final TypeAdapter<Object> elementAdapter;
    private final Class<?> elementClass;

    static /* synthetic */ TypeAdapter lambda$static$0(TypeAdapter.Depot depot, Type type) {
        Type typeArrayComponentType = Types.arrayComponentType(type);
        if (typeArrayComponentType == null) {
            return null;
        }
        return new ArrayTypeAdapter(Types.getRawType(typeArrayComponentType), depot.getAdapter(typeArrayComponentType)).nullable();
    }

    private ArrayTypeAdapter(Class<?> cls, TypeAdapter<Object> typeAdapter) {
        this.elementClass = cls;
        this.elementAdapter = typeAdapter;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter
    public JSValue toJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, Object obj) {
        JSArray jSArrayCreateJSArray = context.createJSArray();
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            jSArrayCreateJSArray.setProperty(i, this.elementAdapter.toJSValue(depot, context, Array.get(obj, i)));
        }
        return jSArrayCreateJSArray;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter
    public Object fromJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, JSValue jSValue) {
        JSArray jSArray = (JSArray) jSValue.cast(JSArray.class);
        int length = jSArray.getLength();
        Object objNewInstance = Array.newInstance(this.elementClass, length);
        for (int i = 0; i < length; i++) {
            Array.set(objNewInstance, i, this.elementAdapter.fromJSValue(depot, context, jSArray.getProperty(i)));
        }
        return objNewInstance;
    }
}
