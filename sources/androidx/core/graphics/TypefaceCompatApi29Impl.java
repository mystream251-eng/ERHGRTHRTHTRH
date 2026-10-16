package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import com.viaccessorca.voplayer.VOPlayer;
import java.io.IOException;
import java.io.InputStream;
import tv.broadpeak.smartlib.session.streaming.StreamingSessionOptions;

/* JADX INFO: loaded from: classes.dex */
public class TypefaceCompatApi29Impl extends TypefaceCompatBaseImpl {
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    protected FontsContractCompat.FontInfo findBestInfo(FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    protected Typeface createFromInputStream(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    /* JADX WARN: Code duplicated, block: B:9:0x001c A[Catch: IOException -> 0x0060, PHI: r4
  0x001c: PHI (r4v5 android.graphics.fonts.FontFamily$Builder) = (r4v3 android.graphics.fonts.FontFamily$Builder), (r4v1 android.graphics.fonts.FontFamily$Builder) binds: [B:19:0x0051, B:8:0x001a] A[DONT_GENERATE, DONT_INLINE], TRY_LEAVE, TryCatch #2 {IOException -> 0x0060, blocks: (B:6:0x000e, B:9:0x001c, B:27:0x005f, B:26:0x005c, B:11:0x0020, B:15:0x0035, B:17:0x0047, B:18:0x004e, B:23:0x0057), top: B:45:0x000e, inners: #0, #1 }] */
    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontInfo(Context context, CancellationSignal cancellationSignal, FontsContractCompat.FontInfo[] fontInfoArr, int i) {
        ContentResolver contentResolver = context.getContentResolver();
        int length = fontInfoArr.length;
        FontFamily.Builder builder = null;
        int i2 = 0;
        while (true) {
            int i3 = 1;
            if (i2 >= length) {
                if (builder == null) {
                    return null;
                }
                return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle((i & 1) != 0 ? VOPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING : StreamingSessionOptions.CDN_EDGE_PARSER, (i & 2) != 0 ? 1 : 0)).build();
            }
            FontsContractCompat.FontInfo fontInfo = fontInfoArr[i2];
            try {
                ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(fontInfo.getUri(), "r", cancellationSignal);
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    try {
                        Font.Builder weight = new Font.Builder(parcelFileDescriptorOpenFileDescriptor).setWeight(fontInfo.getWeight());
                        if (!fontInfo.isItalic()) {
                            i3 = 0;
                        }
                        Font fontBuild = weight.setSlant(i3).setTtcIndex(fontInfo.getTtcIndex()).build();
                        if (builder == null) {
                            builder = new FontFamily.Builder(fontBuild);
                        } else {
                            builder.addFont(fontBuild);
                        }
                        if (parcelFileDescriptorOpenFileDescriptor != null) {
                            parcelFileDescriptorOpenFileDescriptor.close();
                        }
                    } catch (Throwable th) {
                        if (parcelFileDescriptorOpenFileDescriptor != null) {
                            try {
                                parcelFileDescriptorOpenFileDescriptor.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } else if (parcelFileDescriptorOpenFileDescriptor != null) {
                    parcelFileDescriptorOpenFileDescriptor.close();
                }
            } catch (IOException unused) {
            }
            i2++;
        }
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        FontResourcesParserCompat.FontFileResourceEntry[] entries = fontFamilyFilesResourceEntry.getEntries();
        int length = entries.length;
        FontFamily.Builder builder = null;
        int i2 = 0;
        while (true) {
            int i3 = 1;
            if (i2 >= length) {
                break;
            }
            FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry = entries[i2];
            try {
                Font.Builder weight = new Font.Builder(resources, fontFileResourceEntry.getResourceId()).setWeight(fontFileResourceEntry.getWeight());
                if (!fontFileResourceEntry.isItalic()) {
                    i3 = 0;
                }
                Font fontBuild = weight.setSlant(i3).setTtcIndex(fontFileResourceEntry.getTtcIndex()).setFontVariationSettings(fontFileResourceEntry.getVariationSettings()).build();
                if (builder == null) {
                    builder = new FontFamily.Builder(fontBuild);
                } else {
                    builder.addFont(fontBuild);
                }
            } catch (IOException unused) {
            }
            i2++;
        }
        if (builder == null) {
            return null;
        }
        return new Typeface.CustomFallbackBuilder(builder.build()).setStyle(new FontStyle((i & 1) != 0 ? VOPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING : StreamingSessionOptions.CDN_EDGE_PARSER, (i & 2) != 0 ? 1 : 0)).build();
    }

    @Override // androidx.core.graphics.TypefaceCompatBaseImpl
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int i, String str, int i2) {
        try {
            Font fontBuild = new Font.Builder(resources, i).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(fontBuild).build()).setStyle(fontBuild.getStyle()).build();
        } catch (IOException unused) {
            return null;
        }
    }
}
