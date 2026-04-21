package kotlin.text;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CharIterator;
import kotlin.collections.CollectionsKt;
import kotlin.collections.Grouping;
import kotlin.collections.IndexedValue;
import kotlin.collections.IndexingIterable;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: _Strings.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000â\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u001f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u000f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a!\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a!\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0010\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\b*\u00020\u0002\u001a\u0010\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n*\u00020\u0002\u001aE\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u00100\u0004H\u0086\b\u001a3\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u00020\u00050\f\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0086\b\u001aM\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0086\b\u001aN\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0018\b\u0001\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u00020\u00050\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0086\b¢\u0006\u0002\u0010\u0018\u001ah\u0010\u0014\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0018\b\u0002\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0086\b¢\u0006\u0002\u0010\u0019\u001a`\u0010\u001a\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u0018\b\u0002\u0010\u0015*\u0012\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\u0006\b\u0000\u0012\u0002H\u000e0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u00100\u0004H\u0086\b¢\u0006\u0002\u0010\u0018\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0002H\u0087\b\u001a!\u0010\u001b\u001a\u00020\u001c*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0012\u0010\u001d\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001c\u001a\u0012\u0010\u001d\u001a\u00020\u001f*\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001c\u001a\u0012\u0010 \u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001c\u001a\u0012\u0010 \u001a\u00020\u001f*\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001c\u001a!\u0010!\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010!\u001a\u00020\u001f*\u00020\u001f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010\"\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010\"\u001a\u00020\u001f*\u00020\u001f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0015\u0010#\u001a\u00020\u0005*\u00020\u00022\u0006\u0010$\u001a\u00020\u001cH\u0087\b\u001a)\u0010%\u001a\u00020\u0005*\u00020\u00022\u0006\u0010$\u001a\u00020\u001c2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00050\u0004H\u0087\b\u001a\u001c\u0010'\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010$\u001a\u00020\u001cH\u0087\b¢\u0006\u0002\u0010(\u001a!\u0010)\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010)\u001a\u00020\u001f*\u00020\u001f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a6\u0010*\u001a\u00020\u0002*\u00020\u00022'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010+H\u0086\b\u001a6\u0010*\u001a\u00020\u001f*\u00020\u001f2'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010+H\u0086\b\u001aQ\u0010.\u001a\u0002H/\"\f\b\u0000\u0010/*\u000600j\u0002`1*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2'\u0010\u0003\u001a#\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010+H\u0086\b¢\u0006\u0002\u00102\u001a!\u00103\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u00103\u001a\u00020\u001f*\u00020\u001f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a<\u00104\u001a\u0002H/\"\f\b\u0000\u0010/*\u000600j\u0002`1*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u00105\u001a<\u00106\u001a\u0002H/\"\f\b\u0000\u0010/*\u000600j\u0002`1*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u00105\u001a(\u00107\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0087\b¢\u0006\u0002\u00108\u001a(\u00109\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0087\b¢\u0006\u0002\u00108\u001a\n\u0010:\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010:\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0011\u0010;\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010<\u001a(\u0010;\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u00108\u001a3\u0010=\u001a\b\u0012\u0004\u0012\u0002H?0>\"\u0004\b\u0000\u0010?*\u00020\u00022\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H?0\b0\u0004H\u0086\b\u001aL\u0010@\u001a\u0002H/\"\u0004\b\u0000\u0010?\"\u0010\b\u0001\u0010/*\n\u0012\u0006\b\u0000\u0012\u0002H?0A*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H?0\b0\u0004H\u0086\b¢\u0006\u0002\u0010B\u001aI\u0010C\u001a\u0002H?\"\u0004\b\u0000\u0010?*\u00020\u00022\u0006\u0010D\u001a\u0002H?2'\u0010E\u001a#\u0012\u0013\u0012\u0011H?¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0+H\u0086\b¢\u0006\u0002\u0010G\u001a^\u0010H\u001a\u0002H?\"\u0004\b\u0000\u0010?*\u00020\u00022\u0006\u0010D\u001a\u0002H?2<\u0010E\u001a8\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0013\u0012\u0011H?¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0IH\u0086\b¢\u0006\u0002\u0010J\u001aI\u0010K\u001a\u0002H?\"\u0004\b\u0000\u0010?*\u00020\u00022\u0006\u0010D\u001a\u0002H?2'\u0010E\u001a#\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u0011H?¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u0002H?0+H\u0086\b¢\u0006\u0002\u0010G\u001a^\u0010L\u001a\u0002H?\"\u0004\b\u0000\u0010?*\u00020\u00022\u0006\u0010D\u001a\u0002H?2<\u0010E\u001a8\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u0011H?¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u0002H?0IH\u0086\b¢\u0006\u0002\u0010J\u001a!\u0010M\u001a\u00020N*\u00020\u00022\u0012\u0010O\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020N0\u0004H\u0086\b\u001a6\u0010P\u001a\u00020N*\u00020\u00022'\u0010O\u001a#\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020N0+H\u0086\b\u001a)\u0010Q\u001a\u00020\u0005*\u00020\u00022\u0006\u0010$\u001a\u00020\u001c2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00050\u0004H\u0087\b\u001a\u0019\u0010R\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0006\u0010$\u001a\u00020\u001c¢\u0006\u0002\u0010(\u001a9\u0010S\u001a\u0014\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050>0\f\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0086\b\u001aS\u0010S\u001a\u0014\u0012\u0004\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0>0\f\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e*\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0086\b\u001aR\u0010T\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u001c\b\u0001\u0010\u0015*\u0016\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050U0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0086\b¢\u0006\u0002\u0010\u0018\u001al\u0010T\u001a\u0002H\u0015\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e\"\u001c\b\u0002\u0010\u0015*\u0016\u0012\u0006\b\u0000\u0012\u0002H\r\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0U0\u0016*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H\u00152\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u000e0\u0004H\u0086\b¢\u0006\u0002\u0010\u0019\u001a5\u0010V\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0W\"\u0004\b\u0000\u0010\r*\u00020\u00022\u0014\b\u0004\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\r0\u0004H\u0087\b\u001a!\u0010X\u001a\u00020\u001c*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a!\u0010Y\u001a\u00020\u001c*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\n\u0010Z\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010Z\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0011\u0010[\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010<\u001a(\u0010[\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u00108\u001a-\u0010\\\u001a\b\u0012\u0004\u0012\u0002H?0>\"\u0004\b\u0000\u0010?*\u00020\u00022\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0\u0004H\u0086\b\u001aB\u0010]\u001a\b\u0012\u0004\u0012\u0002H?0>\"\u0004\b\u0000\u0010?*\u00020\u00022'\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0+H\u0086\b\u001aH\u0010^\u001a\b\u0012\u0004\u0012\u0002H?0>\"\b\b\u0000\u0010?*\u00020_*\u00020\u00022)\u0010\u000f\u001a%\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H?0+H\u0086\b\u001aa\u0010`\u001a\u0002H/\"\b\b\u0000\u0010?*\u00020_\"\u0010\b\u0001\u0010/*\n\u0012\u0006\b\u0000\u0012\u0002H?0A*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2)\u0010\u000f\u001a%\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H?0+H\u0086\b¢\u0006\u0002\u0010a\u001a[\u0010b\u001a\u0002H/\"\u0004\b\u0000\u0010?\"\u0010\b\u0001\u0010/*\n\u0012\u0006\b\u0000\u0012\u0002H?0A*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2'\u0010\u000f\u001a#\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0+H\u0086\b¢\u0006\u0002\u0010a\u001a3\u0010c\u001a\b\u0012\u0004\u0012\u0002H?0>\"\b\b\u0000\u0010?*\u00020_*\u00020\u00022\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H?0\u0004H\u0086\b\u001aL\u0010d\u001a\u0002H/\"\b\b\u0000\u0010?*\u00020_\"\u0010\b\u0001\u0010/*\n\u0012\u0006\b\u0000\u0012\u0002H?0A*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u0001H?0\u0004H\u0086\b¢\u0006\u0002\u0010B\u001aF\u0010e\u001a\u0002H/\"\u0004\b\u0000\u0010?\"\u0010\b\u0001\u0010/*\n\u0012\u0006\b\u0000\u0012\u0002H?0A*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0\u0004H\u0086\b¢\u0006\u0002\u0010B\u001a\u0011\u0010f\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010<\u001a8\u0010g\u001a\u0004\u0018\u00010\u0005\"\u000e\b\u0000\u0010?*\b\u0012\u0004\u0012\u0002H?0h*\u00020\u00022\u0012\u0010i\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0\u0004H\u0086\b¢\u0006\u0002\u00108\u001a-\u0010j\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u001a\u0010k\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00050lj\n\u0012\u0006\b\u0000\u0012\u00020\u0005`m¢\u0006\u0002\u0010n\u001a\u0011\u0010o\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010<\u001a8\u0010p\u001a\u0004\u0018\u00010\u0005\"\u000e\b\u0000\u0010?*\b\u0012\u0004\u0012\u0002H?0h*\u00020\u00022\u0012\u0010i\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H?0\u0004H\u0086\b¢\u0006\u0002\u00108\u001a-\u0010q\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u001a\u0010k\u001a\u0016\u0012\u0006\b\u0000\u0012\u00020\u00050lj\n\u0012\u0006\b\u0000\u0012\u00020\u0005`m¢\u0006\u0002\u0010n\u001a\n\u0010r\u001a\u00020\u0001*\u00020\u0002\u001a!\u0010r\u001a\u00020\u0001*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a0\u0010s\u001a\u0002Ht\"\b\b\u0000\u0010t*\u00020\u0002*\u0002Ht2\u0012\u0010O\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020N0\u0004H\u0087\b¢\u0006\u0002\u0010u\u001a-\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0010*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a-\u0010v\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001f0\u0010*\u00020\u001f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a6\u0010w\u001a\u00020\u0005*\u00020\u00022'\u0010E\u001a#\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050+H\u0086\b\u001aK\u0010x\u001a\u00020\u0005*\u00020\u00022<\u0010E\u001a8\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050IH\u0086\b\u001a6\u0010y\u001a\u00020\u0005*\u00020\u00022'\u0010E\u001a#\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00050+H\u0086\b\u001aK\u0010z\u001a\u00020\u0005*\u00020\u00022<\u0010E\u001a8\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u0005\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(F\u0012\u0004\u0012\u00020\u00050IH\u0086\b\u001a\n\u0010{\u001a\u00020\u0002*\u00020\u0002\u001a\r\u0010{\u001a\u00020\u001f*\u00020\u001fH\u0087\b\u001a\n\u0010|\u001a\u00020\u0005*\u00020\u0002\u001a!\u0010|\u001a\u00020\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\u0011\u0010}\u001a\u0004\u0018\u00010\u0005*\u00020\u0002¢\u0006\u0002\u0010<\u001a(\u0010}\u001a\u0004\u0018\u00010\u0005*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b¢\u0006\u0002\u00108\u001a\u0018\u0010~\u001a\u00020\u0002*\u00020\u00022\f\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020\u001c0\b\u001a\u0013\u0010~\u001a\u00020\u0002*\u00020\u00022\u0007\u0010\u007f\u001a\u00030\u0080\u0001\u001a\u001b\u0010~\u001a\u00020\u001f*\u00020\u001f2\f\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020\u001c0\bH\u0087\b\u001a\u0013\u0010~\u001a\u00020\u001f*\u00020\u001f2\u0007\u0010\u007f\u001a\u00030\u0080\u0001\u001a\"\u0010\u0081\u0001\u001a\u00020\u001c*\u00020\u00022\u0012\u0010i\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u001c0\u0004H\u0086\b\u001a$\u0010\u0082\u0001\u001a\u00030\u0083\u0001*\u00020\u00022\u0013\u0010i\u001a\u000f\u0012\u0004\u0012\u00020\u0005\u0012\u0005\u0012\u00030\u0083\u00010\u0004H\u0086\b\u001a\u0013\u0010\u0084\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001c\u001a\u0013\u0010\u0084\u0001\u001a\u00020\u001f*\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001c\u001a\u0013\u0010\u0085\u0001\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001c\u001a\u0013\u0010\u0085\u0001\u001a\u00020\u001f*\u00020\u001f2\u0006\u0010\u001e\u001a\u00020\u001c\u001a\"\u0010\u0086\u0001\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\"\u0010\u0086\u0001\u001a\u00020\u001f*\u00020\u001f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\"\u0010\u0087\u0001\u001a\u00020\u0002*\u00020\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a\"\u0010\u0087\u0001\u001a\u00020\u001f*\u00020\u001f2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004H\u0086\b\u001a+\u0010\u0088\u0001\u001a\u0002H/\"\u0010\b\u0000\u0010/*\n\u0012\u0006\b\u0000\u0012\u00020\u00050A*\u00020\u00022\u0006\u0010\u0017\u001a\u0002H/¢\u0006\u0003\u0010\u0089\u0001\u001a\u001d\u0010\u008a\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u00050\u008b\u0001j\t\u0012\u0004\u0012\u00020\u0005`\u008c\u0001*\u00020\u0002\u001a\u0011\u0010\u008d\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050>*\u00020\u0002\u001a\u0011\u0010\u008e\u0001\u001a\b\u0012\u0004\u0012\u00020\u00050U*\u00020\u0002\u001a\u0012\u0010\u008f\u0001\u001a\t\u0012\u0004\u0012\u00020\u00050\u0090\u0001*\u00020\u0002\u001a\u001f\u0010\u0091\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u00050\u0092\u0001j\t\u0012\u0004\u0012\u00020\u0005`\u0093\u0001*\u00020\u0002H\u0007\u001a\u0018\u0010\u0094\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u00050\u0095\u00010\b*\u00020\u0002\u001a)\u0010\u0096\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00100>*\u00020\u00022\u0007\u0010\u0097\u0001\u001a\u00020\u0002H\u0086\u0004\u001a]\u0010\u0096\u0001\u001a\b\u0012\u0004\u0012\u0002H\u000e0>\"\u0004\b\u0000\u0010\u000e*\u00020\u00022\u0007\u0010\u0097\u0001\u001a\u00020\u000228\u0010\u000f\u001a4\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b,\u0012\t\b-\u0012\u0005\b\b(\u0098\u0001\u0012\u0014\u0012\u00120\u0005¢\u0006\r\b,\u0012\t\b-\u0012\u0005\b\b(\u0099\u0001\u0012\u0004\u0012\u0002H\u000e0+H\u0086\b¨\u0006\u009a\u0001"}, d2 = {"all", "", "", "predicate", "Lkotlin/Function1;", "", "any", "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "associate", "", "K", "V", "transform", "Lkotlin/Pair;", "associateBy", "keySelector", "valueTransform", "associateByTo", "M", "", FirebaseAnalytics.Param.DESTINATION, "(Ljava/lang/CharSequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "(Ljava/lang/CharSequence;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "associateTo", "count", "", "drop", "n", "", "dropLast", "dropLastWhile", "dropWhile", "elementAt", FirebaseAnalytics.Param.INDEX, "elementAtOrElse", "defaultValue", "elementAtOrNull", "(Ljava/lang/CharSequence;I)Ljava/lang/Character;", "filter", "filterIndexed", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "filterIndexedTo", "C", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "(Ljava/lang/CharSequence;Ljava/lang/Appendable;Lkotlin/jvm/functions/Function2;)Ljava/lang/Appendable;", "filterNot", "filterNotTo", "(Ljava/lang/CharSequence;Ljava/lang/Appendable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Appendable;", "filterTo", "find", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Character;", "findLast", "first", "firstOrNull", "(Ljava/lang/CharSequence;)Ljava/lang/Character;", "flatMap", "", "R", "flatMapTo", "", "(Ljava/lang/CharSequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "fold", "initial", "operation", "acc", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "foldIndexed", "Lkotlin/Function3;", "(Ljava/lang/CharSequence;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "foldRight", "foldRightIndexed", "forEach", "", "action", "forEachIndexed", "getOrElse", "getOrNull", "groupBy", "groupByTo", "", "groupingBy", "Lkotlin/collections/Grouping;", "indexOfFirst", "indexOfLast", "last", "lastOrNull", "map", "mapIndexed", "mapIndexedNotNull", "", "mapIndexedNotNullTo", "(Ljava/lang/CharSequence;Ljava/util/Collection;Lkotlin/jvm/functions/Function2;)Ljava/util/Collection;", "mapIndexedTo", "mapNotNull", "mapNotNullTo", "mapTo", "max", "maxBy", "", "selector", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/CharSequence;Ljava/util/Comparator;)Ljava/lang/Character;", "min", "minBy", "minWith", "none", "onEach", "S", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/CharSequence;", "partition", "reduce", "reduceIndexed", "reduceRight", "reduceRightIndexed", "reversed", "single", "singleOrNull", "slice", "indices", "Lkotlin/ranges/IntRange;", "sumBy", "sumByDouble", "", "take", "takeLast", "takeLastWhile", "takeWhile", "toCollection", "(Ljava/lang/CharSequence;Ljava/util/Collection;)Ljava/util/Collection;", "toHashSet", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "toList", "toMutableList", "toSet", "", "toSortedSet", "Ljava/util/SortedSet;", "Lkotlin/collections/SortedSet;", "withIndex", "Lkotlin/collections/IndexedValue;", "zip", "other", "a", "b", "kotlin-stdlib"}, k = 5, mv = {1, 1, 5}, xi = 1, xs = "kotlin/text/StringsKt")
class StringsKt___StringsKt extends StringsKt__StringsKt {
    @InlineOnly
    private static final char elementAt(@NotNull CharSequence $receiver, int index) {
        return $receiver.charAt(index);
    }

    @InlineOnly
    private static final char elementAtOrElse(@NotNull CharSequence $receiver, int index, Function1<? super Integer, Character> function1) {
        return (index < 0 || index > StringsKt.getLastIndex($receiver)) ? function1.invoke(Integer.valueOf(index)).charValue() : $receiver.charAt(index);
    }

    @InlineOnly
    private static final Character elementAtOrNull(@NotNull CharSequence $receiver, int index) {
        return StringsKt.getOrNull($receiver, index);
    }

    @InlineOnly
    private static final Character find(@NotNull CharSequence $receiver, Function1<? super Character, Boolean> function1) {
        CharIterator it = StringsKt.iterator($receiver);
        while (it.hasNext()) {
            char element$iv = it.nextChar();
            if (function1.invoke(Character.valueOf(element$iv)).booleanValue()) {
                return Character.valueOf(element$iv);
            }
        }
        return null;
    }

    @InlineOnly
    private static final Character findLast(@NotNull CharSequence $receiver, Function1<? super Character, Boolean> function1) {
        IntProgression intProgressionReversed = RangesKt.reversed(StringsKt.getIndices($receiver));
        int index$iv = intProgressionReversed.getFirst();
        int last = intProgressionReversed.getLast();
        int step = intProgressionReversed.getStep();
        if (step <= 0 ? index$iv >= last : index$iv <= last) {
            while (true) {
                char element$iv = $receiver.charAt(index$iv);
                if (!function1.invoke(Character.valueOf(element$iv)).booleanValue()) {
                    if (index$iv == last) {
                        break;
                    }
                    index$iv += step;
                } else {
                    return Character.valueOf(element$iv);
                }
            }
        }
        return null;
    }

    public static final char first(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length() == 0) {
            throw new NoSuchElementException("Char sequence is empty.");
        }
        return receiver.charAt(0);
    }

    public static final char first(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            if (predicate.invoke(Character.valueOf(element)).booleanValue()) {
                return element;
            }
        }
        throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
    }

    @Nullable
    public static final Character firstOrNull(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length() == 0) {
            return null;
        }
        return Character.valueOf(receiver.charAt(0));
    }

    @Nullable
    public static final Character firstOrNull(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            if (predicate.invoke(Character.valueOf(element)).booleanValue()) {
                return Character.valueOf(element);
            }
        }
        return null;
    }

    @InlineOnly
    private static final char getOrElse(@NotNull CharSequence $receiver, int index, Function1<? super Integer, Character> function1) {
        return (index < 0 || index > StringsKt.getLastIndex($receiver)) ? function1.invoke(Integer.valueOf(index)).charValue() : $receiver.charAt(index);
    }

    @Nullable
    public static final Character getOrNull(@NotNull CharSequence receiver, int index) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (index < 0 || index > StringsKt.getLastIndex(receiver)) {
            return null;
        }
        return Character.valueOf(receiver.charAt(index));
    }

    public static final int indexOfFirst(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = receiver.length() - 1;
        if (length < 0) {
            return -1;
        }
        int index = 0;
        while (!predicate.invoke(Character.valueOf(receiver.charAt(index))).booleanValue()) {
            if (index == length) {
                return -1;
            }
            index++;
        }
        return index;
    }

    public static final int indexOfLast(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        IntProgression intProgressionReversed = RangesKt.reversed(StringsKt.getIndices(receiver));
        int index = intProgressionReversed.getFirst();
        int last = intProgressionReversed.getLast();
        int step = intProgressionReversed.getStep();
        if (step > 0) {
            if (index > last) {
                return -1;
            }
        } else if (index < last) {
            return -1;
        }
        while (!predicate.invoke(Character.valueOf(receiver.charAt(index))).booleanValue()) {
            if (index == last) {
                return -1;
            }
            index += step;
        }
        return index;
    }

    public static final char last(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length() == 0) {
            throw new NoSuchElementException("Char sequence is empty.");
        }
        return receiver.charAt(StringsKt.getLastIndex(receiver));
    }

    public static final char last(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        IntProgression intProgressionReversed = RangesKt.reversed(StringsKt.getIndices(receiver));
        int index = intProgressionReversed.getFirst();
        int last = intProgressionReversed.getLast();
        int step = intProgressionReversed.getStep();
        if (step <= 0 ? index >= last : index <= last) {
            while (true) {
                char element = receiver.charAt(index);
                if (!predicate.invoke(Character.valueOf(element)).booleanValue()) {
                    if (index == last) {
                        break;
                    }
                    index += step;
                } else {
                    return element;
                }
            }
        }
        throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
    }

    @Nullable
    public static final Character lastOrNull(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length() == 0) {
            return null;
        }
        return Character.valueOf(receiver.charAt(receiver.length() - 1));
    }

    @Nullable
    public static final Character lastOrNull(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        IntProgression intProgressionReversed = RangesKt.reversed(StringsKt.getIndices(receiver));
        int index = intProgressionReversed.getFirst();
        int last = intProgressionReversed.getLast();
        int step = intProgressionReversed.getStep();
        if (step > 0) {
            if (index > last) {
                return null;
            }
        } else if (index < last) {
            return null;
        }
        while (true) {
            char element = receiver.charAt(index);
            if (predicate.invoke(Character.valueOf(element)).booleanValue()) {
                return Character.valueOf(element);
            }
            if (index == last) {
                return null;
            }
            index += step;
        }
    }

    public static final char single(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        switch (receiver.length()) {
            case 0:
                throw new NoSuchElementException("Char sequence is empty.");
            case 1:
                return receiver.charAt(0);
            default:
                throw new IllegalArgumentException("Char sequence has more than one element.");
        }
    }

    public static final char single(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        Character single = (Character) null;
        boolean found = false;
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            if (predicate.invoke(Character.valueOf(element)).booleanValue()) {
                if (found) {
                    throw new IllegalArgumentException("Char sequence contains more than one matching element.");
                }
                single = Character.valueOf(element);
                found = true;
            }
        }
        if (!found) {
            throw new NoSuchElementException("Char sequence contains no character matching the predicate.");
        }
        if (single == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Char");
        }
        return single.charValue();
    }

    @Nullable
    public static final Character singleOrNull(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver.length() == 1) {
            return Character.valueOf(receiver.charAt(0));
        }
        return null;
    }

    @Nullable
    public static final Character singleOrNull(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        Character single = (Character) null;
        boolean found = false;
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            if (predicate.invoke(Character.valueOf(element)).booleanValue()) {
                if (found) {
                    return null;
                }
                single = Character.valueOf(element);
                found = true;
            }
        }
        if (!found) {
            return null;
        }
        return single;
    }

    @NotNull
    public static final CharSequence drop(@NotNull CharSequence receiver, int n) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(n >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + n + " is less than zero.").toString());
        }
        return receiver.subSequence(RangesKt.coerceAtMost(n, receiver.length()), receiver.length());
    }

    @NotNull
    public static final String drop(@NotNull String receiver, int n) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(n >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + n + " is less than zero.").toString());
        }
        int iCoerceAtMost = RangesKt.coerceAtMost(n, receiver.length());
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = receiver.substring(iCoerceAtMost);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }

    @NotNull
    public static final CharSequence dropLast(@NotNull CharSequence receiver, int n) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(n >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + n + " is less than zero.").toString());
        }
        return StringsKt.take(receiver, RangesKt.coerceAtLeast(receiver.length() - n, 0));
    }

    @NotNull
    public static final String dropLast(@NotNull String receiver, int n) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(n >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + n + " is less than zero.").toString());
        }
        return StringsKt.take(receiver, RangesKt.coerceAtLeast(receiver.length() - n, 0));
    }

    @NotNull
    public static final CharSequence dropLastWhile(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
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
            return receiver.subSequence(0, index + 1);
        }
        return "";
    }

    @NotNull
    public static final String dropLastWhile(@NotNull String receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        IntProgression intProgressionReversed = RangesKt.reversed(StringsKt.getIndices(receiver));
        int index = intProgressionReversed.getFirst();
        int last = intProgressionReversed.getLast();
        int step = intProgressionReversed.getStep();
        if (step > 0) {
            if (index > last) {
                return "";
            }
        } else if (index < last) {
            return "";
        }
        while (predicate.invoke(Character.valueOf(receiver.charAt(index))).booleanValue()) {
            if (index == last) {
                return "";
            }
            index += step;
        }
        int i = index + 1;
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = receiver.substring(0, i);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @NotNull
    public static final CharSequence dropWhile(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
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
    public static final String dropWhile(@NotNull String receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = receiver.length() - 1;
        if (length < 0) {
            return "";
        }
        int index = 0;
        while (predicate.invoke(Character.valueOf(receiver.charAt(index))).booleanValue()) {
            if (index == length) {
                return "";
            }
            index++;
        }
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = receiver.substring(index);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }

    @NotNull
    public static final CharSequence filter(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        Appendable destination$iv = new StringBuilder();
        int length = receiver.length() - 1;
        if (length >= 0) {
            int index$iv = 0;
            while (true) {
                char element$iv = receiver.charAt(index$iv);
                if (predicate.invoke(Character.valueOf(element$iv)).booleanValue()) {
                    destination$iv.append(element$iv);
                }
                if (index$iv == length) {
                    break;
                }
                index$iv++;
            }
        }
        return (CharSequence) destination$iv;
    }

    @NotNull
    public static final String filter(@NotNull String receiver, @NotNull Function1<? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        String $receiver$iv = receiver;
        Appendable destination$iv = new StringBuilder();
        int length = $receiver$iv.length() - 1;
        if (length >= 0) {
            int index$iv = 0;
            while (true) {
                char element$iv = $receiver$iv.charAt(index$iv);
                if (predicate.invoke(Character.valueOf(element$iv)).booleanValue()) {
                    destination$iv.append(element$iv);
                }
                if (index$iv == length) {
                    break;
                }
                index$iv++;
            }
        }
        String string = ((StringBuilder) destination$iv).toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "filterTo(StringBuilder(), predicate).toString()");
        return string;
    }

    @NotNull
    public static final CharSequence filterIndexed(@NotNull CharSequence receiver, @NotNull Function2<? super Integer, ? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        Appendable destination$iv = new StringBuilder();
        int index$iv$iv = 0;
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char item$iv$iv = it.nextChar();
            int index$iv$iv2 = index$iv$iv + 1;
            if (predicate.invoke(Integer.valueOf(index$iv$iv), Character.valueOf(item$iv$iv)).booleanValue()) {
                destination$iv.append(item$iv$iv);
            }
            index$iv$iv = index$iv$iv2;
        }
        return (CharSequence) destination$iv;
    }

    @NotNull
    public static final String filterIndexed(@NotNull String receiver, @NotNull Function2<? super Integer, ? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        String $receiver$iv = receiver;
        Appendable destination$iv = new StringBuilder();
        int index$iv$iv = 0;
        CharIterator it = StringsKt.iterator($receiver$iv);
        while (it.hasNext()) {
            char item$iv$iv = it.nextChar();
            int index$iv$iv2 = index$iv$iv + 1;
            if (predicate.invoke(Integer.valueOf(index$iv$iv), Character.valueOf(item$iv$iv)).booleanValue()) {
                destination$iv.append(item$iv$iv);
            }
            index$iv$iv = index$iv$iv2;
        }
        String string = ((StringBuilder) destination$iv).toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "filterIndexedTo(StringBu…(), predicate).toString()");
        return string;
    }

    @NotNull
    public static final <C extends Appendable> C filterIndexedTo(@NotNull CharSequence receiver, @NotNull C destination, @NotNull Function2<? super Integer, ? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int index$iv = 0;
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char item$iv = it.nextChar();
            int index$iv2 = index$iv + 1;
            if (predicate.invoke(Integer.valueOf(index$iv), Character.valueOf(item$iv)).booleanValue()) {
                destination.append(item$iv);
            }
            index$iv = index$iv2;
        }
        return destination;
    }

    @NotNull
    public static final CharSequence filterNot(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        Appendable destination$iv = new StringBuilder();
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element$iv = it.nextChar();
            if (!predicate.invoke(Character.valueOf(element$iv)).booleanValue()) {
                destination$iv.append(element$iv);
            }
        }
        return (CharSequence) destination$iv;
    }

    @NotNull
    public static final String filterNot(@NotNull String receiver, @NotNull Function1<? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        String $receiver$iv = receiver;
        Appendable destination$iv = new StringBuilder();
        CharIterator it = StringsKt.iterator($receiver$iv);
        while (it.hasNext()) {
            char element$iv = it.nextChar();
            if (!predicate.invoke(Character.valueOf(element$iv)).booleanValue()) {
                destination$iv.append(element$iv);
            }
        }
        String string = ((StringBuilder) destination$iv).toString();
        Intrinsics.checkExpressionValueIsNotNull(string, "filterNotTo(StringBuilder(), predicate).toString()");
        return string;
    }

    @NotNull
    public static final <C extends Appendable> C filterNotTo(@NotNull CharSequence receiver, @NotNull C destination, @NotNull Function1<? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            if (!predicate.invoke(Character.valueOf(element)).booleanValue()) {
                destination.append(element);
            }
        }
        return destination;
    }

    @NotNull
    public static final <C extends Appendable> C filterTo(@NotNull CharSequence receiver, @NotNull C destination, @NotNull Function1<? super Character, Boolean> predicate) throws IOException {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int length = receiver.length() - 1;
        if (length >= 0) {
            int index = 0;
            while (true) {
                char element = receiver.charAt(index);
                if (predicate.invoke(Character.valueOf(element)).booleanValue()) {
                    destination.append(element);
                }
                if (index == length) {
                    break;
                }
                index++;
            }
        }
        return destination;
    }

    @NotNull
    public static final CharSequence slice(@NotNull CharSequence receiver, @NotNull IntRange indices) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(indices, "indices");
        return indices.isEmpty() ? "" : StringsKt.subSequence(receiver, indices);
    }

    @NotNull
    public static final String slice(@NotNull String receiver, @NotNull IntRange indices) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(indices, "indices");
        return indices.isEmpty() ? "" : StringsKt.substring(receiver, indices);
    }

    @NotNull
    public static final CharSequence slice(@NotNull CharSequence receiver, @NotNull Iterable<Integer> indices) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(indices, "indices");
        int size = CollectionsKt.collectionSizeOrDefault(indices, 10);
        if (size == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder(size);
        Iterator<Integer> it = indices.iterator();
        while (it.hasNext()) {
            int i = it.next().intValue();
            result.append(receiver.charAt(i));
        }
        return result;
    }

    @InlineOnly
    private static final String slice(@NotNull String $receiver, Iterable<Integer> iterable) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.slice((CharSequence) $receiver, iterable).toString();
    }

    @NotNull
    public static final CharSequence take(@NotNull CharSequence receiver, int n) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(n >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + n + " is less than zero.").toString());
        }
        return receiver.subSequence(0, RangesKt.coerceAtMost(n, receiver.length()));
    }

    @NotNull
    public static final String take(@NotNull String receiver, int n) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(n >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + n + " is less than zero.").toString());
        }
        int iCoerceAtMost = RangesKt.coerceAtMost(n, receiver.length());
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = receiver.substring(0, iCoerceAtMost);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        return strSubstring;
    }

    @NotNull
    public static final CharSequence takeLast(@NotNull CharSequence receiver, int n) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(n >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + n + " is less than zero.").toString());
        }
        int length = receiver.length();
        return receiver.subSequence(length - RangesKt.coerceAtMost(n, length), length);
    }

    @NotNull
    public static final String takeLast(@NotNull String receiver, int n) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (!(n >= 0)) {
            throw new IllegalArgumentException(("Requested character count " + n + " is less than zero.").toString());
        }
        int length = receiver.length();
        int iCoerceAtMost = length - RangesKt.coerceAtMost(n, length);
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String strSubstring = receiver.substring(iCoerceAtMost);
        Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }

    @NotNull
    public static final CharSequence takeLastWhile(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int index = StringsKt.getLastIndex(receiver);
        if (index >= 0) {
            while (predicate.invoke(Character.valueOf(receiver.charAt(index))).booleanValue()) {
                if (index != 0) {
                    index--;
                }
            }
            return receiver.subSequence(index + 1, receiver.length());
        }
        return receiver.subSequence(0, receiver.length());
    }

    @NotNull
    public static final String takeLastWhile(@NotNull String receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int index = StringsKt.getLastIndex(receiver);
        if (index >= 0) {
            while (predicate.invoke(Character.valueOf(receiver.charAt(index))).booleanValue()) {
                if (index != 0) {
                    index--;
                }
            }
            int i = index + 1;
            if (receiver == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = receiver.substring(i);
            Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.String).substring(startIndex)");
            return strSubstring;
        }
        return receiver;
    }

    @NotNull
    public static final CharSequence takeWhile(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
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
            return receiver.subSequence(0, index);
        }
        return receiver.subSequence(0, receiver.length());
    }

    @NotNull
    public static final String takeWhile(@NotNull String receiver, @NotNull Function1<? super Character, Boolean> predicate) {
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
            if (receiver == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            String strSubstring = receiver.substring(0, index);
            Intrinsics.checkExpressionValueIsNotNull(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return strSubstring;
        }
        return receiver;
    }

    @NotNull
    public static final CharSequence reversed(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        StringBuilder sbReverse = new StringBuilder(receiver).reverse();
        Intrinsics.checkExpressionValueIsNotNull(sbReverse, "StringBuilder(this).reverse()");
        return sbReverse;
    }

    @InlineOnly
    private static final String reversed(@NotNull String $receiver) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        return StringsKt.reversed((CharSequence) $receiver).toString();
    }

    @NotNull
    public static final <K, V> Map<K, V> associate(@NotNull CharSequence receiver, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        int capacity = RangesKt.coerceAtLeast(MapsKt.mapCapacity(receiver.length()), 16);
        Map destination$iv = new LinkedHashMap(capacity);
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element$iv = it.nextChar();
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Character.valueOf(element$iv));
            destination$iv.put(pairInvoke.getFirst(), pairInvoke.getSecond());
        }
        return destination$iv;
    }

    @NotNull
    public static final <K> Map<K, Character> associateBy(@NotNull CharSequence receiver, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        int capacity = RangesKt.coerceAtLeast(MapsKt.mapCapacity(receiver.length()), 16);
        Map destination$iv = new LinkedHashMap(capacity);
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element$iv = it.nextChar();
            destination$iv.put(keySelector.invoke(Character.valueOf(element$iv)), Character.valueOf(element$iv));
        }
        return destination$iv;
    }

    @NotNull
    public static final <K, V> Map<K, V> associateBy(@NotNull CharSequence receiver, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        Intrinsics.checkParameterIsNotNull(valueTransform, "valueTransform");
        int capacity = RangesKt.coerceAtLeast(MapsKt.mapCapacity(receiver.length()), 16);
        Map destination$iv = new LinkedHashMap(capacity);
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element$iv = it.nextChar();
            destination$iv.put(keySelector.invoke(Character.valueOf(element$iv)), valueTransform.invoke(Character.valueOf(element$iv)));
        }
        return destination$iv;
    }

    @NotNull
    public static final <K, M extends Map<? super K, ? super Character>> M associateByTo(@NotNull CharSequence receiver, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            destination.put(keySelector.invoke(Character.valueOf(element)), Character.valueOf(element));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateByTo(@NotNull CharSequence receiver, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        Intrinsics.checkParameterIsNotNull(valueTransform, "valueTransform");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            destination.put(keySelector.invoke(Character.valueOf(element)), valueTransform.invoke(Character.valueOf(element)));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, ? super V>> M associateTo(@NotNull CharSequence receiver, @NotNull M destination, @NotNull Function1<? super Character, ? extends Pair<? extends K, ? extends V>> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            Pair<? extends K, ? extends V> pairInvoke = transform.invoke(Character.valueOf(element));
            destination.put(pairInvoke.getFirst(), pairInvoke.getSecond());
        }
        return destination;
    }

    @NotNull
    public static final <C extends Collection<? super Character>> C toCollection(@NotNull CharSequence receiver, @NotNull C destination) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char item = it.nextChar();
            destination.add(Character.valueOf(item));
        }
        return destination;
    }

    @NotNull
    public static final HashSet<Character> toHashSet(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (HashSet) StringsKt.toCollection(receiver, new HashSet(MapsKt.mapCapacity(receiver.length())));
    }

    @NotNull
    public static final List<Character> toList(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        switch (receiver.length()) {
            case 0:
                return CollectionsKt.emptyList();
            case 1:
                return CollectionsKt.listOf(Character.valueOf(receiver.charAt(0)));
            default:
                return StringsKt.toMutableList(receiver);
        }
    }

    @NotNull
    public static final List<Character> toMutableList(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (List) StringsKt.toCollection(receiver, new ArrayList(receiver.length()));
    }

    @NotNull
    public static final Set<Character> toSet(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        switch (receiver.length()) {
            case 0:
                return SetsKt.emptySet();
            case 1:
                return SetsKt.setOf(Character.valueOf(receiver.charAt(0)));
            default:
                return (Set) StringsKt.toCollection(receiver, new LinkedHashSet(MapsKt.mapCapacity(receiver.length())));
        }
    }

    @NotNull
    public static final SortedSet<Character> toSortedSet(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (SortedSet) StringsKt.toCollection(receiver, new TreeSet());
    }

    @NotNull
    public static final <R> List<R> flatMap(@NotNull CharSequence receiver, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        Collection destination$iv = new ArrayList();
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element$iv = it.nextChar();
            CollectionsKt.addAll(destination$iv, transform.invoke(Character.valueOf(element$iv)));
        }
        return (List) destination$iv;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C flatMapTo(@NotNull CharSequence receiver, @NotNull C destination, @NotNull Function1<? super Character, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            CollectionsKt.addAll(destination, transform.invoke(Character.valueOf(element)));
        }
        return destination;
    }

    @NotNull
    public static final <K> Map<K, List<Character>> groupBy(@NotNull CharSequence receiver, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        Map destination$iv = new LinkedHashMap();
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element$iv = it.nextChar();
            K kInvoke = keySelector.invoke(Character.valueOf(element$iv));
            Object value$iv$iv = destination$iv.get(kInvoke);
            if (value$iv$iv == null) {
                ArrayList arrayList = new ArrayList();
                destination$iv.put(kInvoke, arrayList);
                value$iv$iv = arrayList;
            }
            List list$iv = (List) value$iv$iv;
            list$iv.add(Character.valueOf(element$iv));
        }
        return destination$iv;
    }

    @NotNull
    public static final <K, V> Map<K, List<V>> groupBy(@NotNull CharSequence receiver, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        List<V> arrayList;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        Intrinsics.checkParameterIsNotNull(valueTransform, "valueTransform");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char cNextChar = it.nextChar();
            K kInvoke = keySelector.invoke(Character.valueOf(cNextChar));
            List<V> list = linkedHashMap.get(kInvoke);
            if (list == null) {
                arrayList = new ArrayList<>();
                linkedHashMap.put(kInvoke, arrayList);
            } else {
                arrayList = list;
            }
            arrayList.add(valueTransform.invoke(Character.valueOf(cNextChar)));
        }
        return linkedHashMap;
    }

    @NotNull
    public static final <K, M extends Map<? super K, List<Character>>> M groupByTo(@NotNull CharSequence receiver, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            K kInvoke = keySelector.invoke(Character.valueOf(element));
            Object value$iv = destination.get(kInvoke);
            if (value$iv == null) {
                ArrayList arrayList = new ArrayList();
                destination.put(kInvoke, arrayList);
                value$iv = arrayList;
            }
            List list = (List) value$iv;
            list.add(Character.valueOf(element));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, M extends Map<? super K, List<V>>> M groupByTo(@NotNull CharSequence receiver, @NotNull M destination, @NotNull Function1<? super Character, ? extends K> keySelector, @NotNull Function1<? super Character, ? extends V> valueTransform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        Intrinsics.checkParameterIsNotNull(valueTransform, "valueTransform");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            K kInvoke = keySelector.invoke(Character.valueOf(element));
            Object value$iv = destination.get(kInvoke);
            if (value$iv == null) {
                ArrayList arrayList = new ArrayList();
                destination.put(kInvoke, arrayList);
                value$iv = arrayList;
            }
            List list = (List) value$iv;
            list.add(valueTransform.invoke(Character.valueOf(element)));
        }
        return destination;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <K> Grouping<Character, K> groupingBy(@NotNull final CharSequence receiver, @NotNull final Function1<? super Character, ? extends K> keySelector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(keySelector, "keySelector");
        return new Grouping<Character, K>() { // from class: kotlin.text.StringsKt___StringsKt.groupingBy.1
            @Override // kotlin.collections.Grouping
            public /* bridge */ /* synthetic */ Object keyOf(Character ch) {
                return keyOf(ch.charValue());
            }

            @Override // kotlin.collections.Grouping
            @NotNull
            public Iterator<Character> sourceIterator() {
                return StringsKt.iterator(receiver);
            }

            public K keyOf(char element) {
                return (K) keySelector.invoke(Character.valueOf(element));
            }
        };
    }

    @NotNull
    public static final <R> List<R> map(@NotNull CharSequence receiver, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        Collection destination$iv = new ArrayList(receiver.length());
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char item$iv = it.nextChar();
            destination$iv.add(transform.invoke(Character.valueOf(item$iv)));
        }
        return (List) destination$iv;
    }

    @NotNull
    public static final <R> List<R> mapIndexed(@NotNull CharSequence receiver, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        Collection destination$iv = new ArrayList(receiver.length());
        int index$iv = 0;
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char item$iv = it.nextChar();
            Integer numValueOf = Integer.valueOf(index$iv);
            index$iv++;
            destination$iv.add(transform.invoke(numValueOf, Character.valueOf(item$iv)));
        }
        return (List) destination$iv;
    }

    @NotNull
    public static final <R> List<R> mapIndexedNotNull(@NotNull CharSequence receiver, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        int $i$f$mapIndexedNotNullTo = 0;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        Collection destination$iv = new ArrayList();
        int index$iv = 0;
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char item$iv$iv = it.nextChar();
            int index$iv$iv = index$iv + 1;
            int $i$f$mapIndexedNotNullTo2 = $i$f$mapIndexedNotNullTo;
            R rInvoke = transform.invoke(Integer.valueOf(index$iv), Character.valueOf(item$iv$iv));
            if (rInvoke != null) {
                destination$iv.add(rInvoke);
            }
            index$iv = index$iv$iv;
            $i$f$mapIndexedNotNullTo = $i$f$mapIndexedNotNullTo2;
        }
        return (List) destination$iv;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedNotNullTo(@NotNull CharSequence receiver, @NotNull C destination, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        int index = 0;
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char item$iv = it.nextChar();
            int index$iv = index + 1;
            R rInvoke = transform.invoke(Integer.valueOf(index), Character.valueOf(item$iv));
            if (rInvoke != null) {
                destination.add(rInvoke);
            }
            index = index$iv;
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapIndexedTo(@NotNull CharSequence receiver, @NotNull C destination, @NotNull Function2<? super Integer, ? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        int index = 0;
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char item = it.nextChar();
            Integer numValueOf = Integer.valueOf(index);
            index++;
            destination.add(transform.invoke(numValueOf, Character.valueOf(item)));
        }
        return destination;
    }

    @NotNull
    public static final <R> List<R> mapNotNull(@NotNull CharSequence receiver, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        Collection destination$iv = new ArrayList();
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element$iv$iv = it.nextChar();
            R rInvoke = transform.invoke(Character.valueOf(element$iv$iv));
            if (rInvoke != null) {
                destination$iv.add(rInvoke);
            }
        }
        return (List) destination$iv;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapNotNullTo(@NotNull CharSequence receiver, @NotNull C destination, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element$iv = it.nextChar();
            R rInvoke = transform.invoke(Character.valueOf(element$iv));
            if (rInvoke != null) {
                destination.add(rInvoke);
            }
        }
        return destination;
    }

    @NotNull
    public static final <R, C extends Collection<? super R>> C mapTo(@NotNull CharSequence receiver, @NotNull C destination, @NotNull Function1<? super Character, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char item = it.nextChar();
            destination.add(transform.invoke(Character.valueOf(item)));
        }
        return destination;
    }

    @NotNull
    public static final Iterable<IndexedValue<Character>> withIndex(@NotNull final CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return new IndexingIterable(new Function0<CharIterator>() { // from class: kotlin.text.StringsKt___StringsKt.withIndex.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final CharIterator invoke() {
                return StringsKt.iterator(receiver);
            }
        });
    }

    public static final boolean all(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            if (!predicate.invoke(Character.valueOf(element)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean any(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        CharIterator it = StringsKt.iterator(receiver);
        if (it.hasNext()) {
            it.nextChar();
            return true;
        }
        return false;
    }

    public static final boolean any(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            if (predicate.invoke(Character.valueOf(element)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @InlineOnly
    private static final int count(@NotNull CharSequence $receiver) {
        return $receiver.length();
    }

    public static final int count(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int count = 0;
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            if (predicate.invoke(Character.valueOf(element)).booleanValue()) {
                count++;
            }
        }
        return count;
    }

    public static final <R> R fold(@NotNull CharSequence receiver, R r, @NotNull Function2<? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        R rInvoke = r;
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            rInvoke = operation.invoke(rInvoke, Character.valueOf(it.nextChar()));
        }
        return rInvoke;
    }

    public static final <R> R foldIndexed(@NotNull CharSequence receiver, R r, @NotNull Function3<? super Integer, ? super R, ? super Character, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int i = 0;
        R rInvoke = r;
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char cNextChar = it.nextChar();
            Integer numValueOf = Integer.valueOf(i);
            i++;
            rInvoke = operation.invoke(numValueOf, rInvoke, Character.valueOf(cNextChar));
        }
        return rInvoke;
    }

    public static final <R> R foldRight(@NotNull CharSequence receiver, R r, @NotNull Function2<? super Character, ? super R, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        R rInvoke = r;
        for (int lastIndex = StringsKt.getLastIndex(receiver); lastIndex >= 0; lastIndex--) {
            rInvoke = operation.invoke(Character.valueOf(receiver.charAt(lastIndex)), rInvoke);
        }
        return rInvoke;
    }

    public static final <R> R foldRightIndexed(@NotNull CharSequence receiver, R r, @NotNull Function3<? super Integer, ? super Character, ? super R, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        R rInvoke = r;
        for (int lastIndex = StringsKt.getLastIndex(receiver); lastIndex >= 0; lastIndex--) {
            rInvoke = operation.invoke(Integer.valueOf(lastIndex), Character.valueOf(receiver.charAt(lastIndex)), rInvoke);
        }
        return rInvoke;
    }

    public static final void forEach(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            action.invoke(Character.valueOf(element));
        }
    }

    public static final void forEachIndexed(@NotNull CharSequence receiver, @NotNull Function2<? super Integer, ? super Character, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        int index = 0;
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char item = it.nextChar();
            Integer numValueOf = Integer.valueOf(index);
            index++;
            action.invoke(numValueOf, Character.valueOf(item));
        }
    }

    @Nullable
    public static final Character max(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int i = 1;
        if (receiver.length() == 0) {
            return null;
        }
        char max = receiver.charAt(0);
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                char e = receiver.charAt(i);
                if (max < e) {
                    max = e;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(max);
    }

    @Nullable
    public static final <R extends Comparable<? super R>> Character maxBy(@NotNull CharSequence receiver, @NotNull Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        int i = 1;
        if (receiver.length() == 0) {
            return null;
        }
        char maxElem = receiver.charAt(0);
        Comparable maxValue = selector.invoke(Character.valueOf(maxElem));
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                char e = receiver.charAt(i);
                R rInvoke = selector.invoke(Character.valueOf(e));
                if (maxValue.compareTo(rInvoke) < 0) {
                    maxElem = e;
                    maxValue = rInvoke;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(maxElem);
    }

    @Nullable
    public static final Character maxWith(@NotNull CharSequence receiver, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        int i = 1;
        if (receiver.length() == 0) {
            return null;
        }
        char max = receiver.charAt(0);
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                char e = receiver.charAt(i);
                if (comparator.compare(Character.valueOf(max), Character.valueOf(e)) < 0) {
                    max = e;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(max);
    }

    @Nullable
    public static final Character min(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        int i = 1;
        if (receiver.length() == 0) {
            return null;
        }
        char min = receiver.charAt(0);
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                char e = receiver.charAt(i);
                if (min > e) {
                    min = e;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(min);
    }

    @Nullable
    public static final <R extends Comparable<? super R>> Character minBy(@NotNull CharSequence receiver, @NotNull Function1<? super Character, ? extends R> selector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        int i = 1;
        if (receiver.length() == 0) {
            return null;
        }
        char minElem = receiver.charAt(0);
        Comparable minValue = selector.invoke(Character.valueOf(minElem));
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                char e = receiver.charAt(i);
                R rInvoke = selector.invoke(Character.valueOf(e));
                if (minValue.compareTo(rInvoke) > 0) {
                    minElem = e;
                    minValue = rInvoke;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(minElem);
    }

    @Nullable
    public static final Character minWith(@NotNull CharSequence receiver, @NotNull Comparator<? super Character> comparator) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        int i = 1;
        if (receiver.length() == 0) {
            return null;
        }
        char min = receiver.charAt(0);
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                char e = receiver.charAt(i);
                if (comparator.compare(Character.valueOf(min), Character.valueOf(e)) > 0) {
                    min = e;
                }
                if (i == lastIndex) {
                    break;
                }
                i++;
            }
        }
        return Character.valueOf(min);
    }

    public static final boolean none(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        CharIterator it = StringsKt.iterator(receiver);
        if (it.hasNext()) {
            it.nextChar();
            return false;
        }
        return true;
    }

    public static final boolean none(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            if (predicate.invoke(Character.valueOf(element)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <S extends CharSequence> S onEach(@NotNull S receiver, @NotNull Function1<? super Character, Unit> action) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, "action");
        S $receiver = receiver;
        CharIterator it = StringsKt.iterator($receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            action.invoke(Character.valueOf(element));
        }
        return receiver;
    }

    public static final char reduce(@NotNull CharSequence receiver, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int index = 1;
        if (receiver.length() == 0) {
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        }
        char accumulator = receiver.charAt(0);
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                accumulator = operation.invoke(Character.valueOf(accumulator), Character.valueOf(receiver.charAt(index))).charValue();
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        return accumulator;
    }

    public static final char reduceIndexed(@NotNull CharSequence receiver, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int index = 1;
        if (receiver.length() == 0) {
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        }
        char accumulator = receiver.charAt(0);
        int lastIndex = StringsKt.getLastIndex(receiver);
        if (1 <= lastIndex) {
            while (true) {
                accumulator = operation.invoke(Integer.valueOf(index), Character.valueOf(accumulator), Character.valueOf(receiver.charAt(index))).charValue();
                if (index == lastIndex) {
                    break;
                }
                index++;
            }
        }
        return accumulator;
    }

    public static final char reduceRight(@NotNull CharSequence receiver, @NotNull Function2<? super Character, ? super Character, Character> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int index = StringsKt.getLastIndex(receiver);
        if (index < 0) {
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        }
        char accumulator = receiver.charAt(index);
        for (int index2 = index - 1; index2 >= 0; index2--) {
            accumulator = operation.invoke(Character.valueOf(receiver.charAt(index2)), Character.valueOf(accumulator)).charValue();
        }
        return accumulator;
    }

    public static final char reduceRightIndexed(@NotNull CharSequence receiver, @NotNull Function3<? super Integer, ? super Character, ? super Character, Character> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        int index = StringsKt.getLastIndex(receiver);
        if (index < 0) {
            throw new UnsupportedOperationException("Empty char sequence can't be reduced.");
        }
        char accumulator = receiver.charAt(index);
        for (int index2 = index - 1; index2 >= 0; index2--) {
            accumulator = operation.invoke(Integer.valueOf(index2), Character.valueOf(receiver.charAt(index2)), Character.valueOf(accumulator)).charValue();
        }
        return accumulator;
    }

    public static final int sumBy(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Integer> selector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        int sum = 0;
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            sum += selector.invoke(Character.valueOf(element)).intValue();
        }
        return sum;
    }

    public static final double sumByDouble(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Double> selector) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        double sum = 0.0d;
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            sum += selector.invoke(Character.valueOf(element)).doubleValue();
        }
        return sum;
    }

    @NotNull
    public static final Pair<CharSequence, CharSequence> partition(@NotNull CharSequence receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder first = new StringBuilder();
        StringBuilder second = new StringBuilder();
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            if (predicate.invoke(Character.valueOf(element)).booleanValue()) {
                first.append(element);
            } else {
                second.append(element);
            }
        }
        return new Pair<>(first, second);
    }

    @NotNull
    public static final Pair<String, String> partition(@NotNull String receiver, @NotNull Function1<? super Character, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        StringBuilder first = new StringBuilder();
        StringBuilder second = new StringBuilder();
        CharIterator it = StringsKt.iterator(receiver);
        while (it.hasNext()) {
            char element = it.nextChar();
            if (predicate.invoke(Character.valueOf(element)).booleanValue()) {
                first.append(element);
            } else {
                second.append(element);
            }
        }
        return new Pair<>(first.toString(), second.toString());
    }

    @NotNull
    public static final List<Pair<Character, Character>> zip(@NotNull CharSequence receiver, @NotNull CharSequence other) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        int length$iv = Math.min(receiver.length(), other.length());
        ArrayList list$iv = new ArrayList(length$iv);
        int i = length$iv - 1;
        if (i >= 0) {
            int i$iv = 0;
            while (true) {
                char c1 = receiver.charAt(i$iv);
                char c2 = other.charAt(i$iv);
                list$iv.add(TuplesKt.to(Character.valueOf(c1), Character.valueOf(c2)));
                if (i$iv == i) {
                    break;
                }
                i$iv++;
            }
        }
        return list$iv;
    }

    @NotNull
    public static final <V> List<V> zip(@NotNull CharSequence receiver, @NotNull CharSequence other, @NotNull Function2<? super Character, ? super Character, ? extends V> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        int length = Math.min(receiver.length(), other.length());
        ArrayList list = new ArrayList(length);
        int i = length - 1;
        if (i >= 0) {
            int i2 = 0;
            while (true) {
                list.add(transform.invoke(Character.valueOf(receiver.charAt(i2)), Character.valueOf(other.charAt(i2))));
                if (i2 == i) {
                    break;
                }
                i2++;
            }
        }
        return list;
    }

    @NotNull
    public static final Iterable<Character> asIterable(@NotNull CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver instanceof String) {
            if (receiver.length() == 0) {
                return CollectionsKt.emptyList();
            }
        }
        return new StringsKt___StringsKt$asIterable$$inlined$Iterable$1(receiver);
    }

    @NotNull
    public static final Sequence<Character> asSequence(@NotNull final CharSequence receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver instanceof String) {
            if (receiver.length() == 0) {
                return SequencesKt.emptySequence();
            }
        }
        return new Sequence<Character>() { // from class: kotlin.text.StringsKt___StringsKt$asSequence$$inlined$Sequence$1
            @Override // kotlin.sequences.Sequence
            public Iterator<Character> iterator() {
                return StringsKt.iterator(receiver);
            }
        };
    }
}
