package websocket.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import websocket.dto.Greeting;

@Component
public class ScheduledUpdatesOnTopic {

  @Autowired
  private SimpMessagingTemplate template;

  @Scheduled(fixedDelay=2000)
  public void publishUpdates() {
    template.convertAndSend("/topic/greetings", new Greeting("Scheduled message"));
  }

}
