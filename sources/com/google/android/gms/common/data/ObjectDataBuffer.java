package com.google.android.gms.common.data;

import android.os.Bundle;
import com.google.android.gms.common.data.DataBufferObserver;
import com.google.android.gms.common.internal.Asserts;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class ObjectDataBuffer<T> extends AbstractDataBuffer<T> implements DataBufferObserver.Observable, ObjectExclusionFilterable<T> {
    private final ArrayList<Integer> zzob;
    private final HashSet<Integer> zzoe;
    private DataBufferObserverSet zzof;
    private final ArrayList<T> zzog;

    public ObjectDataBuffer() {
        super(null);
        this.zzoe = new HashSet<>();
        this.zzob = new ArrayList<>();
        this.zzog = new ArrayList<>();
        this.zzof = new DataBufferObserverSet();
        zzcl();
    }

    public ObjectDataBuffer(ArrayList<T> arrayList) {
        super(null);
        this.zzoe = new HashSet<>();
        this.zzob = new ArrayList<>();
        this.zzog = arrayList;
        this.zzof = new DataBufferObserverSet();
        zzcl();
    }

    public ObjectDataBuffer(T... tArr) {
        super(null);
        this.zzoe = new HashSet<>();
        this.zzob = new ArrayList<>();
        this.zzog = new ArrayList<>(Arrays.asList(tArr));
        this.zzof = new DataBufferObserverSet();
        zzcl();
    }

    private final void zzcl() {
        this.zzob.clear();
        int size = this.zzog.size();
        for (int i = 0; i < size; i++) {
            if (!this.zzoe.contains(Integer.valueOf(i))) {
                this.zzob.add(Integer.valueOf(i));
            }
        }
    }

    public final void add(T t) {
        int size = this.zzog.size();
        this.zzog.add(t);
        zzcl();
        if (this.zzof.hasObservers()) {
            Asserts.checkState(!this.zzoe.contains(Integer.valueOf(size)));
            int size2 = this.zzob.size();
            Asserts.checkState(size2 > 0);
            int i = size2 - 1;
            Asserts.checkState(this.zzob.get(i).intValue() == size);
            this.zzof.onDataRangeInserted(i, 1);
        }
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver.Observable
    public final void addObserver(DataBufferObserver dataBufferObserver) {
        this.zzof.addObserver(dataBufferObserver);
    }

    @Override // com.google.android.gms.common.data.ObjectExclusionFilterable
    public final void filterOut(T t) {
        int size = this.zzog.size();
        boolean z = false;
        int i = -1;
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 0; i4 < size; i4++) {
            if (!this.zzoe.contains(Integer.valueOf(i4))) {
                i++;
                if (t.equals(this.zzog.get(i4))) {
                    this.zzoe.add(Integer.valueOf(i4));
                    if (!this.zzof.hasObservers()) {
                        z = true;
                    } else if (i2 < 0) {
                        i2 = i;
                        z = true;
                        i3 = 1;
                    } else {
                        i3++;
                        z = true;
                    }
                } else if (i2 >= 0) {
                    zzcl();
                    this.zzof.onDataRangeRemoved(i2, i3);
                    i -= i3;
                    z = false;
                    i2 = -1;
                    i3 = -1;
                }
            }
        }
        if (z) {
            zzcl();
        }
        if (i2 >= 0) {
            this.zzof.onDataRangeRemoved(i2, i3);
        }
    }

    public final void filterOutRaw(int i) {
        int i2;
        boolean zAdd = this.zzoe.add(Integer.valueOf(i));
        if (this.zzof.hasObservers() && zAdd) {
            i2 = 0;
            int size = this.zzob.size();
            while (i2 < size) {
                if (this.zzob.get(i2).intValue() == i) {
                    break;
                } else {
                    i2++;
                }
            }
            i2 = -1;
        } else {
            i2 = -1;
        }
        zzcl();
        if (i2 >= 0) {
            this.zzof.onDataRangeRemoved(i2, 1);
        }
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final T get(int i) {
        return this.zzog.get(getRawPosition(i));
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final int getCount() {
        return this.zzog.size() - this.zzoe.size();
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer
    public final Bundle getMetadata() {
        return null;
    }

    public final int getPositionFromRawPosition(int i) {
        int i2 = -1;
        for (int i3 = 0; i3 <= i; i3++) {
            if (!this.zzoe.contains(Integer.valueOf(i3))) {
                i2++;
            }
        }
        return i2;
    }

    public final T getRaw(int i) {
        return this.zzog.get(i);
    }

    public final int getRawCount() {
        return this.zzog.size();
    }

    public final int getRawPosition(int i) {
        if (i >= 0 && i < getCount()) {
            return this.zzob.get(i).intValue();
        }
        StringBuilder sb = new StringBuilder(53);
        sb.append("Position ");
        sb.append(i);
        sb.append(" is out of bounds for this buffer");
        throw new IllegalArgumentException(sb.toString());
    }

    public final void insertRaw(int i, T t) {
        this.zzog.add(i, t);
        HashSet hashSet = new HashSet(this.zzoe.size());
        Iterator<Integer> it = this.zzoe.iterator();
        int i2 = i;
        while (it.hasNext()) {
            Integer next = it.next();
            if (next.intValue() < i) {
                i2--;
            } else {
                hashSet.add(Integer.valueOf(next.intValue() + 1));
                it.remove();
            }
        }
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            this.zzoe.add((Integer) it2.next());
        }
        zzcl();
        if (this.zzof.hasObservers()) {
            this.zzof.onDataRangeInserted(i2, 1);
        }
    }

    public final boolean isRawPositionFiltered(int i) {
        return this.zzoe.contains(Integer.valueOf(i));
    }

    public final void notifyChanged(T t) {
        if (this.zzof.hasObservers()) {
            int size = this.zzob.size();
            for (int i = 0; i < size; i++) {
                if (t.equals(this.zzog.get(this.zzob.get(i).intValue()))) {
                    this.zzof.onDataRangeChanged(i, 1);
                }
            }
        }
    }

    @Override // com.google.android.gms.common.data.AbstractDataBuffer, com.google.android.gms.common.data.DataBuffer, com.google.android.gms.common.api.Releasable
    public final void release() {
        this.zzof.clear();
    }

    @Override // com.google.android.gms.common.data.DataBufferObserver.Observable
    public final void removeObserver(DataBufferObserver dataBufferObserver) {
        this.zzof.removeObserver(dataBufferObserver);
    }

    public final void removeRaw(int i) {
        this.zzog.remove(i);
        boolean zRemove = this.zzoe.remove(Integer.valueOf(i));
        HashSet hashSet = new HashSet(this.zzoe.size());
        Iterator<Integer> it = this.zzoe.iterator();
        int i2 = i;
        while (it.hasNext()) {
            Integer next = it.next();
            if (next.intValue() < i) {
                i2--;
            } else {
                it.remove();
                hashSet.add(Integer.valueOf(next.intValue() - 1));
            }
        }
        Iterator it2 = hashSet.iterator();
        while (it2.hasNext()) {
            this.zzoe.add((Integer) it2.next());
        }
        zzcl();
        if (zRemove || !this.zzof.hasObservers()) {
            return;
        }
        this.zzof.onDataRangeRemoved(i2, 1);
    }

    public final boolean setRaw(int i, T t) {
        this.zzog.set(i, t);
        boolean z = !this.zzoe.contains(Integer.valueOf(i));
        if (z && this.zzof.hasObservers()) {
            int size = this.zzob.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (this.zzob.get(i2).intValue() == i) {
                    this.zzof.onDataRangeChanged(i2, 1);
                    return z;
                }
            }
        }
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0020 A[PHI: r5
      0x0020: PHI (r5v3 int) = (r5v1 int), (r5v5 int) binds: [B:19:0x005c, B:7:0x001e] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void unfilter(T r11) {
        /*
            r10 = this;
            java.util.ArrayList<T> r0 = r10.zzog
            int r0 = r0.size()
            r1 = 0
            r2 = -1
            r3 = r1
            r4 = r3
            r5 = r4
            r6 = r2
            r7 = r6
        Ld:
            if (r3 >= r0) goto L62
            java.util.HashSet<java.lang.Integer> r8 = r10.zzoe
            java.lang.Integer r9 = java.lang.Integer.valueOf(r3)
            boolean r8 = r8.contains(r9)
            r9 = 1
            if (r8 != 0) goto L2d
            int r5 = r5 + 1
            if (r6 < 0) goto L5f
        L20:
            r10.zzcl()
            com.google.android.gms.common.data.DataBufferObserverSet r4 = r10.zzof
            r4.onDataRangeInserted(r6, r7)
            int r5 = r5 + r7
            r4 = r1
            r6 = r2
            r7 = r6
            goto L5f
        L2d:
            java.util.ArrayList<T> r8 = r10.zzog
            java.lang.Object r8 = r8.get(r3)
            boolean r8 = r11.equals(r8)
            if (r8 == 0) goto L54
            java.util.HashSet<java.lang.Integer> r4 = r10.zzoe
            java.lang.Integer r8 = java.lang.Integer.valueOf(r3)
            r4.remove(r8)
            com.google.android.gms.common.data.DataBufferObserverSet r4 = r10.zzof
            boolean r4 = r4.hasObservers()
            if (r4 == 0) goto L52
            if (r6 >= 0) goto L50
            r6 = r5
            r4 = r9
            r7 = r4
            goto L5f
        L50:
            int r7 = r7 + 1
        L52:
            r4 = r9
            goto L5f
        L54:
            com.google.android.gms.common.data.DataBufferObserverSet r8 = r10.zzof
            boolean r8 = r8.hasObservers()
            if (r8 == 0) goto L5f
            if (r6 < 0) goto L5f
            goto L20
        L5f:
            int r3 = r3 + 1
            goto Ld
        L62:
            if (r4 == 0) goto L67
            r10.zzcl()
        L67:
            if (r6 < 0) goto L6e
            com.google.android.gms.common.data.DataBufferObserverSet r11 = r10.zzof
            r11.onDataRangeInserted(r6, r7)
        L6e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.data.ObjectDataBuffer.unfilter(java.lang.Object):void");
    }

    public final void unfilterRaw(int i) {
        boolean zRemove = this.zzoe.remove(Integer.valueOf(i));
        zzcl();
        if (this.zzof.hasObservers() && zRemove) {
            int i2 = -1;
            int i3 = 0;
            int size = this.zzob.size();
            while (true) {
                if (i3 >= size) {
                    break;
                }
                if (this.zzob.get(i3).intValue() == i) {
                    i2 = i3;
                    break;
                }
                i3++;
            }
            if (i2 >= 0) {
                this.zzof.onDataRangeInserted(i2, 1);
            }
        }
    }
}
