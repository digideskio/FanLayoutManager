package com.cleveroad.fanlayoutmanager;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

/**
 * Created by Alex Yarovoi 16.08.2016
 */
class ShiftToCenterCardScroller extends LinearSmoothScroller {
    private static final float MILLISECONDS_PER_INCH = 400F;

    public ShiftToCenterCardScroller(Context context) {
        super(context);
    }

    @Override
    public PointF computeScrollVectorForPosition(int targetPosition) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null && layoutManager instanceof FanLayoutManager) {
            if (getChildCount() == 0) {
                return null;
            }
            final int firstChildPos = layoutManager.getPosition(layoutManager.getChildAt(0));
            final int direction = targetPosition < firstChildPos ? -1 : 1;
            return new PointF(direction, 0);
        }
        return new PointF();
    }

    @Override
    protected int getHorizontalSnapPreference() {
        return SNAP_TO_START;
    }

    @Override
    public int calculateDxToMakeVisible(View view, int snapPreference) {
        RecyclerView.LayoutManager layoutManager = getLayoutManager();
        if (layoutManager != null) {
            return super.calculateDxToMakeVisible(view, snapPreference) + layoutManager.getWidth() / 2 - view.getWidth() / 2;
        } else {
            return super.calculateDxToMakeVisible(view, snapPreference);
        }
    }

    @Override
    protected void onTargetFound(View targetView, RecyclerView.State state, Action action) {
        super.onTargetFound(targetView, state, action);
    }

    @Override
    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
    }

    @Override
    protected int calculateTimeForScrolling(int dx) {
        int time = super.calculateTimeForScrolling(dx);
        return time;
    }
}
