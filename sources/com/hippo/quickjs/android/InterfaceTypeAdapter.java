package com.hippo.quickjs.android;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
class InterfaceTypeAdapter extends TypeAdapter<Object> {
    static final TypeAdapter.Factory FACTORY = new TypeAdapter.Factory() { // from class: com.hippo.quickjs.android.-$$Lambda$InterfaceTypeAdapter$cKxNMWnBYIHQanUEpM5sOVMqAa0
        @Override // com.hippo.quickjs.android.TypeAdapter.Factory
        public final TypeAdapter create(TypeAdapter.Depot depot, Type type) {
            return InterfaceTypeAdapter.lambda$static$0(depot, type);
        }
    };
    private static final JSValueHolderTag JS_VALUE_HOLDER_TAG = new JSValueHolderTag();
    private final Map<String, Method> methods;
    private final Class<?> rawType;

    private interface JSValueHolder {
        JSValue getJSValue(JSValueHolderTag jSValueHolderTag);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0073  */
    static Map<String, Method> getInterfaceMethods(Type type) {
        Class<?> rawType = Types.getRawType(type);
        if (!rawType.isInterface()) {
            return null;
        }
        HashMap map = new HashMap();
        for (java.lang.reflect.Method method : rawType.getMethods()) {
            Type typeResolve = Types.resolve(type, rawType, method.getGenericReturnType());
            if (typeResolve instanceof TypeVariable) {
                return null;
            }
            String name = method.getName();
            Type[] genericParameterTypes = method.getGenericParameterTypes();
            int length = genericParameterTypes.length;
            Type[] typeArr = new Type[length];
            for (int i = 0; i < length; i++) {
                typeArr[i] = Types.resolve(type, rawType, genericParameterTypes[i]);
                if (typeArr[i] instanceof TypeVariable) {
                    return null;
                }
            }
            Method method2 = (Method) map.get(name);
            if (method2 != null) {
                if (!Arrays.equals(method2.parameterTypes, typeArr)) {
                    return null;
                }
                if (!typeResolve.equals(method2.returnType) && !Types.getRawType(typeResolve).isAssignableFrom(Types.getRawType(method2.returnType))) {
                    map.put(name, new Method(typeResolve, name, typeArr));
                }
            } else {
                map.put(name, new Method(typeResolve, name, typeArr));
            }
        }
        return map;
    }

    static /* synthetic */ TypeAdapter lambda$static$0(TypeAdapter.Depot depot, Type type) {
        Map<String, Method> interfaceMethods = getInterfaceMethods(type);
        if (interfaceMethods == null) {
            return null;
        }
        return new InterfaceTypeAdapter(Types.getRawType(type), interfaceMethods).nullable();
    }

    private InterfaceTypeAdapter(Class<?> cls, Map<String, Method> map) {
        this.rawType = cls;
        this.methods = map;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter
    public JSValue toJSValue(TypeAdapter.Depot depot, TypeAdapter.Context context, Object obj) {
        if (obj instanceof JSValueHolder) {
            return ((JSValueHolder) obj).getJSValue(JS_VALUE_HOLDER_TAG);
        }
        JSObject jSObjectCreateJSObject = context.createJSObject(obj);
        for (Method method : this.methods.values()) {
            jSObjectCreateJSObject.setProperty(method.name, context.createJSFunction(obj, method));
        }
        return jSObjectCreateJSObject;
    }

    @Override // com.hippo.quickjs.android.TypeAdapter
    public Object fromJSValue(final TypeAdapter.Depot depot, final TypeAdapter.Context context, final JSValue jSValue) {
        final JSObject jSObject = (JSObject) jSValue.cast(JSObject.class);
        Object javaObject = jSObject.getJavaObject();
        return this.rawType.isInstance(javaObject) ? javaObject : Proxy.newProxyInstance(this.rawType.getClassLoader(), new Class[]{this.rawType, JSValueHolder.class}, new InvocationHandler() { // from class: com.hippo.quickjs.android.-$$Lambda$InterfaceTypeAdapter$wSHgEs4BBLHqDywrjBeEz002u88
            @Override // java.lang.reflect.InvocationHandler
            public final Object invoke(Object obj, java.lang.reflect.Method method, Object[] objArr) {
                return this.f$0.lambda$fromJSValue$1$InterfaceTypeAdapter(jSValue, depot, context, jSObject, obj, method, objArr);
            }
        });
    }

    public /* synthetic */ Object lambda$fromJSValue$1$InterfaceTypeAdapter(JSValue jSValue, TypeAdapter.Depot depot, TypeAdapter.Context context, JSObject jSObject, Object obj, java.lang.reflect.Method method, Object[] objArr) throws Throwable {
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(this, objArr);
        }
        if (objArr != null && objArr.length == 1 && objArr[0] == JS_VALUE_HOLDER_TAG) {
            return jSValue;
        }
        String name = method.getName();
        Method method2 = this.methods.get(name);
        if (method2 == null) {
            throw new NoSuchMethodException("Can't find method: " + name);
        }
        int length = objArr != null ? objArr.length : 0;
        if (length != method2.parameterTypes.length) {
            throw new IllegalStateException("Parameter number doesn't match: " + name);
        }
        JSValue[] jSValueArr = new JSValue[length];
        for (int i = 0; i < length; i++) {
            jSValueArr[i] = depot.getAdapter(method2.parameterTypes[i]).toJSValue(depot, context, objArr[i]);
        }
        return depot.getAdapter(method2.returnType).fromJSValue(depot, context, ((JSFunction) jSObject.getProperty(name).cast(JSFunction.class)).invoke(jSObject, jSValueArr));
    }

    private static class JSValueHolderTag {
        private JSValueHolderTag() {
        }
    }
}
