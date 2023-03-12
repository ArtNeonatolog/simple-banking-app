package com.skypro.simplebankingapp.dto.request;

public record TransferRequest(String userFrom, String accountFrom, String userTo, String accountTo, double amount) {

}
