INSERT INTO ROLES (id, name)
    VALUES
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_TEACHER'),
    (3, 'ROLE_STUDENT'),
    (4, 'ROLE_MANAGER');

ALTER TABLE telegram_user
    DROP CONSTRAINT telegram_user_state_check;

ALTER TABLE telegram_user
    ADD CONSTRAINT telegram_user_state_check
        CHECK (state IN ('ACTIVE', 'INACTIVE', 'BLOCKED', 'NEW_STATE'));