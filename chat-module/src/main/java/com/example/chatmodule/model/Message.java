package com.example.chatmodule.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    @ManyToOne
    private ChatRoom chatRoom;
    private Timestamp dateEnvoie;
    private String fromUser;
    private String toUser;
    private String text;

}
