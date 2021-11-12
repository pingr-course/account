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

        Optional<Account> existingUsernameOptional = this.accountRepository.findAccountByUsername(account.getUsername());

        if (existingUsernameOptional.isPresent()) {
            throw new IllegalStateException("conta com username " + account.getUsername() + " já existe");
        }

        Optional<Account> existingEmailOptional = this.accountRepository.findAccountByEmail(account.getEmail());

        if (existingEmailOptional.isPresent()) {
            throw new IllegalStateException("conta com email " + account.getEmail() + " já existe");
        }

        return this.accountRepository.save(account);
    }

    public List<AccountIdAndUsername> searchByUsernameAlike(String usernameAlike) {
        if (usernameAlike.length() == 0) throw new IllegalStateException("termo de busca não pode ser vazio");

        return this.accountRepository.searchByUsernameAlike(usernameAlike);
    }
}
