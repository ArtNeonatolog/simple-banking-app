package com.skypro.simplebankingapp.dto;

import com.skypro.simplebankingapp.model.Account;
import com.skypro.simplebankingapp.model.User;
import java.util.List;
import java.util.stream.Collectors;

public record UserDTO (String username, String firstname, String lastname, List<String> accounts) {
    public static UserDTO fromUser (User user) {
        return new UserDTO(user.getUserName(), user.getFirstName(), user.getLastName(), user.getAccountList().stream()
                .map(Account::getAccountNumber).collect(Collectors.toList()));
    }
}
