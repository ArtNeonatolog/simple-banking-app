package com.skypro.simplebankingapp.service;
import com.skypro.simplebankingapp.dto.AccountDTO;
import com.skypro.simplebankingapp.exception.AccountNotFoundException;
import com.skypro.simplebankingapp.exception.InsufficientFundsException;
import com.skypro.simplebankingapp.exception.InvalidChangeAmountException;
import com.skypro.simplebankingapp.model.Account;
import com.skypro.simplebankingapp.model.User;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final UserService userService;

    public AccountService(UserService userService) {
        this.userService = userService;
    }

    public Account changeBalance(String username, String accountNumber, Operation operation, double amount) {
        if (amount <= 0) {
            throw new InvalidChangeAmountException();
        }
        User user = userService.getUser(username);
        Account account = user.getAccountList().stream().filter(e -> e.getAccountNumber().equals(accountNumber)).findFirst().orElseThrow(RuntimeException::new);
        if (operation.equals(Operation.DEPOSIT)) {
            return depositONAccount(account, amount);
        } else {
            return withdrawFromAccount(account, amount);
        }
    }

    public AccountDTO getAccount(String userName, String accountNumber) {
        User user = userService.getUser(userName);
        return user.getAccountList().stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber))
                .findFirst()
                .map(AccountDTO::fromAccount)
                .orElseThrow(AccountNotFoundException::new);
    }

    private Account withdrawFromAccount(Account account, double amount) {
        if (account.getBalance() < amount) {
            throw new InsufficientFundsException();
        } else {
            account.setBalance(account.getBalance() - amount);
            return account;
        }
    }

    private Account depositONAccount(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
        return account;
    }

}
