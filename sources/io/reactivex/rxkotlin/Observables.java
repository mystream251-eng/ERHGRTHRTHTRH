package io.reactivex.rxkotlin;

import io.reactivex.Observable;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.functions.BiFunction;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.functions.Function6;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.functions.Function8;
import kotlin.jvm.functions.Function9;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Observables.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0084\u0001\u0010\u0003\u001aV\u0012$\u0012\"\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007 \b*\u0010\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\u00050\u0005 \b**\u0012$\u0012\"\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007 \b*\u0010\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\u00050\u0005\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0004H\u0007Jw\u0010\u0003\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\u001a\b\u0004\u0010\f\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000b0\rH\u0087\bJ°\u0001\u0010\u0003\u001an\u00120\u0012.\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f \b*\u0016\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0018\u00010\u000e0\u000e \b*6\u00120\u0012.\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f \b*\u0016\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0018\u00010\u000e0\u000e\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0004H\u0007J\u0091\u0001\u0010\u0003\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042 \b\u0004\u0010\f\u001a\u001a\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u000b0\u0011H\u0087\bJ«\u0001\u0010\u0003\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0012\"\u0004\b\u0004\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00042&\b\u0004\u0010\f\u001a \u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u000b0\u0014H\u0087\bJÅ\u0001\u0010\u0003\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0012\"\u0004\b\u0004\u0010\u0015\"\u0004\b\u0005\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00042\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00042,\b\u0004\u0010\f\u001a&\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u000b0\u0017H\u0087\bJß\u0001\u0010\u0003\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0012\"\u0004\b\u0004\u0010\u0015\"\u0004\b\u0005\u0010\u0018\"\u0004\b\u0006\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00042\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u000422\b\u0004\u0010\f\u001a,\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u000b0\u001aH\u0087\bJù\u0001\u0010\u0003\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0012\"\u0004\b\u0004\u0010\u0015\"\u0004\b\u0005\u0010\u0018\"\u0004\b\u0006\u0010\u001b\"\u0004\b\u0007\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00042\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u000428\b\u0004\u0010\f\u001a2\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u000b0\u001dH\u0087\bJ\u0093\u0002\u0010\u0003\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0012\"\u0004\b\u0004\u0010\u0015\"\u0004\b\u0005\u0010\u0018\"\u0004\b\u0006\u0010\u001b\"\u0004\b\u0007\u0010\u001e\"\u0004\b\b\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00042\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u00042\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001e0\u00042>\b\u0004\u0010\f\u001a8\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H\u000b0 H\u0087\bJ\u00ad\u0002\u0010\u0003\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0012\"\u0004\b\u0004\u0010\u0015\"\u0004\b\u0005\u0010\u0018\"\u0004\b\u0006\u0010\u001b\"\u0004\b\u0007\u0010\u001e\"\u0004\b\b\u0010!\"\u0004\b\t\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00042\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u00042\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001e0\u00042\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H!0\u00042D\b\u0004\u0010\f\u001a>\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H\u000b0#H\u0087\bJ\u0084\u0001\u0010$\u001aV\u0012$\u0012\"\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007 \b*\u0010\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\u00050\u0005 \b**\u0012$\u0012\"\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007 \b*\u0010\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\u00050\u0005\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0004H\u0007Jw\u0010$\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\u001a\b\u0004\u0010\f\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000b0\rH\u0087\bJ°\u0001\u0010$\u001an\u00120\u0012.\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f \b*\u0016\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0018\u00010\u000e0\u000e \b*6\u00120\u0012.\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f \b*\u0016\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0018\u00010\u000e0\u000e\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u0004H\u0007J\u0091\u0001\u0010$\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042 \b\u0004\u0010\f\u001a\u001a\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u000b0\u0011H\u0087\bJ«\u0001\u0010$\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0012\"\u0004\b\u0004\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00042&\b\u0004\u0010\f\u001a \u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u000b0\u0014H\u0087\bJÅ\u0001\u0010$\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0012\"\u0004\b\u0004\u0010\u0015\"\u0004\b\u0005\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00042\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00042,\b\u0004\u0010\f\u001a&\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u000b0\u0017H\u0087\bJß\u0001\u0010$\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0012\"\u0004\b\u0004\u0010\u0015\"\u0004\b\u0005\u0010\u0018\"\u0004\b\u0006\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00042\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u000422\b\u0004\u0010\f\u001a,\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u000b0\u001aH\u0087\bJù\u0001\u0010$\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0012\"\u0004\b\u0004\u0010\u0015\"\u0004\b\u0005\u0010\u0018\"\u0004\b\u0006\u0010\u001b\"\u0004\b\u0007\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00042\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u000428\b\u0004\u0010\f\u001a2\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u000b0\u001dH\u0087\bJ\u0093\u0002\u0010$\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0012\"\u0004\b\u0004\u0010\u0015\"\u0004\b\u0005\u0010\u0018\"\u0004\b\u0006\u0010\u001b\"\u0004\b\u0007\u0010\u001e\"\u0004\b\b\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00042\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u00042\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001e0\u00042>\b\u0004\u0010\f\u001a8\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H\u000b0 H\u0087\bJ\u00ad\u0002\u0010$\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u000bH\u000b\u0018\u00010\u00040\u0004\"\u0004\b\u0000\u0010\u0006\"\u0004\b\u0001\u0010\u0007\"\u0004\b\u0002\u0010\u000f\"\u0004\b\u0003\u0010\u0012\"\u0004\b\u0004\u0010\u0015\"\u0004\b\u0005\u0010\u0018\"\u0004\b\u0006\u0010\u001b\"\u0004\b\u0007\u0010\u001e\"\u0004\b\b\u0010!\"\u0004\b\t\u0010\u000b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u00042\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00070\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u000f0\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00042\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00150\u00042\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u00042\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u00042\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001e0\u00042\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H!0\u00042D\b\u0004\u0010\f\u001a>\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u0015\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001e\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u0002H\u000b0#H\u0087\b¨\u0006%"}, d2 = {"Lio/reactivex/rxkotlin/Observables;", "", "()V", "combineLatest", "Lio/reactivex/Observable;", "Lkotlin/Pair;", "T1", "T2", "kotlin.jvm.PlatformType", "source1", "source2", "R", "combineFunction", "Lkotlin/Function2;", "Lkotlin/Triple;", "T3", "source3", "Lkotlin/Function3;", "T4", "source4", "Lkotlin/Function4;", "T5", "source5", "Lkotlin/Function5;", "T6", "source6", "Lkotlin/Function6;", "T7", "source7", "Lkotlin/Function7;", "T8", "source8", "Lkotlin/Function8;", "T9", "source9", "Lkotlin/Function9;", "zip", "rxkotlin"}, k = 1, mv = {1, 1, 11})
public final class Observables {
    public static final Observables INSTANCE = new Observables();

    private Observables() {
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, R> Observable<R> combineLatest(Observable<T1> source1, Observable<T2> source2, final Function2<? super T1, ? super T2, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.combineLatest(source1, source2, new BiFunction<T1, T2, R>() { // from class: io.reactivex.rxkotlin.Observables.combineLatest.1
            @Override // io.reactivex.functions.BiFunction
            public final R apply(T1 t1, T2 t2) {
                return (R) combineFunction.invoke(t1, t2);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2> Observable<Pair<T1, T2>> combineLatest(Observable<T1> source1, Observable<T2> source2) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        return Observable.combineLatest(source1, source2, new BiFunction<T1, T2, Pair<? extends T1, ? extends T2>>() { // from class: io.reactivex.rxkotlin.Observables.combineLatest.2
            @Override // io.reactivex.functions.BiFunction
            public final Pair<T1, T2> apply(T1 t1, T2 t2) {
                return TuplesKt.to(t1, t2);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, R> Observable<R> combineLatest(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3, final Function3<? super T1, ? super T2, ? super T3, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.combineLatest(source1, source2, source3, new io.reactivex.functions.Function3<T1, T2, T3, R>() { // from class: io.reactivex.rxkotlin.Observables.combineLatest.3
            @Override // io.reactivex.functions.Function3
            public final R apply(T1 t1, T2 t2, T3 t3) {
                return (R) combineFunction.invoke(t1, t2, t3);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3> Observable<Triple<T1, T2, T3>> combineLatest(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        return Observable.combineLatest(source1, source2, source3, new io.reactivex.functions.Function3<T1, T2, T3, Triple<? extends T1, ? extends T2, ? extends T3>>() { // from class: io.reactivex.rxkotlin.Observables.combineLatest.4
            @Override // io.reactivex.functions.Function3
            public final Triple<T1, T2, T3> apply(T1 t1, T2 t2, T3 t3) {
                return new Triple<>(t1, t2, t3);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, R> Observable<R> combineLatest(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3, Observable<T4> source4, final Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        Intrinsics.checkParameterIsNotNull(source4, "source4");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.combineLatest(source1, source2, source3, source4, new io.reactivex.functions.Function4<T1, T2, T3, T4, R>() { // from class: io.reactivex.rxkotlin.Observables.combineLatest.5
            @Override // io.reactivex.functions.Function4
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4) {
                return (R) combineFunction.invoke(t1, t2, t3, t4);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, R> Observable<R> combineLatest(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3, Observable<T4> source4, Observable<T5> source5, final Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        Intrinsics.checkParameterIsNotNull(source4, "source4");
        Intrinsics.checkParameterIsNotNull(source5, "source5");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.combineLatest(source1, source2, source3, source4, source5, new io.reactivex.functions.Function5<T1, T2, T3, T4, T5, R>() { // from class: io.reactivex.rxkotlin.Observables.combineLatest.6
            @Override // io.reactivex.functions.Function5
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
                return (R) combineFunction.invoke(t1, t2, t3, t4, t5);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, T6, R> Observable<R> combineLatest(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3, Observable<T4> source4, Observable<T5> source5, Observable<T6> source6, final Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        Intrinsics.checkParameterIsNotNull(source4, "source4");
        Intrinsics.checkParameterIsNotNull(source5, "source5");
        Intrinsics.checkParameterIsNotNull(source6, "source6");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.combineLatest(source1, source2, source3, source4, source5, source6, new io.reactivex.functions.Function6<T1, T2, T3, T4, T5, T6, R>() { // from class: io.reactivex.rxkotlin.Observables.combineLatest.7
            @Override // io.reactivex.functions.Function6
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                return (R) combineFunction.invoke(t1, t2, t3, t4, t5, t6);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, T6, T7, R> Observable<R> combineLatest(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3, Observable<T4> source4, Observable<T5> source5, Observable<T6> source6, Observable<T7> source7, final Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        Intrinsics.checkParameterIsNotNull(source4, "source4");
        Intrinsics.checkParameterIsNotNull(source5, "source5");
        Intrinsics.checkParameterIsNotNull(source6, "source6");
        Intrinsics.checkParameterIsNotNull(source7, "source7");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.combineLatest(source1, source2, source3, source4, source5, source6, source7, new io.reactivex.functions.Function7<T1, T2, T3, T4, T5, T6, T7, R>() { // from class: io.reactivex.rxkotlin.Observables.combineLatest.8
            @Override // io.reactivex.functions.Function7
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
                return (R) combineFunction.invoke(t1, t2, t3, t4, t5, t6, t7);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<R> combineLatest(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3, Observable<T4> source4, Observable<T5> source5, Observable<T6> source6, Observable<T7> source7, Observable<T8> source8, final Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        Intrinsics.checkParameterIsNotNull(source4, "source4");
        Intrinsics.checkParameterIsNotNull(source5, "source5");
        Intrinsics.checkParameterIsNotNull(source6, "source6");
        Intrinsics.checkParameterIsNotNull(source7, "source7");
        Intrinsics.checkParameterIsNotNull(source8, "source8");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.combineLatest(source1, source2, source3, source4, source5, source6, source7, source8, new io.reactivex.functions.Function8<T1, T2, T3, T4, T5, T6, T7, T8, R>() { // from class: io.reactivex.rxkotlin.Observables.combineLatest.9
            @Override // io.reactivex.functions.Function8
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
                return (R) combineFunction.invoke(t1, t2, t3, t4, t5, t6, t7, t8);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Observable<R> combineLatest(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3, Observable<T4> source4, Observable<T5> source5, Observable<T6> source6, Observable<T7> source7, Observable<T8> source8, Observable<T9> source9, final Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        Intrinsics.checkParameterIsNotNull(source4, "source4");
        Intrinsics.checkParameterIsNotNull(source5, "source5");
        Intrinsics.checkParameterIsNotNull(source6, "source6");
        Intrinsics.checkParameterIsNotNull(source7, "source7");
        Intrinsics.checkParameterIsNotNull(source8, "source8");
        Intrinsics.checkParameterIsNotNull(source9, "source9");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.combineLatest(source1, source2, source3, source4, source5, source6, source7, source8, source9, new io.reactivex.functions.Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R>() { // from class: io.reactivex.rxkotlin.Observables.combineLatest.10
            @Override // io.reactivex.functions.Function9
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return (R) combineFunction.invoke(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, R> Observable<R> zip(Observable<T1> source1, Observable<T2> source2, final Function2<? super T1, ? super T2, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.zip(source1, source2, new BiFunction<T1, T2, R>() { // from class: io.reactivex.rxkotlin.Observables.zip.1
            @Override // io.reactivex.functions.BiFunction
            public final R apply(T1 t1, T2 t2) {
                return (R) combineFunction.invoke(t1, t2);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2> Observable<Pair<T1, T2>> zip(Observable<T1> source1, Observable<T2> source2) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        return Observable.zip(source1, source2, new BiFunction<T1, T2, Pair<? extends T1, ? extends T2>>() { // from class: io.reactivex.rxkotlin.Observables.zip.2
            @Override // io.reactivex.functions.BiFunction
            public final Pair<T1, T2> apply(T1 t1, T2 t2) {
                return TuplesKt.to(t1, t2);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, R> Observable<R> zip(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3, final Function3<? super T1, ? super T2, ? super T3, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.zip(source1, source2, source3, new io.reactivex.functions.Function3<T1, T2, T3, R>() { // from class: io.reactivex.rxkotlin.Observables.zip.3
            @Override // io.reactivex.functions.Function3
            public final R apply(T1 t1, T2 t2, T3 t3) {
                return (R) combineFunction.invoke(t1, t2, t3);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3> Observable<Triple<T1, T2, T3>> zip(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        return Observable.zip(source1, source2, source3, new io.reactivex.functions.Function3<T1, T2, T3, Triple<? extends T1, ? extends T2, ? extends T3>>() { // from class: io.reactivex.rxkotlin.Observables.zip.4
            @Override // io.reactivex.functions.Function3
            public final Triple<T1, T2, T3> apply(T1 t1, T2 t2, T3 t3) {
                return new Triple<>(t1, t2, t3);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, R> Observable<R> zip(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3, Observable<T4> source4, final Function4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        Intrinsics.checkParameterIsNotNull(source4, "source4");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.zip(source1, source2, source3, source4, new io.reactivex.functions.Function4<T1, T2, T3, T4, R>() { // from class: io.reactivex.rxkotlin.Observables.zip.5
            @Override // io.reactivex.functions.Function4
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4) {
                return (R) combineFunction.invoke(t1, t2, t3, t4);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, R> Observable<R> zip(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3, Observable<T4> source4, Observable<T5> source5, final Function5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        Intrinsics.checkParameterIsNotNull(source4, "source4");
        Intrinsics.checkParameterIsNotNull(source5, "source5");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.zip(source1, source2, source3, source4, source5, new io.reactivex.functions.Function5<T1, T2, T3, T4, T5, R>() { // from class: io.reactivex.rxkotlin.Observables.zip.6
            @Override // io.reactivex.functions.Function5
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
                return (R) combineFunction.invoke(t1, t2, t3, t4, t5);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, T6, R> Observable<R> zip(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3, Observable<T4> source4, Observable<T5> source5, Observable<T6> source6, final Function6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        Intrinsics.checkParameterIsNotNull(source4, "source4");
        Intrinsics.checkParameterIsNotNull(source5, "source5");
        Intrinsics.checkParameterIsNotNull(source6, "source6");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.zip(source1, source2, source3, source4, source5, source6, new io.reactivex.functions.Function6<T1, T2, T3, T4, T5, T6, R>() { // from class: io.reactivex.rxkotlin.Observables.zip.7
            @Override // io.reactivex.functions.Function6
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                return (R) combineFunction.invoke(t1, t2, t3, t4, t5, t6);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, T6, T7, R> Observable<R> zip(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3, Observable<T4> source4, Observable<T5> source5, Observable<T6> source6, Observable<T7> source7, final Function7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        Intrinsics.checkParameterIsNotNull(source4, "source4");
        Intrinsics.checkParameterIsNotNull(source5, "source5");
        Intrinsics.checkParameterIsNotNull(source6, "source6");
        Intrinsics.checkParameterIsNotNull(source7, "source7");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.zip(source1, source2, source3, source4, source5, source6, source7, new io.reactivex.functions.Function7<T1, T2, T3, T4, T5, T6, T7, R>() { // from class: io.reactivex.rxkotlin.Observables.zip.8
            @Override // io.reactivex.functions.Function7
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
                return (R) combineFunction.invoke(t1, t2, t3, t4, t5, t6, t7);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<R> zip(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3, Observable<T4> source4, Observable<T5> source5, Observable<T6> source6, Observable<T7> source7, Observable<T8> source8, final Function8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        Intrinsics.checkParameterIsNotNull(source4, "source4");
        Intrinsics.checkParameterIsNotNull(source5, "source5");
        Intrinsics.checkParameterIsNotNull(source6, "source6");
        Intrinsics.checkParameterIsNotNull(source7, "source7");
        Intrinsics.checkParameterIsNotNull(source8, "source8");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.zip(source1, source2, source3, source4, source5, source6, source7, source8, new io.reactivex.functions.Function8<T1, T2, T3, T4, T5, T6, T7, T8, R>() { // from class: io.reactivex.rxkotlin.Observables.zip.9
            @Override // io.reactivex.functions.Function8
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
                return (R) combineFunction.invoke(t1, t2, t3, t4, t5, t6, t7, t8);
            }
        });
    }

    @SchedulerSupport(SchedulerSupport.NONE)
    @CheckReturnValue
    public final <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Observable<R> zip(Observable<T1> source1, Observable<T2> source2, Observable<T3> source3, Observable<T4> source4, Observable<T5> source5, Observable<T6> source6, Observable<T7> source7, Observable<T8> source8, Observable<T9> source9, final Function9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> combineFunction) {
        Intrinsics.checkParameterIsNotNull(source1, "source1");
        Intrinsics.checkParameterIsNotNull(source2, "source2");
        Intrinsics.checkParameterIsNotNull(source3, "source3");
        Intrinsics.checkParameterIsNotNull(source4, "source4");
        Intrinsics.checkParameterIsNotNull(source5, "source5");
        Intrinsics.checkParameterIsNotNull(source6, "source6");
        Intrinsics.checkParameterIsNotNull(source7, "source7");
        Intrinsics.checkParameterIsNotNull(source8, "source8");
        Intrinsics.checkParameterIsNotNull(source9, "source9");
        Intrinsics.checkParameterIsNotNull(combineFunction, "combineFunction");
        return Observable.zip(source1, source2, source3, source4, source5, source6, source7, source8, source9, new io.reactivex.functions.Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R>() { // from class: io.reactivex.rxkotlin.Observables.zip.10
            @Override // io.reactivex.functions.Function9
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return (R) combineFunction.invoke(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        });
    }
}
