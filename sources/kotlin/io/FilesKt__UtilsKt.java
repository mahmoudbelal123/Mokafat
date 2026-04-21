package kotlin.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: Utils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001a(\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a(\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a8\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\u001a\b\u0002\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u001a&\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u001a\n\u0010\u0019\u001a\u00020\u000f*\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\n\u0010\u001c\u001a\u00020\u0002*\u00020\u0002\u001a\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0002¢\u0006\u0002\b\u001e\u001a\u0011\u0010\u001c\u001a\u00020\u001f*\u00020\u001fH\u0002¢\u0006\u0002\b\u001e\u001a\u0012\u0010 \u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0014\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010#\u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\u0012\u0010(\u001a\u00020\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u001b\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002H\u0002¢\u0006\u0002\b*\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006+"}, d2 = {"extension", "", "Ljava/io/File;", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "createTempDir", "prefix", "suffix", "directory", "createTempFile", "copyRecursively", "", "target", "overwrite", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "copyTo", "bufferSize", "", "deleteRecursively", "endsWith", "other", "normalize", "", "normalize$FilesKt__UtilsKt", "Lkotlin/io/FilePathComponents;", "relativeTo", "base", "relativeToOrNull", "relativeToOrSelf", "resolve", "relative", "resolveSibling", "startsWith", "toRelativeString", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "kotlin-stdlib"}, k = 5, mv = {1, 1, 5}, xi = 1, xs = "kotlin/io/FilesKt")
class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {
    @NotNull
    public static /* bridge */ /* synthetic */ File createTempDir$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            file = (File) null;
        }
        return FilesKt.createTempDir(str, str2, file);
    }

    @NotNull
    public static final File createTempDir(@NotNull String prefix, @Nullable String suffix, @Nullable File directory) throws IOException {
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        File dir = File.createTempFile(prefix, suffix, directory);
        dir.delete();
        if (dir.mkdir()) {
            Intrinsics.checkExpressionValueIsNotNull(dir, "dir");
            return dir;
        }
        throw new IOException("Unable to create temporary directory " + dir + ".");
    }

    @NotNull
    public static /* bridge */ /* synthetic */ File createTempFile$default(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = (String) null;
        }
        if ((i & 4) != 0) {
            file = (File) null;
        }
        return FilesKt.createTempFile(str, str2, file);
    }

    @NotNull
    public static final File createTempFile(@NotNull String prefix, @Nullable String suffix, @Nullable File directory) throws IOException {
        Intrinsics.checkParameterIsNotNull(prefix, "prefix");
        File fileCreateTempFile = File.createTempFile(prefix, suffix, directory);
        Intrinsics.checkExpressionValueIsNotNull(fileCreateTempFile, "File.createTempFile(prefix, suffix, directory)");
        return fileCreateTempFile;
    }

    @NotNull
    public static final String getExtension(@NotNull File receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return StringsKt.substringAfterLast(receiver.getName(), '.', "");
    }

    @NotNull
    public static final String getInvariantSeparatorsPath(@NotNull File receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (File.separatorChar != '/') {
            return StringsKt.replace$default(receiver.getPath(), File.separatorChar, '/', false, 4, (Object) null);
        }
        String path = receiver.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path, "path");
        return path;
    }

    @NotNull
    public static final String getNameWithoutExtension(@NotNull File receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return StringsKt.substringBeforeLast$default(receiver.getName(), ".", (String) null, 2, (Object) null);
    }

    @NotNull
    public static final String toRelativeString(@NotNull File receiver, @NotNull File base) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(base, "base");
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(receiver, base);
        if (relativeStringOrNull$FilesKt__UtilsKt != null) {
            return relativeStringOrNull$FilesKt__UtilsKt;
        }
        throw new IllegalArgumentException("this and base files have different roots: " + receiver + " and " + base + ".");
    }

    @NotNull
    public static final File relativeTo(@NotNull File receiver, @NotNull File base) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(base, "base");
        return new File(FilesKt.toRelativeString(receiver, base));
    }

    @NotNull
    public static final File relativeToOrSelf(@NotNull File receiver, @NotNull File base) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(base, "base");
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(receiver, base);
        if (relativeStringOrNull$FilesKt__UtilsKt == null) {
            return receiver;
        }
        String it = relativeStringOrNull$FilesKt__UtilsKt;
        return new File(it);
    }

    @Nullable
    public static final File relativeToOrNull(@NotNull File receiver, @NotNull File base) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(base, "base");
        String relativeStringOrNull$FilesKt__UtilsKt = toRelativeStringOrNull$FilesKt__UtilsKt(receiver, base);
        if (relativeStringOrNull$FilesKt__UtilsKt == null) {
            return null;
        }
        String it = relativeStringOrNull$FilesKt__UtilsKt;
        return new File(it);
    }

    private static final String toRelativeStringOrNull$FilesKt__UtilsKt(@NotNull File $receiver, File base) {
        FilePathComponents thisComponents = normalize$FilesKt__UtilsKt(FilesKt.toComponents($receiver));
        FilePathComponents baseComponents = normalize$FilesKt__UtilsKt(FilesKt.toComponents(base));
        if (!Intrinsics.areEqual(thisComponents.getRoot(), baseComponents.getRoot())) {
            return null;
        }
        int baseCount = baseComponents.getSize();
        int thisCount = thisComponents.getSize();
        int i = 0;
        int maxSameCount = Math.min(thisCount, baseCount);
        while (i < maxSameCount && Intrinsics.areEqual(thisComponents.getSegments().get(i), baseComponents.getSegments().get(i))) {
            i++;
        }
        int sameCount = i;
        StringBuilder res = new StringBuilder();
        int i2 = baseCount - 1;
        if (i2 >= sameCount) {
            while (!Intrinsics.areEqual(baseComponents.getSegments().get(i2).getName(), "..")) {
                res.append("..");
                if (i2 != sameCount) {
                    res.append(File.separatorChar);
                }
                if (i2 != sameCount) {
                    i2--;
                }
            }
            return null;
        }
        if (sameCount < thisCount) {
            if (sameCount < baseCount) {
                res.append(File.separatorChar);
            }
            String str = File.separator;
            Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
            CollectionsKt.joinTo(CollectionsKt.drop(thisComponents.getSegments(), sameCount), res, (124 & 2) != 0 ? ", " : str, (124 & 4) != 0 ? "" : null, (124 & 8) != 0 ? "" : null, (124 & 16) != 0 ? -1 : 0, (124 & 32) != 0 ? "..." : null, (124 & 64) != 0 ? (Function1) null : null);
        }
        return res.toString();
    }

    @NotNull
    public static /* bridge */ /* synthetic */ File copyTo$default(File file, File file2, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 8192;
        }
        return FilesKt.copyTo(file, file2, z, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x0097 A[Catch: all -> 0x009b, Exception -> 0x009d, TRY_ENTER, TryCatch #8 {Exception -> 0x009d, all -> 0x009b, blocks: (B:27:0x0065, B:29:0x007d, B:44:0x0097, B:45:0x009a), top: B:65:0x0065 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00ad  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.io.File copyTo(@org.jetbrains.annotations.NotNull java.io.File r11, @org.jetbrains.annotations.NotNull java.io.File r12, boolean r13, int r14) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            java.lang.String r0 = "target"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            boolean r0 = r11.exists()
            if (r0 != 0) goto L1f
            kotlin.io.NoSuchFileException r0 = new kotlin.io.NoSuchFileException
            r3 = 0
            java.lang.String r4 = "The source file doesn't exist."
            r5 = 2
            r6 = 0
            r1 = r0
            r2 = r11
            r1.<init>(r2, r3, r4, r5, r6)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L1f:
            boolean r0 = r12.exists()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L3f
            if (r13 != 0) goto L2b
        L29:
            r0 = r1
            goto L33
        L2b:
            boolean r0 = r12.delete()
            if (r0 != 0) goto L32
            goto L29
        L32:
            r0 = r2
        L33:
            if (r0 == 0) goto L3f
            kotlin.io.FileAlreadyExistsException r1 = new kotlin.io.FileAlreadyExistsException
            java.lang.String r2 = "The destination file already exists."
            r1.<init>(r11, r12, r2)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        L3f:
            boolean r0 = r11.isDirectory()
            if (r0 == 0) goto L55
            boolean r0 = r12.mkdirs()
            if (r0 != 0) goto L84
            kotlin.io.FileSystemException r0 = new kotlin.io.FileSystemException
            java.lang.String r1 = "Failed to create target directory."
            r0.<init>(r11, r12, r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L55:
            java.io.File r0 = r12.getParentFile()
            if (r0 == 0) goto L5e
            r0.mkdirs()
        L5e:
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r11)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r3 = r0
            java.io.FileInputStream r3 = (java.io.FileInputStream) r3     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r4 = r2
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r5.<init>(r12)     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            java.io.Closeable r5 = (java.io.Closeable) r5     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r6 = r5
            java.io.FileOutputStream r6 = (java.io.FileOutputStream) r6     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L89
            r7 = r2
            r8 = r3
            java.io.InputStream r8 = (java.io.InputStream) r8     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L89
            r9 = r6
            java.io.OutputStream r9 = (java.io.OutputStream) r9     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L89
            kotlin.io.ByteStreamsKt.copyTo(r8, r9, r14)     // Catch: java.lang.Throwable -> L86 java.lang.Exception -> L89
            r5.close()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
            r0.close()
        L84:
            return r12
        L86:
            r6 = move-exception
            r7 = r2
            goto L95
        L89:
            r6 = move-exception
            r5.close()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L91
            goto L92
        L8e:
            r6 = move-exception
            r7 = r1
            goto L95
        L91:
            r7 = move-exception
        L92:
            java.lang.Throwable r6 = (java.lang.Throwable) r6     // Catch: java.lang.Throwable -> L8e
            throw r6     // Catch: java.lang.Throwable -> L8e
        L95:
            if (r7 != 0) goto L9a
            r5.close()     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
        L9a:
            throw r6     // Catch: java.lang.Throwable -> L9b java.lang.Exception -> L9d
        L9b:
            r1 = move-exception
            goto Lab
        L9d:
            r2 = move-exception
            r0.close()     // Catch: java.lang.Throwable -> La2 java.lang.Exception -> La7
            goto La8
        La2:
            r2 = move-exception
            r10 = r2
            r2 = r1
            r1 = r10
            goto Lab
        La7:
            r3 = move-exception
        La8:
            java.lang.Throwable r2 = (java.lang.Throwable) r2     // Catch: java.lang.Throwable -> La2
            throw r2     // Catch: java.lang.Throwable -> La2
        Lab:
            if (r2 != 0) goto Lb0
            r0.close()
        Lb0:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__UtilsKt.copyTo(java.io.File, java.io.File, boolean, int):java.io.File");
    }

    public static /* bridge */ /* synthetic */ boolean copyRecursively$default(File file, File file2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            function2 = new Function2() { // from class: kotlin.io.FilesKt__UtilsKt.copyRecursively.1
                @Override // kotlin.jvm.functions.Function2
                @NotNull
                public final Void invoke(@NotNull File file3, @NotNull IOException exception) throws IOException {
                    Intrinsics.checkParameterIsNotNull(file3, "file");
                    Intrinsics.checkParameterIsNotNull(exception, "exception");
                    throw exception;
                }
            };
        }
        return FilesKt.copyRecursively(file, file2, z, function2);
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00c7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00b0 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean copyRecursively(@org.jetbrains.annotations.NotNull java.io.File r12, @org.jetbrains.annotations.NotNull java.io.File r13, boolean r14, @org.jetbrains.annotations.NotNull final kotlin.jvm.functions.Function2<? super java.io.File, ? super java.io.IOException, ? extends kotlin.io.OnErrorAction> r15) {
        /*
            Method dump skipped, instruction units count: 260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__UtilsKt.copyRecursively(java.io.File, java.io.File, boolean, kotlin.jvm.functions.Function2):boolean");
    }

    public static final boolean deleteRecursively(@NotNull File receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Sequence $receiver$iv = FilesKt.walkBottomUp(receiver);
        boolean accumulator$iv = true;
        for (Object element$iv : $receiver$iv) {
            File it = (File) element$iv;
            boolean res = accumulator$iv;
            accumulator$iv = (it.delete() || !it.exists()) && res;
        }
        return accumulator$iv;
    }

    public static final boolean startsWith(@NotNull File receiver, @NotNull File other) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        FilePathComponents components = FilesKt.toComponents(receiver);
        FilePathComponents otherComponents = FilesKt.toComponents(other);
        if (!(!Intrinsics.areEqual(components.getRoot(), otherComponents.getRoot())) && components.getSize() >= otherComponents.getSize()) {
            return components.getSegments().subList(0, otherComponents.getSize()).equals(otherComponents.getSegments());
        }
        return false;
    }

    public static final boolean startsWith(@NotNull File receiver, @NotNull String other) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return FilesKt.startsWith(receiver, new File(other));
    }

    public static final boolean endsWith(@NotNull File receiver, @NotNull File other) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        FilePathComponents components = FilesKt.toComponents(receiver);
        FilePathComponents otherComponents = FilesKt.toComponents(other);
        if (otherComponents.isRooted()) {
            return Intrinsics.areEqual(receiver, other);
        }
        int shift = components.getSize() - otherComponents.getSize();
        if (shift < 0) {
            return false;
        }
        return components.getSegments().subList(shift, components.getSize()).equals(otherComponents.getSegments());
    }

    public static final boolean endsWith(@NotNull File receiver, @NotNull String other) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return FilesKt.endsWith(receiver, new File(other));
    }

    @NotNull
    public static final File normalize(@NotNull File receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        FilePathComponents $receiver = FilesKt.toComponents(receiver);
        File root = $receiver.getRoot();
        List<File> listNormalize$FilesKt__UtilsKt = normalize$FilesKt__UtilsKt($receiver.getSegments());
        String str = File.separator;
        Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
        return FilesKt.resolve(root, CollectionsKt.joinToString$default(listNormalize$FilesKt__UtilsKt, str, null, null, 0, null, null, 62, null));
    }

    private static final FilePathComponents normalize$FilesKt__UtilsKt(@NotNull FilePathComponents $receiver) {
        return new FilePathComponents($receiver.getRoot(), normalize$FilesKt__UtilsKt($receiver.getSegments()));
    }

    private static final List<File> normalize$FilesKt__UtilsKt(@NotNull List<? extends File> list) {
        List list2 = new ArrayList(list.size());
        for (File file : list) {
            String name = file.getName();
            if (name != null) {
                int iHashCode = name.hashCode();
                if (iHashCode != 46) {
                    if (iHashCode == 1472 && name.equals("..")) {
                        if (list2.isEmpty() || !(!Intrinsics.areEqual(((File) CollectionsKt.last(list2)).getName(), ".."))) {
                            list2.add(file);
                        } else {
                            list2.remove(list2.size() - 1);
                        }
                    }
                } else if (name.equals(".")) {
                }
            }
            list2.add(file);
        }
        return list2;
    }

    @NotNull
    public static final File resolve(@NotNull File receiver, @NotNull File relative) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(relative, "relative");
        if (FilesKt.isRooted(relative)) {
            return relative;
        }
        String baseName = receiver.toString();
        if ((baseName.length() == 0) || StringsKt.endsWith$default((CharSequence) baseName, File.separatorChar, false, 2, (Object) null)) {
            return new File(baseName + relative);
        }
        return new File(baseName + File.separatorChar + relative);
    }

    @NotNull
    public static final File resolve(@NotNull File receiver, @NotNull String relative) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(relative, "relative");
        return FilesKt.resolve(receiver, new File(relative));
    }

    @NotNull
    public static final File resolveSibling(@NotNull File receiver, @NotNull File relative) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(relative, "relative");
        FilePathComponents components = FilesKt.toComponents(receiver);
        File parentSubPath = components.getSize() == 0 ? new File("..") : components.subPath(0, components.getSize() - 1);
        return FilesKt.resolve(FilesKt.resolve(components.getRoot(), parentSubPath), relative);
    }

    @NotNull
    public static final File resolveSibling(@NotNull File receiver, @NotNull String relative) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(relative, "relative");
        return FilesKt.resolveSibling(receiver, new File(relative));
    }
}
