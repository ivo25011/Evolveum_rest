package com.example.Evolveum_rest.service;

import com.example.Evolveum_rest.model.Policy;
import com.example.Evolveum_rest.repository.PolicyRepo;
import com.example.Evolveum_rest.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepo policyRepo;

    @Autowired
    private UserRepo userRepo;

    public List<Policy> getAllPolicies() {
        return policyRepo.findAll();
    }

    public ResponseEntity<String> getPolicyById(String policyId) {
        Optional<Policy> policy = policyRepo.findById(policyId);
        if (!policy.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Policy with ID '" + policyId + "' not found.");
        }
        return ResponseEntity.ok(policy.get().toString());
    }

    public ResponseEntity<String> createPolicy(Policy policy) {
        if (policyRepo.existsById(policy.getId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Policy with ID '" + policy.getId() + "' already exists.");
        }

        policyRepo.save(policy);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Policy with ID '" + policy.getId() + "' has been created.");
    }

    public ResponseEntity<String> updatePolicy(String policyId, Policy updatedPolicy) {
        Optional<Policy> existingPolicy = policyRepo.findById(policyId);

        if(!existingPolicy.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Policy with ID '" + policyId + "' not found.");
        }
        Policy policy = existingPolicy.get();
        policy.setName(updatedPolicy.getName());
        policy.setConditionType(updatedPolicy.getConditionType());
        policy.setConditionValue(updatedPolicy.getConditionValue());
        policyRepo.save(policy);
        return ResponseEntity.status(HttpStatus.OK).body("Policy with ID '" + policyId + "' has been updated.");
    }

    public ResponseEntity<String> deletePolicy(String policyId) {
        Optional<Policy> policy = policyRepo.findById(policyId);

        if (!policy.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Policy with ID '" + policyId + "' not found.");
        }
        policyRepo.delete(policy.get());
        return ResponseEntity.status(HttpStatus.OK).body("Policy with ID '" + policyId + "' has been deleted.");
    }
}
