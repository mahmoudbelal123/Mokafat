package com.google.android.gms.common.util;

import android.os.Binder;
import android.os.Process;
import android.os.StrictMode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public class ProcessUtils {
    private static String zzaai = null;
    private static int zzaaj = 0;

    public static class SystemGroupsNotAvailableException extends Exception {
        SystemGroupsNotAvailableException(String str) {
            super(str);
        }

        SystemGroupsNotAvailableException(String str, Throwable th) {
            super(str, th);
        }
    }

    private ProcessUtils() {
    }

    @Nullable
    public static String getCallingProcessName() {
        int callingPid = Binder.getCallingPid();
        return callingPid == zzde() ? getMyProcessName() : zzl(callingPid);
    }

    @Nullable
    public static String getMyProcessName() {
        if (zzaai == null) {
            zzaai = zzl(zzde());
        }
        return zzaai;
    }

    public static boolean hasSystemGroups() throws Throwable {
        BufferedReader bufferedReaderZzm;
        BufferedReader bufferedReader = null;
        try {
            try {
                int iZzde = zzde();
                StringBuilder sb = new StringBuilder(24);
                sb.append("/proc/");
                sb.append(iZzde);
                sb.append("/status");
                bufferedReaderZzm = zzm(sb.toString());
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e) {
            e = e;
        }
        try {
            boolean zZzk = zzk(bufferedReaderZzm);
            IOUtils.closeQuietly(bufferedReaderZzm);
            return zZzk;
        } catch (IOException e2) {
            e = e2;
            throw new SystemGroupsNotAvailableException("Unable to access /proc/pid/status.", e);
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = bufferedReaderZzm;
            IOUtils.closeQuietly(bufferedReader);
            throw th;
        }
    }

    private static int zzde() {
        if (zzaaj == 0) {
            zzaaj = Process.myPid();
        }
        return zzaaj;
    }

    private static boolean zzk(BufferedReader bufferedReader) throws IOException, SystemGroupsNotAvailableException {
        String strTrim;
        long j;
        do {
            String line = bufferedReader.readLine();
            if (line == null) {
                throw new SystemGroupsNotAvailableException("Missing Groups entry from proc/pid/status.");
            }
            strTrim = line.trim();
        } while (!strTrim.startsWith("Groups:"));
        for (String str : strTrim.substring(7).trim().split("\\s", -1)) {
            try {
                j = Long.parseLong(str);
            } catch (NumberFormatException e) {
            }
            if (j >= 1000 && j < 2000) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    private static String zzl(int i) throws Throwable {
        BufferedReader bufferedReaderZzm;
        BufferedReader bufferedReader = null;
        if (i <= 0) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder(25);
            sb.append("/proc/");
            sb.append(i);
            sb.append("/cmdline");
            bufferedReaderZzm = zzm(sb.toString());
        } catch (IOException e) {
            bufferedReaderZzm = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            String strTrim = bufferedReaderZzm.readLine().trim();
            IOUtils.closeQuietly(bufferedReaderZzm);
            return strTrim;
        } catch (IOException e2) {
            IOUtils.closeQuietly(bufferedReaderZzm);
            return null;
        } catch (Throwable th2) {
            bufferedReader = bufferedReaderZzm;
            th = th2;
            IOUtils.closeQuietly(bufferedReader);
            throw th;
        }
    }

    private static BufferedReader zzm(String str) throws IOException {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return new BufferedReader(new FileReader(str));
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }
}
