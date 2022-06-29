package com.bosonit.EJ2.application.usecase;

import com.bosonit.EJ2.application.port.DeletePersonPort;
import com.bosonit.EJ2.domain.PersonaEnt;
import com.bosonit.EJ2.exceptions.NotFoundException;
import com.bosonit.EJ2.infraestructure.Repository.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletePersonUseCase implements DeletePersonPort {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    GetPersonUseCase getPersonUseCase;
    @Autowired
    ModelMapper modelMapper;

    public String deletePerson(Integer id){
        try {
            PersonaEnt personaEnt = modelMapper.map(getPersonUseCase.getPersonaByID(id) , PersonaEnt.class);
            personaRepository.delete(personaEnt);
            return "Persona eliminada";
        } catch (Exception e) {
            throw new NotFoundException("El ususario: " + id + " no existe y no se puede eliminar");
        }
    }
}
