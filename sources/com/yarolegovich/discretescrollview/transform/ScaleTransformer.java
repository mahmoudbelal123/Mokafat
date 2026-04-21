package com.yarolegovich.discretescrollview.transform;

import android.support.annotation.FloatRange;
import android.view.View;
import com.yarolegovich.discretescrollview.transform.Pivot;

/* JADX INFO: loaded from: classes.dex */
public class ScaleTransformer implements DiscreteScrollItemTransformer {
    private Pivot pivotX = Pivot.X.CENTER.create();
    private Pivot pivotY = Pivot.Y.CENTER.create();
    private float minScale = 0.8f;
    private float maxMinDiff = 0.2f;

    @Override // com.yarolegovich.discretescrollview.transform.DiscreteScrollItemTransformer
    public void transformItem(View item, float position) {
        this.pivotX.setOn(item);
        this.pivotY.setOn(item);
        float closenessToCenter = 1.0f - Math.abs(position);
        float scale = this.minScale + (this.maxMinDiff * closenessToCenter);
        item.setScaleX(scale);
        item.setScaleY(scale);
    }

    public static class Builder {
        private ScaleTransformer transformer = new ScaleTransformer();
        private float maxScale = 1.0f;

        public Builder setMinScale(@FloatRange(from = 0.01d) float scale) {
            this.transformer.minScale = scale;
            return this;
        }

        public Builder setMaxScale(@FloatRange(from = 0.01d) float scale) {
            this.maxScale = scale;
            return this;
        }

        public Builder setPivotX(Pivot.X pivotX) {
            return setPivotX(pivotX.create());
        }

        public Builder setPivotX(Pivot pivot) {
            assertAxis(pivot, 0);
            this.transformer.pivotX = pivot;
            return this;
        }

        public Builder setPivotY(Pivot.Y pivotY) {
            return setPivotY(pivotY.create());
        }

        public Builder setPivotY(Pivot pivot) {
            assertAxis(pivot, 1);
            this.transformer.pivotY = pivot;
            return this;
        }

        public ScaleTransformer build() {
            this.transformer.maxMinDiff = this.maxScale - this.transformer.minScale;
            return this.transformer;
        }

        private void assertAxis(Pivot pivot, int axis) {
            if (pivot.getAxis() != axis) {
                throw new IllegalArgumentException("You passed a Pivot for wrong axis.");
            }
        }
    }
}
