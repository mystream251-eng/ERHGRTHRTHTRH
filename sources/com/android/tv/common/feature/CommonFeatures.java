package com.android.tv.common.feature;

/* JADX INFO: loaded from: classes.dex */
public class CommonFeatures {
    public static TestableFeature DVR = TestableFeature.createTestableFeature(FeatureUtils.OR(EngOnlyFeature.ENG_ONLY_FEATURE, Sdk.N_PRE_2_OR_HIGHER));
    public static Feature USE_SW_CODEC_FOR_SD = new PropertyFeature("use_sw_codec_for_sd", true);
}
