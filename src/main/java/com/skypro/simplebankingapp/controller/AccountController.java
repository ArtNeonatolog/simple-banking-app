package com.skypro.simplebankingapp.controller;

import com.skypro.simplebankingapp.dto.AccountDTO;
import com.skypro.simplebankingapp.dto.request.BalanceChangeRequest;
import com.skypro.simplebankingapp.dto.request.TransferRequest;
import com.skypro.simplebankingapp.service.AccountService;
import com.skypro.simplebankingapp.service.Operation;
import com.skypro.simplebankingapp.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final TransferService transferService;

    public AccountController(AccountService accountService, TransferService transferService) {
        this.accountService = accountService;
        this.transferService = transferService;
    }

    @GetMapping("/")
    AccountDTO getBalance(@RequestParam("username") String username, @RequestParam("account") String account) {
        return accountService.getAccount(username, account);
    }


    @PostMapping("/transfer")
    ResponseEntity<?> transferMoney(@RequestBody TransferRequest transferRequest) {
        transferService.transferMoney(transferRequest);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/deposit")
    ResponseEntity<?> depositMoney(@RequestBody BalanceChangeRequest balanceChangeRequest) {
        accountService.changeBalance(balanceChangeRequest.username(), balanceChangeRequest.account(), Operation.DEPOSIT, balanceChangeRequest.amount());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/withdraw")
    ResponseEntity<?> withdrawMoney(@RequestBody BalanceChangeRequest balanceChangeRequest) {
        accountService.changeBalance(balanceChangeRequest.username(), balanceChangeRequest.account(), Operation.WITHDRAW, balanceChangeRequest.amount());
        return ResponseEntity.noContent().build();

    }
}
