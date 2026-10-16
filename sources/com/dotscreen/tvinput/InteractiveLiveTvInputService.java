package com.dotscreen.tvinput;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.media.tv.TvInputService;
import android.net.Uri;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.NotificationCompat;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.dotscreen.tv.common.INetRequestProvider;
import com.dotscreen.tv.common.utils.CommonUtils;
import com.google.android.media.tv.companionlibrary.TvPlayer;
import com.google.android.media.tv.companionlibrary.model.Channel;
import com.google.android.media.tv.companionlibrary.model.InternalProviderData;
import com.google.android.media.tv.companionlibrary.model.Program;
import com.google.android.media.tv.companionlibrary.model.RecordedProgram;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: InteractiveLiveTvInputService.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0016R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/dotscreen/tvinput/InteractiveLiveTvInputService;", "Lcom/dotscreen/tvinput/BaseMegaTvInputService;", "()V", "TAG", "", "kotlin.jvm.PlatformType", "onCreateSession", "Landroid/media/tv/TvInputService$Session;", "inputId", "TvInputSessionImpl", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
public final class InteractiveLiveTvInputService extends BaseMegaTvInputService {
    private final String TAG = InteractiveLiveTvInputService.class.getSimpleName();

    @Override // android.media.tv.TvInputService
    public TvInputService.Session onCreateSession(String inputId) {
        Log.d(this.TAG, "onCreateSession (" + getApplicationContext() + ')');
        TvInputSessionImpl tvInputSessionImpl = new TvInputSessionImpl(this, this, inputId);
        tvInputSessionImpl.setOverlayViewEnabled(true);
        TvInputService.Session sessionSessionCreated = super.sessionCreated(tvInputSessionImpl);
        Intrinsics.checkExpressionValueIsNotNull(sessionSessionCreated, "super.sessionCreated(session)");
        return sessionSessionCreated;
    }

    /* JADX INFO: compiled from: InteractiveLiveTvInputService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0012H\u0016J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u0012\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u001a\u0010!\u001a\u00020\u00172\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%H\u0016J\u0012\u0010&\u001a\u00020\u00172\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\b\u0010)\u001a\u00020\u001eH\u0016J\u0010\u0010*\u001a\u00020\u001e2\u0006\u0010+\u001a\u00020\u0017H\u0016J\u0010\u0010,\u001a\u00020\u00172\u0006\u0010-\u001a\u00020.H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\n\u001a\b\u0018\u00010\u000bR\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/dotscreen/tvinput/InteractiveLiveTvInputService$TvInputSessionImpl;", "Lcom/dotscreen/tvinput/BaseMegaTvInputService$Session;", "Lcom/dotscreen/tvinput/BaseMegaTvInputService;", "context", "Landroid/content/Context;", "inputId", "", "(Lcom/dotscreen/tvinput/InteractiveLiveTvInputService;Landroid/content/Context;Ljava/lang/String;)V", "channelInfoDisposable", "Lio/reactivex/disposables/Disposable;", "imageContainer", "Lcom/android/volley/toolbox/ImageLoader$ImageContainer;", "Lcom/android/volley/toolbox/ImageLoader;", "getImageContainer", "()Lcom/android/volley/toolbox/ImageLoader$ImageContainer;", "setImageContainer", "(Lcom/android/volley/toolbox/ImageLoader$ImageContainer;)V", "overlayView", "Landroid/view/View;", "getTvPlayer", "Lcom/google/android/media/tv/companionlibrary/TvPlayer;", "onCreateOverlayView", "onKeyDown", "", "keyCode", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onKeyUp", "onPlayChannel", "", "channel", "Lcom/google/android/media/tv/companionlibrary/model/Channel;", "onPlayProgram", "program", "Lcom/google/android/media/tv/companionlibrary/model/Program;", "startPosMs", "", "onPlayRecordedProgram", "recordedProgram", "Lcom/google/android/media/tv/companionlibrary/model/RecordedProgram;", "onRelease", "onSetCaptionEnabled", "enabled", "onTune", "channelUri", "Landroid/net/Uri;", "tvinput_prodRelease"}, k = 1, mv = {1, 1, 16})
    public final class TvInputSessionImpl extends BaseMegaTvInputService.Session {
        private Disposable channelInfoDisposable;
        private final Context context;
        private ImageLoader.ImageContainer imageContainer;
        private final String inputId;
        private View overlayView;
        final /* synthetic */ InteractiveLiveTvInputService this$0;

        @Override // com.dotscreen.tvinput.BaseMegaTvInputService.Session
        public TvPlayer getTvPlayer() {
            return null;
        }

        @Override // com.dotscreen.tvinput.BaseMegaTvInputService.Session
        public boolean onPlayProgram(Program program, long startPosMs) {
            return true;
        }

        @Override // com.dotscreen.tvinput.BaseMegaTvInputService.Session
        public boolean onPlayRecordedProgram(RecordedProgram recordedProgram) {
            return false;
        }

        @Override // android.media.tv.TvInputService.Session
        public void onSetCaptionEnabled(boolean enabled) {
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TvInputSessionImpl(InteractiveLiveTvInputService interactiveLiveTvInputService, Context context, String str) {
            super(context, str);
            Intrinsics.checkParameterIsNotNull(context, "context");
            this.this$0 = interactiveLiveTvInputService;
            this.context = context;
            this.inputId = str;
        }

        public final ImageLoader.ImageContainer getImageContainer() {
            return this.imageContainer;
        }

        public final void setImageContainer(ImageLoader.ImageContainer imageContainer) {
            this.imageContainer = imageContainer;
        }

        @Override // android.media.tv.TvInputService.Session
        public View onCreateOverlayView() {
            Log.d(this.this$0.TAG, "onCreateOverlayView()");
            Object systemService = this.this$0.getSystemService("layout_inflater");
            if (systemService == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
            }
            View viewInflate = ((LayoutInflater) systemService).inflate(R.layout.interactive_overlay, (ViewGroup) null);
            this.overlayView = viewInflate;
            if (viewInflate == null) {
                Intrinsics.throwNpe();
            }
            return viewInflate;
        }

        /* JADX WARN: Code duplicated, block: B:9:0x0030  */
        @Override // com.dotscreen.tvinput.BaseMegaTvInputService.Session
        public void onPlayChannel(Channel channel) {
            Object obj;
            Log.d(this.this$0.TAG, "onPlayChannel: " + channel);
            notifyVideoUnavailable(3);
            Object obj2 = null;
            if (channel != null) {
                try {
                    InternalProviderData internalProviderData = channel.getInternalProviderData();
                    if (internalProviderData != null) {
                        obj = internalProviderData.get(CommonUtils.KEY_INTERACTIVE_BACKGROUND_URL);
                    } else {
                        obj = null;
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                    return;
                }
            } else {
                obj = null;
            }
            if (obj instanceof String) {
                obj2 = obj;
            }
            String str = (String) obj2;
            if (str != null) {
                ImageLoader.ImageContainer imageContainer = this.imageContainer;
                if (imageContainer != null) {
                    imageContainer.cancelRequest();
                }
                Object applicationContext = this.this$0.getApplicationContext();
                if (applicationContext == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.dotscreen.tv.common.INetRequestProvider");
                }
                this.imageContainer = ((INetRequestProvider) applicationContext).getImageLoader().get(str, new ImageLoader.ImageListener() { // from class: com.dotscreen.tvinput.InteractiveLiveTvInputService$TvInputSessionImpl$onPlayChannel$$inlined$let$lambda$1
                    @Override // com.android.volley.Response.ErrorListener
                    public void onErrorResponse(VolleyError error) {
                        Intrinsics.checkParameterIsNotNull(error, "error");
                    }

                    @Override // com.android.volley.toolbox.ImageLoader.ImageListener
                    public void onResponse(ImageLoader.ImageContainer response, boolean z) {
                        Intrinsics.checkParameterIsNotNull(response, "response");
                        View view = this.this$0.overlayView;
                        if (view != null) {
                            view.setBackground(new BitmapDrawable(this.this$0.this$0.getResources(), response.getBitmap()));
                        }
                        this.this$0.notifyVideoAvailable();
                    }
                });
            }
        }

        @Override // com.dotscreen.tvinput.BaseMegaTvInputService.Session, android.media.tv.TvInputService.Session
        public void onRelease() {
            Log.d(this.this$0.TAG, "onRelease()");
            ImageLoader.ImageContainer imageContainer = this.imageContainer;
            if (imageContainer != null) {
                imageContainer.cancelRequest();
            }
            super.onRelease();
        }

        @Override // com.dotscreen.tvinput.BaseMegaTvInputService.Session, android.media.tv.TvInputService.Session
        public boolean onTune(Uri channelUri) {
            Intrinsics.checkParameterIsNotNull(channelUri, "channelUri");
            notifyVideoUnavailable(1);
            Uri uriBuild = channelUri.buildUpon().query("").build();
            Log.d(this.this$0.TAG, "Tune to " + uriBuild);
            return super.onTune(uriBuild);
        }

        @Override // android.media.tv.TvInputService.Session, android.view.KeyEvent.Callback
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            Log.d(this.this$0.TAG, "onKeyDown(" + keyCode + ", " + event + ')');
            return super.onKeyDown(keyCode, event);
        }

        @Override // android.media.tv.TvInputService.Session, android.view.KeyEvent.Callback
        public boolean onKeyUp(int keyCode, KeyEvent event) {
            Log.d(this.this$0.TAG, "onKeyUp(" + keyCode + ", " + event + ')');
            return super.onKeyUp(keyCode, event);
        }
    }
}
