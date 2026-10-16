package io.sentry;

import java.net.URI;

/* JADX INFO: loaded from: classes2.dex */
final class Dsn {
    private final String path;
    private final String projectId;
    private final String publicKey;
    private final String secretKey;
    private final URI sentryUri;

    public String getProjectId() {
        return this.projectId;
    }

    public String getPath() {
        return this.path;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    URI getSentryUri() {
        return this.sentryUri;
    }

    Dsn(String str) throws IllegalArgumentException {
        try {
            URI uriNormalize = new URI(str).normalize();
            String userInfo = uriNormalize.getUserInfo();
            if (userInfo == null || userInfo.isEmpty()) {
                throw new IllegalArgumentException("Invalid DSN: No public key provided.");
            }
            String[] strArrSplit = userInfo.split(":", -1);
            String str2 = strArrSplit[0];
            this.publicKey = str2;
            if (str2 == null || str2.isEmpty()) {
                throw new IllegalArgumentException("Invalid DSN: No public key provided.");
            }
            this.secretKey = strArrSplit.length > 1 ? strArrSplit[1] : null;
            String path = uriNormalize.getPath();
            path = path.endsWith("/") ? path.substring(0, path.length() - 1) : path;
            int iLastIndexOf = path.lastIndexOf("/") + 1;
            String strSubstring = path.substring(0, iLastIndexOf);
            if (!strSubstring.endsWith("/")) {
                strSubstring = strSubstring + "/";
            }
            this.path = strSubstring;
            String strSubstring2 = path.substring(iLastIndexOf);
            this.projectId = strSubstring2;
            if (strSubstring2.isEmpty()) {
                throw new IllegalArgumentException("Invalid DSN: A Project Id is required.");
            }
            this.sentryUri = new URI(uriNormalize.getScheme(), null, uriNormalize.getHost(), uriNormalize.getPort(), strSubstring + "api/" + this.projectId, null, null);
        } catch (Throwable th) {
            throw new IllegalArgumentException(th);
        }
    }
}
