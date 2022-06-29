package com.bosonit.EJ2.infraestructure.controller;


import com.bosonit.EJ2.exceptions.NotFoundException;
import com.bosonit.EJ2.application.port.DeletePersonPort;
import com.bosonit.EJ2.domain.PersonaEnt;
import com.bosonit.EJ2.application.usecase.GetPersonUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class DeletePerson {

    @Autowired
    DeletePersonPort deletePersonPort;

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable Integer id) throws Exception {
       return  deletePersonPort.deletePerson(id);
    }
}