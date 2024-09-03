package com.jendra.Zen_Roti.service;

import com.jendra.Zen_Roti.entity.Payment;
import com.jendra.Zen_Roti.exception.DuplicateEmailException;
import jakarta.mail.MessagingException;

public interface EmailService {

    void registrationEmail(String userEmail,String username) throws DuplicateEmailException;
    void passwordRecoveryEmail(String userEmail,String username);
    void invoiceEmail(Payment payment) throws MessagingException;
    String getCode();

}
