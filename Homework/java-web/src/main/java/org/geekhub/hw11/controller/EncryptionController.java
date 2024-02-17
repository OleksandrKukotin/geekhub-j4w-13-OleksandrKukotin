package org.geekhub.hw11.controller;

import com.geekhub.hw15.encoding.EncodingAlgorithm;
import com.geekhub.hw15.encoding.EncodingOperation;
import org.geekhub.hw11.service.EncryptionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EncryptionController {

    private final EncryptionService service;

    public EncryptionController(EncryptionService service) {
        this.service = service;
    }

    @GetMapping("/encrypt")
    public String showEncryptPage() {
        return "encrypt";
    }

    @PostMapping("/encrypt/action")
    public String encryptMessage(@RequestParam("userId") long userId,
                                 @RequestParam("message") String message,
                                 @RequestParam("algorithm") String algorithm,
                                 @RequestParam("operation") String operation) {
        service.encrypt(userId, message, EncodingAlgorithm.fromValue(algorithm), EncodingOperation.fromValue(operation));
        return "history";
    }
}
