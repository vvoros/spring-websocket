package websocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import websocket.dto.Greeting;
import websocket.dto.HelloMessage;

@Controller
public class GreetingController {

  @Autowired
  private SimpMessagingTemplate template;

  @MessageMapping("/simplehello")
  @SendTo("/topic/greetings")
  public Greeting simpleGreeting(HelloMessage hello) throws Exception {
    return new Greeting("Hello, " + hello.getName() + "!");
  }

  @MessageMapping("/hello")
  public void greetingWithMessage(HelloMessage hello) throws Exception {
    String[] messages = {"message1", "message2", "message3", "message4", "message5"};

    for(int i = 0; i < messages.length; i++){
      Thread.sleep(500); // simulated delay
      this.template.convertAndSend("/topic/greetings", new Greeting("Your message " + hello.getName() + ": " + messages[i]));
    }
  }

}
