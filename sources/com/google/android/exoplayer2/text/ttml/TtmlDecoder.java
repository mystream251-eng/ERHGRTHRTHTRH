package com.google.android.exoplayer2.text.ttml;

import android.text.Layout;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.text.SimpleSubtitleDecoder;
import com.google.android.exoplayer2.text.SubtitleDecoderException;
import com.google.android.exoplayer2.util.ColorParser;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/* JADX INFO: loaded from: classes2.dex */
public final class TtmlDecoder extends SimpleSubtitleDecoder {
    private static final String ATTR_BEGIN = "begin";
    private static final String ATTR_DURATION = "dur";
    private static final String ATTR_END = "end";
    private static final String ATTR_IMAGE = "backgroundImage";
    private static final String ATTR_REGION = "region";
    private static final String ATTR_STYLE = "style";
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final String TAG = "TtmlDecoder";
    private static final String TTP = "http://www.w3.org/ns/ttml#parameter";
    private final XmlPullParserFactory xmlParserFactory;
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern PERCENTAGE_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final Pattern PIXEL_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)px (\\d+\\.?\\d*?)px$");
    private static final Pattern CELL_RESOLUTION = Pattern.compile("^(\\d+) (\\d+)$");
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);
    private static final CellResolution DEFAULT_CELL_RESOLUTION = new CellResolution(32, 15);

    public TtmlDecoder() {
        super(TAG);
        try {
            XmlPullParserFactory xmlPullParserFactoryNewInstance = XmlPullParserFactory.newInstance();
            this.xmlParserFactory = xmlPullParserFactoryNewInstance;
            xmlPullParserFactoryNewInstance.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.text.SimpleSubtitleDecoder
    public TtmlSubtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        TtmlSubtitle ttmlSubtitle;
        FrameAndTickRate frameAndTickRate;
        try {
            XmlPullParser xmlPullParserNewPullParser = this.xmlParserFactory.newPullParser();
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            HashMap map3 = new HashMap();
            TtsExtent ttsExtent = null;
            map2.put("", new TtmlRegion(null));
            xmlPullParserNewPullParser.setInput(new ByteArrayInputStream(bArr, 0, i), null);
            ArrayDeque arrayDeque = new ArrayDeque();
            FrameAndTickRate frameAndTickRates = DEFAULT_FRAME_AND_TICK_RATE;
            CellResolution cellResolution = DEFAULT_CELL_RESOLUTION;
            int i2 = 0;
            TtmlSubtitle ttmlSubtitle2 = null;
            for (int eventType = xmlPullParserNewPullParser.getEventType(); eventType != 1; eventType = xmlPullParserNewPullParser.getEventType()) {
                TtmlNode ttmlNode = (TtmlNode) arrayDeque.peek();
                if (i2 == 0) {
                    String name = xmlPullParserNewPullParser.getName();
                    if (eventType == 2) {
                        if (TtmlNode.TAG_TT.equals(name)) {
                            frameAndTickRates = parseFrameAndTickRates(xmlPullParserNewPullParser);
                            cellResolution = parseCellResolution(xmlPullParserNewPullParser, DEFAULT_CELL_RESOLUTION);
                            ttsExtent = parseTtsExtent(xmlPullParserNewPullParser);
                        }
                        TtsExtent ttsExtent2 = ttsExtent;
                        FrameAndTickRate frameAndTickRate2 = frameAndTickRates;
                        CellResolution cellResolution2 = cellResolution;
                        if (isSupportedTag(name)) {
                            if (TtmlNode.TAG_HEAD.equals(name)) {
                                ttmlSubtitle = ttmlSubtitle2;
                                frameAndTickRate = frameAndTickRate2;
                                parseHeader(xmlPullParserNewPullParser, map, cellResolution2, ttsExtent2, map2, map3);
                            } else {
                                ttmlSubtitle = ttmlSubtitle2;
                                frameAndTickRate = frameAndTickRate2;
                                try {
                                    TtmlNode node = parseNode(xmlPullParserNewPullParser, ttmlNode, map2, frameAndTickRate);
                                    arrayDeque.push(node);
                                    if (ttmlNode != null) {
                                        ttmlNode.addChild(node);
                                    }
                                } catch (SubtitleDecoderException e) {
                                    Log.w(TAG, "Suppressing parser error", e);
                                    i2++;
                                }
                            }
                            frameAndTickRates = frameAndTickRate;
                            ttsExtent = ttsExtent2;
                            cellResolution = cellResolution2;
                        } else {
                            Log.i(TAG, "Ignoring unsupported tag: " + xmlPullParserNewPullParser.getName());
                            i2++;
                            frameAndTickRates = frameAndTickRate2;
                            ttsExtent = ttsExtent2;
                            cellResolution = cellResolution2;
                        }
                    } else {
                        ttmlSubtitle = ttmlSubtitle2;
                        if (eventType == 4) {
                            ttmlNode.addChild(TtmlNode.buildTextNode(xmlPullParserNewPullParser.getText()));
                        } else if (eventType == 3) {
                            ttmlSubtitle2 = xmlPullParserNewPullParser.getName().equals(TtmlNode.TAG_TT) ? new TtmlSubtitle((TtmlNode) arrayDeque.peek(), map, map2, map3) : ttmlSubtitle;
                            arrayDeque.pop();
                        }
                    }
                    xmlPullParserNewPullParser.next();
                } else {
                    ttmlSubtitle = ttmlSubtitle2;
                    if (eventType == 2) {
                        i2++;
                    } else if (eventType == 3) {
                        i2--;
                    }
                }
                ttmlSubtitle2 = ttmlSubtitle;
                xmlPullParserNewPullParser.next();
            }
            return ttmlSubtitle2;
        } catch (IOException e2) {
            throw new IllegalStateException("Unexpected error when reading input.", e2);
        } catch (XmlPullParserException e3) {
            throw new SubtitleDecoderException("Unable to decode source", e3);
        }
    }

    private FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlPullParser) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "frameRate");
        int i = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        float f = 1.0f;
        String attributeValue2 = xmlPullParser.getAttributeValue(TTP, "frameRateMultiplier");
        if (attributeValue2 != null) {
            String[] strArrSplit = Util.split(attributeValue2, " ");
            if (strArrSplit.length != 2) {
                throw new SubtitleDecoderException("frameRateMultiplier doesn't have 2 parts");
            }
            f = Integer.parseInt(strArrSplit[0]) / Integer.parseInt(strArrSplit[1]);
        }
        int i2 = DEFAULT_FRAME_AND_TICK_RATE.subFrameRate;
        String attributeValue3 = xmlPullParser.getAttributeValue(TTP, "subFrameRate");
        if (attributeValue3 != null) {
            i2 = Integer.parseInt(attributeValue3);
        }
        int i3 = DEFAULT_FRAME_AND_TICK_RATE.tickRate;
        String attributeValue4 = xmlPullParser.getAttributeValue(TTP, "tickRate");
        if (attributeValue4 != null) {
            i3 = Integer.parseInt(attributeValue4);
        }
        return new FrameAndTickRate(i * f, i2, i3);
    }

    private CellResolution parseCellResolution(XmlPullParser xmlPullParser, CellResolution cellResolution) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "cellResolution");
        if (attributeValue == null) {
            return cellResolution;
        }
        Matcher matcher = CELL_RESOLUTION.matcher(attributeValue);
        if (!matcher.matches()) {
            Log.w(TAG, "Ignoring malformed cell resolution: " + attributeValue);
            return cellResolution;
        }
        try {
            int i = Integer.parseInt(matcher.group(1));
            int i2 = Integer.parseInt(matcher.group(2));
            if (i == 0 || i2 == 0) {
                throw new SubtitleDecoderException("Invalid cell resolution " + i + " " + i2);
            }
            return new CellResolution(i, i2);
        } catch (NumberFormatException unused) {
            Log.w(TAG, "Ignoring malformed cell resolution: " + attributeValue);
            return cellResolution;
        }
    }

    private TtsExtent parseTtsExtent(XmlPullParser xmlPullParser) {
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_EXTENT);
        if (attributeValue == null) {
            return null;
        }
        Matcher matcher = PIXEL_COORDINATES.matcher(attributeValue);
        if (!matcher.matches()) {
            Log.w(TAG, "Ignoring non-pixel tts extent: " + attributeValue);
            return null;
        }
        try {
            return new TtsExtent(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        } catch (NumberFormatException unused) {
            Log.w(TAG, "Ignoring malformed tts extent: " + attributeValue);
            return null;
        }
    }

    private Map<String, TtmlStyle> parseHeader(XmlPullParser xmlPullParser, Map<String, TtmlStyle> map, CellResolution cellResolution, TtsExtent ttsExtent, Map<String, TtmlRegion> map2, Map<String, String> map3) throws XmlPullParserException, IOException {
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "style")) {
                String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "style");
                TtmlStyle styleAttributes = parseStyleAttributes(xmlPullParser, new TtmlStyle());
                if (attributeValue != null) {
                    for (String str : parseStyleIds(attributeValue)) {
                        styleAttributes.chain(map.get(str));
                    }
                }
                if (styleAttributes.getId() != null) {
                    map.put(styleAttributes.getId(), styleAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "region")) {
                TtmlRegion regionAttributes = parseRegionAttributes(xmlPullParser, cellResolution, ttsExtent);
                if (regionAttributes != null) {
                    map2.put(regionAttributes.id, regionAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, TtmlNode.TAG_METADATA)) {
                parseMetadata(xmlPullParser, map3);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, TtmlNode.TAG_HEAD));
        return map;
    }

    private void parseMetadata(XmlPullParser xmlPullParser, Map<String, String> map) throws XmlPullParserException, IOException {
        String attributeValue;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, TtmlNode.TAG_IMAGE) && (attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "id")) != null) {
                map.put(attributeValue, xmlPullParser.nextText());
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, TtmlNode.TAG_METADATA));
    }

    /* JADX WARN: Code duplicated, block: B:53:0x0149  */
    private TtmlRegion parseRegionAttributes(XmlPullParser xmlPullParser, CellResolution cellResolution, TtsExtent ttsExtent) {
        float f;
        float f2;
        float f3;
        float f4;
        int i;
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "id");
        if (attributeValue == null) {
            return null;
        }
        String attributeValue2 = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_ORIGIN);
        if (attributeValue2 != null) {
            Matcher matcher = PERCENTAGE_COORDINATES.matcher(attributeValue2);
            Matcher matcher2 = PIXEL_COORDINATES.matcher(attributeValue2);
            if (matcher.matches()) {
                try {
                    float f5 = Float.parseFloat(matcher.group(1)) / 100.0f;
                    f = Float.parseFloat(matcher.group(2)) / 100.0f;
                    f2 = f5;
                } catch (NumberFormatException unused) {
                    Log.w(TAG, "Ignoring region with malformed origin: " + attributeValue2);
                    return null;
                }
            } else {
                if (!matcher2.matches()) {
                    Log.w(TAG, "Ignoring region with unsupported origin: " + attributeValue2);
                    return null;
                }
                if (ttsExtent == null) {
                    Log.w(TAG, "Ignoring region with missing tts:extent: " + attributeValue2);
                    return null;
                }
                try {
                    int i2 = Integer.parseInt(matcher2.group(1));
                    int i3 = Integer.parseInt(matcher2.group(2));
                    f2 = i2 / ttsExtent.width;
                    f = i3 / ttsExtent.height;
                } catch (NumberFormatException unused2) {
                    Log.w(TAG, "Ignoring region with malformed origin: " + attributeValue2);
                    return null;
                }
            }
            String attributeValue3 = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_EXTENT);
            if (attributeValue3 != null) {
                Matcher matcher3 = PERCENTAGE_COORDINATES.matcher(attributeValue3);
                Matcher matcher4 = PIXEL_COORDINATES.matcher(attributeValue3);
                if (matcher3.matches()) {
                    try {
                        float f6 = Float.parseFloat(matcher3.group(1)) / 100.0f;
                        f3 = Float.parseFloat(matcher3.group(2)) / 100.0f;
                        f4 = f6;
                    } catch (NumberFormatException unused3) {
                        Log.w(TAG, "Ignoring region with malformed extent: " + attributeValue2);
                        return null;
                    }
                } else {
                    if (!matcher4.matches()) {
                        Log.w(TAG, "Ignoring region with unsupported extent: " + attributeValue2);
                        return null;
                    }
                    if (ttsExtent == null) {
                        Log.w(TAG, "Ignoring region with missing tts:extent: " + attributeValue2);
                        return null;
                    }
                    try {
                        int i4 = Integer.parseInt(matcher4.group(1));
                        int i5 = Integer.parseInt(matcher4.group(2));
                        f4 = i4 / ttsExtent.width;
                        f3 = i5 / ttsExtent.height;
                    } catch (NumberFormatException unused4) {
                        Log.w(TAG, "Ignoring region with malformed extent: " + attributeValue2);
                        return null;
                    }
                }
                String attributeValue4 = XmlPullParserUtil.getAttributeValue(xmlPullParser, TtmlNode.ATTR_TTS_DISPLAY_ALIGN);
                if (attributeValue4 != null) {
                    String lowerInvariant = Util.toLowerInvariant(attributeValue4);
                    byte b = -1;
                    int iHashCode = lowerInvariant.hashCode();
                    if (iHashCode != -1364013995) {
                        if (iHashCode == 92734940 && lowerInvariant.equals("after")) {
                            b = 1;
                        }
                    } else if (lowerInvariant.equals(TtmlNode.CENTER)) {
                        b = 0;
                    }
                    if (b == 0) {
                        f += f3 / 2.0f;
                        i = 1;
                    } else if (b != 1) {
                        i = 0;
                    } else {
                        f += f3;
                        i = 2;
                    }
                } else {
                    i = 0;
                }
                return new TtmlRegion(attributeValue, f2, f, 0, i, f4, 1, 1.0f / cellResolution.rows);
            }
            Log.w(TAG, "Ignoring region without an extent");
            return null;
        }
        Log.w(TAG, "Ignoring region without an origin");
        return null;
    }

    private String[] parseStyleIds(String str) {
        String strTrim = str.trim();
        return strTrim.isEmpty() ? new String[0] : Util.split(strTrim, "\\s+");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private TtmlStyle parseStyleAttributes(XmlPullParser xmlPullParser, TtmlStyle ttmlStyle) {
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            String attributeValue = xmlPullParser.getAttributeValue(i);
            byte b = -1;
            switch (xmlPullParser.getAttributeName(i)) {
                case "id":
                    if (!"style".equals(xmlPullParser.getName())) {
                        break;
                    } else {
                        ttmlStyle = createIfNull(ttmlStyle).setId(attributeValue);
                        break;
                    }
                    break;
                case "backgroundColor":
                    ttmlStyle = createIfNull(ttmlStyle);
                    try {
                        ttmlStyle.setBackgroundColor(ColorParser.parseTtmlColor(attributeValue));
                        break;
                    } catch (IllegalArgumentException unused) {
                        Log.w(TAG, "Failed parsing background value: " + attributeValue);
                        break;
                    }
                    break;
                case "color":
                    ttmlStyle = createIfNull(ttmlStyle);
                    try {
                        ttmlStyle.setFontColor(ColorParser.parseTtmlColor(attributeValue));
                        break;
                    } catch (IllegalArgumentException unused2) {
                        Log.w(TAG, "Failed parsing color value: " + attributeValue);
                        break;
                    }
                    break;
                case "fontFamily":
                    ttmlStyle = createIfNull(ttmlStyle).setFontFamily(attributeValue);
                    break;
                case "fontSize":
                    try {
                        ttmlStyle = createIfNull(ttmlStyle);
                        parseFontSize(attributeValue, ttmlStyle);
                        break;
                    } catch (SubtitleDecoderException unused3) {
                        Log.w(TAG, "Failed parsing fontSize value: " + attributeValue);
                        break;
                    }
                    break;
                case "fontWeight":
                    ttmlStyle = createIfNull(ttmlStyle).setBold(TtmlNode.BOLD.equalsIgnoreCase(attributeValue));
                    break;
                case "fontStyle":
                    ttmlStyle = createIfNull(ttmlStyle).setItalic(TtmlNode.ITALIC.equalsIgnoreCase(attributeValue));
                    break;
                case "textAlign":
                    String lowerInvariant = Util.toLowerInvariant(attributeValue);
                    switch (lowerInvariant.hashCode()) {
                        case -1364013995:
                            if (lowerInvariant.equals(TtmlNode.CENTER)) {
                                b = 4;
                            }
                            break;
                        case 100571:
                            if (lowerInvariant.equals("end")) {
                                b = 3;
                            }
                            break;
                        case 3317767:
                            if (lowerInvariant.equals(TtmlNode.LEFT)) {
                                b = 0;
                            }
                            break;
                        case 108511772:
                            if (lowerInvariant.equals(TtmlNode.RIGHT)) {
                                b = 2;
                            }
                            break;
                        case 109757538:
                            if (lowerInvariant.equals("start")) {
                                b = 1;
                            }
                            break;
                    }
                    if (b == 0) {
                        ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_NORMAL);
                        break;
                    } else {
                        if (b == 1) {
                            ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_NORMAL);
                        } else if (b == 2) {
                            ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_OPPOSITE);
                        } else if (b == 3) {
                            ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_OPPOSITE);
                        } else if (b == 4) {
                            ttmlStyle = createIfNull(ttmlStyle).setTextAlign(Layout.Alignment.ALIGN_CENTER);
                        }
                        break;
                    }
                    break;
                case "textDecoration":
                    String lowerInvariant2 = Util.toLowerInvariant(attributeValue);
                    switch (lowerInvariant2.hashCode()) {
                        case -1461280213:
                            if (lowerInvariant2.equals(TtmlNode.NO_UNDERLINE)) {
                                b = 3;
                            }
                            break;
                        case -1026963764:
                            if (lowerInvariant2.equals(TtmlNode.UNDERLINE)) {
                                b = 2;
                            }
                            break;
                        case 913457136:
                            if (lowerInvariant2.equals(TtmlNode.NO_LINETHROUGH)) {
                                b = 1;
                            }
                            break;
                        case 1679736913:
                            if (lowerInvariant2.equals(TtmlNode.LINETHROUGH)) {
                                b = 0;
                            }
                            break;
                    }
                    if (b == 0) {
                        ttmlStyle = createIfNull(ttmlStyle).setLinethrough(true);
                        break;
                    } else {
                        if (b == 1) {
                            ttmlStyle = createIfNull(ttmlStyle).setLinethrough(false);
                        } else if (b == 2) {
                            ttmlStyle = createIfNull(ttmlStyle).setUnderline(true);
                        } else if (b == 3) {
                            ttmlStyle = createIfNull(ttmlStyle).setUnderline(false);
                        }
                        break;
                    }
                    break;
            }
        }
        return ttmlStyle;
    }

    private TtmlStyle createIfNull(TtmlStyle ttmlStyle) {
        return ttmlStyle == null ? new TtmlStyle() : ttmlStyle;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:25:0x0074  */
    /* JADX WARN: Code duplicated, block: B:71:0x0100  */
    private TtmlNode parseNode(XmlPullParser xmlPullParser, TtmlNode ttmlNode, Map<String, TtmlRegion> map, FrameAndTickRate frameAndTickRate) throws SubtitleDecoderException {
        long j;
        long j2;
        byte b;
        int attributeCount = xmlPullParser.getAttributeCount();
        TtmlStyle styleAttributes = parseStyleAttributes(xmlPullParser, null);
        String[] strArr = null;
        String strSubstring = null;
        String str = "";
        long timeExpression = C.TIME_UNSET;
        long timeExpression2 = C.TIME_UNSET;
        long timeExpression3 = C.TIME_UNSET;
        for (int i = 0; i < attributeCount; i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            switch (attributeName) {
                case "region":
                    b = 4;
                    break;
                case "dur":
                    b = 2;
                    break;
                case "end":
                    b = 1;
                    break;
                case "begin":
                    b = 0;
                    break;
                case "style":
                    b = 3;
                    break;
                case "backgroundImage":
                    b = 5;
                    break;
                default:
                    b = -1;
                    break;
            }
            if (b == 0) {
                timeExpression = parseTimeExpression(attributeValue, frameAndTickRate);
            } else if (b == 1) {
                timeExpression2 = parseTimeExpression(attributeValue, frameAndTickRate);
            } else if (b == 2) {
                timeExpression3 = parseTimeExpression(attributeValue, frameAndTickRate);
            } else if (b == 3) {
                String[] styleIds = parseStyleIds(attributeValue);
                if (styleIds.length > 0) {
                    strArr = styleIds;
                }
            } else if (b == 4) {
                if (map.containsKey(attributeValue)) {
                    str = attributeValue;
                }
            } else if (b == 5 && attributeValue.startsWith("#")) {
                strSubstring = attributeValue.substring(1);
            }
        }
        if (ttmlNode != null) {
            long j3 = ttmlNode.startTimeUs;
            j = C.TIME_UNSET;
            if (j3 != C.TIME_UNSET) {
                if (timeExpression != C.TIME_UNSET) {
                    timeExpression += ttmlNode.startTimeUs;
                }
                if (timeExpression2 != C.TIME_UNSET) {
                    timeExpression2 += ttmlNode.startTimeUs;
                }
            }
        } else {
            j = C.TIME_UNSET;
        }
        long j4 = timeExpression;
        if (timeExpression2 != j) {
            j2 = timeExpression2;
        } else if (timeExpression3 != j) {
            j2 = j4 + timeExpression3;
        } else if (ttmlNode == null || ttmlNode.endTimeUs == j) {
            j2 = timeExpression2;
        } else {
            j2 = ttmlNode.endTimeUs;
        }
        return TtmlNode.buildNode(xmlPullParser.getName(), j4, j2, styleAttributes, strArr, str, strSubstring);
    }

    private static boolean isSupportedTag(String str) {
        return str.equals(TtmlNode.TAG_TT) || str.equals(TtmlNode.TAG_HEAD) || str.equals(TtmlNode.TAG_BODY) || str.equals(TtmlNode.TAG_DIV) || str.equals(TtmlNode.TAG_P) || str.equals(TtmlNode.TAG_SPAN) || str.equals(TtmlNode.TAG_BR) || str.equals("style") || str.equals(TtmlNode.TAG_STYLING) || str.equals(TtmlNode.TAG_LAYOUT) || str.equals("region") || str.equals(TtmlNode.TAG_METADATA) || str.equals(TtmlNode.TAG_IMAGE) || str.equals("data") || str.equals(TtmlNode.TAG_INFORMATION);
    }

    private static void parseFontSize(String str, TtmlStyle ttmlStyle) throws SubtitleDecoderException {
        Matcher matcher;
        String[] strArrSplit = Util.split(str, "\\s+");
        if (strArrSplit.length == 1) {
            matcher = FONT_SIZE.matcher(str);
        } else if (strArrSplit.length == 2) {
            matcher = FONT_SIZE.matcher(strArrSplit[1]);
            Log.w(TAG, "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            throw new SubtitleDecoderException("Invalid number of entries for fontSize: " + strArrSplit.length + ".");
        }
        if (matcher.matches()) {
            String strGroup = matcher.group(3);
            byte b = -1;
            int iHashCode = strGroup.hashCode();
            if (iHashCode != 37) {
                if (iHashCode != 3240) {
                    if (iHashCode == 3592 && strGroup.equals("px")) {
                        b = 0;
                    }
                } else if (strGroup.equals("em")) {
                    b = 1;
                }
            } else if (strGroup.equals("%")) {
                b = 2;
            }
            if (b == 0) {
                ttmlStyle.setFontSizeUnit(1);
            } else if (b == 1) {
                ttmlStyle.setFontSizeUnit(2);
            } else if (b == 2) {
                ttmlStyle.setFontSizeUnit(3);
            } else {
                throw new SubtitleDecoderException("Invalid unit for fontSize: '" + strGroup + "'.");
            }
            ttmlStyle.setFontSize(Float.valueOf(matcher.group(1)).floatValue());
            return;
        }
        throw new SubtitleDecoderException("Invalid expression for fontSize: '" + str + "'.");
    }

    /* JADX WARN: Code duplicated, block: B:50:0x00e4  */
    private static long parseTimeExpression(String str, FrameAndTickRate frameAndTickRate) throws SubtitleDecoderException {
        double d;
        double d2;
        Matcher matcher = CLOCK_TIME.matcher(str);
        byte b = 2;
        if (matcher.matches()) {
            double d3 = (Long.parseLong(matcher.group(1)) * 3600) + (Long.parseLong(matcher.group(2)) * 60) + Long.parseLong(matcher.group(3));
            String strGroup = matcher.group(4);
            double d4 = d3 + (strGroup != null ? Double.parseDouble(strGroup) : 0.0d);
            String strGroup2 = matcher.group(5);
            double d5 = d4 + (strGroup2 != null ? Long.parseLong(strGroup2) / frameAndTickRate.effectiveFrameRate : 0.0d);
            String strGroup3 = matcher.group(6);
            return (long) ((d5 + (strGroup3 != null ? (Long.parseLong(strGroup3) / ((double) frameAndTickRate.subFrameRate)) / ((double) frameAndTickRate.effectiveFrameRate) : 0.0d)) * 1000000.0d);
        }
        Matcher matcher2 = OFFSET_TIME.matcher(str);
        if (matcher2.matches()) {
            double d6 = Double.parseDouble(matcher2.group(1));
            String strGroup4 = matcher2.group(2);
            int iHashCode = strGroup4.hashCode();
            if (iHashCode != 102) {
                if (iHashCode != 104) {
                    if (iHashCode != 109) {
                        if (iHashCode != 3494) {
                            if (iHashCode != 115) {
                                if (iHashCode == 116 && strGroup4.equals("t")) {
                                    b = 5;
                                } else {
                                    b = -1;
                                }
                            } else if (!strGroup4.equals("s")) {
                                b = -1;
                            }
                        } else if (strGroup4.equals("ms")) {
                            b = 3;
                        } else {
                            b = -1;
                        }
                    } else if (strGroup4.equals("m")) {
                        b = 1;
                    } else {
                        b = -1;
                    }
                } else if (strGroup4.equals("h")) {
                    b = 0;
                } else {
                    b = -1;
                }
            } else if (strGroup4.equals("f")) {
                b = 4;
            } else {
                b = -1;
            }
            if (b != 0) {
                if (b != 1) {
                    if (b == 3) {
                        d2 = 1000.0d;
                    } else if (b == 4) {
                        d2 = frameAndTickRate.effectiveFrameRate;
                    } else if (b == 5) {
                        d2 = frameAndTickRate.tickRate;
                    }
                    d6 /= d2;
                } else {
                    d = 60.0d;
                }
                return (long) (d6 * 1000000.0d);
            }
            d = 3600.0d;
            d6 *= d;
            return (long) (d6 * 1000000.0d);
        }
        throw new SubtitleDecoderException("Malformed time expression: " + str);
    }

    private static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        FrameAndTickRate(float f, int i, int i2) {
            this.effectiveFrameRate = f;
            this.subFrameRate = i;
            this.tickRate = i2;
        }
    }

    private static final class CellResolution {
        final int columns;
        final int rows;

        CellResolution(int i, int i2) {
            this.columns = i;
            this.rows = i2;
        }
    }

    private static final class TtsExtent {
        final int height;
        final int width;

        TtsExtent(int i, int i2) {
            this.width = i;
            this.height = i2;
        }
    }
}
