package android.support.constraint;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.common.util.CrashUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    static final boolean ALLOWS_EMBEDDED = false;
    private static final boolean DEBUG = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-1.1.0";
    SparseArray<View> mChildrenByIds;
    private ArrayList<ConstraintHelper> mConstraintHelpers;
    private ConstraintSet mConstraintSet;
    private int mConstraintSetId;
    private HashMap<String, Integer> mDesignIds;
    private boolean mDirtyHierarchy;
    private int mLastMeasureHeight;
    int mLastMeasureHeightMode;
    int mLastMeasureHeightSize;
    private int mLastMeasureWidth;
    int mLastMeasureWidthMode;
    int mLastMeasureWidthSize;
    ConstraintWidgetContainer mLayoutWidget;
    private int mMaxHeight;
    private int mMaxWidth;
    private Metrics mMetrics;
    private int mMinHeight;
    private int mMinWidth;
    private int mOptimizationLevel;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets;

    public void setDesignInformation(int type, Object value1, Object value2) {
        if (type == 0 && (value1 instanceof String) && (value2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap<>();
            }
            String name = (String) value1;
            int index = name.indexOf("/");
            if (index != -1) {
                name = name.substring(index + 1);
            }
            int id = ((Integer) value2).intValue();
            this.mDesignIds.put(name, Integer.valueOf(id));
        }
    }

    public Object getDesignInformation(int type, Object value) {
        if (type == 0 && (value instanceof String)) {
            String name = (String) value;
            if (this.mDesignIds != null && this.mDesignIds.containsKey(name)) {
                return this.mDesignIds.get(name);
            }
            return null;
        }
        return null;
    }

    public ConstraintLayout(Context context) throws XmlPullParserException, IOException {
        super(context);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mVariableDimensionsWidgets = new ArrayList<>(100);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 3;
        this.mConstraintSet = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        init(null);
    }

    public ConstraintLayout(Context context, AttributeSet attrs) throws XmlPullParserException, IOException {
        super(context, attrs);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mVariableDimensionsWidgets = new ArrayList<>(100);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 3;
        this.mConstraintSet = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        init(attrs);
    }

    public ConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) throws XmlPullParserException, IOException {
        super(context, attrs, defStyleAttr);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mVariableDimensionsWidgets = new ArrayList<>(100);
        this.mLayoutWidget = new ConstraintWidgetContainer();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 3;
        this.mConstraintSet = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        init(attrs);
    }

    @Override // android.view.View
    public void setId(int id) {
        this.mChildrenByIds.remove(getId());
        super.setId(id);
        this.mChildrenByIds.put(getId(), this);
    }

    private void init(AttributeSet attrs) throws XmlPullParserException, IOException {
        this.mLayoutWidget.setCompanionWidget(this);
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ConstraintLayout_Layout);
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.mMinWidth = a.getDimensionPixelOffset(attr, this.mMinWidth);
                } else if (attr == R.styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.mMinHeight = a.getDimensionPixelOffset(attr, this.mMinHeight);
                } else if (attr == R.styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.mMaxWidth = a.getDimensionPixelOffset(attr, this.mMaxWidth);
                } else if (attr == R.styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.mMaxHeight = a.getDimensionPixelOffset(attr, this.mMaxHeight);
                } else if (attr == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.mOptimizationLevel = a.getInt(attr, this.mOptimizationLevel);
                } else if (attr == R.styleable.ConstraintLayout_Layout_constraintSet) {
                    int id = a.getResourceId(attr, 0);
                    try {
                        this.mConstraintSet = new ConstraintSet();
                        this.mConstraintSet.load(getContext(), id);
                    } catch (Resources.NotFoundException e) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = id;
                }
            }
            a.recycle();
        }
        this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (Build.VERSION.SDK_INT < 14) {
            onViewAdded(child);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        super.removeView(view);
        if (Build.VERSION.SDK_INT < 14) {
            onViewRemoved(view);
        }
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewAdded(view);
        }
        ConstraintWidget widget = getViewWidget(view);
        if ((view instanceof Guideline) && !(widget instanceof android.support.constraint.solver.widgets.Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.widget = new android.support.constraint.solver.widgets.Guideline();
            layoutParams.isGuideline = true;
            ((android.support.constraint.solver.widgets.Guideline) layoutParams.widget).setOrientation(layoutParams.orientation);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper helper = (ConstraintHelper) view;
            helper.validateParams();
            ((LayoutParams) view.getLayoutParams()).isHelper = true;
            if (!this.mConstraintHelpers.contains(helper)) {
                this.mConstraintHelpers.add(helper);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.mChildrenByIds.remove(view.getId());
        ConstraintWidget widget = getViewWidget(view);
        this.mLayoutWidget.remove(widget);
        this.mConstraintHelpers.remove(view);
        this.mVariableDimensionsWidgets.remove(widget);
        this.mDirtyHierarchy = true;
    }

    public void setMinWidth(int value) {
        if (value == this.mMinWidth) {
            return;
        }
        this.mMinWidth = value;
        requestLayout();
    }

    public void setMinHeight(int value) {
        if (value == this.mMinHeight) {
            return;
        }
        this.mMinHeight = value;
        requestLayout();
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public void setMaxWidth(int value) {
        if (value == this.mMaxWidth) {
            return;
        }
        this.mMaxWidth = value;
        requestLayout();
    }

    public void setMaxHeight(int value) {
        if (value == this.mMaxHeight) {
            return;
        }
        this.mMaxHeight = value;
        requestLayout();
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    private void updateHierarchy() throws IllegalAccessException {
        int count = getChildCount();
        boolean recompute = false;
        int i = 0;
        while (true) {
            if (i >= count) {
                break;
            }
            View child = getChildAt(i);
            if (!child.isLayoutRequested()) {
                i++;
            } else {
                recompute = true;
                break;
            }
        }
        if (recompute) {
            this.mVariableDimensionsWidgets.clear();
            setChildrenConstraints();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0202 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0214  */
    /* JADX WARN: Type inference failed for: r33v0, types: [android.support.constraint.ConstraintLayout] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v36 */
    /* JADX WARN: Type inference failed for: r9v46 */
    /* JADX WARN: Type inference failed for: r9v47 */
    /* JADX WARN: Type inference failed for: r9v50 */
    /* JADX WARN: Type inference failed for: r9v56 */
    /* JADX WARN: Type inference failed for: r9v70 */
    /* JADX WARN: Type inference failed for: r9v75 */
    /* JADX WARN: Type inference failed for: r9v76 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setChildrenConstraints() throws java.lang.IllegalAccessException {
        /*
            Method dump skipped, instruction units count: 1145
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.setChildrenConstraints():void");
    }

    private final ConstraintWidget getTargetWidget(int id) {
        if (id == 0) {
            return this.mLayoutWidget;
        }
        View view = this.mChildrenByIds.get(id);
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).widget;
    }

    public final ConstraintWidget getViewWidget(View view) {
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).widget;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void internalMeasureChildren(int r23, int r24) {
        /*
            Method dump skipped, instruction units count: 275
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.internalMeasureChildren(int, int):void");
    }

    private void updatePostMeasures() {
        int widgetsCount = getChildCount();
        for (int i = 0; i < widgetsCount; i++) {
            View child = getChildAt(i);
            if (child instanceof Placeholder) {
                ((Placeholder) child).updatePostMeasure(this);
            }
        }
        int helperCount = this.mConstraintHelpers.size();
        if (helperCount > 0) {
            for (int i2 = 0; i2 < helperCount; i2++) {
                ConstraintHelper helper = this.mConstraintHelpers.get(i2);
                helper.updatePostMeasure(this);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void internalMeasureDimensions(int r34, int r35) {
        /*
            Method dump skipped, instruction units count: 841
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.internalMeasureDimensions(int, int):void");
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.mLayoutWidget.fillMetrics(metrics);
    }

    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) throws IllegalAccessException {
        int childState;
        int startingHeight;
        int startingWidth;
        int startingWidth2;
        boolean containerWrapWidth;
        int startingHeight2;
        boolean optimiseDimensions;
        int sizeDependentWidgetsCount;
        boolean containerWrapHeight;
        int startingHeight3;
        int startingWidth3;
        int i;
        int minHeight;
        int childState2;
        int baseline;
        int i2 = widthMeasureSpec;
        int i3 = heightMeasureSpec;
        System.currentTimeMillis();
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        boolean validLastMeasure = (this.mLastMeasureWidth == -1 || this.mLastMeasureHeight == -1) ? false : true;
        boolean sameSize = widthMode == 1073741824 && heightMode == 1073741824 && widthSize == this.mLastMeasureWidth && heightSize == this.mLastMeasureHeight;
        boolean sameMode = widthMode == this.mLastMeasureWidthMode && heightMode == this.mLastMeasureHeightMode;
        boolean sameMeasure = sameMode && widthSize == this.mLastMeasureWidthSize && heightSize == this.mLastMeasureHeightSize;
        boolean fitSizeWidth = sameMode && widthMode == Integer.MIN_VALUE && heightMode == 1073741824 && widthSize >= this.mLastMeasureWidth && heightSize == this.mLastMeasureHeight;
        boolean fitSizeHeight = sameMode && widthMode == 1073741824 && heightMode == Integer.MIN_VALUE && widthSize == this.mLastMeasureWidth && heightSize >= this.mLastMeasureHeight;
        this.mLastMeasureWidthMode = widthMode;
        this.mLastMeasureHeightMode = heightMode;
        this.mLastMeasureWidthSize = widthSize;
        this.mLastMeasureHeightSize = heightSize;
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int REMEASURES_A = 0;
        this.mLayoutWidget.setX(paddingLeft);
        this.mLayoutWidget.setY(paddingTop);
        ConstraintWidgetContainer constraintWidgetContainer = this.mLayoutWidget;
        int REMEASURES_B = this.mMaxWidth;
        constraintWidgetContainer.setMaxWidth(REMEASURES_B);
        this.mLayoutWidget.setMaxHeight(this.mMaxHeight);
        if (Build.VERSION.SDK_INT >= 17) {
            this.mLayoutWidget.setRtl(getLayoutDirection() == 1);
        }
        setSelfDimensionBehaviour(widthMeasureSpec, heightMeasureSpec);
        int startingWidth4 = this.mLayoutWidget.getWidth();
        int startingHeight4 = this.mLayoutWidget.getHeight();
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            updateHierarchy();
        }
        boolean optimiseDimensions2 = (this.mOptimizationLevel & 8) == 8;
        if (optimiseDimensions2) {
            this.mLayoutWidget.preOptimize();
            this.mLayoutWidget.optimizeForDimensions(startingWidth4, startingHeight4);
            internalMeasureDimensions(widthMeasureSpec, heightMeasureSpec);
        } else {
            internalMeasureChildren(widthMeasureSpec, heightMeasureSpec);
        }
        updatePostMeasures();
        if (getChildCount() > 0) {
            solveLinearSystem("First pass");
        }
        int sizeDependentWidgetsCount2 = this.mVariableDimensionsWidgets.size();
        int heightMode2 = paddingTop + getPaddingBottom();
        int paddingTop2 = paddingLeft + getPaddingRight();
        if (sizeDependentWidgetsCount2 > 0) {
            boolean needSolverPass = false;
            boolean containerWrapWidth2 = this.mLayoutWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            boolean containerWrapHeight2 = this.mLayoutWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            int minWidth = Math.max(this.mLayoutWidget.getWidth(), this.mMinWidth);
            int height = this.mLayoutWidget.getHeight();
            int minWidth2 = this.mMinHeight;
            int minHeight2 = Math.max(height, minWidth2);
            childState = 0;
            int minWidth3 = minWidth;
            int minHeight3 = minHeight2;
            int i4 = 0;
            while (i4 < sizeDependentWidgetsCount2) {
                boolean fitSizeWidth2 = fitSizeWidth;
                ConstraintWidget widget = this.mVariableDimensionsWidgets.get(i4);
                int sizeDependentWidgetsCount3 = sizeDependentWidgetsCount2;
                View child = (View) widget.getCompanionWidget();
                if (child == null) {
                    startingWidth3 = startingWidth4;
                    startingHeight3 = startingHeight4;
                } else {
                    startingHeight3 = startingHeight4;
                    LayoutParams params = (LayoutParams) child.getLayoutParams();
                    startingWidth3 = startingWidth4;
                    if (params.isHelper) {
                        i = i4;
                    } else if (!params.isGuideline) {
                        i = i4;
                        if (child.getVisibility() != 8 && (!optimiseDimensions2 || !widget.getResolutionWidth().isResolved() || !widget.getResolutionHeight().isResolved())) {
                            int widthSpec = (params.width == -2 && params.horizontalDimensionFixed) ? getChildMeasureSpec(i2, paddingTop2, params.width) : View.MeasureSpec.makeMeasureSpec(widget.getWidth(), CrashUtils.ErrorDialogData.SUPPRESSED);
                            int heightSpec = (params.height == -2 && params.verticalDimensionFixed) ? getChildMeasureSpec(i3, heightMode2, params.height) : View.MeasureSpec.makeMeasureSpec(widget.getHeight(), CrashUtils.ErrorDialogData.SUPPRESSED);
                            child.measure(widthSpec, heightSpec);
                            if (this.mMetrics != null) {
                                minHeight = minHeight3;
                                childState2 = childState;
                                this.mMetrics.additionalMeasures++;
                            } else {
                                minHeight = minHeight3;
                                childState2 = childState;
                            }
                            REMEASURES_A++;
                            int measuredWidth = child.getMeasuredWidth();
                            int measuredHeight = child.getMeasuredHeight();
                            if (measuredWidth != widget.getWidth()) {
                                widget.setWidth(measuredWidth);
                                if (optimiseDimensions2) {
                                    widget.getResolutionWidth().resolve(measuredWidth);
                                }
                                if (containerWrapWidth2 && widget.getRight() > minWidth3) {
                                    int w = widget.getRight() + widget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin();
                                    minWidth3 = Math.max(minWidth3, w);
                                }
                                needSolverPass = true;
                            }
                            if (measuredHeight != widget.getHeight()) {
                                widget.setHeight(measuredHeight);
                                if (optimiseDimensions2) {
                                    widget.getResolutionHeight().resolve(measuredHeight);
                                }
                                if (containerWrapHeight2) {
                                    minHeight3 = minHeight;
                                    if (widget.getBottom() > minHeight3) {
                                        int h = widget.getBottom() + widget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin();
                                        minHeight3 = Math.max(minHeight3, h);
                                    }
                                } else {
                                    minHeight3 = minHeight;
                                }
                                needSolverPass = true;
                            } else {
                                minHeight3 = minHeight;
                            }
                            if (params.needsBaseline && (baseline = child.getBaseline()) != -1 && baseline != widget.getBaselineDistance()) {
                                widget.setBaselineDistance(baseline);
                                needSolverPass = true;
                            }
                            childState = Build.VERSION.SDK_INT >= 11 ? combineMeasuredStates(childState2, child.getMeasuredState()) : childState2;
                        }
                    }
                    i4 = i + 1;
                    fitSizeWidth = fitSizeWidth2;
                    sizeDependentWidgetsCount2 = sizeDependentWidgetsCount3;
                    startingHeight4 = startingHeight3;
                    startingWidth4 = startingWidth3;
                    i2 = widthMeasureSpec;
                    i3 = heightMeasureSpec;
                }
                i = i4;
                i4 = i + 1;
                fitSizeWidth = fitSizeWidth2;
                sizeDependentWidgetsCount2 = sizeDependentWidgetsCount3;
                startingHeight4 = startingHeight3;
                startingWidth4 = startingWidth3;
                i2 = widthMeasureSpec;
                i3 = heightMeasureSpec;
            }
            int startingWidth5 = startingWidth4;
            int startingHeight5 = startingHeight4;
            int sizeDependentWidgetsCount4 = sizeDependentWidgetsCount2;
            if (needSolverPass) {
                startingWidth = startingWidth5;
                this.mLayoutWidget.setWidth(startingWidth);
                startingHeight = startingHeight5;
                this.mLayoutWidget.setHeight(startingHeight);
                if (optimiseDimensions2) {
                    this.mLayoutWidget.solveGraph();
                }
                solveLinearSystem("2nd pass");
                boolean needSolverPass2 = false;
                if (this.mLayoutWidget.getWidth() < minWidth3) {
                    this.mLayoutWidget.setWidth(minWidth3);
                    needSolverPass2 = true;
                }
                if (this.mLayoutWidget.getHeight() < minHeight3) {
                    this.mLayoutWidget.setHeight(minHeight3);
                    needSolverPass2 = true;
                }
                if (needSolverPass2) {
                    solveLinearSystem("3rd pass");
                }
            } else {
                startingHeight = startingHeight5;
                startingWidth = startingWidth5;
            }
            int REMEASURES_B2 = 0;
            int i5 = 0;
            while (true) {
                int i6 = i5;
                int sizeDependentWidgetsCount5 = sizeDependentWidgetsCount4;
                if (i6 >= sizeDependentWidgetsCount5) {
                    break;
                }
                ConstraintWidget widget2 = this.mVariableDimensionsWidgets.get(i6);
                View child2 = (View) widget2.getCompanionWidget();
                if (child2 == null) {
                    startingWidth2 = startingWidth;
                    containerWrapWidth = containerWrapWidth2;
                } else {
                    startingWidth2 = startingWidth;
                    containerWrapWidth = containerWrapWidth2;
                    if (child2.getMeasuredWidth() != widget2.getWidth() || child2.getMeasuredHeight() != widget2.getHeight()) {
                        int widthSpec2 = View.MeasureSpec.makeMeasureSpec(widget2.getWidth(), CrashUtils.ErrorDialogData.SUPPRESSED);
                        startingHeight2 = startingHeight;
                        int heightSpec2 = View.MeasureSpec.makeMeasureSpec(widget2.getHeight(), CrashUtils.ErrorDialogData.SUPPRESSED);
                        child2.measure(widthSpec2, heightSpec2);
                        if (this.mMetrics != null) {
                            optimiseDimensions = optimiseDimensions2;
                            sizeDependentWidgetsCount = sizeDependentWidgetsCount5;
                            containerWrapHeight = containerWrapHeight2;
                            this.mMetrics.additionalMeasures++;
                        } else {
                            optimiseDimensions = optimiseDimensions2;
                            sizeDependentWidgetsCount = sizeDependentWidgetsCount5;
                            containerWrapHeight = containerWrapHeight2;
                        }
                        REMEASURES_B2++;
                    }
                    i5 = i6 + 1;
                    startingWidth = startingWidth2;
                    containerWrapWidth2 = containerWrapWidth;
                    startingHeight = startingHeight2;
                    optimiseDimensions2 = optimiseDimensions;
                    sizeDependentWidgetsCount4 = sizeDependentWidgetsCount;
                    containerWrapHeight2 = containerWrapHeight;
                }
                startingHeight2 = startingHeight;
                optimiseDimensions = optimiseDimensions2;
                sizeDependentWidgetsCount = sizeDependentWidgetsCount5;
                containerWrapHeight = containerWrapHeight2;
                i5 = i6 + 1;
                startingWidth = startingWidth2;
                containerWrapWidth2 = containerWrapWidth;
                startingHeight = startingHeight2;
                optimiseDimensions2 = optimiseDimensions;
                sizeDependentWidgetsCount4 = sizeDependentWidgetsCount;
                containerWrapHeight2 = containerWrapHeight;
            }
        } else {
            childState = 0;
        }
        int androidLayoutWidth = this.mLayoutWidget.getWidth() + paddingTop2;
        int androidLayoutHeight = this.mLayoutWidget.getHeight() + heightMode2;
        if (Build.VERSION.SDK_INT < 11) {
            setMeasuredDimension(androidLayoutWidth, androidLayoutHeight);
            this.mLastMeasureWidth = androidLayoutWidth;
            this.mLastMeasureHeight = androidLayoutHeight;
            return;
        }
        int resolvedWidthSize = resolveSizeAndState(androidLayoutWidth, widthMeasureSpec, childState);
        int resolvedHeightSize = resolveSizeAndState(androidLayoutHeight, heightMeasureSpec, childState << 16);
        int resolvedWidthSize2 = resolvedWidthSize & ViewCompat.MEASURED_SIZE_MASK;
        int resolvedHeightSize2 = resolvedHeightSize & ViewCompat.MEASURED_SIZE_MASK;
        int resolvedWidthSize3 = Math.min(this.mMaxWidth, resolvedWidthSize2);
        int resolvedHeightSize3 = Math.min(this.mMaxHeight, resolvedHeightSize2);
        if (this.mLayoutWidget.isWidthMeasuredTooSmall()) {
            resolvedWidthSize3 |= 16777216;
        }
        if (this.mLayoutWidget.isHeightMeasuredTooSmall()) {
            resolvedHeightSize3 |= 16777216;
        }
        setMeasuredDimension(resolvedWidthSize3, resolvedHeightSize3);
        this.mLastMeasureWidth = resolvedWidthSize3;
        this.mLastMeasureHeight = resolvedHeightSize3;
    }

    private void setSelfDimensionBehaviour(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int heightPadding = getPaddingTop() + getPaddingBottom();
        int widthPadding = getPaddingLeft() + getPaddingRight();
        ConstraintWidget.DimensionBehaviour widthBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        ConstraintWidget.DimensionBehaviour heightBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        int desiredWidth = 0;
        int desiredHeight = 0;
        getLayoutParams();
        if (widthMode == Integer.MIN_VALUE) {
            widthBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            desiredWidth = widthSize;
        } else if (widthMode != 0) {
            if (widthMode == 1073741824) {
                desiredWidth = Math.min(this.mMaxWidth, widthSize) - widthPadding;
            }
        } else {
            widthBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        if (heightMode == Integer.MIN_VALUE) {
            heightBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            desiredHeight = heightSize;
        } else if (heightMode != 0) {
            if (heightMode == 1073741824) {
                desiredHeight = Math.min(this.mMaxHeight, heightSize) - heightPadding;
            }
        } else {
            heightBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        }
        this.mLayoutWidget.setMinWidth(0);
        this.mLayoutWidget.setMinHeight(0);
        this.mLayoutWidget.setHorizontalDimensionBehaviour(widthBehaviour);
        this.mLayoutWidget.setWidth(desiredWidth);
        this.mLayoutWidget.setVerticalDimensionBehaviour(heightBehaviour);
        this.mLayoutWidget.setHeight(desiredHeight);
        this.mLayoutWidget.setMinWidth((this.mMinWidth - getPaddingLeft()) - getPaddingRight());
        this.mLayoutWidget.setMinHeight((this.mMinHeight - getPaddingTop()) - getPaddingBottom());
    }

    protected void solveLinearSystem(String reason) {
        this.mLayoutWidget.layout();
        if (this.mMetrics != null) {
            this.mMetrics.resolutions++;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int widgetsCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i = 0; i < widgetsCount; i++) {
            View child = getChildAt(i);
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            ConstraintWidget widget = params.widget;
            if ((child.getVisibility() != 8 || params.isGuideline || params.isHelper || isInEditMode) && !params.isInPlaceholder) {
                int l = widget.getDrawX();
                int t = widget.getDrawY();
                int r = widget.getWidth() + l;
                int b = widget.getHeight() + t;
                child.layout(l, t, r, b);
                if (child instanceof Placeholder) {
                    Placeholder holder = (Placeholder) child;
                    View content = holder.getContent();
                    if (content != null) {
                        content.setVisibility(0);
                        content.layout(l, t, r, b);
                    }
                }
            }
        }
        int helperCount = this.mConstraintHelpers.size();
        if (helperCount > 0) {
            for (int i2 = 0; i2 < helperCount; i2++) {
                ConstraintHelper helper = this.mConstraintHelpers.get(i2);
                helper.updatePostLayout(this);
            }
        }
    }

    public void setOptimizationLevel(int level) {
        this.mLayoutWidget.setOptimizationLevel(level);
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.getOptimizationLevel();
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public void setConstraintSet(ConstraintSet set) {
        this.mConstraintSet = set;
    }

    public View getViewById(int id) {
        return this.mChildrenByIds.get(id);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0109  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void dispatchDraw(android.graphics.Canvas r27) {
        /*
            Method dump skipped, instruction units count: 289
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.dispatchDraw(android.graphics.Canvas):void");
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int BASELINE = 5;
        public static final int BOTTOM = 4;
        public static final int CHAIN_PACKED = 2;
        public static final int CHAIN_SPREAD = 0;
        public static final int CHAIN_SPREAD_INSIDE = 1;
        public static final int END = 7;
        public static final int HORIZONTAL = 0;
        public static final int LEFT = 1;
        public static final int MATCH_CONSTRAINT = 0;
        public static final int MATCH_CONSTRAINT_PERCENT = 2;
        public static final int MATCH_CONSTRAINT_SPREAD = 0;
        public static final int MATCH_CONSTRAINT_WRAP = 1;
        public static final int PARENT_ID = 0;
        public static final int RIGHT = 2;
        public static final int START = 6;
        public static final int TOP = 3;
        public static final int UNSET = -1;
        public static final int VERTICAL = 1;
        public int baselineToBaseline;
        public int bottomToBottom;
        public int bottomToTop;
        public float circleAngle;
        public int circleConstraint;
        public int circleRadius;
        public boolean constrainedHeight;
        public boolean constrainedWidth;
        public String dimensionRatio;
        int dimensionRatioSide;
        float dimensionRatioValue;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public int endToEnd;
        public int endToStart;
        public int goneBottomMargin;
        public int goneEndMargin;
        public int goneLeftMargin;
        public int goneRightMargin;
        public int goneStartMargin;
        public int goneTopMargin;
        public int guideBegin;
        public int guideEnd;
        public float guidePercent;
        public boolean helped;
        public float horizontalBias;
        public int horizontalChainStyle;
        boolean horizontalDimensionFixed;
        public float horizontalWeight;
        boolean isGuideline;
        boolean isHelper;
        boolean isInPlaceholder;
        public int leftToLeft;
        public int leftToRight;
        public int matchConstraintDefaultHeight;
        public int matchConstraintDefaultWidth;
        public int matchConstraintMaxHeight;
        public int matchConstraintMaxWidth;
        public int matchConstraintMinHeight;
        public int matchConstraintMinWidth;
        public float matchConstraintPercentHeight;
        public float matchConstraintPercentWidth;
        boolean needsBaseline;
        public int orientation;
        int resolveGoneLeftMargin;
        int resolveGoneRightMargin;
        int resolvedGuideBegin;
        int resolvedGuideEnd;
        float resolvedGuidePercent;
        float resolvedHorizontalBias;
        int resolvedLeftToLeft;
        int resolvedLeftToRight;
        int resolvedRightToLeft;
        int resolvedRightToRight;
        public int rightToLeft;
        public int rightToRight;
        public int startToEnd;
        public int startToStart;
        public int topToBottom;
        public int topToTop;
        public float verticalBias;
        public int verticalChainStyle;
        boolean verticalDimensionFixed;
        public float verticalWeight;
        ConstraintWidget widget;

        public void reset() {
            if (this.widget != null) {
                this.widget.reset();
            }
        }

        public LayoutParams(LayoutParams source) {
            super((ViewGroup.MarginLayoutParams) source);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = 0.0f;
            this.verticalWeight = 0.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
            this.guideBegin = source.guideBegin;
            this.guideEnd = source.guideEnd;
            this.guidePercent = source.guidePercent;
            this.leftToLeft = source.leftToLeft;
            this.leftToRight = source.leftToRight;
            this.rightToLeft = source.rightToLeft;
            this.rightToRight = source.rightToRight;
            this.topToTop = source.topToTop;
            this.topToBottom = source.topToBottom;
            this.bottomToTop = source.bottomToTop;
            this.bottomToBottom = source.bottomToBottom;
            this.baselineToBaseline = source.baselineToBaseline;
            this.circleConstraint = source.circleConstraint;
            this.circleRadius = source.circleRadius;
            this.circleAngle = source.circleAngle;
            this.startToEnd = source.startToEnd;
            this.startToStart = source.startToStart;
            this.endToStart = source.endToStart;
            this.endToEnd = source.endToEnd;
            this.goneLeftMargin = source.goneLeftMargin;
            this.goneTopMargin = source.goneTopMargin;
            this.goneRightMargin = source.goneRightMargin;
            this.goneBottomMargin = source.goneBottomMargin;
            this.goneStartMargin = source.goneStartMargin;
            this.goneEndMargin = source.goneEndMargin;
            this.horizontalBias = source.horizontalBias;
            this.verticalBias = source.verticalBias;
            this.dimensionRatio = source.dimensionRatio;
            this.dimensionRatioValue = source.dimensionRatioValue;
            this.dimensionRatioSide = source.dimensionRatioSide;
            this.horizontalWeight = source.horizontalWeight;
            this.verticalWeight = source.verticalWeight;
            this.horizontalChainStyle = source.horizontalChainStyle;
            this.verticalChainStyle = source.verticalChainStyle;
            this.constrainedWidth = source.constrainedWidth;
            this.constrainedHeight = source.constrainedHeight;
            this.matchConstraintDefaultWidth = source.matchConstraintDefaultWidth;
            this.matchConstraintDefaultHeight = source.matchConstraintDefaultHeight;
            this.matchConstraintMinWidth = source.matchConstraintMinWidth;
            this.matchConstraintMaxWidth = source.matchConstraintMaxWidth;
            this.matchConstraintMinHeight = source.matchConstraintMinHeight;
            this.matchConstraintMaxHeight = source.matchConstraintMaxHeight;
            this.matchConstraintPercentWidth = source.matchConstraintPercentWidth;
            this.matchConstraintPercentHeight = source.matchConstraintPercentHeight;
            this.editorAbsoluteX = source.editorAbsoluteX;
            this.editorAbsoluteY = source.editorAbsoluteY;
            this.orientation = source.orientation;
            this.horizontalDimensionFixed = source.horizontalDimensionFixed;
            this.verticalDimensionFixed = source.verticalDimensionFixed;
            this.needsBaseline = source.needsBaseline;
            this.isGuideline = source.isGuideline;
            this.resolvedLeftToLeft = source.resolvedLeftToLeft;
            this.resolvedLeftToRight = source.resolvedLeftToRight;
            this.resolvedRightToLeft = source.resolvedRightToLeft;
            this.resolvedRightToRight = source.resolvedRightToRight;
            this.resolveGoneLeftMargin = source.resolveGoneLeftMargin;
            this.resolveGoneRightMargin = source.resolveGoneRightMargin;
            this.resolvedHorizontalBias = source.resolvedHorizontalBias;
            this.widget = source.widget;
        }

        private static class Table {
            public static final int ANDROID_ORIENTATION = 1;
            public static final int LAYOUT_CONSTRAINED_HEIGHT = 28;
            public static final int LAYOUT_CONSTRAINED_WIDTH = 27;
            public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR = 43;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF = 16;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR = 42;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF = 15;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF = 14;
            public static final int LAYOUT_CONSTRAINT_CIRCLE = 2;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE = 4;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS = 3;
            public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO = 44;
            public static final int LAYOUT_CONSTRAINT_END_TO_END_OF = 20;
            public static final int LAYOUT_CONSTRAINT_END_TO_START_OF = 19;
            public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN = 5;
            public static final int LAYOUT_CONSTRAINT_GUIDE_END = 6;
            public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT = 7;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT = 32;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX = 37;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN = 36;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT = 38;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS = 29;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE = 47;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT = 45;
            public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR = 39;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF = 8;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF = 9;
            public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR = 41;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF = 10;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF = 11;
            public static final int LAYOUT_CONSTRAINT_START_TO_END_OF = 17;
            public static final int LAYOUT_CONSTRAINT_START_TO_START_OF = 18;
            public static final int LAYOUT_CONSTRAINT_TOP_CREATOR = 40;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF = 13;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF = 12;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS = 30;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE = 48;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT = 46;
            public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT = 31;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MAX = 34;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MIN = 33;
            public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT = 35;
            public static final int LAYOUT_EDITOR_ABSOLUTEX = 49;
            public static final int LAYOUT_EDITOR_ABSOLUTEY = 50;
            public static final int LAYOUT_GONE_MARGIN_BOTTOM = 24;
            public static final int LAYOUT_GONE_MARGIN_END = 26;
            public static final int LAYOUT_GONE_MARGIN_LEFT = 21;
            public static final int LAYOUT_GONE_MARGIN_RIGHT = 23;
            public static final int LAYOUT_GONE_MARGIN_START = 25;
            public static final int LAYOUT_GONE_MARGIN_TOP = 22;
            public static final int UNUSED = 0;
            public static final SparseIntArray map = new SparseIntArray();

            private Table() {
            }

            static {
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                map.append(R.styleable.ConstraintLayout_Layout_android_orientation, 1);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public LayoutParams(Context c, AttributeSet attrs) {
            int i;
            int i2;
            float f;
            int i3;
            int commaIndex;
            super(c, attrs);
            int i4 = -1;
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            int i5 = 0;
            this.circleRadius = 0;
            float f2 = 0.0f;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            int i6 = 1;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = 0.0f;
            this.verticalWeight = 0.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.ConstraintLayout_Layout);
            int N = a.getIndexCount();
            int i7 = 0;
            while (i7 < N) {
                int attr = a.getIndex(i7);
                int look = Table.map.get(attr);
                switch (look) {
                    case 0:
                        f = f2;
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        break;
                    case 1:
                        f = f2;
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        this.orientation = a.getInt(attr, this.orientation);
                        break;
                    case 2:
                        i = i5;
                        f = f2;
                        i2 = i6;
                        this.circleConstraint = a.getResourceId(attr, this.circleConstraint);
                        i3 = -1;
                        if (this.circleConstraint == -1) {
                            this.circleConstraint = a.getInt(attr, -1);
                        }
                        break;
                    case 3:
                        i = i5;
                        f = f2;
                        i2 = i6;
                        this.circleRadius = a.getDimensionPixelSize(attr, this.circleRadius);
                        i3 = -1;
                        break;
                    case 4:
                        i = i5;
                        i2 = i6;
                        this.circleAngle = a.getFloat(attr, this.circleAngle) % 360.0f;
                        f = 0.0f;
                        if (this.circleAngle < 0.0f) {
                            this.circleAngle = (360.0f - this.circleAngle) % 360.0f;
                        }
                        i3 = -1;
                        break;
                    case 5:
                        i = i5;
                        i2 = i6;
                        this.guideBegin = a.getDimensionPixelOffset(attr, this.guideBegin);
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 6:
                        i = i5;
                        i2 = i6;
                        this.guideEnd = a.getDimensionPixelOffset(attr, this.guideEnd);
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 7:
                        i = i5;
                        i2 = i6;
                        this.guidePercent = a.getFloat(attr, this.guidePercent);
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 8:
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        this.leftToLeft = a.getResourceId(attr, this.leftToLeft);
                        if (this.leftToLeft == i3) {
                            this.leftToLeft = a.getInt(attr, i3);
                            i3 = -1;
                        }
                        f = 0.0f;
                        break;
                    case 9:
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        this.leftToRight = a.getResourceId(attr, this.leftToRight);
                        if (this.leftToRight == i3) {
                            this.leftToRight = a.getInt(attr, i3);
                        }
                        f = 0.0f;
                        break;
                    case 10:
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        this.rightToLeft = a.getResourceId(attr, this.rightToLeft);
                        if (this.rightToLeft == i3) {
                            this.rightToLeft = a.getInt(attr, i3);
                        }
                        f = 0.0f;
                        break;
                    case 11:
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        this.rightToRight = a.getResourceId(attr, this.rightToRight);
                        if (this.rightToRight == i3) {
                            this.rightToRight = a.getInt(attr, i3);
                        }
                        f = 0.0f;
                        break;
                    case 12:
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        this.topToTop = a.getResourceId(attr, this.topToTop);
                        if (this.topToTop == i3) {
                            this.topToTop = a.getInt(attr, i3);
                        }
                        f = 0.0f;
                        break;
                    case 13:
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        this.topToBottom = a.getResourceId(attr, this.topToBottom);
                        if (this.topToBottom == i3) {
                            this.topToBottom = a.getInt(attr, i3);
                        }
                        f = 0.0f;
                        break;
                    case 14:
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        this.bottomToTop = a.getResourceId(attr, this.bottomToTop);
                        if (this.bottomToTop == i3) {
                            this.bottomToTop = a.getInt(attr, i3);
                        }
                        f = 0.0f;
                        break;
                    case 15:
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        this.bottomToBottom = a.getResourceId(attr, this.bottomToBottom);
                        if (this.bottomToBottom == i3) {
                            this.bottomToBottom = a.getInt(attr, i3);
                        }
                        f = 0.0f;
                        break;
                    case 16:
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        this.baselineToBaseline = a.getResourceId(attr, this.baselineToBaseline);
                        if (this.baselineToBaseline == i3) {
                            this.baselineToBaseline = a.getInt(attr, i3);
                        }
                        f = 0.0f;
                        break;
                    case 17:
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        this.startToEnd = a.getResourceId(attr, this.startToEnd);
                        if (this.startToEnd == i3) {
                            this.startToEnd = a.getInt(attr, i3);
                        }
                        f = 0.0f;
                        break;
                    case 18:
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        this.startToStart = a.getResourceId(attr, this.startToStart);
                        if (this.startToStart == i3) {
                            this.startToStart = a.getInt(attr, i3);
                        }
                        f = 0.0f;
                        break;
                    case 19:
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        this.endToStart = a.getResourceId(attr, this.endToStart);
                        if (this.endToStart == i3) {
                            this.endToStart = a.getInt(attr, i3);
                        }
                        f = 0.0f;
                        break;
                    case 20:
                        i = i5;
                        i2 = i6;
                        this.endToEnd = a.getResourceId(attr, this.endToEnd);
                        i3 = -1;
                        if (this.endToEnd == -1) {
                            this.endToEnd = a.getInt(attr, -1);
                        }
                        f = 0.0f;
                        break;
                    case 21:
                        i = i5;
                        i2 = i6;
                        this.goneLeftMargin = a.getDimensionPixelSize(attr, this.goneLeftMargin);
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 22:
                        i = i5;
                        i2 = i6;
                        this.goneTopMargin = a.getDimensionPixelSize(attr, this.goneTopMargin);
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 23:
                        i = i5;
                        i2 = i6;
                        this.goneRightMargin = a.getDimensionPixelSize(attr, this.goneRightMargin);
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 24:
                        i = i5;
                        i2 = i6;
                        this.goneBottomMargin = a.getDimensionPixelSize(attr, this.goneBottomMargin);
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 25:
                        i = i5;
                        i2 = i6;
                        this.goneStartMargin = a.getDimensionPixelSize(attr, this.goneStartMargin);
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 26:
                        i = i5;
                        i2 = i6;
                        this.goneEndMargin = a.getDimensionPixelSize(attr, this.goneEndMargin);
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 27:
                        i = i5;
                        i2 = i6;
                        this.constrainedWidth = a.getBoolean(attr, this.constrainedWidth);
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 28:
                        i = i5;
                        i2 = i6;
                        this.constrainedHeight = a.getBoolean(attr, this.constrainedHeight);
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 29:
                        i = i5;
                        i2 = i6;
                        this.horizontalBias = a.getFloat(attr, this.horizontalBias);
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 30:
                        i = i5;
                        i2 = i6;
                        this.verticalBias = a.getFloat(attr, this.verticalBias);
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 31:
                        i = 0;
                        this.matchConstraintDefaultWidth = a.getInt(attr, 0);
                        i2 = 1;
                        if (this.matchConstraintDefaultWidth == 1) {
                            Log.e(ConstraintLayout.TAG, "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                        }
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 32:
                        this.matchConstraintDefaultHeight = a.getInt(attr, 0);
                        if (this.matchConstraintDefaultHeight != 1) {
                            i2 = 1;
                            i = 0;
                            i3 = -1;
                            f = 0.0f;
                        } else {
                            Log.e(ConstraintLayout.TAG, "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                            i = 0;
                            i2 = 1;
                            i3 = -1;
                            f = 0.0f;
                        }
                        break;
                    case 33:
                        try {
                            this.matchConstraintMinWidth = a.getDimensionPixelSize(attr, this.matchConstraintMinWidth);
                        } catch (Exception e) {
                            int value = a.getInt(attr, this.matchConstraintMinWidth);
                            if (value == -2) {
                                this.matchConstraintMinWidth = -2;
                            }
                        }
                        i = 0;
                        i2 = 1;
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 34:
                        try {
                            this.matchConstraintMaxWidth = a.getDimensionPixelSize(attr, this.matchConstraintMaxWidth);
                        } catch (Exception e2) {
                            int value2 = a.getInt(attr, this.matchConstraintMaxWidth);
                            if (value2 == -2) {
                                this.matchConstraintMaxWidth = -2;
                            }
                        }
                        i = 0;
                        i2 = 1;
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 35:
                        this.matchConstraintPercentWidth = Math.max(0.0f, a.getFloat(attr, this.matchConstraintPercentWidth));
                        i = 0;
                        i2 = 1;
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 36:
                        try {
                            this.matchConstraintMinHeight = a.getDimensionPixelSize(attr, this.matchConstraintMinHeight);
                        } catch (Exception e3) {
                            int value3 = a.getInt(attr, this.matchConstraintMinHeight);
                            if (value3 == -2) {
                                this.matchConstraintMinHeight = -2;
                            }
                        }
                        i = 0;
                        i2 = 1;
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 37:
                        try {
                            this.matchConstraintMaxHeight = a.getDimensionPixelSize(attr, this.matchConstraintMaxHeight);
                        } catch (Exception e4) {
                            int value4 = a.getInt(attr, this.matchConstraintMaxHeight);
                            if (value4 == -2) {
                                this.matchConstraintMaxHeight = -2;
                            }
                        }
                        i = 0;
                        i2 = 1;
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 38:
                        this.matchConstraintPercentHeight = Math.max(0.0f, a.getFloat(attr, this.matchConstraintPercentHeight));
                        i = 0;
                        i2 = 1;
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                        i = 0;
                        i2 = 1;
                        i3 = -1;
                        f = 0.0f;
                        break;
                    case 43:
                    default:
                        f = f2;
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        break;
                    case 44:
                        this.dimensionRatio = a.getString(attr);
                        this.dimensionRatioValue = Float.NaN;
                        this.dimensionRatioSide = i4;
                        if (this.dimensionRatio != null) {
                            int len = this.dimensionRatio.length();
                            int commaIndex2 = this.dimensionRatio.indexOf(44);
                            if (commaIndex2 <= 0 || commaIndex2 >= len - 1) {
                                commaIndex = 0;
                            } else {
                                String dimension = this.dimensionRatio.substring(i5, commaIndex2);
                                if (dimension.equalsIgnoreCase("W")) {
                                    this.dimensionRatioSide = i5;
                                } else if (dimension.equalsIgnoreCase("H")) {
                                    this.dimensionRatioSide = i6;
                                }
                                commaIndex = commaIndex2 + 1;
                            }
                            int colonIndex = this.dimensionRatio.indexOf(58);
                            if (colonIndex < 0 || colonIndex >= len - 1) {
                                String r = this.dimensionRatio.substring(commaIndex);
                                if (r.length() > 0) {
                                    try {
                                        this.dimensionRatioValue = Float.parseFloat(r);
                                        break;
                                    } catch (NumberFormatException e5) {
                                    }
                                }
                            } else {
                                String nominator = this.dimensionRatio.substring(commaIndex, colonIndex);
                                String denominator = this.dimensionRatio.substring(colonIndex + 1);
                                if (nominator.length() > 0 && denominator.length() > 0) {
                                    try {
                                        float nominatorValue = Float.parseFloat(nominator);
                                        float denominatorValue = Float.parseFloat(denominator);
                                        if (nominatorValue > f2 && denominatorValue > f2) {
                                            if (this.dimensionRatioSide == 1) {
                                                try {
                                                    this.dimensionRatioValue = Math.abs(denominatorValue / nominatorValue);
                                                    break;
                                                } catch (NumberFormatException e6) {
                                                }
                                            } else {
                                                this.dimensionRatioValue = Math.abs(nominatorValue / denominatorValue);
                                            }
                                        }
                                    } catch (NumberFormatException e7) {
                                    }
                                }
                            }
                            i = 0;
                            i2 = 1;
                            i3 = -1;
                            f = 0.0f;
                        }
                        f = f2;
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        break;
                    case 45:
                        this.horizontalWeight = a.getFloat(attr, f2);
                        f = f2;
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        break;
                    case 46:
                        this.verticalWeight = a.getFloat(attr, f2);
                        f = f2;
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        break;
                    case 47:
                        this.horizontalChainStyle = a.getInt(attr, i5);
                        f = f2;
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        break;
                    case 48:
                        this.verticalChainStyle = a.getInt(attr, i5);
                        f = f2;
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        break;
                    case 49:
                        this.editorAbsoluteX = a.getDimensionPixelOffset(attr, this.editorAbsoluteX);
                        f = f2;
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        break;
                    case 50:
                        this.editorAbsoluteY = a.getDimensionPixelOffset(attr, this.editorAbsoluteY);
                        f = f2;
                        i2 = i6;
                        i3 = i4;
                        i = i5;
                        break;
                }
                i7++;
                i5 = i;
                i4 = i3;
                i6 = i2;
                f2 = f;
            }
            a.recycle();
            validate();
        }

        public void validate() {
            this.isGuideline = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            if (this.width == -2 && this.constrainedWidth) {
                this.horizontalDimensionFixed = false;
                this.matchConstraintDefaultWidth = 1;
            }
            if (this.height == -2 && this.constrainedHeight) {
                this.verticalDimensionFixed = false;
                this.matchConstraintDefaultHeight = 1;
            }
            if (this.width == 0 || this.width == -1) {
                this.horizontalDimensionFixed = false;
                if (this.width == 0 && this.matchConstraintDefaultWidth == 1) {
                    this.width = -2;
                    this.constrainedWidth = true;
                }
            }
            if (this.height == 0 || this.height == -1) {
                this.verticalDimensionFixed = false;
                if (this.height == 0 && this.matchConstraintDefaultHeight == 1) {
                    this.height = -2;
                    this.constrainedHeight = true;
                }
            }
            if (this.guidePercent != -1.0f || this.guideBegin != -1 || this.guideEnd != -1) {
                this.isGuideline = true;
                this.horizontalDimensionFixed = true;
                this.verticalDimensionFixed = true;
                if (!(this.widget instanceof android.support.constraint.solver.widgets.Guideline)) {
                    this.widget = new android.support.constraint.solver.widgets.Guideline();
                }
                ((android.support.constraint.solver.widgets.Guideline) this.widget).setOrientation(this.orientation);
            }
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = 0.0f;
            this.verticalWeight = 0.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = 0.0f;
            this.verticalWeight = 0.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
        }

        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        @TargetApi(17)
        public void resolveLayoutDirection(int layoutDirection) {
            int preLeftMargin = this.leftMargin;
            int preRightMargin = this.rightMargin;
            super.resolveLayoutDirection(layoutDirection);
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolveGoneLeftMargin = this.goneLeftMargin;
            this.resolveGoneRightMargin = this.goneRightMargin;
            this.resolvedHorizontalBias = this.horizontalBias;
            this.resolvedGuideBegin = this.guideBegin;
            this.resolvedGuideEnd = this.guideEnd;
            this.resolvedGuidePercent = this.guidePercent;
            boolean isRtl = 1 == getLayoutDirection();
            if (!isRtl) {
                if (this.startToEnd != -1) {
                    this.resolvedLeftToRight = this.startToEnd;
                }
                if (this.startToStart != -1) {
                    this.resolvedLeftToLeft = this.startToStart;
                }
                if (this.endToStart != -1) {
                    this.resolvedRightToLeft = this.endToStart;
                }
                if (this.endToEnd != -1) {
                    this.resolvedRightToRight = this.endToEnd;
                }
                if (this.goneStartMargin != -1) {
                    this.resolveGoneLeftMargin = this.goneStartMargin;
                }
                if (this.goneEndMargin != -1) {
                    this.resolveGoneRightMargin = this.goneEndMargin;
                }
            } else {
                boolean startEndDefined = false;
                if (this.startToEnd == -1) {
                    if (this.startToStart != -1) {
                        this.resolvedRightToRight = this.startToStart;
                        startEndDefined = true;
                    }
                } else {
                    this.resolvedRightToLeft = this.startToEnd;
                    startEndDefined = true;
                }
                if (this.endToStart != -1) {
                    this.resolvedLeftToRight = this.endToStart;
                    startEndDefined = true;
                }
                if (this.endToEnd != -1) {
                    this.resolvedLeftToLeft = this.endToEnd;
                    startEndDefined = true;
                }
                if (this.goneStartMargin != -1) {
                    this.resolveGoneRightMargin = this.goneStartMargin;
                }
                if (this.goneEndMargin != -1) {
                    this.resolveGoneLeftMargin = this.goneEndMargin;
                }
                if (startEndDefined) {
                    this.resolvedHorizontalBias = 1.0f - this.horizontalBias;
                }
                if (this.isGuideline && this.orientation == 1) {
                    if (this.guidePercent == -1.0f) {
                        if (this.guideBegin == -1) {
                            if (this.guideEnd != -1) {
                                this.resolvedGuideBegin = this.guideEnd;
                                this.resolvedGuideEnd = -1;
                                this.resolvedGuidePercent = -1.0f;
                            }
                        } else {
                            this.resolvedGuideEnd = this.guideBegin;
                            this.resolvedGuideBegin = -1;
                            this.resolvedGuidePercent = -1.0f;
                        }
                    } else {
                        this.resolvedGuidePercent = 1.0f - this.guidePercent;
                        this.resolvedGuideBegin = -1;
                        this.resolvedGuideEnd = -1;
                    }
                }
            }
            if (this.endToStart == -1 && this.endToEnd == -1 && this.startToStart == -1 && this.startToEnd == -1) {
                if (this.rightToLeft == -1) {
                    if (this.rightToRight != -1) {
                        this.resolvedRightToRight = this.rightToRight;
                        if (this.rightMargin <= 0 && preRightMargin > 0) {
                            this.rightMargin = preRightMargin;
                        }
                    }
                } else {
                    this.resolvedRightToLeft = this.rightToLeft;
                    if (this.rightMargin <= 0 && preRightMargin > 0) {
                        this.rightMargin = preRightMargin;
                    }
                }
                if (this.leftToLeft == -1) {
                    if (this.leftToRight != -1) {
                        this.resolvedLeftToRight = this.leftToRight;
                        if (this.leftMargin <= 0 && preLeftMargin > 0) {
                            this.leftMargin = preLeftMargin;
                            return;
                        }
                        return;
                    }
                    return;
                }
                this.resolvedLeftToLeft = this.leftToLeft;
                if (this.leftMargin <= 0 && preLeftMargin > 0) {
                    this.leftMargin = preLeftMargin;
                }
            }
        }
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        this.mDirtyHierarchy = true;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
