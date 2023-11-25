package com.challeng;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootTest
@EnableFeignClients
@EnableKafka
class ChallengeApplicationTests {

    @Test
    void contextLoads() {
    }

}
