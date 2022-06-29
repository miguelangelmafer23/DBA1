package com.bosonit.EJ2.infraestructure.controller;

import com.bosonit.EJ2.exceptions.UnprocesableException;
import com.bosonit.EJ2.application.port.CreatePersonPort;
import com.bosonit.EJ2.domain.PersonaEnt;
import com.bosonit.EJ2.application.usecase.GetPersonUseCase;
import com.bosonit.EJ2.infraestructure.DTOs.InputPersonaDTO;
import com.bosonit.EJ2.infraestructure.DTOs.OutPutPersonaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/person")
public class CreatePerson {

    @Autowired
    CreatePersonPort createPersonPort;

    @PostMapping("/add")
    public OutPutPersonaDTO addPersona(@RequestBody InputPersonaDTO inputPersonaDTO) throws Exception {
      return createPersonPort.addPersona(inputPersonaDTO);
    }
}
