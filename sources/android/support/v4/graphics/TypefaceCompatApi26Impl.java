package android.support.v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
@RequiresApi(26)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    private static final String ABORT_CREATION_METHOD = "abortCreation";
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
    private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String FREEZE_METHOD = "freeze";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi26Impl";
    private static final Method sAbortCreation;
    private static final Method sAddFontFromAssetManager;
    private static final Method sAddFontFromBuffer;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;
    private static final Method sFreeze;

    static {
        Constructor fontFamilyCtor;
        Constructor constructor;
        Constructor constructor2;
        Class<?> cls;
        Constructor<?> constructor3;
        Method addFontMethod;
        Method createFromFamiliesWithDefaultMethod;
        Method freezeMethod;
        Method abortCreationMethod;
        Method createFromFamiliesWithDefaultMethod2;
        Method abortCreationMethod2;
        Method createFromFamiliesWithDefaultMethod3 = null;
        try {
            cls = Class.forName(FONT_FAMILY_CLASS);
            try {
                constructor3 = cls.getConstructor(new Class[0]);
                try {
                    addFontMethod = cls.getMethod(ADD_FONT_FROM_ASSET_MANAGER_METHOD, AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class);
                } catch (ClassNotFoundException | NoSuchMethodException e) {
                    e = e;
                    constructor = null;
                    constructor2 = constructor;
                    Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
                    cls = null;
                    constructor3 = null;
                    addFontMethod = null;
                    createFromFamiliesWithDefaultMethod = null;
                    freezeMethod = null;
                    abortCreationMethod = null;
                    createFromFamiliesWithDefaultMethod2 = null;
                    sFontFamilyCtor = constructor3;
                    sFontFamily = cls;
                    sAddFontFromAssetManager = addFontMethod;
                    sAddFontFromBuffer = createFromFamiliesWithDefaultMethod;
                    sFreeze = freezeMethod;
                    sAbortCreation = abortCreationMethod;
                    sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod2;
                }
            } catch (ClassNotFoundException | NoSuchMethodException e2) {
                e = e2;
                fontFamilyCtor = null;
                constructor = fontFamilyCtor;
                constructor2 = constructor;
                Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
                cls = null;
                constructor3 = null;
                addFontMethod = null;
                createFromFamiliesWithDefaultMethod = null;
                freezeMethod = null;
                abortCreationMethod = null;
                createFromFamiliesWithDefaultMethod2 = null;
                sFontFamilyCtor = constructor3;
                sFontFamily = cls;
                sAddFontFromAssetManager = addFontMethod;
                sAddFontFromBuffer = createFromFamiliesWithDefaultMethod;
                sFreeze = freezeMethod;
                sAbortCreation = abortCreationMethod;
                sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod2;
            }
        } catch (ClassNotFoundException | NoSuchMethodException e3) {
            e = e3;
            fontFamilyCtor = null;
        }
        try {
            Method addFromBufferMethod = cls.getMethod(ADD_FONT_FROM_BUFFER_METHOD, ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE);
            try {
                freezeMethod = cls.getMethod(FREEZE_METHOD, new Class[0]);
                try {
                    abortCreationMethod = cls.getMethod(ABORT_CREATION_METHOD, new Class[0]);
                    try {
                        Object familyArray = Array.newInstance(cls, 1);
                        createFromFamiliesWithDefaultMethod3 = Typeface.class.getDeclaredMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, familyArray.getClass(), Integer.TYPE, Integer.TYPE);
                        createFromFamiliesWithDefaultMethod3.setAccessible(true);
                        createFromFamiliesWithDefaultMethod2 = createFromFamiliesWithDefaultMethod3;
                        createFromFamiliesWithDefaultMethod = addFromBufferMethod;
                    } catch (ClassNotFoundException | NoSuchMethodException e4) {
                        e = e4;
                        Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
                        cls = null;
                        constructor3 = null;
                        addFontMethod = null;
                        createFromFamiliesWithDefaultMethod = null;
                        freezeMethod = null;
                        abortCreationMethod = null;
                        createFromFamiliesWithDefaultMethod2 = null;
                    }
                } catch (ClassNotFoundException | NoSuchMethodException e5) {
                    e = e5;
                    abortCreationMethod2 = null;
                    Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
                    cls = null;
                    constructor3 = null;
                    addFontMethod = null;
                    createFromFamiliesWithDefaultMethod = null;
                    freezeMethod = null;
                    abortCreationMethod = null;
                    createFromFamiliesWithDefaultMethod2 = null;
                    sFontFamilyCtor = constructor3;
                    sFontFamily = cls;
                    sAddFontFromAssetManager = addFontMethod;
                    sAddFontFromBuffer = createFromFamiliesWithDefaultMethod;
                    sFreeze = freezeMethod;
                    sAbortCreation = abortCreationMethod;
                    sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod2;
                }
            } catch (ClassNotFoundException | NoSuchMethodException e6) {
                e = e6;
                abortCreationMethod2 = null;
            }
        } catch (ClassNotFoundException | NoSuchMethodException e7) {
            e = e7;
            constructor2 = null;
            Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
            cls = null;
            constructor3 = null;
            addFontMethod = null;
            createFromFamiliesWithDefaultMethod = null;
            freezeMethod = null;
            abortCreationMethod = null;
            createFromFamiliesWithDefaultMethod2 = null;
            sFontFamilyCtor = constructor3;
            sFontFamily = cls;
            sAddFontFromAssetManager = addFontMethod;
            sAddFontFromBuffer = createFromFamiliesWithDefaultMethod;
            sFreeze = freezeMethod;
            sAbortCreation = abortCreationMethod;
            sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod2;
        }
        sFontFamilyCtor = constructor3;
        sFontFamily = cls;
        sAddFontFromAssetManager = addFontMethod;
        sAddFontFromBuffer = createFromFamiliesWithDefaultMethod;
        sFreeze = freezeMethod;
        sAbortCreation = abortCreationMethod;
        sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod2;
    }

    private static boolean isFontFamilyPrivateAPIAvailable() {
        if (sAddFontFromAssetManager == null) {
            Log.w(TAG, "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return sAddFontFromAssetManager != null;
    }

    private static Object newFamily() {
        try {
            return sFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontFromAssetManager(Context context, Object family, String fileName, int ttcIndex, int weight, int style) {
        try {
            Boolean result = (Boolean) sAddFontFromAssetManager.invoke(family, context.getAssets(), fileName, 0, false, Integer.valueOf(ttcIndex), Integer.valueOf(weight), Integer.valueOf(style), null);
            return result.booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontFromBuffer(Object family, ByteBuffer buffer, int ttcIndex, int weight, int style) {
        try {
            Boolean result = (Boolean) sAddFontFromBuffer.invoke(family, buffer, Integer.valueOf(ttcIndex), null, Integer.valueOf(weight), Integer.valueOf(style));
            return result.booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Typeface createFromFamiliesWithDefault(Object family) {
        try {
            Object familyArray = Array.newInstance((Class<?>) sFontFamily, 1);
            Array.set(familyArray, 0, family);
            return (Typeface) sCreateFromFamiliesWithDefault.invoke(null, familyArray, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean freeze(Object family) {
        try {
            Boolean result = (Boolean) sFreeze.invoke(family, new Object[0]);
            return result.booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static void abortCreation(Object family) {
        try {
            sAbortCreation.invoke(family, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.support.v4.graphics.TypefaceCompatBaseImpl, android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context, fontFamilyFilesResourceEntry, resources, i);
        }
        Object objNewFamily = newFamily();
        for (FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry : fontFamilyFilesResourceEntry.getEntries()) {
            if (!addFontFromAssetManager(context, objNewFamily, fontFileResourceEntry.getFileName(), 0, fontFileResourceEntry.getWeight(), fontFileResourceEntry.isItalic() ? 1 : 0)) {
                abortCreation(objNewFamily);
                return null;
            }
        }
        if (freeze(objNewFamily)) {
            return createFromFamiliesWithDefault(objNewFamily);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[Catch: IOException -> 0x006f, SYNTHETIC, TRY_LEAVE, TryCatch #1 {IOException -> 0x006f, blocks: (B:8:0x001d, B:11:0x002d, B:15:0x0050, B:26:0x0062, B:30:0x006b, B:29:0x0067, B:31:0x006e), top: B:54:0x001d, inners: #3 }] */
    @Override // android.support.v4.graphics.TypefaceCompatApi21Impl, android.support.v4.graphics.TypefaceCompatBaseImpl, android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r18, @android.support.annotation.Nullable android.os.CancellationSignal r19, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] r20, int r21) throws java.lang.Throwable {
        /*
            r17 = this;
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = 1
            r5 = 0
            int r6 = r2.length
            if (r6 >= r4) goto Lc
            return r5
        Lc:
            boolean r4 = isFontFamilyPrivateAPIAvailable()
            if (r4 != 0) goto L72
            r4 = r17
            android.support.v4.provider.FontsContractCompat$FontInfo r6 = r4.findBestInfo(r2, r3)
            android.content.ContentResolver r7 = r18.getContentResolver()
            android.net.Uri r8 = r6.getUri()     // Catch: java.io.IOException -> L6f
            java.lang.String r9 = "r"
            android.os.ParcelFileDescriptor r8 = r7.openFileDescriptor(r8, r9, r1)     // Catch: java.io.IOException -> L6f
            if (r8 != 0) goto L31
        L2b:
            if (r8 == 0) goto L30
            r8.close()     // Catch: java.io.IOException -> L6f
        L30:
            return r5
        L31:
            android.graphics.Typeface$Builder r9 = new android.graphics.Typeface$Builder     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L58
            java.io.FileDescriptor r10 = r8.getFileDescriptor()     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L58
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L58
            int r10 = r6.getWeight()     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L58
            android.graphics.Typeface$Builder r9 = r9.setWeight(r10)     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L58
            boolean r10 = r6.isItalic()     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L58
            android.graphics.Typeface$Builder r9 = r9.setItalic(r10)     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L58
            android.graphics.Typeface r9 = r9.build()     // Catch: java.lang.Throwable -> L54 java.lang.Throwable -> L58
            if (r8 == 0) goto L53
            r8.close()     // Catch: java.io.IOException -> L6f
        L53:
            return r9
        L54:
            r0 = move-exception
            r9 = r0
            r10 = r5
            goto L5e
        L58:
            r0 = move-exception
            r9 = r0
            throw r9     // Catch: java.lang.Throwable -> L5b
        L5b:
            r0 = move-exception
            r10 = r9
            r9 = r0
        L5e:
            if (r8 == 0) goto L6e
            if (r10 == 0) goto L6b
            r8.close()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L6f
            goto L6e
        L66:
            r0 = move-exception
            r10.addSuppressed(r0)     // Catch: java.io.IOException -> L6f
            goto L6e
        L6b:
            r8.close()     // Catch: java.io.IOException -> L6f
        L6e:
            throw r9     // Catch: java.io.IOException -> L6f
        L6f:
            r0 = move-exception
            r8 = r0
            return r5
        L72:
            r4 = r17
            r6 = r18
            java.util.Map r7 = android.support.v4.provider.FontsContractCompat.prepareFontData(r6, r2, r1)
            java.lang.Object r8 = newFamily()
            r9 = 0
            int r10 = r2.length
            r11 = 0
        L81:
            if (r11 >= r10) goto Lb0
            r12 = r2[r11]
            android.net.Uri r13 = r12.getUri()
            java.lang.Object r13 = r7.get(r13)
            java.nio.ByteBuffer r13 = (java.nio.ByteBuffer) r13
            if (r13 != 0) goto L92
            goto Lac
        L92:
            int r14 = r12.getTtcIndex()
            int r15 = r12.getWeight()
            boolean r5 = r12.isItalic()
            boolean r5 = addFontFromBuffer(r8, r13, r14, r15, r5)
            if (r5 != 0) goto Laa
            abortCreation(r8)
            r10 = 0
            return r10
        Laa:
            r5 = 1
            r9 = r5
        Lac:
            int r11 = r11 + 1
            r5 = 0
            goto L81
        Lb0:
            if (r9 != 0) goto Lb7
            abortCreation(r8)
            r5 = 0
            return r5
        Lb7:
            r5 = 0
            boolean r10 = freeze(r8)
            if (r10 != 0) goto Lbf
            return r5
        Lbf:
            android.graphics.Typeface r5 = createFromFamiliesWithDefault(r8)
            android.graphics.Typeface r10 = android.graphics.Typeface.create(r5, r3)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    @Override // android.support.v4.graphics.TypefaceCompatBaseImpl, android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int id, String path, int style) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context, resources, id, path, style);
        }
        Object fontFamily = newFamily();
        if (!addFontFromAssetManager(context, fontFamily, path, 0, -1, -1)) {
            abortCreation(fontFamily);
            return null;
        }
        if (freeze(fontFamily)) {
            return createFromFamiliesWithDefault(fontFamily);
        }
        return null;
    }
}
