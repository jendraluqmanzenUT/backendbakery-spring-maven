package com.jendra.Zen_Roti.service;

import com.jendra.Zen_Roti.entity.Account;
import com.jendra.Zen_Roti.entity.Status;
import com.jendra.Zen_Roti.exception.AccountNotFoundException;
import com.jendra.Zen_Roti.exception.InsufficientFundsException;
import com.jendra.Zen_Roti.exception.UserNotFoundException;

import java.util.List;

public interface AccountService {

    Account save(Account account);
    Account findById(Long id) throws AccountNotFoundException;
    Account findByUserId(Long userId) throws AccountNotFoundException, UserNotFoundException;

    List<Account> findAll();

    void changeAccountStatus(Account account, Status status);

    void deleteById(Long id) throws AccountNotFoundException;

    Account update(Account account) throws AccountNotFoundException;

    void fundAccount(Account account, double amount,Status status) throws AccountNotFoundException;

    // when a user decides to fund the account directly/ or save change
    void useFunds(Account account, double amount, Status status, int operation) throws Exception;

}
