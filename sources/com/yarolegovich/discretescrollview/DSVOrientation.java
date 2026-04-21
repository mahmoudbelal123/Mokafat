package com.yarolegovich.discretescrollview;

import android.graphics.Point;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public enum DSVOrientation {
    HORIZONTAL { // from class: com.yarolegovich.discretescrollview.DSVOrientation.1
        @Override // com.yarolegovich.discretescrollview.DSVOrientation
        Helper createHelper() {
            return new HorizontalHelper();
        }
    },
    VERTICAL { // from class: com.yarolegovich.discretescrollview.DSVOrientation.2
        @Override // com.yarolegovich.discretescrollview.DSVOrientation
        Helper createHelper() {
            return new VerticalHelper();
        }
    };

    interface Helper {
        boolean canScrollHorizontally();

        boolean canScrollVertically();

        float getDistanceFromCenter(Point point, int i, int i2);

        int getDistanceToChangeCurrent(int i, int i2);

        int getFlingVelocity(int i, int i2);

        int getPendingDx(int i);

        int getPendingDy(int i);

        int getViewEnd(int i, int i2);

        boolean hasNewBecomeVisible(DiscreteScrollLayoutManager discreteScrollLayoutManager);

        boolean isViewVisible(Point point, int i, int i2, int i3, int i4);

        void offsetChildren(int i, RecyclerViewProxy recyclerViewProxy);

        void setCurrentViewCenter(Point point, int i, Point point2);

        void shiftViewCenter(Direction direction, int i, Point point);
    }

    abstract Helper createHelper();

    protected static class HorizontalHelper implements Helper {
        protected HorizontalHelper() {
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public int getViewEnd(int recyclerWidth, int recyclerHeight) {
            return recyclerWidth;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public int getDistanceToChangeCurrent(int childWidth, int childHeight) {
            return childWidth;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public void setCurrentViewCenter(Point recyclerCenter, int scrolled, Point outPoint) {
            int newX = recyclerCenter.x - scrolled;
            outPoint.set(newX, recyclerCenter.y);
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public void shiftViewCenter(Direction direction, int shiftAmount, Point outCenter) {
            int newX = outCenter.x + direction.applyTo(shiftAmount);
            outCenter.set(newX, outCenter.y);
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public boolean isViewVisible(Point viewCenter, int halfWidth, int halfHeight, int endBound, int extraSpace) {
            int viewLeft = viewCenter.x - halfWidth;
            int viewRight = viewCenter.x + halfWidth;
            return viewLeft < endBound + extraSpace && viewRight > (-extraSpace);
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public boolean hasNewBecomeVisible(DiscreteScrollLayoutManager lm) {
            View firstChild = lm.getFirstChild();
            View lastChild = lm.getLastChild();
            int leftBound = -lm.getExtraLayoutSpace();
            int rightBound = lm.getWidth() + lm.getExtraLayoutSpace();
            boolean isNewVisibleFromLeft = lm.getDecoratedLeft(firstChild) > leftBound && lm.getPosition(firstChild) > 0;
            boolean isNewVisibleFromRight = lm.getDecoratedRight(lastChild) < rightBound && lm.getPosition(lastChild) < lm.getItemCount() - 1;
            return isNewVisibleFromLeft || isNewVisibleFromRight;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public void offsetChildren(int amount, RecyclerViewProxy helper) {
            helper.offsetChildrenHorizontal(amount);
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public float getDistanceFromCenter(Point center, int viewCenterX, int viewCenterY) {
            return viewCenterX - center.x;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public int getFlingVelocity(int velocityX, int velocityY) {
            return velocityX;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public boolean canScrollHorizontally() {
            return true;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public boolean canScrollVertically() {
            return false;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public int getPendingDx(int pendingScroll) {
            return pendingScroll;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public int getPendingDy(int pendingScroll) {
            return 0;
        }
    }

    protected static class VerticalHelper implements Helper {
        protected VerticalHelper() {
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public int getViewEnd(int recyclerWidth, int recyclerHeight) {
            return recyclerHeight;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public int getDistanceToChangeCurrent(int childWidth, int childHeight) {
            return childHeight;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public void setCurrentViewCenter(Point recyclerCenter, int scrolled, Point outPoint) {
            int newY = recyclerCenter.y - scrolled;
            outPoint.set(recyclerCenter.x, newY);
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public void shiftViewCenter(Direction direction, int shiftAmount, Point outCenter) {
            int newY = outCenter.y + direction.applyTo(shiftAmount);
            outCenter.set(outCenter.x, newY);
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public void offsetChildren(int amount, RecyclerViewProxy helper) {
            helper.offsetChildrenVertical(amount);
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public float getDistanceFromCenter(Point center, int viewCenterX, int viewCenterY) {
            return viewCenterY - center.y;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public boolean isViewVisible(Point viewCenter, int halfWidth, int halfHeight, int endBound, int extraSpace) {
            int viewTop = viewCenter.y - halfHeight;
            int viewBottom = viewCenter.y + halfHeight;
            return viewTop < endBound + extraSpace && viewBottom > (-extraSpace);
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public boolean hasNewBecomeVisible(DiscreteScrollLayoutManager lm) {
            View firstChild = lm.getFirstChild();
            View lastChild = lm.getLastChild();
            int topBound = -lm.getExtraLayoutSpace();
            int bottomBound = lm.getHeight() + lm.getExtraLayoutSpace();
            boolean isNewVisibleFromTop = lm.getDecoratedTop(firstChild) > topBound && lm.getPosition(firstChild) > 0;
            boolean isNewVisibleFromBottom = lm.getDecoratedBottom(lastChild) < bottomBound && lm.getPosition(lastChild) < lm.getItemCount() - 1;
            return isNewVisibleFromTop || isNewVisibleFromBottom;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public int getFlingVelocity(int velocityX, int velocityY) {
            return velocityY;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public boolean canScrollHorizontally() {
            return false;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public boolean canScrollVertically() {
            return true;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public int getPendingDx(int pendingScroll) {
            return 0;
        }

        @Override // com.yarolegovich.discretescrollview.DSVOrientation.Helper
        public int getPendingDy(int pendingScroll) {
            return pendingScroll;
        }
    }
}
