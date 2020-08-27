package com.michielan.skyqremote;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.OverScroller;
import android.widget.ScrollView;

import androidx.core.view.ViewCompat;

public class SyncedScrollView extends ScrollView implements ScrollNotifier {

    private ScrollListener scrollListener = null;

    private OverScroller scroller;
    private Runnable scrollerTaskRunnable;

    public SyncedScrollView(Context context) {
        super(context);
        init();
    }

    public SyncedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SyncedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        scroller = new OverScroller(getContext());
        scrollerTaskRunnable = new Runnable() {
            @Override
            public void run() {
                scroller.computeScrollOffset();
                smoothScrollTo(0, scroller.getCurrY());
                if (!scroller.isFinished())
                    SyncedScrollView.this.post(this);
                else
                    ViewCompat.postInvalidateOnAnimation(SyncedScrollView.this);
            }
        };

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollListener != null)
            scrollListener.onScrollChanged(this, l, t, oldl, oldt);
    }

    @Override
    public ScrollListener getScrollListener() {
        return scrollListener;
    }

    @Override
    public void setScrollListener(ScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }

    public void fling(int velocityY) {
        scroller.forceFinished(true);
        scroller.fling(getScrollX(), getScrollY(), 0, velocityY, 0, 0, 0, getChildAt(0).getHeight());
        post(scrollerTaskRunnable);
    }

}
