package com.challeng.configuration.swagger;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FeatureToogleConfig {

    @Value("${checkingCpf.feature.toogle}")
    private Boolean checkingCpf;


}
