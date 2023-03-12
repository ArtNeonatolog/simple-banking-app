package com.skypro.simplebankingapp.dto.request;
import com.skypro.simplebankingapp.model.User;

public record CreateUserRequest (String firstName, String lastName, String userName, String password, String repeatPassword) {
    public User toUser() {
        return new User(this.firstName, this.lastName, this.userName, this.password);
    }
}
