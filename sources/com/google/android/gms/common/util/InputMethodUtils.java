package com.google.android.gms.common.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: loaded from: classes.dex */
public class InputMethodUtils {
    private InputMethodUtils() {
    }

    public static boolean hideSoftInput(Context context, View view) {
        InputMethodManager inputMethodManagerZzf = zzf(context);
        if (inputMethodManagerZzf == null) {
            return false;
        }
        inputMethodManagerZzf.hideSoftInputFromWindow(view.getWindowToken(), 0);
        return true;
    }

    public static boolean isAcceptingText(Context context) {
        InputMethodManager inputMethodManagerZzf = zzf(context);
        if (inputMethodManagerZzf != null) {
            return inputMethodManagerZzf.isAcceptingText();
        }
        return false;
    }

    public static void restart(Context context, View view) {
        InputMethodManager inputMethodManagerZzf = zzf(context);
        if (inputMethodManagerZzf != null) {
            inputMethodManagerZzf.restartInput(view);
        }
    }

    public static boolean showSoftInput(Context context, View view) {
        InputMethodManager inputMethodManagerZzf = zzf(context);
        if (inputMethodManagerZzf == null) {
            return false;
        }
        inputMethodManagerZzf.showSoftInput(view, 0);
        return true;
    }

    private static InputMethodManager zzf(Context context) {
        return (InputMethodManager) context.getSystemService("input_method");
    }
}
