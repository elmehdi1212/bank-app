package com.bank.controller;

import com.bank.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    @Autowired
    private AccountService account;

    @PostMapping("/deposit")
    public void deposit(@RequestParam int amount) {
        account.deposit(amount);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestParam int amount) {
        account.withdraw(amount);
    }

    @GetMapping("/statement")
    public String printStatement() {
        return account.printStatement();
    }
}