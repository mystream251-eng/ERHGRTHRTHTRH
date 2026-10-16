package com.viaccessorca.voplayer;

/* JADX INFO: loaded from: classes2.dex */
public class VOSubtitleTrack {
    public static final byte SubType_Chapter = 1;
    public static final byte SubType_ClosedCaption = 3;
    public static final byte SubType_MediaDescription = 4;
    public static final byte SubType_Narrative = 2;
    public static final byte SubType_Unknown = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f93a;
    private String b;
    private int c;
    private boolean d;
    private int e;
    private boolean f;
    private boolean g;
    private boolean h;
    private AdaptationSetRole i;
    private Accessibility j;

    public enum Accessibility {
        UNSET,
        VISUALLY_IMPAIRED,
        HARD_OF_HEARING,
        DESCRIPTION,
        ENHANCED_AUDIO_INTELLIGIBILITY,
        CAPTION,
        SIGN,
        UNKNOWN
    }

    public enum AdaptationSetRole {
        UNSET,
        ALTERNATE,
        COMMENTARY,
        DESCRIPTION,
        DUB,
        EMERGENCY,
        MAIN,
        SUBTITLE,
        SUPPLEMENTARY,
        UNKNOWN
    }

    public VOSubtitleTrack(String str, String str2, int i, boolean z, int i2) {
        this.f93a = null;
        this.b = null;
        this.c = 0;
        this.d = false;
        this.e = 0;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = AdaptationSetRole.UNSET;
        this.j = Accessibility.UNSET;
        this.f93a = str;
        this.b = str2;
        this.c = i;
        this.d = z;
        this.e = i2;
    }

    public VOSubtitleTrack(String str, String str2, int i, boolean z, int i2, boolean z2, boolean z3, boolean z4) {
        this(str, str2, i, z, i2);
        this.f = z2;
        this.g = z3;
        this.h = z4;
    }

    public VOSubtitleTrack(String str, String str2, int i, boolean z, int i2, boolean z2, boolean z3, boolean z4, int i3) {
        this(str, str2, i, z, i2, z2, z3, z4);
        this.i = AdaptationSetRole.values()[i3];
    }

    public VOSubtitleTrack(String str, String str2, int i, boolean z, int i2, boolean z2, boolean z3, boolean z4, int i3, int i4) {
        this(str, str2, i, z, i2, z2, z3, z4, i3);
        this.j = Accessibility.values()[i4];
    }

    public boolean IsAutoselect() {
        return this.h;
    }

    public boolean IsDefault() {
        return this.f;
    }

    public boolean IsForced() {
        return this.g;
    }

    public boolean IsSelected() {
        return this.d;
    }

    public Accessibility getAccessibility() {
        return this.j;
    }

    public AdaptationSetRole getAdaptationSetRole() {
        return this.i;
    }

    public String getLanguage() {
        String str = this.b;
        return str != null ? str : "default";
    }

    public String getName() {
        String str = this.f93a;
        return str != null ? str : "default";
    }

    public int getSubtitleSubType() {
        return this.e;
    }

    public int getSubtitleType() {
        return this.c;
    }

    public void setSelected(boolean z) {
        this.d = z;
    }
}
