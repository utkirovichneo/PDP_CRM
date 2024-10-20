package com.pdp.pdp_crm.bot.service;

import com.pdp.pdp_crm.bot.model.TelegramUser;
import com.pdp.pdp_crm.bot.repository.TelegramUserRepo;
import com.pdp.pdp_crm.entity.User;
import com.pdp.pdp_crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TelegramUserService {

    private final TelegramUserRepo telegramUserRepo;
    private final UserRepository userRepository;


    @Autowired
    public TelegramUserService(TelegramUserRepo telegramUserRepo, UserRepository userRepository) {
        this.telegramUserRepo = telegramUserRepo;
        this.userRepository = userRepository;
    }

    public void saveUser(TelegramUser telegramUser) {
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
