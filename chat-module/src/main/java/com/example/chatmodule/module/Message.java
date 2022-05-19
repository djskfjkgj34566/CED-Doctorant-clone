package com.example.chatmodule.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Message {

    public String sender;
    public String content;
    public String timestamp;
}
