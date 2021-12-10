package com.pingr.accounts.Account;

import com.pingr.accounts.Account.exceptions.InvalidAccountCreationException;
import com.pingr.accounts.Account.exceptions.InvalidArgumentsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final ProducerService producerService;

    @Autowired
    public AccountService(
            AccountRepository accountRepository,
            ProducerService producerService
    ) {
        this.accountRepository = accountRepository;
        this.producerService = producerService;
    }

    public Account createAccount(Account account) {
        if (account == null) throw new InvalidAccountCreationException("conta não pode ser nula");

        try {
            Account savedAccount = this.accountRepository.save(account);
            this.producerService.sendMessage(savedAccount);
            return savedAccount;
        } catch (Exception e) {
            throw new InvalidAccountCreationException("conta inválida para criação");
        }
    }

    public List<AccountIdAndUsername> searchByUsernameAlike(String usernameAlike) {
        if (usernameAlike.length() == 0) throw new InvalidArgumentsException("termo de busca vazio");

        return this.accountRepository.searchByUsernameAlike(usernameAlike);
    }
}
