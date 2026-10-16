package androidx.leanback.widget.picker;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import androidx.leanback.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class TimePicker extends Picker {
    private static final int AM_INDEX = 0;
    private static final int HOURS_IN_HALF_DAY = 12;
    private static final int PM_INDEX = 1;
    static final String TAG = "TimePicker";
    PickerColumn mAmPmColumn;
    int mColAmPmIndex;
    int mColHourIndex;
    int mColMinuteIndex;
    private final PickerUtility.TimeConstant mConstant;
    private int mCurrentAmPmIndex;
    private int mCurrentHour;
    private int mCurrentMinute;
    PickerColumn mHourColumn;
    private boolean mIs24hFormat;
    PickerColumn mMinuteColumn;
    private String mTimePickerFormat;

    public TimePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TimePicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mConstant = PickerUtility.getTimeConstantInstance(Locale.getDefault(), context.getResources());
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.lbTimePicker);
        this.mIs24hFormat = typedArrayObtainStyledAttributes.getBoolean(R.styleable.lbTimePicker_is24HourFormat, DateFormat.is24HourFormat(context));
        boolean z = typedArrayObtainStyledAttributes.getBoolean(R.styleable.lbTimePicker_useCurrentTime, true);
        updateColumns();
        updateColumnsRange();
        if (z) {
            Calendar calendarForLocale = PickerUtility.getCalendarForLocale(null, this.mConstant.locale);
            setHour(calendarForLocale.get(11));
            setMinute(calendarForLocale.get(12));
            setAmPmValue();
        }
    }

    private static boolean updateMin(PickerColumn pickerColumn, int i) {
        if (i == pickerColumn.getMinValue()) {
            return false;
        }
        pickerColumn.setMinValue(i);
        return true;
    }

    private static boolean updateMax(PickerColumn pickerColumn, int i) {
        if (i == pickerColumn.getMaxValue()) {
            return false;
        }
        pickerColumn.setMaxValue(i);
        return true;
    }

    String getBestHourMinutePattern() {
        String strReplace;
        if (PickerUtility.SUPPORTS_BEST_DATE_TIME_PATTERN) {
            strReplace = DateFormat.getBestDateTimePattern(this.mConstant.locale, this.mIs24hFormat ? "Hma" : "hma");
        } else {
            java.text.DateFormat timeInstance = SimpleDateFormat.getTimeInstance(3, this.mConstant.locale);
            if (timeInstance instanceof SimpleDateFormat) {
                strReplace = ((SimpleDateFormat) timeInstance).toPattern().replace("s", "");
                if (this.mIs24hFormat) {
                    strReplace = strReplace.replace('h', 'H').replace("a", "");
                }
            } else {
                strReplace = this.mIs24hFormat ? "H:mma" : "h:mma";
            }
        }
        return TextUtils.isEmpty(strReplace) ? "h:mma" : strReplace;
    }

    List<CharSequence> extractSeparators() {
        String bestHourMinutePattern = getBestHourMinutePattern();
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        char[] cArr = {'H', 'h', 'K', 'k', 'm', 'M', 'a'};
        boolean z = false;
        char c = 0;
        for (int i = 0; i < bestHourMinutePattern.length(); i++) {
            char cCharAt = bestHourMinutePattern.charAt(i);
            if (cCharAt != ' ') {
                if (cCharAt != '\'') {
                    if (z || !isAnyOf(cCharAt, cArr)) {
                        sb.append(cCharAt);
                    } else if (cCharAt != c) {
                        arrayList.add(sb.toString());
                        sb.setLength(0);
                    }
                    c = cCharAt;
                } else if (z) {
                    z = false;
                } else {
                    sb.setLength(0);
                    z = true;
                }
            }
        }
        arrayList.add(sb.toString());
        return arrayList;
    }

    private static boolean isAnyOf(char c, char[] cArr) {
        for (char c2 : cArr) {
            if (c == c2) {
                return true;
            }
        }
        return false;
    }

    private String extractTimeFields() {
        StringBuilder sb;
        String bestHourMinutePattern = getBestHourMinutePattern();
        boolean z = TextUtils.getLayoutDirectionFromLocale(this.mConstant.locale) == 1;
        boolean z2 = bestHourMinutePattern.indexOf(97) < 0 || bestHourMinutePattern.indexOf("a") > bestHourMinutePattern.indexOf("m");
        String str = z ? "mh" : "hm";
        if (is24Hour()) {
            return str;
        }
        if (z2) {
            sb = new StringBuilder();
            sb.append(str);
            sb.append("a");
        } else {
            sb = new StringBuilder();
            sb.append("a");
            sb.append(str);
        }
        return sb.toString();
    }

    private void updateColumns() {
        String bestHourMinutePattern = getBestHourMinutePattern();
        if (TextUtils.equals(bestHourMinutePattern, this.mTimePickerFormat)) {
            return;
        }
        this.mTimePickerFormat = bestHourMinutePattern;
        String strExtractTimeFields = extractTimeFields();
        List<CharSequence> listExtractSeparators = extractSeparators();
        if (listExtractSeparators.size() != strExtractTimeFields.length() + 1) {
            throw new IllegalStateException("Separators size: " + listExtractSeparators.size() + " must equal the size of timeFieldsPattern: " + strExtractTimeFields.length() + " + 1");
        }
        setSeparators(listExtractSeparators);
        String upperCase = strExtractTimeFields.toUpperCase();
        this.mAmPmColumn = null;
        this.mMinuteColumn = null;
        this.mHourColumn = null;
        this.mColAmPmIndex = -1;
        this.mColMinuteIndex = -1;
        this.mColHourIndex = -1;
        ArrayList arrayList = new ArrayList(3);
        for (int i = 0; i < upperCase.length(); i++) {
            char cCharAt = upperCase.charAt(i);
            if (cCharAt == 'A') {
                PickerColumn pickerColumn = new PickerColumn();
                this.mAmPmColumn = pickerColumn;
                arrayList.add(pickerColumn);
                this.mAmPmColumn.setStaticLabels(this.mConstant.ampm);
                this.mColAmPmIndex = i;
                updateMin(this.mAmPmColumn, 0);
                updateMax(this.mAmPmColumn, 1);
            } else if (cCharAt == 'H') {
                PickerColumn pickerColumn2 = new PickerColumn();
                this.mHourColumn = pickerColumn2;
                arrayList.add(pickerColumn2);
                this.mHourColumn.setStaticLabels(this.mConstant.hours24);
                this.mColHourIndex = i;
            } else if (cCharAt == 'M') {
                PickerColumn pickerColumn3 = new PickerColumn();
                this.mMinuteColumn = pickerColumn3;
                arrayList.add(pickerColumn3);
                this.mMinuteColumn.setStaticLabels(this.mConstant.minutes);
                this.mColMinuteIndex = i;
            } else {
                throw new IllegalArgumentException("Invalid time picker format.");
            }
        }
        setColumns(arrayList);
    }

    private void updateColumnsRange() {
        updateMin(this.mHourColumn, !this.mIs24hFormat ? 1 : 0);
        updateMax(this.mHourColumn, this.mIs24hFormat ? 23 : 12);
        updateMin(this.mMinuteColumn, 0);
        updateMax(this.mMinuteColumn, 59);
        PickerColumn pickerColumn = this.mAmPmColumn;
        if (pickerColumn != null) {
            updateMin(pickerColumn, 0);
            updateMax(this.mAmPmColumn, 1);
        }
    }

    private void setAmPmValue() {
        if (is24Hour()) {
            return;
        }
        setColumnValue(this.mColAmPmIndex, this.mCurrentAmPmIndex, false);
    }

    public void setHour(int i) {
        if (i < 0 || i > 23) {
            throw new IllegalArgumentException("hour: " + i + " is not in [0-23] range in");
        }
        this.mCurrentHour = i;
        if (!is24Hour()) {
            int i2 = this.mCurrentHour;
            if (i2 >= 12) {
                this.mCurrentAmPmIndex = 1;
                if (i2 > 12) {
                    this.mCurrentHour = i2 - 12;
                }
            } else {
                this.mCurrentAmPmIndex = 0;
                if (i2 == 0) {
                    this.mCurrentHour = 12;
                }
            }
            setAmPmValue();
        }
        setColumnValue(this.mColHourIndex, this.mCurrentHour, false);
    }

    public int getHour() {
        if (this.mIs24hFormat) {
            return this.mCurrentHour;
        }
        if (this.mCurrentAmPmIndex == 0) {
            return this.mCurrentHour % 12;
        }
        return (this.mCurrentHour % 12) + 12;
    }

    public void setMinute(int i) {
        if (i < 0 || i > 59) {
            throw new IllegalArgumentException("minute: " + i + " is not in [0-59] range.");
        }
        this.mCurrentMinute = i;
        setColumnValue(this.mColMinuteIndex, i, false);
    }

    public int getMinute() {
        return this.mCurrentMinute;
    }

    public void setIs24Hour(boolean z) {
        if (this.mIs24hFormat == z) {
            return;
        }
        int hour = getHour();
        int minute = getMinute();
        this.mIs24hFormat = z;
        updateColumns();
        updateColumnsRange();
        setHour(hour);
        setMinute(minute);
        setAmPmValue();
    }

    public boolean is24Hour() {
        return this.mIs24hFormat;
    }

    public boolean isPm() {
        return this.mCurrentAmPmIndex == 1;
    }

    @Override // androidx.leanback.widget.picker.Picker
    public void onColumnValueChanged(int i, int i2) {
        if (i == this.mColHourIndex) {
            this.mCurrentHour = i2;
        } else if (i == this.mColMinuteIndex) {
            this.mCurrentMinute = i2;
        } else {
            if (i == this.mColAmPmIndex) {
                this.mCurrentAmPmIndex = i2;
                return;
            }
            throw new IllegalArgumentException("Invalid column index.");
        }
    }
}
