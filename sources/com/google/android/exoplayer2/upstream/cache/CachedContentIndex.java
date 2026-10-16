package com.google.android.exoplayer2.upstream.cache;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.ReusableBufferedOutputStream;
import com.google.android.exoplayer2.util.Util;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes2.dex */
class CachedContentIndex {
    public static final String FILE_NAME = "cached_content_index.exi";
    private static final int FLAG_ENCRYPTED_INDEX = 1;
    private static final int VERSION = 2;
    private final AtomicFile atomicFile;
    private ReusableBufferedOutputStream bufferedOutputStream;
    private boolean changed;
    private final Cipher cipher;
    private final boolean encrypt;
    private final SparseArray<String> idToKey;
    private final HashMap<String, CachedContent> keyToContent;
    private final SparseBooleanArray removedIds;
    private final SecretKeySpec secretKeySpec;

    public CachedContentIndex(File file) {
        this(file, null);
    }

    public CachedContentIndex(File file, byte[] bArr) {
        this(file, bArr, bArr != null);
    }

    public CachedContentIndex(File file, byte[] bArr, boolean z) {
        this.encrypt = z;
        if (bArr != null) {
            Assertions.checkArgument(bArr.length == 16);
            try {
                this.cipher = getCipher();
                this.secretKeySpec = new SecretKeySpec(bArr, "AES");
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                throw new IllegalStateException(e);
            }
        } else {
            Assertions.checkState(!z);
            this.cipher = null;
            this.secretKeySpec = null;
        }
        this.keyToContent = new HashMap<>();
        this.idToKey = new SparseArray<>();
        this.removedIds = new SparseBooleanArray();
        this.atomicFile = new AtomicFile(new File(file, FILE_NAME));
    }

    public void load() {
        Assertions.checkState(!this.changed);
        if (readFile()) {
            return;
        }
        this.atomicFile.delete();
        this.keyToContent.clear();
        this.idToKey.clear();
    }

    public void store() throws Throwable {
        if (this.changed) {
            writeFile();
            this.changed = false;
            int size = this.removedIds.size();
            for (int i = 0; i < size; i++) {
                this.idToKey.remove(this.removedIds.keyAt(i));
            }
            this.removedIds.clear();
        }
    }

    public CachedContent getOrAdd(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        return cachedContent == null ? addNew(str) : cachedContent;
    }

    public CachedContent get(String str) {
        return this.keyToContent.get(str);
    }

    public Collection<CachedContent> getAll() {
        return this.keyToContent.values();
    }

    public int assignIdForKey(String str) {
        return getOrAdd(str).id;
    }

    public String getKeyForId(int i) {
        return this.idToKey.get(i);
    }

    public void maybeRemove(String str) {
        CachedContent cachedContent = this.keyToContent.get(str);
        if (cachedContent == null || !cachedContent.isEmpty() || cachedContent.isLocked()) {
            return;
        }
        this.keyToContent.remove(str);
        this.changed = true;
        this.idToKey.put(cachedContent.id, null);
        this.removedIds.put(cachedContent.id, true);
    }

    public void removeEmpty() {
        int size = this.keyToContent.size();
        String[] strArr = new String[size];
        this.keyToContent.keySet().toArray(strArr);
        for (int i = 0; i < size; i++) {
            maybeRemove(strArr[i]);
        }
    }

    public Set<String> getKeys() {
        return this.keyToContent.keySet();
    }

    public void applyContentMetadataMutations(String str, ContentMetadataMutations contentMetadataMutations) {
        if (getOrAdd(str).applyMetadataMutations(contentMetadataMutations)) {
            this.changed = true;
        }
    }

    public ContentMetadata getContentMetadata(String str) {
        CachedContent cachedContent = get(str);
        return cachedContent != null ? cachedContent.getMetadata() : DefaultContentMetadata.EMPTY;
    }

    private boolean readFile() throws Throwable {
        DataInputStream dataInputStream = null;
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(this.atomicFile.openRead());
            DataInputStream dataInputStream2 = new DataInputStream(bufferedInputStream);
            try {
                int i = dataInputStream2.readInt();
                if (i >= 0 && i <= 2) {
                    if ((dataInputStream2.readInt() & 1) != 0) {
                        if (this.cipher != null) {
                            byte[] bArr = new byte[16];
                            dataInputStream2.readFully(bArr);
                            try {
                                this.cipher.init(2, this.secretKeySpec, new IvParameterSpec(bArr));
                                dataInputStream2 = new DataInputStream(new CipherInputStream(bufferedInputStream, this.cipher));
                            } catch (InvalidAlgorithmParameterException e) {
                                e = e;
                                throw new IllegalStateException(e);
                            } catch (InvalidKeyException e2) {
                                e = e2;
                                throw new IllegalStateException(e);
                            }
                        } else {
                            Util.closeQuietly(dataInputStream2);
                            return false;
                        }
                    } else if (this.encrypt) {
                        this.changed = true;
                    }
                    int i2 = dataInputStream2.readInt();
                    int iHeaderHashCode = 0;
                    for (int i3 = 0; i3 < i2; i3++) {
                        CachedContent fromStream = CachedContent.readFromStream(i, dataInputStream2);
                        add(fromStream);
                        iHeaderHashCode += fromStream.headerHashCode(i);
                    }
                    int i4 = dataInputStream2.readInt();
                    boolean z = dataInputStream2.read() == -1;
                    if (i4 == iHeaderHashCode && z) {
                        Util.closeQuietly(dataInputStream2);
                        return true;
                    }
                    Util.closeQuietly(dataInputStream2);
                    return false;
                }
                Util.closeQuietly(dataInputStream2);
                return false;
            } catch (IOException unused) {
                dataInputStream = dataInputStream2;
                if (dataInputStream != null) {
                    Util.closeQuietly(dataInputStream);
                }
                return false;
            } catch (Throwable th) {
                th = th;
                dataInputStream = dataInputStream2;
                if (dataInputStream != null) {
                    Util.closeQuietly(dataInputStream);
                }
                throw th;
            }
        } catch (IOException unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private void writeFile() throws Throwable {
        DataOutputStream dataOutputStream = null;
        try {
            try {
                OutputStream outputStreamStartWrite = this.atomicFile.startWrite();
                if (this.bufferedOutputStream == null) {
                    this.bufferedOutputStream = new ReusableBufferedOutputStream(outputStreamStartWrite);
                } else {
                    this.bufferedOutputStream.reset(outputStreamStartWrite);
                }
                DataOutputStream dataOutputStream2 = new DataOutputStream(this.bufferedOutputStream);
                try {
                    dataOutputStream2.writeInt(2);
                    int iHeaderHashCode = 0;
                    dataOutputStream2.writeInt(this.encrypt ? 1 : 0);
                    if (this.encrypt) {
                        byte[] bArr = new byte[16];
                        new Random().nextBytes(bArr);
                        dataOutputStream2.write(bArr);
                        try {
                            this.cipher.init(1, this.secretKeySpec, new IvParameterSpec(bArr));
                            dataOutputStream2.flush();
                            dataOutputStream2 = new DataOutputStream(new CipherOutputStream(this.bufferedOutputStream, this.cipher));
                        } catch (InvalidAlgorithmParameterException e) {
                            e = e;
                            throw new IllegalStateException(e);
                        } catch (InvalidKeyException e2) {
                            e = e2;
                            throw new IllegalStateException(e);
                        }
                    }
                    dataOutputStream2.writeInt(this.keyToContent.size());
                    for (CachedContent cachedContent : this.keyToContent.values()) {
                        cachedContent.writeToStream(dataOutputStream2);
                        iHeaderHashCode += cachedContent.headerHashCode(2);
                    }
                    dataOutputStream2.writeInt(iHeaderHashCode);
                    this.atomicFile.endWrite(dataOutputStream2);
                    Util.closeQuietly((Closeable) null);
                } catch (IOException e3) {
                    e = e3;
                    dataOutputStream = dataOutputStream2;
                    throw new Cache.CacheException(e);
                } catch (Throwable th) {
                    th = th;
                    dataOutputStream = dataOutputStream2;
                    Util.closeQuietly(dataOutputStream);
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private CachedContent addNew(String str) {
        CachedContent cachedContent = new CachedContent(getNewId(this.idToKey), str);
        add(cachedContent);
        this.changed = true;
        return cachedContent;
    }

    private void add(CachedContent cachedContent) {
        this.keyToContent.put(cachedContent.key, cachedContent);
        this.idToKey.put(cachedContent.id, cachedContent.key);
    }

    private static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        if (Util.SDK_INT == 18) {
            try {
                return Cipher.getInstance("AES/CBC/PKCS5PADDING", "BC");
            } catch (Throwable unused) {
            }
        }
        return Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }

    public static int getNewId(SparseArray<String> sparseArray) {
        int size = sparseArray.size();
        int i = 0;
        int iKeyAt = size == 0 ? 0 : sparseArray.keyAt(size - 1) + 1;
        if (iKeyAt >= 0) {
            return iKeyAt;
        }
        while (i < size && i == sparseArray.keyAt(i)) {
            i++;
        }
        return i;
    }
}
