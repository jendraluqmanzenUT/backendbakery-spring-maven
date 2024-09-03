package com.jendra.Zen_Roti.controller;

import com.jendra.Zen_Roti.entity.User;
import com.jendra.Zen_Roti.exception.DuplicateEmailException;
import com.jendra.Zen_Roti.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> sendEmail(@RequestBody User user) throws DuplicateEmailException {
        registerService.setUser(user);
        registerService.sendCode();
        return new ResponseEntity<>("Email successfully sent to "+user.getEmail(), HttpStatus.CREATED);
    }

    @PostMapping("/confirmRegistration/{providedCode}")
    public ResponseEntity<User> register(@PathVariable String providedCode) throws DuplicateEmailException {
        return new ResponseEntity<>(registerService.register(providedCode), HttpStatus.CREATED);
    }


}
