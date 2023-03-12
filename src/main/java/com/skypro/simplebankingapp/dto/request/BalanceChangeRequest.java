package com.skypro.simplebankingapp.dto.request;

public record BalanceChangeRequest (String username, String account, double amount) {
}
