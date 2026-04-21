package rx.exceptions;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import rx.plugins.RxJavaPlugins;

/* JADX INFO: loaded from: classes.dex */
public final class OnErrorThrowable extends RuntimeException {
    private static final long serialVersionUID = -569558213262703934L;
    private final boolean hasValue;
    private final Object value;

    private OnErrorThrowable(Throwable exception) {
        super(exception);
        this.hasValue = false;
        this.value = null;
    }

    private OnErrorThrowable(Throwable exception, Object value) {
        Object v;
        super(exception);
        this.hasValue = true;
        if (value instanceof Serializable) {
            v = value;
        } else {
            try {
                v = String.valueOf(value);
            } catch (Throwable ex) {
                v = ex.getMessage();
            }
        }
        this.value = v;
    }

    public Object getValue() {
        return this.value;
    }

    public boolean isValueNull() {
        return this.hasValue;
    }

    public static OnErrorThrowable from(Throwable t) {
        if (t == null) {
            t = new NullPointerException();
        }
        Throwable cause = Exceptions.getFinalCause(t);
        if (cause instanceof OnNextValue) {
            return new OnErrorThrowable(t, ((OnNextValue) cause).getValue());
        }
        return new OnErrorThrowable(t);
    }

    public static Throwable addValueAsLastCause(Throwable e, Object value) {
        if (e == null) {
            e = new NullPointerException();
        }
        Throwable lastCause = Exceptions.getFinalCause(e);
        if ((lastCause instanceof OnNextValue) && ((OnNextValue) lastCause).getValue() == value) {
            return e;
        }
        Exceptions.addCause(e, new OnNextValue(value));
        return e;
    }

    public static class OnNextValue extends RuntimeException {
        private static final long serialVersionUID = -3454462756050397899L;
        private final Object value;

        static final class Primitives {
            static final Set<Class<?>> INSTANCE = create();

            Primitives() {
            }

            private static Set<Class<?>> create() {
                Set<Class<?>> set = new HashSet<>();
                set.add(Boolean.class);
                set.add(Character.class);
                set.add(Byte.class);
                set.add(Short.class);
                set.add(Integer.class);
                set.add(Long.class);
                set.add(Float.class);
                set.add(Double.class);
                return set;
            }
        }

        public OnNextValue(Object value) {
            Object v;
            super("OnError while emitting onNext value: " + renderValue(value));
            if (value instanceof Serializable) {
                v = value;
            } else {
                try {
                    v = String.valueOf(value);
                } catch (Throwable ex) {
                    v = ex.getMessage();
                }
            }
            this.value = v;
        }

        public Object getValue() {
            return this.value;
        }

        static String renderValue(Object value) {
            if (value == null) {
                return "null";
            }
            if (Primitives.INSTANCE.contains(value.getClass())) {
                return value.toString();
            }
            if (value instanceof String) {
                return (String) value;
            }
            if (value instanceof Enum) {
                return ((Enum) value).name();
            }
            String pluggedRendering = RxJavaPlugins.getInstance().getErrorHandler().handleOnNextValueRendering(value);
            if (pluggedRendering != null) {
                return pluggedRendering;
            }
            return value.getClass().getName() + ".class";
        }
    }
}
