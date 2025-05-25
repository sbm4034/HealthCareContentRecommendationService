package org.healthcarecontent;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ApplicationStartup {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(ApplicationStartup.class, args);
        System.out.println("Healthcare Content Service is running...");
    }
}