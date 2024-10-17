package com.pdp.pdp_crm.bot.handler;

import com.pdp.pdp_crm.bot.enums.State;
import com.pdp.pdp_crm.bot.model.TelegramUser;
import com.pdp.pdp_crm.bot.service.TelegramUserService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class Handler {

    private final TelegramUserService telegramUserService;

    private final TelegramBot bot;

    @Autowired
    public Handler(TelegramUserService telegramUserService, TelegramBot bot) {
        this.telegramUserService = telegramUserService;
        this.bot = bot;
    }

    public void handle(Update update) {
        Message message = update.message();
        if (message == null) return;

        Long chatId = message.chat().id();
        String text = message.text();
        User user = message.from();

        if (message.contact() != null) {
            handleContactShare(message, chatId, user);
        } else if ("/start".equals(text)) {
            promptForContact(chatId);
        } else if ("Davomat".equals(text)) {
            respondWithUserData(chatId);
        }
    }

    private void handleContactShare(Message message, Long chatId, User user) {
        String phoneNumber = message.contact().phoneNumber();
        String fullName = user.firstName() + (user.lastName() != null ? " " + user.lastName() : "");

        TelegramUser telegramUser = new TelegramUser(null, chatId, fullName, phoneNumber, State.ACTIVE, LocalDateTime.now(), LocalDateTime.now(), true);
        telegramUserService.saveUser(telegramUser);

        Keyboard keyboard = createReplyKeyboard("Davomat");
        sendResponse(chatId, "Rahmat! Davomat tanlang.", keyboard);
    }

    private void promptForContact(Long chatId) {
        Keyboard keyboard = new ReplyKeyboardMarkup(
                new KeyboardButton("Share Contact").requestContact(true)
        ).oneTimeKeyboard(true).resizeKeyboard(true);

        sendResponse(chatId, "Iltimos, kontakt ma'lumotlaringizni ulashing.", keyboard);
    }

    private void respondWithUserData(Long chatId) {
        TelegramUser existingUser = telegramUserService.findByChatId(chatId).orElse(null);
        String response = (existingUser != null) ?
                "Sizning ma'lumotlaringiz: " + existingUser.getFullName() + ", " + existingUser.getPhone() :
                "Foydalanuvchi topilmadi";

        sendResponse(chatId, response);
    }

    private void sendResponse(Long chatId, String messageText, Keyboard keyboard) {
        SendMessage sendMessage = new SendMessage(chatId, messageText).replyMarkup(keyboard);
        executeSendMessage(sendMessage);
    }

    private void sendResponse(Long chatId, String messageText) {
        sendResponse(chatId, messageText, null);
    }

    private void executeSendMessage(SendMessage sendMessage) {
        try {
            bot.execute(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Keyboard createReplyKeyboard(String buttonText) {
        return new ReplyKeyboardMarkup(new KeyboardButton(buttonText))
                .oneTimeKeyboard(true)
                .resizeKeyboard(true);
    }
}
