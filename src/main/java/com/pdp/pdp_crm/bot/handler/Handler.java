package com.pdp.pdp_crm.bot.handler;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

import java.util.Objects;

import static com.pdp.pdp_crm.bot.TelegramBotRunner.bot;

public class Handler {
    public static void handle(Update update) {
        Message message = update.message();
        Long chatId = message.chat().id();
        String text = message.text();
        User user = message.from();
        if (Objects.equals(text, "/start")) {

            Keyboard keyboard = new ReplyKeyboardMarkup(
                    new String[]{"Davomat"}
            ).oneTimeKeyboard(true).resizeKeyboard(true);
            SendMessage sendMessage = new SendMessage(chatId, "Asosiy menyu" )
                    .replyMarkup(keyboard);
            bot.execute(sendMessage);
        }

    }
}
