package com.example.axam.querys.service;

import java.util.List;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.axam.commands.events.CreatedEtudiantEvent;
import com.example.axam.commands.events.DeletedEtudiantEvent;
import com.example.axam.commands.events.UpdatedEtudiantEvent;
import com.example.axam.querys.entities.Etudiant;
import com.example.axam.querys.queries.GetAllEtudiant;
import com.example.axam.querys.queries.GetEtudiant;
import com.example.axam.querys.repository.EtudiantRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class EtudiantEventHandlerService {

    private final EtudiantRepository etudiantRepository;

    @EventHandler
    public void on(CreatedEtudiantEvent event, EventMessage<CreatedEtudiantEvent> eventMessage) {
        log.info("==========================================================================");
        log.info("Handling CreatedEtudiantEvent for etudiant with id: {}", event.getId());

        Etudiant etudiant = new Etudiant();
        etudiant.setId(event.getId());
        etudiant.setNom(event.getNom());
        etudiant.setPrenom(event.getPrenom());
        etudiant.setEmail(event.getEmail());
        etudiant.setCreatedAt(eventMessage.getTimestamp());

        etudiantRepository.save(etudiant);
    }
       @EventHandler
    public void on(DeletedEtudiantEvent event, EventMessage<DeletedEtudiantEvent> eventMessage) {
       log.info("==========================================================================");
       log.info("Handling DeletedEtudiantEvent for etudiant with id: {}", event.getId());

       etudiantRepository.deleteById(event.getId());
    }
       @EventHandler
    public void on(UpdatedEtudiantEvent event, EventMessage<UpdatedEtudiantEvent> eventMessage) {
        log.info("==========================================================================");
        log.info("Handling UpdatedEtudiantEvent for etudiant with id: {}", event.getId());

        Etudiant etudiant = etudiantRepository.findById(event.getId()).get();
        if (etudiant==null) {
            log.warn("Etudiant with id {} not found for update", event.getId());
            return;
            
        }
        if (etudiant != null) {
            etudiant.setNom(event.getNom());
            etudiant.setPrenom(event.getPrenom());
            etudiant.setEmail(event.getEmail());
            etudiant.setCreatedAt(eventMessage.getTimestamp());
        }

        etudiantRepository.save(etudiant);
    }

    @QueryHandler
    public List<Etudiant>  on(GetAllEtudiant event) {
        log.info("==========================================================================");
        log.info("Handling GetAllEtudiant query");

        // Fetch all etudiants from the repository
       return etudiantRepository.findAll();
    }
     @QueryHandler
    public Etudiant on(GetEtudiant event) {
        log.info("==========================================================================");
        log.info("Handling GetEtudiant query for id: {}", event.getId());

        // Fetch etudiant by id from the repository
       return etudiantRepository.findById(event.getId()).orElse(null)   ;
    }


}
