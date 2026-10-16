package io.sentry;

import java.util.Queue;

/* JADX INFO: loaded from: classes2.dex */
final class SynchronizedQueue<E> extends SynchronizedCollection<E> implements Queue<E> {
    private static final long serialVersionUID = 1;

    static <E> SynchronizedQueue<E> synchronizedQueue(Queue<E> queue) {
        return new SynchronizedQueue<>(queue);
    }

    private SynchronizedQueue(Queue<E> queue) {
        super(queue);
    }

    protected SynchronizedQueue(Queue<E> queue, Object obj) {
        super(queue, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.sentry.SynchronizedCollection
    public Queue<E> decorated() {
        return (Queue) super.decorated();
    }

    @Override // java.util.Queue
    public E element() {
        E eElement;
        synchronized (this.lock) {
            eElement = decorated().element();
        }
        return eElement;
    }

    @Override // io.sentry.SynchronizedCollection, java.util.Collection
    public boolean equals(Object obj) {
        boolean zEquals;
        if (obj == this) {
            return true;
        }
        synchronized (this.lock) {
            zEquals = decorated().equals(obj);
        }
        return zEquals;
    }

    @Override // io.sentry.SynchronizedCollection, java.util.Collection
    public int hashCode() {
        int iHashCode;
        synchronized (this.lock) {
            iHashCode = decorated().hashCode();
        }
        return iHashCode;
    }

    @Override // java.util.Queue
    public boolean offer(E e) {
        boolean zOffer;
        synchronized (this.lock) {
            zOffer = decorated().offer(e);
        }
        return zOffer;
    }

    @Override // java.util.Queue
    public E peek() {
        E ePeek;
        synchronized (this.lock) {
            ePeek = decorated().peek();
        }
        return ePeek;
    }

    @Override // java.util.Queue
    public E poll() {
        E ePoll;
        synchronized (this.lock) {
            ePoll = decorated().poll();
        }
        return ePoll;
    }

    @Override // java.util.Queue
    public E remove() {
        E eRemove;
        synchronized (this.lock) {
            eRemove = decorated().remove();
        }
        return eRemove;
    }
}
