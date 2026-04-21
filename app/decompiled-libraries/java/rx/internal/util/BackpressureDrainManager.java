package rx.internal.util;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Producer;
import rx.annotations.Experimental;

/* JADX INFO: loaded from: classes.dex */
@Experimental
public final class BackpressureDrainManager extends AtomicLong implements Producer {
    private static final long serialVersionUID = 2826241102729529449L;
    final BackpressureQueueCallback actual;
    boolean emitting;
    Throwable exception;
    volatile boolean terminated;

    public interface BackpressureQueueCallback {
        boolean accept(Object obj);

        void complete(Throwable th);

        Object peek();

        Object poll();
    }

    public BackpressureDrainManager(BackpressureQueueCallback actual) {
        this.actual = actual;
    }

    public boolean isTerminated() {
        return this.terminated;
    }

    public void terminate() {
        this.terminated = true;
    }

    public void terminate(Throwable error) {
        if (!this.terminated) {
            this.exception = error;
            this.terminated = true;
        }
    }

    public void terminateAndDrain() throws Throwable {
        this.terminated = true;
        drain();
    }

    public void terminateAndDrain(Throwable error) throws Throwable {
        if (!this.terminated) {
            this.exception = error;
            this.terminated = true;
            drain();
        }
    }

    @Override // rx.Producer
    public void request(long n) throws Throwable {
        long r;
        boolean mayDrain;
        long u;
        if (n == 0) {
            return;
        }
        do {
            r = get();
            mayDrain = r == 0;
            if (r == LongCompanionObject.MAX_VALUE) {
                break;
            }
            if (n == LongCompanionObject.MAX_VALUE) {
                u = n;
                mayDrain = true;
            } else if (r > LongCompanionObject.MAX_VALUE - n) {
                u = LongCompanionObject.MAX_VALUE;
            } else {
                u = r + n;
            }
        } while (!compareAndSet(r, u));
        if (mayDrain) {
            drain();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0040 A[Catch: all -> 0x00a8, TRY_ENTER, TryCatch #6 {all -> 0x00a8, blocks: (B:36:0x0047, B:87:0x00a7, B:18:0x0023, B:20:0x0029, B:34:0x0040, B:90:0x00ab), top: B:133:0x0047 }] */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:84:0x00a4
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1182)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void drain() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: rx.internal.util.BackpressureDrainManager.drain():void");
    }
}
