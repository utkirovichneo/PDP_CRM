package com.pdp.pdp_crm.bot.model;


import com.pdp.pdp_crm.bot.enums.State;
import com.pdp.pdp_crm.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TelegramUser")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TelegramUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String fullName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private State state;
}
