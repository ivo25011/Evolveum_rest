package com.example.Evolveum_rest.repository;

import com.example.Evolveum_rest.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepo extends JpaRepository<Policy,String> {
}
