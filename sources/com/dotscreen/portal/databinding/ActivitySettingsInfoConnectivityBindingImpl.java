package com.dotscreen.portal.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import androidx.lifecycle.MutableLiveData;
import com.dotscreen.megacable.portal.R;
import com.dotscreen.portal.components.NotificationView;
import com.dotscreen.portal.ui.screens.setup.connectivities.ConnectivityInfoViewModel;
import com.dotscreen.tv.ui.views.TimeView;

/* JADX INFO: loaded from: classes.dex */
public class ActivitySettingsInfoConnectivityBindingImpl extends ActivitySettingsInfoConnectivityBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.title, 13);
        sViewsWithIds.put(R.id.notification_icon, 14);
        sViewsWithIds.put(R.id.time, 15);
    }

    public ActivitySettingsInfoConnectivityBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 16, sIncludes, sViewsWithIds));
    }

    private ActivitySettingsInfoConnectivityBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 12, (NotificationView) objArr[14], (TimeView) objArr[15], (TextView) objArr[13], (TextView) objArr[5], (TextView) objArr[6], (TextView) objArr[7], (TextView) objArr[8], (TextView) objArr[9], (TextView) objArr[10], (TextView) objArr[11], (TextView) objArr[12], (TextView) objArr[4], (TextView) objArr[1], (TextView) objArr[3], (TextView) objArr[2]);
        this.mDirtyFlags = -1L;
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag(null);
        this.value1Status.setTag(null);
        this.value2Status.setTag(null);
        this.value3Status.setTag(null);
        this.value4Status.setTag(null);
        this.value5Status.setTag(null);
        this.value6Status.setTag(null);
        this.value7Status.setTag(null);
        this.value8Status.setTag(null);
        this.valueCompassStatus.setTag(null);
        this.valueConnectivityType.setTag(null);
        this.valueRightvStatus.setTag(null);
        this.valueUiopStatus.setTag(null);
        setRootTag(view);
        invalidateAll();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_URI;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.mDirtyFlags != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, Object obj) {
        if (1 != i) {
            return false;
        }
        setViewModel((ConnectivityInfoViewModel) obj);
        return true;
    }

    @Override // com.dotscreen.portal.databinding.ActivitySettingsInfoConnectivityBinding
    public void setViewModel(ConnectivityInfoViewModel connectivityInfoViewModel) {
        this.mViewModel = connectivityInfoViewModel;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    protected boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewModelTimeAndroidServerStatusLiveData((MutableLiveData) obj, i2);
            case 1:
                return onChangeViewModelUiopStatusLiveData((MutableLiveData) obj, i2);
            case 2:
                return onChangeViewModelConnectivityCheckServerLiveData((MutableLiveData) obj, i2);
            case 3:
                return onChangeViewModelGoogleICMPLiveData((MutableLiveData) obj, i2);
            case 4:
                return onChangeViewModelCompassStatusLiveData((MutableLiveData) obj, i2);
            case 5:
                return onChangeViewModelConnectivityCheckDateLiveData((MutableLiveData) obj, i2);
            case 6:
                return onChangeViewModelConnectivityStatusLiveData((MutableLiveData) obj, i2);
            case 7:
                return onChangeViewModelHasInternetLiveData((MutableLiveData) obj, i2);
            case 8:
                return onChangeViewModelIsValidatedConnectionLiveData((MutableLiveData) obj, i2);
            case 9:
                return onChangeViewModelHttpsGoogleServerLiveData((MutableLiveData) obj, i2);
            case 10:
                return onChangeViewModelRightVStatusLiveData((MutableLiveData) obj, i2);
            case 11:
                return onChangeViewModelXviewplusServerLiveData((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeViewModelTimeAndroidServerStatusLiveData(MutableLiveData<String> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewModelUiopStatusLiveData(MutableLiveData<String> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeViewModelConnectivityCheckServerLiveData(MutableLiveData<String> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    private boolean onChangeViewModelGoogleICMPLiveData(MutableLiveData<String> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        return true;
    }

    private boolean onChangeViewModelCompassStatusLiveData(MutableLiveData<String> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        return true;
    }

    private boolean onChangeViewModelConnectivityCheckDateLiveData(MutableLiveData<String> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        return true;
    }

    private boolean onChangeViewModelConnectivityStatusLiveData(MutableLiveData<String> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        return true;
    }

    private boolean onChangeViewModelHasInternetLiveData(MutableLiveData<String> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        return true;
    }

    private boolean onChangeViewModelIsValidatedConnectionLiveData(MutableLiveData<String> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        return true;
    }

    private boolean onChangeViewModelHttpsGoogleServerLiveData(MutableLiveData<String> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        return true;
    }

    private boolean onChangeViewModelRightVStatusLiveData(MutableLiveData<String> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        return true;
    }

    private boolean onChangeViewModelXviewplusServerLiveData(MutableLiveData<String> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:106:0x017a  */
    /* JADX WARN: Code duplicated, block: B:16:0x0043  */
    /* JADX WARN: Code duplicated, block: B:25:0x0061  */
    /* JADX WARN: Code duplicated, block: B:34:0x007f  */
    /* JADX WARN: Code duplicated, block: B:43:0x009d  */
    /* JADX WARN: Code duplicated, block: B:52:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:61:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:70:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:79:0x0117  */
    /* JADX WARN: Code duplicated, block: B:88:0x0138  */
    /* JADX WARN: Code duplicated, block: B:97:0x0157  */
    @Override // androidx.databinding.ViewDataBinding
    protected void executeBindings() {
        long j;
        String str;
        String value;
        String value2;
        String value3;
        String value4;
        String value5;
        String value6;
        String value7;
        String value8;
        String value9;
        String value10;
        String value11;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0L;
        }
        ConnectivityInfoViewModel connectivityInfoViewModel = this.mViewModel;
        String value12 = null;
        if ((16383 & j) != 0) {
            if ((j & 12289) == 0) {
                value3 = null;
            } else {
                MutableLiveData<String> timeAndroidServerStatusLiveData = connectivityInfoViewModel != null ? connectivityInfoViewModel.getTimeAndroidServerStatusLiveData() : null;
                updateLiveDataRegistration(0, timeAndroidServerStatusLiveData);
                if (timeAndroidServerStatusLiveData != null) {
                    value3 = timeAndroidServerStatusLiveData.getValue();
                } else {
                    value3 = null;
                }
            }
            if ((j & 12290) == 0) {
                value4 = null;
            } else {
                MutableLiveData<String> uiopStatusLiveData = connectivityInfoViewModel != null ? connectivityInfoViewModel.getUiopStatusLiveData() : null;
                updateLiveDataRegistration(1, uiopStatusLiveData);
                if (uiopStatusLiveData != null) {
                    value4 = uiopStatusLiveData.getValue();
                } else {
                    value4 = null;
                }
            }
            if ((j & 12292) == 0) {
                value5 = null;
            } else {
                MutableLiveData<String> connectivityCheckServerLiveData = connectivityInfoViewModel != null ? connectivityInfoViewModel.getConnectivityCheckServerLiveData() : null;
                updateLiveDataRegistration(2, connectivityCheckServerLiveData);
                if (connectivityCheckServerLiveData != null) {
                    value5 = connectivityCheckServerLiveData.getValue();
                } else {
                    value5 = null;
                }
            }
            if ((j & 12296) == 0) {
                value6 = null;
            } else {
                MutableLiveData<String> googleICMPLiveData = connectivityInfoViewModel != null ? connectivityInfoViewModel.getGoogleICMPLiveData() : null;
                updateLiveDataRegistration(3, googleICMPLiveData);
                if (googleICMPLiveData != null) {
                    value6 = googleICMPLiveData.getValue();
                } else {
                    value6 = null;
                }
            }
            if ((j & 12304) == 0) {
                value7 = null;
            } else {
                MutableLiveData<String> compassStatusLiveData = connectivityInfoViewModel != null ? connectivityInfoViewModel.getCompassStatusLiveData() : null;
                updateLiveDataRegistration(4, compassStatusLiveData);
                if (compassStatusLiveData != null) {
                    value7 = compassStatusLiveData.getValue();
                } else {
                    value7 = null;
                }
            }
            if ((j & 12320) == 0) {
                value10 = null;
            } else {
                MutableLiveData<String> connectivityCheckDateLiveData = connectivityInfoViewModel != null ? connectivityInfoViewModel.getConnectivityCheckDateLiveData() : null;
                updateLiveDataRegistration(5, connectivityCheckDateLiveData);
                if (connectivityCheckDateLiveData != null) {
                    value10 = connectivityCheckDateLiveData.getValue();
                } else {
                    value10 = null;
                }
            }
            if ((j & 12352) == 0) {
                value8 = null;
            } else {
                MutableLiveData<String> connectivityStatusLiveData = connectivityInfoViewModel != null ? connectivityInfoViewModel.getConnectivityStatusLiveData() : null;
                updateLiveDataRegistration(6, connectivityStatusLiveData);
                if (connectivityStatusLiveData != null) {
                    value8 = connectivityStatusLiveData.getValue();
                } else {
                    value8 = null;
                }
            }
            if ((j & 12416) == 0) {
                value9 = null;
            } else {
                MutableLiveData<String> hasInternetLiveData = connectivityInfoViewModel != null ? connectivityInfoViewModel.getHasInternetLiveData() : null;
                updateLiveDataRegistration(7, hasInternetLiveData);
                if (hasInternetLiveData != null) {
                    value9 = hasInternetLiveData.getValue();
                } else {
                    value9 = null;
                }
            }
            if ((j & 12544) == 0) {
                value11 = null;
            } else {
                MutableLiveData<String> mutableLiveDataIsValidatedConnectionLiveData = connectivityInfoViewModel != null ? connectivityInfoViewModel.isValidatedConnectionLiveData() : null;
                updateLiveDataRegistration(8, mutableLiveDataIsValidatedConnectionLiveData);
                if (mutableLiveDataIsValidatedConnectionLiveData != null) {
                    value11 = mutableLiveDataIsValidatedConnectionLiveData.getValue();
                } else {
                    value11 = null;
                }
            }
            if ((j & 12800) == 0) {
                value = null;
            } else {
                MutableLiveData<String> httpsGoogleServerLiveData = connectivityInfoViewModel != null ? connectivityInfoViewModel.getHttpsGoogleServerLiveData() : null;
                updateLiveDataRegistration(9, httpsGoogleServerLiveData);
                if (httpsGoogleServerLiveData != null) {
                    value = httpsGoogleServerLiveData.getValue();
                } else {
                    value = null;
                }
            }
            if ((j & 13312) == 0) {
                value2 = null;
            } else {
                MutableLiveData<String> rightVStatusLiveData = connectivityInfoViewModel != null ? connectivityInfoViewModel.getRightVStatusLiveData() : null;
                updateLiveDataRegistration(10, rightVStatusLiveData);
                if (rightVStatusLiveData != null) {
                    value2 = rightVStatusLiveData.getValue();
                } else {
                    value2 = null;
                }
            }
            if ((j & 14336) != 0) {
                MutableLiveData<String> xviewplusServerLiveData = connectivityInfoViewModel != null ? connectivityInfoViewModel.getXviewplusServerLiveData() : null;
                updateLiveDataRegistration(11, xviewplusServerLiveData);
                if (xviewplusServerLiveData != null) {
                    value12 = xviewplusServerLiveData.getValue();
                }
            }
            str = value12;
        } else {
            str = null;
            value = null;
            value2 = null;
            value3 = null;
            value4 = null;
            value5 = null;
            value6 = null;
            value7 = null;
            value8 = null;
            value9 = null;
            value10 = null;
            value11 = null;
        }
        if ((j & 12289) != 0) {
            TextViewBindingAdapter.setText(this.value1Status, value3);
        }
        if ((j & 12296) != 0) {
            TextViewBindingAdapter.setText(this.value2Status, value6);
        }
        if ((j & 12292) != 0) {
            TextViewBindingAdapter.setText(this.value3Status, value5);
        }
        if ((j & 14336) != 0) {
            TextViewBindingAdapter.setText(this.value4Status, str);
        }
        if ((j & 12800) != 0) {
            TextViewBindingAdapter.setText(this.value5Status, value);
        }
        if ((12320 & j) != 0) {
            TextViewBindingAdapter.setText(this.value6Status, value10);
        }
        if ((12416 & j) != 0) {
            TextViewBindingAdapter.setText(this.value7Status, value9);
        }
        if ((12544 & j) != 0) {
            TextViewBindingAdapter.setText(this.value8Status, value11);
        }
        if ((j & 12304) != 0) {
            TextViewBindingAdapter.setText(this.valueCompassStatus, value7);
        }
        if ((12352 & j) != 0) {
            TextViewBindingAdapter.setText(this.valueConnectivityType, value8);
        }
        if ((13312 & j) != 0) {
            TextViewBindingAdapter.setText(this.valueRightvStatus, value2);
        }
        if ((j & 12290) != 0) {
            TextViewBindingAdapter.setText(this.valueUiopStatus, value4);
        }
    }
}
