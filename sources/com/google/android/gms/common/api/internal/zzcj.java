package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
final class zzcj extends Handler {
    private final /* synthetic */ zzch zzml;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzcj(zzch zzchVar, Looper looper) {
        super(looper);
        this.zzml = zzchVar;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                PendingResult<?> pendingResult = (PendingResult) message.obj;
                synchronized (this.zzml.zzfa) {
                    try {
                        if (pendingResult == null) {
                            this.zzml.zzme.zzd(new Status(13, "Transform returned null"));
                        } else if (pendingResult instanceof zzbx) {
                            this.zzml.zzme.zzd(((zzbx) pendingResult).getStatus());
                        } else {
                            this.zzml.zzme.zza(pendingResult);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
            case 1:
                RuntimeException runtimeException = (RuntimeException) message.obj;
                String strValueOf = String.valueOf(runtimeException.getMessage());
                Log.e("TransformedResultImpl", strValueOf.length() != 0 ? "Runtime exception on the transformation worker thread: ".concat(strValueOf) : new String("Runtime exception on the transformation worker thread: "));
                throw runtimeException;
            default:
                int i = message.what;
                StringBuilder sb = new StringBuilder(70);
                sb.append("TransformationResultHandler received unknown message type: ");
                sb.append(i);
                Log.e("TransformedResultImpl", sb.toString());
                return;
        }
    }
}
