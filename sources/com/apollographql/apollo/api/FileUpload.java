package com.apollographql.apollo.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FileUpload.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u000e"}, d2 = {"Lcom/apollographql/apollo/api/FileUpload;", "", "mimetype", "", "filePath", "(Ljava/lang/String;Ljava/lang/String;)V", "getFilePath", "()Ljava/lang/String;", "getMimetype", "equals", "", "other", "hashCode", "", "apollo-api"}, k = 1, mv = {1, 1, 16})
public final class FileUpload {
    private final String filePath;
    private final String mimetype;

    public FileUpload(String mimetype, String filePath) {
        Intrinsics.checkParameterIsNotNull(mimetype, "mimetype");
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        this.mimetype = mimetype;
        this.filePath = filePath;
    }

    public final String getFilePath() {
        return this.filePath;
    }

    public final String getMimetype() {
        return this.mimetype;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FileUpload)) {
            return false;
        }
        FileUpload fileUpload = (FileUpload) other;
        return ((Intrinsics.areEqual(this.mimetype, fileUpload.mimetype) ^ true) || (Intrinsics.areEqual(this.filePath, fileUpload.filePath) ^ true)) ? false : true;
    }

    public int hashCode() {
        return (this.mimetype.hashCode() * 31) + this.filePath.hashCode();
    }
}
