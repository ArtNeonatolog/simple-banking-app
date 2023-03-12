package com.skypro.simplebankingapp.dto;
import com.skypro.simplebankingapp.model.Account;
import com.skypro.simplebankingapp.model.Currency;

public record AccountDTO (String accountNumber, Currency currency, double amount) {
    public static AccountDTO fromAccount (Account account) {
        return new AccountDTO(account.getAccountNumber(), account.getCurrency(), account.getBalance());
    }
}
