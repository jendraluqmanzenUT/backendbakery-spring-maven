package com.jendra.Zen_Roti.service;

import com.jendra.Zen_Roti.entity.User;
import com.jendra.Zen_Roti.exception.DuplicateEmailException;

public interface RegisterService {

    void sendCode() throws DuplicateEmailException;
    boolean codesMatch(String providedCode);
    User register(String providedCode) throws DuplicateEmailException;
    void setUser(User user);


}
