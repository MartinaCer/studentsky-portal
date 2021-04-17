package com.example.dbmodel;

public enum GlobalLinkType {
    ELEARNING(1),
    UNIVERSITY(2),
    FACULTY(3),
    OTHERLINK(4);

    private final int value;
    private static final GlobalLinkType[] VALUES = values();

    private GlobalLinkType(int type) {
        this.value = type;
    }

    public static GlobalLinkType valueOf(int type) {
        for (GlobalLinkType globalLinkType : VALUES) {
            if (globalLinkType.value == type) {
                return globalLinkType;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }
}
