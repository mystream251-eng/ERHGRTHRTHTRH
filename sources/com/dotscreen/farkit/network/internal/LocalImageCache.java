package com.dotscreen.farkit.network.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.android.volley.toolbox.ImageLoader;

/* JADX INFO: loaded from: classes.dex */
public class LocalImageCache implements ImageLoader.ImageCache {
    private static final String ASSET_PREFIX = "file:///android_asset/";
    private Context mContext;

    @Override // com.android.volley.toolbox.ImageLoader.ImageCache
    public void putBitmap(String str, Bitmap bitmap) {
    }

    public LocalImageCache(Context context) {
        this.mContext = context;
    }

    @Override // com.android.volley.toolbox.ImageLoader.ImageCache
    public Bitmap getBitmap(String str) {
        try {
            if (str.contains(ASSET_PREFIX)) {
                return BitmapFactory.decodeStream(this.mContext.getResources().getAssets().open(str.substring(str.indexOf(ASSET_PREFIX) + 22)));
            }
            if (!str.contains("android.resource")) {
                return null;
            }
            return BitmapFactory.decodeStream(this.mContext.getContentResolver().openInputStream(Uri.parse(str.substring(str.indexOf("android.resource")))));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
