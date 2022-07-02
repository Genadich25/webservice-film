package com.webservicewithapi.webservicefilm.repositories;

import com.webservicewithapi.webservicefilm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
