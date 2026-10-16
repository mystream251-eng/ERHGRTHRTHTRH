package com.google.crypto.tink.streamingaead;

import com.google.crypto.tink.PrimitiveSet;
import com.google.crypto.tink.StreamingAead;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.NonWritableChannelException;
import java.nio.channels.SeekableByteChannel;
import java.security.GeneralSecurityException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
final class SeekableByteChannelDecrypter implements SeekableByteChannel {
    byte[] associatedData;
    long cachedPosition;
    SeekableByteChannel ciphertextChannel;
    long startingPosition;
    SeekableByteChannel attemptingChannel = null;
    SeekableByteChannel matchingChannel = null;
    Deque<StreamingAead> remainingPrimitives = new ArrayDeque();

    public SeekableByteChannelDecrypter(PrimitiveSet<StreamingAead> primitives, SeekableByteChannel ciphertextChannel, final byte[] associatedData) throws IOException {
        Iterator<PrimitiveSet.Entry<StreamingAead>> it = primitives.getRawPrimitives().iterator();
        while (it.hasNext()) {
            this.remainingPrimitives.add(it.next().getPrimitive());
        }
        this.ciphertextChannel = ciphertextChannel;
        this.cachedPosition = -1L;
        this.startingPosition = ciphertextChannel.position();
        this.associatedData = (byte[]) associatedData.clone();
    }

    private synchronized SeekableByteChannel nextAttemptingChannel() throws IOException {
        SeekableByteChannel seekableByteChannelNewSeekableDecryptingChannel;
        while (!this.remainingPrimitives.isEmpty()) {
            this.ciphertextChannel.position(this.startingPosition);
            try {
                seekableByteChannelNewSeekableDecryptingChannel = this.remainingPrimitives.removeFirst().newSeekableDecryptingChannel(this.ciphertextChannel, this.associatedData);
                if (this.cachedPosition >= 0) {
                    seekableByteChannelNewSeekableDecryptingChannel.position(this.cachedPosition);
                }
            } catch (GeneralSecurityException unused) {
            }
        }
        throw new IOException("No matching key found for the ciphertext in the stream.");
        return seekableByteChannelNewSeekableDecryptingChannel;
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    public synchronized int read(ByteBuffer dst) throws IOException {
        if (dst.remaining() == 0) {
            return 0;
        }
        if (this.matchingChannel != null) {
            return this.matchingChannel.read(dst);
        }
        if (this.attemptingChannel == null) {
            this.attemptingChannel = nextAttemptingChannel();
        }
        while (true) {
            try {
                int i = this.attemptingChannel.read(dst);
                if (i == 0) {
                    return 0;
                }
                this.matchingChannel = this.attemptingChannel;
                this.attemptingChannel = null;
                return i;
            } catch (IOException unused) {
                this.attemptingChannel = nextAttemptingChannel();
            }
            throw th;
        }
    }

    @Override // java.nio.channels.SeekableByteChannel
    public synchronized SeekableByteChannel position(long newPosition) throws IOException {
        if (this.matchingChannel != null) {
            this.matchingChannel.position(newPosition);
        } else {
            if (newPosition < 0) {
                throw new IllegalArgumentException("Position must be non-negative");
            }
            this.cachedPosition = newPosition;
            if (this.attemptingChannel != null) {
                this.attemptingChannel.position(newPosition);
            }
        }
        return this;
    }

    @Override // java.nio.channels.SeekableByteChannel
    public synchronized long position() throws IOException {
        if (this.matchingChannel != null) {
            return this.matchingChannel.position();
        }
        return this.cachedPosition;
    }

    @Override // java.nio.channels.SeekableByteChannel
    public synchronized long size() throws IOException {
        if (this.matchingChannel != null) {
        } else {
            throw new IOException("Cannot determine size before first read()-call.");
        }
        return this.matchingChannel.size();
    }

    @Override // java.nio.channels.SeekableByteChannel
    public SeekableByteChannel truncate(long size) throws IOException {
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer src) throws IOException {
        throw new NonWritableChannelException();
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() throws IOException {
        this.ciphertextChannel.close();
    }

    @Override // java.nio.channels.Channel
    public synchronized boolean isOpen() {
        return this.ciphertextChannel.isOpen();
    }
}
