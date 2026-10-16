package com.dotscreen.tv.common.bokit;

import android.os.Parcel;
import android.os.Parcelable;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.tv.common.utils.notifications.NotificationsContract;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: MegacableUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 '2\u00020\u0001:\u0001'B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004BA\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0006\u0010\u001d\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\u0006\u0010!\u001a\u00020\u000eJ\b\u0010\"\u001a\u00020\u0006H\u0016J\u001a\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u00032\u0006\u0010&\u001a\u00020\u001cH\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006("}, d2 = {"Lcom/dotscreen/tv/common/bokit/Profile;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "externalId", "", "id", "name", TtmlNode.TAG_IMAGE, "properties", "", "", "hasPinCode", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Z)V", "getExternalId", "()Ljava/lang/String;", "getHasPinCode", "()Z", "setHasPinCode", "(Z)V", "getId", "getImage", "getName", "getProperties", "()Ljava/util/Map;", "describeContents", "", "getParentalLevel", "Lcom/dotscreen/bokit/model/ParentalRating;", "boService", "Lcom/dotscreen/bokit/services/BOService;", "isMainProfile", "toString", "writeToParcel", "", "dest", NotificationsContract.COLUMN_FLAGS, "CREATOR", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class Profile implements Parcelable {

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String externalId;
    private boolean hasPinCode;
    private final String id;
    private final String image;
    private final String name;
    private final Map<String, Object> properties;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Profile(String externalId, String id, String name, String image, Map<String, ? extends Object> properties, boolean z) {
        Intrinsics.checkParameterIsNotNull(externalId, "externalId");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(image, "image");
        Intrinsics.checkParameterIsNotNull(properties, "properties");
        this.externalId = externalId;
        this.id = id;
        this.name = name;
        this.image = image;
        this.properties = properties;
        this.hasPinCode = z;
    }

    public final String getExternalId() {
        return this.externalId;
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getImage() {
        return this.image;
    }

    public final Map<String, Object> getProperties() {
        return this.properties;
    }

    public final boolean getHasPinCode() {
        return this.hasPinCode;
    }

    public final void setHasPinCode(boolean z) {
        this.hasPinCode = z;
    }

    /* JADX INFO: renamed from: com.dotscreen.tv.common.bokit.Profile$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: MegacableUtils.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001c\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001d\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010H\u0016¢\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0017¨\u0006\u0018"}, d2 = {"Lcom/dotscreen/tv/common/bokit/Profile$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/dotscreen/tv/common/bokit/Profile;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "getProfilesProperties", "", "", "", "jsonProperties", "Lorg/json/JSONArray;", "hasPinCode", "", "value", "", "newArray", "", "size", "(I)[Lcom/dotscreen/tv/common/bokit/Profile;", "parseProfile", "profileJson", "Lorg/json/JSONObject;", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
    public static final class Companion implements Parcelable.Creator<Profile> {
        private final boolean hasPinCode(int value) {
            return value == 1;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final Map<String, Object> getProfilesProperties(JSONArray jsonProperties) throws JSONException {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int length = jsonProperties.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jsonProperties.getJSONObject(i);
                String string = jSONObject.getString("name");
                Intrinsics.checkExpressionValueIsNotNull(string, "property.getString(\"name\")");
                String string2 = jSONObject.getString("value");
                Intrinsics.checkExpressionValueIsNotNull(string2, "property.getString(\"value\")");
                linkedHashMap.put(string, string2);
            }
            return linkedHashMap;
        }

        public final Profile parseProfile(JSONObject profileJson) throws JSONException {
            Intrinsics.checkParameterIsNotNull(profileJson, "profileJson");
            String string = profileJson.getString("externalId");
            Intrinsics.checkExpressionValueIsNotNull(string, "profileJson.getString(\"externalId\")");
            String string2 = profileJson.getString("id");
            Intrinsics.checkExpressionValueIsNotNull(string2, "profileJson.getString(\"id\")");
            String string3 = profileJson.getString("name");
            Intrinsics.checkExpressionValueIsNotNull(string3, "profileJson.getString(\"name\")");
            String string4 = profileJson.getString(TtmlNode.TAG_IMAGE);
            Intrinsics.checkExpressionValueIsNotNull(string4, "profileJson.getString(\"image\")");
            Companion companion = this;
            JSONArray jSONArray = profileJson.getJSONArray("profileProperties");
            Intrinsics.checkExpressionValueIsNotNull(jSONArray, "profileJson.getJSONArray(\"profileProperties\")");
            return new Profile(string, string2, string3, string4, companion.getProfilesProperties(jSONArray), companion.hasPinCode(profileJson.getInt("pinCodeExist")));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Profile createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            return new Profile(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    }

    public final int getParentalLevel() {
        if (this.properties.containsKey("PrLevel")) {
            return Integer.parseInt(String.valueOf(this.properties.get("PrLevel")));
        }
        return 5;
    }

    public final ParentalRating getParentalLevel(BOService boService) {
        Intrinsics.checkParameterIsNotNull(boService, "boService");
        ParentalRating parentalRatingForName = ParentalRating.INSTANCE.forName(boService, "RATING:" + getParentalLevel());
        if (parentalRatingForName == null) {
            Intrinsics.throwNpe();
        }
        return parentalRatingForName;
    }

    public final boolean isMainProfile() {
        return this.properties.containsKey("IsMain") && Intrinsics.areEqual(this.properties.get("IsMain"), "true");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Profile(Parcel parcel) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        String string = parcel.readString();
        Intrinsics.checkExpressionValueIsNotNull(string, "parcel.readString()");
        String string2 = parcel.readString();
        Intrinsics.checkExpressionValueIsNotNull(string2, "parcel.readString()");
        String string3 = parcel.readString();
        Intrinsics.checkExpressionValueIsNotNull(string3, "parcel.readString()");
        String string4 = parcel.readString();
        Intrinsics.checkExpressionValueIsNotNull(string4, "parcel.readString()");
        this(string, string2, string3, string4, MapsKt.emptyMap(), parcel.readByte() != ((byte) 0));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (dest != null) {
            dest.writeString(this.externalId);
        }
        if (dest != null) {
            dest.writeString(this.id);
        }
        if (dest != null) {
            dest.writeString(this.name);
        }
        if (dest != null) {
            dest.writeString(this.image);
        }
        if (dest != null) {
            dest.writeByte(this.hasPinCode ? (byte) 1 : (byte) 0);
        }
    }

    public String toString() {
        return '#' + this.externalId + ' ' + this.name + ", prLevel = " + getParentalLevel();
    }
}
