package com.universal.utils;

/**
 * @author pankaj
 */
public enum UniqueIdGenerators {
    ALPHANUMERIC_WITH_TIME_MILLI(UniqueIdGenerator.UniqueIdGroup.DATETIMEMILLI,
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789"),
    ALPHANUMERIC_WITH_TIME(UniqueIdGenerator.UniqueIdGroup.DATETIME,
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789"),
    ALPHANUMERIC_WITH_DATE(UniqueIdGenerator.UniqueIdGroup.DATE,
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789"),
    ALPHANUMERIC(UniqueIdGenerator.UniqueIdGroup.NONE,
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789"),
    ALPHABETS_WITH_TIME(UniqueIdGenerator.UniqueIdGroup.DATETIME,
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
    ALPHABETS_WITH_DATE(UniqueIdGenerator.UniqueIdGroup.DATE,
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ"), ALPHABETS(UniqueIdGenerator.UniqueIdGroup.NONE,
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ"), NUMERIC_WITH_TIME(
            UniqueIdGenerator.UniqueIdGroup.DATETIME,
            "123456789"), NUMERIC_WITH_DATE(UniqueIdGenerator.UniqueIdGroup.DATE,
            "123456789"), NUMERIC(UniqueIdGenerator.UniqueIdGroup.NONE,
            "123456789"), UUID(
            UniqueIdGenerator.UniqueIdGroup.UUID_GEN,
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789");

    private UniqueIdGenerator uniqueIdGenerator;

    UniqueIdGenerators(UniqueIdGenerator.UniqueIdGroup uniqueIdGroup, String allowedChars) {
        uniqueIdGenerator = new UniqueIdGenerator();
        uniqueIdGenerator.setUniqueIdGroup(uniqueIdGroup);
        uniqueIdGenerator.setAllowedChars(allowedChars);
    }

    public String generate(String prefix, int length) throws IllegalArgumentException {
        return uniqueIdGenerator.generate(prefix, length);
    }

    public String generate(int length) throws IllegalArgumentException {
        return uniqueIdGenerator.generate(length);
    }
}
