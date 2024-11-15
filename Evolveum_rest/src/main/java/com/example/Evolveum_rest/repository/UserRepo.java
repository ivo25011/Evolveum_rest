package com.example.Evolveum_rest.repository;

import com.example.Evolveum_rest.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users,String> {

    Optional<Users> getUserByName(String name);
}
