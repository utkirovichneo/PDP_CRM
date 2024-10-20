package com.pdp.pdp_crm.bot.repository;

import com.pdp.pdp_crm.bot.model.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelegramUserRepo extends JpaRepository<TelegramUser, Long> {
    TelegramUser findByChatId(Long chatId);

}
