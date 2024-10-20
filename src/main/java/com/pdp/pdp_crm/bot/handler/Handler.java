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
        if (update == null || update.message() == null) {
            System.out.println("Xatolik: update yoki message null!");
            return;
        }

        Message message = update.message();
        if (message.chat() == null) {
            System.out.println("Xatolik: message.chat() null!");
            return;
        }

        Long chatId = message.chat().id();
        String text = message.text() != null ? message.text() : "";
        User user = message.from();

        System.out.println("Keldi: " + text + " | Chat ID: " + chatId);
        TelegramUser existingUser = telegramUserService.findByChatId(chatId).orElse(null);

        if ("/start".equals(text)) {
            String fullName = user.firstName() + (user.lastName() != null ? " " + user.lastName() : "");

            if (existingUser != null) {
                if (existingUser.getPhone() != null) {
                    Keyboard keyboard = createReplyKeyboard("Davomat");
                    sendResponse(chatId, "Salom " + fullName + ", Botga xush kelibsiz!", keyboard);

                } else {
                    promptForContact(chatId);
                }
            } else {
                promptForContact(chatId);
            }
        } else if ("Davomat".equals(text) && existingUser != null) {
            respondWithUserData(chatId);
        } else if (message.contact() != null) {
            handleContactShare(message, chatId, user);
        } else {
            sendResponse(chatId, "Iltimos, avval kontakt ma'lumotlaringizni ulashing.");
        }
    }


    private void handleContactShare(Message message, Long chatId, User user) {
        if (message.contact() == null) {
            System.out.println("Xatolik: Kontakt ma'lumotlari mavjud emas!");
            return;
        }

        String phoneNumber = message.contact().phoneNumber();
        String fullName = user.firstName() + (user.lastName() != null ? " " + user.lastName() : "");

        TelegramUser telegramUser = new TelegramUser(
                null, chatId, fullName, phoneNumber, State.ACTIVE,
                LocalDateTime.now(), true, LocalDateTime.now()
        );
        telegramUserService.saveUser(telegramUser);

        Keyboard keyboard = createReplyKeyboard("Davomat");
        sendResponse(chatId, "Salom " + fullName + ", Botga xush kelibsiz!", keyboard);
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
        if (chatId == null || messageText == null) {
            System.out.println("Xatolik: chatId yoki messageText null!");
            return;
        }

        SendMessage sendMessage = new SendMessage(chatId, messageText);
        if (keyboard != null) {
            sendMessage.replyMarkup(keyboard);
        }
        executeSendMessage(sendMessage);
    }

    private void sendResponse(Long chatId, String messageText) {
        sendResponse(chatId, messageText, null);
    }

    private void executeSendMessage(SendMessage sendMessage) {
        try {
            if (sendMessage == null) {
                System.out.println("Xatolik: SendMessage obyekti null!");
                return;
            }
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
