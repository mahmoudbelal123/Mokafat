package kotlin.text;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: Strings.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\u001a\u001c\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u000e\u001a\u00020\n*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\u0002\u001a\u001f\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\rH\u0087\u0002\u001a\u0015\u0010\u000f\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\n\u001a\u001c\u0010\u0014\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010\u0014\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a?\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0017*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0002¢\u0006\u0002\b\u001c\u001a:\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001aE\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\rH\u0002¢\u0006\u0002\b\u001c\u001a:\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n\u0018\u00010\u0017*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010 \u001a\u00020\r*\u00020\u00022\u0006\u0010!\u001a\u00020\u0006\u001a&\u0010\"\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a;\u0010\"\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\rH\u0002¢\u0006\u0002\b$\u001a&\u0010\"\u001a\u00020\u0006*\u00020\u00022\u0006\u0010%\u001a\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u0010&\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u0010&\u001a\u00020\u0006*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\r\u0010'\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a\r\u0010(\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a\r\u0010)\u001a\u00020\r*\u00020\u0002H\u0087\b\u001a\u000f\u0010*\u001a\u00020\r*\u0004\u0018\u00010\u0002H\u0087\b\u001a\u000f\u0010+\u001a\u00020\r*\u0004\u0018\u00010\u0002H\u0087\b\u001a\r\u0010,\u001a\u00020-*\u00020\u0002H\u0086\u0002\u001a&\u0010.\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u0010.\u001a\u00020\u0006*\u00020\u00022\u0006\u0010%\u001a\u00020\n2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a&\u0010/\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a,\u0010/\u001a\u00020\u0006*\u00020\u00022\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\u001e2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0010\u00100\u001a\b\u0012\u0004\u0012\u00020\n01*\u00020\u0002\u001a\u0010\u00102\u001a\b\u0012\u0004\u0012\u00020\n03*\u00020\u0002\u001a\u0015\u00104\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0087\f\u001a\u000f\u00105\u001a\u00020\n*\u0004\u0018\u00010\nH\u0087\b\u001a\u001c\u00106\u001a\u00020\u0002*\u00020\u00022\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00108\u001a\u00020\u0011\u001a\u001c\u00106\u001a\u00020\n*\u00020\n2\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00108\u001a\u00020\u0011\u001a\u001c\u00109\u001a\u00020\u0002*\u00020\u00022\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00108\u001a\u00020\u0011\u001a\u001c\u00109\u001a\u00020\n*\u00020\n2\u0006\u00107\u001a\u00020\u00062\b\b\u0002\u00108\u001a\u00020\u0011\u001aG\u0010:\u001a\b\u0012\u0004\u0012\u00020\u000101*\u00020\u00022\u000e\u0010;\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0<2\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010=\u001a\u00020\u0006H\u0002¢\u0006\u0004\b>\u0010?\u001a=\u0010:\u001a\b\u0012\u0004\u0012\u00020\u000101*\u00020\u00022\u0006\u0010;\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010=\u001a\u00020\u0006H\u0002¢\u0006\u0002\b>\u001a4\u0010@\u001a\u00020\r*\u00020\u00022\u0006\u0010A\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010B\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u0012\u0010C\u001a\u00020\u0002*\u00020\u00022\u0006\u0010D\u001a\u00020\u0002\u001a\u0012\u0010C\u001a\u00020\n*\u00020\n2\u0006\u0010D\u001a\u00020\u0002\u001a\u001a\u0010E\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0006\u001a\u0012\u0010E\u001a\u00020\u0002*\u00020\u00022\u0006\u0010F\u001a\u00020\u0001\u001a\u001d\u0010E\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u0006H\u0087\b\u001a\u0015\u0010E\u001a\u00020\n*\u00020\n2\u0006\u0010F\u001a\u00020\u0001H\u0087\b\u001a\u0012\u0010G\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010G\u001a\u00020\n*\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010H\u001a\u00020\u0002*\u00020\u00022\u0006\u0010I\u001a\u00020\u0002\u001a\u001a\u0010H\u001a\u00020\u0002*\u00020\u00022\u0006\u0010D\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a\u0012\u0010H\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u0002\u001a\u001a\u0010H\u001a\u00020\n*\u00020\n2\u0006\u0010D\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0002\u001a+\u0010J\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0014\b\b\u0010K\u001a\u000e\u0012\u0004\u0012\u00020M\u0012\u0004\u0012\u00020\u00020LH\u0087\b\u001a\u001d\u0010J\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010N\u001a\u00020\nH\u0087\b\u001a$\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a$\u0010O\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a$\u0010Q\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a$\u0010Q\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a$\u0010R\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a$\u0010R\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a$\u0010S\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a$\u0010S\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\u0006\u0010N\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001d\u0010T\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010N\u001a\u00020\nH\u0087\b\u001a\"\u0010U\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u0002\u001a\u001a\u0010U\u001a\u00020\u0002*\u00020\u00022\u0006\u0010F\u001a\u00020\u00012\u0006\u0010N\u001a\u00020\u0002\u001a%\u0010U\u001a\u00020\n*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u0002H\u0087\b\u001a\u001d\u0010U\u001a\u00020\n*\u00020\n2\u0006\u0010F\u001a\u00020\u00012\u0006\u0010N\u001a\u00020\u0002H\u0087\b\u001a=\u0010V\u001a\b\u0012\u0004\u0012\u00020\n03*\u00020\u00022\u0012\u0010;\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0<\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010=\u001a\u00020\u0006¢\u0006\u0002\u0010W\u001a0\u0010V\u001a\b\u0012\u0004\u0012\u00020\n03*\u00020\u00022\n\u0010;\u001a\u00020\u0019\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010=\u001a\u00020\u0006\u001a%\u0010V\u001a\b\u0012\u0004\u0012\u00020\n03*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010=\u001a\u00020\u0006H\u0087\b\u001a=\u0010X\u001a\b\u0012\u0004\u0012\u00020\n01*\u00020\u00022\u0012\u0010;\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0<\"\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010=\u001a\u00020\u0006¢\u0006\u0002\u0010Y\u001a0\u0010X\u001a\b\u0012\u0004\u0012\u00020\n01*\u00020\u00022\n\u0010;\u001a\u00020\u0019\"\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010=\u001a\u00020\u0006\u001a\u001c\u0010Z\u001a\u00020\r*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u001c\u0010Z\u001a\u00020\r*\u00020\u00022\u0006\u0010D\u001a\u00020\u00022\b\b\u0002\u0010\f\u001a\u00020\r\u001a$\u0010Z\u001a\u00020\r*\u00020\u00022\u0006\u0010D\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\r\u001a\u0012\u0010[\u001a\u00020\u0002*\u00020\u00022\u0006\u0010F\u001a\u00020\u0001\u001a\u001d\u0010[\u001a\u00020\u0002*\u00020\n2\u0006\u0010\\\u001a\u00020\u00062\u0006\u0010]\u001a\u00020\u0006H\u0087\b\u001a\u001f\u0010^\u001a\u00020\n*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00062\b\b\u0002\u0010#\u001a\u00020\u0006H\u0087\b\u001a\u0012\u0010^\u001a\u00020\n*\u00020\u00022\u0006\u0010F\u001a\u00020\u0001\u001a\u0012\u0010^\u001a\u00020\n*\u00020\n2\u0006\u0010F\u001a\u00020\u0001\u001a\u001c\u0010_\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001c\u0010_\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001c\u0010`\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001c\u0010`\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001c\u0010a\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001c\u0010a\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001c\u0010b\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\u00112\b\b\u0002\u0010P\u001a\u00020\n\u001a\u001c\u0010b\u001a\u00020\n*\u00020\n2\u0006\u0010I\u001a\u00020\n2\b\b\u0002\u0010P\u001a\u00020\n\u001a\n\u0010c\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010c\u001a\u00020\u0002*\u00020\u00022\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0LH\u0086\b\u001a\u0016\u0010c\u001a\u00020\u0002*\u00020\u00022\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0011\u001a\r\u0010c\u001a\u00020\n*\u00020\nH\u0087\b\u001a!\u0010c\u001a\u00020\n*\u00020\n2\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0LH\u0086\b\u001a\u0016\u0010c\u001a\u00020\n*\u00020\n2\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0011\u001a\n\u0010e\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010e\u001a\u00020\u0002*\u00020\u00022\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0LH\u0086\b\u001a\u0016\u0010e\u001a\u00020\u0002*\u00020\u00022\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0011\u001a\r\u0010e\u001a\u00020\n*\u00020\nH\u0087\b\u001a!\u0010e\u001a\u00020\n*\u00020\n2\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0LH\u0086\b\u001a\u0016\u0010e\u001a\u00020\n*\u00020\n2\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0011\u001a\n\u0010f\u001a\u00020\u0002*\u00020\u0002\u001a!\u0010f\u001a\u00020\u0002*\u00020\u00022\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0LH\u0086\b\u001a\u0016\u0010f\u001a\u00020\u0002*\u00020\u00022\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0011\u001a\r\u0010f\u001a\u00020\n*\u00020\nH\u0087\b\u001a!\u0010f\u001a\u00020\n*\u00020\n2\u0012\u0010d\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0LH\u0086\b\u001a\u0016\u0010f\u001a\u00020\n*\u00020\n2\n\u0010\u0018\u001a\u00020\u0019\"\u00020\u0011\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006g"}, d2 = {"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/lang/CharSequence;)Lkotlin/ranges/IntRange;", "lastIndex", "", "getLastIndex", "(Ljava/lang/CharSequence;)I", "commonPrefixWith", "", "other", "ignoreCase", "", "commonSuffixWith", "contains", "char", "", "regex", "Lkotlin/text/Regex;", "endsWith", "suffix", "findAnyOf", "Lkotlin/Pair;", "chars", "", "startIndex", "last", "findAnyOf$StringsKt__StringsKt", "strings", "", "findLastAnyOf", "hasSurrogatePairAt", FirebaseAnalytics.Param.INDEX, "indexOf", "endIndex", "indexOf$StringsKt__StringsKt", "string", "indexOfAny", "isEmpty", "isNotBlank", "isNotEmpty", "isNullOrBlank", "isNullOrEmpty", "iterator", "Lkotlin/collections/CharIterator;", "lastIndexOf", "lastIndexOfAny", "lineSequence", "Lkotlin/sequences/Sequence;", "lines", "", "matches", "orEmpty", "padEnd", "length", "padChar", "padStart", "rangesDelimitedBy", "delimiters", "", "limit", "rangesDelimitedBy$StringsKt__StringsKt", "(Ljava/lang/CharSequence;[Ljava/lang/String;IZI)Lkotlin/sequences/Sequence;", "regionMatchesImpl", "thisOffset", "otherOffset", "removePrefix", "prefix", "removeRange", "range", "removeSuffix", "removeSurrounding", "delimiter", "replace", "transform", "Lkotlin/Function1;", "Lkotlin/text/MatchResult;", "replacement", "replaceAfter", "missingDelimiterValue", "replaceAfterLast", "replaceBefore", "replaceBeforeLast", "replaceFirst", "replaceRange", "split", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Ljava/util/List;", "splitToSequence", "(Ljava/lang/CharSequence;[Ljava/lang/String;ZI)Lkotlin/sequences/Sequence;", "startsWith", "subSequence", "start", "end", "substring", "substringAfter", "substringAfterLast", "substringBefore", "substringBeforeLast", "trim", "predicate", "trimEnd", "trimStart", "kotlin-stdlib"}, k = 5, mv = {1, 1, 5}, xi = 1, xs = "kotlin/text/StringsKt")
public class StringsKt__StringsKt extends StringsKt__StringsJVMKt {
    @NotNull
    public static final CharSequence trim(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int startIndex = 0;
        int endIndex = receiver.length() - 1;
        boolean startFound = false;
        while (startIndex <= endIndex) {
            int index = !startFound ? startIndex : endIndex;
            boolean match = predicate.invoke(Character.valueOf(receiver.charAt(index))).booleanValue();
            if (!startFound) {
                if (!match) {
                    startFound = true;
                } else {
                    startIndex++;
                }
            } else {
                if (!match) {
                    break;
                }
                endIndex--;
            }
        }
        return receiver.subSequence(startIndex, endIndex + 1);
    }

    @NotNull
    public static final String trim(@NotNull String receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        String $receiver$iv = receiver;
        boolean startFound$iv = false;
        int startIndex$iv = 0;
        int endIndex$iv = $receiver$iv.length() - 1;
        while (startIndex$iv <= endIndex$iv) {
            int index$iv = !startFound$iv ? startIndex$iv : endIndex$iv;
            boolean match$iv = predicate.invoke(Character.valueOf($receiver$iv.charAt(index$iv))).booleanValue();
            if (startFound$iv) {
                if (!match$iv) {
                    break;
                }
                endIndex$iv--;
            } else if (match$iv) {
                startIndex$iv++;
            } else {
                startFound$iv = true;
            }
        }
        return $receiver$iv.subSequence(startIndex$iv, endIndex$iv + 1).toString();
    }

    @NotNull
    public static final CharSequence trimStart(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = receiver.length() - 1;
        if (length >= 0) {
            int index = 0;
            while (predicate.invoke(Character.valueOf(receiver.charAt(index))).booleanValue()) {
                if (index != length) {
                    index++;
                }
            }
            return receiver.subSequence(index, receiver.length());
        }
        return "";
    }

    @NotNull
    public static final String trimStart(@NotNull String receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        String strSubSequence;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        String $receiver$iv = receiver;
        int index$iv = 0;
        int length = $receiver$iv.length() - 1;
        if (length < 0) {
            break;
        }
        while (predicate.invoke(Character.valueOf($receiver$iv.charAt(index$iv))).booleanValue()) {
            if (index$iv == length) {
                break;
            }
            index$iv++;
        }
        int $i$f$trimStart = $receiver$iv.length();
        strSubSequence = $receiver$iv.subSequence(index$iv, $i$f$trimStart);
        return strSubSequence.toString();
    }

    @NotNull
    public static final CharSequence trimEnd(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        IntProgression intProgressionReversed = RangesKt.reversed(StringsKt.getIndices(receiver));
        int index = intProgressionReversed.getFirst();
        int last = intProgressionReversed.getLast();
        int step = intProgressionReversed.getStep();
        if (step <= 0 ? index >= last : index <= last) {
            while (predicate.invoke(Character.valueOf(receiver.charAt(index))).booleanValue()) {
                if (index != last) {
                    index += step;
                }
            }
            return receiver.subSequence(0, index + 1).toString();
        }
        return "";
    }

    @NotNull
    public static final String trimEnd(@NotNull String receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        String string;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        String $receiver$iv = receiver;
        IntProgression intProgressionReversed = RangesKt.reversed(StringsKt.getIndices($receiver$iv));
        int index$iv = intProgressionReversed.getFirst();
        int last = intProgressionReversed.getLast();
        int step = intProgressionReversed.getStep();
        if (step <= 0 ? index$iv < last : index$iv > last) {
            break;
        }
        while (predicate.invoke(Character.valueOf($receiver$iv.charAt(index$iv))).booleanValue()) {
            if (index$iv == last) {
                break;
            }
            index$iv += step;
        }
        int $i$f$trimEnd = index$iv + 1;
        string = $receiver$iv.subSequence(0, $i$f$trimEnd).toString();
        return string.toString();
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence receiver, @NotNull char... chars) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        int startIndex$iv = 0;
        int endIndex$iv = receiver.length() - 1;
        boolean startFound$iv = false;
        while (startIndex$iv <= endIndex$iv) {
            int index$iv = !startFound$iv ? startIndex$iv : endIndex$iv;
            char it = receiver.charAt(index$iv);
            boolean match$iv = ArraysKt.contains(chars, it);
            if (startFound$iv) {
                if (!match$iv) {
                    break;
                }
                endIndex$iv--;
            } else if (match$iv) {
                startIndex$iv++;
            } else {
                startFound$iv = true;
            }
        }
        int $i$a$1$trim = endIndex$iv + 1;
        return receiver.subSequence(startIndex$iv, $i$a$1$trim);
    }

    @NotNull
    public static final String trim(@NotNull String receiver, @NotNull char... chars) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        String $receiver$iv$iv = receiver;
        boolean startFound$iv$iv = false;
        int startIndex$iv$iv = 0;
        int endIndex$iv$iv = $receiver$iv$iv.length() - 1;
        while (startIndex$iv$iv <= endIndex$iv$iv) {
            int index$iv$iv = !startFound$iv$iv ? startIndex$iv$iv : endIndex$iv$iv;
            char it = $receiver$iv$iv.charAt(index$iv$iv);
            boolean match$iv$iv = ArraysKt.contains(chars, it);
            if (startFound$iv$iv) {
                if (!match$iv$iv) {
                    break;
                }
                endIndex$iv$iv--;
            } else if (match$iv$iv) {
                startIndex$iv$iv++;
            } else {
                startFound$iv$iv = true;
            }
        }
        int $i$a$2$trim = endIndex$iv$iv + 1;
        return $receiver$iv$iv.subSequence(startIndex$iv$iv, $i$a$2$trim).toString();
    }

    /* JADX WARN: Incorrect condition in loop: B:6:0x001e */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.CharSequence trimStart(@org.jetbrains.annotations.NotNull java.lang.CharSequence r6, @org.jetbrains.annotations.NotNull char... r7) {
        /*
            r0 = 0
            java.lang.String r1 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r1)
            java.lang.String r1 = "chars"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r1)
            r1 = r6
            int r2 = r1.length()
            int r2 = r2 + (-1)
            if (r2 < 0) goto L2e
            r3 = 0
            r4 = r3
        L16:
            char r5 = r1.charAt(r3)
            boolean r5 = kotlin.collections.ArraysKt.contains(r7, r5)
            if (r5 != 0) goto L29
            int r2 = r1.length()
            java.lang.CharSequence r2 = r1.subSequence(r3, r2)
            goto L32
        L29:
            if (r3 == r2) goto L2e
            int r3 = r3 + 1
            goto L16
        L2e:
            java.lang.String r2 = ""
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
        L32:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.trimStart(java.lang.CharSequence, char[]):java.lang.CharSequence");
    }

    /* JADX WARN: Incorrect condition in loop: B:10:0x002c */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.String trimStart(@org.jetbrains.annotations.NotNull java.lang.String r8, @org.jetbrains.annotations.NotNull char... r9) {
        /*
            r0 = 0
            java.lang.String r1 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r1)
            java.lang.String r1 = "chars"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r1)
            r1 = r8
            if (r1 != 0) goto L16
            kotlin.TypeCastException r2 = new kotlin.TypeCastException
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.CharSequence"
            r2.<init>(r3)
            throw r2
        L16:
            r2 = r1
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r3 = 0
            r4 = r3
            int r5 = r2.length()
            int r5 = r5 + (-1)
            if (r5 < 0) goto L3c
            r6 = r3
        L24:
            char r7 = r2.charAt(r3)
            boolean r7 = kotlin.collections.ArraysKt.contains(r9, r7)
            if (r7 != 0) goto L37
            int r4 = r2.length()
            java.lang.CharSequence r4 = r2.subSequence(r3, r4)
            goto L41
        L37:
            if (r3 == r5) goto L3c
            int r3 = r3 + 1
            goto L24
        L3c:
            java.lang.String r3 = ""
            r4 = r3
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
        L41:
            java.lang.String r0 = r4.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.trimStart(java.lang.String, char[]):java.lang.String");
    }

    /* JADX WARN: Incorrect condition in loop: B:9:0x0033 */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.CharSequence trimEnd(@org.jetbrains.annotations.NotNull java.lang.CharSequence r8, @org.jetbrains.annotations.NotNull char... r9) {
        /*
            r0 = 0
            java.lang.String r1 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r1)
            java.lang.String r1 = "chars"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r1)
            r1 = r8
            kotlin.ranges.IntRange r2 = kotlin.text.StringsKt.getIndices(r1)
            kotlin.ranges.IntProgression r2 = (kotlin.ranges.IntProgression) r2
            kotlin.ranges.IntProgression r2 = kotlin.ranges.RangesKt.reversed(r2)
            int r3 = r2.getFirst()
            int r4 = r2.getLast()
            int r2 = r2.getStep()
            if (r2 <= 0) goto L27
            if (r3 > r4) goto L48
            goto L29
        L27:
            if (r3 < r4) goto L48
        L29:
            r5 = 0
            r6 = r5
        L2b:
            char r7 = r1.charAt(r3)
            boolean r7 = kotlin.collections.ArraysKt.contains(r9, r7)
            if (r7 != 0) goto L42
            int r2 = r3 + 1
            java.lang.CharSequence r2 = r1.subSequence(r5, r2)
            java.lang.String r2 = r2.toString()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            goto L4c
        L42:
            if (r3 == r4) goto L48
            int r7 = r3 + r2
            r3 = r7
            goto L2b
        L48:
            java.lang.String r2 = ""
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
        L4c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.trimEnd(java.lang.CharSequence, char[]):java.lang.CharSequence");
    }

    /* JADX WARN: Incorrect condition in loop: B:13:0x0041 */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.String trimEnd(@org.jetbrains.annotations.NotNull java.lang.String r10, @org.jetbrains.annotations.NotNull char... r11) {
        /*
            r0 = 0
            java.lang.String r1 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r1)
            java.lang.String r1 = "chars"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r1)
            r1 = r10
            if (r1 != 0) goto L16
            kotlin.TypeCastException r2 = new kotlin.TypeCastException
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.CharSequence"
            r2.<init>(r3)
            throw r2
        L16:
            r2 = r1
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            r3 = 0
            r4 = r3
            kotlin.ranges.IntRange r5 = kotlin.text.StringsKt.getIndices(r2)
            kotlin.ranges.IntProgression r5 = (kotlin.ranges.IntProgression) r5
            kotlin.ranges.IntProgression r5 = kotlin.ranges.RangesKt.reversed(r5)
            int r6 = r5.getFirst()
            int r7 = r5.getLast()
            int r5 = r5.getStep()
            if (r5 <= 0) goto L36
            if (r6 > r7) goto L56
            goto L38
        L36:
            if (r6 < r7) goto L56
        L38:
            r8 = r3
        L39:
            char r9 = r2.charAt(r6)
            boolean r9 = kotlin.collections.ArraysKt.contains(r11, r9)
            if (r9 != 0) goto L50
            int r4 = r6 + 1
            java.lang.CharSequence r3 = r2.subSequence(r3, r4)
            java.lang.String r3 = r3.toString()
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            goto L5a
        L50:
            if (r6 == r7) goto L56
            int r9 = r6 + r5
            r6 = r9
            goto L39
        L56:
            java.lang.String r3 = ""
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
        L5a:
            java.lang.String r0 = r3.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.trimEnd(java.lang.String, char[]):java.lang.String");
    }

    @NotNull
    public static final CharSequence trim(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int startIndex$iv = 0;
        int endIndex$iv = receiver.length() - 1;
        boolean startFound$iv = false;
        while (startIndex$iv <= endIndex$iv) {
            int index$iv = !startFound$iv ? startIndex$iv : endIndex$iv;
            char it = receiver.charAt(index$iv);
            boolean match$iv = CharsKt.isWhitespace(it);
            if (startFound$iv) {
                if (!match$iv) {
                    break;
                }
                endIndex$iv--;
            } else if (match$iv) {
                startIndex$iv++;
            } else {
                startFound$iv = true;
            }
        }
        int $i$a$3$trim = endIndex$iv + 1;
        return receiver.subSequence(startIndex$iv, $i$a$3$trim);
    }

    @InlineOnly
    private static final String trim(@NotNull String $receiver) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.trim((CharSequence) $receiver).toString();
    }

    /* JADX WARN: Incorrect condition in loop: B:6:0x0019 */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.CharSequence trimStart(@org.jetbrains.annotations.NotNull java.lang.CharSequence r6) {
        /*
            r0 = 0
            java.lang.String r1 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r1)
            r1 = r6
            int r2 = r1.length()
            int r2 = r2 + (-1)
            if (r2 < 0) goto L29
            r3 = 0
            r4 = r3
        L11:
            char r5 = r1.charAt(r3)
            boolean r5 = kotlin.text.CharsKt.isWhitespace(r5)
            if (r5 != 0) goto L24
            int r2 = r1.length()
            java.lang.CharSequence r2 = r1.subSequence(r3, r2)
            goto L2d
        L24:
            if (r3 == r2) goto L29
            int r3 = r3 + 1
            goto L11
        L29:
            java.lang.String r2 = ""
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
        L2d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.trimStart(java.lang.CharSequence):java.lang.CharSequence");
    }

    @InlineOnly
    private static final String trimStart(@NotNull String $receiver) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.trimStart((CharSequence) $receiver).toString();
    }

    /* JADX WARN: Incorrect condition in loop: B:9:0x002e */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.CharSequence trimEnd(@org.jetbrains.annotations.NotNull java.lang.CharSequence r8) {
        /*
            r0 = 0
            java.lang.String r1 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r1)
            r1 = r8
            kotlin.ranges.IntRange r2 = kotlin.text.StringsKt.getIndices(r1)
            kotlin.ranges.IntProgression r2 = (kotlin.ranges.IntProgression) r2
            kotlin.ranges.IntProgression r2 = kotlin.ranges.RangesKt.reversed(r2)
            int r3 = r2.getFirst()
            int r4 = r2.getLast()
            int r2 = r2.getStep()
            if (r2 <= 0) goto L22
            if (r3 > r4) goto L43
            goto L24
        L22:
            if (r3 < r4) goto L43
        L24:
            r5 = 0
            r6 = r5
        L26:
            char r7 = r1.charAt(r3)
            boolean r7 = kotlin.text.CharsKt.isWhitespace(r7)
            if (r7 != 0) goto L3d
            int r2 = r3 + 1
            java.lang.CharSequence r2 = r1.subSequence(r5, r2)
            java.lang.String r2 = r2.toString()
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            goto L47
        L3d:
            if (r3 == r4) goto L43
            int r7 = r3 + r2
            r3 = r7
            goto L26
        L43:
            java.lang.String r2 = ""
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
        L47:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.trimEnd(java.lang.CharSequence):java.lang.CharSequence");
    }

    @InlineOnly
    private static final String trimEnd(@NotNull String $receiver) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.trimEnd((CharSequence) $receiver).toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ CharSequence padStart$default(CharSequence charSequence, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return StringsKt.padStart(charSequence, i, c);
    }

    @NotNull
    public static final CharSequence padStart(@NotNull CharSequence receiver, int length, char padChar) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (length < 0) {
            throw new IllegalArgumentException("Desired length " + length + " is less than zero.");
        }
        if (length <= receiver.length()) {
            return receiver.subSequence(0, receiver.length());
        }
        StringBuilder sb = new StringBuilder(length);
        int length2 = length - receiver.length();
        int i = 1;
        if (1 <= length2) {
            while (true) {
                sb.append(padChar);
                if (i == length2) {
                    break;
                }
                i++;
            }
        }
        sb.append(receiver);
        return sb;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String padStart$default(String str, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return StringsKt.padStart(str, i, c);
    }

    @NotNull
    public static final String padStart(@NotNull String receiver, int length, char padChar) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.padStart((CharSequence) receiver, length, padChar).toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ CharSequence padEnd$default(CharSequence charSequence, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return StringsKt.padEnd(charSequence, i, c);
    }

    @NotNull
    public static final CharSequence padEnd(@NotNull CharSequence receiver, int length, char padChar) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (length < 0) {
            throw new IllegalArgumentException("Desired length " + length + " is less than zero.");
        }
        if (length <= receiver.length()) {
            return receiver.subSequence(0, receiver.length());
        }
        StringBuilder sb = new StringBuilder(length);
        sb.append(receiver);
        int length2 = length - receiver.length();
        int i = 1;
        if (1 <= length2) {
            while (true) {
                sb.append(padChar);
                if (i == length2) {
                    break;
                }
                i++;
            }
        }
        return sb;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String padEnd$default(String str, int i, char c, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            c = ' ';
        }
        return StringsKt.padEnd(str, i, c);
    }

    @NotNull
    public static final String padEnd(@NotNull String receiver, int length, char padChar) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.padEnd((CharSequence) receiver, length, padChar).toString();
    }

    @InlineOnly
    private static final boolean isNullOrEmpty(@Nullable CharSequence $receiver) {
        return $receiver == null || $receiver.length() == 0;
    }

    @InlineOnly
    private static final boolean isEmpty(@NotNull CharSequence $receiver) {
        return $receiver.length() == 0;
    }

    @InlineOnly
    private static final boolean isNotEmpty(@NotNull CharSequence $receiver) {
        return $receiver.length() > 0;
    }

    @InlineOnly
    private static final boolean isNotBlank(@NotNull CharSequence $receiver) {
        return !StringsKt.isBlank($receiver);
    }

    @InlineOnly
    private static final boolean isNullOrBlank(@Nullable CharSequence $receiver) {
        return $receiver == null || StringsKt.isBlank($receiver);
    }

    @NotNull
    public static final CharIterator iterator(@NotNull final CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new CharIterator() { // from class: kotlin.text.StringsKt__StringsKt.iterator.1
            private int index;

            private final int getIndex() {
                return this.index;
            }

            private final void setIndex(int i) {
                this.index = i;
            }

            @Override // kotlin.collections.CharIterator
            public char nextChar() {
                CharSequence charSequence = receiver;
                int i = this.index;
                this.index = i + 1;
                return charSequence.charAt(i);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.index < receiver.length();
            }
        };
    }

    @InlineOnly
    private static final String orEmpty(@Nullable String $receiver) {
        return $receiver != null ? $receiver : "";
    }

    @NotNull
    public static final IntRange getIndices(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new IntRange(0, receiver.length() - 1);
    }

    public static final int getLastIndex(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.length() - 1;
    }

    public static final boolean hasSurrogatePairAt(@NotNull CharSequence receiver, int index) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (index >= 0 && index <= receiver.length() + (-2)) && Character.isHighSurrogate(receiver.charAt(index)) && Character.isLowSurrogate(receiver.charAt(index + 1));
    }

    @NotNull
    public static final String substring(@NotNull String receiver, @NotNull IntRange range) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(range, "range");
        int iIntValue = range.getStart().intValue();
        int iIntValue2 = range.getEndInclusive().intValue() + 1;
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = receiver.substring(iIntValue, iIntValue2);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @NotNull
    public static final CharSequence subSequence(@NotNull CharSequence receiver, @NotNull IntRange range) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(range, "range");
        return receiver.subSequence(range.getStart().intValue(), range.getEndInclusive().intValue() + 1);
    }

    @Deprecated(message = "Use parameters named startIndex and endIndex.", replaceWith = @ReplaceWith(expression = "subSequence(startIndex = start, endIndex = end)", imports = {}))
    @InlineOnly
    private static final CharSequence subSequence(@NotNull String $receiver, int start, int end) {
        return $receiver.subSequence(start, end);
    }

    @InlineOnly
    private static final String substring(@NotNull CharSequence $receiver, int startIndex, int endIndex) {
        return $receiver.subSequence(startIndex, endIndex).toString();
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ String substring$default(CharSequence $receiver, int startIndex, int endIndex, int i, Object obj) {
        if ((i & 2) != 0) {
            endIndex = $receiver.length();
        }
        return $receiver.subSequence(startIndex, endIndex).toString();
    }

    @NotNull
    public static final String substring(@NotNull CharSequence receiver, @NotNull IntRange range) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(range, "range");
        return receiver.subSequence(range.getStart().intValue(), range.getEndInclusive().intValue() + 1).toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String substringBefore$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return StringsKt.substringBefore(str, c, str2);
    }

    @NotNull
    public static final String substringBefore(@NotNull String receiver, char delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = receiver.substring(0, index);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String substringBefore$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return StringsKt.substringBefore(str, str2, str3);
    }

    @NotNull
    public static final String substringBefore(@NotNull String receiver, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = receiver.substring(0, index);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String substringAfter$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return StringsKt.substringAfter(str, c, str2);
    }

    @NotNull
    public static final String substringAfter(@NotNull String receiver, char delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        int i = index + 1;
        int length = receiver.length();
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = receiver.substring(i, length);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String substringAfter$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return StringsKt.substringAfter(str, str2, str3);
    }

    @NotNull
    public static final String substringAfter(@NotNull String receiver, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        int length = delimiter.length() + index;
        int length2 = receiver.length();
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = receiver.substring(length, length2);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String substringBeforeLast$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return StringsKt.substringBeforeLast(str, c, str2);
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String receiver, char delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = receiver.substring(0, index);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String substringBeforeLast$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return StringsKt.substringBeforeLast(str, str2, str3);
    }

    @NotNull
    public static final String substringBeforeLast(@NotNull String receiver, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = receiver.substring(0, index);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String substringAfterLast$default(String str, char c, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = str;
        }
        return StringsKt.substringAfterLast(str, c, str2);
    }

    @NotNull
    public static final String substringAfterLast(@NotNull String receiver, char delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        int i = index + 1;
        int length = receiver.length();
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = receiver.substring(i, length);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String substringAfterLast$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str3 = str;
        }
        return StringsKt.substringAfterLast(str, str2, str3);
    }

    @NotNull
    public static final String substringAfterLast(@NotNull String receiver, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        int length = delimiter.length() + index;
        int length2 = receiver.length();
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = receiver.substring(length, length2);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence receiver, int startIndex, int endIndex, @NotNull CharSequence replacement) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        if (endIndex < startIndex) {
            throw new IndexOutOfBoundsException("End index (" + endIndex + ") is less than start index (" + startIndex + ").");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(receiver, 0, startIndex);
        sb.append(replacement);
        sb.append(receiver, endIndex, receiver.length());
        return sb;
    }

    @InlineOnly
    private static final String replaceRange(@NotNull String $receiver, int startIndex, int endIndex, CharSequence replacement) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.replaceRange((CharSequence) $receiver, startIndex, endIndex, replacement).toString();
    }

    @NotNull
    public static final CharSequence replaceRange(@NotNull CharSequence receiver, @NotNull IntRange range, @NotNull CharSequence replacement) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(range, "range");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        return StringsKt.replaceRange(receiver, range.getStart().intValue(), range.getEndInclusive().intValue() + 1, replacement);
    }

    @InlineOnly
    private static final String replaceRange(@NotNull String $receiver, IntRange range, CharSequence replacement) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.replaceRange((CharSequence) $receiver, range, replacement).toString();
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence receiver, int startIndex, int endIndex) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (endIndex >= startIndex) {
            if (endIndex == startIndex) {
                return receiver.subSequence(0, receiver.length());
            }
            StringBuilder sb = new StringBuilder(receiver.length() - (endIndex - startIndex));
            sb.append(receiver, 0, startIndex);
            sb.append(receiver, endIndex, receiver.length());
            return sb;
        }
        throw new IndexOutOfBoundsException("End index (" + endIndex + ") is less than start index (" + startIndex + ").");
    }

    @InlineOnly
    private static final String removeRange(@NotNull String $receiver, int startIndex, int endIndex) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.removeRange((CharSequence) $receiver, startIndex, endIndex).toString();
    }

    @NotNull
    public static final CharSequence removeRange(@NotNull CharSequence receiver, @NotNull IntRange range) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(range, "range");
        return StringsKt.removeRange(receiver, range.getStart().intValue(), range.getEndInclusive().intValue() + 1);
    }

    @InlineOnly
    private static final String removeRange(@NotNull String $receiver, IntRange range) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.removeRange((CharSequence) $receiver, range).toString();
    }

    @NotNull
    public static final CharSequence removePrefix(@NotNull CharSequence receiver, @NotNull CharSequence prefix) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (!StringsKt.startsWith$default(receiver, prefix, false, 2, (Object) null)) {
            return receiver.subSequence(0, receiver.length());
        }
        return receiver.subSequence(prefix.length(), receiver.length());
    }

    @NotNull
    public static final String removePrefix(@NotNull String receiver, @NotNull CharSequence prefix) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (StringsKt.startsWith$default((CharSequence) receiver, prefix, false, 2, (Object) null)) {
            int length = prefix.length();
            if (receiver == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = receiver.substring(length);
            Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
            return strSubstring;
        }
        return receiver;
    }

    @NotNull
    public static final CharSequence removeSuffix(@NotNull CharSequence receiver, @NotNull CharSequence suffix) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        return StringsKt.endsWith$default(receiver, suffix, false, 2, (Object) null) ? receiver.subSequence(0, receiver.length() - suffix.length()) : receiver.subSequence(0, receiver.length());
    }

    @NotNull
    public static final String removeSuffix(@NotNull String receiver, @NotNull CharSequence suffix) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        if (StringsKt.endsWith$default((CharSequence) receiver, suffix, false, 2, (Object) null)) {
            int length = receiver.length() - suffix.length();
            if (receiver == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = receiver.substring(0, length);
            Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return strSubstring;
        }
        return receiver;
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence receiver, @NotNull CharSequence prefix, @NotNull CharSequence suffix) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        if (receiver.length() >= prefix.length() + suffix.length() && StringsKt.startsWith$default(receiver, prefix, false, 2, (Object) null) && StringsKt.endsWith$default(receiver, suffix, false, 2, (Object) null)) {
            return receiver.subSequence(prefix.length(), receiver.length() - suffix.length());
        }
        return receiver.subSequence(0, receiver.length());
    }

    @NotNull
    public static final String removeSurrounding(@NotNull String receiver, @NotNull CharSequence prefix, @NotNull CharSequence suffix) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        if (receiver.length() >= prefix.length() + suffix.length() && StringsKt.startsWith$default((CharSequence) receiver, prefix, false, 2, (Object) null) && StringsKt.endsWith$default((CharSequence) receiver, suffix, false, 2, (Object) null)) {
            int length = prefix.length();
            int length2 = receiver.length() - suffix.length();
            if (receiver == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = receiver.substring(length, length2);
            Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return strSubstring;
        }
        return receiver;
    }

    @NotNull
    public static final CharSequence removeSurrounding(@NotNull CharSequence receiver, @NotNull CharSequence delimiter) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        return StringsKt.removeSurrounding(receiver, delimiter, delimiter);
    }

    @NotNull
    public static final String removeSurrounding(@NotNull String receiver, @NotNull CharSequence delimiter) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        return StringsKt.removeSurrounding(receiver, delimiter, delimiter);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String replaceBefore$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return StringsKt.replaceBefore(str, c, str2, str3);
    }

    @NotNull
    public static final String replaceBefore(@NotNull String receiver, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.replaceRange((CharSequence) receiver, 0, index, (CharSequence) replacement).toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String replaceBefore$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return StringsKt.replaceBefore(str, str2, str3, str4);
    }

    @NotNull
    public static final String replaceBefore(@NotNull String receiver, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.replaceRange((CharSequence) receiver, 0, index, (CharSequence) replacement).toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String replaceAfter$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return StringsKt.replaceAfter(str, c, str2, str3);
    }

    @NotNull
    public static final String replaceAfter(@NotNull String receiver, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        int i = index + 1;
        int length = receiver.length();
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.replaceRange((CharSequence) receiver, i, length, (CharSequence) replacement).toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String replaceAfter$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return StringsKt.replaceAfter(str, str2, str3, str4);
    }

    @NotNull
    public static final String replaceAfter(@NotNull String receiver, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.indexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        int length = delimiter.length() + index;
        int length2 = receiver.length();
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.replaceRange((CharSequence) receiver, length, length2, (CharSequence) replacement).toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String replaceAfterLast$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return StringsKt.replaceAfterLast(str, str2, str3, str4);
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String receiver, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        int length = delimiter.length() + index;
        int length2 = receiver.length();
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.replaceRange((CharSequence) receiver, length, length2, (CharSequence) replacement).toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String replaceAfterLast$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return StringsKt.replaceAfterLast(str, c, str2, str3);
    }

    @NotNull
    public static final String replaceAfterLast(@NotNull String receiver, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        int i = index + 1;
        int length = receiver.length();
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.replaceRange((CharSequence) receiver, i, length, (CharSequence) replacement).toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String replaceBeforeLast$default(String str, char c, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = str;
        }
        return StringsKt.replaceBeforeLast(str, c, str2, str3);
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String receiver, char delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.replaceRange((CharSequence) receiver, 0, index, (CharSequence) replacement).toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String replaceBeforeLast$default(String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 4) != 0) {
            str4 = str;
        }
        return StringsKt.replaceBeforeLast(str, str2, str3, str4);
    }

    @NotNull
    public static final String replaceBeforeLast(@NotNull String receiver, @NotNull String delimiter, @NotNull String replacement, @NotNull String missingDelimiterValue) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiter, "delimiter");
        Intrinsics.checkParameterIsNotNull(replacement, "replacement");
        Intrinsics.checkParameterIsNotNull(missingDelimiterValue, "missingDelimiterValue");
        int index = StringsKt.lastIndexOf$default((CharSequence) receiver, delimiter, 0, false, 6, (Object) null);
        if (index == -1) {
            return missingDelimiterValue;
        }
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.replaceRange((CharSequence) receiver, 0, index, (CharSequence) replacement).toString();
    }

    @InlineOnly
    private static final String replace(@NotNull CharSequence $receiver, Regex regex, String replacement) {
        return regex.replace($receiver, replacement);
    }

    @InlineOnly
    private static final String replace(@NotNull CharSequence $receiver, Regex regex, Function1<? super MatchResult, ? extends CharSequence> function1) {
        return regex.replace($receiver, function1);
    }

    @InlineOnly
    private static final String replaceFirst(@NotNull CharSequence $receiver, Regex regex, String replacement) {
        return regex.replaceFirst($receiver, replacement);
    }

    @InlineOnly
    private static final boolean matches(@NotNull CharSequence $receiver, Regex regex) {
        return regex.matches($receiver);
    }

    public static final boolean regionMatchesImpl(@NotNull CharSequence receiver, int thisOffset, @NotNull CharSequence other, int otherOffset, int length, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        if (otherOffset < 0 || thisOffset < 0 || thisOffset > receiver.length() - length || otherOffset > other.length() - length) {
            return false;
        }
        int i = length - 1;
        if (i >= 0) {
            for (int index = 0; CharsKt.equals(receiver.charAt(thisOffset + index), other.charAt(otherOffset + index), ignoreCase); index++) {
                if (index == i) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static /* bridge */ /* synthetic */ boolean startsWith$default(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.startsWith(charSequence, c, z);
    }

    public static final boolean startsWith(@NotNull CharSequence receiver, char c, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.length() > 0 && CharsKt.equals(receiver.charAt(0), c, ignoreCase);
    }

    public static /* bridge */ /* synthetic */ boolean endsWith$default(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.endsWith(charSequence, c, z);
    }

    public static final boolean endsWith(@NotNull CharSequence receiver, char c, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.length() > 0 && CharsKt.equals(receiver.charAt(StringsKt.getLastIndex(receiver)), c, ignoreCase);
    }

    public static /* bridge */ /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.startsWith(charSequence, charSequence2, z);
    }

    public static final boolean startsWith(@NotNull CharSequence receiver, @NotNull CharSequence prefix, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (!ignoreCase && (receiver instanceof String) && (prefix instanceof String)) {
            return StringsKt.startsWith$default((String) receiver, (String) prefix, false, 2, (Object) null);
        }
        return StringsKt.regionMatchesImpl(receiver, 0, prefix, 0, prefix.length(), ignoreCase);
    }

    public static /* bridge */ /* synthetic */ boolean startsWith$default(CharSequence charSequence, CharSequence charSequence2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.startsWith(charSequence, charSequence2, i, z);
    }

    public static final boolean startsWith(@NotNull CharSequence receiver, @NotNull CharSequence prefix, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        if (!ignoreCase && (receiver instanceof String) && (prefix instanceof String)) {
            return StringsKt.startsWith$default((String) receiver, (String) prefix, startIndex, false, 4, (Object) null);
        }
        return StringsKt.regionMatchesImpl(receiver, startIndex, prefix, 0, prefix.length(), ignoreCase);
    }

    public static /* bridge */ /* synthetic */ boolean endsWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.endsWith(charSequence, charSequence2, z);
    }

    public static final boolean endsWith(@NotNull CharSequence receiver, @NotNull CharSequence suffix, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(suffix, "suffix");
        if (!ignoreCase && (receiver instanceof String) && (suffix instanceof String)) {
            return StringsKt.endsWith$default((String) receiver, (String) suffix, false, 2, (Object) null);
        }
        return StringsKt.regionMatchesImpl(receiver, receiver.length() - suffix.length(), suffix, 0, suffix.length(), ignoreCase);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String commonPrefixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.commonPrefixWith(charSequence, charSequence2, z);
    }

    @NotNull
    public static final String commonPrefixWith(@NotNull CharSequence receiver, @NotNull CharSequence other, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        int shortestLength = Math.min(receiver.length(), other.length());
        int i = 0;
        while (i < shortestLength && CharsKt.equals(receiver.charAt(i), other.charAt(i), ignoreCase)) {
            i++;
        }
        if (StringsKt.hasSurrogatePairAt(receiver, i - 1) || StringsKt.hasSurrogatePairAt(other, i - 1)) {
            i--;
        }
        return receiver.subSequence(0, i).toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String commonSuffixWith$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.commonSuffixWith(charSequence, charSequence2, z);
    }

    @NotNull
    public static final String commonSuffixWith(@NotNull CharSequence receiver, @NotNull CharSequence other, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        int thisLength = receiver.length();
        int otherLength = other.length();
        int shortestLength = Math.min(thisLength, otherLength);
        int i = 0;
        while (i < shortestLength && CharsKt.equals(receiver.charAt((thisLength - i) - 1), other.charAt((otherLength - i) - 1), ignoreCase)) {
            i++;
        }
        if (StringsKt.hasSurrogatePairAt(receiver, (thisLength - i) - 1) || StringsKt.hasSurrogatePairAt(other, (otherLength - i) - 1)) {
            i--;
        }
        return receiver.subSequence(thisLength - i, thisLength).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Incorrect condition in loop: B:38:0x0096 */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a6 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlin.Pair<java.lang.Integer, java.lang.Character> findAnyOf$StringsKt__StringsKt(@org.jetbrains.annotations.NotNull java.lang.CharSequence r17, char[] r18, int r19, boolean r20, boolean r21) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = 0
            r5 = 1
            if (r3 != 0) goto L50
            int r6 = r1.length
            if (r6 != r5) goto L50
            boolean r6 = r0 instanceof java.lang.String
            if (r6 == 0) goto L50
            char r5 = kotlin.collections.ArraysKt.single(r18)
            if (r21 != 0) goto L2d
            r6 = r0
            java.lang.String r6 = (java.lang.String) r6
            if (r6 != 0) goto L26
            kotlin.TypeCastException r4 = new kotlin.TypeCastException
            java.lang.String r6 = "null cannot be cast to non-null type java.lang.String"
            r4.<init>(r6)
            throw r4
        L26:
            java.lang.String r6 = (java.lang.String) r6
            int r6 = r6.indexOf(r5, r2)
            goto L40
        L2d:
            r6 = r0
            java.lang.String r6 = (java.lang.String) r6
            if (r6 != 0) goto L3a
            kotlin.TypeCastException r4 = new kotlin.TypeCastException
            java.lang.String r6 = "null cannot be cast to non-null type java.lang.String"
            r4.<init>(r6)
            throw r4
        L3a:
            java.lang.String r6 = (java.lang.String) r6
            int r6 = r6.lastIndexOf(r5, r2)
        L40:
            if (r6 >= 0) goto L43
            goto L4f
        L43:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
            java.lang.Character r7 = java.lang.Character.valueOf(r5)
            kotlin.Pair r4 = kotlin.TuplesKt.to(r4, r7)
        L4f:
            return r4
        L50:
            r6 = 0
            if (r21 != 0) goto L63
            int r7 = kotlin.ranges.RangesKt.coerceAtLeast(r2, r6)
            kotlin.ranges.IntRange r8 = new kotlin.ranges.IntRange
            int r9 = kotlin.text.StringsKt.getLastIndex(r17)
            r8.<init>(r7, r9)
            kotlin.ranges.IntProgression r8 = (kotlin.ranges.IntProgression) r8
            goto L6f
        L63:
            int r7 = kotlin.text.StringsKt.getLastIndex(r17)
            int r7 = kotlin.ranges.RangesKt.coerceAtMost(r2, r7)
            kotlin.ranges.IntProgression r8 = kotlin.ranges.RangesKt.downTo(r7, r6)
        L6f:
            r7 = r8
            int r8 = r7.getFirst()
            int r9 = r7.getLast()
            int r10 = r7.getStep()
            if (r10 <= 0) goto L81
            if (r8 > r9) goto Lbe
            goto L83
        L81:
            if (r8 < r9) goto Lbe
        L83:
            r11 = r6
            r12 = r11
        L85:
            char r13 = r0.charAt(r8)
            r14 = r1
            int r6 = r14.length
            int r6 = r6 - r5
            if (r6 < 0) goto La0
            r15 = r12
            r12 = 0
        L90:
            char r5 = r14[r12]
            boolean r5 = kotlin.text.CharsKt.equals(r5, r13, r3)
            if (r5 == 0) goto L99
            goto La3
        L99:
            if (r12 == r6) goto L9f
            int r12 = r12 + 1
            r5 = 1
            goto L90
        L9f:
            r12 = r15
        La0:
            r5 = -1
            r15 = r12
            r12 = r5
        La3:
            r5 = r12
            if (r5 < 0) goto Lb5
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)
            char r6 = r1[r5]
            java.lang.Character r6 = java.lang.Character.valueOf(r6)
            kotlin.Pair r4 = kotlin.TuplesKt.to(r4, r6)
            return r4
        Lb5:
            if (r8 == r9) goto Lbe
            int r5 = r8 + r10
            r8 = r5
            r12 = r15
            r5 = 1
            r6 = 0
            goto L85
        Lbe:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt(java.lang.CharSequence, char[], int, boolean, boolean):kotlin.Pair");
    }

    public static /* bridge */ /* synthetic */ int indexOfAny$default(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.indexOfAny(charSequence, cArr, i, z);
    }

    public static final int indexOfAny(@NotNull CharSequence receiver, @NotNull char[] chars, int startIndex, boolean ignoreCase) {
        Integer first;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        Pair<Integer, Character> pairFindAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt(receiver, chars, startIndex, ignoreCase, false);
        if (pairFindAnyOf$StringsKt__StringsKt == null || (first = pairFindAnyOf$StringsKt__StringsKt.getFirst()) == null) {
            return -1;
        }
        return first.intValue();
    }

    public static /* bridge */ /* synthetic */ int lastIndexOfAny$default(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.lastIndexOfAny(charSequence, cArr, i, z);
    }

    public static final int lastIndexOfAny(@NotNull CharSequence receiver, @NotNull char[] chars, int startIndex, boolean ignoreCase) {
        Integer first;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(chars, "chars");
        Pair<Integer, Character> pairFindAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt(receiver, chars, startIndex, ignoreCase, true);
        if (pairFindAnyOf$StringsKt__StringsKt == null || (first = pairFindAnyOf$StringsKt__StringsKt.getFirst()) == null) {
            return -1;
        }
        return first.intValue();
    }

    static /* bridge */ /* synthetic */ int indexOf$StringsKt__StringsKt$default(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2, int i3, Object obj) {
        if ((i3 & 16) != 0) {
            z2 = false;
        }
        return indexOf$StringsKt__StringsKt(charSequence, charSequence2, i, i2, z, z2);
    }

    private static final int indexOf$StringsKt__StringsKt(@NotNull CharSequence $receiver, CharSequence other, int startIndex, int endIndex, boolean ignoreCase, boolean last) {
        IntRange intRangeDownTo;
        if (last) {
            intRangeDownTo = RangesKt.downTo(RangesKt.coerceAtMost(startIndex, StringsKt.getLastIndex($receiver)), RangesKt.coerceAtLeast(endIndex, 0));
        } else {
            intRangeDownTo = new IntRange(RangesKt.coerceAtLeast(startIndex, 0), RangesKt.coerceAtMost(endIndex, $receiver.length()));
        }
        IntProgression indices = intRangeDownTo;
        if (($receiver instanceof String) && (other instanceof String)) {
            int index = indices.getFirst();
            int last2 = indices.getLast();
            int step = indices.getStep();
            if (step > 0) {
                if (index > last2) {
                    return -1;
                }
            } else if (index < last2) {
                return -1;
            }
            while (!StringsKt.regionMatches((String) other, 0, (String) $receiver, index, other.length(), ignoreCase)) {
                if (index != last2) {
                    index += step;
                } else {
                    return -1;
                }
            }
            return index;
        }
        int index2 = indices.getFirst();
        int last3 = indices.getLast();
        int step2 = indices.getStep();
        if (step2 > 0) {
            if (index2 > last3) {
                return -1;
            }
        } else if (index2 < last3) {
            return -1;
        }
        while (!StringsKt.regionMatchesImpl(other, 0, $receiver, index2, other.length(), ignoreCase)) {
            if (index2 != last3) {
                index2 += step2;
            } else {
                return -1;
            }
        }
        return index2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair<Integer, String> findAnyOf$StringsKt__StringsKt(@NotNull CharSequence $receiver, Collection<String> collection, int startIndex, boolean ignoreCase, boolean last) {
        Object element$iv;
        Object element$iv2;
        if (!ignoreCase && collection.size() == 1) {
            String string = (String) CollectionsKt.single(collection);
            int index = !last ? StringsKt.indexOf$default($receiver, string, startIndex, false, 4, (Object) null) : StringsKt.lastIndexOf$default($receiver, string, startIndex, false, 4, (Object) null);
            if (index < 0) {
                return null;
            }
            return TuplesKt.to(Integer.valueOf(index), string);
        }
        int $i$f$firstOrNull = 0;
        IntProgression indices = !last ? new IntRange(RangesKt.coerceAtLeast(startIndex, 0), $receiver.length()) : RangesKt.downTo(RangesKt.coerceAtMost(startIndex, StringsKt.getLastIndex($receiver)), 0);
        if ($receiver instanceof String) {
            int index2 = indices.getFirst();
            int last2 = indices.getLast();
            int step = indices.getStep();
            if (step <= 0 ? index2 >= last2 : index2 <= last2) {
                int $i$a$1$firstOrNull = 0;
                while (true) {
                    int index3 = index2;
                    Collection<String> $receiver$iv = collection;
                    int $i$f$firstOrNull2 = $i$f$firstOrNull;
                    Iterator it = $receiver$iv.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            element$iv2 = null;
                            break;
                        }
                        element$iv2 = it.next();
                        String it2 = (String) element$iv2;
                        int i = $i$a$1$firstOrNull;
                        Iterator it3 = it;
                        Iterable $receiver$iv2 = $receiver$iv;
                        if (StringsKt.regionMatches(it2, 0, (String) $receiver, index3, it2.length(), ignoreCase)) {
                            $i$a$1$firstOrNull = i;
                            break;
                        }
                        $i$a$1$firstOrNull = i;
                        $receiver$iv = $receiver$iv2;
                        it = it3;
                    }
                    String matchingString = (String) element$iv2;
                    if (matchingString == null) {
                        if (index3 == last2) {
                            break;
                        }
                        index2 = index3 + step;
                        $i$f$firstOrNull = $i$f$firstOrNull2;
                    } else {
                        return TuplesKt.to(Integer.valueOf(index3), matchingString);
                    }
                }
            }
        } else {
            int index4 = indices.getFirst();
            int last3 = indices.getLast();
            int step2 = indices.getStep();
            if (step2 <= 0 ? index4 >= last3 : index4 <= last3) {
                int $i$a$2$firstOrNull = 0;
                while (true) {
                    int index5 = index4;
                    Collection<String> $receiver$iv3 = collection;
                    int $i$f$firstOrNull3 = $i$f$firstOrNull;
                    Iterator it4 = $receiver$iv3.iterator();
                    while (true) {
                        if (!it4.hasNext()) {
                            element$iv = null;
                            break;
                        }
                        element$iv = it4.next();
                        String it5 = (String) element$iv;
                        int i2 = $i$a$2$firstOrNull;
                        Iterator it6 = it4;
                        Iterable $receiver$iv4 = $receiver$iv3;
                        if (StringsKt.regionMatchesImpl(it5, 0, $receiver, index5, it5.length(), ignoreCase)) {
                            $i$a$2$firstOrNull = i2;
                            break;
                        }
                        $i$a$2$firstOrNull = i2;
                        $receiver$iv3 = $receiver$iv4;
                        it4 = it6;
                    }
                    String matchingString2 = (String) element$iv;
                    if (matchingString2 == null) {
                        if (index5 == last3) {
                            break;
                        }
                        index4 = index5 + step2;
                        $i$f$firstOrNull = $i$f$firstOrNull3;
                    } else {
                        return TuplesKt.to(Integer.valueOf(index5), matchingString2);
                    }
                }
            }
        }
        return null;
    }

    @Nullable
    public static /* bridge */ /* synthetic */ Pair findAnyOf$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.findAnyOf(charSequence, collection, i, z);
    }

    @Nullable
    public static final Pair<Integer, String> findAnyOf(@NotNull CharSequence receiver, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(strings, "strings");
        return findAnyOf$StringsKt__StringsKt(receiver, strings, startIndex, ignoreCase, false);
    }

    @Nullable
    public static /* bridge */ /* synthetic */ Pair findLastAnyOf$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.findLastAnyOf(charSequence, collection, i, z);
    }

    @Nullable
    public static final Pair<Integer, String> findLastAnyOf(@NotNull CharSequence receiver, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(strings, "strings");
        return findAnyOf$StringsKt__StringsKt(receiver, strings, startIndex, ignoreCase, true);
    }

    public static /* bridge */ /* synthetic */ int indexOfAny$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.indexOfAny(charSequence, (Collection<String>) collection, i, z);
    }

    public static final int indexOfAny(@NotNull CharSequence receiver, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Integer first;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(strings, "strings");
        Pair<Integer, String> pairFindAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt(receiver, strings, startIndex, ignoreCase, false);
        if (pairFindAnyOf$StringsKt__StringsKt == null || (first = pairFindAnyOf$StringsKt__StringsKt.getFirst()) == null) {
            return -1;
        }
        return first.intValue();
    }

    public static /* bridge */ /* synthetic */ int lastIndexOfAny$default(CharSequence charSequence, Collection collection, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.lastIndexOfAny(charSequence, (Collection<String>) collection, i, z);
    }

    public static final int lastIndexOfAny(@NotNull CharSequence receiver, @NotNull Collection<String> strings, int startIndex, boolean ignoreCase) {
        Integer first;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(strings, "strings");
        Pair<Integer, String> pairFindAnyOf$StringsKt__StringsKt = findAnyOf$StringsKt__StringsKt(receiver, strings, startIndex, ignoreCase, true);
        if (pairFindAnyOf$StringsKt__StringsKt == null || (first = pairFindAnyOf$StringsKt__StringsKt.getFirst()) == null) {
            return -1;
        }
        return first.intValue();
    }

    public static /* bridge */ /* synthetic */ int indexOf$default(CharSequence charSequence, char c, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.indexOf(charSequence, c, i, z);
    }

    public static final int indexOf(@NotNull CharSequence receiver, char c, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (ignoreCase || !(receiver instanceof String)) {
            return StringsKt.indexOfAny(receiver, new char[]{c}, startIndex, ignoreCase);
        }
        String str = (String) receiver;
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        return str.indexOf(c, startIndex);
    }

    public static /* bridge */ /* synthetic */ int indexOf$default(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.indexOf(charSequence, str, i, z);
    }

    public static final int indexOf(@NotNull CharSequence receiver, @NotNull String string, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(string, "string");
        if (ignoreCase || !(receiver instanceof String)) {
            return indexOf$StringsKt__StringsKt$default(receiver, string, startIndex, receiver.length(), ignoreCase, false, 16, null);
        }
        String str = (String) receiver;
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        return str.indexOf(string, startIndex);
    }

    public static /* bridge */ /* synthetic */ int lastIndexOf$default(CharSequence charSequence, char c, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.lastIndexOf(charSequence, c, i, z);
    }

    public static final int lastIndexOf(@NotNull CharSequence receiver, char c, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (ignoreCase || !(receiver instanceof String)) {
            return StringsKt.lastIndexOfAny(receiver, new char[]{c}, startIndex, ignoreCase);
        }
        String str = (String) receiver;
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        return str.lastIndexOf(c, startIndex);
    }

    public static /* bridge */ /* synthetic */ int lastIndexOf$default(CharSequence charSequence, String str, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = StringsKt.getLastIndex(charSequence);
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        return StringsKt.lastIndexOf(charSequence, str, i, z);
    }

    public static final int lastIndexOf(@NotNull CharSequence receiver, @NotNull String string, int startIndex, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(string, "string");
        if (ignoreCase || !(receiver instanceof String)) {
            return indexOf$StringsKt__StringsKt(receiver, string, startIndex, 0, ignoreCase, true);
        }
        String str = (String) receiver;
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        return str.lastIndexOf(string, startIndex);
    }

    public static /* bridge */ /* synthetic */ boolean contains$default(CharSequence charSequence, CharSequence charSequence2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.contains(charSequence, charSequence2, z);
    }

    public static final boolean contains(@NotNull CharSequence receiver, @NotNull CharSequence other, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        if (other instanceof String) {
            if (StringsKt.indexOf$default(receiver, (String) other, 0, ignoreCase, 2, (Object) null) < 0) {
                return false;
            }
        } else if (indexOf$StringsKt__StringsKt$default(receiver, other, 0, receiver.length(), ignoreCase, false, 16, null) < 0) {
            return false;
        }
        return true;
    }

    public static /* bridge */ /* synthetic */ boolean contains$default(CharSequence charSequence, char c, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return StringsKt.contains(charSequence, c, z);
    }

    public static final boolean contains(@NotNull CharSequence receiver, char c, boolean ignoreCase) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return StringsKt.indexOf$default(receiver, c, 0, ignoreCase, 2, (Object) null) >= 0;
    }

    @InlineOnly
    private static final boolean contains(@NotNull CharSequence $receiver, Regex regex) {
        return regex.containsMatchIn($receiver);
    }

    static /* bridge */ /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, char[] cArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return rangesDelimitedBy$StringsKt__StringsKt(charSequence, cArr, i, z, i2);
    }

    private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(@NotNull CharSequence $receiver, final char[] delimiters, int startIndex, final boolean ignoreCase, int limit) {
        if (!(limit >= 0)) {
            throw new IllegalArgumentException(("Limit must be non-negative, but was " + limit + ".").toString());
        }
        return new DelimitedRangesSequence($receiver, startIndex, limit, new Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Pair<? extends Integer, ? extends Integer> invoke(CharSequence charSequence, Integer num) {
                return invoke(charSequence, num.intValue());
            }

            @Nullable
            public final Pair<Integer, Integer> invoke(@NotNull CharSequence receiver, int startIndex2) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                Pair pairFindAnyOf$StringsKt__StringsKt = StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt(receiver, delimiters, startIndex2, ignoreCase, false);
                if (pairFindAnyOf$StringsKt__StringsKt == null) {
                    return null;
                }
                Pair it = pairFindAnyOf$StringsKt__StringsKt;
                return TuplesKt.to(it.getFirst(), 1);
            }
        });
    }

    static /* bridge */ /* synthetic */ Sequence rangesDelimitedBy$StringsKt__StringsKt$default(CharSequence charSequence, String[] strArr, int i, boolean z, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return rangesDelimitedBy$StringsKt__StringsKt(charSequence, strArr, i, z, i2);
    }

    private static final Sequence<IntRange> rangesDelimitedBy$StringsKt__StringsKt(@NotNull CharSequence $receiver, String[] delimiters, int startIndex, final boolean ignoreCase, int limit) {
        if (!(limit >= 0)) {
            throw new IllegalArgumentException(("Limit must be non-negative, but was " + limit + ".").toString());
        }
        final List delimitersList = ArraysKt.asList(delimiters);
        return new DelimitedRangesSequence($receiver, startIndex, limit, new Function2<CharSequence, Integer, Pair<? extends Integer, ? extends Integer>>() { // from class: kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Pair<? extends Integer, ? extends Integer> invoke(CharSequence charSequence, Integer num) {
                return invoke(charSequence, num.intValue());
            }

            @Nullable
            public final Pair<Integer, Integer> invoke(@NotNull CharSequence receiver, int startIndex2) {
                Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                Pair pairFindAnyOf$StringsKt__StringsKt = StringsKt__StringsKt.findAnyOf$StringsKt__StringsKt(receiver, (Collection<String>) delimitersList, startIndex2, ignoreCase, false);
                if (pairFindAnyOf$StringsKt__StringsKt == null) {
                    return null;
                }
                Pair it = pairFindAnyOf$StringsKt__StringsKt;
                return TuplesKt.to(it.getFirst(), Integer.valueOf(((String) it.getSecond()).length()));
            }
        });
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return StringsKt.splitToSequence(charSequence, strArr, z, i);
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull final CharSequence receiver, @NotNull String[] delimiters, boolean ignoreCase, int limit) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiters, "delimiters");
        return SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(receiver, delimiters, 0, ignoreCase, limit, 2, (Object) null), new Function1<IntRange, String>() { // from class: kotlin.text.StringsKt__StringsKt.splitToSequence.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final String invoke(@NotNull IntRange it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return StringsKt.substring(receiver, it);
            }
        });
    }

    @NotNull
    public static /* bridge */ /* synthetic */ List split$default(CharSequence charSequence, String[] strArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return StringsKt.split(charSequence, strArr, z, i);
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence receiver, @NotNull String[] delimiters, boolean ignoreCase, int limit) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiters, "delimiters");
        Iterable $receiver$iv = SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default(receiver, delimiters, 0, ignoreCase, limit, 2, (Object) null));
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($receiver$iv, 10));
        for (Object item$iv$iv : $receiver$iv) {
            IntRange it = (IntRange) item$iv$iv;
            destination$iv$iv.add(StringsKt.substring(receiver, it));
        }
        return (List) destination$iv$iv;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Sequence splitToSequence$default(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return StringsKt.splitToSequence(charSequence, cArr, z, i);
    }

    @NotNull
    public static final Sequence<String> splitToSequence(@NotNull final CharSequence receiver, @NotNull char[] delimiters, boolean ignoreCase, int limit) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiters, "delimiters");
        return SequencesKt.map(rangesDelimitedBy$StringsKt__StringsKt$default(receiver, delimiters, 0, ignoreCase, limit, 2, (Object) null), new Function1<IntRange, String>() { // from class: kotlin.text.StringsKt__StringsKt.splitToSequence.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final String invoke(@NotNull IntRange it) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                return StringsKt.substring(receiver, it);
            }
        });
    }

    @NotNull
    public static /* bridge */ /* synthetic */ List split$default(CharSequence charSequence, char[] cArr, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        return StringsKt.split(charSequence, cArr, z, i);
    }

    @NotNull
    public static final List<String> split(@NotNull CharSequence receiver, @NotNull char[] delimiters, boolean ignoreCase, int limit) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(delimiters, "delimiters");
        Iterable $receiver$iv = SequencesKt.asIterable(rangesDelimitedBy$StringsKt__StringsKt$default(receiver, delimiters, 0, ignoreCase, limit, 2, (Object) null));
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($receiver$iv, 10));
        for (Object item$iv$iv : $receiver$iv) {
            IntRange it = (IntRange) item$iv$iv;
            destination$iv$iv.add(StringsKt.substring(receiver, it));
        }
        return (List) destination$iv$iv;
    }

    @InlineOnly
    private static final List<String> split(@NotNull CharSequence $receiver, Regex regex, int limit) {
        return regex.split($receiver, limit);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ List split$default(CharSequence $receiver, Regex regex, int limit, int i, Object obj) {
        if ((i & 2) != 0) {
            limit = 0;
        }
        return regex.split($receiver, limit);
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return StringsKt.splitToSequence$default(receiver, new String[]{"\r\n", "\n", "\r"}, false, 0, 6, (Object) null);
    }

    @NotNull
    public static final List<String> lines(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return SequencesKt.toList(StringsKt.lineSequence(receiver));
    }
}
