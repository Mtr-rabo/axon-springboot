package com.example.axam.commands.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import com.example.axam.commands.CreateEtudiantCommand;
import com.example.axam.commands.DeleteEtudiantCommand;
import com.example.axam.commands.UpdateEtudiantCommand;
import com.example.axam.commands.events.CreatedEtudiantEvent;
import com.example.axam.commands.events.DeletedEtudiantEvent;
import com.example.axam.commands.events.UpdatedEtudiantEvent;


@Aggregate
public class EtudiantAggregate {
    @AggregateIdentifier
    private String id;
    private String nom;
    private String prenom;
    private String email;

    public EtudiantAggregate() {
        // Default constructor for Axon framework
    }
    @CommandHandler
    public EtudiantAggregate( CreateEtudiantCommand createEtudiantCommand) {

       
        AggregateLifecycle.apply(
            new CreatedEtudiantEvent(
                createEtudiantCommand.getId(),
                createEtudiantCommand.getNom(),
                createEtudiantCommand.getPrenom(),
                createEtudiantCommand.getEmail()
            )
        );

        

    }
    
    @EventSourcingHandler
    public void on(CreatedEtudiantEvent event) {
        this.id = event.getId();
        this.nom = event.getNom();
        this.prenom = event.getPrenom();
        this.email = event.getEmail();
    }

     @CommandHandler
    public void handler( UpdateEtudiantCommand updateEtudiantCommand) {

        AggregateLifecycle.apply(
            new UpdatedEtudiantEvent(
                updateEtudiantCommand.getId(),
                updateEtudiantCommand.getNom(),
                updateEtudiantCommand.getPrenom(),
                updateEtudiantCommand.getEmail()
            )
        );

    }
    @EventSourcingHandler
    public void on(UpdatedEtudiantEvent event) {
        this.id = event.getId();
        this.nom = event.getNom();
        this.prenom = event.getPrenom();
        this.email = event.getEmail();
    }

   @CommandHandler
   public void handle(DeleteEtudiantCommand deleteEtudiantCommand) {
       AggregateLifecycle.apply(
           new DeletedEtudiantEvent(
               deleteEtudiantCommand.getId()
           )
       );
   }

   @EventSourcingHandler
   public void on(DeletedEtudiantEvent event) {
       this.id = event.getId();
       this.nom = null;
       this.prenom = null;
       this.email = null;
   }

}
