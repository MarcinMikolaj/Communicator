package project.simplechat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.security.Principal;

@Controller
public class ChatMessageController {

    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public ChatMessageController(SimpMessagingTemplate simpMessagingTemplate){
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/chat")
    //Instead, add Principal argument which is injected automatically to method you can use SimpMessageHeaderAccessor sha to get Principal object
    public void processMessageFromClient(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor sha, Principal principal, @Header("simpSessionId") String sessionId) throws Exception{
        System.out.println("to username, chatMessage.getName: " + chatMessage.getName() + " , sha.getUser().getName(): " + sha.getUser().getName());
            simpMessagingTemplate.convertAndSendToUser(chatMessage.getName(),"/queue/messages",chatMessage);
       // }
    }
}
