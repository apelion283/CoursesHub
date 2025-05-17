package com.courseshubbackend.pojos.course;

import java.util.Arrays;

public enum CourseStatusEnum {
    
        ACTIVE(0),
        INACTIVE(1),
        DELETED(2);

        private final int value;
        CourseStatusEnum(int value) { this.value = value; }

    public int getValue() { return value; }

    public static CourseStatusEnum fromValue(int value) {
        return Arrays.stream(values())
                .filter(cs -> cs.getValue() == value)
                .findFirst()
                .orElse(ACTIVE);
    }

}
