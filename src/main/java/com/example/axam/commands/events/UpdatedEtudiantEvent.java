package com.example.axam.commands.events;

import com.example.axam.commons.events.BaseEvent;

import lombok.Getter;

public class UpdatedEtudiantEvent extends BaseEvent<String> {

    @Getter private String nom;
    @Getter private String prenom;
    @Getter private String email;

    public UpdatedEtudiantEvent(String id, String nom, String prenom, String email) {
        super(id);
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
}
