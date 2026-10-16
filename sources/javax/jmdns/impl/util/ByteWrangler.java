package javax.jmdns.impl.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes2.dex */
public class ByteWrangler {
    public static final String CHARSET_NAME = "UTF-8";
    public static final int MAX_DATA_LENGTH = 256;
    public static final int MAX_VALUE_LENGTH = 255;
    private static Logger logger = LoggerFactory.getLogger(ByteWrangler.class.getName());
    public static final byte[] NO_VALUE = new byte[0];
    public static final byte[] EMPTY_TXT = {0};
    private static final Charset CHARSET_UTF_8 = Charset.forName("UTF-8");

    public static void writeUTF(OutputStream outputStream, String str) throws IOException {
        outputStream.write(str.getBytes(CHARSET_UTF_8));
    }

    public static String readUTF(byte[] bArr) {
        return readUTF(bArr, 0, bArr.length);
    }

    public static String readUTF(byte[] bArr, int i, int i2) {
        return new String(bArr, i, i2, CHARSET_UTF_8);
    }

    public static void readProperties(Map<String, byte[]> map, byte[] bArr) throws Exception {
        int i;
        if (bArr != null) {
            int i2 = 0;
            while (i2 < bArr.length) {
                int i3 = i2 + 1;
                int i4 = bArr[i2] & 255;
                if (i4 == 0 || (i = i3 + i4) > bArr.length) {
                    map.clear();
                    return;
                }
                int i5 = 0;
                while (i5 < i4 && bArr[i3 + i5] != 61) {
                    i5++;
                }
                String utf = readUTF(bArr, i3, i5);
                if (utf == null) {
                    map.clear();
                    return;
                }
                if (i5 == i4) {
                    map.put(utf, NO_VALUE);
                } else {
                    int i6 = i5 + 1;
                    int i7 = i4 - i6;
                    byte[] bArr2 = new byte[i7];
                    System.arraycopy(bArr, i3 + i6, bArr2, 0, i7);
                    map.put(utf, bArr2);
                }
                i2 = i;
            }
        }
    }

    public static byte[] textFromProperties(Map<String, ?> map) {
        byte[] byteArray = null;
        if (map != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(256);
                for (Map.Entry<String, ?> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(100);
                    writeUTF(byteArrayOutputStream2, key);
                    if (value != null) {
                        if (value instanceof String) {
                            byteArrayOutputStream2.write(61);
                            writeUTF(byteArrayOutputStream2, (String) value);
                        } else if (value instanceof byte[]) {
                            byte[] bArr = (byte[]) value;
                            if (bArr.length > 0) {
                                byteArrayOutputStream2.write(61);
                                byteArrayOutputStream2.write(bArr, 0, bArr.length);
                            } else {
                                value = null;
                            }
                        } else {
                            throw new IllegalArgumentException("Invalid property value: " + value);
                        }
                    }
                    byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
                    if (byteArray2.length > 255) {
                        Logger logger2 = logger;
                        StringBuilder sb = new StringBuilder();
                        sb.append(key);
                        sb.append(value == null ? "" : "=" + value);
                        logger2.warn("Cannot have individual values larger that 255 chars. Offending value: {}", sb.toString());
                        return EMPTY_TXT;
                    }
                    byteArrayOutputStream.write((byte) byteArray2.length);
                    byteArrayOutputStream.write(byteArray2, 0, byteArray2.length);
                }
                byteArray = byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException("unexpected exception: " + e);
            }
        }
        return (byteArray == null || byteArray.length <= 0) ? EMPTY_TXT : byteArray;
    }

    public static byte[] encodeText(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(256);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(100);
        writeUTF(byteArrayOutputStream2, str);
        byte[] byteArray = byteArrayOutputStream2.toByteArray();
        if (byteArray.length > 255) {
            logger.warn("Cannot have individual values larger that 255 chars. Offending value: {}", str);
            return EMPTY_TXT;
        }
        byteArrayOutputStream.write((byte) byteArray.length);
        byteArrayOutputStream.write(byteArray, 0, byteArray.length);
        byte[] byteArray2 = byteArrayOutputStream.toByteArray();
        return byteArray2.length > 0 ? byteArray2 : EMPTY_TXT;
    }
}
