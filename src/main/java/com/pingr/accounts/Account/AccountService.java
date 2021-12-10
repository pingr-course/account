package com.pingr.accounts.Account;

import com.pingr.accounts.Account.exceptions.InvalidAccountCreationException;
import com.pingr.accounts.Account.exceptions.InvalidArgumentsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final CourseService courseService;

    @Autowired
    public AccountService(AccountRepository accountRepository, CourseService courseService) {
        this.accountRepository = accountRepository;
        this.courseService = courseService;
    }

    public Account createAccount(Account account) {
        if (account == null) throw new InvalidAccountCreationException("conta não pode ser nula");

        try {
            return this.accountRepository.save(account);
        } catch (Exception e) {
            throw new InvalidAccountCreationException("conta inválida para criação");
        }
    }

    public List<AccountIdAndUsername> searchByUsernameAlike(String usernameAlike) {
        if (usernameAlike.length() == 0) throw new InvalidArgumentsException("termo de busca vazio");

        return this.accountRepository.searchByUsernameAlike(usernameAlike);
    }

    public Optional<Account> findOne(Long id) {
        return this.accountRepository.findById(id);
    }

    public void addCourses(String name, Long accountID) {
        List<Course> courses = this.courseService.find(name);
        Optional<Account> optionalAccount = this.accountRepository.findById(accountID);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            System.out.println(courses);
            for (Course course: courses) {
                account.addCourse(course);
            }
            this.accountRepository.save(account);
        } else {
            throw new RuntimeException("Conta " + accountID + " não encontrada");
        }

    }
}
