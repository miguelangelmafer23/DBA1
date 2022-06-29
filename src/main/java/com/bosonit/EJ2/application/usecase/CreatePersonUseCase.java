package com.bosonit.EJ2.application.usecase;


import com.bosonit.EJ2.application.port.CreatePersonPort;
import com.bosonit.EJ2.domain.PersonaEnt;
import com.bosonit.EJ2.exceptions.NotFoundException;
import com.bosonit.EJ2.exceptions.UnprocesableException;
import com.bosonit.EJ2.infraestructure.DTOs.InputPersonaDTO;
import com.bosonit.EJ2.infraestructure.DTOs.OutPutPersonaDTO;
import com.bosonit.EJ2.infraestructure.Repository.PersonaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePersonUseCase implements CreatePersonPort {

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    ModelMapper modelMapper;

    public OutPutPersonaDTO addPersona(InputPersonaDTO inputPersonaDTO) throws Exception{

        PersonaEnt personaEnt = modelMapper.map(inputPersonaDTO, PersonaEnt.class);

        if(personaEnt.getUsuario().length()>10){
            throw new UnprocesableException("El usuario no debe tener más de 10 caracteres");}
        if(personaEnt.getUsuario() == null){
            throw new UnprocesableException("El usuario no puede ser nulo");}
        if(personaEnt.getPassword() == null){
            throw new UnprocesableException("La password no puede ser nula");}
        if(personaEnt.getName() == null){
            throw new UnprocesableException("El nombre no puede ser nulo");}
        if(personaEnt.getCompany_email() == null){
            throw new UnprocesableException("El email_company no puede ser nulo");}
        if(personaEnt.getPersona_email() == null){
            throw new UnprocesableException("El email_personal no puede ser nulo");}
        if(personaEnt.getCity() == null){
            throw new UnprocesableException("La city no puede ser nula");}
        if(personaEnt.getActive() == null){
            throw new UnprocesableException("El campo activo no puede ser nulo");}
        if (personaEnt.getCreated_date() == null){
            throw new UnprocesableException("Created_date no puede ser nula");}
        else{
            personaRepository.save(personaEnt);
            return modelMapper.map(personaEnt,OutPutPersonaDTO.class);}
    }
}
