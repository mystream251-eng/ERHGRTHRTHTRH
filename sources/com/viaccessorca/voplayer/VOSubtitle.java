package com.viaccessorca.voplayer;

import android.text.Html;
import java.io.UnsupportedEncodingException;

/* JADX INFO: loaded from: classes2.dex */
public class VOSubtitle {
    public static final byte Format_ASCII = 1;
    public static final byte Format_UTF16 = 3;
    public static final byte Format_UTF8 = 2;
    public static final byte Format_Unknown = 0;
    public static final byte Type_CC_CEA608 = 2;
    public static final byte Type_DVB = 5;
    public static final byte Type_SMPTE = 4;
    public static final byte Type_SUB_FILE = 1;
    public static final byte Type_TTML = 6;
    public static final byte Type_Unknown = 0;
    public static final byte Type_WEBVTT = 3;
    private byte[] m329t7p6390828478416;
    private String m329t7p6390828478417;
    private VOSubtitleCharAttributes[] m329t7p6390828478418;
    private VOSubtitleSettingsAttributes m329t7p6390828478419;
    private int m329t7p6390828478420;
    private int m329t7p6390828478421;
    private String m329t7p6390828478422;
    private int m329t7p6390828478459;
    private boolean m329t7p6390828478460;

    public VOSubtitle(String str) {
        this.m329t7p6390828478417 = str;
        this.m329t7p6390828478421 = 0;
        this.m329t7p6390828478459 = 0;
        this.m329t7p6390828478420 = 0;
        this.m329t7p6390828478418 = null;
        this.m329t7p6390828478419 = null;
        this.m329t7p6390828478460 = false;
        this.m329t7p6390828478422 = null;
    }

    public VOSubtitle(String str, VOSubtitleCharAttributes[] vOSubtitleCharAttributesArr, int i) {
        this.m329t7p6390828478417 = str;
        this.m329t7p6390828478421 = 0;
        this.m329t7p6390828478420 = i;
        this.m329t7p6390828478418 = vOSubtitleCharAttributesArr;
        this.m329t7p6390828478419 = null;
        this.m329t7p6390828478460 = false;
        this.m329t7p6390828478422 = null;
    }

    public VOSubtitleCharAttributes[] getCharArrayAttributes() {
        return this.m329t7p6390828478418;
    }

    public int getFileId() {
        return this.m329t7p6390828478459;
    }

    public int getFormat() {
        return this.m329t7p6390828478420;
    }

    public byte[] getRAWText() {
        return this.m329t7p6390828478416;
    }

    public VOSubtitleSettingsAttributes getSettingsAttributes() {
        int i = this.m329t7p6390828478421;
        if (i == 3 || i == 5) {
            return this.m329t7p6390828478419;
        }
        return null;
    }

    public String getText() {
        return this.m329t7p6390828478417;
    }

    public String getTextHtml() {
        return ("<p>" + this.m329t7p6390828478417.replaceAll("\r\n", "<br>")).replaceAll("\n", "<br>").replaceAll("\r", "<br>") + "</p>";
    }

    public String getTextPlanned() {
        return Html.fromHtml(this.m329t7p6390828478417.replace(" ", "&nbsp;").replace("\n", "<br />")).toString();
    }

    public String getTtmlStylingBuffer() {
        String str = this.m329t7p6390828478422;
        return str != null ? str.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "").replaceAll("\"", "\\\"") : str;
    }

    public int getType() {
        return this.m329t7p6390828478421;
    }

    public void setText(String str) {
        this.m329t7p6390828478417 = str;
    }

    public void setText(byte[] bArr, String str) throws UnsupportedEncodingException {
        this.m329t7p6390828478417 = new String(bArr, str);
    }
}
