package com.chatapp.services;

import com.chatapp.model.User;
import com.chatapp.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface UserService {

    public boolean authenticate(String email,String password);

}
