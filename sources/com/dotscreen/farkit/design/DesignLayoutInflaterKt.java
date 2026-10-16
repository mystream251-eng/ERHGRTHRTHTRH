package com.dotscreen.farkit.design;

import android.R;
import android.content.res.ColorStateList;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DesignLayoutInflater.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a&\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"DEBUG", "", "TAG", "", "kotlin.jvm.PlatformType", "applyAttributesToView", "Landroid/view/View;", "view", "designManager", "Lcom/dotscreen/farkit/design/DesignManager;", "attrs", "Landroid/util/AttributeSet;", "farkit_release"}, k = 2, mv = {1, 1, 16})
public final class DesignLayoutInflaterKt {
    private static final boolean DEBUG = false;
    private static final String TAG = DesignLayoutInflater.class.getSimpleName();

    /* JADX INFO: Access modifiers changed from: private */
    public static final View applyAttributesToView(View view, DesignManager designManager, AttributeSet attributeSet) {
        if (view != null && attributeSet != null) {
            int attributeCount = attributeSet.getAttributeCount();
            for (int i = 0; i < attributeCount; i++) {
                String attributeName = attributeSet.getAttributeName(i);
                if (attributeName != null) {
                    int iHashCode = attributeName.hashCode();
                    if (iHashCode != -1904798943) {
                        if (iHashCode != -1251200944) {
                            if (iHashCode == -148046674 && attributeName.equals("farDesignColor") && (view instanceof TextView)) {
                                String attributeValue = attributeSet.getAttributeValue(i);
                                Intrinsics.checkExpressionValueIsNotNull(attributeValue, "attrs.getAttributeValue(i)");
                                Integer color = designManager.getColor(attributeValue);
                                if (color != null) {
                                    ((TextView) view).setTextColor(color.intValue());
                                } else {
                                    Integer color2 = designManager.getColor(attributeSet.getAttributeValue(i) + ".default.textColor");
                                    Integer color3 = designManager.getColor(attributeSet.getAttributeValue(i) + ".selected.textColor");
                                    if (color2 != null && color3 != null) {
                                        ((TextView) view).setTextColor(new ColorStateList(new int[][]{new int[]{-16842908}, new int[]{R.attr.state_focused}}, new int[]{color2.intValue(), color3.intValue()}));
                                    }
                                }
                            }
                        } else if (attributeName.equals("farDesignTint") && (view instanceof ImageView)) {
                            String attributeValue2 = attributeSet.getAttributeValue(i);
                            Intrinsics.checkExpressionValueIsNotNull(attributeValue2, "attrs.getAttributeValue(i)");
                            Integer color4 = designManager.getColor(attributeValue2);
                            if (color4 != null) {
                                int iIntValue = color4.intValue();
                                if (Build.VERSION.SDK_INT >= 21) {
                                    ((ImageView) view).setImageTintList(ColorStateList.valueOf(iIntValue));
                                }
                            }
                        }
                    } else if (attributeName.equals("farDesignIndeterminateTint") && (view instanceof ProgressBar)) {
                        String attributeValue3 = attributeSet.getAttributeValue(i);
                        Intrinsics.checkExpressionValueIsNotNull(attributeValue3, "attrs.getAttributeValue(i)");
                        Integer color5 = designManager.getColor(attributeValue3);
                        if (color5 != null) {
                            int iIntValue2 = color5.intValue();
                            if (Build.VERSION.SDK_INT >= 21) {
                                ((ProgressBar) view).setIndeterminateTintList(ColorStateList.valueOf(iIntValue2));
                            }
                        }
                    }
                }
            }
        }
        return view;
    }
}
