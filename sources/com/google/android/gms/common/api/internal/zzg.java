package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes.dex */
public final class zzg extends zzc<Boolean> {
    private final ListenerHolder.ListenerKey<?> zzea;

    public zzg(ListenerHolder.ListenerKey<?> listenerKey, TaskCompletionSource<Boolean> taskCompletionSource) {
        super(4, taskCompletionSource);
        this.zzea = listenerKey;
    }

    @Override // com.google.android.gms.common.api.internal.zzc, com.google.android.gms.common.api.internal.zzb
    public final /* bridge */ /* synthetic */ void zza(@NonNull Status status) {
        super.zza(status);
    }

    @Override // com.google.android.gms.common.api.internal.zzc, com.google.android.gms.common.api.internal.zzb
    public final /* bridge */ /* synthetic */ void zza(@NonNull zzaa zzaaVar, boolean z) {
    }

    @Override // com.google.android.gms.common.api.internal.zzc, com.google.android.gms.common.api.internal.zzb
    public final /* bridge */ /* synthetic */ void zza(@NonNull RuntimeException runtimeException) {
        super.zza(runtimeException);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    /*  JADX ERROR: JadxRuntimeException in pass: FinishTypeInference
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r0v5 boolean
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.lambda$visit$0(FinishTypeInference.java:27)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.typeinference.FinishTypeInference.visit(FinishTypeInference.java:22)
        */
    @Override // com.google.android.gms.common.api.internal.zzc
    public final void zzb(com.google.android.gms.common.api.internal.GoogleApiManager.zza<?> r4) throws android.os.RemoteException {
        /*
            r3 = this;
            java.util.Map r0 = r4.zzbn()
            com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey<?> r1 = r3.zzea
            java.lang.Object r0 = r0.remove(r1)
            com.google.android.gms.common.api.internal.zzbv r0 = (com.google.android.gms.common.api.internal.zzbv) r0
            if (r0 == 0) goto L1f
            com.google.android.gms.common.api.internal.UnregisterListenerMethod<com.google.android.gms.common.api.Api$AnyClient, ?> r1 = r0.zzlu
            com.google.android.gms.common.api.Api$Client r4 = r4.zzae()
            com.google.android.gms.tasks.TaskCompletionSource<T> r2 = r3.zzdu
            r1.unregisterListener(r4, r2)
            com.google.android.gms.common.api.internal.RegisterListenerMethod<com.google.android.gms.common.api.Api$AnyClient, ?> r4 = r0.zzlt
            r4.clearListener()
            return
        L1f:
            com.google.android.gms.tasks.TaskCompletionSource<T> r4 = r3.zzdu
            r0 = 0
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r4.trySetResult(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzg.zzb(com.google.android.gms.common.api.internal.GoogleApiManager$zza):void");
    }
}
