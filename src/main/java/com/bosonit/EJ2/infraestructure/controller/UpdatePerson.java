package com.bosonit.EJ2.infraestructure.controller;

import com.bosonit.EJ2.exceptions.UnprocesableException;
import com.bosonit.EJ2.application.port.UpdatePersonaPort;
import com.bosonit.EJ2.domain.PersonaEnt;
import com.bosonit.EJ2.application.usecase.GetPersonUseCase;
import com.bosonit.EJ2.infraestructure.DTOs.InputPersonaDTO;
import com.bosonit.EJ2.infraestructure.DTOs.OutPutPersonaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")

public class UpdatePerson {

    @Autowired
    UpdatePersonaPort updatePersonaPort;

    @PutMapping("/update/{id}")
    public OutPutPersonaDTO updatePersona(@PathVariable Integer id, @RequestBody InputPersonaDTO inputPersonaDTO) throws Exception {
        return updatePersonaPort.updatePerson(id, inputPersonaDTO);
    }
}

