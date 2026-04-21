package butterknife.internal;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class DebouncingOnClickListener implements View.OnClickListener {
    static boolean enabled = true;
    private static final Runnable ENABLE_AGAIN = new Runnable() { // from class: butterknife.internal.DebouncingOnClickListener.1
        @Override // java.lang.Runnable
        public void run() {
            DebouncingOnClickListener.enabled = true;
        }
    };

    public abstract void doClick(View view);

    @Override // android.view.View.OnClickListener
    public final void onClick(View v) {
        if (enabled) {
            enabled = false;
            v.post(ENABLE_AGAIN);
            doClick(v);
        }
    }
}
