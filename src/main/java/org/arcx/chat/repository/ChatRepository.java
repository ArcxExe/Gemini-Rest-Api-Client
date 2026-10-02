package org.arcx.chat.repository;

import org.arcx.chat.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRepository extends JpaRepository<ChatEntity, UUID> {


}