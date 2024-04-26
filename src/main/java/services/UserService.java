package services;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import repos.UserRepo;

public class UserService {
    @Autowired
    private UserRepo userRepo;

    public boolean authenticate(String email,String password){
        User user = userRepo.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }
}
