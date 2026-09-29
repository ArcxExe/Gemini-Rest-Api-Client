package org.arcx;

import org.arcx.gemini.service.GeminiService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Main{

    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
        GeminiService geminiService = context.getBean(GeminiService.class);
        System.out.println("Start request");
        String answer = geminiService.ask("Какая сегодня дата ?");
        System.out.println("Answer: " + answer);
    }
}
