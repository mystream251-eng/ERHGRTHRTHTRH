package com.dotscreen.bokit.type;

/* JADX INFO: loaded from: classes.dex */
public enum IdentifierScheme {
    DATA_PROVIDER("DATA_PROVIDER"),
    DISTRIBUTOR("DISTRIBUTOR"),
    IMDB("IMDB"),
    TVDB("TVDB"),
    TMDB("TMDB"),
    METACRITIC("METACRITIC"),
    ROTTEN_TOMATOES("ROTTEN_TOMATOES"),
    APPLE_ITUNES("APPLE_ITUNES"),
    GOOGLE_PLAY("GOOGLE_PLAY"),
    AMAZON_PRIME("AMAZON_PRIME"),
    NETFLIX("NETFLIX"),
    GENERIC_DEEP_LINK("GENERIC_DEEP_LINK"),
    YOUTUBE("YOUTUBE"),
    VIMEO("VIMEO"),
    $UNKNOWN("$UNKNOWN");

    private final String rawValue;

    IdentifierScheme(String str) {
        this.rawValue = str;
    }

    public String rawValue() {
        return this.rawValue;
    }

    public static IdentifierScheme safeValueOf(String str) {
        for (IdentifierScheme identifierScheme : values()) {
            if (identifierScheme.rawValue.equals(str)) {
                return identifierScheme;
            }
        }
        return $UNKNOWN;
    }
}
