package com.example.chatmodule.controller;

import com.example.chatmodule.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatRoomController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/send.message")
    public void sendPublicMessage(Message instantMessage) {
        simpMessagingTemplate.convertAndSend
                ("/topic/public.messages", instantMessage);
    }
}
