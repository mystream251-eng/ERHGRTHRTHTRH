package com.nytimes.android.external.cache;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
final class FuturesGetChecked {

    interface GetCheckedTypeValidator {
        void validateClass(Class<? extends Exception> cls);
    }

    static <V, X extends Exception> V getChecked(@Nonnull Future<V> future, Class<X> cls) throws Exception {
        return (V) getChecked(bestGetCheckedTypeValidator(), future, cls);
    }

    static <V, X extends Exception> V getChecked(@Nonnull GetCheckedTypeValidator getCheckedTypeValidator, @Nonnull Future<V> future, Class<X> cls) throws Exception {
        getCheckedTypeValidator.validateClass(cls);
        try {
            return future.get();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        } catch (ExecutionException unused2) {
            throw new RuntimeException();
        }
    }

    static <V, X extends Exception> V getChecked(@Nonnull Future<V> future, Class<X> cls, long j, @Nonnull TimeUnit timeUnit) throws Exception {
        bestGetCheckedTypeValidator().validateClass(cls);
        try {
            return future.get(j, timeUnit);
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new RuntimeException();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e2) {
            throw new RuntimeException(e2);
        }
    }

    @Nonnull
    private static GetCheckedTypeValidator bestGetCheckedTypeValidator() {
        return GetCheckedTypeValidatorHolder.BEST_VALIDATOR;
    }

    @Nonnull
    static GetCheckedTypeValidator weakSetValidator() {
        return GetCheckedTypeValidatorHolder.WeakSetValidator.INSTANCE;
    }

    private FuturesGetChecked() {
    }

    static class GetCheckedTypeValidatorHolder {
        static final String CLASS_VALUE_VALIDATOR_NAME = GetCheckedTypeValidatorHolder.class.getName() + "$ClassValueValidator";
        static final GetCheckedTypeValidator BEST_VALIDATOR = getBestValidator();

        GetCheckedTypeValidatorHolder() {
        }

        enum WeakSetValidator implements GetCheckedTypeValidator {
            INSTANCE;

            private static final Set<WeakReference<Class<? extends Exception>>> validClasses = new CopyOnWriteArraySet();

            @Override // com.nytimes.android.external.cache.FuturesGetChecked.GetCheckedTypeValidator
            public void validateClass(@Nonnull Class<? extends Exception> cls) {
                Iterator<WeakReference<Class<? extends Exception>>> it = validClasses.iterator();
                while (it.hasNext()) {
                    if (cls.equals(it.next().get())) {
                        return;
                    }
                }
                if (validClasses.size() > 1000) {
                    validClasses.clear();
                }
                validClasses.add(new WeakReference<>(cls));
            }
        }

        @Nonnull
        static GetCheckedTypeValidator getBestValidator() {
            try {
                return (GetCheckedTypeValidator) Class.forName(CLASS_VALUE_VALIDATOR_NAME).getEnumConstants()[0];
            } catch (Throwable unused) {
                return FuturesGetChecked.weakSetValidator();
            }
        }
    }
}
