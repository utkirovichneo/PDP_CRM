package com.pdp.pdp_crm.bot.service;

import com.pdp.pdp_crm.bot.model.TelegramUser;
import com.pdp.pdp_crm.bot.repository.TelegramUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TelegramUserService {

    private final TelegramUserRepo telegramUserRepo;



    @Autowired
    public TelegramUserService(TelegramUserRepo telegramUserRepo) {
        this.telegramUserRepo = telegramUserRepo;
    }

    public void saveUser(TelegramUser telegramUser) {
        telegramUser.setCreatedAt(LocalDateTime.now());
        telegramUser.setUpdatedAt(LocalDateTime.now());
        telegramUser.setIsActive(true);
        telegramUserRepo.save(telegramUser);
    }

    public Optional<TelegramUser> findByChatId(Long chatId) {
        return Optional.ofNullable(telegramUserRepo.findByChatId(chatId));
    }

    public List<TelegramUser> findAllUsers() {
        return telegramUserRepo.findAll();
    }

    public void deleteUser(Long userId) {
        telegramUserRepo.deleteById(userId);
    }
}
