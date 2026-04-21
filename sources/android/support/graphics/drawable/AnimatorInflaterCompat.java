package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build;
import android.support.annotation.AnimatorRes;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import android.view.animation.Interpolator;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class AnimatorInflaterCompat {
    private static final boolean DBG_ANIMATOR_INFLATER = false;
    private static final int MAX_NUM_POINTS = 100;
    private static final String TAG = "AnimatorInflater";
    private static final int TOGETHER = 0;
    private static final int VALUE_TYPE_COLOR = 3;
    private static final int VALUE_TYPE_FLOAT = 0;
    private static final int VALUE_TYPE_INT = 1;
    private static final int VALUE_TYPE_PATH = 2;
    private static final int VALUE_TYPE_UNDEFINED = 4;

    public static Animator loadAnimator(Context context, @AnimatorRes int id) throws Resources.NotFoundException {
        if (Build.VERSION.SDK_INT >= 24) {
            Animator objectAnimator = AnimatorInflater.loadAnimator(context, id);
            return objectAnimator;
        }
        Animator objectAnimator2 = loadAnimator(context, context.getResources(), context.getTheme(), id);
        return objectAnimator2;
    }

    public static Animator loadAnimator(Context context, Resources resources, Resources.Theme theme, @AnimatorRes int id) throws Resources.NotFoundException {
        return loadAnimator(context, resources, theme, id, 1.0f);
    }

    public static Animator loadAnimator(Context context, Resources resources, Resources.Theme theme, @AnimatorRes int id, float pathErrorScale) throws Resources.NotFoundException {
        XmlResourceParser parser = null;
        try {
            try {
                try {
                    parser = resources.getAnimation(id);
                    Animator animator = createAnimatorFromXml(context, resources, theme, parser, pathErrorScale);
                    return animator;
                } catch (IOException ex) {
                    Resources.NotFoundException rnf = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
                    rnf.initCause(ex);
                    throw rnf;
                }
            } catch (XmlPullParserException ex2) {
                Resources.NotFoundException rnf2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
                rnf2.initCause(ex2);
                throw rnf2;
            }
        } finally {
            if (parser != null) {
                parser.close();
            }
        }
    }

    private static class PathDataEvaluator implements TypeEvaluator<PathParser.PathDataNode[]> {
        private PathParser.PathDataNode[] mNodeArray;

        private PathDataEvaluator() {
        }

        PathDataEvaluator(PathParser.PathDataNode[] nodeArray) {
            this.mNodeArray = nodeArray;
        }

        @Override // android.animation.TypeEvaluator
        public PathParser.PathDataNode[] evaluate(float fraction, PathParser.PathDataNode[] startPathData, PathParser.PathDataNode[] endPathData) {
            if (!PathParser.canMorph(startPathData, endPathData)) {
                throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
            }
            if (this.mNodeArray == null || !PathParser.canMorph(this.mNodeArray, startPathData)) {
                this.mNodeArray = PathParser.deepCopyNodes(startPathData);
            }
            for (int i = 0; i < startPathData.length; i++) {
                this.mNodeArray[i].interpolatePathDataNode(startPathData[i], endPathData[i], fraction);
            }
            return this.mNodeArray;
        }
    }

    private static PropertyValuesHolder getPVH(TypedArray styledAttributes, int valueType, int valueFromId, int valueToId, String propertyName) {
        int valueType2;
        PropertyValuesHolder returnValue;
        int valueTo;
        int valueTo2;
        int valueFrom;
        int valueTo3;
        int valueTo4;
        float valueTo5;
        PropertyValuesHolder propertyValuesHolderOfFloat;
        float valueFrom2;
        float valueTo6;
        int toType;
        PropertyValuesHolder propertyValuesHolder;
        PropertyValuesHolder propertyValuesHolderOfObject;
        TypedValue tvFrom = styledAttributes.peekValue(valueFromId);
        boolean hasFrom = tvFrom != null;
        int fromType = hasFrom ? tvFrom.type : 0;
        TypedValue tvTo = styledAttributes.peekValue(valueToId);
        boolean hasTo = tvTo != null;
        int toType2 = hasTo ? tvTo.type : 0;
        if (valueType == 4) {
            if ((hasFrom && isColorType(fromType)) || (hasTo && isColorType(toType2))) {
                valueType2 = 3;
            } else {
                valueType2 = 0;
            }
        } else {
            valueType2 = valueType;
        }
        boolean getFloats = valueType2 == 0;
        if (valueType2 == 2) {
            String fromString = styledAttributes.getString(valueFromId);
            String toString = styledAttributes.getString(valueToId);
            PathParser.PathDataNode[] nodesFrom = PathParser.createNodesFromPathData(fromString);
            PathParser.PathDataNode[] nodesTo = PathParser.createNodesFromPathData(toString);
            if (nodesFrom == null && nodesTo == null) {
                toType = toType2;
                propertyValuesHolder = null;
            } else {
                if (nodesFrom != null) {
                    TypeEvaluator evaluator = new PathDataEvaluator();
                    if (nodesTo != null) {
                        if (!PathParser.canMorph(nodesFrom, nodesTo)) {
                            throw new InflateException(" Can't morph from " + fromString + " to " + toString);
                        }
                        toType = toType2;
                        propertyValuesHolderOfObject = PropertyValuesHolder.ofObject(propertyName, evaluator, nodesFrom, nodesTo);
                    } else {
                        toType = toType2;
                        propertyValuesHolderOfObject = PropertyValuesHolder.ofObject(propertyName, evaluator, nodesFrom);
                    }
                    returnValue = propertyValuesHolderOfObject;
                } else {
                    toType = toType2;
                    propertyValuesHolder = null;
                    if (nodesTo != null) {
                        TypeEvaluator evaluator2 = new PathDataEvaluator();
                        returnValue = PropertyValuesHolder.ofObject(propertyName, evaluator2, nodesTo);
                    }
                }
            }
            returnValue = propertyValuesHolder;
        } else {
            int toType3 = toType2;
            TypeEvaluator evaluator3 = null;
            if (valueType2 == 3) {
                evaluator3 = ArgbEvaluator.getInstance();
            }
            if (getFloats) {
                if (hasFrom) {
                    if (fromType == 5) {
                        valueFrom2 = styledAttributes.getDimension(valueFromId, 0.0f);
                    } else {
                        valueFrom2 = styledAttributes.getFloat(valueFromId, 0.0f);
                    }
                    if (!hasTo) {
                        propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(propertyName, valueFrom2);
                    } else {
                        if (toType3 == 5) {
                            valueTo6 = styledAttributes.getDimension(valueToId, 0.0f);
                        } else {
                            valueTo6 = styledAttributes.getFloat(valueToId, 0.0f);
                        }
                        PropertyValuesHolder returnValue2 = PropertyValuesHolder.ofFloat(propertyName, valueFrom2, valueTo6);
                        returnValue = returnValue2;
                    }
                } else {
                    if (toType3 == 5) {
                        valueTo5 = styledAttributes.getDimension(valueToId, 0.0f);
                    } else {
                        valueTo5 = styledAttributes.getFloat(valueToId, 0.0f);
                    }
                    propertyValuesHolderOfFloat = PropertyValuesHolder.ofFloat(propertyName, valueTo5);
                }
                returnValue = propertyValuesHolderOfFloat;
            } else if (hasFrom) {
                if (fromType == 5) {
                    int valueFrom3 = (int) styledAttributes.getDimension(valueFromId, 0.0f);
                    valueFrom = valueFrom3;
                } else {
                    valueFrom = isColorType(fromType) ? styledAttributes.getColor(valueFromId, 0) : styledAttributes.getInt(valueFromId, 0);
                }
                int valueFrom4 = valueFrom;
                if (!hasTo) {
                    returnValue = PropertyValuesHolder.ofInt(propertyName, valueFrom4);
                } else {
                    if (toType3 == 5) {
                        int valueTo7 = (int) styledAttributes.getDimension(valueToId, 0.0f);
                        valueTo4 = valueTo7;
                        valueTo3 = 0;
                    } else if (isColorType(toType3)) {
                        valueTo3 = 0;
                        valueTo4 = styledAttributes.getColor(valueToId, 0);
                    } else {
                        valueTo3 = 0;
                        valueTo4 = styledAttributes.getInt(valueToId, 0);
                    }
                    int[] iArr = new int[2];
                    iArr[valueTo3] = valueFrom4;
                    iArr[1] = valueTo4;
                    returnValue = PropertyValuesHolder.ofInt(propertyName, iArr);
                }
            } else if (hasTo) {
                if (toType3 == 5) {
                    int valueTo8 = (int) styledAttributes.getDimension(valueToId, 0.0f);
                    valueTo2 = valueTo8;
                    valueTo = 0;
                } else if (isColorType(toType3)) {
                    valueTo = 0;
                    valueTo2 = styledAttributes.getColor(valueToId, 0);
                } else {
                    valueTo = 0;
                    valueTo2 = styledAttributes.getInt(valueToId, 0);
                }
                int[] iArr2 = new int[1];
                iArr2[valueTo] = valueTo2;
                returnValue = PropertyValuesHolder.ofInt(propertyName, iArr2);
            } else {
                returnValue = null;
            }
            if (returnValue != null && evaluator3 != null) {
                returnValue.setEvaluator(evaluator3);
            }
        }
        return returnValue;
    }

    private static void parseAnimatorFromTypeArray(ValueAnimator anim, TypedArray arrayAnimator, TypedArray arrayObjectAnimator, float pixelSize, XmlPullParser parser) {
        long duration = TypedArrayUtils.getNamedInt(arrayAnimator, parser, "duration", 1, 300);
        long startDelay = TypedArrayUtils.getNamedInt(arrayAnimator, parser, "startOffset", 2, 0);
        int valueType = TypedArrayUtils.getNamedInt(arrayAnimator, parser, "valueType", 7, 4);
        if (TypedArrayUtils.hasAttribute(parser, "valueFrom") && TypedArrayUtils.hasAttribute(parser, "valueTo")) {
            if (valueType == 4) {
                valueType = inferValueTypeFromValues(arrayAnimator, 5, 6);
            }
            PropertyValuesHolder pvh = getPVH(arrayAnimator, valueType, 5, 6, "");
            if (pvh != null) {
                anim.setValues(pvh);
            }
        }
        anim.setDuration(duration);
        anim.setStartDelay(startDelay);
        anim.setRepeatCount(TypedArrayUtils.getNamedInt(arrayAnimator, parser, "repeatCount", 3, 0));
        anim.setRepeatMode(TypedArrayUtils.getNamedInt(arrayAnimator, parser, "repeatMode", 4, 1));
        if (arrayObjectAnimator != null) {
            setupObjectAnimator(anim, arrayObjectAnimator, valueType, pixelSize, parser);
        }
    }

    private static void setupObjectAnimator(ValueAnimator anim, TypedArray arrayObjectAnimator, int valueType, float pixelSize, XmlPullParser parser) {
        ObjectAnimator oa = (ObjectAnimator) anim;
        String pathData = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "pathData", 1);
        if (pathData != null) {
            String propertyXName = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyXName", 2);
            String propertyYName = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyYName", 3);
            if (valueType == 2 || valueType == 4) {
            }
            if (propertyXName == null && propertyYName == null) {
                throw new InflateException(arrayObjectAnimator.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
            }
            Path path = PathParser.createPathFromPathData(pathData);
            setupPathMotion(path, oa, 0.5f * pixelSize, propertyXName, propertyYName);
            return;
        }
        String propertyName = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyName", 0);
        oa.setPropertyName(propertyName);
    }

    private static void setupPathMotion(Path path, ObjectAnimator oa, float precision, String propertyXName, String propertyYName) {
        PathMeasure measureForTotalLength = new PathMeasure(path, false);
        float totalLength = 0.0f;
        ArrayList<Float> contourLengths = new ArrayList<>();
        contourLengths.add(Float.valueOf(0.0f));
        do {
            float pathLength = measureForTotalLength.getLength();
            totalLength += pathLength;
            contourLengths.add(Float.valueOf(totalLength));
        } while (measureForTotalLength.nextContour());
        PathMeasure pathMeasure = new PathMeasure(path, false);
        int numPoints = Math.min(100, ((int) (totalLength / precision)) + 1);
        float[] mX = new float[numPoints];
        float[] mY = new float[numPoints];
        float[] position = new float[2];
        float step = totalLength / (numPoints - 1);
        float currentDistance = 0.0f;
        int contourIndex = 0;
        int contourIndex2 = 0;
        while (true) {
            int i = contourIndex2;
            if (i >= numPoints) {
                break;
            }
            pathMeasure.getPosTan(currentDistance, position, null);
            mX[i] = position[0];
            mY[i] = position[1];
            currentDistance += step;
            PathMeasure measureForTotalLength2 = measureForTotalLength;
            if (contourIndex + 1 < contourLengths.size() && currentDistance > contourLengths.get(contourIndex + 1).floatValue()) {
                currentDistance -= contourLengths.get(contourIndex + 1).floatValue();
                contourIndex++;
                pathMeasure.nextContour();
            }
            contourIndex2 = i + 1;
            measureForTotalLength = measureForTotalLength2;
        }
        PropertyValuesHolder x = propertyXName != null ? PropertyValuesHolder.ofFloat(propertyXName, mX) : null;
        PropertyValuesHolder y = propertyYName != null ? PropertyValuesHolder.ofFloat(propertyYName, mY) : null;
        if (x == null) {
            oa.setValues(y);
        } else if (y == null) {
            oa.setValues(x);
        } else {
            oa.setValues(x, y);
        }
    }

    private static Animator createAnimatorFromXml(Context context, Resources res, Resources.Theme theme, XmlPullParser parser, float pixelSize) throws XmlPullParserException, IOException {
        return createAnimatorFromXml(context, res, theme, parser, Xml.asAttributeSet(parser), null, 0, pixelSize);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00fe, code lost:
    
        if (r25 == null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0100, code lost:
    
        if (r12 == null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0102, code lost:
    
        r0 = new android.animation.Animator[r12.size()];
        r2 = 0;
        r3 = r12.iterator();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x0111, code lost:
    
        if (r3.hasNext() == false) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x0113, code lost:
    
        r0[r2] = r3.next();
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0120, code lost:
    
        if (r26 != 0) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0122, code lost:
    
        r25.playTogether(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0126, code lost:
    
        r25.playSequentially(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0129, code lost:
    
        return r7;
     */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00cf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.animation.Animator createAnimatorFromXml(android.content.Context r20, android.content.res.Resources r21, android.content.res.Resources.Theme r22, org.xmlpull.v1.XmlPullParser r23, android.util.AttributeSet r24, android.animation.AnimatorSet r25, int r26, float r27) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 298
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.AnimatorInflaterCompat.createAnimatorFromXml(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.animation.AnimatorSet, int, float):android.animation.Animator");
    }

    private static PropertyValuesHolder[] loadValues(Context context, Resources res, Resources.Theme theme, XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
        ArrayList<PropertyValuesHolder> values;
        ArrayList<PropertyValuesHolder> values2 = null;
        while (true) {
            values = values2;
            int type = parser.getEventType();
            if (type == 3 || type == 1) {
                break;
            }
            if (type != 2) {
                parser.next();
                values2 = values;
            } else {
                String name = parser.getName();
                if (name.equals("propertyValuesHolder")) {
                    TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER);
                    String propertyName = TypedArrayUtils.getNamedString(a, parser, "propertyName", 3);
                    int valueType = TypedArrayUtils.getNamedInt(a, parser, "valueType", 2, 4);
                    PropertyValuesHolder pvh = loadPvh(context, res, theme, parser, propertyName, valueType);
                    if (pvh == null) {
                        pvh = getPVH(a, valueType, 0, 1, propertyName);
                    }
                    if (pvh != null) {
                        if (values == null) {
                            values = new ArrayList<>();
                        }
                        values.add(pvh);
                    }
                    a.recycle();
                }
                values2 = values;
                parser.next();
            }
        }
        PropertyValuesHolder[] valuesArray = null;
        if (values != null) {
            int count = values.size();
            valuesArray = new PropertyValuesHolder[count];
            for (int i = 0; i < count; i++) {
                valuesArray[i] = values.get(i);
            }
        }
        return valuesArray;
    }

    private static int inferValueTypeOfKeyframe(Resources res, Resources.Theme theme, AttributeSet attrs, XmlPullParser parser) {
        TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_KEYFRAME);
        int valueType = 0;
        TypedValue keyframeValue = TypedArrayUtils.peekNamedValue(a, parser, FirebaseAnalytics.Param.VALUE, 0);
        boolean hasValue = keyframeValue != null;
        if (hasValue && isColorType(keyframeValue.type)) {
            valueType = 3;
        }
        a.recycle();
        return valueType;
    }

    private static int inferValueTypeFromValues(TypedArray styledAttributes, int valueFromId, int valueToId) {
        TypedValue tvFrom = styledAttributes.peekValue(valueFromId);
        boolean hasFrom = tvFrom != null;
        int fromType = hasFrom ? tvFrom.type : 0;
        TypedValue tvTo = styledAttributes.peekValue(valueToId);
        boolean hasTo = tvTo != null;
        int toType = hasTo ? tvTo.type : 0;
        return ((hasFrom && isColorType(fromType)) || (hasTo && isColorType(toType))) ? 3 : 0;
    }

    private static void dumpKeyframes(Object[] keyframes, String header) {
        if (keyframes == null || keyframes.length == 0) {
            return;
        }
        Log.d(TAG, header);
        int count = keyframes.length;
        for (int i = 0; i < count; i++) {
            Keyframe keyframe = (Keyframe) keyframes[i];
            StringBuilder sb = new StringBuilder();
            sb.append("Keyframe ");
            sb.append(i);
            sb.append(": fraction ");
            sb.append(keyframe.getFraction() < 0.0f ? "null" : Float.valueOf(keyframe.getFraction()));
            sb.append(", ");
            sb.append(", value : ");
            sb.append(keyframe.hasValue() ? keyframe.getValue() : "null");
            Log.d(TAG, sb.toString());
        }
    }

    private static PropertyValuesHolder loadPvh(Context context, Resources res, Resources.Theme theme, XmlPullParser parser, String propertyName, int valueType) throws XmlPullParserException, IOException {
        int type;
        PropertyValuesHolder value;
        ArrayList<Keyframe> keyframes;
        int type2;
        float f;
        Resources resources;
        Resources.Theme theme2;
        XmlPullParser xmlPullParser;
        PropertyValuesHolder value2 = null;
        ArrayList<Keyframe> keyframes2 = null;
        int valueType2 = valueType;
        while (true) {
            int next = parser.next();
            type = next;
            if (next == 3 || type == 1) {
                break;
            }
            String name = parser.getName();
            if (name.equals("keyframe")) {
                if (valueType2 == 4) {
                    resources = res;
                    theme2 = theme;
                    xmlPullParser = parser;
                    valueType2 = inferValueTypeOfKeyframe(resources, theme2, Xml.asAttributeSet(parser), xmlPullParser);
                } else {
                    resources = res;
                    theme2 = theme;
                    xmlPullParser = parser;
                }
                Keyframe keyframe = loadKeyframe(context, resources, theme2, Xml.asAttributeSet(parser), valueType2, xmlPullParser);
                if (keyframe != null) {
                    if (keyframes2 == null) {
                        keyframes2 = new ArrayList<>();
                    }
                    keyframes2.add(keyframe);
                }
                parser.next();
            }
        }
        if (keyframes2 != null) {
            int size = keyframes2.size();
            int count = size;
            if (size > 0) {
                int i = 0;
                Keyframe firstKeyframe = keyframes2.get(0);
                Keyframe lastKeyframe = keyframes2.get(count - 1);
                float endFraction = lastKeyframe.getFraction();
                float f2 = 0.0f;
                if (endFraction < 1.0f) {
                    if (endFraction < 0.0f) {
                        lastKeyframe.setFraction(1.0f);
                    } else {
                        keyframes2.add(keyframes2.size(), createNewKeyframe(lastKeyframe, 1.0f));
                        count++;
                    }
                }
                float startFraction = firstKeyframe.getFraction();
                if (startFraction != 0.0f) {
                    if (startFraction < 0.0f) {
                        firstKeyframe.setFraction(0.0f);
                    } else {
                        keyframes2.add(0, createNewKeyframe(firstKeyframe, 0.0f));
                        count++;
                    }
                }
                Keyframe[] keyframeArray = new Keyframe[count];
                keyframes2.toArray(keyframeArray);
                while (i < count) {
                    Keyframe keyframe2 = keyframeArray[i];
                    if (keyframe2.getFraction() >= f2) {
                        value = value2;
                        keyframes = keyframes2;
                        type2 = type;
                        f = f2;
                    } else if (i == 0) {
                        keyframe2.setFraction(f2);
                        value = value2;
                        keyframes = keyframes2;
                        type2 = type;
                        f = f2;
                    } else if (i == count - 1) {
                        keyframe2.setFraction(1.0f);
                        value = value2;
                        keyframes = keyframes2;
                        type2 = type;
                        f = 0.0f;
                    } else {
                        int startIndex = i;
                        int endIndex = i;
                        int j = startIndex + 1;
                        value = value2;
                        int endIndex2 = endIndex;
                        while (true) {
                            int j2 = j;
                            keyframes = keyframes2;
                            type2 = type;
                            if (j2 >= count - 1) {
                                f = 0.0f;
                                break;
                            }
                            f = 0.0f;
                            if (keyframeArray[j2].getFraction() >= 0.0f) {
                                break;
                            }
                            endIndex2 = j2;
                            j = j2 + 1;
                            keyframes2 = keyframes;
                            type = type2;
                        }
                        float gap = keyframeArray[endIndex2 + 1].getFraction() - keyframeArray[startIndex - 1].getFraction();
                        distributeKeyframes(keyframeArray, gap, startIndex, endIndex2);
                    }
                    i++;
                    f2 = f;
                    value2 = value;
                    keyframes2 = keyframes;
                    type = type2;
                }
                PropertyValuesHolder value3 = PropertyValuesHolder.ofKeyframe(propertyName, keyframeArray);
                if (valueType2 != 3) {
                    return value3;
                }
                value3.setEvaluator(ArgbEvaluator.getInstance());
                return value3;
            }
        }
        return null;
    }

    private static Keyframe createNewKeyframe(Keyframe sampleKeyframe, float fraction) {
        if (sampleKeyframe.getType() == Float.TYPE) {
            return Keyframe.ofFloat(fraction);
        }
        if (sampleKeyframe.getType() == Integer.TYPE) {
            return Keyframe.ofInt(fraction);
        }
        return Keyframe.ofObject(fraction);
    }

    private static void distributeKeyframes(Keyframe[] keyframes, float gap, int startIndex, int endIndex) {
        int count = (endIndex - startIndex) + 2;
        float increment = gap / count;
        for (int i = startIndex; i <= endIndex; i++) {
            keyframes[i].setFraction(keyframes[i - 1].getFraction() + increment);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.animation.Keyframe loadKeyframe(android.content.Context r9, android.content.res.Resources r10, android.content.res.Resources.Theme r11, android.util.AttributeSet r12, int r13, org.xmlpull.v1.XmlPullParser r14) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            int[] r0 = android.support.graphics.drawable.AndroidResources.STYLEABLE_KEYFRAME
            android.content.res.TypedArray r0 = android.support.v4.content.res.TypedArrayUtils.obtainAttributes(r10, r11, r12, r0)
            r1 = 0
            java.lang.String r2 = "fraction"
            r3 = 3
            r4 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r2 = android.support.v4.content.res.TypedArrayUtils.getNamedFloat(r0, r14, r2, r3, r4)
            java.lang.String r4 = "value"
            r5 = 0
            android.util.TypedValue r4 = android.support.v4.content.res.TypedArrayUtils.peekNamedValue(r0, r14, r4, r5)
            r6 = 1
            if (r4 == 0) goto L1c
            r7 = r6
            goto L1d
        L1c:
            r7 = r5
        L1d:
            r8 = 4
            if (r13 != r8) goto L2d
            if (r7 == 0) goto L2c
            int r8 = r4.type
            boolean r8 = isColorType(r8)
            if (r8 == 0) goto L2c
            r13 = 3
            goto L2d
        L2c:
            r13 = 0
        L2d:
            if (r7 == 0) goto L4c
            if (r13 == r3) goto L41
            switch(r13) {
                case 0: goto L35;
                case 1: goto L41;
                default: goto L34;
            }
        L34:
            goto L4b
        L35:
            java.lang.String r3 = "value"
            r8 = 0
            float r3 = android.support.v4.content.res.TypedArrayUtils.getNamedFloat(r0, r14, r3, r5, r8)
            android.animation.Keyframe r1 = android.animation.Keyframe.ofFloat(r2, r3)
            goto L4b
        L41:
            java.lang.String r3 = "value"
            int r3 = android.support.v4.content.res.TypedArrayUtils.getNamedInt(r0, r14, r3, r5, r5)
            android.animation.Keyframe r1 = android.animation.Keyframe.ofInt(r2, r3)
        L4b:
            goto L58
        L4c:
            if (r13 != 0) goto L53
            android.animation.Keyframe r3 = android.animation.Keyframe.ofFloat(r2)
            goto L57
        L53:
            android.animation.Keyframe r3 = android.animation.Keyframe.ofInt(r2)
        L57:
            r1 = r3
        L58:
            java.lang.String r3 = "interpolator"
            int r3 = android.support.v4.content.res.TypedArrayUtils.getNamedResourceId(r0, r14, r3, r6, r5)
            if (r3 <= 0) goto L67
            android.view.animation.Interpolator r5 = android.support.graphics.drawable.AnimationUtilsCompat.loadInterpolator(r9, r3)
            r1.setInterpolator(r5)
        L67:
            r0.recycle()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.graphics.drawable.AnimatorInflaterCompat.loadKeyframe(android.content.Context, android.content.res.Resources, android.content.res.Resources$Theme, android.util.AttributeSet, int, org.xmlpull.v1.XmlPullParser):android.animation.Keyframe");
    }

    private static ObjectAnimator loadObjectAnimator(Context context, Resources res, Resources.Theme theme, AttributeSet attrs, float pathErrorScale, XmlPullParser parser) throws Resources.NotFoundException {
        ObjectAnimator anim = new ObjectAnimator();
        loadAnimator(context, res, theme, attrs, anim, pathErrorScale, parser);
        return anim;
    }

    private static ValueAnimator loadAnimator(Context context, Resources res, Resources.Theme theme, AttributeSet attrs, ValueAnimator anim, float pathErrorScale, XmlPullParser parser) throws Resources.NotFoundException {
        TypedArray arrayAnimator = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_ANIMATOR);
        TypedArray arrayObjectAnimator = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
        if (anim == null) {
            anim = new ValueAnimator();
        }
        parseAnimatorFromTypeArray(anim, arrayAnimator, arrayObjectAnimator, pathErrorScale, parser);
        int resID = TypedArrayUtils.getNamedResourceId(arrayAnimator, parser, "interpolator", 0, 0);
        if (resID > 0) {
            Interpolator interpolator = AnimationUtilsCompat.loadInterpolator(context, resID);
            anim.setInterpolator(interpolator);
        }
        arrayAnimator.recycle();
        if (arrayObjectAnimator != null) {
            arrayObjectAnimator.recycle();
        }
        return anim;
    }

    private static boolean isColorType(int type) {
        return type >= 28 && type <= 31;
    }
}
