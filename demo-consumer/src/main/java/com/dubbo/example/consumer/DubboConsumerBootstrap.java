package com.dubbo.example.consumer;

import com.dubbo.example.DemoService;

import org.apache.dubbo.config.annotation.Reference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
public class DubboConsumerBootstrap {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Reference(version = "1.0.0")
    private DemoService demoService;

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerBootstrap.class).close();
    }

    @Bean
    public ApplicationRunner runner() {
        final String result = "";
        int n = 1000;
        do {
            n--;
            String result2 = demoService.sayHello("wangzhenli"+n);
            logger.info(result2);
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while(n>0);

        return args -> logger.info(result);
    }
}
