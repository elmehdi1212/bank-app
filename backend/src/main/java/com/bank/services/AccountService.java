package com.bank.services;

public interface AccountService {
    void deposit(int amount);
    void withdraw(int amount);
    String printStatement();
}