package com.example.Evolveum_rest.service;

import com.example.Evolveum_rest.model.Policy;
import com.example.Evolveum_rest.model.Users;
import com.example.Evolveum_rest.repository.PolicyRepo;
import com.example.Evolveum_rest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PolicyRepo policyRepo;

    public ResponseEntity<String> getUserByName(String name){
        Optional<Users> user = userRepo.findById(name);
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with name '" + user + "' not found.");
        }
        return ResponseEntity.ok("User found: '" + user.get()+ "'");
    }

    public ResponseEntity<String> createUser(Users user) {
        if (userRepo.existsById(user.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("User with name '" + user.getName() + "' already exists.");
        }
        applyPolicies(user);
        userRepo.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User with name '" + user.getName() + "' has been created.");
    }

    public ResponseEntity<String> updateUser(String name, Users updatedUser) {
        Optional<Users> user = userRepo.findById(name);
        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with name '" + name + "' not found.");
        }
        Users existingUser = user.get();
        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmailAddress(updatedUser.getEmailAddress());
        existingUser.setOrganizationUnit(updatedUser.getOrganizationUnit());
        existingUser.setBirthDate(updatedUser.getBirthDate());
        existingUser.setRegisteredOn(LocalDate.now());

        applyPolicies(existingUser);
        userRepo.save(existingUser);
        return ResponseEntity.ok("User with name '" + name + "' has been updated.");
    }

    public ResponseEntity<String> deleteUser(String name) {
        Optional<Users> user = userRepo.getUserByName(name);

        if (!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with name: '" + name+ "'");
        }
        userRepo.delete(user.get());
        return ResponseEntity.status(HttpStatus.OK).body("User with '" + name + "' has been deleted.");

    }

    private void applyPolicies(Users user) {
        List<Policy> policies = policyRepo.findAll();
        List<String> applicablePolicies = new ArrayList<>();

        for (Policy policy : policies) {
            if (isPolicyApplicable(user, policy)) {
                applicablePolicies.add(policy.getId());
            }
        }

        user.setPolicy(applicablePolicies);
    }

    private boolean isPolicyApplicable(Users user, Policy policy) {
        String conditionType = policy.getConditionType();
        String conditionValue = policy.getConditionValue();

        switch (conditionType) {
            case "youngerThan":
                int age = Period.between(user.getBirthDate(), LocalDate.now()).getYears();
                return age < Integer.parseInt(conditionValue);

            case "emailDomainIs":
                return user.getEmailAddress().endsWith("@" + conditionValue);

            case "isMemberOf":
                return user.getOrganizationUnit().contains(conditionValue);

            default:
                return false;
        }
    }

}
