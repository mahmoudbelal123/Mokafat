package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.IAccountAccessor;

/* JADX INFO: loaded from: classes.dex */
public class AccountAccessor extends IAccountAccessor.Stub {
    private Context mContext;
    private int zzqu = -1;
    private Account zzs;

    public AccountAccessor(Context context, Account account) {
        this.mContext = context.getApplicationContext();
        this.zzs = account;
    }

    public static AccountAccessor fromGoogleAccountName(Context context, String str) {
        return new AccountAccessor(context, TextUtils.isEmpty(str) ? null : new Account(str, AccountType.GOOGLE));
    }

    public static Account getAccountBinderSafe(IAccountAccessor iAccountAccessor) {
        if (iAccountAccessor == null) {
            return null;
        }
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return iAccountAccessor.getAccount();
        } catch (RemoteException e) {
            Log.w("AccountAccessor", "Remote account accessor probably died");
            return null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AccountAccessor) {
            return this.zzs.equals(((AccountAccessor) obj).zzs);
        }
        return false;
    }

    @Override // com.google.android.gms.common.internal.IAccountAccessor
    public Account getAccount() {
        int callingUid = Binder.getCallingUid();
        if (callingUid == this.zzqu) {
            return this.zzs;
        }
        if (!GooglePlayServicesUtilLight.isGooglePlayServicesUid(this.mContext, callingUid)) {
            throw new SecurityException("Caller is not GooglePlayServices");
        }
        this.zzqu = callingUid;
        return this.zzs;
    }
}
