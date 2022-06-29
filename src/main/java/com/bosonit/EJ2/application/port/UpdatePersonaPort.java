package com.bosonit.EJ2.application.port;

import com.bosonit.EJ2.domain.PersonaEnt;
import com.bosonit.EJ2.infraestructure.DTOs.InputPersonaDTO;
import com.bosonit.EJ2.infraestructure.DTOs.OutPutPersonaDTO;

public interface UpdatePersonaPort {

    public OutPutPersonaDTO updatePerson(Integer id, InputPersonaDTO personaNew);
}
