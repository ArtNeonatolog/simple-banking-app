package com.skypro.simplebankingapp.dto.request;

public record ChangePasswordRequest (String userName, String oldPassword, String newPassword) {
}
