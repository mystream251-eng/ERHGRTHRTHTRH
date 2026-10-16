package com.viaccessorca.voplayer;

/* JADX INFO: loaded from: classes2.dex */
public class VOSubtitleCharAttributes {
    public static final byte DisplayMode_Caption_Off = 0;
    public static final byte DisplayMode_Caption_On = 1;
    public static final byte DisplayMode_Caption_On_Flash = 2;
    public static final byte FontStyle_Bold = 4;
    public static final byte FontStyle_Italic = 2;
    public static final byte FontStyle_None = 0;
    public static final byte FontStyle_Strikethrough = 8;
    public static final byte FontStyle_Underline = 1;
    public static final char Transparency_Full = 255;
    public static final char Transparency_Medium = 128;
    public static final char Transparency_None = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private h f90a;
    private f b;

    public VOSubtitleCharAttributes(byte b, int i, byte b2, byte b3, int i2, char c) {
        this.f90a = new h(b, i, b2);
        this.b = new f(b3, i2, c);
    }

    public int getBackgoundColor() {
        return this.b.getColor();
    }

    public byte getBackgoundDisplayMode() {
        return this.b.getDisplayMode();
    }

    public char getBackgoundTransparencyLevel() {
        return this.b.getTransparencyLevel();
    }

    public int getForegroundColor() {
        return this.f90a.getColor();
    }

    public byte getForegroundDisplayMode() {
        return this.f90a.getDisplayMode();
    }

    public byte getForegroundFontStyle() {
        return this.f90a.getFontStyle();
    }

    public void setCharBackgroundAttributes(byte b, int i, char c) {
    }

    public void setCharForegroundAttributes(byte b, int i, byte b2) {
    }
}
