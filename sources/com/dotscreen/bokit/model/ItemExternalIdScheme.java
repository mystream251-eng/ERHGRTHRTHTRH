package com.dotscreen.bokit.model;

import kotlin.Metadata;

/* JADX INFO: compiled from: Item.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Lcom/dotscreen/bokit/model/ItemExternalIdScheme;", "", "scheme", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getScheme", "()Ljava/lang/String;", "DataProvider", "Distributor", "IMDB", "TVDB", "TMDB", "Metacritic", "RottenTomatoes", "AppleiTunes", "GooglePlay", "AmazonPrime", "Netflix", "GenericDeeplink", "Youtube", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public enum ItemExternalIdScheme {
    DataProvider("DATA_PROVIDER"),
    Distributor("DISTRIBUTOR"),
    IMDB("IMDB"),
    TVDB("TVDB"),
    TMDB("TMDB"),
    Metacritic("METACRITIC"),
    RottenTomatoes("ROTTEN_TOMATOES"),
    AppleiTunes("APPLE_ITUNES"),
    GooglePlay("GOOGLE_PLAY"),
    AmazonPrime("AMAZON_PRIME"),
    Netflix("NETFLIX"),
    GenericDeeplink("GENERIC_DEEP_LINK"),
    Youtube("YOUTUBE");

    private final String scheme;

    ItemExternalIdScheme(String str) {
        this.scheme = str;
    }

    public final String getScheme() {
        return this.scheme;
    }
}
