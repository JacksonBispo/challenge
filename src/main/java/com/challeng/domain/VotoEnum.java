package com.challeng.domain;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum VotoEnum {

    SIM(1),
    NAO(0);

    private Integer value;
    VotoEnum(Integer value) {
        this.value = value;
    }

    public static VotoEnum fromValue(Integer intValue) {

        return Arrays.stream(VotoEnum.values())
                .filter(situation -> situation.value.equals(intValue))
                .findFirst().orElse(null);
    }
}
