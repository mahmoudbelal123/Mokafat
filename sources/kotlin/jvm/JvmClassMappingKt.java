package kotlin.jvm;

import java.lang.annotation.Annotation;
import java.lang.reflect.GenericDeclaration;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: JvmClassMapping.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a!\u0010\u0018\u001a\u00020\u0019\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\r*\u0006\u0012\u0002\b\u00030\u001aH\u0007¢\u0006\u0002\u0010\u001b\"'\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\"0\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00018GX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"&\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\u0002H\u00028Ç\u0002¢\u0006\u0006\u001a\u0004\b\n\u0010\u000e\";\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018Ç\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u000f\u0010\t\u001a\u0004\b\u0010\u0010\u000b\"+\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000b\"-\u0010\u0013\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0007\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000b\"+\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\r*\b\u0012\u0004\u0012\u0002H\u00020\u00078G¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001c"}, d2 = {"annotationClass", "Lkotlin/reflect/KClass;", "T", "", "getAnnotationClass", "(Ljava/lang/annotation/Annotation;)Lkotlin/reflect/KClass;", "java", "Ljava/lang/Class;", "java$annotations", "(Lkotlin/reflect/KClass;)V", "getJavaClass", "(Lkotlin/reflect/KClass;)Ljava/lang/Class;", "javaClass", "", "(Ljava/lang/Object;)Ljava/lang/Class;", "javaClass$annotations", "getRuntimeClassOfKClassInstance", "javaObjectType", "getJavaObjectType", "javaPrimitiveType", "getJavaPrimitiveType", "kotlin", "getKotlinClass", "(Ljava/lang/Class;)Lkotlin/reflect/KClass;", "isArrayOf", "", "", "([Ljava/lang/Object;)Z", "kotlin-runtime"}, k = 2, mv = {1, 1, 5})
@JvmName(name = "JvmClassMappingKt")
public final class JvmClassMappingKt {
    private static /* synthetic */ void java$annotations(KClass kClass) {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use 'java' property to get Java class corresponding to this Kotlin class or cast this instance to Any if you really want to get the runtime Java class of this implementation of KClass.", replaceWith = @ReplaceWith(expression = "(this as Any).javaClass", imports = {}))
    private static /* synthetic */ void javaClass$annotations(KClass kClass) {
    }

    @JvmName(name = "getJavaClass")
    @NotNull
    public static final <T> Class<T> getJavaClass(@NotNull KClass<T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.jvm.internal.ClassBasedDeclarationContainer");
        }
        GenericDeclaration jClass = ((ClassBasedDeclarationContainer) receiver).getJClass();
        if (jClass == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<T>");
        }
        return (Class) jClass;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0091  */
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final <T> java.lang.Class<T> getJavaPrimitiveType(@org.jetbrains.annotations.NotNull kotlin.reflect.KClass<T> r3) {
        /*
            java.lang.String r0 = "$receiver"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            if (r3 != 0) goto Lf
            kotlin.TypeCastException r0 = new kotlin.TypeCastException
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.jvm.internal.ClassBasedDeclarationContainer"
            r0.<init>(r1)
            throw r0
        Lf:
            r0 = r3
            kotlin.jvm.internal.ClassBasedDeclarationContainer r0 = (kotlin.jvm.internal.ClassBasedDeclarationContainer) r0
            java.lang.Class r0 = r0.getJClass()
            boolean r1 = r0.isPrimitive()
            if (r1 == 0) goto L2a
            if (r0 != 0) goto L26
            kotlin.TypeCastException r1 = new kotlin.TypeCastException
            java.lang.String r2 = "null cannot be cast to non-null type java.lang.Class<T>"
            r1.<init>(r2)
            throw r1
        L26:
            r1 = r0
            java.lang.Class r1 = (java.lang.Class) r1
            return r1
        L2a:
            java.lang.String r1 = r0.getName()
            if (r1 != 0) goto L31
            goto L91
        L31:
            int r2 = r1.hashCode()
            switch(r2) {
                case -2056817302: goto L86;
                case -527879800: goto L7b;
                case -515992664: goto L70;
                case 155276373: goto L65;
                case 344809556: goto L5a;
                case 398507100: goto L4f;
                case 398795216: goto L44;
                case 761287205: goto L39;
                default: goto L38;
            }
        L38:
            goto L91
        L39:
            java.lang.String r2 = "java.lang.Double"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L91
            java.lang.Class r1 = java.lang.Double.TYPE
            goto L92
        L44:
            java.lang.String r2 = "java.lang.Long"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L91
            java.lang.Class r1 = java.lang.Long.TYPE
            goto L92
        L4f:
            java.lang.String r2 = "java.lang.Byte"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L91
            java.lang.Class r1 = java.lang.Byte.TYPE
            goto L92
        L5a:
            java.lang.String r2 = "java.lang.Boolean"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L91
            java.lang.Class r1 = java.lang.Boolean.TYPE
            goto L92
        L65:
            java.lang.String r2 = "java.lang.Character"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L91
            java.lang.Class r1 = java.lang.Character.TYPE
            goto L92
        L70:
            java.lang.String r2 = "java.lang.Short"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L91
            java.lang.Class r1 = java.lang.Short.TYPE
            goto L92
        L7b:
            java.lang.String r2 = "java.lang.Float"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L91
            java.lang.Class r1 = java.lang.Float.TYPE
            goto L92
        L86:
            java.lang.String r2 = "java.lang.Integer"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L91
            java.lang.Class r1 = java.lang.Integer.TYPE
            goto L92
        L91:
            r1 = 0
        L92:
            java.lang.Class r1 = (java.lang.Class) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.jvm.JvmClassMappingKt.getJavaPrimitiveType(kotlin.reflect.KClass):java.lang.Class");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @NotNull
    public static final <T> Class<T> getJavaObjectType(@NotNull KClass<T> receiver) {
        Class cls;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.jvm.internal.ClassBasedDeclarationContainer");
        }
        Class thisJClass = ((ClassBasedDeclarationContainer) receiver).getJClass();
        if (!thisJClass.isPrimitive()) {
            if (thisJClass == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<T>");
            }
            return thisJClass;
        }
        String name = thisJClass.getName();
        if (name != null) {
            switch (name.hashCode()) {
                case -1325958191:
                    cls = !name.equals("double") ? thisJClass : Double.class;
                    break;
                case 104431:
                    cls = !name.equals("int") ? thisJClass : Integer.class;
                    break;
                case 3039496:
                    cls = !name.equals("byte") ? thisJClass : Byte.class;
                    break;
                case 3052374:
                    cls = !name.equals("char") ? thisJClass : Character.class;
                    break;
                case 3327612:
                    cls = !name.equals("long") ? thisJClass : Long.class;
                    break;
                case 64711720:
                    cls = !name.equals("boolean") ? thisJClass : Boolean.class;
                    break;
                case 97526364:
                    cls = !name.equals("float") ? thisJClass : Float.class;
                    break;
                case 109413500:
                    cls = !name.equals("short") ? thisJClass : Short.class;
                    break;
                default:
                    cls = thisJClass;
                    break;
            }
        } else {
            cls = thisJClass;
        }
        if (cls == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<T>");
        }
        return cls;
    }

    @JvmName(name = "getKotlinClass")
    @NotNull
    public static final <T> KClass<T> getKotlinClass(@NotNull Class<T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        KClass<T> kClassCreateKotlinClass = Reflection.createKotlinClass(receiver);
        if (kClassCreateKotlinClass == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<T>");
        }
        return kClassCreateKotlinClass;
    }

    @NotNull
    public static final <T> Class<T> getJavaClass(@NotNull T receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
        }
        GenericDeclaration genericDeclaration = receiver.getClass();
        if (genericDeclaration == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<T>");
        }
        return (Class) genericDeclaration;
    }

    @JvmName(name = "getRuntimeClassOfKClassInstance")
    @NotNull
    public static final <T> Class<KClass<T>> getRuntimeClassOfKClassInstance(@NotNull KClass<T> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
        }
        GenericDeclaration genericDeclaration = receiver.getClass();
        if (genericDeclaration == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.Class<kotlin.reflect.KClass<T>>");
        }
        return (Class) genericDeclaration;
    }

    private static final <T> boolean isArrayOf(@NotNull Object[] $receiver) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return Object.class.isAssignableFrom($receiver.getClass().getComponentType());
    }

    @NotNull
    public static final <T extends Annotation> KClass<? extends T> getAnnotationClass(@NotNull T receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        if (receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.annotation.Annotation");
        }
        KClass<? extends T> kotlinClass = getKotlinClass(receiver.annotationType());
        if (kotlinClass == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KClass<out T>");
        }
        return kotlinClass;
    }
}
