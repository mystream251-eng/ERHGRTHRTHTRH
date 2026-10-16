package com.google.android.gms.tasks;

/* JADX INFO: compiled from: com.google.android.gms:play-services-tasks@@17.1.0 */
/* JADX INFO: loaded from: classes2.dex */
public final class DuplicateTaskCompletionException extends IllegalStateException {
    private DuplicateTaskCompletionException(String str, Throwable th) {
        super(str, th);
    }

    public static IllegalStateException of(Task<?> task) {
        String string;
        if (!task.isComplete()) {
            return new IllegalStateException("DuplicateTaskCompletionException can only be created from completed Task.");
        }
        Exception exception = task.getException();
        if (exception != null) {
            string = "failure";
        } else if (task.isSuccessful()) {
            String strValueOf = String.valueOf(task.getResult());
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 7);
            sb.append("result ");
            sb.append(strValueOf);
            string = sb.toString();
        } else {
            string = task.isCanceled() ? "cancellation" : "unknown issue";
        }
        String strValueOf2 = String.valueOf(string);
        return new DuplicateTaskCompletionException(strValueOf2.length() != 0 ? "Complete with: ".concat(strValueOf2) : new String("Complete with: "), exception);
    }
}
