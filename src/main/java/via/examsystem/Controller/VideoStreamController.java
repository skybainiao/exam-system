package via.examsystem.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@CrossOrigin(
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH},
        allowCredentials = "true"
)
@Controller
public class VideoStreamController {

    private final SimpMessagingTemplate messagingTemplate;

    public VideoStreamController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/video-stream")
    @SendTo("/topic/video-streams")
    public void handleVideoStream(Map<String, Object> message) {
        System.out.println("Received video stream message: " + message);
        messagingTemplate.convertAndSend("/topic/video-streams", message);
    }
}