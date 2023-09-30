package com.epic.cms.controller;

import com.epic.cms.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("producer")
public class KafkaPublisherRestController {
    private static final Logger logger = LoggerFactory.getLogger(KafkaPublisherRestController.class);
    @Autowired
    private KafkaTemplate<String, User> userKafkaTemplate;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate1;

    private static final String topic1 = "userMessage";
    private static final String topic2 = "user";

    @GetMapping("/publishType/{name}")
    public String publishMessage(@PathVariable String name) {

        kafkaTemplate.send(topic1, "Hello " + name );
        return "Message Published Successfully";
    }

    @PostMapping("/publishUser/save")
    public String postMessage(Model model,
                              @ModelAttribute("userForm") User userForm) {
        userKafkaTemplate.send(topic2, userForm);

        return "User Published Successfully";
    }
}
