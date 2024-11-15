package com.example.Evolveum_rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Policy {

    @Id
    private String id;
    private String name;
    private String conditionType;
    private String conditionValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConditionType() {
        return conditionType;
    }

    public void setConditionType(String conditionType) {
        this.conditionType = conditionType;
    }

    public String getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(String conditionValue) {
        this.conditionValue = conditionValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("    \"id\": \"").append(id).append("\",\n");
        sb.append("    \"name\": \"").append(name).append("\",\n");
        sb.append("    \"conditionType\": \"").append(conditionType).append("\",\n");
        sb.append("    \"conditionValue\": ").append(conditionValue).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
