package com.chatapp.services.impl;

import com.chatapp.model.User;
import com.chatapp.repos.UserRepo;
import com.chatapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;
    @Override
    public boolean authenticate(String email,String password){
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getPassword().equals(password);
        }
        return false;
    }
    public void saveUser(User user ){

        this.userRepo.save(user);
    }
}
