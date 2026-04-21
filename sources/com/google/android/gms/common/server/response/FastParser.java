package com.google.android.gms.common.server.response;

import android.util.Log;
import com.google.android.gms.common.server.response.FastJsonResponse;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import kotlin.text.Typography;
import rx.internal.operators.OperatorReplay;

/* JADX INFO: loaded from: classes.dex */
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zzwv = {'u', 'l', 'l'};
    private static final char[] zzww = {'r', 'u', 'e'};
    private static final char[] zzwx = {'r', 'u', 'e', Typography.quote};
    private static final char[] zzwy = {'a', 'l', 's', 'e'};
    private static final char[] zzwz = {'a', 'l', 's', 'e', Typography.quote};
    private static final char[] zzxa = {'\n'};
    private static final zza<Integer> zzxc = new com.google.android.gms.common.server.response.zza();
    private static final zza<Long> zzxd = new zzb();
    private static final zza<Float> zzxe = new zzc();
    private static final zza<Double> zzxf = new zzd();
    private static final zza<Boolean> zzxg = new zze();
    private static final zza<String> zzxh = new zzf();
    private static final zza<BigInteger> zzxi = new zzg();
    private static final zza<BigDecimal> zzxj = new zzh();
    private final char[] zzwq = new char[1];
    private final char[] zzwr = new char[32];
    private final char[] zzws = new char[1024];
    private final StringBuilder zzwt = new StringBuilder(32);
    private final StringBuilder zzwu = new StringBuilder(1024);
    private final Stack<Integer> zzxb = new Stack<>();

    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super(str, th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface zza<O> {
        O zzh(FastParser fastParser, BufferedReader bufferedReader) throws ParseException, IOException;
    }

    private final int zza(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i;
        char cZzj = zzj(bufferedReader);
        if (cZzj == 0) {
            throw new ParseException("Unexpected EOF");
        }
        if (cZzj == ',') {
            throw new ParseException("Missing value");
        }
        if (cZzj == 'n') {
            zzb(bufferedReader, zzwv);
            return 0;
        }
        bufferedReader.mark(1024);
        if (cZzj == '\"') {
            i = 0;
            boolean z = false;
            while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                char c = cArr[i];
                if (Character.isISOControl(c)) {
                    throw new ParseException("Unexpected control character while reading string");
                }
                if (c == '\"' && !z) {
                    bufferedReader.reset();
                    bufferedReader.skip(i + 1);
                    return i;
                }
                z = c == '\\' ? !z : false;
                i++;
            }
        } else {
            cArr[0] = cZzj;
            i = 1;
            while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                if (cArr[i] == '}' || cArr[i] == ',' || Character.isWhitespace(cArr[i]) || cArr[i] == ']') {
                    bufferedReader.reset();
                    bufferedReader.skip(i - 1);
                    cArr[i] = 0;
                    return i;
                }
                i++;
            }
        }
        if (i == cArr.length) {
            throw new ParseException("Absurdly long value");
        }
        throw new ParseException("Unexpected EOF");
    }

    private final String zza(BufferedReader bufferedReader) throws ParseException, IOException {
        this.zzxb.push(2);
        char cZzj = zzj(bufferedReader);
        if (cZzj == '\"') {
            this.zzxb.push(3);
            String strZzb = zzb(bufferedReader, this.zzwr, this.zzwt, null);
            zzk(3);
            if (zzj(bufferedReader) != ':') {
                throw new ParseException("Expected key/value separator");
            }
            return strZzb;
        }
        if (cZzj == ']') {
            zzk(2);
            zzk(1);
            zzk(5);
            return null;
        }
        if (cZzj == '}') {
            zzk(2);
            return null;
        }
        StringBuilder sb = new StringBuilder(19);
        sb.append("Unexpected token: ");
        sb.append(cZzj);
        throw new ParseException(sb.toString());
    }

    private final String zza(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        char cZzj = zzj(bufferedReader);
        if (cZzj == '\"') {
            return zzb(bufferedReader, cArr, sb, cArr2);
        }
        if (cZzj != 'n') {
            throw new ParseException("Expected string");
        }
        zzb(bufferedReader, zzwv);
        return null;
    }

    private final <T extends FastJsonResponse> ArrayList<T> zza(BufferedReader bufferedReader, FastJsonResponse.Field<?, ?> field) throws ParseException, IOException {
        OperatorReplay.UnboundedReplayBuffer unboundedReplayBuffer = (ArrayList<T>) new ArrayList();
        char cZzj = zzj(bufferedReader);
        if (cZzj == ']') {
            zzk(5);
            return unboundedReplayBuffer;
        }
        if (cZzj == 'n') {
            zzb(bufferedReader, zzwv);
            zzk(5);
            return null;
        }
        if (cZzj != '{') {
            StringBuilder sb = new StringBuilder(19);
            sb.append("Unexpected token: ");
            sb.append(cZzj);
            throw new ParseException(sb.toString());
        }
        Stack<Integer> stack = this.zzxb;
        while (true) {
            stack.push(1);
            try {
                FastJsonResponse fastJsonResponseNewConcreteTypeInstance = field.newConcreteTypeInstance();
                if (!zza(bufferedReader, fastJsonResponseNewConcreteTypeInstance)) {
                    return unboundedReplayBuffer;
                }
                unboundedReplayBuffer.add(fastJsonResponseNewConcreteTypeInstance);
                char cZzj2 = zzj(bufferedReader);
                if (cZzj2 != ',') {
                    if (cZzj2 == ']') {
                        zzk(5);
                        return unboundedReplayBuffer;
                    }
                    StringBuilder sb2 = new StringBuilder(19);
                    sb2.append("Unexpected token: ");
                    sb2.append(cZzj2);
                    throw new ParseException(sb2.toString());
                }
                if (zzj(bufferedReader) != '{') {
                    throw new ParseException("Expected start of next object in array");
                }
                stack = this.zzxb;
            } catch (IllegalAccessException e) {
                throw new ParseException("Error instantiating inner object", e);
            } catch (InstantiationException e2) {
                throw new ParseException("Error instantiating inner object", e2);
            }
        }
    }

    private final <O> ArrayList<O> zza(BufferedReader bufferedReader, zza<O> zzaVar) throws ParseException, IOException {
        char cZzj = zzj(bufferedReader);
        if (cZzj == 'n') {
            zzb(bufferedReader, zzwv);
            return null;
        }
        if (cZzj != '[') {
            throw new ParseException("Expected start of array");
        }
        this.zzxb.push(5);
        ArrayList<O> arrayList = new ArrayList<>();
        while (true) {
            bufferedReader.mark(1024);
            char cZzj2 = zzj(bufferedReader);
            if (cZzj2 == 0) {
                throw new ParseException("Unexpected EOF");
            }
            if (cZzj2 != ',') {
                if (cZzj2 == ']') {
                    zzk(5);
                    return arrayList;
                }
                bufferedReader.reset();
                arrayList.add(zzaVar.zzh(this, bufferedReader));
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x010f, code lost:
    
        zzk(1);
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:139:0x02af A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:141:0x0291 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final boolean zza(java.io.BufferedReader r14, com.google.android.gms.common.server.response.FastJsonResponse r15) throws com.google.android.gms.common.server.response.FastParser.ParseException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 734
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastParser.zza(java.io.BufferedReader, com.google.android.gms.common.server.response.FastJsonResponse):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zza(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        while (true) {
            char cZzj = zzj(bufferedReader);
            if (cZzj != '\"') {
                if (cZzj == 'f') {
                    zzb(bufferedReader, z ? zzwz : zzwy);
                    return false;
                }
                if (cZzj == 'n') {
                    zzb(bufferedReader, zzwv);
                    return false;
                }
                if (cZzj == 't') {
                    zzb(bufferedReader, z ? zzwx : zzww);
                    return true;
                }
                StringBuilder sb = new StringBuilder(19);
                sb.append("Unexpected token: ");
                sb.append(cZzj);
                throw new ParseException(sb.toString());
            }
            if (z) {
                throw new ParseException("No boolean value found in string");
            }
            z = true;
        }
    }

    private final String zzb(BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(1024);
        char cZzj = zzj(bufferedReader);
        if (cZzj == '\"') {
            if (bufferedReader.read(this.zzwq) == -1) {
                throw new ParseException("Unexpected EOF while parsing string");
            }
            char c = this.zzwq[0];
            boolean z = false;
            do {
                if (c != '\"' || z) {
                    z = c == '\\' ? !z : false;
                    if (bufferedReader.read(this.zzwq) == -1) {
                        throw new ParseException("Unexpected EOF while parsing string");
                    }
                    c = this.zzwq[0];
                }
            } while (!Character.isISOControl(c));
            throw new ParseException("Unexpected control character while reading string");
        }
        if (cZzj == ',') {
            throw new ParseException("Missing value");
        }
        int i = 1;
        if (cZzj == '[') {
            this.zzxb.push(5);
            bufferedReader.mark(32);
            if (zzj(bufferedReader) != ']') {
                bufferedReader.reset();
                boolean z2 = false;
                boolean z3 = false;
                while (i > 0) {
                    char cZzj2 = zzj(bufferedReader);
                    if (cZzj2 == 0) {
                        throw new ParseException("Unexpected EOF while parsing array");
                    }
                    if (Character.isISOControl(cZzj2)) {
                        throw new ParseException("Unexpected control character while reading array");
                    }
                    if (cZzj2 == '\"' && !z2) {
                        z3 = !z3;
                    }
                    if (cZzj2 == '[' && !z3) {
                        i++;
                    }
                    if (cZzj2 == ']' && !z3) {
                        i--;
                    }
                    z2 = (cZzj2 == '\\' && z3) ? !z2 : false;
                }
            }
            zzk(5);
        } else if (cZzj != '{') {
            bufferedReader.reset();
            zza(bufferedReader, this.zzws);
        } else {
            this.zzxb.push(1);
            bufferedReader.mark(32);
            char cZzj3 = zzj(bufferedReader);
            if (cZzj3 != '}') {
                if (cZzj3 != '\"') {
                    StringBuilder sb = new StringBuilder(18);
                    sb.append("Unexpected token ");
                    sb.append(cZzj3);
                    throw new ParseException(sb.toString());
                }
                bufferedReader.reset();
                zza(bufferedReader);
                while (zzb(bufferedReader) != null) {
                }
            }
            zzk(1);
        }
        char cZzj4 = zzj(bufferedReader);
        if (cZzj4 == ',') {
            zzk(2);
            return zza(bufferedReader);
        }
        if (cZzj4 == '}') {
            zzk(2);
            return null;
        }
        StringBuilder sb2 = new StringBuilder(18);
        sb2.append("Unexpected token ");
        sb2.append(cZzj4);
        throw new ParseException(sb2.toString());
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0031 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String zzb(java.io.BufferedReader r9, char[] r10, java.lang.StringBuilder r11, char[] r12) throws com.google.android.gms.common.server.response.FastParser.ParseException, java.io.IOException {
        /*
            r0 = 0
            r11.setLength(r0)
            int r1 = r10.length
            r9.mark(r1)
            r1 = r0
            r2 = r1
        La:
            int r3 = r9.read(r10)
            r4 = -1
            if (r3 == r4) goto L70
            r4 = r2
            r2 = r1
            r1 = r0
        L14:
            if (r1 >= r3) goto L66
            char r5 = r10[r1]
            boolean r6 = java.lang.Character.isISOControl(r5)
            r7 = 1
            if (r6 == 0) goto L39
            if (r12 == 0) goto L2e
            r6 = r0
        L22:
            int r8 = r12.length
            if (r6 >= r8) goto L2e
            char r8 = r12[r6]
            if (r8 != r5) goto L2b
            r6 = r7
            goto L2f
        L2b:
            int r6 = r6 + 1
            goto L22
        L2e:
            r6 = r0
        L2f:
            if (r6 != 0) goto L39
            com.google.android.gms.common.server.response.FastParser$ParseException r9 = new com.google.android.gms.common.server.response.FastParser$ParseException
            java.lang.String r10 = "Unexpected control character while reading string"
            r9.<init>(r10)
            throw r9
        L39:
            r6 = 34
            if (r5 != r6) goto L5a
            if (r2 != 0) goto L5a
            r11.append(r10, r0, r1)
            r9.reset()
            int r1 = r1 + r7
            long r0 = (long) r1
            r9.skip(r0)
            if (r4 == 0) goto L55
            java.lang.String r9 = r11.toString()
            java.lang.String r9 = com.google.android.gms.common.util.JsonUtils.unescapeString(r9)
            return r9
        L55:
            java.lang.String r9 = r11.toString()
            return r9
        L5a:
            r6 = 92
            if (r5 != r6) goto L62
            r2 = r2 ^ 1
            r4 = r7
            goto L63
        L62:
            r2 = r0
        L63:
            int r1 = r1 + 1
            goto L14
        L66:
            r11.append(r10, r0, r3)
            int r1 = r10.length
            r9.mark(r1)
            r1 = r2
            r2 = r4
            goto La
        L70:
            com.google.android.gms.common.server.response.FastParser$ParseException r9 = new com.google.android.gms.common.server.response.FastParser$ParseException
            java.lang.String r10 = "Unexpected EOF while parsing string"
            r9.<init>(r10)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.server.response.FastParser.zzb(java.io.BufferedReader, char[], java.lang.StringBuilder, char[]):java.lang.String");
    }

    private final void zzb(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i = 0;
        while (i < cArr.length) {
            int i2 = bufferedReader.read(this.zzwr, 0, cArr.length - i);
            if (i2 == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i3 = 0; i3 < i2; i3++) {
                if (cArr[i3 + i] != this.zzwr[i3]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i += i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zzc(BufferedReader bufferedReader) throws ParseException, IOException {
        return zza(bufferedReader, this.zzwr, this.zzwt, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzd(BufferedReader bufferedReader) throws ParseException, IOException {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int iZza = zza(bufferedReader, this.zzws);
        if (iZza == 0) {
            return 0;
        }
        char[] cArr = this.zzws;
        if (iZza <= 0) {
            throw new ParseException("No number to parse");
        }
        if (cArr[0] == '-') {
            i = Integer.MIN_VALUE;
            i2 = 1;
            z = true;
        } else {
            z = false;
            i = -2147483647;
            i2 = 0;
        }
        if (i2 < iZza) {
            i4 = i2 + 1;
            int iDigit = Character.digit(cArr[i2], 10);
            if (iDigit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            i3 = -iDigit;
        } else {
            int i5 = i2;
            i3 = 0;
            i4 = i5;
        }
        while (i4 < iZza) {
            int i6 = i4 + 1;
            int iDigit2 = Character.digit(cArr[i4], 10);
            if (iDigit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (i3 < -214748364) {
                throw new ParseException("Number too large");
            }
            int i7 = i3 * 10;
            if (i7 < i + iDigit2) {
                throw new ParseException("Number too large");
            }
            i3 = i7 - iDigit2;
            i4 = i6;
        }
        if (!z) {
            return -i3;
        }
        if (i4 > 1) {
            return i3;
        }
        throw new ParseException("No digits to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zze(BufferedReader bufferedReader) throws ParseException, IOException {
        long j;
        long j2;
        int i;
        int iZza = zza(bufferedReader, this.zzws);
        if (iZza == 0) {
            return 0L;
        }
        char[] cArr = this.zzws;
        if (iZza <= 0) {
            throw new ParseException("No number to parse");
        }
        int i2 = 0;
        if (cArr[0] == '-') {
            j = Long.MIN_VALUE;
            i2 = 1;
        } else {
            j = -9223372036854775807L;
        }
        int i3 = i2;
        if (i2 < iZza) {
            i = i2 + 1;
            int iDigit = Character.digit(cArr[i2], 10);
            if (iDigit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            j2 = -iDigit;
        } else {
            j2 = 0;
            i = i2;
        }
        while (i < iZza) {
            int i4 = i + 1;
            int iDigit2 = Character.digit(cArr[i], 10);
            if (iDigit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (j2 < -922337203685477580L) {
                throw new ParseException("Number too large");
            }
            long j3 = j2 * 10;
            long j4 = iDigit2;
            if (j3 < j + j4) {
                throw new ParseException("Number too large");
            }
            i = i4;
            j2 = j3 - j4;
        }
        if (i3 == 0) {
            return -j2;
        }
        if (i > 1) {
            return j2;
        }
        throw new ParseException("No digits to parse");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigInteger zzf(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZza = zza(bufferedReader, this.zzws);
        if (iZza == 0) {
            return null;
        }
        return new BigInteger(new String(this.zzws, 0, iZza));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float zzg(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZza = zza(bufferedReader, this.zzws);
        if (iZza == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.zzws, 0, iZza));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double zzh(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZza = zza(bufferedReader, this.zzws);
        if (iZza == 0) {
            return 0.0d;
        }
        return Double.parseDouble(new String(this.zzws, 0, iZza));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigDecimal zzi(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZza = zza(bufferedReader, this.zzws);
        if (iZza == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zzws, 0, iZza));
    }

    private final char zzj(BufferedReader bufferedReader) throws ParseException, IOException {
        if (bufferedReader.read(this.zzwq) == -1) {
            return (char) 0;
        }
        while (Character.isWhitespace(this.zzwq[0])) {
            if (bufferedReader.read(this.zzwq) == -1) {
                return (char) 0;
            }
        }
        return this.zzwq[0];
    }

    private final void zzk(int i) throws ParseException {
        if (this.zzxb.isEmpty()) {
            StringBuilder sb = new StringBuilder(46);
            sb.append("Expected state ");
            sb.append(i);
            sb.append(" but had empty stack");
            throw new ParseException(sb.toString());
        }
        int iIntValue = this.zzxb.pop().intValue();
        if (iIntValue != i) {
            StringBuilder sb2 = new StringBuilder(46);
            sb2.append("Expected state ");
            sb2.append(i);
            sb2.append(" but had ");
            sb2.append(iIntValue);
            throw new ParseException(sb2.toString());
        }
    }

    public void parse(InputStream inputStream, T t) throws ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        try {
            try {
                this.zzxb.push(0);
                char cZzj = zzj(bufferedReader);
                if (cZzj == 0) {
                    throw new ParseException("No data to parse");
                }
                if (cZzj == '[') {
                    this.zzxb.push(5);
                    Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t.getFieldMappings();
                    if (fieldMappings.size() != 1) {
                        throw new ParseException("Object array response class must have a single Field");
                    }
                    FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                    t.addConcreteTypeArrayInternal(value, value.getOutputFieldName(), zza(bufferedReader, value));
                } else {
                    if (cZzj != '{') {
                        StringBuilder sb = new StringBuilder(19);
                        sb.append("Unexpected token: ");
                        sb.append(cZzj);
                        throw new ParseException(sb.toString());
                    }
                    this.zzxb.push(1);
                    zza(bufferedReader, t);
                }
                zzk(0);
            } catch (IOException e) {
                throw new ParseException(e);
            }
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e2) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
        }
    }

    public void parse(String str, T t) throws ParseException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        try {
            parse(byteArrayInputStream, t);
        } finally {
            try {
                byteArrayInputStream.close();
            } catch (IOException e) {
                Log.w("FastParser", "Failed to close the input stream while parsing.");
            }
        }
    }
}
