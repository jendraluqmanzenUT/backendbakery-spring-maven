package com.jendra.Zen_Roti.service;


import com.jendra.Zen_Roti.entity.Address;
import com.jendra.Zen_Roti.entity.User;
import com.jendra.Zen_Roti.exception.DuplicateEmailException;
import com.jendra.Zen_Roti.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    User save(User user) throws DuplicateEmailException;
    User findById(Long id) throws UserNotFoundException;

    void deleteById(Long id) throws UserNotFoundException;

    User update(User user) throws UserNotFoundException;
    List<User> findAll();
    User saveAddress(Address address, Long userId) throws Exception;

    List<User> findAllActiveUsers();

    List<User> findAllNoneActiveUsers();

    boolean existsByEmail(String email);
    boolean existsById(Long id);
}
