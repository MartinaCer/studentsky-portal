package com.example.dbmodel;

public enum ContactType {
    EMAIL(1),
    PHONENUMBER(2),
    BANKNUMBER(3);

    private final int value;
    private static final ContactType[] VALUES = values();

    private ContactType(int type) {
        this.value = type;
    }

    public static ContactType valueOf(int type) {
        for (ContactType contactType : VALUES) {
            if (contactType.value == type) {
                return contactType;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

}
