package com.viaccessorca.analytics.fma;

import android.util.Log;

/* JADX INFO: loaded from: classes2.dex */
public class VoFmaEventQueue {
    private EventNode mCurrent;
    private EventNode mBegin = null;
    private EventNode mEnd = null;
    private int mCount = 0;

    protected class EventNode {
        protected VoFmaEvent mEvent;
        protected long mTime;
        protected EventNode mPrev = null;
        protected EventNode mNext = null;

        protected EventNode(VoFmaEvent voFmaEvent) {
            this.mTime = voFmaEvent.getCurrentTime();
            this.mEvent = voFmaEvent;
        }

        protected EventNode getNext() {
            return this.mNext;
        }
    }

    protected VoFmaEventQueue() {
    }

    protected synchronized boolean isEmpty() {
        return this.mCount == 0;
    }

    protected void status() {
        if (this.mCount == 0 && this.mBegin == null && this.mEnd == null) {
            Log.e("Q-KOBI", "Status: OK (empty)");
        } else if (this.mCount > 0 && this.mBegin != null && this.mEnd != null) {
            Log.e("Q-KOBI", "Status: OK");
        } else {
            Log.e("Q-KOBI", "Status: ERROR!!");
        }
        Log.e("Q-KOBI", "Status: count = " + this.mCount);
        if (this.mBegin != null) {
            Log.e("Q-KOBI", "Status: mBegin = Not null");
        } else {
            Log.e("Q-KOBI", "Status: mBegin = null");
        }
        if (this.mEnd != null) {
            Log.e("Q-KOBI", "Status: mEnd = Not null");
        } else {
            Log.e("Q-KOBI", "Status: mEnd = null");
        }
    }

    protected synchronized EventNode first() {
        this.mCurrent = null;
        if (!isEmpty()) {
            if (this.mBegin == null && this.mEnd != null) {
                this.mBegin = this.mEnd;
                while (this.mBegin.mPrev != null) {
                    this.mBegin = this.mBegin.mPrev;
                }
                while (this.mEnd.mNext != null) {
                    this.mEnd = this.mEnd.mNext;
                }
            }
            this.mCurrent = this.mBegin;
        }
        return this.mCurrent;
    }

    protected synchronized void add(VoFmaEvent voFmaEvent) {
        EventNode eventNode = new EventNode(voFmaEvent);
        if (isEmpty()) {
            this.mBegin = eventNode;
            this.mEnd = eventNode;
        } else if (eventNode.mTime >= this.mEnd.mTime) {
            this.mEnd.mNext = eventNode;
            eventNode.mPrev = this.mEnd;
            this.mEnd = eventNode;
        } else if (eventNode.mTime <= this.mBegin.mTime) {
            eventNode.mNext = this.mBegin;
            this.mBegin.mPrev = eventNode;
            this.mBegin = eventNode;
        } else {
            for (EventNode eventNode2 = this.mBegin; eventNode2 != null; eventNode2 = eventNode2.mNext) {
                if (eventNode.mTime > eventNode2.mTime && (eventNode2.mNext == null || (eventNode2.mNext != null && eventNode.mTime <= eventNode2.mNext.mTime))) {
                    eventNode.mNext = eventNode2.mNext;
                    eventNode.mPrev = eventNode2;
                    eventNode2.mNext = eventNode;
                    break;
                }
            }
        }
        this.mCount++;
    }

    protected synchronized void delete(EventNode eventNode) {
        if (this.mCount == 1) {
            this.mBegin = null;
            this.mEnd = null;
            this.mCount = 0;
        } else {
            if (this.mBegin == eventNode) {
                if (eventNode.mNext != null) {
                    EventNode eventNode2 = eventNode.mNext;
                    this.mBegin = eventNode2;
                    eventNode2.mPrev = null;
                } else {
                    this.mBegin = null;
                }
            } else if (this.mEnd == eventNode) {
                EventNode eventNode3 = eventNode.mPrev;
                this.mEnd = eventNode3;
                eventNode3.mNext = null;
            } else {
                if (eventNode.mPrev != null) {
                    eventNode.mPrev.mNext = eventNode.mNext;
                }
                if (eventNode.mNext != null) {
                    eventNode.mNext.mPrev = eventNode.mPrev;
                }
            }
            this.mCount--;
        }
    }

    public void displayQueue() {
        Log.v("Debugging", "Mcount: " + this.mCount);
        EventNode next = this.mBegin;
        if (next == null || this.mEnd == null) {
            return;
        }
        while (next != null) {
            Log.v("Debugging", " mBegin event:" + next.mEvent.getType() + ", " + next.mEvent.getTime());
            next = next.getNext();
        }
    }
}
