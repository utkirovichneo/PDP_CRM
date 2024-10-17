package com.pdp.pdp_crm.bot.confic;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class BotConfig {
    @Bean
    public TelegramBot telegramBot() {
        return new TelegramBot("7550055443:AAF24PObTTkk-QE7o1DLWN0DHp99Cx8lACY");
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }
}
