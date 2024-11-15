package com.example.Evolveum_rest.model;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Users {

    @Id
    private String name;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private List<String> organizationUnit;
    private LocalDate birthDate;
    private LocalDate registeredOn;
    private List<String> policies;

    public Users() {
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOrganizationUnit() {
        return organizationUnit;
    }

    public void setOrganizationUnit(List<String> organizationUnit) {
        this.organizationUnit = organizationUnit;
    }

    public List<String> getPolicies() {
        return policies;
    }

    public void setPolicy(List<String> policies) {
        this.policies = policies;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("    \"name\": \"").append(name).append("\",\n");
        sb.append("    \"firstName\": \"").append(firstName).append("\",\n");
        sb.append("    \"lastName\": \"").append(lastName).append("\",\n");
        sb.append("    \"emailAddress\": \"").append(emailAddress).append("\",\n");
        sb.append("    \"organizationUnit\": ").append(organizationUnit).append(",\n");
        sb.append("    \"birthDate\": \"").append(birthDate).append("\",\n");
        sb.append("    \"registeredOn\": \"").append(registeredOn).append("\",\n");
        sb.append("    \"policies\": ").append(policies).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
