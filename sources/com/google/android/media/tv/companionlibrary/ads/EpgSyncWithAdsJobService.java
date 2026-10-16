package com.google.android.media.tv.companionlibrary.ads;

import android.net.Uri;
import com.google.android.media.tv.companionlibrary.model.Advertisement;
import com.google.android.media.tv.companionlibrary.model.Channel;
import com.google.android.media.tv.companionlibrary.model.InternalProviderData;
import com.google.android.media.tv.companionlibrary.model.Program;
import com.google.android.media.tv.companionlibrary.sync.EpgSyncJobService;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public abstract class EpgSyncWithAdsJobService extends EpgSyncJobService {
    public abstract List<Program> getOriginalProgramsForChannel(Uri uri, Channel channel, long j, long j2);

    @Override // com.google.android.media.tv.companionlibrary.sync.EpgSyncJobService
    public final List<Program> getProgramsForChannel(Uri uri, Channel channel, long j, long j2) throws EpgSyncJobService.EpgSyncException {
        return repeatAndInsertAds(channel, getOriginalProgramsForChannel(uri, channel, j, j2), j, j2);
    }

    public static List<Program> repeatAndInsertAds(Channel channel, List<Program> list, long j, long j2) {
        long endTimeUtcMillis;
        if (j > j2) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        if (list.isEmpty()) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        if (channel.getInternalProviderData() != null && !channel.getInternalProviderData().isRepeatable()) {
            for (Program program : list) {
                if (program.getStartTimeUtcMillis() <= j2 && program.getEndTimeUtcMillis() >= j) {
                    arrayList.add(new Program.Builder(program).setChannelId(channel.getId()).build());
                }
            }
            return arrayList;
        }
        long endTimeUtcMillis2 = 0;
        for (Program program2 : list) {
            endTimeUtcMillis2 += program2.getEndTimeUtcMillis() - program2.getStartTimeUtcMillis();
        }
        if (endTimeUtcMillis2 <= 0) {
            throw new IllegalArgumentException("The duration of all programs must be greater than 0ms.");
        }
        int i = 0;
        int size = list.size();
        for (long j3 = j - (j % endTimeUtcMillis2); j3 < j2; j3 = endTimeUtcMillis) {
            int i2 = i + 1;
            Program program3 = list.get(i % size);
            endTimeUtcMillis = j3 + endTimeUtcMillis2;
            if (program3.getEndTimeUtcMillis() > -1 && program3.getStartTimeUtcMillis() > -1) {
                endTimeUtcMillis = (program3.getEndTimeUtcMillis() - program3.getStartTimeUtcMillis()) + j3;
            }
            if (endTimeUtcMillis >= j) {
                InternalProviderData internalProviderData = program3.getInternalProviderData();
                shiftAdsTimeWithProgram(internalProviderData, program3.getStartTimeUtcMillis(), j3);
                arrayList.add(new Program.Builder(program3).setChannelId(channel.getId()).setStartTimeUtcMillis(j3).setEndTimeUtcMillis(endTimeUtcMillis).setInternalProviderData(internalProviderData).build());
            }
            i = i2;
        }
        return arrayList;
    }

    private static void shiftAdsTimeWithProgram(InternalProviderData internalProviderData, long j, long j2) {
        if (internalProviderData == null) {
            return;
        }
        long j3 = j2 - j;
        List<Advertisement> ads = internalProviderData.getAds();
        ArrayList arrayList = new ArrayList();
        for (Advertisement advertisement : ads) {
            arrayList.add(new Advertisement.Builder(advertisement).setStartTimeUtcMillis(advertisement.getStartTimeUtcMillis() + j3).setStopTimeUtcMillis(advertisement.getStopTimeUtcMillis() + j3).build());
        }
        internalProviderData.setAds(arrayList);
    }
}
