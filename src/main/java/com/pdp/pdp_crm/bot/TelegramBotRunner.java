package com.pdp.pdp_crm.bot;

import com.pdp.pdp_crm.bot.handler.Handler;
import com.pdp.pdp_crm.bot.service.TelegramUserService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TelegramBotRunner implements ApplicationRunner {

    private final TelegramUserService telegramUserService;
    private final TelegramBot bot;
    private final ExecutorService executor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
    );

    public TelegramBotRunner(TelegramUserService telegramUserService) {
        this.telegramUserService = telegramUserService;
        this.bot = new TelegramBot("7550055443:AAF24PObTTkk-QE7o1DLWN0DHp99Cx8lACY");
    }

    @Override
    public void run(ApplicationArguments args) {
        Handler handler = new Handler(telegramUserService, bot);

        bot.setUpdatesListener(updates -> {
            for (Update update : updates) {
                CompletableFuture.runAsync(() -> {
                    if (update.message() != null) {
                        handler.handle(update);
                    }
                }, executor);
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
