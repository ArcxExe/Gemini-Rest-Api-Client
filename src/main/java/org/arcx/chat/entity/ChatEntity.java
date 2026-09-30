package org.arcx.chat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "chat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "title" , length = 255)
    private String title;

    @Column(name = "created_at" , columnDefinition = "TIMESTAMP")
    private LocalTime created_at;
}
