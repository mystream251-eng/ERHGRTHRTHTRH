package com.dotscreen.bokit.type;

/* JADX INFO: loaded from: classes.dex */
public enum CrewDepartment {
    PRODUCERS("PRODUCERS"),
    DIRECTORS("DIRECTORS"),
    CINEMATOGRAPHERS("CINEMATOGRAPHERS"),
    EDITORS("EDITORS"),
    WRITERS("WRITERS"),
    COMPOSERS("COMPOSERS"),
    CASTING("CASTING"),
    ART_DIRECTOR("ART_DIRECTOR"),
    MUSIC_DEPARTMENT("MUSIC_DEPARTMENT"),
    SET_DECORATORS("SET_DECORATORS"),
    SOUND_DEPARTMENT("SOUND_DEPARTMENT"),
    MAKEUP_DEPARTMENT("MAKEUP_DEPARTMENT"),
    COSTUMES_DEPARTMENT("COSTUMES_DEPARTMENT"),
    ANIMATION_DEPARTMENT("ANIMATION_DEPARTMENT"),
    VISUAL_EFFECTS("VISUAL_EFFECTS"),
    SPECIAL_EFFECTS("SPECIAL_EFFECTS"),
    ART_DEPARTMENT("ART_DEPARTMENT"),
    ASSISTANT_DIRECTOR("ASSISTANT_DIRECTOR"),
    CAMERA_DEPARTMENT("CAMERA_DEPARTMENT"),
    EDITORIAL_DEPARTMENT("EDITORIAL_DEPARTMENT"),
    LOCATION_MANAGEMENT("LOCATION_MANAGEMENT"),
    PRODUCTION_DESIGNERS("PRODUCTION_DESIGNERS"),
    PRODUCTION_MANAGERS("PRODUCTION_MANAGERS"),
    STUNTS("STUNTS"),
    TRANSPORTATION_DEPARTMENT("TRANSPORTATION_DEPARTMENT"),
    THANKS("THANKS"),
    MISCELLANEOUS("MISCELLANEOUS"),
    $UNKNOWN("$UNKNOWN");

    private final String rawValue;

    CrewDepartment(String str) {
        this.rawValue = str;
    }

    public String rawValue() {
        return this.rawValue;
    }

    public static CrewDepartment safeValueOf(String str) {
        for (CrewDepartment crewDepartment : values()) {
            if (crewDepartment.rawValue.equals(str)) {
                return crewDepartment;
            }
        }
        return $UNKNOWN;
    }
}
