package rx.internal.util;

import rx.functions.Func1;

/* JADX INFO: loaded from: classes.dex */
public final class UtilityFunctions {
    private UtilityFunctions() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Func1<? super T, Boolean> alwaysTrue() {
        return AlwaysTrue.INSTANCE;
    }

    public static <T> Func1<? super T, Boolean> alwaysFalse() {
        return AlwaysFalse.INSTANCE;
    }

    public static <T> Func1<T, T> identity() {
        return Identity.INSTANCE;
    }

    enum AlwaysTrue implements Func1<Object, Boolean> {
        INSTANCE;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // rx.functions.Func1
        public Boolean call(Object o) {
            return true;
        }
    }

    enum AlwaysFalse implements Func1<Object, Boolean> {
        INSTANCE;

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // rx.functions.Func1
        public Boolean call(Object o) {
            return false;
        }
    }

    enum Identity implements Func1<Object, Object> {
        INSTANCE;

        @Override // rx.functions.Func1
        public Object call(Object o) {
            return o;
        }
    }
}
