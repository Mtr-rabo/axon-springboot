package com.example.axam.querys.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.axam.querys.entities.Etudiant;
import com.example.axam.querys.queries.GetAllEtudiant;
import com.example.axam.querys.queries.GetEtudiant;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/query/etudiants")
public class EtudiantQueryControler {

    private QueryGateway queryGateway;

    @GetMapping("/all")
    public CompletableFuture<List<Etudiant>>  getAllEtudiants() {
      return queryGateway.query(new GetAllEtudiant(),ResponseTypes.multipleInstancesOf(Etudiant.class))
            .exceptionally(ex -> {
                throw new RuntimeException("Error fetching etudiants", ex);
            });
        
    }
    @GetMapping("/{id}")
    public CompletableFuture<Etudiant> getEtudiantById(@PathVariable String id) {
        return queryGateway.query(new GetEtudiant(id), ResponseTypes.instanceOf(Etudiant.class))
                .exceptionally(ex -> {
                    throw new RuntimeException("Error fetching etudiant", ex);
                });
    }

}
