package com.userFront.service;

import com.userFront.domain.PrimaryAccount;
import com.userFront.domain.SavingsAccount;

import java.security.Principal;

public interface AccountService {
    SavingsAccount createSavingsAccount();

    PrimaryAccount createPrimaryAccount();

    void deposit(String accountType, double amount, Principal principal);

    void withdraw(String accountType, double amount, Principal principal);
}
