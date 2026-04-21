package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.sqlite.CursorWrapper;
import com.viewpagerindicator.BuildConfig;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@KeepName
@SafeParcelable.Class(creator = "DataHolderCreator", validate = BuildConfig.DEBUG)
public final class DataHolder extends AbstractSafeParcelable implements Closeable {
    public static final Parcelable.Creator<DataHolder> CREATOR = new DataHolderCreator();
    private static final Builder zznt = new com.google.android.gms.common.data.zza(new String[0], null);
    private boolean mClosed;

    @SafeParcelable.VersionField(id = 1000)
    private final int zzal;

    @SafeParcelable.Field(getter = "getStatusCode", id = 3)
    private final int zzam;

    @SafeParcelable.Field(getter = "getColumns", id = 1)
    private final String[] zznm;
    private Bundle zznn;

    @SafeParcelable.Field(getter = "getWindows", id = 2)
    private final CursorWindow[] zzno;

    @SafeParcelable.Field(getter = "getMetadata", id = 4)
    private final Bundle zznp;
    private int[] zznq;
    private int zznr;
    private boolean zzns;

    public static class Builder {
        private final String[] zznm;
        private final ArrayList<HashMap<String, Object>> zznu;
        private final String zznv;
        private final HashMap<Object, Integer> zznw;
        private boolean zznx;
        private String zzny;

        private Builder(String[] strArr, String str) {
            this.zznm = (String[]) Preconditions.checkNotNull(strArr);
            this.zznu = new ArrayList<>();
            this.zznv = str;
            this.zznw = new HashMap<>();
            this.zznx = false;
            this.zzny = null;
        }

        /* synthetic */ Builder(String[] strArr, String str, com.google.android.gms.common.data.zza zzaVar) {
            this(strArr, str);
        }

        private final boolean zzg(String str) {
            Asserts.checkNotNull(str);
            return this.zznx && str.equals(this.zzny);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public DataHolder build(int i) {
            return new DataHolder(this, i, (Bundle) null, (com.google.android.gms.common.data.zza) (0 == true ? 1 : 0));
        }

        public DataHolder build(int i, Bundle bundle) {
            return new DataHolder(this, i, bundle, -1, (com.google.android.gms.common.data.zza) null);
        }

        public DataHolder build(int i, Bundle bundle, int i2) {
            return new DataHolder(this, i, bundle, i2, (com.google.android.gms.common.data.zza) null);
        }

        public boolean containsRowWithValue(String str, Object obj) {
            int size = this.zznu.size();
            for (int i = 0; i < size; i++) {
                if (Objects.equal(this.zznu.get(i).get(str), obj)) {
                    return true;
                }
            }
            return false;
        }

        public Builder descendingSort(String str) {
            if (zzg(str)) {
                return this;
            }
            sort(str);
            Collections.reverse(this.zznu);
            return this;
        }

        public int getCount() {
            return this.zznu.size();
        }

        public void modifyUniqueRowValue(Object obj, String str, Object obj2) {
            Integer num;
            if (this.zznv == null || (num = this.zznw.get(obj)) == null) {
                return;
            }
            this.zznu.get(num.intValue()).put(str, obj2);
        }

        public Builder removeRowsWithValue(String str, Object obj) {
            for (int size = this.zznu.size() - 1; size >= 0; size--) {
                if (Objects.equal(this.zznu.get(size).get(str), obj)) {
                    this.zznu.remove(size);
                }
            }
            return this;
        }

        public Builder sort(String str) {
            if (zzg(str)) {
                return this;
            }
            Collections.sort(this.zznu, new zza(str));
            if (this.zznv != null) {
                this.zznw.clear();
                int size = this.zznu.size();
                for (int i = 0; i < size; i++) {
                    Object obj = this.zznu.get(i).get(this.zznv);
                    if (obj != null) {
                        this.zznw.put(obj, Integer.valueOf(i));
                    }
                }
            }
            this.zznx = true;
            this.zzny = str;
            return this;
        }

        public Builder withRow(ContentValues contentValues) {
            Asserts.checkNotNull(contentValues);
            HashMap<String, Object> map = new HashMap<>(contentValues.size());
            for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                map.put(entry.getKey(), entry.getValue());
            }
            return withRow(map);
        }

        public Builder withRow(HashMap<String, Object> map) {
            Object obj;
            int iIntValue;
            Asserts.checkNotNull(map);
            if (this.zznv == null || (obj = map.get(this.zznv)) == null) {
                iIntValue = -1;
            } else {
                Integer num = this.zznw.get(obj);
                if (num == null) {
                    this.zznw.put(obj, Integer.valueOf(this.zznu.size()));
                    iIntValue = -1;
                } else {
                    iIntValue = num.intValue();
                }
            }
            if (iIntValue == -1) {
                this.zznu.add(map);
            } else {
                this.zznu.remove(iIntValue);
                this.zznu.add(iIntValue, map);
            }
            this.zznx = false;
            return this;
        }
    }

    public static class DataHolderException extends RuntimeException {
        public DataHolderException(String str) {
            super(str);
        }
    }

    private static final class zza implements Comparator<HashMap<String, Object>> {
        private final String zznz;

        zza(String str) {
            this.zznz = (String) Preconditions.checkNotNull(str);
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(HashMap<String, Object> map, HashMap<String, Object> map2) {
            Object objCheckNotNull = Preconditions.checkNotNull(map.get(this.zznz));
            Object objCheckNotNull2 = Preconditions.checkNotNull(map2.get(this.zznz));
            if (objCheckNotNull.equals(objCheckNotNull2)) {
                return 0;
            }
            if (objCheckNotNull instanceof Boolean) {
                return ((Boolean) objCheckNotNull).compareTo((Boolean) objCheckNotNull2);
            }
            if (objCheckNotNull instanceof Long) {
                return ((Long) objCheckNotNull).compareTo((Long) objCheckNotNull2);
            }
            if (objCheckNotNull instanceof Integer) {
                return ((Integer) objCheckNotNull).compareTo((Integer) objCheckNotNull2);
            }
            if (objCheckNotNull instanceof String) {
                return ((String) objCheckNotNull).compareTo((String) objCheckNotNull2);
            }
            if (objCheckNotNull instanceof Double) {
                return ((Double) objCheckNotNull).compareTo((Double) objCheckNotNull2);
            }
            if (objCheckNotNull instanceof Float) {
                return ((Float) objCheckNotNull).compareTo((Float) objCheckNotNull2);
            }
            String strValueOf = String.valueOf(objCheckNotNull);
            StringBuilder sb = new StringBuilder(24 + String.valueOf(strValueOf).length());
            sb.append("Unknown type for lValue ");
            sb.append(strValueOf);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    @SafeParcelable.Constructor
    DataHolder(@SafeParcelable.Param(id = 1000) int i, @SafeParcelable.Param(id = 1) String[] strArr, @SafeParcelable.Param(id = 2) CursorWindow[] cursorWindowArr, @SafeParcelable.Param(id = 3) int i2, @SafeParcelable.Param(id = 4) Bundle bundle) {
        this.mClosed = false;
        this.zzns = true;
        this.zzal = i;
        this.zznm = strArr;
        this.zzno = cursorWindowArr;
        this.zzam = i2;
        this.zznp = bundle;
    }

    public DataHolder(Cursor cursor, int i, Bundle bundle) {
        this(new CursorWrapper(cursor), i, bundle);
    }

    private DataHolder(Builder builder, int i, Bundle bundle) {
        this(builder.zznm, zza(builder, -1), i, bundle);
    }

    private DataHolder(Builder builder, int i, Bundle bundle, int i2) {
        this(builder.zznm, zza(builder, i2), i, bundle);
    }

    /* synthetic */ DataHolder(Builder builder, int i, Bundle bundle, int i2, com.google.android.gms.common.data.zza zzaVar) {
        this(builder, i, bundle, i2);
    }

    /* synthetic */ DataHolder(Builder builder, int i, Bundle bundle, com.google.android.gms.common.data.zza zzaVar) {
        this(builder, i, (Bundle) null);
    }

    public DataHolder(CursorWrapper cursorWrapper, int i, Bundle bundle) {
        this(cursorWrapper.getColumnNames(), zza(cursorWrapper), i, bundle);
    }

    public DataHolder(String[] strArr, CursorWindow[] cursorWindowArr, int i, Bundle bundle) {
        this.mClosed = false;
        this.zzns = true;
        this.zzal = 1;
        this.zznm = (String[]) Preconditions.checkNotNull(strArr);
        this.zzno = (CursorWindow[]) Preconditions.checkNotNull(cursorWindowArr);
        this.zzam = i;
        this.zznp = bundle;
        validateContents();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Builder builder(String[] strArr) {
        return new Builder(strArr, null, 0 == true ? 1 : 0);
    }

    public static Builder builder(String[] strArr, String str) {
        Preconditions.checkNotNull(str);
        return new Builder(strArr, str, null);
    }

    public static DataHolder empty(int i) {
        return empty(i, null);
    }

    public static DataHolder empty(int i, Bundle bundle) {
        return new DataHolder(zznt, i, bundle);
    }

    private final void zza(String str, int i) {
        if (this.zznn == null || !this.zznn.containsKey(str)) {
            String strValueOf = String.valueOf(str);
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "No such column: ".concat(strValueOf) : new String("No such column: "));
        }
        if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (i < 0 || i >= this.zznr) {
            throw new CursorIndexOutOfBoundsException(i, this.zznr);
        }
    }

    private static CursorWindow[] zza(Builder builder, int i) {
        long jLongValue;
        if (builder.zznm.length == 0) {
            return new CursorWindow[0];
        }
        List listSubList = (i < 0 || i >= builder.zznu.size()) ? builder.zznu : builder.zznu.subList(0, i);
        int size = listSubList.size();
        CursorWindow cursorWindow = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow);
        cursorWindow.setNumColumns(builder.zznm.length);
        boolean z = false;
        CursorWindow cursorWindow2 = cursorWindow;
        int i2 = 0;
        while (i2 < size) {
            try {
                if (!cursorWindow2.allocRow()) {
                    StringBuilder sb = new StringBuilder(72);
                    sb.append("Allocating additional cursor window for large data set (row ");
                    sb.append(i2);
                    sb.append(")");
                    Log.d("DataHolder", sb.toString());
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i2);
                    cursorWindow2.setNumColumns(builder.zznm.length);
                    arrayList.add(cursorWindow2);
                    if (!cursorWindow2.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow2);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                Map map = (Map) listSubList.get(i2);
                boolean zPutDouble = true;
                for (int i3 = 0; i3 < builder.zznm.length && zPutDouble; i3++) {
                    String str = builder.zznm[i3];
                    Object obj = map.get(str);
                    if (obj == null) {
                        zPutDouble = cursorWindow2.putNull(i2, i3);
                    } else if (obj instanceof String) {
                        zPutDouble = cursorWindow2.putString((String) obj, i2, i3);
                    } else {
                        if (obj instanceof Long) {
                            jLongValue = ((Long) obj).longValue();
                        } else if (obj instanceof Integer) {
                            zPutDouble = cursorWindow2.putLong(((Integer) obj).intValue(), i2, i3);
                        } else if (obj instanceof Boolean) {
                            jLongValue = ((Boolean) obj).booleanValue() ? 1L : 0L;
                        } else if (obj instanceof byte[]) {
                            zPutDouble = cursorWindow2.putBlob((byte[]) obj, i2, i3);
                        } else if (obj instanceof Double) {
                            zPutDouble = cursorWindow2.putDouble(((Double) obj).doubleValue(), i2, i3);
                        } else {
                            if (!(obj instanceof Float)) {
                                String strValueOf = String.valueOf(obj);
                                StringBuilder sb2 = new StringBuilder(32 + String.valueOf(str).length() + String.valueOf(strValueOf).length());
                                sb2.append("Unsupported object for column ");
                                sb2.append(str);
                                sb2.append(": ");
                                sb2.append(strValueOf);
                                throw new IllegalArgumentException(sb2.toString());
                            }
                            zPutDouble = cursorWindow2.putDouble(((Float) obj).floatValue(), i2, i3);
                        }
                        zPutDouble = cursorWindow2.putLong(jLongValue, i2, i3);
                    }
                }
                if (zPutDouble) {
                    z = false;
                } else {
                    if (z) {
                        throw new DataHolderException("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                    }
                    StringBuilder sb3 = new StringBuilder(74);
                    sb3.append("Couldn't populate window data for row ");
                    sb3.append(i2);
                    sb3.append(" - allocating new window.");
                    Log.d("DataHolder", sb3.toString());
                    cursorWindow2.freeLastRow();
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i2);
                    cursorWindow2.setNumColumns(builder.zznm.length);
                    arrayList.add(cursorWindow2);
                    i2--;
                    z = true;
                }
                i2++;
            } catch (RuntimeException e) {
                int size2 = arrayList.size();
                for (int i4 = 0; i4 < size2; i4++) {
                    ((CursorWindow) arrayList.get(i4)).close();
                }
                throw e;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    private static CursorWindow[] zza(CursorWrapper cursorWrapper) {
        int startPosition;
        ArrayList arrayList = new ArrayList();
        try {
            int count = cursorWrapper.getCount();
            CursorWindow window = cursorWrapper.getWindow();
            if (window == null || window.getStartPosition() != 0) {
                startPosition = 0;
            } else {
                window.acquireReference();
                cursorWrapper.setWindow(null);
                arrayList.add(window);
                startPosition = window.getNumRows();
            }
            while (startPosition < count) {
                if (!cursorWrapper.moveToPosition(startPosition)) {
                    break;
                }
                CursorWindow window2 = cursorWrapper.getWindow();
                if (window2 != null) {
                    window2.acquireReference();
                    cursorWrapper.setWindow(null);
                } else {
                    window2 = new CursorWindow(false);
                    window2.setStartPosition(startPosition);
                    cursorWrapper.fillWindow(startPosition, window2);
                }
                if (window2.getNumRows() == 0) {
                    break;
                }
                arrayList.add(window2);
                startPosition = window2.getStartPosition() + window2.getNumRows();
            }
            cursorWrapper.close();
            return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
        } catch (Throwable th) {
            cursorWrapper.close();
            throw th;
        }
    }

    public final void clearColumn(String str, int i, int i2) {
        zza(str, i);
        this.zzno[i2].putNull(i, this.zznn.getInt(str));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (int i = 0; i < this.zzno.length; i++) {
                    this.zzno[i].close();
                }
            }
        }
    }

    public final void copyToBuffer(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zza(str, i);
        this.zzno[i2].copyStringToBuffer(i, this.zznn.getInt(str), charArrayBuffer);
    }

    public final void disableLeakDetection() {
        this.zzns = false;
    }

    protected final void finalize() throws Throwable {
        try {
            if (this.zzns && this.zzno.length > 0 && !isClosed()) {
                close();
                String string = toString();
                StringBuilder sb = new StringBuilder(178 + String.valueOf(string).length());
                sb.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
                sb.append(string);
                sb.append(")");
                Log.e("DataBuffer", sb.toString());
            }
        } finally {
            super.finalize();
        }
    }

    public final boolean getBoolean(String str, int i, int i2) {
        zza(str, i);
        return Long.valueOf(this.zzno[i2].getLong(i, this.zznn.getInt(str))).longValue() == 1;
    }

    public final byte[] getByteArray(String str, int i, int i2) {
        zza(str, i);
        return this.zzno[i2].getBlob(i, this.zznn.getInt(str));
    }

    public final int getCount() {
        return this.zznr;
    }

    public final double getDouble(String str, int i, int i2) {
        zza(str, i);
        return this.zzno[i2].getDouble(i, this.zznn.getInt(str));
    }

    public final float getFloat(String str, int i, int i2) {
        zza(str, i);
        return this.zzno[i2].getFloat(i, this.zznn.getInt(str));
    }

    public final int getInteger(String str, int i, int i2) {
        zza(str, i);
        return this.zzno[i2].getInt(i, this.zznn.getInt(str));
    }

    public final long getLong(String str, int i, int i2) {
        zza(str, i);
        return this.zzno[i2].getLong(i, this.zznn.getInt(str));
    }

    public final Bundle getMetadata() {
        return this.zznp;
    }

    public final int getStatusCode() {
        return this.zzam;
    }

    public final String getString(String str, int i, int i2) {
        zza(str, i);
        return this.zzno[i2].getString(i, this.zznn.getInt(str));
    }

    public final int getWindowIndex(int i) {
        int i2 = 0;
        Preconditions.checkState(i >= 0 && i < this.zznr);
        while (true) {
            if (i2 >= this.zznq.length) {
                break;
            }
            if (i < this.zznq[i2]) {
                i2--;
                break;
            }
            i2++;
        }
        return i2 == this.zznq.length ? i2 - 1 : i2;
    }

    public final boolean hasColumn(String str) {
        return this.zznn.containsKey(str);
    }

    public final boolean hasNull(String str, int i, int i2) {
        zza(str, i);
        return this.zzno[i2].isNull(i, this.zznn.getInt(str));
    }

    public final boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    public final void logCursorMetadataForDebugging() {
        Log.d("DataHolder", "*******************************************");
        int length = this.zzno.length;
        StringBuilder sb = new StringBuilder(32);
        sb.append("num cursor windows : ");
        sb.append(length);
        Log.d("DataHolder", sb.toString());
        int i = this.zznr;
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append("total number of objects in holder: ");
        sb2.append(i);
        Log.d("DataHolder", sb2.toString());
        int length2 = this.zznq.length;
        StringBuilder sb3 = new StringBuilder(42);
        sb3.append("total mumber of windowOffsets: ");
        sb3.append(length2);
        Log.d("DataHolder", sb3.toString());
        for (int i2 = 0; i2 < this.zznq.length; i2++) {
            int i3 = this.zznq[i2];
            StringBuilder sb4 = new StringBuilder(43);
            sb4.append("offset for window ");
            sb4.append(i2);
            sb4.append(" : ");
            sb4.append(i3);
            Log.d("DataHolder", sb4.toString());
            int numRows = this.zzno[i2].getNumRows();
            StringBuilder sb5 = new StringBuilder(45);
            sb5.append("num rows for window ");
            sb5.append(i2);
            sb5.append(" : ");
            sb5.append(numRows);
            Log.d("DataHolder", sb5.toString());
            int startPosition = this.zzno[i2].getStartPosition();
            StringBuilder sb6 = new StringBuilder(46);
            sb6.append("start pos for window ");
            sb6.append(i2);
            sb6.append(" : ");
            sb6.append(startPosition);
            Log.d("DataHolder", sb6.toString());
        }
        Log.d("DataHolder", "*******************************************");
    }

    public final Uri parseUri(String str, int i, int i2) {
        String string = getString(str, i, i2);
        if (string == null) {
            return null;
        }
        return Uri.parse(string);
    }

    public final void replaceValue(String str, int i, int i2, double d) {
        zza(str, i);
        this.zzno[i2].putDouble(d, i, this.zznn.getInt(str));
    }

    public final void replaceValue(String str, int i, int i2, long j) {
        zza(str, i);
        this.zzno[i2].putLong(j, i, this.zznn.getInt(str));
    }

    public final void replaceValue(String str, int i, int i2, String str2) {
        zza(str, i);
        this.zzno[i2].putString(str2, i, this.zznn.getInt(str));
    }

    public final void replaceValue(String str, int i, int i2, byte[] bArr) {
        zza(str, i);
        this.zzno[i2].putBlob(bArr, i, this.zznn.getInt(str));
    }

    public final void validateContents() {
        this.zznn = new Bundle();
        for (int i = 0; i < this.zznm.length; i++) {
            this.zznn.putInt(this.zznm[i], i);
        }
        this.zznq = new int[this.zzno.length];
        int numRows = 0;
        for (int i2 = 0; i2 < this.zzno.length; i2++) {
            this.zznq[i2] = numRows;
            numRows += this.zzno[i2].getNumRows() - (numRows - this.zzno[i2].getStartPosition());
        }
        this.zznr = numRows;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, this.zznm, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, this.zzno, i, false);
        SafeParcelWriter.writeInt(parcel, 3, getStatusCode());
        SafeParcelWriter.writeBundle(parcel, 4, getMetadata(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzal);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
        if ((i & 1) != 0) {
            close();
        }
    }
}
