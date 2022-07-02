package com.webservicewithapi.webservicefilm.services.interfaces;

import com.webservicewithapi.webservicefilm.models.User;

public interface UserService {
    public User registerUser(String username, String email, String name);

    public User getInfoUser(Long userId);

    public User editUser(Long userId, String username, String name);

    public void removeUser(Long userId);
}
