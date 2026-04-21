package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
/* JADX INFO: loaded from: classes.dex */
final class zzxn<FieldDescriptorType> extends zzxm<FieldDescriptorType, Object> {
    zzxn(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.measurement.zzxm
    public final void zzsm() {
        if (!isImmutable()) {
            for (int i = 0; i < zzxw(); i++) {
                Map.Entry<FieldDescriptorType, Object> entryZzbu = zzbu(i);
                if (((zzvf) entryZzbu.getKey()).zzvy()) {
                    entryZzbu.setValue(Collections.unmodifiableList((List) entryZzbu.getValue()));
                }
            }
            for (Map.Entry<FieldDescriptorType, Object> entry : zzxx()) {
                if (((zzvf) entry.getKey()).zzvy()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzsm();
    }
}
