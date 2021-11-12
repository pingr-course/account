package com.pingr.accounts.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        if (account == null) throw new IllegalStateException("conta não pode ser nula");

        try {
            return this.accountRepository.save(account);
        } catch (Exception e) {
            throw new IllegalStateException("conta inválida para criação");
        }
    }

    public List<AccountIdAndUsername> searchByUsernameAlike(String usernameAlike) {
        if (usernameAlike.length() == 0) throw new IllegalStateException("termo de busca não pode ser vazio");

        return this.accountRepository.searchByUsernameAlike(usernameAlike);
    }
}
