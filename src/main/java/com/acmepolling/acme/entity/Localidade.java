package com.acmepolling.acme.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class Localidade{

    @Id 
    @GeneratedValue(strategy=GenerationType.UUID)
    UUID id;

    private String cidade;
    private String estado;
}