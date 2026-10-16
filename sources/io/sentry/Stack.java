package io.sentry;

import io.sentry.util.Objects;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;

/* JADX INFO: loaded from: classes2.dex */
final class Stack {
    private final Deque<StackItem> items;
    private final ILogger logger;

    static final class StackItem {
        private volatile ISentryClient client;
        private final SentryOptions options;
        private volatile Scope scope;

        StackItem(SentryOptions sentryOptions, ISentryClient iSentryClient, Scope scope) {
            this.client = (ISentryClient) Objects.requireNonNull(iSentryClient, "ISentryClient is required.");
            this.scope = (Scope) Objects.requireNonNull(scope, "Scope is required.");
            this.options = (SentryOptions) Objects.requireNonNull(sentryOptions, "Options is required");
        }

        StackItem(StackItem stackItem) {
            this.options = stackItem.options;
            this.client = stackItem.client;
            this.scope = new Scope(stackItem.scope);
        }

        public ISentryClient getClient() {
            return this.client;
        }

        public void setClient(ISentryClient iSentryClient) {
            this.client = iSentryClient;
        }

        public Scope getScope() {
            return this.scope;
        }

        public SentryOptions getOptions() {
            return this.options;
        }
    }

    public Stack(ILogger iLogger, StackItem stackItem) {
        this.items = new LinkedBlockingDeque();
        this.logger = (ILogger) Objects.requireNonNull(iLogger, "logger is required");
        this.items.push((StackItem) Objects.requireNonNull(stackItem, "rootStackItem is required"));
    }

    public Stack(Stack stack) {
        this(stack.logger, new StackItem(stack.items.getLast()));
        Iterator<StackItem> itDescendingIterator = stack.items.descendingIterator();
        if (itDescendingIterator.hasNext()) {
            itDescendingIterator.next();
        }
        while (itDescendingIterator.hasNext()) {
            push(new StackItem(itDescendingIterator.next()));
        }
    }

    StackItem peek() {
        return this.items.peek();
    }

    void pop() {
        synchronized (this.items) {
            if (this.items.size() != 1) {
                this.items.pop();
            } else {
                this.logger.log(SentryLevel.WARNING, "Attempt to pop the root scope.", new Object[0]);
            }
        }
    }

    void push(StackItem stackItem) {
        this.items.push(stackItem);
    }

    int size() {
        return this.items.size();
    }
}
