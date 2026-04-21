package com.google.firebase.iid;

import android.os.Build;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class zzk implements Parcelable {
    public static final Parcelable.Creator<zzk> CREATOR = new zzl();
    private Messenger zzad;
    private zzu zzae;

    public static final class zza extends ClassLoader {
        @Override // java.lang.ClassLoader
        protected final Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
            if (!"com.google.android.gms.iid.MessengerCompat".equals(str)) {
                return super.loadClass(str, z);
            }
            if (!FirebaseInstanceId.zzk()) {
                return zzk.class;
            }
            Log.d("FirebaseInstanceId", "Using renamed FirebaseIidMessengerCompat class");
            return zzk.class;
        }
    }

    public zzk(IBinder iBinder) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.zzad = new Messenger(iBinder);
        } else {
            this.zzae = new zzv(iBinder);
        }
    }

    public final void send(Message message) throws RemoteException {
        if (this.zzad != null) {
            this.zzad.send(message);
        } else {
            this.zzae.send(message);
        }
    }

    private final IBinder getBinder() {
        return this.zzad != null ? this.zzad.getBinder() : this.zzae.asBinder();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            return getBinder().equals(((zzk) obj).getBinder());
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        return getBinder().hashCode();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzad != null) {
            parcel.writeStrongBinder(this.zzad.getBinder());
        } else {
            parcel.writeStrongBinder(this.zzae.asBinder());
        }
    }
}
