package com.example.axam.commands;

import lombok.Getter;

public class CreateEtudiantCommand extends BaseCommand<String> {
    @Getter private String nom;
    @Getter private String prenom;
    @Getter private String email;
    public CreateEtudiantCommand(String id, String nom, String prenom, String email) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email; 
    }

}
