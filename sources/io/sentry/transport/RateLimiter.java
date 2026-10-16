package io.sentry.transport;

import androidx.core.os.EnvironmentCompat;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import io.sentry.ILogger;
import io.sentry.SentryEnvelope;
import io.sentry.SentryEnvelopeItem;
import io.sentry.SentryLevel;
import io.sentry.cache.EnvelopeCache;
import io.sentry.hints.Retryable;
import io.sentry.hints.SubmissionResult;
import io.sentry.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public final class RateLimiter {
    private static final int HTTP_RETRY_AFTER_DEFAULT_DELAY_MILLIS = 60000;
    private final ICurrentDateProvider currentDateProvider;
    private final ILogger logger;
    private final Map<DataCategory, Date> sentryRetryAfterLimit;

    private enum DataCategory {
        All("__all__"),
        Default("default"),
        Error("error"),
        Session(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE),
        Attachment("attachment"),
        Transaction("transaction"),
        Security("security"),
        Unknown(EnvironmentCompat.MEDIA_UNKNOWN);

        private final String category;

        DataCategory(String str) {
            this.category = str;
        }

        public String getCategory() {
            return this.category;
        }
    }

    public RateLimiter(ICurrentDateProvider iCurrentDateProvider, ILogger iLogger) {
        this.sentryRetryAfterLimit = new ConcurrentHashMap();
        this.currentDateProvider = iCurrentDateProvider;
        this.logger = iLogger;
    }

    public RateLimiter(ILogger iLogger) {
        this(CurrentDateProvider.getInstance(), iLogger);
    }

    public SentryEnvelope filter(SentryEnvelope sentryEnvelope, Object obj) {
        ArrayList arrayList = null;
        for (SentryEnvelopeItem sentryEnvelopeItem : sentryEnvelope.getItems()) {
            if (isRetryAfter(sentryEnvelopeItem.getHeader().getType().getItemType())) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(sentryEnvelopeItem);
            }
        }
        if (arrayList == null) {
            return sentryEnvelope;
        }
        this.logger.log(SentryLevel.INFO, "%d items will be dropped due rate limiting.", Integer.valueOf(arrayList.size()));
        ArrayList arrayList2 = new ArrayList();
        for (SentryEnvelopeItem sentryEnvelopeItem2 : sentryEnvelope.getItems()) {
            if (!arrayList.contains(sentryEnvelopeItem2)) {
                arrayList2.add(sentryEnvelopeItem2);
            }
        }
        if (arrayList2.isEmpty()) {
            this.logger.log(SentryLevel.INFO, "Envelope discarded due all items rate limited.", new Object[0]);
            markHintWhenSendingFailed(obj, false);
            return null;
        }
        return new SentryEnvelope(sentryEnvelope.getHeader(), arrayList2);
    }

    private static void markHintWhenSendingFailed(Object obj, boolean z) {
        if (obj instanceof SubmissionResult) {
            ((SubmissionResult) obj).setResult(false);
        }
        if (obj instanceof Retryable) {
            ((Retryable) obj).setRetry(z);
        }
    }

    private boolean isRetryAfter(String str) {
        Date date;
        DataCategory categoryFromItemType = getCategoryFromItemType(str);
        Date date2 = new Date(this.currentDateProvider.getCurrentTimeMillis());
        Date date3 = this.sentryRetryAfterLimit.get(DataCategory.All);
        if (date3 != null && !date2.after(date3)) {
            return true;
        }
        if (DataCategory.Unknown.equals(categoryFromItemType) || (date = this.sentryRetryAfterLimit.get(categoryFromItemType)) == null) {
            return false;
        }
        return !date2.after(date);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:17:0x0033  */
    private DataCategory getCategoryFromItemType(String str) {
        byte b;
        switch (str) {
            case "attachment":
                b = 2;
                break;
            case "event":
                b = 0;
                break;
            case "session":
                b = 1;
                break;
            case "transaction":
                b = 3;
                break;
            default:
                b = -1;
                break;
        }
        if (b == 0) {
            return DataCategory.Error;
        }
        if (b == 1) {
            return DataCategory.Session;
        }
        if (b == 2) {
            return DataCategory.Attachment;
        }
        if (b == 3) {
            return DataCategory.Transaction;
        }
        return DataCategory.Unknown;
    }

    public void updateRetryAfterLimits(String str, String str2, int i) {
        if (str == null) {
            if (i == 429) {
                applyRetryAfterOnlyIfLonger(DataCategory.All, new Date(this.currentDateProvider.getCurrentTimeMillis() + parseRetryAfterOrDefault(str2)));
                return;
            }
            return;
        }
        int i2 = -1;
        String[] strArrSplit = str.split(",", -1);
        int length = strArrSplit.length;
        int i3 = 0;
        while (i3 < length) {
            String[] strArrSplit2 = strArrSplit[i3].replace(" ", "").split(":", i2);
            if (strArrSplit2.length > 0) {
                long retryAfterOrDefault = parseRetryAfterOrDefault(strArrSplit2[0]);
                if (strArrSplit2.length > 1) {
                    String str3 = strArrSplit2[1];
                    Date date = new Date(this.currentDateProvider.getCurrentTimeMillis() + retryAfterOrDefault);
                    if (str3 != null && !str3.isEmpty()) {
                        for (String str4 : str3.split(";", i2)) {
                            DataCategory dataCategoryValueOf = DataCategory.Unknown;
                            try {
                                String strCapitalize = StringUtils.capitalize(str4);
                                if (strCapitalize != null) {
                                    dataCategoryValueOf = DataCategory.valueOf(strCapitalize);
                                } else {
                                    this.logger.log(SentryLevel.ERROR, "Couldn't capitalize: %s", str4);
                                }
                            } catch (IllegalArgumentException e) {
                                this.logger.log(SentryLevel.INFO, e, "Unknown category: %s", str4);
                            }
                            if (!DataCategory.Unknown.equals(dataCategoryValueOf)) {
                                applyRetryAfterOnlyIfLonger(dataCategoryValueOf, date);
                            }
                        }
                    } else {
                        applyRetryAfterOnlyIfLonger(DataCategory.All, date);
                    }
                }
            }
            i3++;
            i2 = -1;
        }
    }

    private void applyRetryAfterOnlyIfLonger(DataCategory dataCategory, Date date) {
        Date date2 = this.sentryRetryAfterLimit.get(dataCategory);
        if (date2 == null || date.after(date2)) {
            this.sentryRetryAfterLimit.put(dataCategory, date);
        }
    }

    private long parseRetryAfterOrDefault(String str) {
        if (str != null) {
            try {
                return (long) (Double.parseDouble(str) * 1000.0d);
            } catch (NumberFormatException unused) {
            }
        }
        return DefaultLoadErrorHandlingPolicy.DEFAULT_TRACK_BLACKLIST_MS;
    }
}
