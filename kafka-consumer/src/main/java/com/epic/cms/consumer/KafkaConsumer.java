package com.epic.cms.consumer;

import com.epic.cms.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class KafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    List<String> messages = new ArrayList<>();

    @GetMapping("/consumeStringMessage")
    public List<String> consumeMsg() {
        return messages;
    }

    @KafkaListener(topics = "userMessage", groupId = "group_id_1")
    public List<String> getMsgFromTopic(String data) {
        messages.add(data);
        System.out.println("message = " + data);
        return messages;
    }

    @KafkaListener(topics = "user", groupId = "group_id_2",containerFactory = "userListener")
    public List<String> getMsgFromUser (User user) {
        System.out.println("message from user= " + user);
        messages.add(user.getFirstname());
        messages.add(user.getLastname());
        messages.add(user.getAddress());
        return messages;
    }



}
