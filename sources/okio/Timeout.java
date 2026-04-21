package okio;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class Timeout {
    public static final Timeout NONE = new Timeout() { // from class: okio.Timeout.1
        @Override // okio.Timeout
        public Timeout timeout(long timeout, TimeUnit unit) {
            return this;
        }

        @Override // okio.Timeout
        public Timeout deadlineNanoTime(long deadlineNanoTime) {
            return this;
        }

        @Override // okio.Timeout
        public void throwIfReached() throws IOException {
        }
    };
    private long deadlineNanoTime;
    private boolean hasDeadline;
    private long timeoutNanos;

    public Timeout timeout(long timeout, TimeUnit unit) {
        if (timeout < 0) {
            throw new IllegalArgumentException("timeout < 0: " + timeout);
        }
        if (unit == null) {
            throw new IllegalArgumentException("unit == null");
        }
        this.timeoutNanos = unit.toNanos(timeout);
        return this;
    }

    public long timeoutNanos() {
        return this.timeoutNanos;
    }

    public boolean hasDeadline() {
        return this.hasDeadline;
    }

    public long deadlineNanoTime() {
        if (this.hasDeadline) {
            return this.deadlineNanoTime;
        }
        throw new IllegalStateException("No deadline");
    }

    public Timeout deadlineNanoTime(long deadlineNanoTime) {
        this.hasDeadline = true;
        this.deadlineNanoTime = deadlineNanoTime;
        return this;
    }

    public final Timeout deadline(long duration, TimeUnit unit) {
        if (duration > 0) {
            if (unit == null) {
                throw new IllegalArgumentException("unit == null");
            }
            return deadlineNanoTime(System.nanoTime() + unit.toNanos(duration));
        }
        throw new IllegalArgumentException("duration <= 0: " + duration);
    }

    public Timeout clearTimeout() {
        this.timeoutNanos = 0L;
        return this;
    }

    public Timeout clearDeadline() {
        this.hasDeadline = false;
        return this;
    }

    public void throwIfReached() throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        }
        if (this.hasDeadline && this.deadlineNanoTime - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public final void waitUntilNotified(Object monitor) throws InterruptedIOException {
        long waitNanos;
        try {
            boolean hasDeadline = hasDeadline();
            long timeoutNanos = timeoutNanos();
            if (!hasDeadline && timeoutNanos == 0) {
                monitor.wait();
                return;
            }
            long start = System.nanoTime();
            if (hasDeadline && timeoutNanos != 0) {
                long deadlineNanos = deadlineNanoTime() - start;
                waitNanos = Math.min(timeoutNanos, deadlineNanos);
            } else if (hasDeadline) {
                long waitNanos2 = deadlineNanoTime() - start;
                waitNanos = waitNanos2;
            } else {
                waitNanos = timeoutNanos;
            }
            long elapsedNanos = 0;
            if (waitNanos > 0) {
                long waitMillis = waitNanos / 1000000;
                try {
                    monitor.wait(waitMillis, (int) (waitNanos - (1000000 * waitMillis)));
                    elapsedNanos = System.nanoTime() - start;
                } catch (InterruptedException e) {
                    e = e;
                    throw new InterruptedIOException("interrupted");
                }
            }
            if (elapsedNanos >= waitNanos) {
                throw new InterruptedIOException("timeout");
            }
        } catch (InterruptedException e2) {
            e = e2;
        }
    }
}
