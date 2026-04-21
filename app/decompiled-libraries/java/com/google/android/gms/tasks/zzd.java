package com.google.android.gms.tasks;

/* JADX INFO: loaded from: classes.dex */
final class zzd implements Runnable {
    private final /* synthetic */ Task zzafn;
    private final /* synthetic */ zzc zzafo;

    zzd(zzc zzcVar, Task task) {
        this.zzafo = zzcVar;
        this.zzafn = task;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.zzafn.isCanceled()) {
            this.zzafo.zzafm.zzdp();
            return;
        }
        try {
            this.zzafo.zzafm.setResult(this.zzafo.zzafl.then(this.zzafn));
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                this.zzafo.zzafm.setException((Exception) e.getCause());
            } else {
                this.zzafo.zzafm.setException(e);
            }
        } catch (Exception e2) {
            this.zzafo.zzafm.setException(e2);
        }
    }
}
