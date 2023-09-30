/**
 * Author : lahiru_p
 * Date : 2/25/2023
 * Time : 10:21 PM
 * Project Name : kafka-publisher
 */

package com.epic.cms.controller;

import com.epic.cms.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KafkaPublisherController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("userForm", new User());
        return "index";
    }
}
