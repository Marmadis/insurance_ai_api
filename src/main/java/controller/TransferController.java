package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/main")
public class TransferController {


    private final String apiUrl = "https://generativelanguage.googleapis.com/v1beta2/models/chat-bison-001:generateMessage?key=$apiKey";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/sendMessage")
    public ResponseEntity<String> sendMessageAndGetResponse(@RequestBody String message) {
        String instructionMessage = "The next message will be on the issue of life insurance. YOU WILL HAVE TO GIVE " +
                "TWO ANSWERS TO THE FOLLOWING MESSAGE. THE FIRST MESSAGE IS SIMPLY A NUMBER (PROBABILITY OF INSURANCE COVERAGE FROM 0 TO 100). " +
                "THE SECOND MESSAGE IS AN ALREADY DETAILED RESPONSE TO THE USER. AT THE END BE SURE TO WRITE: " +
                "THANK YOU FOR USING THE Halyk Life VIRTUAL ASSISTANT";

        ResponseEntity<String> apiResponse = restTemplate.postForEntity(apiUrl, instructionMessage, String.class);
        apiResponse = restTemplate.postForEntity(apiUrl, message, String.class);
        String score = apiResponse.getBody();
        giveScore(score);
        String answer = apiResponse.getBody();
        return ResponseEntity.ok(answer);
    }

    public ResponseEntity<String> giveScore(String score) {
        return ResponseEntity.ok(score);
    }
}
