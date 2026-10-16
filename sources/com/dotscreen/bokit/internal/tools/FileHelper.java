package com.dotscreen.bokit.internal.tools;

import android.util.Log;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FileHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\u0004J\u001c\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00072\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/dotscreen/bokit/internal/tools/FileHelper;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "readBytesFromFile", "Lio/reactivex/Single;", "", "path", "writeBytesToFile", "", "data", "bokit-android_release"}, k = 1, mv = {1, 4, 1})
public final class FileHelper {
    public static final FileHelper INSTANCE = new FileHelper();
    private static final String TAG = FileHelper.class.getSimpleName();

    private FileHelper() {
    }

    public static final /* synthetic */ String access$getTAG$p(FileHelper fileHelper) {
        return TAG;
    }

    public final Single<byte[]> readBytesFromFile(final String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        Single<byte[]> singleCreate = Single.create(new SingleOnSubscribe<byte[]>() { // from class: com.dotscreen.bokit.internal.tools.FileHelper.readBytesFromFile.1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<byte[]> observer) {
                Intrinsics.checkNotNullParameter(observer, "observer");
                File file = new File(path);
                Log.d(FileHelper.access$getTAG$p(FileHelper.INSTANCE), "readBytesFromFile: " + file);
                try {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr = new byte[1024];
                    for (int i = bufferedInputStream.read(bArr); i != -1; i = bufferedInputStream.read(bArr)) {
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                    bufferedInputStream.close();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    Log.d(FileHelper.access$getTAG$p(FileHelper.INSTANCE), "readBytesFromFile: done " + byteArray.length + " bytes.");
                    observer.onSuccess(byteArray);
                } catch (Exception e) {
                    e.printStackTrace();
                    observer.onError(e);
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "Single.create { observer…)\n            }\n        }");
        return singleCreate;
    }

    public final Single<Unit> writeBytesToFile(final byte[] data, final String path) {
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(path, "path");
        Single<Unit> singleCreate = Single.create(new SingleOnSubscribe<Unit>() { // from class: com.dotscreen.bokit.internal.tools.FileHelper.writeBytesToFile.1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(SingleEmitter<Unit> observer) {
                Intrinsics.checkNotNullParameter(observer, "observer");
                File file = new File(path);
                boolean zMkdirs = file.getParentFile().mkdirs();
                Log.d(FileHelper.access$getTAG$p(FileHelper.INSTANCE), "parent: " + file.getParentFile());
                Log.d(FileHelper.access$getTAG$p(FileHelper.INSTANCE), "writeBytesToFile: " + file + " (" + zMkdirs + ')');
                try {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    byte[] bArr = new byte[1024];
                    for (int i = byteArrayInputStream.read(bArr); i != -1; i = byteArrayInputStream.read(bArr)) {
                        fileOutputStream.write(bArr, 0, i);
                    }
                    fileOutputStream.close();
                    byteArrayInputStream.close();
                    Log.d(FileHelper.access$getTAG$p(FileHelper.INSTANCE), "writeBytesToFile: done " + data.length + " bytes.");
                    observer.onSuccess(Unit.INSTANCE);
                } catch (Exception e) {
                    e.printStackTrace();
                    observer.onError(e);
                }
            }
        });
        Intrinsics.checkNotNullExpressionValue(singleCreate, "Single.create { observer…)\n            }\n        }");
        return singleCreate;
    }
}
