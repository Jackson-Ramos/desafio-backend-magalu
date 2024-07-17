package com.jcode_development.magalums.repository;

import com.jcode_development.magalums.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, String> {

    @Query("SELECT u FROM User u WHERE u.login =: login")
    UserDetails loadUserByLogin(@Param("login") String login);
}
