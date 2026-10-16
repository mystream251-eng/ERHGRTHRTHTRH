package com.viaccessorca.voplayer;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.MimeTypes;

/* JADX INFO: loaded from: classes2.dex */
public class VOAudioTrack {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f59a;
    private String b;
    private String c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private AdaptationSetRole h;
    private Accessibility i;

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

    public VOAudioTrack(String str, String str2, String str3, boolean z, boolean z2) {
        this.f59a = null;
        this.b = null;
        this.c = null;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = AdaptationSetRole.UNSET;
        this.i = Accessibility.UNSET;
        this.f59a = str;
        this.b = str2;
        this.c = str3;
        this.e = z;
        this.d = z2;
    }

    public VOAudioTrack(String str, String str2, String str3, boolean z, boolean z2, boolean z3) {
        this(str, str2, str3, z, z2);
    }

    public VOAudioTrack(String str, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4) {
        this(str, str2, str3, z, z2, z3);
        this.g = z4;
    }

    public VOAudioTrack(String str, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, int i) {
        this(str, str2, str3, z, z2, z3, z4);
        this.h = AdaptationSetRole.values()[i];
    }

    public VOAudioTrack(String str, String str2, String str3, boolean z, boolean z2, boolean z3, boolean z4, int i, int i2) {
        this(str, str2, str3, z, z2, z3, z4, i);
        this.i = Accessibility.values()[i2];
    }

    public boolean IsAudioDescription() {
        return this.g;
    }

    public boolean IsDefault() {
        return this.d;
    }

    public boolean IsInband() {
        return this.f;
    }

    public boolean IsSelected() {
        return this.e;
    }

    public Accessibility getAccessibility() {
        return this.i;
    }

    public AdaptationSetRole getAdaptationSetRole() {
        return this.h;
    }

    public String getCodec() {
        String str = this.c;
        return (str == null || str.equals("AACL") || this.c.equals("") || this.c.equals(C.LANGUAGE_UNDETERMINED) || this.c.equals("Und")) ? MimeTypes.AUDIO_MP4 : this.c;
    }

    public String getLanguage() {
        String str = this.b;
        return str != null ? str : "default";
    }

    public String getName() {
        String str = this.f59a;
        return str != null ? str : "default";
    }

    public void setSelected(boolean z) {
        this.e = z;
    }
}
