package dagger.internal;

import dagger.MembersInjector;

/* JADX INFO: loaded from: classes.dex */
public final class MembersInjectors {
    public static <T> T injectMembers(MembersInjector<T> membersInjector, T instance) {
        membersInjector.injectMembers(instance);
        return instance;
    }

    public static <T> MembersInjector<T> noOp() {
        return NoOpMembersInjector.INSTANCE;
    }

    private enum NoOpMembersInjector implements MembersInjector<Object> {
        INSTANCE;

        @Override // dagger.MembersInjector
        public void injectMembers(Object instance) {
            Preconditions.checkNotNull(instance);
        }
    }

    public static <T> MembersInjector<T> delegatingTo(MembersInjector<? super T> delegate) {
        return (MembersInjector) Preconditions.checkNotNull(delegate);
    }

    private MembersInjectors() {
    }
}
