package com.yarolegovich.discretescrollview;

/* JADX INFO: loaded from: classes.dex */
enum Direction {
    START { // from class: com.yarolegovich.discretescrollview.Direction.1
        @Override // com.yarolegovich.discretescrollview.Direction
        public int applyTo(int delta) {
            return delta * (-1);
        }

        @Override // com.yarolegovich.discretescrollview.Direction
        public boolean sameAs(int direction) {
            return direction < 0;
        }
    },
    END { // from class: com.yarolegovich.discretescrollview.Direction.2
        @Override // com.yarolegovich.discretescrollview.Direction
        public int applyTo(int delta) {
            return delta;
        }

        @Override // com.yarolegovich.discretescrollview.Direction
        public boolean sameAs(int direction) {
            return direction > 0;
        }
    };

    public abstract int applyTo(int i);

    public abstract boolean sameAs(int i);

    public static Direction fromDelta(int delta) {
        return delta > 0 ? END : START;
    }
}
