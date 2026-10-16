package com.viaccessorca.voplayer;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Pattern f143a = Pattern.compile("tts:origin=\"([0.-9.]+(%|px)) ([0.-9.]+(%|px))\"", 32);
    private static final Pattern b = Pattern.compile("tts:extent=\"([0.-9.]+(%|px)) ([0.-9.]+(%|px))\"", 32);

    private static final class b {
        public static BackgroundColorSpan getRGBColorSpan(byte b, int i, char c) {
            int i2;
            int i3 = (16711680 & i) >> 16;
            int i4 = (65280 & i) >> 8;
            int i5 = i & 255;
            if ((b & 1) == 1) {
                if (255 < c) {
                    c = 255;
                }
                i2 = 255 - c;
            } else {
                i2 = 0;
            }
            return new BackgroundColorSpan(Color.argb(i2, i3, i4, i5));
        }
    }

    private static final class c {
        static ForegroundColorSpan a(byte b, int i) {
            int i2 = (16711680 & i) >> 16;
            int i3 = (65280 & i) >> 8;
            int i4 = 255;
            int i5 = i & 255;
            if (b == 0) {
                i4 = 0;
            } else if (b != 1) {
                i4 = 128;
            }
            return new ForegroundColorSpan(Color.argb(i4, i2, i3, i5));
        }

        static StrikethroughSpan a(byte b) {
            if ((b & 8) == 8) {
                return new StrikethroughSpan();
            }
            return null;
        }

        static StyleSpan b(byte b) {
            int i = (b & 2) != 2 ? 0 : 2;
            if ((b & 4) == 4) {
                i++;
            }
            return new StyleSpan(i);
        }

        static final UnderlineSpan c(byte b) {
            if ((b & 1) == 1) {
                return new UnderlineSpan();
            }
            return null;
        }
    }

    protected static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public String f144a;
        public String b;
        public String c = "";

        protected d() {
        }
    }

    private static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f145a;
        String b;
        String c;
        String d;

        private e() {
            this.f145a = "-1";
            this.b = "-1";
            this.c = "-1";
            this.d = "-1";
        }
    }

    private static d a(VOSubtitle vOSubtitle) {
        String strSubstring;
        int iIndexOf;
        String str;
        d dVar = new d();
        dVar.f144a = "";
        dVar.b = "";
        dVar.c = "";
        int iIndexOf2 = vOSubtitle.getText().indexOf("ase64");
        if (iIndexOf2 != -1 && (iIndexOf = (strSubstring = vOSubtitle.getText().substring(iIndexOf2)).indexOf(">")) != -1) {
            dVar.f144a = "<div><span style=\"vertical-align:bottom; display:inline-block; height: 100%;\"></span><img class=\"text-png\" src=\"data:image/png;base64," + strSubstring.substring(iIndexOf + 1).split("<")[0] + "\"></div>";
            e eVarD = d(vOSubtitle);
            if (eVarD.f145a == "-1" && eVarD.b == "-1" && eVarD.c == "-1" && eVarD.d == "-1") {
                str = "top: auto; bottom: 0px";
            } else {
                str = "top: " + eVarD.f145a + "; left: " + eVarD.b + "; width: " + eVarD.c + "; height: " + eVarD.d + "";
            }
            dVar.b = str;
            dVar.c = "img-smpte";
        }
        return dVar;
    }

    protected static void a(VOSubtitle vOSubtitle, TextView textView) {
        if (vOSubtitle != null) {
            String textPlanned = vOSubtitle.getTextPlanned();
            if (textPlanned == null || 2 != vOSubtitle.getType()) {
                textView.setText((CharSequence) null);
            } else {
                a(textPlanned, vOSubtitle.getCharArrayAttributes(), textView);
            }
        }
    }

    private static void a(String str, VOSubtitleCharAttributes[] vOSubtitleCharAttributesArr, TextView textView) {
        SpannableString spannableString = new SpannableString(str);
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            if ((!Character.isWhitespace(str.charAt(i2)) || Character.getType(str.charAt(i2)) != 15) && vOSubtitleCharAttributesArr != null && i < vOSubtitleCharAttributesArr.length) {
                int i3 = i2 + 1;
                spannableString.setSpan(c.a(vOSubtitleCharAttributesArr[i].getForegroundDisplayMode(), vOSubtitleCharAttributesArr[i].getForegroundColor()), i2, i3, 256);
                spannableString.setSpan(c.b(vOSubtitleCharAttributesArr[i].getForegroundFontStyle()), i2, i3, 256);
                spannableString.setSpan(c.a(vOSubtitleCharAttributesArr[i].getForegroundFontStyle()), i2, i3, 256);
                spannableString.setSpan(c.c(vOSubtitleCharAttributesArr[i].getForegroundFontStyle()), i2, i3, 256);
                spannableString.setSpan(b.getRGBColorSpan(vOSubtitleCharAttributesArr[i].getBackgoundDisplayMode(), vOSubtitleCharAttributesArr[i].getBackgoundColor(), vOSubtitleCharAttributesArr[i].getBackgoundTransparencyLevel()), i2, i3, 256);
                i++;
            }
        }
        textView.setTypeface(Typeface.MONOSPACE);
        textView.setTextScaleX(1.3f);
        textView.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
        textView.setText(spannableString);
    }

    protected static d b(VOSubtitle vOSubtitle) {
        d dVar;
        String str;
        if (vOSubtitle == null) {
            return null;
        }
        int type = vOSubtitle.getType();
        if (type == 3) {
            return c(vOSubtitle);
        }
        if (type != 4) {
            if (type != 6) {
                dVar = new d();
                dVar.f144a = vOSubtitle.getTextHtml();
                dVar.b = "text-align:center;top:auto;bottom:0px;width:100%;";
                str = "text-sub text-srt text-sub-fileid-" + vOSubtitle.getFileId();
            } else if (!vOSubtitle.getText().contains("<smpte:image")) {
                dVar = new d();
                dVar.f144a = vOSubtitle.getText();
                dVar.b = "";
                str = "text-sub text-ttml text-sub-fileid-0";
            }
            dVar.c = str;
            return dVar;
        }
        return a(vOSubtitle);
    }

    /* JADX WARN: Code duplicated, block: B:19:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:20:0x00e8  */
    /* JADX WARN: Instruction removed from duplicated block: B:19:0x00cc, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:20:0x00e8, please report this as an issue */
    private static d c(VOSubtitle vOSubtitle) {
        StringBuilder sb;
        int size;
        StringBuilder sb2;
        String str;
        d dVar = new d();
        dVar.f144a = "<div class ='text-sub text-webvtt text-sub-fileid-0'>" + vOSubtitle.getTextHtml() + "</div>";
        VOSubtitleSettingsAttributes settingsAttributes = vOSubtitle.getSettingsAttributes();
        if (settingsAttributes.getSize() == 100 || settingsAttributes.getPosition() + settingsAttributes.getSize() <= 100) {
            sb = new StringBuilder();
            sb.append("");
            sb.append("width: ");
            size = settingsAttributes.getSize();
        } else {
            sb = new StringBuilder();
            sb.append("");
            sb.append("width: ");
            size = 100 - settingsAttributes.getPosition();
        }
        sb.append(size);
        sb.append("%; ");
        String string = sb.toString();
        if (settingsAttributes.getSize() < 100) {
            string = string + "left: " + (settingsAttributes.getPosition() - (settingsAttributes.getSize() / 2)) + "%; ";
        }
        int alignType = settingsAttributes.getAlignType();
        if (alignType == 0) {
            String str2 = string + "text-align: left; ";
            sb2 = new StringBuilder();
            sb2.append(str2);
            str = "justify-content: flex-start; ";
        } else if (alignType == 2) {
            String str3 = string + "text-align: right; ";
            sb2 = new StringBuilder();
            sb2.append(str3);
            str = "justify-content: flex-end; ";
        } else if (alignType == 3) {
            String str4 = string + "text-align: left; ";
            sb2 = new StringBuilder();
            sb2.append(str4);
            str = "justify-content: flex-start; ";
        } else if (alignType != 4) {
            String str5 = (string + "text-align: center; ") + "display: table; ";
            sb2 = new StringBuilder();
            sb2.append(str5);
            str = "margin: 0 auto; ";
        } else {
            String str6 = string + "text-align: right; ";
            sb2 = new StringBuilder();
            sb2.append(str6);
            str = "justify-content: flex-end; ";
        }
        sb2.append(str);
        dVar.b = sb2.toString();
        dVar.c = "";
        return dVar;
    }

    private static e d(VOSubtitle vOSubtitle) {
        e eVar = new e();
        Matcher matcher = b.matcher(vOSubtitle.getText());
        if (matcher.find()) {
            eVar.c = matcher.group(1);
            eVar.d = matcher.group(3);
        }
        Matcher matcher2 = f143a.matcher(vOSubtitle.getText());
        if (matcher2.find()) {
            eVar.b = matcher2.group(1);
            eVar.f145a = matcher2.group(3);
        }
        return eVar;
    }
}
