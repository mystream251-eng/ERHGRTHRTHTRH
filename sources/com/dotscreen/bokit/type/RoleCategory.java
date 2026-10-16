package com.dotscreen.bokit.type;

/* JADX INFO: loaded from: classes.dex */
public enum RoleCategory {
    ACTOR("ACTOR"),
    VOICE("VOICE"),
    PRESENTER("PRESENTER"),
    COMMENTATOR("COMMENTATOR"),
    GUEST("GUEST"),
    CORRESPONDENT("CORRESPONDENT"),
    CONDUCTOR("CONDUCTOR"),
    PLAYER("PLAYER"),
    DANCER("DANCER"),
    PARTICIPANT("PARTICIPANT"),
    JUDGE("JUDGE"),
    SELF("SELF"),
    $UNKNOWN("$UNKNOWN");

    private final String rawValue;

    RoleCategory(String str) {
        this.rawValue = str;
    }

    public String rawValue() {
        return this.rawValue;
    }

    public static RoleCategory safeValueOf(String str) {
        for (RoleCategory roleCategory : values()) {
            if (roleCategory.rawValue.equals(str)) {
                return roleCategory;
            }
        }
        return $UNKNOWN;
    }
}
