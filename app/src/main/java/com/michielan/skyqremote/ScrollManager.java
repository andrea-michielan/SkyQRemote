package com.michielan.skyqremote;

import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;

public class ScrollManager implements ScrollListener {

    private static final int SCROLL_HORIZONTAL = 1;
    private static final int SCROLL_VERTICAL = 2;

    private ArrayList<ScrollNotifier> clients = new ArrayList<>(4);

    private volatile boolean isSyncing = false;
    private int scrollType = SCROLL_HORIZONTAL;

    public void addScrollClient(ScrollNotifier client) {
        clients.add(client);
        client.setScrollListener(this);
    }

    public void onScrollChanged(View sender, int l, int t, int oldl, int oldt) {
        if (isSyncing)
            return;

        isSyncing = true;

        if (l != oldl)
            scrollType = SCROLL_HORIZONTAL;
        else if (t != oldt)
            scrollType = SCROLL_VERTICAL;
        else {
            isSyncing = false;
            return;
        }

        for (ScrollNotifier client : clients) {
            View view = (View) client;
            if (view == sender)
                continue;

            // scroll relevant views only
            // TODO Add support for horizontal ListViews - currently weird things happen when ListView is being scrolled horizontally
            if ((scrollType == SCROLL_HORIZONTAL && view instanceof HorizontalScrollView)
                    || (scrollType == SCROLL_VERTICAL && view instanceof ScrollView)
                    || (scrollType == SCROLL_VERTICAL && view instanceof ListView)) {
                view.scrollTo(l, t);
            }
        }

        isSyncing = false;
    }

}
