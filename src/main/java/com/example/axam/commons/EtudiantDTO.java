
package com.example.axam.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Builder @Getter @Setter
public class EtudiantDTO {

    private String id;
    private String nom;
    private String prenom;
    private String email;

    // Getters and Setters

}
