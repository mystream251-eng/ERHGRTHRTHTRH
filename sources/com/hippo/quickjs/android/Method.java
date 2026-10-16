package com.hippo.quickjs.android;

import androidx.exifinterface.media.ExifInterface;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public final class Method {
    final String name;
    final Type[] parameterTypes;
    final Type returnType;

    public static Method create(Type type, java.lang.reflect.Method method) {
        Class<?> rawType = Types.getRawType(type);
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
        return new Method(typeResolve, name, typeArr);
    }

    public Method(Type type, String str, Type[] typeArr) {
        this.returnType = canonicalize(type);
        this.name = str;
        this.parameterTypes = new Type[typeArr.length];
        for (int i = 0; i < typeArr.length; i++) {
            this.parameterTypes[i] = canonicalize(typeArr[i]);
        }
    }

    private static Type canonicalize(Type type) {
        return Types.removeSubtypeWildcard(Types.canonicalize(type));
    }

    private static String getTypeSignature(Type type) {
        if (type instanceof Types.GenericArrayTypeImpl) {
            return "[" + getTypeSignature(((Types.GenericArrayTypeImpl) type).getGenericComponentType());
        }
        if ((type instanceof Class) && ((Class) type).isPrimitive()) {
            if (type == Void.TYPE) {
                return ExifInterface.GPS_MEASUREMENT_INTERRUPTED;
            }
            if (type == Boolean.TYPE) {
                return "Z";
            }
            if (type == Byte.TYPE) {
                return "B";
            }
            if (type == Character.TYPE) {
                return "C";
            }
            if (type == Short.TYPE) {
                return ExifInterface.LATITUDE_SOUTH;
            }
            if (type == Integer.TYPE) {
                return "I";
            }
            if (type == Long.TYPE) {
                return "J";
            }
            if (type == Float.TYPE) {
                return "F";
            }
            if (type == Double.TYPE) {
                return "D";
            }
        }
        String name = Types.getRawType(type).getName();
        StringBuilder sb = new StringBuilder(name.length() + 2);
        sb.append("L");
        for (int i = 0; i < name.length(); i++) {
            char cCharAt = name.charAt(i);
            if (cCharAt == '.') {
                cCharAt = '/';
            }
            sb.append(cCharAt);
        }
        sb.append(";");
        return sb.toString();
    }

    String getSignature() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Type type : this.parameterTypes) {
            sb.append(getTypeSignature(type));
        }
        sb.append(")");
        sb.append(getTypeSignature(this.returnType));
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.returnType);
        sb.append(" ");
        sb.append(this.name);
        sb.append("(");
        for (int i = 0; i < this.parameterTypes.length; i++) {
            if (i != 0) {
                sb.append(", ");
            }
            sb.append(this.parameterTypes[i]);
        }
        sb.append(")");
        return sb.toString();
    }

    public int hashCode() {
        return ((((this.returnType.hashCode() + 31) * 31) + this.name.hashCode()) * 31) + Arrays.hashCode(this.parameterTypes);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Method)) {
            return false;
        }
        Method method = (Method) obj;
        return this.returnType.equals(method.returnType) && this.name.equals(method.name) && Arrays.equals(this.parameterTypes, method.parameterTypes);
    }
}
