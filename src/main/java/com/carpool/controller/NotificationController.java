package com.carpool.controller;

import com.carpool.domain.Message;
import com.carpool.domain.OutputMessage;
import com.carpool.domain.Posts;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Crawlers on 4/26/2017.
 */
@Controller
public class NotificationController {
    @MessageMapping("/newpost")
    @SendTo("/topic/greetings")
    public Posts greeting(Posts posts) throws Exception {
        Thread.sleep(1000); // simulated delay
        System.out.println("Post:"+posts);
        return posts;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getFrom(), message.getText(), time);
    }

    @GetMapping("/socket")
    public ModelAndView socket(){
        return new ModelAndView("socket");
    }

}
