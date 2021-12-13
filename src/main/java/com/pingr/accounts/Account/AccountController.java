package com.pingr.accounts.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // C - POST
    // R - GET
    // U - PUT/PATCH
    // D - DELETE

    @PostMapping // POST /api/v1/accounts
    public Account createAccount(@RequestBody Account account) {
        return this.accountService.createAccount(account);
    }

    // query string
    // GET /api/v1/account?username=joao
    @GetMapping
    public List<AccountIdAndUsername> searcByUsernameAlike(@RequestParam("usernameAlike") String usernameAlike) {
        return this.accountService.searchByUsernameAlike(usernameAlike);
    }

    @PostMapping(path = "/{id}/courses")
    public void addCourse(@PathVariable("id") Long id, @RequestBody Long courseId) {
        this.accountService.addCourses(id, courseId);
    }

    @GetMapping(path = "/{id}")
    public Optional<Account> getOne(@PathVariable("id") Long id) {
        return this.accountService.findOne(id);
    }
}
