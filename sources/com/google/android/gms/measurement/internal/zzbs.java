package com.google.android.gms.measurement.internal;

import android.os.Process;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.BlockingQueue;

/* JADX INFO: loaded from: classes.dex */
final class zzbs extends Thread {
    private final /* synthetic */ zzbo zzapg;
    private final Object zzapj;
    private final BlockingQueue<zzbr<?>> zzapk;

    public zzbs(zzbo zzboVar, String str, BlockingQueue<zzbr<?>> blockingQueue) {
        this.zzapg = zzboVar;
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(blockingQueue);
        this.zzapj = new Object();
        this.zzapk = blockingQueue;
        setName(str);
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        int i;
        boolean z = false;
        while (!z) {
            try {
                this.zzapg.zzapc.acquire();
                z = true;
            } catch (InterruptedException e) {
                zza(e);
            }
        }
        try {
            int threadPriority = Process.getThreadPriority(Process.myTid());
            while (true) {
                zzbr<?> zzbrVarPoll = this.zzapk.poll();
                if (zzbrVarPoll != null) {
                    if (!zzbrVarPoll.zzapi) {
                        i = 10;
                    } else {
                        i = threadPriority;
                    }
                    Process.setThreadPriority(i);
                    zzbrVarPoll.run();
                } else {
                    synchronized (this.zzapj) {
                        if (this.zzapk.peek() == null && !this.zzapg.zzapd) {
                            try {
                                this.zzapj.wait(30000L);
                            } catch (InterruptedException e2) {
                                zza(e2);
                            }
                        }
                    }
                    synchronized (this.zzapg.zzapb) {
                        if (this.zzapk.peek() == null) {
                            break;
                        }
                    }
                }
            }
            synchronized (this.zzapg.zzapb) {
                this.zzapg.zzapc.release();
                this.zzapg.zzapb.notifyAll();
                if (this == this.zzapg.zzaov) {
                    zzbo.zza(this.zzapg, null);
                } else if (this == this.zzapg.zzaow) {
                    zzbo.zzb(this.zzapg, null);
                } else {
                    this.zzapg.zzgo().zzjd().zzbx("Current scheduler thread is neither worker nor network");
                }
            }
        } catch (Throwable th) {
            synchronized (this.zzapg.zzapb) {
                this.zzapg.zzapc.release();
                this.zzapg.zzapb.notifyAll();
                if (this == this.zzapg.zzaov) {
                    zzbo.zza(this.zzapg, null);
                } else if (this == this.zzapg.zzaow) {
                    zzbo.zzb(this.zzapg, null);
                } else {
                    this.zzapg.zzgo().zzjd().zzbx("Current scheduler thread is neither worker nor network");
                }
                throw th;
            }
        }
    }

    public final void zzke() {
        synchronized (this.zzapj) {
            this.zzapj.notifyAll();
        }
    }

    private final void zza(InterruptedException interruptedException) {
        this.zzapg.zzgo().zzjg().zzg(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }
}
