package org.tukaani.xz;

/* JADX INFO: loaded from: classes3.dex */
public class XZ {
    public static final int CHECK_CRC32 = 1;
    public static final int CHECK_CRC64 = 4;
    public static final int CHECK_NONE = 0;
    public static final int CHECK_SHA256 = 10;
    public static final byte[] HEADER_MAGIC = {-3, 55, 122, 88, 90, 0};
    public static final byte[] FOOTER_MAGIC = {89, 90};

    private XZ() {
    }
}
