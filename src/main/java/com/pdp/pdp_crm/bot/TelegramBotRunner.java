package com.pdp.pdp_crm.bot;

import com.pdp.pdp_crm.bot.handler.Handler;
import com.pdp.pdp_crm.bot.model.TelegramUser;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TelegramBotRunner {
    private static  TelegramUser telegramUser = new TelegramUser();
    public static final TelegramBot bot = new TelegramBot("6722286090:AAExc8OTIotVdZFtvZNe7KrnvU8ZD8fjfIU");
    public static final ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    public static void main(String[] args) {

        bot.setUpdatesListener(updates -> {
            for(Update update : updates){
                CompletableFuture.runAsync(()->{
                    if (update.message() != null){
                        Handler.handle(update);
                    }
                });
            }
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
