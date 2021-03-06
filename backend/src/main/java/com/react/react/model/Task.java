package com.react.react.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Setter
    private String name;


    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Boolean done = false;

    public Task(String name, String description, Boolean done) {
        this.name = name;
        this.description = description;
        this.done = done;
    }

    public Task() {
    }
}
