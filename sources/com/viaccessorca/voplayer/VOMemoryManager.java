package com.viaccessorca.voplayer;

import com.viaccessorca.common.VOLibraryLoader;

/* JADX INFO: loaded from: classes2.dex */
class VOMemoryManager {
    static {
        VOLibraryLoader.b();
    }

    protected VOMemoryManager() {
    }

    private static final native void MzNEQ0Y1RDE4OUZBNDY2Mzk3QTUyQjJFOTMxQ0REOTU();

    private static final native int MzZGNjQwQTFDNTBENEYzOEJCODJDMkFDQ0NFM0U4ODE();

    private static final native void ODNEMjE4NEIyNTdCNDc4RkFFMzYwRDlFMzkyQjNGNkE(String str);

    private static final native void RDBBRDA5RjM3ODNBNDhFNTkzMTEzQjEzNzI3NTcwOEU();

    protected void cleanup() {
        MzNEQ0Y1RDE4OUZBNDY2Mzk3QTUyQjJFOTMxQ0REOTU();
    }

    protected void dump() {
        RDBBRDA5RjM3ODNBNDhFNTkzMTEzQjEzNzI3NTcwOEU();
    }

    protected int getCurrentAlloc() {
        return MzZGNjQwQTFDNTBENEYzOEJCODJDMkFDQ0NFM0U4ODE();
    }

    protected void start(String str) {
        ODNEMjE4NEIyNTdCNDc4RkFFMzYwRDlFMzkyQjNGNkE(str);
    }
}
