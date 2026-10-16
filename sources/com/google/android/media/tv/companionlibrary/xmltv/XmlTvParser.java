package com.google.android.media.tv.companionlibrary.xmltv;

import android.graphics.Color;
import android.media.tv.TvContentRating;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import com.google.android.media.tv.companionlibrary.model.Advertisement;
import com.google.android.media.tv.companionlibrary.model.Channel;
import com.google.android.media.tv.companionlibrary.model.InternalProviderData;
import com.google.android.media.tv.companionlibrary.model.Program;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class XmlTvParser {
    private static final String ANDROID_TV_RATING = "com.android.tv";
    private static final String ATTR_AD_START = "start";
    private static final String ATTR_AD_STOP = "stop";
    private static final String ATTR_AD_TYPE = "type";
    private static final String ATTR_APP_LINK_COLOR = "color";
    private static final String ATTR_APP_LINK_INTENT_URI = "intent-uri";
    private static final String ATTR_APP_LINK_POSTER_URI = "poster-uri";
    private static final String ATTR_APP_LINK_TEXT = "text";
    private static final String ATTR_CHANNEL = "channel";
    private static final String ATTR_ID = "id";
    private static final String ATTR_REPEAT_PROGRAMS = "repeat-programs";
    private static final String ATTR_SRC = "src";
    private static final String ATTR_START = "start";
    private static final String ATTR_STOP = "stop";
    private static final String ATTR_SYSTEM = "system";
    private static final String ATTR_VIDEO_SRC = "video-src";
    private static final String ATTR_VIDEO_TYPE = "video-type";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss Z", Locale.US);
    private static final String TAG = "XmlTvParser";
    private static final String TAG_AD = "advertisement";
    private static final String TAG_APP_LINK = "app-link";
    private static final String TAG_CATEGORY = "category";
    private static final String TAG_CHANNEL = "channel";
    private static final String TAG_DESC = "desc";
    private static final String TAG_DISPLAY_NAME = "display-name";
    private static final String TAG_DISPLAY_NUMBER = "display-number";
    private static final String TAG_ICON = "icon";
    private static final String TAG_PROGRAM = "programme";
    private static final String TAG_RATING = "rating";
    private static final String TAG_REQUEST_URL = "request-url";
    private static final String TAG_TITLE = "title";
    private static final String TAG_TV = "tv";
    private static final String TAG_VALUE = "value";
    private static final String VALUE_ADVERTISEMENT_TYPE_VAST = "VAST";
    private static final String VALUE_VIDEO_TYPE_HLS = "HLS";
    private static final String VALUE_VIDEO_TYPE_HTTP_PROGRESSIVE = "HTTP_PROGRESSIVE";
    private static final String VALUE_VIDEO_TYPE_MPEG_DASH = "MPEG_DASH";

    private XmlTvParser() {
    }

    private static TvContentRating xmlTvRatingToTvContentRating(XmlTvRating xmlTvRating) {
        if (ANDROID_TV_RATING.equals(xmlTvRating.system)) {
            return TvContentRating.unflattenFromString(xmlTvRating.value);
        }
        return null;
    }

    public static TvListing parse(InputStream inputStream) throws XmlTvParseException {
        return parse(inputStream, Xml.newPullParser());
    }

    private static TvListing parse(InputStream inputStream, XmlPullParser xmlPullParser) throws XmlTvParseException {
        try {
            xmlPullParser.setInput(inputStream, null);
            if (xmlPullParser.next() != 2 || !TAG_TV.equals(xmlPullParser.getName())) {
                throw new XmlTvParseException("Input stream does not contain an XMLTV description");
            }
            return parseTvListings(xmlPullParser);
        } catch (IOException | ParseException | XmlPullParserException e) {
            Log.w(TAG, e.getMessage());
            return null;
        }
    }

    private static TvListing parseTvListings(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        while (xmlPullParser.next() != 1) {
            if (xmlPullParser.getEventType() == 2 && "channel".equalsIgnoreCase(xmlPullParser.getName())) {
                arrayList.add(parseChannel(xmlPullParser));
            }
            if (xmlPullParser.getEventType() == 2 && TAG_PROGRAM.equalsIgnoreCase(xmlPullParser.getName())) {
                arrayList2.add(parseProgram(xmlPullParser));
            }
        }
        return new TvListing(arrayList, arrayList2);
    }

    private static Channel parseChannel(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        String strNextText = null;
        boolean zEqualsIgnoreCase = false;
        String str = null;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if ("id".equalsIgnoreCase(attributeName)) {
                str = attributeValue;
            } else if (ATTR_REPEAT_PROGRAMS.equalsIgnoreCase(attributeName)) {
                zEqualsIgnoreCase = "TRUE".equalsIgnoreCase(attributeValue);
            }
        }
        String strNextText2 = null;
        XmlTvIcon icon = null;
        XmlTvAppLink appLink = null;
        Advertisement ad = null;
        while (xmlPullParser.next() != 1) {
            if (xmlPullParser.getEventType() == 2) {
                if (TAG_DISPLAY_NAME.equalsIgnoreCase(xmlPullParser.getName()) && strNextText == null) {
                    strNextText = xmlPullParser.nextText();
                } else if (TAG_DISPLAY_NUMBER.equalsIgnoreCase(xmlPullParser.getName()) && strNextText2 == null) {
                    strNextText2 = xmlPullParser.nextText();
                } else if (TAG_ICON.equalsIgnoreCase(xmlPullParser.getName()) && icon == null) {
                    icon = parseIcon(xmlPullParser);
                } else if (TAG_APP_LINK.equalsIgnoreCase(xmlPullParser.getName()) && appLink == null) {
                    appLink = parseAppLink(xmlPullParser);
                } else if (TAG_AD.equalsIgnoreCase(xmlPullParser.getName()) && ad == null) {
                    ad = parseAd(xmlPullParser, "channel");
                }
            } else if ("channel".equalsIgnoreCase(xmlPullParser.getName()) && xmlPullParser.getEventType() == 3) {
                break;
            }
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(strNextText)) {
            throw new IllegalArgumentException("id and display-name can not be null.");
        }
        InternalProviderData internalProviderData = new InternalProviderData();
        internalProviderData.setRepeatable(zEqualsIgnoreCase);
        Channel.Builder serviceId = new Channel.Builder().setDisplayName(strNextText).setDisplayNumber(strNextText2).setOriginalNetworkId(str.hashCode()).setInternalProviderData(internalProviderData).setTransportStreamId(0).setServiceId(0);
        if (icon != null) {
            serviceId.setChannelLogo(icon.src);
        }
        if (appLink != null) {
            serviceId.setAppLinkColor(appLink.color.intValue()).setAppLinkIconUri(appLink.icon.src).setAppLinkIntentUri(appLink.intentUri).setAppLinkPosterArtUri(appLink.posterUri).setAppLinkText(appLink.text);
        }
        if (ad != null) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(ad);
            internalProviderData.setAds(arrayList);
            serviceId.setInternalProviderData(internalProviderData);
        }
        return serviceId.build();
    }

    private static Program parseProgram(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException, ParseException {
        int i;
        String strNextText = null;
        int i2 = 0;
        String str = null;
        Long lValueOf = null;
        Long lValueOf2 = null;
        String str2 = null;
        int i3 = 3;
        while (true) {
            i = 2;
            if (i2 >= xmlPullParser.getAttributeCount()) {
                break;
            }
            String attributeName = xmlPullParser.getAttributeName(i2);
            String attributeValue = xmlPullParser.getAttributeValue(i2);
            if ("channel".equalsIgnoreCase(attributeName)) {
                str = attributeValue;
            } else if ("start".equalsIgnoreCase(attributeName)) {
                lValueOf = Long.valueOf(DATE_FORMAT.parse(attributeValue).getTime());
            } else if ("stop".equalsIgnoreCase(attributeName)) {
                lValueOf2 = Long.valueOf(DATE_FORMAT.parse(attributeValue).getTime());
            } else if (ATTR_VIDEO_SRC.equalsIgnoreCase(attributeName)) {
                str2 = attributeValue;
            } else if (ATTR_VIDEO_TYPE.equalsIgnoreCase(attributeName)) {
                if (VALUE_VIDEO_TYPE_HTTP_PROGRESSIVE.equals(attributeValue)) {
                    i3 = 3;
                } else if (VALUE_VIDEO_TYPE_HLS.equals(attributeValue)) {
                    i3 = 2;
                } else if (VALUE_VIDEO_TYPE_MPEG_DASH.equals(attributeValue)) {
                    i3 = 0;
                }
            }
            i2++;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        String strNextText2 = null;
        XmlTvIcon icon = null;
        while (xmlPullParser.next() != 1) {
            String name = xmlPullParser.getName();
            if (xmlPullParser.getEventType() == i) {
                if ("title".equalsIgnoreCase(xmlPullParser.getName())) {
                    strNextText = xmlPullParser.nextText();
                } else if (TAG_DESC.equalsIgnoreCase(name)) {
                    strNextText2 = xmlPullParser.nextText();
                } else if (TAG_ICON.equalsIgnoreCase(name)) {
                    icon = parseIcon(xmlPullParser);
                } else if (TAG_CATEGORY.equalsIgnoreCase(name)) {
                    arrayList.add(xmlPullParser.nextText());
                } else if (TAG_RATING.equalsIgnoreCase(name)) {
                    TvContentRating tvContentRatingXmlTvRatingToTvContentRating = xmlTvRatingToTvContentRating(parseRating(xmlPullParser));
                    if (tvContentRatingXmlTvRatingToTvContentRating != null) {
                        arrayList2.add(tvContentRatingXmlTvRatingToTvContentRating);
                    }
                } else if (TAG_AD.equalsIgnoreCase(name)) {
                    arrayList3.add(parseAd(xmlPullParser, TAG_PROGRAM));
                }
            } else {
                if (TAG_PROGRAM.equalsIgnoreCase(name)) {
                    if (xmlPullParser.getEventType() == 3) {
                        break;
                    }
                }
                i = 2;
            }
            i = 2;
        }
        if (TextUtils.isEmpty(str) || lValueOf == null || lValueOf2 == null) {
            throw new IllegalArgumentException("channel, start, and end can not be null.");
        }
        InternalProviderData internalProviderData = new InternalProviderData();
        internalProviderData.setVideoType(i3);
        internalProviderData.setVideoUrl(str2);
        internalProviderData.setAds(arrayList3);
        return new Program.Builder().setChannelId(str.hashCode()).setTitle(strNextText).setDescription(strNextText2).setPosterArtUri(icon.src).setCanonicalGenres((String[]) arrayList.toArray(new String[arrayList.size()])).setStartTimeUtcMillis(lValueOf.longValue()).setEndTimeUtcMillis(lValueOf2.longValue()).setContentRatings((TvContentRating[]) arrayList2.toArray(new TvContentRating[arrayList2.size()])).setInternalProviderData(internalProviderData).build();
    }

    private static XmlTvIcon parseIcon(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String str = null;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (ATTR_SRC.equalsIgnoreCase(attributeName)) {
                str = attributeValue;
            }
        }
        while (xmlPullParser.next() != 1 && (!TAG_ICON.equalsIgnoreCase(xmlPullParser.getName()) || xmlPullParser.getEventType() != 3)) {
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Icon src cannot be null.");
        }
        return new XmlTvIcon(str);
    }

    private static XmlTvAppLink parseAppLink(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String str = null;
        Integer numValueOf = null;
        String str2 = null;
        String str3 = null;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if ("text".equalsIgnoreCase(attributeName)) {
                str = attributeValue;
            } else if ("color".equalsIgnoreCase(attributeName)) {
                numValueOf = Integer.valueOf(Color.parseColor(attributeValue));
            } else if (ATTR_APP_LINK_POSTER_URI.equalsIgnoreCase(attributeName)) {
                str2 = attributeValue;
            } else if (ATTR_APP_LINK_INTENT_URI.equalsIgnoreCase(attributeName)) {
                str3 = attributeValue;
            }
        }
        XmlTvIcon icon = null;
        while (xmlPullParser.next() != 1) {
            if (xmlPullParser.getEventType() == 2 && TAG_ICON.equalsIgnoreCase(xmlPullParser.getName()) && icon == null) {
                icon = parseIcon(xmlPullParser);
            } else if (TAG_APP_LINK.equalsIgnoreCase(xmlPullParser.getName()) && xmlPullParser.getEventType() == 3) {
                break;
            }
        }
        return new XmlTvAppLink(str, numValueOf, str2, str3, icon);
    }

    private static XmlTvRating parseRating(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String strNextText = null;
        String str = null;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if (ATTR_SYSTEM.equalsIgnoreCase(attributeName)) {
                str = attributeValue;
            }
        }
        while (xmlPullParser.next() != 1) {
            if (xmlPullParser.getEventType() == 2) {
                if (TAG_VALUE.equalsIgnoreCase(xmlPullParser.getName())) {
                    strNextText = xmlPullParser.nextText();
                }
            } else if (TAG_RATING.equalsIgnoreCase(xmlPullParser.getName()) && xmlPullParser.getEventType() == 3) {
                break;
            }
        }
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(strNextText)) {
            throw new IllegalArgumentException("system and value cannot be null.");
        }
        return new XmlTvRating(str, strNextText);
    }

    private static Advertisement parseAd(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException, ParseException {
        String strNextText = null;
        Long lValueOf = null;
        Long lValueOf2 = null;
        for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
            String attributeName = xmlPullParser.getAttributeName(i);
            String attributeValue = xmlPullParser.getAttributeValue(i);
            if ("start".equalsIgnoreCase(attributeName)) {
                lValueOf = Long.valueOf(DATE_FORMAT.parse(attributeValue).getTime());
            } else if ("stop".equalsIgnoreCase(attributeName)) {
                lValueOf2 = Long.valueOf(DATE_FORMAT.parse(attributeValue).getTime());
            } else if ("type".equalsIgnoreCase(attributeName)) {
                VALUE_ADVERTISEMENT_TYPE_VAST.equalsIgnoreCase(attributeName);
            }
        }
        while (xmlPullParser.next() != 1) {
            if (xmlPullParser.getEventType() == 2) {
                if (TAG_REQUEST_URL.equalsIgnoreCase(xmlPullParser.getName())) {
                    strNextText = xmlPullParser.nextText();
                }
            } else if (TAG_AD.equalsIgnoreCase(xmlPullParser.getName()) && xmlPullParser.getEventType() == 3) {
                break;
            }
        }
        Advertisement.Builder builder = new Advertisement.Builder();
        if (str.equals(TAG_PROGRAM)) {
            if (lValueOf == null || lValueOf2 == null) {
                throw new IllegalArgumentException("start, stop time of program ads cannot be null");
            }
            builder.setStartTimeUtcMillis(lValueOf.longValue());
            builder.setStopTimeUtcMillis(lValueOf2.longValue());
        }
        return builder.setType(0).setRequestUrl(strNextText).build();
    }

    public static class TvListing {
        private List<Channel> mChannels;
        private HashMap<Long, List<Program>> mProgramMap;
        private List<Program> mPrograms;

        private TvListing(List<Channel> list, List<Program> list2) {
            this.mChannels = list;
            this.mPrograms = new ArrayList(list2);
            this.mProgramMap = new HashMap<>();
            for (Channel channel : list) {
                ArrayList arrayList = new ArrayList();
                Iterator<Program> it = list2.iterator();
                while (it.hasNext()) {
                    Program next = it.next();
                    if (next.getChannelId() == channel.getOriginalNetworkId()) {
                        arrayList.add(new Program.Builder(next).setChannelId(channel.getId()).build());
                        it.remove();
                    }
                }
                this.mProgramMap.put(Long.valueOf(channel.getOriginalNetworkId()), arrayList);
            }
        }

        public List<Channel> getChannels() {
            return this.mChannels;
        }

        public List<Program> getAllPrograms() {
            return this.mPrograms;
        }

        public List<Program> getPrograms(Channel channel) {
            return this.mProgramMap.get(Long.valueOf(channel.getOriginalNetworkId()));
        }
    }

    private static class XmlTvIcon {
        public final String src;

        private XmlTvIcon(String str) {
            this.src = str;
        }
    }

    private static class XmlTvRating {
        public final String system;
        public final String value;

        public XmlTvRating(String str, String str2) {
            this.system = str;
            this.value = str2;
        }
    }

    private static class XmlTvAppLink {
        public final Integer color;
        public final XmlTvIcon icon;
        public final String intentUri;
        public final String posterUri;
        public final String text;

        public XmlTvAppLink(String str, Integer num, String str2, String str3, XmlTvIcon xmlTvIcon) {
            this.text = str;
            this.color = num;
            this.posterUri = str2;
            this.intentUri = str3;
            this.icon = xmlTvIcon;
        }
    }

    public static class XmlTvParseException extends Exception {
        public XmlTvParseException(String str) {
            super(str);
        }
    }
}
