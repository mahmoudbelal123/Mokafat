package com.google.firebase.messaging;

/* JADX INFO: loaded from: classes.dex */
public final class SendException extends Exception {
    public static final int ERROR_INVALID_PARAMETERS = 1;
    public static final int ERROR_SIZE = 2;
    public static final int ERROR_TOO_MANY_MESSAGES = 4;
    public static final int ERROR_TTL_EXCEEDED = 3;
    public static final int ERROR_UNKNOWN = 0;
    private final int errorCode;

    /* JADX WARN: Removed duplicated region for block: B:28:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    SendException(java.lang.String r8) {
        /*
            r7 = this;
            r7.<init>(r8)
            r0 = 3
            r1 = 4
            r2 = 0
            r3 = 2
            r4 = 1
            if (r8 == 0) goto L59
            java.util.Locale r5 = java.util.Locale.US
            java.lang.String r8 = r8.toLowerCase(r5)
            r5 = -1
            int r6 = r8.hashCode()
            switch(r6) {
                case -1743242157: goto L42;
                case -1290953729: goto L38;
                case -920906446: goto L2e;
                case -617027085: goto L24;
                case -95047692: goto L1a;
                default: goto L19;
            }
        L19:
            goto L4b
        L1a:
            java.lang.String r6 = "missing_to"
            boolean r8 = r8.equals(r6)
            if (r8 == 0) goto L4b
            r5 = r4
            goto L4b
        L24:
            java.lang.String r6 = "messagetoobig"
            boolean r8 = r8.equals(r6)
            if (r8 == 0) goto L4b
            r5 = r3
            goto L4b
        L2e:
            java.lang.String r6 = "invalid_parameters"
            boolean r8 = r8.equals(r6)
            if (r8 == 0) goto L4b
            r5 = r2
            goto L4b
        L38:
            java.lang.String r6 = "toomanymessages"
            boolean r8 = r8.equals(r6)
            if (r8 == 0) goto L4b
            r5 = r1
            goto L4b
        L42:
            java.lang.String r6 = "service_not_available"
            boolean r8 = r8.equals(r6)
            if (r8 == 0) goto L4b
            r5 = r0
        L4b:
            switch(r5) {
                case 0: goto L56;
                case 1: goto L56;
                case 2: goto L53;
                case 3: goto L52;
                case 4: goto L4f;
                default: goto L4e;
            }
        L4e:
            goto L59
        L4f:
            r0 = r1
            goto L5b
        L52:
            goto L5b
        L53:
            r0 = r3
            goto L5b
        L56:
            r0 = r4
            goto L5b
        L59:
            r0 = r2
        L5b:
            r7.errorCode = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.SendException.<init>(java.lang.String):void");
    }

    public final int getErrorCode() {
        return this.errorCode;
    }
}
