package com.bosonit.EJ2.application.port;

import com.bosonit.EJ2.domain.PersonaEnt;
import com.bosonit.EJ2.infraestructure.DTOs.OutPutPersonaDTO;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface GetPersonPort {

    public OutPutPersonaDTO getPersonaByID(Integer id) throws Exception;

    public List <OutPutPersonaDTO> getPersonByName(String name);

    public List<OutPutPersonaDTO> getAllPerson();

    public Page<PersonaEnt> getAllPersonPage(Integer pageNumber, Integer pageSize);

    public List<PersonaEnt> getData(HashMap<String, Object> conditions);


}
