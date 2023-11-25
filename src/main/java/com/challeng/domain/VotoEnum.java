package com.challeng.domain;

import lombok.Getter;
import org.apache.kafka.common.protocol.types.Field;

import java.util.Arrays;

@Getter
public enum VotoEnum {

    SIM("SIM"),
    NAO("NAO");

    private String value;
    VotoEnum(String value) {
        this.value = value;
    }

    public static VotoEnum fromValue(String intValue) {

        return Arrays.stream(VotoEnum.values())
                .filter(situation -> situation.value.equals(intValue))
                .findFirst().orElse(null);
    }
}
