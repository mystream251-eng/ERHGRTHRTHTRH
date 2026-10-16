package com.dotscreen.farkit.design;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DesignContextWrapper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/dotscreen/farkit/design/DesignContextWrapper;", "Landroid/content/ContextWrapper;", "context", "Landroid/content/Context;", "designManager", "Lcom/dotscreen/farkit/design/DesignManager;", "(Landroid/content/Context;Lcom/dotscreen/farkit/design/DesignManager;)V", "designLayoutInflater", "Lcom/dotscreen/farkit/design/DesignLayoutInflater;", "getDesignLayoutInflater", "()Lcom/dotscreen/farkit/design/DesignLayoutInflater;", "designLayoutInflater$delegate", "Lkotlin/Lazy;", "getSystemService", "", "name", "", "farkit_release"}, k = 1, mv = {1, 1, 16})
public final class DesignContextWrapper extends ContextWrapper {

    /* JADX INFO: renamed from: designLayoutInflater$delegate, reason: from kotlin metadata */
    private final Lazy designLayoutInflater;
    private final DesignManager designManager;

    private final DesignLayoutInflater getDesignLayoutInflater() {
        return (DesignLayoutInflater) this.designLayoutInflater.getValue();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DesignContextWrapper(Context context, DesignManager designManager) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(designManager, "designManager");
        this.designManager = designManager;
        this.designLayoutInflater = LazyKt.lazy(new Function0<DesignLayoutInflater>() { // from class: com.dotscreen.farkit.design.DesignContextWrapper$designLayoutInflater$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DesignLayoutInflater invoke() {
                LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.this$0.getBaseContext());
                Intrinsics.checkExpressionValueIsNotNull(layoutInflaterFrom, "LayoutInflater.from(baseContext)");
                DesignContextWrapper designContextWrapper = this.this$0;
                return new DesignLayoutInflater(layoutInflaterFrom, designContextWrapper, designContextWrapper.designManager, false);
            }
        });
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String name) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        return (name.hashCode() == 1563956416 && name.equals("layout_inflater")) ? getDesignLayoutInflater() : super.getSystemService(name);
    }
}
