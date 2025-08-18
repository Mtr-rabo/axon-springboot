package com.example.axam.commands;

import lombok.Getter;

public class UpdateEtudiantCommand extends BaseCommand<String> {
    @Getter private String nom;
    @Getter private String prenom;
    @Getter private String email;

    public UpdateEtudiantCommand(String id, String nom, String prenom, String email) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email; 
    }
}
