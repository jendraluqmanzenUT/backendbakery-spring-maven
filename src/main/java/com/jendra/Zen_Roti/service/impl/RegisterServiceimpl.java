package com.jendra.Zen_Roti.service;

import com.jendra.Zen_Roti.entity.User;
import com.jendra.Zen_Roti.exception.DuplicateEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {


    private final EmailService emailService;

    private  final UserService userService;
    private User user;

    public RegisterServiceImpl(EmailService emailService, UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
    }

    @Override
    public void sendCode() throws DuplicateEmailException {
        emailService.registrationEmail(user.getEmail(), user.getUsername());
    }

    @Override
    public boolean codesMatch(String providedCode) {
        return emailService.getCode().equals(providedCode);
    }

    @Override
    public User register(String providedCode) throws DuplicateEmailException {
        if(codesMatch(providedCode)){

            return userService.save(user);
        }
        return null;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }
}
