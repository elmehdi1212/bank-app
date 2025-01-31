package com.bank.services.impl;

import com.bank.services.AccountService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class Account implements AccountService {
    private int balance = 0;
    private List<Transaction> transactions = new ArrayList<>();

    @Override
    public void deposit(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        transactions.add(new Transaction(LocalDate.now(), amount, balance));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance -= amount;
        transactions.add(new Transaction(LocalDate.now(), -amount, balance));
    }

    @Override
    public String printStatement() {
        List<Transaction> reversedTransactions = new ArrayList<>(transactions);
        Collections.reverse(reversedTransactions);
        StringBuilder statement = new StringBuilder("Date       | Amount | Balance\n");
        for (Transaction t : reversedTransactions) {
            statement.append(t.getDate()).append(" | ").append(t.getAmount()).append(" | ").append(t.getBalance()).append("\n");
        }
        return statement.toString();
    }

    private static class Transaction {
        private LocalDate date;
        private int amount;
        private int balance;

        public Transaction(LocalDate date, int amount, int balance) {
            this.date = date;
            this.amount = amount;
            this.balance = balance;
        }

        public LocalDate getDate() {
            return date;
        }

        public int getAmount() {
            return amount;
        }

        public int getBalance() {
            return balance;
        }
    }
}
