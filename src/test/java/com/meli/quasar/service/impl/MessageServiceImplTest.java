package com.meli.quasar.service.impl;

import com.meli.quasar.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageServiceImplTest {

    @Autowired
    private MessageService messageService;

    @Test
    void getMessage() {
        List<List<String>> messageList=new ArrayList<>();
        List<String> messageOne=new ArrayList<>();
        messageOne.add("este");
        messageOne.add("");
        messageOne.add("");
        messageOne.add("mensaje");
        messageOne.add("");
        List<String> messageTwo=new ArrayList<>();
        messageTwo.add("");
        messageTwo.add("es");
        messageTwo.add("");
        messageTwo.add("");
        messageTwo.add("secreto");
        List<String> messageThree=new ArrayList<>();
        messageThree.add("este");
        messageThree.add("");
        messageThree.add("un");
        messageThree.add("");
        messageThree.add("");
        messageList.add(messageOne);
        messageList.add(messageTwo);
        messageList.add(messageThree);
        assertEquals("este es un mensaje secreto", messageService.getMessage(messageList));
    }
}