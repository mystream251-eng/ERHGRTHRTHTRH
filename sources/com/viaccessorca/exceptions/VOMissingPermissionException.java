package com.viaccessorca.exceptions;

/* JADX INFO: loaded from: classes2.dex */
public final class VOMissingPermissionException extends VOException {
    private static final long serialVersionUID = -2293389430541029149L;
    private String[] mPermissions;

    public VOMissingPermissionException(String str) {
        super(VOStatusCode.ERROR_SECURITY_MISSING_PERMISSION);
        this.mPermissions = new String[]{str};
    }

    public VOMissingPermissionException(String[] strArr) {
        super(VOStatusCode.ERROR_SECURITY_MISSING_PERMISSION);
        this.mPermissions = strArr;
    }

    public String[] getPermissions() {
        return this.mPermissions;
    }
}
