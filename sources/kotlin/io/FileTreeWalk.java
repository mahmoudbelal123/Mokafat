package kotlin.io;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin._Assertions;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: FileTreeWalk.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001a\u001b\u001cB\u0019\b\u0010\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0089\u0001\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b\u00128\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b\u0018\u00010\r\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014¢\u0006\u0002\u0010\u0015J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0017H\u0096\u0002J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0014J\u001a\u0010\u0007\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\bJ \u0010\f\u001a\u00020\u00002\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\rJ\u001a\u0010\n\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R@\u0010\f\u001a4\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000b\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lkotlin/io/FileTreeWalk;", "Lkotlin/sequences/Sequence;", "Ljava/io/File;", "start", "direction", "Lkotlin/io/FileWalkDirection;", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;)V", "onEnter", "Lkotlin/Function1;", "", "onLeave", "", "onFail", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "f", "Ljava/io/IOException;", "e", "maxDepth", "", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;I)V", "iterator", "", "depth", "function", "DirectoryState", "FileTreeWalkIterator", "WalkState", "kotlin-stdlib"}, k = 1, mv = {1, 1, 5})
public final class FileTreeWalk implements Sequence<File> {
    private final FileWalkDirection direction;
    private final int maxDepth;
    private final Function1<File, Boolean> onEnter;
    private final Function2<File, IOException, Unit> onFail;
    private final Function1<File, Unit> onLeave;
    private final File start;

    /* JADX WARN: Multi-variable type inference failed */
    private FileTreeWalk(File start, FileWalkDirection direction, Function1<? super File, Boolean> function1, Function1<? super File, Unit> function12, Function2<? super File, ? super IOException, Unit> function2, int maxDepth) {
        this.start = start;
        this.direction = direction;
        this.onEnter = function1;
        this.onLeave = function12;
        this.onFail = function2;
        this.maxDepth = maxDepth;
    }

    /* synthetic */ FileTreeWalk(File file, FileWalkDirection fileWalkDirection, Function1 function1, Function1 function12, Function2 function2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, (i2 & 2) != 0 ? FileWalkDirection.TOP_DOWN : fileWalkDirection, function1, function12, function2, (i2 & 32) != 0 ? Integer.MAX_VALUE : i);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FileTreeWalk(@NotNull File start, @NotNull FileWalkDirection direction) {
        this(start, direction, null, null, null, 0, 32, null);
        Intrinsics.checkParameterIsNotNull(start, "start");
        Intrinsics.checkParameterIsNotNull(direction, "direction");
    }

    public /* synthetic */ FileTreeWalk(File file, FileWalkDirection fileWalkDirection, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, (i & 2) != 0 ? FileWalkDirection.TOP_DOWN : fileWalkDirection);
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<File> iterator() {
        return new FileTreeWalkIterator();
    }

    /* JADX INFO: compiled from: FileTreeWalk.kt */
    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0003H&R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lkotlin/io/FileTreeWalk$WalkState;", "", "root", "Ljava/io/File;", "(Ljava/io/File;)V", "getRoot", "()Ljava/io/File;", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 5})
    private static abstract class WalkState {

        @NotNull
        private final File root;

        @Nullable
        public abstract File step();

        public WalkState(@NotNull File root) {
            Intrinsics.checkParameterIsNotNull(root, "root");
            this.root = root;
        }

        @NotNull
        public final File getRoot() {
            return this.root;
        }
    }

    /* JADX INFO: compiled from: FileTreeWalk.kt */
    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/io/FileTreeWalk$DirectoryState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootDir", "Ljava/io/File;", "(Ljava/io/File;)V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 5})
    private static abstract class DirectoryState extends WalkState {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DirectoryState(@NotNull File rootDir) {
            super(rootDir);
            Intrinsics.checkParameterIsNotNull(rootDir, "rootDir");
            if (_Assertions.ENABLED) {
                boolean zIsDirectory = rootDir.isDirectory();
                if (_Assertions.ENABLED && !zIsDirectory) {
                    throw new AssertionError("rootDir must be verified to be directory beforehand.");
                }
            }
        }
    }

    /* JADX INFO: compiled from: FileTreeWalk.kt */
    @Metadata(bv = {1, 0, 1}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u000f\u0010\u0011B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0002H\u0082\u0010R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;", "Lkotlin/collections/AbstractIterator;", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk;)V", "state", "Ljava/util/Stack;", "Lkotlin/io/FileTreeWalk$WalkState;", "getState", "()Ljava/util/Stack;", "computeNext", "", "directoryState", "Lkotlin/io/FileTreeWalk$DirectoryState;", "root", "gotoNext", "BottomUpDirectoryState", "SingleFileState", "TopDownDirectoryState", "kotlin-stdlib"}, k = 1, mv = {1, 1, 5})
    private final class FileTreeWalkIterator extends AbstractIterator<File> {
        private final Stack<WalkState> state = new Stack<>();

        public FileTreeWalkIterator() {
            if (FileTreeWalk.this.start.isDirectory()) {
                this.state.push(directoryState(FileTreeWalk.this.start));
            } else if (FileTreeWalk.this.start.isFile()) {
                this.state.push(new SingleFileState(this, FileTreeWalk.this.start));
            } else {
                done();
            }
        }

        private final Stack<WalkState> getState() {
            return this.state;
        }

        @Override // kotlin.collections.AbstractIterator
        protected void computeNext() {
            File nextFile = gotoNext();
            if (nextFile != null) {
                setNext(nextFile);
            } else {
                done();
            }
        }

        private final DirectoryState directoryState(File root) {
            switch (FileTreeWalk.this.direction) {
                case TOP_DOWN:
                    return new TopDownDirectoryState(this, root);
                case BOTTOM_UP:
                    return new BottomUpDirectoryState(this, root);
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }

        private final File gotoNext() {
            while (!this.state.empty()) {
                WalkState walkStatePeek = this.state.peek();
                if (walkStatePeek == null) {
                    Intrinsics.throwNpe();
                }
                WalkState topState = walkStatePeek;
                File file = topState.step();
                if (file == null) {
                    this.state.pop();
                } else {
                    if (Intrinsics.areEqual(file, topState.getRoot()) || !file.isDirectory() || this.state.size() >= FileTreeWalk.this.maxDepth) {
                        return file;
                    }
                    this.state.push(directoryState(file));
                }
            }
            return null;
        }

        /* JADX INFO: compiled from: FileTreeWalk.kt */
        @Metadata(bv = {1, 0, 1}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\n\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0018\u001a\u00020\u0006X\u0082\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\b\"\u0004\b\u001a\u0010\n¨\u0006\u001c"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$BottomUpDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "failed", "", "getFailed", "()Z", "setFailed", "(Z)V", "fileIndex", "", "getFileIndex", "()I", "setFileIndex", "(I)V", "fileList", "", "getFileList", "()[Ljava/io/File;", "setFileList", "([Ljava/io/File;)V", "[Ljava/io/File;", "rootVisited", "getRootVisited", "setRootVisited", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 5})
        private final class BottomUpDirectoryState extends DirectoryState {
            private boolean failed;
            private int fileIndex;
            private File[] fileList;
            private boolean rootVisited;
            final /* synthetic */ FileTreeWalkIterator this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public BottomUpDirectoryState(@NotNull FileTreeWalkIterator $outer, File rootDir) {
                super(rootDir);
                Intrinsics.checkParameterIsNotNull(rootDir, "rootDir");
                this.this$0 = $outer;
            }

            private final boolean getRootVisited() {
                return this.rootVisited;
            }

            private final void setRootVisited(boolean z) {
                this.rootVisited = z;
            }

            private final File[] getFileList() {
                return this.fileList;
            }

            private final void setFileList(File[] fileArr) {
                this.fileList = fileArr;
            }

            private final int getFileIndex() {
                return this.fileIndex;
            }

            private final void setFileIndex(int i) {
                this.fileIndex = i;
            }

            private final boolean getFailed() {
                return this.failed;
            }

            private final void setFailed(boolean z) {
                this.failed = z;
            }

            @Override // kotlin.io.FileTreeWalk.WalkState
            @Nullable
            public File step() {
                if (!this.failed && this.fileList == null) {
                    Function1 function1 = FileTreeWalk.this.onEnter;
                    if (Intrinsics.areEqual((Object) (function1 != null ? (Boolean) function1.invoke(getRoot()) : null), (Object) false)) {
                        return null;
                    }
                    this.fileList = getRoot().listFiles();
                    if (this.fileList == null) {
                        Function2 function2 = FileTreeWalk.this.onFail;
                        if (function2 != null) {
                        }
                        this.failed = true;
                    }
                }
                if (this.fileList != null) {
                    int i = this.fileIndex;
                    File[] fileArr = this.fileList;
                    if (fileArr == null) {
                        Intrinsics.throwNpe();
                    }
                    if (i < fileArr.length) {
                        File[] fileArr2 = this.fileList;
                        if (fileArr2 == null) {
                            Intrinsics.throwNpe();
                        }
                        int i2 = this.fileIndex;
                        this.fileIndex = i2 + 1;
                        return fileArr2[i2];
                    }
                }
                if (this.rootVisited) {
                    Function1 function12 = FileTreeWalk.this.onLeave;
                    if (function12 != null) {
                    }
                    return null;
                }
                this.rootVisited = true;
                return getRoot();
            }
        }

        /* JADX INFO: compiled from: FileTreeWalk.kt */
        @Metadata(bv = {1, 0, 1}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0003H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\fX\u0082\u000e¢\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$TopDownDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "fileIndex", "", "getFileIndex", "()I", "setFileIndex", "(I)V", "fileList", "", "getFileList", "()[Ljava/io/File;", "setFileList", "([Ljava/io/File;)V", "[Ljava/io/File;", "rootVisited", "", "getRootVisited", "()Z", "setRootVisited", "(Z)V", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 5})
        private final class TopDownDirectoryState extends DirectoryState {
            private int fileIndex;
            private File[] fileList;
            private boolean rootVisited;
            final /* synthetic */ FileTreeWalkIterator this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public TopDownDirectoryState(@NotNull FileTreeWalkIterator $outer, File rootDir) {
                super(rootDir);
                Intrinsics.checkParameterIsNotNull(rootDir, "rootDir");
                this.this$0 = $outer;
            }

            private final boolean getRootVisited() {
                return this.rootVisited;
            }

            private final void setRootVisited(boolean z) {
                this.rootVisited = z;
            }

            private final File[] getFileList() {
                return this.fileList;
            }

            private final void setFileList(File[] fileArr) {
                this.fileList = fileArr;
            }

            private final int getFileIndex() {
                return this.fileIndex;
            }

            private final void setFileIndex(int i) {
                this.fileIndex = i;
            }

            /* JADX WARN: Code restructure failed: missing block: B:38:0x009a, code lost:
            
                if (r0.length == 0) goto L39;
             */
            @Override // kotlin.io.FileTreeWalk.WalkState
            @org.jetbrains.annotations.Nullable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.io.File step() {
                /*
                    r10 = this;
                    boolean r0 = r10.rootVisited
                    r1 = 0
                    if (r0 != 0) goto L2f
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.this$0
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1 r0 = kotlin.io.FileTreeWalk.access$getOnEnter$p(r0)
                    if (r0 == 0) goto L1a
                    java.io.File r2 = r10.getRoot()
                    java.lang.Object r0 = r0.invoke(r2)
                    java.lang.Boolean r0 = (java.lang.Boolean) r0
                    goto L1b
                L1a:
                    r0 = r1
                L1b:
                    r2 = 0
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
                    boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
                    if (r0 == 0) goto L27
                    return r1
                L27:
                    r0 = 1
                    r10.rootVisited = r0
                    java.io.File r0 = r10.getRoot()
                    return r0
                L2f:
                    java.io.File[] r0 = r10.fileList
                    if (r0 == 0) goto L57
                    int r0 = r10.fileIndex
                    java.io.File[] r2 = r10.fileList
                    if (r2 != 0) goto L3c
                    kotlin.jvm.internal.Intrinsics.throwNpe()
                L3c:
                    java.lang.Object[] r2 = (java.lang.Object[]) r2
                    int r2 = r2.length
                    if (r0 >= r2) goto L42
                    goto L57
                L42:
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.this$0
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1 r0 = kotlin.io.FileTreeWalk.access$getOnLeave$p(r0)
                    if (r0 == 0) goto L56
                    java.io.File r2 = r10.getRoot()
                    java.lang.Object r0 = r0.invoke(r2)
                    kotlin.Unit r0 = (kotlin.Unit) r0
                L56:
                    return r1
                L57:
                    java.io.File[] r0 = r10.fileList
                    if (r0 != 0) goto Lb1
                    java.io.File r0 = r10.getRoot()
                    java.io.File[] r0 = r0.listFiles()
                    r10.fileList = r0
                    java.io.File[] r0 = r10.fileList
                    if (r0 != 0) goto L8c
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.this$0
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function2 r0 = kotlin.io.FileTreeWalk.access$getOnFail$p(r0)
                    if (r0 == 0) goto L8c
                    java.io.File r2 = r10.getRoot()
                    kotlin.io.AccessDeniedException r9 = new kotlin.io.AccessDeniedException
                    java.io.File r4 = r10.getRoot()
                    r5 = 0
                    java.lang.String r6 = "Cannot list files in a directory"
                    r7 = 2
                    r8 = 0
                    r3 = r9
                    r3.<init>(r4, r5, r6, r7, r8)
                    java.lang.Object r0 = r0.invoke(r2, r9)
                    kotlin.Unit r0 = (kotlin.Unit) r0
                L8c:
                    java.io.File[] r0 = r10.fileList
                    if (r0 == 0) goto L9c
                    java.io.File[] r0 = r10.fileList
                    if (r0 != 0) goto L97
                    kotlin.jvm.internal.Intrinsics.throwNpe()
                L97:
                    java.lang.Object[] r0 = (java.lang.Object[]) r0
                    int r0 = r0.length
                    if (r0 != 0) goto Lb1
                L9c:
                    kotlin.io.FileTreeWalk$FileTreeWalkIterator r0 = r10.this$0
                    kotlin.io.FileTreeWalk r0 = kotlin.io.FileTreeWalk.this
                    kotlin.jvm.functions.Function1 r0 = kotlin.io.FileTreeWalk.access$getOnLeave$p(r0)
                    if (r0 == 0) goto Lb0
                    java.io.File r2 = r10.getRoot()
                    java.lang.Object r0 = r0.invoke(r2)
                    kotlin.Unit r0 = (kotlin.Unit) r0
                Lb0:
                    return r1
                Lb1:
                    java.io.File[] r0 = r10.fileList
                    if (r0 != 0) goto Lb8
                    kotlin.jvm.internal.Intrinsics.throwNpe()
                Lb8:
                    int r1 = r10.fileIndex
                    int r2 = r1 + 1
                    r10.fileIndex = r2
                    r0 = r0[r1]
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FileTreeWalk.FileTreeWalkIterator.TopDownDirectoryState.step():java.io.File");
            }
        }

        /* JADX INFO: compiled from: FileTreeWalk.kt */
        @Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$SingleFileState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootFile", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "visited", "", "getVisited", "()Z", "setVisited", "(Z)V", "step", "kotlin-stdlib"}, k = 1, mv = {1, 1, 5})
        private final class SingleFileState extends WalkState {
            final /* synthetic */ FileTreeWalkIterator this$0;
            private boolean visited;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public SingleFileState(@NotNull FileTreeWalkIterator $outer, File rootFile) {
                super(rootFile);
                Intrinsics.checkParameterIsNotNull(rootFile, "rootFile");
                this.this$0 = $outer;
                if (_Assertions.ENABLED) {
                    boolean zIsFile = rootFile.isFile();
                    if (_Assertions.ENABLED && !zIsFile) {
                        throw new AssertionError("rootFile must be verified to be file beforehand.");
                    }
                }
            }

            private final boolean getVisited() {
                return this.visited;
            }

            private final void setVisited(boolean z) {
                this.visited = z;
            }

            @Override // kotlin.io.FileTreeWalk.WalkState
            @Nullable
            public File step() {
                if (this.visited) {
                    return null;
                }
                this.visited = true;
                return getRoot();
            }
        }
    }

    @NotNull
    public final FileTreeWalk onEnter(@NotNull Function1<? super File, Boolean> function) {
        Intrinsics.checkParameterIsNotNull(function, "function");
        return new FileTreeWalk(this.start, this.direction, function, this.onLeave, this.onFail, this.maxDepth);
    }

    @NotNull
    public final FileTreeWalk onLeave(@NotNull Function1<? super File, Unit> function) {
        Intrinsics.checkParameterIsNotNull(function, "function");
        return new FileTreeWalk(this.start, this.direction, this.onEnter, function, this.onFail, this.maxDepth);
    }

    @NotNull
    public final FileTreeWalk onFail(@NotNull Function2<? super File, ? super IOException, Unit> function) {
        Intrinsics.checkParameterIsNotNull(function, "function");
        return new FileTreeWalk(this.start, this.direction, this.onEnter, this.onLeave, function, this.maxDepth);
    }

    @NotNull
    public final FileTreeWalk maxDepth(int depth) {
        if (depth <= 0) {
            throw new IllegalArgumentException("depth must be positive, but was " + depth + ".");
        }
        return new FileTreeWalk(this.start, this.direction, this.onEnter, this.onLeave, this.onFail, depth);
    }
}
