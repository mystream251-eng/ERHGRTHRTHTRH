package com.google.android.media.tv.companionlibrary.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class InternalProviderData {
    private static final boolean DEBUG = true;
    private static final String KEY_ADVERTISEMENTS = "advertisements";
    private static final String KEY_ADVERTISEMENT_REQUEST_URL = "requestUrl";
    private static final String KEY_ADVERTISEMENT_START = "start";
    private static final String KEY_ADVERTISEMENT_STOP = "stop";
    private static final String KEY_ADVERTISEMENT_TYPE = "type";
    private static final String KEY_CUSTOM_DATA = "custom";
    private static final String KEY_RECORDING_START_TIME = "recordingStartTime";
    private static final String KEY_REPEATABLE = "repeatable";
    private static final String KEY_VIDEO_TYPE = "type";
    private static final String KEY_VIDEO_URL = "url";
    private static final String TAG = "InternalProviderData";
    private JSONObject mJsonObject;

    public InternalProviderData() {
        this.mJsonObject = new JSONObject();
    }

    public InternalProviderData(String str) throws ParseException {
        try {
            this.mJsonObject = new JSONObject(str);
        } catch (JSONException e) {
            throw new ParseException(e.getMessage());
        }
    }

    public InternalProviderData(byte[] bArr) throws ParseException {
        try {
            this.mJsonObject = new JSONObject(new String(bArr));
        } catch (JSONException e) {
            throw new ParseException(e.getMessage());
        }
    }

    private int jsonHash(JSONObject jSONObject) {
        Iterator<String> itKeys = jSONObject.keys();
        int iJsonHash = 0;
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                if (jSONObject.get(next) instanceof JSONObject) {
                    iJsonHash += jsonHash(jSONObject.getJSONObject(next));
                } else {
                    iJsonHash += next.hashCode() + jSONObject.get(next).hashCode();
                }
            } catch (JSONException unused) {
            }
        }
        return iJsonHash;
    }

    public int hashCode() {
        return jsonHash(this.mJsonObject);
    }

    private boolean jsonEquals(JSONObject jSONObject, JSONObject jSONObject2) {
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                if (jSONObject.get(next) instanceof JSONObject) {
                    return jsonEquals(jSONObject.getJSONObject(next), jSONObject2.getJSONObject(next));
                }
                if (!jSONObject.get(next).equals(jSONObject2.get(next))) {
                }
            } catch (JSONException unused) {
            }
            return false;
        }
        return jSONObject.length() == jSONObject2.length();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof InternalProviderData)) {
            return false;
        }
        return jsonEquals(this.mJsonObject, ((InternalProviderData) obj).mJsonObject);
    }

    public String toString() {
        return this.mJsonObject.toString();
    }

    public int getVideoType() {
        if (!this.mJsonObject.has("type")) {
            return -1;
        }
        try {
            return this.mJsonObject.getInt("type");
        } catch (JSONException unused) {
            return -1;
        }
    }

    public void setVideoType(int i) {
        try {
            this.mJsonObject.put("type", i);
        } catch (JSONException unused) {
        }
    }

    public String getVideoUrl() {
        if (!this.mJsonObject.has("url")) {
            return null;
        }
        try {
            return this.mJsonObject.getString("url");
        } catch (JSONException unused) {
            return null;
        }
    }

    public List<Advertisement> getAds() {
        ArrayList arrayList = new ArrayList();
        try {
            if (this.mJsonObject.has(KEY_ADVERTISEMENTS)) {
                JSONArray jSONArray = new JSONArray(this.mJsonObject.get(KEY_ADVERTISEMENTS).toString());
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    long j = jSONObject.getLong("start");
                    long j2 = jSONObject.getLong(KEY_ADVERTISEMENT_STOP);
                    int i2 = jSONObject.getInt("type");
                    arrayList.add(new Advertisement.Builder().setStartTimeUtcMillis(j).setStopTimeUtcMillis(j2).setType(i2).setRequestUrl(jSONObject.getString(KEY_ADVERTISEMENT_REQUEST_URL)).build());
                }
            }
        } catch (JSONException unused) {
        }
        return arrayList;
    }

    public long getRecordedProgramStartTime() {
        try {
            return this.mJsonObject.getLong(KEY_RECORDING_START_TIME);
        } catch (JSONException unused) {
            return 0L;
        }
    }

    public void setVideoUrl(String str) {
        try {
            this.mJsonObject.put("url", str);
        } catch (JSONException unused) {
        }
    }

    public boolean isRepeatable() {
        if (!this.mJsonObject.has(KEY_REPEATABLE)) {
            return false;
        }
        try {
            return this.mJsonObject.getBoolean(KEY_REPEATABLE);
        } catch (JSONException unused) {
            return false;
        }
    }

    public void setRepeatable(boolean z) {
        try {
            this.mJsonObject.put(KEY_REPEATABLE, z);
        } catch (JSONException unused) {
        }
    }

    public void setAds(List<Advertisement> list) {
        if (list != null) {
            try {
                if (list.isEmpty()) {
                    return;
                }
                JSONArray jSONArray = new JSONArray();
                for (Advertisement advertisement : list) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("start", advertisement.getStartTimeUtcMillis());
                    jSONObject.put(KEY_ADVERTISEMENT_STOP, advertisement.getStopTimeUtcMillis());
                    jSONObject.put("type", advertisement.getType());
                    jSONObject.put(KEY_ADVERTISEMENT_REQUEST_URL, advertisement.getRequestUrl());
                    jSONArray.put(jSONObject);
                }
                this.mJsonObject.put(KEY_ADVERTISEMENTS, jSONArray);
            } catch (JSONException unused) {
            }
        }
    }

    public void setRecordingStartTime(long j) {
        try {
            this.mJsonObject.put(KEY_RECORDING_START_TIME, j);
        } catch (JSONException unused) {
        }
    }

    public InternalProviderData put(String str, Object obj) throws ParseException {
        try {
            if (!this.mJsonObject.has("custom")) {
                this.mJsonObject.put("custom", new JSONObject());
            }
            this.mJsonObject.getJSONObject("custom").put(str, String.valueOf(obj));
            return this;
        } catch (JSONException e) {
            throw new ParseException(e.getMessage());
        }
    }

    public Object get(String str) throws ParseException {
        if (!this.mJsonObject.has("custom")) {
            return null;
        }
        try {
            return this.mJsonObject.getJSONObject("custom").opt(str);
        } catch (JSONException e) {
            throw new ParseException(e.getMessage());
        }
    }

    public boolean has(String str) throws ParseException {
        if (!this.mJsonObject.has("custom")) {
            return false;
        }
        try {
            return this.mJsonObject.getJSONObject("custom").has(str);
        } catch (JSONException e) {
            throw new ParseException(e.getMessage());
        }
    }

    public class ParseException extends JSONException {
        public ParseException(String str) {
            super(str);
        }
    }
}
