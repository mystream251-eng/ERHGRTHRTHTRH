package org.tukaani.xz;

/* JADX INFO: loaded from: classes3.dex */
interface FilterEncoder extends FilterCoder {
    long getFilterID();

    byte[] getFilterProps();

    FinishableOutputStream getOutputStream(FinishableOutputStream finishableOutputStream, ArrayCache arrayCache);

    boolean supportsFlushing();
}
