package com.google.firebase.iid;

import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
final class zzah {
    private final Messenger zzad;
    private final zzk zzcb;

    zzah(IBinder iBinder) throws RemoteException {
        String interfaceDescriptor = iBinder.getInterfaceDescriptor();
        if ("android.os.IMessenger".equals(interfaceDescriptor)) {
            this.zzad = new Messenger(iBinder);
            this.zzcb = null;
        } else if ("com.google.android.gms.iid.IMessengerCompat".equals(interfaceDescriptor)) {
            this.zzcb = new zzk(iBinder);
            this.zzad = null;
        } else {
            String strValueOf = String.valueOf(interfaceDescriptor);
            Log.w("MessengerIpcClient", strValueOf.length() != 0 ? "Invalid interface descriptor: ".concat(strValueOf) : new String("Invalid interface descriptor: "));
            throw new RemoteException();
        }
    }

    final void send(Message message) throws RemoteException {
        if (this.zzad != null) {
            this.zzad.send(message);
        } else {
            if (this.zzcb != null) {
                this.zzcb.send(message);
                return;
            }
            throw new IllegalStateException("Both messengers are null");
        }
    }
}
