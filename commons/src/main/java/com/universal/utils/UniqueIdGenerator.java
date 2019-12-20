package com.universal.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;

/**
 * @author pankaj
 */
public class UniqueIdGenerator {

    private final Random random = new Random();

    public enum UniqueIdGroup {
        DATE {
            @Override
            public String getValue() {
                Timestamp now = new Timestamp(System.currentTimeMillis());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
                return simpleDateFormat.format(now);
            }
        },
        DATETIME {
            @Override
            public String getValue() {
                Timestamp now = new Timestamp(System.currentTimeMillis());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmss");
                return simpleDateFormat.format(now);
            }
        },
        DATETIMEMILLI {
            @Override
            public String getValue() {
                Timestamp now = new Timestamp(System.currentTimeMillis());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddHHmmssSSS");
                return simpleDateFormat.format(now);
            }
        },
        NONE {
            @Override
            public String getValue() {
                return "";
            }
        },

        UUID_GEN {

            @Override
            public String getValue() {
                return UUID.randomUUID().toString().replaceAll("-", "");
            }

        };
        public abstract String getValue();
    }

    private UniqueIdGroup uniqueIdGroup = UniqueIdGroup.DATETIME;
    private String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";

    /**
     * Get the uniqueIdGroup.
     *
     * @return the uniqueIdGroup
     */
    public UniqueIdGroup getUniqueIdGroup() {
        return uniqueIdGroup;
    }

    /**
     * Set the uniqueIdGroup.
     *
     * @param uniqueIdGroup
     *            the uniqueIdGroup to set
     */
    public void setUniqueIdGroup(UniqueIdGroup uniqueIdGroup) {
        this.uniqueIdGroup = uniqueIdGroup;
    }

    /**
     * Get the allowedChars.
     *
     * @return the allowedChars
     */
    public String getAllowedChars() {
        return allowedChars;
    }

    /**
     * Set the allowedChars.
     *
     * @param allowedChars
     *            the allowedChars to set
     */
    public void setAllowedChars(String allowedChars) {
        this.allowedChars = allowedChars;
    }

    /**
     * Generate a random string
     *
     * @return generated random string
     * @throws IllegalArgumentException
     */
    public String generate(int length) throws IllegalArgumentException, IllegalStateException {
        return generate("", length);
    }

    /**
     * Generate a random string starting from the prefix, and having 'length'
     * size
     *
     * @param prefix
     * @param length
     * @return generated random string
     * @throws IllegalArgumentException
     */
    public String generate(String prefix, int length) throws IllegalArgumentException, IllegalStateException {
        String base = uniqueIdGroup.getValue();

        StringBuilder builder = new StringBuilder(prefix);
        builder.append(base);

        if (length < builder.length() + 1) {
            throw new IllegalArgumentException("Cannot generate random string of length " + length
                    + " for GeneratorType: " + uniqueIdGroup.getValue());
        }

        appendRandomString(builder, length);
        String result = builder.substring(0, length).toUpperCase();
        return result;
    }

    /**
     * Populates builder with random characters selected from allowedChars
     * having max length
     *
     * @param builder
     * @param length
     */
    public void appendRandomString(StringBuilder builder, int length) throws IllegalStateException {
        if (allowedChars.length() == 0) {
            throw new IllegalStateException("AllowedChars cannot be empty string");
        }

        while (builder.length() < length) {
            int randomPosition = random.nextInt(allowedChars.length());
            builder.append(allowedChars.charAt(randomPosition));
        }
    }
}
