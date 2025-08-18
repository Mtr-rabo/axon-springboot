package com.example.axam.commands.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.axam.commands.CreateEtudiantCommand;
import com.example.axam.commands.DeleteEtudiantCommand;
import com.example.axam.commands.UpdateEtudiantCommand;
import com.example.axam.commons.EtudiantDTO;

import lombok.AllArgsConstructor;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/command/etudiant")
@AllArgsConstructor
public class EtudiantCommandControler {

private final CommandGateway commandGateway;

  

    @PostMapping("/create")    
    public CompletableFuture<String> createNewEtudiant(@RequestBody EtudiantDTO etdDto){


        return commandGateway.send(new CreateEtudiantCommand(
            UUID.randomUUID().toString(),
            etdDto.getNom(),
            etdDto.getPrenom(),
            etdDto.getEmail()
        ));

    }

    @PutMapping("/update")
    public CompletableFuture<String> updateEtudiant(@RequestBody EtudiantDTO etdDto) {
        return commandGateway.send(new UpdateEtudiantCommand(
            etdDto.getId(),
            etdDto.getNom(),
            etdDto.getPrenom(),
            etdDto.getEmail()
        ));
    }

       @DeleteMapping("/delete/{id}")
    public CompletableFuture<String> deleteEtudiant(@PathVariable String id) {
        return commandGateway.send(new DeleteEtudiantCommand(
            id
        ));
    }       
  

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> exceptionHandler(Exception e) {
       return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
