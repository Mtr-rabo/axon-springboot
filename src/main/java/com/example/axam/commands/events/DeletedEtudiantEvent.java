package com.example.axam.commands.events;

import com.example.axam.commons.events.BaseEvent;

public class DeletedEtudiantEvent extends BaseEvent<String> {
    public DeletedEtudiantEvent(String id) {
        super(id);
    }

}
