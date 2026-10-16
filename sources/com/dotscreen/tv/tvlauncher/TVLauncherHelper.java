package com.dotscreen.tv.tvlauncher;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import androidx.tvprovider.media.tv.Channel;
import androidx.tvprovider.media.tv.ChannelLogoUtils;
import androidx.tvprovider.media.tv.PreviewProgram;
import androidx.tvprovider.media.tv.TvContractCompat;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TVLauncherHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\u0013J\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\t2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0013J \u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001eJ \u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u001d\u001a\u00020\u001eJ\u0018\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u000e\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u0015J\u000e\u0010#\u001a\u00020\u00172\u0006\u0010\u0012\u001a\u00020\u0013R\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\n \u0007*\u0004\u0018\u00010\u000e0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/dotscreen/tv/tvlauncher/TVLauncherHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "TAG", "", "kotlin.jvm.PlatformType", "channels", "", "Landroidx/tvprovider/media/tv/Channel;", "getChannels", "()Ljava/util/List;", "contentResolver", "Landroid/content/ContentResolver;", "getContentResolver", "()Landroid/content/ContentResolver;", "channel", "channelId", "", "getPrograms", "Landroidx/tvprovider/media/tv/PreviewProgram;", "removeChannel", "", "removeProgram", "programId", "setChannel", TvContractCompat.Channels.Logo.CONTENT_DIRECTORY, "Landroid/graphics/Bitmap;", "default", "", "logoUri", "Landroid/net/Uri;", "setProgram", "program", "touchChannel", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class TVLauncherHelper {
    private final String TAG;
    private final ContentResolver contentResolver;
    private final Context context;

    public TVLauncherHelper(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        this.TAG = TVLauncherHelper.class.getSimpleName();
        this.contentResolver = this.context.getContentResolver();
    }

    public final ContentResolver getContentResolver() {
        return this.contentResolver;
    }

    public final List<Channel> getChannels() {
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = this.contentResolver.query(TvContractCompat.Channels.CONTENT_URI, Channel.PROJECTION, null, null, null);
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            do {
                Channel channel = Channel.fromCursor(cursorQuery);
                Intrinsics.checkExpressionValueIsNotNull(channel, "channel");
                if (TVLauncherHelperKt.isTVLauncherChannel(channel, this.context)) {
                    arrayList.add(channel);
                }
            } while (cursorQuery.moveToNext());
            cursorQuery.close();
        }
        return arrayList;
    }

    public final Channel channel(long channelId) {
        for (Channel channel : getChannels()) {
            if (channel.getId() == channelId) {
                return channel;
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public static /* synthetic */ long setChannel$default(TVLauncherHelper tVLauncherHelper, Channel channel, Uri uri, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return tVLauncherHelper.setChannel(channel, uri, z);
    }

    public final long setChannel(Channel channel, Uri logoUri, boolean z) throws Throwable {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(logoUri, "logoUri");
        long channel2 = setChannel(channel, z);
        ChannelLogoUtils.storeChannelLogo(this.context, channel2, logoUri);
        return channel2;
    }

    public static /* synthetic */ long setChannel$default(TVLauncherHelper tVLauncherHelper, Channel channel, Bitmap bitmap, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return tVLauncherHelper.setChannel(channel, bitmap, z);
    }

    public final long setChannel(Channel channel, Bitmap logo, boolean z) {
        Intrinsics.checkParameterIsNotNull(channel, "channel");
        Intrinsics.checkParameterIsNotNull(logo, "logo");
        long channel2 = setChannel(channel, z);
        ChannelLogoUtils.storeChannelLogo(this.context, channel2, logo);
        return channel2;
    }

    private final long setChannel(Channel channel, boolean z) throws InvalidParameterException {
        long id = channel.getId();
        if (id == -1) {
            if (!Intrinsics.areEqual(channel.getType(), TvContractCompat.Channels.TYPE_PREVIEW)) {
                throw new InvalidParameterException("channel type must be TYPE_PREVIEW");
            }
            Uri uriInsert = this.contentResolver.insert(TvContractCompat.Channels.CONTENT_URI, channel.toContentValues());
            Log.d(this.TAG, "setChannel: channelUri = " + uriInsert);
            id = ContentUris.parseId(uriInsert);
        } else {
            Log.d(this.TAG, "setChannel: update = " + channel);
            this.contentResolver.update(TvContractCompat.buildChannelUri(id), channel.toContentValues(), null, null);
        }
        if (z) {
            TvContractCompat.requestChannelBrowsable(this.context, id);
        }
        return id;
    }

    public final void touchChannel(long channelId) {
        List<PreviewProgram> programs = getPrograms(channelId);
        if (!programs.isEmpty()) {
            PreviewProgram previewProgramBuild = new PreviewProgram.Builder(programs.get(0)).build();
            Intrinsics.checkExpressionValueIsNotNull(previewProgramBuild, "PreviewProgram.Builder(programs[0]).build()");
            setProgram(previewProgramBuild);
        }
    }

    public final void removeChannel(long channelId) {
        if (channelId > 0) {
            this.contentResolver.delete(TvContractCompat.buildChannelUri(channelId), null, null);
        }
    }

    public final List<PreviewProgram> getPrograms(long channelId) {
        ArrayList arrayList = new ArrayList();
        Cursor cursorQuery = this.contentResolver.query(TvContractCompat.PreviewPrograms.CONTENT_URI, PreviewProgram.PROJECTION, null, null, null);
        if (cursorQuery != null && cursorQuery.moveToFirst()) {
            do {
                PreviewProgram program = PreviewProgram.fromCursor(cursorQuery);
                Intrinsics.checkExpressionValueIsNotNull(program, "program");
                if (program.getChannelId() == channelId) {
                    arrayList.add(program);
                }
            } while (cursorQuery.moveToNext());
            cursorQuery.close();
        }
        return arrayList;
    }

    public final long setProgram(PreviewProgram program) {
        Intrinsics.checkParameterIsNotNull(program, "program");
        long id = program.getId();
        if (id == -1) {
            Uri uriInsert = this.contentResolver.insert(TvContractCompat.PreviewPrograms.CONTENT_URI, program.toContentValues());
            Log.d(this.TAG, "setProgram: programUri = " + uriInsert);
            return ContentUris.parseId(uriInsert);
        }
        Log.d(this.TAG, "setChannel: update = " + program);
        this.contentResolver.update(TvContractCompat.buildPreviewProgramUri(id), program.toContentValues(), null, null);
        return id;
    }

    public final void removeProgram(long programId) {
        if (programId > 0) {
            this.contentResolver.delete(TvContractCompat.buildPreviewProgramUri(programId), null, null);
        }
    }
}
