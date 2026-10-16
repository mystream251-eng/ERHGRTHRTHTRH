package com.viaccessorca.voplayer;

import android.content.Context;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/* JADX INFO: loaded from: classes2.dex */
class c {

    private class b extends DefaultHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private StringBuffer f124a;
        int b;
        int c;
        int d;
        int e;
        boolean f;
        private List<Integer> g;
        private List<Integer> h;
        private List<String> i;
        private List<String> j;
        private boolean k;
        C0187c l;

        private b() {
            this.b = 0;
            this.c = 0;
            this.d = 0;
            this.e = 0;
            this.f = false;
            this.g = new ArrayList();
            this.h = new ArrayList();
            this.i = new ArrayList();
            this.j = new ArrayList();
            this.k = false;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void characters(char[] cArr, int i, int i2) {
            this.f124a.append(new String(cArr, i, i2));
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endDocument() throws SAXException {
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) throws SAXException {
            List list;
            Object objTrim;
            int i;
            if (!str2.equals("limit")) {
                if (str2.equals("limits_per_score")) {
                    int size = this.g.size();
                    int size2 = this.h.size();
                    this.f = false;
                    if (size == size2) {
                        for (int i2 = 0; i2 < size; i2++) {
                            this.l.addScoreLimit(this.h.get(i2).intValue(), this.g.get(i2).intValue());
                        }
                    } else {
                        this.k = true;
                    }
                } else if (str2.equals("limit_per_ids")) {
                    this.f = false;
                    for (int i3 = 0; i3 < this.i.size(); i3++) {
                        String str4 = this.i.get(i3);
                        if (this.j.size() > 0) {
                            for (int i4 = 0; i4 < this.j.size(); i4++) {
                                String str5 = this.j.get(i4);
                                int i5 = this.b;
                                if (i5 >= 0) {
                                    this.l.addSpecificDeviceLimit(str4, str5, i5);
                                }
                                int i6 = this.c;
                                if (i6 >= 0) {
                                    this.l.addSpecificDeviceSwLimit(str4, str5, i6);
                                }
                                int i7 = this.d;
                                if (i7 >= 0) {
                                    this.l.addSpecificDeviceHwLimit(str4, str5, i7);
                                }
                            }
                        } else {
                            int i8 = this.b;
                            if (i8 >= 0) {
                                this.l.addSpecificDeviceLimit(str4, null, i8);
                            }
                            int i9 = this.c;
                            if (i9 >= 0) {
                                this.l.addSpecificDeviceSwLimit(str4, null, i9);
                            }
                            int i10 = this.d;
                            if (i10 >= 0) {
                                this.l.addSpecificDeviceHwLimit(str4, null, i10);
                            }
                        }
                    }
                } else {
                    try {
                        if (str2.equals("score")) {
                            this.e = Integer.parseInt(this.f124a.toString().trim());
                        } else if (str2.equals("fallback_bitrate")) {
                            this.l.setFallbackLimit(Integer.parseInt(this.f124a.toString().trim()));
                        } else if (str2.equals("bitrate")) {
                            this.b = Integer.parseInt(this.f124a.toString().trim());
                        } else if (str2.equals("sw_bitrate")) {
                            this.c = Integer.parseInt(this.f124a.toString().trim());
                        } else if (str2.equals("hw_bitrate")) {
                            String strTrim = this.f124a.toString().trim();
                            this.d = Integer.parseInt(strTrim);
                            if (!this.f) {
                                this.l.setDefaultHwLimit(Integer.parseInt(strTrim));
                            }
                        } else {
                            if (str2.equals("id")) {
                                list = this.i;
                            } else if (str2.equals("level")) {
                                list = this.j;
                            }
                            objTrim = this.f124a.toString().trim();
                            list.add(objTrim);
                        }
                    } catch (NumberFormatException unused) {
                        this.k = true;
                    }
                }
            } else if (this.b >= 0 && (i = this.e) >= 0) {
                this.h.add(Integer.valueOf(i));
                list = this.g;
                objTrim = Integer.valueOf(this.b);
                list.add(objTrim);
            }
            this.f124a = new StringBuffer();
        }

        public C0187c getStreams() {
            if (this.k) {
                return null;
            }
            return this.l;
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startDocument() throws SAXException {
            this.k = false;
            this.f124a = new StringBuffer();
            this.l = new C0187c(c.this);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            List<String> list;
            if (str2.equals("limit")) {
                this.b = -1;
                this.c = -1;
                this.d = -1;
                this.e = -1;
                return;
            }
            if (!str2.equals("limits_per_score") && !str2.equals("limit_per_ids")) {
                if (str2.equals("android_versions")) {
                    list = this.j;
                } else if (!str2.equals("apply_to_ids")) {
                    return;
                } else {
                    list = this.i;
                }
                list.clear();
                return;
            }
            this.b = -1;
            this.c = -1;
            this.d = -1;
            this.e = -1;
            this.g.clear();
            this.h.clear();
            this.i.clear();
            this.j.clear();
            this.f = true;
        }
    }

    /* JADX INFO: renamed from: com.viaccessorca.voplayer.c$c, reason: collision with other inner class name */
    protected class C0187c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Map<Integer, Integer> f125a;
        private Map<String, Integer> b;
        private Map<String, Integer> c;
        private Map<String, Integer> d;
        private int e = -1;
        private int f = -1;

        protected C0187c(c cVar) {
            this.f125a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.f125a = new HashMap();
            this.b = new HashMap();
            this.c = new HashMap();
            this.d = new HashMap();
        }

        protected int a() {
            return this.f;
        }

        public void addScoreLimit(int i, int i2) {
            this.f125a.put(Integer.valueOf(i), Integer.valueOf(i2));
        }

        public boolean addSpecificDeviceHwLimit(String str, String str2, int i) {
            String str3;
            String str4 = (str != null) & (str2 != null) ? ";" : null;
            if (str4 != null) {
                str3 = new String(str + str4 + str2);
            } else {
                if (str == null) {
                    return false;
                }
                str3 = new String(str);
            }
            if (this.d.containsKey(str3)) {
                return false;
            }
            this.d.put(str3, Integer.valueOf(i));
            return true;
        }

        public boolean addSpecificDeviceLimit(String str, String str2, int i) {
            String str3;
            String str4 = (str != null) & (str2 != null) ? ";" : null;
            if (str4 != null) {
                str3 = new String(str + str4 + str2);
            } else {
                if (str == null) {
                    return false;
                }
                str3 = new String(str);
            }
            if (this.b.containsKey(str3)) {
                return false;
            }
            this.b.put(str3, Integer.valueOf(i));
            return true;
        }

        public boolean addSpecificDeviceSwLimit(String str, String str2, int i) {
            String str3;
            String str4 = (str != null) & (str2 != null) ? ";" : null;
            if (str4 != null) {
                str3 = new String(str + str4 + str2);
            } else {
                if (str == null) {
                    return false;
                }
                str3 = new String(str);
            }
            if (this.c.containsKey(str3)) {
                return false;
            }
            this.c.put(str3, Integer.valueOf(i));
            return true;
        }

        protected int b() {
            return this.e;
        }

        protected String c() {
            return ";";
        }

        protected Map<Integer, Integer> d() {
            return this.f125a;
        }

        protected Map<String, Integer> e() {
            return this.b;
        }

        protected Map<String, Integer> f() {
            return this.d;
        }

        protected Map<String, Integer> g() {
            return this.c;
        }

        public void setDefaultHwLimit(int i) {
            this.e = i;
        }

        public void setFallbackLimit(int i) {
            this.f = i;
        }
    }

    c() {
    }

    private C0187c a(InputSource inputSource) {
        try {
            XMLReader xMLReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
            b bVar = new b();
            xMLReader.setContentHandler(bVar);
            xMLReader.parse(inputSource);
            return bVar.getStreams();
        } catch (IOException | ParserConfigurationException | SAXException unused) {
            return null;
        }
    }

    protected C0187c a(Context context) {
        try {
            return a(new InputSource(context.getAssets().open("deviceAdaptationDb.xml")));
        } catch (IOException unused) {
            return null;
        }
    }
}
