package org.arcx.chat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id" , nullable = false)
    private ChatEntity chat;

    @Column(nullable = false, length = 50)
    private String role; // User , Model

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "model_version", length = 100)
    private String modelVersion;

    @Column(name = "response_id", length = 100)
    private String responseId;

    @Column(name = "prompt_token")
    private Integer promptToken ;

    @Column(name = "total_tokens")
    private Integer totalTokens ;

    @Column(name = "created_at" , nullable = false , updatable = false)
    private Instant createdAy = Instant.now();

}
