package org.slf4j.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

/* JADX INFO: loaded from: classes3.dex */
class AndroidLoggerFactory implements ILoggerFactory {
    static final String ANONYMOUS_TAG = "null";
    static final int TAG_MAX_LENGTH = 23;
    private final ConcurrentMap<String, Logger> loggerMap = new ConcurrentHashMap();

    AndroidLoggerFactory() {
    }

    @Override // org.slf4j.ILoggerFactory
    public Logger getLogger(String str) {
        String strLoggerNameToTag = loggerNameToTag(str);
        Logger logger = this.loggerMap.get(strLoggerNameToTag);
        if (logger != null) {
            return logger;
        }
        AndroidLoggerAdapter androidLoggerAdapter = new AndroidLoggerAdapter(strLoggerNameToTag);
        Logger loggerPutIfAbsent = this.loggerMap.putIfAbsent(strLoggerNameToTag, androidLoggerAdapter);
        return loggerPutIfAbsent == null ? androidLoggerAdapter : loggerPutIfAbsent;
    }

    static String loggerNameToTag(String str) {
        if (str == null) {
            return ANONYMOUS_TAG;
        }
        int length = str.length();
        if (length <= 23) {
            return str;
        }
        StringBuilder sb = new StringBuilder(26);
        int i = 0;
        int length2 = 0;
        do {
            int iIndexOf = str.indexOf(46, i);
            if (iIndexOf == -1) {
                int i2 = length - i;
                if (length2 == 0 || length2 + i2 > 23) {
                    return getSimpleName(str);
                }
                sb.append((CharSequence) str, i, length);
                return sb.toString();
            }
            sb.append(str.charAt(i));
            if (iIndexOf - i > 1) {
                sb.append('*');
            }
            sb.append('.');
            i = iIndexOf + 1;
            length2 = sb.length();
        } while (length2 <= 23);
        return getSimpleName(str);
    }

    private static String getSimpleName(String str) {
        int length = str.length();
        int iLastIndexOf = str.lastIndexOf(46);
        if (iLastIndexOf != -1) {
            int i = iLastIndexOf + 1;
            if (length - i <= 23) {
                return str.substring(i);
            }
        }
        return '*' + str.substring((length - 23) + 1);
    }
}
