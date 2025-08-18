package com.example.axam.querys.entities;

import java.sql.Date;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Etudiant {
    @Id
  private String id;
    private String nom;
    private String prenom;
    private String email;
    private Instant createdAt;
}
