package com.viaccessorca.voplayer;

/* JADX INFO: loaded from: classes2.dex */
public class VOSecondaryContent {
    private String m329t7p6390828478461;
    private int m329t7p6390828478462;
    private boolean m329t7p6390828478463;

    public VOSecondaryContent(String str, int i, boolean z) {
        this.m329t7p6390828478461 = null;
        this.m329t7p6390828478462 = -1;
        this.m329t7p6390828478463 = false;
        this.m329t7p6390828478461 = str;
        this.m329t7p6390828478462 = i;
        this.m329t7p6390828478463 = z;
    }

    public int getStartPositionInPrimary() {
        return this.m329t7p6390828478462;
    }

    public String getUrl() {
        return this.m329t7p6390828478461;
    }

    public boolean isPersistent() {
        return this.m329t7p6390828478463;
    }
}
