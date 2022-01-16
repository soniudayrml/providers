package spring.ezservice.providers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.ezservice.providers.models.User;
import spring.ezservice.providers.repositories.UserRepository;

import java.util.NoSuchElementException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public boolean isUserNameExists(String userName)
    {
        try {
            User user = userRepository.findByUserName(userName).get();
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }
}
