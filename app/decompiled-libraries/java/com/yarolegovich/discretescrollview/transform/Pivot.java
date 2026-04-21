package com.yarolegovich.discretescrollview.transform;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class Pivot {
    public static final int AXIS_X = 0;
    public static final int AXIS_Y = 1;
    private static final int PIVOT_CENTER = -1;
    private static final int PIVOT_MAX = -2;
    private int axis;
    private int pivotPoint;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Axis {
    }

    public enum X {
        LEFT { // from class: com.yarolegovich.discretescrollview.transform.Pivot.X.1
            @Override // com.yarolegovich.discretescrollview.transform.Pivot.X
            public Pivot create() {
                return new Pivot(0, 0);
            }
        },
        CENTER { // from class: com.yarolegovich.discretescrollview.transform.Pivot.X.2
            @Override // com.yarolegovich.discretescrollview.transform.Pivot.X
            public Pivot create() {
                return new Pivot(0, -1);
            }
        },
        RIGHT { // from class: com.yarolegovich.discretescrollview.transform.Pivot.X.3
            @Override // com.yarolegovich.discretescrollview.transform.Pivot.X
            public Pivot create() {
                return new Pivot(0, -2);
            }
        };

        public abstract Pivot create();
    }

    public enum Y {
        TOP { // from class: com.yarolegovich.discretescrollview.transform.Pivot.Y.1
            @Override // com.yarolegovich.discretescrollview.transform.Pivot.Y
            public Pivot create() {
                return new Pivot(1, 0);
            }
        },
        CENTER { // from class: com.yarolegovich.discretescrollview.transform.Pivot.Y.2
            @Override // com.yarolegovich.discretescrollview.transform.Pivot.Y
            public Pivot create() {
                return new Pivot(1, -1);
            }
        },
        BOTTOM { // from class: com.yarolegovich.discretescrollview.transform.Pivot.Y.3
            @Override // com.yarolegovich.discretescrollview.transform.Pivot.Y
            public Pivot create() {
                return new Pivot(1, -2);
            }
        };

        public abstract Pivot create();
    }

    public Pivot(int axis, int pivotPoint) {
        this.axis = axis;
        this.pivotPoint = pivotPoint;
    }

    public void setOn(View view) {
        if (this.axis == 0) {
            switch (this.pivotPoint) {
                case -2:
                    view.setPivotX(view.getWidth());
                    break;
                case -1:
                    view.setPivotX(view.getWidth() * 0.5f);
                    break;
                default:
                    view.setPivotX(this.pivotPoint);
                    break;
            }
        }
        if (this.axis == 1) {
            switch (this.pivotPoint) {
                case -2:
                    view.setPivotY(view.getHeight());
                    break;
                case -1:
                    view.setPivotY(view.getHeight() * 0.5f);
                    break;
                default:
                    view.setPivotY(this.pivotPoint);
                    break;
            }
        }
    }

    public int getAxis() {
        return this.axis;
    }
}
