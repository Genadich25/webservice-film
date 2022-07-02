package com.webservicewithapi.webservicefilm.services;

import com.webservicewithapi.webservicefilm.WebserviceFilmApplication;
import com.webservicewithapi.webservicefilm.exceptions.NotValidEmailOrUsernameException;
import com.webservicewithapi.webservicefilm.models.User;
import com.webservicewithapi.webservicefilm.repositories.UserRepository;
import com.webservicewithapi.webservicefilm.services.interfaces.UserService;
import com.webservicewithapi.webservicefilm.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String username, String email, String name){
        if(Utils.validateUsername(username) && Utils.validateEmail(email)){
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setName(name);
            userRepository.save(user);
            WebserviceFilmApplication.log.info("User:{} registered", user);
            return user;
        } else {
            WebserviceFilmApplication.log.error("Not valid email:{} or username:{}", email,username);
            throw new NotValidEmailOrUsernameException();
        }
    }

    @Override
    public User getInfoUser(Long userId){
        return userRepository.findById(userId).get();
    }

    @Override
    public User editUser(Long userId, String username, String name) {
        User user = getInfoUser(userId);
        if(username != null){
            if(!Utils.validateUsername(username)){
                WebserviceFilmApplication.log.error("Not valid  username:{}", username);
                throw new NotValidEmailOrUsernameException();
            }
            WebserviceFilmApplication.log.info("Username:{} updated", username);
            user.setUsername(username);
        }
        if(name != null){
            WebserviceFilmApplication.log.info("Name:{} updated", name);
            user.setName(name);
        }
        userRepository.save(user);
        WebserviceFilmApplication.log.info("User:{} updated", user);
        return user;
    }

    @Override
    public void removeUser(Long userId) {
        userRepository.deleteById(userId);
        WebserviceFilmApplication.log.info("User with Id:{} deleted",userId);
    }
}
