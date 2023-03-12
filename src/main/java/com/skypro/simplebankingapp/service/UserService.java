package com.skypro.simplebankingapp.service;

import com.skypro.simplebankingapp.dto.UserDTO;
import com.skypro.simplebankingapp.dto.request.CreateUserRequest;
import com.skypro.simplebankingapp.exception.InvalidPasswordException;
import com.skypro.simplebankingapp.exception.UserAlreadyExistsException;
import com.skypro.simplebankingapp.exception.UserNotFoundException;
import com.skypro.simplebankingapp.model.Account;
import com.skypro.simplebankingapp.model.Currency;
import com.skypro.simplebankingapp.model.User;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final Map<String, User> mapOfUsers = new HashMap<>();

    public UserDTO addUser(CreateUserRequest userRequest) {
        if (mapOfUsers.containsKey(userRequest.userName())) {
            throw new UserAlreadyExistsException();
        }
        validateUsers(userRequest);
        User user = userRequest.toUser();
        mapOfUsers.put(user.getUserName(), user);
        return UserDTO.fromUser(createNewUserAccounts(user));
    }

    private void validateUsers(CreateUserRequest userRequest) {
    }

    private User createNewUserAccounts (User user) {
        user.addAccount(new Account(UUID.randomUUID().toString(), 0.0, Currency.RUB));
        user.addAccount(new Account(UUID.randomUUID().toString(), 0.0, Currency.EUR));
        user.addAccount(new Account(UUID.randomUUID().toString(), 0.0, Currency.USD));

        return user;
    }

    public User updateUser(String userName, String firstName, String lastName) {
        if (!mapOfUsers.containsKey(userName)) {
            throw new UserNotFoundException();
        }
        User user = mapOfUsers.get(userName);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
        }


    public void updatePassword(String userName, String userPassword, String newPassword) {
        if (!mapOfUsers.containsKey(userName)) {
            throw new UserNotFoundException();
        }
        User user = mapOfUsers.get(userName);
        if (!user.getPassword().equals(userPassword)) {
            throw new InvalidPasswordException();
        }
            user.setPassword(newPassword);
    }

    public User removeUser(String userName) {
        if (!mapOfUsers.containsKey(userName)) {
            throw new UserNotFoundException();
        }
        return mapOfUsers.remove(userName);
    }

    public User getUser(String userName) {
        if (!mapOfUsers.containsKey(userName)) {
            throw new UserNotFoundException();
        }
        return mapOfUsers.get(userName);
    }

    public Collection<UserDTO> getAllUser() {
        return mapOfUsers.values().stream().map(UserDTO::fromUser).collect(Collectors.toList());
    }

}

