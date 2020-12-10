package org.hs.os;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication()
@EnableScheduling
public class SentimentanalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentimentanalysisApplication.class, args);
        System.out.print("Dalli stinkt");
    }

}
