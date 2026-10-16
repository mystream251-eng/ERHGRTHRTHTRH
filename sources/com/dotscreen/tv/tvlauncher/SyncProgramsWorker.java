package com.dotscreen.tv.tvlauncher;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.media.tv.TvContentRating;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import androidx.tvprovider.media.tv.Channel;
import androidx.tvprovider.media.tv.PreviewProgram;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import com.dotscreen.bokit.R;
import com.dotscreen.bokit.model.Artwork;
import com.dotscreen.bokit.model.Lineup;
import com.dotscreen.bokit.model.ParentalRating;
import com.dotscreen.bokit.model.TVAiring;
import com.dotscreen.bokit.model.TVAiringTimeOfDay;
import com.dotscreen.bokit.model.TVProgram;
import com.dotscreen.bokit.services.BOService;
import com.dotscreen.tv.common.utils.CommonUtils;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: SyncProgramsWorker.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\rH\u0016R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/dotscreen/tv/tvlauncher/SyncProgramsWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "TAG", "", "kotlin.jvm.PlatformType", "doWork", "Landroidx/work/ListenableWorker$Result;", "onStopped", "", "common_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class SyncProgramsWorker extends Worker {
    private final String TAG;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SyncProgramsWorker(Context context, WorkerParameters params) {
        super(context, params);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(params, "params");
        String simpleName = SyncProgramsWorker.class.getSimpleName();
        this.TAG = simpleName;
        Log.i(simpleName, "new SyncProgramsWorker");
    }

    /* JADX WARN: Code duplicated, block: B:199:0x079d A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:202:0x080c A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:205:0x08c0 A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:206:0x08e2 A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:209:0x08f6 A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:211:0x0903 A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:213:0x0915  */
    /* JADX WARN: Code duplicated, block: B:221:0x0956 A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:223:0x0967 A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:225:0x096f A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:229:0x099f  */
    /* JADX WARN: Code duplicated, block: B:243:0x09ca A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:249:0x09dc A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:252:0x09ec A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:256:0x09fc A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:272:0x0ae4 A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Code duplicated, block: B:275:0x0b1c A[Catch: Exception -> 0x0b90, TryCatch #1 {Exception -> 0x0b90, blocks: (B:5:0x0022, B:7:0x0034, B:11:0x003e, B:15:0x005e, B:17:0x0066, B:19:0x006c, B:22:0x0073, B:137:0x053f, B:139:0x0558, B:141:0x0575, B:142:0x057e, B:138:0x0544, B:23:0x0081, B:24:0x00ac, B:26:0x00b2, B:28:0x00c2, B:29:0x00c6, B:30:0x00eb, B:32:0x00f1, B:34:0x00fd, B:36:0x0105, B:45:0x0134, B:55:0x018c, B:59:0x0196, B:62:0x01bd, B:64:0x01c3, B:66:0x01cd, B:69:0x01da, B:73:0x020b, B:75:0x029e, B:76:0x02a1, B:78:0x0310, B:79:0x0313, B:81:0x03a2, B:83:0x03db, B:85:0x03e5, B:87:0x03ef, B:88:0x03f2, B:89:0x0400, B:91:0x040a, B:93:0x0410, B:94:0x041c, B:96:0x042c, B:98:0x043d, B:99:0x044d, B:105:0x047e, B:128:0x04d4, B:102:0x0474, B:106:0x0482, B:112:0x0497, B:114:0x049c, B:116:0x04a2, B:118:0x04a8, B:120:0x04ae, B:121:0x04b1, B:123:0x04be, B:124:0x04c1, B:125:0x04c8, B:127:0x04ce, B:109:0x048d, B:82:0x03d2, B:72:0x01e1, B:68:0x01d4, B:49:0x0142, B:52:0x014c, B:54:0x015a, B:131:0x0503, B:133:0x0509, B:143:0x0583, B:145:0x059f, B:147:0x05a5, B:150:0x05ac, B:285:0x0b57, B:287:0x0b6f, B:286:0x0b5c, B:151:0x05b8, B:153:0x05e6, B:154:0x05f0, B:155:0x05fa, B:157:0x0600, B:176:0x0653, B:180:0x0665, B:182:0x066b, B:188:0x06ab, B:190:0x06d6, B:192:0x06dc, B:194:0x06e8, B:197:0x06f0, B:199:0x079d, B:200:0x07a0, B:202:0x080c, B:203:0x080f, B:205:0x08c0, B:207:0x08e9, B:209:0x08f6, B:211:0x0903, B:212:0x0906, B:214:0x0917, B:216:0x0924, B:218:0x092a, B:219:0x0936, B:221:0x0956, B:223:0x0967, B:225:0x096f, B:226:0x0976, B:227:0x0978, B:233:0x09ab, B:257:0x0a02, B:279:0x0b36, B:230:0x09a1, B:235:0x09b0, B:241:0x09c5, B:243:0x09ca, B:245:0x09d0, B:247:0x09d6, B:249:0x09dc, B:250:0x09df, B:252:0x09ec, B:253:0x09ef, B:254:0x09f6, B:256:0x09fc, B:238:0x09bb, B:206:0x08e2, B:185:0x0679, B:187:0x0681, B:258:0x0a15, B:270:0x0a5f, B:272:0x0ae4, B:273:0x0ae7, B:275:0x0b1c, B:276:0x0b23), top: B:297:0x0022 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:205:0x08c0, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.work.Worker
    public ListenableWorker.Result doWork() {
        long j;
        long j2;
        Iterator it;
        PreviewProgram previewProgram;
        PreviewProgram previewProgram2;
        List<PreviewProgram> list;
        TVLauncherHelper tVLauncherHelper;
        long j3;
        String str;
        String str2;
        String str3;
        String str4;
        TVLauncherHelper tVLauncherHelper2;
        PreviewProgram.Builder intentUri;
        ParentalRating parentalLevel;
        TVProgram tVProgramBlockingGet;
        String[] stringArray;
        String str5;
        String episodeTitle;
        Integer seasonNumber;
        Integer episodeNumber;
        Integer productionYear;
        int iIntValue;
        TvContentRating tvContentRating;
        Double rating;
        Uri.Builder builderBuildUpon;
        PreviewProgram previewProgram3;
        Artwork squareArtwork;
        PreviewProgram.Builder internalProviderId;
        Object next;
        Duration durationOfMillis;
        long j4;
        long j5;
        boolean z;
        boolean z2;
        String str6;
        List<PreviewProgram> list2;
        String str7;
        TVLauncherHelper tVLauncherHelper3;
        Uri uriBuild;
        TvContentRating tvContentRating2;
        Uri.Builder builderBuildUpon2;
        Uri.Builder builderAppendQueryParameter;
        Duration durationOfMillis2;
        long j6 = getInputData().getLong(SyncProgramsWorkerKt.DATA_CHANNEL_ID_KEY, -1L);
        if (j6 > 0) {
            Context applicationContext = getApplicationContext();
            String str8 = "applicationContext";
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "applicationContext");
            TVLauncherHelper tVLauncherHelper4 = new TVLauncherHelper(applicationContext);
            try {
                Channel channel = tVLauncherHelper4.channel(j6);
                BOService bOService = CommonUtils.getBOService(getApplicationContext());
                List<PreviewProgram> programs = tVLauncherHelper4.getPrograms(j6);
                String internalProviderId2 = channel != null ? channel.getInternalProviderId() : null;
                if (internalProviderId2 != null) {
                    int iHashCode = internalProviderId2.hashCode();
                    String str9 = " | ";
                    String str10 = TvContractCompat.PARAM_INPUT;
                    String str11 = " - ";
                    String str12 = "java.lang.String.format(format, *args)";
                    String str13 = "%03d";
                    TVLauncherHelper tVLauncherHelper5 = tVLauncherHelper4;
                    String str14 = "applicationContext.resources";
                    switch (iHashCode) {
                        case 1432626061:
                            String str15 = " | ";
                            String str16 = " - ";
                            String str17 = "%03d";
                            String str18 = TvContractCompat.PARAM_INPUT;
                            String str19 = str14;
                            String str20 = "java.lang.String.format(format, *args)";
                            TVLauncherHelper tVLauncherHelper6 = tVLauncherHelper5;
                            List<PreviewProgram> list3 = programs;
                            long j7 = j6;
                            if (internalProviderId2.equals("channel0")) {
                                if (channel.isBrowsable() || list3.isEmpty()) {
                                    List airings = (List) CommonUtils.getCurrentLineup(bOService).retry().flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.tvlauncher.SyncProgramsWorker$doWork$airings$1
                                        @Override // io.reactivex.functions.Function
                                        public final Observable<com.dotscreen.bokit.model.Channel> apply(Lineup it2) {
                                            Intrinsics.checkParameterIsNotNull(it2, "it");
                                            return it2.getItems();
                                        }
                                    }).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.tvlauncher.SyncProgramsWorker$doWork$airings$2
                                        @Override // io.reactivex.functions.Function
                                        public final Maybe<Object> apply(final com.dotscreen.bokit.model.Channel channel2) {
                                            Intrinsics.checkParameterIsNotNull(channel2, "channel");
                                            return channel2.airing(TVAiringTimeOfDay.Now).map(new Function<T, R>() { // from class: com.dotscreen.tv.tvlauncher.SyncProgramsWorker$doWork$airings$2.1
                                                @Override // io.reactivex.functions.Function
                                                public final Object apply(TVAiring it2) {
                                                    Intrinsics.checkParameterIsNotNull(it2, "it");
                                                    return it2;
                                                }
                                            }).onErrorReturn(new Function<Throwable, Object>() { // from class: com.dotscreen.tv.tvlauncher.SyncProgramsWorker$doWork$airings$2.2
                                                @Override // io.reactivex.functions.Function
                                                public final com.dotscreen.bokit.model.Channel apply(Throwable it2) {
                                                    Intrinsics.checkParameterIsNotNull(it2, "it");
                                                    return channel2;
                                                }
                                            });
                                        }
                                    }).toList().blockingGet();
                                    Intrinsics.checkExpressionValueIsNotNull(airings, "airings");
                                    if (airings.size() > 1) {
                                        CollectionsKt.sortWith(airings, new Comparator<T>() { // from class: com.dotscreen.tv.tvlauncher.SyncProgramsWorker$doWork$$inlined$sortBy$1
                                            /* JADX WARN: Multi-variable type inference failed */
                                            @Override // java.util.Comparator
                                            public final int compare(T t, T t2) {
                                                int zappingNumber;
                                                int zappingNumber2 = 0;
                                                if (t instanceof TVAiring) {
                                                    zappingNumber = ((TVAiring) t).getChannel().getZappingNumber();
                                                } else {
                                                    zappingNumber = t instanceof com.dotscreen.bokit.model.Channel ? ((com.dotscreen.bokit.model.Channel) t).getZappingNumber() : 0;
                                                }
                                                Integer numValueOf = Integer.valueOf(zappingNumber);
                                                if (t2 instanceof TVAiring) {
                                                    zappingNumber2 = ((TVAiring) t2).getChannel().getZappingNumber();
                                                } else if (t2 instanceof com.dotscreen.bokit.model.Channel) {
                                                    zappingNumber2 = ((com.dotscreen.bokit.model.Channel) t2).getZappingNumber();
                                                }
                                                return ComparisonsKt.compareValues(numValueOf, Integer.valueOf(zappingNumber2));
                                            }
                                        });
                                    }
                                    PreviewProgram previewProgramBuild = (PreviewProgram) null;
                                    Iterator it2 = airings.iterator();
                                    j = Long.MAX_VALUE;
                                    while (it2.hasNext()) {
                                        Object next2 = it2.next();
                                        if (!(next2 instanceof TVAiring)) {
                                            it = it2;
                                            list = list3;
                                            tVLauncherHelper2 = tVLauncherHelper6;
                                            PreviewProgram previewProgram4 = previewProgramBuild;
                                            long j8 = j7;
                                            if (next2 instanceof com.dotscreen.bokit.model.Channel) {
                                                try {
                                                    Iterator<T> it3 = list.iterator();
                                                    do {
                                                        if (!it3.hasNext()) {
                                                            throw new NoSuchElementException("Collection contains no element matching the predicate.");
                                                        }
                                                        next = it3.next();
                                                        squareArtwork = ((com.dotscreen.bokit.model.Channel) next2).getSquareArtwork();
                                                        Uri liveTvDeeplink = CommonUtils.getLiveTvDeeplink(getApplicationContext(), (com.dotscreen.bokit.model.Channel) next2, (Boolean) true);
                                                        j3 = j8;
                                                        PreviewProgram.Builder type = new PreviewProgram.Builder().setChannelId(j3).setType(6);
                                                        StringBuilder sb = new StringBuilder();
                                                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                                                        str3 = str17;
                                                        String str21 = String.format(str3, Arrays.copyOf(new Object[]{Integer.valueOf(((com.dotscreen.bokit.model.Channel) next2).getZappingNumber())}, 1));
                                                        str = str20;
                                                        Intrinsics.checkNotNullExpressionValue(str21, str);
                                                        sb.append(str21);
                                                        str2 = str16;
                                                        sb.append(str2);
                                                        sb.append(((com.dotscreen.bokit.model.Channel) next2).getTitle());
                                                        sb.append(" | pas d'information");
                                                        PreviewProgram.Builder posterArtAspectRatio = ((PreviewProgram.Builder) type.setTitle(sb.toString())).setLive(true).setPosterArtAspectRatio(0);
                                                        if (squareArtwork == null) {
                                                            Intrinsics.throwNpe();
                                                        }
                                                        internalProviderId = posterArtAspectRatio.setLogoUri(Uri.parse(String.valueOf(Artwork.urlWithSize$default(squareArtwork, new Point(256, 256), 0.0d, 2, null)))).setIntentUri(liveTvDeeplink).setInternalProviderId(((com.dotscreen.bokit.model.Channel) next2).getId());
                                                        if (previewProgram3 != null) {
                                                            internalProviderId.setId(previewProgram3.getId());
                                                        }
                                                        previewProgramBuild = internalProviderId.build();
                                                    } while (!Intrinsics.areEqual(((PreviewProgram) next).getInternalProviderId(), ((com.dotscreen.bokit.model.Channel) next2).getId()));
                                                    previewProgram3 = (PreviewProgram) next;
                                                } catch (Exception unused) {
                                                    previewProgram3 = null;
                                                }
                                                squareArtwork = ((com.dotscreen.bokit.model.Channel) next2).getSquareArtwork();
                                                Uri liveTvDeeplink2 = CommonUtils.getLiveTvDeeplink(getApplicationContext(), (com.dotscreen.bokit.model.Channel) next2, (Boolean) true);
                                                j3 = j8;
                                                PreviewProgram.Builder type2 = new PreviewProgram.Builder().setChannelId(j3).setType(6);
                                                StringBuilder sb2 = new StringBuilder();
                                                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                                                str3 = str17;
                                                String str22 = String.format(str3, Arrays.copyOf(new Object[]{Integer.valueOf(((com.dotscreen.bokit.model.Channel) next2).getZappingNumber())}, 1));
                                                str = str20;
                                                Intrinsics.checkNotNullExpressionValue(str22, str);
                                                sb2.append(str22);
                                                str2 = str16;
                                                sb2.append(str2);
                                                sb2.append(((com.dotscreen.bokit.model.Channel) next2).getTitle());
                                                sb2.append(" | pas d'information");
                                                PreviewProgram.Builder posterArtAspectRatio2 = ((PreviewProgram.Builder) type2.setTitle(sb2.toString())).setLive(true).setPosterArtAspectRatio(0);
                                                if (squareArtwork == null) {
                                                    Intrinsics.throwNpe();
                                                }
                                                internalProviderId = posterArtAspectRatio2.setLogoUri(Uri.parse(String.valueOf(Artwork.urlWithSize$default(squareArtwork, new Point(256, 256), 0.0d, 2, null)))).setIntentUri(liveTvDeeplink2).setInternalProviderId(((com.dotscreen.bokit.model.Channel) next2).getId());
                                                if (previewProgram3 != null) {
                                                    internalProviderId.setId(previewProgram3.getId());
                                                }
                                                previewProgramBuild = internalProviderId.build();
                                            } else {
                                                str = str20;
                                                str2 = str16;
                                                j3 = j8;
                                                str3 = str17;
                                                previewProgramBuild = previewProgram4;
                                            }
                                        } else {
                                            try {
                                                Iterator<T> it4 = list3.iterator();
                                                while (true) {
                                                    if (!it4.hasNext()) {
                                                        it = it2;
                                                        previewProgram = previewProgramBuild;
                                                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                                                    }
                                                    Object next3 = it4.next();
                                                    it = it2;
                                                    try {
                                                        previewProgram = previewProgramBuild;
                                                        try {
                                                            if (Intrinsics.areEqual(((PreviewProgram) next3).getInternalProviderId(), ((TVAiring) next2).getChannel().getId())) {
                                                                previewProgram2 = (PreviewProgram) next3;
                                                            } else {
                                                                it2 = it;
                                                                previewProgramBuild = previewProgram;
                                                            }
                                                        } catch (Exception unused2) {
                                                            previewProgram2 = null;
                                                        }
                                                    } catch (Exception unused3) {
                                                        previewProgram = previewProgramBuild;
                                                        previewProgram2 = null;
                                                    }
                                                    previewProgram2 = null;
                                                }
                                            } catch (Exception unused4) {
                                                it = it2;
                                            }
                                            long time = ((TVAiring) next2).getEndDate().getTime();
                                            if (time < j) {
                                                j = time;
                                            }
                                            Long internalProviderFlag1 = previewProgram2 != null ? previewProgram2.getInternalProviderFlag1() : null;
                                            long time2 = ((TVAiring) next2).getStartDate().getTime();
                                            if (internalProviderFlag1 != null && internalProviderFlag1.longValue() == time2) {
                                                Log.v(this.TAG, "channel0: don't need to update program #" + previewProgram2.getId());
                                                list = list3;
                                                tVLauncherHelper = tVLauncherHelper6;
                                                j3 = j7;
                                                str = str20;
                                                previewProgramBuild = previewProgram;
                                                str2 = str16;
                                                str3 = str17;
                                                str19 = str19;
                                            } else {
                                                Artwork stillArtwork = ((TVAiring) next2).getStillArtwork();
                                                Artwork squareArtwork2 = ((TVAiring) next2).getChannel().getSquareArtwork();
                                                long j9 = j;
                                                Uri liveTvDeeplink3 = CommonUtils.getLiveTvDeeplink(getApplicationContext(), ((TVAiring) next2).getChannel(), (Boolean) true);
                                                if (liveTvDeeplink3 == null || (builderBuildUpon = liveTvDeeplink3.buildUpon()) == null) {
                                                    str4 = str18;
                                                } else {
                                                    str4 = str18;
                                                    Uri.Builder builderAppendQueryParameter2 = builderBuildUpon.appendQueryParameter(str4, CommonUtils.getOTTLiveInputId());
                                                    Uri uriBuild2 = builderAppendQueryParameter2 != null ? builderAppendQueryParameter2.build() : null;
                                                    str18 = str4;
                                                    PreviewProgram.Builder type3 = new PreviewProgram.Builder().setChannelId(j7).setType(5);
                                                    StringBuilder sb3 = new StringBuilder();
                                                    StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                                                    tVLauncherHelper2 = tVLauncherHelper6;
                                                    long j10 = j7;
                                                    String str23 = str17;
                                                    String str24 = String.format(str23, Arrays.copyOf(new Object[]{Integer.valueOf(((TVAiring) next2).getChannel().getZappingNumber())}, 1));
                                                    String str25 = str20;
                                                    Intrinsics.checkNotNullExpressionValue(str24, str25);
                                                    sb3.append(str24);
                                                    String str26 = str16;
                                                    sb3.append(str26);
                                                    sb3.append(((TVAiring) next2).getChannel().getTitle());
                                                    String str27 = str15;
                                                    sb3.append(str27);
                                                    str15 = str27;
                                                    sb3.append(((TVAiring) next2).getTitle());
                                                    PreviewProgram.Builder posterArtAspectRatio3 = ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) type3.setTitle(sb3.toString())).setDescription(((TVAiring) next2).getSummary())).setLive(true).setGenre(((TVAiring) next2).getMetadata().getGenres()[0]).setReviewRatingStyle(0)).setPosterArtAspectRatio(0);
                                                    if (squareArtwork2 == null) {
                                                        Intrinsics.throwNpe();
                                                    }
                                                    Context applicationContext2 = getApplicationContext();
                                                    Intrinsics.checkExpressionValueIsNotNull(applicationContext2, "applicationContext");
                                                    Resources resources = applicationContext2.getResources();
                                                    Intrinsics.checkExpressionValueIsNotNull(resources, str19);
                                                    int iApplyDimension = (int) TypedValue.applyDimension(1, 28.0f, resources.getDisplayMetrics());
                                                    Context applicationContext3 = getApplicationContext();
                                                    Intrinsics.checkExpressionValueIsNotNull(applicationContext3, "applicationContext");
                                                    Resources resources2 = applicationContext3.getResources();
                                                    Intrinsics.checkExpressionValueIsNotNull(resources2, str19);
                                                    list = list3;
                                                    PreviewProgram.Builder logoContentDescription = posterArtAspectRatio3.setLogoUri(Uri.parse(String.valueOf(Artwork.urlWithSize$default(squareArtwork2, new Point(iApplyDimension, (int) TypedValue.applyDimension(1, 28.0f, resources2.getDisplayMetrics())), 0.0d, 2, null)))).setLogoContentDescription(((TVAiring) next2).getChannel().getTitle());
                                                    if (stillArtwork == null) {
                                                        Intrinsics.throwNpe();
                                                    }
                                                    Context applicationContext4 = getApplicationContext();
                                                    Intrinsics.checkExpressionValueIsNotNull(applicationContext4, "applicationContext");
                                                    Resources resources3 = applicationContext4.getResources();
                                                    Intrinsics.checkExpressionValueIsNotNull(resources3, str19);
                                                    int iApplyDimension2 = (int) TypedValue.applyDimension(1, 320.0f, resources3.getDisplayMetrics());
                                                    Context applicationContext5 = getApplicationContext();
                                                    Intrinsics.checkExpressionValueIsNotNull(applicationContext5, "applicationContext");
                                                    Resources resources4 = applicationContext5.getResources();
                                                    Intrinsics.checkExpressionValueIsNotNull(resources4, str19);
                                                    intentUri = ((PreviewProgram.Builder) ((PreviewProgram.Builder) logoContentDescription.setPosterArtUri(Uri.parse(String.valueOf(Artwork.urlWithSize$default(stillArtwork, new Point(iApplyDimension2, (int) TypedValue.applyDimension(1, 180.0f, resources4.getDisplayMetrics())), 0.0d, 2, null))))).setPreviewVideoUri(uriBuild2).setInternalProviderId(String.valueOf(((TVAiring) next2).getChannel().getId())).setInternalProviderFlag1(((TVAiring) next2).getStartDate().getTime())).setStartTimeUtcMillis(((TVAiring) next2).getStartDate().getTime()).setEndTimeUtcMillis(time).setDurationMillis((int) (time - ((TVAiring) next2).getStartDate().getTime())).setIntentUri(liveTvDeeplink3);
                                                    if (previewProgram2 != null) {
                                                        Log.v(this.TAG, "channel0: update program #" + previewProgram2.getId());
                                                        intentUri.setId(previewProgram2.getId());
                                                    } else {
                                                        Log.v(this.TAG, "channel0: create new program");
                                                    }
                                                    if (((TVAiring) next2).getMetadata().getRating() == null) {
                                                        rating = ((TVAiring) next2).getMetadata().getRating();
                                                        if (rating == null) {
                                                            Intrinsics.throwNpe();
                                                        }
                                                        intentUri.setReviewRating(String.valueOf(rating.doubleValue() * 1.25d));
                                                    }
                                                    parentalLevel = ((TVAiring) next2).getMetadata().getParentalLevel();
                                                    if (parentalLevel != null && (tvContentRating = parentalLevel.toTvContentRating()) != null) {
                                                    }
                                                    intentUri.setReleaseDate("");
                                                    intentUri.setSeasonNumber(null, -1);
                                                    intentUri.setEpisodeNumber(null, -1);
                                                    intentUri.setEpisodeTitle(null);
                                                    tVProgramBlockingGet = ((TVAiring) next2).getTvProgram().blockingGet();
                                                    if (tVProgramBlockingGet != null) {
                                                        intentUri.setDescription(tVProgramBlockingGet.getSummary());
                                                        productionYear = tVProgramBlockingGet.getMetadata().getProductionYear();
                                                        if (productionYear != null) {
                                                            iIntValue = productionYear.intValue();
                                                            if (iIntValue > 0) {
                                                                intentUri.setReleaseDate(String.valueOf(iIntValue));
                                                            }
                                                            Unit unit = Unit.INSTANCE;
                                                        }
                                                    }
                                                    Context applicationContext6 = getApplicationContext();
                                                    Intrinsics.checkExpressionValueIsNotNull(applicationContext6, "applicationContext");
                                                    stringArray = applicationContext6.getResources().getStringArray(R.array.genres_array);
                                                    str5 = ((TVAiring) next2).getMetadata().getGenres()[0];
                                                    if (!Intrinsics.areEqual(str5, stringArray[1]) || Intrinsics.areEqual(str5, stringArray[2])) {
                                                        intentUri.setType(0);
                                                    } else {
                                                        if (Intrinsics.areEqual(str5, stringArray[3]) || Intrinsics.areEqual(str5, stringArray[4])) {
                                                            intentUri.setType(3);
                                                            if (tVProgramBlockingGet != null) {
                                                                if (tVProgramBlockingGet.getSeasonNumber() != null && tVProgramBlockingGet.getEpisodeNumber() != null) {
                                                                    seasonNumber = tVProgramBlockingGet.getSeasonNumber();
                                                                    if (seasonNumber == null) {
                                                                        Intrinsics.throwNpe();
                                                                    }
                                                                    intentUri.setSeasonNumber(seasonNumber.intValue());
                                                                    episodeNumber = tVProgramBlockingGet.getEpisodeNumber();
                                                                    if (episodeNumber == null) {
                                                                        Intrinsics.throwNpe();
                                                                    }
                                                                    intentUri.setEpisodeNumber(episodeNumber.intValue());
                                                                }
                                                                episodeTitle = tVProgramBlockingGet.getEpisodeTitle();
                                                                if (episodeTitle != null) {
                                                                }
                                                            }
                                                        }
                                                        previewProgramBuild = intentUri.build();
                                                        str = str25;
                                                        j = j9;
                                                        str2 = str26;
                                                        j3 = j10;
                                                        str3 = str23;
                                                    }
                                                    previewProgramBuild = intentUri.build();
                                                    str = str25;
                                                    j = j9;
                                                    str2 = str26;
                                                    j3 = j10;
                                                    str3 = str23;
                                                }
                                                str18 = str4;
                                                PreviewProgram.Builder type4 = new PreviewProgram.Builder().setChannelId(j7).setType(5);
                                                StringBuilder sb4 = new StringBuilder();
                                                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                                                tVLauncherHelper2 = tVLauncherHelper6;
                                                long j11 = j7;
                                                String str28 = str17;
                                                String str29 = String.format(str28, Arrays.copyOf(new Object[]{Integer.valueOf(((TVAiring) next2).getChannel().getZappingNumber())}, 1));
                                                String str210 = str20;
                                                Intrinsics.checkNotNullExpressionValue(str29, str210);
                                                sb4.append(str29);
                                                String str211 = str16;
                                                sb4.append(str211);
                                                sb4.append(((TVAiring) next2).getChannel().getTitle());
                                                String str212 = str15;
                                                sb4.append(str212);
                                                str15 = str212;
                                                sb4.append(((TVAiring) next2).getTitle());
                                                PreviewProgram.Builder posterArtAspectRatio4 = ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) type4.setTitle(sb4.toString())).setDescription(((TVAiring) next2).getSummary())).setLive(true).setGenre(((TVAiring) next2).getMetadata().getGenres()[0]).setReviewRatingStyle(0)).setPosterArtAspectRatio(0);
                                                if (squareArtwork2 == null) {
                                                    Intrinsics.throwNpe();
                                                }
                                                Context applicationContext7 = getApplicationContext();
                                                Intrinsics.checkExpressionValueIsNotNull(applicationContext7, "applicationContext");
                                                Resources resources5 = applicationContext7.getResources();
                                                Intrinsics.checkExpressionValueIsNotNull(resources5, str19);
                                                int iApplyDimension3 = (int) TypedValue.applyDimension(1, 28.0f, resources5.getDisplayMetrics());
                                                Context applicationContext8 = getApplicationContext();
                                                Intrinsics.checkExpressionValueIsNotNull(applicationContext8, "applicationContext");
                                                Resources resources6 = applicationContext8.getResources();
                                                Intrinsics.checkExpressionValueIsNotNull(resources6, str19);
                                                list = list3;
                                                PreviewProgram.Builder logoContentDescription2 = posterArtAspectRatio4.setLogoUri(Uri.parse(String.valueOf(Artwork.urlWithSize$default(squareArtwork2, new Point(iApplyDimension3, (int) TypedValue.applyDimension(1, 28.0f, resources6.getDisplayMetrics())), 0.0d, 2, null)))).setLogoContentDescription(((TVAiring) next2).getChannel().getTitle());
                                                if (stillArtwork == null) {
                                                    Intrinsics.throwNpe();
                                                }
                                                Context applicationContext9 = getApplicationContext();
                                                Intrinsics.checkExpressionValueIsNotNull(applicationContext9, "applicationContext");
                                                Resources resources7 = applicationContext9.getResources();
                                                Intrinsics.checkExpressionValueIsNotNull(resources7, str19);
                                                int iApplyDimension4 = (int) TypedValue.applyDimension(1, 320.0f, resources7.getDisplayMetrics());
                                                Context applicationContext10 = getApplicationContext();
                                                Intrinsics.checkExpressionValueIsNotNull(applicationContext10, "applicationContext");
                                                Resources resources8 = applicationContext10.getResources();
                                                Intrinsics.checkExpressionValueIsNotNull(resources8, str19);
                                                intentUri = ((PreviewProgram.Builder) ((PreviewProgram.Builder) logoContentDescription2.setPosterArtUri(Uri.parse(String.valueOf(Artwork.urlWithSize$default(stillArtwork, new Point(iApplyDimension4, (int) TypedValue.applyDimension(1, 180.0f, resources8.getDisplayMetrics())), 0.0d, 2, null))))).setPreviewVideoUri(uriBuild2).setInternalProviderId(String.valueOf(((TVAiring) next2).getChannel().getId())).setInternalProviderFlag1(((TVAiring) next2).getStartDate().getTime())).setStartTimeUtcMillis(((TVAiring) next2).getStartDate().getTime()).setEndTimeUtcMillis(time).setDurationMillis((int) (time - ((TVAiring) next2).getStartDate().getTime())).setIntentUri(liveTvDeeplink3);
                                                if (previewProgram2 != null) {
                                                    Log.v(this.TAG, "channel0: update program #" + previewProgram2.getId());
                                                    intentUri.setId(previewProgram2.getId());
                                                } else {
                                                    Log.v(this.TAG, "channel0: create new program");
                                                }
                                                if (((TVAiring) next2).getMetadata().getRating() == null) {
                                                    rating = ((TVAiring) next2).getMetadata().getRating();
                                                    if (rating == null) {
                                                        Intrinsics.throwNpe();
                                                    }
                                                    intentUri.setReviewRating(String.valueOf(rating.doubleValue() * 1.25d));
                                                }
                                                parentalLevel = ((TVAiring) next2).getMetadata().getParentalLevel();
                                                if (parentalLevel != null) {
                                                }
                                                intentUri.setReleaseDate("");
                                                intentUri.setSeasonNumber(null, -1);
                                                intentUri.setEpisodeNumber(null, -1);
                                                intentUri.setEpisodeTitle(null);
                                                tVProgramBlockingGet = ((TVAiring) next2).getTvProgram().blockingGet();
                                                if (tVProgramBlockingGet != null) {
                                                    intentUri.setDescription(tVProgramBlockingGet.getSummary());
                                                    productionYear = tVProgramBlockingGet.getMetadata().getProductionYear();
                                                    if (productionYear != null) {
                                                        iIntValue = productionYear.intValue();
                                                        if (iIntValue > 0) {
                                                            intentUri.setReleaseDate(String.valueOf(iIntValue));
                                                        }
                                                        Unit unit2 = Unit.INSTANCE;
                                                    }
                                                }
                                                Context applicationContext11 = getApplicationContext();
                                                Intrinsics.checkExpressionValueIsNotNull(applicationContext11, "applicationContext");
                                                stringArray = applicationContext11.getResources().getStringArray(R.array.genres_array);
                                                str5 = ((TVAiring) next2).getMetadata().getGenres()[0];
                                                if (!Intrinsics.areEqual(str5, stringArray[1])) {
                                                    intentUri.setType(0);
                                                } else {
                                                    if (Intrinsics.areEqual(str5, stringArray[3])) {
                                                    }
                                                    intentUri.setType(3);
                                                    if (tVProgramBlockingGet != null) {
                                                        if (tVProgramBlockingGet.getSeasonNumber() != null) {
                                                            seasonNumber = tVProgramBlockingGet.getSeasonNumber();
                                                            if (seasonNumber == null) {
                                                                Intrinsics.throwNpe();
                                                            }
                                                            intentUri.setSeasonNumber(seasonNumber.intValue());
                                                            episodeNumber = tVProgramBlockingGet.getEpisodeNumber();
                                                            if (episodeNumber == null) {
                                                                Intrinsics.throwNpe();
                                                            }
                                                            intentUri.setEpisodeNumber(episodeNumber.intValue());
                                                        }
                                                        episodeTitle = tVProgramBlockingGet.getEpisodeTitle();
                                                        if (episodeTitle != null) {
                                                        }
                                                    }
                                                }
                                                previewProgramBuild = intentUri.build();
                                                str = str210;
                                                j = j9;
                                                str2 = str211;
                                                j3 = j11;
                                                str3 = str28;
                                            }
                                            tVLauncherHelper6 = tVLauncherHelper;
                                            str17 = str3;
                                            str16 = str2;
                                            j7 = j3;
                                            list3 = list;
                                            str19 = str19;
                                            it2 = it;
                                            str20 = str;
                                        }
                                        if (previewProgramBuild != null) {
                                            tVLauncherHelper = tVLauncherHelper2;
                                            Long.valueOf(tVLauncherHelper.setProgram(previewProgramBuild));
                                        } else {
                                            tVLauncherHelper = tVLauncherHelper2;
                                        }
                                        tVLauncherHelper6 = tVLauncherHelper;
                                        str17 = str3;
                                        str16 = str2;
                                        j7 = j3;
                                        list3 = list;
                                        str19 = str19;
                                        it2 = it;
                                        str20 = str;
                                    }
                                    j2 = j7;
                                } else {
                                    Log.v(this.TAG, "channel0: not browsable, skipping update");
                                    j2 = j7;
                                    j = Long.MAX_VALUE;
                                }
                                if (j == Long.MAX_VALUE) {
                                    durationOfMillis = Duration.ofMinutes(1L);
                                } else {
                                    durationOfMillis = Duration.ofMillis(Math.max(0L, j - System.currentTimeMillis()) + ((long) 2000));
                                }
                                Log.v(this.TAG, "channel0: schedule update in " + durationOfMillis);
                                TVLauncherUtils.scheduleSyncingPrograms(j2, durationOfMillis);
                                TVLauncherUtils.scheduleSyncingProgramsFast(j2, Duration.ofSeconds(1L));
                            }
                            break;
                        case 1432626062:
                            if (internalProviderId2.equals("channel1")) {
                                if (channel.isBrowsable() || programs.isEmpty()) {
                                    List<TVAiring> list4 = (List) CommonUtils.getCurrentLineup(bOService).retry().flatMapObservable(new Function<T, ObservableSource<? extends R>>() { // from class: com.dotscreen.tv.tvlauncher.SyncProgramsWorker$doWork$airings$3
                                        @Override // io.reactivex.functions.Function
                                        public final Observable<com.dotscreen.bokit.model.Channel> apply(Lineup it5) {
                                            Intrinsics.checkParameterIsNotNull(it5, "it");
                                            return it5.getItems();
                                        }
                                    }).flatMapMaybe(new Function<T, MaybeSource<? extends R>>() { // from class: com.dotscreen.tv.tvlauncher.SyncProgramsWorker$doWork$airings$4
                                        @Override // io.reactivex.functions.Function
                                        public final Maybe<TVAiring> apply(com.dotscreen.bokit.model.Channel it5) {
                                            Intrinsics.checkParameterIsNotNull(it5, "it");
                                            return it5.airing(TVAiringTimeOfDay.PrimeTime);
                                        }
                                    }).toList().blockingGet();
                                    ArrayList arrayList = new ArrayList();
                                    for (TVAiring tVAiring : list4) {
                                        if (tVAiring.getMetadata().getRating() != null) {
                                            arrayList.add(tVAiring);
                                        }
                                    }
                                    final Comparator comparatorReversed = new Comparator<T>() { // from class: com.dotscreen.tv.tvlauncher.SyncProgramsWorker$doWork$$inlined$compareBy$1
                                        /* JADX WARN: Multi-variable type inference failed */
                                        @Override // java.util.Comparator
                                        public final int compare(T t, T t2) {
                                            return ComparisonsKt.compareValues(((TVAiring) t).getMetadata().getRating(), ((TVAiring) t2).getMetadata().getRating());
                                        }
                                    }.reversed();
                                    Intrinsics.checkExpressionValueIsNotNull(comparatorReversed, "compareBy<TVAiring> { it…              .reversed()");
                                    CollectionsKt.sortWith(arrayList, new Comparator<T>() { // from class: com.dotscreen.tv.tvlauncher.SyncProgramsWorker$doWork$$inlined$thenBy$1
                                        /* JADX WARN: Multi-variable type inference failed */
                                        @Override // java.util.Comparator
                                        public final int compare(T t, T t2) {
                                            int iCompare = comparatorReversed.compare(t, t2);
                                            return iCompare != 0 ? iCompare : ComparisonsKt.compareValues(Integer.valueOf(((TVAiring) t).getChannel().getZappingNumber()), Integer.valueOf(((TVAiring) t2).getChannel().getZappingNumber()));
                                        }
                                    });
                                    Iterator it5 = arrayList.iterator();
                                    j4 = Long.MAX_VALUE;
                                    int i = 0;
                                    boolean z3 = false;
                                    while (it5.hasNext()) {
                                        TVAiring tVAiring2 = (TVAiring) it5.next();
                                        PreviewProgram previewProgram5 = i < programs.size() ? programs.get(i) : null;
                                        Iterator it6 = it5;
                                        String str30 = str8;
                                        long time3 = tVAiring2.getStartDate().getTime();
                                        List<PreviewProgram> list5 = programs;
                                        String str31 = str11;
                                        long time4 = tVAiring2.getEndDate().getTime();
                                        if (time3 > System.currentTimeMillis()) {
                                            if (time3 < j4) {
                                                j4 = time3;
                                            }
                                        } else if (time4 < j4) {
                                            j4 = time4;
                                        }
                                        Long internalProviderFlag2 = previewProgram5 != null ? previewProgram5.getInternalProviderFlag1() : null;
                                        if (internalProviderFlag2 == null || internalProviderFlag2.longValue() != time3 || previewProgram5 == null) {
                                            z2 = z3;
                                        } else {
                                            z2 = z3;
                                            if (previewProgram5.isLive() == tVAiring2.isLive()) {
                                                Log.v(this.TAG, "channel1: don't need to update program #" + previewProgram5.getId());
                                                i++;
                                                z3 = z2;
                                                str6 = str30;
                                                list2 = list5;
                                                str7 = str14;
                                                tVLauncherHelper3 = tVLauncherHelper5;
                                            }
                                            programs = list2;
                                            tVLauncherHelper5 = tVLauncherHelper3;
                                            str8 = str6;
                                            str12 = str12;
                                            it5 = it6;
                                            str11 = str31;
                                            str10 = str10;
                                            j6 = j6;
                                            str13 = str13;
                                            str14 = str7;
                                            str9 = str9;
                                        }
                                        boolean z4 = tVAiring2.isLive() ? true : z2;
                                        Artwork stillArtwork2 = tVAiring2.getStillArtwork();
                                        Artwork squareArtwork3 = tVAiring2.getChannel().getSquareArtwork();
                                        boolean z5 = z4;
                                        Uri liveTvDeeplink4 = CommonUtils.getLiveTvDeeplink(getApplicationContext(), tVAiring2.getChannel(), (Boolean) true);
                                        if (tVAiring2.isLive()) {
                                            uriBuild = (liveTvDeeplink4 == null || (builderBuildUpon2 = liveTvDeeplink4.buildUpon()) == null || (builderAppendQueryParameter = builderBuildUpon2.appendQueryParameter(str10, CommonUtils.getOTTLiveInputId())) == null) ? null : builderAppendQueryParameter.build();
                                        } else {
                                            uriBuild = Uri.parse("https://download.blender.org/durian/trailer/sintel_trailer-720p.mp4");
                                        }
                                        if (!tVAiring2.isLive()) {
                                            liveTvDeeplink4 = Uri.parse("megacable://com.dotscreen.megacable.portal/details?channel_id=" + tVAiring2.getChannel().getId() + "&start=" + time3);
                                        }
                                        PreviewProgram.Builder type5 = new PreviewProgram.Builder().setChannelId(j6).setType(5);
                                        StringBuilder sb5 = new StringBuilder();
                                        StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                                        String str32 = String.format(str13, Arrays.copyOf(new Object[]{Integer.valueOf(tVAiring2.getChannel().getZappingNumber())}, 1));
                                        Intrinsics.checkNotNullExpressionValue(str32, str12);
                                        sb5.append(str32);
                                        sb5.append(str31);
                                        sb5.append(tVAiring2.getChannel().getTitle());
                                        sb5.append(str9);
                                        sb5.append(tVAiring2.getTitle());
                                        PreviewProgram.Builder posterArtAspectRatio5 = ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) type5.setTitle(sb5.toString())).setDescription(tVAiring2.getSummary())).setLive(tVAiring2.isLive()).setGenre(tVAiring2.getMetadata().getGenres()[0]).setReviewRatingStyle(0)).setPosterArtAspectRatio(0);
                                        if (squareArtwork3 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        Context applicationContext12 = getApplicationContext();
                                        str6 = str30;
                                        Intrinsics.checkExpressionValueIsNotNull(applicationContext12, str6);
                                        Resources resources9 = applicationContext12.getResources();
                                        str7 = str14;
                                        Intrinsics.checkExpressionValueIsNotNull(resources9, str7);
                                        str31 = str31;
                                        int iApplyDimension5 = (int) TypedValue.applyDimension(1, 28.0f, resources9.getDisplayMetrics());
                                        Context applicationContext13 = getApplicationContext();
                                        Intrinsics.checkExpressionValueIsNotNull(applicationContext13, str6);
                                        Resources resources10 = applicationContext13.getResources();
                                        Intrinsics.checkExpressionValueIsNotNull(resources10, str7);
                                        PreviewProgram.Builder logoContentDescription3 = posterArtAspectRatio5.setLogoUri(Uri.parse(String.valueOf(Artwork.urlWithSize$default(squareArtwork3, new Point(iApplyDimension5, (int) TypedValue.applyDimension(1, 28.0f, resources10.getDisplayMetrics())), 0.0d, 2, null)))).setLogoContentDescription(tVAiring2.getChannel().getTitle());
                                        if (stillArtwork2 == null) {
                                            Intrinsics.throwNpe();
                                        }
                                        Context applicationContext14 = getApplicationContext();
                                        Intrinsics.checkExpressionValueIsNotNull(applicationContext14, str6);
                                        Resources resources11 = applicationContext14.getResources();
                                        Intrinsics.checkExpressionValueIsNotNull(resources11, str7);
                                        int iApplyDimension6 = (int) TypedValue.applyDimension(1, 320.0f, resources11.getDisplayMetrics());
                                        Context applicationContext15 = getApplicationContext();
                                        Intrinsics.checkExpressionValueIsNotNull(applicationContext15, str6);
                                        Resources resources12 = applicationContext15.getResources();
                                        Intrinsics.checkExpressionValueIsNotNull(resources12, str7);
                                        PreviewProgram.Builder intentUri2 = ((PreviewProgram.Builder) ((PreviewProgram.Builder) logoContentDescription3.setPosterArtUri(Uri.parse(String.valueOf(Artwork.urlWithSize$default(stillArtwork2, new Point(iApplyDimension6, (int) TypedValue.applyDimension(1, 180.0f, resources12.getDisplayMetrics())), 0.0d, 2, null))))).setInternalProviderId(tVAiring2.getChannel().getId()).setInternalProviderFlag1(time3)).setStartTimeUtcMillis(time3).setEndTimeUtcMillis(time4).setDurationMillis((int) (time4 - time3)).setPreviewVideoUri(uriBuild).setIntentUri(liveTvDeeplink4);
                                        if (i < list5.size()) {
                                            list2 = list5;
                                            intentUri2.setId(list2.get(i).getId());
                                            Log.v(this.TAG, "channel1: update program #" + list2.get(i).getId());
                                        } else {
                                            list2 = list5;
                                            Log.v(this.TAG, "channel1: create new program");
                                        }
                                        if (tVAiring2.getMetadata().getRating() != null) {
                                            Double rating2 = tVAiring2.getMetadata().getRating();
                                            if (rating2 == null) {
                                                Intrinsics.throwNpe();
                                            }
                                            intentUri2.setReviewRating(String.valueOf(rating2.doubleValue() * 1.25d));
                                        }
                                        ParentalRating parentalLevel2 = tVAiring2.getMetadata().getParentalLevel();
                                        if (parentalLevel2 != null && (tvContentRating2 = parentalLevel2.toTvContentRating()) != null) {
                                        }
                                        TVProgram tVProgramBlockingGet2 = tVAiring2.getTvProgram().retry().blockingGet();
                                        if (tVProgramBlockingGet2 != null) {
                                            intentUri2.setDescription(tVProgramBlockingGet2.getSummary());
                                            Integer productionYear2 = tVProgramBlockingGet2.getMetadata().getProductionYear();
                                            if (productionYear2 != null) {
                                                intentUri2.setReleaseDate(String.valueOf(productionYear2.intValue()));
                                            }
                                        }
                                        Context applicationContext16 = getApplicationContext();
                                        Intrinsics.checkExpressionValueIsNotNull(applicationContext16, str6);
                                        String[] stringArray2 = applicationContext16.getResources().getStringArray(R.array.genres_array);
                                        String str33 = tVAiring2.getMetadata().getGenres()[0];
                                        if (Intrinsics.areEqual(str33, stringArray2[1]) || Intrinsics.areEqual(str33, stringArray2[2])) {
                                            intentUri2.setType(0);
                                        } else if (Intrinsics.areEqual(str33, stringArray2[3]) || Intrinsics.areEqual(str33, stringArray2[4])) {
                                            intentUri2.setType(3);
                                            if (tVProgramBlockingGet2 != null) {
                                                if (tVProgramBlockingGet2.getSeasonNumber() != null && tVProgramBlockingGet2.getEpisodeNumber() != null) {
                                                    Integer seasonNumber2 = tVProgramBlockingGet2.getSeasonNumber();
                                                    if (seasonNumber2 == null) {
                                                        Intrinsics.throwNpe();
                                                    }
                                                    intentUri2.setSeasonNumber(seasonNumber2.intValue());
                                                    Integer episodeNumber2 = tVProgramBlockingGet2.getEpisodeNumber();
                                                    if (episodeNumber2 == null) {
                                                        Intrinsics.throwNpe();
                                                    }
                                                    intentUri2.setEpisodeNumber(episodeNumber2.intValue());
                                                }
                                                String episodeTitle2 = tVProgramBlockingGet2.getEpisodeTitle();
                                                if (episodeTitle2 != null) {
                                                }
                                            }
                                        }
                                        PreviewProgram previewProgramBuild2 = intentUri2.build();
                                        Intrinsics.checkExpressionValueIsNotNull(previewProgramBuild2, "builder.build()");
                                        tVLauncherHelper3 = tVLauncherHelper5;
                                        tVLauncherHelper3.setProgram(previewProgramBuild2);
                                        i++;
                                        z3 = z5;
                                        programs = list2;
                                        tVLauncherHelper5 = tVLauncherHelper3;
                                        str8 = str6;
                                        str12 = str12;
                                        it5 = it6;
                                        str11 = str31;
                                        str10 = str10;
                                        j6 = j6;
                                        str13 = str13;
                                        str14 = str7;
                                        str9 = str9;
                                    }
                                    j5 = j6;
                                    List<PreviewProgram> list6 = programs;
                                    boolean z6 = z3;
                                    TVLauncherHelper tVLauncherHelper7 = tVLauncherHelper5;
                                    while (i < list6.size()) {
                                        Log.v(this.TAG, "channel1: delete program #" + list6.get(i).getId());
                                        tVLauncherHelper7.removeProgram(list6.get(i).getId());
                                        i++;
                                    }
                                    z = z6;
                                } else {
                                    Log.v(this.TAG, "channel1: not browsable, skipping update");
                                    j5 = j6;
                                    j4 = Long.MAX_VALUE;
                                    z = false;
                                }
                                if (j4 == Long.MAX_VALUE) {
                                    durationOfMillis2 = Duration.ofMinutes(1L);
                                } else {
                                    durationOfMillis2 = Duration.ofMillis(Math.max(0L, j4 - System.currentTimeMillis()) + ((long) 2000));
                                }
                                Log.v(this.TAG, "channel1: schedule update in " + durationOfMillis2);
                                long j12 = j5;
                                TVLauncherUtils.scheduleSyncingPrograms(j12, durationOfMillis2);
                                if (z) {
                                    TVLauncherUtils.scheduleSyncingProgramsFast(j12, Duration.ofSeconds(1L));
                                } else {
                                    TVLauncherUtils.cancelScheduleSyncingProgramsFast(j12);
                                }
                            }
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ListenableWorker.Result resultSuccess = ListenableWorker.Result.success();
        Intrinsics.checkExpressionValueIsNotNull(resultSuccess, "Result.success()");
        return resultSuccess;
    }

    @Override // androidx.work.ListenableWorker
    public void onStopped() {
        super.onStopped();
    }
}
