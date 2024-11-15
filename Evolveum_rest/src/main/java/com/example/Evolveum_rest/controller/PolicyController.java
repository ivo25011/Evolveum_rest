package com.example.Evolveum_rest.controller;

import com.example.Evolveum_rest.model.Policy;
import com.example.Evolveum_rest.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    PolicyService policyService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getPolicy(@PathVariable String id) {
        return policyService.getPolicyById(id);
    }

    @GetMapping
    public List<Policy> getAllPolicies() {
        return policyService.getAllPolicies();
    }
    @PostMapping
    public ResponseEntity<String> createPolicy(@RequestBody Policy policy) {
        return policyService.createPolicy(policy);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePolicy(@PathVariable String id, @RequestBody Policy updatedPolicy) {
        return policyService.updatePolicy(id, updatedPolicy);
    }

    @DeleteMapping("/{policyId}")
    public ResponseEntity<String> deletePolicy(@PathVariable String policyId) {
        return policyService.deletePolicy(policyId);
    }
}
