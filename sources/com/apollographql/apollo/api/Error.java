package com.apollographql.apollo.api;

import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Error.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0015B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b¢\u0006\u0002\u0010\tJ\u0016\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\bH\u0007J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u000e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0007R\u001f\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0016"}, d2 = {"Lcom/apollographql/apollo/api/Error;", "", "message", "", "locations", "", "Lcom/apollographql/apollo/api/Error$Location;", "customAttributes", "", "(Ljava/lang/String;Ljava/util/List;Ljava/util/Map;)V", "getCustomAttributes", "()Ljava/util/Map;", "getLocations", "()Ljava/util/List;", "getMessage", "()Ljava/lang/String;", "equals", "", "other", "hashCode", "", "Location", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class Error {
    private final Map<String, Object> customAttributes;
    private final List<Location> locations;
    private final String message;

    public Error(String message, List<Location> locations, Map<String, ? extends Object> customAttributes) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        Intrinsics.checkParameterIsNotNull(locations, "locations");
        Intrinsics.checkParameterIsNotNull(customAttributes, "customAttributes");
        this.message = message;
        this.locations = locations;
        this.customAttributes = customAttributes;
    }

    public final String getMessage() {
        return this.message;
    }

    public /* synthetic */ Error(String str, List list, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? CollectionsKt.emptyList() : list, (i & 4) != 0 ? MapsKt.emptyMap() : map);
    }

    public final List<Location> getLocations() {
        return this.locations;
    }

    public final Map<String, Object> getCustomAttributes() {
        return this.customAttributes;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "message", imports = {}))
    public final String message() {
        return this.message;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "locations", imports = {}))
    public final List<Location> locations() {
        return this.locations;
    }

    @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "customAttributes", imports = {}))
    public final Map<String, Object> customAttributes() {
        return this.customAttributes;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Error)) {
            return false;
        }
        Error error = (Error) other;
        return ((Intrinsics.areEqual(this.message, error.message) ^ true) || (Intrinsics.areEqual(this.locations, error.locations) ^ true) || (Intrinsics.areEqual(this.customAttributes, error.customAttributes) ^ true)) ? false : true;
    }

    public int hashCode() {
        return (((this.message.hashCode() * 31) + this.locations.hashCode()) * 31) + this.customAttributes.hashCode();
    }

    /* JADX INFO: compiled from: Error.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\b\u0010\u0004\u001a\u00020\u0003H\u0007J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo/api/Error$Location;", "", "line", "", "column", "(JJ)V", "getColumn", "()J", "getLine", "equals", "", "other", "hashCode", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
    public static final class Location {
        private final long column;
        private final long line;

        public Location(long j, long j2) {
            this.line = j;
            this.column = j2;
        }

        public final long getLine() {
            return this.line;
        }

        public final long getColumn() {
            return this.column;
        }

        @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "line", imports = {}))
        public final long line() {
            return this.line;
        }

        @Deprecated(message = "Use property instead", replaceWith = @ReplaceWith(expression = "column", imports = {}))
        /* JADX INFO: renamed from: column, reason: from getter */
        public final long getColumn() {
            return this.column;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Location)) {
                return false;
            }
            Location location = (Location) other;
            return this.line == location.line && this.column == location.column;
        }

        public int hashCode() {
            return (Long.hashCode(this.line) * 31) + Long.hashCode(this.column);
        }
    }
}
