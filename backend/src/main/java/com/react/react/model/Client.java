package com.react.react.model;

import lombok.Getter;
import lombok.Setter;
import org.yaml.snakeyaml.tokens.Token;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Token.ID id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;
}
